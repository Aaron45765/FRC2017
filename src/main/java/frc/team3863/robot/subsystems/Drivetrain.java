package frc.team3863.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team3863.robot.Constants;
import frc.team3863.robot.OI;
import frc.team3863.robot.commands.Drive;

import static frc.team3863.robot.Robot.cheesyDriveHelper;

/**
 * Created by Aaron Fang on 11/5/2017.
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    CANTalon rightA;
    CANTalon rightB;
    CANTalon leftA;
    CANTalon leftB;

    CANTalon.TalonControlMode controlMode;

    DoubleSolenoid shifter;
    boolean inHigh;

    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new Drive(cheesyDriveHelper.cheesyDrive(OI.throttle.getY(), OI.direction.getX(), OI.quickTurn.get(), isHigh())));
    }

    public Drivetrain(){
        rightA = new CANTalon(Constants.rightDriveA_ID);
        rightB = new CANTalon(Constants.rightDriveB_ID);
        leftA = new CANTalon(Constants.leftDriveA_ID);
        leftB = new CANTalon(Constants.leftDriveB_ID);

        controlMode = CANTalon.TalonControlMode.PercentVbus;

        rightA.changeControlMode(controlMode);
        rightB.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightB.set(rightA.getDeviceID());

        leftA.changeControlMode(controlMode);
        leftB.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftB.set(leftA.getDeviceID());

        //rightA.configEncoderCodesPerRev(Constants.rightDrive_Enc);
        //leftA.configEncoderCodesPerRev(Constants.leftDrive_Enc);

        shifter = new DoubleSolenoid(Constants.HIGH_GEAR_ID, Constants.LOW_GEAR_ID);
    }

    public void setOpenLoop(){
        controlMode = CANTalon.TalonControlMode.PercentVbus;
        rightA.changeControlMode(controlMode);
        leftA.changeControlMode(controlMode);
    }

    public void setClosedLoopSpeed(){
        controlMode = CANTalon.TalonControlMode.Speed;
        rightA.changeControlMode(controlMode);
        leftA.changeControlMode(controlMode);
    }

    public void setClosedLoopPosition(){
        controlMode = CANTalon.TalonControlMode.Position;
        rightA.changeControlMode(controlMode);
        leftA.changeControlMode(controlMode);
    }

    public void setRight(double set){
        rightA.set(set);
    }

    public void setLeft(double set){
        leftA.set(set);
    }

    public int getRightEnc(){
        return rightA.getEncPosition();
    }

    public int getLeftEnc(){
        return leftA.getEncPosition();
    }

    public double getRightSpeed(){
        return rightA.getSpeed();
    }

    public double getLeftSpeed(){
        return leftA.getSpeed();
    }

    public void setHigh(){
        shifter.set(DoubleSolenoid.Value.kForward);
        inHigh = true;
    }

    public void setLow(){
        shifter.set(DoubleSolenoid.Value.kReverse);
        inHigh = false;
    }

    public boolean isHigh(){
        return inHigh;
    }

    public void clearEncoders(){
        rightA.setEncPosition(0);
        leftA.setEncPosition(0);
    }
}

