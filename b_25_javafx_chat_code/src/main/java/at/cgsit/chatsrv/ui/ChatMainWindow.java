/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.chatsrv.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class ChatMainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        BorderPane root = new BorderPane();

        // ===== Top: Menu Bar =====
        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        // ===== Left: Chat Rooms =====
        VBox roomsPane = createRoomsPane();
        root.setLeft(roomsPane);

        // ===== Right: Chat Users =====
        VBox usersPane = createUsersPane();
        root.setRight(usersPane);

        // ===== Center: Chat Messages =====
        VBox messagesPane = createMessagesPane();
        root.setCenter(messagesPane);

        // ===== Bottom: Message Input =====
        HBox inputPane = createInputPane();
        root.setBottom(inputPane);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("CGS Chat Server - JavaFX Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem connectItem = new MenuItem("Connect");
        MenuItem disconnectItem = new MenuItem("Disconnect");
        MenuItem exitItem = new MenuItem("Exit");

        // TODO: hook up actions
        exitItem.setOnAction(e -> System.exit(0));

        fileMenu.getItems().addAll(connectItem, disconnectItem, new SeparatorMenuItem(), exitItem);

        Menu viewMenu = new Menu("View");
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        helpMenu.getItems().add(aboutItem);

        MenuBar menuBar = new MenuBar(fileMenu, viewMenu, helpMenu);
        return menuBar;
    }

    private VBox createRoomsPane() {
        Label roomsLabel = new Label("Chat Rooms");

        ListView<String> roomsList = new ListView<>();
        roomsList.getItems().addAll(
                "Lobby",
                "Java",
                "Quarkus",
                "Random",
                "Admins"
        );
        roomsList.setPrefWidth(200);

        // TODO: on room selection, load messages for that room
        roomsList.getSelectionModel().selectFirst();

        VBox box = new VBox(8, roomsLabel, roomsList);
        box.setPadding(new Insets(10));
        box.setPrefWidth(220);
        box.setStyle("-fx-background-color: #f5f5f5;");
        return box;
    }

    private VBox createUsersPane() {
        Label usersLabel = new Label("Users");

        ListView<String> usersList = new ListView<>();
        usersList.getItems().addAll(
                "Chris",
                "Alex",
                "Anna",
                "Bot-Assist",
                "Guest123"
        );
        usersList.setPrefWidth(200);

        // TODO: on user select, maybe open DM or highlight

        VBox box = new VBox(8, usersLabel, usersList);
        box.setPadding(new Insets(10));
        box.setPrefWidth(220);
        box.setStyle("-fx-background-color: #f5f5f5;");
        return box;
    }

    private VBox createMessagesPane() {
        Label messagesLabel = new Label("Messages");

        ListView<String> messagesList = new ListView<>();
        messagesList.getItems().addAll(
                "[System] Welcome to the Lobby!",
                "Chris: Hallo zusammen ",
                "Alex: Servus Chris, läuft der Server schon auf AWS?",
                "Chris: Noch lokal, aber bald "
        );

        // Optional: allow wrapping with cellFactory later
        messagesList.setPrefHeight(400);

        VBox box = new VBox(8, messagesLabel, messagesList);
        box.setPadding(new Insets(10));
        VBox.setVgrow(messagesList, Priority.ALWAYS);
        return box;
    }

    private HBox createInputPane() {
        TextField inputField = new TextField();
        inputField.setPromptText("Type a message...");

        Button sendButton = new Button("Send");

        // TODO: hook up to real send logic
        sendButton.setOnAction(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                // For now just print – in real app, send to server & update messages list
                System.out.println("SEND: " + text);
                inputField.clear();
            }
        });

        HBox box = new HBox(8, inputField, sendButton);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(10));
        HBox.setHgrow(inputField, Priority.ALWAYS);
        box.setBorder(new Border(new BorderStroke(
                null,
                BorderStrokeStyle.SOLID,
                null,
                new BorderWidths(1, 0, 0, 0)
        )));

        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
