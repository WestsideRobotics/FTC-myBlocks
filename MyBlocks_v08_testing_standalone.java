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

v08 dated 2-5-20
- same as v07 but standalone (void) method
- use a captive method: same class but not a myBlock

Next to test:
- hardwareMap, included by extension - how to use motors

*/

package org.firstinspires.ftc.teamcode;

// these are (usually!) automatically listed by OnBot Java when needed
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;


// New!  myBlocks can now automatically include certain common
// parameters: OpMode, LinearOpMode, hardwareMap, telemetry, gamepad1
// and gamepad2.  This is done by extending the class as shown below. 

// For myBlock publication, the class name can be short
// such as MyBlocks or Utilities.  It would contain many or all 
// myBlocks available for a user, not just one as shown here.
public class MyBlocks_v08_testing_standalone extends BlocksOpModeCompanion {

    static String gamepadSelection = null;
    
    // This Annotation must appear immediately before any myBlock method.
    // It's optional to add a comment, tooltip, and/or parameterLabels.
    @ExportToBlocks (
    comment = "v08 dated 2-5-2020  Put this myBlock in a loop.  It causes the DS phone to speak when a specified gamepad button is pressed.",
    tooltip = "Control DS speech with gamepad.",
    parameterLabels = {"Gamepad number", "Gamepad button", "With courtesy"}
    )

    public static void saySomethingSolo (int gpNum, String gpBut, boolean isPolite) {

        telemetry.addData(": Press button A or B per the myBlock ", null);
        telemetry.addData("      : =========== ", null);
        
        if ( gamepad1.a && gpNum == 1 && gpBut.equalsIgnoreCase("A") ) {        
            gamepadSelection = "Gamepad 1, Button A";
            telemetry.addData("Valid gamepad selection", gamepadSelection);
            telemetry.update();
            telemetry.speak ("Referee 1, randomize the Stones.", null, null);
            politePauses(isPolite);
        } 
        
        else if ( gamepad2.a && gpNum == 2 && gpBut.equalsIgnoreCase("A") ) {        
            gamepadSelection = "Gamepad 2, Button A";
            telemetry.addData("Valid gamepad selection", gamepadSelection);
            telemetry.update();
            telemetry.speak ("Referee 2, randomize the Stones.", null, null);
            politePauses(isPolite);
        } 
        
        else if ( gamepad1.b && gpNum == 1 && gpBut.equalsIgnoreCase("B") ) {        
            gamepadSelection = "Gamepad 1, Button B";
            telemetry.addData("Valid gamepad selection", gamepadSelection);
            telemetry.update();
            telemetry.speak ("Driver 1, pick up your controller", null, null);
            politePauses(isPolite);
        }
        
        else if ( gamepad2.b && gpNum == 2 && gpBut.equalsIgnoreCase("B") ) {        
            gamepadSelection = "Gamepad 2, Button B";
            telemetry.addData("Valid gamepad selection", gamepadSelection);
            telemetry.update();
            telemetry.speak ("Driver 2, pick up your controller", null, null);
            politePauses(isPolite);
        }
        
        else {
            gamepadSelection = "None";
            telemetry.addData("Valid gamepad selection", gamepadSelection);
            telemetry.update();
        }
        
    }   // end method saySomething()


    // This method is not a myBlock, it's called by the method above.
    // Needs to be declared static; public seems optional.
    static void politePauses (Boolean politeCall)  {
        
            try { Thread.sleep(2200); }     // speaking duration
            catch (InterruptedException ex)
                  { Thread.currentThread().interrupt(); }
            
            if (politeCall) telemetry.speak ("Please!", null, null);

            try { Thread.sleep(400); }      // speaking duration
            catch (InterruptedException ex)
                  { Thread.currentThread().interrupt(); }

    }   // end method politePauses()

}       // end class
