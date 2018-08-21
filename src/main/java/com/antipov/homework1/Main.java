package com.antipov.homework1;

public class Main {
    public static void main(String[] args) {
        Course course = new Course();
        Team team = new Team("Гики");
        team.showTeam();
        course.doIt(team);
        team.showResults();
    }
}
