
package frc.robot.commands.DriveCommands;

import com.fasterxml.jackson.databind.DeserializationConfig;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerveDrivetrain;

public class RotateAmountFast extends CommandBase {
  private final swerveDrivetrain m_drivetrain;
  double desiredAngle;
  int direction;
  double rotSpeed;

  /** Creates a new AutoDriveSwerve. */
  public RotateAmountFast(swerveDrivetrain drivetrain, int des, int dir, double speed) { //dir = 1 is counter clockwise, dir = -1 is clockwise
    m_drivetrain=drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    desiredAngle = des;
    //(desiredAngle/360)*(2*Math.PI*14)
    desiredAngle/=360;
    desiredAngle*=2;
    desiredAngle*=Math.PI;
    desiredAngle*=15.75;
    direction = dir;

    speed = rotSpeed;

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetAllDriveEncoders();
    System.out.println("initializedROTAMOUNT");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("average drive encoder value" +m_drivetrain.getAverageEncoderValue());
   
    final var xSpeed =0.0;

    final var ySpeed =0.0;
   //add if conditional
   
    
    final var rot = 1 * direction;

    m_drivetrain.drive(xSpeed, ySpeed, rot, false, false);
    System.out.println("executingROTAMOUNT");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ended");
    m_drivetrain.drive(0, 0, 0, false, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      if(m_drivetrain.getAverageEncoderValue() > desiredAngle ){ //(desiredAngle/360)*(2*Math.PI*14)
        System.out.println("ROTAMOUNTFINNISHED");
        return true;
      }
      else return false;

    //return m_drivetrain.averageDistanceReached();
  }
}
