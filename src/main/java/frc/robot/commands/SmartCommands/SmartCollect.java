package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.HopperCommands.HopperASetBrakeMode;
import frc.robot.commands.HopperCommands.HopperASetCoastMode;
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

public class SmartCollect extends SequentialCommandGroup {
    
  

    public SmartCollect(hopper hopper, intake intake){
        //addRequirements(hopper);
    
        addCommands(
       
        //turn on the intake
            new LowerIntake(intake),
            new edu.wpi.first.wpilibj2.command.WaitCommand(.5),
            new IntakeSpin(intake, -1),
            new HopperIndex(hopper, 0.25),
            new HopperASetBrakeMode(hopper),
            new HopperBSetPower(hopper, -0.15),//just roll that cargo up a bit
            new edu.wpi.first.wpilibj2.command.WaitCommand(.5),
            new HopperASetCoastMode(hopper),
            new HopperBSetPower(hopper, 0),
            new IntakeSpin(intake, 0),
            new RaiseIntake(intake)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;
    }
}




