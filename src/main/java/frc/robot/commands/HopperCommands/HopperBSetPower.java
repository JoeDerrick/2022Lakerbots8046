package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperBSetPower extends CommandBase{
    private  hopper hopper;
    private double value;
    public HopperBSetPower(hopper subsystem, double speed){
        hopper = subsystem;
        value = -speed;
        addRequirements(hopper);
    }

    public void initialize(){
    }

    public void execute(){
        hopper.hopperBSetPower(value);

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