package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.Lab;
import csg.data.Schedule;
import csg.data.TeachingAssistantPrototype;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class RemvSchedule_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    Schedule schedule;
    
    public RemvSchedule_Transaction(CourseSiteData initData, Schedule initSchedule) {
        data = initData;
        schedule = initSchedule;
    }

    @Override
    public void doTransaction() {
       data.removeSchedule(schedule);
    }

    @Override
    public void undoTransaction() {
        data.addSchedule(schedule);
    }
}
