/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.utils.Constants;
import frc.robot.utils.ControllerMap;
import frc.robot.utils.RobotMap;

public class Shooter extends TimedRobot {

  // creates an enum for the shooting location
  private enum shootingLocation {

    // three different shooting locations
    CLOSE, MEDIUM, FAR

  };

  // defines a timer
  Timer timer;

  // defines the motors for the shooter
  WPI_TalonSRX topShooter;
  WPI_TalonSRX bottomShooter;

  // defines the feeder talon
  Talon feederTalon;

  // makes TalonSRXConfigurations for the top and bottom
  TalonSRXConfiguration topConfig;
  TalonSRXConfiguration bottomConfig;

  // defines top and bottom RPM values
  double topRPM;
  double bottomRPM;

  // defines top and bottom velocity per 100ms
  double topTargetVelocityPer100Ms;
  double bottomTargetVelocityPer100Ms;

  // defines bool that y has been released for one teleopPeriodic cycle
  boolean buttonYBeenReleasedForLoop;

  // defines bool that says is y was pressed last teleopPeriodic cycle
  boolean yPressedLastCycle;

  // defines bool to feed shooter
  boolean transitioningToOriginalPoint;

  // defines the wanted shot power
  shootingLocation wantedShotPower;

  // defines the controller
  XboxController controller;

  @Override
  public void robotInit() {

    // initializes the controller
    controller = new XboxController(ControllerMap.controllerPort);

    // defines the values for the PID top config
    topConfig.voltageCompSaturation = Constants.kvoltageComp;
    topConfig.openloopRamp = 0.1;
    topConfig.forwardSoftLimitEnable = false;
    topConfig.reverseSoftLimitEnable = false;
    // topConfig.peakCurrentLimit = 50;
    // topConfig.peakCurrentDuration = 20;
    topConfig.neutralDeadband = 0.005;
    topConfig.nominalOutputForward = 0;
    topConfig.nominalOutputReverse = 0;
    topConfig.peakOutputForward = 1;
    topConfig.peakOutputReverse = -0.3;
    topConfig.closedloopRamp = 0.3;
    topConfig.slot0.allowableClosedloopError = 0;
    topConfig.slot0.closedLoopPeakOutput = 1.0;
    topConfig.slot0.closedLoopPeriod = 2;
    topConfig.slot0.integralZone = 0;
    topConfig.slot0.kP = 0.001;
    topConfig.slot0.kI = 0.0;
    topConfig.slot0.kD = 0.0;
    topConfig.slot0.kF = 0.055;

    // initializes and configures the top shooting motor
    topShooter = new WPI_TalonSRX(RobotMap.ShooterMap.topShooterID);
    topShooter.configAllSettings(topConfig);
    topShooter.setNeutralMode(NeutralMode.Coast);

    // makes bottom config mimic the top configuration
    bottomConfig = topConfig;

    // initializes and configures the bottom shooting motor
    bottomShooter = new WPI_TalonSRX(RobotMap.ShooterMap.bottomShooterID);
    bottomShooter.configAllSettings(bottomConfig);
    bottomShooter.setNeutralMode(NeutralMode.Coast);

    // initializes and configures the feeder talon
    feederTalon = new Talon(RobotMap.ShooterMap.feederMotorID);
    bottomShooter.setNeutralMode(NeutralMode.Brake);

    // initializes y not having been released for one teleopPeriodic cycle as being false
    buttonYBeenReleasedForLoop = false;

    // initializes transitioning to original feeder point to being false
    transitioningToOriginalPoint = false;

    // initializes the value to being false
    yPressedLastCycle = false;
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void teleopPeriodic() {

    // if buttonX is pressed then...
    if (controller.getRawButtonPressed(ControllerMap.ShooterControls.buttonX)) {

      // make wantedShotPower close
      wantedShotPower = shootingLocation.CLOSE;

    // ...else if buttonB is pressed then...
    } else if (controller.getRawButtonPressed(ControllerMap.ShooterControls.buttonB)) {

      // make wantedShotPower medium
      wantedShotPower = shootingLocation.MEDIUM;

    // ...else if buttonA is pressed then...
    } else if (controller.getRawButtonPressed(ControllerMap.ShooterControls.buttonA)) {

      // make wantedShotPower far
      wantedShotPower = shootingLocation.FAR;

    }

    // if the wantedShotPower is close then...
    if (wantedShotPower == shootingLocation.CLOSE) {
      
      // make top and bottom RPM to close value in constants
      topRPM = Constants.k_topCloseRPM;
      bottomRPM = Constants.k_bottomCloseRPM;

    // ...else if the wantedShotPower is medium then...
    } else if (wantedShotPower == shootingLocation.MEDIUM) {

      // make top and bottom RPM to medium value in constants
      topRPM = Constants.k_topMediumRPM;
      bottomRPM = Constants.k_bottomMediumRPM;

    // ...else if the wantedShotPower is far then...
    } else if (wantedShotPower == shootingLocation.FAR) {

      // make top and bottom RPM to far value in constants
      topRPM = Constants.k_topFarRPM;
      bottomRPM = Constants.k_bottomFarRPM;

    }

    // sets velocity of top and bottom values per 100 ms
    topTargetVelocityPer100Ms = Constants.kRPMtoCTREEncoderTicksVelocity * topRPM;
    bottomTargetVelocityPer100Ms = Constants.kRPMtoCTREEncoderTicksVelocity * bottomRPM;

    // if rightTrigger is pressed more than a hundreth then...
    if (controller.getRawAxis(ControllerMap.ShooterControls.RightTrigger) > 0.01) {

      // set top and bottom velocity value
      topShooter.set(ControlMode.Velocity, topTargetVelocityPer100Ms);
      bottomShooter.set(ControlMode.Velocity, bottomTargetVelocityPer100Ms);

      // ...else then...
    } else {

      // set the motor output to 0 percent
      topShooter.set(0.0);
      bottomShooter.set(0.0);

    }

    // if button y is released and y was pressed last teleopPeriodic cycle then...
    if (controller.getRawButtonReleased(ControllerMap.ShooterControls.buttonY) && yPressedLastCycle == true) {
      
      // button Y has been released for loop now equals false
      buttonYBeenReleasedForLoop = false;
      
      // y pressed last cycle then equals false
      yPressedLastCycle = false;
    }

    // if button Y is pressed then...
    if (controller.getRawButtonPressed(ControllerMap.ShooterControls.buttonY)) {

      // raise piece in the way of balls
      feederTalon.set(-0.05);

      // y is being pressed and is now a part of the last cycle
      yPressedLastCycle = true;

    // ...else if button y has been released and button Y has not been released for one loop yet then...
    } else if (buttonYBeenReleasedForLoop == false) {

      // resets the timer to 0
      timer.stop();
      timer.reset();

      // enable timer
      timer.start();

      // sets value to being true
      buttonYBeenReleasedForLoop = true;

      // sets value to being true
      transitioningToOriginalPoint = true;
      
    // ...else if motorTransitioningToOriginalPoint is true and seconds passed doesnt equal one then
    } else if (transitioningToOriginalPoint == true && timer.hasElapsed(0.1) == false) {

      // set motor to 5 percent to return back
      feederTalon.set(0.05);

    // ...otherwise just...
    } else {
      
      // set motor to 0 percent
      feederTalon.set(0.0);

    }

  }
}