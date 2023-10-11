// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final Intake m_intake = new Intake();
    public final Drivetrain m_drivetrain = new Drivetrain();

// Joysticks
//do not use subStick or driveStick variables.
//it will cause problems. (don't know why)
private static final XboxController subStick = new XboxController(1);
private static final XboxController driveStick = new XboxController(0);
private final JoystickButton driveA = new JoystickButton(new XboxController(0), 1);
private final JoystickButton driveB = new JoystickButton(new XboxController(0), 2);
private final JoystickButton driveX = new JoystickButton(new XboxController(0), 3);
private final JoystickButton driveY = new JoystickButton(new XboxController(0), 4);
private final JoystickButton driveLB = new JoystickButton(new XboxController(0), 5);
private final JoystickButton driveRB = new JoystickButton(new XboxController(0), 6);
private final JoystickButton driveBack = new JoystickButton(new XboxController(0), 7);
private final JoystickButton subA = new JoystickButton(new XboxController(1), 1);
private final JoystickButton subB = new JoystickButton(new XboxController(1), 2);
private final JoystickButton subX = new JoystickButton(new XboxController(1), 3);
private final JoystickButton subY = new JoystickButton(new XboxController(1), 4);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  //SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    //SmartDashboard.putData("Autonomous Command", new AutoPaths());
    SmartDashboard.putData("DefaultDrivetrain", new ArcadeDriveLimited( m_drivetrain ));
    SmartDashboard.putData("Defaultintake", new DefaultIntake( m_intake ));

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    //m_chooser.setDefaultOption("Autonomous Command", new AutoPaths());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    //SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    subA.whileTrue(
      new IntakePiece(m_intake)
    );

    subB.whileTrue(
      new ExtakePiece(m_intake)
    );

    subX.onTrue(
      new StowIntake(m_intake)
    );

    subY.onTrue(
      new DeployIntake(m_intake)
    );

    driveRB.onTrue(
      new InstantCommand(m_drivetrain::setBrakesOn, m_drivetrain)
    );

    driveLB.onTrue(
      new InstantCommand(m_drivetrain::setBrakesOff, m_drivetrain)
    );

    // driveA.whileTrue(
    //   new IntakePiece(m_intake)
    // );

    // driveB.whileTrue(
    //   new ExtakePiece(m_intake)
    // );

    // driveX.onTrue(
    //   new StowIntake(m_intake)
    // );

    // driveY.onTrue(
    //   new DeployIntake(m_intake)
    // );

    driveBack.whileTrue(
      new GyroEngage(m_drivetrain)
    );
  }

  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(new ArcadeDriveLimited(m_drivetrain));
    //m_intake.setDefaultCommand(new Defaultintake(m_intake));
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public static XboxController getdriveStick() {
      return driveStick;
    }

public static XboxController getsubStick() {
      return subStick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand(String a) {
    //return m_chooser.getSelected();

    //Establish AutoPaths command
    AutoPaths p = new AutoPaths(m_drivetrain, m_intake);

    //Default autonomous command
    Command autoCommand = new SequentialCommandGroup(p.MobilityLong());

    //Autonomous options
    switch(a) {
      case "b1A":
        //Short Shot auto option
        autoCommand = new SequentialCommandGroup(p.MobilityShort());
        break;
      case "b2B":
        //Engage Charge Station auto option
        autoCommand = new SequentialCommandGroup(p.MobilityEngage());
        break;
      case "b3C":
        //Long Drive auto option
        autoCommand = new SequentialCommandGroup(p.MobilityLong());
        break;
    }

    return autoCommand;
  }
}