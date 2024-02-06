package task4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * // todo Task 1
 * //            Есть класс Human
 * Поля: String name, int age, Music type
 * <p>
 * Music - класс. Поля: String name, double duration.
 * <p>
 * Необходимо сохранить массив Human[] в Set используя Stream API,
 * причем сохранять только Human, чей age четный
 * <p>
 * P.S. осуществлять проверки на age и duration (age >=1 <=100), (duration >=0.0)
 */
public class Task4 {

    public static void main(String[] args) {

        Music music1 = new Music("Rock", 2.20);
        Music music2 = new Music("Rock", 2.50);
        Music music3 = new Music("Rock", 1.20);

        Human human1 = new Human("Artur", 26);
        Human human2 = new Human(27);
        Human human3 = new Human("Sergey", 26);
        Human human4 = new Human("Artur", 29);
        Human human5 = new Human(26);
        Human human6 = new Human("Artur", 26);
        Human human7 = new Human(101);

        human1.setType(music1);
        human2.setType(music1);
        human3.setType(music1);
        human4.setType(music3);
        human5.setType(music3);
        human6.setType(music3);
        human7.setType(music2);

        Human[] humans = new Human[7];
        humans[0] = human1;
        humans[1] = human2;
        humans[2] = human3;
        humans[3] = human4;
        humans[4] = human5;
        humans[5] = human6;
        humans[6] = human7;



        Set<Human> humanSet =
                Arrays.stream(humans)
                        .filter(human -> human.getAge() % 2 == 0
                                && human.getAge() >= 1
                                && human.getAge() <= 100
                                && human.getType().getDuration() == 1.20)
                        .collect(Collectors.toSet());
        humanSet.forEach(System.out::println);


    }
}

