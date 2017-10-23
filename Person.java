/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

/**
 *
 * @author Sony
 */
public class Person {
    char game_start;
    String username;

    public Person(char game_start, String username) {
        this.game_start = game_start;
        this.username = username;
    }

    public char getGame_start() {
        return game_start;
    }

    public String getUsername() {
        return username;
    }
    
}
