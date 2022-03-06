
package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerveDrivetrain;

public class RotateAmount extends CommandBase {
  private final swerveDrivetrain m_drivetrain;
  double desired;
  int direction;
  /** Creates a new AutoDriveSwerve. */
  public RotateAmount(swerveDrivetrain drivetrain, int desired) {
    m_drivetrain=drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetAllDriveEncoders();
    System.out.println("initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("average drive encoder value" +m_drivetrain.getAverageEncoderValue());
   
    final var xSpeed =0.0;

    final var ySpeed =0.0;
   //add if conditional
   
    if(Math.round(m_drivetrain.getAngle()) < desired){
      direction = 1;
    }
    else{
      direction =  - 1;
    }

  if(Math.abs(Math.round(m_drivetrain.getAngle()) - desired) > 180){
    direction = -direction;
  }
    final var rot = 0.3 * direction;

    m_drivetrain.drive(xSpeed, ySpeed, rot, false, false);
    System.out.println("executing");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      if(Math.round(m_drivetrain.getAngle()) == desired){
        return true;
      }
      else return false;

    //return m_drivetrain.averageDistanceReached();
  }
}
