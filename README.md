# spring-clean-architecture

[![Build Status](https://travis-ci.org/coi-gov-pl/spring-clean-architecture.svg?branch=develop)](https://travis-ci.org/coi-gov-pl/spring-clean-architecture)

An example web app structured using [Clean Architecture][clean-arch],
implemented using [Spring Framework][spring].

![Robert C Martin - Clean Architecture](http://i.imgur.com/WkBAATy.png)

Watch youtube video of Robert C. Martin "Uncle Bob" on Clean Architecture and Design:

[![Robert C Martin - Clean Architecture and Design](https://img.youtube.com/vi/Nsjsiz2A9mg/0.jpg)](https://www.youtube.com/watch?v=Nsjsiz2A9mg)

[clean-arch]: https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html
[spring]: https://projects.spring.io/spring-framework/

### Un strict mode

If you feel that external configuration is a bit of a hasle, you can see example of integrating spring-context into domain logic. It's less flexible and surely it's not advised by Uncle Bob. If you feel that your thing, you might look at branch: `spring-in-domain-logic` (See diff here: https://github.com/coi-gov-pl/spring-clean-architecture/compare/spring-in-domain-logic).
