/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.piratasenelcaribe.states;

import dev.piratasenelcaribe.client.Simulation;
import dev.piratasenelcaribe.client.gfx.Assets;
import dev.piratasenelcaribe.worlds.World;
import java.awt.Graphics;

/**
 *
 * @author suber
 */
public class SimulationState extends State{

    private final World world;
    public SimulationState(Simulation simulation) {
        super(simulation);
        world = new World("res/worlds/cliente1.xml",Simulation.getWidth(),Simulation.getHeight());
    }

    @Override
    public void tick() {
        world.tick();
        
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        //g.drawImage(Assets.cofreA, 0, 0, 92,92,null);
        //g.drawImage(Assets.cofreA, 280, 280, 92,92,null);
        //g.drawImage(Assets.cofreA, 450, 70, 92,92,null);
        //g.drawImage(Assets.pirataD, 100, 100, 64,64,null);
    }   
}
