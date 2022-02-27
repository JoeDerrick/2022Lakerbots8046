// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Utils.InstrumFX;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import frc.robot.subsystems.climber;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
public class climber extends SubsystemBase {
    //  ID=CONSTANTS


    // ID=DECLARATIONS
private WPI_TalonFX climberLeft;
private WPI_TalonFX climberRight;
private DoubleSolenoid armRelease;

Joystick _joy = new Joystick(0);  

StringBuilder _sb = new StringBuilder();

int _loops = 0;

int _smoothing = 0;

int _pov = -1;

public climber() {
        // ID=CONSTRUCTORS
climberLeft = new WPI_TalonFX(18);
climberRight = new WPI_TalonFX(17);
 
 

armRelease = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, 4, 5);
 addChild("armRelease", armRelease);
 
climberRight.configFactoryDefault();

climberRight.configNeutralDeadband(0.001);

climberRight.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
    Constants.kPIDLoopIdx,
    Constants.kTimeoutMs);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    
/* Config the peak and nominal outputs */
climberRight.configNominalOutputForward(0, Constants.kTimeoutMs);
climberRight.configNominalOutputReverse(0, Constants.kTimeoutMs);
climberRight.configPeakOutputForward(1, Constants.kTimeoutMs);
climberRight.configPeakOutputReverse(-1, Constants.kTimeoutMs);

/* Config the Velocity closed loop gains in slot0 */
climberRight.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
climberRight.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
climberRight.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
climberRight.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
//-----------------------------------------//
climberLeft.configFactoryDefault();

climberLeft.configNeutralDeadband(0.001);

climberLeft.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
    Constants.kPIDLoopIdx,
    Constants.kTimeoutMs);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    
/* Config the peak and nominal outputs */
climberLeft.configNominalOutputForward(0, Constants.kTimeoutMs);
climberLeft.configNominalOutputReverse(0, Constants.kTimeoutMs);
climberLeft.configPeakOutputForward(1, Constants.kTimeoutMs);
climberLeft.configPeakOutputReverse(-1, Constants.kTimeoutMs);

/* Config the Velocity closed loop gains in slot0 */
climberLeft.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
climberLeft.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
climberLeft.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
climberLeft.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
/*
 * Talon FX does not need sensor phase set for its integrated sensor
 * This is because it will always be correct if the selected feedback device is
 * integrated sensor (default value)
 * and the user calls getSelectedSensor* to get the sensor's position/velocity.
 * 
 * https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html#
 * sensor-phase
 */
