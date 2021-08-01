/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import entities.ship.Ship;
import java.rmi.RemoteException;

/**
 *
 * @author suber
 */
public class Hilo implements Runnable{
    private final Ship ship;
    private Thread thread;

    public Hilo(Ship ship) {
        this.ship = ship;
        this.thread = new Thread();
    }

    
    
    @Override
    public void run() {
        try {
            ship.ejecuta();
            this.stop();
        } catch (RemoteException ex) {
            System.out.println("Error al ejecutar al barco "+ship.getName());        
        } catch (InterruptedException ex) {
            System.out.println("Error al tratar de eliminar el hilo del barco "+ship.getName());        
        }
    }
    
    public synchronized void start(){
        //if(active) return;
        
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() throws InterruptedException{
        thread.join();
    }

    public Ship getShip() {
        return ship;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
