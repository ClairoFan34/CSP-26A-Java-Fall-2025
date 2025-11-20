import java.util.ArrayList;

public class CellPhone {
    // All private variables
    private String brand;
    private String model;
    private int storage;
    private boolean isOn;
    private int batteryLevel;
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
    public int getBattery() {
        return batteryLevel;
    }
}
