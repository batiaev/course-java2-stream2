package com.batiaev.java2;

public class Course {

    // Массив препятствий типа int[], условная сложность
    private int[] obstacles = { 1, 2, 3, 4};

    // Метод doIt() принимает в качестве аргумента объект типа Team, затем вызывает
    // метод Team.overcomeObstacles(), которому в качестве параметра передается массив obstacles.
    public void doIt(Team t) {
        t.overcomeObstacles(obstacles);
    }

    public int[] getObstacles() {
        return obstacles;
    }

    public void setObstacles(int[] obstacles) {
        this.obstacles = obstacles;
    }

}