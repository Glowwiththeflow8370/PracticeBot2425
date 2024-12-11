// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */

  // This is a test subsystem
  // While it could be used for a shooter later
  // Right now imma make it send an output when a button is pressed

  // Output button thing
  String output;
  public Shooter() {
    output = "Shooter Engaged";
  }

  public void Shoot(){
    System.out.println(output);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // No u
  }
}
