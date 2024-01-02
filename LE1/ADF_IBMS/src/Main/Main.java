/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import Classes.Accounts;
import Classes.Bookings;
import static Classes.HTPopulator.getACCOUNTS_FILE_PATH;
import static Classes.HTPopulator.getAccountsHashtable;
import static Classes.HTPopulator.getBOOKINGS_FILE_PATH;
import static Classes.HTPopulator.getBookingsHashtable;
import static Classes.HTPopulator.getProvidersHashtable;
import static Classes.HTPopulator.getSCHEDULES_FILE_PATH;
import static Classes.HTPopulator.getSchedulesHashtable;
import static Classes.HTPopulator.getServicesHashtable;
import static Classes.HTPopulator.getTimeslotsHashtable;
import static Classes.HTPopulator.populateAccountsHashtable;
import static Classes.HTPopulator.populateBookingsHashTable;
import static Classes.HTPopulator.populateProvidersHashTable;
import static Classes.HTPopulator.populateSchedulesHashTable;
import static Classes.HTPopulator.populateServicesHashTable;
import static Classes.HTPopulator.populateTimeslotsHashTable;
import Classes.Providers;
import Classes.Schedules;
import Classes.Services;
import Classes.Timeslots;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gianne Bacay
 */
public class Main extends javax.swing.JFrame {

    public static volatile boolean isRunning = true;
    public static int dayIdCounter = 1;
    public static int accountNum = 0;
    public static int barberNum = 0;
    public static int serviceNum = 0;
    public static int schedNum = 0;
    public static int bookNum = 0;
    public static int timeslotNum = 0;

    public static String dayOfWeek;
    public static String L3BGdefault;
    public static String enterContactsBHBG;
    public static String providerBGDefault;

    public static Thread updateL7;
    public static String daySelectedName;
    public static String dayNum;
    public static String monthNum;
    public static String yearNum;
    public static String dateFormat;
    public static String bookText;

    public static String timezoneSelected;
    public static String timeslotSelected;
    public static ArrayList<Integer> timeslotsSelectedList;
    public static Hashtable<String, ArrayList<Integer>> appointmentsHashtable;

    public static String barberNameSelected;
    public static String serviceNameSelected;

