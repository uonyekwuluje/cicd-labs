FROM ubuntu:20.04

ARG DEBIAN_FRONTEND=noninteractive
ARG RUNNER_VERSION="2.294.0"

RUN apt update -y && apt upgrade -y && useradd -m ubuntu
RUN apt install -y software-properties-common curl wget jq build-essential unzip libssl-dev libffi-dev python3-pip \
    && pip3 install yq docker ansible --user

# Install Latest Git
RUN add-apt-repository ppa:git-core/ppa -y \
    && apt update && apt install -y git

# Install Github Actions
RUN mkdir /opt/actions-runner && cd /opt/actions-runner \
    && curl -O -L https://github.com/actions/runner/releases/download/v${RUNNER_VERSION}/actions-runner-linux-x64-${RUNNER_VERSION}.tar.gz \
    && tar xzf ./actions-runner-linux-x64-${RUNNER_VERSION}.tar.gz

RUN chown -R ubuntu:ubuntu /opt/actions-runner && /opt/actions-runner/bin/installdependencies.sh

USER ubuntu
WORKDIR /home/ubuntu
COPY --chown=ubuntu:ubuntu start.sh start.sh

RUN chmod u+x ./start.sh

ENTRYPOINT ["/home/ubuntu/start.sh"]
