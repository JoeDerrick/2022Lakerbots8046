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

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;;

/**
 *
 */
public class launcher extends SubsystemBase {

	private WPI_TalonFX launcherLead;
	private WPI_TalonFX launcherFollow;
	private DoubleSolenoid hoodAngle;
	/* Hardware */
	Joystick _joy = new Joystick(1);

	/* String for output */
	StringBuilder _sb = new StringBuilder();

	/* Loop tracker for prints */
	int _loops = 0;


	/**
	*
	*/
	public launcher() {
		// BEGIN ID=CONSTRUCTORS
		launcherLead = new WPI_TalonFX(21);
		launcherFollow = new WPI_TalonFX(14);

		hoodAngle = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
		addChild("hoodAngle", hoodAngle);
		//---start here---//
		/* Factory Default all hardware to prevent unexpected behaviour */
		launcherLead.configFactoryDefault();
		launcherFollow.configFactoryDefault();



		/* Config neutral deadband to be the smallest possible */
		launcherLead.configNeutralDeadband(0.001);
		launcherFollow.configNeutralDeadband(0.001);
		/* Config sensor used for Primary PID [Velocity] */
		launcherLead.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
				Constants.kPIDLoopIdx,
				Constants.kTimeoutMs);
		launcherFollow.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
				Constants.kPIDLoopIdx,
				Constants.kTimeoutMs);
		/* Config the peak and nominal outputs */
		launcherLead.configNominalOutputForward(0, Constants.kTimeoutMs);
		launcherLead.configNominalOutputReverse(0, Constants.kTimeoutMs);
		launcherLead.configPeakOutputForward(1, Constants.kTimeoutMs);
		launcherLead.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		launcherFollow.configNominalOutputForward(0, Constants.kTimeoutMs);
		launcherFollow.configNominalOutputReverse(0, Constants.kTimeoutMs);
		launcherFollow.configPeakOutputForward(1, Constants.kTimeoutMs);
		launcherFollow.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		/* Config the Velocity closed loop gains in slot0 */
		launcherLead.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
		launcherLead.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
		launcherLead.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
		launcherLead.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);

		launcherFollow.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
		launcherFollow.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
		launcherFollow.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
		launcherFollow.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);

		//---end here----//
		/*
		 * Talon FX does not need sensor phase set for its integrated sensor
		 * This is because it will always be correct if the selected feedback device is
		 * integrated sensor (default value)
		 * and the user calls getSelectedSensor* to get the sensor's position/velocity.
		 * 
		 * https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html#
		 * sensor-phase
		 */
		// launcherLead.setSensorPhase(true);
		//launcherFollow.follow(launcherLead);
		launcherFollow.setInverted(true);
	}
	
	
	@Override
	public void periodic() {
			//SmartDashboard.putNumber("Lead Percent Speed", +launcherLead.getSelectedSensorVelocity()/19000);
			//SmartDashboard.putNumber("Follow Percent Speed", +launcherFollow.getSelectedSensorVelocity()/-19000);
		// This method will be called once per scheduler run

	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run when in simulation

	}
	public void teleopPeriodic() {

	}


	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void launcherLeadPercentPower(double speed) {
		launcherLead.set(ControlMode.PercentOutput, speed);
	}
	public void launcherFollowPercentPower(double speed) {
		launcherFollow.set(ControlMode.PercentOutput, speed);
	}

	public void launcherVelocitySet(double speed){
		launcherLead.set(ControlMode.Velocity,speed);
	}
	public void launcherFollowVelocitySet(double speed){
		launcherFollow.set(ControlMode.Velocity,speed);
	}

	public void extendPiston() {
		hoodAngle.set(kForward);
		//System.out.println("method reached");
	}

	public void retractPiston() {
		hoodAngle.set(kReverse);
		//System.out.println("method reached");
	}
	

	 

	
	public void launcherLeadTune() {
		/* Get gamepad axis */
		double leftYstick = -1 * _joy.getY();

		/* Get Talon/Victor's current output percentage */
		double motorOutput = launcherLead.getMotorOutputPercent();

		/* Prepare line to print */
		_sb.append("\tout:");
		/* Cast to int to remove decimal places */
		_sb.append((int) (motorOutput * 100));
		_sb.append("%"); // Percent

		_sb.append("\tspd:");
		_sb.append(launcherLead.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
		_sb.append("u"); // Native units

		/**
		 * When button 1 is held, start and run Velocity Closed loop.
		 * Velocity Closed Loop is controlled by joystick position x500 RPM, [-500, 500]
		 * RPM
		 */
		if (_joy.getRawButton(4)) {
			/* Velocity Closed Loop */

			/**
			 * Convert 2000 RPM to units / 100ms.
			 * 2048 Units/Rev * 2000 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
			double targetVelocity_UnitsPer100ms = leftYstick * 2000.0 * 2048.0 / 600.0;
			/* 2000 RPM in either direction */
			launcherLead.set(TalonFXControlMode.Velocity, targetVelocity_UnitsPer100ms);

			/* Append more signals to print when in speed mode. */
			_sb.append("\terr:");
			_sb.append(launcherLead.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetVelocity_UnitsPer100ms);
		} else {
			/* Percent Output */

			launcherLead.set(TalonFXControlMode.PercentOutput, leftYstick);
		}

		/* Print built string every 10 loops */
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
		}
		/* Reset built string */
		_sb.setLength(0);
	}
	public void launcherFollowTune() {
		/* Get gamepad axis */
		double leftYstick = -1 * _joy.getY();

		/* Get Talon/Victor's current output percentage */
		double motorOutput = launcherFollow.getMotorOutputPercent();

		/* Prepare line to print */
		_sb.append("\tout:");
		/* Cast to int to remove decimal places */
		_sb.append((int) (motorOutput * 100));
		_sb.append("%"); // Percent

		_sb.append("\tspd:");
		_sb.append(launcherFollow.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
		_sb.append("u"); // Native units

		/**
		 * When button 1 is held, start and run Velocity Closed loop.
		 * Velocity Closed Loop is controlled by joystick position x500 RPM, [-500, 500]
		 * RPM
		 */
		if (_joy.getRawButton(4)) {
			/* Velocity Closed Loop */

			/**
			 * Convert 2000 RPM to units / 100ms.
			 * 2048 Units/Rev * 2000 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
			double targetVelocity_UnitsPer100ms = leftYstick * 2000.0 * 2048.0 / 600.0;
			/* 2000 RPM in either direction */
			launcherFollow.set(TalonFXControlMode.Velocity, targetVelocity_UnitsPer100ms);

			/* Append more signals to print when in speed mode. */
			_sb.append("\terr:");
			_sb.append(launcherFollow.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetVelocity_UnitsPer100ms);
		} else {
			/* Percent Output */

			launcherFollow.set(TalonFXControlMode.PercentOutput, leftYstick);
		}

		/* Print built string every 10 loops */
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
		}
		/* Reset built string */
		_sb.setLength(0);
	}
	

}

