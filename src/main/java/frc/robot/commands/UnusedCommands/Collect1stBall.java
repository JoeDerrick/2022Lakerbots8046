package frc.robot.commands.UnusedCommands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
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

// ROBOTBUILDER TYPE: SequentialCommandGroup.

public class Collect1stBall extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public Collect1stBall(hopper hopper, intake intake){
    
        addCommands(
            new LowerIntake(intake),
            new IntakeSpin(intake, -0.75),
            new HopperASetPower(hopper, 0.25),
            new HopperBSetPower(hopper, 0.25),
            new WaitForCargo(hopper),
            new HopperBSetPower(hopper, 0),
            new HopperASetPower(hopper, 0),
            new IntakeSpin(intake, 0)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}




