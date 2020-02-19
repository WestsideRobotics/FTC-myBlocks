/*
    v01 dated 2-18-20
    Try to create MyBlocks that allow writing to and reading from
    a text file on the RC phone.  This is currently not possible with
    regular Blocks.
    
    Worked!
    
    v02 dated 2-19-20
    Provide myBlock parameter to specify name of file.
    Pure input or output, no multiplication inside myBlock.
    Tested with write in one Op Mode and read in another Op Mode.
    
    Next: write and read multiple values, that might be used for
    robot set-up, calibration or choices of autonomous program.

    The class ReadWriteFile does not appear to have a method for 
    appending to a file; might need to use java.io.Writer.write()
    or a java.io.FileWriter method.
    
 */


package org.firstinspires.ftc.teamcode;

// these are (usually!) automatically listed by OnBot Java when needed
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;

import com.qualcomm.robotcore.util.ReadWriteFile;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import java.io.File;

public class MyBlocks_fileAccess_v02_OBJ extends BlocksOpModeCompanion {

    // The method getSettingsFile() retrieves the settings (including location)
    // of the named file.  If the file doesn't already exist, it is created
    // in the FIRST/settings folder.  Put the filename in quotes if it's not
    // already a declared variable of type String.
    // Note: there is also a method copyFile(File fromFile, File toFile).
    
    // The write and read myBlocks can omit the filename parameter, if a team
    // always uses the same file.  In such case the filename can be declared
    // once at the class level (must be static), and used by all myBlock
    // methods.  Like this:
    // static File myFileName = AppUtil.getInstance().getSettingsFile("myTestFile.txt");


    // This Annotation must appear immediately before any myBlock method.
    // It's optional to add a comment, tooltip, and/or parameterLabels.
    // Comment must appear on a single line, no rollovers.
    @ExportToBlocks (
    comment = "v02 dated 2-19-2020  Write a number to a specified file on RC phone.  Includes telemetry of number being written.",
    tooltip = "Specify and write to file on RC phone.",
    parameterLabels = {"Number to Write", "Full Filename"}
    )
    
    public static void writeToFile (double myNumber, String thisFileName) {
        
        File myFileName = AppUtil.getInstance().getSettingsFile(thisFileName);

        // The String.valueOf() method reads a numerical value as a String.
        ReadWriteFile.writeFile(myFileName, String.valueOf(myNumber));
        
        telemetry.addData("Number being written to file", myNumber);
        telemetry.update();
        
    }   // end of method writeToFile()


    @ExportToBlocks (
    comment = "v02 dated 2-19-2020  Read and return a number from a specified file on RC phone.",
    tooltip = "Specify and read from file on RC phone.",
    parameterLabels = {"Full Filename"}
    )
    public static double readFromFile (String thisFileName) {
        
        File myFileName = AppUtil.getInstance().getSettingsFile(thisFileName);

        // The parseDouble() method interprets a String value as a double.
        // The trim() method removes any leading or trailing white space. 
        double Y = Double.parseDouble(ReadWriteFile.readFile(myFileName).trim());
        
        return Y;
        
    }  // end of method readFromFile()

}   // end of class

