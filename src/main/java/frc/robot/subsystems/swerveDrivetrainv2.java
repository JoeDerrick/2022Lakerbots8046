// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class swerveDrivetrainv2 extends SubsystemBase {

  // these are limits you can change!!!
  public static final double kMaxSpeed = Units.feetToMeters(13.6); // 20 feet per second//13.6
  public static final double kMaxAngularSpeed = Math.PI; // 1/2 rotation per second
  public static double feildCalibration = 0;

  // this is where you put the angle offsets you got from the smart dashboard
  // Need to be tuned!!!

  public static double frontLeftOffset = 5.0;// //module _0_
  public static double frontRightOffset = 151.0;// //module _1_
  public static double backLeftOffset = 170.0;// //module _2_
  public static double backRightOffset = 291.0;// //module _3_
  // note errata Cancoders can't have an ID higher than 15 if they are to be used
  // as remote sensors on talon fx

  // put your can Id's here!
  public static final int frontLeftDriveId = 1;
  public static final int frontLeftCANCoderId = 11;
  public static final int frontLeftSteerId = 2;
  // put your can Id's here!
  public static final int frontRightDriveId = 5;
  public static final int frontRightCANCoderId = 13;
  public static final int frontRightSteerId = 6;
  // put your can Id's here!
  public static final int backLeftDriveId = 7;
  public static final int backLeftCANCoderId = 9;
  public static final int backLeftSteerId = 8;
  // put your can Id's here!

  public static final int backRightDriveId = 3;
  public static final int backRightCANCoderId = 15;
  public static final int backRightSteerId = 4;

  // public static TalonSRX _pigeonTalon = new TalonSRX(10);//removed talon and
  // added pigeon as custom cicuit
  //public static PigeonIMU _PigeonIMU = new PigeonIMU(10);//changed from PIGEONIMU to WPI_ PIGEON IMU to access get rotation2D

  public static WPI_PigeonIMU _PigeonIMU = new WPI_PigeonIMU(10);

  public final static SwerveDriveKinematics kinematicsv2 = new SwerveDriveKinematics(
      new Translation2d(
          Units.inchesToMeters(8.75),
          Units.inchesToMeters(8.75)),
      new Translation2d(
          Units.inchesToMeters(8.75),
          Units.inchesToMeters(-8.75)),
      new Translation2d(
          Units.inchesToMeters(-8.75),
          Units.inchesToMeters(8.75)),
      new Translation2d(
          Units.inchesToMeters(-8.75),
          Units.inchesToMeters(-8.75))
  );// a matrix to set relative places of modules on the chassis.
  // measure the distance from the center of the robot to center of each wheel

  //-------------------Odometry class for tracking robot pose-----------------//
  SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(kinematicsv2, _PigeonIMU.getRotation2d());// +/-180?

  private final swerveModules m_frontLeft = 
    new swerveModules(
      new TalonFX(frontLeftDriveId),
      new TalonFX(frontLeftSteerId), 
      new CANCoder(frontLeftCANCoderId),
      Rotation2d.fromDegrees(frontLeftOffset)
    );
    private final swerveModules m_frontRight = 
    new swerveModules(
      new TalonFX(frontRightDriveId),
      new TalonFX(frontRightSteerId), 
      new CANCoder(frontRightCANCoderId),
      Rotation2d.fromDegrees(frontLeftOffset)
    );
    private final swerveModules m_backLeft = 
    new swerveModules(
      new TalonFX(backLeftDriveId),
      new TalonFX(backLeftSteerId), 
      new CANCoder(backLeftCANCoderId),
      Rotation2d.fromDegrees(backLeftOffset)
    );
    private final swerveModules m_backRight = 
    new swerveModules(
      new TalonFX(frontLeftDriveId),
      new TalonFX(frontLeftSteerId), 
      new CANCoder(frontLeftCANCoderId),
      Rotation2d.fromDegrees(backLeftOffset)
    );
