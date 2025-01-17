package com.example.phase2.GUI;

import com.example.phase2.controller.Controller;
import com.example.phase2.controller.Presenter;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {
    Stage window;
    Scene sceneLogin, sceneMainMenu;
    LoginPage loginPage = new LoginPage();
    Presenter presenter = new Presenter();
    Controller controller = new Controller(presenter);

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Meals");

        TextField userNameInput = new TextField();
        TextField passCodeInput = new TextField();

        Button loginButton = new Button("Log In");
        loginButton.setOnAction(e -> {
            controller.loginAction(userNameInput.getText(), passCodeInput.getText());
            {if(!presenter.getLoginStatus()) AlertBox.display("LoginFail", "Login unsuccessful," +
                    " please try again!");}
            {if(presenter.getLoginStatus()) window.setScene(sceneMainMenu); ;}
        });

        Button createUserButton = new Button("Create Account");
        createUserButton.setOnAction(e -> CreateUserBox.display(controller));

        Button addIngButton = new Button("Add Ingredient");
        addIngButton.setOnAction(e -> AddIngredientBox.display(controller));

        Button findRecipeButton = new Button("Find Recipe");

        Button viewFridgeButton = new Button("View Fridge");
        viewFridgeButton.setOnAction(e -> ViewFridgeBox.display(controller, presenter));

        Button logOutButton = new Button("Logout");
        logOutButton.setOnAction(e -> {
            controller.logoutAction();
            window.close();
        });

        LoginLayOut loginLayOut = new LoginLayOut();

        MainMenuLayOut mainMenuLayOut = new MainMenuLayOut();

        sceneLogin = new Scene(loginLayOut.loginScene(userNameInput, passCodeInput, loginButton,
                createUserButton), 900, 600);

        sceneMainMenu = new Scene(mainMenuLayOut.mainMenuScene(addIngButton, findRecipeButton,
                viewFridgeButton, logOutButton), 900, 600);

        window.setScene(sceneLogin);
        window.setResizable(false);

        window.show();

    }
}
