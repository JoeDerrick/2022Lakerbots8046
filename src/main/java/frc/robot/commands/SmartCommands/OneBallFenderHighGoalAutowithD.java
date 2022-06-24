package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.commands.DriveCommands.DriveForwards;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.DriveCommands.RotateAmount;
import frc.robot.commands.DriveCommands.RotateAmountFast;
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
public class OneBallFenderHighGoalAutowithD extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public OneBallFenderHighGoalAutowithD(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain, intake intake, limelight limelight){


        addCommands(
            new HoodRetract(launcher),    
            new edu.wpi.first.wpilibj2.command.WaitCommand(2.0),
            new DriveBackwards(swerveDrivetrain, 20),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.1),
            new SmartLaunchWithReverse(hopper, limelight, swerveDrivetrain, launcher),
            //new LaunchLowGoal(hopper, launcher),//CHANGE BACK TO SMART LAUNCH AFTER TESTING
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.1),
            new RotateAmountFast(swerveDrivetrain, 80, 1, 0.8),
            //new edu.wpi.first.wpilibj2.command.WaitCommand(0.1),
            new DriveForwards(swerveDrivetrain,30),
            //new edu.wpi.first.wpilibj2.command.WaitCommand(0.1),
            new RotateAmountFast(swerveDrivetrain, 47, 1, 0.8),
           // new edu.wpi.first.wpilibj2.command.WaitCommand(0.1),
            new LowerIntake(intake),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.1),
            new IntakeSpin(intake, -1.0),
            new DriveForwards(swerveDrivetrain,65),
            new SmartCollect(hopper, intake).withTimeout(1),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.1),
            new RotateAmountFast(swerveDrivetrain, 55, 1, 0.8),
            new StopCollecting(hopper, intake),
            new DriveForwards(swerveDrivetrain,10),
            new LaunchLowGoal(hopper, launcher)
            
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




