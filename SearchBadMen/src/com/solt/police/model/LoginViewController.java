package com.solt.police.model;

import java.net.URL;
import java.util.ResourceBundle;

import com.solt.police.entity.Police;
import com.solt.police.service.PoliceService;
import com.solt.police.utility.ProjectException;
import com.solt.police.utility.Security;
import com.solt.police.utility.ShowAlert;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;
    private PoliceService srv;
    public void clear() {
    	name.clear();
    	password.clear();
    }

    public void submit() {
    	try {
        	String n = name.getText();
        	String pass = password.getText();
        	Police police = srv.findLoginname(n);
        	if (police == null) {
    			throw new ProjectException("Login Name is invalid!");
    		}
        	police.getPassword();
        	if (!pass.equals(police.getPassword())) {
    				throw new ProjectException("Password is invalid!");
        	}
        	
        	Security.setPolice(police);
        	MainFrameViewController.showview();
        	name.getScene().getWindow().hide();
        	}catch(ProjectException e)
        	{
        		ShowAlert.alert(e.getMessage(), AlertType.WARNING);
        	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		srv = new PoliceService();
		
	}

}

