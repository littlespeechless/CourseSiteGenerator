/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.controllers;

import csg.CourseSiteGenerateApp;
import static csg.CourseSitePropertyType.OH_EDIT_TA_TEXT;
import static csg.CourseSitePropertyType.OH_EDIT_TA_TITLE;
import static csg.CourseSitePropertyType.OH_EDIT_TA_TYPE;
import csg.transactions.AddTA_Transaction;
import static csg.CourseSitePropertyType.OH_EMAIL_ERROR_TEXT;
import static csg.CourseSitePropertyType.OH_EMAIL_TABLE_COLUMN_TEXT;
import static csg.CourseSitePropertyType.OH_EMAIL_TEXT_FIELD;
import static csg.CourseSitePropertyType.OH_END_TIME_COMBO_BOX;
import static csg.CourseSitePropertyType.OH_NAME_ERROR_TEXT;
import static csg.CourseSitePropertyType.OH_NAME_TABLE_COLUMN_TEXT;
import static csg.CourseSitePropertyType.OH_NAME_TEXT_FIELD;
import static csg.CourseSitePropertyType.OH_TAS_TABLE_VIEW;
import static csg.CourseSitePropertyType.OH_TOGGLE_GRADUATE;
import static csg.CourseSitePropertyType.OH_TOGGLE_GRADUATE_TEXT;
import static csg.CourseSitePropertyType.OH_TOGGLE_UNDERGRADUATE;
import static csg.CourseSitePropertyType.OH_TOGGLE_UNDERGRADUATE_TEXT;
import static csg.CourseSitePropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSitePropertyType.OH_START_TIME_COMBO_BOX;
import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.transactions.AddTimeSlot_Transaction;
import csg.transactions.ChangeOHTable_Transaction;
import csg.transactions.EditTA_Transaction;
import csg.transactions.RemvTA_Transaction;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author zhengyu
 */
public class CourseSiteController {
    CourseSiteGenerateApp app;
    public CourseSiteController(CourseSiteGenerateApp initApp){
        app = initApp;
    }
    
    
    /*
    * OH PAGE  CONTROLLS 
    */
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
            CourseSiteData data = (CourseSiteData) app.getDataComponent();
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
    public  void processRemoveTA(TeachingAssistantPrototype ta){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        RemvTA_Transaction removeTA_Transaction = new RemvTA_Transaction(data, ta);
        app.processTransaction(removeTA_Transaction);
    }
    public void processAddTimeslot(TeachingAssistantPrototype ta, int column,TimeSlot dataSlot){
        AppGUIModule gui = app.getGUIModule();
        //TimeSlot data = (TimeSlot) app.getDataComponent();
        TimeSlot dataTimeSlot = dataSlot;
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        AddTimeSlot_Transaction addTimeSlot_Transaction = new AddTimeSlot_Transaction(data,dataTimeSlot, ta,column);
        app.processTransaction(addTimeSlot_Transaction);
    }
    public void changeTimeRange(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        TableView officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        TimeSlot begin = (TimeSlot)officeHoursTableView.getItems().get(0);
        TimeSlot end = (TimeSlot) officeHoursTableView.getItems().get(officeHoursTableView.getItems().size()-1);
        String oldStartTime = begin.getStartTime();
        String oldEndTime = end.getEndTime();
        ComboBox startTime = (ComboBox)gui.getGUINode(OH_START_TIME_COMBO_BOX);
        ComboBox endTime = (ComboBox)gui.getGUINode(OH_END_TIME_COMBO_BOX);
        String newStartTime = (String)startTime.getValue();
        String newEndTime = (String)endTime.getValue();
        int startIndex = data.getStartTimeslotIndex(newStartTime);
        int endIndex = data.getEndTimeslotIndex(newEndTime);
        
            if (oldStartTime.equals(newStartTime)&&oldEndTime.equals(newEndTime)) {
            
            }else{
                ChangeOHTable_Transaction changeOHTable_Transaction = new ChangeOHTable_Transaction(
                app, data, oldStartTime, oldEndTime, newStartTime, newEndTime, 
                officeHoursTableView.getItems());
                app.processTransaction(changeOHTable_Transaction);
            }
    }
    public int getHours(String selected){
        int hour = 0;
         if (selected!=null){
                if (selected.contains("pm")) {
                    if (selected.contains("12")) {
                        hour = 12;
                    }else{
                        hour = 12+Integer.parseInt(selected.substring(0,selected.indexOf(":")));
                    }
                }else{
                    hour=Integer.parseInt(selected.substring(0,selected.indexOf(":")));
                }
         }
         return hour;
    }

    public void changeToAll(){
        AppGUIModule gui = app.getGUIModule();
        TableView taOfficeHoursTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
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
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
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
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
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
    public void showEditTADialog(Stage parent,Object titleProperty, Object contentProperty, 
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
            CourseSiteData data = (CourseSiteData) app.getDataComponent();
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
        CourseSiteData officeHoursData = (CourseSiteData) app.getDataComponent();
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
    public void highlightOH(TeachingAssistantPrototype ta){
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> tableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        data.refreshOH();
        for(int i =2; i<tableView.getColumns().size();i++){
            TableColumn column = tableView.getColumns().get(i);
            column.setCellFactory(col -> {
                    return new TableCell<TimeSlot, String>() {
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item == null || empty) {
                                setText(null);
                                setStyle("-fx-background-color: none");
                            } else {
                                if (item.toUpperCase().contains(ta.getName().toUpperCase())) {
                                    setText(item);
                                    setStyle("-fx-background-color: #87cefa");
                                }else{
                                    setText(item);
                                    setStyle("-fx-background-color: none");
                                }
                                
                            }
                        }
                    };
                });
        }
        data.refreshOH();
        
        
    }
}
