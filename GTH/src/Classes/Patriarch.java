/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Classes;


/**
 *
 * @author Gianne Bacay
 */
public class Patriarch {
    private String patriarchName,yearsFromCreation,sonName,fatherName,ageAtFirstChild,yearsAfterFirstChild,lifespan;

    public Patriarch() {
        this.patriarchName = "";
        this.yearsFromCreation = "";
        this.sonName = "";
        this.fatherName = "";
        this.ageAtFirstChild = "";
        this.yearsAfterFirstChild = "";
        this.lifespan = "";
    }

    public Patriarch(String patriarchName,String yearsFromCreation,String sonName,
        String fatherName,String ageAtFirstChild,String yearsAfterFirstChild,String lifespan) {
        this.patriarchName = patriarchName;
        this.yearsFromCreation = yearsFromCreation;
        this.sonName = sonName;
        this.fatherName = fatherName;
        this.ageAtFirstChild = ageAtFirstChild;
        this.yearsAfterFirstChild = yearsAfterFirstChild;
        this.lifespan = lifespan;
    }

    public Patriarch(Patriarch patriarch) {
        this.patriarchName = patriarch.patriarchName;
        this.yearsFromCreation = patriarch.yearsFromCreation;
        this.sonName = patriarch.sonName;
        this.fatherName = patriarch.fatherName;
        this.ageAtFirstChild = patriarch.ageAtFirstChild;
        this.yearsAfterFirstChild = patriarch.yearsAfterFirstChild;
        this.lifespan = patriarch.lifespan;
    }

    // Setters
    public void setPatriarchName(String patriarchName) {
        this.patriarchName = patriarchName;
    }
    public void setYearsFromCreation(String yearsFromCreation) {
        this.yearsFromCreation = yearsFromCreation;
    }
    public void setSonName(String sonName) {
        this.sonName = sonName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public void setAgeAtFirstChild(String ageAtFirstChild) {
        this.ageAtFirstChild = ageAtFirstChild;
    }
    public void setYearsAfterFirstChild(String yearsAfterFirstChild) {
        this.yearsAfterFirstChild = yearsAfterFirstChild;
    }
    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }
    
    
    // Getters
    public String getPatriarchName() {
        return this.patriarchName;
    }
    public String getYearsFromCreation() {
        return this.yearsFromCreation;
    }
    public String getSonName() {
        return this.sonName;
    }
    public String getFatherName() {
        return this.fatherName;
    }
    public String getAgeAtFirstChild() {
        return this.ageAtFirstChild;
    }
    public String getearsAfterFirstChild() {
        return this.yearsAfterFirstChild;
    }
    public String getLifespan() {
        return this.lifespan;
    }
}
