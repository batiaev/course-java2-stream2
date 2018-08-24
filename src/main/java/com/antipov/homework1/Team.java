package com.antipov.homework1;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Participant> teamMembers = new ArrayList<Participant>();

    public Team(String name) {
        this.name = name;
        teamMembers.add(new Participant("Алексей"));
        teamMembers.add(new Participant("Борис"));
        teamMembers.add(new Participant("Дмитрий"));
        teamMembers.add(new Participant("Евгений"));
    }

    public void showTeam() {
        System.out.println("Комманда \"" + name + "\"");
        System.out.println("Имя     (характеристики:ST,DX,EN,IQ)");
        for (Participant member : teamMembers) {
            System.out.println(
                    member.getName() + " (" + member.getStrength() + "," + member.getDexterity() + "," +
                    member.getEndurance() + "," + member.getIntellect() + ")");
        }
    }

    public void showResults() {
        System.out.println("Результаты:");
        for (Participant member : teamMembers) {
            System.out.println(member.getName() + ": " + (member.isPassing() ?
                    "прошел все испытания" : "не прошел испытания"));
        }
    }

    public void Execute(Obstacle test) {
        for (Participant member : teamMembers) {
            if (member.isPassing() & !test.checkPassing(member))
                member.setPassing(false);
        }
    }
}
