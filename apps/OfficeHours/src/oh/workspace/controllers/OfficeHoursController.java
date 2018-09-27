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
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import static oh.OfficeHoursPropertyType.OH_EDIT_TA_TEXT;
import static oh.OfficeHoursPropertyType.OH_EDIT_TA_TITLE;
import static oh.OfficeHoursPropertyType.OH_EDIT_TA_TYPE;
import static oh.OfficeHoursPropertyType.OH_EMAIL_ERROR_TEXT;
import static oh.OfficeHoursPropertyType.OH_EMAIL_TABLE_COLUMN_TEXT;
import static oh.OfficeHoursPropertyType.OH_NAME_ERROR_TEXT;
import static oh.OfficeHoursPropertyType.OH_NAME_TABLE_COLUMN_TEXT;
import static oh.OfficeHoursPropertyType.OH_TAS_TABLE_VIEW;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_GRADUATE;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_GRADUATE_TEXT;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_UNDERGRADUATE;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_UNDERGRADUATE_TEXT;
import properties_manager.PropertiesManager;



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
        RadioButton grauButton = (RadioButton) gui.getGUINode(OH_TOGGLE_GRADUATE);
        RadioButton undergruadeButton = (RadioButton) gui.getGUINode(OH_TOGGLE_UNDERGRADUATE);
        TeachingAssistantPrototype ta;
        if(grauButton.isSelected()){
            ta = new TeachingAssistantPrototype(name,email,"Graduate");
            AddTA_Transaction addTATransaction = new AddTA_Transaction(data, ta);
            app.processTransaction(addTATransaction);            
           
        }else if (undergruadeButton.isSelected()){
            ta = new TeachingAssistantPrototype(name,email,"Undergraduate");
            AddTA_Transaction addTATransaction = new AddTA_Transaction(data, ta);
            app.processTransaction(addTATransaction);
        }
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
    public void changeToAll(){
        AppGUIModule gui = app.getGUIModule();
        TableView taOfficeHoursTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        taOfficeHoursTableView.setItems(data.getAllTAS());
        up();
        
    }
    public void changeToUndergraduate (){
        AppGUIModule gui = app.getGUIModule();
        TableView taOfficeHoursTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        taOfficeHoursTableView.setItems(data.getUndergradTAS());
        up();
    }
    public void changeToGrad(){
        AppGUIModule gui = app.getGUIModule();
        TableView taOfficeHoursTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        taOfficeHoursTableView.setItems(data.getGradTAS());
        up();
    }
    public void editTA(TeachingAssistantPrototype ta){
        showEditTADialog(app.getGUIModule().getWindow(), OH_EDIT_TA_TITLE , OH_EDIT_TA_TEXT, OH_TOGGLE_UNDERGRADUATE_TEXT, OH_TOGGLE_GRADUATE_TEXT, OH_NAME_TABLE_COLUMN_TEXT, OH_EMAIL_TABLE_COLUMN_TEXT, OH_EDIT_TA_TYPE, ta);
    }
    public static void showEditTADialog(Stage parent,Object titleProperty, Object contentProperty, 
        Object undergradProperty,Object gradProperty,Object nameFiedProperty, Object emailFiedProperty, Object typeProperty, 
        TeachingAssistantPrototype ta){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String title = props.getProperty(titleProperty);
        String contentText = props.getProperty(contentProperty);
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setContentText(contentText);
        //set textfield
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        RadioButton undergrad = new RadioButton(props.getProperty(undergradProperty));
        RadioButton grad = new RadioButton(props.getProperty(gradProperty));
        ToggleGroup toggleGroup = new ToggleGroup();
        undergrad.setToggleGroup(toggleGroup);
        grad.setToggleGroup(toggleGroup);
        HBox combinBox = new HBox(undergrad,grad);
        TextField name = new TextField();
        name.setText(ta.getName());
        TextField email = new TextField();
        email.setText(ta.getEmail());
        grid.add(new Label(props.getProperty(contentProperty)), 0, 0);
        grid.add(new Label(props.getProperty(nameFiedProperty)), 0, 1);
        grid.add(name, 1, 1);
        grid.add(new Label(props.getProperty(emailFiedProperty)), 0, 2);
        grid.add(email, 1, 2);
        grid.add(new Label(props.getProperty(typeProperty)),0,3);
        grid.add(combinBox, 1, 3);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Node loginButton = dialog.getDialogPane().lookupButton(ButtonType.OK);
        loginButton.setDisable(true);

        dialog.showAndWait();
    }
}
