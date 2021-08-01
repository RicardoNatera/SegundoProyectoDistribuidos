/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.piratasenelcaribe.states;

import dev.piratasenelcaribe.client.Simulation;
import java.awt.Graphics;

/**
 *
 * @author suber
 */
public abstract class State {
    
    protected Simulation simulation;
    
    private static State currentState=null;

    public State(Simulation simulation) {
        this.simulation = simulation;
    }

    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }

    public static State getCurrentState() {
        return currentState;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
