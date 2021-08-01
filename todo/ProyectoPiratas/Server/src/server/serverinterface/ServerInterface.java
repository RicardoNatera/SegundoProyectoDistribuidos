/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.serverinterface;

import entities.ship.Ship;
import java.rmi.*;
import java.util.LinkedList;

/**
 *
 * @author suber
 */
public interface ServerInterface extends Remote{
    public void recibe(LinkedList<Ship> s) throws RemoteException;
    public void recibe(Ship s) throws RemoteException;
}
