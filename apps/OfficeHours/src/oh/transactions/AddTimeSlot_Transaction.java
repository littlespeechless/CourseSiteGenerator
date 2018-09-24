/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import oh.data.TeachingAssistantPrototype;
import jtps.jTPS_Transaction;
import oh.data.TimeSlot;
import oh.data.OfficeHoursData;
import oh.OfficeHoursApp;



/**
 *
 * @author zhengyu
 */
public class AddTimeSlot_Transaction implements jTPS_Transaction{
    TimeSlot data;
    OfficeHoursApp app;
    TeachingAssistantPrototype ta;
    int column;
    
    public AddTimeSlot_Transaction(OfficeHoursApp initApp,TimeSlot initData, TeachingAssistantPrototype initTA,int iniColumn) {
        data = initData;
        ta = initTA;
        column=iniColumn;
        app = initApp;
    }

    @Override
    public void doTransaction() {
        OfficeHoursData officeHoursData = (OfficeHoursData) app.getDataComponent();
        data.addTA(officeHoursData.getColumnDayOfWeek(column),ta);        
    }

    @Override
    
    public void undoTransaction() {
        OfficeHoursData officeHoursData = (OfficeHoursData) app.getDataComponent();
        data.removeTA(officeHoursData.getColumnDayOfWeek(column),ta);        
    }
}
