
package org.usfirst.frc.team1757.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	CANTalon talon, talon2;
	TeamDrive liftTeam;
	Potentiometer stringPot;
	WinchBase Winch;
	Joystick gamepad;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        gamepad = new Joystick(0);
    	
    	talon = new CANTalon(1);
        talon2 = new CANTalon(2);
        talon.setInverted(true);
        
        Winch = new DirectWinch(talon, talon2);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	while ((isEnabled()) && (isOperatorControl())) {    		
    		SmartDashboard.putNumber("Output", Winch.getM_output());
    		SmartDashboard.putBoolean("Is Running", Winch.isM_isRunning());
    		
    		Winch.enable();
    		
    		if (gamepad.getRawButton(2)) /*Button A*/ {
    			if (Winch.isM_isRunning())
    				Winch.disable();
    			else
    				Winch.enable();
    		}
    		
    		if (gamepad.getRawButton(3)) /*Button B*/ {
    			Winch.recordAverageData();
    			break;
    		}
    		
    		if (gamepad.getRawButton(1)) /*Button X*/ {
    			Winch.recordAverageData();
    			break;
    		}
    		
    		if (gamepad.getRawButton(4)) /*Button Y*/ {
    			Winch.recordAverageData();
    			break;
    		}
    		
    		if (gamepad.getRawButton(10)) /*Button Start*/ {
    			Winch.stop();
    		}
    		
    		if (gamepad.getRawButton(8)) /*Button RT*/ {
    			Winch.changeOutput(.001D);;
    		}
    		
    		if (gamepad.getRawButton(7)) /*Button LT*/ {
    			Winch.changeOutput(-.001D);
    		}
    		
    		Winch.run();
    	}
    	Winch.disable();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
