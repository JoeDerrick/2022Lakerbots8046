package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.RotateToTarget;
import frc.robot.commands.HopperCommands.HopperASetPower;
import frc.robot.commands.HopperCommands.HopperASetSpeed;
import frc.robot.commands.HopperCommands.HopperAdvanceA;
import frc.robot.commands.HopperCommands.HopperAdvanceB;
import frc.robot.commands.HopperCommands.HopperBSetPower;
import frc.robot.commands.HopperCommands.HopperBSetSpeed;
import frc.robot.commands.HopperCommands.WaitForCargo;
import frc.robot.commands.LauncherCommands.LauncherGo;
import frc.robot.commands.LauncherCommands.LauncherTestBoth;
import frc.robot.commands.LauncherHoodCommands.HoodExtend;
import frc.robot.commands.LimeLightCommands.turnOffLEDs;
import frc.robot.commands.LimeLightCommands.turnOnLEDs;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.swerveDrivetrain;
import frc.robot.commands.DriveCommands.*;
import frc.robot.commands.SmartCommands.*;
public class SmartRotateToTarget extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public SmartRotateToTarget(hopper hopper, launcher launcher, double leadSpeed, double rearSpeed, limelight limelight, swerveDrivetrain swerveDrivetrain){
        
        addCommands(
           
            new turnOnLEDs(limelight),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.25),
            new RotateToTarget(limelight, swerveDrivetrain),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.25),
            new turnOffLEDs(limelight),
            new LaunchLowGoal(hopper, launcher)
            );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




