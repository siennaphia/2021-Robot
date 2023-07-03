package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem to control shooter and loader
*/
public class Serializer extends Subsystem {
  
  public void normal(double serializerPower) {
    RobotMap.serializerLeft.set(ControlMode.PercentOutput, -serializerPower);
    RobotMap.serializerRight.set(ControlMode.PercentOutput, serializerPower);
  }

  public void invert(double serializerPower){
    RobotMap.serializerLeft.set(ControlMode.PercentOutput, serializerPower);
    RobotMap.serializerRight.set(ControlMode.PercentOutput, serializerPower);
  }

  @Override
  public void initDefaultCommand() {}
}