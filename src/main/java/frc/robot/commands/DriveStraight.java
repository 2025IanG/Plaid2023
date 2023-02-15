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
	 * @desc Simple drive straight command with only voltage
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

		startValLeft = m_subsystem.getLeftEncoderPosition(0);
		startValRight = m_subsystem.getRightEncoderPosition(0);
		endValLeft = startValLeft + m_subsystem.inchesToNativeUnits(inches);
    	endValRight = startValRight - m_subsystem.inchesToNativeUnits(inches);

		useEncoders = true;
    	
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	// set our target position as current position plus desired distance
    	// get the robot's current direction, so we can stay pointed that way
    	initialHeading = m_subsystem.getGyroYaw();

		SmartDashboard.putNumber("Start DriveStraight Val:", startValLeft);
		SmartDashboard.putNumber("End DriveStraight Val:", endValLeft);
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	double proportion = DrivetrainMotors.kP_gyroDriveStraight * (m_subsystem.getGyroYaw() - initialHeading);
    	double leftVal = 1 * vBus;
		double rightVal = 1 * vBus;
		
		m_subsystem.tankDrive(leftVal - proportion, rightVal + proportion);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	if(useEncoders) {
    		// have we gone far enough?
    		if(Math.signum(vBus) < 0) {
				SmartDashboard.putString("Reverse DriveStraight:", "end");
    			return m_subsystem.getLeftEncoderPosition(0) <= endValLeft || m_subsystem.getRightEncoderPosition(0) >= endValRight;
    		} else {
				SmartDashboard.putString("Forward DriveStraight:", "end");
    			return m_subsystem.getLeftEncoderPosition(0) >= endValLeft || m_subsystem.getRightEncoderPosition(0) <= endValRight;
    		}
    	}

		return false;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	m_subsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}