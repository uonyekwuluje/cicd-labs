clean-image:
	docker system prune -a

create-image:
	docker-compose build

create-runner:
	docker-compose up -d runner-service --force-recreate --remove-orphans

scale-runner:
	docker-compose up -d --scale runner-service=5 --force-recreate --remove-orphans

delete-runner:
	docker-compose down

status:
	docker-compose ps
