/*  v03 dated 1/3/2020
This is a test of FTC Pull Request #1859 from Liz Looney,
"Added support for calling team code Java methods from Blocks."

This file is not an Op Mode, it creates a Java class to contain
(unrelated) methods used by the FTC software to make new custom Blocks.

Notes:
- Would like to add an in-Block comment here, to serve as parameter
instructions and other user documentation.

- Was not able to instantiate method parameters as objects for the whole class;
it compiles here in OBJ but does not run.

- There are 4 automatic types: OpMode, LinearOpMode, HardwareMap and Telemetry.
At least 3 ways to set up HardwareMap and Telemetry:
    1. Declare as parameter.  This is intended but makes a large Block.
    2. Declare outside method, assign inside method.
    3. Full instantiation inside the method. This seems best for now.
Why not include these in all Blocks, without sockets?

- If a method's interface was changed in OBJ: 'Build Everything', then
close and re-open Blocks, replace the changed Block.  Otherwise OK to
just re-run the OpMode from the DS phone, even if not opened or saved in Blocks.

- v03 added gamepad and timers.
- not able to use common method to hold the parameter declarations.
- re-use hardware mapping?

- Next: find examples that cannot be accomplished with a Blocks Function.

- Future: Experienced teams can help new teams.  Or within a team, that 'solo'
Java learner can support 'majority' Blocks students; all contributing.

The FTC world may ultimately benefit from a curated repo (Blocks Store)
for tested, well documented Blocks.


Testing corrections and comments to westsiderobotics@verizon.net
*/

package org.firstinspires.ftc.teamcode;

// These library classes are added automatically.
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class MyBlocks_OBJ_v03 {

    // these 3 can be declared here or inside each using method
    // private static Telemetry myTelemetry;
    // private static Gamepad myGamepad1;
    // private static HardwareMap myHardware;

    public static String myGreeting(String greetingRecipient) {
        return ("Hello, " + greetingRecipient + "!");
        }
        
    // this needs an in-Block comment to describe the 3 parameters
    public static int inchesToCounts(int inchesToDrive,
    int countsPerWheelRotation, double wheelDiameter) {
        double circumference = wheelDiameter * Math.PI;
        double rotations = inchesToDrive / circumference;
        double countsToDrive = rotations * countsPerWheelRotation;
        return (int) countsToDrive;
    }

    public static void saySomething(Telemetry myTelemetry)  {
        myTelemetry.speak("I'm in the Caddy.", "en", "US");
    }

    // Telemetry parameter name is always set to 'telemetry', even if 
    // this method calls it something else like 'myTelemetry'.  Still works.
    public static void sayLocation(String myLocation, Telemetry myTelemetry)  {
        myTelemetry.speak("I'm in " + myLocation + ".", "en", "US");
        myTelemetry.addData("I'm in", myLocation);
        myTelemetry.update();
    }

    // HardwareMap parameter name is always set to 'hardwareMap', even if 
    // this method calls it something else like 'myHW'.  Still works.
    // Likewise, parameters of type OpMode and LinearOpMode are always
    // named 'this'. 
    // The sleep() method is available only with LinearOpMode, not OpMode.
    
    public static void wiggleServo(HardwareMap myHW, LinearOpMode myLOM, Telemetry myTelemetry)  {
        Servo grabber = myHW.get(Servo.class, "P0_HS485HB_grabber");
        grabber.setPosition(0.5);
        myLOM.sleep(500);
        grabber.setPosition(0.4);
        myLOM.sleep(500);
        myTelemetry.addData("Runtime", myLOM.getRuntime());
        myTelemetry.update();
    }
 
    // Some Java commands are available with OpMode or LinearOpMode, since
    // the former is parent to the latter.  An example is getRuntime().
    public static void displayRuntime(OpMode myOM, Telemetry myTelemetry)  {
        myTelemetry.addData("Runtime since INIT", myOM.getRuntime());
        myTelemetry.update();
    }


    // Gamepad is not an automatically provided parameter type, but can
    // be used per this example.  The name myGamepad1 was not chosen as
    // gamepad1, to identify it as a local object.
    
    // Can also do this also with Telemetry and HardwareMap, see below.
    
    public static void gamepadTest(Telemetry myTelemetry, LinearOpMode myLOM)  {
        Gamepad myGamepad1 = myLOM.gamepad1;
        myTelemetry.addData("Button A is pressed", myGamepad1.a);  // true/false
        myTelemetry.update();
    }
 
 
    // This method has 6 generic sockets, making it a large Block.
    public static void largeBlock (
            OpMode myOM,
            LinearOpMode myLOM,
            HardwareMap myHW, 
            Telemetry myTelemetry,
            Gamepad gamepad1,
            Gamepad gamepad2
            )  {
        myTelemetry.addData("6 sockets", "big Block");
        myTelemetry.update();
    }
 
   
   // This minimizes the number of sockets in the Block.
    public static void smallBlock(LinearOpMode myLOM)  {
        Telemetry myTelemetry = myLOM.telemetry;
        Gamepad myGamepad1 = myLOM.gamepad1;
        HardwareMap myHardware = myLOM.hardwareMap;
        Servo grabber = myHardware.get(Servo.class, "P0_HS485HB_grabber");
        grabber.setPosition(0.5);
        myLOM.sleep(500);
        grabber.setPosition(0.4);
        myLOM.sleep(500);
        myTelemetry.addData("Button A is pressed", myGamepad1.a);  // true/false
        myTelemetry.update();
    }
 
 
   // Creating a new ElapsedTime timer must be outside any method that
   // might be used in a loop.  Otherwise it will be constantly reset.
    private static ElapsedTime myStopwatch = new ElapsedTime();
    
    public static void stopwatchB(LinearOpMode myLOM) {
        Telemetry myTelemetry = myLOM.telemetry;
        Gamepad myGamepad1 = myLOM.gamepad1;
        if (myGamepad1.b) myStopwatch.reset();  // press B, reset to zero
        myTelemetry.addData("To reset Stopwatch", "Press B button");
        myTelemetry.addData("Stopwatch timer", "%.2f", myStopwatch.time());
        myTelemetry.update();
    }
   
}   // end of class
