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
import static csg.CourseSitePropertyType.SC_ADD_ITEM_BUTTON;
import static csg.CourseSitePropertyType.SC_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_END_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_LINK_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_SCHEDULE_TABLEVIEW;
import static csg.CourseSitePropertyType.SC_START_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_TITLE_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_TOPIC_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_TYPE_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_CSS_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_EXPORT_DIR;
import static csg.CourseSitePropertyType.SITE_FAVICON_IMG;
import static csg.CourseSitePropertyType.SITE_HOME;
import static csg.CourseSitePropertyType.SITE_HWS;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_EMAIL_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_NAME_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_OH_TEXT_AREA;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_ROOM_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_LEFT_FOOTER_IMG;
import static csg.CourseSitePropertyType.SITE_NAVBAR_IMG;
import static csg.CourseSitePropertyType.SITE_NUMBER_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_RIGHT_FOOTER_IMG;
import static csg.CourseSitePropertyType.SITE_SCHEDULE;
import static csg.CourseSitePropertyType.SITE_SEMESTER_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_SUBJECT_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_SYLLABUS;
import static csg.CourseSitePropertyType.SITE_TITLE_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_YEAR_COMBO_BOX;
import static csg.CourseSitePropertyType.SY_ACADEMIC_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_DESCRIPTION_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_GRADED_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_GRADING_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_OUTCOMES_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_PREREQUIREMENT_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_SPECIAL_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_TEXTBOOK_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_TOPIC_TEXT_AREA;
import csg.data.TimeSlot.DayOfWeek;
import csg.workspace.CourseSiteWorkspace;
import static djf.AppPropertyType.SAVE_BUTTON;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author zhengyu
 */
public class CourseSiteData implements AppDataComponent{
    CourseSiteGenerateApp app;
     // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<String> subjects;
    ObservableList<String> numbers;
    
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
    public static final int MIN_START_HOUR = 8;
    public static final int MAX_END_HOUR = 23;
    public CourseSiteData(CourseSiteGenerateApp initApp){
        app = initApp;
         AppGUIModule gui = app.getGUIModule();
         
        ComboBox subject = (ComboBox)gui.getGUINode(SITE_SUBJECT_COMBO_BOX);
        ComboBox number = (ComboBox)gui.getGUINode(SITE_NUMBER_COMBO_BOX);
        subjects = subject.getItems();
        numbers = number.getItems();
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
        //banner loading
        initalizeBannerCombobox();
        //time rangebuilding
        resetOfficeHours();
        ComboBox css = (ComboBox)gui.getGUINode(SITE_CSS_COMBO_BOX);
        css.setOnAction(null);
        initalizeCssComboBox();
        //loading css files
        css.setValue(css.getItems().get(0));
        CourseSiteWorkspace workspace = (CourseSiteWorkspace) app.getWorkspaceComponent();
        workspace.getController().setOldCss("");
        initializeType();
    }

