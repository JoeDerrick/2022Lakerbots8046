package frc.robot.commands.SmartCommands;
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
import frc.robot.commands.IntakeCommands.RaiseIntake;
import frc.robot.commands.UnusedCommands.HopperABAdvance;
import frc.robot.commands.HopperCommands.HopperAdvanceB;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.HopperCommands.HopperIndexCollect1;

// ROBOTBUILDER TYPE: SequentialCommandGroup.

public class SmartCollect1 extends SequentialCommandGroup {
    
  
 
    public SmartCollect1(hopper hopper, intake intake){
        //addRequirements(hopper);
    
        addCommands(
       
        //turn on the intake
            new LowerIntake(intake),
            new edu.wpi.first.wpilibj2.command.WaitCommand(.25),
            new IntakeSpin(intake, -1),
            new HopperIndexCollect1(hopper, 0.25),
            new IntakeSpin(intake, 0),
            new RaiseIntake(intake)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;
    }
}




