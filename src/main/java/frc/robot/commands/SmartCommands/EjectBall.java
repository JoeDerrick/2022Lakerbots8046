package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.LowerIntake;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.climber;
import frc.robot.commands.ClimberCommands.highBar.HighBarHookRetract;
// ROBOTBUILDER TYPE: SequentialCommandGroup.

public class EjectBall extends SequentialCommandGroup {
    
  

    public EjectBall(hopper hopper, intake intake, climber climber){
        //addRequirements(hopper);
    
        addCommands(
            new HighBarHookRetract(climber),
            new HopperBSetPower(hopper, 1),
            new LowerIntake(intake),
            new IntakeSpin(intake, 1)/*,
            new edu.wpi.first.wpilibj2.command.WaitCommand(1),
            new IntakeSpin(intake, 0),
            new HopperBSetPower(hopper, 0),
            new RaiseIntake(intake)
            */
        );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;
    }
}




