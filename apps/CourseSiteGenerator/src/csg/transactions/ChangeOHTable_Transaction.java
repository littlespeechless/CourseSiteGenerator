package csg.transactions;

import csg.CourseSiteGenerateApp;
import static csg.CourseSitePropertyType.OH_END_TIME_COMBO_BOX;
import static csg.CourseSitePropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSitePropertyType.OH_START_TIME_COMBO_BOX;
import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeOHTable_Transaction implements jTPS_Transaction {
    CourseSiteGenerateApp app;
    CourseSiteData data;
    TeachingAssistantPrototype ta;
    String oldStartTime;
    String newStartTime;
    String oldEndTime;
    String newEndTime;
    ObservableList<TimeSlot> oldList;

    
    public ChangeOHTable_Transaction(CourseSiteGenerateApp initApp,CourseSiteData initData, String initOldStartTime, 
            String initOldEndTime,String initNewStartTime, String initNewEndTime, ObservableList<TimeSlot> initList) {
        app =initApp;
        data = initData;
        oldStartTime = initOldStartTime;
        oldEndTime = initOldEndTime;
        newStartTime = initNewStartTime;
        newEndTime = initNewEndTime;
        oldList = initList;
    }

    @Override
    public void doTransaction() {
        ObservableList<TimeSlot> list = data.changeOHTable(newStartTime, newEndTime,oldList);
        AppGUIModule gui = app.getGUIModule();
        TableView officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.setItems(list);
        ComboBox startTime = (ComboBox)gui.getGUINode(OH_START_TIME_COMBO_BOX);
        ComboBox endTime = (ComboBox)gui.getGUINode(OH_END_TIME_COMBO_BOX);
        startTime.setValue(newStartTime);
        endTime.setValue(newEndTime);
        
    }

    @Override
    public void undoTransaction() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox startTime = (ComboBox)gui.getGUINode(OH_START_TIME_COMBO_BOX);
        ComboBox endTime = (ComboBox)gui.getGUINode(OH_END_TIME_COMBO_BOX);
        TableView officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.setItems(oldList);
        startTime.setValue(oldStartTime);
        endTime.setValue(oldEndTime);
        
    }
}
