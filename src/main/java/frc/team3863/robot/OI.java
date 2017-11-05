package frc.team3863.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team3863.robot.commands.ShiftHigh;
import frc.team3863.robot.commands.ShiftLow;

/**
 * Created by Aaron Fang on 11/5/2017.
 */
public class OI {
    public static Joystick throttle;
    public static Joystick direction;
    public static Button quickTurn;
    public Button highGear;
    public Button lowGear;
    public OI() {
        throttle = new Joystick(Constants.LEFT_JOYSTICK_ID);
        direction = new Joystick(Constants.RIGHT_JOYSTICK_ID);
        quickTurn = new JoystickButton(direction, 1);
        highGear.whenPressed(new ShiftHigh());
        lowGear.whenPressed(new ShiftLow());
    }
}
