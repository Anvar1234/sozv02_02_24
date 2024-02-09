package task5;

import java.util.*;

/**
 * // Task 2
 * С помощью Stream API сложить все нечетные числа и положить их в LinkedList,
 * в конце вывести
 */
public class Task5 {
    public static void main(String[] args) {

        System.out.print("Введите кол-во элементов N: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println();
        System.out.printf("Введите %s значений: \n", n);
        long[] ar = new long[n];
        for (int i = 0; i < n; i++) {
            long val = in.nextLong();
            ar[i] = val;
        }

        List<Long> longs = new LinkedList<>();
        longs.add(sumOfOddNumbers(ar));

        System.out.print("Сумма нечетных элементов = ");
        longs.forEach(System.out::println);
    }

    private static long sumOfOddNumbers(long[] array) {
        return Arrays.stream(array)
                .filter(x -> x % 2 != 0)
                .reduce(0, Long::sum);
    }
}


