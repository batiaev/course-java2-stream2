package com.peanig.lesson1;

public class Member {
    private String name;
    private int[] marks;
    private int score;
    private boolean isPassed = false;

    public void setMarks(int[] marks) {
        this.marks = marks;
        score = 0;
        for(int i = 0; i < marks.length; i++ ){
            score += marks[i];
        }
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks;
    }

    public Member(String name) {
        this.name = name;
    }
}
