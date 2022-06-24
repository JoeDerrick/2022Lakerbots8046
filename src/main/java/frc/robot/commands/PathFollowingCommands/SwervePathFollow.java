package frc.robot.commands.PathFollowingCommands;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.Utils.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.commands.DriveCommands.DriveForwards;
import frc.robot.commands.DriveCommands.Rotate180;
import frc.robot.commands.DriveCommands.RotateAmount;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.LowerIntake;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.swerveDrivetrain;
import frc.robot.subsystems.swerveDrivetrainv2;
import frc.robot.subsystems.intake;
import frc.robot.commands.*;
import frc.robot.commands.DriveCommands.Rotate180;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
/*
public class SwervePathFollow extends SequentialCommandGroup {
    
   // CommandGroupBase.addCommands(SequentialCommandGroup);
    

    public SwervePathFollow( swerveDrivetrainv2 swerveDrivetrainv2){
        addCommands(
           
        // Create config for trajectory
    TrajectoryConfig config =
    new TrajectoryConfig(
            Constants.AutoConstants.kMaxSpeedMetersPerSecond,
            Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared).setKinematics(swerveDrivetrainv2.kinematics);
        

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
        swerveDrivetrainv2.kinematics,

        // Position controllers
        new PIDController(Constants.AutoConstants.kPXController, 0, 0),
        new PIDController(Constants.AutoConstants.kPYController, 0, 0),
        thetaController,
        swerveDrivetrainv2::setModuleStates,
        swerveDrivetrainv2);

        ++++++++++++++++++
// Reset odometry to the starting pose of the trajectory.
swerveDrivetrainv2.resetOdometry(exampleTrajectory.getInitialPose());

// Run path following command, then stop at the end.
return swerveControllerCommand.andThen(() -> swerveDrivetrain.drive(0, 0, 0, false));
    }

    @Override
    public boolean runsWhenDisabled() {
   
        return false;


    }
}

*/


