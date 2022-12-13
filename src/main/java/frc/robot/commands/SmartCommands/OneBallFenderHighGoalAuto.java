package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.commands.DriveCommands.DriveForwards;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.DriveCommands.RotateAmount;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.LowerIntake;
import frc.robot.commands.IntakeCommands.RaiseIntake;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.swerveDrivetrain;
import frc.robot.subsystems.intake;
import frc.robot.commands.*;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.SmartCommands.DriveAndCollect;
import frc.robot.subsystems.limelight;
import frc.robot.commands.LimeLightCommands.*;
import frc.robot.commands.LauncherHoodCommands.*;
public class OneBallFenderHighGoalAuto extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public OneBallFenderHighGoalAuto(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain, intake intake, limelight limelight){


        addCommands(
            new HoodExtend(launcher),    
            new edu.wpi.first.wpilibj2.command.WaitCommand(8),
            new DriveBackwards(swerveDrivetrain, 50),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.25),
            new SmartLaunchWithReverse(hopper, limelight, swerveDrivetrain, launcher),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.25),
            new DriveBackwards(swerveDrivetrain, 30)
            // add rotate using limelight
            // add high goal tarmac
            
        //turn torwards ball using rotateAmount (untested)
            //lower intake
            //spin intake
            //1(drive forward until ball gotten) or 2(if 5 sec has elapest)
            //if first option shoot 2        if second option shoot 1
        );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




