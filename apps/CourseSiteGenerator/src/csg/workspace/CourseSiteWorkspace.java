/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;
import csg.CourseSiteGenerateApp;
import static csg.CourseSitePropertyType.CSG_FOOLPROOF_SETTINGS;
import static csg.CourseSitePropertyType.CSG_GRID_PANE;
import static csg.CourseSitePropertyType.CSG_MEETING;
import static csg.CourseSitePropertyType.CSG_OH;
import static csg.CourseSitePropertyType.CSG_SCHEDULE;
import static csg.CourseSitePropertyType.CSG_SCROLL_PANE;
import static csg.CourseSitePropertyType.CSG_SITE;
import static csg.CourseSitePropertyType.CSG_SYLLABUS;
import static csg.CourseSitePropertyType.OH_ADD_TA_BUTTON;
import static csg.CourseSitePropertyType.OH_BOX;
import static csg.CourseSitePropertyType.OH_EMAIL_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_EMAIL_TEXT_FIELD;
import static csg.CourseSitePropertyType.OH_END_TIME_COMBO_BOX;
import static csg.CourseSitePropertyType.OH_END_TIME_LABLE;
import static csg.CourseSitePropertyType.OH_END_TIME_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_FRIDAY_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_MONDAY_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_NAME_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_NAME_TEXT_FIELD;
import static csg.CourseSitePropertyType.OH_OFFICE_HOURS_BOX;
import static csg.CourseSitePropertyType.OH_OFFICE_HOURS_HEADER_BOX;
import static csg.CourseSitePropertyType.OH_OFFICE_HOURS_HEADER_LABEL;
import static csg.CourseSitePropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSitePropertyType.OH_REMOVE_TA_BUTTON;
import static csg.CourseSitePropertyType.OH_SLOTS_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_START_TIME_COMBO_BOX;
import static csg.CourseSitePropertyType.OH_START_TIME_LABLE;
import static csg.CourseSitePropertyType.OH_START_TIME_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_TAS_HEADER_LABEL;
import static csg.CourseSitePropertyType.OH_TAS_TABLE_VIEW;
import static csg.CourseSitePropertyType.OH_TAS_TOGGLE;
import static csg.CourseSitePropertyType.OH_TA_BOX;
import static csg.CourseSitePropertyType.OH_THURSDAY_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_TOGGLE_ALL;
import static csg.CourseSitePropertyType.OH_TOGGLE_GRADUATE;
import static csg.CourseSitePropertyType.OH_TOGGLE_UNDERGRADUATE;
import static csg.CourseSitePropertyType.OH_TUESDAY_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_TYPE_TABLE_COLUMN;
import static csg.CourseSitePropertyType.OH_WEDNESDAY_TABLE_COLUMN;
import static csg.CourseSitePropertyType.SITE_BANNER;
import static csg.CourseSitePropertyType.SITE_BANNER_BOX;
import static csg.CourseSitePropertyType.SITE_CSS;
import static csg.CourseSitePropertyType.SITE_CSS_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_EXPORT;
import static csg.CourseSitePropertyType.SITE_FAVICON;
import static csg.CourseSitePropertyType.SITE_FAVICON_IMG;
import static csg.CourseSitePropertyType.SITE_HOME;
import static csg.CourseSitePropertyType.SITE_HWS;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_BOX;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_EMAIL;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_EMAIL_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_HOMEPAGE;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_NAME;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_NAME_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_OH;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_OH_TEXT_AREA;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_ROOM;
import static csg.CourseSitePropertyType.SITE_INSTRUCTOR_ROOM_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_LEFT_FOOTER;
import static csg.CourseSitePropertyType.SITE_LEFT_FOOTER_IMG;
import static csg.CourseSitePropertyType.SITE_NAVBAR;
import static csg.CourseSitePropertyType.SITE_NAVBAR_IMG;
import static csg.CourseSitePropertyType.SITE_NOTE;
import static csg.CourseSitePropertyType.SITE_NUMBER;
import static csg.CourseSitePropertyType.SITE_NUMBER_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_PAGE;
import static csg.CourseSitePropertyType.SITE_PAGE_BOX;
import static csg.CourseSitePropertyType.SITE_RIGHT_FOOTER;
import static csg.CourseSitePropertyType.SITE_RIGHT_FOOTER_IMG;
import static csg.CourseSitePropertyType.SITE_SCHEDULE;
import static csg.CourseSitePropertyType.SITE_SEMESTER;
import static csg.CourseSitePropertyType.SITE_SEMESTER_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_STYLE;
import static csg.CourseSitePropertyType.SITE_STYLE_BOX;
import static csg.CourseSitePropertyType.SITE_SUBJECT;
import static csg.CourseSitePropertyType.SITE_SUBJECT_COMBO_BOX;
import static csg.CourseSitePropertyType.SITE_SYLLABUS;
import static csg.CourseSitePropertyType.SITE_TITLE;
import static csg.CourseSitePropertyType.SITE_TITLE_TEXT_FIELD;
import static csg.CourseSitePropertyType.SITE_YEAR;
import static csg.CourseSitePropertyType.SITE_YEAR_COMBO_BOX;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.workspace.controllers.CourseSiteController;
import csg.workspace.foolproof.CourseSiteFoolproofDesign;
import static csg.workspace.style.CSGStyle.CLASS_CHECK_BOX_LABLE;
import static csg.workspace.style.CSGStyle.CLASS_COMBO_BOX_LABLE;
import static csg.workspace.style.CSGStyle.CLASS_CSG_GRID_PANE;
import static csg.workspace.style.CSGStyle.CLASS_CSG_HBOX;
import static csg.workspace.style.CSGStyle.CLASS_CSG_SCROLL_PANE;
import static csg.workspace.style.CSGStyle.CLASS_CSG_SUB_GRID_PANE;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TAB;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TAB_GRID_PANE;
import static csg.workspace.style.CSGStyle.CLASS_OH_BUTTON;
import static csg.workspace.style.CSGStyle.CLASS_OH_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_OH_DAY_OF_WEEK_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_OH_HEADER_LABEL;
import static csg.workspace.style.CSGStyle.CLASS_OH_OFFICE_HOURS_TABLE_VIEW;
import static csg.workspace.style.CSGStyle.CLASS_OH_RADIO_BUTTON;
import static csg.workspace.style.CSGStyle.CLASS_OH_TABLE_VIEW;
import static csg.workspace.style.CSGStyle.CLASS_OH_TEXT_FIELD;
import static csg.workspace.style.CSGStyle.CLASS_OH_TIME_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_OH_VBOX;
import static csg.workspace.style.CSGStyle.CLASS_SITE_ICON_BUTTON;
import static csg.workspace.style.CSGStyle.CLASS_SITE_LABLE;
import static csg.workspace.style.CSGStyle.CLASS_SITE_TITLE_LABLE;
import djf.AppTemplate;
import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author zhengyu
 */
