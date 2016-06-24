package com.rnowif.query;

import org.junit.Test;

import java.util.List;

import static com.rnowif.query.Query.with;
import static com.rnowif.query.matchers.EqualToMatcher.equalTo;
import static com.rnowif.query.matchers.GreaterThanMatcher.greaterThan;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

public class QueryFeatureTest {

    private enum Gender {
        MALE, FEMALE
    }

    private static class User {

        private final Integer age;
        private final Gender gender;

        private User(Integer age, Gender gender) {
            this.age = age;
            this.gender = gender;
        }

        public Integer getAge() {
            return age;
        }

        public Gender getGender() {
            return gender;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            User user = (User) o;

            if (!age.equals(user.age)) {
                return false;
            }
            return gender == user.gender;

        }

        @Override
        public int hashCode() {
            int result = age.hashCode();
            result = 31 * result + gender.hashCode();
            return result;
        }
    }

    @Test
    public void should_apply_query_to_filter_stream() {

        List<User> users = asList(
                new User(15, Gender.FEMALE),
                new User(18, Gender.FEMALE),
                new User(17, Gender.MALE),
                new User(19, Gender.MALE)
        );

        Query<User> byAge = with(User::getAge, greaterThan(18));
        Query<User> byGender = with(User::getGender, equalTo(Gender.MALE));

        List<User> majorMen = users.stream()
                .filter(byAge.and(byGender))
                .collect(toList());

        assertThat(majorMen).containsExactly(new User(19, Gender.MALE));
    }
}
