public class CellPhoneDemo {
    public static void main(String[] args) {
        // This demo is just meant to showcase whats possible with the class that I created
        // All of this code is non-interactable and just made to show capability


        // Two new objects created: One with the default constructor, and one with the constructor that has parameters
        CellPhone phone1 = new CellPhone();
        CellPhone phone2 = new CellPhone("Apple", "iPhone 16", 256);

        // Phone attribute values are printed out and displayed before being edited
        System.out.println("Initial phone attribute values: ");
        System.out.println(phone1);
        System.out.println();
        System.out.println(phone2);

        // Trying to add a contact before the phone is the on powerstate will cause it to fail, as it should here
        phone2.newContact("Angel");   

        // Ill now turn both phones on to display the main callmaking and contact keeping abillities
        phone1.togglePower();
        phone2.togglePower();

        // Adding contacts should now work successfully
        // These could also work through user input, but for the case of the demo i just hard coded it
        phone1.newContact("Angel");
        phone1.newContact("Professor Zhang");
        phone2.newContact("Angel");
        phone2.newContact("Professor Zhang");
        System.out.println("Phone 1 contacts:");
        System.out.println(phone1.getContacts());
        System.out.println("Phone 2 contacts:");
        System.out.println(phone2.getContacts());

        // Calling a contact that's not in the contact list should fail to call
        phone2.makeCall("legnA", 5);

        // Now to display how a proper call work and how the battery works
        // Most of the batter drainage will come from making calls, at a rate of 0.5 per minute
        phone2.makeCall("Angel", 300);
        System.out.println("Battery: " + phone2.getBattery());
        System.out.println("Phone power state: " + (phone2.isOn() ? "On" : "Off"));

        // Once the battery reaches zero, the phone is incapable of making calls or even turning on
        phone2.togglePower();

        // Charging the should restore all capabilities phone
        phone2.chargeBattery();
        System.out.println("Battery: " + phone2.getBattery());

        // For the final comparison test ill make two more clones of phone 2 and use it to test the equals method
        CellPhone phone3 = new CellPhone("Apple", "iPhone 16", 256);
        CellPhone phone4 = new CellPhone("Apple", "iPhone 16", 256);

        System.out.println("phone3 equals phone4? " + phone3.equals(phone4));




    }
}
