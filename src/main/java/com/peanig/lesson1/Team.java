package com.peanig.lesson1;

import java.util.List;
import java.util.Arrays;

public class Team {
    private List<Member> members;

    public Team(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void showResults(){
        for (Member member: members){
            System.out.println(member.getName()+"'s marks:");
            System.out.println(Arrays.toString(member.getMarks()));
            System.out.print(member.getScore()+ " - ");
            if (member.isPassed() == true) {
                System.out.println("passed");
            } else {
                System.out.println("didn't pass");
            }
            System.out.println();
        }
    }
}
