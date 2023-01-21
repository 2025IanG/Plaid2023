package frc.robot.commands;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;


public class DeployIntake extends CommandBase {
    private final Intake m_intake;
    public DeployIntake(Intake subsystem) {
        m_intake = subsystem;
        addRequirements(m_intake);

    }
    public void initialize() {

    }
    @Override
    public void execute() {
        m_intake.setIntakeSolinoid(Value.kForward);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.setIntakeSolinoid(Value.kOff);
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