package frc.robot.commands.DriveCommands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.swerveDrivetrain;


public class DriveWithJoystickFieldOriented extends CommandBase {

    private final swerveDrivetrain swerveDrivetrain;
    private limelight m_limelight;
    private final XboxController xboxController0;
  
    // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
    //---
    private final SlewRateLimiter xspeedLimiter = new SlewRateLimiter(2.2);
    private final SlewRateLimiter yspeedLimiter = new SlewRateLimiter(2.2);
    private final SlewRateLimiter rotLimiter = new SlewRateLimiter(2.2);
  
    private final double deadband =0.05;
    public double  rawOutput;
    private double Kp = 0.24; //0.05?//test commit//test
    public double speedLimit = 0.3;
    public double rotateLimit =0.6;
    public double boostedSpeed =  0.5;
    public double rot = 0; // <--- compiler is stopid
    public double maxRot = 0.7;
  
  public DriveWithJoystickFieldOriented( swerveDrivetrain subsystem, XboxController controller, limelight controller2) {
    this.m_limelight = controller2;
    swerveDrivetrain = subsystem;
    addRequirements(m_limelight,swerveDrivetrain);

    this.xboxController0 = controller;
  }

  @Override
  public void execute() {
  //-------DEADBAND MODE---------------------//
    if(rawOutput< deadband && xboxController0.getLeftTriggerAxis() < 0.1 && xboxController0.getRightTriggerAxis() < 0.1){
// all inputs must be under deadband for this mode to be active
      rawOutput = Math.pow(Math.pow(xboxController0.getLeftY(),2)
  + Math.pow(xboxController0.getLeftX(),2),0.5);
     // System.out.println("deadband mode");
    final var xSpeed =0;
    final var ySpeed =0;
    final var rot =0;

    boolean calibrate = xboxController0.getRightBumper();
    swerveDrivetrain.drive(xSpeed, ySpeed, rot, true, calibrate); //set "false" to "true" if you want to use field-oriented driving (In two other places farther down)
    //System.out.println("R-trigger:" + controller.getRightTriggerAxis());
    //System.out.println("L-trigger:" + controller.getLeftTriggerAxis());

  }

  else if(xboxController0.getLeftBumperPressed()){
//vision tracking while driving

    final var xSpeed = 
      -xspeedLimiter.calculate(xboxController0.getLeftY())
      *swerveDrivetrain.kMaxSpeed*speedLimit;

    final var ySpeed = 
      -yspeedLimiter.calculate(xboxController0.getLeftX())
      * swerveDrivetrain.kMaxSpeed*speedLimit;
      
    double x = m_limelight.getState().xOffset;
    //final var rot = -Pyaw*(currentYaw - swerveDrivetrain.getAngle());
    if(x > 1.0){
      rot = -Kp*(x);
     }
     else if(x < 1.0){
      rot = -Kp*(x);
     }
       if(rot > maxRot){
         rot = maxRot;
       }
       if(rot < -maxRot){
         rot = -maxRot;
       }

    //SmartDashboard.putNumber("Current value from gyro", currentYaw);
    //SmartDashboard.putNumber("DriveTrain angle", drivetrain.getAngle());

    boolean calibrate = xboxController0.getRightBumper();

    swerveDrivetrain.drive(xSpeed, ySpeed, rot, true, calibrate);

    rawOutput = Math.pow(Math.pow(xboxController0.getLeftY(),2)
  + Math.pow(xboxController0.getLeftX(),2),0.5);
     
    }
    else if(xboxController0.getRawButtonPressed(9) == true){
      //BOOST MODE

      //Never leaving "Yaw lock mode" so the robot cannot rotate.-Not sure what the trigger values are/create a deadband for them.
      //resolved 12/14/22
      //replaced the rot value with normal triggers when we changed to non field oriented drive, this section is a duplicate of
      // the section below it.
        
          final var xSpeed = 
            -xspeedLimiter.calculate(xboxController0.getLeftY())
            *swerveDrivetrain.kMaxSpeed*boostedSpeed;
      
          final var ySpeed = 
            -yspeedLimiter.calculate(xboxController0.getLeftX())
            * swerveDrivetrain.kMaxSpeed*boostedSpeed;
            
          //final var rot = -Pyaw*(currentYaw - swerveDrivetrain.getAngle());
          final var rot =
          -rotLimiter.calculate(-1*xboxController0.getLeftTriggerAxis()+xboxController0.getRightTriggerAxis())
            * swerveDrivetrain.kMaxAngularSpeed*rotateLimit;
      
      
          //SmartDashboard.putNumber("Current value from gyro", currentYaw);
          //SmartDashboard.putNumber("DriveTrain angle", drivetrain.getAngle());
      
          boolean calibrate = xboxController0.getRightBumper();
      
          swerveDrivetrain.drive(xSpeed, ySpeed, rot, false, calibrate);
      
          rawOutput = Math.pow(Math.pow(xboxController0.getLeftY(),2)
        + Math.pow(xboxController0.getLeftX(),2),0.5);
      
            //System.out.println(+rawOutput + "yaw lock mode");
            
            //System.out.println("R-trigger:" + controller.getRightTriggerAxis());
            //System.out.println("L-trigger:" + controller.getLeftTriggerAxis());
           
      
          }
  
  else{

    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    final var xSpeed =
      -xspeedLimiter.calculate(xboxController0.getLeftY())
        * swerveDrivetrain.kMaxSpeed*speedLimit;//.3 is a speed limiter

    // Get the y speed or sideways/strafe speed. We are inverting this because
    // we want a positive value when we pull to the left. Xbox controllers
    // return positive values when you pull to the right by default.
    final var ySpeed =
      -yspeedLimiter.calculate(xboxController0.getLeftX())
        * swerveDrivetrain.kMaxSpeed*speedLimit;
    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.
    final var rot =
      -rotLimiter.calculate(-1*xboxController0.getLeftTriggerAxis()+xboxController0.getRightTriggerAxis())
        * swerveDrivetrain.kMaxAngularSpeed*rotateLimit;

    boolean calibrate = xboxController0.getRightBumper();

    swerveDrivetrain.drive(xSpeed, ySpeed, rot, true, calibrate);

    rawOutput = Math.pow(Math.pow(xboxController0.getLeftY(),2)
  + Math.pow(xboxController0.getLeftX(),2),0.5);
    }
  }
  

}
