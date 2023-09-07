/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Classes;

/**
 *
 * @author Gianne Bacay
 */
public class DataItem {
    private short data;
    
    DataItem(){
        this.data=0;
    }
    
    DataItem(short d){
        this.data=d;
    }
    
    DataItem(DataItem Item){
        this.data = Item.data;
    }
    
    public void copyDataItem(DataItem Item){
        this.data = Item.data;
    }
    
    //Setters
    public void setItem(short data){
        this.data = data;
    }
    
    //Getters
    public DataItem getDataItem(){
        return this;
    }
    
    public short getItem(){
        return this.data;
    }
    
    public boolean equal(DataItem item){
        return this.data==item.data;
    }
    
    @Override
    public String toString(){
        return Short.toString(this.data);
    }
}
