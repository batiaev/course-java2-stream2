package com.batiaev.java2;

public class HomeWork1 {

    // Массив имен участников
    private static String[] members = {
            "Алёша Попович",
            "Медведко-богатырь",
            "Добрыня Никитич",
            "Илья Муромец"
    };

    // Название команды
    private static String teamName = "\"Богатыри\"";

    public static void main(String[] args) {

        // Создание объекта типа Team
        Team t = new Team(teamName, members);

        // Метод выводящий информацию о членах команды
        t.showMembersInfo();

        // Создание объекта типа Course (полоса препятствий, хотя кажется перевод не точный)
        Course c = new Course();

        // Метод Course.doIt() принимает в качестве аргумента объект типа Team
        c.doIt(t);

        // Метод Team.showResults() выводит информацию о результатах прохождения полосы препятствий
        t.showResults();
    }
}
