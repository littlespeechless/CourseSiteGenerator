package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.Lecture;
import csg.data.TeachingAssistantPrototype;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class EditLecture_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    Lecture lecture;
    String oldSection;
    String oldDays;
    String oldTime;
    String oldRoom;
    String newSection;
    String newDays;
    String newTime;
    String newRoom;
    
    public EditLecture_Transaction(CourseSiteData initData, Lecture initLecture,
            String initSection, String initDays, String initTime, String initRoom) {
        data = initData;
        lecture = initLecture;
        newSection =initSection;
        newDays =initDays;
        newTime = initTime;
        newRoom = initRoom;
        oldSection = lecture.getSection();
        oldDays = lecture.getDays();
        oldTime = lecture.getTime();
        oldRoom = lecture.getRoom();
    }

    @Override
    public void doTransaction() {
        data.editLecture(newSection, newDays, newTime, newRoom, lecture);
    }

    @Override
    public void undoTransaction() {
       data.editLecture(oldSection, oldDays, oldTime, oldRoom, lecture);
    }
}
