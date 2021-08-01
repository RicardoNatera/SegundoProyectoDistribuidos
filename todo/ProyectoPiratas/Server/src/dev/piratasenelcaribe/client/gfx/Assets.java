/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.piratasenelcaribe.client.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author suber
 */
public class Assets {
    public static BufferedImage pirataD,pirataI,realD,realI,cofreA,cofreC,piedraP,piedraG,muelle,aguaBG;
    public static BufferedImage m1i1,m1i2,m1i3;
    public static BufferedImage m2i1,m2i2;
    public static BufferedImage m3i1,m3i2,m3i3;
    public static BufferedImage m4i1,m4i2;
    private static final int width=64,height=64;
    public static void init(){
        SpriteSheet sheet=new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet.png"));
        aguaBG=ImageLoader.loadImage("/textures/agua_fondo.png");
        pirataD = sheet.crop(0,0, width, height);
        pirataI = sheet.crop(width,0, width, height);
        realD = sheet.crop(width*2,0, width, height);
        realI = sheet.crop(width*3,0, width, height);
        cofreA = sheet.crop(0,height, width, height);
        cofreC = sheet.crop(width,height, width, height);
        piedraP = sheet.crop(width*2,height, width, height);
        piedraG = sheet.crop(width*3,height, width, height);
        muelle = sheet.crop(0,height*2, width, height);
        
        //islas
        m1i1=ImageLoader.loadImage("/textures/maquina1_isla1.png");
        m1i2=ImageLoader.loadImage("/textures/maquina1_isla2.png");
        m1i3=ImageLoader.loadImage("/textures/maquina1_isla3.png");
        

    }
}
