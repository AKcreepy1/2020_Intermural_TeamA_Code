/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

public class ControllerMap {

    // the port for the controller itself
    public static final int controllerPort = 0;

    public class ShooterControls {

        // axis used for shooting
        public static final int RightTrigger = 3;

        // buttons used for defining the distance for the shot
        public static final int buttonA = 0;
        public static final int buttonB = 1;
        public static final int buttonX = 2;

        // button used for feeder to enable
        public static final int buttonY = 3;

    }

    public class DriveTrainControls {

        // joysticks used to drive the drive train
        public static final int xJoystickAxis = 1;
        public static final int yJoystickAxis = 4;

        // toggle button used for activating precision mode (slows robot)
        public static final int rightStickButton = 9;
    }
    
}
