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
import frc.robot.commands.HopperCommands.HopperIndex;

// ROBOTBUILDER TYPE: SequentialCommandGroup.

public class EjectBall extends SequentialCommandGroup {
    
  

    public EjectBall(hopper hopper, intake intake){
        //addRequirements(hopper);
    
        addCommands(
            
            new HopperBSetPower(hopper, 1),
            new edu.wpi.first.wpilibj2.command.WaitCommand(1),
            new IntakeSpin(intake, 0),
            new HopperBSetPower(hopper, 0),
            new RaiseIntake(intake)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;
    }
}




