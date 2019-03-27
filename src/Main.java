import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{
	protected User curUser;
	protected String tempString;
	public void start (Stage primaryStage){
		GridPane bgPane = new GridPane();
		bgPane.setMinWidth(1020);
		bgPane.setMinHeight(750);
		bgPane.setAlignment(Pos.CENTER);
		Label menuTitle = new Label("XYZ University Attendance System");
		menuTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 50));
		GridPane.setHalignment(menuTitle, HPos.CENTER);
		bgPane.add(menuTitle, 0, 0);
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		StudentLogin(mainPane, primaryStage,"User.txt");
		
		GridPane LectPane = new GridPane();
		LectPane.setPadding(new Insets(20, 20, 20, 20));
		LectPane.setVgap(5);
		LectPane.setHgap(5);
		LecturerLogin(LectPane, primaryStage,"Parent.txt");
		
		GridPane AdminPane = new GridPane();
		AdminPane.setPadding(new Insets(20,20,20,20));
		AdminPane.setVgap(5);
		AdminPane.setHgap(5);
		AdminLogin(AdminPane, primaryStage);
		
		TabPane pane = new TabPane();
		pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		pane.setStyle("-fx-background-color: #ffffe0");
		Tab student = new Tab();
		student.setText("Student Login");
		student.setContent(mainPane);
		student.setStyle("-fx-background-color: #add8e6");
		Tab lect = new Tab();
		lect.setText("Lecturer Login");
		lect.setContent(LectPane);
		lect.setStyle("-fx-background-color: #00ff00");
		Tab admin = new Tab();
		admin.setText("Admin Login");
		admin.setContent(AdminPane);
		admin.setStyle("-fx-background-color: #ef3d47");
		pane.getTabs().addAll(student,lect,admin);
		pane.getSelectionModel().select(student);
		GridPane subPane = new GridPane();
		subPane.setAlignment(Pos.CENTER);
		subPane.add(pane, 0, 0);
		bgPane.add(subPane, 0, 1);
		bgPane.setAlignment(Pos.CENTER);
		Button exit = new Button("Exit Program");
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				System.exit(1);
			}
		});
		bgPane.add(exit, 0, 2);
		GridPane.setHalignment(exit, HPos.CENTER);
		
		
		Scene scene = new Scene(bgPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//AdminLogin() method is used to display the admin login pane.
	public void AdminLogin (GridPane mainPane, Stage primaryStage){
		GridPane loginPane = new GridPane();
		loginPane.setVgap(8);
		TextField txtUsername = new TextField();
		txtUsername.setPromptText("Enter Username");
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Enter Password");
		Label lblUsername = new Label("Username : ");
		Label lblPassword = new Label("Password  : ");
		lblUsername.setMinWidth(150);
		txtUsername.setMinWidth(200);
		lblUsername.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		lblPassword.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		loginPane.add(lblUsername, 0, 0);
		loginPane.add(txtUsername, 1, 0);
		loginPane.add(lblPassword, 0, 1);
		loginPane.add(txtPassword, 1, 1);
		Label error = new Label("");
		error.setStyle("-fx-text-fill: Red");
		loginPane.add(error, 0, 3);
		
		Button login = new Button("Login");
		login.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		GridPane.setHalignment(login, HPos.RIGHT);
		login.setStyle("-fx-background-color: #add8e6");
		loginPane.add(login, 1, 3);
		
		login.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		login.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		login.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				error.setText("");
				final String admin = "ADMIN";
				final String password = "admin";
				if (txtUsername.getText().equals(admin) && txtPassword.getText().equals(password)){
					adminMenu(primaryStage);
				}
				else {
					error.setText("Invalid Login Details");
				}
			}
		});
		
		Label title = new Label("Admin Login");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		mainPane.add(loginPane, 0, 1);
		mainPane.setAlignment(Pos.CENTER);
	}
	
	public void adminMenu(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Welcome Admin!");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		mainPane.add(lb1, 0, 0);
		GridPane.setHalignment(lb1, HPos.CENTER);
		Button regNew = new Button("Register New Student");
		regNew.setOnAction(e -> registerPage(primaryStage));
		GridPane.setHalignment(regNew, HPos.CENTER);
		mainPane.add(regNew, 0, 1);
		
		Button regLect = new Button("Register New Lecturer");
		regLect.setOnAction(e -> registerLect(primaryStage));
		GridPane.setHalignment(regLect, HPos.CENTER);
		mainPane.add(regLect, 0, 2);
		
		Button viewLect = new Button("View Lecturers/Students");
		viewLect.setOnAction(e-> viewUser(primaryStage));
		GridPane.setHalignment(viewLect, HPos.CENTER);
		mainPane.add(viewLect, 0, 3);
		
		Button modAtt = new Button("Modify Attendance");
		modAtt.setOnAction(e -> viewAtt(primaryStage, "Admin"));
		GridPane.setHalignment(modAtt, HPos.CENTER);
		mainPane.add(modAtt, 0, 4);
		
		Button reason = new Button("View Absent Reasons");
		reason.setOnAction(e ->modReason(primaryStage));
		GridPane.setHalignment(reason, HPos.CENTER);
		mainPane.add(reason, 0, 5);
		
		Button exit = new Button("Back to Login");
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
		GridPane.setHalignment(exit, HPos.CENTER);
		mainPane.add(exit, 0, 6);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void modReason(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Reasons To Approve");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		Label error = new Label();
		GridPane.setHalignment(error, HPos.CENTER);
		mainPane.add(error, 0, 4);
		error.setTextFill(Color.RED);
		ArrayList<String> reasons = new ArrayList<>();
		try {
			File file = new File("Reason.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					reasons.add(input.nextLine());
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		ArrayList<String> list = new ArrayList<>();
		for(int i=0;i<reasons.size();i++){
			list.add(reasons.get(i).split(";")[0]);
		}
		final ComboBox<String> cb1 = new ComboBox(collection(list));
		GridPane.setHalignment(cb1, HPos.CENTER);
		
		mainPane.add(new Label("Student Login ID "), 0, 1);
		mainPane.add(cb1, 1, 1);
		
		Button back = new Button("Back");
		GridPane.setHalignment(back, HPos.CENTER);
		mainPane.add(back, 0, 2);
		
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminMenu(primaryStage);
			}
		});
		
		Button done = new Button("Choose");
		GridPane.setHalignment(done, HPos.CENTER);
		mainPane.add(done, 0, 3);
		
		done.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(!(cb1.getSelectionModel().getSelectedItem() == null)){
					approve(primaryStage, cb1.getSelectionModel().getSelectedItem());
				}
				else {
					error.setText("Please Choose a student");
				}
			}
		});
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void approve(Stage primaryStage, String chosen){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Student : "+chosen);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		String[] buffer;
		String session = "",reason ="";
		try {
			File file = new File("Reason.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					buffer = input.nextLine().split(";");
					
					if(buffer[0].equals(chosen)){
						tempString = buffer[0]+";"+buffer[1]+";"+buffer[2];
						session = buffer[1];
						reason = buffer[2];
					}
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		buffer = session.split("_");
		mainPane.add(new Label("Intake : "+ buffer[0] + " Time : "+ buffer[1] + " Subject : " + buffer[2] + " Date : "+ buffer[3]), 0, 1);
		
		Label excuse = new Label("Reason : "+ reason);
		GridPane.setHalignment(excuse, HPos.CENTER);
		excuse.setWrapText(true);
		mainPane.add(excuse, 0, 2);
		
		Button back = new Button("Cancel");
		GridPane.setHalignment(back, HPos.LEFT);
		mainPane.add(back, 0, 3);
		
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				modReason(primaryStage);
			}
		});
		
		Button done = new Button("Decline");
		GridPane.setHalignment(done, HPos.CENTER);
		mainPane.add(done, 0, 3);
		
		done.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				deleteReason(tempString);
				modReason(primaryStage);
			}
		});
		
		Button done1 = new Button("Accept");
		GridPane.setHalignment(done1, HPos.RIGHT);
		mainPane.add(done1, 0, 3);
		
		done1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				acceptReason(tempString);
				modReason(primaryStage);
			}
		});
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void acceptReason(String newLine){
		ArrayList<String> old = new ArrayList<>();
		try {
			File file = new File("Reason.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					old.add(input.nextLine());
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		String [] buffer;
		for(int i=0;i<old.size();i++){
			buffer = old.get(i).split(";");
			if(buffer[0].equals(newLine.split(";")[0]) && buffer[1].equals(newLine.split(";")[1])){
				old.remove(i);
			}
		}
		try {
			File file = new File ("Reason.txt");
			PrintWriter output = new PrintWriter(file);
			if (file.exists()){
				for (int i=0;i<old.size();i++){
					output.println(old.get(i));
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			
		}
		old.clear();
		try {
			File file = new File("Attendence.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					old.add(input.nextLine());
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		for(int i=0;i<old.size();i++){
			buffer = old.get(i).split(";");
			if(buffer[0].equals(newLine.split(";")[1]) && buffer[1].equals(newLine.split(";")[0])){
				buffer[3] = "Absent With Reason";
				old.remove(i);
				tempString = buffer[0]+";"+buffer[1]+";"+buffer[2]+";"+buffer[3];
				old.add(tempString);
			}
		}
		try {
			File file = new File ("Attendence.txt");
			PrintWriter output = new PrintWriter(file);
			if (file.exists()){
				for (int i=0;i<old.size();i++){
					output.println(old.get(i));
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	public void deleteReason(String newLine){
		ArrayList<String> old = new ArrayList<>();
		try {
			File file = new File("Reason.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					old.add(input.nextLine());
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		String [] buffer;
		for(int i=0;i<old.size();i++){
			buffer = old.get(i).split(";");
			if(buffer[0].equals(newLine.split(";")[0]) && buffer[1].equals(newLine.split(";")[1])){
				old.remove(i);
			}
		}
		try {
			File file = new File ("Reason.txt");
			PrintWriter output = new PrintWriter(file);
			if (file.exists()){
				for (int i=0;i<old.size();i++){
					output.println(old.get(i));
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	public void viewUser(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		

		TabPane pane = new TabPane();
		pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		pane.setStyle("-fx-background-color: #ffffe0");
		Tab student = new Tab();
		student.setText("Student Accounts");
		student.setContent(studentPane(primaryStage));
		student.setStyle("-fx-background-color: #add8e6");
		Tab lect = new Tab();
		lect.setText("Lecturer Accounts");
		lect.setContent(lecturerPane(primaryStage));
		lect.setStyle("-fx-background-color: #00ff00");
		pane.getTabs().addAll(student,lect);
		pane.getSelectionModel().select(student);
		GridPane.setHalignment(pane, HPos.CENTER);
		mainPane.add(pane, 0, 0);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public GridPane studentPane (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		Label errorMsg = new Label();
		errorMsg.setStyle("-fx-text-fill: Red");
		GridPane.setHalignment(errorMsg, HPos.CENTER);
		
		TableColumn<User, String> loginID = new TableColumn<>("Login ID");
		loginID.setMinWidth(80);
		loginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));
		
		TableColumn<User, String> Fullname = new TableColumn<>("Full Name");
		Fullname.setMinWidth(100);
		Fullname.setCellValueFactory(new PropertyValueFactory<>("fname"));
		
		TableColumn<User, String> BDate = new TableColumn<>("Birth Date");
		BDate.setMinWidth(100);
		BDate.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
		
		TableColumn<User, String> gender = new TableColumn<>("Gender");
		gender.setMinWidth(80);
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		
		TableColumn<User, String> IDno = new TableColumn<>("IC/No");
		IDno.setMinWidth(100);
		IDno.setCellValueFactory(new PropertyValueFactory<>("IDno"));
		
		TableColumn<User, String> email = new TableColumn<>("Email Address");
		email.setMinWidth(150);
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<User, String> intake = new TableColumn<>("Intake");
		intake.setMinWidth(100);
		intake.setCellValueFactory(new PropertyValueFactory<>("intake"));
		
		TableColumn<User, String> pwd = new TableColumn<>("Password");
		pwd.setMinWidth(100);
		pwd.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		TableView<User> table = new TableView<>();
		mainPane.add(table, 0, 0);
		GridPane.setHalignment(table, HPos.CENTER);
		ArrayList<User>	user = new ArrayList<>();
		user = readFile("Student.txt", "Student");
		ObservableList<User> list = (ObservableList<User>) collection(user);
		table.setItems(list);
		table.getColumns().addAll(loginID,Fullname,BDate,gender, IDno, email,intake);
		
		Button viewPassword = new Button("View Passwords");
		GridPane.setHalignment(viewPassword, HPos.CENTER);
		mainPane.add(viewPassword, 0, 1);
		
		Button hidePassword = new Button("Hide Passwords");
		GridPane.setHalignment(hidePassword, HPos.CENTER);
		mainPane.add(hidePassword, 0, 1);
		hidePassword.setVisible(false);
		
		viewPassword.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				hidePassword.setVisible(true);
				viewPassword.setVisible(false);
				table.getColumns().add(pwd);
			}
		});
		
		hidePassword.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				viewPassword.setVisible(true);
				hidePassword.setVisible(false);
				table.getColumns().remove(pwd);
			}
		});
		
		Button delete = new Button("Remove User");
		GridPane.setHalignment(delete, HPos.CENTER);
		mainPane.add(delete, 0, 2);
		
		delete.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> userSelected, allUser;
				allUser = table.getItems();
				userSelected = table.getSelectionModel().getSelectedItems();
				if (userSelected.size() == 0){
					errorMsg.setText("Please Select User");
				}
				else {
					errorMsg.setText("");
					userSelected.forEach(allUser::remove);
					List<User> user = table.getItems();
				    ArrayList<User> aUser;
				    if (user instanceof ArrayList<?>) {
				        aUser = (ArrayList<User>) user;
				    } else {
				        aUser = new ArrayList<>(user);
				    }
					writeFile("Student.txt", "Student", aUser);
				}
			}
		});
		
		Button modify = new Button("Modify User");
		GridPane.setHalignment(modify, HPos.CENTER);
		mainPane.add(modify, 0, 3);
		modify.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> userSelected;
				userSelected = table.getSelectionModel().getSelectedItems();
				ArrayList<User> aUser;
				if (userSelected instanceof ArrayList<?>) {
					aUser = (ArrayList<User>) userSelected;
				} else {
				    aUser = new ArrayList<>(userSelected);
				}
				if (userSelected.size() != 0){
					errorMsg.setText("");
					modStud(primaryStage, aUser);
				}
				else {
					errorMsg.setText("Please Select User");
				}
				
			}
		});
		
		Button exit = new Button("Back to Main Menu");
		mainPane.add(exit, 0, 4);
		GridPane.setHalignment(exit, HPos.CENTER);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminMenu(primaryStage);
			}
		});
		mainPane.add(errorMsg, 0, 5);
		return mainPane;
	}
	
	public void modStud (Stage primaryStage, ArrayList<User> aUser){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Modify Student");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setMinWidth(300);
		mainPane.add(lb1, 0, 0);
		mainPane.add(new Label("Login ID"), 0, 1);
		TextField txt1 = new TextField();
		txt1.setPromptText("Enter New Login ID");
		mainPane.add(txt1, 1, 1);
		txt1.setText(aUser.get(0).getLoginID());
		mainPane.add(new Label("Password"), 0, 2);
		PasswordField txt2 = new PasswordField();
		txt2.setText(aUser.get(0).getPassword());
		txt2.setPromptText("Enter New Password");
		mainPane.add(txt2, 1, 2);
		mainPane.add(new Label("Enter Full Name"), 0, 4);
		TextField txt4 = new TextField();
		txt4.setText(aUser.get(0).getFname());
		txt4.setPromptText("Enter Full Name");
		mainPane.add(txt4, 1, 4);
		mainPane.add(new Label("Enter IC/Passport No."), 0, 5);
		TextField txt5 = new TextField();
		txt5.setPromptText("Enter IC or Passport No.");
		txt5.setText(aUser.get(0).getIDno());
		mainPane.add(txt5, 1, 5);
		mainPane.add(new Label("Email Address"), 0, 6);
		TextField txt6 = new TextField();
		txt6.setPromptText("Enter Email Address");
		txt6.setText(aUser.get(0).getEmail());
		mainPane.add(txt6, 1, 6);
		mainPane.add(new Label("Intake "), 0, 8);
		ArrayList<String> intakes = new ArrayList<>();
		intakes.add("Intake 1");
		intakes.add("Intake 2");
		intakes.add("Intake 3");
		final ComboBox<String> cb1 = new ComboBox(collection(intakes));
		cb1.getSelectionModel().select(((Student)aUser.get(0)).getIntake());
		mainPane.add(cb1, 1, 8);
		mainPane.add(new Label("Gender"), 0, 9);
		GridPane genderPane = new GridPane();
		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Male");
		rb1.setUserData("Male");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("Female");
		rb2.setUserData("Female");
		rb2.setToggleGroup(group);
		if (aUser.get(0).getGender().equals("Male")){
			rb1.setSelected(true);
		}
		else if (aUser.get(0).getGender().equals("Female")){
			rb2.setSelected(true);
		}
		GridPane.setHalignment(rb1, HPos.CENTER);
		GridPane.setHalignment(rb2, HPos.CENTER);
		genderPane.add(rb1, 0, 0);
		genderPane.add(rb2, 1, 0);
		mainPane.add(genderPane, 1, 9);
		Button mod = new Button("Modify");
		Label errorMsg = new Label();
		mainPane.add(errorMsg, 0, 10);
		errorMsg.setStyle("-fx-text-fill: Red");
		mod.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		mod.setStyle("-fx-background-color: #add8e6");
		mod.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				mod.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		mod.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				mod.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		mod.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				errorMsg.setText("");
				ArrayList<User> user = new ArrayList<>();
				user = readFile ("Student.txt", "Student");
				String bdate = aUser.get(0).getBdate();
				for (int i=0;i<user.size();i++){
					if (user.get(i).getLoginID().equals(aUser.get(0).getLoginID())){
						user.remove(i);
					}
				}
				if (txt1.getText().equals("")){
					errorMsg.setText("Please Enter Login ID");
				}
				else if (txt2.getText().equals("")){
					errorMsg.setText("Please Enter a password.");
				}
				else if (txt4.getText().equals("")){
					errorMsg.setText("Please Enter Full Name");
				}
				else if (txt5.getText().equals("")){
					errorMsg.setText("Please Enter IC Number");
				}
				else if (txt6.getText().equals("")){
					errorMsg.setText("Please Enter Email Address");
				}
				else if (!txt6.getText().contains("@") && !txt6.getText().contains(".com")){
					txt6.setText("");
					errorMsg.setText("Please Enter Valid Email");
				}
				else if (!rb1.isSelected() && !rb2.isSelected()){
					errorMsg.setText("Please Select Gender");
				}
				else if (cb1.getSelectionModel().getSelectedItem() == null){
					errorMsg.setText("Please Choose Intake");
				}
				else {
					User newUser = new Student(txt4.getText(),txt2.getText(), bdate,txt1.getText()
							, group.getSelectedToggle().getUserData().toString() , txt6.getText(), txt5.getText(), cb1.getSelectionModel().getSelectedItem());
					//0-fname, 1-password, 2-Bdate,3-loginID, 4 - gender, 5- email, 6-IDno, 7-Intake
					
					boolean checker = true;
					for(int i=0;i<user.size();i++){
						if(user.get(i).getLoginID().equals(newUser.getLoginID())){
							checker = false;
						}
					}
					
					if (checker == true){
						user.add(newUser);
						modUser(user, "Student.txt", "Student");
						viewUser(primaryStage);
					}
					else if (checker == false){
						txt1.setText("");
						errorMsg.setText("Login ID Taken");
					}
				}
			}
		});
		
		GridPane.setHalignment(mod, HPos.RIGHT);
		mainPane.add(mod, 1, 10);
		Button exit = new Button ("Cancel");
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				viewUser(primaryStage);
			}
		});
		mainPane.add(exit, 1, 11);
		GridPane.setHalignment(exit, HPos.RIGHT);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public GridPane lecturerPane (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label errorMsg = new Label();
		errorMsg.setStyle("-fx-text-fill: Red");
		GridPane.setHalignment(errorMsg, HPos.CENTER);
		
		TableColumn<User, String> loginID = new TableColumn<>("Login ID");
		loginID.setMinWidth(80);
		loginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));
		
		TableColumn<User, String> Fullname = new TableColumn<>("Full Name");
		Fullname.setMinWidth(100);
		Fullname.setCellValueFactory(new PropertyValueFactory<>("fname"));
		
		TableColumn<User, String> BDate = new TableColumn<>("Birth Date");
		BDate.setMinWidth(100);
		BDate.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
		
		TableColumn<User, String> gender = new TableColumn<>("Gender");
		gender.setMinWidth(80);
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		
		TableColumn<User, String> IDno = new TableColumn<>("IC/No");
		IDno.setMinWidth(100);
		IDno.setCellValueFactory(new PropertyValueFactory<>("IDno"));
		
		TableColumn<User, String> email = new TableColumn<>("Email Address");
		email.setMinWidth(150);
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<User, String> ms = new TableColumn<>("Marital Status");
		ms.setMinWidth(100);
		ms.setCellValueFactory(new PropertyValueFactory<>("marStat"));
		
		TableColumn<User, String> pwd = new TableColumn<>("Password");
		pwd.setMinWidth(100);
		pwd.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		TableView<User> table = new TableView<>();
		mainPane.add(table, 0, 0);
		GridPane.setHalignment(table, HPos.CENTER);
		ArrayList<User>	user = new ArrayList<>();
		user = readFile("Lecturer.txt", "Lecturer");
		ObservableList<User> list = (ObservableList<User>) collection(user);
		table.setItems(list);
		table.getColumns().addAll(loginID,Fullname,BDate,gender, IDno, email,ms);
		
		Button viewPassword = new Button("View Passwords");
		GridPane.setHalignment(viewPassword, HPos.CENTER);
		mainPane.add(viewPassword, 0, 1);
		
		Button hidePassword = new Button("Hide Passwords");
		GridPane.setHalignment(hidePassword, HPos.CENTER);
		mainPane.add(hidePassword, 0, 1);
		hidePassword.setVisible(false);
		
		viewPassword.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				hidePassword.setVisible(true);
				viewPassword.setVisible(false);
				table.getColumns().add(pwd);
			}
		});
		
		hidePassword.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				viewPassword.setVisible(true);
				hidePassword.setVisible(false);
				table.getColumns().remove(pwd);
			}
		});
		
		Button delete = new Button("Remove User");
		GridPane.setHalignment(delete, HPos.CENTER);
		mainPane.add(delete, 0, 2);
		
		delete.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> userSelected, allUser;
				allUser = table.getItems();
				userSelected = table.getSelectionModel().getSelectedItems();
				if (userSelected.size() == 0){
					errorMsg.setText("Please Select User");
				}
				else {
					errorMsg.setText("");
					userSelected.forEach(allUser::remove);
					List<User> user = table.getItems();
				    ArrayList<User> aUser;
				    if (user instanceof ArrayList<?>) {
				        aUser = (ArrayList<User>) user;
				    } else {
				        aUser = new ArrayList<>(user);
				    }
					writeFile("Lecturer.txt", "Lecturer", aUser);
				}
			}
		});
		
		Button modify = new Button("Modify User");
		GridPane.setHalignment(modify, HPos.CENTER);
		mainPane.add(modify, 0, 3);
		modify.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> userSelected;
				userSelected = table.getSelectionModel().getSelectedItems();
				ArrayList<User> aUser;
				if (userSelected instanceof ArrayList<?>) {
					aUser = (ArrayList<User>) userSelected;
				} else {
				    aUser = new ArrayList<>(userSelected);
				}
				if (userSelected.size() != 0){
					errorMsg.setText("");
					modLect(primaryStage, aUser);
				}
				else {
					errorMsg.setText("Please Select User");
				}
				
			}
		});
		
		Button exit = new Button("Back to Main Menu");
		mainPane.add(exit, 0, 4);
		GridPane.setHalignment(exit, HPos.CENTER);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminMenu(primaryStage);
			}
		});
		mainPane.add(errorMsg, 0, 5);
		return mainPane;
	}
	
	public void modLect(Stage primaryStage, ArrayList<User> aUser){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Modify Lecturer");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setMinWidth(300);
		mainPane.add(lb1, 0, 0);
		mainPane.add(new Label("Login ID"), 0, 1);
		TextField txt1 = new TextField();
		txt1.setPromptText("Enter New Login ID");
		mainPane.add(txt1, 1, 1);
		txt1.setText(aUser.get(0).getLoginID());
		mainPane.add(new Label("Password"), 0, 2);
		PasswordField txt2 = new PasswordField();
		txt2.setText(aUser.get(0).getPassword());
		txt2.setPromptText("Enter New Password");
		mainPane.add(txt2, 1, 2);
		mainPane.add(new Label("Enter Full Name"), 0, 4);
		TextField txt4 = new TextField();
		txt4.setText(aUser.get(0).getFname());
		txt4.setPromptText("Enter Full Name");
		mainPane.add(txt4, 1, 4);
		mainPane.add(new Label("Enter IC/Passport No."), 0, 5);
		TextField txt5 = new TextField();
		txt5.setPromptText("Enter IC or Passport No.");
		txt5.setText(aUser.get(0).getIDno());
		mainPane.add(txt5, 1, 5);
		mainPane.add(new Label("Email Address"), 0, 6);
		TextField txt6 = new TextField();
		txt6.setPromptText("Enter Email Address");
		txt6.setText(aUser.get(0).getEmail());
		mainPane.add(txt6, 1, 6);
		mainPane.add(new Label("Marital Status "), 0, 8);
		ArrayList<String> status = new ArrayList<>();
		status.add("Single");
		status.add("Married");
		status.add("Widowed");
		final ComboBox<String> cb1 = new ComboBox(collection(status));
		cb1.getSelectionModel().select(((Lecturer)aUser.get(0)).getMarStat());
		mainPane.add(cb1, 1, 8);
		mainPane.add(new Label("Gender"), 0, 9);
		GridPane genderPane = new GridPane();
		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Male");
		rb1.setUserData("Male");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("Female");
		rb2.setUserData("Female");
		rb2.setToggleGroup(group);
		if (aUser.get(0).getGender().equals("Male")){
			rb1.setSelected(true);
		}
		else if (aUser.get(0).getGender().equals("Female")){
			rb2.setSelected(true);
		}
		GridPane.setHalignment(rb1, HPos.CENTER);
		GridPane.setHalignment(rb2, HPos.CENTER);
		genderPane.add(rb1, 0, 0);
		genderPane.add(rb2, 1, 0);
		mainPane.add(genderPane, 1, 9);
		Button mod = new Button("Modify");
		Label errorMsg = new Label();
		mainPane.add(errorMsg, 0, 10);
		errorMsg.setStyle("-fx-text-fill: Red");
		mod.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		mod.setStyle("-fx-background-color: #add8e6");
		mod.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				mod.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		mod.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				mod.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		mod.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				errorMsg.setText("");
				ArrayList<User> user = new ArrayList<>();
				user = readFile ("Lecturer.txt", "Lecturer");
				String bdate = aUser.get(0).getBdate();
				for (int i=0;i<user.size();i++){
					if (user.get(i).getLoginID().equals(aUser.get(0).getLoginID())){
						user.remove(i);
					}
				}
				if (txt1.getText().equals("")){
					errorMsg.setText("Please Enter Login ID");
				}
				else if (txt2.getText().equals("")){
					errorMsg.setText("Please Enter a password.");
				}
				else if (txt4.getText().equals("")){
					errorMsg.setText("Please Enter Full Name");
				}
				else if (txt5.getText().equals("")){
					errorMsg.setText("Please Enter IC Number");
				}
				else if (txt6.getText().equals("")){
					errorMsg.setText("Please Enter Email Address");
				}
				else if (!txt6.getText().contains("@") && !txt6.getText().contains(".com")){
					txt6.setText("");
					errorMsg.setText("Please Enter Valid Email");
				}
				else if (!rb1.isSelected() && !rb2.isSelected()){
					errorMsg.setText("Please Select Gender");
				}
				else if (cb1.getSelectionModel().getSelectedItem() == null){
					errorMsg.setText("Please Choose Intake");
				}
				else {
					User newUser = new Lecturer(txt4.getText(),txt2.getText(), bdate,txt1.getText()
							, group.getSelectedToggle().getUserData().toString() , txt6.getText(), txt5.getText(), cb1.getSelectionModel().getSelectedItem());
					//0-fname, 1-password, 2-Bdate,3-loginID, 4 - gender, 5- email, 6-IDno, 7-Intake
					
					boolean checker = true;
					for(int i=0;i<user.size();i++){
						if(user.get(i).getLoginID().equals(newUser.getLoginID())){
							checker = false;
						}
					}
					
					if (checker == true){
						user.add(newUser);
						modUser(user, "Lecturer.txt", "Lecturer");
						viewUser(primaryStage);
					}
					else if (checker == false){
						txt1.setText("");
						errorMsg.setText("Login ID Taken");
					}
				}
			}
		});
		
		GridPane.setHalignment(mod, HPos.RIGHT);
		mainPane.add(mod, 1, 10);
		Button exit = new Button ("Cancel");
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				viewUser(primaryStage);
			}
		});
		mainPane.add(exit, 1, 11);
		GridPane.setHalignment(exit, HPos.RIGHT);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void modifyAtt(Stage primaryStage, String name, String type){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label error = new Label("");
		error.setTextFill(Color.RED);
		mainPane.add(error, 0, 4);
		Label lb1 = new Label("Take Attendence");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setMinWidth(300);
		mainPane.add(lb1, 0, 0);
		ArrayList<User> user = new ArrayList<>();
		String [] buffer;
		try {
			File file = new File("Attendence.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					buffer = input.nextLine().split(";");
					if(buffer[0].equals(name)){
						user.add(new Student(buffer[1],buffer[2],buffer[3]));
					}
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		
		TableColumn<User, String> loginID = new TableColumn<>("Login ID");
		loginID.setMinWidth(80);
		loginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));
		
		TableColumn<User, String> Fullname = new TableColumn<>("Full Name");
		Fullname.setMinWidth(100);
		Fullname.setCellValueFactory(new PropertyValueFactory<>("fname"));
		
		TableColumn<User, String> attend = new TableColumn<>("Attendence");
		attend.setMinWidth(180);
		attend.setCellValueFactory(new PropertyValueFactory<>("attend"));
		
		TableView<User> table = new TableView<>();
		mainPane.add(table, 0, 1);
		GridPane.setHalignment(table, HPos.CENTER);
		
		ObservableList<User> list = FXCollections.observableArrayList();
		for (int i=0;i<user.size();i++){
			list.add(user.get(i));
		}
		table.setItems(list);
		table.getColumns().addAll(loginID,Fullname,attend);
		
		Button absent = new Button("Mark as Absent");
		GridPane.setHalignment(absent, HPos.CENTER);
		absent.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> selectedUser = table.getSelectionModel().getSelectedItems();
				if (selectedUser.size() != 0){
					((Student)selectedUser.get(0)).setAttend("Absent");
					table.refresh();
				}else {
					error.setText("Please Select a Student");
				}
			}
		});
		
		Button att = new Button("Mark as Attend");
		GridPane.setHalignment(att, HPos.CENTER);
		att.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> selectedUser = table.getSelectionModel().getSelectedItems();
				if (selectedUser.size() != 0){
					((Student)selectedUser.get(0)).setAttend("Attended");
					table.refresh();
				}else {
					error.setText("Please Select a Student");
				}
			}
		});
		Button late = new Button("Mark as Late");
		GridPane.setHalignment(late, HPos.CENTER);
		late.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> selectedUser = table.getSelectionModel().getSelectedItems();
				if (selectedUser.size() != 0){
					((Student)selectedUser.get(0)).setAttend("Late");
					table.refresh();
				}else {
					error.setText("Please Select a Student");
				}
			}
		});
		Button exit = new Button("Cancel");
		GridPane.setHalignment(exit, HPos.CENTER);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if (type.equals("Lecturer")){
					lectMenu(primaryStage);
				}
				else if (type.equals("Admin")){
					adminMenu(primaryStage);
				}
			}
		});
		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		mainPane.add(pane, 0, 2);
		pane.add(absent, 0, 0);
		pane.add(att, 1, 0);
		pane.add(late, 0, 1);
		pane.add(exit, 1, 1);
		
		Button mark = new Button("Finish, Mark Attendence");
		GridPane.setHalignment(mark, HPos.CENTER);
		mainPane.add(mark, 0, 3);
		mark.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ArrayList<String> old = new ArrayList<>();
				try {
					File file = new File("Attendence.txt");
					if(file.exists()){
						Scanner input = new Scanner(file);
						while (input.hasNextLine()){
							old.add(input.nextLine());
						}
						input.close();
					}
				} catch (FileNotFoundException e) {
					
				}
				try {
					File file = new File ("Attendence.txt");
					PrintWriter output = new PrintWriter(file);
					if (file.exists()){
						for(int i=0;i<old.size();i++){
							if (!old.get(i).split(";")[0].equals(name)){
								output.println(old.get(i));
							}
						}
						for (int i=0;i<list.size();i++){
							output.print(name+";");
							output.print(list.get(i).getLoginID()+";");
							output.print(list.get(i).getFname()+";");
							output.println(((Student)list.get(i)).getAttend());
						}
					}
					output.flush();
					output.close();
				} catch (FileNotFoundException e) {
					
				}
				if (type.equals("Lecturer")){
					lectMenu(primaryStage);
				}
				else if (type.equals("Admin")){
					adminMenu(primaryStage);
				}
			}
		});
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void registerLect (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Lecturer Register Page");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setMinWidth(300);
		mainPane.add(lb1, 0, 0);
		mainPane.add(new Label("New Login ID"), 0, 1);
		TextField txt1 = new TextField();
		txt1.setPromptText("Enter New Login ID");
		mainPane.add(txt1, 1, 1);
		mainPane.add(new Label("New Password"), 0, 2);
		PasswordField txt2 = new PasswordField();
		txt2.setPromptText("Enter New Password");
		mainPane.add(txt2, 1, 2);
		mainPane.add(new Label("Confirm Password"), 0, 3);
		PasswordField txt3 = new PasswordField();
		txt3.setPromptText("Reenter New Passwprd");
		mainPane.add(txt3, 1, 3);
		mainPane.add(new Label("Enter Full Name"), 0, 4);
		TextField txt4 = new TextField();
		txt4.setPromptText("Enter Full Name");
		mainPane.add(txt4, 1, 4);
		mainPane.add(new Label("Enter IC/Passport No."), 0, 5);
		TextField txt5 = new TextField();
		txt5.setPromptText("Enter IC or Passport No.");
		mainPane.add(txt5, 1, 5);
		mainPane.add(new Label("Email Address"), 0, 6);
		TextField txt6 = new TextField();
		txt6.setPromptText("Enter Email Address");
		mainPane.add(txt6, 1, 6);
		mainPane.add(new Label("Date of Birth"), 0, 7);
		final DatePicker datePicker = new DatePicker();
		datePicker.setPromptText("Select the Date ->");
		mainPane.add(datePicker, 1, 7);
		mainPane.add(new Label("Marrital Status "), 0, 8);
		ArrayList<String> status = new ArrayList<>();
		status.add("Single");
		status.add("Married");
		status.add("Widowed");
		final ComboBox<String> cb1 = new ComboBox(collection(status));
		mainPane.add(cb1, 1, 8);
		mainPane.add(new Label("Gender"), 0, 9);
		GridPane genderPane = new GridPane();
		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Male");
		rb1.setUserData("Male");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("Female");
		rb2.setUserData("Female");
		rb2.setToggleGroup(group);
		GridPane.setHalignment(rb1, HPos.CENTER);
		GridPane.setHalignment(rb2, HPos.CENTER);
		genderPane.add(rb1, 0, 0);
		genderPane.add(rb2, 1, 0);
		mainPane.add(genderPane, 1, 9);
		Button register = new Button("Register");
		Label errorMsg = new Label();
		mainPane.add(errorMsg, 0, 10);
		errorMsg.setStyle("-fx-text-fill: Red");
		register.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		register.setStyle("-fx-background-color: #add8e6");
		register.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		register.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		register.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				errorMsg.setText("");
				ArrayList<User> user = new ArrayList<>();
				user = readFile ("Lecturer.txt", "Lecturer");
				
				if (txt1.getText().equals("")){
					errorMsg.setText("Please Enter Login ID");
				}
				else if (txt2.getText().equals("")){
					errorMsg.setText("Please Enter a password.");
				}
				else if (!txt2.getText().equals(txt3.getText())){
					errorMsg.setText("Please Passwords do not match!");
				}
				else if (txt4.getText().equals("")){
					errorMsg.setText("Please Enter Full Name");
				}
				else if (txt5.getText().equals("")){
					errorMsg.setText("Please Enter IC Number");
				}
				else if (txt6.getText().equals("")){
					errorMsg.setText("Please Enter Email Address");
				}
				else if (!txt6.getText().contains("@") && !txt6.getText().contains(".com")){
					txt6.setText("");
					errorMsg.setText("Please Enter Valid Email");
				}
				else if (datePicker.getValue() == null ){
					errorMsg.setText("Please Enter BirthDate");
				}
				else if (!rb1.isSelected() && !rb2.isSelected()){
					errorMsg.setText("Please Select Gender");
				}
				else if (cb1.getSelectionModel().getSelectedItem() == null){
					errorMsg.setText("Please Choose Marital Status");
				}
				else {
					User newUser = new Lecturer(txt4.getText(),txt2.getText(), datePicker.getValue().toString(),txt1.getText()
							, group.getSelectedToggle().getUserData().toString() , txt6.getText(), txt5.getText(), cb1.getSelectionModel().getSelectedItem());
					//0-fname, 1-password, 2-Bdate,3-loginID, 4 - gender, 5- email, 6-IDno, 7-Intake
					
					boolean checker = true;
					for(int i=0;i<user.size();i++){
						if(user.get(i).getLoginID().equals(newUser.getLoginID())){
							checker = false;
						}
					}
					if (checker == true){
						addUser(newUser, "Lecturer.txt", "Lecturer");
						adminMenu(primaryStage);
					}
					else if (checker == false){
						txt1.setText("");
						errorMsg.setText("Login ID Taken");
					}
				}
			}
		});
		
		GridPane.setHalignment(register, HPos.RIGHT);
		mainPane.add(register, 1, 10);
		Button exit = new Button ("Cancel");
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminMenu(primaryStage);
			}
		});
		mainPane.add(exit, 1, 11);
		GridPane.setHalignment(exit, HPos.RIGHT);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void registerPage(Stage primaryStage) {
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Student Register Page");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setMinWidth(300);
		mainPane.add(lb1, 0, 0);
		mainPane.add(new Label("New Login ID"), 0, 1);
		TextField txt1 = new TextField();
		txt1.setPromptText("Enter New Login ID");
		mainPane.add(txt1, 1, 1);
		mainPane.add(new Label("New Password"), 0, 2);
		PasswordField txt2 = new PasswordField();
		txt2.setPromptText("Enter New Password");
		mainPane.add(txt2, 1, 2);
		mainPane.add(new Label("Confirm Password"), 0, 3);
		PasswordField txt3 = new PasswordField();
		txt3.setPromptText("Reenter New Passwprd");
		mainPane.add(txt3, 1, 3);
		mainPane.add(new Label("Enter Full Name"), 0, 4);
		TextField txt4 = new TextField();
		txt4.setPromptText("Enter Full Name");
		mainPane.add(txt4, 1, 4);
		mainPane.add(new Label("Enter IC/Passport No."), 0, 5);
		TextField txt5 = new TextField();
		txt5.setPromptText("Enter IC or Passport No.");
		mainPane.add(txt5, 1, 5);
		mainPane.add(new Label("Email Address"), 0, 6);
		TextField txt6 = new TextField();
		txt6.setPromptText("Enter Email Address");
		mainPane.add(txt6, 1, 6);
		mainPane.add(new Label("Date of Birth"), 0, 7);
		final DatePicker datePicker = new DatePicker();
		datePicker.setPromptText("Select the Date ->");
		mainPane.add(datePicker, 1, 7);
		mainPane.add(new Label("Intake "), 0, 8);
		ArrayList<String> intakes = new ArrayList<>();
		intakes.add("Intake 1");
		intakes.add("Intake 2");
		intakes.add("Intake 3");
		final ComboBox<String> cb1 = new ComboBox(collection(intakes));
		mainPane.add(cb1, 1, 8);
		mainPane.add(new Label("Gender"), 0, 9);
		GridPane genderPane = new GridPane();
		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Male");
		rb1.setUserData("Male");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("Female");
		rb2.setUserData("Female");
		rb2.setToggleGroup(group);
		GridPane.setHalignment(rb1, HPos.CENTER);
		GridPane.setHalignment(rb2, HPos.CENTER);
		genderPane.add(rb1, 0, 0);
		genderPane.add(rb2, 1, 0);
		mainPane.add(genderPane, 1, 9);
		Button register = new Button("Register");
		Label errorMsg = new Label();
		mainPane.add(errorMsg, 0, 10);
		errorMsg.setStyle("-fx-text-fill: Red");
		register.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		register.setStyle("-fx-background-color: #add8e6");
		register.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		register.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		register.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				errorMsg.setText("");
				ArrayList<User> user = new ArrayList<>();
				user = readFile ("Student.txt", "Student");
				
				if (txt1.getText().equals("")){
					errorMsg.setText("Please Enter Login ID");
				}
				else if (txt2.getText().equals("")){
					errorMsg.setText("Please Enter a password.");
				}
				else if (!txt2.getText().equals(txt3.getText())){
					errorMsg.setText("Please Passwords do not match!");
				}
				else if (txt4.getText().equals("")){
					errorMsg.setText("Please Enter Full Name");
				}
				else if (txt5.getText().equals("")){
					errorMsg.setText("Please Enter IC Number");
				}
				else if (txt6.getText().equals("")){
					errorMsg.setText("Please Enter Email Address");
				}
				else if (!txt6.getText().contains("@") && !txt6.getText().contains(".com")){
					txt6.setText("");
					errorMsg.setText("Please Enter Valid Email");
				}
				else if (datePicker.getValue() == null ){
					errorMsg.setText("Please Enter BirthDate");
				}
				else if (!rb1.isSelected() && !rb2.isSelected()){
					errorMsg.setText("Please Select Gender");
				}
				else if (cb1.getSelectionModel().getSelectedItem() == null){
					errorMsg.setText("Please Choose Intake");
				}
				else {
					User newUser = new Student(txt4.getText(),txt2.getText(), datePicker.getValue().toString(),txt1.getText()
							, group.getSelectedToggle().getUserData().toString() , txt6.getText(), txt5.getText(), cb1.getSelectionModel().getSelectedItem());
					//0-fname, 1-password, 2-Bdate,3-loginID, 4 - gender, 5- email, 6-IDno, 7-Intake
					
					boolean checker = true;
					for(int i=0;i<user.size();i++){
						if(user.get(i).getLoginID().equals(newUser.getLoginID())){
							checker = false;
						}
					}
					
					if (checker == true){
						addUser(newUser, "Student.txt", "Student");
						adminMenu(primaryStage);
					}
					else if (checker == false){
						txt1.setText("");
						errorMsg.setText("Login ID Taken");
					}
				}
			}
		});
		
		GridPane.setHalignment(register, HPos.RIGHT);
		mainPane.add(register, 1, 10);
		Button exit = new Button ("Cancel");
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminMenu(primaryStage);
			}
		});
		mainPane.add(exit, 1, 11);
		GridPane.setHalignment(exit, HPos.RIGHT);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void writeFile(String filename, String type, ArrayList<User> user){
		if (type.equalsIgnoreCase("Student")){
			try {
				File file = new File (filename);
				PrintWriter output = new PrintWriter(file);
				if (file.exists()){
					for (int i=0;i<user.size();i++){
						output.print(user.get(i).getFname()+";");
						output.print(user.get(i).getPassword()+";");
						output.print(user.get(i).getBdate()+";");
						output.print(user.get(i).getLoginID()+";");
						output.print(user.get(i).getGender()+";");
						output.print(user.get(i).getEmail()+";");
						output.print(user.get(i).getIDno()+";");
						output.print(((Student) user.get(i)).getIntake());
						output.println();
					}
				}
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				
			}
		}
		else if (type.equalsIgnoreCase("Lecturer")){
			try {
				File file = new File (filename);
				PrintWriter output = new PrintWriter(file);
				if (file.exists()){
					for (int i=0;i<user.size();i++){
						output.print(user.get(i).getFname()+";");
						output.print(user.get(i).getPassword()+";");
						output.print(user.get(i).getBdate()+";");
						output.print(user.get(i).getLoginID()+";");
						output.print(user.get(i).getGender()+";");
						output.print(user.get(i).getEmail()+";");
						output.print(user.get(i).getIDno()+";");
						output.print(((Lecturer) user.get(i)).getMarStat());
						output.println();
					}
				}
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				
			}
		}
	}

	public void modUser (ArrayList<User> user, String filename, String type){
		if (type.equalsIgnoreCase("Student")){
			try {
				File file = new File (filename);
				PrintWriter output = new PrintWriter(file);
				if (file.exists()){
					for (int i=0;i<user.size();i++){
						output.print(user.get(i).getFname()+";");
						output.print(user.get(i).getPassword()+";");
						output.print(user.get(i).getBdate()+";");
						output.print(user.get(i).getLoginID()+";");
						output.print(user.get(i).getGender()+";");
						output.print(user.get(i).getEmail()+";");
						output.print(user.get(i).getIDno()+";");
						output.print(((Student) user.get(i)).getIntake());
						output.println();
					}
				}
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				
			}
		}
		else if (type.equalsIgnoreCase("Lecturer")){
			try {
				File file = new File (filename);
				PrintWriter output = new PrintWriter(file);
				if (file.exists()){
					for (int i=0;i<user.size();i++){
						output.print(user.get(i).getFname()+";");
						output.print(user.get(i).getPassword()+";");
						output.print(user.get(i).getBdate()+";");
						output.print(user.get(i).getLoginID()+";");
						output.print(user.get(i).getGender()+";");
						output.print(user.get(i).getEmail()+";");
						output.print(user.get(i).getIDno()+";");
						output.print(((Lecturer) user.get(i)).getMarStat());
						output.println();
					}
				}
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				
			}
		}
	}
	
	public void addUser (User newUser, String filename, String type){
		ArrayList<User> user = new ArrayList<>();
		if (type.equalsIgnoreCase("Student")){
			user =  readFile(filename,"Student");
			user.add(newUser);
			try {
				File file = new File (filename);
				PrintWriter output = new PrintWriter(file);
				if (file.exists()){
					for (int i=0;i<user.size();i++){
						output.print(user.get(i).getFname()+";");
						output.print(user.get(i).getPassword()+";");
						output.print(user.get(i).getBdate()+";");
						output.print(user.get(i).getLoginID()+";");
						output.print(user.get(i).getGender()+";");
						output.print(user.get(i).getEmail()+";");
						output.print(user.get(i).getIDno()+";");
						output.print(((Student) user.get(i)).getIntake());
						output.println();
					}
				}
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				
			}
		}
		else if (type.equalsIgnoreCase("Lecturer")){
			user =  readFile(filename,"Lecturer");
			user.add(newUser);
			try {
				File file = new File (filename);
				PrintWriter output = new PrintWriter(file);
				if (file.exists()){
					for (int i=0;i<user.size();i++){
						output.print(user.get(i).getFname()+";");
						output.print(user.get(i).getPassword()+";");
						output.print(user.get(i).getBdate()+";");
						output.print(user.get(i).getLoginID()+";");
						output.print(user.get(i).getGender()+";");
						output.print(user.get(i).getEmail()+";");
						output.print(user.get(i).getIDno()+";");
						output.print(((Lecturer) user.get(i)).getMarStat());
						output.println();
					}
				}
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				
			}
		}
	}
	
	public ArrayList<User> readFile (String filename, String type){
		ArrayList<User> user = new ArrayList<User>();
		String[] buffer;
		if (type.equalsIgnoreCase("Student")){
			try {
				File file = new File(filename);
				if(file.exists()){
					Scanner input = new Scanner(file);
					user.clear();
					while (input.hasNext()){
						buffer = input.nextLine().split(";");
						user.add(new Student(buffer[0],buffer[1],buffer[2],buffer[3],buffer[4],buffer[5],buffer[6],buffer[7]));
					}
					input.close();
				}
			} catch (FileNotFoundException e) {
				
			}
			//0-fname, 1-password, 2-Bdate,3-loginID, 4 - gender, 5- email, 6-IDno, 7-Intake
		}
		else if (type.equalsIgnoreCase("Lecturer")){
			try {
				File file = new File(filename);
				if(file.exists()){
					Scanner input = new Scanner(file);
					user.clear();
					while (input.hasNext()){
						buffer = input.nextLine().split(";");
						user.add(new Lecturer(buffer[0],buffer[1],buffer[2],buffer[3],buffer[4],buffer[5],buffer[6],buffer[7]));
					}
					input.close();
				}
			} catch (FileNotFoundException e) {
				
			}
			//0-fname, 1-password, 2-Bdate,3-loginID, 4 - gender, 5- email, 6-IDno, 7-MaritalStatus
		}
		return user;
	}

	public ObservableList<?> collection (ArrayList<?> option){
		ObservableList<?> options = FXCollections.observableArrayList(option);
		return options;
	}
	
	//StudentLogin() method is used to display the student login pane.
	public void StudentLogin(GridPane mainPane, Stage primaryStage, String filename){
		GridPane loginPane = new GridPane();
		loginPane.setVgap(8);
		TextField txtUsername = new TextField();
		txtUsername.setPromptText("Enter Username");
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Enter Password");
		Label lblUsername = new Label("Username : ");
		Label lblPassword = new Label("Password  : ");
		lblUsername.setMinWidth(150);
		txtUsername.setMinWidth(200);
		lblUsername.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		lblPassword.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		loginPane.add(lblUsername, 0, 0);
		loginPane.add(txtUsername, 1, 0);
		loginPane.add(lblPassword, 0, 1);
		loginPane.add(txtPassword, 1, 1);
		Label error = new Label("");
		error.setStyle("-fx-text-fill: Red");
		loginPane.add(error, 0, 3);
		
		Button login = new Button("Login");
		login.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		login.setStyle("-fx-background-color: #add8e6");
		login.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		login.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		login.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ArrayList<User> user = new ArrayList<>();
				user = readFile("Student.txt", "Student");
				boolean checker = true;
				for(int y =0;y<user.size();y++){
					if (txtUsername.getText().equals(user.get(y).getLoginID()) && txtPassword.getText().equals(user.get(y).getPassword())){
						curUser = user.get(y);
						y = user.size();
						error.setText("");
						checker = false;
						studMenu(primaryStage);
					}
				}
				if (checker == true){
					error.setText("Invalid Login Details");
					txtUsername.setText("");
					txtPassword.setText("");
				}
			}
		});
		GridPane.setHalignment(login, HPos.RIGHT);
		loginPane.add(login, 1, 3);
		
		Label title = new Label("Student Login");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		mainPane.add(loginPane, 0, 1);
		mainPane.setAlignment(Pos.CENTER);
	}
	
	public void studMenu (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Welcome "+ curUser.getFname());
		title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		
		Label error = new Label();
		error.setTextFill(Color.RED);
		mainPane.add(error, 0, 5);
		
		Button check = new Button("Check Attendence");
		GridPane.setHalignment(check, HPos.CENTER);
		mainPane.add(check, 0, 1);
		check.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				checkAtt(primaryStage);
			}
		});
		
		Button absent = new Button("Provide Reason For Absent");
		GridPane.setHalignment(absent, HPos.CENTER);
		mainPane.add(absent, 0, 2);
		absent.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				reasonAbsent(primaryStage);
			}
		});
		
		GridPane pwdChg = new GridPane();
		pwdChg.setVisible(false);
		pwdChg.setAlignment(Pos.CENTER);
		mainPane.add(pwdChg, 0, 3);
		TextField newPassword = new TextField();
		pwdChg.add(newPassword, 0, 0);
		newPassword.setPromptText("Input New Password");
		Button confirm = new Button("Change");
		GridPane.setHalignment(confirm, HPos.CENTER);
		pwdChg.add(confirm, 0, 1);
		confirm.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ArrayList<User> user = new ArrayList<>();
				if (!newPassword.equals("")){
					user = readFile ("Student.txt", "Student");
					for (int i=0;i<user.size();i++){
						if (user.get(i).getLoginID().equals(curUser.getLoginID())){
							user.remove(i);
						}
					}
					curUser.setPassword(newPassword.getText());
					user.add(curUser);
					modUser(user, "Student.txt", "Student");
					studMenu(primaryStage);
				}else {
					error.setText("Please Input New Password");
				}
			}
		});
		Button change = new Button("Change Password");
		GridPane.setHalignment(change, HPos.CENTER);
		mainPane.add(change, 0, 3);
		change.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				mainPane.getChildren().remove(change);
				pwdChg.setVisible(true);
			}
		});
		
		Button exit = new Button("Log Out");
		GridPane.setHalignment(exit, HPos.CENTER);
		mainPane.add(exit, 0, 4);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void reasonAbsent(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Choose Attendance");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		mainPane.add(title, 0, 0);
		Label error = new Label();
		error.setTextFill(Color.RED);
		Set<String> type = new HashSet<String>();
		String [] buffer;
		try {
			File file = new File("Attendence.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					buffer = input.nextLine().split(";");
					if (buffer[3].equals("Absent")){
						type.add(buffer[0]);
					}
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		
		ArrayList<String> list = new ArrayList<>();
		list.addAll(type);
		final ComboBox<String> cb1 = new ComboBox(collection(list));
		GridPane.setHalignment(cb1, HPos.CENTER);
		mainPane.add(cb1, 0, 1);
		
		TextArea txt = new TextArea();
		mainPane.add(txt, 0, 2);
		
		Button back = new Button("Back");
		GridPane.setHalignment(back, HPos.LEFT);
		mainPane.add(back, 0, 3);
		
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				studMenu(primaryStage);
			}
		});
		
		Button done = new Button("Done");
		GridPane.setHalignment(done, HPos.RIGHT);
		mainPane.add(done, 0, 3);
		
		done.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				String temp;
				temp = curUser.getLoginID()+";"+cb1.getSelectionModel().getSelectedItem()+";"+txt.getText();
				addReason(temp);
				studMenu(primaryStage);
			}
		});
		
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void addReason (String newLine){
		ArrayList<String> old = new ArrayList<>();
		try {
			File file = new File("Reason.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					old.add(input.nextLine());
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		String [] buffer;
		for(int i=0;i<old.size();i++){
			buffer = old.get(i).split(";");
			if(buffer[0].equals(newLine.split(";")[0]) && buffer[1].equals(newLine.split(";")[1])){
				old.remove(i);
			}
		}
		try {
			File file = new File ("Reason.txt");
			PrintWriter output = new PrintWriter(file);
			if (file.exists()){
				for (int i=0;i<old.size();i++){
					output.println(old.get(i));
				}
				output.println(newLine);
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	public void checkAtt(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map = getMap("Java Programming");
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Java Programming");
		xAxis.setLabel("Status");       
		yAxis.setLabel("Amount");
		XYChart.Series<String,Number> series1 = new XYChart.Series<>();
		series1.setName("Attended");
		XYChart.Series<String,Number> series2 = new XYChart.Series<>();
		series2.setName("Absent");
		XYChart.Series<String,Number> series3 = new XYChart.Series<>();
		series3.setName("Late");
		
		for(String key: map.keySet()){
			if(key.equals("Attended")){
				series1.getData().add(new XYChart.Data<String,Number>(key, map.get(key)));
			}
			else if(key.equals("Absent")){
				series2.getData().add(new XYChart.Data<String,Number>(key, map.get(key)));
			}
			else if(key.equals("Late")){
				series3.getData().add(new XYChart.Data<String,Number>(key, map.get(key)));
			}
		}
		bc.getData().add(series1);
		bc.getData().add(series2);
		bc.getData().add(series3);
		mainPane.add(bc, 0, 0);
		
		HashMap<String, Integer> map1 = new HashMap<String,Integer>();
		map1 = getMap("Database Systems");
		final CategoryAxis xAxis1 = new CategoryAxis();
        final NumberAxis yAxis1 = new NumberAxis();
		final BarChart<String,Number> bc1 = new BarChart<String,Number>(xAxis1,yAxis1);
		bc1.setTitle("Database Systems");
		xAxis1.setLabel("Status");       
		yAxis1.setLabel("Amount");
		XYChart.Series<String,Number> series1_1 = new XYChart.Series<>();
		series1_1.setName("Attended");
		XYChart.Series<String,Number> series2_1 = new XYChart.Series<>();
		series2_1.setName("Absent");
		XYChart.Series<String,Number> series3_1 = new XYChart.Series<>();
		series3_1.setName("Late");
		
		for(String key: map1.keySet()){
			if(key.equals("Attended")){
				series1_1.getData().add(new XYChart.Data<String,Number>(key, map1.get(key)));
			}
			else if(key.equals("Absent")){
				series2_1.getData().add(new XYChart.Data<String,Number>(key, map1.get(key)));
			}
			else if(key.equals("Late")){
				series3_1.getData().add(new XYChart.Data<String,Number>(key, map1.get(key)));
			}
		}
		bc1.getData().add(series1_1);
		bc1.getData().add(series2_1);
		bc1.getData().add(series3_1);
		mainPane.add(bc1, 0, 0);
		bc1.setVisible(false);
		
		HashMap<String, Integer> map2 = new HashMap<String,Integer>();
		map2 = getMap("Mathematics");
		final CategoryAxis xAxis2 = new CategoryAxis();
        final NumberAxis yAxis2 = new NumberAxis();
		final BarChart<String,Number> bc2 = new BarChart<String,Number>(xAxis2,yAxis2);
		bc2.setTitle("Mathematics");
		xAxis2.setLabel("Status");       
		yAxis2.setLabel("Amount");
		XYChart.Series<String,Number> series1_2 = new XYChart.Series<>();
		series1_2.setName("Attended");
		XYChart.Series<String,Number> series2_2 = new XYChart.Series<>();
		series2_2.setName("Absent");
		XYChart.Series<String,Number> series3_2 = new XYChart.Series<>();
		series3_2.setName("Late");
		
		for(String key: map2.keySet()){
			if(key.equals("Attended")){
				series1_2.getData().add(new XYChart.Data<String,Number>(key, map2.get(key)));
			}
			else if(key.equals("Absent")){
				series2_2.getData().add(new XYChart.Data<String,Number>(key, map2.get(key)));
			}
			else if(key.equals("Late")){
				series3_2.getData().add(new XYChart.Data<String,Number>(key, map2.get(key)));
			}
		}
		bc2.getData().add(series1_2);
		bc2.getData().add(series2_2);
		bc2.getData().add(series3_2);
		mainPane.add(bc2, 0, 0);
		bc2.setVisible(false);
		
		GridPane selectionPane = new GridPane();
		selectionPane.setAlignment(Pos.CENTER);
		selectionPane.setHgap(3);
		mainPane.add(selectionPane, 0, 1);
		
		Button sub1 = new Button("View Java Programming");
		GridPane.setHalignment(sub1, HPos.CENTER);
		selectionPane.add(sub1, 0, 0);
		sub1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				bc.setVisible(true);
				bc1.setVisible(false);
				bc2.setVisible(false);
			}
		});
		
		Button sub2 = new Button("View Database Systems");
		GridPane.setHalignment(sub2, HPos.CENTER);
		selectionPane.add(sub2, 1, 0);
		sub2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				bc.setVisible(false);
				bc1.setVisible(true);
				bc2.setVisible(false);
			}
		});
		
		Button sub3 = new Button("View Mathematics");
		GridPane.setHalignment(sub3, HPos.CENTER);
		selectionPane.add(sub3, 2, 0);
		sub3.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				bc.setVisible(false);
				bc1.setVisible(false);
				bc2.setVisible(true);
			}
		});
		
		Button exit = new Button("Return to Menu");
		GridPane.setHalignment(exit, HPos.CENTER);
		mainPane.add(exit, 0, 2);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				studMenu(primaryStage);
			}
		});
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public HashMap<String, Integer> getMap(String type){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Attended", 0);
		map.put("Absent", 0);
		map.put("Late", 0);
		String [] buffer;
		try {
			File file = new File("Attendence.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					buffer = input.nextLine().split(";");
					if(buffer[1].equals(curUser.getLoginID()) && buffer[0].split("_")[2].equals(type)){
						for(String key: map.keySet()){
							if(buffer[3].equals(key)){
								map.replace(key, map.get(key)+1);
							}else if (buffer[3].equals(key)){
								map.replace(key, map.get(key)+1);
							}else if (buffer[3].equals(key)){
								map.replace(key, map.get(key)+1);
							}
						}
					}
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		return map;
	}
	
	//ParentLogin() method is used to display the parent login pane
	public void LecturerLogin(GridPane mainPane, Stage primaryStage, String filename){
		GridPane loginPane = new GridPane();
		loginPane.setVgap(8);
		TextField txtUsername = new TextField();
		txtUsername.setPromptText("Enter Username");
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Enter Password");
		Label lblUsername = new Label("Username : ");
		Label lblPassword = new Label("Password  : ");
		lblUsername.setMinWidth(150);
		txtUsername.setMinWidth(200);
		lblUsername.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		lblPassword.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		loginPane.add(lblUsername, 0, 0);
		loginPane.add(txtUsername, 1, 0);
		loginPane.add(lblPassword, 0, 1);
		loginPane.add(txtPassword, 1, 1);
		Label error = new Label("");
		error.setStyle("-fx-text-fill: Red");
		loginPane.add(error, 0, 3);
		
		Button login = new Button("Login");
		login.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		login.setStyle("-fx-background-color: #add8e6");
		login.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		login.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		login.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ArrayList<User> user = new ArrayList<>();
				user = readFile("Lecturer.txt", "Lecturer");
				boolean checker = true;
				for(int y =0;y<user.size();y++){
					if (txtUsername.getText().equals(user.get(y).getLoginID()) && txtPassword.getText().equals(user.get(y).getPassword())){
						curUser = user.get(y);
						y = user.size();
						error.setText("");
						checker = false;
						lectMenu(primaryStage);
					}
				}
				if (checker == true){
					error.setText("Invalid Login Details");
					txtUsername.setText("");
					txtPassword.setText("");
				}
			}
		});
		GridPane.setHalignment(login, HPos.RIGHT);
		loginPane.add(login, 1, 2);
		
		Label title = new Label("Lecturer Login");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		mainPane.add(loginPane, 0, 1);
		mainPane.setAlignment(Pos.CENTER);
	}
	
	public void lectMenu (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(20);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Welcome "+curUser.getFname());
		title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		
		Button intake1 = new Button("View Intake 1 Students");
		GridPane.setHalignment(intake1, HPos.CENTER);
		mainPane.add(intake1, 0, 1);
		intake1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				intake1(primaryStage);
			}
		});
		
		Button intake2 = new Button("View Intake 2 Students");
		GridPane.setHalignment(intake2, HPos.CENTER);
		mainPane.add(intake2, 0, 2);
		intake2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				intake2(primaryStage);
			}
		});
		
		Button intake3 = new Button("View Intake 3 Students");
		GridPane.setHalignment(intake3, HPos.CENTER);
		mainPane.add(intake3, 0, 3);
		intake3.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				intake3(primaryStage);
			}
		});
		
		Button viewAtt = new Button("View Attendence");
		GridPane.setHalignment(viewAtt, HPos.CENTER);
		mainPane.add(viewAtt, 0, 4);
		viewAtt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				viewAtt(primaryStage, "Lecturer");
			}
		});
		
		Button exit = new Button("Log Out");
		GridPane.setHalignment(exit, HPos.CENTER);
		mainPane.add(exit, 0, 5);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void viewAtt (Stage primaryStage, String Type){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Choose Attendance");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		mainPane.add(title, 0, 0);
		Set<String> type = new HashSet<String>();
		String [] buffer;
		try {
			File file = new File("Attendence.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					buffer = input.nextLine().split(";");
					type.add(buffer[0]);
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		
		ArrayList<String> list = new ArrayList<>();
		list.addAll(type);
		final ComboBox<String> cb1 = new ComboBox(collection(list));
		GridPane.setHalignment(cb1, HPos.CENTER);
		mainPane.add(cb1, 0, 1);
		
		Button back = new Button("Back");
		GridPane.setHalignment(back, HPos.LEFT);
		mainPane.add(back, 0, 2);
		
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(Type.equals("Lecturer")){
					lectMenu(primaryStage);
				}else if (Type.equals("Admin")){
					adminMenu(primaryStage);
				}
			}
		});
		
		Button done = new Button("Done");
		GridPane.setHalignment(done, HPos.RIGHT);
		mainPane.add(done, 0, 2);
		
		done.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				modifyAtt(primaryStage, cb1.getSelectionModel().getSelectedItem(),Type);
			}
		});
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void intake1 (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(10);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Intake 1 Students");
		mainPane.add(title, 0, 0);
		GridPane.setHalignment(title, HPos.CENTER);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		
		TableColumn<User, String> loginID = new TableColumn<>("Login ID");
		loginID.setMinWidth(80);
		loginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));
		
		TableColumn<User, String> Fullname = new TableColumn<>("Full Name");
		Fullname.setMinWidth(100);
		Fullname.setCellValueFactory(new PropertyValueFactory<>("fname"));
		
		TableColumn<User, String> BDate = new TableColumn<>("Birth Date");
		BDate.setMinWidth(100);
		BDate.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
		
		TableColumn<User, String> gender = new TableColumn<>("Gender");
		gender.setMinWidth(80);
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		
		TableColumn<User, String> IDno = new TableColumn<>("IC/No");
		IDno.setMinWidth(100);
		IDno.setCellValueFactory(new PropertyValueFactory<>("IDno"));
		
		TableColumn<User, String> email = new TableColumn<>("Email Address");
		email.setMinWidth(150);
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableView<User> table = new TableView<>();
		mainPane.add(table, 0, 1);
		GridPane.setHalignment(table, HPos.CENTER);
		ArrayList<User>	user = new ArrayList<>();
		user = readFile("Student.txt", "Student");
		ObservableList<User> list = FXCollections.observableArrayList();
		for (int i=0;i<user.size();i++){
			if(((Student)user.get(i)).getIntake().equals("Intake 1")){
				list.add(user.get(i));
			}
		}
		table.setItems(list);
		table.getColumns().addAll(loginID,Fullname,BDate,gender, IDno, email);
		
		Button takeAtt = new Button("Take Attendence");
		GridPane.setHalignment(takeAtt, HPos.CENTER);
		takeAtt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				takeAtt(primaryStage, "Intake 1");
			}
		});
		mainPane.add(takeAtt, 0, 2);
		
		Button exit = new Button("Back to Menu");
		GridPane.setHalignment(exit, HPos.CENTER);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				lectMenu(primaryStage);
			}
		});
		mainPane.add(exit, 0, 3);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void intake2 (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(10);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Intake 2 Students");
		mainPane.add(title, 0, 0);
		GridPane.setHalignment(title, HPos.CENTER);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		
		TableColumn<User, String> loginID = new TableColumn<>("Login ID");
		loginID.setMinWidth(80);
		loginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));
		
		TableColumn<User, String> Fullname = new TableColumn<>("Full Name");
		Fullname.setMinWidth(100);
		Fullname.setCellValueFactory(new PropertyValueFactory<>("fname"));
		
		TableColumn<User, String> BDate = new TableColumn<>("Birth Date");
		BDate.setMinWidth(100);
		BDate.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
		
		TableColumn<User, String> gender = new TableColumn<>("Gender");
		gender.setMinWidth(80);
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		
		TableColumn<User, String> IDno = new TableColumn<>("IC/No");
		IDno.setMinWidth(100);
		IDno.setCellValueFactory(new PropertyValueFactory<>("IDno"));
		
		TableColumn<User, String> email = new TableColumn<>("Email Address");
		email.setMinWidth(150);
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableView<User> table = new TableView<>();
		mainPane.add(table, 0, 1);
		GridPane.setHalignment(table, HPos.CENTER);
		ArrayList<User>	user = new ArrayList<>();
		user = readFile("Student.txt", "Student");
		ObservableList<User> list = FXCollections.observableArrayList();
		for (int i=0;i<user.size();i++){
			if(((Student)user.get(i)).getIntake().equals("Intake 2")){
				list.add(user.get(i));
			}
		}
		table.setItems(list);
		table.getColumns().addAll(loginID,Fullname,BDate,gender, IDno, email);
		
		Button takeAtt = new Button("Take Attendence");
		GridPane.setHalignment(takeAtt, HPos.CENTER);
		takeAtt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				takeAtt(primaryStage, "Intake 2");
			}
		});
		mainPane.add(takeAtt, 0, 2);
		
		Button exit = new Button("Back to Menu");
		GridPane.setHalignment(exit, HPos.CENTER);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				lectMenu(primaryStage);
			}
		});
		mainPane.add(exit, 0, 3);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void intake3 (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(10);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Intake 3 Students");
		mainPane.add(title, 0, 0);
		GridPane.setHalignment(title, HPos.CENTER);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		
		TableColumn<User, String> loginID = new TableColumn<>("Login ID");
		loginID.setMinWidth(80);
		loginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));
		
		TableColumn<User, String> Fullname = new TableColumn<>("Full Name");
		Fullname.setMinWidth(100);
		Fullname.setCellValueFactory(new PropertyValueFactory<>("fname"));
		
		TableColumn<User, String> BDate = new TableColumn<>("Birth Date");
		BDate.setMinWidth(100);
		BDate.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
		
		TableColumn<User, String> gender = new TableColumn<>("Gender");
		gender.setMinWidth(80);
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		
		TableColumn<User, String> IDno = new TableColumn<>("IC/No");
		IDno.setMinWidth(100);
		IDno.setCellValueFactory(new PropertyValueFactory<>("IDno"));
		
		TableColumn<User, String> email = new TableColumn<>("Email Address");
		email.setMinWidth(150);
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableView<User> table = new TableView<>();
		mainPane.add(table, 0, 1);
		GridPane.setHalignment(table, HPos.CENTER);
		ArrayList<User>	user = new ArrayList<>();
		user = readFile("Student.txt", "Student");
		ObservableList<User> list = FXCollections.observableArrayList();
		for (int i=0;i<user.size();i++){
			if(((Student)user.get(i)).getIntake().equals("Intake 3")){
				list.add(user.get(i));
			}
		}
		table.setItems(list);
		table.getColumns().addAll(loginID,Fullname,BDate,gender, IDno, email);
		
		Button takeAtt = new Button("Take Attendence");
		GridPane.setHalignment(takeAtt, HPos.CENTER);
		takeAtt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				takeAtt(primaryStage, "Intake 3");
			}
		});
		mainPane.add(takeAtt, 0, 2);
		
		Button exit = new Button("Back to Menu");
		GridPane.setHalignment(exit, HPos.CENTER);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				lectMenu(primaryStage);
			}
		});
		mainPane.add(exit, 0, 3);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void takeAtt(Stage primaryStage, String intake){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.setVgap(2);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		Label error = new Label();
		mainPane.add(error, 0, 8);
		error.setTextFill(Color.RED);
		
		GridPane selectionPane = new GridPane();
		selectionPane.setVgap(10);
		selectionPane.setHgap(5);
		mainPane.add(selectionPane, 0, 1);
		Label lb1 = new Label("Take Attendence");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setMinWidth(300);
		mainPane.add(lb1, 0, 0);
		ArrayList<String> intakes = new ArrayList<>();
		intakes.add("Intake 1");
		intakes.add("Intake 2");
		intakes.add("Intake 3");
		final ComboBox<String> cb1 = new ComboBox(collection(intakes));
		cb1.getSelectionModel().select(intake);
		selectionPane.add(new Label("Intake "), 0, 1);
		selectionPane.add(cb1, 1, 1);
		ArrayList<String> time = new ArrayList<>();
		time.add("10am - 12pm");
		time.add("2pm - 4pm");
		time.add("4pm - 6pm");
		final ComboBox<String> cb2 = new ComboBox(collection(time));
		selectionPane.add(new Label("Time Slot "), 0, 2);
		selectionPane.add(cb2, 1, 2);
		ArrayList<String> module = new ArrayList<>();
		module.add("Mathematics");
		module.add("Java Programming");
		module.add("Database Systems");
		final ComboBox<String> cb3 = new ComboBox(collection(module));
		selectionPane.add(new Label("Module "), 0, 3);
		selectionPane.add(cb3, 1, 3);
		selectionPane.add(new Label("Date"), 0, 4);
		final DatePicker datePicker = new DatePicker();
		datePicker.setPromptText("Select the Date ->");
		selectionPane.add(datePicker, 1, 4);
		
		TableColumn<User, String> loginID = new TableColumn<>("Login ID");
		loginID.setMinWidth(80);
		loginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));
		
		TableColumn<User, String> Fullname = new TableColumn<>("Full Name");
		Fullname.setMinWidth(100);
		Fullname.setCellValueFactory(new PropertyValueFactory<>("fname"));
		
		TableColumn<User, String> attend = new TableColumn<>("Attendence");
		attend.setMinWidth(100);
		attend.setCellValueFactory(new PropertyValueFactory<>("attend"));
		
		TableView<User> table = new TableView<>();
		mainPane.add(table, 0, 5);
		GridPane.setHalignment(table, HPos.CENTER);
		ArrayList<User>	user = new ArrayList<>();
		user = readFile("Student.txt", "Student");
		ObservableList<User> list = FXCollections.observableArrayList();
		for (int i=0;i<user.size();i++){
			if(((Student)user.get(i)).getIntake().equals(cb1.getSelectionModel().getSelectedItem())){
				list.add(user.get(i));
			}
		}
		table.setItems(list);
		table.getColumns().addAll(loginID,Fullname,attend);
		
		Button absent = new Button("Mark as Absent");
		GridPane.setHalignment(absent, HPos.CENTER);
		absent.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> selectedUser = table.getSelectionModel().getSelectedItems();
				if (selectedUser.size() != 0){
					((Student)selectedUser.get(0)).setAttend("Absent");
					table.refresh();
				}else {
					error.setText("Please Select a Student");
				}
			}
		});
		
		Button att = new Button("Mark as Attend");
		GridPane.setHalignment(att, HPos.CENTER);
		att.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> selectedUser = table.getSelectionModel().getSelectedItems();
				if (selectedUser.size() != 0){
					((Student)selectedUser.get(0)).setAttend("Attended");
					table.refresh();
				}else {
					error.setText("Please Select a Student");
				}
			}
		});
		Button late = new Button("Mark as Late");
		GridPane.setHalignment(late, HPos.CENTER);
		late.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ObservableList<User> selectedUser = table.getSelectionModel().getSelectedItems();
				if (selectedUser.size() != 0){
					((Student)selectedUser.get(0)).setAttend("Late");
					table.refresh();
				}else {
					error.setText("Please Select a Student");
				}
			}
		});
		Button exit = new Button("Cancel");
		GridPane.setHalignment(exit, HPos.CENTER);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				lectMenu(primaryStage);
			}
		});
		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		mainPane.add(pane, 0, 6);
		pane.add(absent, 0, 0);
		pane.add(att, 1, 0);
		pane.add(late, 0, 1);
		pane.add(exit, 1, 1);
		
		Button mark = new Button("Finish, Mark Attendence");
		GridPane.setHalignment(mark, HPos.CENTER);
		mainPane.add(mark, 0, 7);
		mark.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if (cb1.getSelectionModel().getSelectedItem() == null){
					error.setText("Please Fill In the Details (Intake)");
				}
				else if (cb2.getSelectionModel().getSelectedItem() == null){
					error.setText("Please Fill In the Details (Time Slot)");
				}else if (cb3.getSelectionModel().getSelectedItem() == null){
					error.setText("Please Fill In the Details (Module)");
				}else if (datePicker.getValue() == null){
					error.setText("Please Fill In the Details (Date");
				}
				else {
					String filename = cb1.getSelectionModel().getSelectedItem() +"_"+ cb2.getSelectionModel().getSelectedItem() +"_"+ cb3.getSelectionModel().getSelectedItem() +"_"+datePicker.getValue().toString();
					ArrayList<String> old = new ArrayList<>();
					try {
						File file = new File("Attendence.txt");
						if(file.exists()){
							Scanner input = new Scanner(file);
							while (input.hasNextLine()){
								old.add(input.nextLine());
							}
							input.close();
						}
					} catch (FileNotFoundException e) {
						
					}
					boolean checker = true;
					for(int i=0;i<old.size();i++){
						if (filename.equals(old.get(i).split(";")[0])){
							checker = false;
						}
					}
					if(checker == true) {
						markAtt(filename, list);
						lectMenu(primaryStage);
					}
					else if (checker == false){
						error.setText("This Attendence is Already Taken! Please Modify Details");
					}
				}
			}
		});
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Attendance System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void markAtt (String name, ObservableList<User> list){
		ArrayList<String> old = new ArrayList<>();
		try {
			File file = new File("Attendence.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					old.add(input.nextLine());
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		try {
			File file = new File ("Attendence.txt");
			PrintWriter output = new PrintWriter(file);
			if (file.exists()){
				for (int i=0;i<old.size();i++){
					output.println(old.get(i));
				}
				for (int i=0;i<list.size();i++){
					output.print(name+";");
					output.print(list.get(i).getLoginID()+";");
					output.print(list.get(i).getFname()+";");
					output.println(((Student)list.get(i)).getAttend());
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	//main() method will launch the application
	public static void main (String [] args){
		launch(args);
	}
}
