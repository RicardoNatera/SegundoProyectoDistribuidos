/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author suber
 */
public abstract class Entity implements Serializable{
    
    protected float x,y;
    protected int worldID;
    protected String Name;
    protected BufferedImage texture;
    protected Rectangle r;
    protected int width,height;

    public Entity(float x, float y, int worldID,String Name,int width,int height) {
        this.x = x;
        this.y = y;
        this.worldID = worldID;
        this.Name=Name;
        this.texture=null;
        this.width=width;
        this.height=height;
        this.r=new Rectangle((int)x,(int)y,width,height);
    }
    
    public Entity(float x, float y, int worldID,String Name,int width,int height,BufferedImage texture) {
        this.x = x;
        this.y = y;
        this.worldID = worldID;
        this.Name=Name;
        this.texture=texture;
        this.width=width;
        this.height=height;
        this.r=new Rectangle((int)x,(int)y,width,height);
    }

    public boolean hover(int x,int y){
        return r.contains(x, y);
    }
    
    public Image getTexture() {
        return texture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWorldID() {
        return worldID;
    }

    public void setWorldID(int worldID) {
        this.worldID = worldID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }
    
    
    public abstract void tick();
    public abstract void tick(int x,int y);
    public abstract void render(Graphics g);
}
