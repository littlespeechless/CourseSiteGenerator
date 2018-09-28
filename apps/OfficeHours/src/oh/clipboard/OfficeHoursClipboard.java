package oh.clipboard;

import djf.components.AppClipboardComponent;
import djf.modules.AppGUIModule;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.OH_TAS_TABLE_VIEW;
import oh.data.OfficeHoursData;
import oh.data.TeachingAssistantPrototype;
import oh.transactions.CutTA_Transaction;
import oh.transactions.PasteTA_Transaction;

/**
 *
 * @author McKillaGorilla
 */
public class OfficeHoursClipboard implements AppClipboardComponent {
    OfficeHoursApp app;
    ArrayList<TeachingAssistantPrototype> clipboardCutItems;
    ArrayList<TeachingAssistantPrototype> clipboardCopiedItems;
    TeachingAssistantPrototype clipboardItem;
    TeachingAssistantPrototype renamedItem;
    int renameCounter;
    
    public OfficeHoursClipboard(OfficeHoursApp initApp) {
        app = initApp;
        clipboardCutItems = null;
        clipboardCopiedItems = null;
        clipboardItem =null;
        renamedItem = null;
        renameCounter = 1;
    }
    
    @Override
    public void cut() {
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        TableView tatableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        TeachingAssistantPrototype ta =  (TeachingAssistantPrototype) tatableView.getSelectionModel().getSelectedItem();
        CutTA_Transaction cutTA_Transaction = new CutTA_Transaction(data, ta);
        app.processTransaction(cutTA_Transaction);
        clipboardItem=ta;
        app.getFoolproofModule().updateAll();
    }

    @Override
    public void copy() {
        AppGUIModule gui = app.getGUIModule();
        TableView tatableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        TeachingAssistantPrototype ta =  (TeachingAssistantPrototype) tatableView.getSelectionModel().getSelectedItem();
        clipboardItem = ta;
        app.getFoolproofModule().updateAll();
    }
    
    @Override
    public void paste() {
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        if(data.getAllTAS().contains(clipboardItem)==true){
            renamedItem = new TeachingAssistantPrototype
        (renameCounter+clipboardItem.getName(), renameCounter+clipboardItem.getEmail(), clipboardItem.getType());
            renameCounter++;
            PasteTA_Transaction pasteTA_Transaction = new PasteTA_Transaction(data, renamedItem);
            app.processTransaction(pasteTA_Transaction);
            data.refreshOH();
        }else{
            PasteTA_Transaction pasteTA_Transaction = new PasteTA_Transaction(data, clipboardItem);
            app.processTransaction(pasteTA_Transaction);
            data.refreshOH();
        }
        
    }    

    @Override
    public boolean hasSomethingToCut() {
        return ((OfficeHoursData)app.getDataComponent()).isTASelected();
    }

    @Override
    public boolean hasSomethingToCopy() {
        return ((OfficeHoursData)app.getDataComponent()).isTASelected();
    }

    @Override
    public boolean hasSomethingToPaste() {
       /* if ((clipboardCutItems != null) && (!clipboardCutItems.isEmpty()))
            return true;
        else if ((clipboardCopiedItems != null) && (!clipboardCopiedItems.isEmpty()))
            return true;
        else
            return false;*/
        if (clipboardItem!=null) {
            return true;
        }else
            return false;
    }
    public void decreementRenameCounter(){
        renameCounter--;
    }

}