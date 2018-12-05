/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.files;

import csg.CourseSiteGenerateApp;
import static csg.CourseSitePropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSitePropertyType.OH_TAS_TABLE_VIEW;
import static csg.CourseSitePropertyType.SC_END_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_START_DATE_DATE_PICKER;
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
import csg.data.CourseSiteData;
import csg.data.Lab;
import csg.data.Lecture;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import csg.workspace.CourseSiteWorkspace;
import static djf.AppPropertyType.EXPORT_BUTTON;
import static djf.AppPropertyType.SAVE_BUTTON;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppWebDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;


/**
 *
 * @author zhengyu
 */
public class CourseSiteFiles implements  AppFileComponent{
    CourseSiteGenerateApp app;
    
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_GRAD_TAS = "grad_tas";
    static final String JSON_NAME = "name";
    static final String JSON_EMAIL= "email";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_START_TIME = "time";
    static final String JSON_DAY_OF_WEEK = "day";
    static final String JSON_MONDAY = "monday";
    static final String JSON_TUESDAY = "tuesday";
    static final String JSON_WEDNESDAY = "wednesday";
    static final String JSON_THURSDAY = "thursday";
    static final String JSON_FRIDAY = "friday";
    public CourseSiteFiles(CourseSiteGenerateApp initApp){
        app = initApp;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        CourseSiteData dataManager = (CourseSiteData) data;
        AppGUIModule gui = app.getGUIModule();
        //create Site pane object
        String subjectText = (String) ((ComboBox) gui.getGUINode(SITE_SUBJECT_COMBO_BOX)).getEditor().getText();
        String numberText = (String) ((ComboBox) gui.getGUINode(SITE_NUMBER_COMBO_BOX)).getEditor().getText();
        String yearText = (String) ((ComboBox) gui.getGUINode(SITE_YEAR_COMBO_BOX)).getEditor().getText();
        String semesterText = (String) ((ComboBox) gui.getGUINode(SITE_SEMESTER_COMBO_BOX)).getEditor().getText();
        String titleText = (String) ((TextField) gui.getGUINode(SITE_TITLE_TEXT_FIELD)).getText();
        
        //image objects
        ImageView faviconImageView = (ImageView) gui.getGUINode(SITE_FAVICON_IMG);
        ImageView navbarImageView = (ImageView) gui.getGUINode(SITE_NAVBAR_IMG);
        ImageView leftFooterImageView = (ImageView) gui.getGUINode(SITE_LEFT_FOOTER_IMG);
        ImageView rightFooterImageView = (ImageView) gui.getGUINode(SITE_RIGHT_FOOTER_IMG);
        String faviconPath = URLDecoder.decode(faviconImageView.getImage().impl_getUrl().replace("file:", ""), "UTF-8");
        String navbarPath = URLDecoder.decode(navbarImageView.getImage().impl_getUrl().replace("file:", ""), "UTF-8");
        String leftFooterPath = URLDecoder.decode(leftFooterImageView.getImage().impl_getUrl().replace("file:", ""), "UTF-8");
        String rightFooterPath = URLDecoder.decode(rightFooterImageView.getImage().impl_getUrl().replace("file:", ""), "UTF-8");
        ComboBox cssComboBox = (ComboBox)gui.getGUINode(SITE_CSS_COMBO_BOX);
        String cssName = "";
        if (cssComboBox.getValue()!=null) {
            cssName = (String) cssComboBox.getValue();
        }
        //create each individual object
        JsonObject favicon = Json.createObjectBuilder().add("href", faviconPath).build();
        JsonObject navbar = Json.createObjectBuilder().add("src", navbarPath).build();
        JsonObject leftFooter = Json.createObjectBuilder().add("src", leftFooterPath).build();
        JsonObject rightFooter = Json.createObjectBuilder().add("src", rightFooterPath).build();
        //create logo object and puts all img object into it
        JsonObject logoObject = Json.createObjectBuilder().add("favicon",favicon)
                .add("navbar",navbar).add("bottom_left",leftFooter).add("bottom_right",rightFooter).build();
        
        //instructor
        String insNameText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_NAME_TEXT_FIELD)).getText();
        String insEmailText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_EMAIL_TEXT_FIELD)).getText();
        String insRoomText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_ROOM_TEXT_FIELD)).getText();
        String insHomepageText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD)).getText();
        String insOHText = ((TextArea) gui.getGUINode(SITE_INSTRUCTOR_OH_TEXT_AREA)).getText();
        //build each object
        /*JsonObject insName = Json.createObjectBuilder().add("name",insNameText).build();
        JsonObject insEmail = Json.createObjectBuilder().add("email",insEmailText).build();
        JsonObject insRoom = Json.createObjectBuilder().add("room",insRoomText).build();
        JsonObject insLink = Json.createObjectBuilder().add("link",insHomepageText).build();
        JsonObject insPhoto = Json.createObjectBuilder().add("photo","./images/"+insNameText.replaceAll(" ", "")+".jpg").build();
        */
        if (insOHText.equals("")) {
            insOHText = "[]";
        }
        JsonReader jsonReader = Json.createReader(new StringReader(insOHText));
        JsonArray insOHArray = jsonReader.readArray();
        jsonReader.close();
        //put them together 
        JsonObject instructorObject = Json.createObjectBuilder().add("name",insNameText)
                .add("link",insHomepageText).add("email",insEmailText).add("room",insRoomText)
                .add("photo","./images/"+insNameText.replaceAll(" ", "")+".jpg")
                .add("hours",insOHArray).build();
        
        //pages
        CheckBox homeBox = (CheckBox) gui.getGUINode(SITE_HOME);
        CheckBox syllabusBox = (CheckBox)gui.getGUINode(SITE_SYLLABUS);
        CheckBox scheduleBox = (CheckBox)gui.getGUINode(SITE_SCHEDULE);
        CheckBox hwsBox = (CheckBox)gui.getGUINode(SITE_HWS);
        JsonArrayBuilder pageArrayBuilder = Json.createArrayBuilder();
        if (homeBox.isSelected()) {
            JsonObject home = Json.createObjectBuilder().add("name","Home")
                    .add("link","index.html").build();
            pageArrayBuilder.add(home);
        }
        if (syllabusBox.isSelected()) {
            JsonObject syllabus = Json.createObjectBuilder().add("name","Syllabus")
                    .add("link","syllabus.html").build();
            pageArrayBuilder.add(syllabus);
        }
        if (scheduleBox.isSelected()) {
            JsonObject schedule = Json.createObjectBuilder().add("name","Schedule")
                    .add("link","schedule.html").build();
            pageArrayBuilder.add(schedule);
        }
        if (hwsBox.isSelected()) {
            JsonObject hws = Json.createObjectBuilder().add("name","HWs")
                    .add("link","hws.html").build();
            pageArrayBuilder.add(hws);
        }
        JsonArray pagesArray = pageArrayBuilder.build();
        //put eveything into one site Object
        
        JsonObject sitePaneObject = Json.createObjectBuilder().add("subject",subjectText).
                add("number", numberText).add("semester", semesterText).add("year", yearText)
                .add("title",titleText).add("logos",logoObject).add("css",cssName)
                .add("instructor",instructorObject).add("pages",pagesArray)
                .build();
        //Syllabus object
        String desText = ((TextArea) gui.getGUINode(SY_DESCRIPTION_TEXT_AREA)).getText();
        String topicAreaText = ((TextArea) gui.getGUINode(SY_TOPIC_TEXT_AREA)).getText();
        String prereqText = ((TextArea) gui.getGUINode(SY_PREREQUIREMENT_TEXT_AREA)).getText();
        String outcomesText = ((TextArea) gui.getGUINode(SY_OUTCOMES_TEXT_AREA)).getText();
        String textbookText = ((TextArea) gui.getGUINode(SY_TEXTBOOK_TEXT_AREA)).getText();
        String gradedText = ((TextArea) gui.getGUINode(SY_GRADED_TEXT_AREA)).getText();
        String gradingText = ((TextArea) gui.getGUINode(SY_GRADING_TEXT_AREA)).getText();
        String academicText = ((TextArea) gui.getGUINode(SY_ACADEMIC_TEXT_AREA)).getText();
        String specialText = ((TextArea) gui.getGUINode(SY_SPECIAL_TEXT_AREA)).getText();
        //creat individual Object
        if (topicAreaText.equals("")) {
            topicAreaText="[]";
        }
        JsonReader topicAreaReader = Json.createReader(new StringReader(topicAreaText));
        JsonArray topicArea= topicAreaReader.readArray();
        topicAreaReader.close();
        if (outcomesText.equals("")) {
            outcomesText="[]";
        }
        JsonReader outcomeReader = Json.createReader(new StringReader(outcomesText));
        JsonArray outcomes = outcomeReader.readArray();
        outcomeReader.close();
        if (textbookText.equals("")) {
            textbookText="[]";
        }
        JsonReader textbookReader = Json.createReader(new StringReader(textbookText));
        JsonArray textbook = textbookReader.readArray();
        textbookReader.close();
        if (gradedText.equals("")) {
            gradedText="[]";
        }
        JsonReader gradedReader = Json.createReader(new StringReader(gradedText));
        JsonArray graded = gradedReader.readArray();
        gradedReader.close();
        
        //put everything in one object
        JsonObject syllabusObject = Json.createObjectBuilder()
                .add("description",desText).add("topics",topicArea)
                .add("prerequisites",prereqText).add("outcomes",outcomes)
                .add("textbooks",textbook).add("gradedComponents",graded)
                .add("gradingNote",gradingText).add("academicDishonesty",academicText).
                add("specialAssistance",specialText).build();
       
        
        //Meeting Time 
        //lecture
        JsonArrayBuilder lectureArrayBuilder = Json.createArrayBuilder();
        Iterator<Lecture> lectureIterator = dataManager.lecturesIterator();
        while(lectureIterator.hasNext()){
            Lecture temp = lectureIterator.next();
            JsonObject object = Json.createObjectBuilder().
                    add("section",temp.getSection()).add("days",temp.getDays())
                    .add("time",temp.getTime()).add("room",temp.getRoom()).build();
            lectureArrayBuilder.add(object);
        }
        JsonArray lectureArray = lectureArrayBuilder.build();
        //recitation
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
        Iterator<Recitation> recitationIterator = dataManager.recitationsIterator();
        while(recitationIterator.hasNext()){
            Recitation temp = recitationIterator.next();
            JsonObject object = Json.createObjectBuilder().add("section",temp.getSection())
                    .add("day_time",temp.getDaysTime()).add("location",temp.getRoom())
                    .add("ta_1",temp.getTa1()).add("ta_2",temp.getTa2()).build();
            recitationArrayBuilder.add(object);
        }
        JsonArray recitationArray = recitationArrayBuilder.build();
        //lab
        JsonArrayBuilder labArrayBuilder = Json.createArrayBuilder();
        Iterator<Lab> labIterator = dataManager.labsIterator();
        while(labIterator.hasNext()){
            Lab temp = labIterator.next();
            JsonObject object = Json.createObjectBuilder().add("section",temp.getSection())
                    .add("day_time",temp.getDaysTime()).add("location",temp.getRoom())
                    .add("ta_1",temp.getTa1()).add("ta_2",temp.getTa2()).build();
            labArrayBuilder.add(object);
        }
        JsonArray labArray = labArrayBuilder.build();
        //put everything in one object
        JsonObject meetingTimeObject = Json.createObjectBuilder().add("lectures",lectureArray)
                .add("labs",labArray).add("recitations",recitationArray).build();
        
        //Office Hours
        //Undergrad save 
	JsonArrayBuilder taUndergradArrayBuilder = Json.createArrayBuilder();
	Iterator<TeachingAssistantPrototype> undergradIterator = dataManager.undergraduateIterator();
        while (undergradIterator.hasNext()) {
            TeachingAssistantPrototype ta = undergradIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName()).add(JSON_EMAIL, ta.getEmail()).build();
	    taUndergradArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taUndergradArrayBuilder.build();
        //Grad save
        JsonArrayBuilder taGradArrayBuilder = Json.createArrayBuilder();
	Iterator<TeachingAssistantPrototype> gradIterator = dataManager.graduateIterator();
        while (gradIterator.hasNext()) {
            TeachingAssistantPrototype ta = gradIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName()).add(JSON_EMAIL, ta.getEmail()).build();
	    taGradArrayBuilder.add(taJson);
	}
	JsonArray gradTAsArray = taGradArrayBuilder.build();
        //time slot save
        JsonArrayBuilder taOHbArrayBuilder = Json.createArrayBuilder();
        Iterator<TimeSlot> ohIterator = ((TableView)gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW)).getItems().iterator();
        while(ohIterator.hasNext()){
            TimeSlot timeSlot=ohIterator.next();
            for(int i =2;i<7;i++){
                DayOfWeek dayOfWeek = dataManager.getColumnDayOfWeek(i);
                for(int j=0;j<timeSlot.getTAS(dayOfWeek).size();j++){
                    JsonObject ohJson = Json.createObjectBuilder()
                            .add(JSON_START_TIME, timeSlot.getStartTime().replaceAll(":", "_"))
                            .add(JSON_DAY_OF_WEEK, dayOfWeek.toString())
                            .add(JSON_NAME, timeSlot.getTAS(dayOfWeek).get(j).getName()).build();
                    taOHbArrayBuilder.add(ohJson);
                }
            }    
        }
        JsonArray officeHourArray = taOHbArrayBuilder.build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject officeHourObject = Json.createObjectBuilder()
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_OFFICE_HOURS,officeHourArray)
		.build();
        
        
        //SCHEDULE
        DatePicker startDatePicker = (DatePicker)gui.getGUINode(SC_START_DATE_DATE_PICKER);
        DatePicker endDatePicker = (DatePicker)gui.getGUINode(SC_END_DATE_DATE_PICKER);
        LocalDate startingMonday = startDatePicker.getValue();
        LocalDate endingFriday = endDatePicker.getValue();
        String mondayMonth = "";
        String mondayDay = "";
        String mondayYear = "";
        String fridayMonth = "";
        String fridayDay ="";
        String fridayYear = "";
        if (startingMonday!=null) {
            mondayDay = ""+startingMonday.getDayOfMonth();
            mondayMonth = ""+startingMonday.getMonthValue();
            mondayYear = ""+startingMonday.getYear();
        }
        if (endingFriday!=null) {
            fridayDay = ""+endingFriday.getDayOfMonth();
            fridayMonth =""+endingFriday.getMonthValue();
            fridayYear = ""+endingFriday.getYear();
        }
        
        JsonArrayBuilder holidayArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder scheduleLectureArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder scheduleRecitationArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder scheduleLabArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder hwsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder referenceArrayBuilder = Json.createArrayBuilder();
        
        Iterator<Schedule> scheduleIterator = dataManager.schedulesIterator();
        while(scheduleIterator.hasNext()){
            Schedule temp = scheduleIterator.next();
            String month = temp.getDate().substring(temp.getDate().indexOf("-")+1,temp.getDate().lastIndexOf("-"));
            String year = temp.getDate().substring(0,temp.getDate().indexOf("-"));
            String day = temp.getDate().substring(temp.getDate().lastIndexOf("-")+1);
            switch(temp.getType()){
                case "Holiday":
                    JsonObject holiday = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("link",temp.getLink()).build();
                    holidayArrayBuilder.add(holiday);break;
                case "Lecture":
                    JsonObject lecture = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).build();
                    scheduleLectureArrayBuilder.add(lecture);break;
                case "Reference":
                    JsonObject reference = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).build();
                    referenceArrayBuilder.add(reference);break;
                case "Recitation":
                    JsonObject recitation = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).build();
                    scheduleRecitationArrayBuilder.add(recitation);break;
                case "Lab":
                    JsonObject lab = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).build();
                    scheduleLabArrayBuilder.add(lab);break;
                case "HW":
                    JsonObject hw = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).add("time","")
                            .add("criteria","none").build();
                    hwsArrayBuilder.add(hw);break;
            }
        }
        //BUild arrays 
        JsonArray holidayArray = holidayArrayBuilder.build();
        JsonArray scheduleLectureArray = scheduleLectureArrayBuilder.build();
        JsonArray scheduleRecitationArray = scheduleRecitationArrayBuilder.build();
        JsonArray scheduleLabArray = scheduleLabArrayBuilder.build();
        JsonArray hwArray = hwsArrayBuilder.build();
        JsonArray referenceArray = referenceArrayBuilder.build();
        
        //put together
        JsonObject scheduleObject = Json.createObjectBuilder()
                .add("startingMondayYear",mondayYear).add("startingMondayMonth",mondayMonth)
                .add("startingMondayDay",mondayDay).add("endingFridayYear",fridayYear)
                .add("endingFridayMonth",fridayMonth).add("endingFridayDay",fridayDay)
                .add("holidays",holidayArray).add("lectures",scheduleLectureArray)
                .add("references",referenceArray).add("recitations",scheduleRecitationArray)
                .add("hws",hwArray).add("labs",scheduleLabArray).build();
        
        
        //put all tabs object together
        JsonObject courseSiteGeneratorObject = Json.createObjectBuilder()
                .add("site_pane",sitePaneObject).add("syllabus_pane",syllabusObject)
                .add("meetingTime_pane",meetingTimeObject).add("officeHours_pane",officeHourObject)
                .add("schedule_pane",scheduleObject).build();
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(courseSiteGeneratorObject);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(courseSiteGeneratorObject);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
        
        //update controls
        ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(true);
        ((Button) gui.getGUINode(SAVE_BUTTON)).setDisable(true);
        CheckBox home = (CheckBox) gui.getGUINode(SITE_HOME);
        CheckBox syllabus = (CheckBox)gui.getGUINode(SITE_SYLLABUS);
        CheckBox schedule = (CheckBox)gui.getGUINode(SITE_SCHEDULE);
        CheckBox hws = (CheckBox)gui.getGUINode(SITE_HWS);
        if (home.isSelected()||syllabus.isSelected()||schedule.isSelected()||hws.isSelected()) {
          ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(false);
        }
        app.getFoolproofModule().updateAll();
        
        
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        CourseSiteData dataManager = (CourseSiteData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        //reset everything
        dataManager.reset();

        // load files as officeHoursObject
        JsonObject courseSiteObject = loadJSONFile(filePath);
        
        // laod site pane
        JsonObject sitePaneObject = courseSiteObject.getJsonObject("site_pane");
        // load banner
        String subjectText = sitePaneObject.getString("subject");
        String numberText = sitePaneObject.getString("number");
        String semesterText = sitePaneObject.getString("semester");
        String yearText = sitePaneObject.getString("year");
        String titleText = sitePaneObject.getString("title");
        //put in each ui box
        
        ((ComboBox) gui.getGUINode(SITE_SUBJECT_COMBO_BOX)).setValue(subjectText);
        ((ComboBox) gui.getGUINode(SITE_NUMBER_COMBO_BOX)).setValue(numberText);
        ((ComboBox) gui.getGUINode(SITE_YEAR_COMBO_BOX)).setValue(yearText);
        ((ComboBox) gui.getGUINode(SITE_SEMESTER_COMBO_BOX)).setValue(semesterText);
        ((TextField) gui.getGUINode(SITE_TITLE_TEXT_FIELD)).setText(titleText);
        dataManager.addSubject(subjectText);
        dataManager.addNumber(numberText);
        String dir = ".\\export\\" + subjectText+"_"+numberText+"_"+semesterText+"_"+yearText+"\\public_html";
        ((Label) gui.getGUINode(SITE_EXPORT_DIR)).setText(dir);
        
        //load icons
        JsonObject icons = sitePaneObject.getJsonObject("logos");
        JsonObject favicon = icons.getJsonObject("favicon");
        JsonObject navbar = icons.getJsonObject("navbar");
        JsonObject leftFooter = icons.getJsonObject("bottom_left");
        JsonObject rightFooter = icons.getJsonObject("bottom_right");
        String faviconPath = favicon.getString("href");
        String navbarPath = navbar.getString("src");
        String leftFooterPath = leftFooter.getString("src");
        String rightFooterPath = rightFooter.getString("src");
        File faviconFile = new File(faviconPath);
        File navbarFile = new File (navbarPath);
        File rightFooterFile = new File(rightFooterPath);
        File leftFooterFile = new File(leftFooterPath);
        ImageView faviconImageView = (ImageView) gui.getGUINode(SITE_FAVICON_IMG);
        ImageView navbarImageView = (ImageView) gui.getGUINode(SITE_NAVBAR_IMG);
        ImageView leftFooterImageView = (ImageView) gui.getGUINode(SITE_LEFT_FOOTER_IMG);
        ImageView rightFooterImageView = (ImageView) gui.getGUINode(SITE_RIGHT_FOOTER_IMG);
        if (faviconFile.exists()) {
            Image faviconImage = new Image(faviconFile.toURI().toString());
            faviconImageView.setImage(faviconImage);
        }
        if(navbarFile.exists()){
            Image navbarImage = new Image(navbarFile.toURI().toString());
            navbarImageView.setImage(navbarImage);
        }
        if (leftFooterFile.exists()) {
            Image leftFooterImg = new Image(leftFooterFile.toURI().toString());
            leftFooterImageView.setImage(leftFooterImg);
        }
        if(rightFooterFile.exists()){
            Image rightFooterImg = new Image(rightFooterFile.toURI().toString());
            rightFooterImageView.setImage(rightFooterImg);
        }
        
        String cssFileName = sitePaneObject.getString("css");
        ComboBox cssComboBox = (ComboBox)gui.getGUINode(SITE_CSS_COMBO_BOX);
        if (cssComboBox.getItems().contains(cssFileName)) {
            cssComboBox.setValue(cssFileName);
        }
        CourseSiteWorkspace workspace = (CourseSiteWorkspace) app.getWorkspaceComponent();
        workspace.getController().setOldCss("");
        
        //instructor
        JsonObject instructorObject= sitePaneObject.getJsonObject("instructor");
        String insNameText = instructorObject.getString("name");
        String insHomePageText = instructorObject.getString("link");
        String insEmailText = instructorObject.getString("email");
        String insRoomText = instructorObject.getString("room");
        String insOHText = instructorObject.getJsonArray("hours").toString();
        
        //set value
        ((TextField) gui.getGUINode(SITE_INSTRUCTOR_NAME_TEXT_FIELD)).setText(insNameText);
        ((TextField) gui.getGUINode(SITE_INSTRUCTOR_EMAIL_TEXT_FIELD)).setText(insEmailText);
        ((TextField) gui.getGUINode(SITE_INSTRUCTOR_ROOM_TEXT_FIELD)).setText(insRoomText);
        ((TextField) gui.getGUINode(SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD)).setText(insHomePageText);
        ((TextArea) gui.getGUINode(SITE_INSTRUCTOR_OH_TEXT_AREA)).setText(insOHText);
        
        //pages
        JsonArray pageArray = sitePaneObject.getJsonArray("pages");
        CheckBox homeBox = (CheckBox) gui.getGUINode(SITE_HOME);
        CheckBox syllabusBox = (CheckBox)gui.getGUINode(SITE_SYLLABUS);
        CheckBox scheduleBox = (CheckBox)gui.getGUINode(SITE_SCHEDULE);
        CheckBox hwsBox = (CheckBox)gui.getGUINode(SITE_HWS);
        for (int i = 0; i<pageArray.size();i++){
            JsonObject page = pageArray.getJsonObject(i);
            String name = page.getString("name");
            switch(name){
                case "Home": homeBox.setSelected(true);break;
                case "Syllabus": syllabusBox.setSelected(true);break;
                case "Schedule": scheduleBox.setSelected(true);break;
                case "HWs": hwsBox.setSelected(true);break;
            }
        }
        
        //set Syllbus tab
        JsonObject syllbusObject = courseSiteObject.getJsonObject("syllabus_pane");
        String desText = syllbusObject.getString("description");
        String topicText = syllbusObject.getJsonArray("topics").toString();
        String prere1Text = syllbusObject.getString("prerequisites");
        String outcomeText = syllbusObject.getJsonArray("outcomes").toString();
        String textbookText = syllbusObject.getJsonArray("textbooks").toString();
        String gradedText = syllbusObject.getJsonArray("gradedComponents").toString();
        String gradingText = syllbusObject.getString("gradingNote");
        String academicText = syllbusObject.getString("academicDishonesty");
        String specialText = syllbusObject.getString("specialAssistance");
        
        //set value
        ((TextArea) gui.getGUINode(SY_DESCRIPTION_TEXT_AREA)).setText(desText);
        ((TextArea) gui.getGUINode(SY_TOPIC_TEXT_AREA)).setText(topicText);
        ((TextArea) gui.getGUINode(SY_PREREQUIREMENT_TEXT_AREA)).setText(prere1Text);
        ((TextArea) gui.getGUINode(SY_OUTCOMES_TEXT_AREA)).setText(outcomeText);
        ((TextArea) gui.getGUINode(SY_TEXTBOOK_TEXT_AREA)).setText(textbookText);
        ((TextArea) gui.getGUINode(SY_GRADED_TEXT_AREA)).setText(gradedText);
        ((TextArea) gui.getGUINode(SY_GRADING_TEXT_AREA)).setText(gradingText);
        ((TextArea) gui.getGUINode(SY_ACADEMIC_TEXT_AREA)).setText(academicText);
        ((TextArea) gui.getGUINode(SY_SPECIAL_TEXT_AREA)).setText(specialText);
        
        //meeting time pane
        JsonObject meetingTimeObject = courseSiteObject.getJsonObject("meetingTime_pane");
        //Lectures 
        JsonArray lecturesArray = meetingTimeObject.getJsonArray("lectures");
        for(int i =0; i < lecturesArray.size();i++){
            JsonObject lecture = lecturesArray.getJsonObject(i);
            String section = lecture.getString("section");
            String day = lecture.getString("days");
            String time = lecture.getString("time");
            String room = lecture.getString("room");
            Lecture temp = new Lecture(section, day, time, room);
            dataManager.addLecture(temp);
        }
        //labs:
        JsonArray labsArray = meetingTimeObject.getJsonArray("labs");
        for(int i=0; i<labsArray.size();i++){
            JsonObject object = labsArray.getJsonObject(i);
            String section = object.getString("section");
            String dayTime = object.getString("day_time");
            String room = object.getString("location");
            String ta1 = object.getString("ta_1");
            String ta2 = object.getString("ta_2");
            Lab lab = new Lab(section, dayTime, room, ta1, ta2);
            dataManager.addLab(lab);
        }
        
        //reciations
        JsonArray recitationsArray = meetingTimeObject.getJsonArray("recitations");
        for(int i=0; i<recitationsArray.size();i++){
            JsonObject object = recitationsArray.getJsonObject(i);
            String section = object.getString("section");
            String dayTime = object.getString("day_time");
            String room = object.getString("location");
            String ta1 = object.getString("ta_1");
            String ta2 = object.getString("ta_2");
            Recitation recitation = new Recitation(section, dayTime, room, ta1, ta2);
            dataManager.addRecitation(recitation);
        }
        
        //office Hour
        JsonObject officeHoursObject = courseSiteObject.getJsonObject("officeHours_pane");
        // LOAD THE START AND END HOURS
	String startHour = officeHoursObject.getString(JSON_START_HOUR);
        String endHour = officeHoursObject.getString(JSON_END_HOUR);
        dataManager.initHours(startHour, endHour);

        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = officeHoursObject.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name,email,"Undergraduate");
            dataManager.addTA(ta);
            dataManager.addUnderGradTA(ta);
        }
        //NOW LOAD ALL THE GRAD TAs
        JsonArray jsonTAArray2 = officeHoursObject.getJsonArray(JSON_GRAD_TAS);
        for (int i = 0; i < jsonTAArray2.size(); i++) {
            JsonObject jsonTA = jsonTAArray2.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name,email,"Graduate");
            dataManager.addTA(ta);
            dataManager.addGradTA(ta);
        }
        // TIME SLOT
        JsonArray jsonTAOHArray = officeHoursObject.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i<jsonTAOHArray.size();i++){
            JsonObject jsonTime = jsonTAOHArray.getJsonObject(i);
            String dayString = jsonTime.getString(JSON_DAY_OF_WEEK).toLowerCase();
            String timeString = jsonTime.getString(JSON_START_TIME);
            String nameString = jsonTime.getString(JSON_NAME);
            TimeSlot timeSlot = dataManager.getTimeSlot(timeString);
            DayOfWeek dayOfWeek = null;
            TeachingAssistantPrototype ta = dataManager.getTAWithName(nameString);
            switch(dayString){
                case JSON_MONDAY: dayOfWeek=DayOfWeek.MONDAY;break;
                case JSON_TUESDAY: dayOfWeek=DayOfWeek.TUESDAY;break;
                case JSON_WEDNESDAY: dayOfWeek = DayOfWeek.WEDNESDAY;break;
                case JSON_THURSDAY: dayOfWeek = DayOfWeek.THURSDAY;break;
                case JSON_FRIDAY: dayOfWeek = DayOfWeek.FRIDAY;break;    
            }
            if (ta!=null)
                timeSlot.addTA(dayOfWeek, ta);
        }
        
        //schedules
        JsonObject scheduleObject = courseSiteObject.getJsonObject("schedule_pane");
        String mondayYear = scheduleObject.getString("startingMondayYear");
        String mondayMonth = scheduleObject.getString("startingMondayMonth");
        String mondayDay = scheduleObject.getString("startingMondayDay");
        String fridayYear = scheduleObject.getString("endingFridayYear");
        String fridayMonth = scheduleObject.getString("endingFridayMonth");
        String fridayDay = scheduleObject.getString("endingFridayDay");
        //set value
        DatePicker startDatePicker = (DatePicker)gui.getGUINode(SC_START_DATE_DATE_PICKER);
        DatePicker endDatePicker = (DatePicker)gui.getGUINode(SC_END_DATE_DATE_PICKER);
        if(mondayYear.equals("")||mondayMonth.equals("")||mondayDay.equals("")){
            
        }else{
            LocalDate startMonday = LocalDate.of(Integer.parseInt(mondayYear), 
                    Integer.parseInt(mondayMonth), Integer.parseInt(mondayDay));
            startDatePicker.setValue(startMonday);
        }
        if(fridayYear.equals("")||fridayMonth.equals("")||fridayDay.equals("")){
            
        }else{
            LocalDate endFriday = LocalDate.of(Integer.parseInt(fridayYear), 
                    Integer.parseInt(fridayMonth), Integer.parseInt(fridayDay));
            endDatePicker.setValue(endFriday);
        }
        //holiday
        JsonArray holidayArray = scheduleObject.getJsonArray("holidays");
        for(int i =0;i<holidayArray.size();i++){
            JsonObject object = holidayArray.getJsonObject(i);
            String month = object.getString("month");
            String day = object.getString("day");
            String year = object.getString("year");
            String title = object.getString("title");
            String link = object.getString("link");
            String topic = "";
            String type = "Holiday";
            String date = Integer.parseInt(year)+"-"+Integer.parseInt(month)+"-"+
                    Integer.parseInt(day);
            
            Schedule schedule = new Schedule(type, date, title, topic, link);
            dataManager.addSchedule(schedule);
        }
        //lectures
        JsonArray scheduleLecturesArry = scheduleObject.getJsonArray("lectures");
        for(int i = 0; i<scheduleLecturesArry.size();i++){
            JsonObject object = scheduleLecturesArry.getJsonObject(i);
            String month = object.getString("month");
            String day = object.getString("day");
            String year = object.getString("year");
            String title = object.getString("title");
            String link = object.getString("link");
            String topic = object.getString("topic");
            String type = "Lecture";
            String date = Integer.parseInt(year)+"-"+Integer.parseInt(month)+"-"+
                    Integer.parseInt(day);
            Schedule schedule = new Schedule(type, date, title, topic, link);
            dataManager.addSchedule(schedule);
        }
        
        JsonArray referencesArray = scheduleObject.getJsonArray("references");
        for(int i = 0; i<referencesArray.size();i++){
            JsonObject object = referencesArray.getJsonObject(i);
            String month = object.getString("month");
            String day = object.getString("day");
            String year = object.getString("year");
            String title = object.getString("title");
            String link = object.getString("link");
            String topic = object.getString("topic");
            String type = "Reference";
            String date = Integer.parseInt(year)+"-"+Integer.parseInt(month)+"-"+
                    Integer.parseInt(day);
            Schedule schedule = new Schedule(type, date, title, topic, link);
            dataManager.addSchedule(schedule);
        }
        
        JsonArray scheduleRecitationsArry = scheduleObject.getJsonArray("recitations");
        for(int i = 0; i<scheduleRecitationsArry.size();i++){
            JsonObject object = scheduleRecitationsArry.getJsonObject(i);
            String month = object.getString("month");
            String day = object.getString("day");
            String year = object.getString("year");
            String title = object.getString("title");
            String link = object.getString("link");
            String topic = object.getString("topic");
            String type = "Recitation";
            String date = Integer.parseInt(year)+"-"+Integer.parseInt(month)+"-"+
                    Integer.parseInt(day);
            Schedule schedule = new Schedule(type, date, title, topic, link);
            dataManager.addSchedule(schedule);
        }
        
        JsonArray hwsArray = scheduleObject.getJsonArray("hws");
        for(int i = 0; i<hwsArray.size();i++){
            JsonObject object = hwsArray.getJsonObject(i);
            String month = object.getString("month");
            String day = object.getString("day");
            String year = object.getString("year");
            String title = object.getString("title");
            String link = object.getString("link");
            String topic = object.getString("topic");
            String type = "HW";
            String date = Integer.parseInt(year)+"-"+Integer.parseInt(month)+"-"+
                    Integer.parseInt(day);
            Schedule schedule = new Schedule(type, date, title, topic, link);
            dataManager.addSchedule(schedule);
        }
        
        JsonArray scheduleLabsArray = scheduleObject.getJsonArray("labs");
        for(int i = 0; i<scheduleLabsArray.size();i++){
            JsonObject object = scheduleLabsArray.getJsonObject(i);
            String month = object.getString("month");
            String day = object.getString("day");
            String year = object.getString("year");
            String title = object.getString("title");
            String link = object.getString("link");
            String topic = object.getString("topic");
            String type = "Lab";
            String date = Integer.parseInt(year)+"-"+Integer.parseInt(month)+"-"+
                    Integer.parseInt(day);
            Schedule schedule = new Schedule(type, date, title, topic, link);
            dataManager.addSchedule(schedule);
        }
        
        //update controls
        app.getTPS().clearAllTransactions();
        ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(true);
        CheckBox home = (CheckBox) gui.getGUINode(SITE_HOME);
        CheckBox syllabus = (CheckBox)gui.getGUINode(SITE_SYLLABUS);
        CheckBox schedule = (CheckBox)gui.getGUINode(SITE_SCHEDULE);
        CheckBox hws = (CheckBox)gui.getGUINode(SITE_HWS);
        if (home.isSelected()||syllabus.isSelected()||schedule.isSelected()||hws.isSelected()) {
          ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(false);
        }
        ((Button) gui.getGUINode(SAVE_BUTTON)).setDisable(true);
        //app.getFoolproofModule().updateAll();
        
        
    }
    
     // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        //System.out.println("xxx");
        CourseSiteData dataManager = (CourseSiteData) data;
        AppGUIModule gui = app.getGUIModule();
        String exportPath = ((Label) gui.getGUINode(SITE_EXPORT_DIR)).getText();
        File exportDir = new File(exportPath);
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }else{
            //reset all html files
            for(File htmlFile : exportDir.listFiles()){
                if (!htmlFile.isDirectory()){
                    htmlFile.delete();
                }
            }
        }
        File imageDir = new File(exportPath+"/images");
        if (!imageDir.exists()) {
            imageDir.mkdir();
        }else{
            for(File htmlFile : imageDir.listFiles()){
                if (!htmlFile.isDirectory()){
                    htmlFile.delete();
                }
            }
        }
        File jsDir = new File(exportDir+"/js");
        if (!jsDir.exists()) {
            jsDir.mkdir();
        }else{
            for(File htmlFile : jsDir.listFiles()){
                if (!htmlFile.isDirectory()){
                    htmlFile.delete();
                }
            }
        }
        File cssDir = new File(exportPath+"/css");
        if (!cssDir.exists()) {
            cssDir.mkdir();
        }else{
            for(File htmlFile : cssDir.listFiles()){
                if (!htmlFile.isDirectory()){
                    htmlFile.delete();
                }
            }
        }
        ImageView faviconImageView = (ImageView) gui.getGUINode(SITE_FAVICON_IMG);
        ImageView navbarImageView = (ImageView) gui.getGUINode(SITE_NAVBAR_IMG);
        ImageView leftFooterImageView = (ImageView) gui.getGUINode(SITE_LEFT_FOOTER_IMG);
        ImageView rightFooterImageView = (ImageView) gui.getGUINode(SITE_RIGHT_FOOTER_IMG);
        String faviconPath = URLDecoder.decode(faviconImageView.getImage().impl_getUrl().replace("file:", ""), "UTF-8");
        String navbarPath = URLDecoder.decode(navbarImageView.getImage().impl_getUrl().replace("file:", ""), "UTF-8");
        String leftFooterPath = URLDecoder.decode(leftFooterImageView.getImage().impl_getUrl().replace("file:", ""), "UTF-8");
        String rightFooterPath = URLDecoder.decode(rightFooterImageView.getImage().impl_getUrl().replace("file:", ""), "UTF-8");
        File faviconFile = new File(faviconPath);
        File navbarFile = new File (navbarPath);
        File rightFooterFile = new File(rightFooterPath);
        File leftFooterFile = new File(leftFooterPath);
        ComboBox cssComboBox = (ComboBox)gui.getGUINode(SITE_CSS_COMBO_BOX);
        File cssFile = new File ("./work/css/"+cssComboBox.getValue().toString());
        // copy all files to export dir
        try{
           Files.copy(faviconFile.toPath(),Paths.get(exportDir+"/images/"+faviconFile.getName())
                   ,StandardCopyOption.REPLACE_EXISTING);
           Files.copy(navbarFile.toPath(),Paths.get(exportDir+"/images/"+navbarFile.getName())
                   ,StandardCopyOption.REPLACE_EXISTING);
           Files.copy(rightFooterFile.toPath(),Paths.get(exportDir+"/images/"+rightFooterFile.getName())
                   ,StandardCopyOption.REPLACE_EXISTING);
           Files.copy(leftFooterFile.toPath(),Paths.get(exportDir+"/images/"+leftFooterFile.getName())
                   ,StandardCopyOption.REPLACE_EXISTING);
           Files.copy(cssFile.toPath(),Paths.get(exportDir+"/css/"+cssFile.getName())
                   ,StandardCopyOption.REPLACE_EXISTING);
           File jsFolder = new File("./work/js");
           
           for (File jsFile:jsFolder.listFiles()){
               Files.copy(jsFile.toPath(),Paths.get(exportDir+"/js/"+jsFile.getName())
                   ,StandardCopyOption.REPLACE_EXISTING);
           }
            File defaultCssFile =  new File(exportDir+"/css/course_homepage_layout.css");
            BufferedWriter writer = new BufferedWriter(new FileWriter(defaultCssFile, false));
            writer.append(defaultCss());
            writer.close();
           
        }catch(Exception ex){
            
        }
        String finalDispalyPath = null;
        
        CheckBox homeBox = (CheckBox) gui.getGUINode(SITE_HOME);
        CheckBox syllabusBox = (CheckBox)gui.getGUINode(SITE_SYLLABUS);
        CheckBox scheduleBox = (CheckBox)gui.getGUINode(SITE_SCHEDULE);
        CheckBox hwsBox = (CheckBox)gui.getGUINode(SITE_HWS);
        
        //pages data is used in every page so create once
        buildHomePageData(exportPath);
        if(homeBox.isSelected()){
            buildHomePage(exportPath, cssFile.getName());
            //buildHomePageData(exportPath);
            finalDispalyPath = exportPath+"/index.html";
        }
        if (syllabusBox.isSelected()) {
            buildSyllabus(exportPath, cssFile.getName());
            buildSyllabusData(exportPath);
            //buildHomePageData(exportPath);
            if (finalDispalyPath==null) {
                finalDispalyPath = exportPath+"/syllabus.html";
            }
        }
        if (scheduleBox.isSelected()) {
            buildSchedule(exportPath, cssFile.getName());
            buildScheduleData(exportPath);
            //buildHomePageData(exportPath);

            if (finalDispalyPath==null) {
                finalDispalyPath = exportPath+"/schedule.html";
            }
        }
        if (hwsBox.isSelected()) {
            buildHWS(exportPath, cssFile.getName());
            buildScheduleData(exportPath);
            //buildHomePageData(exportPath);

            if (finalDispalyPath==null) {
                finalDispalyPath = exportPath+"/hws.html";
            }
        }
        //System.out.println(finalDispalyPath);
        java.net.CookieHandler.setDefault(new com.sun.webkit.network.CookieManager());
        AppWebDialog appWebDialog = new  AppWebDialog(app);
        appWebDialog.showWebDialog(finalDispalyPath);
        appWebDialog.getWebEngine().reload();
        
        //update control
        ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(true);
        CheckBox home = (CheckBox) gui.getGUINode(SITE_HOME);
        CheckBox syllabus = (CheckBox)gui.getGUINode(SITE_SYLLABUS);
        CheckBox schedule = (CheckBox)gui.getGUINode(SITE_SCHEDULE);
        CheckBox hws = (CheckBox)gui.getGUINode(SITE_HWS);
        if (home.isSelected()||syllabus.isSelected()||schedule.isSelected()||hws.isSelected()) {
          ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(false);
        }
        ((Button) gui.getGUINode(SAVE_BUTTON)).setDisable(true);
        
        
    }
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void buildHomePage(String exportDir,String cssFileName) throws IOException{
         String html = indexString().replace("CUSTOMCSS", cssFileName);
         File indexFile =  new File(exportDir+"/index.html");
         BufferedWriter writer = new BufferedWriter(new FileWriter(indexFile, false));
         writer.append(html);
         writer.close();
    }
    public void buildHomePageData(String exportDir)throws IOException{
        AppGUIModule gui = app.getGUIModule();
        //create Site pane object
            String subjectText = (String) ((ComboBox) gui.getGUINode(SITE_SUBJECT_COMBO_BOX)).getEditor().getText();
            String numberText = (String) ((ComboBox) gui.getGUINode(SITE_NUMBER_COMBO_BOX)).getEditor().getText();
            String yearText = (String) ((ComboBox) gui.getGUINode(SITE_YEAR_COMBO_BOX)).getEditor().getText();
            String semesterText = (String) ((ComboBox) gui.getGUINode(SITE_SEMESTER_COMBO_BOX)).getEditor().getText();
            String titleText = (String) ((TextField) gui.getGUINode(SITE_TITLE_TEXT_FIELD)).getText();
            ImageView faviconImageView = (ImageView) gui.getGUINode(SITE_FAVICON_IMG);
            ImageView navbarImageView = (ImageView) gui.getGUINode(SITE_NAVBAR_IMG);
            ImageView leftFooterImageView = (ImageView) gui.getGUINode(SITE_LEFT_FOOTER_IMG);
            ImageView rightFooterImageView = (ImageView) gui.getGUINode(SITE_RIGHT_FOOTER_IMG);
            String faviconPath = faviconImageView.getImage().impl_getUrl().replace("file:", "");
            String navbarPath = navbarImageView.getImage().impl_getUrl().replace("file:", "");
            String leftFooterPath = leftFooterImageView.getImage().impl_getUrl().replace("file:", "");
            String rightFooterPath = rightFooterImageView.getImage().impl_getUrl().replace("file:", "");
            File faviconFile = new File(faviconPath);
            File navbarFile = new File (navbarPath);
            File rightFooterFile = new File(rightFooterPath);
            File leftFooterFile = new File(leftFooterPath);
            //create each individual object
            JsonObject favicon = Json.createObjectBuilder().add("href", "./images/"+faviconFile.getName()).build();
            JsonObject navbar = Json.createObjectBuilder().add("src", "./images/"+navbarFile.getName()).build();
            JsonObject leftFooter = Json.createObjectBuilder().add("src", "./images/"+leftFooterFile.getName()).build();
            JsonObject rightFooter = Json.createObjectBuilder().add("src", "./images/"+rightFooterFile.getName()).build();
            //create logo object and puts all img object into it
            JsonObject logoObject = Json.createObjectBuilder().add("favicon",favicon)
                    .add("navbar",navbar).add("bottom_left",leftFooter).add("bottom_right",rightFooter).build();

            //instructor
            String insNameText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_NAME_TEXT_FIELD)).getText();
            String insEmailText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_EMAIL_TEXT_FIELD)).getText();
            String insRoomText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_ROOM_TEXT_FIELD)).getText();
            String insHomepageText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD)).getText();
            String insOHText = ((TextArea) gui.getGUINode(SITE_INSTRUCTOR_OH_TEXT_AREA)).getText();
            
            if (insOHText.equals("")) {
                insOHText = "[]";
            }
            JsonReader jsonReader = Json.createReader(new StringReader(insOHText));
            JsonArray insOHArray = jsonReader.readArray();
            jsonReader.close();
            //put them together 
            JsonObject instructorObject = Json.createObjectBuilder().add("name",insNameText)
                    .add("link",insHomepageText).add("email",insEmailText).add("room",insRoomText)
                    .add("photo","./images/"+insNameText.replaceAll(" ", "")+".jpg")
                    .add("hours",insOHArray).build();
            //pages
            CheckBox homeBox = (CheckBox) gui.getGUINode(SITE_HOME);
            CheckBox syllabusBox = (CheckBox)gui.getGUINode(SITE_SYLLABUS);
            CheckBox scheduleBox = (CheckBox)gui.getGUINode(SITE_SCHEDULE);
            CheckBox hwsBox = (CheckBox)gui.getGUINode(SITE_HWS);
            JsonArrayBuilder pageArrayBuilder = Json.createArrayBuilder();
            if (homeBox.isSelected()) {
                JsonObject home = Json.createObjectBuilder().add("name","Home")
                        .add("link","index.html").build();
                pageArrayBuilder.add(home);
            }
            if (syllabusBox.isSelected()) {
                JsonObject syllabus = Json.createObjectBuilder().add("name","Syllabus")
                        .add("link","syllabus.html").build();
                pageArrayBuilder.add(syllabus);
            }
            if (scheduleBox.isSelected()) {
                JsonObject schedule = Json.createObjectBuilder().add("name","Schedule")
                        .add("link","schedule.html").build();
                pageArrayBuilder.add(schedule);
            }
            if (hwsBox.isSelected()) {
                JsonObject hws = Json.createObjectBuilder().add("name","HWs")
                        .add("link","hws.html").build();
                pageArrayBuilder.add(hws);
            }
            JsonArray pagesArray = pageArrayBuilder.build();
            //put eveything into one site Object

            JsonObject sitePaneObject = Json.createObjectBuilder().add("subject",subjectText).
                    add("number", numberText).add("semester", semesterText).add("year", yearText)
                    .add("title",titleText).add("logos",logoObject)
                    .add("instructor",instructorObject).add("pages",pagesArray)
                    .build();
            
            exportDataFile(sitePaneObject, exportDir+"/js/PageData.json");
        }
    public void buildSyllabus(String exportDir, String cssFileName) throws IOException{
         String html = syllabusString().replace("CUSTOMCSS", cssFileName);
         File syllabusFile =  new File(exportDir+"/syllabus.html");
         BufferedWriter writer = new BufferedWriter(new FileWriter(syllabusFile, false));
         writer.append(html);
         writer.close();
    }
    public void buildSyllabusData(String exportDir) throws IOException{
        AppGUIModule gui = app.getGUIModule();
        //Syllabus object
        String desText = ((TextArea) gui.getGUINode(SY_DESCRIPTION_TEXT_AREA)).getText();
        String topicAreaText = ((TextArea) gui.getGUINode(SY_TOPIC_TEXT_AREA)).getText();
        String prereqText = ((TextArea) gui.getGUINode(SY_PREREQUIREMENT_TEXT_AREA)).getText();
        String outcomesText = ((TextArea) gui.getGUINode(SY_OUTCOMES_TEXT_AREA)).getText();
        String textbookText = ((TextArea) gui.getGUINode(SY_TEXTBOOK_TEXT_AREA)).getText();
        String gradedText = ((TextArea) gui.getGUINode(SY_GRADED_TEXT_AREA)).getText();
        String gradingText = ((TextArea) gui.getGUINode(SY_GRADING_TEXT_AREA)).getText();
        String academicText = ((TextArea) gui.getGUINode(SY_ACADEMIC_TEXT_AREA)).getText();
        String specialText = ((TextArea) gui.getGUINode(SY_SPECIAL_TEXT_AREA)).getText();
        //creat individual Object
        if (topicAreaText.equals("")) {
            topicAreaText="[]";
        }
        JsonReader topicAreaReader = Json.createReader(new StringReader(topicAreaText));
        JsonArray topicArea= topicAreaReader.readArray();
        topicAreaReader.close();
        if (outcomesText.equals("")) {
            outcomesText="[]";
        }
        JsonReader outcomeReader = Json.createReader(new StringReader(outcomesText));
        JsonArray outcomes = outcomeReader.readArray();
        outcomeReader.close();
        if (textbookText.equals("")) {
            textbookText="[]";
        }
        JsonReader textbookReader = Json.createReader(new StringReader(textbookText));
        JsonArray textbook = textbookReader.readArray();
        textbookReader.close();
        if (gradedText.equals("")) {
            gradedText="[]";
        }
        JsonReader gradedReader = Json.createReader(new StringReader(gradedText));
        JsonArray graded = gradedReader.readArray();
        gradedReader.close();
        
        //put everything in one object
        JsonObject syllabusObject = Json.createObjectBuilder()
                .add("description",desText).add("topics",topicArea)
                .add("prerequisites",prereqText).add("outcomes",outcomes)
                .add("textbooks",textbook).add("gradedComponents",graded)
                .add("gradingNote",gradingText).add("academicDishonesty",academicText).
                add("specialAssistance",specialText).build();
        
        exportDataFile(syllabusObject, exportDir+"/js/SyllabusData.json");
        
        //Meeting Time 
        //lecture
        CourseSiteData dataManager = (CourseSiteData) app.getDataComponent();
        JsonArrayBuilder lectureArrayBuilder = Json.createArrayBuilder();
        Iterator<Lecture> lectureIterator = dataManager.lecturesIterator();
        while(lectureIterator.hasNext()){
            Lecture temp = lectureIterator.next();
            JsonObject object = Json.createObjectBuilder().
                    add("section",temp.getSection()).add("days",temp.getDays())
                    .add("time",temp.getTime()).add("room",temp.getRoom()).build();
            lectureArrayBuilder.add(object);
        }
        JsonArray lectureArray = lectureArrayBuilder.build();
        //recitation
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
        Iterator<Recitation> recitationIterator = dataManager.recitationsIterator();
        while(recitationIterator.hasNext()){
            Recitation temp = recitationIterator.next();
            JsonObject object = Json.createObjectBuilder().add("section",temp.getSection())
                    .add("day_time",temp.getDaysTime()).add("location",temp.getRoom())
                    .add("ta_1",temp.getTa1()).add("ta_2",temp.getTa2()).build();
            recitationArrayBuilder.add(object);
        }
        JsonArray recitationArray = recitationArrayBuilder.build();
        //lab
        JsonArrayBuilder labArrayBuilder = Json.createArrayBuilder();
        Iterator<Lab> labIterator = dataManager.labsIterator();
        while(labIterator.hasNext()){
            Lab temp = labIterator.next();
            JsonObject object = Json.createObjectBuilder().add("section",temp.getSection())
                    .add("day_time",temp.getDaysTime()).add("location",temp.getRoom())
                    .add("ta_1",temp.getTa1()).add("ta_2",temp.getTa2()).build();
            labArrayBuilder.add(object);
        }
        JsonArray labArray = labArrayBuilder.build();
        //put everything in one object
        JsonObject meetingTimeObject = Json.createObjectBuilder().add("lectures",lectureArray)
                .add("labs",labArray).add("recitations",recitationArray).build();
        
        exportDataFile(meetingTimeObject, exportDir+"/js/SectionsData.json");
        
        
        //Office Hours
        //instructor
            String insNameText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_NAME_TEXT_FIELD)).getText();
            String insEmailText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_EMAIL_TEXT_FIELD)).getText();
            String insRoomText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_ROOM_TEXT_FIELD)).getText();
            String insHomepageText = ((TextField) gui.getGUINode(SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD)).getText();
            String insOHText = ((TextArea) gui.getGUINode(SITE_INSTRUCTOR_OH_TEXT_AREA)).getText();
            
            if (insOHText.equals("")) {
                insOHText = "[]";
            }
            JsonReader jsonReader = Json.createReader(new StringReader(insOHText));
            JsonArray insOHArray = jsonReader.readArray();
            jsonReader.close();
            //put them together 
            JsonObject instructorObject = Json.createObjectBuilder().add("name",insNameText)
                    .add("link",insHomepageText).add("email",insEmailText).add("room",insRoomText)
                    .add("photo","./images/"+insNameText.replaceAll(" ", "")+".jpg")
                    .add("hours",insOHArray).build();
        //Undergrad save 
	JsonArrayBuilder taUndergradArrayBuilder = Json.createArrayBuilder();
	Iterator<TeachingAssistantPrototype> undergradIterator = dataManager.undergraduateIterator();
        while (undergradIterator.hasNext()) {
            TeachingAssistantPrototype ta = undergradIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName()).add(JSON_EMAIL, ta.getEmail()).build();
	    taUndergradArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taUndergradArrayBuilder.build();
        //Grad save
        JsonArrayBuilder taGradArrayBuilder = Json.createArrayBuilder();
	Iterator<TeachingAssistantPrototype> gradIterator = dataManager.graduateIterator();
        while (gradIterator.hasNext()) {
            TeachingAssistantPrototype ta = gradIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName()).add(JSON_EMAIL, ta.getEmail()).build();
	    taGradArrayBuilder.add(taJson);
	}
	JsonArray gradTAsArray = taGradArrayBuilder.build();
        //time slot save
        JsonArrayBuilder taOHbArrayBuilder = Json.createArrayBuilder();
        //add insturctor into the oh
        for(int i =0; i<insOHArray.size();i++){
            JsonObject object = insOHArray.getJsonObject(i);
            String day = object.getString("day").toUpperCase();
            String time = object.getString("time");
            String startTime = time.substring(0,time.indexOf("-"));
            String endTime = time.substring(time.indexOf("-")+1);
            if (startTime.contains(":")){
                int min = Integer.parseInt(startTime.substring(startTime.indexOf(":")+1,startTime.length()-2));
                if (min<=30){
                    startTime = startTime.substring(0,startTime.indexOf(":"))
                            +":00"+startTime.substring(startTime.length()-2);
                }else{
                    startTime = startTime.substring(0,startTime.indexOf(":"))
                            +":30"+startTime.substring(startTime.length()-2);
                }
            }else{
                startTime = startTime.substring(0,startTime.length()-2)+":00"
                        +startTime.substring(startTime.length()-2);
            }
            if (endTime.contains(":")){
                int min = Integer.parseInt(endTime.substring(endTime.indexOf(":")+1,endTime.length()-2));
                if (min<=30){
                    endTime = endTime.substring(0,endTime.indexOf(":"))
                            +":00"+endTime.substring(endTime.length()-2);
                }else{
                    endTime = endTime.substring(0,endTime.indexOf(":"))
                            +":30"+endTime.substring(endTime.length()-2);
                }
            }else{
                int hour = Integer.parseInt(endTime.substring(0,endTime.length()-2))-1;
                endTime = hour+":30"+endTime.substring(endTime.length()-2);
            }
            if(startTime.contains("pm")){
                if (!startTime.contains("12")) {
                    int hour = Integer.parseInt(startTime.substring(0,startTime.indexOf(":")))+12;
                    startTime = hour+startTime.substring(startTime.indexOf(":"),startTime.length()-2);
                }else{
                    startTime = startTime.substring(0,startTime.length()-2);
                }
            }else{
                startTime = startTime.substring(0,startTime.length()-2);
            }
            if(endTime.contains("pm")){
                if (!endTime.contains("12")) {
                    int hour = Integer.parseInt(endTime.substring(0,endTime.indexOf(":")))+12;
                    endTime = hour+endTime.substring(endTime.indexOf(":"),endTime.length()-2);
                }else{
                    endTime = endTime.substring(0,endTime.length()-2);
                }
            }else{
                endTime = endTime.substring(0,endTime.length()-2);
            }
            //System.out.println(startTime + endTime);
            
            LocalTime startTimes = LocalTime.parse(startTime.replace("_", ":"));
            LocalTime endTimes = LocalTime.parse(endTime.replace("_", ":"));
            while (startTimes.isBefore(endTimes)) {
                if (startTimes.isBefore(LocalTime.of(12, 0))) {
                    if (startTimes.getMinute()==0) {
                        startTime = startTimes.toString().replace(":", "_")+"0am";
                    }else{
                        startTime = startTimes.toString().replace(":", "_")+"am";
                    }
                    
                }else{
                    if (startTimes.getMinute()==0) {
                        startTime = (startTimes.getHour()-12)+"_"+startTimes.getMinute()+"0pm";
                    }else{
                        startTime = (startTimes.getHour()-12)+"_"+startTimes.getMinute()+"pm";
                    }
                }
                JsonObject ohJson = Json.createObjectBuilder()
                            .add(JSON_START_TIME, startTime)
                            .add(JSON_DAY_OF_WEEK, day)
                            .add(JSON_NAME, insNameText).build();
                taOHbArrayBuilder.add(ohJson);
                //System.out.println(startTime);
                startTimes = startTimes.plusMinutes(30);
            }
            if (startTimes.isBefore(LocalTime.of(12, 0))) {
                    if (startTimes.getMinute()==0) {
                        startTime = startTimes.toString().replace(":", "_")+"0am";
                    }else{
                        startTime = startTimes.toString().replace(":", "_")+"am";
                    }
                    
            }else{
                    if (startTimes.getMinute()==0) {
                        startTime = (startTimes.getHour()-12)+"_"+startTimes.getMinute()+"0pm";
                    }else{
                        startTime = (startTimes.getHour()-12)+"_"+startTimes.getMinute()+"pm";
                    }
            }
            JsonObject ohJson = Json.createObjectBuilder()
                            .add(JSON_START_TIME, startTime)
                            .add(JSON_DAY_OF_WEEK, day)
                            .add(JSON_NAME, insNameText).build();
           taOHbArrayBuilder.add(ohJson);
           //System.out.println(startTime);
           startTimes = startTimes.plusMinutes(30);   
            
        }
        // TAs
        Iterator<TimeSlot> ohIterator = ((TableView)gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW)).getItems().iterator();
        while(ohIterator.hasNext()){
            TimeSlot timeSlot=ohIterator.next();
            for(int i =2;i<7;i++){
                DayOfWeek dayOfWeek = dataManager.getColumnDayOfWeek(i);
                for(int j=0;j<timeSlot.getTAS(dayOfWeek).size();j++){
                    JsonObject ohJson = Json.createObjectBuilder()
                            .add(JSON_START_TIME, timeSlot.getStartTime().replaceAll(":", "_"))
                            .add(JSON_DAY_OF_WEEK, dayOfWeek.toString())
                            .add(JSON_NAME, timeSlot.getTAS(dayOfWeek).get(j).getName()).build();
                    taOHbArrayBuilder.add(ohJson);
                }
            }    
        }
        JsonArray officeHourArray = taOHbArrayBuilder.build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject officeHourObject = Json.createObjectBuilder()
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add("instructor",instructorObject)
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_OFFICE_HOURS,officeHourArray)
		.build();
        
        exportDataFile(officeHourObject, exportDir+"/js/OfficeHoursData.json");
    }
    public void buildSchedule(String exportDir, String cssFileName) throws IOException{
        String html = scheduleString().replace("CUSTOMCSS", cssFileName);
         File scheduleFile =  new File(exportDir+"/schedule.html");
         BufferedWriter writer = new BufferedWriter(new FileWriter(scheduleFile, false));
         writer.append(html);
         writer.close();
    }
    public void buildScheduleData(String exportDir) throws IOException{
        AppGUIModule gui = app.getGUIModule(); 
        //SCHEDULE
        CourseSiteData dataManager = (CourseSiteData) app.getDataComponent();
        DatePicker startDatePicker = (DatePicker)gui.getGUINode(SC_START_DATE_DATE_PICKER);
        DatePicker endDatePicker = (DatePicker)gui.getGUINode(SC_END_DATE_DATE_PICKER);
        LocalDate startingMonday = startDatePicker.getValue();
        LocalDate endingFriday = endDatePicker.getValue();
        String mondayMonth = "";
        String mondayDay = "";
        String mondayYear = "";
        String fridayMonth = "";
        String fridayDay ="";
        String fridayYear = "";
        if (startingMonday!=null) {
            mondayDay = ""+startingMonday.getDayOfMonth();
            mondayMonth = ""+startingMonday.getMonthValue();
            mondayYear = ""+startingMonday.getYear();
        }
        if (endingFriday!=null) {
            fridayDay = ""+endingFriday.getDayOfMonth();
            fridayMonth =""+endingFriday.getMonthValue();
            fridayYear = ""+endingFriday.getYear();
        }
        
        JsonArrayBuilder holidayArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder scheduleLectureArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder scheduleRecitationArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder scheduleLabArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder hwsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder referenceArrayBuilder = Json.createArrayBuilder();
        
        Iterator<Schedule> scheduleIterator = dataManager.schedulesIterator();
        while(scheduleIterator.hasNext()){
            Schedule temp = scheduleIterator.next();
            String month = temp.getDate().substring(temp.getDate().indexOf("-")+1,temp.getDate().lastIndexOf("-"));
            String year = temp.getDate().substring(0,temp.getDate().indexOf("-"));
            String day = temp.getDate().substring(temp.getDate().lastIndexOf("-")+1);
            switch(temp.getType()){
                case "Holiday":
                    JsonObject holiday = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("link",temp.getLink()).build();
                    holidayArrayBuilder.add(holiday);break;
                case "Lecture":
                    JsonObject lecture = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).build();
                    scheduleLectureArrayBuilder.add(lecture);break;
                case "Reference":
                    JsonObject reference = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).build();
                    referenceArrayBuilder.add(reference);break;
                case "Recitation":
                    JsonObject recitation = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).build();
                    scheduleRecitationArrayBuilder.add(recitation);break;
                case "Lab":
                    JsonObject lab = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).build();
                    scheduleLabArrayBuilder.add(lab);break;
                case "HW":
                    JsonObject hw = Json.createObjectBuilder().add("month",month)
                            .add("day",day).add("year",year).add("title",temp.getTitle())
                            .add("topic",temp.getTopic()).add("link",temp.getLink()).add("time","")
                            .add("criteria","none").build();
                    hwsArrayBuilder.add(hw);break;
            }
        }
        //BUild arrays 
        JsonArray holidayArray = holidayArrayBuilder.build();
        JsonArray scheduleLectureArray = scheduleLectureArrayBuilder.build();
        JsonArray scheduleRecitationArray = scheduleRecitationArrayBuilder.build();
        JsonArray scheduleLabArray = scheduleLabArrayBuilder.build();
        JsonArray hwArray = hwsArrayBuilder.build();
        JsonArray referenceArray = referenceArrayBuilder.build();
        
        //put together
        JsonObject scheduleObject = Json.createObjectBuilder()
                .add("startingMondayYear",mondayYear).add("startingMondayMonth",mondayMonth)
                .add("startingMondayDay",mondayDay).add("endingFridayYear",fridayYear)
                .add("endingFridayMonth",fridayMonth).add("endingFridayDay",fridayDay)
                .add("holidays",holidayArray).add("lectures",scheduleLectureArray)
                .add("references",referenceArray).add("recitations",scheduleRecitationArray)
                .add("hws",hwArray).add("labs",scheduleLabArray).build();
        exportDataFile(scheduleObject, exportDir+"/js/ScheduleData.json");
    }
    public void buildHWS(String exportDir, String cssFileName)throws  IOException{
        String html = hwsString().replace("CUSTOMCSS", cssFileName);
         File hwFile =  new File(exportDir+"/hws.html");
         BufferedWriter writer = new BufferedWriter(new FileWriter(hwFile, false));
         writer.append(html);
         writer.close();
    }
    public void exportDataFile(JsonObject object, String path) throws IOException{
        Map<String, Object> properties = new HashMap<>(1);
            properties.put(JsonGenerator.PRETTY_PRINTING, true);
            JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
            StringWriter sw = new StringWriter();
            JsonWriter jsonWriter = writerFactory.createWriter(sw);
            jsonWriter.writeObject(object);
            jsonWriter.close();

            // INIT THE WRITER
            OutputStream os = new FileOutputStream(path);
            JsonWriter jsonFileWriter = Json.createWriter(os);
            jsonFileWriter.writeObject(object);
            String prettyPrinted = sw.toString();
            PrintWriter pw = new PrintWriter(path);
            pw.write(prettyPrinted);
            pw.close();
    }
    public String indexString(){
        return "<!doctype html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <title></title>\n" +
            "    <meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\">\n" +
            "\n" +
            "    <link href=\"./css/course_homepage_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "    <link href=\"./css/CUSTOMCSS\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "\n" +
            "    <!-- LINK TO OUR JavaScript FILE AND JQuery -->\n" +
            "    <script src=\"./js/jquery.min.js\"></script>\n" +
            "    <script src=\"./js/PageBuilder.js\"></script>\n" +
            "\n" +
            "    <!-- ONCE THE PAGE FULLY LOADS, COMPLETE BUILDING THE CONTENT -->\n" +
            "    <script>\n" +
            "        $(document).ready()\n" +
            "        {\n" +
            "            buildPage(\"Home\", \".\");\n" +
            "        }\n" +
            "    </script>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<div id=\"content\">\n" +
            "    <div id=\"navbar\">\n" +
            "        <a id=\"navbar_image_link\"></a>\n" +
            "    </div>\n" +
            "\n" +
            "    <div id=\"banner\"></div>\n" +
            "\n" +
            "    <div id=\"desc\">\n" +
            "\n" +
            "        <p>Welcome to the <span id=\"inlined_course\"></span> Web site. If you are enrolled in the course, be sure to periodically check this site for all materials and changes as the semester moves along. Note that all grades will be posted to <a href=\"http://blackboard.stonybrook.edu\">Blackboard</a>.</p>\n" +
            "        <br /><br /><br /><br /><br /><br /><br />\n" +
            "        <hr />\n" +
            "\n" +
            "        <a id=\"left_footer_image_link\"></a>\n" +
            "        <a id=\"right_footer_image_link\"></a>\n" +
            "        <p class=\"footer\">Web page created and maintained<br />\n" +
            "            by <span id=\"author_link\"></span></p>\n" +
            "        <br /><br />\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";
    }
    public  String syllabusString(){
        return "<!doctype html>\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "        <title></title>\n" +
            "        <meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\">\n" +
            "        <link href=\"./css/course_homepage_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "        <link href=\"./css/CUSTOMCSS\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "\n" +
            "        <!-- LINK TO OUR JavaScript FILE AND JQuery -->\n" +
            "        <script src=\"./js/jquery.min.js\"></script>\n" +
            "        <script src=\"./js/PageBuilder.js\"></script>\n" +
            "        <script src=\"./js/OfficeHoursBuilder.js\"></script>\n" +
            "        <script src=\"./js/SectionsBuilder.js\"></script>\n" +
            "        <script src=\"./js/SyllabusBuilder.js\"></script>\n" +
            "\n" +
            "        <!-- ONCE THE PAGE FULLY LOADS INITIALIZE THE SLIDESHOW -->\n" +
            "        <script>\n" +
            "            $(document).ready()\n" +
            "            {\n" +
            "                buildPage(\"Syllabus\", \".\");\n" +
            "                buildOfficeHours();\n" +
            "                buildSections();\n" +
            "                buildSyllabus();\n" +
            "            }\n" +
            "        </script>\n" +
            "    </head>\n" +
            "\n" +
            "    <body>\n" +
            "        <div id=\"content\">\n" +
            "            <div id=\"navbar\"><a id=\"navbar_image_link\"></a></div>\n" +
            "\n" +
            "            <div id=\"banner\"></div>\n" +
            "            <div id=\"desc\">\n" +
            "\n" +
            "                <div id=\"custom_syllabus_content\">\n" +
            "\n" +
            "                    <h3>COURSE DESCRIPTION</h3>\n" +
            "                    <p id='syllabus_course_description'></p>\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3>COURSE TOPICS</h3>\n" +
            "                    <ul id=\"syllabus_course_topics\">\n" +
            "                    </ul>\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3>PREREQUISITES</h3>\n" +
            "                    <p id=\"syllabus_prerequisites\"></p>\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3>COURSE OUTCOMES</h3>\n" +
            "                    <p>At the end of the course you should have the following knowledge and skills:</p>\n" +
            "                    <ul id=\"syllabus_course_outcomes\">\n" +
            "                    </ul>\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3>LECTURES</h3>\n" +
            "                    <table>\n" +
            "                        <tbody>\n" +
            "                            <tr id='lectures_row'>\n" +
            "                            </tr>\n" +
            "                        </tbody>\n" +
            "                    </table>\n" +
            "                    <br />\n" +
            "                    <br />\n" +
            "                    \n" +
            "                    <h3 id=\"labs_heading\"></h3>\n" +
            "                    <table>\n" +
            "                        <tbody id=\"labs_table\">\n" +
            "                        </tbody>\n" +
            "                    </table>\n" +
            "                    <br />\n" +
            "                    <br />\n" +
            "                                  \n" +
            "                    <h3 id=\"recitations_heading\"></h3>\n" +
            "                    <table>\n" +
            "                        <tbody id=\"recitations_table\">\n" +
            "                        </tbody>\n" +
            "                    </table>\n" +
            "                    <br />\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3>INSTRUCTOR</h3>                    \n" +
            "                    <table>\n" +
            "                        <tr>\n" +
            "                            <td id='instructor_data'></td>\n" +
            "                            <td id='instructor_photo'></td>\n" +
            "                        </tr>\n" +
            "                    </table>\n" +
            "                    <br />\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3 id=\"graders\">GRAD TEACHING ASSISTANTS (Grading Appointments)</h3>\n" +
            "\n" +
            "                    <table>\n" +
            "                        <tbody id=\"grad_tas\">\n" +
            "                        </tbody>\n" +
            "                    </table>\n" +
            "                    <br />\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3 id=\"tas\">UNDERGRAD TEACHING ASSISTANTS (Help)</h3>\n" +
            "\n" +
            "                    <table>\n" +
            "                        <tbody id=\"undergrad_tas\">\n" +
            "                        </tbody>\n" +
            "                    </table>\n" +
            "                    <br />\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3 id=\"office_hours_grid\">OFFICE HOURS (in Old CS 2217)</h3>\n" +
            "                    <table class=\"office_hours_schedule\" cellspacing=\"0\" border=\"1\">\n" +
            "                        <tbody id=\"office_hours_table\">\n" +
            "                            <tr>\n" +
            "                                <th class=\"sch\" width=\"150px\">Start Time</th>\n" +
            "                                <th class=\"sch\" width=\"150px\">End Time</th>\n" +
            "                                <th class=\"sch\" width=\"200px\">MONDAY</th>\n" +
            "                                <th class=\"sch\" width=\"200px\">TUESDAY</th>\n" +
            "                                <th class=\"sch\" width=\"200px\">WEDNESDAY</th>\n" +
            "                                <th class=\"sch\" width=\"200px\">THURSDAY</th>\n" +
            "                                <th class=\"sch\" width=\"200px\">FRIDAY</th>\n" +
            "                            </tr>                        \n" +
            "                        </tbody>\n" +
            "                    </table>\n" +
            "                    <br /> \n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3>TEXTBOOKS</h3>                    \n" +
            "                    <div id='textbook_data'></div>\n" +
            "                    <br clear=\"all\" />\n" +
            "                    <br />\n" +
            "\n" +
            "\n" +
            "                    <h3>GRADED COMPONENTS</h3>                    \n" +
            "                    <ul id='graded_components'></ul>\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3>GRADING BREAKDOWN</h3>                    \n" +
            "                    <table id='grading_breakdown'></table>\n" +
            "                    <p id='grading_note'></p>\n" +
            "                    <br />                    \n" +
            "\n" +
            "                    <h3>ACADEMIC DISHONESTY</h3>\n" +
            "                    <div id='academic_dishonesty'></div>\n" +
            "                    <br />\n" +
            "\n" +
            "                    <h3>SPECIAL ASSISTANCE</h3>\n" +
            "                    <div id='special_assistance'></div>\n" +
            "                    <br />\n" +
            "\n" +
            "                    <hr />\n" +
            "\n" +
            "                    <a id=\"left_footer_image_link\"></a>\n" +
            "                    <a id=\"right_footer_image_link\"></a>\n" +
            "                    <p class=\"footer\">Web page created and maintained<br />\n" +
            "                        by <span id=\"author_link\"></span></p>\n" +
            "                    <br /><br />\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </body>\n" +
            "</html>";
    }
    public String scheduleString(){
        return "<!doctype html>\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "        <title></title>\n" +
            "        <meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\">\n" +
            "        <link href=\"./css/course_homepage_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "        <link href=\"./css/CUSTOMCSS\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "\n" +
            "        <!-- LINK TO OUR JavaScript FILE AND JQuery -->\n" +
            "        <script src=\"./js/jquery.min.js\"></script>\n" +
            "        <script src=\"./js/PageBuilder.js\"></script>\n" +
            "        <script src=\"./js/ScheduleBuilder.js\"></script>\n" +
            "\n" +
            "        <!-- ONCE THE PAGE FULLY LOADS INITIALIZE THE SLIDESHOW -->\n" +
            "        <script>\n" +
            "            $(document).ready()\n" +
            "            {\n" +
            "                buildPage(\"Schedule\", \".\");\n" +
            "                buildSchedule();\n" +
            "            }\n" +
            "        </script>\n" +
            "    </head>\n" +
            "\n" +
            "    <body>\n" +
            "        <div id=\"content\">\n" +
            "            <div id=\"navbar\"><a id=\"navbar_image_link\"></a></div>\n" +
            "\n" +
            "            <div id=\"banner\"></div>\n" +
            "\n" +
            "\n" +
            "            <div id=\"desc\">\n" +
            "\n" +
            "                <table border=\"1\" cellspacing=\"0\" class=\"schedule\">\n" +
            "                    <tbody id=\"schedule_table\">\n" +
            "                        <!-- THIS WILL BE FILLED IN BY JAVASCRIPT -->                        \n" +
            "                    </tbody>\n" +
            "                </table>\n" +
            "\n" +
            "                <br />\n" +
            "\n" +
            "                <hr />\n" +
            "\n" +
            "                <a id=\"left_footer_image_link\"></a>\n" +
            "                <a id=\"right_footer_image_link\"></a>\n" +
            "                <p class=\"footer\">Web page created and maintained<br />\n" +
            "                    by <span id=\"author_link\"></span></p>\n" +
            "                <br /><br />\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </body>\n" +
            "</html>";
    }
    public String hwsString(){
        return "<!doctype html>\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "        <title></title>\n" +
            "        <meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\">\n" +
            "        <link href=\"./css/course_homepage_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "        <link href=\"./css/CUSTOMCSS\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "\n" +
            "        <!-- LINK TO OUR JavaScript FILE AND JQuery -->\n" +
            "        <script src=\"./js/jquery.min.js\"></script>\n" +
            "        <script src=\"./js/PageBuilder.js\"></script>\n" +
            "        <script src=\"./js/HWsBuilder.js\"></script>\n" +
            "\n" +
            "        <!-- ONCE THE PAGE FULLY LOADS INITIALIZE THE SLIDESHOW -->\n" +
            "        <script>\n" +
            "            $(document).ready()\n" +
            "            {\n" +
            "                buildPage(\"HWs\", \".\");\n" +
            "                buildHWs();\n" +
            "            }\n" +
            "        </script>\n" +
            "    </head>\n" +
            "\n" +
            "    <body>\n" +
            "        <div id=\"content\">\n" +
            "            <div id=\"navbar\"><a id=\"navbar_image_link\"></a></div>\n" +
            "\n" +
            "            <div id=\"banner\"></div>\n" +
            "\n" +
            "            <div id=\"desc\">\n" +
            "                <h3>Homework Assignments</h3>\n" +
            "\n" +
            "                <p>Note that the <a href='./schedule.html'>Schedule Page</a> also contains links to assignments.</p>\n" +
            "\n" +
            "                <table class=\"hws\">\n" +
            "                    <tbody id=\"hws\">\n" +
            "                        <tr class=\"hws\" style=\"background-color:rgb(235,215,215)\">\n" +
            "                            <th class=\"hws\">Homework</th>\n" +
            "                            <th class=\"hws\">Due Date</th>\n" +
            "                            <!--th class=\"hws\">Grading Criteria</th>-->\n" +
            "                        </tr>\n" +
            "\n" +
            "                        <!-- THIS WILL BE FILLED IN BY JAVASCRIPT -->\n" +
            "                    </tbody>\n" +
            "                </table>\n" +
            "                <br />\n" +
            "                <hr />\n" +
            "\n" +
            "                <a id=\"left_footer_image_link\"></a>\n" +
            "                <a id=\"right_footer_image_link\"></a>\n" +
            "                <p class=\"footer\">Web page created and maintained<br />\n" +
            "                    by <span id=\"author_link\"></span></p>\n" +
            "                <br /><br />\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </body>\n" +
            "</html>";
    }
    public String defaultCss(){
        return "#content\n" +
                "{\n" +
                "    position:absolute;\n" +
                "    width:80%;\n" +
                "    left:10%;\n" +
                "    right:10%;\n" +
                "    text-align:left;\n" +
                "}\n" +
                "\n" +
                "#navbar\n" +
                "{\n" +
                "    position:absolute	;\n" +
                "    width:100%;\n" +
                "    height:45px;\n" +
                "    top:0px;\n" +
                "}\n" +
                "\n" +
                "#banner\n" +
                "{\n" +
                "    position:absolute;\n" +
                "    width:100%;\n" +
                "    height:80px;\n" +
                "    text-align:center;\n" +
                "    padding-top:10px;\n" +
                "    padding-bottom:0px;\n" +
                "    margin-top:0px;\n" +
                "    margin-bottom:0px;\n" +
                "    top:50px;\n" +
                "}\n" +
                "\n" +
                "#desc\n" +
                "{\n" +
                "    position:absolute;\n" +
                "    padding-left:2%;\n" +
                "    padding-right:2%;\n" +
                "    padding-top:0px;\n" +
                "    width:96%;\n" +
                "    margin-top:0px;\n" +
                "    top:140px;\n" +
                "}\n" +
                "\n" +
                "img.sbu_navbar\n" +
                "{\n" +
                "    float:right;\n" +
                "}\n" +
                "\n" +
                "img.photo_floating_right\n" +
                "{\n" +
                "    float:right;\n" +
                "    padding-left:20px;\n" +
                "}\n" +
                "\n" +
                "img.photo_floating_left\n" +
                "{\n" +
                "    float:left;\n" +
                "    padding-right:20px;\n" +
                "}\n" +
                "\n" +
                "p.footer\n" +
                "{\n" +
                "    font-size:8pt;\n" +
                "    text-align:center;\n" +
                "    padding-top:00px;\n" +
                "}\n" +
                "\n" +
                "img {\n" +
                "    border:0;\n" +
                "}\n" +
                "\n" +
                "img.stonybrook {\n" +
                "    background-color:white;\n" +
                "    padding-left:10px;\n" +
                "    float:left;\n" +
                "}\n" +
                "\n" +
                "img.cs {\n" +
                "    float:right;\n" +
                "    padding-right:10px;\n" +
                "}\n" +
                "\n" +
                "img.start_up\n" +
                "{\n" +
                "    float:right;\n" +
                "    margin-left:20px;\n" +
                "    margin-right:20px;\n" +
                "    margin-bottom:5px;\n" +
                "}\n" +
                "\n" +
                "img.centered {\n" +
                "    margin-left:30%;\n" +
                "    margin-right:30%;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "a.nav, a.open_nav {\n" +
                "    display:inline-block;\n" +
                "    border:none;\n" +
                "    text-decoration:none;\n" +
                "    text-align:center;\n" +
                "    font-weight:bold;\n" +
                "    padding-left:5px;\n" +
                "    padding-right:20px;\n" +
                "    padding-top:5px;\n" +
                "    width:80px;\n" +
                "}\n" +
                "\n" +
                "p.contact_info\n" +
                "{\n" +
                "    padding-top:0px;\n" +
                "    margin-top:0px;\n" +
                "    margin-bottom:0px;\n" +
                "}\n" +
                "\n" +
                "td.course_name\n" +
                "{\n" +
                "    padding-left:10px;\n" +
                "    padding-right:10px;\n" +
                "}\n" +
                "\n" +
                "img.logo_page\n" +
                "{\n" +
                "    position:relative;\n" +
                "    width:100%;\n" +
                "    height:auto;\n" +
                "}\n" +
                "\n" +
                "table\n" +
                "{\n" +
                "    border-color:#555555;\n" +
                "}\n" +
                "\n" +
                "table.schedule\n" +
                "{\n" +
                "    margin:auto;\n" +
                "    width:100%;\n" +
                "}\n" +
                "\n" +
                "td.sch\n" +
                "{\n" +
                "    position:relative;\n" +
                "    width:20%;\n" +
                "    height:150px;\n" +
                "    margin-left:0%;\n" +
                "    margin-right:0%;\n" +
                "    vertical-align:top;\n" +
                "}\n" +
                "\n" +
                "th.sch\n" +
                "{\n" +
                "    vertical-align:top;\n" +
                "    text-align:center;\n" +
                "    border-color:#888888;\n" +
                "}\n" +
                "\n" +
                "td.holiday\n" +
                "{\n" +
                "    background-color:#aaaaaa;\n" +
                "    vertical-align:top;\n" +
                "}\n" +
                "\n" +
                ".textbook_image {\n" +
                "    float:left;\n" +
                "    margin-right:3em;\n" +
                "}";
        }
    }
