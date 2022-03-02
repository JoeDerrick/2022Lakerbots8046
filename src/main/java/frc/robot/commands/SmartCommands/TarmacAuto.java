package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.AutoDrive;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.swerveDrivetrain;

public class TarmacAuto extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public TarmacAuto(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain){


        addCommands(
            new LaunchHighGoalTarmac(hopper, launcher),
            new AutoDrive(swerveDrivetrain)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




