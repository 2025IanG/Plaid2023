package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DrivetrainMotors;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.WaitCommand;

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
            //DeployIntake.java
            new WaitCommand(w),
            //ExtakePiece.java
            new WaitCommand(w),
            new DriveStraight(m_drive, -1, -180)
        );
    }

    public Command MobilityEngage() {
        return new SequentialCommandGroup(
            //DeployIntake.java
            new WaitCommand(w),
            //ExtakePiece.java
            new WaitCommand(w),
            new DriveStraight(m_drive, -1, -80.125)
        );
    }

    public Command MobilityLong() {
        return new SequentialCommandGroup(
            //DeployIntake.java
            new WaitCommand(w),
            //ExtakePiece.java
            new WaitCommand(w),
            new DriveStraight(m_drive, -1, -216)
        );
    }
}