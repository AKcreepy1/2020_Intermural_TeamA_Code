/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.utils.RobotMap;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.Shooter;


public class Robot extends TimedRobot {

  //---------------------Controllers-------------------------
  XboxController omnicientController = new XboxController(0);

  //------------RobotMap-------------
  RobotMap omnitientRobotMap = new RobotMap();

  //----------------------------------Subsystems--------------------------------------
  DriveTrain driveTrain = new DriveTrain(omnicientController);
  Shooter shooter = new Shooter(omnicientController);


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
  public void autonomousInit() {
    driveTrain.autonomousInit();
    shooter.autonomousInit();
  }

  @Override
  public void autonomousPeriodic() {
    driveTrain.autonomousPeriodic();
    shooter.autonomousPeriodic();
  }

  @Override
  public void teleopInit() {
    driveTrain.teleopInit();
    shooter.teleopInit();
  }

  @Override
  public void teleopPeriodic() {
    driveTrain.teleopPeriodic();
    shooter.teleopPeriodic();
  }

  @Override
  public void disabledInit() {
    driveTrain.disabledInit();
    shooter.disabledInit();
  }

  @Override
  public void disabledPeriodic() {
    driveTrain.disabledPeriodic();
    shooter.disabledPeriodic();
  }

  @Override
  public void testInit() {
    driveTrain.testInit();
    shooter.testInit();
  }

  @Override
  public void testPeriodic() {
    driveTrain.testPeriodic();
    shooter.testPeriodic();
  }
}
