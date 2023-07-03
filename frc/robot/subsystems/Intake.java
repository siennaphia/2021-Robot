package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem to control shooter and loader
*/
public class Intake extends Subsystem {
  private double currentIntakeSpeed = 0f;
  public void intake(double intakeSpeed) {
    if(currentIntakeSpeed==0){
      currentIntakeSpeed = intakeSpeed;
      RobotMap.intake.set(ControlMode.PercentOutput, currentIntakeSpeed);
    }else{
      currentIntakeSpeed = 0;
      RobotMap.intake.set(ControlMode.PercentOutput, currentIntakeSpeed);
    }
    
  }

  @Override
  public void initDefaultCommand() {}
}