package com.example.jcore;

import java.lang.String;

/**
 * Course - класс полоса препятствий
 *
 * @version 1.0.1
 * @package com.example.jcore
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Course {

    /**
     *  @access private
     *  @var array obstacles
     */
    private String[] obstacles = { "лужа", "камень", "яма", "барьер" };

    /**
     * constructor
     *
     * @return undefined
     */
    public Course() {

    }

    /**
     * doIt - пройти командой всю полосу препятствий
     *
     * @param team - экземпляр класса команда участников
     * @return undefined
     */
    public void doIt ( Team team ) {

        String[] players = team.getPlayers();

        for ( int i = 0; i < players.length; i++ ) {
            for ( int j = 0; j < this.obstacles.length; j++ ) {
                team.passAnObstacle( players[i], this.obstacles[j] );
            }
        }
    }
}