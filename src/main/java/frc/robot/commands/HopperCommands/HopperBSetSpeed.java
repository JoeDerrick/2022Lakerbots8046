package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperBSetSpeed extends CommandBase{
    private  hopper hopper;
    private double speed;
    public HopperBSetSpeed(hopper subsystem, double speed){
        hopper = subsystem;
        //value = speed;
        addRequirements(hopper);
        
    }

    public void initialize(){
       hopper.hopperBSetSpeed(speed);
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