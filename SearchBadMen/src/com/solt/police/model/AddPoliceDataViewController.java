package com.solt.police.model;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.solt.police.entity.Police;
import com.solt.police.service.PoliceService;
import com.solt.police.utility.Reloader;
import com.solt.police.utility.ShowAlert;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddPoliceDataViewController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField position;

    @FXML
    private TextArea address;

    @FXML
    private PasswordField password;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField loginid;
    
    private PoliceService psrv;
    private Reloader reload;
    private Date date;
   public void close() {
	   name.getScene().getWindow().hide();
    }
   
   @SuppressWarnings("static-access")
public void add() {
	   if (!name.getText().isEmpty()&&name.getText()!=null) {
   		Police police = new Police();
   		police.setLoginid(loginid.getText());
   		police.setName(name.getText());
   		police.setPassword(password.getText());
   		police.setBirthday(date.valueOf(birthday.getValue().toString()));
   		police.setPosition(position.getText());
   		police.setAddress(address.getText());
		psrv.add(police);
		reload.ReloadView();
		close();
			
		}
   	else
   	{
   		ShowAlert.alert("Police Name is empty!", AlertType.WARNING);
   	}
    }
   public static void showView(Reloader reload) {
   	try {
   		FXMLLoader loader = new FXMLLoader(AddPoliceDataViewController.class.getResource("AddPoliceDataView.fxml"));
   		Parent view = loader.load();
   		AddPoliceDataViewController controller = loader.getController();
   		controller.reload = reload;
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(view));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
   }
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	psrv = new PoliceService();
	reload = (Reloader) new PoliceDataViewController();
   }

}
