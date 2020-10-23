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

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.utils.Constants;
import frc.robot.utils.ControllerMap;
import frc.robot.utils.GenerateDefaultTalonSRX;
import frc.robot.utils.RobotMap;

public class Shooter extends TimedRobot {

  private enum shootingLocation {
    CLOSE, MEDIUM, FAR
  };

  String[] distanceModes = {"CLOSE", "MEDIUM", "FAR"};

  // defines the motors for the shooter
  WPI_TalonSRX topShooter;
  WPI_TalonSRX bottomShooter;
  WPI_TalonSRX tunnelTalon;

  TalonSRXConfiguration topConfig;
  TalonSRXConfiguration bottomConfig;

  double topRPM;
  double bottomRPM;

  shootingLocation desiredShotPower;

  XboxController controller;

  public Shooter(XboxController omniController) {
    controller = omniController;
  }

  @Override
  public void robotInit() {

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

    topShooter = GenerateDefaultTalonSRX.generateDefaultTalon(RobotMap.ShooterMap.topShooterID);
    topShooter.configAllSettings(topConfig);
    topShooter.setNeutralMode(NeutralMode.Coast);


    bottomConfig = topConfig;

    bottomShooter = GenerateDefaultTalonSRX.generateDefaultTalon(RobotMap.ShooterMap.bottomShooterID);
    bottomShooter.configAllSettings(bottomConfig);
    bottomShooter.setNeutralMode(NeutralMode.Coast);
  
    tunnelTalon = new WPI_TalonSRX(22);
    bottomShooter.setNeutralMode(NeutralMode.Brake);
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

    if (controller.getRawButtonPressed(0)) {
      desiredShotPower = shootingLocation.CLOSE;
    } else if (controller.getRawButtonPressed(1)) {
      desiredShotPower = shootingLocation.MEDIUM;
    } else if (controller.getRawButtonPressed(2)) {
      desiredShotPower = shootingLocation.FAR;
    }

    if (desiredShotPower == shootingLocation.CLOSE) {
      topRPM = 60;
      bottomRPM = 60;
    } else if (desiredShotPower == shootingLocation.MEDIUM) {
      topRPM = 300;
      bottomRPM = 300;
    } else if (desiredShotPower == shootingLocation.FAR) {
      topRPM = 600;
      bottomRPM = 600;
    }

    double topTargetVelocityPer100Ms = Constants.kRPMtoCTREEncoderTicksVelocity * topRPM;
    double bottomTargetVelocityPer100Ms = Constants.kRPMtoCTREEncoderTicksVelocity * bottomRPM;

    if (controller.getRawAxis(ControllerMap.RightTrigger) > 0.01) {
      topShooter.set(ControlMode.Velocity, topTargetVelocityPer100Ms);
      bottomShooter.set(ControlMode.Velocity, bottomTargetVelocityPer100Ms);

      if (topShooter.getSelectedSensorVelocity() < topTargetVelocityPer100Ms + 2
       && topShooter.getSelectedSensorVelocity() > topTargetVelocityPer100Ms - 2 
       && bottomShooter.getSelectedSensorVelocity() < topTargetVelocityPer100Ms + 2
       && bottomShooter.getSelectedSensorVelocity() > topTargetVelocityPer100Ms - 2) {
        
        tunnelTalon.set(0.05);

      }
    } else {
      topShooter.set(0);
      bottomShooter.set(0);
      tunnelTalon.set(0);
    }
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
