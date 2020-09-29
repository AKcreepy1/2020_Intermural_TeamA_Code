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
import frc.robot.Subsystems.TurnTable;


public class Robot extends TimedRobot {

  DriveTrain driveTrain = new DriveTrain();
  Shooter shooter = new Shooter();
  TurnTable turnTable = new TurnTable();


  @Override
  public void robotInit() {
    driveTrain.robotInit();
    shooter.robotInit();
    turnTable.robotInit();
  }

  @Override
  public void robotPeriodic() {
    driveTrain.robotPeriodic();
    shooter.robotPeriodic();
    turnTable.robotPeriodic();
  }

  @Override
  public void autonomousInit() {
    driveTrain.autonomousInit();
    shooter.autonomousInit();
    turnTable.autonomousInit();
  }

  @Override
  public void autonomousPeriodic() {
    driveTrain.autonomousPeriodic();
    shooter.autonomousPeriodic();
    turnTable.autonomousPeriodic();
  }

  @Override
  public void teleopInit() {
    driveTrain.teleopInit();
    shooter.teleopInit();
    turnTable.teleopInit();
  }

  @Override
  public void teleopPeriodic() {
    driveTrain.teleopPeriodic();
    shooter.teleopPeriodic();
    turnTable.teleopPeriodic();
  }

  @Override
  public void disabledInit() {
    driveTrain.disabledInit();
    shooter.disabledInit();
    turnTable.disabledInit();
  }

  @Override
  public void disabledPeriodic() {
    driveTrain.disabledPeriodic();
    shooter.disabledPeriodic();
    turnTable.disabledPeriodic();
  }

  @Override
  public void testInit() {
    driveTrain.testInit();
    shooter.testInit();
    turnTable.testInit();
  }

  @Override
  public void testPeriodic() {
    driveTrain.testPeriodic();
    shooter.testPeriodic();
    turnTable.testPeriodic();
  }
}
