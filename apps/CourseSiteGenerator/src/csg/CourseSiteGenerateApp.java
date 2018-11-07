/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import csg.clipboard.CourseSiteClipboard;
import csg.data.CourseSiteData;
import csg.files.CourseSiteFiles;
import csg.workspace.CourseSiteWorkspace;
import djf.AppTemplate;
import djf.components.AppClipboardComponent;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import djf.components.AppWorkspaceComponent;
import java.util.Locale;
import static javafx.application.Application.launch;

/**
 *
 * @author zhengyu
 */
public class CourseSiteGenerateApp extends AppTemplate {
    
   /**
     * This is where program execution begins. Since this is a JavaFX app it
     * will simply call launch, which gets JavaFX rolling, resulting in sending
     * the properly initialized Stage (i.e. window) to the start method inherited
     * from AppTemplate, defined in the Desktop Java Framework.
     * 
     * @param args Command-line arguments, there are no such settings used
     * by this application.
     */
    public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	launch(args);
    }

   @Override
    public AppDataComponent buildDataComponent(AppTemplate app) {
        return new CourseSiteData(this);
    }

    @Override
    public AppFileComponent buildFileComponent() {
        return new CourseSiteFiles(this);
    }

    @Override
    public AppWorkspaceComponent buildWorkspaceComponent(AppTemplate app) {
        return new CourseSiteWorkspace(this);        
    }

    @Override
    public AppClipboardComponent buildClipboardComponent(AppTemplate app) {
       return new CourseSiteClipboard(this);
    }
  
}
