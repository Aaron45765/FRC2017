package frc.team3863.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import frc.team3863.robot.Constants;
import lib.util.DriveSignal;
import edu.wpi.first.wpilibj.PIDController;

import static frc.team3863.robot.Robot.drivetrain;

/**
 * Created by Aaron Fang on 11/18/2017.
 */
public class AutoAim extends Command {
    NetworkTable table;
    PIDController rotationControl;
    double p = 0.0;
    double i = 0.0;
    double d = 0.0;
    public AutoAim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivetrain);
        table = NetworkTable.getTable("GRIP/myContoursReport");
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    protected void initialize() {
        drivetrain.setOpenLoop();
        rotationControl = new PIDController(p, i, d, drivetrain.getGyro(), drivetrain);
        rotationControl.setInputRange(-180, 180);
        rotationControl.setOutputRange(-1.0, 1.0);
        rotationControl.setAbsoluteTolerance(1.0);
        rotationControl.enable();
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    protected void execute() {
        rotationControl.setSetpoint(drivetrain.getAngle() + getOffsetDegrees());
    }


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return rotationControl.onTarget();
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    protected void end() {
        drivetrain.setOpenLoop();
        rotationControl.disable();
    }


    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    protected void interrupted() {
        super.interrupted();
    }

    private double getOffsetDegrees(){
        double radiansPerPixel = (double)Constants.CAMERA_HORIZONTAL_FOV_DEGREES*(double)Constants.CAMERA_HORIZONTAL_PIXELS;
        double boilerLocation = table.getNumber("boilerX", -1.0);
        boilerLocation -= Constants.CAMERA_HORIZONTAL_PIXELS/2;
        if (boilerLocation != -1.0){
            double offsetDegrees = (radiansPerPixel*boilerLocation);
            return offsetDegrees;
        }
        else{
            return 0.0;
        }
    }

}
