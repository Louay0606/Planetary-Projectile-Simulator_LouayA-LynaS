/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import javafx.scene.image.Image;


public class Planet {

    private String name;
    private double gravity;   // m/s^2
    private String groundImagePath;
    private int id;           // 1 = Mercury, 2 = Venus, etc.

    public Planet(int id, String name, double gravity, String groundImagePath) {
        this.id = id;
        this.name = name;
        this.gravity = gravity;
        this.groundImagePath = groundImagePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGravity() {
        return gravity;
    }

    public Image getGroundImage() {
        return new Image(groundImagePath);
    }
}
