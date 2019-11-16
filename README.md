=========

## Spring Boot + Postgresql + Docker Compose

### Prepair

Launch 1 EC2 Amazon Linux

Install docker, docker-compose:  
```sh
sudo yum install git -y
sudo yum update -y
sudo yum install -y docker
sudo service docker start
sudo usermod -a -G docker ec2-user
sudo -i
curl -L https://github.com/docker/compose/releases/download/1.23.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
exit
#Logout and Login
```

Install maven, java8:  

`https://docs.aws.amazon.com/neptune/latest/userguide/iam-auth-connect-prerq.html`


### How to run 

```sh
git clone https://github.com/hoangmnsd/spring-maven-postgres-docker

cd spring-maven-postgres-docker

sh cleanRun.sh
```

### How to test

Using Postman, send POST request to `http://<EC2-PUBLIC-IP>:12345/v1/product` with body:  
```
{"name":"product001"}
```

if `success`:  
```
{
    "product": {
        "id": 1,
        "name": "product001",
        "new": false
    },
    "result": {
        "success": true,
        "message": "Success"
    }
}
```

In Browser check this link `http://<EC2-PUBLIC-IP>:12345/v1/product/product001`  
if `success`, browser will show something like this:  
`{"product":{"id":1,"name":"product001","new":false},"result":{"success":true,"message":"Success"}}`


### If you want to run in local windows machine

Make sure you install maven, java8, Intelij IDEA, Postgresql.

Config postgresql open port 5432, create database `store`, and user `dbuser`/password `password`

execute below query on PgAdmin4:  

```
GRANT ALL PRIVILEGES ON DATABASE "store" TO "dbuser";
    create table if not exists product
    (
      id  bigint not null constraint product_pkey primary key,
      name  varchar(255) UNIQUE
    );
    CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;
```

Edit `src/main/resources/application.yml`, uncomment and comment like this

```
#below is config for Docker compose
#ENV_DATASOURCE_URL: jdbc:postgresql://postgres/store
#below is config for postgresql in local windows
ENV_DATASOURCE_URL: jdbc:postgresql://localhost:5432/store
```

#### Run app by command  

```sh
mvn spring-boot:run
```

#### How to test on local machine

Using Postman, send POST request to `http://localhost:12345/v1/product` with body:  
```
{"name":"product001"}
```

if `success`:  
```
{
    "product": {
        "id": 1,
        "name": "product001",
        "new": false
    },
    "result": {
        "success": true,
        "message": "Success"
    }
}
```

In Browser check this link `http://localhost:12345/v1/product/product001`  
if `success`, browser will show something like this:  
`{"product":{"id":1,"name":"product001","new":false},"result":{"success":true,"message":"Success"}}`

**REFERENCES**:  
https://muzir.github.io/spring/docker/docker-compose/postgres/2019/03/24/Spring-Boot-Docker.html
