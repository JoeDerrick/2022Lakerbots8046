
package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerveDrivetrain;

public class AutoDrive extends CommandBase {
  private final swerveDrivetrain m_drivetrain;
  /** Creates a new AutoDriveSwerve. */
  public AutoDrive(swerveDrivetrain drivetrain) {
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

    final var ySpeed =0.2;
   
    final var rot = 0.0;

    m_drivetrain.drive(xSpeed, ySpeed, rot, true, false);
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
      if (m_drivetrain.getAverageEncoderValue()<50){
        return false;
      }
      return true;

    //return m_drivetrain.averageDistanceReached();
  }
}
