// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.XboxController; // In case we are using an xbox controller
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystems.DT;
import frc.robot.Robot;

public class Drive extends Command {
  /** Creates a new Drive. */
  private final DT m_Drive;
  private final CommandPS4Controller dController;

  Timer m_Timer = new Timer();
  double axis1;
  double axis2;

  public Drive(DT m_Drive, CommandPS4Controller dController) {
    this.m_Drive = m_Drive;
    this.dController = dController;
    // Use addRequirements() here to declare subsystem dependencies.

    SmartDashboard.putNumber("X axis", axis2);
    SmartDashboard.putNumber("Y axis", axis1);

    addRequirements(Robot.m_DriveDT);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Drive Command Started");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_Drive.arcadeDrive(-dController.getLeftY(), dController.getRightX());
    // Sets error margin to combat stick drift
    axis1 = MathUtil.applyDeadband(dController.getLeftY(), 0.25);
    axis2 = MathUtil.applyDeadband(-dController.getRightX(), 0.25, 0.50);
    //m_Drive.tank((axis1 - axis2), (axis1 - axis2), (axis1 + axis2), (axis1 + axis2));
    // Hopefully this works lol
    m_Drive.tank((axis1 - axis2), (axis1 + axis2));

    // Some more debug stuff (to be put on smart dash)
    SmartDashboard.putNumber("X axis", axis2);
    SmartDashboard.putNumber("Y axis", axis1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
