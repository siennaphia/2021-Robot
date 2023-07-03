package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import com.analog.adis16470.frc.ADIS16470_IMU;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class RobotMap {
    public static CANSparkMax RF = new CANSparkMax(1, MotorType.kBrushless);
    public static CANSparkMax RB = new CANSparkMax(2, MotorType.kBrushless);
    public static CANSparkMax LF = new CANSparkMax(3, MotorType.kBrushless);
    public static CANSparkMax LB = new CANSparkMax(4, MotorType.kBrushless);
    public static CANSparkMax upperShooter = new CANSparkMax(8, MotorType.kBrushless);
    public static CANSparkMax lowerShooter = new CANSparkMax(9, MotorType.kBrushless);
    public static VictorSPX shooterLift = new VictorSPX(10);
    public static VictorSPX intake = new VictorSPX(7);
    public static VictorSPX serializerLeft = new VictorSPX(5);
    public static VictorSPX serializerRight = new VictorSPX(6);
    // public static final ADIS16470_IMU imu = new ADIS16470_IMU();
    // public static VictorSPX FRMotor = new VictorSPX(1);
    // public static VictorSPX BLMotor = new VictorSPX(2);
    // public static VictorSPX BRMotor = new VictorSPX(3);
    // public static VictorSPX FLMotor = new VictorSPX(4);
    // public static VictorSPX midMotor = new VictorSPX(5);
    // public static VictorSPX rightShooter = new VictorSPX(6);
    // public static VictorSPX loadMotor = new VictorSPX(7);
    // public static VictorSPX leftShooter = new VictorSPX(8);
}