/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import dev.piratasenelcaribe.client.Simulation;
import entities.ship.Ship;
//import java.io.*;
//import java.rmi.Naming;
//import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import server.serverinterface.ServerInterface;
/**
 *
 * @author suber
 */

public class ServerOperation extends UnicastRemoteObject implements ServerInterface{
    private static final long serialVersionUID = 1L;
    static int puertoRMI = 12345;
    static Registry registro;
    static String miNombre= "Servidor";
    
    protected ServerOperation() throws RemoteException {
        super();
    }
    
    @Override
    public void recibe(Ship s) throws RemoteException {
        System.out.println("*****El Barco " +s.getName() + " ha llegado.*****");
        Hilo hilo=new Hilo(s);
        hilo.start();
    }
    @Override
    public void recibe(LinkedList<Ship> s) throws RemoteException {
        
        s.forEach((barco) -> {
            System.out.println("*****AAAAAAAAAAAAEl Barco " +barco.getName() + " ha llegado.*****");
            try {
                barco.ejecuta() ;
            } catch (RemoteException ex) {
                System.out.println("Hubo un error al tratar de ejecutar el barco: "+barco.getName());
            }
        });
    }
    
    public static void main(String[] args){
        
        
        miNombre = miNombre + JOptionPane.showInputDialog("Numero del Servidor");
        
        try {
            //System.setSecurityManager(new SecurityManager());
            //SecurityManager securityManager = System.getSecurityManager();
            registro = LocateRegistry.getRegistry(puertoRMI);
            
            try{
                
                registro.rebind(miNombre, new ServerOperation());
            }catch(RemoteException e){
                System.out.println("Comenzando rmiregistry en el puerto: "+puertoRMI);
                LocateRegistry.createRegistry(puertoRMI);
                
                registro.rebind(miNombre, new ServerOperation());
            }
            System.out.println(miNombre + " listo!");
            System.out.println("Servidores conectados actualmente: "+Arrays.toString(registro.list()));
            Simulation simulation = new Simulation("a",660,480);
            simulation.start();
        } catch (RemoteException e) {
            System.err.println("Server exception: " + e.toString());
        }
    }

}