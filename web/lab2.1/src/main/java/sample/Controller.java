package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import company.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button factory;

    @FXML
    private Button ok;

    @FXML
    private Button ok1;

    @FXML
    private Pane panecategory;

    @FXML
    private Pane panefactory;

    @FXML
    private Pane paneresult;

    @FXML
    private Button sort;

    @FXML
    private TextField text;

    @FXML
    private TextField text1;

    @FXML
    private TextArea res;

    @FXML
    void initialize() {
        assert factory != null : "fx:id=\"factory\" was not injected: check your FXML file '1.fxml'.";
        assert ok != null : "fx:id=\"ok\" was not injected: check your FXML file '1.fxml'.";
        assert ok1 != null : "fx:id=\"ok1\" was not injected: check your FXML file '1.fxml'.";
        assert panecategory != null : "fx:id=\"panecategory\" was not injected: check your FXML file '1.fxml'.";
        assert panefactory != null : "fx:id=\"panefactory\" was not injected: check your FXML file '1.fxml'.";
        assert paneresult != null : "fx:id=\"paneresult\" was not injected: check your FXML file '1.fxml'.";
        assert sort != null : "fx:id=\"sort\" was not injected: check your FXML file '1.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file '1.fxml'.";
        assert text1 != null : "fx:id=\"text1\" was not injected: check your FXML file '1.fxml'.";
        assert res != null : "fx:id=\"res\" was not injected: check your FXML file '1.fxml'.";

        Lastics lastic1 = new Lastics("Gos1", "Map", 1.05, "Rubber");
        Pens pen1 = new Pens("Gos2", "Kaleo blue", 2.00, "blue");
        Pencils pencil1 = new Pencils("Gos1", "Megan usual", 3.50, 2);
        Pencils pencil2 = new Pencils("Gos2", "Megan2", 5.50, 2);
        Pencils pencil3 = new Pencils("Gos3", "Megan3", 4.50, 2);

        ArrayList<Tovar> tovars = new ArrayList<Tovar>();
        tovars.add(lastic1);
        tovars.add(pen1);
        tovars.add(pencil1);
        tovars.add(pencil2);
        tovars.add(pencil3);

        Agency agency = new Agency("Flowers", tovars);

        Extander ex = new Extander();


        factory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                res.setText("");
                panecategory.setVisible(false);
                panefactory.setVisible(true);
                paneresult.setVisible(false);
            }
        });

        sort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                res.setText("");
                panefactory.setVisible(false);
                panecategory.setVisible(true);
                paneresult.setVisible(false);
            }
        });

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FindByFactory findByFactory = new FindByFactory(agency);
                String par = text.getText();
                String r = "";
                for( Tovar tovar: findByFactory.find(par)){
                    r += tovar.getClassName() + ": " + tovar.getName() + "\n";
                }
                res.setText(r);
                paneresult.setVisible(true);
                text.setText("");
            }
        });

        ok1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SortByCategory sortByCategory = new SortByCategory(agency);
                String par = text1.getText();
                String r = "";
                for( Tovar tovar: sortByCategory.sort(par)){
                    r += tovar.getClassName() + ": " + tovar.getName() + "\n";
                }
                res.setText(r);
                paneresult.setVisible(true);
                text1.setText("");
            }
        });


    }

}
