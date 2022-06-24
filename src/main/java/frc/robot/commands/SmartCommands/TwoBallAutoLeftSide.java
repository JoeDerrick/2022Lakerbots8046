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
import frc.robot.commands.LauncherHoodCommands.*;
public class TwoBallAutoLeftSide extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public TwoBallAutoLeftSide(hopper hopper, launcher launcher, swerveDrivetrain swerveDrivetrain, intake intake, limelight limelight){


        addCommands(
            new HoodExtend(launcher),    
            new LowerIntake(intake),
            new edu.wpi.first.wpilibj2.command.WaitCommand(.25),
            new IntakeSpin(intake, -1.0),
            new DriveForwards(swerveDrivetrain, 40),
            new SmartCollect(hopper, intake).withTimeout(1.0),
            //new DriveAndCollect(hopper, launcher, swerveDrivetrain, intake),
            new StopCollecting(hopper, intake),
            new HopperBSetPower(hopper, 0),
            new RaiseIntake(intake),
            new RotateAmount(swerveDrivetrain, 180, -1,0.5),
            new SmartLaunchWithReverse(hopper, limelight, swerveDrivetrain, launcher)
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




