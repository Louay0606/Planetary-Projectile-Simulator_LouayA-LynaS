package controller;

/*
 *  Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 *  Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Model.Planet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author louay
 */
public class ViewController implements Initializable {

    private Planet selectedPlanet;
    private String selectedObjectName;
    private Image selectedObjectImage;

    private double selectedVelocity;
    private double selectedAngle;

    private double timeOfFlight;
    private double maxHeight;
    private double range;

    private Planet[] planets;

    @FXML
    private AnchorPane WelcomeMenuPane;
    @FXML
    private ImageView BackgroundMenu;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label clickLabel;
    @FXML
    private ImageView venusMenu;
    @FXML
    private ImageView mercuryMenu;
    @FXML
    private ImageView earthMenu;
    @FXML
    private ImageView marsMenu;
    @FXML
    private ImageView jupiterMenu;
    @FXML
    private ImageView saturnMenu;
    @FXML
    private ImageView uranusMenu;
    @FXML
    private ImageView neptuneMenu;
    @FXML
    private ImageView BackgroudSecond;
    @FXML
    private Label planetName;
    @FXML
    private ImageView planetGround;
    @FXML
    private ImageView start;
    @FXML
    private ImageView flower;
    @FXML
    private ImageView soccer;
    @FXML
    private ImageView apple;
    @FXML
    private AnchorPane pane3;
    @FXML
    private Label objectChosenLabel;
    @FXML
    private ImageView objectChosenImageView;
    @FXML
    private Button returnButtonS3;
    @FXML
    private Button nextButtonS3;
    @FXML
    private ComboBox<Double> VelecotyComboBox;
    @FXML
    private Slider sliderangle;
    @FXML
    private AnchorPane pane4;
    @FXML
    private ImageView planetGroundSimulation;
    @FXML
    private Label timeFlightLabel;
    @FXML
    private Label maxHeightLabel;
    @FXML
    private ImageView thrownObjectImagiew;
    @FXML
    private Label distanceResultLabel;
    @FXML
    private Button resartSimuButton;
    @FXML
    private Button exitSimuButton;
    @FXML
    private AnchorPane pane5;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Label angleValueLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        planets = new Planet[]{
                new Planet(1, "Mercury", 3.70, "/View/planet1.png"),
                new Planet(2, "Venus", 8.87, "/View/planet2.png"),
                new Planet(3, "Earth", 9.81, "/View/planet3.png"),
                new Planet(4, "Mars", 3.71, "/View/planet4.png"),
                new Planet(5, "Jupiter", 24.79, "/View/planet5.png"),
                new Planet(6, "Saturn", 10.44, "/View/planet6.png"),
                new Planet(7, "Uranus", 8.69, "/View/planet7.png"),
                new Planet(8, "Neptune", 11.15, "/View/planet8.png")
        };

        floatAnimation(start);
        floatAnimation(apple);
        floatAnimation(flower);
        floatAnimation(soccer);

