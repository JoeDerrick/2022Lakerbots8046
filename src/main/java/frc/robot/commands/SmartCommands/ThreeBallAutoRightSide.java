package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.DriveAndTurn;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.commands.DriveCommands.DriveForwards;
import frc.robot.commands.DriveCommands.DriveForwardsFast;
import frc.robot.commands.DriveCommands.DriveForwardsForever;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.DriveCommands.RotateAmount;
import frc.robot.commands.DriveCommands.RotateAmountFast;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.LowerIntake;
import frc.robot.commands.IntakeCommands.RaiseIntake;
import frc.robot.commands.LauncherHoodCommands.HoodExtend;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.swerveDrivetrain;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.limelight;
import frc.robot.commands.*;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.SmartCommands.DriveAndCollect;
import frc.robot.commands.SmartCommands.SmartLaunch;
public class ThreeBallAutoRightSide extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public ThreeBallAutoRightSide(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain, intake intake, limelight limelight){


        addCommands(
            new HoodExtend(launcher),
            new LowerIntake(intake),
            new edu.wpi.first.wpilibj2.command.WaitCommand(.25),
            new IntakeSpin(intake, -1.0),
            new DriveForwards(swerveDrivetrain,50),
            new SmartCollect(hopper, intake).withTimeout(0.75),
            //new DriveAndCollect(hopper, launcher, swerveDrivetrain, intake),
            new StopCollecting(hopper, intake),
            new HopperBSetPower(hopper, 0),
            new RaiseIntake(intake),
            new edu.wpi.first.wpilibj2.command.WaitCommand(.3),
            new DriveBackwards(swerveDrivetrain, 10),
            new RotateAmountFast(swerveDrivetrain, 175, 1, 0.8),
            //new SmartLaunch(hopper, limelight, swerveDrivetrain, launcher),
            new SmartLaunch(hopper, limelight, swerveDrivetrain, launcher),
            new RotateAmountFast(swerveDrivetrain, 75, 1, 0.8),
            //new DriveAndTurn(swerveDrivetrain, 75),
            new LowerIntake(intake),
            new edu.wpi.first.wpilibj2.command.WaitCommand(.25),
            new IntakeSpin(intake, -1.0),
            new DriveForwardsFast(swerveDrivetrain, 85),
            new SmartCollect(hopper, intake).withTimeout(1),
            new StopCollecting(hopper, intake),
            new RotateAmountFast(swerveDrivetrain, 120, -1, 0.8),
            new edu.wpi.first.wpilibj2.command.WaitCommand(.25),
            new DriveForwardsFast(swerveDrivetrain, 15),
            new SmartLaunch(hopper, limelight, swerveDrivetrain, launcher)
            //needs to be modified to pickup just one ball
            //new RotateAmount(swerveDrivetrain, 170, 1, 0.8)
            //new SmartLaunch(hopper, limelight, swerveDrivetrain, launcher),
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




