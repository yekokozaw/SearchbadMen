package com.solt.police.model;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.solt.police.entity.Police;
import com.solt.police.service.PoliceService;
import com.solt.police.utility.ProjectException;
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

public class EditPoliceDataViewController implements Initializable{
	
	private PoliceService psrv;
	private Police police;
	private Reloader reloader;

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
    
    private Date date;
    
    public void AccessPoliceData() {
    	date = police.getBirthday();
    	LocalDate ld = date.toLocalDate();
    	name.setText(police.getName());
    	position.setText(police.getPosition());
    	birthday.setValue(ld);
    	address.setText(police.getAddress());
    	name.setEditable(false);
    }
    
    @SuppressWarnings("static-access")
	public void add() {
    	try{
    		if (loginid.getText().isEmpty() || loginid.getText()==null) {
    		throw new ProjectException("Login ID is Empty!");
			
		}
    		police.setLoginid(loginid.getText());
    		police.setName(name.getText());
       		police.setPassword(password.getText());
       		police.setBirthday(date.valueOf(birthday.getValue().toString()));
       		police.setPosition(position.getText());
       		police.setAddress(address.getText());
       		psrv.update(police);
       		reloader.ReloadView();
       		close();
    	}catch (ProjectException e){
        	ShowAlert.alert(e.getMessage(), AlertType.WARNING);
        	
        }
    }

    public void close() {
    	name.getScene().getWindow().hide();
    }
    
    public static void showView(Reloader load, Police police) {
    	try {
    		FXMLLoader loader = new FXMLLoader(EditPoliceDataViewController.class.getResource("EditPoliceDataView.fxml"));
			Parent	root = loader.load();
			EditPoliceDataViewController control = loader.getController();
			control.police = police;
			control.reloader = load;
			control.AccessPoliceData();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
	    	stage.setScene(new Scene(root));
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		psrv = new PoliceService();
		police = new Police();
		reloader = new PoliceDataViewController();
	}

}
