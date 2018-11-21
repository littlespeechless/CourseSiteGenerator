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
import static csg.CourseSitePropertyType.SC_ADD_ITEM_BUTTON;
import static csg.CourseSitePropertyType.SC_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_END_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_LINK_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_SCHEDULE_TABLEVIEW;
import static csg.CourseSitePropertyType.SC_TITLE_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_TOPIC_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_TYPE_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_NUMBER_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_SUBJECT_COMBO_BOX;
import csg.data.CourseSiteData;
import csg.data.Lab;
import csg.data.Lecture;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.transactions.AddLab_Transaction;
import csg.transactions.AddLecture_Transaction;
import csg.transactions.AddRecitation_Transaction;
import csg.transactions.AddSchedule_Transaction;
import csg.transactions.AddTimeSlot_Transaction;
import csg.transactions.ChangeCheckBox_Transaction;
import csg.transactions.ChangeComboBox_Transaction;
import csg.transactions.ChangeDate_Transaction;
import csg.transactions.ChangeImage_Transaction;
import csg.transactions.ChangeNumber_Transaction;
import csg.transactions.ChangeOHTable_Transaction;
import csg.transactions.ChangeSubject_Transaction;
import csg.transactions.ChangeTextArea_Transaction;
import csg.transactions.ChangeTextField_Transaction;
import csg.transactions.EditLab_Transaction;
import csg.transactions.EditLecture_Transaction;
import csg.transactions.EditRecitation_Transaction;
import csg.transactions.EditSchedule_Transaction;
import csg.transactions.EditTA_Transaction;
import csg.transactions.RemvLab_Transaction;
import csg.transactions.RemvLecture_Transaction;
import csg.transactions.RemvRecitation_Transaction;
import csg.transactions.RemvSchedule_Transaction;
import csg.transactions.RemvTA_Transaction;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static csg.CourseSitePropertyType.SC_START_DATE_DATE_PICKER;
import java.time.Month;

/**
 *
 * @author zhengyu
 */
