// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

// Constants import
import frc.robot.Constants.MotorIDs;

public class DT extends SubsystemBase {
  /** Creates a new DT. */

  // Motor definitions
  private final CANSparkMax rightFront;
  private final CANSparkMax rightBack;
  private final CANSparkMax leftFront;
  private final CANSparkMax leftBack;

  private final DifferentialDrive m_Drive;

  public DT() {
    // Create motor objects
    rightFront = new CANSparkMax(MotorIDs.k_RightFrontMotorPort, MotorType.kBrushless);
    rightBack = new CANSparkMax(MotorIDs.k_RightBackMotorPort, MotorType.kBrushless);
    leftFront = new CANSparkMax(MotorIDs.k_LeftFrontMotorPort, MotorType.kBrushless);
    leftBack = new CANSparkMax(MotorIDs.k_LeftBackMotorPort, MotorType.kBrushless);

    // Invert output of left motors
    leftFront.setInverted(true);
    leftBack.setInverted(true); 

    // Group motor objects into Differential Drive
    m_Drive = new DifferentialDrive(
      // Sets the right motors
      (double output) -> 
      {rightFront.set(output);
      rightBack.set(output);}, 
      // Sets the left motors
      (double output) -> 
      {leftFront.set(output);
      leftBack.set(output);});
  }

  // Drive method
  public void arcadeDrive(double contX, double contY){
      m_Drive.arcadeDrive(contX, contY);
  }

  // This is most likely redundant?
  public void resetMotors(){
    m_Drive.tankDrive(0,0);
  }

  // Note to self: Figure out how to use Encoders :P

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Stops the motors?
    System.out.println("Doing checks");

    /*
    // Add these if nessecary
    rightFront.set(0);
    rightBack.set(0);
    leftFront.set(0);
    leftBack.set(0);
     */
  }
}
