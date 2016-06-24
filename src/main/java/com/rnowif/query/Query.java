package com.rnowif.query;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Query<T> extends Predicate<T> {

    static <R, T> Query<T> with(Function<T, R> supplier, Matcher<R> matcher) {
        return null;
    }
}
