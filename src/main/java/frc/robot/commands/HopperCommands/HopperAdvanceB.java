/*
init-get hopper pos
exe- give number
*/
package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

import javax.swing.text.Position;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperAdvanceB extends CommandBase{
    private  hopper hopper;
    private double value;
    public HopperAdvanceB(hopper subsystem, double position){
        hopper = subsystem;
        value = position;
        addRequirements(hopper);
    }

    public void initialize(){
        hopper.resethopperB();
        hopper.hopperAdvanceB(value);
    }

    public void execute(){ 

    }

    public boolean isFinished(){
        return hopper.hopperBAtPosition(value);
    }

    public void end() {

    }

    public void interrupted(){
        end();
    }
}