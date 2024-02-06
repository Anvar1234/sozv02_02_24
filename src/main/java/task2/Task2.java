package task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.
                printf("Task1: Англ букв 'O' = %s, Русских букв 'O' = %s \n",
                        countCharOInText(poem)[0],
                        countCharOInText(poem)[1]);

        System.out.println("\nTask2_2 ____________________________________________");
        System.out.println("toUpperCase: ");
        System.out.println(toUppercase(poem));


        System.out.println("\nTask2_3 ____________________________________________");
        convertTextToAlternatingRegister(poem).stream().forEach(System.out::println);

        System.out.println("\nTask2_4 ____________________________________________");
        divideIntoStanzas(poem).stream().forEach(System.out::println);

    }


    //todo 1) Необходимо посчитать и вывести количество английских и русских букв 'o' в стихотворении
    //вариант поиска только 'О' символа:
    public static int[] countCharOInText(List<String> poem) {
        int countOfEng = 0;
        int countOfRus = 0;

        for (String s : poem) {
            for (int i = 0; i < s.length(); i++) {
                if (s.toLowerCase().charAt(i) == Character.toLowerCase('o')) countOfEng++;
                else if (s.toLowerCase().charAt(i) == Character.toLowerCase('о')) countOfRus++;
            }
        }
        return new int[]{countOfEng, countOfRus};
    }


    // todo 2) Необходимо каждое слово с тремя и более русскими гласными перевести в верхний регистр
    //Здесь не сразу допер раскидать по отдельным методам, поэтому такая вложенность.
    public static StringBuilder toUppercase(List<String> poem) {
        String vowelLetters = "аеёиоуыэюя";
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : poem) {
            String[] words = s.split(" ");
            for (String word : words) {
                int vowelCount = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (vowelLetters.contains(String.valueOf(Character.toLowerCase(word.charAt(i))))) vowelCount++;
                }
                if (vowelCount >= 3) {
                    stringBuilder.append(word.toUpperCase()).append(" ");
                } else stringBuilder.append(word).append(" ");
            }
            stringBuilder.append(" \n");
        }
        return stringBuilder;
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
