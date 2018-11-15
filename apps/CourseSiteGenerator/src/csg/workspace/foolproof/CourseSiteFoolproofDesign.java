/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.foolproof;

import csg.CourseSiteGenerateApp;
import static csg.CourseSitePropertyType.MT_REMOVE_LAB_BUTTON;
import static csg.CourseSitePropertyType.MT_REMOVE_LECTURE_BUTTON;
import static csg.CourseSitePropertyType.MT_REMOVE_RECITATION_BUTTON;
import static csg.CourseSitePropertyType.OH_ADD_TA_BUTTON;
import static csg.CourseSitePropertyType.OH_EMAIL_TEXT_FIELD;
import static csg.CourseSitePropertyType.OH_NAME_TEXT_FIELD;
import static csg.CourseSitePropertyType.OH_REMOVE_TA_BUTTON;
import static csg.CourseSitePropertyType.OH_TOGGLE_ALL;
import static csg.CourseSitePropertyType.SC_ADD_ITEM_BUTTON;
import static csg.CourseSitePropertyType.SC_CLEAR_ITEM_BUTTON;
import static csg.CourseSitePropertyType.SC_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_END_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_LINK_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_REMOVE_ITEM_BUTTON;
import static csg.CourseSitePropertyType.SC_START_DATE_DATE_PIKER;
import static csg.CourseSitePropertyType.SC_TITLE_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_TOPIC_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_TYPE_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_HOME;
import static csg.CourseSitePropertyType.SITE_HWS;
import static csg.CourseSitePropertyType.SITE_SCHEDULE;
import static csg.CourseSitePropertyType.SITE_SYLLABUS;
import csg.data.CourseSiteData;
import static djf.AppPropertyType.EXPORT_BUTTON;
import static djf.AppPropertyType.SAVE_BUTTON;
import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author zhengyu
 */
public class CourseSiteFoolproofDesign implements FoolproofDesign {
    CourseSiteGenerateApp app;
    
