package com.solt.police.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.solt.police.entity.Respondent;
import com.solt.police.service.RespondentService;
import com.solt.police.utility.Reloader;
import com.solt.police.utility.Security;
import com.solt.police.utility.ShowAlert;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddRespondentViewController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField crime;

    @FXML
    private TextArea address;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField crimeid;

    @FXML
    private TextField contact;

    @FXML
    private TextField policename;
    
    @FXML
    private Button chooseimage;
    
    private RespondentService rsrv;
    private Reloader reload;
    private Date date;
    private BufferedImage image = null;
    private File file = null;
    
    public void close() {
    	name.getScene().getWindow().hide();
    }
    public void loadImage() {
    	final Stage stage = null;
    	int width = 400;
    	int height =400;
    	
    	FileChooser filechoose = new FileChooser();
    	File createfolder = new File("D:\\Images");
    	boolean result = createfolder.mkdir();
    	if (result == true) { 	
    	
    	try {
			file = filechoose.showOpenDialog(stage);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(file);	
			} catch (Exception e) {
				e.printStackTrace();
		}
    	}else
    	{
    		try {
    			file = filechoose.showOpenDialog(stage);
    			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    			image = ImageIO.read(file);	
    			} catch (Exception e) {
    				e.printStackTrace();
    		}
    	}
    	chooseimage.setText(file.getName());
    }
    @SuppressWarnings("static-access")
	public void add() {
    	if (!crimeid.getText().isEmpty()&&crimeid.getText()!=null) {
    		
    		Respondent respondent = new Respondent();
    		respondent.setCrimeid(crimeid.getText());
    		respondent.setPhoto(crimeid.getText());
    		respondent.setName(name.getText());
    		respondent.setBirthday(date.valueOf(birthday.getValue().toString()));
    		respondent.setCrime(crime.getText());
    		respondent.setContact(contact.getText());
    		respondent.setAddress(address.getText());
    		respondent.setPolicename(Security.getPolice().getName());
			rsrv.add(respondent);
			reload.ReloadView();
			
			try {
				file = new File("D:\\Images\\"+crimeid.getText()+".jpg");
				ImageIO.write(image, "jpg", file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			close();
			
		}
    	else
    	{
    		ShowAlert.alert("Crime Id is empty!", AlertType.WARNING);
    	}
    }
   
    public static void showView(Reloader reload) {
    	try {
    		FXMLLoader loader = new FXMLLoader(AddRespondentViewController.class.getResource("AddRespondentView.fxml"));
    		Parent view = loader.load();
    		AddRespondentViewController controller = loader.getController();
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
		rsrv = new RespondentService();
		reload = (Reloader) new RespondentViewController();
		
	}

}
