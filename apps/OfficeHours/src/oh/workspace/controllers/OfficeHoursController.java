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
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
import static oh.OfficeHoursPropertyType.OH_ADD_TA_BUTTON;
import static oh.OfficeHoursPropertyType.OH_EDIT_TA_TEXT;
import static oh.OfficeHoursPropertyType.OH_EDIT_TA_TITLE;
import static oh.OfficeHoursPropertyType.OH_EDIT_TA_TYPE;
import static oh.OfficeHoursPropertyType.OH_EMAIL_ERROR_TEXT;
import static oh.OfficeHoursPropertyType.OH_EMAIL_TABLE_COLUMN_TEXT;
import static oh.OfficeHoursPropertyType.OH_NAME_ERROR_TEXT;
import static oh.OfficeHoursPropertyType.OH_NAME_TABLE_COLUMN_TEXT;
import static oh.OfficeHoursPropertyType.OH_TAS_TABLE_VIEW;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_ALL;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_GRADUATE;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_GRADUATE_TEXT;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_UNDERGRADUATE;
import static oh.OfficeHoursPropertyType.OH_TOGGLE_UNDERGRADUATE_TEXT;
import oh.transactions.EditTA_Transaction;
import static oh.workspace.foolproof.OfficeHoursFoolproofDesign.VALID_EMAIL_ADDRESS_REGEX;
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
        Iterator<TimeSlot> timeSlotsIterator = data.officeHoursIterator();
        up();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            timeSlot.setToAll();
        }
        
        
    }
    public void changeToUndergraduate (){
        AppGUIModule gui = app.getGUIModule();
        TableView taOfficeHoursTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        taOfficeHoursTableView.setItems(data.getUndergradTAS());
        Iterator<TimeSlot> timeSlotsIterator = data.officeHoursIterator();
        up();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            timeSlot.setToUndergrad();
        }
    }
    public void changeToGrad(){
        AppGUIModule gui = app.getGUIModule();
        TableView taOfficeHoursTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        taOfficeHoursTableView.setItems(data.getGradTAS());
        Iterator<TimeSlot> timeSlotsIterator = data.officeHoursIterator();
        up();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            timeSlot.setToGrad();
        }
        
    }
    public void editTA(TeachingAssistantPrototype ta){
        showEditTADialog(app.getGUIModule().getWindow(), OH_EDIT_TA_TITLE , OH_EDIT_TA_TEXT, OH_TOGGLE_UNDERGRADUATE_TEXT, OH_TOGGLE_GRADUATE_TEXT, OH_NAME_TABLE_COLUMN_TEXT, OH_EMAIL_TABLE_COLUMN_TEXT, OH_EDIT_TA_TYPE, ta);
    }
    public  void showEditTADialog(Stage parent,Object titleProperty, Object contentProperty, 
        Object undergradProperty,Object gradProperty,Object nameFiedProperty, Object emailFiedProperty, Object typeProperty, 
        TeachingAssistantPrototype ta){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String title = props.getProperty(titleProperty);
        String contentText = props.getProperty(contentProperty);
        // Create the custom dialog.
        Dialog dialog = new Dialog<>();
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
        if (ta.getType().equals("Undergraduate")) {
            undergrad.setSelected(true);
        }else{
            grad.setSelected(true);
        }
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
        Node oKButton = dialog.getDialogPane().lookupButton(ButtonType.OK);
        oKButton.setDisable(true);
        name.setOnKeyPressed((event) -> {
             validateInput(name, email, oKButton,undergrad,grad,ta);
        });
        name.setOnKeyReleased((event) -> {
             validateInput(name, email, oKButton,undergrad,grad,ta);
     
        });
        email.setOnKeyPressed((event) -> {
             validateInput(name, email, oKButton,undergrad,grad,ta);
        });
        email.setOnKeyReleased((event) -> {
             validateInput(name, email, oKButton,undergrad,grad,ta);
     
        });
        
        grad.setOnAction((event) -> {
             validateInput(name, email, oKButton,undergrad,grad,ta);            
            
        });
        undergrad.setOnAction((event) -> {
             validateInput(name, email, oKButton,undergrad,grad,ta);            
        });
        Optional<ButtonType> result = dialog.showAndWait();
        if(!result.isPresent()){
            // alert is exited, no button has been pressed.
        }else if(result.get() == ButtonType.OK){
            //oke button is pressed
            OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
            if (undergrad.isSelected()) {
                EditTA_Transaction editTA_Transaction = new 
                EditTA_Transaction(data, ta, name.getText(), email.getText(), "Undergraduate");
                app.processTransaction(editTA_Transaction);
            }else{
                EditTA_Transaction editTA_Transaction = new 
                EditTA_Transaction(data, ta, name.getText(), email.getText(), "Graduate");
                app.processTransaction(editTA_Transaction);
            }
            data.refreshOH();
            
        }else if(result.get() == ButtonType.CANCEL){
            // cancel button is pressed
        }
        
        
    }
    
    public void validateInput(TextField nameField, TextField emailField,Node node,
        RadioButton undergrad, RadioButton grad,TeachingAssistantPrototype ta){
        node.setDisable(true);
        String name = nameField.getText().trim();
        String email =emailField.getText();
        final String invalidCSS = "-fx-focus-color: red;-fx-faint-focus-color: #ff000022;-fx-text-fill: red;";
        final String validCSS="-fx-focus-color: #039ED3;-fx-faint-focus-color: #039ED322;-fx-text-fill: black;";
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        OfficeHoursData officeHoursData = (OfficeHoursData) app.getDataComponent();
        nameField.setStyle(invalidCSS);
        emailField.setStyle(invalidCSS);
        if(name.equals(ta.getName())){
                if (email.equals(ta.getEmail())) {
                    if (ta.getType().equals("Undergraduate")&&undergrad.isSelected()==false) {
                        nameField.setStyle(validCSS);
                        emailField.setStyle(validCSS);
                        node.setDisable(false);
                        }else if (ta.getType().equals("Graduate")&&grad.isSelected()==false){
                            nameField.setStyle(validCSS);
                            emailField.setStyle(validCSS);
                            node.setDisable(false);
                        }         
                }else{
                    if (!matcher.find()==true) {
                        nameField.setStyle(invalidCSS);
                        emailField.setStyle(invalidCSS);
                    }else{
                        nameField.setStyle(validCSS);
                        emailField.setStyle(validCSS);
                        node.setDisable(false);  
                    }
                }
        }
        else if (matcher.find()==true&&officeHoursData.getTAWithName(name)==null) {
                node.setDisable(false);
                nameField.setStyle(validCSS);
                emailField.setStyle(validCSS);                
        }else{
               if (officeHoursData.getTAWithName(name)!=null||name.equals("")) {
                   
                }else{
                    nameField.setStyle(validCSS);
                }
                if(matcher.find()==true){
                    emailField.setStyle(validCSS);
                }
                
        }
    }
}
