/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.K_constants.ControllerMap;
import frc.robot.K_constants.RobotMap;

public class DriveTrain extends TimedRobot {

  double controllerXAxis;
  double controllerYAxis;

  double leftSidePower;
  double rightSidePower;

  XboxController controller;

  TalonSRX frMasterTalon;
  TalonSRX flMasterTalon;
  VictorSPX brFollowerTalon;
  VictorSPX blFollowerTalon;

  public DriveTrain(XboxController omniscientController) {
    controller = omniscientController;
  }

  @Override
  public void robotInit() {

    frMasterTalon = new TalonSRX(RobotMap.DriveTrainMap.frontRightController);
    flMasterTalon = new TalonSRX(RobotMap.DriveTrainMap.frontLeftController);

    brFollowerTalon = new VictorSPX(RobotMap.DriveTrainMap.backRightController);
    brFollowerTalon.follow(frMasterTalon);

    blFollowerTalon = new VictorSPX(RobotMap.DriveTrainMap.backLeftController);
    blFollowerTalon.follow(flMasterTalon);

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {

    controllerXAxis = controller.getRawAxis(ControllerMap.xJoystickAxis);
    controllerYAxis = controller.getRawAxis(ControllerMap.yJoystickAxis);

    leftSidePower = controllerYAxis + controllerXAxis;
    rightSidePower = controllerYAxis - controllerXAxis;

    frMasterTalon.set(ControlMode.PercentOutput, rightSidePower);
    flMasterTalon.set(ControlMode.PercentOutput, leftSidePower);

  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }
}
