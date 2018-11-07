/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.foolproof;

import csg.CourseSiteGenerateApp;
import static csg.CourseSitePropertyType.OH_ADD_TA_BUTTON;
import static csg.CourseSitePropertyType.OH_EMAIL_TEXT_FIELD;
import static csg.CourseSitePropertyType.OH_NAME_TEXT_FIELD;
import static csg.CourseSitePropertyType.OH_REMOVE_TA_BUTTON;
import static csg.CourseSitePropertyType.OH_TOGGLE_ALL;
import csg.data.CourseSiteData;
import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
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
    }
    public void removeTAButtonControls(){
        AppGUIModule gui = app.getGUIModule();
        ((Button) gui.getGUINode(OH_REMOVE_TA_BUTTON)).setDisable(true);
        CourseSiteData data = (CourseSiteData)app.getDataComponent();
        if (data.isTASelected()) {
        ((Button) gui.getGUINode(OH_REMOVE_TA_BUTTON)).setDisable(false);
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
