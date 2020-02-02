/*
This series of classes explores myBlocks' handling of String parameters.
Initial testing didn't recognize them; tried many iterations with
String, char, importing java.lang.String, etc.

v02 2-2-2020   Simplest case: char type only, works fine

*/

package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;

public class MyBlocks_String_v02 extends BlocksOpModeCompanion {

    public static char gamepadSelection = 'N';

    @ExportToBlocks (
    comment = "v02 dated 2-2-2020  All char, works.  Put this myBlock in a loop.  It returns a text value based on pressing the gamepad button specified by parameters.",
    tooltip = "Returns text if specified gamepad button is pressed.",
    parameterLabels = {"Gamepad number", "Button letter", "Is polite?"}
    )

    public static char showButton (int gpNum, char gpBut, boolean isPolite) {

        if ( gamepad1.a && gpNum == 1 && (gpBut == 'A' || gpBut == 'a') ) {        
            gamepadSelection = 'A';
            telemetry.addData("Now release Gamepad 1 Button A", null);
            if (isPolite) telemetry.addData("...please!", null);
            } 
        else  {
            gamepadSelection = '0';  // "zero"
            telemetry.addData("The specified button is not pressed.", null);
            }
        telemetry.update();

        return gamepadSelection;
        
    }   // end method showButton()
}       // end class
