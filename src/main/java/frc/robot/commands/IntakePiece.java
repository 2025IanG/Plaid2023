package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakePiece extends CommandBase {

    private final Intake m_intake;

    public IntakePiece(Intake subsystem) {

         m_intake = subsystem;
        addRequirements(m_intake);
    
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_intake.setIntakeMotor(0.75);
    }
    
    @Override
    public void end(boolean interrupted) {
        m_intake.stopIntakeMotor();
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}