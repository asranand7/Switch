package in.practise.lambdas;

import java.util.function.Function;

public class LambdasMain {
    private static int[] x_cord = {-1,0,1,0};
    private static int[] y_cord = {0,1,0,-1};
    public static void main(String[] args) {
        // Predicate Test
        MyPredicate<Integer> isDivisibleBy3 = a -> a%3 == 0;

       MyPredicate<Integer> isDivisibleBy5 = a -> a%2 == 1;

        System.out.println(isDivisibleBy3.check(15));

        System.out.println(isDivisibleBy5.check(15));

        System.out.println(isDivisibleBy3.and(isDivisibleBy5).check(15));



        // Consumer Test

        MyConsumer<Integer> printnum = a -> System.out.println(a);
        MyConsumer<Integer> printDoubleTheNumber = a -> System.out.println(2*a);
        MyConsumer<Integer> andThen = printnum.andThen(printDoubleTheNumber);
        andThen.accept(5);


        // Function Test
        MyFunction<Integer, String> toStringFunction = Object::toString;
        MyFunction<String, Integer> lengthFunction = String::length;

// Compose functions
        MyFunction<Integer, Integer> composedFunction1 = toStringFunction.andThen(lengthFunction);
        MyFunction<Integer, Integer> composedFunction2 = lengthFunction.compose(toStringFunction);

// Apply the composed functions
        System.out.println(composedFunction1.apply(123)); // Output: 3 (length of "123")
        System.out.println(composedFunction2.apply(123)); // Output: 3 (length of "123")

    }

}