package oh.data;

import javafx.collections.ObservableList;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.util.Iterator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static oh.OfficeHoursPropertyType.OH_TAS_TABLE_VIEW;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_ALL;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_GRADUATE;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_UNDERGRADUATE;
import oh.data.TimeSlot.DayOfWeek;

/**
 * This is the data component for TAManagerApp. It has all the data needed
 * to be set by the user via the User Interface and file I/O can set and get
 * all the data from this object
 * 
 * @author Richard McKenna
 */
public class OfficeHoursData implements AppDataComponent {

    // WE'LL NEED ACCESS TO THE APP TO NOTIFY THE GUI WHEN DATA CHANGES
    OfficeHoursApp app;

    // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<TeachingAssistantPrototype> teachingAssistants;
    ObservableList<TeachingAssistantPrototype> underGradTAS;
    ObservableList<TeachingAssistantPrototype> gradTAS; 

    ObservableList<TimeSlot> officeHours;
    ObservableList<TimeSlot> undergradOH;
    ObservableList<TimeSlot> gradOH;
    

    // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
    // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
    // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
    // NO MEANS FOR CHANGING THESE VALUES
    int startHour;
    int endHour;
    
    // DEFAULT VALUES FOR START AND END HOURS IN MILITARY HOURS
    public static final int MIN_START_HOUR = 9;
    public static final int MAX_END_HOUR = 20;

    /**
     * This constructor will setup the required data structures for
     * use, but will have to wait on the office hours grid, since
     * it receives the StringProperty objects from the Workspace.
     * 
     * @param initApp The application this data manager belongs to. 
     */
    public OfficeHoursData(OfficeHoursApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        AppGUIModule gui = app.getGUIModule();

        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
        teachingAssistants = taTableView.getItems();
        underGradTAS = new TableView<TeachingAssistantPrototype>().getItems();
        gradTAS = new TableView<TeachingAssistantPrototype>().getItems();
       

        // THESE ARE THE DEFAULT OFFICE HOURS
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        
        resetOfficeHours();
    }
    
    private void resetOfficeHours() {
        //THIS WILL STORE OUR OFFICE HOURS
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView)gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHours = officeHoursTableView.getItems(); 
        officeHours.clear();
        undergradOH = new TableView<TimeSlot>().getItems();
        gradOH = new TableView<TimeSlot>().getItems();
        
