package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.swerveDrivetrain;

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




