package com.antipov.homework1;

public class Main {
    public static void main(String[] args) {
        Course course = new Course();
        Team team = new Team();
        course.doIt(team);
        team.showResults();
    }
}
