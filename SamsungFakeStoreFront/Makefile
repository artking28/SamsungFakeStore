APP_NAME=SamsungFakeStoreFront
WAR_FILE=target/$(APP_NAME)-1.0-SNAPSHOT.war
CONTAINER_PORT=4200:8080
CONTAINER_NAME=wildfly-server
CONTAINER_PASSWORD=root1234
DEPLOY_DIR=/opt/bitnami/wildfly/standalone/deployments/


.PHONY: build deploy clean_deploy container

all: deploy

container:
	@if [ "$$(docker ps -aq -f name=$(CONTAINER_NAME))" ]; then \
		docker start $(CONTAINER_NAME); \
	else \
		docker run -d --name $(CONTAINER_NAME) -p $(CONTAINER_PORT) -e WILDFLY_PASSWORD=$(CONTAINER_PASSWORD) bitnami/wildfly:27.0.1; \
	fi

build:
	clear
	mvn clean package

clean_deploy:
	docker exec -it $(CONTAINER_NAME) rm -f $(DEPLOY_DIR)*.war*

deploy: container build clean_deploy
	docker cp $(WAR_FILE) $(CONTAINER_NAME):$(DEPLOY_DIR)/
	docker restart $(CONTAINER_NAME)