public class CourseSiteWorkspace extends AppWorkspaceComponent{

    public CourseSiteWorkspace(AppTemplate initApp) {
        super(initApp);
    }
    
    public CourseSiteWorkspace(CourseSiteGenerateApp app) {
        super(app);

        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();

        // SETUP FOOLPROOF DESIGN FOR THIS APP
        initFoolproofDesign();
        
    }
    private void initLayout(){
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder csgBuilder = app.getGUIModule().getNodesBuilder();
       
        //Create TabPane for the application
        TabPane siteTabPane = new TabPane();
        
        //Site TAB
        Tab siteTab = csgBuilder.buildTap(CSG_SITE, workspace, CLASS_CSG_TAB, ENABLED);
        
        //banner 
        HBox bannerBox = csgBuilder.buildHBox(SITE_BANNER_BOX, null, CLASS_CSG_HBOX, ENABLED);
        GridPane bannerPane = csgBuilder.buildGridPane(CSG_GRID_PANE, bannerBox, CLASS_CSG_GRID_PANE, ENABLED);
        Label bannerLable = csgBuilder.buildLabel(SITE_BANNER, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label subjectLabel = csgBuilder.buildLabel(SITE_SUBJECT, null, CLASS_SITE_LABLE, ENABLED);
        Label numberLabel = csgBuilder.buildLabel(SITE_NUMBER, null, CLASS_SITE_LABLE, ENABLED);
        Label semesterLabel = csgBuilder.buildLabel(SITE_SEMESTER, null, CLASS_SITE_LABLE, ENABLED);
        Label yearLabel = csgBuilder.buildLabel(SITE_YEAR, null, CLASS_SITE_LABLE, ENABLED);
        Label exportLabel = csgBuilder.buildLabel(SITE_EXPORT, null, CLASS_SITE_LABLE, ENABLED);
        Label titleLabel = csgBuilder.buildLabel(SITE_TITLE, null, CLASS_SITE_LABLE, ENABLED);
        ComboBox subjecCombo = csgBuilder.buildComboBox(SITE_SUBJECT_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        ComboBox numberCombo = csgBuilder.buildComboBox(SITE_NUMBER_COMBO_BOX,  null, CLASS_COMBO_BOX_LABLE, ENABLED);
        ComboBox semesterCombo = csgBuilder.buildComboBox(SITE_SEMESTER_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        ComboBox yearCombo = csgBuilder.buildComboBox(SITE_YEAR_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        subjecCombo.setEditable(ENABLED);
        numberCombo.setEditable(ENABLED);
        semesterCombo.setEditable(ENABLED);
        yearCombo.setEditable(ENABLED);
        TextField titleField = csgBuilder.buildTextField(SITE_TITLE_TEXT_FIELD, null, "", ENABLED);
        bannerPane.add(bannerLable, 0, 0);
        GridPane firstRow = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_SUB_GRID_PANE, ENABLED);
        firstRow.add(subjectLabel, 0, 0);
        firstRow.add(subjecCombo,1,0);
        firstRow.add(numberLabel,2,0);
        firstRow.add(numberCombo,3,0);
        firstRow.add(semesterLabel, 0, 1);
        firstRow.add(semesterCombo, 1, 1);
        firstRow.add(yearLabel, 2, 1);
        firstRow.add(yearCombo, 3, 1);
        firstRow.add(titleLabel, 0, 2);
        firstRow.add(titleField, 1, 2);
        bannerPane.add(firstRow, 0, 1);
        bannerPane.add(exportLabel, 0, 2);
        //pages
        HBox pagesBox = csgBuilder.buildHBox(SITE_PAGE_BOX, null, CLASS_CSG_HBOX, ENABLED);
        Label pageLabel = csgBuilder.buildLabel(SITE_PAGE, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        CheckBox homeCheckbox = csgBuilder.buildCheckBox(SITE_HOME, null, CLASS_CHECK_BOX_LABLE, ENABLED);
        CheckBox syllabusCheckbox = csgBuilder.buildCheckBox(SITE_SYLLABUS, null, CLASS_CHECK_BOX_LABLE, ENABLED);
        CheckBox scheduleCheckbox = csgBuilder.buildCheckBox(SITE_SCHEDULE, null, CLASS_CHECK_BOX_LABLE, ENABLED);
        CheckBox hwsCheckbox = csgBuilder.buildCheckBox(SITE_HWS, null, CLASS_CHECK_BOX_LABLE, ENABLED);
        GridPane pageGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, pagesBox, CLASS_CSG_GRID_PANE, ENABLED);
        pageGridPane.add(pageLabel, 0, 0);
        pageGridPane.add(homeCheckbox, 1, 0);
        pageGridPane.add(syllabusCheckbox, 2, 0);
        pageGridPane.add(scheduleCheckbox, 3, 0);
        pageGridPane.add(hwsCheckbox, 4, 0);
        
        // styles
        HBox styleBox = csgBuilder.buildHBox(SITE_STYLE_BOX, null, CLASS_CSG_HBOX, ENABLED);
        Label styleLabel = csgBuilder.buildLabel(SITE_STYLE, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label cssLabel = csgBuilder.buildLabel(SITE_CSS, null, CLASS_SITE_LABLE, ENABLED);
        Label noteLabel = csgBuilder.buildLabel(SITE_NOTE, null, CLASS_SITE_LABLE, ENABLED);
        Button faviconButton = csgBuilder.buildTextButton(SITE_FAVICON, null, CLASS_SITE_ICON_BUTTON, ENABLED);
        Button navbarButton = csgBuilder.buildTextButton(SITE_NAVBAR, null, CLASS_SITE_ICON_BUTTON, ENABLED);
        Button leftFooterButton = csgBuilder.buildTextButton(SITE_LEFT_FOOTER, null, CLASS_SITE_ICON_BUTTON, ENABLED);
        Button rightFooterButton = csgBuilder.buildTextButton(SITE_RIGHT_FOOTER, null, CLASS_SITE_ICON_BUTTON, ENABLED);
        ComboBox cssComboBox = csgBuilder.buildComboBox(SITE_CSS_COMBO_BOX, null, CLASS_SITE_ICON_BUTTON, ENABLED);
        Image favicon = csgBuilder.buildImage(SITE_FAVICON_IMG, null, ENABLED);
        Image navbar = csgBuilder.buildImage(SITE_NAVBAR_IMG, null, ENABLED);
        Image leftFooter = csgBuilder.buildImage(SITE_LEFT_FOOTER_IMG, null, ENABLED);
        Image rightFooter = csgBuilder.buildImage(SITE_RIGHT_FOOTER_IMG, null, ENABLED);
        GridPane styleGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, styleBox, CLASS_CSG_GRID_PANE, ENABLED);
        styleGridPane.add(styleLabel, 0, 0);
        GridPane iconspGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_SUB_GRID_PANE, ENABLED);
        iconspGridPane.add(faviconButton, 0, 0);
        iconspGridPane.add(new ImageView(favicon), 1, 0);
        iconspGridPane.add(navbarButton, 0, 1);
        iconspGridPane.add(new ImageView(navbar), 1, 1);
        iconspGridPane.add(leftFooterButton, 0, 2);
        iconspGridPane.add(new ImageView(leftFooter), 1, 2);
        iconspGridPane.add(rightFooterButton, 0, 3);
        iconspGridPane.add(new ImageView(rightFooter), 1, 3);
        iconspGridPane.add(cssLabel, 0, 4);
        iconspGridPane.add(cssComboBox, 1, 4);
        styleGridPane.add(iconspGridPane, 0, 1);
        styleGridPane.add(noteLabel, 0, 2);
        
        //INSTRCUTOR BOX
        HBox instructorBox = csgBuilder.buildHBox(SITE_INSTRUCTOR_BOX, null, CLASS_CSG_HBOX, ENABLED);
        Label instructorLabel = csgBuilder.buildLabel(SITE_INSTRUCTOR, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label instructorName = csgBuilder.buildLabel(SITE_INSTRUCTOR_NAME, null, CLASS_SITE_LABLE, ENABLED);
        Label instructorRoom = csgBuilder.buildLabel(SITE_INSTRUCTOR_ROOM, null, CLASS_SITE_LABLE, ENABLED);
        Label instructorEmail = csgBuilder.buildLabel(SITE_INSTRUCTOR_EMAIL, null, CLASS_SITE_LABLE, ENABLED);
        Label instructorHomePage = csgBuilder.buildLabel(SITE_INSTRUCTOR_HOMEPAGE, null, CLASS_SITE_LABLE, ENABLED);
        TextField instructorNameField = csgBuilder.buildTextField(SITE_INSTRUCTOR_NAME_TEXT_FIELD, null, "", ENABLED);
        TextField instructorRoomField = csgBuilder.buildTextField(SITE_INSTRUCTOR_ROOM_TEXT_FIELD, null, "", ENABLED);
        TextField instructorEmailField = csgBuilder.buildTextField(SITE_INSTRUCTOR_EMAIL_TEXT_FIELD, null, "", ENABLED);
        TextField instructorHomePageField = csgBuilder.buildTextField(SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD, null, "", ENABLED);
        TextArea instructorOHTextArea = csgBuilder.buildTextArea(SITE_INSTRUCTOR_OH_TEXT_AREA, null, "", ENABLED);
        TitledPane instructorOH = csgBuilder.buildTitledPane(SITE_INSTRUCTOR_OH, null, "", ENABLED);
        instructorOH.setContent(instructorOHTextArea);
        instructorOH.setExpanded(Boolean.FALSE);
        GridPane instructorGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, instructorBox, CLASS_CSG_GRID_PANE, ENABLED);
        instructorGridPane.add(instructorLabel, 0, 0);
        GridPane instructorSubGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_SUB_GRID_PANE, ENABLED);
        instructorSubGridPane.add(instructorName, 0, 0);
        instructorSubGridPane.add(instructorNameField, 1, 0);
        instructorSubGridPane.add(instructorRoom, 2, 0);
        instructorSubGridPane.add(instructorRoomField, 3, 0);
        instructorSubGridPane.add(instructorEmail, 0, 1);
        instructorSubGridPane.add(instructorEmailField, 1, 1);
        instructorSubGridPane.add(instructorHomePage, 2, 1);
        instructorSubGridPane.add(instructorHomePageField, 3, 1);
        instructorGridPane.add(instructorSubGridPane, 0, 1);
        instructorGridPane.add(instructorOH, 0, 2);
        //Combine all boxes in grid pane
        GridPane siteGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_TAB_GRID_PANE, ENABLED);
        siteGridPane.add(bannerBox, 0, 0);
        siteGridPane.add(pagesBox, 0, 1);
        siteGridPane.add(styleBox, 0, 2);
        siteGridPane.add(instructorBox, 0, 3);
        ScrollPane siteScrollPane = csgBuilder.buildScrollPane(CSG_SCROLL_PANE, null,CLASS_CSG_SCROLL_PANE , ENABLED);
        siteScrollPane.setContent(siteGridPane);
        siteTab.setContent(siteScrollPane);
        
        //SYLLBUS TAB
        Tab syllbusTab = csgBuilder.buildTap(CSG_SYLLABUS, workspace, CLASS_CSG_TAB, ENABLED);
        
        
        //MEETING TIME TAB
        Tab meetingTab = csgBuilder.buildTap(CSG_MEETING, workspace, CLASS_CSG_TAB, ENABLED);
        
        //OFFICE HOURS TAB
        Tab OHTab = csgBuilder.buildTap(CSG_OH, null, CLASS_CSG_TAB, ENABLED);
        VBox taVBox = csgBuilder.buildVBox(OH_TA_BOX, workspace, CLASS_OH_VBOX, ENABLED);
        Label taLabel = csgBuilder.buildLabel(OH_TAS_HEADER_LABEL, null, CLASS_OH_HEADER_LABEL, ENABLED);
        Button removeTAbButton = csgBuilder.buildTextButton(OH_REMOVE_TA_BUTTON, null, CLASS_OH_BUTTON, ENABLED);
        HBox taTypeBox = csgBuilder.buildHBox(OH_TAS_TOGGLE, null, "", ENABLED);
        //MAKE A RAIDO BUTTON
        final ToggleGroup taTypGroup = new ToggleGroup();
        RadioButton allButton = csgBuilder.buildRadioButton(OH_TOGGLE_ALL, taTypeBox,CLASS_OH_RADIO_BUTTON , ENABLED);
        RadioButton underguasButton=csgBuilder.buildRadioButton(OH_TOGGLE_UNDERGRADUATE, taTypeBox,CLASS_OH_RADIO_BUTTON , ENABLED);
        RadioButton graudButton=csgBuilder.buildRadioButton(OH_TOGGLE_GRADUATE, taTypeBox,CLASS_OH_RADIO_BUTTON , ENABLED);
        allButton.setToggleGroup(taTypGroup);
        allButton.setSelected(ENABLED);
        underguasButton.setToggleGroup(taTypGroup);
        graudButton.setToggleGroup(taTypGroup);
        
        GridPane taHeader = csgBuilder.buildGridPane(CSG_GRID_PANE, taVBox, CLASS_CSG_GRID_PANE, ENABLED);
        taHeader.add(removeTAbButton, 0, 0);
        taHeader.add(taLabel, 1, 0);
        taHeader.add(taTypeBox, 2, 0);
        //taTable
        TableView<TeachingAssistantPrototype> taTable = csgBuilder.buildTableView(OH_TAS_TABLE_VIEW, taVBox, CLASS_OH_TABLE_VIEW, ENABLED);
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn nameColumn = csgBuilder.buildTableColumn(OH_NAME_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        nameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
        nameColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/4.0));
        //email column
        TableColumn emailColumn = csgBuilder.buildTableColumn(OH_EMAIL_TABLE_COLUMN,taTable, CLASS_OH_COLUMN);
        emailColumn.setCellValueFactory(new PropertyValueFactory<String, String>("email"));
        emailColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/4.0));
        //slot 
        TableColumn slotColumn = csgBuilder.buildTableColumn(OH_SLOTS_TABLE_COLUMN,taTable,CLASS_OH_COLUMN);
        slotColumn.setCellValueFactory(new PropertyValueFactory<String, String>("slots"));
        slotColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/4.0));
        //type
        TableColumn typeColumn = csgBuilder.buildTableColumn(OH_TYPE_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        typeColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/4.0));
        
        VBox.setVgrow(taTable, Priority.ALWAYS);
        
        //add ta 
        TextField taNameField = csgBuilder.buildTextField(OH_NAME_TEXT_FIELD, null, CLASS_OH_TEXT_FIELD, ENABLED);
        TextField taEmailField = csgBuilder.buildTextField(OH_EMAIL_TEXT_FIELD, null,CLASS_OH_TEXT_FIELD, ENABLED);
        Button addTAButton = csgBuilder.buildTextButton(OH_ADD_TA_BUTTON, null, "", ENABLED);
        
        GridPane addTAGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, taVBox, CLASS_CSG_GRID_PANE, ENABLED);
        addTAGridPane.add(taNameField, 0, 0);
        addTAGridPane.add(taEmailField, 1, 0);
        addTAGridPane.add(addTAButton, 2, 0);
        
        
        VBox OHVBox = csgBuilder.buildVBox(OH_OFFICE_HOURS_BOX, null, CLASS_OH_VBOX, ENABLED);
        HBox spaceBox = csgBuilder.buildHBox(OH_OFFICE_HOURS_HEADER_BOX, OHVBox, "", ENABLED);
        HBox ohHeaderbox = csgBuilder.buildHBox(OH_OFFICE_HOURS_HEADER_BOX, OHVBox, "", ENABLED);
        ohHeaderbox.setStyle("-fx-spacing:100;");
        Label officehourLabel = csgBuilder.buildLabel(OH_OFFICE_HOURS_HEADER_LABEL, ohHeaderbox, CLASS_OH_HEADER_LABEL, ENABLED);
        Label startTimelLabel = csgBuilder.buildLabel(OH_START_TIME_LABLE, null, "", ENABLED);
        Label endTimeLabel =csgBuilder.buildLabel(OH_END_TIME_LABLE, null, "", ENABLED);
        ComboBox startTimeComboBox = csgBuilder.buildComboBox(OH_START_TIME_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        ComboBox endTimeComboBox = csgBuilder.buildComboBox(OH_END_TIME_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        GridPane ohTimeRange = csgBuilder.buildGridPane(CSG_GRID_PANE, ohHeaderbox, CLASS_CSG_GRID_PANE, ENABLED);
        ohTimeRange.add(startTimelLabel, 0, 0);
        ohTimeRange.add(startTimeComboBox, 1, 0);
        ohTimeRange.add(endTimeLabel, 2, 0);
        ohTimeRange.add(endTimeComboBox, 3, 0);
   
        
        //oh table
        TableView<TimeSlot> officeHoursTable = csgBuilder.buildTableView(OH_OFFICE_HOURS_TABLE_VIEW, OHVBox, CLASS_OH_OFFICE_HOURS_TABLE_VIEW, ENABLED);
        TableColumn startTimeColumn = csgBuilder.buildTableColumn(OH_START_TIME_TABLE_COLUMN, officeHoursTable, CLASS_OH_TIME_COLUMN);
        TableColumn endTimeColumn = csgBuilder.buildTableColumn(OH_END_TIME_TABLE_COLUMN, officeHoursTable, CLASS_OH_TIME_COLUMN);
        TableColumn mondayColumn = csgBuilder.buildTableColumn(OH_MONDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN);
        TableColumn tuesdayColumn = csgBuilder.buildTableColumn(OH_TUESDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN);
        TableColumn wednesdayColumn = csgBuilder.buildTableColumn(OH_WEDNESDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN);
        TableColumn thursdayColumn = csgBuilder.buildTableColumn(OH_THURSDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN);
        TableColumn fridayColumn = csgBuilder.buildTableColumn(OH_FRIDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN);
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("endTime"));
        mondayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("monday"));
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("tuesday"));
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("wednesday"));
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("thursday"));
        fridayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("friday"));
        for (int i = 0; i < officeHoursTable.getColumns().size(); i++) {
            ((TableColumn)officeHoursTable.getColumns().get(i)).prefWidthProperty().bind(officeHoursTable.widthProperty().multiply(1.0/7.0));
        }
        VBox.setVgrow(officeHoursTable, Priority.ALWAYS);
        //layout
        SplitPane sPane = new SplitPane(taVBox, OHVBox);
        sPane.setOrientation(Orientation.VERTICAL);
        sPane.setDividerPositions(.4);
        sPane.setStyle("-fx-border-radius:15px;");
        ScrollPane ohScrollPane = csgBuilder.buildScrollPane(CSG_SCROLL_PANE, null,CLASS_CSG_SCROLL_PANE , ENABLED);
        ohScrollPane.setContent(sPane);
        OHTab.setContent(ohScrollPane);


        //SCHEDULE TAB
        Tab scheduleTab = csgBuilder.buildTap(CSG_SCHEDULE, workspace, CLASS_CSG_TAB, ENABLED);
        
        siteTabPane.getTabs().add(siteTab);
        siteTabPane.getTabs().add(syllbusTab);
        siteTabPane.getTabs().add(meetingTab);
        siteTabPane.getTabs().add(OHTab);
        siteTabPane.getTabs().add(scheduleTab);
        siteTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        workspace = new BorderPane();
        ((BorderPane)workspace).setCenter(siteTabPane);
    }
    private void initControllers(){
        CourseSiteController controller = new CourseSiteController((CourseSiteGenerateApp) app);
        
        /*
            OH PANE
        */
        //KEYBOARD 
        AppGUIModule gui = app.getGUIModule();
        
        ((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD)).setOnKeyPressed(e -> {
            controller.up();
        });
        
        ((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD)).setOnKeyReleased(e -> {
            controller.up();
        });
        
        ((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD)).setOnKeyPressed(e -> {
            controller.up();
        });
        
        ((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD)).setOnKeyReleased(e -> {
            controller.up();
        });
        
        //ADD TA 
        ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setOnAction(e -> {
            controller.processAddTA();
        });
        //REMOVE TA
        TableView taOfficeHoursTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        ((Button) gui.getGUINode(OH_REMOVE_TA_BUTTON)).setOnAction(e -> {
            TeachingAssistantPrototype ta = (TeachingAssistantPrototype)taOfficeHoursTableView.getSelectionModel().getSelectedItem();
            controller.processRemoveTA(ta);
        });
        //TIME SLOT
        TableView officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.getSelectionModel().setCellSelectionEnabled(true);
        taOfficeHoursTableView.setOnMouseClicked((event) -> {
            controller.up();
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                TeachingAssistantPrototype ta = (TeachingAssistantPrototype)taOfficeHoursTableView.getSelectionModel().getSelectedItem();
                controller.editTA(ta);
            }else{
                if ((TeachingAssistantPrototype)taOfficeHoursTableView.getSelectionModel().getSelectedItem()!=null) {
                    controller.up();
                    TeachingAssistantPrototype ta = (TeachingAssistantPrototype)taOfficeHoursTableView.getSelectionModel().getSelectedItem();
                    controller.highlightOH(ta);
                }
        
            }
        });
        officeHoursTableView.setOnMouseClicked((event) -> {
              TablePosition tp = officeHoursTableView.getFocusModel().getFocusedCell();
              TeachingAssistantPrototype ta = (TeachingAssistantPrototype)taOfficeHoursTableView.getSelectionModel().getSelectedItem();
               if (!(ta==null)&&tp.getColumn()>1) {
                  controller.processAddTimeslot(ta,tp.getColumn(),(TimeSlot)officeHoursTableView.getSelectionModel().getSelectedItem());
               }
               
        });
        // time range
        ComboBox startTime = (ComboBox)gui.getGUINode(OH_START_TIME_COMBO_BOX);
        ComboBox endTime = (ComboBox)gui.getGUINode(OH_END_TIME_COMBO_BOX);
        
        startTime.setOnAction((event) -> {
           controller.changeTimeRange();
           String selected = (String)startTime.getValue();
           String endSelected = (String)endTime.getValue();
                int startHour =controller.getHours(selected);
                int endHour = controller.getHours(endSelected);
                //endTime.getItems().clear();
                boolean remove = false;
                while(remove!=true){
                    if (!((String)endTime.getItems().get(0)).equals(endSelected)) {
                        endTime.getItems().remove(0);
                    }else{
                        remove = true;
                    }
                }
                for (int i = endHour-1;i>startHour;i--){
                        if (i<12) {
                            endTime.getItems().add(0,i+":00am");
                        }else if (i == 12 ){
                            endTime.getItems().add(0,i+":00pm");
                        }else{
                            endTime.getItems().add(0,i-12+":00pm");
                        }
                }
                
        });
        
        endTime.setOnAction((event) -> {
            controller.changeTimeRange();
           String selected = (String)startTime.getValue();
           String endSelected = (String)endTime.getValue();
                int startHour =controller.getHours(selected);
                int endHour = controller.getHours(endSelected);
                //endTime.getItems().clear();
                boolean remove = false;
                while(remove!=true){
                    int index = startTime.getItems().size()-1;
                    if (!((String)startTime.getItems().get(index)).equals(selected)) {
                        startTime.getItems().remove(index);
                    }else{
                        remove = true;
                    }
                }
                 for (int i =startHour+1;i<endHour;i++){
                        if (i<12) {
                            startTime.getItems().add(i+":00am");
                        }else if (i == 12 ){
                            startTime.getItems().add(i+":00pm");
                        }else{
                            startTime.getItems().add(i-12+":00pm");
                        }
                }
               
        });
        
        
        
        
        //TA TOGGLE
        RadioButton ALL = (RadioButton)gui.getGUINode(OH_TOGGLE_ALL);
        RadioButton Undergrad = (RadioButton)gui.getGUINode(OH_TOGGLE_UNDERGRADUATE);
        RadioButton Grad = (RadioButton)gui.getGUINode(OH_TOGGLE_GRADUATE);
        ALL.setOnAction((event) -> {
            if(ALL.isSelected()){
                controller.changeToAll();
            }
        });
        Undergrad.setOnAction((event) -> {
            if(Undergrad.isSelected()){
                controller.changeToUndergraduate();
            }
        });
        Grad.setOnAction((event) -> {
            if(Grad.isSelected()){
                controller.changeToGrad();
            }
        });
        // DON'T LET ANYONE SORT THE TABLES
        for (int i = 0; i < officeHoursTableView.getColumns().size(); i++) {
            ((TableColumn)officeHoursTableView.getColumns().get(i)).setSortable(false);
        }
    }
    private void initFoolproofDesign(){
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(CSG_FOOLPROOF_SETTINGS,
                new CourseSiteFoolproofDesign((CourseSiteGenerateApp) app));
    }

    @Override
    public void showNewDialog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