/*
  private swerveModules[] modules = new swerveModules[] {

      new swerveModules(new TalonFX(frontLeftDriveId), new TalonFX(frontLeftSteerId), new CANCoder(frontLeftCANCoderId),
          Rotation2d.fromDegrees(frontLeftOffset)), // Front Left
      new swerveModules(new TalonFX(frontRightDriveId), new TalonFX(frontRightSteerId),
          new CANCoder(frontRightCANCoderId), Rotation2d.fromDegrees(frontRightOffset)), // Front Right
      new swerveModules(new TalonFX(backLeftDriveId), new TalonFX(backLeftSteerId), new CANCoder(backLeftCANCoderId),
          Rotation2d.fromDegrees(backLeftOffset)), // Back Left
      new swerveModules(new TalonFX(backRightDriveId), new TalonFX(backRightSteerId), new CANCoder(backRightCANCoderId),
          Rotation2d.fromDegrees(backRightOffset)) // Back Right

  };
  */
  

  public swerveDrivetrainv2() {

    resetAllDriveEncoders();

  }
  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }
   /** Zeroes the heading of the robot. */
   public void zeroHeading() {
  _PigeonIMU.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return _PigeonIMU.getRotation2d().getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return _PigeonIMU.getRate() * -1; //(DriveConstants.kGyroReversed ? -1.0 : 1.0) removed as we just brute force the gyro reversed also called other places though
  }

 
   /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    m_odometry.resetPosition(pose, _PigeonIMU.getRotation2d());
  }

  public double getAngle() {
    double[] ypr = new double[3];// here is an array source pigeonAPI:
                                 // https://docs.ctre-phoenix.com/en/stable/ch11_BringUpPigeon.html#pigeon-api
    _PigeonIMU.getYawPitchRoll(ypr); // put the data from the pidgey into this array
    return ypr[0];// look at the first column in the array
  }

  public double getAngularRate() {
    double[] xyz = new double[3];
    _PigeonIMU.getRawGyro(xyz);
    return xyz[2];
  }

  public void resetYaw() {
    _PigeonIMU.setYaw(0, 50);
    // _PigeonIMU.setFusedHeading(0);// try both of these to see how they work
    _PigeonIMU.setAccumZAngle(0, 50);// 50 is ktimeouts
  }

  /**
   * Method to drive the robot using joystick info.
   *
   * @param xSpeed        Speed of the robot in the x direction (forward).
   * @param ySpeed        Speed of the robot in the y direction (sideways).
   * @param rot           Angular rate of the robot.
   * @param fieldRelative Whether the provided x and y speeds are relative to the
   *                      field.
   * @param calibrateGyro button to recalibrate the gyro offset
   */
  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative, boolean calibrateGyro) {

    if (calibrateGyro) {
      // gyro.reset(); //recalibrates gyro offset
      resetYaw();
    }
    var swerveModuleStates = kinematicsv2.toSwerveModuleStates(
    
        fieldRelative // if field relative is true, do the ? line, otherwise do the : line //called a
                      // conditional operator condition ? result_if_true : result_if_false
            ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, _PigeonIMU.getRotation2d())
            : new ChassisSpeeds(xSpeed, ySpeed, rot));

    // using states generated above normalize using the max wheel speed.
    SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, kMaxSpeed);

      m_frontLeft.setDesiredState(swerveModuleStates[0]);
      m_frontRight.setDesiredState(swerveModuleStates[1]);
      m_backLeft.setDesiredState(swerveModuleStates[2]);
      m_backRight.setDesiredState(swerveModuleStates[3]);
      
      /// -----COMMENT THIS OUT/IN FOR SWERVE MODULE ANGLE CONFIGURATION--------//
      // SmartDashboard.putNumber("gyro Angle", getAngle());

    

    // -----smart Dashboard outputs ----// re-write without the fancy states thing
    /*to make it clearer
    for (int i = 0; i < states.length; i++) {
      swerveModules module = modules[i];
      SwerveModuleState state = states[i];
       SmartDashboard.putNumber(String.valueOf(i), module.getWheelPosition()/1350);
    }
    // SmartDashboard.putNumber("Average Drive EncoderValue",
    // getAverageEncoderValue());
    */
  }

  public void setModuleStates(SwerveModuleState[] desiredStates){
    SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kMaxSpeedMetersPerSecond);
    m_frontLeft.setDesiredState(desiredStates[0]);
    m_frontRight.setDesiredState(desiredStates[1]);
    m_backLeft.setDesiredState(desiredStates[2]);
    m_backRight.setDesiredState(desiredStates[3]);
  }

  public double getAverageEncoderValue() {

    return (((Math.abs(m_frontLeft.getWheelPosition()) +
        Math.abs(m_frontRight.getWheelPosition()) +
        Math.abs(m_backLeft.getWheelPosition()) +
        Math.abs(m_backRight.getWheelPosition())// Do the math to make this into inches
    ) / 4) / (1350)); // 1280 ticks per revolution (2048 ticks per rev x 6.8 Gear Ratio / 2pi x3.5
                      // inches per rev)

  }

  public boolean averageDistanceReached() {

    if (getAverageEncoderValue() > 300) {
      return false;

    } else
      return true;
  }

  public void resetAllDriveEncoders() {
   
    m_frontLeft.resetDriveEncoder();
    m_frontRight.resetDriveEncoder();
    m_backLeft.resetDriveEncoder();
    m_backRight.resetDriveEncoder();
  }


 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // SmartDashboard.putNumber("Updated Encoder Val", getAverageEncoderValue());
   System.out.println("test");
    m_odometry.update(
      _PigeonIMU.getRotation2d(),
      m_frontLeft.getState(),
      m_frontRight.getState(),
      m_backLeft.getState(),
      m_backRight.getState());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
