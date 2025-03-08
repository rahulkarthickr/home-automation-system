import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class TV {
    private static boolean live = false;
    private static boolean record = false;
    protected static int vol_lvl = 1;
    protected static int channel_no = 100;
    private static boolean mute = false;

    public static boolean tv_mode(Scanner sc) {
        boolean power = false;
        String tv_mode = "";
        try {
            System.out.print("Enter TV mode (ON / OFF): ");
            tv_mode = sc.nextLine();
            String tv_case = tv_mode.toUpperCase();
            if(tv_case.equals("ON")) {
                power = true;
                System.out.println("POWER ON");
            }
            else if(tv_case.equals("OFF")) {
                System.out.println("POWER OFF");
            }
            else {
                System.out.println("Invalid mode. Please enter valid mode (ON / OFF).");
            }

        }
        catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input.");
        }
        return power;
    }

    public static String options(Scanner sc) {
        String option = "";
        try {
            System.out.println("==== TV Remote ====");
            System.out.println("Live");
            System.out.println("Record");
            System.out.println("Guide");
            System.out.println("Back");
            System.out.println("Home");
            System.out.println("Volume");
            System.out.println("Channel");
            System.out.println("Keypad");
            System.out.println("Mute");
            System.out.println("Status");
            System.out.println("Exit");
            System.out.print("Enter the operation that you wish to perform: ");
            option = sc.nextLine();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return option;
    }

    public static boolean operation(Scanner sc, String ac_op) {
        try {
            String op_case = ac_op.toUpperCase();
            switch (op_case) {
                case "LIVE":
                    live = true;
                    System.out.println("TV view is shifted to Live TV page.");
                    break;
                case "RECORD":
                    record = true;
                    System.out.println("The current program is now being recorded.");
                    break;
                case "GUIDE":
                    System.out.println("TV view is displaying user manual guide.");
                    break;
                case "BACK":
                    System.out.println("TV view is shifted to previous page.");
                    break;
                case "HOME":
                    System.out.println("Welcome to your TV Home.");
                    break;
                case "VOLUME":
                    handleVolume(sc);
                    break;
                case "CHANNEL":
                    handleChannel(sc);
                    break;
                case "KEYPAD":
                    handleKeypad(sc);
                    break;
                case "MUTE":
                    mute = true;
                    System.out.println("TV volume has been muted.");
                    break;
                case "STATUS":
                    displayStatus();
                    break;
                case "EXIT":
                    System.out.println("TV is powering off.");
                    return false;
                default:
                    System.out.println("Invalid operation. Please enter valid operation from TV remote");
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    private static void handleVolume(Scanner sc) {
        String vol_change = "";
        String vol_case = "";
        try {
            System.out.println("Volume level: " + vol_lvl);
            System.out.print("Enter the volume change (Up / Down): ");
            vol_change = sc.nextLine();
            vol_case = vol_change.toUpperCase();
            if(vol_case.equals("UP")) {
                if(vol_lvl <= 10) {
                    vol_lvl++;
                    System.out.println("Volume level: " + vol_lvl);
                }
                else {
                    System.out.println("Volume level has reached its maximum limit (10).");
                }
            }
            else if(vol_case.equals("DOWN")) {
                if(vol_lvl > 0){
                    vol_lvl--;
                    System.out.println("Volume level: " + vol_lvl);
                }
                else {
                    System.out.println("Volume level has reached to minimum limit (0).");
                }
            }
            else {
                System.out.println("Invalid volume operation. Please enter valid volume operation (Up / Down).");
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input (Up / Down).");
        }
    }

    private static void handleChannel(Scanner sc) {
        String channel_change = "";
        String channel_case = "";
        try {
            System.out.println("Channel number: " + channel_no);
            System.out.print("Enter the channel change (Up / Down): ");
            channel_change = sc.nextLine();
            channel_case = channel_change.toUpperCase();
            if(channel_no >= 100 && channel_no <= 999) {
                if(channel_case.equals("UP")) {
                    channel_no++;
                    System.out.println("Channel number: " + channel_no);
                }
                else if(channel_case.equals("DOWN")) {
                    channel_no--;
                    System.out.println("Channel number: " + channel_no);
                }
                else {
                    System.out.println("Invalid channel operation. Please enter valid channel operation (Up / Down).");
                }
            }
            else if(channel_no > 999) {
                System.out.println("Channel does not exists.");
            }
            else if(channel_no < 100) {
                System.out.println("Channel does not exist.");
            }
            else {
                System.out.println("Invalid channel change. Please enter channel number between (100 - 999).");
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input (Up / Down).");
        }
    }

    private static void handleKeypad(Scanner sc) {
        StringBuilder channelInput = new StringBuilder();
        System.out.println("1  2  3\n4  5  6\n7  8  9\nC  0  E");
        System.out.print("Enter channel number (100-999). Press 'E' to confirm, 'C' to clear: ");
        try {
            while(true) {
                String key = sc.nextLine().toUpperCase();
                if (key.equals("C")) {
                    channelInput.setLength(0);
                    System.out.println("Input cleared. Enter channel number:");
                } else if (key.equals("E")) {
                    if (channelInput.length() == 3) {
                        int channel = Integer.parseInt(channelInput.toString());
                        if (channel >= 100 && channel <= 999) {
                            channel_no = channel;
                            System.out.println("Channel has been changed to: " + channel);
                            break;
                        } else {
                            System.out.println("Invalid channel. Enter a number between 100 and 999.");
                        }
                    } else {
                        System.out.println("Enter a 3-digit channel before pressing 'E'.");
                    }
                } else if (key.matches("[0-9]")) {
                    if (channelInput.length() < 3) {
                        channelInput.append(key);
                        System.out.println("Current input: " + channelInput);
                    } else {
                        System.out.println("Channel number cannot exceed 3 digits.");
                    }
                } else {
                    System.out.println("Invalid key. Use 0-9, 'C' (clear), or 'E' (enter).");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayStatus() {
        System.out.println("==== TV Status ====");
        System.out.println("Live: " + (live ? "Watched" : "Not Watched"));
        System.out.println("Record: " +(record ? "ON" : "OFF"));
        System.out.println("Volume: " + vol_lvl);
        System.out.println("Channel-No: " + channel_no);
        System.out.println("Mute: " + (mute ? "ON" : "OFF"));
    }

}

class AC {
    protected static int temp = 24;
    protected static String mode = "Cool";
    protected static String speed = "Auto";
    protected static boolean swing_mode = false;
    protected static boolean sleep_mode = false;
    protected static int timer = 0;

    public static boolean ac_mode(Scanner sc) {
        boolean power = false;
        String ac_mode = "";
        try {
            System.out.print("Enter AC mode (ON / OFF): ");
            ac_mode = sc.nextLine();
            String ac_case = ac_mode.toUpperCase();
            if(ac_case.equals("ON")) {
                power = true;
                System.out.println("POWER ON");
            }
            else if(ac_case.equals("OFF")) {
                System.out.println("POWER OFF");
            }
            else {
                System.out.println("Invalid mode. Please enter valid mode (ON / OFF).");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid string input (ON / OFF).");
        }
        return power;
    }

    public static String options(Scanner sc) {
        String option = "";
        try {
            System.out.println("==== AC Remote ====");
            System.out.println("Temperature");
            System.out.println("Mode");
            System.out.println("Fan");
            System.out.println("Swing");
            System.out.println("Sleep");
            System.out.println("Timer");
            System.out.println("Status");
            System.out.println("Exit");
            System.out.print("Enter the operation that you wish to perform: ");
            option = sc.nextLine();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return option;
    }

    public static boolean operation(Scanner sc, String ac_op) {
        try {
            String op_case = ac_op.toUpperCase();
            switch (op_case) {
                case "TEMPERATURE":
                    handleTemp(sc);
                    break;
                case "MODE":
                    handleMode(sc);
                    break;
                case "FAN":
                    handleFan(sc);
                    break;
                case "SWING":
                    handleSwing(sc);
                    break;
                case "SLEEP":
                    handleSleep(sc);
                    break;
                case "TIMER":
                    handleTimer(sc);
                    break;
                case "STATUS":
                    displayStatus();
                    break;
                case "EXIT":
                    System.out.println("AC is powering off.");
                    return false;
                default:
                    System.out.println("Invalid operation. Please enter valid operation from AC remote.");
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    private static void handleTemp(Scanner sc) {
        String temp_change = "";
        String temp_case = "";
        try{
            System.out.println("Current temperature: " + temp);
            System.out.print("Enter temperature change (Up / Down): ");
            temp_change = sc.nextLine();
            temp_case = temp_change.toUpperCase();
            if(temp_case.equals("UP")) {
                if(temp < 32) {
                    temp++;
                    System.out.println("Current temperature: " + temp);
                }
                else {
                    System.out.println("Temperature has reached its maximum limit (32°C)");
                }
            }
            else if(temp_case.equals("DOWN")) {
                if(temp > 18) {
                    temp--;
                    System.out.println("Current temperature: " + temp);
                }
                else {
                    System.out.println("Temperature has reached its minimum limit (18°C)");
                }
            }
            else {
                System.out.println("Invalid temperature change. Please enter valid string input (Up / Down).");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input (Up / Down).");
        }
    }

    private static void handleMode(Scanner sc) {
        String mode_change = "";
        String mode_case = "";
        System.out.println("Current mode: " + mode);
        try{
            System.out.print("Change mode to (Cool / Dry / Fan / Heat): ");
            mode_change = sc.nextLine();
            mode_case = mode_change.toUpperCase();
            switch (mode_case) {
                case "COOL":
                case "DRY":
                case "FAN":
                case "HEAT":
                    mode = mode_change;
                    System.out.println("Current mode: " + mode);
                    break;
                default:
                    System.out.println("Invalid mode. Please enter valid mode (Cool / Dry / Fan / Heat).");
                    break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid string input (Cool / Dry / Fan / Heat).");
        }
    }

    private static void handleFan(Scanner sc) {
        String speed_change = "";
        String speed_case = "";
        System.out.println("Current speed: " + speed);
        try {
            System.out.print("Change to fan speed (Auto / High / Mid / Low): ");
            speed_change = sc.nextLine();
            speed_case = speed_change.toUpperCase();
            switch (speed_case) {
                case "AUTO":
                case "HIGH":
                case "MID":
                case "LOW":
                    speed = speed_change;
                    System.out.println("Current fan speed: " + speed);
                    break;
                default:
                    System.out.println("Invalid speed. Please enter valid speed (Auto / High / Mid / Low).");
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid speed change. Please enter valid speed (Auto / High / Mid / Low).");
        }
    }

    private static void handleSwing(Scanner sc) {
        String swing = "";
        try {
            System.out.println("Current swing mode: " + (swing_mode ? "ON" : "OFF"));
            System.out.print("Enter swing mode (ON / OFF): ");
            swing = sc.nextLine();
            String swing_case = swing.toUpperCase();
            if(swing_case.equals("ON")) {
                swing_mode = true;
                System.out.println("Swing mode: ON");
            }
            else if(swing_case.equals("OFF")){
                swing_mode = false;
                System.out.println("Swing mode: OFF");
            }
            else {
                System.out.println("Invalid mode. Please enter valid swing mode (ON / OFF).");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input (ON / OFF).");
        }
    }

    private static void handleSleep(Scanner sc) {
        String sleep = "";
        try {
            System.out.println("Current sleep mode: " + (sleep_mode ? "ON" : "OFF"));
            System.out.print("Enter sleep mode (ON / OFF): ");
            sleep = sc.nextLine();

            String sleep_case = sleep.toUpperCase();
            if(sleep_case.equals("ON")) {
                sleep_mode = true;
                System.out.println("Sleep mode: ON");
            }
            else if(sleep_case.equals("OFF")){
                sleep_mode = false;
                System.out.println("Sleep mode: OFF");
            }
            else {
                System.out.println("Invalid mode. Please enter valid sleep mode (ON / OFF).");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input (ON / OFF).");
        }
    }
    private static void handleTimer(Scanner sc) {
        try {
            System.out.println("Current timer: " + timer + " hours");
            System.out.print("Set timer in hours (0 to disable): ");
            String timerInput = sc.nextLine();
            try {
                int newTimer = Integer.parseInt(timerInput);
                if (newTimer >= 0 && newTimer <= 24) {
                    timer = newTimer;
                    System.out.println("Timer set to: " + timer + " hours.");
                } else {
                    System.out.println("Invalid timer value. Please enter a value between 0 and 24.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayStatus() {
        System.out.println("==== AC Status ====");
        System.out.println("Temperature: " + temp + "°C");
        System.out.println("Mode: " + mode);
        System.out.println("Fan-Speed: " + speed);
        System.out.println("Swing: " + (swing_mode ? "ON" : "OFF"));
        System.out.println("Sleep: " + (sleep_mode ? "ON" : "OFF"));
        System.out.println("Timer: " + timer + " hours");
    }
}

class GEYSER {
    private static boolean powerState = false;
    private static int temperature = 40;
    private static int timer = 0;

    public static boolean geyser_mode(Scanner sc) {
        boolean power = false;
        String geyser_mode = "";
        try {
            System.out.print("Enter Geyser mode (ON / OFF): ");
            geyser_mode = sc.nextLine();

            String geyser_case = geyser_mode.toUpperCase();
            if(geyser_case.equals("ON")) {
                power = true;
                powerState = true;
                System.out.println("POWER ON");
            }
            else if(geyser_case.equals("OFF")) {
                powerState = false;
                System.out.println("POWER OFF");
            }
            else {
                System.out.println("Invalid mode. Please enter valid mode (ON / OFF).");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input (ON / OFF).");
        }
        return power;
    }

    public static String options(Scanner sc) {
        String option = "";
        try {
            System.out.println("==== Geyser ====");
            System.out.println("Temperature");
            System.out.println("Timer");
            System.out.println("Status");
            System.out.println("Exit");
            System.out.print("Enter the operation that you wish to perform: ");
            option = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return option;
    }

    public static boolean operation(Scanner sc, String geyser_op) {
        try {
            String op_case = geyser_op.toUpperCase();
            switch (op_case) {
                case "TEMPERATURE":
                    handleTemperature(sc);
                    break;
                case "TIMER":
                    handleTimer(sc);
                    break;
                case "STATUS":
                    displayStatus();
                    break;
                case "EXIT":
                    System.out.println("Geyser is powering off.");
                    return false;
                default:
                    System.out.println("Invalid operation. Please enter a valid operation.");
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    private static void handleTemperature(Scanner sc) {
        try {
            System.out.println("Current temperature: " + temperature + "°C");
            System.out.print("Enter new temperature (30-75°C): ");
            String tempInput = sc.nextLine();
            try {
                int newTemp = Integer.parseInt(tempInput);
                if (newTemp >= 30 && newTemp <= 75) {
                    temperature = newTemp;
                    System.out.println("Temperature set to: " + temperature + "°C");
                } else {
                    System.out.println("Invalid temperature. Please enter a value between 30 and 75.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleTimer(Scanner sc) {
        try {
            System.out.println("Current timer: " + timer + " minutes");
            System.out.print("Set timer in minutes (0 to disable): ");
            String timerInput = sc.nextLine();
            try {
                int newTimer = Integer.parseInt(timerInput);
                if (newTimer >= 0 && newTimer <= 180) {
                    timer = newTimer;
                    System.out.println("Timer set to: " + timer + " minutes.");
                } else {
                    System.out.println("Invalid timer value. Please enter a value between 0 and 180.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayStatus() {
        System.out.println("==== Geyser Status ====");
        System.out.println("Power: " + (powerState ? "ON" : "OFF"));
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Timer: " + timer + " minutes");
    }
}

class FAN {
    private static boolean powerState = false;
    private static int fan_speed = 1;

    public static boolean fan_mode(Scanner sc) {
        boolean power = false;
        String fan_mode = "";
        try {
            System.out.print("Enter fan mode (ON / OFF): ");
            fan_mode = sc.nextLine();
            String fan_case = fan_mode.toUpperCase();
            if(fan_case.equals("ON")) {
                power = true;
                powerState = true;
                System.out.println("POWER ON");
            }
            else if(fan_case.equals("OFF")) {
                powerState = false;
                System.out.println("POWER OFF");
            }
            else {
                System.out.println("Invalid mode. Please enter valid mode (ON / OFF).");
            }

        }
        catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input.");
        }
        return power;
    }

    public static String options(Scanner sc) {
        String option = "";
        try {
            System.out.println("==== Fan ====");
            System.out.println("Speed");
            System.out.println("Status");
            System.out.println("Exit");
            System.out.print("Enter the operation that you wish to perform: ");
            option = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return option;
    }

    public static boolean operation(Scanner sc, String fan_op) {
        try {
            String op_case = fan_op.toUpperCase();
            switch (op_case) {
                case "SPEED":
                    System.out.println("Current fan speed : " + fan_speed);
                    System.out.print("Enter fan speed (1 - 7): ");
                    try {
                        fan_speed = sc.nextInt();
                        sc.nextLine();
                        switch (fan_speed) {
                            case 0:
                                System.out.println("Fan speed has reached its minimum limit");
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                                System.out.println("Fan speed has been to: " + fan_speed);
                                break;
                            default:
                                System.out.println("Invalid speed. Please enter valid fan speed (1 - 7).");
                                break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number between 1 and 7.");
                        sc.nextLine();
                    }
                    break;
                case "STATUS":
                    displayStatus();
                    break;
                case "EXIT":
                    System.out.println("Fan is powering off");
                    return false;
                default:
                    System.out.println("Invalid fan operation. Please enter a valid operation.");
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    private static void displayStatus() {
        System.out.println("==== Fan Status ====");
        System.out.println("Power: " + (powerState ? "ON" : "OFF"));
        System.out.println("Speed: " + fan_speed);
    }
}

class LIGHT {
    private static boolean powerState = false;
    protected static int brightness = 1;
    public static boolean light_mode(Scanner sc) {
        boolean power = false;
        String light_mode = "";
        try {
            System.out.print("Enter light mode (ON / OFF): ");
            light_mode = sc.nextLine();
            String light_case = light_mode.toUpperCase();
            if(light_case.equals("ON")) {
                power = true;
                powerState = true;
                System.out.println("POWER ON");
            }
            else if(light_case.equals("OFF")) {
                powerState = false;
                System.out.println("POWER OFF");
            }
            else {
                System.out.println("Invalid mode. Please enter valid mode (ON / OFF).");
            }

        }
        catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input. Please enter valid string input.");
        }
        return power;
    }

    public static String options(Scanner sc) {
        String option = "";
        try {
            System.out.println("==== Light ====");
            System.out.println("Brightness");
            System.out.println("Status");
            System.out.println("Exit");
            System.out.print("Enter the operation that you wish to perform: ");
            option = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return option;
    }

    public static boolean operation(Scanner sc, String light_op) {
        try {
            String op_case = light_op.toUpperCase();
            switch (op_case) {
                case "BRIGHTNESS":
                    System.out.println("Current light brightness: " + brightness);
                    System.out.print("Enter light brightness (1 - 7): ");
                    try {
                        brightness = sc.nextInt();
                        sc.nextLine();
                        switch (brightness) {
                            case 0:
                                System.out.println("Light brightness has reached its minimum limit. There is no light.");
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                System.out.println("Light brightness: " + brightness);
                                break;
                            case 7:
                                System.out.println("Light brightness has reached its maximum limit");
                                break;
                            default:
                                System.out.println("Invalid speed. Please enter valid fan speed (1 - 7).");
                                break;
                        }
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid speed. Please enter valid fan speed (1 - 7).");
                        sc.nextLine();
                    }
                    break;
                case "STATUS":
                    displayStatus();
                    break;
                case "EXIT":
                    System.out.println("Light is powering off");
                    return false;
                default:
                    System.out.println("Invalid light operation. Please enter a valid operation.");
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    private static void displayStatus() {
        System.out.println("==== Light Status ====");
        System.out.println("Power: " + (powerState ? "ON" : "OFF"));
        System.out.println("Brightness: " + brightness);
    }
}

class Device {
    private int id;
    private String name;

    public Device(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}

class User {
    private String username;
    private String password;
    private ArrayList<Device> devices;
    private int nextDeviceId;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.devices = new ArrayList<>();
        this.nextDeviceId = 1;
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public boolean removeDevice(int deviceId) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == deviceId) {
                devices.remove(i);
                return true;
            }
        }
        return false;
    }
}

class UserManager {
    private ArrayList<User> users;
    private User currentUser;

    public UserManager() {
        users = new ArrayList<>();
    }

    public boolean registerUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        User newUser = new User(username, password);
        users.add(newUser);
        return true;
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.verifyPassword(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}

public class Main {
    protected static UserManager userManager = new UserManager();
    protected static int nextDeviceId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== Welcome to Home Automation System ====");
        boolean running = true;
        while (running) {
            if (userManager.getCurrentUser() == null) {
                running = showAuthMenu(sc);
            }
            else {
                running = showMainMenu(sc);
            }
        }
        System.out.println("Thank you for using Home Automation System!");
        sc.close();
    }

    public static boolean showAuthMenu(Scanner sc) {
        System.out.println("==== Authentication ====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option (1 - 3): ");
        String option = sc.nextLine();
        switch (option) {
            case "1":
                handleLogin(sc);
                return true;
            case "2":
                handleRegistration(sc);
                return true;
            case "3":
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
                return true;
        }
    }

    public static void handleLogin(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (userManager.loginUser(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public static void handleRegistration(Scanner sc) {
        System.out.print("Enter new username: ");
        String username = sc.nextLine();
        System.out.print("Enter new password: ");
        String password = sc.nextLine();
        if (userManager.registerUser(username, password)) {
            System.out.println("Registration successful! Please login.");
        } else {
            System.out.println("Username already exists. Please choose another username.");
        }
    }

    public static boolean showMainMenu(Scanner sc) {
        User currentUser = userManager.getCurrentUser();
        ArrayList<Device> devices = currentUser.getDevices();
        System.out.println("==== Welcome, " + currentUser.getUsername() + " ====");
        System.out.println("1. Add New Device");
        System.out.println("2. View Devices");
        System.out.println("3. Control Devices");
        System.out.println("4. Manage Devices");
        System.out.println("5. Logout");
        System.out.print("Choose an option (1 - 5): ");
        String option = sc.nextLine();
        switch (option) {
            case "1":
                addDevice(sc);
                return true;
            case "2":
                if (devices.isEmpty()) {
                    System.out.println("You haven't added any devices yet. Please add a device first.");
                } else {
                    System.out.println("Your devices:");
                    for (Device device : devices) {
                        System.out.println(device.toString());
                    }
                }
                return true;
            case "3":
                if (!devices.isEmpty()) {
                    controlDevice(sc, devices.size(), devices);
                } else {
                    System.out.println("No devices added. Please add a device first.");
                }
                return true;
            case "4":
                if(devices.isEmpty()) {
                    System.out.println("You haven't added any devices. Please add a device first.");
                }
                else {
                    manageDevices(sc);
                }
                return true;
            case "5":
                userManager.logout();
                System.out.println("Logged out successfully.");
                return true;
            default:
                System.out.println("Invalid option. Please try again.");
                return true;
        }
    }

    public static void manageDevices(Scanner sc) {
        User currentUser = userManager.getCurrentUser();
        ArrayList<Device> devices = currentUser.getDevices();
        System.out.println("==== Device Management ====");
        System.out.println("1. Remove Devices");
        System.out.println("2. Back to Main Menu");
        System.out.print("Choose an option: ");
        String option = sc.nextLine();
        switch (option) {
            case "1":
                if (devices.isEmpty()) {
                    System.out.println("You haven't added any devices yet.");
                } else {
                    System.out.println("Your devices:");
                    for (Device device : devices) {
                        System.out.println(device.toString());
                    }
                    try {
                        System.out.print("Enter device ID to remove: ");
                        int deviceId = Integer.parseInt(sc.nextLine());
                        if (currentUser.removeDevice(deviceId)) {
                            System.out.println("Device with ID " + deviceId + " has been removed successfully.");
                        } else {
                            System.out.println("Device with ID " + deviceId + " not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer device ID.");
                    }
                }
                break;
            case "2":
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    public static void addDevice(Scanner sc) {
        User currentUser = userManager.getCurrentUser();
        System.out.println("Here is the list of devices that can be automated:");
        System.out.println("1. TV");
        System.out.println("2. AC");
        System.out.println("3. Geyser");
        System.out.println("4. Fan");
        System.out.println("5. Light");
        try {
            System.out.print("Enter the number of devices in your home: ");
            int device_count = Integer.parseInt(sc.nextLine());
            for(int i = 1; i <= device_count; i++) {
                System.out.print("Enter device " + i + " name: ");
                String deviceName = sc.nextLine().toUpperCase();
                if (deviceName.equals("TV") || deviceName.equals("AC") ||
                        deviceName.equals("GEYSER") || deviceName.equals("FAN") ||
                        deviceName.equals("LIGHT")) {
                    Device device = new Device(nextDeviceId++, deviceName);
                    currentUser.addDevice(device);
                    System.out.println(deviceName + " has been added successfully with ID: " + (nextDeviceId-1));
                } else {
                    System.out.println("Invalid device name. Please enter a valid device name.");
                    i--;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }

    public static void controlDevice(Scanner sc, int device_count, ArrayList<Device> devices) {
        String control_device = "";
        User currentUser = userManager.getCurrentUser();
        System.out.println("Your devices:");
        for (Device device : devices) {
            System.out.println(device.toString());
        }
        System.out.println("Enter 'back' to return to Main Menu");
        try {
            System.out.print("Enter the device ID of the device you want to control: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("back")) {
                return;
            }
            try {
                int deviceId = Integer.parseInt(input);
                for (Device device : devices) {
                    if (device.getId() == deviceId) {
                        control_device = device.getName();
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                control_device = input;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid input");
            return;
        }
        String control_case = control_device.toUpperCase();
        switch (control_case) {
            case "TV":
                boolean tv_power = TV.tv_mode(sc);
                if(tv_power) {
                    while(tv_power) {
                        String tv_operation = TV.options(sc);
                        tv_power = TV.operation(sc,tv_operation);
                    }
                }
                break;
            case "AC":
                boolean ac_power = AC.ac_mode(sc);
                if(ac_power) {
                    while(ac_power) {
                        String ac_operation = AC.options(sc);
                        ac_power = AC.operation(sc,ac_operation);
                    }
                }
                break;
            case "GEYSER":
                boolean geyser_power = GEYSER.geyser_mode(sc);
                if(geyser_power) {
                    while(geyser_power) {
                        String geyser_operation = GEYSER.options(sc);
                        geyser_power = GEYSER.operation(sc,geyser_operation);
                    }
                }
                break;
            case "FAN":
                boolean fan_power = FAN.fan_mode(sc);
                if(fan_power) {
                    while (fan_power) {
                        String fan_operation = FAN.options(sc);
                        fan_power = FAN.operation(sc, fan_operation);
                    }
                }
                break;
            case "LIGHT":
                boolean light_power = LIGHT.light_mode(sc);
                if(light_power) {
                    while(light_power) {
                        String light_operation = LIGHT.options(sc);
                        light_power = LIGHT.operation(sc, light_operation);
                    }
                }
                break;
            default:
                System.out.println("Invalid device. Please enter valid added device.");
                break;
        }
    }
}