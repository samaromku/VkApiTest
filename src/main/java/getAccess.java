import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class getAccess extends Application {
    private static String url;
    private static String id;
    private static String access;

    @Override
    public void start(final Stage stage) {
        stage.setTitle("VK_API");
        stage.setWidth(818);
        stage.setHeight(640);
        Scene scene = new Scene(new Group());

        VBox root = new VBox();

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);
        final TextField locationField = new TextField("https://oauth.vk.com/authorize?client_id=5640858&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=wall&response_type=token&v=5.52");
        webEngine.loadContent("<center><b>Ожидание подключения...</b></center>");
        webEngine.load(locationField.getText());
        webEngine.locationProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                locationField.setText(newValue);
                url = locationField.getText();
                if(url.startsWith("https://oauth.vk.com/blank.html#access_token=")) {
                    stage.close();
                    clearURL(url);
                }
            }
        });
        root.getChildren().addAll(scrollPane);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
        System.out.println();
    }

    public static void main(String[] args) {
        launch(args);
        send("We%20need%20peace,%20preferably%20a%20whole!");
    }

    private static void clearURL(String url){
        //System.out.println(url);
        String access = "access_token=";
        String ID = "user_id=";
        int f = url.indexOf(access) + access.length();
        int s = url.indexOf('&');
        access = url.substring(f, s);
        f = url.lastIndexOf(ID) + ID.length();
        id = url.substring(f);
        //System.out.println(access + ":id" + id);
    }

    private static void send(String message){
        JEditorPane p = new JEditorPane();
        try {
            p.setPage("https://api.vk.com/method/wall.post?owner_id=" + id + "&message=" + message + "&v=5.52&access_token=" + access);
            System.out.println(p.getText());
        } catch (IOException ex) {
            System.out.println(p.getText());
            System.out.println("Не удалось отправить запрос!");}
    }
}