package com.batiaev.java2;

public class Team {

    // Переменные класса:

    // Название команды
    private String teamName;

    // Массива типа TeamMember[]  для хранения списка участников.
    private TeamMember[] members = new TeamMember[4];

    // Массив типа String[]  для хранения результатов по преодолению препятствий.
    private String[] results = new String[4];

    // Конструктор класса, параметры: String название команды,
    //  и массив типа String[] список имен участников.
    public Team(String teamName, String[] names) {
        this.teamName = teamName;

        // Заполнение массива типа TeamMember[] новыми участниками.
        for (int i = 0; i < members.length; i++) {

            // Создание объектов TeamMember (новых участников),
            // им передаются имена из массива String[] names  из конструктора.
            members[i] = new TeamMember(names[i], i + 3);
        }
    }

    // Метод выводит информацию о команде и каждом из участников.
    public void showMembersInfo() {
        for (int i = 0; i < members.length; i++) {
            System.out.println("команда: " + teamName + "." +
                    " Имя: " + members[i].getName() + "." +
                    " силушка: " + members[i].getSkill() + "."
            );
        }
    }

    // Метод выводит результаты после соревнований, если массив String[] results  был заполнен.
    public void showResults() {

        for (String s : results) {
            System.out.println(s);
        }

    }

    // Метод принимает в качестве аргумента массив int[] obstacles (условных препятствий),
    // и проводит расчет кто из команды прошел. Результаты записывает в массив String[] results.
    public void overcomeObstacles(int[] obst) {

        for (int i = 0; i < obst.length; i++) {
            for (int j = 0; j < members.length; j++) {
                if (obst[i] <= members[j].getSkill() && i == obst.length - 1) {
                    results[j] = members[j].getName() + " преодолел препятствие номер: " + obst[i];
                } else if (obst[i] > members[j].getSkill()) {
                    results[j] = members[j].getName() + " не преодолел препятствие номер: " +
                            obst[i];
                }
            }
        }
    }


    // Вложенный класс TeamMember хранит данные о участнике, имя и навык.
    class TeamMember {

        private String name;
        private int skill;

        public TeamMember(String name, int skill) {
            this.name = name;
            this.skill = skill;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSkill() {
            return skill;
        }

        public void setSkill(int skill) {
            this.skill = skill;
        }

    }
}
