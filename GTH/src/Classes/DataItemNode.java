/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


/**
 *
 * @author Gianne Bacay
 */
public class DataItemNode extends Patriarch{
    private DataItemNode next;
  private DataItemNode prev;

  public DataItemNode() {
    super(" ", " "," "," "," "," "," ");
    this.next = null;
    this.prev = null;
  }

  public DataItemNode(String patriarchName,String yearsFromCreation,String sonName,
        String fatherName,String ageAtFirstChild,String yearsAfterFirstChild,String lifespan) {
    super(patriarchName,yearsFromCreation,sonName,fatherName,ageAtFirstChild,yearsAfterFirstChild,lifespan);
    this.next = null;
    this.prev = null;
  }

  public DataItemNode(String patriarchName,String yearsFromCreation,String sonName,
        String fatherName,String ageAtFirstChild,String yearsAfterFirstChild,String lifespan, DataItemNode next, DataItemNode prev) {
    super(patriarchName,yearsFromCreation,sonName,fatherName,ageAtFirstChild,yearsAfterFirstChild,lifespan);
    this.next = next;
    this.prev = prev;
  }

  public DataItemNode(DataItemNode next, DataItemNode prev) {
    this.next = next;
    this.prev = prev;
  }

  public DataItemNode(Patriarch patriarch) {
    this.setPatriarchName(patriarch.getPatriarchName());
    this.setYearsFromCreation(patriarch.getYearsFromCreation()); 
    this.setSonName(patriarch.getSonName()); 
    this.setFatherName(patriarch.getFatherName()); 
    this.setAgeAtFirstChild(patriarch.getAgeAtFirstChild()); 
    this.setYearsAfterFirstChild(patriarch.getYearsFromCreation()); 
    this.setLifespan(patriarch.getLifespan());
  }
  
  public DataItemNode(DataItemNode patriarchName,DataItemNode yearsFromCreation,DataItemNode sonName,
        DataItemNode fatherName,DataItemNode ageAtFirstChild,DataItemNode yearsAfterFirstChild,
        DataItemNode lifespan, DataItemNode next, DataItemNode prev) {
    this.setPatriarchName(patriarchName.getPatriarchName());
    this.setYearsFromCreation(yearsFromCreation.getYearsFromCreation()); 
    this.setSonName(sonName.getSonName()); 
    this.setFatherName(fatherName.getFatherName()); 
    this.setAgeAtFirstChild(ageAtFirstChild.getAgeAtFirstChild()); 
    this.setYearsAfterFirstChild(yearsAfterFirstChild.getYearsFromCreation()); 
    this.setLifespan(lifespan.getLifespan());
    this.next = next.getNextNode();
    this.prev = prev.getPrevNode();
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
