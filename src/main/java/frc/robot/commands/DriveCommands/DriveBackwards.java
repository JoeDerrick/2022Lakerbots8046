
package frc.robot.commands.DriveCommands;

import org.ejml.equation.Variable;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerveDrivetrain;

public class DriveBackwards extends CommandBase {
  double amount;
  private final swerveDrivetrain m_drivetrain;
  /** Creates a new AutoDriveSwerve. */
  public DriveBackwards(swerveDrivetrain drivetrain, double amt) {
    m_drivetrain=drivetrain;
    amount = amt;
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
    
   
    final var xSpeed =-0.3;

    final var ySpeed =0.0;
   
    final var rot = 0.0;

    m_drivetrain.drive(xSpeed, ySpeed, rot, false, false);
    System.out.println("executing");
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
      if (m_drivetrain.getAverageEncoderValue() < amount){
        return false;
      }
      return true;

    //return m_drivetrain.averageDistanceReached();
  }
}
