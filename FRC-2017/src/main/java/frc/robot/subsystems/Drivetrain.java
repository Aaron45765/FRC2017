package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.*;

public class Drivetrain extends Subsystem{
    private TalonSRX mLeftA = new TalonSRX(Constants.kLeftDriveA);
    private TalonSRX mLeftB = new TalonSRX(Constants.kLeftDriveB);
    private TalonSRX mRightA = new TalonSRX(Constants.kRightDriveA);
    private TalonSRX mRightB = new TalonSRX(Constants.kRightDriveB);

    public Drivetrain(){
        mLeftA.setSensorPhase(false);
        mRightA.setSensorPhase(false);

        mLeftA.setInverted(false);
        mLeftB.setInverted(false);
        mRightA.setInverted(true);
        mRightB.setInverted(true);

        mLeftB.follow(mLeftA);
        mRightB.follow(mLeftA);

    }

    public void initDefaultCommand(){
        setDefaultCommand(new Drive());
    }

    public void setDrivePower(double left, double right){
        mLeftA.set(ControlMode.PercentOutput, left);
        mRightA.set(ControlMode.PercentOutput, right);
    }

}