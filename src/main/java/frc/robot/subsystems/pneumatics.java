// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class pneumatics extends SubsystemBase {
   
 
private Compressor compressor;
//private DoubleSolenoid armRelease;
//private DoubleSolenoid changeHoodAngleDoubleSolenoid;

  
    public pneumatics() {
    
   
    
        
compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
 addChild("compressor",compressor);
 compressor.enableDigital();
 
 //compressor.disable();
 //add compressor enabling here
 

    
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Pressure", compressor.getPressure());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

