package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants.DrivetrainMotors;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class AutoPaths extends CommandBase{
    private Drivetrain m_drive;
    private Intake m_intake;
    private double w = 0.25;

    public AutoPaths(Drivetrain drive, Intake intake) {
        m_drive = drive;
        m_intake = intake;
    }

    public Command MobilityShort() {
        return new SequentialCommandGroup(
            new InstantCommand(m_drive::setBrakesOn, m_drive),
            new WaitCommand(w),
            new ExtakePiece(m_intake).withTimeout(1.5),
            new WaitCommand(w),
            new DriveStraight(m_drive, 0.2, 100 * 2 + (1+100/100)*8)
        );
    }

    public Command MobilityEngage() {
        return new SequentialCommandGroup(
            new InstantCommand(m_drive::setBrakesOn, m_drive),
            new InstantCommand(m_drive::initGyro, m_drive),
            new WaitCommand(w),
            new ExtakePiece(m_intake).withTimeout(1.5),
            new WaitCommand(w),
            new DriveStraight(m_drive, 0.175, 90 * 2 + (1+90/100)*8),
            new WaitCommand(w),
            new GyroEngage(m_drive)
        );
    }

    public Command MobilityLong() {
        return new SequentialCommandGroup(
            new InstantCommand(m_drive::setBrakesOn, m_drive),
            new WaitCommand(w),
            new ExtakePiece(m_intake).withTimeout(1.5),
            new WaitCommand(w),
            new DriveStraight(m_drive, 0.2, 190 * 2 + (1+190/100)*8)
        );
    }
}