    public CourseSiteFoolproofDesign (CourseSiteGenerateApp initApp){
        app =initApp;
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @Override
    public void updateControls() {
        addTAButtonControls();
        removeTAButtonControls();
        removeScheduleControls();
        removeMTControls();
        exportIconControls();
        scheduleDatePickerControls();
        ScheduleControls();
    }
    public void exportIconControls(){
        AppGUIModule gui = app.getGUIModule();
        ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(true);
        CheckBox home = (CheckBox) gui.getGUINode(SITE_HOME);
        CheckBox syllabus = (CheckBox)gui.getGUINode(SITE_SYLLABUS);
        CheckBox schedule = (CheckBox)gui.getGUINode(SITE_SCHEDULE);
        CheckBox hws = (CheckBox)gui.getGUINode(SITE_HWS);
        if (((Button)gui.getGUINode(SAVE_BUTTON)).isDisabled()) {
            if (home.isSelected()||syllabus.isSelected()||schedule.isSelected()||hws.isSelected()) {
                ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(false);
            }
        }else{
            ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(true);
        }

        
    }
    public void removeTAButtonControls(){
        AppGUIModule gui = app.getGUIModule();
        ((Button) gui.getGUINode(OH_REMOVE_TA_BUTTON)).setDisable(true);
        CourseSiteData data = (CourseSiteData)app.getDataComponent();
        if (data.isTASelected()) {
        ((Button) gui.getGUINode(OH_REMOVE_TA_BUTTON)).setDisable(false);
        }
    }
    public void removeMTControls(){
        AppGUIModule gui = app.getGUIModule();
        ((Button) gui.getGUINode(MT_REMOVE_LECTURE_BUTTON)).setDisable(true);
        CourseSiteData data = (CourseSiteData)app.getDataComponent();
        if (data.isLectureSelected()) {
        ((Button) gui.getGUINode(MT_REMOVE_LECTURE_BUTTON)).setDisable(false);
        }
        ((Button) gui.getGUINode(MT_REMOVE_RECITATION_BUTTON)).setDisable(true);
        if (data.isRecitationSelected()) {
        ((Button) gui.getGUINode(MT_REMOVE_RECITATION_BUTTON)).setDisable(false);
        }
        ((Button) gui.getGUINode(MT_REMOVE_LAB_BUTTON)).setDisable(true);
        if (data.isLabSelected()) {
        ((Button) gui.getGUINode(MT_REMOVE_LAB_BUTTON)).setDisable(false);
        }
        
        
    }
    public void ScheduleControls(){
        AppGUIModule gui = app.getGUIModule();
        ((Button) gui.getGUINode(SC_ADD_ITEM_BUTTON)).setDisable(true);
        ComboBox type = (ComboBox) gui.getGUINode(SC_TYPE_COMBO_BOX);
        DatePicker date = (DatePicker) gui.getGUINode(SC_DATE_DATE_PICKER);
        TextField title = (TextField)gui.getGUINode(SC_TITLE_TEXT_FIELD);
        TextField topic = (TextField)gui.getGUINode(SC_TOPIC_TEXT_FIELD);
        TextField link = (TextField)gui.getGUINode(SC_LINK_TEXT_FIELD);
        if (type.getValue()==null||date.getValue()==null||
                title.getText().equals("")||topic.getText().equals("")|link.getText().equals("")){
        }else{
         ((Button) gui.getGUINode(SC_ADD_ITEM_BUTTON)).setDisable(false);
           
        }

    }
    public void scheduleDatePickerControls(){
        AppGUIModule gui = app.getGUIModule();
        ((DatePicker) gui.getGUINode(SC_DATE_DATE_PICKER)).setDisable(true);
        DatePicker startDate = (DatePicker) gui.getGUINode(SC_START_DATE_DATE_PIKER);
        DatePicker endDate = (DatePicker) gui.getGUINode(SC_END_DATE_DATE_PICKER);

        if (startDate.getValue()!=null&&endDate.getValue()!=null) {
            ((DatePicker) gui.getGUINode(SC_DATE_DATE_PICKER)).setDisable(false);
        }
    }
    public void removeScheduleControls(){
         AppGUIModule gui = app.getGUIModule();
         ((Button) gui.getGUINode(SC_REMOVE_ITEM_BUTTON)).setDisable(true);
         CourseSiteData data = (CourseSiteData)app.getDataComponent();
         if(data.isScheduleSelected()){
            ((Button) gui.getGUINode(SC_REMOVE_ITEM_BUTTON)).setDisable(false);
         }
    }
    public void addTAButtonControls(){
        AppGUIModule gui = app.getGUIModule();
        ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setDisable(true);
        TextField nameField =((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD));
        TextField emailField = ((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD));
        String name = nameField.getText().trim();
        String email =emailField.getText();
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        CourseSiteData courseSiteData = (CourseSiteData) app.getDataComponent();
        RadioButton radioButton = (RadioButton) gui.getGUINode(OH_TOGGLE_ALL);
        nameField.getStyleClass().remove("error");
        emailField.getStyleClass().remove("error");
        
        if(radioButton.isSelected()){
            
        }else {
            if (matcher.find()==true&&courseSiteData.getTAWithName(name)==null) {
                ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setDisable(false);
                nameField.getStyleClass().add("error");
                emailField.getStyleClass().add("error");
            }else{
               if (courseSiteData.getTAWithName(name)!=null||name.equals("")) {
                
                }else{
                    nameField.getStyleClass().add("error");

                }
                if(matcher.find()==true){
                    emailField.getStyleClass().add("error");
                }
            }
            
            
           
        }

        /*if (!(((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD)).getText().trim().equals(""))) {
            if((((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD)).getText().trim().equals(""))){
                
            }else{
               ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setDisable(false);

            }
        }
        */
    }
}
