// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DT;

public class DriveForward extends Command {
  // Create Declarations
  private final DT m_Drive;
  private double distance;
  private double speed;
  /** Creates a new autoDrive. 
   *  Its a Constructor
  */
  public DriveForward(double distance, double speed, DT m_Drive) {
    this.m_Drive = m_Drive;
    this.distance = distance;
    this.speed = speed;
    addRequirements(Robot.m_DriveDT);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Drive.resetMotors();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Running Command!");
    m_Drive.tank(speed, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Command Interrupted");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // I wanna see if this works?
    return (m_Drive.getAverageEncoderDistance() >= distance);
  }
}
