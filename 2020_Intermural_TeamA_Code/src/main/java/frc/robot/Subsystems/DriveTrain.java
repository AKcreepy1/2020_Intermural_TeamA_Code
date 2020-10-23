/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.utils.ControllerMap;
import frc.robot.utils.RobotMap;

public class DriveTrain extends TimedRobot {

  double controllerXAxis;
  double controllerYAxis;

  double leftSidePower;
  double rightSidePower;

  double precisionValue;

  boolean precisionEnabled;
  boolean buttonHasBeenReleased;

  XboxController controller;

  Talon frTalonSR;
  Talon brTalonSR;
  WPI_TalonSRX flMasterTalon;
  WPI_VictorSPX blFollowerTalon;

  public DriveTrain(XboxController omniscientController) {
    controller = omniscientController;
  }

  @Override
  public void robotInit() {

      frTalonSR = new Talon(RobotMap.DriveTrainMap.frontRightID);
      brTalonSR = new Talon(RobotMap.DriveTrainMap.backRightID);

      flMasterTalon = new WPI_TalonSRX(RobotMap.DriveTrainMap.frontLeftID);
      blFollowerTalon = new WPI_VictorSPX(RobotMap.DriveTrainMap.backLeftID);
      blFollowerTalon.follow(flMasterTalon);

      precisionEnabled = false;
      buttonHasBeenReleased = true;
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

    if (controller.getRawButtonPressed(ControllerMap.rightStickButton) && buttonHasBeenReleased == true) {
      precisionEnabled = !precisionEnabled;
      buttonHasBeenReleased = false;
      precisionValue = 10.0;
    } else if (controller.getRawButtonReleased(ControllerMap.rightStickButton)) {
      buttonHasBeenReleased = true;
      precisionValue = 1.0;
    }

    controllerXAxis = controller.getRawAxis(ControllerMap.xJoystickAxis);
    controllerYAxis = controller.getRawAxis(ControllerMap.yJoystickAxis);

    leftSidePower = (controllerYAxis + controllerXAxis) / precisionValue;
    rightSidePower = (controllerYAxis - controllerXAxis) / precisionValue;

    frTalonSR.set(rightSidePower);
    brTalonSR.set(leftSidePower);

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
