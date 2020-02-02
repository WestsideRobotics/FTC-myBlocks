/*
This series of classes explores myBlocks' handling of String parameters.
Initial testing didn't recognize them; tried many iterations with
String, char, importing java.lang.String, etc.

v02 2-2-2020   Simplest case: char type only, works fine

v03 2-2-2020
Change all char to String; it builds but the parameter does not work.

The returned value is properly passing as a String back to the Op Mode;
the DS phone does say "zero".  So the String class is recognized here,
without needing to import java.lang.String.

But in the IF statement (gpBut == "A") is not returning true, apparently 
because the "A" text in the myBlock is not being processed as String?
Also did not work after declaring the type (in the method signature)
as java.lang.String.  Also tried importing java.lang.String, no go.

When only the parameter is changed back to char, it works.

*/

package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;

public class MyBlocks_String_v03 extends BlocksOpModeCompanion {

    public static String gamepadSelection = "None";

    @ExportToBlocks (
    comment = "v03 dated 2-2-2020 Change parameter type from char to String, doesn't work. Put this myBlock in a loop.  It returns a text value based on pressing the gamepad button specified by parameters.",
    tooltip = "Returns text if specified gamepad button is pressed.",
    parameterLabels = {"Gamepad number", "Button letter", "Is polite?"}
    )

    public static String showButton (int gpNum, String gpBut, boolean isPolite) {

        if ( gamepad1.a && gpNum == 1 && (gpBut == "A" || gpBut == "a") ) {        
            gamepadSelection = "A";
            telemetry.addData("Now release Gamepad 1 Button A", null);
            if (isPolite) telemetry.addData("...please!", null);
            } 
        else  {
            gamepadSelection = "0";  // "zero"
            telemetry.addData("The specified button is not pressed.", null);
            }
        telemetry.update();

        return gamepadSelection;
        
    }   // end method showButton()
}       // end class
