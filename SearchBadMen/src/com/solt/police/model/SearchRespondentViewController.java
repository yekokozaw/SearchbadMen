package com.solt.police.model;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.solt.police.entity.Respondent;
import com.solt.police.service.RespondentService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SearchRespondentViewController implements Initializable{

    @FXML
    private TextField schcrimeid;

    @FXML
    private Label name;

    @FXML
    private Label birthday;

    @FXML
    private Label crime;

    @FXML
    private Label contact;

    @FXML
    private Label address;

    @FXML
    private ImageView imageview;

    private RespondentService rsrv;
    private List<Respondent>list;
    
    public void schclear() {
    	schcrimeid.setText(null);
    	name.setText(null);
    	birthday.setText(null);
    	crime.setText(null);
    	contact.setText(null);
    	address.setText(null);
    	imageview.setImage(null);
    }
	public void search() {
		try {
			list = rsrv.findcrimeid(schcrimeid.getText());
			String path= "D:\\Images\\"+schcrimeid.getText()+".jpg";
			File file = new File(path);
			imageview.setFitHeight(300);
			imageview.setFitWidth(300);
			Image image = new Image(file.toURI().toString());
			 for (Respondent respondent : list) {
				name.setText(respondent.getName());
				birthday.setText(respondent.getBirthday().toString());
				crime.setText(respondent.getCrime());
				contact.setText(respondent.getContact());
				address.setText(respondent.getAddress());
				imageview.setImage(image);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rsrv = new RespondentService();
		list = new ArrayList<Respondent>();
	}
}
