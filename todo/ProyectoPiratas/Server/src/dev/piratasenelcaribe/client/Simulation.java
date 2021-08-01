/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.piratasenelcaribe.client;

import dev.piratasenelcaribe.client.gfx.Assets;
import dev.piratasenelcaribe.display.Display;
import dev.piratasenelcaribe.mouseinput.MouseManager;
import dev.piratasenelcaribe.states.MenuState;
import dev.piratasenelcaribe.states.SimulationState;
import dev.piratasenelcaribe.states.State;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suber
 */
public class Simulation implements Runnable{
    private Display display; 
    static public int width,height;
    public String title;
    private boolean running=false;

    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State simulationState;
    private State menuState;
    
    private MouseManager mouseManager;

    
    public Simulation(String title, int width,int height){
        this.width=width;
        this.height=height;
        this.title=title;
        mouseManager=new MouseManager();
    }
    
    private void init(){
        display = new Display(title,width,height);
        //display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        Assets.init();
        
        simulationState = new SimulationState(this);
        menuState = new MenuState(this);
        State.setCurrentState(simulationState);
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    private void tick(){
        
        if(State.getCurrentState() != null){
            State.getCurrentState().tick();
        }
    }
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
       if(bs == null){
           display.getCanvas().createBufferStrategy(3);
           return;
       }
       g = bs.getDrawGraphics();
       //Clear
       g.clearRect(0, 0, width, height);
       // Draw here
       if(State.getCurrentState() != null){
        State.getCurrentState().render(g);
       }
       //-----------
       bs.show();
       g.dispose();
       
    }
    
    @Override
    public void run() {
      
        init();

        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta=0;
        long now;
        long lastTime = System.nanoTime();
        long timer=0;
        long ticks=0;
        
        while (running) {
            now = System.nanoTime();
            delta += (now-lastTime)/timePerTick;
            timer+=now-lastTime;
            lastTime = now;
            
            if(delta>=1){
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer>=1000000000){
                System.out.println("Ticks and frames: "+ticks);
                ticks=0;
                timer=0;
            }
        }

        try {
            stop();
        } catch (InterruptedException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    public synchronized void start(){
        if(running) return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() throws InterruptedException{
        if(!running) return;
        
        running= false;
        thread.join();
    } 

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
