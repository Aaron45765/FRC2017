package team3863.robot.subsystems;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import lib.util.PIDConstant;
import team3863.robot.Constants;
import team3863.robot.commands.CheesyDrive;

/**
 * Created by Aaron Fang on 11/5/2017.
 */
public class Drivetrain extends Subsystem implements PIDOutput {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    CANTalon rightA;
    CANTalon rightB;
    CANTalon leftA;
    CANTalon leftB;

    CANTalon.TalonControlMode controlMode;

    DoubleSolenoid shifter;
    boolean inHigh;

    AHRS gyro;

    private PIDConstant right_low_constants = new PIDConstant(0.0, 0.0, 0.0, 0.0);
    private PIDConstant right_high_constants = new PIDConstant(0.0, 0.0, 0.0, 0.0);
    private PIDConstant left_low_constants = new PIDConstant(0.0, 0.0, 0.0, 0.0);
    private PIDConstant left_high_constants = new PIDConstant(0.0, 0.0, 0.0, 0.0);


    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new CheesyDrive());
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

        rightA.configEncoderCodesPerRev(Constants.rightDrive_Enc);
        leftA.configEncoderCodesPerRev(Constants.leftDrive_Enc);

        rightA.setVoltageRampRate(10.0);
        leftA.setVoltageRampRate(10.0);

        rightA.setInverted(true);
        leftA.setInverted(false);

        shifter = new DoubleSolenoid(Constants.HIGH_GEAR_ID, Constants.LOW_GEAR_ID);


        rightA.setProfile(0);
        leftA.setProfile(0);
        rightA.setPID(right_low_constants.getkP(), right_low_constants.getkI(), right_low_constants.getkD());
        rightA.setF(right_low_constants.getkF());
        leftA.setPID(left_low_constants.getkP(), left_low_constants.getkI(), left_low_constants.getkD());
        leftA.setF(left_low_constants.getkF());

        rightA.setProfile(1);
        leftA.setProfile(1);
        rightA.setPID(right_high_constants.getkP(), right_high_constants.getkI(), right_high_constants.getkD());
        rightA.setF(right_high_constants.getkF());
        leftA.setPID(left_high_constants.getkP(), left_high_constants.getkI(), left_high_constants.getkD());
        leftA.setF(left_high_constants.getkF());

        setLow();
        gyro = new AHRS(SerialPort.Port.kUSB);

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
        rightA.setProfile(1);
        leftA.setProfile(1);
        shifter.set(DoubleSolenoid.Value.kForward);
        inHigh = true;
    }

    public void setLow(){
        rightA.setProfile(0);
        leftA.setProfile(0);
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

    public double getAngle(){
        return gyro.getAngle();
    }

    public AHRS getGyro(){
        return gyro;
    }

    public void pidWrite(double value){
        setRight(value);
        setLeft(-value);
    }

    //where vel is from 0.0-1.0
    public void setDriveVel(double leftVel, double rightVel){
        if(controlMode.equals(CANTalon.TalonControlMode.Speed)) {
            if ((Math.abs(leftVel * Constants.DRIVETRAIN_HIGH_SPEED) > Constants.DRIVETRAIN_LOW_SPEED) || (Math.abs(rightVel * Constants.DRIVETRAIN_HIGH_SPEED) > Constants.DRIVETRAIN_LOW_SPEED)) {
                setHigh();
                setRight(rightVel);
                setLeft(leftVel);
            } else {
                setLow();
                setRight(rightVel);
                setLeft(leftVel);
            }
        }
        else{
            setRight(rightVel);
            setLeft(leftVel);
        }

    }

}

