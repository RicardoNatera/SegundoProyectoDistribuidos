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
public class Map {
    
    private String point1, point2;
    private int distance;

    public Map(String point1, String point2, int distance) {
        this.point1 = point1;
        this.point2 = point2;
        this.distance = distance;
    }

    public Map() {
    }

    public String getPoint1() {
        return point1;
    }
    
    public String getPoint2() {
        return point2;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPoint1(String point1) {
        this.point1 = point1;
    }
    
    public void setPoint2(String point2) {
        this.point2 = point2;
    }
   
}
