/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ship;

import entities.item.Treasure;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author suber
 */
public class Chest implements Serializable{
    protected int capacity;
    protected int currentState;
    private Treasure array[];
    private int size;
    private int totalCapacity;
    
    public Chest(int worldID, int capacity) {
        
        this.capacity=capacity;
        currentState=0;
        array=new Treasure[2];
        size=0;
        totalCapacity=2;
    }
    
    // to add an element at the end
    public void addElement(Treasure element){
        if(element.getSize()+currentState>capacity) return;
        
        // double the capacity if all the allocated space is utilized
        if (size == totalCapacity){
            ensureCapacity(2); 
        }
        array[size] = element;
        size++;
        currentState=currentState+element.getSize();
    }
    
    // to add an element at a particular index
    public void addElement(int index, Treasure element){
        if(element.getSize()+currentState>capacity) return;
        
        // double the capacity if all the allocated space is utilized
        if (size == totalCapacity){
            ensureCapacity(2); 
        }
        // shift all elements from the given index to right
        for(int i=size-1;i>=index;i--){
            array[i+1] = array[i];
        }
        // insert the element at the specified index
        array[index] = element;
        size++;
        currentState=currentState+element.getSize();
    }
    
    // to get an element at an index
    public Treasure getElement(int index){
        return array[index];
    }
    
    // to remove an element at a particular index
    public void remove(int index){
        if(index>=size || index<0){
            System.out.println("No element at this index");
        }else{
            Treasure e=this.getElement(index);
            currentState=currentState-e.getSize();

            for(int i=index;i<size-1;i++){
                array[i] = array[i+1];
            }
            array[size-1]=null;
            size--;
            
        }
        
    }
    
     /* method to increase the capacity, if necessary, to ensure it can hold at least the 
    *  number of elements specified by minimum capacity arguement
    */
    public void ensureCapacity(int minCapacity){
        Treasure temp[] = new Treasure[totalCapacity*minCapacity];
        for (int i=0; i < totalCapacity; i++){
            temp[i] = array[i];
        }
        array = temp;
        totalCapacity = totalCapacity * minCapacity;
    }
    
     /*
    *  Trim the capacity of dynamic array to the current size. i.e. remove unused space
    */
    public void trimToSize(){
        System.out.println("Trimming the array");
        Treasure temp[] = new Treasure[size];
        for (int i=0; i < size; i++){
            temp[i] = array[i];
        }
        array = temp;
        totalCapacity = array.length;
    }
    
    // to get the current size
    public int getSize(){
        return size;
    }
     
    // to get the current capacity
    public int getTotalCapacity(){
        return totalCapacity;
    }
    
    // method to print elements in array
    public void printElements(){
        System.out.println("elements in array are :"+Arrays.toString(array));
    }
    
    public String info(){
        String Tesoros="";
        for (Treasure treasure : array) {
            if(treasure!=null){
                Tesoros=Tesoros.concat(treasure.info()+"\n");
            }
        }
        return Tesoros;
    }
    
    // quicksort the array
    public static void quicksort (Treasure lista1[], int izq, int der){
        int i=izq;
        int j=der;
        int pivote=lista1[(i+j)/2].getSize();
        do {
            while (lista1[i].getSize()<pivote){
                i++;
            }
            while (lista1[j].getSize()>pivote){
                j--;
            }
            if (i<=j){
                Treasure e=lista1[i];
                lista1[i]=lista1[j];
                lista1[j]=e;
                i++;
                j--;
            }
        }while(i<=j);
        if (izq<j){
            quicksort(lista1, izq, j);
        }
        if (i<der){
            quicksort(lista1, i, der);
        }
    }
    
}
