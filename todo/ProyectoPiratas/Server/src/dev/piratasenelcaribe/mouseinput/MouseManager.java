/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.piratasenelcaribe.mouseinput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author suber
 */
public class MouseManager  implements MouseListener{

    public boolean hover;
    public int hoverX,hoverY;
    
    public MouseManager() {
        hover=false;
        hoverX=hoverY=-1;
    }

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        hover=true;
        hoverX=e.getX();
        hoverY=e.getY();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hover=false;
        hoverX=hoverY=-1;
    }
    
}
