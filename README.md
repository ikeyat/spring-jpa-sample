# Overview
Implementation with and without using Spring Data JPA

# JPA without using Spring Data JPA
## Implementation
Inject ```EntityMangager``` into ```Repository``` implementation class.
Each operation method is implemented in the ```Repository```.
Each method delegate actual process to ```EntityManager```.
Their signatures imitate ones of ```JpaRepository``` provided by Spring Data JPA.

This is based on Spring Boot and ```@EnableJpaRepositories``` is enabled.
To avoid using Spring Data JPA, I use ```@Profile``` on ```Service``` classes.
I have 2 ```Service``` classes. One is for without Spring Data JPA, the other is for Spring Data JPA.


## How to run
1. Set profile "without-datajpa" into application.yml
1. Run main method in ```Application```
1. Access http://localhost:8080/rooms
1. Confirm JPA mode at the bottom of web pages. It might show ```RoomServiceImpl```.

# JPA with using Spring Data JPA
## Implementation
Basic usage of Spring Data JPA.
Spring Boot helps configurations for Spring Data JPA.
But I dare to have configuration code for Spring Data JPA for learning its mechanism.

## How to run
1. Set profile empty or anything other than "without-datajpa" into application.yml
1. Run main method in ```Application```
1. Access http://localhost:8080/rooms
1. Confirm JPA mode at the bottom of web pages. It might show ```RoomServiceJpaImpl```.