    @Override
    public void reset() {
        AppGUIModule gui = app.getGUIModule();
        //reset site pane
        ((ComboBox) gui.getGUINode(SITE_SUBJECT_COMBO_BOX)).setValue("");
        ((ComboBox) gui.getGUINode(SITE_NUMBER_COMBO_BOX)).setValue("");
        ((ComboBox) gui.getGUINode(SITE_YEAR_COMBO_BOX)).setValue("");
        ((ComboBox) gui.getGUINode(SITE_SEMESTER_COMBO_BOX)).setValue("");
        ((TextField) gui.getGUINode(SITE_TITLE_TEXT_FIELD)).setText("");
        ((Label) gui.getGUINode(SITE_EXPORT_DIR)).setText(".\\export\\subject_number_semester_year\\public_html");
        //page
        ((CheckBox) gui.getGUINode(SITE_HOME)).setSelected(false);
        ((CheckBox)gui.getGUINode(SITE_SYLLABUS)).setSelected(false);
        ((CheckBox)gui.getGUINode(SITE_SCHEDULE)).setSelected(false);
        ((CheckBox)gui.getGUINode(SITE_HWS)).setSelected(false);
        //style
        initalizeCssComboBox();
        Image image = new Image("file:./images/LoadImage.png");
        ImageView faviconImageView = (ImageView) gui.getGUINode(SITE_FAVICON_IMG);
        ImageView navbarImageView = (ImageView) gui.getGUINode(SITE_NAVBAR_IMG);
        ImageView leftFooterImageView = (ImageView) gui.getGUINode(SITE_LEFT_FOOTER_IMG);
        ImageView rightFooterImageView = (ImageView) gui.getGUINode(SITE_RIGHT_FOOTER_IMG);
        faviconImageView.setImage(image);
        navbarImageView.setImage(image);
        leftFooterImageView.setImage(image);
        rightFooterImageView.setImage(image);
        ComboBox css = (ComboBox)gui.getGUINode(SITE_CSS_COMBO_BOX);
        css.setValue(css.getItems().get(0));
        CourseSiteWorkspace workspace = (CourseSiteWorkspace) app.getWorkspaceComponent();
        workspace.getController().setOldCss("");
        //instructor
        ((TextField) gui.getGUINode(SITE_INSTRUCTOR_NAME_TEXT_FIELD)).setText("");
        ((TextField) gui.getGUINode(SITE_INSTRUCTOR_EMAIL_TEXT_FIELD)).setText("");
        ((TextField) gui.getGUINode(SITE_INSTRUCTOR_ROOM_TEXT_FIELD)).setText("");
        ((TextField) gui.getGUINode(SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD)).setText("");
        ((TextArea) gui.getGUINode(SITE_INSTRUCTOR_OH_TEXT_AREA)).setText("");
        
        //reste syllabus
        ((TextArea) gui.getGUINode(SY_DESCRIPTION_TEXT_AREA)).setText("");
        ((TextArea) gui.getGUINode(SY_TOPIC_TEXT_AREA)).setText("");
        ((TextArea) gui.getGUINode(SY_PREREQUIREMENT_TEXT_AREA)).setText("");
        ((TextArea) gui.getGUINode(SY_OUTCOMES_TEXT_AREA)).setText("");
        ((TextArea) gui.getGUINode(SY_TEXTBOOK_TEXT_AREA)).setText("");
        ((TextArea) gui.getGUINode(SY_GRADED_TEXT_AREA)).setText("");
        ((TextArea) gui.getGUINode(SY_GRADING_TEXT_AREA)).setText("");
        ((TextArea) gui.getGUINode(SY_ACADEMIC_TEXT_AREA)).setText("");
        ((TextArea) gui.getGUINode(SY_SPECIAL_TEXT_AREA)).setText("");
        
        //reset meeting time
        labs.clear();
        recitations.clear();
        lectures.clear();
        //reset OH
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        underGradTAS.clear();
        gradTAS.clear();
        resetOfficeHours();
        //reset schedule pane
        schedules.clear();
        ((DatePicker)gui.getGUINode(SC_START_DATE_DATE_PICKER)).setValue(null);
        ((DatePicker)gui.getGUINode(SC_END_DATE_DATE_PICKER)).setValue(null);
        ComboBox type = (ComboBox) gui.getGUINode(SC_TYPE_COMBO_BOX);
            DatePicker date = (DatePicker) gui.getGUINode(SC_DATE_DATE_PICKER);
            TextField title = (TextField)gui.getGUINode(SC_TITLE_TEXT_FIELD);
            TextField topic = (TextField)gui.getGUINode(SC_TOPIC_TEXT_FIELD);
            TextField link = (TextField)gui.getGUINode(SC_LINK_TEXT_FIELD);
            type.setValue(null);
            date.setValue(null);
            title.setText("");
            topic.setText("");
            link.setText("");
            ((Button) gui.getGUINode(SC_ADD_ITEM_BUTTON)).setText("Add");
        
        app.getTPS().clearAllTransactions();
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
    public void initalizeBannerCombobox() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox year = (ComboBox)gui.getGUINode(SITE_YEAR_COMBO_BOX);
        int currentYear =  Calendar.getInstance().get(Calendar.YEAR);
        year.getItems().add(currentYear);
        year.getItems().add(currentYear+1);
        //year.setValue(currentYear);
        ComboBox semester = (ComboBox)gui.getGUINode(SITE_SEMESTER_COMBO_BOX);
        semester.getItems().addAll("Spring","Summer","Fall","Winter");
        //semester.setValue("Fall");
        try{
            File subjectFile = new File("./app_data/properties/subject.txt");
            if (!subjectFile.exists()) {
                subjectFile.createNewFile();
            }else{
                BufferedReader br = new BufferedReader(new FileReader(subjectFile));
                String st;
                while((st=br.readLine())!=null){
                    if(!subjects.equals(""))
                        subjects.add(st);
                }
            }
            File numberFile = new File("./app_data/properties/number.txt");
            if (!numberFile.exists()) {
                subjectFile.createNewFile();
            }else{
                BufferedReader br = new BufferedReader(new FileReader(numberFile));
                String st;
                while((st=br.readLine())!=null){
                    if (!numbers.equals("")) {
                        numbers.add(st);
                    }
                    
                }
            }
        }catch(Exception e){
            
        }
        
    }
    public void initalizeCssComboBox(){
        AppGUIModule gui = app.getGUIModule();
        ComboBox css = (ComboBox)gui.getGUINode(SITE_CSS_COMBO_BOX);
       
        File cssFolder = new File("work/css/");
        if (cssFolder.list().length>0) {
            for (File cssFile:cssFolder.listFiles()){
            if (!css.getItems().contains(cssFile.getName())&&cssFile.getName().contains(".css")) {
                css.getItems().add(cssFile.getName());
                }           
            }
        }else{
            css.setValue("");
        }
        
    }
    
