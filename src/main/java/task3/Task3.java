package task3;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

/**
 * Наконец-то Binocla влюбился. В ту самую и неповторимую. К сожалению, избранная девушка его динамит, а потому
 * Binocla решил её впечатлить и сразить всех потенциальных конкурентов наповал.
 * Binocla решил зашифровать послание любви с помощью специального шифра. Он решил следующее: пускай я возьму
 * числовое представление (Unicode) каждого символа и просуммирую их. Однако Binocla слишком стеснительный, чтобы
 * в лоб сказать о своих чувствах и он просит передать послание с помощью вас. Помогите ему!
 * <p>
 * Input: N - строка, содержащее ваше имя и следующий шаблонный текст: " says that Binocla loves you!".
 * Так, например, если Иван решит передать послание, он введет в консоль "Ivan says that Binocla loves you!".
 * Имя гарантированно состоит из одного слова. До имени гарантированно нет пробелов. Если шаблон нарушен (например,
 * не хватает пробела/неправильный регистр/неправильный символ/отсутствие или замена восклицательного знака) -
 * вывести "What a mess!" (без кавычек)
 * <p>
 * Output: Вывести шифр в виде числа (числовой тип данных)
 * <p>
 * Примеры:
 * 1)
 * serGEY says that Binocla loves you!
 * Вывод:
 * 3231
 * 2)
 * Sergey says that Binocla loves you!
 * Вывод:
 * 3295
 * 3)
 * Sergey says that binocla loves you!
 * Вывод:
 * What a mess!
 * <p>
 * Примечание: Ваше имя может быть пустым, однако все пробелы в шаблонной строке обязаны присутствовать.
 * Вводить строку требуется целиком с шаблоном (то есть нельзя ввести только Ваше имя). Между Вашим именем и
 * шаблонной строкой обязательно должен быть ТОЛЬКО один пробел
 * <p>
 */
public class Task3 {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Введите любовное послание от Binocla: ");
        String fromBinocla = prompt();

        Optional<Integer> optionalUnicode = stringToUnicode(fromBinocla);
        if (optionalUnicode.isEmpty()) System.out.println("What a mess!");
        else System.out.println(optionalUnicode.get());
    }

    public static Optional<Integer> stringToUnicode(String str) {
        if (isValidText(str)) {
            int code = 0;
            for (int i = 0; i < str.length(); i++) {
                code += str.charAt(i);
            }
            return Optional.of(code);
        }
        return Optional.empty();
    }

    private static boolean isValidText(String str) {
        if (isContainsPattern(str)) {
            String[] textArray = str.split(" ");
            return textArray.length == 6;
        }
        return false;
    }

    private static boolean isContainsPattern(String str) {
        String pattern = "says that Binocla loves you!";
        return str.contains(pattern);
    }

    private static String prompt() {
        return sc.nextLine();
    }
}
