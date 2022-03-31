// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands.LauncherCommands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.limelight;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */

public class LauncherLimelightLaunch extends CommandBase {
    private launcher launcher;
    private limelight limelight;
    private double valueLead;
    private double valueFollow;
    private double y;
    private double thresholdForHood = -5.0;

        public LauncherLimelightLaunch(launcher subsystem,limelight controller){

            launcher = subsystem;
            this.limelight= controller;
          
        addRequirements(launcher, controller);
        
     
    }
    
        // Called when the command is initially scheduled.
    
        @Override
    public void initialize() {
    
       // System.out.println("Go!");
    }
    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        y =limelight.getState().yOffset;
        //close range
        if(y>thresholdForHood){
            launcher.retractPiston();
            valueLead = y*690+3040;
            valueFollow = y*-863+16150;
        }
        //long range
        else if(y<(thresholdForHood)){
            launcher.extendPiston();
            valueLead = y*-96+15868;// y = slope*distance+ baseline//18331
            valueFollow = y*-193.9-562;
        }
        

        launcher.launcherVelocitySet(valueLead);
        launcher.launcherFollowVelocitySet(-valueFollow);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
       
        return false;

        
    }
}

