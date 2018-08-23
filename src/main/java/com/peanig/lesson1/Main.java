package com.peanig.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Course course = new Course("Java 2","Anton",8);

        List<Member> members = new ArrayList<Member>();
        members.add(new Member("Andrey"));
        members.add(new Member("Vasia"));
        members.add(new Member("Dima"));
        members.add(new Member("Max"));

        Team team = new Team(members);

        course.doIt(team);

        team.showResults();

    }
}