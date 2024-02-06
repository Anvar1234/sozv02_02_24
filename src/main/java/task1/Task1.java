package task1;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * Binocla очень любит пить Coca-Cola. Однажды он ехал на своем Rolls-Royce Cullinan по улице казани.
 * Внезапно, сотрудники ДПС остановили его машину и решили проверить Binocla на опьянение.
 * Сотрудник назвал N русских маленьких букв и попросил сказать ему в ответ лексикографически возрастающую и убывающую
 * последовательность этих букв. Будучи пьяным Binocla сложно справиться с этой задачей. Помогите ему!
 * <p>
 * Input: N (1 <= N <= 100) — количество маленьких русских букв. Затем вводится последовательность символов. Ввод стандартный, через консоль.
 * Output: вывести отсортированные в лексикографическом порядке буквы через пробел (сначала по возрастанию, затем по убыванию)
 * <p>
 * Примеры:
 * 1)
 * 5
 * я а б в г
 * Вывод:
 * а б в г я
 * я г в б а
 * 2)
 * 1
 * г
 * Вывод:
 * г
 * г
 * <p>
 * Примечание: заметьте, что буквы могут повторяться. Необходимо рассматривать русский алфавит, включающий в себя все буквы.
 */
public class Task1 {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите N (1 <= N <= 100): ");
        int n = Integer.parseInt(prompt());
        System.out.printf("Введите %s букв русского алфавита: ", n);
        String inputExpression = prompt();

        Collator collator = Collator.getInstance(new Locale("ru", "RU"));
        collator.setStrength(Collator.SECONDARY);

        String characters1 = inputExpression.replaceAll("\\s+", " ").trim()
                .chars()
                .mapToObj(s -> String.valueOf((char) s))
                .sorted(collator)
                .collect(Collectors.joining(" "));

        String characters2 = new StringBuilder(characters1).reverse().toString();

        System.out.println("Прямой порядок: " + characters1);
        System.out.println("Обратный порядок: " + characters2);
    }

    private static String prompt() {
        return sc.nextLine();
    }
}
