package team3863.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import team3863.robot.commands.*;

/**
 * Created by Aaron Fang on 11/5/2017.
 */
public class OI {
    public static Joystick throttle;
    public static Joystick direction;
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
        throttle = new Joystick(Constants.LEFT_JOYSTICK_ID);
        direction = new Joystick(Constants.RIGHT_JOYSTICK_ID);
        partner = new Joystick(Constants.PARTNER_JOYSTICK_ID);
        quickTurn = new JoystickButton(partner, 12);
        highGear = new JoystickButton(direction, 1);
        lowGear = new JoystickButton(throttle, 1);
        shoot = new JoystickButton(partner, 0);
        disableShooter = new JoystickButton(partner, 0);
        increaseSpeed = new JoystickButton(partner, 0);
        decreaseSpeed = new JoystickButton(partner, 0);
        climber = new JoystickButton(partner, 4);
        highGear.whenPressed(new ShiftHigh());
        lowGear.whenPressed(new ShiftLow());
        shoot.whenPressed(new ShootHigh());
        disableShooter.whenPressed(new DisableShooter());
        increaseSpeed.whenPressed(new IncreaseShooterSpeed(100));
        decreaseSpeed.whenPressed(new DecreaseShooterSpeed(100));
        climber.whenPressed(new Climb());
    }

    public static double getForwardThrottle(){
        double sign = Math.signum(partner.getY());
        return sign * Math.pow(partner.getY(), 2);
    }

    public static double getTurningThrottle(){
        double sign = Math.signum(partner.getZ());
        double forwardThrottleSign = Math.signum(getForwardThrottle());
        if(forwardThrottleSign == -1)
            return sign * Math.pow(partner.getZ(), 2);
        else
            return -sign * Math.pow(partner.getZ(), 2);
    }
}
