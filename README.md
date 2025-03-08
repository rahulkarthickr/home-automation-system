# Home Automation System

This project provides a simulated environment for controlling multiple household devices through CLI.

## Features

### User Management
- **User Registration**: Create new user account with username and password
- **User Authentication**: Secure login system

### Device Management
- **Add Devices**: Register new devices to your automation system
- **Remove Devices**: Remove devices that are no longer needed
- **View Devices**: List all devices registered to your account

### Supported Devices

#### TV (Television)

- Power (ON / OFF)
- Channel control ( Up / Down / Keypad)
- Volume control (Up / Down / Mute)
- Status display

#### AC (Air Conditioner)

- Power (ON / OFF)
- Temperature control (18 °C - 32 °C)
- Mode selection (Cool, Dry, Fan, Heat)
- Fan speed control (Auto, High, Mid, Low)
- Swing control
- Sleep mode
- Status display

#### Geyser (Water Heater)

- Power (ON / OFF)
- Temperature control (30 °C - 75 °C)
- Status display

#### Fan

- Power (ON / OFF) 
- Speed control (1 - 7)
- Status display

#### Light

- Power (ON / OFF)
- Brightness control (1 - 7)
- Status display

## Requirements

- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt

## Usage

1. Clone the repository

   ```bash
   git clone https://github.com/rahulkarthickr/home-automation-system.git
   ```
   
2. Navigate to the project directory
   
   ```bash
   cd home-automation-system
   ```

3. Compile the java file:
   
   ```bash
   javac Main.java
   ```

4. Run the application:
   
   ```bash
   java Main
   ```

## Usage Guide

### Authentication:

1. Register a new account or log in with existing credentials
2. Follow the on-screen prompts to enter username and password

### Main Menu:

After successful login, you'll see the following options:
1. **Add New Device**: Register devices to your account
2. **View Devices**: List all your registered devices
3. **Control Devices**: Operate your registered devices
4. **Manage Devices**: Remove devices from your account
5. **Logout**: Exit your current session

### Control Devices:

1. Select "Control Devices" option from the main menu
2. Choose a device by device ID
3. Power on the device
4. Use the device-specific menu

## Contribution

If you encounter any issues or have suggestions for improvements , please open an issue or submit a pull request.
