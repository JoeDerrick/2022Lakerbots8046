package frc.robot.commands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.HopperCommands.HopperASetPower;
import frc.robot.commands.HopperCommands.HopperAdvanceA;
import frc.robot.commands.HopperCommands.WaitForCargo;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.intake;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.LowerIntake;
import frc.robot.commands.HopperCommands.HopperAdvanceB;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.HopperCommands.HopperIndex;
import frc.robot.commands.HopperABAdvance;

// ROBOTBUILDER TYPE: SequentialCommandGroup.

public class SmartCollect extends SequentialCommandGroup {
    
  

    public SmartCollect(hopper hopper, intake intake){
        //addRequirements(hopper);
    
        addCommands(
       
        //turn on the intake
            new LowerIntake(intake),
            new edu.wpi.first.wpilibj2.command.WaitCommand(.25),
            new IntakeSpin(intake, -0.75),
            new HopperIndex(hopper, 0.25)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;
    }
}




