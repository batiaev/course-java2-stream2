package com.batiaev.java2;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
public class Main {
    public static void main(String[] args) {
        /* Create teams ------------------ */
        Team tmnt_team = new Team();
        tmnt_team.setName("Teenage Mutant Ninja Turtles");
        tmnt_team.setMembers(Arrays.asList(
                new SimpleMember("Leonardo"),
                new SimpleMember("Raphael"),
                new SimpleMember("Donatello"),
                new SimpleMember("Michelangelo")
        ));

        Team dimensionX_team = new Team(
                "Dimension X",
                Arrays.asList(
                        new SimpleMember("Shredder"),
                        new SimpleMember("Krang"),
                        new LiarMember("Bebop"),
                        new LiarMember("Rocksteady"))
        );

        /* Show team -------------------- */
        log.info("_____ TEAMS _____");
        log.info("TEAM: {}", tmnt_team.getName());
        tmnt_team.getMembers().forEach(member -> log.info("- {}", member.getName()));

        log.info("TEAM: {}", dimensionX_team.getName());
        dimensionX_team.getMembers().forEach(member -> log.info("- {}", member.getName()));

        /* Create course ---------------- */
        Course course = new Course();
        course.setObstacleList(Collections.singletonList(new GreatKoreanRandom()));

        /* Let's go! -------------------- */
        course.doIt(tmnt_team);
        course.doIt(dimensionX_team);

        /* Show result ------------------ */
        log.info("_____ RESULT _____");
        log.info("TEAM: {}", tmnt_team.getName());
        tmnt_team.showResults();
        log.info("TEAM: {}", dimensionX_team.getName());
        dimensionX_team.showResults();
    }
}
