package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperIndex extends CommandBase{
    private  hopper hopper;
    private double value;
    private boolean hopperABool;
    private boolean hopperBBool;
    public HopperIndex(hopper subsystem, double speed){
        hopper = subsystem;
        value = speed;
        addRequirements(hopper);


    }

    public void initialize(){
        hopperABool = false;
        hopperBBool = false;
       
    }

    public void execute(){

        if (hopper.getDigitalCargoSensorA()==true){
            hopperABool = true;
        }
        if (hopper.getDigitalCargoSensorB()==true && hopper.getDigitalCargoSensorA() == false){
        }
        if(hopper.getDigitalCargoSensorB() == true && hopperABool==true){
            hopperBBool= true;
        }

        //if we dont have cargo in the spot closest to launcher slowly advance both motors
        if(hopperABool==true && hopperBBool == true){
            hopper.hopperASetPower(0);
            hopper.hopperBSetPower(0);
        }
        if(hopperABool==true && hopperBBool == false){
            hopper.hopperASetPower(0);
            hopper.hopperBSetPower(value);
        }
        if(hopperABool==false && hopperBBool == false){
            hopper.hopperASetPower(-value);
            hopper.hopperBSetPower(value);
        }
      
        
    }

    public boolean isFinished(){
        //if there are 2 cargo collected end the command
        if (hopperABool== true && hopperBBool== true){
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