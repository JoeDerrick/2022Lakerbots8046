package frc.robot.commands.DriveCommands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerveDrivetrain;


public class DriveWithJoystick extends CommandBase {

    private final swerveDrivetrain swerveDrivetrain;
    private final XboxController xboxController0;
  
    // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
    //---
    private final SlewRateLimiter xspeedLimiter = new SlewRateLimiter(2.2);
    private final SlewRateLimiter yspeedLimiter = new SlewRateLimiter(2.2);
    private final SlewRateLimiter rotLimiter = new SlewRateLimiter(2.2);
  
    private final double deadband =0.2;
    public double  rawOutput;
    private double currentYaw;
    private double Pyaw = 0.05; //0.05?//test commit//test
    public double speedLimit = 0.3;
    public double rotateLimit =0.6;
    public double boostedSpeed =  0.5;
  
  public DriveWithJoystick( swerveDrivetrain subsystem, XboxController controller) {
    //this.drivetrain = drivetrain;
    swerveDrivetrain = subsystem;
    addRequirements(swerveDrivetrain);

    this.xboxController0 = controller;
  }

  @Override
  public void execute() {
    
    //add in place rotation using each trigger
    
    //SmartDashboard.putNumber("Current value from gyro", currentYaw);
    //SmartDashboard.putNumber("DriveTrain angle", swerveDrivetrain.getAngle());
    
    //System.out.println("R-trigger:" + controller.getRightTriggerAxis());
    //System.out.println("L-trigger:" + controller.getLeftTriggerAxis());

    //
    if(rawOutput< deadband && xboxController0.getLeftTriggerAxis() < 0.1 && xboxController0.getRightTriggerAxis() < 0.1){
// all inputs must be under deadband for this mode to be active
      rawOutput = Math.pow(Math.pow(xboxController0.getLeftY(),2)
  + Math.pow(xboxController0.getLeftX(),2),0.5);
     // System.out.println("deadband mode");

      
    final var xSpeed =0;
    final var ySpeed =0;
    final var rot =0;

    boolean calibrate = xboxController0.getRightBumper();
    swerveDrivetrain.drive(xSpeed, ySpeed, rot, false, calibrate); //set "false" to "true" if you want to use field-oriented driving (In two other places farther down)
    //System.out.println("R-trigger:" + controller.getRightTriggerAxis());
    //System.out.println("L-trigger:" + controller.getLeftTriggerAxis());

  }
//

  else if(xboxController0.getLeftTriggerAxis()< 0.1 && xboxController0.getRightTriggerAxis()<0.1){
//Never leaving "Yaw lock mode" so the robot cannot rotate.-Not sure what the trigger values are/create a deadband for them.
//resolved 12/14/22
//replaced the rot value with normal triggers when we changed to non field oriented drive, this section is a duplicate of
// the section below it.

    final var xSpeed = 
      -xspeedLimiter.calculate(xboxController0.getLeftY())
      *swerveDrivetrain.kMaxSpeed*speedLimit;

    final var ySpeed = 
      -yspeedLimiter.calculate(xboxController0.getLeftX())
      * swerveDrivetrain.kMaxSpeed*speedLimit;
      
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

    currentYaw = swerveDrivetrain.getAngle();

    swerveDrivetrain.drive(xSpeed, ySpeed, rot, false, calibrate);

    rawOutput = Math.pow(Math.pow(xboxController0.getLeftY(),2)
  + Math.pow(xboxController0.getLeftX(),2),0.5);

    //System.out.println(+rawOutput);
    //System.out.println("normal drive mode");
    //turned true to false until we get gyro working
    //System.out.println("R-trigger:" + controller.getRightTriggerAxis());
   //System.out.println("L-trigger:" + controller.getLeftTriggerAxis());

    }
  }
  

}
