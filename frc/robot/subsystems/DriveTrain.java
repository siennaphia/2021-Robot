package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import frc.robot.RobotMap;
import java.lang.Math;

import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

public class DriveTrain extends Subsystem {
    MecanumDriveKinematics m_kinematics;
    MecanumDriveOdometry m_odometry;
    private CANPIDController frontLeftPIDController;
    private CANPIDController frontRightPIDController;
    private CANPIDController backLeftPIDController;
    private CANPIDController backRightPIDController;
    private double kP;
    private double kI;
    private double kD;
    private double kIz;
    private double kFF;
    private double kMaxOutput;
    private double kMinOutput;
    private double maxRPM;
    MecanumDriveWheelSpeeds wheelSpeeds = new MecanumDriveWheelSpeeds(0,0,0,0);
    double maxVal = 0;
    public DriveTrain() {
        RobotMap.LF.setIdleMode(IdleMode.kBrake);
        RobotMap.RF.setIdleMode(IdleMode.kBrake);
        RobotMap.LB.setIdleMode(IdleMode.kBrake);
        RobotMap.RB.setIdleMode(IdleMode.kBrake);
        RobotMap.RB.setSmartCurrentLimit(40);
        RobotMap.RF.setSmartCurrentLimit(40);
        RobotMap.LB.setSmartCurrentLimit(40);
        RobotMap.LF.setSmartCurrentLimit(40);
        // frontLeftPIDController = RobotMap.LF.getPIDController();
        // frontRightPIDController = RobotMap.RF.getPIDController();
        // backLeftPIDController = RobotMap.LB.getPIDController();
        // backRightPIDController = RobotMap.RB.getPIDController();
        // RobotMap.LF.getEncoder();
        // RobotMap.RF.getEncoder();
        // RobotMap.LB.getEncoder();
        // RobotMap.RB.getEncoder();
        // kP = 9e-2; 
        // kI = 5e-4;
        // kD = 6e-7; 
        // kIz = .7; 
        // kFF = 0.000015; 
        // kMaxOutput = .5; 
        // kMinOutput = -.5;
        // maxRPM = 500;
        // frontLeftPIDController.setP(kP);
        // frontLeftPIDController.setI(kI);
        // frontLeftPIDController.setD(kD);
        // frontLeftPIDController.setIZone(kIz);
        // frontLeftPIDController.setFF(kFF);
        // frontLeftPIDController.setOutputRange(kMinOutput, kMaxOutput);
        // frontRightPIDController.setP(kP);
        // frontRightPIDController.setI(kI);
        // frontRightPIDController.setD(kD);
        // frontRightPIDController.setIZone(kIz);
        // frontRightPIDController.setFF(kFF);
        // frontRightPIDController.setOutputRange(kMinOutput, kMaxOutput);
        // backLeftPIDController.setP(kP);
        // backLeftPIDController.setI(kI);
        // backLeftPIDController.setD(kD);
        // backLeftPIDController.setIZone(kIz);
        // backLeftPIDController.setFF(kFF);
        // backLeftPIDController.setOutputRange(kMinOutput, kMaxOutput);
        // backRightPIDController.setP(kP);
        // backRightPIDController.setI(kI);
        // backRightPIDController.setD(kD);
        // backRightPIDController.setIZone(kIz);
        // backRightPIDController.setFF(kFF);
        // backRightPIDController.setOutputRange(kMinOutput, kMaxOutput);
    }

    // public void setSpeed(double setPoint) {
    //     frontLeftPIDController.setReference(setPoint, ControlType.kVelocity);
    //     frontRightPIDController.setReference(setPoint, ControlType.kVelocity);
    //     backLeftPIDController.setReference(setPoint, ControlType.kVelocity);
    //     backRightPIDController.setReference(setPoint, ControlType.kVelocity);
    // }
    // public void moveDistance(double distanceRotations) {
    //     frontLeftPIDController.setReference(distanceRotations, ControlType.kPosition);
    //     frontRightPIDController.setReference(distanceRotations, ControlType.kPosition);
    //     backLeftPIDController.setReference(distanceRotations, ControlType.kPosition);
    //     backRightPIDController.setReference(distanceRotations, ControlType.kPosition);
    // }
    // public void getDistances(){
    //     System.out.println(RobotMap.LF.getEncoder().getPosition());
    //     System.out.println(RobotMap.RF.getEncoder().getPosition());
    //     System.out.println(RobotMap.LB.getEncoder().getPosition());
    //     System.out.println(RobotMap.RB.getEncoder().getPosition());
    // }
    
