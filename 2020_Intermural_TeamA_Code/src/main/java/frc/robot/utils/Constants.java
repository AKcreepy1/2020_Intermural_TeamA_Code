/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;


public class Constants {
    // talon sensor velocity to RPM. 
    // (600 [100ms] / 1 [Minute]) * (1 [Rev] / 2047 [Native Encoder Ticks])
    public static final double kRPMtoCTREEncoderTicksVelocity = 2047.0 / 600.0; // 2047 ticks * 1 min / (1 rev * 60 seconds * 10 [100ms]);
    
    public static final double kvoltageComp = 11;

    // defines close RPM's of top and bottom motors
    public static final int k_topCloseRPM = 60;
    public static final int k_bottomCloseRPM = 60;

    // defines medium RPM's of top and bottom motors
    public static final int k_topMediumRPM = 300;
    public static final int k_bottomMediumRPM = 60;

    // defines far RPM's of top and bottom motors
    public static final int k_topFarRPM = 600;
    public static final int k_bottomFarRPM = 60;



}