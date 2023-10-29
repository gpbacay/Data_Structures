/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


/**
 *
 * @author Gianne Bacay
 */
public class DataItemNode extends DataItem{
    DataItemNode next;
    DataItemNode prev;

    DataItemNode() {
        super('\0'); // Default value for char
        this.next = null;
        this.prev = null;
    }

    DataItemNode(char data) {
        super(data);
        this.next = null;
        this.prev = null;
    }

    DataItemNode(char data, DataItemNode next, DataItemNode prev) {
        super(data);
        this.next = next;
        this.prev = prev;
    }

    DataItemNode(DataItemNode next, DataItemNode prev) {
        this.next = next;
        this.prev = prev;
    }

    DataItemNode(DataItemNode dc) {
        this.setItem(dc.getItem());
        next = dc.getNextNode();
        prev = dc.getPrevNode();
    }

    // Setters
    public void setNext(DataItemNode node) {
        this.next = node;
    }

    public void setPrev(DataItemNode node) {
        this.prev = node;
    }

    // Getters
    public DataItemNode getNextNode() {
        return this.next;
    }

    public DataItemNode getPrevNode() {
        return this.prev;
    }
}
