// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Drive;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.DT;
//import frc.robot.subsystems.DT;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
//import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import edu.wpi.first.wpilibj2.command.button.Trigger;

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

  //private final DT mDrive;
  // Declare The Controller object
  private final CommandPS4Controller m_driverController =
      new CommandPS4Controller(OperatorConstants.kDriverControllerPort);

  // BOI THIS IS LITERALY A CONSTRUCTER WDYM IT STORES STUFF
  public RobotContainer() {
    // Configure Commands
   
    //mDrive = new DT();
    //m_Drive.execute();
    // Configure the trigger bindings
   
    Robot.m_DriveDT.setDefaultCommand(new Drive(Robot.m_DriveDT, m_driverController));

    configureBindings();
  }

  private void configureBindings() {
    // Runs the "Shooter thing" *it just prints something when the triangle button is pressed
    //m_driverController.triangle().whileTrue(Commands.runOnce(()-> {m_Shoot.execute();}));
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
