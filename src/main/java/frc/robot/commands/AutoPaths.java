package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DrivetrainMotors;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class AutoPaths extends CommandBase{
    private Drivetrain m_drive;
    private Intake m_intake;

    public AutoPaths(Drivetrain drive, Intake intake) {
        m_drive = drive;
        m_intake = intake;
    }

    public Command Taxi() {
        return new SequentialCommandGroup(
            new DriveStraight(m_drive, 0)
        );
    }
}
