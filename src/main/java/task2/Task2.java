package task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A solis ortu usque ad occasum
 * A prima facie никто
 * В клоаке снега тщетных обещаний
 * Найду себе ab ovo самого
 * и как бы не старался сгинуть
 * В дисперсии прекрасного ума
 * Как зиккурат статичен и стабилен
 * Над энтропийными оркестрами себя
 * <p>
 * - Нелепо ранжированным почерком написал стихотворение на салфетке. Binocla в Питере. Тут всегда так: либо спиваешься, либо вешаешься.
 * Тяжко походить на поэта, будучи окрыленным корявыми переводами Керуака.
 * Впрочем, вы можете помочь Binocla с его стихотворением и дать надежду на публикацию в местный сборник питерских писак.
 * У издательства есть ряд требований:
 * 1) Необходимо посчитать и вывести количество английских и русских букв 'o' в стихотворении
 * 2) Необходимо каждое слово с тремя и более русскими гласными перевести в верхний регистр
 * 3) Каждое пятое слово вне зависимости от предыдущих пунктов перевести в чередующийся регистр (Начинается с верхнего, Sergey -> SeRgEy)
 * 4) Наконец, очень важно разделить стихотворение по строфам (4 строки) отступом (то есть после 4-х строк должна идти
 * пустая перед продолжением последующих) и поставить точки в конце каждой строфы.
 * <p>
 * Примечание: Вы можете разбить выполнение задач на методы. Важно, чтобы решение могло отслеживаться поэтапно (например,
 * перед выполнением задания 2 выводится Task 2).
 */
public class Task2 {
    public static void main(String[] args) {
//todo main
        List<String> poem = new ArrayList<>(List.of(
                "A solis ortu usque ad occasum",
                "A prima facie никто",
                "В клоаке снега тщетных обещаний",
                "Найду себе ab ovo самого",
                "и как бы не старался сгинуть",
                "В дисперсии прекрасного ума",
                "Как зиккурат статичен и стабилен",
                "Над энтропийными оркестрами себя"));
        System.out.println("Исходный стих: ");
        poem.stream().forEach(System.out::println);

        System.out.println("\nTask2_1 ____________________________________________");
        //Вывод варианта поиска только 'О' символа:
        char engChar = 'O';
        char rusChar = 'О';
        System.out.
                printf("Task1: Англ букв '%s' = %s, Русских букв '%s' = %s \n",
                        engChar,
                        searchForNumberOfCharactersInText(poem, engChar),
                        rusChar,
                        searchForNumberOfCharactersInText(poem, rusChar));


        System.out.println("\nTask2_2 ____________________________________________");
        System.out.println("toUpperCase: ");
        StringBuilder result = convertTextToUppercase(poem);
        System.out.println(result.toString());


        System.out.println("\nTask2_3 ____________________________________________");
        convertTextToAlternatingRegister(poem).stream().forEach(System.out::println);

        System.out.println("\nTask2_4 ____________________________________________");
        divideIntoStanzas(poem).stream().forEach(System.out::println);

    }


    //todo 1) Необходимо посчитать и вывести количество английских и русских букв 'o' в стихотворении
    //вариант поиска одного произвольного символа:

    private static long searchForNumberOfCharactersInText(List<String> poem, char c) {
        return poem.stream()
                .map(String::toLowerCase)
                .flatMapToInt(line -> line.toLowerCase().chars())
                .mapToObj(ch -> (char) ch)
                .filter(ch -> ch == Character.toLowerCase(c))
                .count();
    }


    // todo 2) Необходимо каждое слово с тремя и более русскими гласными перевести в верхний регистр
    //Здесь не сделал метод textToUpperCase сразу через поток, так как не получалось добавить в конец каждой строки перенос строки.
    public static StringBuilder convertTextToUppercase(List<String> poem) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : poem) {
            stringBuilder.append(convertLineToUppercase(line));
            stringBuilder.append(" \n");
        }
        return stringBuilder;
    }

    private static String convertLineToUppercase(String line) {
        String[] words = line.split(" ");
        return Arrays.stream(words)
                .map(word -> vowelCounting(word) >= 3 ? word.toUpperCase() : word)
                .collect(Collectors.joining(" "));
    }

    private static long vowelCounting(String word) {
        String vowelLetters = "аеёиоуыэюя";
        return word.chars()
                .mapToObj(ch -> (char) ch)
                .filter(ch -> vowelLetters.contains(String.valueOf(Character.toLowerCase(ch))))
                .count();
    }


    // todo 3) Каждое пятое слово вне зависимости от предыдущих пунктов перевести в чередующийся регистр (Начинается с верхнего, Sergey -> SeRgEy)
    public static List<String> convertTextToAlternatingRegister(List<String> poem) {
        List<String> result = new ArrayList<>();
        int count = 1;
        for (String line : poem) { //для каждого предложения
            String[] wordsArray = line.split(" ");
            StringBuilder convertedLine = new StringBuilder();
            for (int i = 0; i < wordsArray.length; i++) { // для каждого слова
                if (count % 5 == 0) {
                    wordsArray[i] = convertWord(wordsArray[i]);
                }
                count++;
                convertedLine.append(wordsArray[i]).append(" ");
            }
            result.add(convertedLine.toString().trim());
        }
        return result;
    }

    public static String convertWord(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) result += Character.toUpperCase(str.charAt(i));
            else result += str.charAt(i);
        }
        return result;
    }

    //todo 4) Разделить стихотворение по строфам (4 строки) отступом (то есть после 4-х строк должна идти
    // пустая перед продолжением последующих) и поставить точки в конце каждой строфы.

    public static List<String> divideIntoStanzas(List<String> poem) {
        List<String> result = new ArrayList<>();
        int count = 1;

        for (String line : poem) {
            if (count % 4 == 0) {
                result.add(editLine(line));
            } else result.add(line);
            count++;
        }
        return result;
    }

    public static String editLine(String line) {
        StringBuilder stringBuilder = new StringBuilder(line);
        return stringBuilder.append(".").append("\n").toString();
    }
}
