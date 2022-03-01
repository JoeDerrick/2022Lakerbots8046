package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.HopperCommands.HopperASetPower;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.intake;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.RaiseIntake;
import frc.robot.commands.HopperCommands.HopperBSetPower;


public class StopCollecting extends SequentialCommandGroup {
    
  

    public StopCollecting(hopper hopper, intake intake){
   
    
        addCommands(
       
        //turn on the intake
            new IntakeSpin(intake, 0),
            new RaiseIntake(intake),
            new HopperASetPower(hopper, 0),
            new HopperBSetPower(hopper, 0)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}




