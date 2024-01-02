/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import Classes.Providers;

/**
 *
 * @author Gianne Bacay
 */
public class HTPopulator {
    private static final String ACCOUNTS_FILE_PATH = "src\\Databases\\accountsDB.csv";
    private static final String PROVIDERS_FILE_PATH = "src\\Databases\\providersDB.csv";
    private static final String SERVICES_FILE_PATH = "src\\Databases\\servicesDB.csv";
    private static final String TIMESLOTS_FILE_PATH = "src\\Databases\\timeslotsDB.csv";
    private static final String SCHEDULES_FILE_PATH = "src\\Databases\\schedulesDB.csv";
    private static final String BOOKINGS_FILE_PATH = "src\\Databases\\bookingsDB.csv";
    
    
    private static final Hashtable<Integer, Accounts> accountsHashtable = new Hashtable<>();
    private static final Hashtable<Integer, Providers> providersHashtable = new Hashtable<>();
    private static final Hashtable<Integer, Services> servicesHashtable = new Hashtable<>();
    private static final Hashtable<Integer, Timeslots> timeslotsHashtable = new Hashtable<>();
    private static final Hashtable<Integer, Schedules> schedulesHashtable = new Hashtable<>();
    private static final Hashtable<Integer, Bookings> bookingsHashtable = new Hashtable<>();

    public static String getACCOUNTS_FILE_PATH() {
        return ACCOUNTS_FILE_PATH;
    }

    public static String getPROVIDERS_FILE_PATH() {
        return PROVIDERS_FILE_PATH;
    }

    public static String getSERVICES_FILE_PATH() {
        return SERVICES_FILE_PATH;
    }
    
    public static String getTIMESLOTS_FILE_PATH() {
        return TIMESLOTS_FILE_PATH;
    }
    
    public static String getSCHEDULES_FILE_PATH() {
        return SCHEDULES_FILE_PATH;
    }

    public static String getBOOKINGS_FILE_PATH() {
        return BOOKINGS_FILE_PATH;
    }

    
    public static Hashtable<Integer, Accounts> getAccountsHashtable() {
        return accountsHashtable;
    }

    public static Hashtable<Integer, Providers> getProvidersHashtable() {
        return providersHashtable;
    }

    public static Hashtable<Integer, Services> getServicesHashtable() {
        return servicesHashtable;
    }

    public static Hashtable<Integer, Timeslots> getTimeslotsHashtable() {
        return timeslotsHashtable;
    }
    
    public static Hashtable<Integer, Schedules> getSchedulesHashtable() {
        return schedulesHashtable;
    }

    public static Hashtable<Integer, Bookings> getBookingsHashtable() {
        return bookingsHashtable;
    }
    
