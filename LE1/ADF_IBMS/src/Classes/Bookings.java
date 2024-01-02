/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Gianne Bacay
 */
public class Bookings{
    private int bookingId,accId,prvdrId,serviceId,schedId;
    private String status;

    // Constructors
    public Bookings() {
        this.bookingId = 0;
        this.accId = 0;
        this.prvdrId = 0;
        this.serviceId = 0;
        this.schedId = 0;
        this.status = " ";
    }

    public Bookings(int bookingId, int accId, int prvdrId, int serviceId, int schedId, String status) {
        this.bookingId = bookingId;
        this.accId = accId;
        this.prvdrId = prvdrId;
        this.serviceId = serviceId;
        this.schedId = schedId;
        this.status = status;
    }

    // Setters
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public void setAccountId(int accId) {
        this.accId = accId;
    }
    public void setProviderId(int prvdrId) {
        this.prvdrId = prvdrId;
    }
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    public void setScheduleId(int schedId) {
        this.schedId = schedId;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Getters
    public int getBookingId() {
        return this.bookingId;
    }
    public int getAccountId() {
        return this.accId;
    }
    public int getProviderId() {
        return this.prvdrId;
    }
    public int getServiceId() {
        return this.serviceId;
    }
    public int getScheduleId() {
        return this.schedId;
    }
    public String getStatus() {
        return this.status;
    }

    // toString method
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + getBookingId() +
                ", accId='" + getAccountId() + '\'' +
                ", prvdrId='" + getProviderId() + '\'' +
                ", serviceId='" + getServiceId() + '\'' +
                ", schedId='" + getScheduleId() + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}
