package oh.data;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class stores information for a single row in our
 * office hours table.
 * 
 * @author Richard McKenna
 */
public class TimeSlot {

    public enum DayOfWeek {   
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    }   
    private StringProperty startTime;
    private StringProperty endTime;
    private HashMap<DayOfWeek, ArrayList<TeachingAssistantPrototype>> tas;
    private HashMap<DayOfWeek, StringProperty> dayText;
    private HashMap<DayOfWeek, ArrayList<TeachingAssistantPrototype>> undergadTAS;
    private HashMap<DayOfWeek, ArrayList<TeachingAssistantPrototype>> gradTAS;
    private HashMap<DayOfWeek,ArrayList<TeachingAssistantPrototype>> clipboard;


    public TimeSlot(String initStartTime, String initEndTime) {
        startTime = new SimpleStringProperty(initStartTime);
        endTime = new SimpleStringProperty(initEndTime);
        tas = new HashMap();
        dayText = new HashMap();
        undergadTAS = new HashMap();
        gradTAS = new HashMap();
        clipboard = new HashMap();
        for (DayOfWeek dow : DayOfWeek.values()) {
            tas.put(dow, new ArrayList());
            gradTAS.put(dow, new ArrayList());
            undergadTAS.put(dow, new ArrayList());
            clipboard.put(dow, new ArrayList());
            dayText.put(dow, new SimpleStringProperty());
        }
    }
    public void addTA(DayOfWeek dayOfWeek, TeachingAssistantPrototype ta){
         //add to different time slot data.
        if (ta.getType().equals("Undergraduate")) {
            if (undergadTAS.get(dayOfWeek).contains(ta)) {
                undergadTAS.get(dayOfWeek).remove(ta);
                ta.setSlots(ta.getSlots()-1);
            }else{
                undergadTAS.get(dayOfWeek).add(ta);
                ta.setSlots(ta.getSlots()+1);
            }
            String temp ="";
            for (int i = 0; i<undergadTAS.get(dayOfWeek).size();i++){
                temp+=undergadTAS.get(dayOfWeek).get(i).getName()+"\n";
            }
            dayText.get(dayOfWeek).set(temp);
        }else{
            if (gradTAS.get(dayOfWeek).contains(ta)) {
                gradTAS.get(dayOfWeek).remove(ta);
                ta.setSlots(ta.getSlots()-1);
            }else{
                gradTAS.get(dayOfWeek).add(ta);
                ta.setSlots(ta.getSlots()+1);
            }
            String temp ="";
            for (int i = 0; i<gradTAS.get(dayOfWeek).size();i++){
                temp+=gradTAS.get(dayOfWeek).get(i).getName()+"\n";
            }
            dayText.get(dayOfWeek).set(temp);
        }
       if(tas.get(dayOfWeek).contains(ta)){
           tas.get(dayOfWeek).remove(ta);
           
           
       }else{
           tas.get(dayOfWeek).add(ta);
           
        }
    }
    public void removeTA(DayOfWeek dayOfWeek, TeachingAssistantPrototype ta){
        //add to different time slot data.
        if (ta.getType().equals("Undergraduate")) {
            if (undergadTAS.get(dayOfWeek).contains(ta)) {
                undergadTAS.get(dayOfWeek).remove(ta);
                ta.setSlots(ta.getSlots()-1);
            }else{
                undergadTAS.get(dayOfWeek).add(ta);
                ta.setSlots(ta.getSlots()+1);
            }
            String temp ="";
            for (int i = 0; i<undergadTAS.get(dayOfWeek).size();i++){
                temp+=undergadTAS.get(dayOfWeek).get(i).getName()+"\n";
            }
            dayText.get(dayOfWeek).set(temp);
        }else{
            if (gradTAS.get(dayOfWeek).contains(ta)) {
                gradTAS.get(dayOfWeek).remove(ta);
                ta.setSlots(ta.getSlots()-1);
            }else{
                gradTAS.get(dayOfWeek).add(ta);
                ta.setSlots(ta.getSlots()+1);
            }
            String temp ="";
            for (int i = 0; i<gradTAS.get(dayOfWeek).size();i++){
                temp+=gradTAS.get(dayOfWeek).get(i).getName()+"\n";
            }
            dayText.get(dayOfWeek).set(temp);
        }
        // all timesolt
        if(tas.get(dayOfWeek).contains(ta)){
           tas.get(dayOfWeek).remove(ta);   
           
        }else{
           tas.get(dayOfWeek).add(ta);
           
        }

    }
    public void editTATimesolot(TeachingAssistantPrototype ta, String newType){
        if (ta.getType().equals("Undergraduate")&&!newType.equals("Undergraduate")) {
            for (DayOfWeek dow : DayOfWeek.values()){            
                    if(undergadTAS.get(dow).contains(ta)){
                        undergadTAS.get(dow).remove(ta);               
                        gradTAS.get(dow).add(ta);
                    }              
            }
        }else if(ta.getType().equals("Graduate")&&!newType.equals("Graduate")){
            for (DayOfWeek dow : DayOfWeek.values()){            
                if(gradTAS.get(dow).contains(ta)){
                    gradTAS.get(dow).remove(ta);                                
                    undergadTAS.get(dow).add(ta);
                }
                    
                
            }
        }
    }
    public void cutTATimeslot(TeachingAssistantPrototype ta){
        if (ta.getType().equals("Undergraduate")) {
            for (DayOfWeek dow : DayOfWeek.values()){
                if (undergadTAS.get(dow).contains(ta)) {
                    undergadTAS.get(dow).remove(ta);
                    tas.get(dow).remove(ta);
                    clipboard.get(dow).add(ta);
                    ta.setSlots(ta.getSlots()-1);
                }
            }
        }else{
            for (DayOfWeek dow : DayOfWeek.values()){
                if (gradTAS.get(dow).contains(ta)) {
                    gradTAS.get(dow).remove(ta);
                    tas.get(dow).remove(ta);
                    clipboard.get(dow).add(ta);
                    ta.setSlots(ta.getSlots()-1);
                }
            }
        }
    }
    public void cutTATimesoltRestore(TeachingAssistantPrototype ta ){
        if (ta.getType().equals("Undergraduate")) {
            for (DayOfWeek dow : DayOfWeek.values()){
                if (clipboard.get(dow).contains(ta)) {
                    undergadTAS.get(dow).add(ta);
                    tas.get(dow).add(ta);
                    clipboard.get(dow).remove(ta);
                    ta.setSlots(ta.getSlots()+1);
                }
            }
        }else{
            for (DayOfWeek dow : DayOfWeek.values()){
                if (clipboard.get(dow).contains(ta)) {
                    gradTAS.get(dow).add(ta);
                    tas.get(dow).add(ta);
                    clipboard.get(dow).remove(ta);
                    ta.setSlots(ta.getSlots()+1);
                }
            }
        }
    }
    public void setToUndergrad(){
        for (DayOfWeek dow : DayOfWeek.values()){
            String tempString="";
            for(int i =0;i<undergadTAS.get(dow).size();i++){
                tempString+=undergadTAS.get(dow).get(i).getName()+"\n";
            }
            dayText.get(dow).set(tempString);
        }
    }
    public void setToGrad(){
        for (DayOfWeek dow : DayOfWeek.values()){
            String tempString="";
            for(int i =0;i<gradTAS.get(dow).size();i++){
                tempString+=gradTAS.get(dow).get(i).getName()+"\n";
            }
            dayText.get(dow).set(tempString);
        }
    }
    public void setToAll(){
        for (DayOfWeek dow : DayOfWeek.values()){
            String tempString="";
            for(int i =0;i<tas.get(dow).size();i++){
                tempString+=tas.get(dow).get(i).getName()+"\n";
            }
            dayText.get(dow).set(tempString);
        }
    }
    // ACCESSORS AND MUTATORS
    public String getStartTime() {
        return startTime.getValue();
    }
    
