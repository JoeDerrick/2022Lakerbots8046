package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperASetPos extends CommandBase{
    private  hopper hopper;
    private double value;
    public HopperASetPos(hopper subsystem, double value){
        hopper = subsystem;
        //value = speed;
        addRequirements(hopper);
    }

    public void initialize(){
        hopper.hopperASetPos(value);

    }

    public void execute(){
        hopper.hopperASetPos(value);
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