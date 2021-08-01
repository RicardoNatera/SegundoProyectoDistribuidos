/*
 * Interfaz del Barco
 */
package rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author suber
 */

public interface InterfazBarco extends Remote{
    void ejecuta() throws RemoteException;
}
