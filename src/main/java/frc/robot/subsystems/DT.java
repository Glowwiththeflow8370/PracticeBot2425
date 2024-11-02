// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.beans.Encoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
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

  //private final DifferentialDrive m_Drive;

  public DT() {
    // Create motor objects
    rightFront = new CANSparkMax(MotorIDs.k_RightFrontMotorPort, MotorType.kBrushless);
    rightBack = new CANSparkMax(MotorIDs.k_RightBackMotorPort, MotorType.kBrushless);
    leftFront = new CANSparkMax(MotorIDs.k_LeftFrontMotorPort, MotorType.kBrushless);
    leftBack = new CANSparkMax(MotorIDs.k_LeftBackMotorPort, MotorType.kBrushless);
   
    // Invert output of left motors
    leftFront.setInverted(true);
    leftBack.setInverted(true); 

    rightFront.setSmartCurrentLimit(80);
    rightBack.setSmartCurrentLimit(80);

    leftFront.setSmartCurrentLimit(80);
    leftBack.setSmartCurrentLimit(80);

    // Set idle mode
    rightFront.setIdleMode(IdleMode.kCoast);
    rightBack.setIdleMode(IdleMode.kCoast);
    leftFront.setIdleMode(IdleMode.kCoast);
    leftBack.setIdleMode(IdleMode.kCoast);
    // Saves configs
    rightFront.burnFlash();
    rightBack.burnFlash();

    leftFront.burnFlash();
    leftBack.burnFlash();
  }
  // Drive method


  // This is most likely redundant?
  public void tank(double rfOut, double lfOut, double rbOut, double lbOut) {
    rightFront.set(rfOut*2);
    rightBack.set(lfOut*2);
    leftFront.set(rbOut*2);
    leftBack.set(lbOut*2);
  }

  public void driveForward(){
    tank(1, 1, 1, 1);
  }
  // Note to self: Figure out how to use Encoders :P

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Stops the motors?
    //System.out.println("Doing checks");
  }
}
