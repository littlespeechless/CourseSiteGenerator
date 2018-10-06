package oh.transactions;

import jtps.jTPS_Transaction;
import oh.data.OfficeHoursData;
import oh.data.TeachingAssistantPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class PasteTA_Transaction implements jTPS_Transaction {
    OfficeHoursData data;
    TeachingAssistantPrototype ta;
    boolean isCut;
    public PasteTA_Transaction(OfficeHoursData initData, TeachingAssistantPrototype initTA, boolean cutStats) {
        data = initData;
        ta = initTA;
        isCut = cutStats;
    }

    @Override
    public void doTransaction() {
        if (isCut = false) {
            data.addTA(ta);
        }else{
            data.cutTARestore(ta);
        }
        
    }

    @Override
    public void undoTransaction() {
        if (isCut = false) {
            data.removeTA(ta);
        }else{
            data.cutTA(ta);
        }
        
    }
}
