/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


/**
 *
 * @author Gianne Bacay
 */
public class DoublyLinkedList {
    private static DataItemNode Head;
    private static DataItemNode Tail;
    private static int size;

    public DoublyLinkedList() {
        Head = new DataItemNode();
        Tail = new DataItemNode();
        size = 0;
        Head.setNext(Tail);
        Tail.setPrev(Head);
    }

    public static void MakeEmpty() {
        size = 0;
        Head = new DataItemNode();
        Tail = new DataItemNode();
        Head.setNext(Tail);
        Head.setPrev(null);
        Tail.setNext(null);
        Tail.setPrev(Head);
    }

    public static void PrintList() {
        DataItemNode nav = Head;
        while (nav != null) {
            System.out.print(nav.getItem() + " ");
            nav = nav.getNextNode();
        }
        System.out.println();
    }

    public static void Add(char data) {
        DataItemNode newNode = new DataItemNode(data);
        if (size == 0) {
            Head = newNode;
            Tail = newNode;
        } else {
            DataItemNode nav = Head;
            while (nav.getNextNode() != null) {
                nav = nav.getNextNode();
            }
            nav.setNext(newNode);
            newNode.setPrev(nav);
        }
        size++;
    }

    public static void Insert(char data, int index) {
        if (index < 0 || index >= size) {
            return;
        }
        DataItemNode newNode = new DataItemNode(data);
        if (index == 0) {
            newNode.setNext(Head);
            Head.setPrev(newNode);
            Head = newNode;
        } else {
            DataItemNode nav = Head;
            for (int i = 0; i < index - 1; i++) {
                nav = nav.getNextNode();
            }
            newNode.setNext(nav.getNextNode());
            nav.getNextNode().setPrev(newNode);
            nav.setNext(newNode);
            newNode.setPrev(nav);
        }
        size++;
    }

    public static void RemoveByValue(char data) {
        DataItemNode nav = Head;
        DataItemNode prev = null;
        while (nav != null && nav.getItem() != data) {
            prev = nav;
            nav = nav.getNextNode();
        }
        if (nav == null) {
            return;
        }
        if (nav == Head) {
            Head = nav.getNextNode();
        } else {
            prev.setNext(nav.getNextNode());
        }
        if (nav == Tail) {
            Tail = prev;
        }
        size--;
    }

    public static void RemoveByIndex(int index) {
        if (index < 0 || index >= size) {
        }
        DataItemNode nav = Head;
        if (index == 0) {
            Head = nav.getNextNode();
            Head.setPrev(null);
            size--;
            return;
        }
        for (int i = 0; i < index; i++) {
            nav = nav.getNextNode();
        }
        DataItemNode prevNode = nav.getPrevNode();
        DataItemNode nextNode = nav.getNextNode();
        if (prevNode != null) {
            prevNode.setNext(nextNode);
        }
        if (nextNode != null) {
            nextNode.setPrev(prevNode);
        }
        if (nav == Tail) {
            Tail = prevNode;
        }
        size--;
    }

    public static DataItemNode FindByValue(char data) {
        DataItemNode nav = Head;
        while (nav != null && nav.getItem() != data) {
            nav = nav.getNextNode();
        }
        return nav;
    }

    public static int FindByValue(DataItemNode dc) {
        if (size > 0) {
            int x = 0, y = size - 1;
            DataItemNode nav1 = Head.getNextNode();
            DataItemNode nav2 = Tail.getPrevNode();
            while (x != y) {
                if (nav1.equals(dc)) return x;
                if (nav2.equals(dc)) return y;
                nav1 = nav1.getNextNode();
                nav2 = nav2.getPrevNode();
                x++;
                y--;
            }
            if (nav1.getItem() == dc.getItem()) return x;
            else if (nav2.getItem() == dc.getItem()) return y;
            else return -1;
        }
        return -1;
    }

    public static DataItemNode FindByIndex(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        DataItemNode nav = Head;
        for (int i = 0; i < index; i++) {
            nav = nav.getNextNode();
        }
        return nav;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DataItemNode nav = Head;
        while (nav != null) {
            sb.append(nav.getItem());
            nav = nav.getNextNode();
        }
        return sb.toString();
    }
}
