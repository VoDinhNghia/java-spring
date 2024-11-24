## init project

- ctrl + shift + p => enter JAVA: Create Java Project => choose Spring Boot => Maven Project => 3.1.2 => choose Java => enter name group id => enter name project.

## run project

- install mvn
- run cmd: mvn clean spring-boot:run
- run manual: go to file SpringbootApplication.java => click right mouse => click Run Java

## Swagger

- link: http://localhost:3000/swagger-ui/index.html#/
- All private api => use postman to test (use swagger => error unauthencation (not fixed yet))

## Docker run elasticsearch and kibana version 8.16.0

- docker run -d --name es-container --net elastic -p 9200:9200 -e "xpack.security.enabled=false" -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" docker.elastic.co/elasticsearch/elasticsearch:8.16.0
- docker run -d --name kb-container --net elastic -p 5601:5601 -e ELASTICSEARCH_URL=http://es-container:9200 docker.elastic.co/kibana/kibana:8.16.0
## Use dockercompose
- run cmd: mvn clean package
- docker compose up
