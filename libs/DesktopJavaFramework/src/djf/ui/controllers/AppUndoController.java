package djf.ui.controllers;

import static djf.AppPropertyType.SAVE_BUTTON;
import djf.AppTemplate;
import djf.modules.AppGUIModule;
import javafx.scene.control.Button;
import jtps.jTPS;

public class AppUndoController {
    private AppTemplate app;
    
    public AppUndoController(AppTemplate initApp) {
        app = initApp;
    }
    
    public void processUndoRequest() {
        jTPS tps = app.getTPS();
        tps.undoTransaction();
        app.getFoolproofModule().updateAll();
        AppGUIModule gui = app.getGUIModule();
        ((Button)gui.getGUINode(SAVE_BUTTON)).setDisable(false);
    }
    
    public void processRedoRequest() {
        jTPS tps = app.getTPS();
        tps.doTransaction();
        app.getFoolproofModule().updateAll();
        AppGUIModule gui = app.getGUIModule();
        ((Button)gui.getGUINode(SAVE_BUTTON)).setDisable(false);
    }    
}
