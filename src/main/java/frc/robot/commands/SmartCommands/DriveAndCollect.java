package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.commands.DriveCommands.DriveForwards;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.DriveCommands.RotateAmount;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.LowerIntake;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.swerveDrivetrain;
import frc.robot.subsystems.intake;
import frc.robot.commands.*;
import frc.robot.commands.DriveCommands.Rotate180;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class DriveAndCollect extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public DriveAndCollect(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain, intake intake){
        addCommands(
            /*
            new ParallelDeadlineGroup(
                deadlineWith(new SmartCollect(hopper, intake)),
                new DriveForwards(swerveDrivetrain, 40)
                
            )
            */
        );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




