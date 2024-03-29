package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.HopperCommands.HopperASetPower;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.LauncherCommands.LauncherCombinedSpeed;
import frc.robot.commands.LauncherCommands.LauncherGo;
import frc.robot.commands.LauncherHoodCommands.HoodRetract;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;


// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: SequentialCommandGroup.



public class LaunchHighGoalTarmac extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public LaunchHighGoalTarmac(hopper hopper, launcher launcher){
        //hopper = subsystem;
        //addRequirements(hopper);
        double leadPower = 0.80;//0.65
        double rearPower = 0.05;//-0.3

        addCommands(
            new LauncherCombinedSpeed(launcher, leadPower, rearPower),
            new HoodRetract(launcher),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.75),
            new HopperASetPower(hopper, -0.5),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.5),
            new HopperBSetPower(hopper, -0.5),
            new edu.wpi.first.wpilibj2.command.WaitCommand(3),
            new LauncherGo(launcher, 0),
            new HopperASetPower(hopper, 0),
            new HopperBSetPower(hopper, 0)
            //new HopperAdvanceB(hopper, -38000)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




