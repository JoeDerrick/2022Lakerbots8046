
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


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Utils.InstrumSRX;

/**
 *
 */
public class hopper extends SubsystemBase {
    private DigitalInput digitalCargoSensorA = new DigitalInput(2);
    private DigitalInput digitalCargoSensorB = new DigitalInput(1);

    Joystick _joy = new Joystick(1);

    StringBuilder _sb = new StringBuilder();

    int _loops = 0;  

    int _smoothing = 0;

    int _pov = -1;

   
private WPI_TalonSRX hopperA;
private WPI_TalonSRX hopperB;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
  
    public hopper() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
 
        hopperA = new WPI_TalonSRX(12);

        hopperB = new WPI_TalonSRX(16);
 
        
        /* Factory Default all hardware to prevent unexpected behaviour */
        hopperA.configFactoryDefault();
     
         /* Config sensor used for Primary PID [Velocity] */
        hopperA.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative,0,30);
        hopperB.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative,0,30);
                                               
      /**
              * Phase sensor accordingly. 
              * Positive Sensor Reading should match Green (blinking) Leds on Talon
              */
        hopperA.setSensorPhase(false);
        hopperA.setInverted(true);
        hopperA.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic, 10);
        hopperA.setNeutralMode(NeutralMode.Coast);
     
             /* Config the peak and nominal outputs */
        hopperA.configNominalOutputForward(0, 30);
        hopperA.configNominalOutputReverse(0, 30);
        hopperA.configPeakOutputForward(1, 30);
        hopperA.configPeakOutputReverse(-1, 30);
        hopperA.configForwardSoftLimitEnable(false);
        hopperA.configReverseSoftLimitEnable(false);
         
             /* Config the Velocity closed loop gains in slot0 */
        hopperA.config_kF(0, 0.063, 30);
        hopperA.config_kP(0, .1, 30);
        hopperA.config_kI(0, 0, 30);
        hopperA.config_kD(0, 0, 30);
     
        hopperA.configMotionCruiseVelocity(10000,10); //18000// need to tune these
        hopperA.configMotionAcceleration(5000, 10); //8000 need to tune these
      
        hopperA.setSelectedSensorPosition(0,0,10);// resets the encoder on the elevator to zero at the start

        //-------------------------------------------------------------------------------------------------------

        hopperB.setSensorPhase(false);
        hopperB.setInverted(true);
        hopperB.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic, 10);
        hopperB.setNeutralMode(NeutralMode.Coast);
     
             /* Config the peak and nominal outputs */
        hopperB.configNominalOutputForward(0, 30);
        hopperB.configNominalOutputReverse(0, 30);
        hopperB.configPeakOutputForward(1, 30);
        hopperB.configPeakOutputReverse(-1, 30);
        hopperB.configForwardSoftLimitEnable(false);
        hopperB.configReverseSoftLimitEnable(false);
         
             /* Config the Velocity closed loop gains in slot0 */
        hopperB.config_kF(0, 0.063, 30);
        hopperB.config_kP(0, .1, 30);
        hopperB.config_kI(0, 0, 30);
        hopperB.config_kD(0, 0, 30);
     
        hopperB.configMotionCruiseVelocity(10000,10); //18000// need to tune these
        hopperB.configMotionAcceleration(5000, 10); //8000 need to tune these
      
        hopperB.setSelectedSensorPosition(0,0,10);// resets the encoder on the elevator to zero at the start

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    public void tuneHopper() {
    
        /* Get gamepad axis */
          double leftYstick = -1 * _joy.getY();
    
         /* Get Talon/Victor's current output percentage */
          double motorOutput = hopperB.getMotorOutputPercent();
    
          /* Prepare line to print */
          _sb.append("\tout:");
          /* Cast to int to remove decimal places */
          _sb.append((int) (motorOutput * 100));
          _sb.append("%"); //Percent 
         
          _sb.append("\tspd:");
          _sb.append(hopperB.getSelectedSensorVelocity(0));
          _sb.append("u"); // Native units
    
          /** 
             * When button 1 is held, start and run Velocity Closed loop.
             * Velocity Closed Loop is controlled by joystick position x500 RPM, [-500, 500] RPM
             */
            if (_joy.getRawButton(1)) {
          /* Velocity Closed Loop */
    
          /**
                 * Convert 500 RPM to units / 100ms.
                 * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
                 * velocity setpoint is in units/100ms
                 */
          double targetPos= leftYstick *4096*10.0;
                /* 500 RPM in either direction */
          hopperB.set(ControlMode.MotionMagic, targetPos);
          
         /* Append more signals to print when in speed mode. */
          _sb.append("\terr:");
                _sb.append(hopperB.getClosedLoopError(0));
                _sb.append("\ttrg:");
                _sb.append(targetPos);
            } else {
    
          /* Percent Output */
          hopperB.set(ControlMode.PercentOutput, leftYstick);
          
          if (_joy.getRawButton(2)) {
            /* Zero sensor positions */
            hopperB.setSelectedSensorPosition(0);
          }
      
          int pov = _joy.getPOV();
          if (_pov == pov) {
            /* no change */
          } else if (_pov == 180) { // D-Pad down
            /* Decrease smoothing */
            _smoothing--;
            if (_smoothing < 0)
              _smoothing = 0;
            hopperB.configMotionSCurveStrength(_smoothing);
      
            System.out.println("Smoothing is set to: " + _smoothing);
          } else if (_pov == 0) { // D-Pad up
            /* Increase smoothing */
            _smoothing++;
            if (_smoothing > 8)
              _smoothing = 8;
            hopperB.configMotionSCurveStrength(_smoothing);
      
            System.out.println("Smoothing is set to: " + _smoothing);
          }
          _pov = pov; /* save the pov value for next time */
      
          /* Instrumentation */
         
        }
        InstrumSRX.Process(hopperB, _sb);
      }

      //good work but we want to be able to control the motors individually
      // so 
      //public void hopperASetPos( double)...etc
      //and
      //public void hopperBSetPos( double.... etc)


    
      public void hopperASetPos(double value) {
        hopperA.set(ControlMode.MotionMagic, value); //insert number?
      }

      public void hopperBSetPos(double value) {
        hopperB.set(ControlMode.MotionMagic, value); //insert number?
      }
          
      
        //Create Method for reseting encoder

      public void resethopperA(){
        hopperA.setSelectedSensorPosition(0);
      }

      public void resethopperB(){
        hopperB.setSelectedSensorPosition(0);
      }

      public void hopperASetSpeed(double speed) {
          hopperA.set(ControlMode.Velocity, speed);    
      }

      public void hopperBSetSpeed(double speed) { 
        hopperB.set(ControlMode.Velocity, speed);
      }
      
      public void hopperASetPower(double power) {
        hopperA.set(ControlMode.PercentOutput, power);
      }

      public void hopperBSetPower(double power) {
        hopperB.set(ControlMode.PercentOutput, power);
      }
      public double hopperAposition(){
        return hopperA.getSelectedSensorPosition();
      }
      public double hopperBposition(){
        return hopperB.getSelectedSensorPosition();
      }
      public void hopperAdvanceA(double value){
        double pos = hopperAposition() + value;
        hopperA.set(ControlMode.MotionMagic, pos);
      }
      public void hopperAdvanceB(double value){
        double pos = hopperBposition() + value;
        hopperB.set(ControlMode.MotionMagic, pos);
      }
      public boolean getDigitalCargoSensorA(){
        System.out.println("checking sensor");
        if (digitalCargoSensorA.get() == true){
          System.out.println("Sensor checked true");
          return false;
        }
        else{
          System.out.println("Sensor checked false");
          return true;
        }
      }

      public boolean getDigitalCargoSensorB(){
       // System.out.println("checking sensor");
        if (digitalCargoSensorB.get() == true){
         // System.out.println("Sensor checked true");
          return false;
        }
        else{
         // System.out.println("Sensor checked false");
          return true;
        }
      }
        
        public boolean hopperAAtPosition(double value){
            double threshold = 500;
          if (Math.abs(hopperA.getSelectedSensorPosition())-value > threshold){
            return false;}
          else {return true;}
          
        }
        public boolean hopperBAtPosition(double value){
          double threshold = 500;
        if (Math.abs(hopperB.getSelectedSensorPosition())-value > threshold){
          return false;}
        else {return true;}
        
      }
      
    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void HopperASetBrakeMode(){
      hopperA.setNeutralMode(NeutralMode.Brake);
    }
    public void HopperASetCoastMode(){
      hopperA.setNeutralMode(NeutralMode.Coast);
    }
    public void HopperBSetBrakeMode(){
      hopperA.setNeutralMode(NeutralMode.Brake);
    }
    public void HopperBSetCoastMode(){
      hopperA.setNeutralMode(NeutralMode.Coast);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