    public Main() {
        initComponents();
        //THREADS
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
            while (true) {
                playAudio("src\\Resources\\BGMusic.wav");
            }
        });
        playSoundFX.start();

        timeslotsSelectedList = new ArrayList<>();
        appointmentsHashtable = new Hashtable<>();

        //Populate Hashtables
        populateTimeslotsHashTable();//Populate timeslots before providers
        populateAccountsHashtable();
        populateProvidersHashTable();
        populateServicesHashTable();
        populateSchedulesHashTable();
        populateBookingsHashTable();
        PopulateTable();

        //Debug Print
        System.out.println(getAccountsHashtable());
        System.out.println("\n");
        System.out.println(getProvidersHashtable());
        System.out.println("\n");
        System.out.println(getServicesHashtable());
        System.out.println("\n");
        System.out.println(getTimeslotsHashtable());
        System.out.println("\n");
        System.out.println(getBookingsHashtable());
        System.out.println("\n");

        //LAYER 1
        L1LandingPage.setVisible(true);

        //LAYER 2
        L2LoginPage.setVisible(false);

        emailBox.setVisible(false);
        passwordBox.setVisible(false);

        jCheckBox1.setVisible(false);
        jCheckBox1.setSelected(true);

        jButton2.setVisible(false);

        //LAYER 2.2
        L2_2SignUpPage.setVisible(false);
        L2_2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2_2BG.gif"));

        //LAYER 3
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeekObj = currentDate.getDayOfWeek();
        dayOfWeek = dayOfWeekObj.toString();

        L3BGdefault = "src\\Resources\\L3Default.png";
        enterContactsBHBG = "src\\Resources\\enterContactsBH.png";

        if ("MONDAY".equals(dayOfWeek)) {
            L3BGdefault = "src\\Resources\\MONDAYonExit.png";
            enterContactsBHBG = "src\\Resources\\MONDAYonEnter.png";

        } else if ("TUESDAY".equals(dayOfWeek)) {
            L3BGdefault = "src\\Resources\\TUESDAYonExit.png";
            enterContactsBHBG = "src\\Resources\\TUESDAYonEnter.png";

        } else if ("WEDNESDAY".equals(dayOfWeek)) {
            L3BGdefault = "src\\Resources\\WEDNESDAYonExit.png";
            enterContactsBHBG = "src\\Resources\\WEDNESDAYonEnter.png";

        } else if ("THURSDAY".equals(dayOfWeek)) {
            L3BGdefault = "src\\Resources\\THURSDAYonExit.png";
            enterContactsBHBG = "src\\Resources\\THURSDAYonEnter.png";

        } else if ("FRIDAY".equals(dayOfWeek)) {
            L3BGdefault = "src\\Resources\\FRIDAYonExit.png";
            enterContactsBHBG = "src\\Resources\\FRIDAYonEnter.png";

        } else if ("SATURDAY".equals(dayOfWeek)) {
            L3BGdefault = "src\\Resources\\SATURDAYonExit.png";
            enterContactsBHBG = "src\\Resources\\SATURDAYonEnter.png";

        } else if ("SUNDAY".equals(dayOfWeek)) {
            L3BGdefault = "src\\Resources\\SUNDAYonExit.png";
            enterContactsBHBG = "src\\Resources\\SUNDAYonEnter.png";

        }

        L3Dashboard.setVisible(false);

        enterNickWild.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterNickWild.png"));
        enterNathanLunay.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterNathanLunay.png"));
        enterKharelPatentis.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterKharelPatentis.png"));
        enterKeinSanico.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterKeinSanico.png"));

        enterNickWild.setVisible(false);
        enterNathanLunay.setVisible(false);
        enterKharelPatentis.setVisible(false);
        enterKeinSanico.setVisible(false);

        L3BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L3Default.gif"));
        L3BG.setVisible(true);
        L3BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\L3BGonEnter.gif"));
        L3BGonEnter.setVisible(false);

        servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesOnEnter.gif"));
        servicesOnEnter.setVisible(false);
        service1.setVisible(false);
        service2.setVisible(false);
        service3.setVisible(false);

        bookOnEnter.setVisible(false);

        //LAYER 4s 
        L4ServicesPage.setVisible(false);
        providerBGDefault = "src\\Resources\\servicesBGDefault.png";
        L4BGonEnter.setVisible(false);
        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.png"));

        //LAYER 5
        L5StaffPage.setVisible(false);
        nick.setVisible(false);
        natty.setVisible(false);
        kian.setVisible(false);
        kharel.setVisible(false);

        //LAYER 6
        L6AboutUsPage.setVisible(false);

        //LAYER 7
        jComboBox1.setSelectedIndex(-1);
        L7AppointmentPage.setVisible(false);
        L7BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L7BG.gif"));

        dayNum = "0";
        monthNum = "0";
        yearNum = "0";

        updateL7 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Limit the selectable dates
                //Date Today
                Date today = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);
                calendar.add(Calendar.DAY_OF_YEAR, 14);//1-365
                Date sevenDaysAhead = calendar.getTime();
                jCalendar1.setSelectableDateRange(today, sevenDaysAhead);

                //Identify day of week name
                Date selectedDay = jCalendar1.getDate();
                calendar = Calendar.getInstance();
                calendar.setTime(selectedDay);
                int daySelected = calendar.get(Calendar.DAY_OF_WEEK);//1-7
                String daySelectedName = " ";
                if (daySelected == 1) {
                    daySelectedName = "SUNDAY";
                } else if (daySelected == 2) {
                    daySelectedName = "MONDAY";
                } else if (daySelected == 3) {
                    daySelectedName = "TUESDAY";
                } else if (daySelected == 4) {
                    daySelectedName = "WEDNESDAY";
                } else if (daySelected == 5) {
                    daySelectedName = "THURSDAY";
                } else if (daySelected == 6) {
                    daySelectedName = "FRIDAY";
                } else if (daySelected == 7) {
                    daySelectedName = "SATURDAY";
                }

                //Identify month name
                int monthSelected = calendar.get(Calendar.MONTH);//1-12
                String monthSelectedName = " ";
                if (monthSelected == 0) {
                    monthSelectedName = "JANUARY";
                } else if (monthSelected == 1) {
                    monthSelectedName = "FEBRUARY";
                } else if (monthSelected == 2) {
                    monthSelectedName = "MARCH";
                } else if (monthSelected == 3) {
                    monthSelectedName = "APRIL";
                } else if (monthSelected == 4) {
                    monthSelectedName = "MAY";
                } else if (monthSelected == 5) {
                    monthSelectedName = "JUNE";
                } else if (monthSelected == 6) {
                    monthSelectedName = "JULY";
                } else if (monthSelected == 7) {
                    monthSelectedName = "AUGUST";
                } else if (monthSelected == 8) {
                    monthSelectedName = "SEPTEMBER";
                } else if (monthSelected == 9) {
                    monthSelectedName = "OCTOBER";
                } else if (monthSelected == 10) {
                    monthSelectedName = "NOVEMBER";
                } else if (monthSelected == 11) {
                    monthSelectedName = "DECEMBER";
                }

                int dayInMonthSelected = calendar.get(Calendar.DAY_OF_MONTH);//1-31
                int yearSelectedNum = calendar.get(Calendar.YEAR);//2024

                String selectedItemString = " ";
                if (jComboBox1.getSelectedItem() != null) {
                    selectedItemString = jComboBox1.getSelectedItem().toString();
                    setTimeslotSelected(selectedItemString); //time
                    jComboBox1.setEnabled(true);
                }
                if (jComboBox1.getSelectedItem() == null) {
                    selectedItemString = "No Selected Timeslot";
                    jComboBox1.setEnabled(false);
                }
                System.out.println(selectedItemString);

                //Set Schedules Parameters
                setTimezoneSelected(jLocaleChooser1.getSelectedItem().toString()); //timeZone

                setDaySelectedName(daySelectedName); //dayName (MONDAY,TUESDAY, or etc.)
                setDayNum(String.valueOf(daySelected)); //day (1-7)
                setMonthNum(String.valueOf(monthSelected + 1)); //month
                setYearNum(String.valueOf(yearSelectedNum)); //year
                setTimeslotSelected(selectedItemString); //time
                setDateFormat(getDayNum() + "/" + getMonthNum() + "/" + getYearNum()); //schedFormat

                //Set Booking View
                bookText = "Book on " + getDaySelectedName() + ", "
                        + dayInMonthSelected + " " + monthSelectedName + " "
                        + getYearNum();
                jLabel3.setText(" ");
                jLabel3.setText(bookText);
                jComboBox1.setSelectedItem(getTimeslotSelected());

                //Dynamic jComboBox timeslots populator
                //Traverse the Timeslots Hashtable
                for (Map.Entry<Integer, Timeslots> entry : getTimeslotsHashtable().entrySet()) {
                    Timeslots value = entry.getValue();
                    if (value.getProviderId() == getBarberNum()) {
                        //Identify and traverse which day of week of timeslots to get (i.e. MONDAY, TUESDAY, or etc.) 
                        //through comparing it from the selected day of week
                        if (getDaySelectedName().equalsIgnoreCase(value.getDayName())) {
                            //get the actual count of timeslots in that specific day of week
                            if (value.getDayId() == getDayIdCounter()) {
                                //Add the timeslot into the jComboBox1 and increment the counter
                                jComboBox1.addItem(value.getTimeslot());
                                setDayIdCounter(getDayIdCounter() + 1);
                            }
                        }
                    }
                }

                //Get and Set the selected timeslot ID
                for (Map.Entry<Integer, Timeslots> entry : getTimeslotsHashtable().entrySet()) {
                    int key = entry.getKey();
                    Timeslots value = entry.getValue();
                    if (getBarberNum() == value.getProviderId()) {
                        if (getTimeslotSelected().equalsIgnoreCase(value.getTimeslot())) {
                            setTimeslotNum(key); //selectedTimeslotID
                            break;
                        }
                    }
                }

                for (Map.Entry<Integer, Bookings> entry : getBookingsHashtable().entrySet()) {
                    Bookings value = entry.getValue();
                    boolean isPast = false;

                    for (Map.Entry<Integer, Schedules> entry1 : getSchedulesHashtable().entrySet()) {
                        Schedules value1 = entry1.getValue();
                        if (value.getScheduleId() == value1.getSchedId()) {
                            Calendar dateCal = Calendar.getInstance();
                            dateCal.set(Integer.parseInt(value1.getYear()),
                                    Integer.parseInt(value1.getMonth()), Integer.parseInt(value1.getDay()));

                            Date schedFormat = dateCal.getTime(); // Date from database
                            if (calendar.before(schedFormat)) {
                                isPast = true;
                                break;  // No need to continue checking other schedules for this booking
                            }
                        }
                    }

                    // Update the status after checking all schedules
                    if (isPast) {
                        if (!value.getStatus().equals("Past")) { // Check to avoid multiple updates
                            value.setStatus("Past");
                            updateBookingsDatabase();
                        }
                    }
                }
            }
        }
        );

        //LAYER 8
        L8BG.setIcon(
                new javax.swing.ImageIcon("src\\Resources\\L8BG.png"));
        L8ProfilePage.setVisible(
                false);
        L9BG.setIcon(
                new javax.swing.ImageIcon("src\\Resources\\L9_BGASOnEnter.png"));
        L9AccountSettings.setVisible(
                false);
        L8BGLogout.setVisible(
                false);

        //LAYER 9
        L9BGLogout.setVisible(
                false);
    }

    //Getters and Setters
    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public static int getDayIdCounter() {
        return dayIdCounter;
    }

    public static void setDayIdCounter(int dayIdCounter) {
        Main.dayIdCounter = dayIdCounter;
    }

    public static int getAccountNum() {
        return accountNum;
    }

    public static void setAccountNum(int accountNum) {
        Main.accountNum = accountNum;
    }

    public static int getBarberNum() {
        return barberNum;
    }

    public static void setBarberNum(int barberNum) {
        Main.barberNum = barberNum;
    }

    public static int getServiceNum() {
        return serviceNum;
    }

    public static void setServiceNum(int serviceNum) {
        Main.serviceNum = serviceNum;
    }

    public static int getSchedNum() {
        return schedNum;
    }

    public static void setSchedNum(int schedNum) {
        Main.schedNum = schedNum;
    }

    public static int getBookNum() {
        return bookNum;
    }

    public static void setBookNum(int bookNum) {
        Main.bookNum = bookNum;
    }

    public static String getDayOfWeek() {
        return dayOfWeek;
    }

    public static void setDayOfWeek(String dayOfWeek) {
        Main.dayOfWeek = dayOfWeek;
    }

    public static String getDayNum() {
        return dayNum;
    }

    public static void setDayNum(String dayNum) {
        Main.dayNum = dayNum;
    }

    public static String getMonthNum() {
        return monthNum;
    }

    public static void setMonthNum(String monthNum) {
        Main.monthNum = monthNum;
    }

    public static String getYearNum() {
        return yearNum;
    }

    public static void setYearNum(String yearNum) {
        Main.yearNum = yearNum;
    }

    public static String getBookText() {
        return bookText;
    }

    public static String getTimezoneSelected() {
        return timezoneSelected;
    }

    public static void setTimezoneSelected(String timezoneSelected) {
        Main.timezoneSelected = timezoneSelected;
    }

    public static String getTimeslotSelected() {
        return timeslotSelected;
    }

    public static void setTimeslotSelected(String timeslotSelected) {
        Main.timeslotSelected = timeslotSelected;
    }

    public static void setDaySelectedName(String daySelectedName) {
        Main.daySelectedName = daySelectedName;
    }

    public static String getDaySelectedName() {
        return daySelectedName;
    }

    public static int getTimeslotNum() {
        return timeslotNum;
    }

    public static void setTimeslotNum(int timeslotNum) {
        Main.timeslotNum = timeslotNum;
    }

    public static String getBarberNameSelected() {
        return barberNameSelected;
    }

    public static void setBarberNameSelected(String barberNameSelected) {
        Main.barberNameSelected = barberNameSelected;
    }

    public static String getServiceNameSelected() {
        return serviceNameSelected;
    }

    public static void setServiceNameSelected(String serviceNameSelected) {
        Main.serviceNameSelected = serviceNameSelected;
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(String dateFormat) {
        Main.dateFormat = dateFormat;
    }

    public static ArrayList<Integer> getTimeslotsSelectedList() {
        return timeslotsSelectedList;
    }

    public static void setTimeslotsSelectedList(ArrayList<Integer> timeslotsSelectedList) {
        Main.timeslotsSelectedList = timeslotsSelectedList;
    }

    public static Hashtable<String, ArrayList<Integer>> getAppointmentsHashtable() {
        return appointmentsHashtable;
    }

    public static void setAppointmentsHashtable(Hashtable<String, ArrayList<Integer>> appointmentsHashtable) {
        Main.appointmentsHashtable = appointmentsHashtable;
    }

    // Method to update JComboBox based on the selected day of the week
    private void updateComboBox() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Clear existing items in the JComboBox
        jComboBox1.removeAllItems();
        // Reset the day counter
        setDayIdCounter(1);
    }

    //Populate Table
    public static void PopulateTable() {
        DefaultTableModel model = (DefaultTableModel) upcomingAP.getModel();
        DefaultTableModel model1 = (DefaultTableModel) pastAP.getModel();

        // Clear the table before populating
        model.setRowCount(0);
        model1.setRowCount(0);

        String schedFormat = " ";
        String serviceFormat = " ";
        String providerFormat = " ";

        //Traverse Bookings
        for (Map.Entry<Integer, Bookings> entry : getBookingsHashtable().entrySet()) {
            Bookings value = entry.getValue();
            if (value.getAccountId() == getAccountNum()) {

                //Traverse Schedules
                for (Map.Entry<Integer, Schedules> entry2 : getSchedulesHashtable().entrySet()) {
                    Schedules value2 = entry2.getValue();
                    if (value.getScheduleId() == value2.getSchedId()) {
                        schedFormat = value2.getSchedFormat();

                        //Traverse Services
                        for (Map.Entry<Integer, Services> entry3 : getServicesHashtable().entrySet()) {
                            Services value3 = entry3.getValue();
                            if (value.getServiceId() == value3.getServiceId()) {
                                serviceFormat = value3.getServiceName();

                                //Traverse Providers
                                for (Map.Entry<Integer, Providers> entry4 : getProvidersHashtable().entrySet()) {
                                    Providers value4 = entry4.getValue();
                                    if (value.getProviderId() == value4.getProviderId()) {
                                        providerFormat = value4.getProviderName();

                                        if (value.getStatus().equalsIgnoreCase("Past")) {
                                            model1.addRow(new Object[]{schedFormat, serviceFormat, providerFormat});
                                        } else {
                                            model.addRow(new Object[]{schedFormat, serviceFormat, providerFormat});
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Audio
    private static void playAudio(String filePath) {
        try {
            System.out.println("Playing audio file: " + filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                    format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                return;
            }

            try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
                line.open(format);
                line.start();

                int bufferSize = (int) format.getSampleRate() * format.getFrameSize();
                byte[] buffer = new byte[bufferSize];
                int bytesRead;

                while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
                    line.write(buffer, 0, bytesRead);
                }

                line.drain();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        }
    }

    //CRUD SYSTEM
    //Accounts
    public static void updateAccountsDatabase() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(getACCOUNTS_FILE_PATH()))) {
            for (Map.Entry<Integer, Accounts> entry : getAccountsHashtable().entrySet()) {
                Accounts account = entry.getValue();
                writer.println(account.getAccId() + "," + account.getUsername() + "," + account.getPassword()
                        + "," + account.getPhoneNum() + "," + account.getEmail() + "," + account.getAddress()
                        + "," + account.getCity() + "," + account.getZipcode() + "," + account.getComments()
                        + "," + account.getStatus() + "," + account.isReminded());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Accounts Database Update Failure!");
        }
    }

    //Schedules
    public static void updateSchedulesDatabase() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(getSCHEDULES_FILE_PATH()))) {
            for (Map.Entry<Integer, Schedules> entry : getSchedulesHashtable().entrySet()) {
                Schedules schedules = entry.getValue();
                writer.println(schedules.getSchedId() + "," + schedules.getTimeZone() + "," + schedules.getDayName() + ","
                        + schedules.getDay() + "," + schedules.getMonth() + "," + schedules.getYear() + ","
                        + schedules.getTime() + "," + schedules.getSchedFormat());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Schedules Database Update Failure!");
        }
    }

    //Bookings
    public static void updateBookingsDatabase() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(getBOOKINGS_FILE_PATH()))) {
            for (Map.Entry<Integer, Bookings> entry : getBookingsHashtable().entrySet()) {
                Bookings bookings = entry.getValue();
                writer.println(bookings.getBookingId() + "," + bookings.getAccountId() + ","
                        + bookings.getProviderId() + "," + bookings.getServiceId() + ","
                        + bookings.getScheduleId() + "," + bookings.getStatus());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Bookings Database Update Failure!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        L1LandingPage = new javax.swing.JLayeredPane();
        jButton1 = new javax.swing.JButton();
        L1BG = new javax.swing.JLabel();
        L2LoginPage = new javax.swing.JLayeredPane();
        exitButton = new javax.swing.JButton();
        enterButton = new javax.swing.JButton();
        emailBox = new javax.swing.JTextField();
        passwordBox = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        hitBox = new javax.swing.JButton();
        L2BG = new javax.swing.JLabel();
        L2_2SignUpPage = new javax.swing.JLayeredPane();
        exitButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        enterButton1 = new javax.swing.JButton();
        L2_2BG = new javax.swing.JLabel();
        L3Dashboard = new javax.swing.JLayeredPane();
        exitButton1 = new javax.swing.JButton();
        service1 = new javax.swing.JButton();
        service2 = new javax.swing.JButton();
        service3 = new javax.swing.JButton();
        barber1 = new javax.swing.JButton();
        barber2 = new javax.swing.JButton();
        barber3 = new javax.swing.JButton();
        barber4 = new javax.swing.JButton();
        enterBook = new javax.swing.JButton();
        enterStaff = new javax.swing.JButton();
        enterServices = new javax.swing.JButton();
        enterAboutUs = new javax.swing.JButton();
        enterContactsBH = new javax.swing.JButton();
        enterAccount = new javax.swing.JButton();
        servicesOnEnter = new javax.swing.JLabel();
        enterNickWild = new javax.swing.JLabel();
        enterNathanLunay = new javax.swing.JLabel();
        enterKharelPatentis = new javax.swing.JLabel();
        enterKeinSanico = new javax.swing.JLabel();
        bookOnEnter = new javax.swing.JLabel();
        L3BG = new javax.swing.JLabel();
        L3BGonEnter = new javax.swing.JLabel();
        L4ServicesPage = new javax.swing.JLayeredPane();
        exitButton2 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        s1Hitbox = new javax.swing.JButton();
        s2Hitbox = new javax.swing.JButton();
        s3Hitbox = new javax.swing.JButton();
        L4BGonEnter = new javax.swing.JLabel();
        providerBG = new javax.swing.JLabel();
        L4BG = new javax.swing.JLabel();
        L5StaffPage = new javax.swing.JLayeredPane();
        exitButton3 = new javax.swing.JButton();
        kharel = new javax.swing.JLabel();
        kian = new javax.swing.JLabel();
        natty = new javax.swing.JLabel();
        nick = new javax.swing.JLabel();
        L5BGStaff = new javax.swing.JLabel();
        NIC = new javax.swing.JButton();
        NAT = new javax.swing.JButton();
        KIAN = new javax.swing.JButton();
        KHAREL = new javax.swing.JButton();
        L6AboutUsPage = new javax.swing.JLayeredPane();
        exitButton4 = new javax.swing.JButton();
        enterTheTeam = new javax.swing.JButton();
        L6BG = new javax.swing.JLabel();
        L7AppointmentPage = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        exitButton5 = new javax.swing.JButton();
        bookAppointmentHB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLocaleChooser1 = new com.toedter.components.JLocaleChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        L7BG = new javax.swing.JLabel();
        L9AccountSettings = new javax.swing.JLayeredPane();
        exitButton8 = new javax.swing.JButton();
        usernameBar2 = new javax.swing.JLabel();
        emailBar2 = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        PhoneField = new javax.swing.JTextField();
        EmailField = new javax.swing.JTextField();
        AddressField = new javax.swing.JTextField();
        CityField = new javax.swing.JTextField();
        ZipCodeField = new javax.swing.JTextField();
        SaveButton = new javax.swing.JButton();
        L9AHitbox1 = new javax.swing.JButton();
        L9BAHitbox1 = new javax.swing.JButton();
        L9ASHitbox1 = new javax.swing.JButton();
        L9LOHitbox1 = new javax.swing.JButton();
        L9BGLogout = new javax.swing.JLabel();
        L9BG = new javax.swing.JLabel();
        L8ProfilePage = new javax.swing.JLayeredPane();
        exitButton7 = new javax.swing.JButton();
        usernameBar = new javax.swing.JLabel();
        emailBar = new javax.swing.JLabel();
        greetingsBar = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        upcomingAP = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pastAP = new javax.swing.JTable();
        AHitbox = new javax.swing.JButton();
        BAHitbox = new javax.swing.JButton();
        ASHitbox = new javax.swing.JButton();
        LOHitbox = new javax.swing.JButton();
        L8BGLogout = new javax.swing.JLabel();
        L8BG = new javax.swing.JLabel();
        L9BGLogout1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("All Day Fade Booking App");
        setIconImage(new ImageIcon("src\\Resources\\IntellEX.png").getImage());
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(800, 500));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        L1LandingPage.setMaximumSize(new java.awt.Dimension(800, 500));
        L1LandingPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                L1LandingPageComponentShown(evt);
            }
        });
        L1LandingPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/normalButton1.gif"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        L1LandingPage.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 260, 60));

        L1BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/LandingPage.gif"))); // NOI18N
        L1LandingPage.add(L1BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(L1LandingPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L2LoginPage.setMaximumSize(new java.awt.Dimension(800, 500));
        L2LoginPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        L2LoginPage.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        enterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/enterDefault.gif"))); // NOI18N
        enterButton.setBorder(null);
        enterButton.setBorderPainted(false);
        enterButton.setContentAreaFilled(false);
        enterButton.setFocusPainted(false);
        enterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterButtonMouseExited(evt);
            }
        });
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });
        L2LoginPage.add(enterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 120, 50));

        emailBox.setText("giannebacay2004@gmail.com");
        emailBox.setBorder(null);
        emailBox.setCaretColor(new java.awt.Color(255, 255, 255));
        emailBox.setSelectionColor(new java.awt.Color(102, 0, 204));
        L2LoginPage.add(emailBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 190, 20));

        passwordBox.setText("admin1");
        passwordBox.setBorder(null);
        passwordBox.setSelectionColor(new java.awt.Color(102, 0, 204));
        passwordBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordBoxActionPerformed(evt);
            }
        });
        L2LoginPage.add(passwordBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 190, 20));

        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        L2LoginPage.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 260, 60));

        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        L2LoginPage.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 260, 60));

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(153, 102, 255));
        jCheckBox1.setText("Remember Me");
        jCheckBox1.setBorder(null);
        jCheckBox1.setContentAreaFilled(false);
        jCheckBox1.setFocusPainted(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        L2LoginPage.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, 20));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Create an account");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        L2LoginPage.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 120, 20));

        hitBox.setBorderPainted(false);
        hitBox.setContentAreaFilled(false);
        hitBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hitBoxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hitBoxMouseExited(evt);
            }
        });
        L2LoginPage.add(hitBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 140, 230));

        L2BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L2Default.png"))); // NOI18N
        L2BG.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                L2BGMouseMoved(evt);
            }
        });
        L2LoginPage.add(L2BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(L2LoginPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L2_2SignUpPage.setMaximumSize(new java.awt.Dimension(800, 500));
        L2_2SignUpPage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                L2_2SignUpPageMouseMoved(evt);
            }
        });
        L2_2SignUpPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton6.setBorderPainted(false);
        exitButton6.setContentAreaFilled(false);
        exitButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton6MouseExited(evt);
            }
        });
        exitButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton6ActionPerformed(evt);
            }
        });
        L2_2SignUpPage.add(exitButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jTextField1.setText("Enter your email here");
        jTextField1.setBorder(null);
        L2_2SignUpPage.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 190, 20));

        jPasswordField1.setText("password");
        jPasswordField1.setBorder(null);
        L2_2SignUpPage.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 190, 20));

        enterButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/enterDefault.gif"))); // NOI18N
        enterButton1.setBorder(null);
        enterButton1.setBorderPainted(false);
        enterButton1.setContentAreaFilled(false);
        enterButton1.setFocusPainted(false);
        enterButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterButton1MouseExited(evt);
            }
        });
        enterButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButton1ActionPerformed(evt);
            }
        });
        L2_2SignUpPage.add(enterButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 120, 50));

        L2_2BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L2_2BG.gif"))); // NOI18N
        L2_2SignUpPage.add(L2_2BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(L2_2SignUpPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L3Dashboard.setMaximumSize(new java.awt.Dimension(800, 500));
        L3Dashboard.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                L3DashboardMouseMoved(evt);
            }
        });
        L3Dashboard.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                L3DashboardComponentShown(evt);
            }
        });
        L3Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton1.setBorderPainted(false);
        exitButton1.setContentAreaFilled(false);
        exitButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton1MouseExited(evt);
            }
        });
        exitButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton1ActionPerformed(evt);
            }
        });
        L3Dashboard.add(exitButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        service1.setBorderPainted(false);
        service1.setContentAreaFilled(false);
        service1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                service1MouseEntered(evt);
            }
        });
        service1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service1ActionPerformed(evt);
            }
        });
        L3Dashboard.add(service1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 330, 70));

        service2.setBorderPainted(false);
        service2.setContentAreaFilled(false);
        service2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                service2MouseEntered(evt);
            }
        });
        service2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service2ActionPerformed(evt);
            }
        });
        L3Dashboard.add(service2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 330, 70));

        service3.setBorderPainted(false);
        service3.setContentAreaFilled(false);
        service3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                service3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                service3MouseExited(evt);
            }
        });
        service3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service3ActionPerformed(evt);
            }
        });
        L3Dashboard.add(service3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 330, 70));

        barber1.setBorder(null);
        barber1.setContentAreaFilled(false);
        barber1.setFocusPainted(false);
        barber1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barber1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                barber1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                barber1MouseExited(evt);
            }
        });
        barber1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barber1ActionPerformed(evt);
            }
        });
        L3Dashboard.add(barber1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 240, 160, 260));

        barber2.setBorderPainted(false);
        barber2.setContentAreaFilled(false);
        barber2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                barber2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                barber2MouseExited(evt);
            }
        });
        barber2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barber2ActionPerformed(evt);
            }
        });
        L3Dashboard.add(barber2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 120, 280));

        barber3.setBorderPainted(false);
        barber3.setContentAreaFilled(false);
        barber3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                barber3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                barber3MouseExited(evt);
            }
        });
        barber3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barber3ActionPerformed(evt);
            }
        });
        L3Dashboard.add(barber3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 150, 270));

        barber4.setBorderPainted(false);
        barber4.setContentAreaFilled(false);
        barber4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                barber4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                barber4MouseExited(evt);
            }
        });
        barber4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barber4ActionPerformed(evt);
            }
        });
        L3Dashboard.add(barber4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 180, 240));

        enterBook.setBorderPainted(false);
        enterBook.setContentAreaFilled(false);
        enterBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterBookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterBookMouseEntered(evt);
            }
        });
        enterBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterBookActionPerformed(evt);
            }
        });
        L3Dashboard.add(enterBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 180, 120));

        enterStaff.setBorderPainted(false);
        enterStaff.setContentAreaFilled(false);
        enterStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterStaffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterStaffMouseEntered(evt);
            }
        });
        enterStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterStaffActionPerformed(evt);
            }
        });
        L3Dashboard.add(enterStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 60, 50));

        enterServices.setBorderPainted(false);
        enterServices.setContentAreaFilled(false);
        enterServices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterServicesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterServicesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterServicesMouseExited(evt);
            }
        });
        enterServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterServicesActionPerformed(evt);
            }
        });
        L3Dashboard.add(enterServices, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 60));

        enterAboutUs.setBorderPainted(false);
        enterAboutUs.setContentAreaFilled(false);
        enterAboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterAboutUsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterAboutUsMouseExited(evt);
            }
        });
        enterAboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterAboutUsActionPerformed(evt);
            }
        });
        L3Dashboard.add(enterAboutUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 150, 50));

        enterContactsBH.setBorderPainted(false);
        enterContactsBH.setContentAreaFilled(false);
        enterContactsBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterContactsBHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterContactsBHMouseExited(evt);
            }
        });
        enterContactsBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterContactsBHActionPerformed(evt);
            }
        });
        L3Dashboard.add(enterContactsBH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 140, 260));

        enterAccount.setIcon(new javax.swing.ImageIcon("src\\Resources\\userIcon1.png"));
        enterAccount.setBorder(null);
        enterAccount.setBorderPainted(false);
        enterAccount.setContentAreaFilled(false);
        enterAccount.setFocusPainted(false);
        enterAccount.setMaximumSize(new java.awt.Dimension(27, 27));
        enterAccount.setMinimumSize(new java.awt.Dimension(27, 27));
        enterAccount.setPreferredSize(new java.awt.Dimension(27, 27));
        enterAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterAccountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterAccountMouseExited(evt);
            }
        });
        enterAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterAccountActionPerformed(evt);
            }
        });
        L3Dashboard.add(enterAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 50, 40));

        servicesOnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/servicesOnEnter.gif"))); // NOI18N
        servicesOnEnter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                servicesOnEnterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                servicesOnEnterMouseExited(evt);
            }
        });
        L3Dashboard.add(servicesOnEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 500));

        enterNickWild.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/enterNickWild.png"))); // NOI18N
        enterNickWild.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterNickWildMouseEntered(evt);
            }
        });
        L3Dashboard.add(enterNickWild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        enterNathanLunay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/enterNathanLunay.png"))); // NOI18N
        L3Dashboard.add(enterNathanLunay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        enterKharelPatentis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/enterKharelPatentis.png"))); // NOI18N
        L3Dashboard.add(enterKharelPatentis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        enterKeinSanico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/enterKeinSanico.png"))); // NOI18N
        L3Dashboard.add(enterKeinSanico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        bookOnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/bookOnEnter.png"))); // NOI18N
        L3Dashboard.add(bookOnEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        L3BG.setIcon(new javax.swing.ImageIcon(L3BGdefault));
        L3Dashboard.add(L3BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        L3BGonEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L3BGonEnter.gif"))); // NOI18N
        L3Dashboard.add(L3BGonEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        getContentPane().add(L3Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L4ServicesPage.setMaximumSize(new java.awt.Dimension(800, 500));
        L4ServicesPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                L4ServicesPageComponentShown(evt);
            }
        });
        L4ServicesPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton2.setBorderPainted(false);
        exitButton2.setContentAreaFilled(false);
        exitButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton2MouseExited(evt);
            }
        });
        exitButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton2ActionPerformed(evt);
            }
        });
        L4ServicesPage.add(exitButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jToggleButton1.setBorder(null);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseExited(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        L4ServicesPage.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 50, 50));

        s1Hitbox.setBorderPainted(false);
        s1Hitbox.setContentAreaFilled(false);
        s1Hitbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                s1HitboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                s1HitboxMouseExited(evt);
            }
        });
        s1Hitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s1HitboxActionPerformed(evt);
            }
        });
        L4ServicesPage.add(s1Hitbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 350, 70));

        s2Hitbox.setBorderPainted(false);
        s2Hitbox.setContentAreaFilled(false);
        s2Hitbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                s2HitboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                s2HitboxMouseExited(evt);
            }
        });
        s2Hitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s2HitboxActionPerformed(evt);
            }
        });
        L4ServicesPage.add(s2Hitbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 350, 80));

        s3Hitbox.setBorderPainted(false);
        s3Hitbox.setContentAreaFilled(false);
        s3Hitbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                s3HitboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                s3HitboxMouseExited(evt);
            }
        });
        s3Hitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s3HitboxActionPerformed(evt);
            }
        });
        L4ServicesPage.add(s3Hitbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, 350, 70));

        L4BGonEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/servicesBGDefault.gif"))); // NOI18N
        L4ServicesPage.add(L4BGonEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        providerBG.setIcon(new javax.swing.ImageIcon(providerBGDefault));
        providerBG.setMaximumSize(new java.awt.Dimension(800, 500));
        providerBG.setMinimumSize(new java.awt.Dimension(800, 500));
        providerBG.setPreferredSize(new java.awt.Dimension(800, 500));
        L4ServicesPage.add(providerBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L4BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/servicesBGDefault.png"))); // NOI18N
        L4ServicesPage.add(L4BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        getContentPane().add(L4ServicesPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L5StaffPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                L5StaffPageComponentShown(evt);
            }
        });
        L5StaffPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton3.setBorderPainted(false);
        exitButton3.setContentAreaFilled(false);
        exitButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton3MouseExited(evt);
            }
        });
        exitButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton3ActionPerformed(evt);
            }
        });
        L5StaffPage.add(exitButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 60));

        kharel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/kharel.png"))); // NOI18N
        L5StaffPage.add(kharel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        kian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/kian.png"))); // NOI18N
        L5StaffPage.add(kian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        natty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/natty.png"))); // NOI18N
        L5StaffPage.add(natty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        nick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/nick.png"))); // NOI18N
        L5StaffPage.add(nick, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L5BGStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L5BGDefault.gif"))); // NOI18N
        L5StaffPage.add(L5BGStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        NIC.setContentAreaFilled(false);
        NIC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NICMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NICMouseExited(evt);
            }
        });
        NIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NICActionPerformed(evt);
            }
        });
        L5StaffPage.add(NIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 110, 110));

        NAT.setContentAreaFilled(false);
        NAT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NATMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NATMouseExited(evt);
            }
        });
        NAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NATActionPerformed(evt);
            }
        });
        L5StaffPage.add(NAT, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 110, 110));

        KIAN.setContentAreaFilled(false);
        KIAN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                KIANMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KIANMouseExited(evt);
            }
        });
        KIAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KIANActionPerformed(evt);
            }
        });
        L5StaffPage.add(KIAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 110, 110));

        KHAREL.setContentAreaFilled(false);
        KHAREL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                KHARELMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KHARELMouseExited(evt);
            }
        });
        KHAREL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KHARELActionPerformed(evt);
            }
        });
        L5StaffPage.add(KHAREL, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 110, 110));

        getContentPane().add(L5StaffPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L6AboutUsPage.setMaximumSize(new java.awt.Dimension(800, 500));
        L6AboutUsPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton4.setBorderPainted(false);
        exitButton4.setContentAreaFilled(false);
        exitButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton4MouseExited(evt);
            }
        });
        exitButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton4ActionPerformed(evt);
            }
        });
        L6AboutUsPage.add(exitButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 60));

        enterTheTeam.setBorder(null);
        enterTheTeam.setBorderPainted(false);
        enterTheTeam.setContentAreaFilled(false);
        enterTheTeam.setFocusPainted(false);
        enterTheTeam.setMaximumSize(new java.awt.Dimension(208, 42));
        enterTheTeam.setMinimumSize(new java.awt.Dimension(208, 42));
        enterTheTeam.setPreferredSize(new java.awt.Dimension(208, 42));
        enterTheTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterTheTeamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterTheTeamMouseExited(evt);
            }
        });
        enterTheTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterTheTeamActionPerformed(evt);
            }
        });
        L6AboutUsPage.add(enterTheTeam, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, -1, -1));

        L6BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L6BGDefault.png"))); // NOI18N
        L6AboutUsPage.add(L6BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(L6AboutUsPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L7AppointmentPage.setMaximumSize(new java.awt.Dimension(800, 500));
        L7AppointmentPage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                L7AppointmentPageMouseMoved(evt);
            }
        });
        L7AppointmentPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                L7AppointmentPageComponentShown(evt);
            }
        });
        L7AppointmentPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(800, 500));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton5.setBorderPainted(false);
        exitButton5.setContentAreaFilled(false);
        exitButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton5MouseExited(evt);
            }
        });
        exitButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(exitButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        bookAppointmentHB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bookAppointmentHB.setContentAreaFilled(false);
        bookAppointmentHB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookAppointmentHBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookAppointmentHBMouseExited(evt);
            }
        });
        bookAppointmentHB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookAppointmentHBActionPerformed(evt);
            }
        });
        jPanel1.add(bookAppointmentHB, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, 180, 30));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("[Selected Service]");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 360, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("with [Selected Provider/Barber]");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 360, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Book on [dayOfWeek], [Month] [dayNum], [Year]");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 300, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Select a timeslot");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 260, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Select a Date");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 330, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Select your time zone locale");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 260, 30));
        jPanel1.add(jLocaleChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 260, -1));

        jComboBox1.setForeground(new java.awt.Color(102, 51, 255));
        jComboBox1.setMaximumRowCount(22);
        jComboBox1.setAutoscrolls(true);
        jComboBox1.setBorder(null);
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 260, -1));

        jCalendar1.setDecorationBackgroundColor(new java.awt.Color(60, 63, 65));
        jCalendar1.setSundayForeground(new java.awt.Color(153, 0, 255));
        jCalendar1.setTodayButtonVisible(true);
        jCalendar1.setWeekdayForeground(new java.awt.Color(153, 102, 255));
        jCalendar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCalendar1MouseClicked(evt);
            }
        });
        jCalendar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendar1PropertyChange(evt);
            }
        });
        jPanel1.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 330, 250));

        L7BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L7BGConfirmAppointment.gif"))); // NOI18N
        jPanel1.add(L7BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        L7AppointmentPage.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(L7AppointmentPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L9AccountSettings.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                L9AccountSettingsComponentShown(evt);
            }
        });
        L9AccountSettings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton8.setBorderPainted(false);
        exitButton8.setContentAreaFilled(false);
        exitButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton8MouseExited(evt);
            }
        });
        exitButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton8ActionPerformed(evt);
            }
        });
        L9AccountSettings.add(exitButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        usernameBar2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usernameBar2.setForeground(new java.awt.Color(255, 255, 255));
        usernameBar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameBar2.setText("[username]");
        L9AccountSettings.add(usernameBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 210, 30));

        emailBar2.setForeground(new java.awt.Color(204, 204, 255));
        emailBar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailBar2.setText("[email]");
        L9AccountSettings.add(emailBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 210, -1));

        NameField.setBorder(null);
        L9AccountSettings.add(NameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 158, 230, -1));

        PhoneField.setBorder(null);
        L9AccountSettings.add(PhoneField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 212, 230, -1));

        EmailField.setBorder(null);
        L9AccountSettings.add(EmailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 230, -1));

        AddressField.setBorder(null);
        L9AccountSettings.add(AddressField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 230, -1));

        CityField.setBorder(null);
        L9AccountSettings.add(CityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 110, -1));

        ZipCodeField.setBorder(null);
        L9AccountSettings.add(ZipCodeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, 90, -1));

        SaveButton.setBorder(null);
        SaveButton.setContentAreaFilled(false);
        SaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SaveButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SaveButtonMouseExited(evt);
            }
        });
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });
        L9AccountSettings.add(SaveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 90, 30));

        L9AHitbox1.setBorder(null);
        L9AHitbox1.setBorderPainted(false);
        L9AHitbox1.setContentAreaFilled(false);
        L9AHitbox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                L9AHitbox1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                L9AHitbox1MouseExited(evt);
            }
        });
        L9AHitbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L9AHitbox1ActionPerformed(evt);
            }
        });
        L9AccountSettings.add(L9AHitbox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 160, 50));

        L9BAHitbox1.setBorder(null);
        L9BAHitbox1.setBorderPainted(false);
        L9BAHitbox1.setContentAreaFilled(false);
        L9BAHitbox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                L9BAHitbox1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                L9BAHitbox1MouseExited(evt);
            }
        });
        L9BAHitbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L9BAHitbox1ActionPerformed(evt);
            }
        });
        L9AccountSettings.add(L9BAHitbox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 160, 40));

        L9ASHitbox1.setBorder(null);
        L9ASHitbox1.setBorderPainted(false);
        L9ASHitbox1.setContentAreaFilled(false);
        L9ASHitbox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                L9ASHitbox1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                L9ASHitbox1MouseExited(evt);
            }
        });
        L9ASHitbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L9ASHitbox1ActionPerformed(evt);
            }
        });
        L9AccountSettings.add(L9ASHitbox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 160, 40));

        L9LOHitbox1.setBorder(null);
        L9LOHitbox1.setBorderPainted(false);
        L9LOHitbox1.setContentAreaFilled(false);
        L9LOHitbox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                L9LOHitbox1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                L9LOHitbox1MouseExited(evt);
            }
        });
        L9LOHitbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L9LOHitbox1ActionPerformed(evt);
            }
        });
        L9AccountSettings.add(L9LOHitbox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 243, 160, 40));

        L9BGLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L8BGLogout.png"))); // NOI18N
        L9AccountSettings.add(L9BGLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        L9BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L9_BGASOnEnter.png"))); // NOI18N
        L9AccountSettings.add(L9BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        getContentPane().add(L9AccountSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        L8ProfilePage.setMaximumSize(new java.awt.Dimension(800, 500));
        L8ProfilePage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                L8ProfilePageComponentShown(evt);
            }
        });
        L8ProfilePage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/returnIcon.png"))); // NOI18N
        exitButton7.setBorderPainted(false);
        exitButton7.setContentAreaFilled(false);
        exitButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton7MouseExited(evt);
            }
        });
        exitButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton7ActionPerformed(evt);
            }
        });
        L8ProfilePage.add(exitButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        usernameBar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usernameBar.setForeground(new java.awt.Color(255, 255, 255));
        usernameBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameBar.setText("[username]");
        L8ProfilePage.add(usernameBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 210, 30));

        emailBar.setForeground(new java.awt.Color(204, 204, 255));
        emailBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailBar.setText("[email]");
        L8ProfilePage.add(emailBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 210, -1));

        greetingsBar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        greetingsBar.setForeground(new java.awt.Color(255, 255, 255));
        greetingsBar.setText("Hello, [username]!");
        L8ProfilePage.add(greetingsBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 320, 30));

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(310, 320));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(310, 320));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(310, 320));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        upcomingAP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule", "Service", "Barber"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(upcomingAP);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 310, 290));

        jTabbedPane1.addTab("Upcoming", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pastAP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule", "Service", "Barber"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(pastAP);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, -3, 310, 290));

        jTabbedPane1.addTab("Past", jPanel3);

        L8ProfilePage.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        AHitbox.setBorder(null);
        AHitbox.setBorderPainted(false);
        AHitbox.setContentAreaFilled(false);
        AHitbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AHitboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AHitboxMouseExited(evt);
            }
        });
        AHitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AHitboxActionPerformed(evt);
            }
        });
        L8ProfilePage.add(AHitbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 160, 50));

        BAHitbox.setBorder(null);
        BAHitbox.setBorderPainted(false);
        BAHitbox.setContentAreaFilled(false);
        BAHitbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BAHitboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BAHitboxMouseExited(evt);
            }
        });
        BAHitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAHitboxActionPerformed(evt);
            }
        });
        L8ProfilePage.add(BAHitbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 160, 40));

        ASHitbox.setBorder(null);
        ASHitbox.setBorderPainted(false);
        ASHitbox.setContentAreaFilled(false);
        ASHitbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ASHitboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ASHitboxMouseExited(evt);
            }
        });
        ASHitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASHitboxActionPerformed(evt);
            }
        });
        L8ProfilePage.add(ASHitbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 160, 40));

        LOHitbox.setBorder(null);
        LOHitbox.setBorderPainted(false);
        LOHitbox.setContentAreaFilled(false);
        LOHitbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LOHitboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LOHitboxMouseExited(evt);
            }
        });
        LOHitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOHitboxActionPerformed(evt);
            }
        });
        L8ProfilePage.add(LOHitbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 243, 160, 40));

        L8BGLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L8BGLogout.png"))); // NOI18N
        L8ProfilePage.add(L8BGLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        L8BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L8BG.png"))); // NOI18N
        L8ProfilePage.add(L8BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        L9BGLogout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/L8BGLogout.png"))); // NOI18N
        L8ProfilePage.add(L9BGLogout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        getContentPane().add(L8ProfilePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int userChoice = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to exit?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        // Check the user's choice
        if (userChoice == JOptionPane.YES_OPTION) {
            Thread playSoundFX1;
            playSoundFX1 = new Thread(() -> {
                playAudio("src\\Resources\\buttonPressed.wav");
            });
            playSoundFX1.start();
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        } else {
            // User clicked 'No' or closed the dialog
            System.out.println("User chose not to proceed.");
            setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon("src\\Resources\\animatedButton1.gif"));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon("src\\Resources\\normalButton1.gif"));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2onEnter.gif"));
        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        // TODO add your handling code here:
        L1LandingPage.setVisible(true);
        L2LoginPage.setVisible(false);
        exitButton.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButtonMouseClicked

    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        exitButton.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButtonMouseEntered

    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
        // TODO add your handling code here:
        exitButton.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButtonMouseExited

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2onExit.gif"));

        emailBox.setVisible(false);
        passwordBox.setVisible(false);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void enterButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterButtonMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        enterButton.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterAnimated.gif"));
        passwordBox.setVisible(true);
        jButton2.setVisible(true);
    }//GEN-LAST:event_enterButtonMouseEntered

    private void enterButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterButtonMouseExited
        // TODO add your handling code here:
        enterButton.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterDefault.gif"));
    }//GEN-LAST:event_enterButtonMouseExited

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButtonActionPerformed
        // TODO add your handling code here:
        if (emailBox.getText().isEmpty() || passwordBox.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
        } else {
            String emailInput = emailBox.getText().trim();

            StringBuilder stringBuilder = new StringBuilder();
            for (char c : passwordBox.getPassword()) {
                stringBuilder.append(c);
            }
            String passwordInput = stringBuilder.toString();
            System.out.println("User logged in");

            boolean accFound = false;

            for (Map.Entry<Integer, Accounts> entry : getAccountsHashtable().entrySet()) {
                int key = entry.getKey();
                Accounts value = entry.getValue();
                if (value.getUsername().equalsIgnoreCase(emailInput) && passwordInput.equals(value.getPassword())) {
                    setAccountNum(key);
                    Thread playSoundFX;
                    playSoundFX = new Thread(() -> {
                        playAudio("src\\Resources\\buttonPressed.wav");
                    });
                    playSoundFX.start();
                    L2LoginPage.setVisible(false);
                    L3Dashboard.setVisible(true);

                    emailBox.setVisible(false);
                    passwordBox.setVisible(false);

                    L3BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L3Default.gif"));
                    L3BG.setVisible(false);
                    L3BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\L3BGonEnter.gif"));
                    L3BGonEnter.setVisible(true);
                    servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesOnEnter.gif"));
                    servicesOnEnter.setVisible(false);
                    accFound = true;
                    if (!jCheckBox1.isSelected()) {
                        emailBox.setText("");
                        passwordBox.setText("");
                    }
                    break;
                }
            }
            if (accFound == false) {
                JOptionPane.showMessageDialog(null, "Account not found! Access denied!");
                emailBox.setText("");
                passwordBox.setText("");
            }
        }
    }//GEN-LAST:event_enterButtonActionPerformed

    private void passwordBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordBoxActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterEmail.png"));
        emailBox.setVisible(true);
        passwordBox.setVisible(false);
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        // TODO add your handling code here:
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterEmail.png"));
        emailBox.setVisible(true);
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterPassword.png"));
        passwordBox.setVisible(true);
        emailBox.setVisible(false);
        jCheckBox1.setVisible(true);
        jButton2.setVisible(true);
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterPassword.png"));
        passwordBox.setVisible(true);
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        jButton2.setForeground(Color.GREEN);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        // TODO add your handling code here:
        jButton2.setForeground(Color.WHITE);
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();
        L2_2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2_2BG.gif"));
        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L2_2SignUpPage.setVisible(true);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(false);
        L8ProfilePage.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void hitBoxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hitBoxMouseEntered
        // TODO add your handling code here:
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2onEnter.gif"));
    }//GEN-LAST:event_hitBoxMouseEntered

    private void hitBoxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hitBoxMouseExited
        // TODO add your handling code here:
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2Default.png"));
    }//GEN-LAST:event_hitBoxMouseExited

    private void L2BGMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L2BGMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_L2BGMouseMoved

    private void exitButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton6MouseClicked

    private void exitButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton6MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();

        exitButton6.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButton6MouseEntered

    private void exitButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton6MouseExited
        // TODO add your handling code here:
        exitButton6.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton6MouseExited

    private void exitButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton6ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(true);
        L2_2SignUpPage.setVisible(false);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(false);
        L8ProfilePage.setVisible(false);
    }//GEN-LAST:event_exitButton6ActionPerformed

    private void enterButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterButton1MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        enterButton1.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterAnimated.gif"));
    }//GEN-LAST:event_enterButton1MouseEntered

    private void enterButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterButton1MouseExited
        // TODO add your handling code here:
        enterButton1.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterDefault.gif"));
    }//GEN-LAST:event_enterButton1MouseExited

    private void enterButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButton1ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.getText().isEmpty() || jPasswordField1.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
        } else {
            int userChoice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to create a new account?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            // Check the user's choice
            if (userChoice == JOptionPane.YES_OPTION) {
                Thread playSoundFX1;
                playSoundFX1 = new Thread(() -> {
                    playAudio("src\\Resources\\buttonPressed2.wav");
                });
                playSoundFX1.start();
                // User clicked 'Yes'
                System.out.println("User chose to proceed.");

                //Create new account
                String emailInput = jTextField1.getText().trim();
                StringBuilder stringBuilder = new StringBuilder();
                for (char c : passwordBox.getPassword()) {
                    stringBuilder.append(c);
                }
                String passwordInput = stringBuilder.toString();
                System.out.println("User signed up");

                int prevAccountId = getAccountsHashtable().size() + 1;
                Accounts newAccount = new Accounts();
                newAccount.setAccId(prevAccountId);
                newAccount.setUsername(emailInput);
                newAccount.setPassword(passwordInput);

                getAccountsHashtable().put(prevAccountId, newAccount);
                updateAccountsDatabase();

                //return to log in page
                L1LandingPage.setVisible(false);
                L2LoginPage.setVisible(true);
                L2_2SignUpPage.setVisible(false);
                L3Dashboard.setVisible(false);
                L4ServicesPage.setVisible(false);
                L5StaffPage.setVisible(false);
                L6AboutUsPage.setVisible(false);
                L7AppointmentPage.setVisible(false);
                L8ProfilePage.setVisible(false);

                JOptionPane.showMessageDialog(
                        null,
                        "You've successfully created a new account!",
                        "New account has been recorded",
                        JOptionPane.INFORMATION_MESSAGE);

                //Reset booking
                setBarberNum(0);
                setServiceNum(0);
            } else {
                // User clicked 'No' or closed the dialog
                System.out.println("User chose not to proceed.");
            }
        }
    }//GEN-LAST:event_enterButton1ActionPerformed

    private void L2_2SignUpPageMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L2_2SignUpPageMouseMoved
        // TODO add your handling code here:
        L2_2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2_2BG.png"));
    }//GEN-LAST:event_L2_2SignUpPageMouseMoved

    private void L1LandingPageComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_L1LandingPageComponentShown
        // TODO add your handling code here:
        setAccountNum(0);
        setBarberNum(0);
        setServiceNum(0);
    }//GEN-LAST:event_L1LandingPageComponentShown

    private void exitButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton1MouseClicked
        // TODO add your handling code here:
        exitButton.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton1MouseClicked

    private void exitButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton1MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        exitButton1.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButton1MouseEntered

    private void exitButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton1MouseExited
        // TODO add your handling code here:
        exitButton1.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton1MouseExited

    private void exitButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton1ActionPerformed
        // TODO add your handling code here:
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2onEnter.gif"));
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();
        L3Dashboard.setVisible(false);
        L2LoginPage.setVisible(true);
    }//GEN-LAST:event_exitButton1ActionPerformed

    private void service1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_service1MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        service1.setVisible(true);
        servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterS1.png"));
        servicesOnEnter.setVisible(true);

        enterContactsBH.setVisible(false);
        barber1.setVisible(false);
        barber2.setVisible(false);
    }//GEN-LAST:event_service1MouseEntered

    private void service1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service1ActionPerformed
        // TODO add your handling code here:
        setServiceNum(1);
        System.out.println(getServiceNum());

        if (getBarberNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(true);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_service1ActionPerformed

    private void service2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_service2MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        service2.setVisible(true);
        servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterS2.png"));
        servicesOnEnter.setVisible(true);

        enterContactsBH.setVisible(false);
        barber1.setVisible(false);
        barber2.setVisible(false);
    }//GEN-LAST:event_service2MouseEntered

    private void service2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service2ActionPerformed
        // TODO add your handling code here:
        setServiceNum(2);
        System.out.println(getServiceNum());

        if (getBarberNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(true);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_service2ActionPerformed

    private void service3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_service3MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        service3.setVisible(true);
        servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterS3.png"));
        servicesOnEnter.setVisible(true);

        enterContactsBH.setVisible(false);
        barber1.setVisible(false);
        barber2.setVisible(false);
    }//GEN-LAST:event_service3MouseEntered

    private void service3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_service3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_service3MouseExited

    private void service3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service3ActionPerformed
        // TODO add your handling code here:
        setServiceNum(3);
        System.out.println(getServiceNum());

        if (getBarberNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(true);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_service3ActionPerformed

    private void barber1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_barber1MouseClicked

    private void barber1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber1MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        enterNickWild.setVisible(true);
    }//GEN-LAST:event_barber1MouseEntered

    private void barber1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber1MouseExited
        // TODO add your handling code here:
        enterNickWild.setVisible(false);
    }//GEN-LAST:event_barber1MouseExited

    private void barber1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barber1ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        setBarberNum(1);
        Providers pr = new Providers();
        pr.setProviderId(getBarberNum());
        System.out.println(pr.getProviderId());

        if (getServiceNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(true);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);

            L4BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildOnEnter.gif"));
            L4BGonEnter.setVisible(true);

            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.gif"));
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_barber1ActionPerformed

    private void barber2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber2MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        enterNathanLunay.setVisible(true);
    }//GEN-LAST:event_barber2MouseEntered

    private void barber2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber2MouseExited
        // TODO add your handling code here:
        enterNathanLunay.setVisible(false);
    }//GEN-LAST:event_barber2MouseExited

    private void barber2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barber2ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        setBarberNum(2);
        Providers pr = new Providers();
        pr.setProviderId(getBarberNum());
        System.out.println(pr.getProviderId());

        if (getServiceNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(true);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);

            L4BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayOnEnter.gif"));
            L4BGonEnter.setVisible(true);

            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.gif"));
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_barber2ActionPerformed

    private void barber3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber3MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        enterKharelPatentis.setVisible(true);
    }//GEN-LAST:event_barber3MouseEntered

    private void barber3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber3MouseExited
        // TODO add your handling code here:
        enterKharelPatentis.setVisible(false);
    }//GEN-LAST:event_barber3MouseExited

    private void barber3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barber3ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        setBarberNum(3);
        Providers pr = new Providers();
        pr.setProviderId(getBarberNum());
        System.out.println(pr.getProviderId());

        if (getServiceNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(true);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);

            L4BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoOnEnter.gif"));
            L4BGonEnter.setVisible(true);

            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.gif"));
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_barber3ActionPerformed

    private void barber4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber4MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        enterKeinSanico.setVisible(true);
    }//GEN-LAST:event_barber4MouseEntered

    private void barber4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barber4MouseExited
        // TODO add your handling code here:
        enterKeinSanico.setVisible(false);
    }//GEN-LAST:event_barber4MouseExited

    private void barber4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barber4ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        setBarberNum(4);
        Providers pr = new Providers();
        pr.setProviderId(getBarberNum());
        System.out.println(pr.getProviderId());

        if (getServiceNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(true);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);

            L4BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisOnEnter.gif"));
            L4BGonEnter.setVisible(true);

            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.gif"));
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_barber4ActionPerformed

    private void enterBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterBookMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_enterBookMouseClicked

    private void enterBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterBookMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L3BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\bookOnEnter.png"));
    }//GEN-LAST:event_enterBookMouseEntered

    private void enterBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterBookActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();
        if (getBarberNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(true);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);
        } else {
            if (getServiceNum() == 0) {
                L1LandingPage.setVisible(false);
                L2LoginPage.setVisible(false);
                L3Dashboard.setVisible(false);
                L4ServicesPage.setVisible(true);
                L5StaffPage.setVisible(false);
                L6AboutUsPage.setVisible(false);
                L7AppointmentPage.setVisible(false);
                L8ProfilePage.setVisible(false);
            } else {
                L1LandingPage.setVisible(false);
                L2LoginPage.setVisible(false);
                L3Dashboard.setVisible(false);
                L4ServicesPage.setVisible(false);
                L5StaffPage.setVisible(false);
                L6AboutUsPage.setVisible(false);
                L7AppointmentPage.setVisible(true);
                L8ProfilePage.setVisible(false);
                L7BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L7BG.gif"));
            }
        }
    }//GEN-LAST:event_enterBookActionPerformed

    private void enterStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterStaffMouseClicked
        // TODO add your handling code here:

        L5StaffPage.setVisible(true);
        exitButton3.setVisible(true);
        //L1 - LANDING PAGE
        L1LandingPage.setVisible(false);
        //LAYER 2 - LOGIN PAGE
        L2LoginPage.setVisible(false);
        //Layer 3 - Dashboard
        L3Dashboard.setVisible(false);
        //Layer 4 - Services
        L4ServicesPage.setVisible(false);
    }//GEN-LAST:event_enterStaffMouseClicked

    private void enterStaffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterStaffMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L3BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\staffOnEnter.png"));
    }//GEN-LAST:event_enterStaffMouseEntered

    private void enterStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterStaffActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        enterStaff.setSelected(true);
    }//GEN-LAST:event_enterStaffActionPerformed

    private void enterServicesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterServicesMouseClicked
        // TODO add your handling code here:
        if (enterServices.isSelected()) {
            enterServices.setSelected(false);
        } else {
            enterServices.setSelected(true);
        }
    }//GEN-LAST:event_enterServicesMouseClicked

    private void enterServicesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterServicesMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L3BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesOnEnter.png"));
        servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesDefault.png"));

        enterContactsBH.setVisible(false);
        barber1.setVisible(false);
        barber2.setVisible(false);

        enterServices.setSelected(true);
    }//GEN-LAST:event_enterServicesMouseEntered

    private void enterServicesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterServicesMouseExited
        // TODO add your handling code here:
        servicesOnEnter.setVisible(false);

        enterContactsBH.setVisible(true);
        barber1.setVisible(true);
        barber2.setVisible(true);
    }//GEN-LAST:event_enterServicesMouseExited

    private void enterServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterServicesActionPerformed
        // TODO add your handling code here:
        if (enterServices.isSelected()) {
            servicesOnEnter.setVisible(true);
            Thread playSoundFX;
            playSoundFX = new Thread(() -> {
                playAudio("src\\Resources\\buttonPressed.wav");
            });
            playSoundFX.start();
            L3BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterServices.png"));
            servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesDefault.png"));
            servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesOnEnter.gif"));

            enterContactsBH.setVisible(false);
            barber1.setVisible(false);
            barber2.setVisible(false);
        } else {
            servicesOnEnter.setVisible(false);

            enterContactsBH.setVisible(true);
            barber1.setVisible(true);
            barber2.setVisible(true);
        }
    }//GEN-LAST:event_enterServicesActionPerformed

    private void enterAboutUsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterAboutUsMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        enterAboutUs.setSelected(true);

        L3BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\aboutUsOnEnter.png"));
    }//GEN-LAST:event_enterAboutUsMouseEntered

    private void enterAboutUsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterAboutUsMouseExited
        // TODO add your handling code here:
        enterAboutUs.setSelected(false);
    }//GEN-LAST:event_enterAboutUsMouseExited

    private void enterAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterAboutUsActionPerformed
        // TODO add your handling code here:
        if (enterAboutUs.isSelected()) {
            Thread playSoundFX;
            playSoundFX = new Thread(() -> {
                playAudio("src\\Resources\\buttonPressed.wav");
            });
            playSoundFX.start();
            L3Dashboard.setVisible(false);

            L6BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L6BGDefault.png"));
            L6AboutUsPage.setVisible(true);

            enterTheTeam.setVisible(true);
        }
    }//GEN-LAST:event_enterAboutUsActionPerformed

    private void enterContactsBHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterContactsBHMouseEntered
        // TODO add your handling code here:
        L3BG.setIcon(new javax.swing.ImageIcon(enterContactsBHBG));
    }//GEN-LAST:event_enterContactsBHMouseEntered

    private void enterContactsBHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterContactsBHMouseExited
        // TODO add your handling code here:
        L3BG.setIcon(new javax.swing.ImageIcon(L3BGdefault));
    }//GEN-LAST:event_enterContactsBHMouseExited

    private void enterContactsBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterContactsBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterContactsBHActionPerformed

    private void enterAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterAccountMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        enterAccount.setIcon(new javax.swing.ImageIcon("src\\Resources\\userIcon1onEnter.png"));
    }//GEN-LAST:event_enterAccountMouseEntered

    private void enterAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterAccountMouseExited
        // TODO add your handling code here:
        enterAccount.setIcon(new javax.swing.ImageIcon("src\\Resources\\userIcon1.png"));
    }//GEN-LAST:event_enterAccountMouseExited

    private void enterAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterAccountActionPerformed
        // TODO add your handling code here:
        L8BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L8_1AppointmentsOnEnter.png"));
        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L2_2SignUpPage.setVisible(false);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(false);
        L8ProfilePage.setVisible(true);
    }//GEN-LAST:event_enterAccountActionPerformed

    private void servicesOnEnterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_servicesOnEnterMouseEntered
        // TODO add your handling code here:
        servicesOnEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesDefault.png"));
        servicesOnEnter.setVisible(true);

        service1.setVisible(true);
        service2.setVisible(true);
        service3.setVisible(true);

        enterContactsBH.setVisible(false);
        barber1.setVisible(false);
        barber2.setVisible(false);
    }//GEN-LAST:event_servicesOnEnterMouseEntered

    private void servicesOnEnterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_servicesOnEnterMouseExited
        // TODO add your handling code here:
        servicesOnEnter.setVisible(false);

        service1.setVisible(false);
        service2.setVisible(false);
        service3.setVisible(false);

        enterContactsBH.setVisible(true);
        barber1.setVisible(true);
        barber2.setVisible(true);
    }//GEN-LAST:event_servicesOnEnterMouseExited

    private void enterNickWildMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterNickWildMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_enterNickWildMouseEntered

    private void L3DashboardMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L3DashboardMouseMoved
        // TODO add your handling code here:
        L3BG.setIcon(new javax.swing.ImageIcon(L3BGdefault));
        L3BG.setVisible(true);
        L3BGonEnter.setVisible(false);
    }//GEN-LAST:event_L3DashboardMouseMoved

    private void L3DashboardComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_L3DashboardComponentShown
        // TODO add your handling code here:
        enterAccount.setIcon(new javax.swing.ImageIcon("src\\Resources\\userIcon1.png"));
        enterAccount.setVisible(true);
        setBarberNum(0);
        setServiceNum(0);
        jComboBox1.setSelectedIndex(-1);
    }//GEN-LAST:event_L3DashboardComponentShown

    private void exitButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton2MouseClicked

    private void exitButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton2MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        exitButton2.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButton2MouseEntered

    private void exitButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton2MouseExited
        // TODO add your handling code here:
        exitButton2.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton2MouseExited

    private void exitButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton2ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();

        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L4BGDefault.png"));
        L4ServicesPage.setVisible(false);
        if (!enterStaff.isSelected()) {
            L3Dashboard.setVisible(true);
        } else {
            L5StaffPage.setVisible(true);
        }
    }//GEN-LAST:event_exitButton2ActionPerformed

    private void jToggleButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();

        jToggleButton1.setSelected(true);
        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterWH.png"));
        s1Hitbox.setVisible(true);
        s2Hitbox.setVisible(true);
        s3Hitbox.setVisible(true);
    }//GEN-LAST:event_jToggleButton1MouseEntered

    private void jToggleButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1MouseExited

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        if (jToggleButton1.isSelected()) {
            if (getBarberNum() == 1) {
                L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildWH.png"));
                if ("MONDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildWHMon.png"));
                } else if ("TUESDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildWHTue.png"));
                } else if ("WEDNESDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildWHWed.png"));
                } else if ("THURSDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildWHThu.png"));
                } else if ("FRIDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildWHFri.png"));
                } else if ("SATURDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildWHSat.png"));
                } else if ("SUNDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildWHSun.png"));
                }
            } else if (getBarberNum() == 2) {
                L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayWH.png"));
                if ("MONDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayWHMon.png"));
                } else if ("TUESDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayWHTue.png"));
                } else if ("WEDNESDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayWHWed.png"));
                } else if ("THURSDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayWHThu.png"));
                } else if ("FRIDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayWHFri.png"));
                } else if ("SATURDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayWHSat.png"));
                } else if ("SUNDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayWHSun.png"));
                }
            } else if (getBarberNum() == 3) {
                L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoWH.png"));
                if ("MONDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoWHMon.png"));
                } else if ("TUESDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoWHTue.png"));
                } else if ("WEDNESDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoWHWed.png"));
                } else if ("THURSDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoWHThu.png"));
                } else if ("FRIDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoWHFri.png"));
                } else if ("SATURDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoWHSat.png"));
                } else if ("SUNDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoWHSun.png"));
                }
            } else if (getBarberNum() == 4) {
                L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisWH.png"));
                if ("MONDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisWHMon.png"));
                } else if ("TUESDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisWHTue.png"));
                } else if ("WEDNESDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisWHWed.png"));
                } else if ("THURSDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisWHThu.png"));
                } else if ("FRIDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisWHFri.png"));
                } else if ("SATURDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisWHSat.png"));
                } else if ("SUNDAY".equals(dayOfWeek)) {
                    L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisWHSun.png"));
                }
            }
            s1Hitbox.setVisible(false);
            s2Hitbox.setVisible(false);
            s3Hitbox.setVisible(false);
        } else {
            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterWH.png"));
            L4BG.setVisible(true);
            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void s1HitboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s1HitboxMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\service1OnEnter.png"));
    }//GEN-LAST:event_s1HitboxMouseEntered

    private void s1HitboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s1HitboxMouseExited
        // TODO add your handling code here:
        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.png"));
    }//GEN-LAST:event_s1HitboxMouseExited

    private void s1HitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s1HitboxActionPerformed
        // TODO add your handling code here:
        setServiceNum(1);
        System.out.println(getServiceNum());

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(true);
        L8ProfilePage.setVisible(false);
    }//GEN-LAST:event_s1HitboxActionPerformed

    private void s2HitboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s2HitboxMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\service2OnEnter.png"));
    }//GEN-LAST:event_s2HitboxMouseEntered

    private void s2HitboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s2HitboxMouseExited
        // TODO add your handling code here:
        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.png"));
    }//GEN-LAST:event_s2HitboxMouseExited

    private void s2HitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s2HitboxActionPerformed
        // TODO add your handling code here:
        setServiceNum(2);
        System.out.println(getServiceNum());

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(true);
        L8ProfilePage.setVisible(false);
    }//GEN-LAST:event_s2HitboxActionPerformed

    private void s3HitboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s3HitboxMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\service3OnEnter.png"));
    }//GEN-LAST:event_s3HitboxMouseEntered

    private void s3HitboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s3HitboxMouseExited
        // TODO add your handling code here:
        L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.png"));
    }//GEN-LAST:event_s3HitboxMouseExited

    private void s3HitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s3HitboxActionPerformed
        // TODO add your handling code here:
        setServiceNum(3);
        System.out.println(getServiceNum());

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(true);
        L8ProfilePage.setVisible(false);
    }//GEN-LAST:event_s3HitboxActionPerformed

    private void exitButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton3MouseClicked
        // TODO add your handling code here:
        exitButton.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
        L5StaffPage.setVisible(false);
        //L1 - LANDING PAGE
        L1LandingPage.setVisible(false);
        //LAYER 2 - LOGIN PAGE
        L2LoginPage.setVisible(false);
        //Layer 3 - Dashboard
        L3Dashboard.setVisible(true);
        //Layer 4 - Services
        L4ServicesPage.setVisible(false);
        //Layer 5 - Staff
        L5StaffPage.setVisible(false);
    }//GEN-LAST:event_exitButton3MouseClicked

    private void exitButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton3MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        exitButton3.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButton3MouseEntered

    private void exitButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton3MouseExited
        // TODO add your handling code here:
        exitButton3.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton3MouseExited

    private void exitButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton3ActionPerformed
        // TODO add your handling code here:]
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();
        L2BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L2onEnter.gif"));
    }//GEN-LAST:event_exitButton3ActionPerformed

    private void NICMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NICMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        nick.setVisible(true);
    }//GEN-LAST:event_NICMouseEntered

    private void NICMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NICMouseExited
        // TODO add your handling code here:
        nick.setVisible(false);
        L5BGStaff.setIcon(new javax.swing.ImageIcon("src\\Resources\\L5BGDefault.png"));
    }//GEN-LAST:event_NICMouseExited

    private void NICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NICActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        setBarberNum(1);
        Providers pr = new Providers();
        pr.setProviderId(getBarberNum());
        System.out.println(pr.getProviderId());

        if (getServiceNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(true);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);

            L4BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\NickWildOnEnter.gif"));
            L4BGonEnter.setVisible(true);

            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.gif"));
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_NICActionPerformed

    private void NATMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NATMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        natty.setVisible(true);
    }//GEN-LAST:event_NATMouseEntered

    private void NATMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NATMouseExited
        // TODO add your handling code here:
        natty.setVisible(false);
        L5BGStaff.setIcon(new javax.swing.ImageIcon("src\\Resources\\L5BGDefault.png"));
    }//GEN-LAST:event_NATMouseExited

    private void NATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NATActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        setBarberNum(2);
        Providers pr = new Providers();
        pr.setProviderId(getBarberNum());
        System.out.println(pr.getProviderId());

        if (getServiceNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(true);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);

            L4BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\NathanLunayOnEnter.gif"));
            L4BGonEnter.setVisible(true);

            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.gif"));
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_NATActionPerformed

    private void KIANMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KIANMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        kian.setVisible(true);
    }//GEN-LAST:event_KIANMouseEntered

    private void KIANMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KIANMouseExited
        // TODO add your handling code here:
        kian.setVisible(false);
        L5BGStaff.setIcon(new javax.swing.ImageIcon("src\\Resources\\L5BGDefault.png"));
    }//GEN-LAST:event_KIANMouseExited

    private void KIANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KIANActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        setBarberNum(3);
        Providers pr = new Providers();
        pr.setProviderId(getBarberNum());
        System.out.println(pr.getProviderId());

        if (getServiceNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(true);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);

            L4BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\KienSanicoOnEnter.gif"));
            L4BGonEnter.setVisible(true);

            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.gif"));
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
        }
    }//GEN-LAST:event_KIANActionPerformed

    private void KHARELMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KHARELMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        kharel.setVisible(true);
    }//GEN-LAST:event_KHARELMouseEntered

    private void KHARELMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KHARELMouseExited
        // TODO add your handling code here:
        kharel.setVisible(false);
        L5BGStaff.setIcon(new javax.swing.ImageIcon("src\\Resources\\L5BGDefault.png"));
    }//GEN-LAST:event_KHARELMouseExited

    private void KHARELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KHARELActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        setBarberNum(4);
        Providers pr = new Providers();
        pr.setProviderId(getBarberNum());
        System.out.println(pr.getProviderId());

        if (getServiceNum() == 0) {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(true);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            s1Hitbox.setVisible(true);
            s2Hitbox.setVisible(true);
            s3Hitbox.setVisible(true);

            L4BGonEnter.setIcon(new javax.swing.ImageIcon("src\\Resources\\KharelPatentisOnEnter.gif"));
            L4BGonEnter.setVisible(true);

            L4BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\servicesBGDefault.gif"));
        } else {
            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(true);
            L8ProfilePage.setVisible(false);
        }
    }//GEN-LAST:event_KHARELActionPerformed

    private void exitButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton4MouseClicked

    private void exitButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton4MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        exitButton4.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButton4MouseEntered

    private void exitButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton4MouseExited
        // TODO add your handling code here:
        exitButton4.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton4MouseExited

    private void exitButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton4ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();

        if (!enterTheTeam.isSelected()) {
            L6AboutUsPage.setVisible(false);
            L3Dashboard.setVisible(true);
        } else {
            L6BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L6BGDefault.png"));
            enterTheTeam.setSelected(false);
        }
    }//GEN-LAST:event_exitButton4ActionPerformed

    private void enterTheTeamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterTheTeamMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        if (!enterTheTeam.isSelected()) {
            L6BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L6BGTeamOnEnter.gif"));
        }
    }//GEN-LAST:event_enterTheTeamMouseEntered

    private void enterTheTeamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterTheTeamMouseExited
        // TODO add your handling code here:
        if (!enterTheTeam.isSelected()) {
            L6BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L6BGDefault.png"));
        }
    }//GEN-LAST:event_enterTheTeamMouseExited

    private void enterTheTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterTheTeamActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();
        enterTheTeam.setSelected(true);
        L6BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L6TheTeam.gif"));
    }//GEN-LAST:event_enterTheTeamActionPerformed

    private void exitButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton5MouseClicked

    private void exitButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton5MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        exitButton5.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButton5MouseEntered

    private void exitButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton5MouseExited
        // TODO add your handling code here:
        exitButton5.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton5MouseExited

    private void exitButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton5ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(true);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(false);
        L8ProfilePage.setVisible(false);

        jComboBox1.setSelectedIndex(-1);
    }//GEN-LAST:event_exitButton5ActionPerformed

    private void bookAppointmentHBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookAppointmentHBMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L7BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L7BGConfirmAppointment.gif"));
    }//GEN-LAST:event_bookAppointmentHBMouseEntered

    private void bookAppointmentHBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookAppointmentHBMouseExited
        // TODO add your handling code here:
        L7BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L7BG.png"));
    }//GEN-LAST:event_bookAppointmentHBMouseExited

    private void bookAppointmentHBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookAppointmentHBActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        int userChoice = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to " + getBookText() + "?"
                + "\n\n" + "Appointment: " + getDaySelectedName() + ", "
                + getDateFormat()
                + "\n" + "Service: " + getServiceNameSelected()
                + "\n" + "Provider: " + getBarberNameSelected()
                + "\n" + "Date: " + getDateFormat()
                + "\n" + "Time zone locale: " + getTimezoneSelected()
                + "\n" + "Timeslot ID: " + getTimeslotNum()
                + "\n" + "Timeslot: " + getTimeslotSelected(),
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        // Check the user's choice
        if (userChoice == JOptionPane.YES_OPTION) {
            Thread playSoundFX1;
            playSoundFX1 = new Thread(() -> {
                playAudio("src\\Resources\\buttonPressed2.wav");
            });
            playSoundFX1.start();
            // User clicked 'Yes'
            System.out.println("User chose to proceed.");

            L1LandingPage.setVisible(false);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(true);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);

            //Ensure that schedules has no duplicate date and time
            for (Map.Entry<Integer, Schedules> entry1 : getSchedulesHashtable().entrySet()) {
                //int key1 = entry1.getKey();
                Schedules value1 = entry1.getValue();
                if (value1.getSchedFormat() == getDateFormat() || value1.getTime() == getTimeslotSelected()) {
                    System.out.println("THERE IS A DUPLICATE IN SCHEDULES' DATE AND TIME!");
                    JOptionPane.showMessageDialog(
                            null,
                            "CHOOSE ANOTHER DATE or TIME!",
                            "DATE or TIME ALREADY TAKEN",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else {
                    //Increment customerCountPerDay if there is no duplicate in schedules' date and time
                    for (Map.Entry<Integer, Providers> entry2 : getProvidersHashtable().entrySet()) {
                        //int key = entry2.getKey();
                        Providers value2 = entry2.getValue();
                        //System.out.println("Checking day: " + dayOfWeek);  // Debug print
                        if (value2.getProviderId() == getBarberNum()) {
                            if (value2.getClientCountPerDay() >= 3) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        value2.getProviderName() + " cannot receive appointments anymore.",
                                        "Maximum customer bookings per day reached!",
                                        JOptionPane.INFORMATION_MESSAGE);
                                System.out.println(value2.getProviderName() + "'s ClientCountPerDay: " + value2.getClientCountPerDay());
                                value2.setStatus("Unavailable");
                                break;
                            } else {
                                value2.setClientCountPerDay(value2.getClientCountPerDay() + 1);

                                //Set the Schedules hashtable
                                int newSchedId = getSchedulesHashtable().size() + 1;
                                Schedules newSchedule = new Schedules(newSchedId, getTimezoneSelected(),
                                        getDaySelectedName(), getDayNum(), getMonthNum(),
                                        getYearNum(), getTimeslotSelected(), getDateFormat());

                                getSchedulesHashtable().put(newSchedId, newSchedule);
                                updateSchedulesDatabase();
                                setSchedNum(newSchedId);

                                //Set the Bookings hashtable
                                int newBookingId = getBookingsHashtable().size() + 1;
                                Bookings newBooking = new Bookings(newBookingId, getAccountNum(),
                                        getBarberNum(), getServiceNum(), getSchedNum(), "Upcoming");

                                getBookingsHashtable().put(newBookingId, newBooking);
                                updateBookingsDatabase();
                                //setBookNum(newBookingId);
                                System.out.println(getBookingsHashtable());
                                //model.addRow(new Object[]{schedFormat, serviceFormat,providerFormat});
                                PopulateTable();

                                //Reset booking
                                setBarberNum(0);
                                setServiceNum(0);

                                JOptionPane.showMessageDialog(
                                        null,
                                        "You've successfully booked an appointment!",
                                        "Appointment has been recorded",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        } else {
            // User clicked 'No' or closed the dialog
            System.out.println("User chose not to proceed.");
        }
    }//GEN-LAST:event_bookAppointmentHBActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        // TODO add your handling code here:

        L7BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L7BG.png"));
    }//GEN-LAST:event_jPanel1MouseMoved

    private void L7AppointmentPageMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L7AppointmentPageMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_L7AppointmentPageMouseMoved

    private void L7AppointmentPageComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_L7AppointmentPageComponentShown
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed.wav");
        });
        playSoundFX.start();

        jComboBox1.setSelectedIndex(jComboBox1.getItemCount() - 1);
        updateL7.start();

        //Service Name
        jLabel1.setText(" ");
        for (Map.Entry<Integer, Services> entry : getServicesHashtable().entrySet()) {
            //int key = entry.getKey();
            Services value = entry.getValue();
            //System.out.println("Checking day: " + dayOfWeek);  // Debug print
            if (getServiceNum() == value.getServiceId()) {
                jLabel1.setText(value.getServiceName());
                setServiceNameSelected(value.getServiceName());
                break;
            }
        }
        //Provider Name
        jLabel2.setText(" ");
        for (Map.Entry<Integer, Providers> entry : getProvidersHashtable().entrySet()) {
            //int key = entry.getKey();
            Providers value = entry.getValue();
            //System.out.println("Checking day: " + dayOfWeek);  // Debug print
            if (getBarberNum() == value.getProviderId()) {
                jLabel2.setText("with " + value.getProviderName());
                setBarberNameSelected(value.getProviderName());
                break;
            }
        }
        setIsRunning(true);
    }//GEN-LAST:event_L7AppointmentPageComponentShown

    private void exitButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton8MouseClicked

    private void exitButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton8MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        exitButton8.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButton8MouseEntered

    private void exitButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton8MouseExited
        // TODO add your handling code here:
        exitButton8.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton8MouseExited

    private void exitButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton8ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L2_2SignUpPage.setVisible(false);
        L3Dashboard.setVisible(true);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(false);
        L8ProfilePage.setVisible(false);
        L9AccountSettings.setVisible(false);
    }//GEN-LAST:event_exitButton8ActionPerformed

    private void SaveButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveButtonMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L9BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L9ASsaveOnEnter.png"));
    }//GEN-LAST:event_SaveButtonMouseEntered

    private void SaveButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveButtonMouseExited
        // TODO add your handling code here:
        L9BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L9_BGASOnEnter.png"));
    }//GEN-LAST:event_SaveButtonMouseExited

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        // TODO add your handling code here:
        //UPDATE LOGGED ACCOUNT'S DETAILS
        for (Map.Entry<Integer, Accounts> entry : getAccountsHashtable().entrySet()) {
            int key = entry.getKey();
            Accounts value = entry.getValue();
            if (value.getAccId() == getAccountNum()) {
                int accountId = key;
                String username = NameField.getText().trim();
                String password = value.getPassword();
                String phoneNum = PhoneField.getText().trim();
                String emailAdd = EmailField.getText().trim();
                String addressLoc = AddressField.getText().trim();
                String cityLoc = CityField.getText().trim();
                String zipcodeId = ZipCodeField.getText().trim();

                Accounts updatedAccount = new Accounts();
                updatedAccount.setAccId(accountId);
                updatedAccount.setUsername(username);
                updatedAccount.setPassword(password);
                updatedAccount.setPhoneNum(phoneNum);
                updatedAccount.setEmail(emailAdd);
                updatedAccount.setAddress(addressLoc);
                updatedAccount.setCity(cityLoc);
                updatedAccount.setZipcode(zipcodeId);

                getAccountsHashtable().replace(key, updatedAccount);
            }
        }
        updateAccountsDatabase();
        JOptionPane.showMessageDialog(
                null,
                "Changes has been saved!",
                "UPDATED ACCOUNT'S SETTINGS SUCCESSFULLY",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void L9AHitbox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9AHitbox1MouseEntered
        // TODO add your handling code here:
        L8ProfilePage.setVisible(true);
        jTabbedPane1.setVisible(true);
        L9AccountSettings.setVisible(false);
    }//GEN-LAST:event_L9AHitbox1MouseEntered

    private void L9AHitbox1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9AHitbox1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_L9AHitbox1MouseExited

    private void L9AHitbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L9AHitbox1ActionPerformed
        // TODO add your handling code here:
        AHitbox.setSelected(true);
        BAHitbox.setSelected(false);
        ASHitbox.setSelected(false);
        LOHitbox.setSelected(false);
        jTabbedPane1.setVisible(true);

        if (!L8ProfilePage.isVisible()) {
            L8BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\l8_1AppointmentsOnEnter.png"));
        }
    }//GEN-LAST:event_L9AHitbox1ActionPerformed

    private void L9BAHitbox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9BAHitbox1MouseEntered
        // TODO add your handling code here:
        L9AccountSettings.setVisible(false);
    }//GEN-LAST:event_L9BAHitbox1MouseEntered

    private void L9BAHitbox1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9BAHitbox1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_L9BAHitbox1MouseExited

    private void L9BAHitbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L9BAHitbox1ActionPerformed
        // TODO add your handling code here:
        AHitbox.setSelected(false);
        BAHitbox.setSelected(true);
        ASHitbox.setSelected(false);
        LOHitbox.setSelected(false);

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L2_2SignUpPage.setVisible(false);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(true);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(false);
        L8ProfilePage.setVisible(false);

    }//GEN-LAST:event_L9BAHitbox1ActionPerformed

    private void L9ASHitbox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9ASHitbox1MouseEntered
        // TODO add your handling code here:
        L8BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L8_BGASOnEnter.png"));
        L9AccountSettings.setVisible(true);
        L9BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L9_BGASOnEnter.png"));

        jTabbedPane1.setVisible(false);
    }//GEN-LAST:event_L9ASHitbox1MouseEntered

    private void L9ASHitbox1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9ASHitbox1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_L9ASHitbox1MouseExited

    private void L9ASHitbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L9ASHitbox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_L9ASHitbox1ActionPerformed

    private void L9LOHitbox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9LOHitbox1MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L9BGLogout.setVisible(true);
        L9BGLogout.setIcon(new javax.swing.ImageIcon("src\\Resources\\L8BGLogout.png"));
    }//GEN-LAST:event_L9LOHitbox1MouseEntered

    private void L9LOHitbox1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9LOHitbox1MouseExited
        // TODO add your handling code here:
        L9BGLogout.setVisible(false);
    }//GEN-LAST:event_L9LOHitbox1MouseExited

    private void L9LOHitbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L9LOHitbox1ActionPerformed
        // TODO add your handling code here:
        AHitbox.setSelected(false);
        BAHitbox.setSelected(false);
        ASHitbox.setSelected(false);
        LOHitbox.setSelected(true);

        int userChoice = JOptionPane.showConfirmDialog(
                null,
                "Do you really want to Log Out?",
                "Log Out Confirmation",
                JOptionPane.YES_NO_OPTION);

        // Check the user's choice
        if (userChoice == JOptionPane.YES_OPTION) {
            Thread playSoundFX1;
            playSoundFX1 = new Thread(() -> {
                playAudio("src\\Resources\\buttonPressed.wav");
            });
            playSoundFX1.start();

            L1LandingPage.setVisible(true);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);
            L9AccountSettings.setVisible(false);

            setAccountNum(0);
            setBarberNum(0);
            setServiceNum(0);

        } else {
            // User clicked 'No' or closed the dialog
            System.out.println("User chose not to proceed.");

        }
    }//GEN-LAST:event_L9LOHitbox1ActionPerformed

    private void L9AccountSettingsComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_L9AccountSettingsComponentShown
        // TODO add your handling code here:
        L9BGLogout.setVisible(false);
        PopulateTable();

        for (Map.Entry<Integer, Accounts> entry : getAccountsHashtable().entrySet()) {
            //int key = entry.getKey();
            Accounts value = entry.getValue();
            if (value.getAccId() == getAccountNum()) {
                usernameBar2.setText(" ");
                usernameBar2.setText(value.getUsername());

                emailBar2.setText(" ");
                emailBar2.setText(value.getEmail());

            }
        }

        for (Map.Entry<Integer, Accounts> entry : getAccountsHashtable().entrySet()) {
            //int key = entry.getKey();
            Accounts value = entry.getValue();
            if (value.getAccId() == getAccountNum()) {
                NameField.setText(" ");
                NameField.setText(value.getUsername());

                PhoneField.setText(" ");
                PhoneField.setText(value.getPhoneNum());

                EmailField.setText(" ");
                EmailField.setText(value.getEmail());

                AddressField.setText(" ");
                AddressField.setText(value.getAddress());

                CityField.setText(" ");
                CityField.setText(value.getCity());

                ZipCodeField.setText(" ");
                ZipCodeField.setText(value.getZipcode());
            }
        }
    }//GEN-LAST:event_L9AccountSettingsComponentShown

    private void exitButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton7MouseClicked

    private void exitButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton7MouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        exitButton7.setIcon(new javax.swing.ImageIcon("src\\Resources\\enterExitButton.gif"));
    }//GEN-LAST:event_exitButton7MouseEntered

    private void exitButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton7MouseExited
        // TODO add your handling code here:
        exitButton7.setIcon(new javax.swing.ImageIcon("src\\Resources\\returnIcon.png"));
    }//GEN-LAST:event_exitButton7MouseExited

    private void exitButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton7ActionPerformed
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\buttonPressed2.wav");
        });
        playSoundFX.start();

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L2_2SignUpPage.setVisible(false);
        L3Dashboard.setVisible(true);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(false);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(false);
        L8ProfilePage.setVisible(false);
        L9AccountSettings.setVisible(false);
    }//GEN-LAST:event_exitButton7ActionPerformed

    private void AHitboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AHitboxMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L8BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L8_1AppointmentsOnEnter.png"));
        jTabbedPane1.setVisible(true);
        if (L9AccountSettings.isVisible()) {
            L9BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L9_BGASOnEnter.png"));
        }
    }//GEN-LAST:event_AHitboxMouseEntered

    private void AHitboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AHitboxMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AHitboxMouseExited

    private void AHitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AHitboxActionPerformed
        // TODO add your handling code here:
        AHitbox.setSelected(true);
        BAHitbox.setSelected(false);
        ASHitbox.setSelected(false);
        LOHitbox.setSelected(false);
        jTabbedPane1.setVisible(true);
    }//GEN-LAST:event_AHitboxActionPerformed

    private void BAHitboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAHitboxMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L8BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L8_BGBAOnEnter.png"));
        jTabbedPane1.setVisible(true);
        L9AccountSettings.setVisible(false);
    }//GEN-LAST:event_BAHitboxMouseEntered

    private void BAHitboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAHitboxMouseExited
        // TODO add your handling code here:
        L8BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L8_1AppointmentsOnEnter.png"));
    }//GEN-LAST:event_BAHitboxMouseExited

    private void BAHitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAHitboxActionPerformed
        // TODO add your handling code here:
        AHitbox.setSelected(false);
        BAHitbox.setSelected(true);
        ASHitbox.setSelected(false);
        LOHitbox.setSelected(false);

        L1LandingPage.setVisible(false);
        L2LoginPage.setVisible(false);
        L2_2SignUpPage.setVisible(false);
        L3Dashboard.setVisible(false);
        L4ServicesPage.setVisible(false);
        L5StaffPage.setVisible(true);
        L6AboutUsPage.setVisible(false);
        L7AppointmentPage.setVisible(false);
        L8ProfilePage.setVisible(false);

        L9AccountSettings.setVisible(false);
    }//GEN-LAST:event_BAHitboxActionPerformed

    private void ASHitboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ASHitboxMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L8BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L8_BGASOnEnter.png"));
        L9AccountSettings.setVisible(true);
        L9BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L9_BGASOnEnter.png"));

        jTabbedPane1.setVisible(false);
    }//GEN-LAST:event_ASHitboxMouseEntered

    private void ASHitboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ASHitboxMouseExited
        // TODO add your handling code here:
        L8BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L8_BGASOnEnter.png"));
        L9AccountSettings.setVisible(false);
        L9BG.setIcon(new javax.swing.ImageIcon("src\\Resources\\L9_BGASOnEnter.png"));

        jTabbedPane1.setVisible(true);
    }//GEN-LAST:event_ASHitboxMouseExited

    private void ASHitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASHitboxActionPerformed
        // TODO add your handling code here:
        AHitbox.setSelected(true);
        BAHitbox.setSelected(false);
        ASHitbox.setSelected(true);
        LOHitbox.setSelected(false);
        jTabbedPane1.setVisible(false);
        L9AccountSettings.setVisible(true);
    }//GEN-LAST:event_ASHitboxActionPerformed

    private void LOHitboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LOHitboxMouseEntered
        // TODO add your handling code here:
        Thread playSoundFX;
        playSoundFX = new Thread(() -> {
            playAudio("src\\Resources\\click.wav");
        });
        playSoundFX.start();
        L8BGLogout.setVisible(true);
        L9AccountSettings.setVisible(false);

    }//GEN-LAST:event_LOHitboxMouseEntered

    private void LOHitboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LOHitboxMouseExited
        // TODO add your handling code here:
        L8BGLogout.setVisible(false);
    }//GEN-LAST:event_LOHitboxMouseExited

    private void LOHitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOHitboxActionPerformed
        // TODO add your handling code here:
        AHitbox.setSelected(false);
        BAHitbox.setSelected(false);
        ASHitbox.setSelected(false);
        LOHitbox.setSelected(true);

        int userChoice = JOptionPane.showConfirmDialog(
                null,
                "Do you really want to Log Out?",
                "Log Out Confirmation",
                JOptionPane.YES_NO_OPTION);

        // Check the user's choice
        if (userChoice == JOptionPane.YES_OPTION) {
            Thread playSoundFX1;
            playSoundFX1 = new Thread(() -> {
                playAudio("src\\Resources\\buttonPressed.wav");
            });
            playSoundFX1.start();

            L1LandingPage.setVisible(true);
            L2LoginPage.setVisible(false);
            L3Dashboard.setVisible(false);
            L4ServicesPage.setVisible(false);
            L5StaffPage.setVisible(false);
            L6AboutUsPage.setVisible(false);
            L7AppointmentPage.setVisible(false);
            L8ProfilePage.setVisible(false);
            L9AccountSettings.setVisible(false);

            setAccountNum(0);
            setBarberNum(0);
            setServiceNum(0);

        } else {
            // User clicked 'No' or closed the dialog
            System.out.println("User chose not to proceed.");

        }
    }//GEN-LAST:event_LOHitboxActionPerformed

    private void L8ProfilePageComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_L8ProfilePageComponentShown
        // TODO add your handling code here:
        PopulateTable();

        for (Map.Entry<Integer, Accounts> entry : getAccountsHashtable().entrySet()) {
            //int key = entry.getKey();
            Accounts value = entry.getValue();
            if (value.getAccId() == getAccountNum()) {
                usernameBar.setText(" ");
                usernameBar.setText(value.getUsername());

                emailBar.setText(" ");
                emailBar.setText(value.getEmail());

                greetingsBar.setText(" ");
                greetingsBar.setText("Hello, " + value.getUsername() + "!");
            }
        }

        L9AccountSettings.setVisible(false);
    }//GEN-LAST:event_L8ProfilePageComponentShown

    private void jCalendar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCalendar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jCalendar1MouseClicked

    private void L4ServicesPageComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_L4ServicesPageComponentShown
        // TODO add your handling code here:
        jToggleButton1.setSelected(true);
        jToggleButton1.setVisible(true);
        jComboBox1.setSelectedIndex(-1);
    }//GEN-LAST:event_L4ServicesPageComponentShown

    private void L5StaffPageComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_L5StaffPageComponentShown
        // TODO add your handling code here:
        jComboBox1.setSelectedIndex(-1);
    }//GEN-LAST:event_L5StaffPageComponentShown

    private void jCalendar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendar1PropertyChange
        // TODO add your handling code here:
        updateComboBox();
    }//GEN-LAST:event_jCalendar1PropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main frame = new Main();
                frame.setResizable(false);
                frame.setTitle("All Day Fade Booking App");
                frame.setIconImage(new ImageIcon("src\\Resources\\IntellEX.png").getImage());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AHitbox;
    private javax.swing.JButton ASHitbox;
    private javax.swing.JTextField AddressField;
    private javax.swing.JButton BAHitbox;
    private javax.swing.JTextField CityField;
    private javax.swing.JTextField EmailField;
    private javax.swing.JButton KHAREL;
    private javax.swing.JButton KIAN;
    private javax.swing.JLabel L1BG;
    private javax.swing.JLayeredPane L1LandingPage;
    private javax.swing.JLabel L2BG;
    private javax.swing.JLayeredPane L2LoginPage;
    private javax.swing.JLabel L2_2BG;
    private javax.swing.JLayeredPane L2_2SignUpPage;
    private javax.swing.JLabel L3BG;
    private javax.swing.JLabel L3BGonEnter;
    private javax.swing.JLayeredPane L3Dashboard;
    private javax.swing.JLabel L4BG;
    private javax.swing.JLabel L4BGonEnter;
    private javax.swing.JLayeredPane L4ServicesPage;
    private javax.swing.JLabel L5BGStaff;
    private javax.swing.JLayeredPane L5StaffPage;
    private javax.swing.JLayeredPane L6AboutUsPage;
    private javax.swing.JLabel L6BG;
    private javax.swing.JLayeredPane L7AppointmentPage;
    private javax.swing.JLabel L7BG;
    private javax.swing.JLabel L8BG;
    private javax.swing.JLabel L8BGLogout;
    private javax.swing.JLayeredPane L8ProfilePage;
    private javax.swing.JButton L9AHitbox1;
    private javax.swing.JButton L9ASHitbox1;
    private javax.swing.JLayeredPane L9AccountSettings;
    private javax.swing.JButton L9BAHitbox1;
    private javax.swing.JLabel L9BG;
    private javax.swing.JLabel L9BGLogout;
    private javax.swing.JLabel L9BGLogout1;
    private javax.swing.JButton L9LOHitbox1;
    private javax.swing.JButton LOHitbox;
    private javax.swing.JButton NAT;
    private javax.swing.JButton NIC;
    private javax.swing.JTextField NameField;
    private javax.swing.JTextField PhoneField;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField ZipCodeField;
    private javax.swing.JButton barber1;
    private javax.swing.JButton barber2;
    private javax.swing.JButton barber3;
    private javax.swing.JButton barber4;
    private javax.swing.JButton bookAppointmentHB;
    private javax.swing.JLabel bookOnEnter;
    private javax.swing.JLabel emailBar;
    private javax.swing.JLabel emailBar2;
    private javax.swing.JTextField emailBox;
    private javax.swing.JButton enterAboutUs;
    private javax.swing.JButton enterAccount;
    private javax.swing.JButton enterBook;
    private javax.swing.JButton enterButton;
    private javax.swing.JButton enterButton1;
    private javax.swing.JButton enterContactsBH;
    private javax.swing.JLabel enterKeinSanico;
    private javax.swing.JLabel enterKharelPatentis;
    private javax.swing.JLabel enterNathanLunay;
    private javax.swing.JLabel enterNickWild;
    private javax.swing.JButton enterServices;
    private javax.swing.JButton enterStaff;
    private javax.swing.JButton enterTheTeam;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton exitButton1;
    private javax.swing.JButton exitButton2;
    private javax.swing.JButton exitButton3;
    private javax.swing.JButton exitButton4;
    private javax.swing.JButton exitButton5;
    private javax.swing.JButton exitButton6;
    private javax.swing.JButton exitButton7;
    private javax.swing.JButton exitButton8;
    private javax.swing.JLabel greetingsBar;
    private javax.swing.JButton hitBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel kharel;
    private javax.swing.JLabel kian;
    private javax.swing.JLabel natty;
    private javax.swing.JLabel nick;
    private javax.swing.JPasswordField passwordBox;
    private static javax.swing.JTable pastAP;
    private javax.swing.JLabel providerBG;
    private javax.swing.JButton s1Hitbox;
    private javax.swing.JButton s2Hitbox;
    private javax.swing.JButton s3Hitbox;
    private javax.swing.JButton service1;
    private javax.swing.JButton service2;
    private javax.swing.JButton service3;
    private javax.swing.JLabel servicesOnEnter;
    private static javax.swing.JTable upcomingAP;
    private javax.swing.JLabel usernameBar;
    private javax.swing.JLabel usernameBar2;
    // End of variables declaration//GEN-END:variables
}
