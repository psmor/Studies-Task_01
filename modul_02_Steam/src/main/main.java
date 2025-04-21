import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class main {

    static List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Man1", 35, Post.MANAGER),
            new Employee("Dir", 44, Post.DIRECTOR),
            new Employee("Eng1_25", 25, Post.ENGINEER),
            new Employee("Eng2_25", 25, Post.ENGINEER),
            new Employee("Eng3_42", 42, Post.ENGINEER),
            new Employee("Man2", 55, Post.MANAGER),
            new Employee("Eng4_33", 33, Post.ENGINEER)
    ));


    public static void main(String[] args) {
        // 01 ///////////////////////////////////////////////////////////////////////////////////////
        // 3-е наибольшее число (На вход int)
        int[] ints1 = {5,2,10,9,4,3,10,1,13};
        int max11 = Arrays.stream(ints1)
                //.boxed()                                  // Упаковка потока в оболочку (в данном случае int в Integer)
                //.sorted(Collections.reverseOrder())       // Обратная сортировка. Требует передачи массива объектов, а не примитивов
                .sorted()                                   // Сортировка по возрастанию
                .limit(ints1.length-2)              // 3-е наибольшее
                .max()
                .getAsInt();
        System.out.println("3-е наибольшее число (1-й способ): " + max11);

        // 3-е наибольшее число (На вход Integer)
        Integer[] ints2 = new Integer[]{5,2,10,9,4,3,10,1,13};
        int max12 = Arrays.stream(ints2)
                .sorted(Collections.reverseOrder())        // Сортировка по убыванию
                .limit(3)                          // 3-е наибольшее
                .min(Integer::compare)
                .get();
        System.out.println("3-е наибольшее число (2-й способ): " + max12);

        // 02 ///////////////////////////////////////////////////////////////////////////////////////
        // 3-е наибольшее уникальное число (На вход Integer)
        int max22 = Arrays.stream(ints2)
                .distinct()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .min(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println("3-е наибольшее уникальное число: "+max22);

        // 03 ///////////////////////////////////////////////////////////////////////////////////////
        // Список имён 3-х самыз старших сотрудников, с должностью "Инженер", в порядке убывания возраста
        List<String> names = employees.stream()
                .filter(employee -> employee.getPost() == Post.ENGINEER)           // Оставляем только тех у кого должность ENGINEER
                //.sorted(Comparator.comparingInt( (o-> o.getAge())) )               // Сортировка по возрасту, в порядке возрастания
                .sorted( Comparator.comparingInt(Employee :: getAge).reversed() )  // Сортировка по возрасту, в порядке убывания возраста
                .map(employee->employee.getName())                                 // Вывод только Имён
                .toList();
        System.out.println("3 самых старших сотрудника: "+names);

        // 04 ///////////////////////////////////////////////////////////////////////////////////////
        // Средний возраст сотрудников, с должностью "Инженер"
        Double averAge = employees.stream()
                .filter(employee -> employee.getPost() == Post.ENGINEER)           // Оставляем только тех у кого должность ENGINEER
                .mapToInt( employee->employee.getAge() )                           // Оставляем только возраст
                .average()                                                         // Среднее значение
                .orElseThrow( ()-> new RuntimeException("Считать нечего") );       // На случай если поток будет пустым
        System.out.println("Средний возраст Инженеров = "+averAge);

        // 05 ///////////////////////////////////////////////////////////////////////////////////////
        // Самое длинное слово
        String[] strings = {"Нет", "Длинное_слово", "Эх", "Упс"};
        String str12 = Arrays.stream(strings)
                //.max(Comparator.comparingInt(String::length))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst()
                .orElseThrow( () -> new RuntimeException("Пусто"));
        System.out.println("Самое длинное слово: "+str12);

        /////////////////////////////////////////////////////////////////////////////////////////
        // Построить хеш мапы
        String str2 = "шляпа солнце сакура море пляж шляпа шавка";
        Map<String, Long> map2 =  Arrays.asList(str2.split(" ")).stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        System.out.println("Получилась мапа:");
        for (String word : map2.keySet()){
            System.out.println("word: " + word + ", value: " + map2.get(word));
        }

        /////////////////////////////////////////////////////////////////////////////////////////
        // Вывести слова в порядке увеличения длинны слова, если длинна одинаковая, тогда в алфовитном порядке.
        System.out.println("Строки в порядке увеличения длинны слова:");
        Arrays.stream(str2.split(" "))
                // Сначала сортировка по длинне, обратная. Затем сортировка по алфавиту
                .sorted( Comparator.comparing(String::length).reversed().thenComparing(String :: toString) )
                .forEach(System.out::println);

        /////////////////////////////////////////////////////////////////////////////////////////
        //Массив строк, в каждом набор из 5 слов, разделённых пробелом. Нужно найти самое длинное.
        System.out.print("Самое длинное слово: ");
        String[] arr = {"шляпа солнце сакура море бриллиант",
                        "погода навека длинный выгода последний"
        };

        String str = Arrays.stream(arr)
                .map(x->x.split(" "))   // каждую строку представим в виде массива. Теперь имеем вложенны массив
                .flatMap(Stream::of)          // Разворачиваем вложеннвй массив в общий поток
                .sorted(Comparator.comparingInt(String::length).reversed()) // Сортировка обратная
                .findFirst()
                .orElseThrow( () -> new RuntimeException("Пусто"));


    }
}
