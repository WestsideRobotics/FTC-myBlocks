/*

This myBlock was inspired by a discussion on accessing robot battery voltage,
not currently available with standard Blocks. Thanks to @vwestin and @AlecHub.

Link: https://github.com/FIRST-Tech-Challenge/SkyStone/issues/6

v01 1-28-2020
Gives telemetry to show voltage level of robot's 12V battery.

v02 1-28-2020
Converted to return the voltage as a numeric value.  No telemetry here,
the Blocks user can build telemetry as needed.

This program uses an enum (List) to avoid needing to identify by name
a specific voltage sensor from the hardwareMap.  This makes it more generic,
works on REV or MR controllers.

There will of course be only one voltage sensor in the List, so the FOR
loop will iterate one time, if a voltage sensor is found.

The Java code from the discussion included the object batteryVoltSensor,
which would be the first and only voltage sensor.  It could be used later,
outside the FOR loop, for calling getVoltage().

But in this myBlock there is no 'later', so that object was deleted in the
next version.

v03 1-29-2020
This is the streamlined version for publishing as a myBlock.

Expanded the FOR-loop object from "vs" to "myVoltageSensor".

If the robot battery is turned off, the myBlock returns the 5-6V from
the RC phone or powered USB hub, if any.

If the controller and its internal voltage sensor are not connected or not
available, the List will be empty and the myBlock returns -999.  This should
be noted if and when user documentation is available for myBlocks.

See the original discussion (linked above) for accessing the voltage
without using an enum (List).  Also see use of getDeviceName().

*/



package org.firstinspires.ftc.teamcode;

// these are automatically listed by OnBot Java when needed
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import java.util.List;


// For myBlock publication, the class name can be short
// such as MyBlocks or Utilities.  It would contain many or all 
// myBlocks available for a user, not just one as shown here.
public class MyBlock_batteryVoltage_v03_OBJ {

    // initialize outside the myBlock method, in case of looping
    static double batteryVoltage = -999;
    
    // The single parameter here is the minimum required,
    // for the current pre-release SDK version implementing myBlocks.
    // It will generate an idle socket in the purple myBlock.
    public static double getVoltage(LinearOpMode myLOM) {
        HardwareMap hardwareMap = myLOM.hardwareMap;

        // Use enum to access the voltage sensor without knowing its 
        // name in the hardwareMap.  Only one sensor will be found.
        List <VoltageSensor> voltageSensors;
        voltageSensors = hardwareMap.getAll(VoltageSensor.class);

        // make sure there is a sensor
        if (voltageSensors.size() > 0) {
        
            // iterate one time
            for (VoltageSensor myVoltageSensor : voltageSensors) {
                batteryVoltage = myVoltageSensor.getVoltage();
                }   // end FOR loop
            }       // end IF
        
            // provide voltage value to the calling Block
            return batteryVoltage;
        
    }   // end method getVoltage()

}       // end class
