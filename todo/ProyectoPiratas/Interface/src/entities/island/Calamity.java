/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.island;

/**
 *
 * @author suber
 */
public class Calamity {
    private final String name;
    private final String type;
    private final int damage;
    private final int prob;

    public Calamity(String name, String type, int damage, int prob) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.prob = prob;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }
    
    public boolean did(){
        int number = (int)((Math.random()*100)+1);
        return prob>=number;
    }
    
}
