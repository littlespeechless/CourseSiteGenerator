package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.Lab;
import csg.data.TeachingAssistantPrototype;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class RemvLab_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    Lab lab;
    
    public RemvLab_Transaction(CourseSiteData initData, Lab initLab) {
        data = initData;
        lab = initLab;
    }

    @Override
    public void doTransaction() {
        data.removeLab(lab);        
    }

    @Override
    public void undoTransaction() {
        data.addLab(lab);
    }
}