public class CourseSiteController {
    CourseSiteGenerateApp app;
    LocalDate oldStartDate;
    LocalDate oldendDate;
    String oldNumber;
    String oldSubject;
    String oldFieldText;
    String oldAreaText;
    String oldComboBox;
    public CourseSiteController(CourseSiteGenerateApp initApp){
        app = initApp;
        AppGUIModule gui = app.getGUIModule();
        //oldStartDate = (LocalDate) ((DatePicker)gui.getGUINode(SC_START_DATE_DATE_PICKER)).getValue();
        //oldendDate = (LocalDate) ((DatePicker)gui.getGUINode(SC_END_DATE_DATE_PICKER)).getValue();
        oldStartDate = LocalDate.MIN;
        oldendDate = LocalDate.MAX;
        oldNumber = "";
        oldSubject= "";
        oldFieldText = "";
        oldAreaText = "";
        oldComboBox = "";
        
    }
    /*
    * SITE PAGE  CONTROLLS 
    */
    public void processSubjectChange(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox subjectBox = (ComboBox) gui.getGUINode(SITE_SUBJECT_COMBO_BOX);
        String newSubject = subjectBox.getEditor().getText();
        if (!oldSubject.equals(newSubject)){
            System.out.println(newSubject);
            ChangeSubject_Transaction cst =new ChangeSubject_Transaction(data, oldSubject,newSubject, subjectBox);
            app.processTransaction(cst);
        }
    }
    public void setOldSubject(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox subjectBox = (ComboBox) gui.getGUINode(SITE_SUBJECT_COMBO_BOX);
        if (subjectBox.getValue()==null) {
            oldSubject = "";
        }else{
            oldSubject =(String) subjectBox.getValue();
        }
        
    }
    public void processNumberChange(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox numberBox = (ComboBox) gui.getGUINode(SITE_NUMBER_COMBO_BOX);
        String newNumber = numberBox.getEditor().getText();
        if (!oldNumber.equals(newNumber)) {
            ChangeNumber_Transaction cnt = new ChangeNumber_Transaction(data, oldNumber,newNumber, numberBox);
            app.processTransaction(cnt);
        }
    }
    public void setOldNumber(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox numberBox = (ComboBox) gui.getGUINode(SITE_NUMBER_COMBO_BOX);
        oldNumber = numberBox.getEditor().getText();
    }
    public void processComboBoxChange(ComboBox comboBox){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        String newString = comboBox.getEditor().getText();
        if (!oldComboBox.equals(newString)) {
            ChangeComboBox_Transaction ccbt = new ChangeComboBox_Transaction(data, oldComboBox, newString, comboBox);
            app.processTransaction(ccbt);
        }
    }
    public void setComboBox(ComboBox box){
        oldComboBox = box.getEditor().getText();
    }
    public void processTextFieldChange(TextField textField){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        String newText = textField.getText();
        if (!oldFieldText.equals(newText)) {
            ChangeTextField_Transaction ctft = new ChangeTextField_Transaction(data, newText, oldFieldText, textField);
            app.processTransaction(ctft);
        }
    }
    public void setOldFieldText(TextField textField){
        oldFieldText = textField.getText();
    }
    public void processTextAreaChange(TextArea textArea){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        String newText = textArea.getText();
        if(!oldAreaText.equals(newText)){
            ChangeTextArea_Transaction ctat = new ChangeTextArea_Transaction(data, newText, oldAreaText, textArea);
            app.processTransaction(ctat);
        }
    }
    public void setOldAreaText(TextArea textArea){
        oldAreaText = textArea.getText();
    }
    public void processCheckboxChange(CheckBox checkBox){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        ChangeCheckBox_Transaction ccbt = new ChangeCheckBox_Transaction(data, checkBox, checkBox.isSelected());
        app.processTransaction(ccbt);
    }
    public void changeImages(Image newimImage,ImageView imageView){
        ChangeImage_Transaction cit = new ChangeImage_Transaction(newimImage, imageView);
        app.processTransaction(cit);
    }
    /*
    * MT PAGE  CONTROLLS 
    */
    public void processAddLecture(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        Lecture lecture = new Lecture("?", "?","?", "?");
        AddLecture_Transaction addLecture_Transaction = new AddLecture_Transaction(
                data, lecture);
        app.processTransaction(addLecture_Transaction);
    }
    public void processRemoveLecture(Lecture lecture){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        RemvLecture_Transaction lecture_Transaction = new RemvLecture_Transaction(data, lecture);
        app.processTransaction(lecture_Transaction);

    }
    public void processChangeLecture(String newSection, String newDays, 
            String newTime, String newRoom, Lecture lecture){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        EditLecture_Transaction editLecture_Transaction1 = new EditLecture_Transaction
        (data, lecture, newSection, newDays, newTime, newRoom);
        app.processTransaction(editLecture_Transaction1);

    }
    public void processAddRecitation(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        Recitation recitation = new Recitation("?", "?", "?", "?", "?");
        AddRecitation_Transaction recitation_Transaction = new AddRecitation_Transaction(data, recitation);
        app.processTransaction(recitation_Transaction);
    }
    public void processRemoveRecitation(Recitation recitation){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        RemvRecitation_Transaction recitation_Transaction = new RemvRecitation_Transaction(data, recitation);
        app.processTransaction(recitation_Transaction);
    }
    public void processChangeRecitation(String newSection, String newDayTime, 
            String newRoom, String TA1,String TA2, Recitation recitation){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        EditRecitation_Transaction recitation_Transaction = new EditRecitation_Transaction(data, recitation, newSection, newDayTime, newRoom, TA1, TA2);
        app.processTransaction(recitation_Transaction);
    }
    public void processAddLab(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        Lab lab = new Lab("?", "?", "?", "?", "?");
        AddLab_Transaction addLab_Transaction = new AddLab_Transaction(data, lab);
        app.processTransaction(addLab_Transaction);
    }
    public void processRemoveLab(Lab lab){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        RemvLab_Transaction remvLab_Transaction =new RemvLab_Transaction(data, lab);
        app.processTransaction(remvLab_Transaction);
    }
    public void processChangeLab(String newSection, String newDayTime, 
            String newRoom, String TA1,String TA2, Lab lab){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        EditLab_Transaction editLab_Transaction = new EditLab_Transaction(data, lab, newSection, newDayTime, newRoom, TA1, TA2);
        app.processTransaction(editLab_Transaction);
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
    public void updateOH(){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        data.refreshOH();
    }
    /*******
     * SCHEDULE CONTROL
     */
    public void processAddOrUpdateSchedule(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        if (((Button) gui.getGUINode(SC_ADD_ITEM_BUTTON)).getText().equals("Add")){
            ComboBox type = (ComboBox) gui.getGUINode(SC_TYPE_COMBO_BOX);
            DatePicker date = (DatePicker) gui.getGUINode(SC_DATE_DATE_PICKER);
            TextField title = (TextField)gui.getGUINode(SC_TITLE_TEXT_FIELD);
            TextField topic = (TextField)gui.getGUINode(SC_TOPIC_TEXT_FIELD);
            TextField link = (TextField)gui.getGUINode(SC_LINK_TEXT_FIELD);
            String newType = (String) type.getValue();
            String newDate = date.getValue().getYear()+"-"+date.getValue().getMonthValue()
                    +"-"+date.getValue().getDayOfMonth();
            String newTitle = title.getText();
            String newTopic = topic.getText();
            String newLink = link.getText();
            Schedule schedule = new Schedule(newType, newDate, newTitle, newTopic, newLink);
            AddSchedule_Transaction addSchedule_Transaction = new AddSchedule_Transaction(data, schedule);
            app.processTransaction(addSchedule_Transaction);
        }else{
            ComboBox type = (ComboBox) gui.getGUINode(SC_TYPE_COMBO_BOX);
            DatePicker date = (DatePicker) gui.getGUINode(SC_DATE_DATE_PICKER);
            TextField title = (TextField)gui.getGUINode(SC_TITLE_TEXT_FIELD);
            TextField topic = (TextField)gui.getGUINode(SC_TOPIC_TEXT_FIELD);
            TextField link = (TextField)gui.getGUINode(SC_LINK_TEXT_FIELD);
            Schedule schedule = (Schedule)((TableView) gui.getGUINode(SC_SCHEDULE_TABLEVIEW)).getSelectionModel().getSelectedItem();
            String newType = (String) type.getValue();
            String newDate = date.getValue().getYear()+"-"+date.getValue().getMonthValue()
                    +"-"+date.getValue().getDayOfMonth();
            String newTitle = title.getText();
            String newTopic = topic.getText();
            String newLink = link.getText();
            EditSchedule_Transaction editSchedule_Transaction = new EditSchedule_Transaction
                (data, schedule, newType, newDate, newTitle, newTopic, newLink);
            app.processTransaction(editSchedule_Transaction);
        }
        
    }
    public void processRemoveSchedule(Schedule schedule){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        RemvSchedule_Transaction remvSchedule_Transaction = new RemvSchedule_Transaction(data, schedule);
        app.processTransaction(remvSchedule_Transaction);
    }
    public void updateScheduleField(Schedule schedule){
            AppGUIModule gui = app.getGUIModule();
            ComboBox type = (ComboBox) gui.getGUINode(SC_TYPE_COMBO_BOX);
            DatePicker date = (DatePicker) gui.getGUINode(SC_DATE_DATE_PICKER);
            TextField title = (TextField)gui.getGUINode(SC_TITLE_TEXT_FIELD);
            TextField topic = (TextField)gui.getGUINode(SC_TOPIC_TEXT_FIELD);
            TextField link = (TextField)gui.getGUINode(SC_LINK_TEXT_FIELD);
            type.setValue(schedule.getType());
            String month = schedule.getDate().substring(schedule.getDate().indexOf("-")+1,schedule.getDate().lastIndexOf("-"));
            String year = schedule.getDate().substring(0,schedule.getDate().indexOf("-"));
            String day = schedule.getDate().substring(schedule.getDate().lastIndexOf("-")+1);
            date.setValue(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)));
            title.textProperty().setValue(schedule.getTitle());
            topic.textProperty().setValue(schedule.getTopic());
            link.textProperty().setValue(schedule.getLink());
            ((Button) gui.getGUINode(SC_ADD_ITEM_BUTTON)).setText("Update");
    }
    public void  processChangeStartDate(LocalDate oldDate, LocalDate newDate){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        if (newDate!=oldStartDate) {
            
            ChangeDate_Transaction cdt = new ChangeDate_Transaction(data, oldDate, newDate, 
                    ((DatePicker)gui.getGUINode(SC_START_DATE_DATE_PICKER)));
            app.processTransaction(cdt);
            
        }
        oldStartDate = oldDate;
    }
    public void  processChangeEndDate(LocalDate oldDate, LocalDate newDate){
        CourseSiteData data = (CourseSiteData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        if (newDate!=oldendDate) {
            ChangeDate_Transaction cdt = new ChangeDate_Transaction(data, oldDate, newDate, 
                    ((DatePicker)gui.getGUINode(SC_END_DATE_DATE_PICKER)));
            app.processTransaction(cdt);
            
        }
        oldendDate = oldDate;
    }
}
