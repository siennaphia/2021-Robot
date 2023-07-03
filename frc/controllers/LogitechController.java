/**
 * Controller class for the driver
 * Instantiates buttons to facilitate writing code for attaching buttons to commands
 * 
 * Built for Logitech Gamepad F310 on X mode
 */

package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class LogitechController {
    
    private Joystick m_stick;
    private final static double deadband = .08;

    public JoystickButton aButton;
    public JoystickButton bButton;
    public JoystickButton xButton;
    public JoystickButton yButton;
    private boolean aPressed;
    private boolean bPressed;
    private boolean xPressed;
    private boolean yPressed;
    private boolean leftBumperPressed;
    private boolean rightBumperPressed;
    private int prevVal;
    public JoystickButton leftBumperButton;
    public JoystickButton rightBumperButton;
    public JoystickButton backButton;
    public JoystickButton startButton;
    public JoystickButton leftJoystickButton;
    public JoystickButton rightJoystickButton;

    public LogitechController(int port) {
        m_stick = new Joystick(port);

        aButton = new JoystickButton(m_stick, 1);
        bButton = new JoystickButton(m_stick, 2);
        xButton = new JoystickButton(m_stick, 3);
        yButton = new JoystickButton(m_stick, 4);
        aPressed = false;
        bPressed = false;
        xPressed = false;
        yPressed = false;
        prevVal = -1;     
        leftBumperButton = new JoystickButton(m_stick, 5);
        rightBumperButton = new JoystickButton(m_stick, 6);
        backButton = new JoystickButton(m_stick, 7);
        startButton = new JoystickButton(m_stick, 8);
        leftJoystickButton = new JoystickButton(m_stick, 9);
        rightJoystickButton = new JoystickButton(m_stick, 10);
    }

    public boolean getLeftBumper(){
        leftBumperPressed = leftBumperButton.get();
        return leftBumperPressed;
    }

    public boolean getRightBumper(){
        rightBumperPressed = rightBumperButton.get();
        return rightBumperPressed;
    }

    public double getLeftXAxis() {
        double val = m_stick.getRawAxis(0);
        return handleDeadband(val);
    }

    public double getLeftYAxis() {
        double val = m_stick.getRawAxis(1);
        return handleDeadband(val);
    }

    public double getRightXAxis() {
        double val = m_stick.getRawAxis(4);
        return handleDeadband(val);
    }

    public double getRightYAxis() {
        double val = m_stick.getRawAxis(5);
        return handleDeadband(val);
    }

    public double getRightTrigger() {
        double val = m_stick.getRawAxis(2);
        return handleDeadband(val);
    }

    public double getLeftTrigger() {
        double val = m_stick.getRawAxis(3);
        return handleDeadband(val);
    }

    public int getDPad() {
        int val = m_stick.getPOV();
        prevVal = val;
        return(val);
    }
    public int getDPadPress(){
        if(prevVal == getDPad()){
            return(-1);
        }else{
            return(prevVal);
        }
    }
    private double handleDeadband(double num) {
        if(Math.abs(num) < deadband) {
            return 0;
        }

        return num;
    }
    
    public boolean getA(){
        aPressed = aButton.get();
        return aPressed;
    }
    public boolean getAPress(){
        if(aPressed == true){
            getA();
            return(false);
        }else{
            if(getA() == true){
                return(true);
            }
            return(false);
        }
    }

    public boolean getB(){
        bPressed = bButton.get();
        return bPressed;
    }
    public boolean getBPress(){
        if(bPressed == true){
            getB();
            return(false);
        }else{
            if(getB() == true){
                return(true);
            }
            return(false);
        }
    }
    
    public boolean getX(){
        xPressed = xButton.get();
        return xPressed;
    }
    public boolean getXPress(){
        if(xPressed == true){
            getX();
            return(false);
        }else{
            if(getX() == true){
                return(true);
            }
            return(false);
        }
    }
    
    public boolean getY(){
        yPressed = yButton.get();
        return yPressed;
    }
    public boolean getYPress(){
        if(yPressed == true){
            getY();
            return(false);
        }else{
            if(getY() == true){
                return(true);
            }
            return(false);
        }
    }
    

}