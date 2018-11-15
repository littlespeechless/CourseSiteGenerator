/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGenerateApp;
import static csg.CourseSitePropertyType.MT_LAB_TABLEVIEW;
import static csg.CourseSitePropertyType.MT_LECTURE_TABLEVIEW;
import static csg.CourseSitePropertyType.MT_RECITATION_TABLEVIEW;
import static csg.CourseSitePropertyType.OH_END_TIME_COMBO_BOX;
import static csg.CourseSitePropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSitePropertyType.OH_START_TIME_COMBO_BOX;
import static csg.CourseSitePropertyType.OH_TAS_TABLE_VIEW;
import static csg.CourseSitePropertyType.OH_TOGGLE_ALL;
import static csg.CourseSitePropertyType.OH_TOGGLE_GRADUATE;
import static csg.CourseSitePropertyType.OH_TOGGLE_UNDERGRADUATE;
import static csg.CourseSitePropertyType.SC_SCHEDULE_TABLEVIEW;
import static csg.CourseSitePropertyType.SITE_SEMESTER_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_SUBJECT_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_YEAR_COMBO_BOX;
import csg.data.TimeSlot.DayOfWeek;
import static djf.AppPropertyType.SAVE_BUTTON;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;

/**
 *
 * @author zhengyu
 */
public class CourseSiteData implements AppDataComponent{
    CourseSiteGenerateApp app;
     // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<TeachingAssistantPrototype> teachingAssistants;
    ObservableList<TeachingAssistantPrototype> underGradTAS;
    ObservableList<TeachingAssistantPrototype> gradTAS; 

    ObservableList<TimeSlot> officeHours;
    ObservableList<TimeSlot> changeableOH;
    ObservableList<TimeSlot> undergradOH;
    ObservableList<TimeSlot> gradOH;
    
    ObservableList<Lecture> lectures;
    ObservableList<Recitation> recitations;
    ObservableList<Lab> labs;
    ObservableList<Schedule> schedules;
    

    // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
    // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
    // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
    // NO MEANS FOR CHANGING THESE VALUES
    int startHour;
    int endHour;
    
    // DEFAULT VALUES FOR START AND END HOURS IN MILITARY HOURS
    public static final int MIN_START_HOUR = 9;
    public static final int MAX_END_HOUR = 21;
    public CourseSiteData(CourseSiteGenerateApp initApp){
        app = initApp;
         AppGUIModule gui = app.getGUIModule();

        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
        TableView<Lecture> lectureTable = (TableView)gui.getGUINode(MT_LECTURE_TABLEVIEW);
        TableView<Recitation> reciationTable = (TableView)gui.getGUINode(MT_RECITATION_TABLEVIEW);
        TableView<Lab> labTable = (TableView)gui.getGUINode(MT_LAB_TABLEVIEW);
        TableView<Schedule> scheduleTable = (TableView)gui.getGUINode(SC_SCHEDULE_TABLEVIEW);
        lectures = lectureTable.getItems();
        recitations = reciationTable.getItems();
        labs = labTable.getItems();
        schedules = scheduleTable.getItems();
        
        teachingAssistants = taTableView.getItems();
        underGradTAS = new TableView<TeachingAssistantPrototype>().getItems();
        gradTAS = new TableView<TeachingAssistantPrototype>().getItems();
        // THESE ARE THE DEFAULT OFFICE HOURS
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        addBannerCombobox();
        addTimeRange();
        resetOfficeHours();
    }

