package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperASetSpeed extends CommandBase{
    private  hopper hopper;
    private double speed;
    public HopperASetSpeed(hopper subsystem, double speed){
        hopper = subsystem;
        //value = speed;
        addRequirements(hopper);
        
    }

    public void initialize(){
    }

    public void execute(){
        hopper.hopperASetSpeed(speed);
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