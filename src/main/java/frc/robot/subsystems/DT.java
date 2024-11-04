// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

// Constants import
import frc.robot.Constants.MotorIDs;
import frc.robot.Constants.DrivetrainConstants;

public class DT extends SubsystemBase {
  /** Creates a new DT. */

  // Motor definitions
  private final CANSparkMax rightFront;
  private final CANSparkMax rightBack;
  private final CANSparkMax leftFront;
  private final CANSparkMax leftBack;

  private RelativeEncoder rightFrontEncoder;
  //private final DifferentialDrive m_Drive;

  public DT() {
    // Create motor objects
    rightFront = new CANSparkMax(MotorIDs.k_RightFrontMotorPort, MotorType.kBrushless);
    rightBack = new CANSparkMax(MotorIDs.k_RightBackMotorPort, MotorType.kBrushless);
    leftFront = new CANSparkMax(MotorIDs.k_LeftFrontMotorPort, MotorType.kBrushless);
    leftBack = new CANSparkMax(MotorIDs.k_LeftBackMotorPort, MotorType.kBrushless);
    
    // Im putting everything into methods because I am lazy and wish not
    // to deal with a wall of code in the constructor : )
    // Thanki for the understandings
    configMotors();
    configEncoders();
  }

  public void configMotors(){
    // Invert output of left motors
    leftFront.setInverted(true);
    leftBack.setInverted(true); 

    rightFront.setSmartCurrentLimit(40);
    rightBack.setSmartCurrentLimit(40);

    leftFront.setSmartCurrentLimit(40);
    leftBack.setSmartCurrentLimit(40);

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

  public void configEncoders(){
    // Note to self: Figure out how to use Encoders :P
    rightFrontEncoder = rightFront.getEncoder();
    // ^ BEHOLD, AN ENCODER (Which for some reason does not like to
    // be final :( )
  }
  
  // Drive method
  // This is most likely redundant?
  // public void tank(double rfOut, double lfOut, double rbOut, double lbOut) {
  //   rightFront.set(rfOut*2);
  //   rightBack.set(lfOut*2);
  //   leftFront.set(rbOut*2);
  //   leftBack.set(lbOut*2);
  // }

  public void tank(double rfOut, double lfOut) {
    // In theory this should have the same function as the
    // Above code, however, it requires less parameters
    // Therefore simplifying its declaration
    rightFront.set(rfOut*2);
    rightBack.follow(rightFront);
    leftFront.set(lfOut*1);
    leftBack.follow(leftFront);
  }

  public void driveForward(){
    tank(1, 1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Stops the motors?
    //System.out.println("Doing checks");
  }
}
