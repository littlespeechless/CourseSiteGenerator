/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;


import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import jtps.jTPS_Transaction;




/**
 *
 * @author zhengyu
 */
public class AddTimeSlot_Transaction implements jTPS_Transaction{
    TimeSlot data;
    CourseSiteData app;
    TeachingAssistantPrototype ta;
    int column;
    
    public AddTimeSlot_Transaction(CourseSiteData initApp,TimeSlot initData, TeachingAssistantPrototype initTA,int iniColumn) {
        data = initData;
        ta = initTA;
        column=iniColumn;
        app = initApp;
    }

    @Override
    public void doTransaction() {
        data.addTA(app.getColumnDayOfWeek(column), ta);
    }

    @Override
    
    public void undoTransaction() {
        data.removeTA(app.getColumnDayOfWeek(column),ta);        
    }
}
