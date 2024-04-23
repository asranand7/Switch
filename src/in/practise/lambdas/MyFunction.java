package in.practise.lambdas;

import java.util.Objects;

@FunctionalInterface
public interface MyFunction<T, R> {

    R apply(T r);

    default <V> MyFunction<T, V> andThen(MyFunction<R, V> after){
        Objects.requireNonNull(after);
        return (T t) ->  after.apply(this.apply(t));
    }

    default <V> MyFunction<V, R> compose(MyFunction<V, T> before){
        Objects.requireNonNull(before);
        return (V v) -> this.apply(before.apply(v));
    }

}
