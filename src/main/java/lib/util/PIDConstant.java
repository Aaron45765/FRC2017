package lib.util;

/**
 * Created by Aaron Fang on 12/2/2017.
 */
public class PIDConstant {
    private double kP, kI, kD, kF;

    public PIDConstant(double kP, double kI, double kD, double kF){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }


    public PIDConstant(double kP, double kI, double kD){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;

    }

    public double getkP() {
        return kP;
    }

    public double getkI() {
        return kI;
    }

    public double getkD() {
        return kD;
    }

    public double getkF() {
        return kF;
    }

    }