    public void drive(double forwardSpeed, double leftSpeed, double turnSpeed){
        if(Math.abs(forwardSpeed)>1){
            forwardSpeed = 1 *Math.signum(forwardSpeed);
        }
        if(Math.abs(leftSpeed)>1){
            leftSpeed = 1 *Math.signum(leftSpeed);
        }
        if(Math.abs(turnSpeed)>0.75){
            turnSpeed = 0.9 *Math.signum(turnSpeed);
        }
        // System.out.println(forwardSpeed);
        // System.out.println(leftSpeed);
        // System.out.println(turnSpeed);
        
        ChassisSpeeds speeds = new ChassisSpeeds(forwardSpeed, leftSpeed, turnSpeed);

        // Convert to wheel 1speeds
        wheelSpeeds = m_kinematics.toWheelSpeeds(speeds);
        
        // Get the individual wheel speeds

        double frontLeft = forwardSpeed - leftSpeed - turnSpeed;
        double frontRight = forwardSpeed + leftSpeed + turnSpeed;
        double backLeft = forwardSpeed + leftSpeed - turnSpeed;
        double backRight = forwardSpeed - leftSpeed + turnSpeed;
        
        if(Math.abs(frontLeft)>maxVal){
            maxVal = frontLeft;
        }
        if(Math.abs(frontRight)>maxVal){
            maxVal = frontRight;
        }
        if(Math.abs(backLeft)>maxVal){
            maxVal = backLeft;
        }
        if(Math.abs(backRight)>maxVal){
            maxVal = backRight;
        }
        if(Math.abs(maxVal)>1){
            maxVal=Math.abs(1/maxVal);
            frontLeft = frontLeft*maxVal;
            frontRight = frontRight*maxVal;
            backLeft = backLeft*maxVal;
            backRight = backRight*maxVal;
        }
        RobotMap.LF.set(frontLeft);
        RobotMap.RF.set(-frontRight);
        RobotMap.LB.set(backLeft);
        RobotMap.RB.set(-backRight);
    }
    // public Rotation2d getGyroHeading(){
    //     Rotation2d rot = Rotation2d.fromDegrees(-RobotMap.imu.getAngle());
    //     return rot;
    // }

    // public void updatePos(){
    //     MecanumDriveWheelSpeeds modifiedWheelSpeeds = new MecanumDriveWheelSpeeds(
    //         RobotMap.LF.getEncoder().getVelocity()*0.039878, RobotMap.RF.getEncoder().getVelocity()*0.039878,
    //         RobotMap.LB.getEncoder().getVelocity()*0.039878, RobotMap.RB.getEncoder().getVelocity()*0.039878);
    //     // m_odometry.update(getGyroHeading(), modifiedWheelSpeeds);
    //     System.out.println(m_odometry.getPoseMeters());
    // }

    @Override
    protected void initDefaultCommand() {
        Translation2d m_frontLeftLocation = new Translation2d(0.288, 0.264);
        Translation2d m_frontRightLocation = new Translation2d(0.288, -0.264);
        Translation2d m_backLeftLocation = new Translation2d(-0.288, 0.264);
        Translation2d m_backRightLocation = new Translation2d(-0.288, -0.264);

        // Creating my kinematics object using the wheel locations.
        m_kinematics = new MecanumDriveKinematics(m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation);
        // m_odometry = new MecanumDriveOdometry(m_kinematics, getGyroHeading(), new Pose2d(5.0, 13.5, new Rotation2d()));
    }

}