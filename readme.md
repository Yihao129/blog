### Blog website backend with Restful API 

It is a Maven Project based on Spring Boot framework.
Functionalities:
1. Stateless authentication 
2. Authirization for apis
3. CRUD operations on blog content and comments
4. Upload files to AWS S3
5. Send email notifications with email consumer micro service
6. Persistence layers with Hibernate
7. Service layers
8. Micro service with AWS SQS
9. Database migration with Flyway plugin
10. Servlet filters 
11. Junit testing
#### Run postgres container locally 

```
docker pull postgres
docker run --name dealerDB -e POSTGRES_DB=train OSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```

#### Run migration

    mvn clean compile flyway:migrate -P unit -Ddb_url=${url} -Ddb_password=${password} -Ddb_username=${username}

you can also add that into maven settings.xml

```$xml
	<profiles>
		<profile>
			<id>unit</id>
			<properties>
				<db_url>localhost:5432/car_unit</db_url>
				<db_username>xxxx</db_username>
				<db_password>xxxx</db_password>
			</properties>
		</profile>
	</profiles>
```

#### Unit Test

```
mvn clean compile test -Dspring.profiles.active=unit
```

#### Configuration information (The config file is located in ./src/main/resources/META-INF/)

```
mvn compile -Dspring.profiles.active=dev
mvn compile -Dspring.profiles.active=unit
```

#### Package Command

```
mvn compile package -DoutputDirectory=./target
```
