package controller;

import Model.Planet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ViewController {

    @FXML
    private Pane WelcomeMenuPane;

    @FXML
    private Pane BackgroundSecond;

    @FXML
    private Label planetName;

    @FXML
    private ImageView planetGround;

    private Planet[] planets;

    @FXML
    public void initialize() {
        planets = new Planet[] {
                new Planet(1, "Mercury", 3.70, "/View/planet1.png"),
                new Planet(2, "Venus",   8.87, "/View/planet2.png"),
                new Planet(3, "Earth",   9.81, "/View/planet3.png"),
                new Planet(4, "Mars",    3.71, "/View/planet4.png"),
                new Planet(5, "Jupiter", 24.79, "/View/planet5.png"),
                new Planet(6, "Saturn",  10.44, "/View/planet6.png"),
                new Planet(7, "Uranus",   8.69, "/View/planet7.png"),
                new Planet(8, "Neptune", 11.15, "/View/planet8.png")
        };
    }

    private void openPlanetScreen(int id) {
        Planet p = planets[id - 1];

        WelcomeMenuPane.setVisible(false);
        BackgroundSecond.setVisible(true);

        planetName.setText(p.getName());

        Image groundImage = new Image(
                getClass().getResourceAsStream(p.getGroundImagePath())
        );
        planetGround.setImage(groundImage);
    }

    // STEP 3 — one tiny method per planet

    @FXML
    void mercuryClicked(MouseEvent event) {
        openPlanetScreen(1);
    }

    @FXML
    void venusClicked(MouseEvent event) {
        openPlanetScreen(2);
    }

    @FXML
    void earthClicked(MouseEvent event) {
        openPlanetScreen(3);
    }

    @FXML
    void marsClicked(MouseEvent event) {
        openPlanetScreen(4);
    }

    @FXML
    void jupiterClicked(MouseEvent event) {
        openPlanetScreen(5);
    }

    @FXML
    void saturnClicked(MouseEvent event) {
        openPlanetScreen(6);
    }

    @FXML
    void uranusClicked(MouseEvent event) {
        openPlanetScreen(7);
    }

    @FXML
    void neptuneClicked(MouseEvent event) {
        openPlanetScreen(8);
    }
}
