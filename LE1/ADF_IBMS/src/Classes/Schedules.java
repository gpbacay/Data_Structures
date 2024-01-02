/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Gianne Bacay
 */
public class Schedules{
    private int schedId;
    private String timeZone, dayName, day, month, year, time, schedFormat;

    // Constructors
    public Schedules() {
        this.schedId = 0;
        this.timeZone = " ";
        this.dayName = " ";
        this.day = " ";
        this.month = " ";
        this.year = " ";
        this.time = " ";
        this.schedFormat = this.day+"/"+this.month+"/"+this.year;
    }

//    public Schedules(int accId, int prvdrId, int serviceId, int schedId) {
//        super(accId,prvdrId,serviceId);
//        this.schedId = schedId;
//    }
    
    public Schedules(int schedId, String timeZone, String dayName, String day, 
            String month, String year, String time, String schedFormat) {
        this.schedId = schedId;
        this.timeZone = timeZone;
        this.dayName = dayName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
        this.schedFormat = schedFormat;
    }

    // Setters
    public void setSchedId(int schedId) {
        this.schedId = schedId;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Getters
    public int getSchedId() {
        return this.schedId;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public String getDayName() {
        return this.dayName;
    }

    public String getDay() {
        return this.day;
    }

    public String getMonth() {
        return this.month;
    }

    public String getYear() {
        return this.year;
    }

    public String getTime() {
        return this.time;
    }
    
    public String getSchedFormat() {
        return this.schedFormat;
    }

    // toString method
    @Override
    public String toString() {
        return "Schedules{" +
                "schedId=" + getSchedId() +
                ", timeZone='" + getTimeZone() + '\'' +
                ", dayName='" + getDayName() + '\'' +
                ", dayNum='" + getDay() + '\'' +
                ", month='" + getMonth() + '\'' +
                ", year='" + getYear() + '\'' +
                ", time='" + getTime() + '\'' +
                ", schedFormat='" + getSchedFormat() + '\'' +
                '}';
    }
}
