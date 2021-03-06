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

package frc.robot.commands.ClimberCommands;
import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;


import frc.robot.subsystems.climber;


/**
 *
 */
public class ClimbTogether extends CommandBase {

    
        private final climber m_climber;
        private double value;
        private final XboxController xBoxController1;


    public ClimbTogether(climber subsystem, XboxController controller) {

        m_climber = subsystem;
       
        addRequirements(m_climber);
        this.xBoxController1 = controller;
       
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //called once when inititialized
    m_climber.resetclimberLeft();
    m_climber.resetclimberRight();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        /*
if(xBoxController1.getLeftBumper()){
    m_climber.resetclimberLeft();
    m_climber.resetclimberRight();
    };
    */

        if(Math.abs(m_climber.getClimberLeftposition()) < Math.abs(m_climber.getClimberRightposition())){
           m_climber.climberLeftSetPower(xBoxController1.getLeftY()*0.3);
        m_climber.climberRightSetPower(xBoxController1.getLeftY()*0.4); 
        }
        else if(Math.abs(m_climber.getClimberLeftposition()) > Math.abs(m_climber.getClimberRightposition())){
        m_climber.climberLeftSetPower(xBoxController1.getLeftY()*0.4);
        m_climber.climberRightSetPower(xBoxController1.getLeftY()*0.3); 
        }
        else{
            m_climber.climberLeftSetPower(xBoxController1.getLeftY()*0.4);
            m_climber.climberRightSetPower(xBoxController1.getLeftY()*0.4); 
        }
        System.out.println("\nleftencoder" + m_climber.getClimberLeftposition() + "       rightencoder" + m_climber.getClimberRightposition() + "\n" );

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
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
