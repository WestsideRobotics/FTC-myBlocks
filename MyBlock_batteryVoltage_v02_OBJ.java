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

*/


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import java.util.List;

public class MyBlock_batteryVoltage_v02_OBJ {

    // initialize to a bad value, in case no Voltage Sensor is available
    static double batteryVoltage = -999;
    
    public static double getBatteryVoltage(LinearOpMode myLOM) {
        Telemetry telemetry = myLOM.telemetry;
        HardwareMap hardwareMap = myLOM.hardwareMap;

        List<VoltageSensor> voltageSensors;
        VoltageSensor batteryVoltSensor = null;

        voltageSensors = hardwareMap.getAll(VoltageSensor.class);

        if (voltageSensors.size() > 0) {
            batteryVoltSensor = voltageSensors.get(0);
            for (VoltageSensor vs: voltageSensors) {
                //telemetry.addData(vs.getDeviceName(), "Voltage: %.2f", vs.getVoltage());
                batteryVoltage = vs.getVoltage();
            }   // end FOR loop
            }       // end IF 
            
        else    {
                //telemetry.addData("no Voltage Sensors detected", "");
                }   // end ELSE

        //telemetry.update();
        return batteryVoltage;
        
    }   // end method getBatteryVoltage()

}       // end class
