/*

This myBlock was inspired by a discussion on accessing robot battery voltage,
not currently available with standard Blocks. Thanks to @vwestin and @AlecHub.

Link: https://github.com/FIRST-Tech-Challenge/SkyStone/issues/6

v01 1-28-2020
Gives telemetry to show voltage level of robot's 12V battery.
*/


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.VoltageSensor;
//import java.util.ArrayList;
import java.util.List;


public class MyBlock_batteryVoltage_v01_OBJ {

    public static void getBatteryVoltage(LinearOpMode myLOM) {
        Telemetry telemetry = myLOM.telemetry;
        HardwareMap hardwareMap = myLOM.hardwareMap;

        List<VoltageSensor> voltageSensors;
        VoltageSensor batteryVoltSensor = null;

        voltageSensors = hardwareMap.getAll(VoltageSensor.class);

        if (voltageSensors.size() > 0) {
            batteryVoltSensor = voltageSensors.get(0);
            for (VoltageSensor vs: voltageSensors) {
                telemetry.addData(vs.getDeviceName(), "Voltage: %.2f", vs.getVoltage());
                }   // end FOR loop
            }       // end IF 
            
        else    {
                telemetry.addData("no Voltage Sensors detected", "");
                }   // end ELSE

        telemetry.update();

    }   // end method getBatteryVoltage()

}       // end class
