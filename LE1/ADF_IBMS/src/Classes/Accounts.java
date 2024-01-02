/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Gianne Bacay
 */
public class Accounts {
    private int accId;
    private String username,password,phoneNum,email,address,city,zipcode,
            comments,status;
    private boolean isReminded;

    // Constructors
    public Accounts() {
        this.accId = 0;
        this.password = " ";
        this.phoneNum = " ";
        this.email = " ";
        this.username = email;
        this.address = " ";
        this.city = " ";
        this.zipcode = " ";
        this.comments = " ";
        this.status = " ";
        this.isReminded = false;
    }

    public Accounts(int accId, String username, String password, String phoneNum, 
        String email, String address, String city, String zipcode, 
        String comments, String status, boolean isReminded) {
        this.accId = accId;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.comments = comments;
        this.status = status;
        this.isReminded = isReminded;
    }

    // Setters
    public void setAccId(int accId) {
        this.accId = accId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
            
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setReminded(boolean isReminded) {
        this.isReminded = isReminded;
    }

    // Getters
    public int getAccId() {
        return this.accId;
    }

    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public String getComments() {
        return this.comments;
    }
    
    public String getStatus() {
        return this.status;
    }

    public boolean isReminded() {
        return this.isReminded;
    }
    

    // toString method
    @Override
    public String toString() {
        return "Accounts{" +
                "accId=" + getAccId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", phoneNum='" + getPhoneNum() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", zipcode='" + getZipcode() + '\'' +
                ", comments='" + getComments() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", isReminded=" + isReminded() +
                '}';
    }
}
