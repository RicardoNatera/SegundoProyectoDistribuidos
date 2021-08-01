/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmicliente;

import entities.ship.Ship;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.LinkedList;
import server.serverinterface.ServerInterface;
/**
 *
 * @author suber
 */
public class RMICliente {

    static int puertoRMI = 12345;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.getSecurityManager();
        
        try {
            Registry registro = LocateRegistry.getRegistry("localhost",puertoRMI);
            ServerInterface si;
            si = (ServerInterface) (registro.lookup("Servidor1"));
            
            System.out.println("Servidor1 encontrado");
            System.out.println("Enviando Barcos");
            LinkedList<String> listaNodos = new LinkedList<>();
            listaNodos.add("Servidor1");
            listaNodos.add("Servidor2");
            listaNodos.add("Servidor3");
            listaNodos.add("Servidor4");
            
            LinkedList<Ship> s = new LinkedList<>();
            
            //Aqui se añaden los 3 barcos
            s.add(new Ship(0,0,1,"name","Pirata",listaNodos,puertoRMI));
            s.add(new Ship(0,0,2,"aaaa","Pirata",listaNodos,puertoRMI));
            s.add(new Ship(0,0,2,"bbbb","Pirata",listaNodos,puertoRMI));
            //--------------------------------------------------------
            s.forEach((barco) -> {
                System.out.println("*****El Barco " +barco.getName() + " está saliendo.*****");
                try {
                    si.recibe(barco);
                } catch (RemoteException ex) {
                    System.out.println("Excepción en recibir al: "+barco.getName());
                }
            });
            
            System.out.println("Fin de la simulación");
            
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Excepción en Main: "+e);
        }
        // TODO code application logic here
    }
    
}
