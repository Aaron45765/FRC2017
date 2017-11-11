package frc.team3863.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team3863.robot.Constants;


/**
 * Created by Aaron Fang on 11/10/2017.
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private CANTalon shooterA, shooterB, feeder, intake, hood, blender;
    private final double shooterA_P = 0.0;
    private final double shooterA_I = 0.0;
    private final double shooterA_D = 0.0;
    private final double shooterA_F = 0.0;

    private final double shooterB_P = 0.0;
    private final double shooterB_I = 0.0;
    private final double shooterB_D = 0.0;
    private final double shooterB_F = 0.0;

    private final double hood_P = 0.0;
    private final double hood_I = 0.0;
    private final double hood_D = 0.0;

    private DoubleSolenoid gate;

    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

    public Shooter(){
        shooterA = new CANTalon(Constants.shooterA_ID);
        shooterB = new CANTalon(Constants.shooterB_ID);
        feeder = new CANTalon(Constants.feeder_ID);
        intake = new CANTalon(Constants.intake_ID);
        hood = new CANTalon(Constants.hood_ID);
        blender = new CANTalon(Constants.blender_ID);

        shooterA.configEncoderCodesPerRev(20);
        shooterB.configEncoderCodesPerRev(20);
        shooterA.setPID(shooterA_P, shooterA_I, shooterA_D);
        shooterA.setF(shooterA_F);

        shooterB.setPID(shooterB_P, shooterB_I, shooterB_D);
        shooterB.setF(shooterB_F);

        shooterA.changeControlMode(CANTalon.TalonControlMode.Speed);
        shooterB.changeControlMode(CANTalon.TalonControlMode.Speed);

        hood.setPID(hood_P, hood_I, hood_D);
        hood.changeControlMode(CANTalon.TalonControlMode.Position);

        gate = new DoubleSolenoid(Constants.GATE_OPEN_ID, Constants.GATE_CLOSED_ID);
    }

    public void setShooterA(double rpm){
        shooterA.set(rpm);
    }

    public void setShooterB(double rpm){
        shooterB.set(rpm);
    }

    public void setFeeder(double set){
        feeder.set(set);
    }

    public void setIntake(double set){
        intake.set(set);
    }

    public void setBlender(double set){
        blender.set(set);
    }

    public void setHoodAngle(){

    }

    public void openGate(){
        gate.set(DoubleSolenoid.Value.kForward);
    }

    public void closeGate(){
        gate.set(DoubleSolenoid.Value.kReverse);
    }
}

