package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.hopper;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperASetPower extends CommandBase{
    private  hopper hopper;
    private double value;
    public HopperASetPower(hopper subsystem, double value){
        hopper = subsystem;
        //value = speed;
        addRequirements(hopper);
    }

    public void initialize(){
       hopper.hopperASetPower(value);
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