/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Classes;


/**
 *
 * @author Gianne Bacay
 */
public class DataItem {
    private char data;

    DataItem() {
        this.data = '\0'; // Default value for char
    }

    DataItem(char d) {
        this.data = d;
    }

    DataItem(DataItem Item) {
        this.data = Item.data;
    }

    // Setters
    public void setItem(char data) {
        this.data = data;
    }

    // Getters
    public char getDataItem() {
        return this.data;
    }

    public char getItem() {
        return this.data;
    }

    public boolean equal(DataItem item) {
        return this.data == item.data;
    }

    @Override
    public String toString() {
        return Character.toString(this.data);
    }
}
