package djf.ui.dialogs;

import djf.AppTemplate;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.net.InetAddressCachePolicy;

/**
 *
 * @author McKillaGorilla
 */
public class AppWebDialog extends Stage {

    AppTemplate app;
    WebView webView;
    boolean first;
    public AppWebDialog(AppTemplate initApp) {
        // KEEP THIS TO ACCESS THINGS
        app = initApp;

        // THIS WILL BE THE ONLY COMPONENT
        webView = new WebView();

        // NOW PUT THE GRID IN THE SCENE AND THE SCENE IN THE DIALOG
        Scene scene = new Scene(webView);
        this.setScene(scene);
        // MAKE IT MODAL
        this.initOwner(app.getGUIModule().getWindow());
        this.initModality(Modality.APPLICATION_MODAL);
        webView.setCache(false);
    }

    public void showWebDialog(String htmlFilePath) throws MalformedURLException {
        WebEngine webEngine = webView.getEngine();
        webEngine.documentProperty().addListener(e->{
            // THE PAGE WILL LOAD ASYNCHRONOUSLY, SO MAKE
            // SURE TO GRAB THE TITLE FOR THE WINDOW
            // ONCE IT'S BEEN LOADED
            String title = webEngine.getTitle();
            this.setTitle(title);

        });
        updatePage();
        webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
             updatePage();
        });
        //InetAddressCachePolicy.setNegativeIfNotSet(InetAddressCachePolicy.NEVER);
        //URL pageURL = new File(htmlFilePath).toURI().toURL();
        //String pagePath = pageURL.toExternalForm();
        //System.out.println(pagePath);
        
        File htmlFile = new File(htmlFilePath);
        webEngine.load(htmlFile.toURI().toString()); 
        this.showAndWait();
        
    }
    public  void updatePage(){
        first = true;
        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
            //System.out.println(t1);
            if(t1.equals(State.SUCCEEDED) && first) {
            first = Boolean.FALSE;
            webView.getEngine().reload();
            }
        }
        });
    }
    public WebEngine getWebEngine(){
        return webView.getEngine();
    }
    public void showWebDialog() {
        String html = buildWebContent();
        WebEngine webEngine = webView.getEngine();
        webEngine.documentProperty().addListener(e->{
            // THE PAGE WILL LOAD ASYNCHRONOUSLY, SO MAKE
            // SURE TO GRAB THE TITLE FOR THE WINDOW
            // ONCE IT'S BEEN LOADED
            String title = webEngine.getTitle();
            this.setTitle(title);
        });
        webEngine.loadContent(html);
        this.showAndWait();
    }

    public String buildWebContent() {
        return "<html><body><h3>Override buildWebContent to load dynamic content.</h3></body></html>";
    }
}
