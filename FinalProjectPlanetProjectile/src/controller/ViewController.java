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
 * Controller for the Planetary Projectile Simulator.
 * Handles user interaction, scene navigation, physics computation,
 * and projectile animation.
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

    @FXML private AnchorPane WelcomeMenuPane;
    @FXML private ImageView BackgroundMenu;
    @FXML private Label welcomeLabel;
    @FXML private Label clickLabel;
    @FXML private ImageView venusMenu;
    @FXML private ImageView mercuryMenu;
    @FXML private ImageView earthMenu;
    @FXML private ImageView marsMenu;
    @FXML private ImageView jupiterMenu;
    @FXML private ImageView saturnMenu;
    @FXML private ImageView uranusMenu;
    @FXML private ImageView neptuneMenu;
    @FXML private ImageView BackgroudSecond;
    @FXML private Label planetName;
    @FXML private ImageView planetGround;
    @FXML private ImageView start;
    @FXML private ImageView flower;
    @FXML private ImageView soccer;
    @FXML private ImageView apple;
    @FXML private AnchorPane pane3;
    @FXML private Label objectChosenLabel;
    @FXML private ImageView objectChosenImageView;
    @FXML private Button returnButtonS3;
    @FXML private Button nextButtonS3;
    @FXML private ComboBox<Double> VelecotyComboBox;
    @FXML private Slider sliderangle;
    @FXML private AnchorPane pane4;
    @FXML private ImageView planetGroundSimulation;
    @FXML private ImageView thrownObjectImagiew;
    @FXML private Button resartSimuButton;
    @FXML private Button exitSimuButton;
    @FXML private AnchorPane pane5;
    @FXML private AnchorPane pane2;
    @FXML private Label angleValueLabel;

    // these exist in the FXML, keep them for injection even if we don't use them
    @FXML private Label timeFlightLabel;
    @FXML private Label maxHeightLabel;
    @FXML private Label distanceResultLabel;

    /**
     * Initializes the controller.
     * Creates planet data, starts idle animations,
     * and sets initial screen visibility.
     */
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

    /**
     * Opens the planet selection screen for a given planet.
     *
     * @param id index of the selected planet
     */
    private void openPlanetScreen(int id) {
        selectedPlanet = planets[id - 1];

        WelcomeMenuPane.setVisible(false);
        pane2.setVisible(true);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);

        planetName.setText(selectedPlanet.getName());

        Image groundImage =
                new Image(getClass().getResourceAsStream(
                        selectedPlanet.getGroundImagePath()));
        planetGround.setImage(groundImage);
        planetGroundSimulation.setImage(groundImage);

        angleValueLabel.setText(
                "Angle: " + (int) sliderangle.getValue() + "°");

        sliderangle.valueProperty().addListener((obs, o, n) ->
                angleValueLabel.setText(
                        "Angle: " + String.format("%.0f°", n.doubleValue())));
    }

    /**
     * Applies a floating animation to menu objects.
     *
     * @param img image to animate
     */
    private void floatAnimation(ImageView img) {
        TranslateTransition t =
                new TranslateTransition(Duration.seconds(1.5), img);
        t.setByY(-12);
        t.setCycleCount(Animation.INDEFINITE);
        t.setAutoReverse(true);
        t.play();
    }

    /**
     * Handles object selection and moves
     * the user to the velocity/angle screen.
     *
     * @param img image of selected object
     * @param objName object name
     */
    private void objectSelected(ImageView img, String objName) {
        selectedObjectName = objName;
        selectedObjectImage = img.getImage();

        pane2.setVisible(false);
        pane3.setVisible(true);

        objectChosenImageView.setImage(selectedObjectImage);
        objectChosenLabel.setText(objName);

        fillVelocityComboBox();
    }

    /**
     * Computes projectile motion values
     * based on physics equations.
     */
    private void computeProjectileValues() {
        double v = selectedVelocity;
        double g = selectedPlanet.getGravity();
        double angleRad = Math.toRadians(selectedAngle);

        timeOfFlight = (2 * v * Math.sin(angleRad)) / g;
        range = (v * v * Math.sin(2 * angleRad)) / g;
        maxHeight = (v * v * Math.pow(Math.sin(angleRad), 2)) / (2 * g);
    }

    @FXML void mercuryClicked(MouseEvent e) { openPlanetScreen(1); }
    @FXML void venusClicked(MouseEvent e) { openPlanetScreen(2); }
    @FXML void earthClicked(MouseEvent e) { openPlanetScreen(3); }
    @FXML void marsClicked(MouseEvent e) { openPlanetScreen(4); }
    @FXML void jupiterClicked(MouseEvent e) { openPlanetScreen(5); }
    @FXML void saturnClicked(MouseEvent e) { openPlanetScreen(6); }
    @FXML void uranusClicked(MouseEvent e) { openPlanetScreen(7); }
    @FXML void neptuneClicked(MouseEvent e) { openPlanetScreen(8); }

    /**
     * Selects the star object.
     */
    @FXML private void startClicked(MouseEvent e) {
        objectSelected(start, "Star");
    }

    /**
     * Selects the flower object.
     */
    @FXML private void flowerClicked(MouseEvent e) {
        objectSelected(flower, "Flower");
    }

    /**
     * Selects the soccer ball object.
     */
    @FXML private void soccerClicked(MouseEvent e) {
        objectSelected(soccer, "Soccer Ball");
    }

    /**
     * Selects the apple object.
     */
    @FXML private void appleClicked(MouseEvent e) {
        objectSelected(apple, "Appple");
    }

    /**
     * Returns from the configuration screen
     * back to object selection.
     */
    @FXML
    private void returnS3(ActionEvent event) {
        pane3.setVisible(false);
        pane2.setVisible(true);
        resetObjectAndControls();
    }

    /**
     * Starts the projectile simulation.
     */
    @FXML
    private void nextS3(ActionEvent event) {
        if (VelecotyComboBox.getValue() == null ||
                selectedObjectImage == null) return;

        selectedVelocity = VelecotyComboBox.getValue();
        selectedAngle = sliderangle.getValue();

        computeProjectileValues();
        startSmoothAnimation();
    }

    /**
     * Restarts the simulation without exiting.
     */
    @FXML
    private void restartButton(ActionEvent event) {
        pane5.setVisible(false);
        pane4.setVisible(true);
        resetObjectAndControls();
        thrownObjectImagiew.setLayoutX(14);
        thrownObjectImagiew.setLayoutY(244);
        thrownObjectImagiew.setRotate(0);
    }

    /**
     * Exits the application.
     */
    @FXML
    private void exitSimuButton(ActionEvent event) {
        javafx.application.Platform.exit();
    }

    /**
     * Resets selected objects and controls
     * to their default state.
     */
    private void resetObjectAndControls() {
        start.setVisible(true);
        apple.setVisible(true);
        flower.setVisible(true);
        soccer.setVisible(true);

        objectChosenImageView.setImage(null);
        objectChosenLabel.setText("");

        VelecotyComboBox.getItems().clear();
        VelecotyComboBox.setValue(null);

        sliderangle.setValue(45);
    }

    /**
     * Fills the velocity combo box based on
     * the selected planet gravity.
     */
    private void fillVelocityComboBox() {
        VelecotyComboBox.getItems().clear();
        double g = selectedPlanet.getGravity();

        if (g <= 4)
            VelecotyComboBox.getItems().addAll(0.0,5.0,10.0,15.0,20.0,25.0);
        else if (g <= 11)
            VelecotyComboBox.getItems().addAll(0.0,10.0,20.0,30.0,40.0);
        else
            VelecotyComboBox.getItems().addAll(0.0,20.0,30.0,40.0,50.0,60.0);

        VelecotyComboBox.setValue(VelecotyComboBox.getItems().get(0));
    }

    /**
     * Starts the projectile animation using a Timeline,
     * exactly as in the original version, and shows the
     * results when it hits the ground or leaves the screen.
     */
    private void startSmoothAnimation() {

        pane3.setVisible(false);
        pane4.setVisible(true);

        // Set the thrown object image
        thrownObjectImagiew.setImage(selectedObjectImage);


        double startX = 24;
        double startY = 150;
        thrownObjectImagiew.setLayoutX(startX);
        thrownObjectImagiew.setLayoutY(startY);

        // Physics
        double v = selectedVelocity;
        double g = selectedPlanet.getGravity();
        double angle = Math.toRadians(selectedAngle);

        // For time tracking
        final double[] t = {0};

        double scale = 2;

        // GROUND VALUES
        double groundTopY = 218;
        double objectHeight = 39;
        double collisionY = 300; // ground

        final Timeline[] timeline = new Timeline[1];

        timeline[0] = new Timeline(new KeyFrame(Duration.millis(16), e -> {

            t[0] += 0.016;

            // PHYSICS
            double x = v * Math.cos(angle) * t[0];
            double y = v * Math.sin(angle) * t[0] - 0.5 * g * t[0] * t[0];

            // Convert into screen coordinates
            double screenX = startX + x * scale;
            double screenY = startY - y * scale;

            thrownObjectImagiew.setLayoutX(screenX);
            thrownObjectImagiew.setLayoutY(screenY);

            // Add spin
            thrownObjectImagiew.setRotate(thrownObjectImagiew.getRotate() + 5);

            // STOP WHEN IT TOUCHES GROUND
            if (screenY >= collisionY) {
                timeline[0].stop();

                showResults();
            }

            // STOP IF OUTSIDE SCREEN (failsafe)
            if (screenX > pane4.getWidth() || screenY > pane4.getHeight()) {
                timeline[0].stop();

                showResults();
            }

        }));

        timeline[0].setCycleCount(Animation.INDEFINITE);
        timeline[0].play();

    }

    /**
     * Displays the results screen.
     */
    private void showResults() {
        pane4.setVisible(false);
        pane5.setVisible(true);
    }
}