    public static Hashtable<Integer, Accounts> populateAccountsHashtable() {
        File accountsFile = new File(ACCOUNTS_FILE_PATH);

        try (Scanner input = new Scanner(accountsFile)) {
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(",");
                int accId = Integer.parseInt(line[0]);
                String username = line[1];
                String password = line[2];
                String phoneNum = line[3];
                String email = line[4];
                String address = line[5];
                String city = line[6];
                String zipcode = line[7];
                String comments = line[8];
                String status = line[9];
                boolean isReminded = Boolean.parseBoolean(line[10].trim());

                Accounts accountObj = new Accounts(accId, username, password, phoneNum, email, address,
                        city, zipcode, comments, status, isReminded);

                accountsHashtable.put(accId, accountObj);
            }
            System.out.println("Accounts Hashtable has been populated with data from the CSV file.");
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
        }
        return accountsHashtable;
    }

    public static Hashtable<Integer, Providers> populateProvidersHashTable() {
        File providersFile = new File(PROVIDERS_FILE_PATH);

        try (Scanner input = new Scanner(providersFile)) {
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(",");
                int prvdrId = Integer.parseInt(line[0]);
                String providerName = line[1];
                int clientCountPerDay = Integer.parseInt(line[2]);
                String status = line[3];
                Providers providerObj = new Providers(prvdrId, providerName, clientCountPerDay, status);
                
                for (Map.Entry<Integer, Timeslots> entry : getTimeslotsHashtable().entrySet()) {
                    Timeslots value = entry.getValue();
                    if(value.getProviderId()==prvdrId){
                        providerObj.addWorkingHoursEntry(value.getDayName(), value.getTimeslot());
                    }
                }
                providersHashtable.put(prvdrId, providerObj);
            }
            System.out.println("Providers Hashtable has been populated with data from the CSV file.");
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer: " + e.getMessage());
        }
        return providersHashtable;
    }
    
    public static Hashtable<Integer, Services> populateServicesHashTable() {
        File servicesFile = new File(SERVICES_FILE_PATH);

        try (Scanner input = new Scanner(servicesFile)) {
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(",");
                int serviceId = Integer.parseInt(line[0]);
                String serviceName = line[1];
                String duration = line[2];
                String price = line[3];

                Services serviceObj = new Services(serviceId, serviceName, duration, price);

                servicesHashtable.put(serviceId, serviceObj);
            }
            System.out.println("Services Hashtable has been populated with data from the CSV file.");
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer: " + e.getMessage());
        }
        return servicesHashtable;
    }
    
    public static Hashtable<Integer, Timeslots> populateTimeslotsHashTable() {
        File timeslotsFile = new File(TIMESLOTS_FILE_PATH);

        try (Scanner input = new Scanner(timeslotsFile)) {
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(",");
                int tsId = Integer.parseInt(line[0].trim());
                int dayId = Integer.parseInt(line[1].trim());
                int prvdrId = Integer.parseInt(line[2].trim());
                String dayName = line[3].trim();
                String timeslot = line[4].trim();  

                Timeslots timeslotObj = new Timeslots(tsId, dayId, prvdrId, dayName, timeslot);

                timeslotsHashtable.put(tsId, timeslotObj);
            }
            System.out.println("Timeslots Hashtable has been populated with data from the CSV file.");
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer: " + e.getMessage());
        }
        return timeslotsHashtable;
    }
    
    public static Hashtable<Integer, Schedules> populateSchedulesHashTable() {
        File schedulesFile = new File(SCHEDULES_FILE_PATH);

        try (Scanner input = new Scanner(schedulesFile)) {
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(",");
                int schedId = Integer.parseInt(line[0].trim());
                String timeZone = line[1].trim();
                String dayName = line[2].trim();
                String dayNum = line[3].trim();
                String month = line[4].trim();
                String year = line[5].trim();
                String time = line[6].trim();
                String schedFormat = line[7].trim();

                Schedules schedules = new Schedules(schedId, timeZone, dayName, 
                        dayNum, month, year, time, schedFormat);

                schedulesHashtable.put(schedId, schedules);
            }
            System.out.println("Schedules Hashtable has been populated with data from the CSV file.");
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer: " + e.getMessage());
        }
        return schedulesHashtable;
    }
    
    public static Hashtable<Integer, Bookings> populateBookingsHashTable() {
        File bookingsFile = new File(BOOKINGS_FILE_PATH);

        try (Scanner input = new Scanner(bookingsFile)) {
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(",");
                int bookingId = Integer.parseInt(line[0].trim());
                int accId = Integer.parseInt(line[1].trim());
                int prvdrId = Integer.parseInt(line[2].trim());
                int serviceId = Integer.parseInt(line[3].trim());
                int schedId = Integer.parseInt(line[4].trim());
                String status = line[5].trim();

                Bookings booking = new Bookings(bookingId, accId, prvdrId, serviceId, schedId, status);

                bookingsHashtable.put(bookingId, booking);
            }
            System.out.println("Bookings Hashtable has been populated with data from the CSV file.");
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer: " + e.getMessage());
        }
        return bookingsHashtable;
    }

//    public static void main(String[] args) {
//        Hashtable<Integer, Timeslots> timeslotsHashTable = populateTimeslotsHashTable();
//        Hashtable<Integer, Accounts> accountsHashtable = populateAccountsHashtable();
//        Hashtable<Integer, Services> servicesHashtable = populateServicesHashTable();
//        Hashtable<Integer, Providers> providersHashtable = populateProvidersHashTable();
//        Hashtable<Integer, Bookings> bookingsHashTable = populateBookingsHashTable();
//
//        System.out.println(timeslotsHashTable);
//        System.out.println("\n");
//        System.out.println(accountsHashtable);
//        System.out.println("\n");
//        System.out.println(providersHashtable);
//        System.out.println("\n");
//        System.out.println(servicesHashtable);
//        System.out.println("\n");
//
//        System.out.println(bookingsHashTable);
//        System.out.println("\n");
//    }
}
