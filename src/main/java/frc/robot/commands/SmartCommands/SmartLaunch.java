package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;

public class SmartLaunch extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public SmartLaunch(hopper hopper, launcher launcher, double leadSpeed, double rearSpeed){
        
        addCommands(
           
            new LauncherTestBoth(launcher, leadSpeed, rearSpeed),
            new HoodExtend(launcher),
            new edu.wpi.first.wpilibj2.command.WaitCommand(0.5),
            new HopperASetSpeed(hopper, 4000),
            new HopperBSetSpeed(hopper, -2000),
            new edu.wpi.first.wpilibj2.command.WaitCommand(3),
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




