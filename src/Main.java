import in.practise.lambdas.MyPredicate;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    private static int[] x_cord = {-1,0,1,0};
    private static int[] y_cord = {0,1,0,-1};
    public static void main(String[] args) {
        MyPredicate<Integer> isDivisibleBy3 = a -> a%3 == 0;

       MyPredicate<Integer> isDivisibleBy5 = a -> a%2 == 1;

        System.out.println(isDivisibleBy3.check(15));

        System.out.println(isDivisibleBy5.check(15));

        System.out.println(isDivisibleBy3.and(isDivisibleBy5).check(15));
    }
    private boolean isSafeIndex(int a, int b, int m, int n) {
        return a >= 0 && a < m && b>=0 && b < n;
    }

}