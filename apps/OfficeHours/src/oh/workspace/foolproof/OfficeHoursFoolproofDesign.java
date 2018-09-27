package oh.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.OH_ADD_TA_BUTTON;
import static oh.OfficeHoursPropertyType.OH_EMAIL_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.OH_NAME_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_ALL;
import oh.data.OfficeHoursData;
import static oh.workspace.controllers.OfficeHoursController.VALID_EMAIL_ADDRESS_REGEX;

/**
 *
 * @author McKillaGorilla
 */
public class OfficeHoursFoolproofDesign implements FoolproofDesign {
    OfficeHoursApp app;
    
    public OfficeHoursFoolproofDesign(OfficeHoursApp initApp) {
        app = initApp;
    }
public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
        ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setDisable(true);
        TextField nameField =((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD));
        TextField emailField = ((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD));
        String name = nameField.getText().trim();
        String email =emailField.getText();
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        OfficeHoursData officeHoursData = (OfficeHoursData) app.getDataComponent();
        RadioButton radioButton = (RadioButton) gui.getGUINode(OH_TOGGLE_ALL);
        nameField.getStyleClass().remove("error");
        emailField.getStyleClass().remove("error");
        
        if(radioButton.isSelected()){
            
        }else {
            if (matcher.find()==true&&officeHoursData.getTAWithName(name)==null) {
                ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setDisable(false);
                nameField.getStyleClass().add("error");
                emailField.getStyleClass().add("error");
            }else{
               if (officeHoursData.getTAWithName(name)!=null||name.equals("")) {
                
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