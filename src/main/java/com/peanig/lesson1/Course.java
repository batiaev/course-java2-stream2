package com.peanig.lesson1;

import java.util.Random;

public class Course extends AbstractCourse implements Workable{
    private int highMark = 5;
    private float neededScore; // необходимый счет для сдачи экзамена

    public Course(String title, String teacher, int lessons){
        super();
        this.title = title;
        this.teacher = teacher;
        this.lessons = lessons;
        this.neededScore = highMark * lessons / 2;
    }

    public void doIt(Team team){
        Random random = new Random();
        for (Member member: team.getMembers()){
            int[] marks = new int[lessons];
            for(int i = 0; i<lessons; i++) {
                marks[i] = random.nextInt(highMark+1);
            }
            member.setMarks(marks);
            if(member.getScore() > neededScore) member.setPassed(true);
        }
    }
}
