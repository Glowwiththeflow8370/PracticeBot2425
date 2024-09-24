// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Drive;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.DT;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

// Redundant Classes/Pkgs that could be added later:
//import edu.wpi.first.wpilibj.PS4Controller.Axis;
//import frc.robot.subsystems.Shooter;
//import frc.robot.commands.Autos;

/*
This is where most of the Robot code is stored *You actually do not have to edit much of the
Robot.java code : P
 */
public class RobotContainer {
  // Declaring Subsystems/Commands, 
  //Im doing this so the declarations can be accessed later!
  private final Shoot m_Shoot;
  private final Drive m_Drive;
  private final DT s_Drive;

  // Declare The Controller object
  private final CommandPS4Controller m_driverController =
      new CommandPS4Controller(OperatorConstants.kDriverControllerPort);

  // BOI THIS IS LITERALY A CONSTRUCTER WDYM IT STORES STUFF
  public RobotContainer() {
    // Configure Subsystems
    s_Drive = new DT();
    // Configure Commands
    m_Shoot = new Shoot();
    m_Drive = new Drive(s_Drive, m_driverController);
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    
    // Runs the "Shooter thing" *it just prints something when the triangle button is pressed
    m_driverController.triangle().whileTrue(Commands.runOnce(()-> {m_Shoot.execute();}));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Commands.runOnce(()->{m_Drive.autoDrive();});
  }
}
