package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.Lecture;
import csg.data.TeachingAssistantPrototype;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class AddLecture_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    Lecture lecture;
    
    public AddLecture_Transaction(CourseSiteData initData, Lecture initLecture) {
        data = initData;
        lecture = initLecture;
    }

    @Override
    public void doTransaction() {
        data.addLecture(lecture);
    }

    @Override
    public void undoTransaction() {
        data.removeLecture(lecture);
    }
}
