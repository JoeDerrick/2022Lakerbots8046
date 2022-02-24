/*
init-get hopper pos
exe- give number
*/
package frc.robot.commands.HopperCommands;

import frc.robot.subsystems.hopper;

//import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class WaitForCargo extends CommandBase{
    private  hopper hopper;
    private double value;
    public WaitForCargo(hopper subsystem){
        hopper = subsystem;
        //value = speed;
        addRequirements(hopper);
    }

    public void initialize(){
    }

    public void execute(){ 
    System.out.println("WAITING FOR BALL");
    }

    public boolean isFinished(){
        return hopper.getDigitalCargoSensorValue();
    }

    public void end() {

    }

    public void interrupted(){
        end();
    }
}