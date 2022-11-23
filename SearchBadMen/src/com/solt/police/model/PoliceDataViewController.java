package com.solt.police.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.solt.police.entity.Police;
import com.solt.police.service.PoliceService;
import com.solt.police.utility.Reloader;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PoliceDataViewController implements Initializable,Reloader{

    @FXML
    private TextField schname;

    @FXML
    private TableView<Police> policetableview;

    @FXML
    private TableColumn<Police, String> colname;
    
    @FXML
    private TableColumn<Police, String> colloginId;
    
    @FXML
    private TableColumn<Police, String> colage;

    @FXML
    private TableColumn<Police, String> colposition;

    @FXML
    private TableColumn<Police, String> coladdress;
    
    private PoliceService psrv;
    private List<Police>list;
    private Police police;
    private Reloader reloader;

   public void add() {
	   AddPoliceDataViewController.showView(this);
    }

   public void clear() {
	   policetableview.getItems().clear();
	   cleardata();
	   dataFindAll();
	   
    }

   private void cleardata() {
	// TODO Auto-generated method stub
   	policetableview.getItems().clear();
   	policetableview.getItems().addAll(list);    	
   	schname.setText("");
}

private void dataFindAll() {
	// TODO Auto-generated method stub
   list = psrv.findAll();
   policetableview.getItems().clear();
   policetableview.getItems().addAll(list);
}

public void search() {
	   list = psrv.find(schname.getText());
	   policetableview.getItems().clear();
	   policetableview.getItems().addAll(list);
   }
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	   	colloginId.setCellValueFactory(new PropertyValueFactory<>("loginid"));
		colname.setCellValueFactory(new PropertyValueFactory<>("name"));
		colage.setCellValueFactory(new PropertyValueFactory<>("birthday"));
		colposition.setCellValueFactory(new PropertyValueFactory<>("position"));
		coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		psrv = new PoliceService();
		list = new ArrayList<>();
		schname.textProperty().addListener((a,b,c)->{
			search();
		});

		MenuItem edit =new MenuItem("Edit");
		edit.setOnAction(event->{
			Police police = policetableview.getSelectionModel().getSelectedItem();
			EditPoliceDataViewController.showView(this, police);
		});
		policetableview.setContextMenu(new ContextMenu(edit));
		
		policetableview.setRowFactory( tv -> {
		    TableRow<Police> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	Police police = policetableview.getSelectionModel().getSelectedItem();
		        	police.setLoginid(police.getLoginid());
		            psrv.delete(police);
		            ReloadView();
		        }
		    });
		    return row ;
		});
		
		reload();
   }

@Override
   public void ReloadView() {
	reload();	
   }

   private void reload() {
	// TODO Auto-generated method stub
	   list = psrv.findAll();
	   policetableview.getItems().clear();
	   policetableview.getItems().addAll(list);
   }

}
