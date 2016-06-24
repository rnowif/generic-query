package com.rnowif.query;

@FunctionalInterface
public interface Matcher<T> {
    boolean matches(T value);
}
