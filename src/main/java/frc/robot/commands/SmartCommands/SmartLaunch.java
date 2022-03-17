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
import frc.robot.commands.LauncherCommands.LauncherLimelightLaunch;
import frc.robot.commands.LauncherCommands.LauncherTestBoth;
import frc.robot.commands.LauncherHoodCommands.HoodExtend;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.swerveDrivetrain;

public class SmartLaunch extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public SmartLaunch(hopper hopper, limelight m_limelight,swerveDrivetrain drivetrain,launcher launcher){
        
        addCommands(
            new LauncherTestBoth(launcher, 0.5, 0.3),//warm up the launcher
            new RotateToTarget( drivetrain,m_limelight), //rotate to target
            new LauncherLimelightLaunch(launcher, m_limelight).withTimeout(1),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.5),
            new HopperASetPower(hopper, -0.5),
            new HopperBSetPower(hopper, -0.5),
            new edu.wpi.first.wpilibj2.command.WaitCommand(1.5),
            new LauncherGo(launcher, 0),
            new HopperASetPower(hopper, 0),
            new HopperBSetPower(hopper, 0)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}




