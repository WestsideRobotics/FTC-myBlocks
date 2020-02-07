/*

v01 1-28-2020
v02 1-28-2020
v03 1-29-2020
These were other files for showing battery voltage level.

v04 1-31-20
- implement new myBlock features: comment, tooltip, parameter name

Testing:
- Any Java change to the method interface DOES require closing and re-opening
  the using Op Mode from the Blocks menu.
- A Java edit that's internal to the method DOES NOT require re-opening
  or re-saving the Op Mode in Blocks; OK to press INIT on the DS phone,
  and the edit will be in effect.
- The annotations are optional and may appear in any order.
- Comment and tooltip must each be entered on a single line, no rollovers.
- The comment text CAN be edited in the Blocks Op Mode.
- Syntax for multiple parameters: parameterLabels {"labelOne", "labelTwo"},
  Line breaks may be used between labels.
- Parameter labels do not need to match the actual parameter names.
- If the number of parameter labels does not match the actual number of 
  parameters in the method's signature, any custom label(s) will be ignored.
  The myBlock will show the default label of the parameter type.

v05 was another file for navigation with Vuforia
v06 was another file with the first single myBlock with custom parameter

v07 dated 2-1-2020
- test all parameter types, return types, & objects included by extension  
- start with gamepad control of DS audio (text to speech)
- sidetracked with String comparison, must use Object.equals() not ==
- myBlock method(s) must be declared as public static in order for the class
  to be listed in Blocks
- does not work: sleep(), with/without various forms of LinearOpMode
- works: Thread.sleep(), but compiles only with exception handling:
  "ERROR: unreported exception java.lang.InterruptedException;
   must be caught or declared to be thrown"
  A working version is shown below.

v09 dated 2-6-20

Fresh start with hardwareMap, included by extension - how to use motors

- if the myBlock method's signature include any of the six parameters
  provided automatically by BlocksOpModeCompanion, there must be a 
  corresponding entry in the list of custom parameter labels.  Fortunately,
  its dummy socket and label will be omitted from the myBlock.
- But the above is not needed.  Previous testing used the wrong name for
  the LinearOpMode parameter, it should have been linearOpMode.  This allows
  access to the sleep() method without using try/catch.  So the final
  version here uses the correct parameter names and omits them from the 
  method signature and import list.
- possible to pass in servo name as parameter? use enum?

*/

package org.firstinspires.ftc.teamcode;

// these are (usually!) automatically listed by OnBot Java when needed
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;
import com.qualcomm.robotcore.hardware.Servo;

// New!  myBlocks can now automatically include certain common
// parameters: OpMode, LinearOpMode, hardwareMap, telemetry, gamepad1
// and gamepad2.  This is done by extending the class as shown below. 

// For myBlock publication, the class name can be short
// such as MyBlocks or Utilities.  It would contain many or all 
// myBlocks available for a user, not just one as shown here.
public class MyBlocks_v09_hardwareMap_OBJ extends BlocksOpModeCompanion {

    static Servo grabber = hardwareMap.get(Servo.class, "P0_HS485HB_grabber");

    // This Annotation must appear immediately before any myBlock method.
    // It's optional to add a comment, tooltip, and/or parameterLabels.
    @ExportToBlocks (
    comment = "v09 dated 2-6-2020  Testing hardwareMap, how to use motors etc.",
    tooltip = "Wiggle a servo, display live status.",
    parameterLabels = {"Duration (milliseconds)", "Number of cycles"}
    )
    
    public static void wiggleServo (int duration, int cycles) {

        telemetry.addData("Servo cycle duration", duration);
        telemetry.addData("Servo cycles to run", cycles);
        telemetry.addData("Press button X to begin", null);
        telemetry.update();

        while (!gamepad1.x && !gamepad2.x) {}
    
        for (int i = 0; i < cycles; i++)  {
            telemetry.addData("Servo current cycle", i+1);
            telemetry.update();
            grabber.setPosition(0.5);
            linearOpMode.sleep(duration);
            grabber.setPosition(0);
            linearOpMode.sleep(duration);
        }

        telemetry.addData("Servo cycle duration", duration);
        telemetry.addData("Servo cycles completed", cycles);
        telemetry.update();


    }   // end method wiggleServo()

}       // end class
