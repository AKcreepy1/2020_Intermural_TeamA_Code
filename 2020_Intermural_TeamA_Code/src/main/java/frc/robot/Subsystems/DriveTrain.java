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

  // define x and y axis gotten from the controller
  double controllerXAxis;
  double controllerYAxis;

  // defines left and right side power variables to give to motors
  double leftSidePower;
  double rightSidePower;

  // defines precision value variable
  double precisionValue;

  // defines bool of whether or not precision mode is enabled
  boolean precisionEnabled;

  // defines bool of whether or not button has been released
  boolean rightStickHasBeenReleased;

  // defines controller
  XboxController controller;

  // defines the main motors for the drive train
  Talon frTalonSR;
  Talon brTalonSR;
  WPI_TalonSRX flMasterTalon;
  WPI_VictorSPX blFollowerTalon;

  @Override
  public void robotInit() {

    // initializes controller for use
    controller = new XboxController(ControllerMap.controllerPort);

    // initializes TalonSR's for use
    frTalonSR = new Talon(RobotMap.DriveTrainMap.frontRightID);
    brTalonSR = new Talon(RobotMap.DriveTrainMap.backRightID);

    // defines master front left talon
    flMasterTalon = new WPI_TalonSRX(RobotMap.DriveTrainMap.frontLeftID);

    // defines follower back left talon and commands it to follow the master
    blFollowerTalon = new WPI_VictorSPX(RobotMap.DriveTrainMap.backLeftID);
    blFollowerTalon.follow(flMasterTalon);

    // initializes precision mode variable to being false
    precisionEnabled = false;

    // initializes rightStickHasBeenReleased variable to being true
    rightStickHasBeenReleased = true;
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void teleopPeriodic() {

    // if rightStick is pressed and rightStickHasBeenReleased then...
    if (controller.getRawButtonPressed(ControllerMap.DriveTrainControls.rightStickButton) && rightStickHasBeenReleased == true) {
      
      // precisionModeEnables is the opposite
      precisionEnabled = !precisionEnabled;

      // rightStickHasBeenReleased is false
      rightStickHasBeenReleased = false;

      // precision value is set to 0.10
      precisionValue = 0.10;
    
      // ...else if rightStickHasBeenReleased then...
    } else if (controller.getRawButtonReleased(ControllerMap.DriveTrainControls.rightStickButton)) {
      
      // rightStickHasBeenReleased is true
      rightStickHasBeenReleased = true;
      
      // precision value is set to 0.10
      precisionValue = 1.0;
    }

    // gets raw X axis controller value
    controllerXAxis = controller.getRawAxis(ControllerMap.DriveTrainControls.xJoystickAxis);
    
    // gets raw Y axis controller value
    controllerYAxis = controller.getRawAxis(ControllerMap.DriveTrainControls.yJoystickAxis);

    // does calculation for the right and left side power
    rightSidePower = (controllerYAxis - controllerXAxis) / precisionValue;
    leftSidePower = (controllerYAxis + controllerXAxis) / precisionValue;


    // sets percent output of the TalonSR's 
    frTalonSR.set(rightSidePower);
    brTalonSR.set(rightSidePower);

    // sets percent output of the TalonSRX's
    flMasterTalon.set(ControlMode.PercentOutput, leftSidePower);

  }
}
