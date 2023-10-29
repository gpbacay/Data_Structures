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
    public static DataItemNode Head;
    public static DataItemNode Tail;
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
            System.out.print(nav.getPatriarchName() + " ");
            nav = nav.getNextNode();
        }
        System.out.println();
    }

    public void Add(Patriarch data) {
        DataItemNode newNode = new DataItemNode(data);
        if (size == 0) {
            Head = newNode;
            Tail = newNode;
        } else {
            Tail.setNext(newNode);
            newNode.setPrev(Tail);
            Tail = newNode;
        }
        size++;
    }

    public void Insert(Patriarch data, int index) {
        if (index < 0 || index > size) {
            return;
        }

        DataItemNode newNode = new DataItemNode(data);

        if (index == 0) {
            newNode.setNext(Head);
            Head.setPrev(newNode);
            Head = newNode;
        } else if (index == size) {
            Tail.setNext(newNode);
            newNode.setPrev(Tail);
            Tail = newNode;
        } else {
            DataItemNode nav = Head;
            for (int i = 0; i < index - 1; i++) {
                nav = nav.getNextNode();
            }
            newNode.setNext(nav.getNextNode());
            newNode.setPrev(nav);
            nav.getNextNode().setPrev(newNode);
            nav.setNext(newNode);
        }
        size++;
    }

    public void RemoveByValue(Patriarch data) {
        DataItemNode nav = Head;
        DataItemNode prev = null;
        while (nav != null && !nav.getPatriarchName().equals(data.getPatriarchName()) ||
                !nav.getYearsFromCreation().equals(data.getYearsFromCreation()) ||
                !nav.getSonName().equals(data.getSonName()) ||
                !nav.getFatherName().equals(data.getFatherName()) ||
                !nav.getAgeAtFirstChild().equals(data.getAgeAtFirstChild()) ||
                !nav.getearsAfterFirstChild().equals(data.getearsAfterFirstChild()) ||
                !nav.getLifespan().equals(data.getLifespan())) {
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

    public void RemoveByIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        DataItemNode nav = Head;

        if (index == 0) {
            Head = nav.getNextNode();
            Head.setPrev(null);
        } else if (index == size - 1) {
            Tail = Tail.getPrevNode();
            Tail.setNext(null);
        } else {
            for (int i = 0; i < index; i++) {
                nav = nav.getNextNode();
            }
            DataItemNode prevNode = nav.getPrevNode();
            DataItemNode nextNode = nav.getNextNode();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }

        size--;
    }

    public static DataItemNode FindByValue(Patriarch data) {
        DataItemNode nav = Head;
        while (nav != null && !nav.getPatriarchName().equals(data.getPatriarchName()) ||
                !nav.getYearsFromCreation().equals(data.getYearsFromCreation()) ||
                !nav.getSonName().equals(data.getSonName()) ||
                !nav.getFatherName().equals(data.getFatherName()) ||
                !nav.getAgeAtFirstChild().equals(data.getAgeAtFirstChild()) ||
                !nav.getearsAfterFirstChild().equals(data.getearsAfterFirstChild()) ||
                !nav.getLifespan().equals(data.getLifespan())) {
            nav = nav.getNextNode();
        }
        return nav;
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

}
