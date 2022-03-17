package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.commands.DriveCommands.DriveForwards;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.DriveCommands.RotateAmount;
import frc.robot.commands.DriveCommands.RotateToTarget;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.LowerIntake;
import frc.robot.commands.IntakeCommands.RaiseIntake;
import frc.robot.commands.LauncherCommands.LauncherLimelightLaunch;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.swerveDrivetrain;
import frc.robot.subsystems.intake;
import frc.robot.commands.*;
import frc.robot.commands.LimeLightCommands.*;
import frc.robot.commands.SmartCommands.DriveAndCollect;
public class ThreeBallAuto extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public ThreeBallAuto(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain, intake intake, limelight limelight){


        addCommands(// this is NOt what we need to do for a three ball please review the objective/map/path
       // new LauncherLimelightLaunch(launcher, limelight),
        new RotateAmount(swerveDrivetrain, 180, -1),
        new LowerIntake(intake),
        new IntakeSpin(intake, -1),
        new HopperBSetPower(hopper, -0.2),
        new DriveForwards(swerveDrivetrain, 29),
        new SmartCollect(hopper, intake)
        //new RotateAmount(swerveDrivetrain, 120, -1),
        //new IntakeSpin(intake, -1),
        //new HopperBSetPower(hopper, -0.2),
        //new DriveForwards(swerveDrivetrain, 120),
        //new SmartCollect(hopper, intake),
        //new RotateToTarget(swerveDrivetrain, limelight),
        //new LauncherLimelightLaunch(launcher, limelight)
        //**Launch High Goal First
            //trun 180
            //collect and drive
            //turn 90//

            //collect and drive
            //turn 90
            //shoot 2 all in 15 seconds
        );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




