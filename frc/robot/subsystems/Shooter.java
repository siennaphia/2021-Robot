package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem to control shooter and loader
*/
public class Shooter extends Subsystem {
  
  public void shoot(double shooterPower) {
    RobotMap.lowerShooter.set(shooterPower);
    RobotMap.upperShooter.set(shooterPower);
  }

  public void shooterLift(double speed){
    RobotMap.shooterLift.set(ControlMode.PercentOutput,speed);
  }

  @Override
  public void initDefaultCommand() {}
}