package controller;

import Model.Planet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Comprehensive physics test suite for ViewController.
 * Uses reflection to inject private values and call private methods.
 */
public class ViewControllerPhysicsTest {

   

    /** Sets a private field inside an object. */
    private void setField(Object obj, String fieldName, Object value) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(obj, value);
    }

    /** Gets a private field from an object. */
    private Object getField(Object obj, String fieldName) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return f.get(obj);
    }

    /** Calls a private method. */
    private Object callMethod(Object obj, String methodName) throws Exception {
        Method m = obj.getClass().getDeclaredMethod(methodName);
        m.setAccessible(true);
        return m.invoke(obj);
    }

    
    @Test
    public void test_Earth_45deg_20ms() throws Exception {
        ViewController vc = new ViewController();
        Planet earth = new Planet(3, "Earth", 9.81, "/View/planet3.png");

        setField(vc, "selectedPlanet", earth);
        setField(vc, "selectedVelocity", 20.0);
        setField(vc, "selectedAngle", 45.0);

        callMethod(vc, "computeProjectileValues");

        assertEquals(40.77, (double) getField(vc, "timeOfFlight"), 0.5);
        assertEquals(20.39, (double) getField(vc, "maxHeight"), 0.5);
        assertEquals(40.77, (double) getField(vc, "range"), 1.0);
    }

    @Test
    public void test_Mars_30deg_50ms() throws Exception {
        ViewController vc = new ViewController();
        Planet mars = new Planet(4, "Mars", 3.71, "/View/mars.png");

        setField(vc, "selectedPlanet", mars);
        setField(vc, "selectedVelocity", 50.0);
        setField(vc, "selectedAngle", 30.0);

        callMethod(vc, "computeProjectileValues");

        double tf = (double) getField(vc, "timeOfFlight");
        double h = (double) getField(vc, "maxHeight");
        double r = (double) getField(vc, "range");

        assertTrue(tf > 30 && tf < 35);
        assertTrue(h > 150 && h < 170);
        assertTrue(r > 380 && r < 420);
    }

 

    @Test
    public void test_Jupiter_90deg_StraightUp() throws Exception {
        ViewController vc = new ViewController();
        Planet jupiter = new Planet(5, "Jupiter", 24.79, "/View/jupiter.png");

        setField(vc, "selectedPlanet", jupiter);
        setField(vc, "selectedVelocity", 30.0);
        setField(vc, "selectedAngle", 90.0);

        callMethod(vc, "computeProjectileValues");

        assertEquals(0.0, (double) getField(vc, "range"), 0.1);
        assertTrue((double) getField(vc, "maxHeight") > 10);
    }

    @Test
    public void test_ZeroGravity_CrazyValues() throws Exception {
        ViewController vc = new ViewController();
        Planet zeroG = new Planet(0, "Zero", 0.0001, "/View/planet1.png");

        setField(vc, "selectedPlanet", zeroG);
        setField(vc, "selectedVelocity", 10.0);
        setField(vc, "selectedAngle", 45.0);

        callMethod(vc, "computeProjectileValues");

        double tf = (double) getField(vc, "timeOfFlight");
        double r = (double) getField(vc, "range");

        assertTrue(tf > 10000);
        assertTrue(r > 10000);
    }

    @Test
    public void test_ObjectSelection_DoesNotBreakPhysics() throws Exception {
        ViewController vc = new ViewController();
        Planet earth = new Planet(3, "Earth", 9.81, "/View/planet3.png");

        setField(vc, "selectedPlanet", earth);
        setField(vc, "selectedObjectName", "Soccer Ball");
        setField(vc, "selectedVelocity", 25.0);
        setField(vc, "selectedAngle", 40.0);

        callMethod(vc, "computeProjectileValues");

        assertTrue((double) getField(vc, "range") > 30);
    }

    @Test
    public void test_InvalidAngle_Negative_ShouldStillRun() throws Exception {
        ViewController vc = new ViewController();
        Planet earth = new Planet(3, "Earth", 9.81, "/View/earth.png");

        setField(vc, "selectedPlanet", earth);
        setField(vc, "selectedVelocity", 10.0);
        setField(vc, "selectedAngle", -20.0);

        callMethod(vc, "computeProjectileValues");

        assertTrue((double) getField(vc, "range") >= 0);
    }

    @Test
    public void test_InvalidVelocity_Zero_ShouldGiveZeroRange() throws Exception {
        ViewController vc = new ViewController();
        Planet earth = new Planet(3, "Earth", 9.81, "/View/earth.png");

        setField(vc, "selectedPlanet", earth);
        setField(vc, "selectedVelocity", 0.0);
        setField(vc, "selectedAngle", 45.0);

        callMethod(vc, "computeProjectileValues");

        assertEquals(0.0, (double) getField(vc, "range"), 0.1);
    }
}
