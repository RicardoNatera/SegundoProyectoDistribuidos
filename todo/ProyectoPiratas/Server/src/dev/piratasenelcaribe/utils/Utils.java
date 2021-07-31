/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.piratasenelcaribe.utils;

/**
 *
 * @author suber
 */
public class Utils {
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            System.out.println("Error al tratar de transformar a entero");
            return 0;
        }
    }
}
