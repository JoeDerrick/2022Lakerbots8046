package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.swerveDrivetrain;
import frc.robot.subsystems.intake;
import frc.robot.commands.*;
import frc.robot.commands.DriveCommands.Rotate180;

public class TarmacAuto extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public TarmacAuto(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain, intake intake){


        addCommands(
            new LaunchHighGoalTarmac(hopper, launcher),
            new DriveBackwards(swerveDrivetrain, 1),
            new Rotate180(swerveDrivetrain),
            new SmartCollect(hopper, intake)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




