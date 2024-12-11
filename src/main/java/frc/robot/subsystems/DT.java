// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

// Kaulai Labs Import (NavX)
import com.kauailabs.navx.frc.*;

// Constants import
import frc.robot.Constants.MotorIDs;
import frc.robot.Constants.DrivetrainConstants;

public class DT extends SubsystemBase {
  /** Creates a new DT. */

  // Make the NavX object
  AHRS ahrs = new AHRS(SPI.Port.kMXP);

  // Motor definitions
  private final CANSparkMax rightFront;
  private final CANSparkMax rightBack;
  private final CANSparkMax leftFront;
  private final CANSparkMax leftBack;

  
  private RelativeEncoder rightFrontEncoder;
  private RelativeEncoder leftFrontEncoder;
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

    rightBack.follow(rightFront);
    leftBack.follow(leftFront);

    // Saves configs
    rightFront.burnFlash();
    rightBack.burnFlash();

    leftFront.burnFlash();
    leftBack.burnFlash();
  }

  public void configEncoders(){
    // Note to self: Figure out how to use Encoders :P
    rightFrontEncoder = rightFront.getEncoder();
    leftFrontEncoder = leftFront.getEncoder();
    // ^ BEHOLD, AN ENCODER (Which for some reason does not like to
    // be final :( )
  }
  
  // Sets all the motors to speed 0 (so they do not move)
  public void resetMotors(){
    rightFront.set(0);
    rightBack.set(0);
    leftFront.set(0);
    leftBack.set(0);
  }
  // Debug Methods (It would be better to log these tho so I will figure
  // out how to do it)

  // Display encoder Data
  public void debugEncoders(){
    System.out.println("Right Encoder Position: " + rightFrontEncoder.getPosition());
    System.out.println("Left Encoder Position: " + leftFrontEncoder.getPosition());
  }
  // Display NavX values
  public void debugNavX(){
    System.out.println("Heading (Angle): " + ahrs.getAngle());
    System.out.println("Yaw: " + ahrs.getYaw());
    System.out.println("Pitch: " + ahrs.getPitch());
    System.out.println("Roll: " + ahrs.getRoll());
    System.out.println("Compass Data (Heading): " + ahrs.getCompassHeading());

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
    rightFront.set(rfOut*DrivetrainConstants.Multiplier);
    //rightBack.follow(rightFront);
    leftFront.set(lfOut*DrivetrainConstants.Multiplier);
    //leftBack.follow(leftFront);
  }

  //public void driveForward(){
  //  tank(1, 1);
  //}

  public double getAverageEncoderDistance(){
    return (rightFrontEncoder.getPosition() + leftFrontEncoder.getPosition()) / 2.0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Some debug code, I will remove it later on : P
    debugEncoders();
  }
}
