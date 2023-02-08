package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.Intake;

public class StowIntake extends CommandBase {

    private final Intake m_intake;

    public StowIntake(Intake subsystem) {
        m_intake = subsystem;

        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_intake.setIntakeSolenoid(Value.kForward);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.setIntakeSolenoid(Value.kOff);
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
