/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx8tpv1;

import com.tpv.util.Connection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader.StateChangeNotification;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import org.datafx.samples.app.MasterViewController;
import org.apache.log4j.Logger;


/**
 *
 * @author daniel
 */
public class JavaFX8TPV1 extends Application {
    Logger log = Logger.getLogger(JavaFX8TPV1.class);
    BooleanProperty ready = new SimpleBooleanProperty(false);
    @Override
    public void start(Stage stage) throws Exception {
        
       stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
               Platform.exit();
               System.exit(0);
            }
       });
        
        //DOMConfigurator.configure(getClass().getResource("log4j.xml"));
        
        /*Runnable task = () -> {
            while(true){
                try{
                    Thread.sleep(500);
                    if(!Connection.isDBConnected() && Connection.getButtonFlowFire()!=null){
                        log.debug("Dispara evento de fallo de conexión");
                        Connection.fireButtonEvent();
                    }
                        
                    
                }catch(InterruptedException e){
                    log.info("Error en pausa de monitor de comunicaciones");
                }
                
            }
        };
        
        Thread thread = new Thread(task);
        thread.start();
        */
        
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
      
        log.debug("Inicializando Flow de infaces gráficas");
        try{
            log.debug("INICIANDO LAS CONEXIONES");
            log.debug("PATH DEL DIRECTORIO DE RECURSOS: "+this.getClass().getResource("/com/tpv/resources/people.png"));
            Connection.initConnections();
            
        }   catch(Exception e){
            
            log.error("Error general de conexiòn a la base de datos");
            e.printStackTrace();
        }
        
        ready.setValue(Boolean.TRUE);
        notifyPreloader(new StateChangeNotification(
                 StateChangeNotification.Type.BEFORE_START));
        
        //stage.show();
        Parent root = FXMLLoader.load(getClass().getResource("TabPanePrincipal.fxml"));
        stage.setScene(new Scene(root,640,480));
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
