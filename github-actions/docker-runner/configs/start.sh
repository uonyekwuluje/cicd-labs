#!/bin/bash

OWNER=$OWNER
ACCESS_TOKEN=$ACCESS_TOKEN
BUILD_LABELS=$BUILD_LABELS
REPOSITORY=$REPOSITORY

RUNNER_SUFFIX=$(cat /dev/urandom | tr -dc 'a-z0-9' | fold -w 5 | head -n 1)
RUNNER_NAME="Docker-Runner-Node-${RUNNER_SUFFIX}"

REG_TOKEN=$(curl -sX POST \
 -H "Accept: application/vnd.github.v3+json" \
 -H "Authorization: token ${ACCESS_TOKEN}" \
 https://api.github.com/repos/${OWNER}/${REPOSITORY}/actions/runners/registration-token \
 | jq .token --raw-output)

echo "======================================================"
echo "Owner: ${OWNER} "
echo "Repository: ${REPOSITORY}"
echo "Runner Labels: ${BUILD_LABELS}"
echo "Registration Token: ${REG_TOKEN}"
echo "======================================================"


cd /opt/actions-runner

./config.sh --unattended --replace \
--url https://github.com/${OWNER}/${REPOSITORY} \
--token ${REG_TOKEN} \
--labels ${BUILD_LABELS} \
--name ${RUNNER_NAME} 

cleanup() {
    echo "Removing runner..."
    ./config.sh remove --unattended --token ${REG_TOKEN}
}

trap 'cleanup; exit 130' INT
trap 'cleanup; exit 143' TERM

./run.sh & wait $!
