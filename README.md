## 项目社区

## 资料
[spring](https://spring.io/guides/gs/serving-web-content/)
[bootstrap](https://v3.bootcss.com/)
[Lombok]()

## 工具
[Flayway](https://flywaydb.org/getstarted/firststeps/maven#migrating-the-database)

[MyBatis Generator]( )


## 部署
- Git
- JDK
- Maven
- Mysql

## Linux命令(centos7)
- yum update
- yum install git
- mkdir App (ls)
- cd App/
- git clone ----
- yum install maven
- java -version
- mvn -v
- mvn clean compile package
- cp src/main/resources/application.properties src/main/resources/application-production.properties
- vim src/main/resources/application-production.properties
- mvn package
- java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
- ps -aux  | grep java
- git pull


##### maven bash
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
