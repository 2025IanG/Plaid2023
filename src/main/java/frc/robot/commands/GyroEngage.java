package frc.robot.commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import  frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroEngage extends CommandBase{

    private Drivetrain m_subsystem;
    double maxAngle = -88.5;
    double minAngle = -91.5;
    double currentAngle;

    public GyroEngage(Drivetrain subsystem) {
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    } 

    @Override
    public void initialize() {
        m_subsystem.resetGyro();
    }

    @Override
    public void execute() {
        currentAngle = m_subsystem.getGyroPitch();
        SmartDashboard.putNumber("Current Pitch", currentAngle);
        SmartDashboard.putNumber("Current Roll", m_subsystem.getGyroRoll());
        SmartDashboard.putNumber("Current Yaw", m_subsystem.getGyroYaw());
        if (currentAngle > maxAngle) {
            if (((maxAngle-currentAngle)/100) < -0.09) {
                m_subsystem.tankDrive(-0.09, -0.09);
            } else {
                m_subsystem.tankDrive(((maxAngle-currentAngle)/100), ((maxAngle-currentAngle)/100));
            }
        } else if (currentAngle < minAngle) {
            if (((minAngle-currentAngle)/100) > 0.09) {
                m_subsystem.tankDrive(0.09, 0.09);
            } else {
                m_subsystem.tankDrive(((minAngle-currentAngle)/100), ((minAngle-currentAngle)/100));
            }
        } else if (currentAngle >= minAngle && currentAngle <= maxAngle) {
            m_subsystem.stopMotors();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
