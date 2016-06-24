# Generic Query

This library aims to allow to build queries of any object using Java 8 lambdas in order to be used as filters or SQL queries.

## Usage

Thanks to the builder, it is possible to build `Query` that applies to specific attributes

```java
Query<User> byAge = with(User::getAge, greaterThan(18));
Query<User> byGender = with(User::getGender, equalTo(Gender.MALE));

List<User> majorMen = users.stream()
        .filter(byAge.and(byGender))
        .collect(toList());                              
```