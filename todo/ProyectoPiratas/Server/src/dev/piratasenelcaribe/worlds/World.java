/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.piratasenelcaribe.worlds;

import dev.piratasenelcaribe.client.gfx.Assets;
import dev.piratasenelcaribe.utils.Utils;
import entities.island.Calamity;
import entities.island.Island;
import entities.item.Map;
import entities.item.Treasure;
import entities.ship.Chest;
import entities.ship.Ship;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author suber
 */
public class World {
    
    public int worldId;
    public Island[] islands;
    public Ship[] barcoInicial;
    public int shipSize;
    public int indexIslands;
    public int width,height;
    
    public World(String path,int width,int height) {
        this.islands=null;
        this.width=width;
        this.height=height;
        this.shipSize=0;
        try {
            loadWorld(path);
        } catch (ParserConfigurationException | SAXException ex) {
            System.out.println("Error al cargar el archivo");
        }
        
    }
    
    public void tick(){
        for (Island land : islands) {
            land.tick();
        }
        
    }
    
    public void render(Graphics g){
        g.drawImage(Assets.aguaBG,0,0, width,height, null);
        for (Island land : islands) {
            land.render(g);
        }

    }
    
    private void loadWorld(String path)throws ParserConfigurationException, SAXException{
        try {
            File file = new File(path);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            
            //lectura de carga inicial
            NodeList nList = document.getElementsByTagName("initialData");
            Node nNode = nList.item(0);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;                
                this.worldId=Utils.parseInt(eElement.getAttribute("worldID"));

                NodeList sList = document.getElementsByTagName("ship");
                Ship[] barcosAux=new Ship[sList.getLength()];
                for (int i = 0; i < sList.getLength(); i++) {
                    Node sNode = sList.item(i);
                    if (sNode!=null && sNode.getNodeType() == Node.ELEMENT_NODE) {
                        
                        Element sElement = (Element) sNode;
                        Ship auxs=new Ship(0,0,this.worldId,sElement.getElementsByTagName("name").item(0).getTextContent(),sElement.getElementsByTagName("kind").item(0).getTextContent(),null,0,sElement.getElementsByTagName("start").item(0).getTextContent());
                        
                        System.out.println("ID Start Island: "+sElement.getElementsByTagName("start").item(0).getTextContent());
                        System.out.println("name: " + sElement.getElementsByTagName("name").item(0).getTextContent());
                        System.out.println("kind : " + sElement.getElementsByTagName("kind").item(0).getTextContent());
                        
                        System.out.println("Crew : " + sElement.getElementsByTagName("Crew").item(0).getTextContent());
                        auxs.setTripulacion(Utils.parseInt(sElement.getElementsByTagName("Crew").item(0).getTextContent()));
                        
                        System.out.println("servings: " + sElement.getElementsByTagName("servings").item(0).getTextContent());
                        auxs.setRaciones(Utils.parseInt(sElement.getElementsByTagName("servings").item(0).getTextContent()));
                        System.out.println("supplies : " + sElement.getElementsByTagName("supplies").item(0).getTextContent());
                        auxs.setMuniciones(Utils.parseInt(sElement.getElementsByTagName("supplies").item(0).getTextContent()));
                        
                        Treasure t;
                        
                        t=new Treasure(5,new Map(sElement.getElementsByTagName("map").item(0).getChildNodes().item(1).getTextContent(),sElement.getElementsByTagName("map").item(0).getChildNodes().item(3).getTextContent(),Utils.parseInt(sElement.getElementsByTagName("map").item(0).getChildNodes().item(5).getTextContent())));
                        System.out.println("Mapa Inicial");
                        System.out.println("Origen: " + sElement.getElementsByTagName("map").item(0).getChildNodes().item(1).getTextContent());
                        System.out.println("Destino: " + sElement.getElementsByTagName("map").item(0).getChildNodes().item(3).getTextContent());
                        System.out.println("Distancia: " + sElement.getElementsByTagName("map").item(0).getChildNodes().item(5).getTextContent());
                        auxs.getChest().addElement(t);
                        
                        barcosAux[shipSize]=auxs;
                        shipSize++;
                    }
                    
                }
                barcoInicial=new Ship[shipSize];
                shipSize=0;
                for (int i = 0; i < barcosAux.length; i++) {
                    Ship b = barcosAux[i];
                    if(b!=null)
                        barcoInicial[i]=b;   
                }
            }
            
            
            //con esa informacion se crea una instancia de barco pirata
            
            //-----------------------------------------------------------------------------------------------------------------
            
            //lectura de informacion de islas
            
            nList = document.getElementsByTagName("island");
            
            if(nList.getLength()>0){
                islands = new Island[nList.getLength()];
                indexIslands=0;
            }
            
            for (int i = 0; i < nList.getLength(); i++) {
                Node e = nList.item(i);
                Island aux=new Island();
                
                if (e.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) e;
                    
                    aux.setId(Utils.parseInt(eElement.getAttribute("id")));
                    
                    aux.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    
                    aux.setX(Utils.parseInt(eElement.getElementsByTagName("coord").item(0).getChildNodes().item(1).getTextContent()));
                    
                    aux.setY(Utils.parseInt(eElement.getElementsByTagName("coord").item(0).getChildNodes().item(3).getTextContent()));

                    aux.setCostoTri(Utils.parseInt(eElement.getElementsByTagName("costs").item(0).getChildNodes().item(1).getTextContent()));
                    
                    aux.setCostoMun(Utils.parseInt(eElement.getElementsByTagName("costs").item(0).getChildNodes().item(3).getTextContent()));

                    String pointsAux="";
                    NodeList points=eElement.getElementsByTagName("points");
                    if(points.getLength()>0){
                        Node a=points.item(0);
                        NodeList nameL= a.getChildNodes();
                        for (int j = 0; j < nameL.getLength(); j++) {
                            Node b = nameL.item(j);
                            if (b.getNodeType() == Node.ELEMENT_NODE) {
                              pointsAux = pointsAux.concat(b.getTextContent()+"\n");
                            }   
                        }
                    }
                    
                    aux.setInterestPoints(pointsAux);
                    
                    
                    NodeList chestL= eElement.getElementsByTagName("chest");
                    Chest auxC=new Chest(aux.getWorldID(),999);
                    if(chestL.getLength()>0){
                        
                        Node a=chestL.item(0);
                        NodeList treasureL= a.getChildNodes();
                        for (int j = 0; j < treasureL.getLength(); j++) {
                            Node b = treasureL.item(j);
                            if (b.getNodeType() == Node.ELEMENT_NODE) {
                              Map auxM=new Map();
                              boolean m=false;
                              Element bElement = (Element) b;
                              if(bElement.getElementsByTagName("name").item(0)!=null){
                              }else if(bElement.getElementsByTagName("map").item(0)!=null){
                                  auxM.setPoint1(bElement.getElementsByTagName("map").item(0).getChildNodes().item(1).getTextContent());
                                  auxM.setPoint2(bElement.getElementsByTagName("map").item(0).getChildNodes().item(3).getTextContent());
                                  auxM.setDistance(Utils.parseInt(bElement.getElementsByTagName("map").item(0).getChildNodes().item(5).getTextContent()));
                                  m=true;
                              }
                              if(m){
                                  auxC.addElement(new Treasure(Utils.parseInt(bElement.getElementsByTagName("size").item(0).getTextContent()),auxM));
                              }else{
                                  auxC.addElement(new Treasure(bElement.getElementsByTagName("name").item(0).getTextContent(),Utils.parseInt(bElement.getElementsByTagName("size").item(0).getTextContent())));
                              }
                            }   
                        }
                    }
                    aux.setLocalChest(auxC);
                    
                    NodeList calamityL= eElement.getElementsByTagName("calamity");
                    if(calamityL.getLength()>0){
                        
                        Node a=calamityL.item(0);
                        NodeList infoL= a.getChildNodes();
                        aux.createCalArray(infoL.getLength());
                        for (int j = 0; j < infoL.getLength(); j++) {
                            Node b = infoL.item(j);
                            Calamity auxCal;
                            if (b.getNodeType() == Node.ELEMENT_NODE) {

                              Element bElement = (Element) b;
                              auxCal=new Calamity(bElement.getElementsByTagName("name").item(0).getTextContent(),
                              bElement.getElementsByTagName("type").item(0).getTextContent(),
                              Utils.parseInt(bElement.getElementsByTagName("damage").item(0).getTextContent()),
                              Utils.parseInt(bElement.getElementsByTagName("prob").item(0).getTextContent()));
                              
                              aux.addCal(auxCal);
                            }   
                        }
                    }
                }
                aux.setWorldID(worldId);
                //Setting islands textures
                switch (worldId) {
                    case 1 -> {
                    switch (indexIslands) {
                        case 0 -> aux.setTexture(Assets.m1i1);
                        case 1 -> aux.setTexture(Assets.m1i2);
                        case 2 -> aux.setTexture(Assets.m1i3);
                        default -> {
                        }
                    }
                    }
                    case 2 -> {
                        if(indexIslands==0){
                            aux.setTexture(Assets.m2i1);
                        }else if(indexIslands==1){
                            aux.setTexture(Assets.m2i2);
                        }
                    }
                    case 3 -> {
                    switch (indexIslands) {
                        case 0 -> aux.setTexture(Assets.m3i1);
                        case 1 -> aux.setTexture(Assets.m3i2);
                        case 2 -> aux.setTexture(Assets.m3i3);
                        default -> {
                        }
                    }
                    }
                    case 4 -> {
                        if(indexIslands==0){
                            aux.setTexture(Assets.m4i1);
                        }else if(indexIslands==1){
                            aux.setTexture(Assets.m4i2);
                        }
                    }
                    default -> {
                    }
                }
                
                islands[indexIslands]=aux;
                indexIslands++;
            }
            for (Island land : islands) {
                System.out.println(land.info());
            } 
            for (Ship ship : barcoInicial) {
                System.out.println(ship.info());
            } 
//-----------------------------------------------------------------------------------------------------------------
        }
        catch(IOException e) {
            System.out.println(e);
        } 
    }
}
