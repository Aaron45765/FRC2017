package team3863.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import team3863.robot.commands.*;

/**
 * Created by Aaron Fang on 11/5/2017.
 * Configured to use a DualShock 4 Controller as the primary driver's controller.
 */
public class OI {
    public static Joystick driver;
    public static Joystick partner;
    public static Button quickTurn;
    public static Button highGear;
    public static Button lowGear;
    public static Button shoot;
    public static Button increaseSpeed;
    public static Button decreaseSpeed;
    public static Button disableShooter;
    public static Button climber;

    public OI() {
        driver = new Joystick(Constants.DRIVER_JOYSTICK_ID);
        partner = new Joystick(Constants.PARTNER_JOYSTICK_ID);

        highGear = new JoystickButton(driver, 5);
        lowGear = new JoystickButton(driver, 6);

        shoot = new JoystickButton(partner, 4);
        disableShooter = new JoystickButton(partner, 1);
        increaseSpeed = new JoystickButton(partner, 0);
        decreaseSpeed = new JoystickButton(partner, 0);
        climber = new JoystickButton(partner, 0);

        highGear.whenPressed(new ShiftHigh());
        lowGear.whenPressed(new ShiftLow());
        shoot.whenPressed(new ShootHigh());
        disableShooter.whenPressed(new DisableShooter());
        increaseSpeed.whenPressed(new IncreaseShooterSpeed(100));
        decreaseSpeed.whenPressed(new DecreaseShooterSpeed(100));
        climber.whenPressed(new Climb());
    }

    public static double getForwardThrottle(){
        double input = -driver.getRawAxis(1);
        double sign = Math.signum(input);
        return sign * Math.pow(input, 2);
    }

    public static double getTurningThrottle(){
        double input = driver.getRawAxis(2);
        double sign = Math.signum(input);
        double forwardThrottleSign = Math.signum(getForwardThrottle());
        if(forwardThrottleSign == -1)
            return sign * Math.pow(input, 2);
        else
            return -sign * Math.pow(input, 2);
    }

    public static boolean isQuickTurn(){
        return Math.abs(getForwardThrottle()) < 0.1;
    }
}
