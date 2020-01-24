package MainApp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private String stringFile = "";
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox(10);
		Scene scene = new Scene(root, 200,400);
		root.setAlignment(Pos.CENTER);		
		root.setPadding(new Insets(20));
		
		Label label = new Label();
		
		Button importButton = new Button("Importer");
		
		importButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// cette partie du code permettra de donner le chemin du dossier a scanner
//				DirectoryChooser fc = new DirectoryChooser();
//				configuringDirectoryChooser(fc);
//				File choice = fc.showDialog(primaryStage);
				
				// cette partie du code permet de donner le chemin vers une image svg pour l'afficher
				FileChooser fc = new FileChooser();
				configuringFileChooser(fc);
				File choice = fc.showOpenDialog(primaryStage);
				if(choice != null) {
					label.setText(choice.getAbsolutePath());
					stringFile = choice.getAbsolutePath();
				}
			}
		});
				
		Button generateButton = new Button("Generate");
		generateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if(label.getText() == "") {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Attention !");
					alert.setContentText("Vous devez sélectionner un fichier à générer");
					alert.showAndWait();
				}else {
					Stage secondStage = new Stage();
					secondStage.setTitle("Prévualisation UML");
					
					
					Scene scene2 = new Scene(new VBox(), 600, 800);
					scene2.setFill(Color.OLDLACE);
					
					WebView view = new WebView();
					
					
					// Load the SVG
				    StringBuilder builder = new StringBuilder();
				    
				    List<String> line = new ArrayList<>();
			    	try {
			    		
			    		line = Files.readAllLines(Paths.get(stringFile));
			    	}catch (IOException e2) {
			    		e2.printStackTrace();
			    	}
			    	int i = 0;
				    while(i<line.size()) {
				    	builder.append(line.get(i));
				    	i++;
				    }
				    
				    view.getEngine().loadContent(builder.toString());
				    
				    // And add it to the scene
			        ((VBox) scene2.getRoot()).getChildren().add(view);
					secondStage.setWidth(800);
					secondStage.setHeight(600);

					secondStage.show();
					secondStage.centerOnScreen();
					secondStage.setScene(scene2);
					secondStage.setResizable(false);
				}
			}
		});
		
		
		Button downloadButton = new Button("Download");
		downloadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if(label.getText() == "") {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Attention !");
					alert.setContentText("Vous devez sélectionner un fichier à générer");
					alert.showAndWait();
				}else {
					System.out.println("download button clicked");
				}
			}			
		});
		
		
		
		root.getChildren().addAll(importButton,label ,generateButton, downloadButton);
		 
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Projet UML");
		primaryStage.setWidth(400);
		primaryStage.setHeight(300);
		
		primaryStage.show();

		primaryStage.centerOnScreen();
//		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		
		
		primaryStage.toFront();

	}
	
	  private void configuringFileChooser(FileChooser fileChooser) {
	 
	      // Add Extension Filters
	      fileChooser.getExtensionFilters().addAll(//
	              new FileChooser.ExtensionFilter("SVG", "*.svg"));
	  }
	
    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Some Directories");
 
        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
