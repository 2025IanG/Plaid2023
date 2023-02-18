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
            new DeployIntake(m_intake),
            new WaitCommand(w),
            new ExtakePiece(m_intake).withTimeout(3),
            new WaitCommand(w),
            new DriveStraight(m_drive, -1, -180)
        );
    }

    public Command MobilityEngage() {
        return new SequentialCommandGroup(
            new InstantCommand(m_drive::setBrakesOn, m_drive),
            new WaitCommand(w),
            new DeployIntake(m_intake).withTimeout(0.1),
            new WaitCommand(w),
            new ExtakePiece(m_intake).withTimeout(1.5),
            new WaitCommand(w),
            new DriveStraight(m_drive, 0.25, 336).withTimeout(5)
            //new DriveStraight(m_drive, 0.25).withTimeout(2)
        );
    }

    public Command MobilityEngageTest() {
        return new SequentialCommandGroup(
            new InstantCommand(m_drive::setBrakesOn, m_drive),
            new WaitCommand(w),
            new DeployIntake(m_intake).withTimeout(0.1),
            new WaitCommand(w),
            new ExtakePiece(m_intake).withTimeout(1.5),
            new WaitCommand(w),
            new DriveStraight(m_drive, 0.25, 842),
            // new DriveStraight(m_drive, 0.25).withTimeout(3),
            new WaitCommand(w),
            new DriveStraight(m_drive, 0.25, -275),
            new WaitCommand(w),
            new DriveStraight(m_drive, 0.25, 0)
        );
    }

    public Command MobilityLong() {
        return new SequentialCommandGroup(
            new DeployIntake(m_intake),
            new WaitCommand(w),
            new ExtakePiece(m_intake).withTimeout(3),
            new WaitCommand(w),
            new DriveStraight(m_drive, -1, -216)
        );
    }
}