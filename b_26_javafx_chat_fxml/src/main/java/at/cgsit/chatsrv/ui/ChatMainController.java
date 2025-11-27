package at.cgsit.chatsrv.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChatMainController {

    @FXML
    private ListView<String> roomsListView;

    @FXML
    private ListView<String> usersListView;

    @FXML
    private ListView<String> messagesListView;

    @FXML
    private TextField messageInputField;

    @FXML
    private MenuItem connectMenuItem;

    @FXML
    private MenuItem disconnectMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    public void initialize() {
        // Init rooms
        roomsListView.getItems().addAll(
                "Lobby",
                "Java",
                "Quarkus",
                "Random",
                "Admins"
        );
        roomsListView.getSelectionModel().selectFirst();

        // Init users
        usersListView.getItems().addAll(
                "Chris",
                "Alex",
                "Anna",
                "Bot-Assist",
                "Guest123"
        );

        // Init messages
        messagesListView.getItems().addAll(
                "[System] Welcome to the Lobby!",
                "Chris: Hallo zusammen ",
                "Alex: Servus Chris, läuft der Server schon auf AWS?",
                "Chris: Noch lokal, aber bald "
        );


    }

    @FXML
    private void onSendMessage() {
        String text = messageInputField.getText();
        if (text == null) {
            return;
        }

        text = text.trim();
        if (text.isEmpty()) {
            return;
        }

        // For now just append to messages list
        messagesListView.getItems().add("Me: " + text);
        messagesListView.scrollTo(messagesListView.getItems().size() - 1);

        // TODO: later send to server via WebSocket/REST
        System.out.println("SEND: " + text);

        messageInputField.clear();

        trimMessages();
    }

    private static final int MAX_MESSAGES = 10;

    private void trimMessages() {
        ObservableList<String> items = messagesListView.getItems();

        if (items.size() > MAX_MESSAGES) {
            // zb. 20 messages drinnen minus 10 .. dann lsöchen wir 10
            int removeCount = items.size() - MAX_MESSAGES;

            // direkt von LIST API .. generell
            items.subList(0, removeCount);
            // hilfs mehtode von jfx

            items.remove(0, removeCount);  // remove first N (older) elements
        }
    }


  @FXML
  private void onRoomListClicked(MouseEvent event) {
    String selectedRoom = roomsListView
        .getSelectionModel()
        .getSelectedItem();

    if (selectedRoom == null) {
      return;
    }

    if (event.getClickCount() == 2) {
      // ===== Double-click on room =====
      System.out.println("Double-click on room: " + selectedRoom);
      // TODO: join room / load history / subscribe via WebSocket, etc.
    } else if (event.getClickCount() == 1) {
      // ===== Single-click on room =====
      System.out.println("Selected room: " + selectedRoom);
      // TODO: maybe preview room info, highlight, etc.
      showRoomInfoPopup(selectedRoom);
    }
  }

  @FXML
  private void onUserListClicked(MouseEvent event) {
    String selectedUser = usersListView
        .getSelectionModel()
        .getSelectedItem();

    if (selectedUser == null) {
      return;
    }

    if (event.getClickCount() == 2) {
      // ===== Double-click on user =====
      System.out.println("Double-click on user: " + selectedUser);
      // TODO: open 1:1 chat tab / DM window / select private chat room
    } else if (event.getClickCount() == 1) {
      // ===== Single-click on user =====
      System.out.println("Selected user: " + selectedUser);
      // TODO: show presence, profile, etc.
      showUserInfoPopup(selectedUser);
    }
  }

  private void showRoomInfoPopup(String roomName) {

    // Example dummy data – replace later with real backend logic
    String description = switch (roomName) {
      case "Lobby"   -> "Main entry chat room for everyone.";
      case "Java"    -> "Java discussions, JVM tuning, frameworks...";
      case "Quarkus" -> "Quarkus, Reactive, RESTEasy, Panache, DevServices...";
      case "Random"  -> "Off-topic chat for fun conversations.";
      case "Admins"  -> "System admins & moderators only.";
      default        -> "General discussion room.";
    };

    int activeUsers = (int) (Math.random() * 20) + 1;  // example dummy count

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Room Details");
    alert.setHeaderText(roomName + " Room Information");

    String text = """
            Room: %s
            Description: %s
            Active Users: %d
            """.formatted(
        roomName,
        description,
        activeUsers
    );

    alert.setContentText(text);
    alert.showAndWait();
  }


  private void showUserInfoPopup(String username) {

    // ---------------------------
    // Example User Data
    // Replace with real lookup later
    // ---------------------------
    String firstName = "Chris";
    String lastName  = "Gruber";
    String email     = "chris@example.com";

    // Full name
    String fullName = firstName + " " + lastName;


    // ---------------------------
    // UI Layout
    // ---------------------------
    VBox root = new VBox();
    root.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-spacing: 12;");
    root.setPrefWidth(320);


    // ----- Header -----
    Label header = new Label("User Profile");
    header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; "
        + "-fx-text-fill: white; -fx-padding: 8;");
    header.setMaxWidth(Double.MAX_VALUE);
    header.setAlignment(javafx.geometry.Pos.CENTER);

    // CGS Blue bar
    VBox headerBox = new VBox(header);
    headerBox.setStyle("-fx-background-color: #749DC6; -fx-padding: 5; "
        + "-fx-background-radius: 6 6 0 0;");


    // ----- Avatar (simple circle) -----
    Circle avatar = new Circle(40);
    avatar.setFill(javafx.scene.paint.Color.web("#749DC6"));
    Label avatarInitial = new Label(firstName.substring(0,1).toUpperCase());
    avatarInitial.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");

    StackPane avatarPane = new StackPane(avatar, avatarInitial);
    avatarPane.setAlignment(javafx.geometry.Pos.CENTER);


    // ----- Name -----
    Label nameLabel = new Label(fullName);
    nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

    // ----- Username -----
    Label usernameLabel = new Label("@" + username);
    usernameLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 13px;");


    // ----- Email + Copy button -----
    HBox emailBox = new HBox(10);
    emailBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

    Label emailLabel = new Label(email);
    emailLabel.setStyle("-fx-font-size: 14px;");

    Button copyBtn = new Button("Copy");
    copyBtn.setOnAction(e -> {
      ClipboardContent content = new ClipboardContent();
      content.putString(email);
      Clipboard.getSystemClipboard().setContent(content);
      copyBtn.setText("Copied ✔");
    });

    emailBox.getChildren().addAll(emailLabel, copyBtn);


    // ----- Start DM Chat button -----
    Button dmButton = new Button("Start DM Chat");
    dmButton.setStyle("""
        -fx-background-color: #749DC6;
        -fx-text-fill: white;
        -fx-font-size: 14px;
        -fx-padding: 6 12;
        -fx-background-radius: 6;
        """);
    dmButton.setOnAction(e -> {
      System.out.println("Start private chat with: " + username);
      // TODO: open DM room
    });


    // ----- Close button -----
    Button closeButton = new Button("Close");
    closeButton.setOnAction(e -> ((Stage) closeButton.getScene().getWindow()).close());


    // Layout assembly
    root.getChildren().addAll(
        headerBox,
        avatarPane,
        nameLabel,
        usernameLabel,
        new Separator(),
        emailBox,
        new Separator(),
        dmButton,
        closeButton
    );


    // ---------------------------
    // Create custom undecorated window
    // ---------------------------
    Stage popupStage = new Stage();
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.setResizable(false);
    popupStage.setTitle("User Profile");

    Scene scene = new Scene(root);
    popupStage.setScene(scene);

    popupStage.showAndWait();
  }




    @FXML
    private void onConnect() {
        // TODO: implement connect to chat server
        System.out.println("Connect clicked");
    }

    @FXML
    private void onDisconnect() {
        // TODO: implement disconnect from chat server
        System.out.println("Disconnect clicked");
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About CGS Chat Client");
        alert.setHeaderText("CGS Chat Client");
        alert.setContentText("JavaFX UI for your Chat Server.\n(c) CGS IT Solutions GmbH");
        alert.showAndWait();
    }
}
