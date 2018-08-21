package com.antipov.homework1;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private List<Obstacle> obstacles = new ArrayList<Obstacle>();

    public Course(){
        //Решил пока не делать отдельные классы испытаний
        obstacles.add(new Obstacle() {
            //Испытание - забег на длинную дистанцию
            int needEndurance = 8;
            public boolean checkPassing(Participant participant) {
                if (participant.getEndurance() >= needEndurance)
                    return true;
                else
                    return false;
            }
        });
        obstacles.add(new Obstacle() {
            //Испытание - перелезть стену
            int needDexterity = 8;
            public boolean checkPassing(Participant participant) {
                if (participant.getDexterity() >= needDexterity)
                    return true;
                else
                    return false;
            }
        });
        obstacles.add(new Obstacle() {
            //Испытание - перенос тяжелых вещей
            int needStrength = 9;
            int needEndurance = 7;
            public boolean checkPassing(Participant participant) {
                if (participant.getStrength() >= needStrength && participant.getEndurance() >= needEndurance)
                    return true;
                else
                    return false;
            }
        });
        obstacles.add(new Obstacle() {
            //Испытание - найти выход, решив головоломки
            int needIntellect = 7;
            public boolean checkPassing(Participant participant) {
                if (participant.getIntellect() >= needIntellect)
                    return true;
                else
                    return false;
            }
        });
    }

    public void doIt(Team team) {
        for (Obstacle test : obstacles) {
            team.Execute(test);
        }
    }
}
