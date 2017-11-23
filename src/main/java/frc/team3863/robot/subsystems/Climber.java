package frc.team3863.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team3863.robot.Constants;

/**
 * Created by Aaron Fang on 11/22/2017.
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private CANTalon climberA, climberB;

    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

    public Climber(){
        climberA = new CANTalon(Constants.climberA_ID);
        climberB = new CANTalon(Constants.climberB_ID);
        climberA.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        climberB.changeControlMode(CANTalon.TalonControlMode.Follower);
        climberB.set(climberA.getDeviceID());
        climberB.reverseOutput(true);

        climberA.enableBrakeMode(true);
        climberB.enableBrakeMode(true);
    }

    public void setPower(double set){
        climberA.set(set);
    }
}

