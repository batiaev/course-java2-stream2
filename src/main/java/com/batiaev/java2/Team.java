package com.batiaev.java2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class Team {
    private String name;
    private List<Member> members = new ArrayList<>();

    private void showMembers() {
        if (members == null) {
            throw new IllegalStateException("Wat? Where all members??? (field 'members' is null)");
        } else if (members.size() == 0) {
            log.warn("Team is empty =/");
        } else {
            members.forEach(member -> log.info(member.getName()));
        }
    }

    public void showResults() {
        members.forEach(member -> log.info("{}: {}", member.getName(), member.getResult()));
    }
}