    @Override
    public void reset() {
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        underGradTAS.clear();
        gradTAS.clear();
        labs.clear();
        recitations.clear();
        lectures.clear();
        schedules.clear();
        addTimeRange();
        resetOfficeHours();
        /*
        for (int i = 0; i <officeHours.size(); i++) {
            TimeSlot timeSlot = officeHours.get(i);
            timeSlot.reset();
        }
       
        for(int i = MIN_START_HOUR;i<MAX_END_HOUR;i++){
            TimeSlot timeSlot = officeHours.get(i);
            timeSlot.reset();
        }*/
        
        
    }

/****************************
 * SITE  RELATED METHODS
 */
    public void addBannerCombobox(){
        AppGUIModule gui = app.getGUIModule();
        ComboBox subject = (ComboBox)gui.getGUINode(SITE_SUBJECT_COMBO_BOX);
        subject.getItems().add("CSE");
        subject.getItems().add("IEE");
        ComboBox year = (ComboBox)gui.getGUINode(SITE_YEAR_COMBO_BOX);
        int currentYear =  Calendar.getInstance().get(Calendar.YEAR);
        year.getItems().add(currentYear);
        year.getItems().add(currentYear+1);
        ComboBox semester = (ComboBox)gui.getGUINode(SITE_SEMESTER_COMBO_BOX);
        semester.getItems().addAll("Spring","Summer","Fall","Winter");
        
    }
/****************************
 * OH RELATED METHODS
 */
    public void resetOfficeHours(){
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView)gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHours = officeHoursTableView.getItems(); 
        officeHours.clear();
        undergradOH = new TableView<TimeSlot>().getItems();
        gradOH = new TableView<TimeSlot>().getItems();
        changeableOH = new TableView<TimeSlot>().getItems();
        
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
    public void addTimeRange(){
        AppGUIModule gui = app.getGUIModule();
        ComboBox startTime = (ComboBox)gui.getGUINode(OH_START_TIME_COMBO_BOX);
        ComboBox endTime = (ComboBox)gui.getGUINode(OH_END_TIME_COMBO_BOX);
        for (int i = startHour;i<endHour;i++){
            if (i<12) {
                startTime.getItems().add(i+":00am");
            }else if (i==12){
                startTime.getItems().add(i+":00pm");
            }else{
                startTime.getItems().add(i-12+":00pm");
            }
        }
        for (int i = startHour+1;i<=endHour+1;i++){
            if (i<12) {
                endTime.getItems().add(i+":00am");
            }else if (i==12){
                endTime.getItems().add(i+":00pm");
            }else{
                endTime.getItems().add(i-12+":00pm");
            }
        }
        startTime.setValue(startTime.getItems().get(0));
        endTime.setValue(endTime.getItems().get(endTime.getItems().size()-1));
    }
    public ObservableList<TimeSlot> changeOHTable(String startTime, String endTime,ObservableList<TimeSlot> table){
        changeableOH = new TableView<TimeSlot>().getItems();
        
        int start = getStartTimeslotIndex(startTime);
        int end =  getEndTimeslotIndex(endTime);
        int index = 0;
        
        Iterator<TimeSlot> timeSlotsIterator = officeHours.iterator();
        while (timeSlotsIterator.hasNext()){
            TimeSlot slot = timeSlotsIterator.next();
            if (index>=start&&index<=end) {
                changeableOH.add(slot);
            }
            index++;
        }
        return changeableOH;
    }
    public int getStartTimeslotIndex(String timeString){
        Iterator<TimeSlot> timeSlotsIterator = officeHours.iterator();
        int index = 0;
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            String timeSlotStartTime = timeSlot.getStartTime();
            if (timeSlotStartTime.equals(timeString)){
                return index;
            }   
            index++;
        }
        return -1;
    }
    public int getEndTimeslotIndex(String timeString){
        Iterator<TimeSlot> timeSlotsIterator = officeHours.iterator();
        int index = 0;
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            String timeSlotEndTime = timeSlot.getEndTime();
            if (timeSlotEndTime.equals(timeString)){
                return index;
            }   
            index++;
        }
        return -1;
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
 /****************************
 * MEETING TIME  RELATED METHODS
 */
    public boolean  isLectureSelected(){
        AppGUIModule gui = app.getGUIModule();
        TableView lectureTable = (TableView)gui.getGUINode(MT_LECTURE_TABLEVIEW);
        return lectureTable.getSelectionModel().getSelectedItem() != null;
    }
    public boolean  isRecitationSelected(){
        AppGUIModule gui = app.getGUIModule();
        TableView reciationTable = (TableView)gui.getGUINode(MT_RECITATION_TABLEVIEW);
        return reciationTable.getSelectionModel().getSelectedItem() != null;
    }
    public boolean  isLabSelected(){
        AppGUIModule gui = app.getGUIModule();
        TableView labTable = (TableView)gui.getGUINode(MT_LAB_TABLEVIEW);
        return labTable.getSelectionModel().getSelectedItem() != null;
    }
    public void addLecture(Lecture lecture ){
        if (!this.lectures.contains(lecture)) {
            this.lectures.add(lecture);
        }
    }
    public void removeLecture(Lecture lecture){
        this.lectures.remove(lecture);
    }
    public void editLecture(String newSection, String newDays, 
            String newTime, String newRoom, Lecture lecture){
        lecture.setSection(newSection);
        lecture.setDays(newDays);
        lecture.setTime(newTime);
        lecture.setRoom(newRoom);
    }
    public void addRecitation(Recitation recitation ){
        if (!this.recitations.contains(recitation)) {
            this.recitations.add(recitation);
        }
    }
    public void removeRecitation(Recitation recitation){
        this.recitations.remove(recitation);
    }
    public void editRecitation(String newSection, String newDayTime, 
            String newRoom, String TA1,String TA2,Recitation recitation){
        recitation.setSection(newSection);
        recitation.setDaysTime(newDayTime);
        recitation.setRoom(newRoom);
        recitation.setTa1(TA1);
        recitation.setTa2(TA2);
    }
    public void addLab(Lab lab ){
        if (!this.labs.contains(lab)) {
            this.labs.add(lab);
        }
    }
    public void removeLab(Lab lab){
        this.labs.remove(lab);
    }
    public void editLab(String newSection, String newDayTime, 
            String newRoom, String TA1,String TA2,Lab lab){
        lab.setSection(newSection);
        lab.setDaysTime(newDayTime);
        lab.setRoom(newRoom);
        lab.setTa1(TA1);
        lab.setTa2(TA2);
    }
 /****************************
 * SCHEDULE  RELATED METHODS
 */
    public boolean isScheduleSelected(){
        AppGUIModule gui = app.getGUIModule();
        TableView scheduleTable = (TableView)gui.getGUINode(SC_SCHEDULE_TABLEVIEW);
        return scheduleTable.getSelectionModel().getSelectedItem() != null;
    }
    public void addSchedule(Schedule schedule){
        if (!schedules.contains(schedule)) {
            schedules.add(schedule);
        }
    }
    public void removeSchedule(Schedule schedule){
        schedules.remove(schedule);
    }
    public void editSchedule(String newType, String newDate, String newTitle,
            String newTopic, String newLink, Schedule schedule){
        schedule.setType(newType);
        schedule.setDate(newDate);
        schedule.setTitle(newTitle);
        schedule.setTopic(newTopic);
        schedule.setLink(newLink);
    }
// ACCESSOR METHODS

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
    public ObservableList<TimeSlot> getChangeableOH(){
        return changeableOH;
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
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
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
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
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