        for (int i = startHour; i <= endHour; i++) {
            TimeSlot timeSlot = new TimeSlot(   this.getTimeString(i, true),
                                                this.getTimeString(i, false));
            officeHours.add(timeSlot);
            //gradOH.add(timeSlot);
            //undergradOH.add(timeSlot);
            TimeSlot halfTimeSlot = new TimeSlot(   this.getTimeString(i, false),
                                                    this.getTimeString(i+1, true));
            officeHours.add(halfTimeSlot);
            //gradOH.add(timeSlot);
            //undergradOH.add(timeSlot);
        }
    }
    
    private String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if (initStartHour <= initEndHour) {
            // THESE ARE VALID HOURS SO KEEP THEM
            // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
            startHour = initStartHour;
            endHour = initEndHour;
        }
        resetOfficeHours();
    }
        
    /**
     * Called each time new work is created or loaded, it resets all data
     * and data structures such that they can be used for new values.
     */
    @Override
    public void reset() {
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        /*
        for (int i = 0; i <officeHours.size(); i++) {
            TimeSlot timeSlot = officeHours.get(i);
            timeSlot.reset();
        }
        */
     
        for(int i = MIN_START_HOUR;i<MAX_END_HOUR;i++){
            TimeSlot timeSlot = officeHours.get(i);
            timeSlot.reset();
        }
        
        resetOfficeHours();
        
    }
    
    // ACCESSOR METHODS

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
    
    public boolean isTASelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView tasTable = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
        return tasTable.getSelectionModel().getSelectedItem() != null;
    }
    
    public void addTA(TeachingAssistantPrototype ta) {
        if (!this.teachingAssistants.contains(ta)){
            this.teachingAssistants.add(ta);
            if(ta.getType().equals("Undergraduate")){
                addUnderGradTA(ta);
            }else{
                addGradTA(ta);
            }
        }
    }
    public void addUnderGradTA(TeachingAssistantPrototype ta){
       if (!this.underGradTAS.contains(ta))
            this.underGradTAS.add(ta);
    }
    public void addGradTA(TeachingAssistantPrototype ta){
       if (!this.gradTAS.contains(ta))
            this.gradTAS.add(ta);
    }
   
    public void removeTA(TeachingAssistantPrototype ta) {
        // REMOVE THE TA FROM THE LIST OF TAs
        this.teachingAssistants.remove(ta);
        if(ta.getType().equals("Undergraduate")){
            this.underGradTAS.remove(ta);
        }else{
            this.gradTAS.remove(ta);
        }
        // AND REMOVE THE TA FROM ALL THEIR OFFICE HOURS
    }
    public void cutTA(TeachingAssistantPrototype ta ){
        removeTA(ta);
        Iterator<TimeSlot> timeSlotsIterator = officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            timeSlot.cutTATimeslot(ta);
        }
        refreshOH();
    }
    public void cutTARestore(TeachingAssistantPrototype ta){
        addTA(ta);
        Iterator<TimeSlot> timeSlotsIterator = officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            timeSlot.cutTATimesoltRestore(ta);
        }
        refreshOH();
    }
    public void pasteTA(TeachingAssistantPrototype ta){
        
    }
    public boolean isDayOfWeekColumn(int columnNumber) {
        return columnNumber >= 2;
    }
    
    public DayOfWeek getColumnDayOfWeek(int columnNumber) {
        return TimeSlot.DayOfWeek.values()[columnNumber-2];
    }
    public Iterator<TeachingAssistantPrototype> undergraduateIterator() {
        return underGradTAS.iterator();
    }
    public Iterator<TeachingAssistantPrototype> graduateIterator() {
        return gradTAS.iterator();
    }
    public Iterator<TeachingAssistantPrototype> teachingAssistantsIterator() {
        return teachingAssistants.iterator();
    }
    
    public Iterator<TimeSlot> officeHoursIterator() {
        return officeHours.iterator();
    }

    public TeachingAssistantPrototype getTAWithName(String name) {
        Iterator<TeachingAssistantPrototype> taIterator = teachingAssistants.iterator();
        while (taIterator.hasNext()) {
            TeachingAssistantPrototype ta = taIterator.next();
            if (ta.getName().toUpperCase().trim().equals(name.toUpperCase()))
                return ta;
        }
        return null;
    }

    public TimeSlot getTimeSlot(String startTime) {
        Iterator<TimeSlot> timeSlotsIterator = officeHours.iterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            String timeSlotStartTime = timeSlot.getStartTime().replace(":", "_");
            if (timeSlotStartTime.equals(startTime))
                return timeSlot;
        }
        return null;
    }
    public  void editTA(TeachingAssistantPrototype ta,String newName,String newEmail,String newType){
        if (ta.getType().equals("Undergraduate")&&!newType.equals("Undergraduate")) {
            underGradTAS.remove(ta);
            gradTAS.add(ta);
            
        }else if(ta.getType().equals("Graduate")&&!newType.equals("Graduate")){
            gradTAS.remove(ta);
            underGradTAS.add(ta);
        }
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        Iterator<TimeSlot> timeSlotsIterator = data.officeHoursIterator();
          while (timeSlotsIterator.hasNext()) {
             TimeSlot timeSlot = timeSlotsIterator.next();
             timeSlot.editTATimesolot(ta, newType);
           }
            ta.setEmail(newEmail);
            ta.setName(newName);
            ta.setType(newType);
            refreshOH();
    }
    public void refreshOH(){
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
            if (((RadioButton)gui.getGUINode(OH_TOGGLE_ALL)).isSelected()) {
                Iterator<TimeSlot> timeSlotsIterator = data.officeHoursIterator();
                while (timeSlotsIterator.hasNext()) {
                    TimeSlot timeSlot = timeSlotsIterator.next();
                    timeSlot.setToAll();
                }
            }else if(((RadioButton)gui.getGUINode(OH_TOGGLE_UNDERGRADUATE)).isSelected()){
                Iterator<TimeSlot> timeSlotsIterator = data.officeHoursIterator();
                while (timeSlotsIterator.hasNext()) {
                    TimeSlot timeSlot = timeSlotsIterator.next();
                    timeSlot.setToUndergrad();
                }
            }else if(((RadioButton)gui.getGUINode(OH_TOGGLE_GRADUATE)).isSelected()){
                Iterator<TimeSlot> timeSlotsIterator = data.officeHoursIterator();
                while (timeSlotsIterator.hasNext()) {
                    TimeSlot timeSlot = timeSlotsIterator.next();
                    timeSlot.setToGrad();
                }
            }
    }
    public ObservableList<TeachingAssistantPrototype> getAllTAS(){
        return teachingAssistants;
    }
    public ObservableList<TeachingAssistantPrototype> getUndergradTAS(){
        return underGradTAS;
    }
    public ObservableList<TeachingAssistantPrototype> getGradTAS(){
         
        return gradTAS;
    }
}