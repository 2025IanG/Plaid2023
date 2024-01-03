package frc.robot.commands;

import org.w3c.dom.UserDataHandler;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.DrivetrainMotors;
import frc.robot.subsystems.Drivetrain;

public class DriveStraight extends CommandBase {

	private Drivetrain m_subsystem;

	double startValLeft;
	double startValRight;
	double endValLeft;
	double endValRight;

	double vBus;
	double initialHeading;
	boolean useEncoders;
	double direction;
	double distThisLeg;

	/**
	 * <p> Simple drive straight command with only voltage
	 * @param subsystem Required Subsystem
	 * @param percentVBus Power given to motors
	 */
  	public DriveStraight(Drivetrain subsystem, double percentVBus) {

		m_subsystem = subsystem;
    	addRequirements(m_subsystem);
    	
		vBus = percentVBus;
		useEncoders = false;
    }

    /**
     * <p> Command that drives straight for distance using the TalonFX encoders.
     * @param subsystem Must be the Drivetrain subsystem.
     * @param percentVBus Power allocated to motors (sign does not matter).
     * @param inches Uses encoders to determine how far in inches the robot should go.
     */
    public DriveStraight(Drivetrain subsystem, double percentVBus, double inches) {
    	
		m_subsystem = subsystem;
		addRequirements(m_subsystem);

    	vBus = Math.abs(percentVBus) * Math.signum(inches);

		m_subsystem.resetEncoders();

		startValLeft = m_subsystem.getLeftEncoderPosition(0);
		startValRight = m_subsystem.getRightEncoderPosition(0);
		endValLeft = startValLeft + m_subsystem.inchesToNativeUnits(inches);
    	endValRight = startValRight + m_subsystem.inchesToNativeUnits(inches);

		useEncoders = true;
    	
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	// set our target position as current position plus desired distance
    	// get the robot's current direction, so we can stay pointed that way
    	initialHeading = m_subsystem.getGyroYaw();

		SmartDashboard.putNumber("Start DriveStraight Left Val:", startValLeft);
		SmartDashboard.putNumber("End DriveStraight Left Val:", endValLeft);
		SmartDashboard.putNumber("Start DriveStraight Right Val:", startValRight);
		SmartDashboard.putNumber("End DriveStraight Right Val:", endValRight);
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
		//kP_gyroDriveStraight later
    	double diversion = (m_subsystem.getGyroRate());
		SmartDashboard.putNumber("Diversion", diversion);
    	double leftVal = vBus;
		double rightVal = vBus;
		
		if (leftVal > 0 && rightVal > 0) {
			if (diversion < 0) {
				m_subsystem.tankDrive(
					leftVal,
					rightVal - 1 * diversion
				);
			} else {
					m_subsystem.tankDrive(
					leftVal + 1 * diversion,
					rightVal
				);
			}	
		} else if (leftVal < 0 && rightVal < 0) {
			if (diversion > 0) {
				m_subsystem.tankDrive(
					leftVal,
					rightVal - 1 * diversion
				);
			} else {
					m_subsystem.tankDrive(
					leftVal + 1 * diversion,
					rightVal
				);
			}
		}

			

		SmartDashboard.putNumber("Current DriveStraight Right Val", m_subsystem.getRightEncoderPosition(0));
		SmartDashboard.putNumber("Current DriveStraight Left Val", m_subsystem.getLeftEncoderPosition(0));
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	if(useEncoders) {
    		// have we gone far enough?
    		if(vBus < 0) {
				SmartDashboard.putString("Reverse DriveStraight:", "end");
    			return m_subsystem.getLeftEncoderPosition(0) <= endValLeft && m_subsystem.getRightEncoderPosition(0) <= endValRight;
    		} else {
				SmartDashboard.putString("Forward DriveStraight:", "end");
    			return m_subsystem.getLeftEncoderPosition(0) >= endValLeft && m_subsystem.getRightEncoderPosition(0) >= endValRight;
    		}
    	} else {
			return false;
		}
		
		// if(useEncoders) {
		// 	if (Math.abs(m_subsystem.getRightEncoderPosition(0)) > 2048 || Math.abs(m_subsystem.getLeftEncoderPosition(0)) > 2048) {
		// 		return true;
		// 	} else {
		// 		return false;
		// 	}
		// } else {
		// 	return false;
		// }
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	m_subsystem.stopMotors();
		m_subsystem.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}