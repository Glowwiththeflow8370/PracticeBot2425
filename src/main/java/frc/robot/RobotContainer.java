// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Drive;
import frc.robot.commands.Shoot;
import frc.robot.commands.DriveForward;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

/*
This is where most of the Robot code is stored *You actually do not have to edit much of the
Robot.java code : P
 */
public class RobotContainer {
  // Declaring Subsystems/Commands, 
  //Im doing this so the declarations can be accessed later!

  // Declare The Controller object
  private final CommandPS4Controller m_driverController =
      new CommandPS4Controller(OperatorConstants.kDriverControllerPort);
  
  // Create Commands
  private final Shoot m_Shoot;
  Drive m_Drive;
  private final Command DriveForward;
  // Create a Selectable chooser (for autonomous)
  SendableChooser<Command> AutoChooser = new SendableChooser<>();

  // BOI THIS IS LITERALY A CONSTRUCTER WDYM IT STORES STUFF
  public RobotContainer() {
    // Configure Commands, this one is the command that allows for the robot
    // to be controlled by a controller
    Robot.m_DriveDT.setDefaultCommand(new Drive(Robot.m_DriveDT, m_driverController));
    // Configure Some more Commands
    m_Shoot = new Shoot();
    DriveForward = new DriveForward(3, 1, Robot.m_DriveDT);

    // This should be for the Auton Stuff (Just need to make it work)
    AutoChooser.setDefaultOption("Drive Forward", DriveForward);
    // This second one wont work : P
    AutoChooser.addOption("Shoot", m_Shoot);
    SmartDashboard.putData(AutoChooser);

    configureBindings();
  }

  private void configureBindings() {
    // Configure Trigger Bindings
    // Runs the "Shooter thing" *it just prints something when the triangle button is pressed
    m_driverController.triangle().whileTrue(Commands.runOnce(()-> {m_Shoot.execute();}));
  }

  public Command getAutonomousCommand() {
    // Whatever command is chosen will get run
    return AutoChooser.getSelected();
  }
}