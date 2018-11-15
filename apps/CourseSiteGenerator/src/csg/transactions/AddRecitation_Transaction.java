package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.Recitation;
import csg.data.TeachingAssistantPrototype;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class AddRecitation_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    Recitation recitation;
    
    public AddRecitation_Transaction(CourseSiteData initData, Recitation initRecitation) {
        data = initData;
        recitation = initRecitation;
    }

    @Override
    public void doTransaction() {
        data.addRecitation(recitation);
    }

    @Override
    public void undoTransaction() {
        data.removeRecitation(recitation);
    }
}