    public void setStartTime(String initStartTime) {
        startTime.setValue(initStartTime);
    }
    
    public StringProperty startTimeProperty() {
        return startTime;
    }
    
    public String getEndTime() {
        return endTime.getValue();
    }
    
    public void setEndTime(String initEndTime) {
        endTime.setValue(initEndTime);
    }
    
    public StringProperty endTimeProperty() {
        return endTime;
    }
    
    public String getMonday() {
        return dayText.get(DayOfWeek.MONDAY).getValue();
    }
    
    public void setMonday(String initMonday) {
        dayText.get(DayOfWeek.MONDAY).setValue(initMonday);
    }
    
    public StringProperty mondayProperty() {
        return this.dayText.get(DayOfWeek.MONDAY);
    }
    
    public String getTuesday() {
        return dayText.get(DayOfWeek.MONDAY).getValue();
    }
    
    public void setTuesday(String initTuesday) {
        dayText.get(DayOfWeek.MONDAY).setValue(initTuesday);
    }
    
    public StringProperty tuesdayProperty() {
        return this.dayText.get(DayOfWeek.TUESDAY);
    }
    
    public String getWednesday() {
        return dayText.get(DayOfWeek.MONDAY).getValue();
    }
    
    public void setWednesday(String initWednesday) {
        dayText.get(DayOfWeek.MONDAY).setValue(initWednesday);
    }
    
    public StringProperty wednesdayProperty() {
        return this.dayText.get(DayOfWeek.WEDNESDAY);
    }
    
    public String getThursday() {
        return dayText.get(DayOfWeek.MONDAY).getValue();
    }
    
    public void setThursday(String initThursday) {
        dayText.get(DayOfWeek.MONDAY).setValue(initThursday);
    }
    
    public StringProperty thursdayProperty() {
        return this.dayText.get(DayOfWeek.THURSDAY);
    }
    
    public String getFriday() {
        return dayText.get(DayOfWeek.MONDAY).getValue();
    }
    
    public void setFriday(String initFriday) {
        dayText.get(DayOfWeek.MONDAY).setValue(initFriday);
    }
    
    public StringProperty fridayProperty() {
        return this.dayText.get(DayOfWeek.FRIDAY);
    }
    
    public void reset() {
        for (DayOfWeek dow : DayOfWeek.values()) {
            tas.get(dow).clear();
            dayText.get(dow).setValue("");
        }
    }
    public ArrayList<TeachingAssistantPrototype>getTAS(DayOfWeek dayOfWeek){
        return tas.get(dayOfWeek);
    }
}