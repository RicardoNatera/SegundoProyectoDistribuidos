/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ship;

import entities.Entity;
import java.awt.Graphics;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import rmiinterface.InterfazBarco;
import server.serverinterface.ServerInterface;

/**
 *
 * @author suber
 */
public class Ship extends Entity implements InterfazBarco{
    
    protected final String Kind;
    protected int tripulacion;
    protected int raciones;
    protected int municiones;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_WIDTH =64,DEFAULT_HEIGHT = 64;
    protected float velocidad;
    protected String state;
    protected Chest chest;
    protected String origen,destino;
    
    protected float xMove,yMove;

   int indiceNodo; // cuál es el siguiente ordenador a visitar
   LinkedList listaNodos; // el itinerario
   int puertoRMI = 12345 ;

    public int getRaciones() {
        return raciones;
    }

    public int getMuniciones() {
        return municiones;
    }

    public int getTripulacion() {
        return tripulacion;
    }

    public void setRaciones(int raciones) {
        this.raciones = raciones;
    }

    public void setMuniciones(int municiones) {
        this.municiones = municiones;
    }

    public void setTripulacion(int tripulacion) {
        this.tripulacion = tripulacion;
    }

   
    public Ship( float x, float y, int worldID, String name,String Kind,LinkedList laListaComputadoras,int elPuertoRMI) {
        super(x,y,worldID,name, DEFAULT_WIDTH,DEFAULT_HEIGHT);
        this.Kind=Kind;
        this.velocidad = DEFAULT_SPEED;
        this.xMove=0;
        this.yMove=0; 
        this.listaNodos=laListaComputadoras;
        this.puertoRMI=elPuertoRMI;
        this.indiceNodo=0; 
        
        if(this.Kind.equals("pirate")){
            this.chest=new Chest(this.worldID,100);
        }else{
            this.chest=new Chest(this.worldID,50);
        }
    }
    public Ship( float x, float y, int worldID, String name,String Kind,LinkedList laListaComputadoras,int elPuertoRMI,String origen) {
        super(x,y,worldID,name, DEFAULT_WIDTH,DEFAULT_HEIGHT);
        this.Kind=Kind;
        this.velocidad = DEFAULT_SPEED;
        this.xMove=0;
        this.yMove=0; 
        this.listaNodos=laListaComputadoras;
        this.puertoRMI=elPuertoRMI;
        this.indiceNodo=0;
        this.origen=origen;
        this.destino="";    
        
        if(this.Kind.equals("pirate")){
            this.chest=new Chest(this.worldID,100);
        }else{
            this.chest=new Chest(this.worldID,50);
        }
    }
    public Ship( float x, float y, int worldID, String name,String Kind,LinkedList laListaComputadoras,int elPuertoRMI,String origen,String destino) {
        super(x,y,worldID,name, DEFAULT_WIDTH,DEFAULT_HEIGHT);
        this.Kind=Kind;
        this.velocidad = DEFAULT_SPEED;
        this.xMove=0;
        this.yMove=0; 
        this.listaNodos=laListaComputadoras;
        this.puertoRMI=elPuertoRMI;
        this.indiceNodo=0;
        this.origen=origen;
        this.destino=destino; 
        
        if(this.Kind.equals("pirate")){
            this.chest=new Chest(this.worldID,100);
        }else{
            this.chest=new Chest(this.worldID,50);
        }
    }
    
    public void next(String siguiente, String actual)throws RemoteException {
        siguiente = (String) listaNodos.get(indiceNodo);
            
        Registry registro=LocateRegistry.getRegistry("localhost",puertoRMI);
        ServerInterface h;
        try {
            System.out.println("Buscando "+siguiente+" en "+actual+" completado");
            h = (ServerInterface) registro.lookup(siguiente);
            h.recibe(this);
        }catch(RemoteException | NotBoundException ex){
            System.out.println("Error al mandar el agente "+this.getName()+ " a "+siguiente); 
            listaNodos.remove(siguiente);
            indiceNodo=0;
            siguiente = (String) listaNodos.get(indiceNodo);
            indiceNodo++;
            if(siguiente.equals(actual) && listaNodos.size()==1){
                listaNodos.remove(actual);
            }else if(indiceNodo<listaNodos.size()){
                next(siguiente,actual);
            }else{
                indiceNodo=0;
                next(siguiente,actual);
            }
        }
    }
    @Override
    public void ejecuta() throws RemoteException {
        String actual,siguiente="";
        System.out.println("El barco ha llegado: "+this.getName());
        actual = (String) listaNodos.get(indiceNodo);
        
        indiceNodo++;
        if(indiceNodo<listaNodos.size()){
            next(siguiente,actual);
        }else{
            indiceNodo=0;
            next(siguiente,actual);
        } 
    }
    
    public void move(){
        x += xMove;
        y += yMove;
    }
    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public String getName() {
        return Name;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Chest getChest() {
        return chest;
    }
     public String info(){
         return "Barco: "+this.getName()+"\n";
     }
    

   @Override
    public void tick(int x,int y) {
        xMove=yMove=0;
        //Logica de la simulacion
        
        //------------
        if(this.hover(x,y)){
            System.out.println("estas encima de "+this.Name);
        }
        
    }
    
    @Override
    public void render(Graphics g) {
        if(texture!=null) g.drawImage(texture, (int)x, (int)y, width, height, null);
    }

    @Override
    public void tick() {
    } 
}
