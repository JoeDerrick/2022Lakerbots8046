package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperASetPower extends CommandBase{
    private  hopper hopper;
    private double value;
    public HopperASetPower(hopper subsystem, double speed){
        hopper = subsystem;
        value = speed;
        addRequirements(hopper);


    }

    public void initialize(){
       
    }

    public void execute(){
        System.out.println("Hopper A set");
        hopper.hopperASetPower(value);
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