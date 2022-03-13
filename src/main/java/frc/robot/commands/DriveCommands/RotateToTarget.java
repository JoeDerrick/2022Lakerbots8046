package frc.robot.commands.DriveCommands;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.swerveDrivetrain;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableType;

public class RotateToTarget extends CommandBase {
  private limelight m_limelight;
  private swerveDrivetrain m_drivetrain;
//rotate proportainal to how close you are to target
double Kp = 0.24;
//rotate floor
double min_command = 0.005;
  
  public RotateToTarget(limelight m_lLimelight, swerveDrivetrain drivetrain) {
    m_limelight = m_lLimelight;
    m_drivetrain= drivetrain;
    addRequirements(m_limelight,drivetrain);
    
  }
 public void initialize(){
    System.out.println("Limelight mode : ACTIVATE");
    //if(m_limelight.getLEDState() == 1){
    
    //else{
    //  m_limelight.turnOffLEDs();
    //System.out.println("Limelight mode : change led mode to on");
 }

 public void execute(){
  //add the if else part functionality here handle turning both directions. 
  double rot = 0; // <--- compiler is stopid
  double maxRot = 0.7;

  double x = m_limelight.getState().xOffset;

  //double x = m_limelight.getx();

  //1 is threshold
  if(x > 1.0){
   rot = -Kp*(x);
  }
  else if(x < 1.0){
   rot = -Kp*(x);
  }
    if(rot > maxRot){
      rot = maxRot;
    }
    if(rot < -maxRot){
      rot = -maxRot;
    }
    System.out.println("rot="+rot);
    System.out.println("x="+x);
      m_drivetrain.drive(0,0, rot, false, false); 
  }


 
 public boolean isFinished(){
  double threshold = 5;
  double x = m_limelight.getState().xOffset;
  if(Math.abs(x) < threshold){
    m_drivetrain.drive(0,0, 0, false, false); 
    System.out.println("finished");
    return true;
   
  }  
  else{
    return false; 
  }
    //m_limelight.onRotationalTarget(threshold);
 }

 public void end() {

}

 public void interrupted(){
     end();
 }

}
