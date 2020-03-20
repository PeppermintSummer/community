##项目社区

##资料
[spring](https://spring.io/guides/gs/serving-web-content/)

[Lombok]()

##工具
[Flayway](https://flywaydb.org/getstarted/firststeps/maven#migrating-the-database)

[MyBatis Generator]( )


##部署
- Git
- JDK
- Maven
- Mysql

##Linux命令(centos7)
- yum update
- yum install git
- mkdir App (ls)
- cd App/
- git clone ----
- yum install maven
- java -version
- mvn -v
- m


##### maven bash
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```