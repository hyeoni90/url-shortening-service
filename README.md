# url-shortening-service
URL을 입력받아 짧은 url을 생성, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
```
예) https://en.wikipedia.org/wiki/URL_shortening -> http://localhost/JZfOQNro
```

## Requirements
* URL 입력폼 제공 및 결과 출력 합니다.
* URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
* 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
* 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
* Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.
* Database 사용은 필수 아님

## Environment
* Java 1.8
* Spring Boot 2.3.8.RELEASE
* Spring Data JPA
* H2 DataBase

## Getting Started
1. 빌드 및 실행
```shell
./gradlew clean && build
./gradlew bootJar
java -jar build/libs/url-shortening-service-0.0.1-SNAPSHOT.jar
```
2. http://localhost:8080 으로 접속
3. [h2 console](http://localhost:8080/h2-console) 