    public void addSubject(String subject){
        if (!subjects.contains(subject)) {
            if (!subject.equals("")) {
                subjects.add(subject);
                try{
                    File subjectFile = new File("./app_data/properties/subject.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(subjectFile, true));
                    writer.append(subject+"\n");
                    writer.close();
                }catch (Exception e){

                }
            }
            
        }
    }
    public void addNumber(String number){
        if(!numbers.contains(number)){
            if(!number.equals(""))
            {
                numbers.add(number);
                try{
                    File numberFile = new File("./app_data/properties/number.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(numberFile, true));
                    writer.append(number+"\n");
                    writer.close();
                }catch(Exception e){

                }
            }
        }
    }
/****************************
 * OH RELATED METHODS
 */
    public void resetOfficeHours(){
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView)gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        addTimeRange();
        officeHours = officeHoursTableView.getItems(); 
        officeHours.clear();
        undergradOH = new TableView<TimeSlot>().getItems();
        gradOH = new TableView<TimeSlot>().getItems();
        changeableOH = new TableView<TimeSlot>().getItems();
        
        for (int i = startHour; i < endHour; i++) {
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
        startTime.getItems().clear();
        endTime.getItems().clear();
        for (int i = startHour;i<endHour-1;i++){
            if (i<12) {
                startTime.getItems().add(i+":00am");
            }else if (i==12){
                startTime.getItems().add(i+":00pm");
            }else{
                startTime.getItems().add(i-12+":00pm");
            }
        }
        for (int i = startHour+1;i<endHour+1;i++){
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
        if (startTime!=null&&endTime!=null) {
            updateHours(startTime, endTime);
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
    public void updateHours(String startTime,String endTime){
        if (startTime.contains("pm")) {
            if(startTime.contains("12")){
                startHour = 12;
            }else{
                startHour = Integer.parseInt(startTime.substring(0,startTime.indexOf(":")))+12;
            }
        }else{
            startHour = Integer.parseInt(startTime.substring(0,startTime.indexOf(":")));
        }
        if (endTime.contains("pm")) {
            if(endTime.contains("12")){
                endHour = 12;
            }else{
                endHour = Integer.parseInt(endTime.substring(0,endTime.indexOf(":")))+12;
            }
        }else{
            endHour = Integer.parseInt(endTime.substring(0,endTime.indexOf(":")));
        }
        
        
    }
    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if (initStartHour < initEndHour) {
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
    public void initializeType(){
        AppGUIModule gui = app.getGUIModule();
        ComboBox type = (ComboBox) gui.getGUINode(SC_TYPE_COMBO_BOX);
        type.getItems().addAll("Holiday","Lecture","HW","Recitation","Lab","Reference");
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
    public Iterator<Lecture> lecturesIterator() {
        return lectures.iterator();
    }
    public Iterator<Recitation> recitationsIterator() {
        return recitations.iterator();
    }
    public Iterator<Lab> labsIterator() {
        return labs.iterator();
    }
    public Iterator<Schedule> schedulesIterator() {
        return schedules.iterator();
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
