package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperIndex extends CommandBase{
    private  hopper hopper;
    private double value;
    public HopperIndex(hopper subsystem, double speed){
        hopper = subsystem;
        value = speed;
        addRequirements(hopper);


    }

    public void initialize(){
       
    }

    public void execute(){
        if(hopper.getDigitalCargoSensorA()== false){
            hopper.hopperASetPower(value);
            hopper.hopperBSetPower(value);
        }
        else if(hopper.getDigitalCargoSensorB()== false){
            hopper.hopperASetPower(0);
            hopper.hopperBSetPower(value);
        }
        else{
            hopper.hopperASetPower(0);   
            hopper.hopperASetPower(0); 
        }
        
    }

    public boolean isFinished(){
        if (hopper.getDigitalCargoSensorA() && hopper.getDigitalCargoSensorB()){
            return true;
        }
        else return false;
    }

    public void end() {

    }

    public void interrupted(){
        end();
    }
}