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
public class Course {
    private List<Obstacle> obstacleList = new ArrayList<>();

    public void doIt(Team team) {
        obstacleList.forEach(obstacle -> team.getMembers().forEach(obstacle::overcome));
    }
}
