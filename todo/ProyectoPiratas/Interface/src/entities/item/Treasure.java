/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.item;

/**
 *
 * @author suber
 */
public class Treasure {

    protected final String Name;
    protected final int size;
    protected final Map map;

    public Treasure(String Name, int size) {
        this.Name = Name;
        this.size = size;
        this.map=null;
    }

    public Treasure( int size, Map map) {
        this.Name="Mapa";
        this.size = size;
        this.map = map;
    }
    
    public String info(){
        String aux;
        if(Name.equals("Mapa")){
            aux="Mapa->Tamaño:"+this.getSize()+"->Origen:"+this.map.getPoint1()+"->Destino:"+this.map.getPoint2()+"->Distancia:"+this.map.getDistance()+"sg"+"\n";
        }else{
            aux=this.getName()+"->Tamaño:"+this.getSize()+"\n";
        }
        return aux;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return Name;
    }

    public Map getMap() {
        return map;
    }
}
