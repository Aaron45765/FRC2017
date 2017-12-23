package team3863.robot;

/**
 * Created by Aaron Fang on 11/5/2017.
 */
public class Constants {
    public static final int leftDriveA_ID = 3;
    public static final int leftDriveB_ID = 15;
    public static final int rightDriveA_ID = 2;
    public static final int rightDriveB_ID = 12;

    public static final int rightDrive_Enc = 20;
    public static final int leftDrive_Enc = 20;

    public static final int climberA_ID = 13;
    public static final int climberB_ID = 14;

    public static final int WHEEL_SIZE = 6; //in
    public static final int DRIVER_JOYSTICK_ID = 0;
    public static final int PARTNER_JOYSTICK_ID = 2;
    public static final int HIGH_GEAR_ID = 0;
    public static final int LOW_GEAR_ID = 1;

    public static final int shooterA_ID = 0;
    public static final int shooterB_ID = 1;

    public static final int feeder_ID = 7;
    public static final int intake_ID = 6;
    public static final int blender_ID = 4;
    public static final int hood_ID = 5;

    public static final int GATE_OPEN_ID = 3;
    public static final int GATE_CLOSED_ID = 2;

    public static final int CAMERA_HORIZONTAL_FOV_DEGREES = 60;
    public static final double CAMERA_HORIZONTAL_FOV_RADIANS = (CAMERA_HORIZONTAL_FOV_DEGREES * 2*Math.PI)/360;
    public static final int CAMERA_HORIZONTAL_PIXELS = 640;

    public static final double DRIVETRAIN_TRACK_WIDTH = 30; //inches
    public static final double DRIVETRAIN_TRACK_LENGTH = 40; //inches
    public static final double DRIVETRAIN_RADIUS = Math.sqrt(Math.pow(DRIVETRAIN_TRACK_LENGTH,2) + Math.pow(DRIVETRAIN_TRACK_WIDTH, 2));
    public static final double DRIVETRAIN_WHEEL_DIAMETER = 6;
    public static final double DRIVETRAIN_WHEEL_CIRCUM = DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
    public static final int DRIVETRAIN_ENC_PER_REV = 20;
    public static final double DRIVETRAIN_HIGH_SPEED = 1;
    public static final double DRIVETRAIN_LOW_SPEED = 1;
}
