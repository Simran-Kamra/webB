package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.web.WebHistory;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    String htLink = "http://";

    @FXML
    TextField addressBar;
    String adrsLink;

    @FXML
    WebView web;

    private WebHistory history;

    WebEngine engine;

    public void go(ActionEvent actionEvent){
        adrsLink = addressBar.getText().toString();
        engine.load(htLink+adrsLink);
        engine.load("http://"+addressBar.getText());

    }
    public void refresh() {
        engine.reload();
    }

    public void displayHistory() {

        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        for(WebHistory.Entry entry : entries) {

            //System.out.println(entry);
            System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
        }
    }

    public void back() {

        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);

        addressBar.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void forward() {

        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);

        addressBar.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void executeJS() {

        engine.executeScript("window.location = \"https://www.youtube.com\";");
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = web.getEngine();
        engine.load(htLink+"www.google.com");

//        homePage = "www.google.com";
//        addressBar.setText(homePage);

    }
}