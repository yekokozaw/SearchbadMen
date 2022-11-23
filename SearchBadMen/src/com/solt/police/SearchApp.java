package com.solt.police;

import com.solt.police.model.LoginViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(LoginViewController.class.getResource("LoginView.fxml"));
		stage.setScene(new Scene(root));
		stage.show();	
	}
	public static void main(String[] args) {
		launch(args);
	}
}
