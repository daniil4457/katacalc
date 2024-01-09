import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Формат выражения: число + пробел + знак выражения + число ");
        System.out.print("Введите арифметическое выражение: ");
        String input = scanner.nextLine();

        String result;
        try {
            result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        // Словарь для римских чисел
        Map<String, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put("I", 1);
        romanNumerals.put("II", 2);
        romanNumerals.put("III", 3);
        romanNumerals.put("IV", 4);
        romanNumerals.put("V", 5);
        romanNumerals.put("VI", 6);
        romanNumerals.put("VII", 7);
        romanNumerals.put("VIII", 8);
        romanNumerals.put("IX", 9);
        romanNumerals.put("X", 10);

        // Разделяем строку с примером пробелами
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            // Если количество частей не равно 3, выводим правило
            throw new Exception("Некорректное выражение");
        }

        String num1Str = parts[0]; // Первое число
        String operator = parts[1]; // знак выражения
        String num2Str = parts[2]; // Второе число

        int num1, num2;
        boolean isRomanNumeral = false; // Флаг для определения, являются ли числа римскими числами
        try {
            // Пробуем определить числа как арабские
            num1 = Integer.parseInt(num1Str);
            num2 = Integer.parseInt(num2Str);
        } catch (Exception e) {
            // Если числа нельзя определить как арабские, пробуем их как римские
            if (romanNumerals.containsKey(num1Str) && romanNumerals.containsKey(num2Str)) {
                num1 = romanNumerals.get(num1Str);
                num2 = romanNumerals.get(num2Str);
                isRomanNumeral = true;
            } else {
                // Если ни одно из условий не выполняется, выводим исключение
                throw new Exception("Некорректное выражение");
            }
        }

        int result;
        if (num1 >0 & num2>0 & num1 <=10 & num2 <= 10) {
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        // Если делитель равен нулю, выводим правило
                        throw new Exception("Делить на ноль нельзя");
                    }
                    result = num1 / num2;
                    break;
                default:
                    // Если оператор не распознан, выводим ограничение по знакам
                    throw new Exception("Неправильный знак! Доступные: +, -, /, * .");
            }
        } else {
            throw new Exception("Введите число(-а) от 1 до 10! (включительно)");
        }

        if (isRomanNumeral) {
            if (result <= 0) {
                // Если результат работы с римскими числами меньше единицы, выбрасываем условие
                throw new Exception("Результат работы с римскими числами не может быть меньше единицы");
            }
            return toRomanNumeral(result); // Преобразуем результирующее число в римское
        } else {
            return String.valueOf(result); // Преобразуем результирующее число в строку
        }
    }

    public static String toRomanNumeral(int num) {
        StringBuilder romanNumeral = new StringBuilder();
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                romanNumeral.append(symbols[i]);
                num -= values[i];
            }
        }

        return romanNumeral.toString();
    }
}