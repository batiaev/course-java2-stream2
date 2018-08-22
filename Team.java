package com.example.jcore;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * Team - класс команда участников
 *
 * @version 1.0.1
 * @package com.example.jcore
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Team {

    /**
     *  @access private
     *  @var string name
     */
    private String name;

    /**
     *  @access private
     *  @var array players
     */
    private String[] players;

    /**
     *  @access private
     *  @var array history
     */
    private List<String> history = new ArrayList<String>();

    /**
     * constructor
     *
     * @param name - название команды
     * @param players - массив участников команды
     * @return undefined
     */
    public Team( String name, String[] players ) {

        this.name = name;
        this.players = players;
    }

    /**
     * getPlayers - получить массив игроков команды
     *
     * @return array
     */
    public String[] getPlayers() {

        return this.players;
    }

    /**
     * printPlayersMainInfo - метод вывода информации обо всех членах команды
     *
     * @return void
     */
    public void printPlayersMainInfo () {

        for ( int i = 0; i < this.players.length; i++ ) {
            System.out.println( this.players[i] );
        }
    }

    /**
     *
     * printPlayersCourseInfo - метод для вывода информации
     * о членах команды, прошедших дистанцию
     *
     * @return void
     */
    public void printPlayersCourseInfo () {

        Object[] history_array = this.history.toArray();

        for( Object action : history_array ){
            System.out.println( action );
        }
    }

    /**
     * passAnObstacle - пройти препятствие и записать действие и участника в историю
     *
     * @param name - имя участника
     * @param obstacle - название препятствия
     * @return void
     */
    public void passAnObstacle ( String name, String obstacle ) {

        this.history.add( name );
        this.history.add( obstacle );
    }
}