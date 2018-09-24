/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX mLeft = new TalonSRX(Constants.kLeftClimber);
  private TalonSRX mRight = new TalonSRX(Constants.kRightClimber);

  public Shooter(){
    mLeft.setInverted(true);
    mRight.setInverted(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setRightPower(double pow){
    mRight.set(ControlMode.PercentOutput, pow);
  }

  public void setLeftPower(double pow){
    mLeft.set(ControlMode.PercentOutput, pow);
  }
}
