package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperBSetBrakeMode extends CommandBase{
    private  hopper hopper;

    public HopperBSetBrakeMode(hopper subsystem){
        hopper = subsystem;
        //value = speed;
        addRequirements(hopper);
    }

    public void initialize(){
       hopper.HopperBSetBrakeMode();
    }

    public void execute(){
    }

    public boolean isFinished(){
        return true;
    }

    public void end() {

    }

    public void interrupted(){
        end();
    }
}