package oh.workspace.controllers;

import djf.modules.AppGUIModule;
import javafx.scene.control.TextField;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.OH_EMAIL_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.OH_NAME_TEXT_FIELD;
import oh.data.OfficeHoursData;
import oh.data.TeachingAssistantPrototype;
import oh.data.TimeSlot;
import oh.transactions.AddTA_Transaction;
import oh.transactions.AddTimeSlot_Transaction;
import djf.ui.dialogs.AppDialogsFacade;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Parent;
import static oh.OfficeHoursPropertyType.OH_EMAIL_ERROR_TEXT;
import static oh.OfficeHoursPropertyType.OH_NAME_ERROR_TEXT;


/**
 *
 * @author McKillaGorilla
 */
public class OfficeHoursController {

    OfficeHoursApp app;

    public OfficeHoursController(OfficeHoursApp initApp) {
        app = initApp;
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public void up(){
        app.getFoolproofModule().updateAll();
    }
    public void processAddTA() {
       app.getFoolproofModule().updateAll();
        AppGUIModule gui = app.getGUIModule();
        TextField nameTF = (TextField) gui.getGUINode(OH_NAME_TEXT_FIELD);
        TextField emailTF = (TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD);
        String name = nameTF.getText();
        String email = emailTF.getText();
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        
        if (!matcher.find()){
            AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), OH_EMAIL_ERROR_TEXT, OH_EMAIL_ERROR_TEXT);
        }else if(name.equals("")){
            AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), OH_NAME_ERROR_TEXT, OH_NAME_ERROR_TEXT);
        }else{
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name,email);
        AddTA_Transaction addTATransaction = new AddTA_Transaction(data, ta);
        app.processTransaction(addTATransaction);

        // NOW CLEAR THE TEXT FIELDS
        nameTF.setText("");
        nameTF.requestFocus();
        emailTF.setText("");
        }
    }
    public void processAddTimeslot(TeachingAssistantPrototype ta, int column,TimeSlot dataSlot){
        AppGUIModule gui = app.getGUIModule();
        //TimeSlot data = (TimeSlot) app.getDataComponent();
        TimeSlot data = dataSlot;
        AddTimeSlot_Transaction addTimeSlot_Transaction = new AddTimeSlot_Transaction(app,data, ta,column);
        app.processTransaction(addTimeSlot_Transaction);
    }
}
