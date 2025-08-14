APP_NAME=SamsungFakeStoreFront
WAR_FILE=target/$(APP_NAME)-1.0-SNAPSHOT.war
CONTAINER_NAME=wildfly-server

.PHONY: build deploy

all: deploy

build:
	mvn clean package

clean_deploy:
	docker exec -it $(CONTAINER_NAME) rm -f $(DEPLOY_DIR)/*.war*

deploy: build clean_deploy
	docker cp $(WAR_FILE) $(CONTAINER_NAME):/opt/bitnami/wildfly/standalone/deployments/
	docker restart $(CONTAINER_NAME)
