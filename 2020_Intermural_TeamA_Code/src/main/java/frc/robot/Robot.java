/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.Shooter;


public class Robot extends TimedRobot {

  //----------------------------------Subsystems--------------------------------------
  DriveTrain driveTrain = new DriveTrain();
  Shooter shooter = new Shooter();


  @Override
  public void robotInit() {
    driveTrain.robotInit();
    shooter.robotInit();
  }

  @Override
  public void robotPeriodic() {
    driveTrain.robotPeriodic();
    shooter.robotPeriodic();
  }

  @Override
  public void teleopPeriodic() {
    driveTrain.teleopPeriodic();
    shooter.teleopPeriodic();
  }

  @Override
  public void disabledPeriodic() {
    driveTrain.disabledPeriodic();
    shooter.disabledPeriodic();
  }
}
