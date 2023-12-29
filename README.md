<h2 align='center'>A distributed and highly available URL Shortener.</h2><br/>

## Introduction

- Shortify is a distributed and highly available URL Shortening service, following the guidelines of efficient System Design and builting on the Reactjs as front-end and Spring boot as back-end.
- It uses Redis as a cache, and MongoDB as a NoSQL database.
- It uses Nginx that acts as a load balancer, and a reverse proxy for the backend server.
- It uses an Unique ID generator inspired by Twitter snowflake for id and hash generation.
- The application is containerized by Docker.
- This is a project demonstrating the entire development process and is meant to be run in a local environment.

## Usage
- Install Docker Desktop for a quick setup.
- Clone this repository
- Open the terminal and start the container with docker compose:<br>

```yml
docker compose up --build

# We can also scale instances of an image for higher scalability or distribution.
Example: docker compose up --build --scale spring-boot-server=3
```
## Demonstration
https://github.com/phhoang98a/Shortify/assets/34488386/296c3799-b813-4714-a2cd-ab5b8d18dda6

## Unique Id and Hash generation
You can read about [Twitter snowflake](https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake.html) and the blog to understand the Id generator I used in this application 
[Generating unique IDs in a distributed environment at high scale.](https://www.callicoder.com/distributed-unique-id-sequence-number-generator/)

I use the base 62 convention which is referred to in System Design Interview by Alex Xu to convert Id to shortURL. 

Base 62 conversion is used as there are 62 possible characters for hashValue. Let us use an example to explain how the conversion works: Convert 1115710 to base 62 representation (1115710 represents 11157 in a base 10 system).

• From its name, base 62 is a way of using 62 characters for encoding. The mappings are: 0-0, ..., 9-9, 10-a, 11-b, ..., 35-z, 36-A, ..., 61-Z, where ‘a’ stands for 10, ‘Z’ stands for 61, etc.

• 1115710 = 2 x 62^2 + 55 x 62^1 + 59 x 62^0 = [2, 55, 59] -> [2, T, X] in base 62 representation. 

• Thus, the short URL is https://tinyurl.com/2TX


## System Design

_**URL shortening**_

![1](https://github.com/phhoang98a/Shortify/assets/34488386/6597ac32-e5fe-4606-ba3b-7f41f204f811)

_**URL redirecting**_

![Screenshot 2023-12-29 at 7 10 25 AM](https://github.com/phhoang98a/Shortify/assets/34488386/34a7c857-4ff9-479a-be53-691781a39989)

