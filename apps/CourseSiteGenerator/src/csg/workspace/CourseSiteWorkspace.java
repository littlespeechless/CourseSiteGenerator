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
import static csg.CourseSitePropertyType.MT_ADD_LAB_BUTTON;
import static csg.CourseSitePropertyType.MT_ADD_LECTURE_BUTTON;
import static csg.CourseSitePropertyType.MT_ADD_RECITATION_BUTTON;
import static csg.CourseSitePropertyType.MT_LAB_DAYSTIME_COLUMN;
import static csg.CourseSitePropertyType.MT_LAB_LABEL;
import static csg.CourseSitePropertyType.MT_LAB_ROOM_COLUMN;
import static csg.CourseSitePropertyType.MT_LAB_SECTION_COLUMN;
import static csg.CourseSitePropertyType.MT_LAB_TA1_COLUMN;
import static csg.CourseSitePropertyType.MT_LAB_TA2_COLUMN;
import static csg.CourseSitePropertyType.MT_LAB_TABLEVIEW;
import static csg.CourseSitePropertyType.MT_LECTURE_DAYS_COLUMN;
import static csg.CourseSitePropertyType.MT_LECTURE_LABEL;
import static csg.CourseSitePropertyType.MT_LECTURE_ROOM_COLUMN;
import static csg.CourseSitePropertyType.MT_LECTURE_SECTION_COLUMN;
import static csg.CourseSitePropertyType.MT_LECTURE_TABLEVIEW;
import static csg.CourseSitePropertyType.MT_LECTURE_TIME_COLUMN;
import static csg.CourseSitePropertyType.MT_RECITATION_DAYSTIME_COLUMN;
import static csg.CourseSitePropertyType.MT_RECITATION_LABEL;
import static csg.CourseSitePropertyType.MT_RECITATION_ROOM_COLUMN;
import static csg.CourseSitePropertyType.MT_RECITATION_SECTION_COLUMN;
import static csg.CourseSitePropertyType.MT_RECITATION_TA1_COLUMN;
import static csg.CourseSitePropertyType.MT_RECITATION_TA2_COLUMN;
import static csg.CourseSitePropertyType.MT_RECITATION_TABLEVIEW;
import static csg.CourseSitePropertyType.MT_REMOVE_LAB_BUTTON;
import static csg.CourseSitePropertyType.MT_REMOVE_LECTURE_BUTTON;
import static csg.CourseSitePropertyType.MT_REMOVE_RECITATION_BUTTON;
import static csg.CourseSitePropertyType.MT_VBOX;
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
import static csg.CourseSitePropertyType.SC_ADD_ITEM_BUTTON;
import static csg.CourseSitePropertyType.SC_ADD_LABEL;
import static csg.CourseSitePropertyType.SC_CALENDAR_LABEL;
import static csg.CourseSitePropertyType.SC_CLEAR_ITEM_BUTTON;
import static csg.CourseSitePropertyType.SC_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_DATE_LABEL;
import static csg.CourseSitePropertyType.SC_END_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SC_END_DATE_LABEL;
import static csg.CourseSitePropertyType.SC_LINK_LABEL;
import static csg.CourseSitePropertyType.SC_LINK_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_REMOVE_ITEM_BUTTON;
import static csg.CourseSitePropertyType.SC_SCHEDULE_DATE_COLUMN;
import static csg.CourseSitePropertyType.SC_SCHEDULE_ITEM_LABEL;
import static csg.CourseSitePropertyType.SC_SCHEDULE_TYPE_COLUMN;
import static csg.CourseSitePropertyType.SC_SCHEDULE_TABLEVIEW;
import static csg.CourseSitePropertyType.SC_SCHEDULE_TITLE_COLUMN;
import static csg.CourseSitePropertyType.SC_SCHEDULE_TOPIC_COLUMN;
import static csg.CourseSitePropertyType.SC_START_DATE_LABEL;
import static csg.CourseSitePropertyType.SC_TITLE_LABEL;
import static csg.CourseSitePropertyType.SC_TITLE_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_TOPIC_LABEL;
import static csg.CourseSitePropertyType.SC_TOPIC_TEXT_FIELD;
import static csg.CourseSitePropertyType.SC_TYPE_COMBO_BOX;
import static csg.CourseSitePropertyType.SC_TYPE_LABEL;
import static csg.CourseSitePropertyType.SC_VBOX;
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
import static csg.CourseSitePropertyType.SY_ACADEMIC_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_ACADEMIC_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_DESCRIPTION_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_DESCRIPTION_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_GRADED_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_GRADED_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_GRADING_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_GRADING_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_OUTCOMES_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_OUTCOMES_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_PREREQUIREMENT_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_PREREQUIREMENT_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_SPECIAL_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_SPECIAL_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_TEXTBOOK_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_TEXTBOOK_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_TOPIC_TEXT_AREA;
import static csg.CourseSitePropertyType.SY_TOPIC_TITLED_PANE;
import static csg.CourseSitePropertyType.SY_VBOX;
import csg.data.Lab;
import csg.data.Lecture;
import csg.data.Recitation;
import csg.data.Schedule;
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
import static csg.workspace.style.CSGStyle.CLASS_CSG_VBOX;
import static csg.workspace.style.CSGStyle.CLASS_MT_VBOX;
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
import static csg.workspace.style.CSGStyle.CLASS_SYLLABUS_SCROOL_PANE;
import static djf.AppPropertyType.EXPORT_BUTTON;
import static djf.AppPropertyType.SAVE_BUTTON;
import djf.AppTemplate;
import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
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
import javafx.stage.FileChooser;
import javafx.util.Callback;
import properties_manager.PropertiesManager;
import static csg.CourseSitePropertyType.SC_START_DATE_DATE_PICKER;
import static csg.CourseSitePropertyType.SITE_EXPORT_DIR;

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
        
        /*****
         * SITE TAB 
         */
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
        Label exportDirLabel =csgBuilder.buildLabel(SITE_EXPORT_DIR, null, CLASS_SITE_LABLE, ENABLED);
        Label titleLabel = csgBuilder.buildLabel(SITE_TITLE, null, CLASS_SITE_LABLE, ENABLED);
        ComboBox subjecCombo = csgBuilder.buildComboBox(SITE_SUBJECT_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        ComboBox numberCombo = csgBuilder.buildComboBox(SITE_NUMBER_COMBO_BOX,  null, CLASS_COMBO_BOX_LABLE, ENABLED);
        ComboBox semesterCombo = csgBuilder.buildComboBox(SITE_SEMESTER_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        ComboBox yearCombo = csgBuilder.buildComboBox(SITE_YEAR_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        subjecCombo.setEditable(true);
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
        GridPane exportPane = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_SUB_GRID_PANE, ENABLED);
        exportPane.add(exportLabel, 0, 0);
        exportPane.add(exportDirLabel, 1, 0);
        bannerPane.add(exportPane, 0, 2);
        
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
        ImageView favicon = csgBuilder.buildImageView(SITE_FAVICON_IMG, null, ENABLED);
        ImageView navbar = csgBuilder.buildImageView(SITE_NAVBAR_IMG, null, ENABLED);
        ImageView leftFooter = csgBuilder.buildImageView(SITE_LEFT_FOOTER_IMG, null, ENABLED);
        ImageView rightFooter = csgBuilder.buildImageView(SITE_RIGHT_FOOTER_IMG, null, ENABLED);
        GridPane styleGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, styleBox, CLASS_CSG_GRID_PANE, ENABLED);
        styleGridPane.add(styleLabel, 0, 0);
        GridPane iconspGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_SUB_GRID_PANE, ENABLED);
        iconspGridPane.add(faviconButton, 0, 0);
        iconspGridPane.add(favicon, 1, 0);
        iconspGridPane.add(navbarButton, 0, 1);
        iconspGridPane.add(navbar, 1, 1);
        iconspGridPane.add(leftFooterButton, 0, 2);
        iconspGridPane.add(leftFooter, 1, 2);
        iconspGridPane.add(rightFooterButton, 0, 3);
        iconspGridPane.add(rightFooter, 1, 3);
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
        
        /*****
         * SYLLABUS TAB
         */   
        //TEXT AREAS 
        Tab syllbusTab = csgBuilder.buildTap(CSG_SYLLABUS, workspace, CLASS_CSG_TAB, ENABLED);
        TextArea descriptionArea = csgBuilder.buildTextArea(SY_DESCRIPTION_TEXT_AREA, null, "", ENABLED);
        TextArea topicArea = csgBuilder.buildTextArea(SY_TOPIC_TEXT_AREA, null, "", ENABLED);
        TextArea prerequirementArea = csgBuilder.buildTextArea(SY_PREREQUIREMENT_TEXT_AREA, null, "", ENABLED);
        TextArea outcomesArea = csgBuilder.buildTextArea(SY_OUTCOMES_TEXT_AREA, null, "", ENABLED);
        TextArea textbookArea = csgBuilder.buildTextArea(SY_TEXTBOOK_TEXT_AREA, null, "", ENABLED);
        TextArea gradedArea = csgBuilder.buildTextArea(SY_GRADED_TEXT_AREA, null, "", ENABLED);
        TextArea gradingArea = csgBuilder.buildTextArea(SY_GRADING_TEXT_AREA, null, "", ENABLED);
        TextArea academicArea = csgBuilder.buildTextArea(SY_ACADEMIC_TEXT_AREA, null, "", ENABLED);
        TextArea specialArea = csgBuilder.buildTextArea(SY_SPECIAL_TEXT_AREA, null, "", ENABLED);
        
        //TITLED PANES 
        TitledPane description = csgBuilder.buildTitledPane(SY_DESCRIPTION_TITLED_PANE,null , "", ENABLED);
        TitledPane topic = csgBuilder.buildTitledPane(SY_TOPIC_TITLED_PANE,null , "", ENABLED);
        TitledPane prerequirment = csgBuilder.buildTitledPane(SY_PREREQUIREMENT_TITLED_PANE, null, "", ENABLED);
        TitledPane outcomes = csgBuilder.buildTitledPane(SY_OUTCOMES_TITLED_PANE, null, "", ENABLED);
        TitledPane textbook = csgBuilder.buildTitledPane(SY_TEXTBOOK_TITLED_PANE, null, "", ENABLED);
        TitledPane graded = csgBuilder.buildTitledPane(SY_GRADED_TITLED_PANE, null, "", ENABLED);
        TitledPane grading = csgBuilder.buildTitledPane(SY_GRADING_TITLED_PANE, null, "", ENABLED);
        TitledPane academic = csgBuilder.buildTitledPane(SY_ACADEMIC_TITLED_PANE, null, "", ENABLED);
        TitledPane special = csgBuilder.buildTitledPane(SY_SPECIAL_TITLED_PANE, null, "", ENABLED);
        
        description.setContent(descriptionArea);
        //description.setExpanded(Boolean.FALSE);
        topic.setContent(topicArea);
        topic.setExpanded(Boolean.FALSE);
        prerequirment.setContent(prerequirementArea);
        prerequirment.setExpanded(Boolean.FALSE);
        outcomes.setContent(outcomesArea);
        outcomes.setExpanded(Boolean.FALSE);
        textbook.setContent(textbookArea);
        textbook.setExpanded(Boolean.FALSE);
        graded.setContent(gradedArea);
        graded.setExpanded(Boolean.FALSE);
        grading.setContent(gradingArea);
        grading.setExpanded(Boolean.FALSE);
        academic.setContent(academicArea);
        academic.setExpanded(Boolean.FALSE);
        special.setContent(specialArea);
        special.setExpanded(Boolean.FALSE);
        
        //LAYOUT FOR TAB 
        GridPane syllabusGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_TAB_GRID_PANE, ENABLED);
        syllabusGridPane.add(description, 0, 0);
        syllabusGridPane.add(topic, 0, 1);
        syllabusGridPane.add(prerequirment, 0, 2);
        syllabusGridPane.add(outcomes, 0, 3);
        syllabusGridPane.add(textbook, 0, 4);
        syllabusGridPane.add(graded, 0, 5);
        syllabusGridPane.add(grading, 0, 6);
        syllabusGridPane.add(academic, 0, 7);
        syllabusGridPane.add(special, 0, 8);
        
        ScrollPane syllabusScrollPane = csgBuilder.buildScrollPane(CSG_SCROLL_PANE, null, CLASS_SYLLABUS_SCROOL_PANE, ENABLED);
        syllabusScrollPane.setContent(syllabusGridPane);
        syllbusTab.setContent(syllabusScrollPane);
        
        /****
         * METTING TAB 
         */
        
        Tab meetingTab = csgBuilder.buildTap(CSG_MEETING, workspace, CLASS_CSG_TAB, ENABLED);
        VBox lectureBox = csgBuilder.buildVBox(MT_VBOX, null, CLASS_MT_VBOX, ENABLED);
        VBox recitationBox = csgBuilder.buildVBox(MT_VBOX, null, CLASS_MT_VBOX, ENABLED);
        VBox labBox = csgBuilder.buildVBox(MT_VBOX, null, CLASS_MT_VBOX, ENABLED);
        
        //lecture
        Button addLecture = csgBuilder.buildTextButton(MT_ADD_LECTURE_BUTTON, null, CLASS_OH_BUTTON, ENABLED);
        Button removeLecture = csgBuilder.buildTextButton(MT_REMOVE_LECTURE_BUTTON, null, CLASS_OH_BUTTON, ENABLED);
        Label lectureLabel = csgBuilder.buildLabel(MT_LECTURE_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        GridPane lectureHeader = csgBuilder.buildGridPane(CSG_GRID_PANE, lectureBox, CLASS_CSG_SUB_GRID_PANE, ENABLED);
        lectureHeader.add(addLecture, 0, 0);
        lectureHeader.add(removeLecture, 1, 0);
        lectureHeader.add(lectureLabel, 2, 0);
        //lecture table 
        TableView<Lecture> lectureTable = csgBuilder.buildTableView(MT_LECTURE_TABLEVIEW, lectureBox, "", ENABLED);
        lectureTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lectureTable.setEditable(ENABLED);
        Callback<TableColumn<Lecture,String>,TableCell<Lecture,String>> lectureCellFactory =(TableColumn<Lecture, String> param) -> new EditingLectureCell();
        //section 
        TableColumn lectureSectionColumn = csgBuilder.buildTableColumn(MT_LECTURE_SECTION_COLUMN, lectureTable, "");
        lectureSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        lectureSectionColumn.setCellFactory(lectureCellFactory);
        lectureSectionColumn.prefWidthProperty().bind(lectureTable.widthProperty().multiply(1.0/4.0));
        
        //days
        TableColumn lectureDaysColumn = csgBuilder.buildTableColumn(MT_LECTURE_DAYS_COLUMN, lectureTable, "");
        lectureDaysColumn.setCellValueFactory(new PropertyValueFactory<String, String>("days"));
        lectureDaysColumn.setCellFactory(lectureCellFactory);
        lectureDaysColumn.prefWidthProperty().bind(lectureTable.widthProperty().multiply(1.0/4.0));
        //time
        TableColumn lectureTimeColumn = csgBuilder.buildTableColumn(MT_LECTURE_TIME_COLUMN, lectureTable, "");
        lectureTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("time"));
        lectureTimeColumn.setCellFactory(lectureCellFactory);
        lectureTimeColumn.prefWidthProperty().bind(lectureTable.widthProperty().multiply(1.0/4.0));
        //room
        TableColumn lectureRoomColumn = csgBuilder.buildTableColumn(MT_LECTURE_ROOM_COLUMN, lectureTable, "");
        lectureRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        lectureRoomColumn.setCellFactory(lectureCellFactory);
        lectureRoomColumn.prefWidthProperty().bind(lectureTable.widthProperty().multiply(1.0/4.0));
        VBox.setVgrow(lectureTable, Priority.ALWAYS);
        
        
        
        // recitation
        Button  addRecitation = csgBuilder.buildTextButton(MT_ADD_RECITATION_BUTTON, null, CLASS_OH_BUTTON, ENABLED);
        Button  removeRecitation = csgBuilder.buildTextButton(MT_REMOVE_RECITATION_BUTTON, null, CLASS_OH_BUTTON, ENABLED);
        Label recitationLabel = csgBuilder.buildLabel(MT_RECITATION_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        GridPane recitationHeader =csgBuilder.buildGridPane(CSG_GRID_PANE, recitationBox, CLASS_CSG_SUB_GRID_PANE, ENABLED);
        recitationHeader.add(addRecitation, 0, 0);
        recitationHeader.add(removeRecitation, 1, 0);
        recitationHeader.add(recitationLabel, 2, 0);
        
        TableView<Recitation> recitationTable = csgBuilder.buildTableView(MT_RECITATION_TABLEVIEW, recitationBox, "", ENABLED);
        recitationTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        recitationTable.setEditable(ENABLED);
        Callback<TableColumn<Recitation,String>,TableCell<Recitation,String>> recitationCellFactory =(TableColumn<Recitation, String> param) -> new EditingRecitationCell();

        //section
        TableColumn recitationSectionColumn = csgBuilder.buildTableColumn(MT_RECITATION_SECTION_COLUMN, recitationTable, "");
        recitationSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        recitationSectionColumn.setCellFactory(recitationCellFactory);
        recitationSectionColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0/5.0));
        //DAYS ADN TIME 
        TableColumn recitationDaysTimeColumn = csgBuilder.buildTableColumn(MT_RECITATION_DAYSTIME_COLUMN, recitationTable, "");
        recitationDaysTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("daysTime"));
        recitationDaysTimeColumn.setCellFactory(recitationCellFactory);
        recitationDaysTimeColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0/5.0));
        //ROOM
        TableColumn recitationRoomColumn = csgBuilder.buildTableColumn(MT_RECITATION_ROOM_COLUMN, recitationTable, "");
        recitationRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        recitationRoomColumn.setCellFactory(recitationCellFactory);
        recitationRoomColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0/5.0));
        //TA1
        TableColumn recitationTA1Column = csgBuilder.buildTableColumn(MT_RECITATION_TA1_COLUMN, recitationTable, "");
        recitationTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta1"));
        recitationTA1Column.setCellFactory(recitationCellFactory);
        recitationTA1Column.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0/5.0));
        //TA2
        TableColumn recitationTA2Column = csgBuilder.buildTableColumn(MT_RECITATION_TA2_COLUMN, recitationTable, "");
        recitationTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta2"));
        recitationTA2Column.setCellFactory(recitationCellFactory);
        recitationTA2Column.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0/5.0));
        
        VBox.setVgrow(recitationTable, Priority.ALWAYS);
        
       
        
        //lab box 
        Button  addLab = csgBuilder.buildTextButton(MT_ADD_LAB_BUTTON, null, CLASS_OH_BUTTON, ENABLED);
        Button  removeLAB = csgBuilder.buildTextButton(MT_REMOVE_LAB_BUTTON, null, CLASS_OH_BUTTON, ENABLED);
        Label labLabel = csgBuilder.buildLabel(MT_LAB_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        GridPane labHeader =csgBuilder.buildGridPane(CSG_GRID_PANE, labBox, CLASS_CSG_SUB_GRID_PANE, ENABLED);
        labHeader.add(addLab, 0, 0);
        labHeader.add(removeLAB, 1, 0);
        labHeader.add(labLabel, 2, 0);
        
        TableView<Lab> labTable = csgBuilder.buildTableView(MT_LAB_TABLEVIEW, labBox, "", ENABLED);
        labTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        labTable.setEditable(ENABLED);
        Callback<TableColumn<Lab,String>,TableCell<Lab,String>> labCellFactory =(TableColumn<Lab, String> param) -> new EditingLabCell();

        //section
        TableColumn labSectionColumn = csgBuilder.buildTableColumn(MT_LAB_SECTION_COLUMN, labTable, "");
        labSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        labSectionColumn.setCellFactory(labCellFactory);
        labSectionColumn.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0/5.0));
        //DAYS ADN TIME 
        TableColumn labDaysTimeColumn = csgBuilder.buildTableColumn(MT_LAB_DAYSTIME_COLUMN, labTable, "");
        labDaysTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("daysTime"));
        labDaysTimeColumn.setCellFactory(labCellFactory);
        labDaysTimeColumn.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0/5.0));
        //ROOM
        TableColumn labRoomColumn = csgBuilder.buildTableColumn(MT_LAB_ROOM_COLUMN, labTable, "");
        labRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        labRoomColumn.setCellFactory(labCellFactory);
        labRoomColumn.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0/5.0));
        //TA1
        TableColumn labTA1Column = csgBuilder.buildTableColumn(MT_LAB_TA1_COLUMN, labTable, "");
        labTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta1"));
        labTA1Column.setCellFactory(labCellFactory);
        labTA1Column.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0/5.0));
        //TA2
        TableColumn labTA2Column = csgBuilder.buildTableColumn(MT_LAB_TA2_COLUMN, labTable, "");
        labTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta2"));
        labTA2Column.setCellFactory(labCellFactory);
        labTA2Column.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0/5.0));
        VBox.setVgrow(labTable, Priority.ALWAYS);
        
        lectureTable.getSelectionModel().setCellSelectionEnabled(true);
        recitationTable.getSelectionModel().setCellSelectionEnabled(true);
        labTable.getSelectionModel().setCellSelectionEnabled(true);
        
        GridPane meetingGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_TAB_GRID_PANE, ENABLED);
        meetingGridPane.add(lectureBox, 0, 0);
        meetingGridPane.add(recitationBox, 0, 1);
        meetingGridPane.add(labBox, 0, 2);
        ScrollPane meetingScrollPane = csgBuilder.buildScrollPane(CSG_SCROLL_PANE, null, CLASS_CSG_SCROLL_PANE, ENABLED);
        meetingScrollPane.setContent(meetingGridPane);
        meetingTab.setContent(meetingScrollPane);
        
        /*******
         * Office Hours Tab
         */
        
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
        

        /*****
         * SCHEDULE TAB 
         */
        
        Tab scheduleTab = csgBuilder.buildTap(CSG_SCHEDULE, workspace, CLASS_CSG_TAB, ENABLED);
        
        VBox calenderBox = csgBuilder.buildVBox(SC_VBOX, null, CLASS_MT_VBOX, ENABLED);
        VBox scheduleBox = csgBuilder.buildVBox(SC_VBOX, null, CLASS_MT_VBOX, ENABLED);
        VBox addItemsBox = csgBuilder.buildVBox(SC_VBOX, null, CLASS_MT_VBOX, ENABLED);
        
        Label calenderLabel = csgBuilder.buildLabel(SC_CALENDAR_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label startDateLabel = csgBuilder.buildLabel(SC_START_DATE_LABEL, null, CLASS_SITE_LABLE, ENABLED);
        Label endDateLabel = csgBuilder.buildLabel(SC_END_DATE_LABEL, null, CLASS_SITE_LABLE, ENABLED);
        DatePicker startDatePicker = csgBuilder.buildDatePicker(SC_START_DATE_DATE_PICKER, "", ENABLED);
        DatePicker endDatePicker = csgBuilder.buildDatePicker(SC_END_DATE_DATE_PICKER, "", ENABLED);
        startDatePicker.setEditable(false);
        endDatePicker.setEditable(false);
        final Callback<DatePicker, DateCell> startdayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           if  (endDatePicker.getValue()!=null){
                            if (item.isAfter(
                                    endDatePicker.getValue().plusDays(-1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }  
                           }
                    }
                };
            }
        };
        startDatePicker.setDayCellFactory(startdayCellFactory);
         final Callback<DatePicker, DateCell> endayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           if(startDatePicker.getValue()!=null){
                            if (item.isBefore(
                                    startDatePicker.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }   
                          }
                    }
                };
            }
        };
        endDatePicker.setDayCellFactory(endayCellFactory);
        GridPane calenderGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, calenderBox, CLASS_CSG_GRID_PANE, ENABLED);
        calenderGridPane.add(calenderLabel, 0, 0);
        calenderGridPane.add(startDateLabel, 0, 1);
        calenderGridPane.add(startDatePicker, 1, 1);
        calenderGridPane.add(endDateLabel, 0, 2);
        calenderGridPane.add(endDatePicker, 1, 2);
        
        Button removeItembButton = csgBuilder.buildIconButton(SC_REMOVE_ITEM_BUTTON, null, CLASS_OH_BUTTON, ENABLED);
        Label scheduleItemLabel = csgBuilder.buildLabel(SC_SCHEDULE_ITEM_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        GridPane itemGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, scheduleBox, CLASS_CSG_GRID_PANE, ENABLED);
        itemGridPane.add(removeItembButton, 0, 0);
        itemGridPane.add(scheduleItemLabel, 1, 0);
        
        TableView <Schedule> scheduleTable = csgBuilder.buildTableView(SC_SCHEDULE_TABLEVIEW, scheduleBox, "", ENABLED);
        //option
        TableColumn scTypeColumn = csgBuilder.buildTableColumn(SC_SCHEDULE_TYPE_COLUMN, scheduleTable, "");
        scTypeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        scTypeColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(1.0/4.0));
        //date
        TableColumn dateColumn = csgBuilder.buildTableColumn(SC_SCHEDULE_DATE_COLUMN, scheduleTable, "");
        dateColumn.setCellValueFactory(new PropertyValueFactory<String, String>("date"));
        dateColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(1.0/4.0));
        //title
        TableColumn titleColumn = csgBuilder.buildTableColumn(SC_SCHEDULE_TITLE_COLUMN, scheduleTable, "");
        titleColumn.setCellValueFactory(new PropertyValueFactory<String, String>("title"));
        titleColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(1.0/4.0));
        //option
        TableColumn topicColumn = csgBuilder.buildTableColumn(SC_SCHEDULE_TOPIC_COLUMN, scheduleTable, "");
        topicColumn.setCellValueFactory(new PropertyValueFactory<String, String>("topic"));
        topicColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(1.0/4.0));
        VBox.setVgrow(scheduleTable, Priority.ALWAYS);
        

        Label additemLabel = csgBuilder.buildLabel(SC_ADD_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label typeLabel = csgBuilder.buildLabel(SC_TYPE_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label dateLabel = csgBuilder.buildLabel(SC_DATE_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label scTitleLabel = csgBuilder.buildLabel(SC_TITLE_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label topicLabel = csgBuilder.buildLabel(SC_TOPIC_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);
        Label linkLabel = csgBuilder.buildLabel(SC_LINK_LABEL, null, CLASS_SITE_TITLE_LABLE, ENABLED);

        ComboBox typeBox = csgBuilder.buildComboBox(SC_TYPE_COMBO_BOX, null, CLASS_COMBO_BOX_LABLE, ENABLED);
        DatePicker datePicker = csgBuilder.buildDatePicker(SC_DATE_DATE_PICKER, "", ENABLED);
        TextField scTitleField = csgBuilder.buildTextField(SC_TITLE_TEXT_FIELD, null, "", ENABLED);
        TextField topicField = csgBuilder.buildTextField(SC_TOPIC_TEXT_FIELD, null, "", ENABLED);
        TextField linkField = csgBuilder.buildTextField(SC_LINK_TEXT_FIELD, null, "", ENABLED);
        Button addItemButton = csgBuilder.buildTextButton(SC_ADD_ITEM_BUTTON, null, "", ENABLED);
        Button clearItemButton = csgBuilder.buildTextButton(SC_CLEAR_ITEM_BUTTON, null, "", ENABLED); 
        datePicker.setEditable(false);
        final Callback<DatePicker, DateCell> dateCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           if  (endDatePicker.getValue()!=null&&startDatePicker.getValue()!=null){
                            if (item.isAfter(
                                    endDatePicker.getValue())
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            if (item.isBefore(
                                    startDatePicker.getValue())
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }   
                           }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dateCellFactory);
        
        GridPane addItemGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, addItemsBox, CLASS_CSG_GRID_PANE, ENABLED);
        addItemGridPane.add(additemLabel, 0, 0);
        addItemGridPane.add(typeLabel, 0, 1);
        addItemGridPane.add(typeBox, 1, 1);
        addItemGridPane.add(dateLabel, 0, 2);
        addItemGridPane.add(datePicker, 1, 2);
        addItemGridPane.add(scTitleLabel, 0, 3);
        addItemGridPane.add(scTitleField, 1, 3);
        addItemGridPane.add(topicLabel, 0, 4);
        addItemGridPane.add(topicField, 1, 4);
        addItemGridPane.add(linkLabel, 0, 5);
        addItemGridPane.add(linkField, 1, 5);
        addItemGridPane.add(addItemButton, 0, 6);
        addItemGridPane.add(clearItemButton, 1, 6);

        GridPane scheduleTabGridPane = csgBuilder.buildGridPane(CSG_GRID_PANE, null, CLASS_CSG_TAB_GRID_PANE, ENABLED);
        scheduleTabGridPane.add(calenderBox, 0, 0);
        scheduleTabGridPane.add(scheduleBox, 0, 1);
        scheduleTabGridPane.add(addItemsBox, 0, 2);
        ScrollPane scheduleScrollPane = csgBuilder.buildScrollPane(CSG_SCROLL_PANE, null, CLASS_CSG_SCROLL_PANE, ENABLED);
        scheduleScrollPane.setContent(scheduleTabGridPane);
        scheduleTab.setContent(scheduleScrollPane);
        
        
        
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
        AppGUIModule gui = app.getGUIModule(); 
        
        /**
         * Site pane
         * 
         */
        ComboBox subjectBox = (ComboBox) gui.getGUINode(SITE_SUBJECT_COMBO_BOX);
        subjectBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                controller.setOldSubject();
            }else{
                controller.processSubjectChange();
                controller.up();
            }
        });
        ComboBox numberBox = (ComboBox) gui.getGUINode(SITE_NUMBER_COMBO_BOX);
        numberBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                controller.setOldNumber();
            }else{
                controller.processNumberChange();
                controller.up();
            }
        });
        ComboBox yearBox = (ComboBox) gui.getGUINode(SITE_YEAR_COMBO_BOX);
        ComboBox semesterBox = (ComboBox) gui.getGUINode(SITE_SEMESTER_COMBO_BOX);
        semesterBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                controller.setComboBox(semesterBox);
            }else{
                controller.processComboBoxChange(semesterBox);
                controller.up();
            }
        });
        yearBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                controller.setComboBox(yearBox);
            }else{
                controller.processComboBoxChange(yearBox);
                controller.up();
            }
        });
        subjectBox.setOnAction((event) -> {
            controller.up();
        });
        numberBox.setOnAction((event) -> {
            controller.up();
        });
        semesterBox.setOnAction((event) -> {
            controller.up();
        });
        yearBox.setOnAction((event) -> {
            controller.up();
        });

        TextField titlefiField = ((TextField) gui.getGUINode(SITE_TITLE_TEXT_FIELD));
        titlefiField.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldFieldText(titlefiField);
                    }else{
                        controller.processTextFieldChange(titlefiField);
                    }
        }); 
                
        ((CheckBox) gui.getGUINode(SITE_HOME)).setOnAction(e->{
            controller.up();
        });
        ((CheckBox) gui.getGUINode(SITE_SYLLABUS)).setOnAction(e->{
            controller.up();
        });
        ((CheckBox) gui.getGUINode(SITE_SCHEDULE)).setOnAction(e->{
            controller.up();
        });
        ((CheckBox) gui.getGUINode(SITE_HWS)).setOnAction(e->{
            controller.up();
        });
        ((CheckBox) gui.getGUINode(SITE_HOME)).setOnMouseClicked(e->{
            controller.processCheckboxChange(((CheckBox) gui.getGUINode(SITE_HOME)));
        });
        ((CheckBox) gui.getGUINode(SITE_SYLLABUS)).setOnMouseClicked(e->{
            controller.processCheckboxChange(((CheckBox) gui.getGUINode(SITE_SYLLABUS)));
        });
        ((CheckBox) gui.getGUINode(SITE_SCHEDULE)).setOnMouseClicked(e->{
            controller.processCheckboxChange(((CheckBox) gui.getGUINode(SITE_SCHEDULE)));
        });
        ((CheckBox) gui.getGUINode(SITE_HWS)).setOnMouseClicked(e->{
            controller.processCheckboxChange(((CheckBox) gui.getGUINode(SITE_HWS)));
        });
        
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );                 
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg","*.png","*.ico")
                
            );
        Button faviconButton = (Button) gui.getGUINode(SITE_FAVICON);
        Button navbarButton = (Button) gui.getGUINode(SITE_NAVBAR);
        Button leftFooterButton = (Button) gui.getGUINode(SITE_LEFT_FOOTER);
        Button rightFooterButton =(Button) gui.getGUINode(SITE_RIGHT_FOOTER);
        ImageView faviconImageView = (ImageView) gui.getGUINode(SITE_FAVICON_IMG);
        ImageView navbarImageView = (ImageView) gui.getGUINode(SITE_NAVBAR_IMG);
        ImageView leftFooterImageView = (ImageView) gui.getGUINode(SITE_LEFT_FOOTER_IMG);
        ImageView rightFooterImageView = (ImageView) gui.getGUINode(SITE_RIGHT_FOOTER_IMG);
        
        faviconButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(gui.getWindow());
            if (file!=null) {
                Image newImage = new Image(file.toURI().toString());
                controller.changeImages(newImage, faviconImageView);
            }
        });
        navbarButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(gui.getWindow());
            if (file!=null) {
                Image newImage = new Image(file.toURI().toString());
                controller.changeImages(newImage, navbarImageView);
            }
        });
        leftFooterButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(gui.getWindow());
            if (file!=null) {
                Image newImage = new Image(file.toURI().toString());
                controller.changeImages(newImage, leftFooterImageView);
            }
        });
        rightFooterButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(gui.getWindow());
            if (file!=null) {
                Image newImage = new Image(file.toURI().toString());
                controller.changeImages(newImage, rightFooterImageView);
            }
        });
        
        
        TextField insName = (TextField) gui.getGUINode(SITE_INSTRUCTOR_NAME_TEXT_FIELD);
        TextField insEmail = (TextField) gui.getGUINode(SITE_INSTRUCTOR_EMAIL_TEXT_FIELD);
        TextField insRoom = (TextField) gui.getGUINode(SITE_INSTRUCTOR_ROOM_TEXT_FIELD);
        TextField insHomepage = (TextField) gui.getGUINode(SITE_INSTRUCTOR_HOMEPAGE_TEXT_FIELD);
        
        insName.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldFieldText(insName);
                    }else{
                        controller.processTextFieldChange(insName);
                    }
        }); 
        insEmail.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldFieldText(insEmail);
                    }else{
                        controller.processTextFieldChange(insEmail);
                    }
        }); 
        insRoom.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldFieldText(insRoom);
                    }else{
                        controller.processTextFieldChange(insRoom);
                    }
        }); 
        insHomepage.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldFieldText(insHomepage);
                    }else{
                        controller.processTextFieldChange(insHomepage);
                    }
        }); 
        
        TextArea insOH = (TextArea) gui.getGUINode(SITE_INSTRUCTOR_OH_TEXT_AREA);
        
        insOH.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(insOH);
                    }else{
                        controller.processTextAreaChange(insOH);
                    }
        }); 
        
        /**
         * SYLLABUS TAB
         * 
         */
        TextArea des = (TextArea) gui.getGUINode(SY_DESCRIPTION_TEXT_AREA);
        TextArea topicArea = (TextArea) gui.getGUINode(SY_TOPIC_TEXT_AREA);
        TextArea prereq = (TextArea) gui.getGUINode(SY_PREREQUIREMENT_TEXT_AREA);
        TextArea outcomes = (TextArea) gui.getGUINode(SY_OUTCOMES_TEXT_AREA);
        TextArea textbook = (TextArea) gui.getGUINode(SY_TEXTBOOK_TEXT_AREA);
        TextArea graded = (TextArea) gui.getGUINode(SY_GRADED_TEXT_AREA);
        TextArea grading = (TextArea) gui.getGUINode(SY_GRADING_TEXT_AREA);
        TextArea academic = (TextArea) gui.getGUINode(SY_ACADEMIC_TEXT_AREA);
        TextArea special = (TextArea) gui.getGUINode(SY_SPECIAL_TEXT_AREA);
        des.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(des);
                    }else{
                        controller.processTextAreaChange(des);
                    }
        }); 
        topicArea.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(topicArea);
                    }else{
                        controller.processTextAreaChange(topicArea);
                    }
        }); 
        prereq.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(prereq);
                    }else{
                        controller.processTextAreaChange(prereq);
                    }
        }); 
        outcomes.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(outcomes);
                    }else{
                        controller.processTextAreaChange(outcomes);
                    }
        }); 
        textbook.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(textbook);
                    }else{
                        controller.processTextAreaChange(textbook);
                    }
        }); 
        graded.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(graded);
                    }else{
                        controller.processTextAreaChange(graded);
                    }
        }); 
        grading.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(grading);
                    }else{
                        controller.processTextAreaChange(grading);
                    }
        }); 
        academic.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(academic);
                    }else{
                        controller.processTextAreaChange(academic);
                    }
        }); 
        special.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        controller.setOldAreaText(special);
                    }else{
                        controller.processTextAreaChange(special);
                    }
        }); 
        
        
        /**
         * meeting time pane
         * 
         */
       TableView lectureTable = (TableView) gui.getGUINode(MT_LECTURE_TABLEVIEW);
        ((Button) gui.getGUINode(MT_ADD_LECTURE_BUTTON)).setOnAction(e->{
            controller.processAddLecture();
        });
        ((Button) gui.getGUINode(MT_REMOVE_LECTURE_BUTTON)).setOnAction(e->{
            Lecture lecture = (Lecture)lectureTable.getSelectionModel().getSelectedItem();
            controller.processRemoveLecture(lecture);
        });
        TableColumn lectureSectionColumn = (TableColumn) lectureTable.getColumns().get(0);
        TableColumn lectureDaysColumn = (TableColumn) lectureTable.getColumns().get(1);
        TableColumn lectureTimeColumn = (TableColumn) lectureTable.getColumns().get(2);
        TableColumn lectureRoomColumn = (TableColumn) lectureTable.getColumns().get(3);
        lectureTable.setOnMouseClicked(e->{
            controller.up();
        });
        lectureSectionColumn.setOnEditCommit(new EventHandler<CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(CellEditEvent<Lecture, String> t) {
                    Lecture lecture = ((Lecture) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLecture(t.getNewValue(),lecture.getDays(),
                            lecture.getTime(),lecture.getRoom(),lecture);
                }
             });
        lectureDaysColumn.setOnEditCommit(new EventHandler<CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(CellEditEvent<Lecture, String> t) {
                    Lecture lecture = ((Lecture) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLecture(lecture.getSection(),t.getNewValue(),
                            lecture.getTime(),lecture.getRoom(),lecture);
                }
             });
        lectureTimeColumn.setOnEditCommit(new EventHandler<CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(CellEditEvent<Lecture, String> t) {
                    Lecture lecture = ((Lecture) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLecture(lecture.getSection(),lecture.getDays(),
                            t.getNewValue(),lecture.getRoom(),lecture);
                }
             });
        lectureRoomColumn.setOnEditCommit(new EventHandler<CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(CellEditEvent<Lecture, String> t) {
                    Lecture lecture = ((Lecture) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLecture(lecture.getSection(),lecture.getDays(),
                            lecture.getTime(),t.getNewValue(),lecture);
                }
             });
        
        TableView recitationTable = (TableView) gui.getGUINode(MT_RECITATION_TABLEVIEW);
        ((Button) gui.getGUINode(MT_ADD_RECITATION_BUTTON)).setOnAction(e->{
            controller.processAddRecitation();
        });
        ((Button) gui.getGUINode(MT_REMOVE_RECITATION_BUTTON)).setOnAction(e->{
            Recitation recitation = (Recitation)recitationTable.getSelectionModel().getSelectedItem();
            controller.processRemoveRecitation(recitation);
        });
        recitationTable.setOnMouseClicked((event) -> {
            controller.up();
        });
        
        TableColumn recitationSectionColumn =(TableColumn) recitationTable.getColumns().get(0);
        TableColumn recitationDaysTimeColumn =(TableColumn) recitationTable.getColumns().get(1);
        TableColumn recitationRoomColumn =(TableColumn) recitationTable.getColumns().get(2);
        TableColumn recitationTA1Column =(TableColumn) recitationTable.getColumns().get(3);
        TableColumn recitationTA2Column =(TableColumn) recitationTable.getColumns().get(4);
        
        recitationSectionColumn.setOnEditCommit(new EventHandler<CellEditEvent<Recitation, String>>() {
                @Override
                public void handle(CellEditEvent<Recitation, String> t) {
                    Recitation recitation = ((Recitation) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeRecitation(t.getNewValue(),recitation.getDaysTime(),
                            recitation.getRoom(),recitation.getTa1(),recitation.getTa2(),recitation);
                }
             });
        recitationDaysTimeColumn.setOnEditCommit(new EventHandler<CellEditEvent<Recitation, String>>() {
                @Override
                public void handle(CellEditEvent<Recitation, String> t) {
                    Recitation recitation = ((Recitation) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeRecitation(recitation.getSection(),t.getNewValue(),
                            recitation.getRoom(),recitation.getTa1(),recitation.getTa2(),recitation);
                }
             });
        recitationRoomColumn.setOnEditCommit(new EventHandler<CellEditEvent<Recitation, String>>() {
                @Override
                public void handle(CellEditEvent<Recitation, String> t) {
                    Recitation recitation = ((Recitation) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeRecitation(recitation.getSection(),recitation.getDaysTime(),
                            t.getNewValue(),recitation.getTa1(),recitation.getTa2(),recitation);
                }
             });
        recitationTA1Column.setOnEditCommit(new EventHandler<CellEditEvent<Recitation, String>>() {
                @Override
                public void handle(CellEditEvent<Recitation, String> t) {
                    Recitation recitation = ((Recitation) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeRecitation(recitation.getSection(),recitation.getDaysTime(),
                            recitation.getRoom(),t.getNewValue(),recitation.getTa2(),recitation);
                }
             });
        recitationTA2Column.setOnEditCommit(new EventHandler<CellEditEvent<Recitation, String>>() {
                @Override
                public void handle(CellEditEvent<Recitation, String> t) {
                    Recitation recitation = ((Recitation) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeRecitation(recitation.getSection(),recitation.getDaysTime(),
                            recitation.getRoom(),recitation.getTa1(),t.getNewValue(),recitation);
                }
             });
        
        TableView labTable = (TableView) gui.getGUINode(MT_LAB_TABLEVIEW);
        ((Button) gui.getGUINode(MT_ADD_LAB_BUTTON)).setOnAction(e->{
            controller.processAddLab();
        });
        ((Button) gui.getGUINode(MT_REMOVE_LAB_BUTTON)).setOnAction(e->{
            Lab lab = (Lab)labTable.getSelectionModel().getSelectedItem();
            controller.processRemoveLab(lab);
        });
        labTable.setOnMouseClicked((event) -> {
            controller.up();
        });
        
        TableColumn labSectionColumn =(TableColumn) labTable.getColumns().get(0);
        TableColumn labDaysTimeColumn =(TableColumn) labTable.getColumns().get(1);
        TableColumn labRoomColumn =(TableColumn) labTable.getColumns().get(2);
        TableColumn labTA1Column =(TableColumn) labTable.getColumns().get(3);
        TableColumn labTA2Column =(TableColumn) labTable.getColumns().get(4);
        
        labSectionColumn.setOnEditCommit(new EventHandler<CellEditEvent<Lab, String>>() {
                @Override
                public void handle(CellEditEvent<Lab, String> t) {
                    Lab lab = ((Lab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLab(t.getNewValue(),lab.getDaysTime(),
                            lab.getRoom(),lab.getTa1(),lab.getTa2(),lab);
                }
             });
        labDaysTimeColumn.setOnEditCommit(new EventHandler<CellEditEvent<Lab, String>>() {
                @Override
                public void handle(CellEditEvent<Lab, String> t) {
                    Lab lab = ((Lab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLab(lab.getSection(),t.getNewValue(),
                            lab.getRoom(),lab.getTa1(),lab.getTa2(),lab);
                }
             });
        labRoomColumn.setOnEditCommit(new EventHandler<CellEditEvent<Lab, String>>() {
                @Override
                public void handle(CellEditEvent<Lab, String> t) {
                    Lab lab = ((Lab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLab(lab.getSection(),lab.getDaysTime(),
                            t.getNewValue(),lab.getTa1(),lab.getTa2(),lab);
                }
             });
        labTA1Column.setOnEditCommit(new EventHandler<CellEditEvent<Lab, String>>() {
                @Override
                public void handle(CellEditEvent<Lab, String> t) {
                    Lab lab = ((Lab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLab(lab.getSection(),lab.getDaysTime(),
                            lab.getRoom(),t.getNewValue(),lab.getTa2(),lab);
                }
             });
        labTA2Column.setOnEditCommit(new EventHandler<CellEditEvent<Lab, String>>() {
                @Override
                public void handle(CellEditEvent<Lab, String> t) {
                    Lab lab = ((Lab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    controller.processChangeLab(lab.getSection(),lab.getDaysTime(),
                            lab.getRoom(),lab.getTa1(),t.getNewValue(),lab);
                }
             });
        
        /*
           OH PANE
        */
        //KEYBOARD 
        
        
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
                if (startTime.getValue()!=null&&endTime.getValue()!=null){
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
                }

        });
        
        endTime.setOnAction((event) -> {
            if (startTime.getValue()!=null&&endTime.getValue()!=null){
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
            }
          
               
        });
        
        
        
        
        //TA TOGGLE
        RadioButton ALL = (RadioButton)gui.getGUINode(OH_TOGGLE_ALL);
        RadioButton Undergrad = (RadioButton)gui.getGUINode(OH_TOGGLE_UNDERGRADUATE);
        RadioButton Grad = (RadioButton)gui.getGUINode(OH_TOGGLE_GRADUATE);
        ALL.setOnAction((event) -> {
            if(ALL.isSelected()){
                controller.changeToAll();
                controller.updateOH();
            }
        });
        Undergrad.setOnAction((event) -> {
            if(Undergrad.isSelected()){
                controller.changeToUndergraduate();
                controller.updateOH();
                
            }
        });
        Grad.setOnAction((event) -> {
            if(Grad.isSelected()){
                controller.changeToGrad();
                controller.updateOH();
                
            }
        });
        // DON'T LET ANYONE SORT THE TABLES
        for (int i = 0; i < officeHoursTableView.getColumns().size(); i++) {
            ((TableColumn)officeHoursTableView.getColumns().get(i)).setSortable(false);
        }
        
        /****
         * SCHEDULE PANE
         * 
         */
        ((DatePicker)gui.getGUINode(SC_START_DATE_DATE_PICKER)).valueProperty().
                addListener((observable, oldValue, newValue) -> {
                   controller.processChangeStartDate(oldValue, newValue);
                   controller.up();
        });
        ((DatePicker)gui.getGUINode(SC_END_DATE_DATE_PICKER)).valueProperty().
                addListener((observable, oldValue, newValue) -> {
                   controller.processChangeEndDate(oldValue, newValue);
                   controller.up();
        });
        TableView scheduleTable = (TableView)gui.getGUINode(SC_SCHEDULE_TABLEVIEW);
        ComboBox type = (ComboBox) gui.getGUINode(SC_TYPE_COMBO_BOX);
        type.setOnAction((event) -> {
            controller.up();
        });
        DatePicker date = (DatePicker) gui.getGUINode(SC_DATE_DATE_PICKER);
        date.setOnAction((event) -> {
            controller.up();
        });
        TextField title = (TextField)gui.getGUINode(SC_TITLE_TEXT_FIELD);
        title.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null&&oldValue
                    !=null){
                controller.up();
            }
        });
        TextField topic = (TextField)gui.getGUINode(SC_TOPIC_TEXT_FIELD);
        topic.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null&&oldValue
                    !=null){
                controller.up();
            }
        });
        TextField link = (TextField)gui.getGUINode(SC_LINK_TEXT_FIELD);
        link.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null&&oldValue
                    !=null){
                controller.up();
            }
        });
       
        
        ((Button) gui.getGUINode(SC_ADD_ITEM_BUTTON)).setOnAction(e->{
            controller.processAddOrUpdateSchedule();
            type.setValue(null);
            date.setValue(null);
            title.setText("");
            topic.setText("");
            link.setText("");
            scheduleTable.getSelectionModel().clearSelection();
        });
        ((Button) gui.getGUINode(SC_REMOVE_ITEM_BUTTON)).setOnAction(e->{
            Schedule schedule = (Schedule)scheduleTable.getSelectionModel().getSelectedItem();
            controller.processRemoveSchedule(schedule);
        });
        scheduleTable.setOnMouseClicked((event) -> {
            Schedule schedule = (Schedule)scheduleTable.getSelectionModel().getSelectedItem();
            if (schedule!=null) {
                controller.updateScheduleField(schedule);
            }
        });
        ((Button) gui.getGUINode(SC_CLEAR_ITEM_BUTTON)).setOnAction(e->{
            type.setValue(null);
            date.setValue(null);
            title.setText("");
            topic.setText("");
            link.setText("");
            scheduleTable.getSelectionModel().clearSelection();
            ((Button) gui.getGUINode(SC_ADD_ITEM_BUTTON)).setText("Add");
        });
    
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
    
    public class EditingLectureCell extends TableCell<Lecture, String> {
 
        private TextField textField;
 
        public EditingLectureCell() {
        }
 
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(null);
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                }
            });
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
    public class EditingRecitationCell extends TableCell<Recitation, String> {
 
        private TextField textField;
 
        public EditingRecitationCell() {
        }
 
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(null);
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                }
            });
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
    public class EditingLabCell extends TableCell<Lab, String> {
 
        private TextField textField;
 
        public EditingLabCell() {
        }
 
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(null);
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                }
            });
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

}
 
