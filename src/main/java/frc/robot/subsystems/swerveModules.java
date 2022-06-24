package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;


public class swerveModules {

private static final double kDriveP = 30.0;
private static final double kDriveI = 0.01;
private static final double kDriveD = 0.1;
private static final double kDriveF = 0.2;

private static final double kAngleP = 1.5;//1.5
private static final double kAngleI = 0.0;
private static final double kAngleD = 0.0;

private static double kEncoderTicksPerRotation = 4096;

private TalonFX driveMotor;
private TalonFX angleMotor;
private CANCoder canCoder;
private Rotation2d offset;

public swerveModules(TalonFX driveMotor, TalonFX angleMotor, CANCoder canCoder, Rotation2d offset) {
 this.driveMotor = driveMotor;
 this.angleMotor = angleMotor;
 this.canCoder = canCoder;
 this.offset = offset;

 TalonFXConfiguration angleTalonFXConfiguration = new TalonFXConfiguration();
 
 angleTalonFXConfiguration.slot0.kP = kAngleP;
 angleTalonFXConfiguration.slot0.kI = kAngleI;
 angleTalonFXConfiguration.slot0.kD = kAngleD;

 angleTalonFXConfiguration.remoteFilter0.remoteSensorDeviceID = canCoder.getDeviceID();
 angleTalonFXConfiguration.remoteFilter0.remoteSensorSource = RemoteSensorSource.CANCoder;
 
 angleTalonFXConfiguration.primaryPID.selectedFeedbackSensor = FeedbackDevice.RemoteSensor0;

 angleMotor.configAllSettings(angleTalonFXConfiguration);
 angleMotor.setNeutralMode(NeutralMode.Brake);


 TalonFXConfiguration driveTalonFXConfiguration = new TalonFXConfiguration();

 driveTalonFXConfiguration.slot0.kP = kDriveP;
 driveTalonFXConfiguration.slot0.kI = kDriveI;
 driveTalonFXConfiguration.slot0.kD = kDriveD;
 driveTalonFXConfiguration.slot0.kF = kDriveF;

 driveMotor.configAllSettings(driveTalonFXConfiguration);
 driveMotor.setNeutralMode(NeutralMode.Brake);
 driveMotor.setSensorPhase(false);


 CANCoderConfiguration canCoderConfiguration = new CANCoderConfiguration();
 canCoderConfiguration.magnetOffsetDegrees = offset.getDegrees();
 canCoder.configAllSettings(canCoderConfiguration);
}



 public Rotation2d getAngle() {
     return Rotation2d.fromDegrees(canCoder.getAbsolutePosition());
 }
public double getRawAngle() {
    return canCoder.getAbsolutePosition();
}
public double getDriveSpeed() {
    return driveMotor.getSelectedSensorVelocity();
}
public double getWheelPosition() {
    return driveMotor.getSelectedSensorPosition(0);
}
public void resetDriveEncoder() {
    driveMotor.setSelectedSensorPosition(0, 0, 50);
}


public void setDesiredState(SwerveModuleState desiredState) {

    Rotation2d currentRotation = getAngle();
    SwerveModuleState state = SwerveModuleState.optimize(desiredState, currentRotation);
    
    // Find the difference between our current rotational position + our new rotational position
    Rotation2d rotationDelta = state.angle.minus(currentRotation);
    
    // Find the new absolute position of the module based on the difference in rotation
    double deltaTicks = (rotationDelta.getDegrees() / 360) * kEncoderTicksPerRotation;
    // Convert the CANCoder from it's position reading back to ticks
    double currentTicks = canCoder.getPosition() / canCoder.configGetFeedbackCoefficient();
    double desiredTicks = currentTicks + deltaTicks;

    //below is a line to comment out from step 5
    angleMotor.set(TalonFXControlMode.Position, desiredTicks);

    double feetPerSecond = Units.metersToFeet(state.speedMetersPerSecond);

    //below is a line to comment out from step 5
    driveMotor.set(TalonFXControlMode.PercentOutput, feetPerSecond / swerveDrivetrain.kMaxSpeed);
  }
////-----yeah i just made this up-------/
  public SwerveModuleState getState() {
    return new SwerveModuleState(driveMotor.getSelectedSensorVelocity(), new Rotation2d(canCoder.getPosition()));
  }

}

