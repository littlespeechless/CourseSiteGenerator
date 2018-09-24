package oh.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a Teaching Assistant for the table of TAs.
 * 
 * @author Richard McKenna
 */
public class TeachingAssistantPrototype {
    // THE TABLE WILL STORE TA NAMES AND EMAILS
    private final StringProperty name;
    private final StringProperty email;
    private final IntegerProperty slots;

    /**
     * Constructor initializes both the TA name and email.
     */
    public TeachingAssistantPrototype(String initName, String initEmail) {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
        slots = new SimpleIntegerProperty(0);
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES

    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    @Override
    public String toString() {
        return name.getValue();
    }

   
    public String getEmail(){
        return email.get();
    }
    public void setEmail(String initEmail){
        email.set(initEmail);
    }
    public StringProperty emailProperty(){
        return email;
    }
    public int getSlots(){
        return slots.get();
    }
    public void setSlots(int initSlot){
        slots.set(initSlot);
    }
    public IntegerProperty slotsProperty(){
        return slots;
    }
}