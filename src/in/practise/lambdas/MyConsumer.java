package in.practise.lambdas;


import java.util.Objects;

@FunctionalInterface
public interface MyConsumer<T> {
    void accept(T t);

    default MyConsumer<T> andThen(MyConsumer<T> myConsumer){
        Objects.requireNonNull(myConsumer);
        return (T t) -> {
            this.accept(t);
            myConsumer.accept(t);
        };
    }
}