// climberRight.setSensorPhase(true);
climberLeft.setInverted(true);
climberLeft.setNeutralMode(NeutralMode.Brake);
climberRight.setNeutralMode(NeutralMode.Brake);
}
    

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }
    public void climberRightTune() {
		/* Get gamepad axis - forward stick is positive */
		double leftYstick = -1.0 * _joy.getY(); /* left-side Y for Xbox360Gamepad */
		double rghtYstick = -1.0 * _joy.getRawAxis(5); /* right-side Y for Xbox360Gamepad */
		if (Math.abs(leftYstick) < 0.10) { leftYstick = 0; } /* deadband 10% */
		if (Math.abs(rghtYstick) < 0.10) { rghtYstick = 0; } /* deadband 10% */

		/* Get current Talon FX motor output */
		double motorOutput = climberRight.getMotorOutputPercent();

		/* Prepare line to print */
		_sb.append("\tOut%:");
		_sb.append(motorOutput);
		_sb.append("\tVel:");
		_sb.append(climberRight.getSelectedSensorVelocity(Constants.kPIDLoopIdx));

		/**
		 * Perform Motion Magic when Button 1 is held, else run Percent Output, which can
		 * be used to confirm hardware setup.
		 */
		if (_joy.getRawButton(1)) {
			/* Motion Magic */

			/* 2048 ticks/rev * 10 Rotations in either direction */
			double targetPos = rghtYstick * 2048 * 10.0;
			climberRight.set(TalonFXControlMode.MotionMagic, targetPos);

			/* Append more signals to print when in speed mode */
			_sb.append("\terr:");
			_sb.append(climberRight.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetPos);
		} else {
			/* Percent Output */

			climberRight.set(TalonFXControlMode.PercentOutput, leftYstick);
		}
		if (_joy.getRawButton(2)) {
			/* Zero sensor positions */
			climberRight.setSelectedSensorPosition(0);
		}

		int pov = _joy.getPOV();
		if (_pov == pov) {
			/* no change */
		} else if (_pov == 180) { // D-Pad down
			/* Decrease smoothing */
			_smoothing--;
			if (_smoothing < 0)
				_smoothing = 0;
			climberRight.configMotionSCurveStrength(_smoothing);

			System.out.println("Smoothing is set to: " + _smoothing);
		} else if (_pov == 0) { // D-Pad up
			/* Increase smoothing */
			_smoothing++;
			if (_smoothing > 8)
				_smoothing = 8;
			climberRight.configMotionSCurveStrength(_smoothing);

			System.out.println("Smoothing is set to: " + _smoothing);
		}
		_pov = pov; /* save the pov value for next time */

		/* Instrumentation */
		InstrumFX.Process(climberRight, _sb);
	}
	public void climberLeftTune() {
		/* Get gamepad axis - forward stick is positive */
		double leftYstick = -1.0 * _joy.getY(); /* left-side Y for Xbox360Gamepad */
		double rghtYstick = -1.0 * _joy.getRawAxis(5); /* right-side Y for Xbox360Gamepad */
		if (Math.abs(leftYstick) < 0.10) { leftYstick = 0; } /* deadband 10% */
		if (Math.abs(rghtYstick) < 0.10) { rghtYstick = 0; } /* deadband 10% */

		/* Get current Talon FX motor output */
		double motorOutput = climberLeft.getMotorOutputPercent();

		/* Prepare line to print */
		_sb.append("\tOut%:");
		_sb.append(motorOutput);
		_sb.append("\tVel:");
		_sb.append(climberLeft.getSelectedSensorVelocity(Constants.kPIDLoopIdx));

		/**
		 * Perform Motion Magic when Button 1 is held, else run Percent Output, which can
		 * be used to confirm hardware setup.
		 */
		if (_joy.getRawButton(1)) {
			/* Motion Magic */

			/* 2048 ticks/rev * 10 Rotations in either direction */
			double targetPos = rghtYstick * 2048 * 10.0;
			climberLeft.set(TalonFXControlMode.MotionMagic, targetPos);

			/* Append more signals to print when in speed mode */
			_sb.append("\terr:");
			_sb.append(climberLeft.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetPos);
		} else {
			/* Percent Output */

			climberLeft.set(TalonFXControlMode.PercentOutput, leftYstick);
		}
		if (_joy.getRawButton(2)) {
			/* Zero sensor positions */
			climberLeft.setSelectedSensorPosition(0);
		}

		int pov = _joy.getPOV();
		if (_pov == pov) {
			/* no change */
		} else if (_pov == 180) { // D-Pad down
			/* Decrease smoothing */
			_smoothing--;
			if (_smoothing < 0)
				_smoothing = 0;
			climberLeft.configMotionSCurveStrength(_smoothing);

			System.out.println("Smoothing is set to: " + _smoothing);
		} else if (_pov == 0) { // D-Pad up
			/* Increase smoothing */
			_smoothing++;
			if (_smoothing > 8)
				_smoothing = 8;
			climberLeft.configMotionSCurveStrength(_smoothing);

			System.out.println("Smoothing is set to: " + _smoothing);
		}
		_pov = pov; /* save the pov value for next time */

		/* Instrumentation */
		InstrumFX.Process(climberLeft, _sb);
	}
	public void climberRightSetPos(double value) {
        climberRight.set(ControlMode.MotionMagic, value); //insert number?
      }

      public void climberLeftSetPos(double value) {
        climberLeft.set(ControlMode.MotionMagic, value); //insert number?
      }
          
      
        //Create Method for reseting encoder

      public void resetclimberRight(){
        climberRight.setSelectedSensorPosition(0);
      }

      public void resetclimberLeft(){
        climberLeft.setSelectedSensorPosition(0);
      }

      public void climberRightSetSpeed(double speed) {
          climberRight.set(ControlMode.Velocity, speed);    
      }

      public void climberLeftSetSpeed(double speed) { 
        climberLeft.set(ControlMode.Velocity, speed);
      }
      
      public void climberRightSetPower(double power) {
        climberRight.set(ControlMode.PercentOutput, power);
      }

      public void climberLeftSetPower(double power) {
        climberLeft.set(ControlMode.PercentOutput, power);
      }	

	  public void climbBothPOwer(double power){
		climberLeft.set(ControlMode.PercentOutput, power);
		climberRight.set(ControlMode.PercentOutput, power);
	  }

      public double climberRightposition(){
        return climberRight.getSelectedSensorPosition();
      }
      public double climberLeftposition(){
        return climberLeft.getSelectedSensorPosition();
      }

}

