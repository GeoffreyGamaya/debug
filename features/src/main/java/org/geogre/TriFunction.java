package org.geogre;

public interface TriFunction<R, S, T, U> {
    R apply(S s, T t, U u);
}
