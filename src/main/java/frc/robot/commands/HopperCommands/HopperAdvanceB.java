/*
init-get hopper pos
exe- give number
*/
package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperAdvanceB extends CommandBase{
    private  hopper hopper;
    private double value;
    public HopperAdvanceB(hopper subsystem, double value){
        hopper = subsystem;
        //value = speed;
        addRequirements(hopper);
    }

    public void initialize(){
        hopper.hopperAdvanceB(1000);
    }

    public void execute(){ 

    }

    public boolean isFinished(){
        return false;
    }

    public void end() {

    }

    public void interrupted(){
        end();
    }
}