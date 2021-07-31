/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.island;

import entities.Entity;
import entities.item.Treasure;
import entities.ship.Chest;
import java.awt.Graphics;

/**
 *
 * @author suber
 */
public class Island extends Entity {
    
    protected Chest localChest;
    protected int id,costoTri,costoMun;
    protected Calamity[] cals;
    protected int indexCals;
    protected String interestPoints;
    public static final int DEFAULT_WIDTH = 124,DEFAULT_HEIGHT = 124;
    
    

    public Island(int id,int costoTri,int costoMun,float x, float y, int worldID, String Name, int capacity,Treasure[] objects) {
        super(x, y, worldID, Name, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.localChest = new Chest(worldID,capacity);
        
        for (Treasure object : objects) {
            localChest.addElement(object);
        }
        this.id=id;
        this.costoTri=costoTri;
        this.costoMun=costoMun;
        this.interestPoints="";
    }

    public Island() {
        super(0, 0, 0, null, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.interestPoints="";
    }

    public String getInterestPoints() {
        return interestPoints;
    }
    
    public String info(){
        String c="";
        for (Calamity cal : cals) {
            if(cal!=null){
                c=c.concat("\n"+cal.getName()+"\n"+cal.getDamage()+"% de la cantidad total puede ser afectada"+"\n"+"Afectará a la "+cal.getType());
            }
        }
        String chesa=localChest.info();
        
        
        return "Isla:"+this.Name+"\n"+
                "Puntos de Interes: "+interestPoints+"\n"+
                "id y WorldId:["+id+","+worldID+"]"+"\n"+
                "Costo por explorar(tripulacion y munición:["+costoTri+","+costoMun+"]"+"\n"+
                "Calamidades:"+"\n"+c+"\n"+
                "Cofre en la isla:"+"\n"+
                chesa;
    }

    public void setInterestPoints(String interestPoints) {
        this.interestPoints = interestPoints;
    }

    
    public int getCostoMun() {
        return costoMun;
    }

    public int getCostoTri() {
        return costoTri;
    }

    public void setCostoMun(int costoMun) {
        this.costoMun = costoMun;
    }

    public void setCostoTri(int costoTri) {
        this.costoTri = costoTri;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Calamity[] getCals() {
        return cals;
    }

    public void setCals(Calamity[] cals) {
        this.cals = cals;
    }
    public void createCalArray(int size){
        this.cals=new Calamity[size];
        this.indexCals=0;
    }
    public void addCal(Calamity c){
        this.cals[this.indexCals]=c;
        this.indexCals++;
    }

    public Chest getLocalChest() {
        return localChest;
    }

    public void setLocalChest(Chest localChest) {
        this.localChest = localChest;
    }

    
    @Override
    public void tick(int x,int y) {
        if(this.hover(x,y)){
            System.out.println("estas encima de "+this.Name);
        }
    }

    @Override
    public void tick() {
    }
    
    @Override
    public void render(Graphics g) {
        if(texture!=null) g.drawImage(texture, (int)x, (int)y, width, height, null);
    }
    
}
