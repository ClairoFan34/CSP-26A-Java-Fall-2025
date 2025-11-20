import java.util.ArrayList;

public class CellPhone {
    // All private variables
    private String brand;
    private String model;
    private int storage;
    private boolean isOn;
    private double batteryLevel;
    private ArrayList<String> contacts = new ArrayList<>();

    // Constructor 1: Default data constructor, initializes all variables when creating a new object
    public CellPhone(){
        brand = "Unknown";
        model = "Unknown";
        storage = 64;
        isOn = false;
        batteryLevel = 100;
        contacts = new ArrayList<>();
    }
    // Constructor 2: Same as first constructor, just with parameters so that certain fields can be set by user 
    public CellPhone(String brand, String model, int storage){
        this.brand = brand;
        this.model = model;
        this.storage = storage;
        isOn = false;
        this.batteryLevel = 100;
        contacts = new ArrayList<>();
    }
    
    // All getters and setters paired
    // Brand
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    // Model
    public String getModel() {
        return model; 
    }
    public void setModel(String model) {
        this.model = model;
    }
    // Storage
    public int getStorage() {
        return storage;
    }
    public void setStorage (int Storage) {
        this.storage = storage;
    }
    //Battery
    public double getBattery() {
        return batteryLevel;
    }
    // Contacts
    public ArrayList<String> getContacts(){
        return contacts;
    }
    // Functionallity methods
    // useBattery: helper method called whenever a certain action is made, depletes the amount of battery depending on action
    private void useBattery(double amount) {
        batteryLevel -= amount;
        if (batteryLevel < 0) {
            batteryLevel = 0;
        }
    }
    // chargeBattery: Just fills the battery back up to full
    public void chargeBattery() {
        batteryLevel = 100;
        System.out.println("Battery fully charged.");
    }
    // togglePower: checks if phone is on or off and does the opposite actions, powering on drains a little battery as well
    // If the battery is fully depleted, it cannot be powered on
    public void togglePower(){
        if (isOn == true) {
            System.out.println("Phone is turning off...");
            isOn = false;
        } 
        else {
            if (batteryLevel <= 0) {
                System.out.println("Battery dead, Cannot be powered on");
                return;
            }
            System.out.println("Phone is turning on...");
            isOn = true;
            useBattery(1); 
        }
    }
    // newContact: used to add new contact to arraylist of x object
    // Wont work if phone is off
    public void newContact(String name) {
        if (!isOn) {
            System.out.println("Phone is off. Cannot add contacts.");
            return;
        }
        contacts.add(name);
    }
    // makeCall: main function of cellphone, used to makes calls to which ever contact what chosen and previously
    // added to the conact list

    // I couldnt figure out how to make the battery drain dynamically :(
    // The plan was to make it so that you could stay on call as long as battery was available
    public void makeCall(String contact, int minutes) {
        if (!isOn) {
            System.out.println("Phone is off, call cannot be made");
            return;
        }
        if (!contacts.contains(contact)) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Calling " + contact + " for " + minutes + " minutes...");
        useBattery(minutes * 0.5);
    }

    // toString method: Used to display all current attributes / values
    public String toString() {
        return "Brand: " + brand + "\nModel: " + model + "\nStorage: " + storage + "GB" + "\nPower: " + (isOn ? "On" : "Off") + "\nBattery: " + batteryLevel + "%" + "\nContacts: " + contacts;
    }

    // equals method: used to check if all current attribute / values are the same between two objects
    public boolean equals(CellPhone other) {
        return this.brand.equals(other.brand) && this.model.equals(other.model) && this.storage == other.storage && this.isOn == other.isOn && this.batteryLevel == other.batteryLevel && this.contacts.equals(other.contacts);
    }
}
