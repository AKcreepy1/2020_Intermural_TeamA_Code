/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

public class RobotMap {

    public static class DriveTrainMap {

        // id's for the TalonSR's which are directly connected to PWM on RoboRio
        public static final int frontRightID = 0;
        public static final int backRightID = 1;

        // id's for the TalonSRX's which are on the drive train
        public static final int frontLeftID = 12;
        public static final int backLeftID = 13;

    }

    public static class ShooterMap {
        public static final int topShooterID = 20;
        public static final int bottomShooterID = 21;
    }
}
