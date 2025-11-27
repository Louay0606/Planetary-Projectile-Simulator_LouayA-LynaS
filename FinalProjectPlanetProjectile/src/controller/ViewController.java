package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import Model.Planet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
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
    private ComboBox<?> VelecotyComboBox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
         
        floatAnimation(start);
        floatAnimation(apple);
        floatAnimation(flower);
        floatAnimation(soccer);
        
        WelcomeMenuPane.setVisible(true);
        pane3.setVisible(false);
        pane4.setVisible(false);


    }    
    
    private void openPlanetScreen(int id) {
        
        selectedPlanet = planets[id - 1];

        
        WelcomeMenuPane.setVisible(false);
        pane3.setVisible(true);

        planetName.setText(selectedPlanet.getName());

        Image groundImage = new Image(
                getClass().getResourceAsStream(selectedPlanet.getGroundImagePath())
        );
        planetGround.setImage(groundImage);
        planetGroundSimulation.setImage(groundImage); // same ground in simulation

        resetObjectAndControls();
    }

    /**
     * create an animation for the second scene where we see the object 
     * slightly moving up and down
     * @param img ( flower , soccer , star and apple imageviews)
     */
    private void floatAnimation(ImageView img) {
    TranslateTransition t = new TranslateTransition(Duration.seconds(1.5), img);
    t.setByY(-12);
    t.setCycleCount(Animation.INDEFINITE);
    t.setAutoReverse(true);
    t.play();
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
    }

    @FXML
    private void flowerClicked(MouseEvent event) {
    }

    @FXML
    private void soccerClicked(MouseEvent event) {
    }

    @FXML
    private void appleClicked(MouseEvent event) {
    }

    @FXML
    private void returnS3(ActionEvent event) {
    }

    @FXML
    private void nextS3(ActionEvent event) {
    }

    @FXML
    private void restartButton(ActionEvent event) {
    }

    @FXML
    private void exitSimuButton(ActionEvent event) {
    }

    private void resetObjectAndControls() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
