
package frc.robot.commands.PathFollowingCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.robot.subsystems.swerveDrivetrain;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import frc.robot.subsystems.swerveDrivetrainv2;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;

import java.util.List;

import frc.robot.Constants;

/*
public class SwervePathFollow2 extends CommandBase {

 // private static final Subsystem swerveDrivetrainv2 = null;
  public swerveDrivetrainv2 swerveDrivetrainv2;
  // Creates a new AutoDriveSwerve. 
  public SwervePathFollow2(swerveDrivetrainv2 subsystem) {
    swerveDrivetrainv2=subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerveDrivetrainv2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    TrajectoryConfig config =
    new TrajectoryConfig(
            Constants.AutoConstants.kMaxSpeedMetersPerSecond,
            Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared).setKinematics(swerveDrivetrainv2.kinematicsv2);
   
   // An example trajectory to follow.  All units in meters.
   Trajectory exampleTrajectory =
   TrajectoryGenerator.generateTrajectory(
   // Start at the origin facing the +X direction
   new Pose2d(0, 0, new Rotation2d(0)),
   // Pass through these two interior waypoints, making an 's' curve path
   List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
   // End 3 meters straight ahead of where we started, facing forward
   new Pose2d(3, 0, new Rotation2d(0)), config); 
   
   var thetaController =
    new ProfiledPIDController(
        Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
thetaController.enableContinuousInput(-Math.PI, Math.PI);

SwerveControllerCommand swerveControllerCommand =
new SwerveControllerCommand(
    exampleTrajectory,
    swerveDrivetrainv2::getPose, // Functional interface to feed supplier
    swerveDrivetrainv2.kinematicsv2,

    // Position controllers
    new PIDController(Constants.AutoConstants.kPXController, 0, 0),
    new PIDController(Constants.AutoConstants.kPYController, 0, 0),
    thetaController,
    swerveDrivetrainv2::setModuleStates,
    swerveDrivetrainv2);


    // Reset odometry to the starting pose of the trajectory.
    swerveDrivetrainv2.resetOdometry(exampleTrajectory.getInitialPose());
     // Run path following command, then stop at the end.
    return swerveControllerCommand.andThen(() -> swerveDrivetrainv2.drive(0, 0, 0, false));
    
  }

 
}
*/
