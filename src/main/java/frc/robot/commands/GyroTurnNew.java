package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DrivetrainMotors;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroTurnNew extends CommandBase {

    private Drivetrain m_subsystem;
    private double targetAngle;
    private double speed;
    private double errorMargin;
    private double speedAdjust;

    public GyroTurnNew(Drivetrain subsystem, double targetHeading, double speed, double marginOfError) {

        m_subsystem = subsystem;
        addRequirements(subsystem);

        targetAngle = targetHeading;
        this.speed = speed;
        errorMargin = marginOfError;

    }

    public void initialize() {
        m_subsystem.resetGyro();
    }

    public void execute() {

        speedAdjust = (Math.abs(targetAngle) - Math.abs(m_subsystem.getGyroYaw())) * DrivetrainMotors.kP_turn;

        double leftWheelMove = (targetAngle * speed) / Math.abs(targetAngle);
        double rightWheelMove = (-1 * targetAngle * speed) / Math.abs(targetAngle);

        m_subsystem.tankDrive(leftWheelMove * speedAdjust, rightWheelMove * speedAdjust);

        SmartDashboard.putNumber("Gyro Value: ", m_subsystem.getGyroYaw());

    }

    public boolean isFinished() {
        return Math.abs(m_subsystem.getGyroYaw()) > (Math.abs(targetAngle) - errorMargin);
    }

    public void end() {
        m_subsystem.stopMotors();
    }
    
}
