package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.ClimberBackward;
import frc.robot.commands.ClimberForward;
import frc.robot.commands.StartShooter;

public class OI{
    Joystick driverController = new Joystick(Constants.kGamepadID);
    Button climberButton = new JoystickButton(driverController, Constants.kClimberButtonID);
    Button shooterButton = new JoystickButton(driverController, Constants.kShooterButtonID);
    public OI(){
        shooterButton.whenPressed(new StartShooter());
        climberButton.whenPressed(new ClimberForward());        
    }

    public double getDriveThrottle(){
        return driverController.getRawAxis(Constants.kDriveForwardAxisID);
    }

    public double getDriveSteering(){
        return driverController.getRawAxis(Constants.kTurningAxisID);
    }
}