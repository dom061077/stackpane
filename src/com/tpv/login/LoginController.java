/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpv.login;

import com.tpv.enums.OrigenPantallaErrorEnum;
import com.tpv.exceptions.TpvException;
import com.tpv.modelo.Checkout;
import com.tpv.modelo.Usuario;
import com.tpv.principal.DataModelTicket;
import com.tpv.service.UsuarioService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx8tpv1.TabPanePrincipalController;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.datafx.controller.flow.action.ActionTrigger;

/**
 *
 * @author daniel
 */

//@FXMLController(value="Login.fxml", title = "Ingreso al Sistema")
public class LoginController implements Initializable{
    
    private TabPanePrincipalController tabController;
    
    Logger log = Logger.getLogger(LoginController.class);
    UsuarioService usuarioService = new UsuarioService();    
    @FXML
    @ActionTrigger("iniciarSesion")
    private Button buttonLogin;
    
    @FXML
    @ActionTrigger("mostrarError")
    private Button goToErrorButton;
    
    @FXML
    private StackPane stackPaneError;
    
    @FXML
    private TextField userName;
    
    @FXML
    public TextField password;
    
    @FXML
    private ImageView imageViewLogoRight;
    
    @FXML
    private ImageView imageViewLogoLeft;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private Label labelError;
                      
    
    @Inject
    private DataModelTicket modelTicket;
    
    
    @FXML
    public  void initialize(URL url, ResourceBundle rb) {
            password.setOnKeyPressed(keyEvent->{
                log.debug("INGRESEANDO POR PRESIONAR TECLA ENTER desde initialize method");
            });
    }    
    
    @PostConstruct
    public void init(){
        loadImage();
            password.setOnKeyPressed(keyEvent->{
                log.debug("INGRESEANDO POR PRESIONAR TECLA ENTER");
                /*if(keyEvent == KeyCode.ENTER){
                    
                    Usuario usuario = null;
                    try{
                        usuario = usuarioService.authenticar(userName.getText(), password.getText());
                    }catch(TpvException e){
                        log.error("Error: "+e.getMessage());
                        modelTicket.setOrigenPantalla(OrigenPantallaErrorEnum.PANTALLA_LOGIN);
                        modelTicket.setException(e);
                        goToErrorButton.fire();
                    }                        
                    if(usuario!=null){
                        modelTicket.setUsuario(usuario);
                        this.tabController.getButtonMenuPrincipal().fire();
                        //buttonLogin.fire();
                    }else{
                        labelError.setText("Usuario o contraseña incorrectos");
                        
                        stackPaneError.setVisible(true);
                        labelError.requestFocus();
                        
                    }
                    
                }
                if(keyEvent.getCode() == KeyCode.ESCAPE){
                    userName.requestFocus();
                }
                if(keyEvent.getCode() == KeyCode.F11){
                    System.exit(0);
                }*/
                
            });
        
        
        Platform.runLater(() -> {
            Checkout checkout = null;
            try{
                checkout = usuarioService.checkMac();
            }catch(TpvException e){
                log.error("Error: "+e.getMessage());
                modelTicket.setOrigenPantalla(OrigenPantallaErrorEnum.PANTALLA_LOGIN);
                modelTicket.setException(e);
                goToErrorButton.fire();
            }
            if(checkout == null){
                log.fatal("La MAC de la PC no coincide con el registro del Checkout");
                labelError.setText("La PC no está habilitada para este Checkout");
                FadeTransition fadeInOut;
                fadeInOut = new FadeTransition(Duration.seconds(1),labelError);
		fadeInOut.setFromValue(1.0);
		fadeInOut.setToValue(.20);
		fadeInOut.setCycleCount(FadeTransition.INDEFINITE);
		fadeInOut.setAutoReverse(true);
		fadeInOut.play();
                
                
                stackPaneError.setVisible(true);
                labelError.setOnKeyPressed(keyEvent -> {
                    if(keyEvent.getCode() == KeyCode.F11){
                        System.exit(0);
                    }
                });
                
            }else
                modelTicket.setCheckout(checkout);
            userName.requestFocus();            
            userName.setOnKeyPressed(keyEvent->{
                if(keyEvent.getCode() == KeyCode.ENTER){
                    password.requestFocus();
                    
                }
                if(keyEvent.getCode() == KeyCode.F11){
                    System.exit(0);
                }
                
            });
            
            labelError.setOnKeyPressed(keyEventLabelError -> {
                if(keyEventLabelError.getCode() == KeyCode.ESCAPE){
                    stackPaneError.setVisible(false);
                    userName.requestFocus();
                }
            });
            
            
                    
        });
        
    }
    
    private void loadImage(){
        String f = this.getClass().getResource("/com/tpv/resources/logologin.jpg").toExternalForm();
        imageViewLogoRight.setImage(new Image(f));
        imageViewLogoLeft.setImage(new Image(f));
    }
    
    public void setTabController(TabPanePrincipalController tabController){
        this.tabController=tabController;
    }    
            
    
}
