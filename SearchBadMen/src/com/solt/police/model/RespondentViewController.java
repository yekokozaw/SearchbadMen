package com.solt.police.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.solt.police.entity.Respondent;
import com.solt.police.service.RespondentService;
import com.solt.police.utility.Reloader;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RespondentViewController implements Initializable,Reloader {

    @FXML
    private TextField schname;

    @FXML
    private TableView<Respondent> respondenttableview;

    @FXML
    private TableColumn<Respondent, String> colname;

    @FXML
    private TableColumn<Respondent, String> colage;

    @FXML
    private TableColumn<Respondent, String> colcrime;

    @FXML
    private TableColumn<Respondent, String> coladdress;
    
    private RespondentService rsrv;
    private List<Respondent>list;
    public void add() {
    	AddRespondentViewController.showView(this);
    }
    public void clear() {
 	   respondenttableview.getItems().clear();
 	   cleardata();
 	   rdataFindAll();
 	   
     }

    private void rdataFindAll() {
		// TODO Auto-generated method stub
    	list = rsrv.findAll();
    	respondenttableview.getItems().clear();
    	respondenttableview.getItems().addAll(list);
	}
	private void cleardata() {
 	// TODO Auto-generated method stub
    	respondenttableview.getItems().clear();
    	respondenttableview.getItems().addAll(list);    	
    	schname.setText("");
 }
	public void search() {
		   list = rsrv.find(schname.getText());
		   respondenttableview.getItems().clear();
		   respondenttableview.getItems().addAll(list);
	   }
	@Override
	public void ReloadView() {
		// TODO Auto-generated method stub
		reload();
	}
	private void reload() {
		// TODO Auto-generated method stub
		colname.setCellValueFactory(new PropertyValueFactory<>("name"));
		colage.setCellValueFactory(new PropertyValueFactory<>("birthday"));
		colcrime.setCellValueFactory(new PropertyValueFactory<>("crime"));
		coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		schname.textProperty().addListener((a,b,c)->{
			search();
		});
		
		list = rsrv.findAll();
		respondenttableview.getItems().clear();
		respondenttableview.getItems().addAll(list);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rsrv = new RespondentService();
		list = new ArrayList<Respondent>();
		reload();
;	}

}
