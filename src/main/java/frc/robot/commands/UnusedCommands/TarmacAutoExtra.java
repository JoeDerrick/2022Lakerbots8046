package frc.robot.commands.UnusedCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.SmartCommands.LaunchHighGoalTarmac;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.swerveDrivetrain;
import frc.robot.subsystems.intake;
import frc.robot.commands.*;
import frc.robot.commands.SmartCommands.*;

public class TarmacAutoExtra extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public TarmacAutoExtra(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain, intake intake){


        addCommands(
            new LaunchHighGoalTarmac(hopper, launcher),
            new DriveBackwards(swerveDrivetrain),
            new Rotate180(swerveDrivetrain),
            new SmartCollect(hopper, intake),
            new Rotate180(swerveDrivetrain),
            new LaunchHighGoalTarmac(hopper, launcher)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




