/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Gianne Bacay
 */
public class Timeslots {
    private int tsId,dayId,prvdrId;
    private String dayName, timeslot;

    public Timeslots() {
        this.tsId = 0;
        this.dayId = 0;
        this.prvdrId=0;
        this.dayName = " ";
        this.timeslot = " ";
    }

    public Timeslots(int tsId, int dayId, int prvdrId, String dayName, String timeslot) {
        this.tsId = tsId;
        this.dayId = dayId;
        this.prvdrId=prvdrId;
        this.dayName = dayName;
        this.timeslot = timeslot;
    }

    // Setters
    public void setTimeslotId(int tsId) {
        this.tsId = tsId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }
    
    public void setProviderId(int prvdrId) {
        this.prvdrId = prvdrId;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    // Getters
    public int getTimeslotId() {
        return this.tsId;
    }

    public int getDayId() {
        return this.dayId;
    }
    
    public int getProviderId() {
        return this.prvdrId;
    }

    public String getDayName() {
        return this.dayName;
    }

    public String getTimeslot() {
        return this.timeslot;
    }

    @Override
    public String toString() {
        return "Timeslots{" +
                "tsId=" + getTimeslotId() +
                ", dayId='" + getDayId() + '\'' +
                ", prvdrId='" + getProviderId() + '\'' +
                ", dayName='" + getDayName() + '\'' +
                ", timeslot='" + getTimeslot() + '\'' +
                '}';
    }
}
