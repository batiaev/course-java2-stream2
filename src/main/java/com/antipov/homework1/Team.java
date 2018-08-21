package com.antipov.homework1;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private List<Participant> teamMembers = new ArrayList<Participant>();

    public Team() {
        teamMembers.add(new Participant("Алексей"));
        teamMembers.add(new Participant("Борис"));
        teamMembers.add(new Participant("Галина"));
        teamMembers.add(new Participant("Евгений"));
    }

    public void showResults() {
        System.out.println("Результаты:");
        for (Participant member :
                teamMembers) {
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
