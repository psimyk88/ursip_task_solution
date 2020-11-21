import java.util.*;
import java.util.stream.IntStream;

/*

Задание:

Реализовать метод для генерации номера проекта на Java, Kotlin или Scala

Сигнатура метода (java):
public static String generateNum(List<Integer> numbers)

На вход будет переданы сущетвующие номера в виде масива целых чисел.
Необходимо вернуть номер следующего проекта, по следующей логике:
Если текущие номера проекта не содержат пропусков, то необходимо вернуть максимальный элемент + 1.
Если пропуски есть, необходимо вернуть минимальный из них.
Пропуском называется разница между номерами больше или равная единицы (f(1,3) содержит пропуск, а f(1,2) нет).
Переданные номера проектов могут дублироваться, но сгенерированный методом номер не должен повторять существующий.
Номера проектов неотрицательные.

Пример:
f(1,2,3) = 4
f(1,3) = 2
f(2) = 1

Сгенерированный номер необходимо вернуть в формате трехразрядной строки, т.е.:
f(1) = "001"
f(10) = "010"
f(100) = "100"

В реализации приветствуется функциональный стиль.
Оцениваться будет качество кода и его корректность.
Решение необходимо залить в публичный репозиторий и предоставить ссылку.

 */
public class Task_Solution {

    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>(Arrays.asList(97,98,99));

        System.out.println(generateNum(integerList));
    }

    public static String generateNum(List<Integer> numbers) {
        if (numbers == null || numbers.size() == 0) {
            throw new NoSuchElementException();
        }
        //сортируем в порядке возрастания входязий список
        numbers.sort(Integer::compareTo);
        //создаем массив целочисленных значений при помощи range , и начинаем сравнивать эл - ты
        //списков друг с другом , при обнаружении несовпадений (пропусков) , берется первый пропущенный
        //элемент, далее при помощи map преобразуем значение в требуемую строку
        String result = IntStream.range(numbers.get(0), numbers.get(numbers.size() -1))
                .filter(x -> !numbers.contains(x))
                .boxed()
                .findFirst()
                .map(x -> x >= 1 && x < 10 ? "00" + x : "0" + x)
                .orElseGet(() -> {
                    int i = numbers.get(numbers.size() - 1) + 1;
                    if (i > 99) {
                        return String.valueOf(i);
                    }
                    return i >= 1 && i < 10 ? "00" + i : "0" + i;
                });
        return result;
    }

}
