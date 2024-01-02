package Classes;

import java.util.Hashtable;

/**
 * Providers class represents service providers with their details.
 * 
 * @author Gianne Bacay
 */
public class Providers {
    private int prvdrId, clientCountPerDay;
    private String providerName, status;
    private Hashtable<String, String> workingHoursHashtable;

    // Constructors
    public Providers() {
        this.prvdrId = 0;
        this.providerName = " ";
        this.workingHoursHashtable = new Hashtable<>();
        this.clientCountPerDay = 0;
        this.status = " ";
    }

    public Providers(int prvdrId, String providerName, int clientCountPerDay, String status) {
        this.prvdrId = prvdrId;
        this.providerName = providerName;
        this.clientCountPerDay = clientCountPerDay;
        this.status = status;
    }

    // Setters
    public void setProviderId(int prvdrId) {
        this.prvdrId = prvdrId;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setWorkingHoursHashtable(Hashtable<String, String> workingHoursHashtable) {
        this.workingHoursHashtable.clear(); // Clear existing entries
        this.workingHoursHashtable.putAll(workingHoursHashtable); // Add new entries
    }

    public void addWorkingHoursEntry(String dayName, String timeslot) {
        if (workingHoursHashtable == null) {
            // Initialize the workingHoursHashtable if it's null
            this.workingHoursHashtable = new Hashtable<>();
        } else {
            // Clear existing entries and add new entries
            this.workingHoursHashtable.put(dayName, timeslot);
        }
    }

    public void setClientCountPerDay(int clientCountPerDay) {
        this.clientCountPerDay = clientCountPerDay;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    // Getters
    public int getProviderId() {
        return this.prvdrId;
    }

    public String getProviderName() {
        return this.providerName;
    }

    public Hashtable<String, String> getWorkingHoursHashtable() {
        return workingHoursHashtable;
    }

    public int getClientCountPerDay() {
        return clientCountPerDay;
    }
    
    public String getStatus() {
        return this.status;
    }

    // toString method
    @Override
    public String toString() {
        return "Providers{" +
                "prvdrId=" + getProviderId() +
                ", providerName='" + getProviderName() + '\'' +
                ", workingHours='" + getWorkingHoursHashtable() + '\'' +
                ", clientCountPerDay='" + getClientCountPerDay() + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
    
}
