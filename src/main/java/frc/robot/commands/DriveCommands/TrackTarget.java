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

public class TrackTarget extends CommandBase {
  private limelight m_limelight;
  private swerveDrivetrain m_SwerveDrivetrain;

double Kp = 0.1;
double min_command = 0.05;
  
  public TrackTarget(limelight m_lLimelight, swerveDrivetrain m_SwerveDrivetrain) {
    m_limelight = m_lLimelight;
    m_SwerveDrivetrain= m_SwerveDrivetrain;
    addRequirements(m_limelight, m_SwerveDrivetrain);
    
  }
  public void initialize(){
    System.out.println("Limelight mode : ACTIVATE");
    m_limelight.changeLEDMode(3);  
 }

 public void execute(){

  //add the if else part functionality here to handle turning both directions. 
   
  //double rot = Kp*(m_limelight.getx()) + min_command;
    //m_SwerveDrivetrain.drive(0,0, rot, false, false);
  }
 
 public boolean isFinished(){
     return false;
 }

 public void end() {

 }

 public void interrupted(){
     end();
 }

}
