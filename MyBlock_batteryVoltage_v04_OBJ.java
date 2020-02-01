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

The Java code from the discussion included the object batteryVoltSensor,
which would be the first voltage sensor.  It could be used later,
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

v04 1-31-20

- implement new features: comment, tooltip, parameter name
- try eliminating FOR loop, take voltage only from first sensor in List

Testing:
- For editing to take effect DOES require closing and re-opening the
  using Op Mode from the Blocks menu.
- The annotations are optional and may appear in any order.
- Comment and tooltip must each be entered on a single line.
- The comment text can be edited in the Blocks Op Mode.
- Syntax for multiple parameters: parameterLabels {"labelOne", "labelTwo"},
  Line breaks may be used between labels.
- Parameter labels do not need to match the actual parameter names.
- If the number of parameter labels does not match the actual number of 
  parameters in the method's signature, any custom label(s) will be ignored.
  The myBlock will show the default label of the parameter type.

*/



package org.firstinspires.ftc.teamcode;

// these are automatically listed by OnBot Java when needed
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import java.util.List;


// New!  myBlocks can now automatically include certain common
// parameters: OpMode, LinearOpMode, hardwareMap, telemetry, gamepad1
// and gamepad2.  This is done by extending the class as shown below. 

// For myBlock publication, the class name can be short
// such as MyBlocks or Utilities.  It would contain many or all 
// myBlocks available for a user, not just one as shown here.
public class MyBlock_batteryVoltage_v04_OBJ extends BlocksOpModeCompanion {

    // initialize outside the myBlock method, in case of looping
    static double batteryVoltage = -999;

    // This Annotation must appear immediately before any myBlock method.
    // It's optional to add a comment, tooltip, and/or parameterLabels.
    @ExportToBlocks (
    comment = "v04 dated 1-31-2020  This myBlock returns the robot battery voltage. If no voltage sensor is found, it returns -999.  A voltage of about 6 volts probably means the controller is powered only by the RC phone or USB hub (if any).",
    tooltip = "Returns the robot battery voltage.",
    parameterLabels = {"One", "Word", "Truth"}
    )

    public static double getVoltage (double uno, String parola, boolean verita) {

        // Use enum to access a voltage sensor without knowing its 
        // name in the hardwareMap.
        List <VoltageSensor> voltageSensors;
        voltageSensors = hardwareMap.getAll(VoltageSensor.class);

        // make sure there is a sensor (controller is attached)
        if (voltageSensors.size() > 0) {
            // use only the first voltage sensor found,
            // don't need to know its name in hardwareMap.
            VoltageSensor myVoltSensor = voltageSensors.get(0);    
            batteryVoltage = myVoltSensor.getVoltage();
            }       // end IF
        
            // provide voltage value to the calling Block
            return batteryVoltage;
        
    }   // end method getVoltage()

}       // end class
