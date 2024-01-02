/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Gianne Bacay
 */
public class Services{
    private int serviceId;
    private String serviceName,duration,price;

    // Constructors
    public Services() {
        this.serviceId = 1;
        this.serviceName = " ";
        this.duration = " ";
        this.price = " ";
    }
    
    public Services(int serviceId, String serviceName, String duration, String price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.duration = duration;
        this.price = price;
    }

    // Setters
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // Getters
    public int getServiceId() {
        return this.serviceId;
    }
    
    public String getServiceName() {
        return this.serviceName;
    }
    
    public String getDuration() {
        return this.duration;
    }

    public String getPrice() {
        return this.price;
    }

    // toString method
    @Override
    public String toString() {
        return "Services{" +
                "serviceId=" + getServiceId() +
                ", serviceName='" + getServiceName() + '\'' +
                ", duration='" + getDuration() + '\'' +
                ", price='" + getPrice() + '\'' +
                '}';
    }
}
