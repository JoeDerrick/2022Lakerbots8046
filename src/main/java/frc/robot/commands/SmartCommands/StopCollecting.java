package frc.robot.commands.SmartCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.HopperCommands.HopperASetPower;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.intake;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.RaiseIntake;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.ClimberCommands.highBar.HighBarHookExtend;
import frc.robot.commands.ClimberCommands.highBar.HighBarHookRetract;
import frc.robot.subsystems.*;

public class StopCollecting extends SequentialCommandGroup {
    
  

    public StopCollecting(hopper hopper, intake intake, climber climber){
   
    
        addCommands(
       
        //turn on the intake
            new IntakeSpin(intake, 0),//possibly add highbar hook retract???
            new RaiseIntake(intake),
            new HopperASetPower(hopper, 0),
            new HopperBSetPower(hopper, 0),
            new HighBarHookExtend(climber)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}




