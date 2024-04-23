package in.practise.lambdas;

import java.util.Objects;

@FunctionalInterface
public interface MyPredicate<T> {
    boolean check(T t);

    default MyPredicate<T> and(MyPredicate<T> other){
        Objects.requireNonNull(other);
        return a -> this.check(a) && other.check(a);
    }

    default MyPredicate<T> or(MyPredicate<T> other){
        Objects.requireNonNull(other);
        return a -> this.check(a) || other.check(a);
    }
}
