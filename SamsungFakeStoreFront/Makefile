APP_NAME=SamsungFakeStoreFront
WAR_FILE=target/$(APP_NAME)-1.0-SNAPSHOT.war
CONTAINER_PORT=4200:8080
CONTAINER_NAME=wildfly-server
CONTAINER_PASSWORD=root1234

.PHONY: build deploy

all: deploy

container:
	docker run -d --name $(CONTAINER_NAME) -p $(CONTAINER_PORT) -e WILDFLY_PASSWORD=$(CONTAINER_PASSWORD) bitnami/wildfly:latest;

build:
	mvn clean package

clean_deploy:
	docker exec -it $(CONTAINER_NAME) rm -f $(DEPLOY_DIR)/*.war*

deploy: build clean_deploy
	docker cp $(WAR_FILE) $(CONTAINER_NAME):/opt/bitnami/wildfly/standalone/deployments/
	docker restart $(CONTAINER_NAME)
