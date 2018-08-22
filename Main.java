package com.example.jcore;

import java.lang.String;

public class Main {
    public static void main(String[] args) {

        // созлаем массив участников команды
        String[] players = { "Stalone", "Ronaldu", "Bold", "Taison" };

        // создаем экземпляр класса команда участников
        Team team = new Team("super_boys", players );

        // создаем экземпляр класса полоса препятствий
        Course course = new Course();

        // проходим полосу препятствий
        course.doIt( team );

        // выводим информация об участниках проходивших препятствия
        team.printPlayersCourseInfo();
    }
}