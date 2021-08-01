/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.piratasenelcaribe.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author suber
 */
public class Tile {
    public static Tile[] tiles = new Tile[256];
    public static Tile waterTile = new WaterTile(0);
    
    protected BufferedImage texture;
    protected final int id;
    public static final int TILEWIDTH=64, TILEHEIGHT=64;
    
    public Tile(BufferedImage texture,int id){
        this.id=id;
        this.texture=texture;
        
        tiles[id]= this;
    }

    public int getId() {
        return id;
    }
    
    public void tick(){
        
    }
    public boolean isSolid(){
        return false;
    }
    public void render(Graphics g, int x, int y){
      g.drawImage(texture, x, y, TILEWIDTH,TILEHEIGHT,null);
    }
    
}
