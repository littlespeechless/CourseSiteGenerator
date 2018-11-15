package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeDate_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    LocalDate oldDate;
    LocalDate newDate;
    DatePicker datePicker;
    
    public ChangeDate_Transaction(CourseSiteData initData, LocalDate initOldDate,LocalDate initNewDate,
            DatePicker initDatePicker) {
        data = initData;
        oldDate = initOldDate;
        newDate = initNewDate;
        datePicker = initDatePicker;
    }

    @Override
    public void doTransaction() {
         datePicker.setValue(newDate);
    }

    @Override
    public void undoTransaction() {
        datePicker.setValue(oldDate);
    }
}
