package oh.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.OH_ADD_TA_BUTTON;
import static oh.OfficeHoursPropertyType.OH_EMAIL_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.OH_NAME_TEXT_FIELD;
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
        String name = ((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD)).getText();
        String email =((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD)).getText();
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        OfficeHoursData officeHoursData = (OfficeHoursData) app.getDataComponent();
        if(matcher.find() == true&&officeHoursData.getTAWithName(name)==null){
            ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setDisable(false);
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