        WelcomeMenuPane.setVisible(true);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
    }

    private void openPlanetScreen(int id) {

        selectedPlanet = planets[id - 1];

        WelcomeMenuPane.setVisible(false);
        pane2.setVisible(true);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);

        planetName.setText(selectedPlanet.getName());

        Image groundImage = new Image(
                getClass().getResourceAsStream(selectedPlanet.getGroundImagePath())
        );
        planetGround.setImage(groundImage);
        planetGroundSimulation.setImage(groundImage);

        angleValueLabel.setText("Angle: " + (int) sliderangle.getValue() + "°");

        sliderangle.valueProperty().addListener((obs, oldVal, newVal) -> {
            angleValueLabel.setText("Angle: " + String.format("%.0f°", newVal.doubleValue()));
        });
    }

    private void floatAnimation(ImageView img) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1.5), img);
        t.setByY(-12);
        t.setCycleCount(Animation.INDEFINITE);
        t.setAutoReverse(true);
        t.play();
    }

    private void objectSelected(ImageView img, String objName) {
        selectedObjectName = objName;
        selectedObjectImage = img.getImage();

        pane2.setVisible(false);
        pane3.setVisible(true);

        objectChosenImageView.setImage(selectedObjectImage);
        objectChosenLabel.setText(objName);

        fillVelocityComboBox();
    }

    private void computeProjectileValues() {
        double v = selectedVelocity;
        double g = selectedPlanet.getGravity();
        double angleRad = Math.toRadians(selectedAngle);

        timeOfFlight = (2 * v * Math.sin(angleRad)) / g;
        range = (v * v * Math.sin(2 * angleRad)) / g;
        maxHeight = (v * v * Math.pow(Math.sin(angleRad), 2)) / (2 * g);
    }

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

    @FXML
    private void startClicked(MouseEvent event) {
        objectSelected(start, "Star");
    }

    @FXML
    private void flowerClicked(MouseEvent event) {
        objectSelected(flower, "Flower");
    }

    @FXML
    private void soccerClicked(MouseEvent event) {
        objectSelected(soccer, "Soccer Ball");
    }

    @FXML
    private void appleClicked(MouseEvent event) {
        objectSelected(apple, "Appple");
    }

    @FXML
    private void returnS3(ActionEvent event) {
        pane3.setVisible(false);
        pane2.setVisible(true);
        resetObjectAndControls();
    }

    @FXML
    private void nextS3(ActionEvent event) {
        if (VelecotyComboBox.getValue() == null || selectedObjectImage == null) {
            return;
        }

        selectedVelocity = VelecotyComboBox.getValue();
        selectedAngle = sliderangle.getValue();

        computeProjectileValues();
        startSmoothAnimation();
    }

    @FXML
    private void restartButton(ActionEvent event) {
    }

    @FXML
    private void exitSimuButton(ActionEvent event) {
    }

    private void resetObjectAndControls() {

        start.setVisible(true);
        apple.setVisible(true);
        flower.setVisible(true);
        soccer.setVisible(true);

        objectChosenImageView.setImage(null);
        objectChosenLabel.setText("");

        if (VelecotyComboBox != null) {
            VelecotyComboBox.getItems().clear();
            VelecotyComboBox.setValue(null);
        }
        sliderangle.setValue(45);
    }

    private void fillVelocityComboBox() {

        VelecotyComboBox.getItems().clear();

        double g = selectedPlanet.getGravity();

        if (g <= 4) {
            VelecotyComboBox.getItems().addAll(0.00, 5.0, 10.0, 15.0, 20.0, 25.0);
        } else if (g <= 11) {
            VelecotyComboBox.getItems().addAll(0.00, 10.0, 20.0, 30.0, 40.0);
        } else {
            VelecotyComboBox.getItems().addAll(0.00, 20.0, 30.0, 40.0, 50.0, 60.0);
        }

        VelecotyComboBox.setValue(VelecotyComboBox.getItems().get(0));
    }

    private void startSmoothAnimation() {

        pane3.setVisible(false);
        pane4.setVisible(true);

        thrownObjectImagiew.setImage(selectedObjectImage);

        double startX = 24;
        double startY = 150;
        thrownObjectImagiew.setLayoutX(startX);
        thrownObjectImagiew.setLayoutY(startY);

        double v = selectedVelocity;
        double g = selectedPlanet.getGravity();
        double angle = Math.toRadians(selectedAngle);

        final double[] t = {0};

        double scale = 2;

        double groundTopY = 218;
        double objectHeight = 39;
        double collisionY = 300;

        final Timeline[] timeline = new Timeline[1];

        timeline[0] = new Timeline(new KeyFrame(Duration.millis(16), e -> {

            t[0] += 0.016;

            double x = v * Math.cos(angle) * t[0];
            double y = v * Math.sin(angle) * t[0] - 0.5 * g * t[0] * t[0];

            double screenX = startX + x * scale;
            double screenY = startY - y * scale;

            thrownObjectImagiew.setLayoutX(screenX);
            thrownObjectImagiew.setLayoutY(screenY);

            thrownObjectImagiew.setRotate(thrownObjectImagiew.getRotate() + 5);

            if (screenY >= collisionY) {
                timeline[0].stop();
                showResults();
            }

            if (screenX > pane4.getWidth() || screenY > pane4.getHeight()) {
                timeline[0].stop();
                showResults();
            }

        }));

        timeline[0].setCycleCount(Animation.INDEFINITE);
        timeline[0].play();
    }

    private void showResults() {
        pane4.setVisible(false);
        pane5.setVisible(true);

        timeFlightLabel.setText(String.format("Time of flight: %.2f s", timeOfFlight));
        maxHeightLabel.setText(String.format("Max Height: %.2f m", maxHeight));
        distanceResultLabel.setText(String.format("Range: %.2f m", range));
    }
}
