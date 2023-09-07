/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.util.ArrayList;
/**
 *
 * @author Gianne Bacay
 */
public class ProgrammingPractice1_ArrayList_BACAY {
    private static ArrayList<DataItem> DItemList = new ArrayList<>();
    
    // Create
    public static void createDataItem(DataItem item) {
        DItemList.add(item);
    }
    
    // Retrieve
    public static DataItem retrieveDataItem(DataItem item) {
        for (DataItem item1 : DItemList) {
            if (item1.getItem() == item.getItem()) {
                return item;
            }
        }
        return null; // Not found
    }
    
    // Update
    public static void updateDataItem(DataItem oldData, DataItem newData) {
        DataItem itemToUpdate = retrieveDataItem(oldData);
        if(itemToUpdate != null){
            itemToUpdate.setItem(newData.getItem());
        }
    }
    
    // Delete
    public static void deleteDataItem(DataItem item){
        DataItem itemToDelete = retrieveDataItem(item);
        if(itemToDelete != null) {
           for(byte i = 0; i < DItemList.size(); i++){
               if(DItemList.get(i).getItem() == itemToDelete.getItem()){
                   DItemList.remove(i);
               }
           }
        }
    }
    
    // Main
    public static void main(String[] args) {
//        //Scenario 1:
//        
//        //Insert three data items unto the arraylist:
//        for(short i = 1; i <= 3; i++){
//            createDataItem(new DataItem(i));
//        }
//        //display the three data items of the arraylist:
//        System.out.println("\n"+DItemList);
//        
//        //update the value of the data items by adding 2:
//        for (DataItem item : DItemList){
//            updateDataItem(item, new DataItem((short) (item.getItem() + (short) 2)));
//        }
//        //display the data items:
//        System.out.println("\n"+DItemList);
//        
//        //delete a data item:
//        DataItem itemToDelete1 = new DataItem((short) 5);
//        deleteDataItem(itemToDelete1);
//        //display the data items:
//        System.out.println("\n"+DItemList);
        
        
//        //Scenario 2:
//        //by using iterative statement
//        
//        //insert 1000 data items unto the arraylist:
//        for(short i = 1; i <= 1000; i++){
//            createDataItem(new DataItem(i));
//        }
//        //display the data items of the arraylist
//        System.out.println("\n"+DItemList);
//    
//        //update the value of the data items by adding 2:
//        for (DataItem item : DItemList){
//            updateDataItem(item, new DataItem((short) (item.getItem() + (short) 2)));
//        }
//        //display the data items:
//        System.out.println("\n"+DItemList);
//
//        //delete half of the data items:
//        for (short i = 0; i < 500; i++){
//            DItemList.remove(0);
//        }
//        //display the data items:
//        System.out.println("\n"+DItemList);


//        //Scenario 3:
//        //by using iterative statement
//        
//        //insert 100,000 (yes, 100k) data items unto the arraylist (or less depende sa makaya sa inyuhang unit):
//        for(short i = 1; i <= 10; i++){
//            for(short j = 1; j <= 10000; j++){
//                DataItem itemToAdd = new DataItem(j);
//                createDataItem(itemToAdd);
//            }
//        }
//        //display the data items of the arraylist:
//        System.out.println("\n"+DItemList);
//    
//        //update the value of the data items by adding 2:
//        for (DataItem item : DItemList){
//            updateDataItem(item, new DataItem((short) (item.getItem() + (short) 2)));
//        }
//        //display the data items:
//        System.out.println("\n"+DItemList);
//        System.out.println("\nArrayList Size: "+DItemList.size());
//
//        //delete half of the data items:
//        for (short i = 0; i < 5; i++){
//            for (short j = 0; j < 10000; j++) {
//                DItemList.remove(0);
//            }
//        }
//        //display the data items:
//        System.out.println("\n"+DItemList);
//        System.out.println("\nArrayList Size: "+DItemList.size());
    }
}
