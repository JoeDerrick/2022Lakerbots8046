package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimberCommands.ClimbDisableSoftLimits;
import frc.robot.commands.ClimberCommands.ClimbEnableSoftLimits;
import frc.robot.commands.ClimberCommands.ClimbReset;
import frc.robot.commands.ClimberCommands.ClimbWithJoystick;
import frc.robot.commands.ClimberCommands.highBar.HighBarClimberExtend;
import frc.robot.commands.ClimberCommands.highBar.HighBarClimberRetract;
import frc.robot.commands.ClimberCommands.highBar.HighBarHookExtend;
import frc.robot.commands.ClimberCommands.highBar.HighBarHookRetract;
import frc.robot.commands.DriveCommands.DriveBackwards;
import frc.robot.commands.DriveCommands.DriveWithJoystick;
import frc.robot.commands.DriveCommands.DriveWithJoystickFieldOriented;
import frc.robot.commands.IntakeCommands.RaiseIntake;
import frc.robot.commands.LauncherCommands.LauncherCombinedSpeed;
import frc.robot.commands.LauncherCommands.LauncherGo;
import frc.robot.commands.LauncherHoodCommands.HoodExtend;
import frc.robot.commands.LauncherHoodCommands.HoodRetract;
import frc.robot.commands.SmartCommands.EjectBall;
import frc.robot.commands.SmartCommands.LaunchHighGoalFender;
import frc.robot.commands.SmartCommands.LaunchHighGoalTarmac;
import frc.robot.commands.SmartCommands.LaunchLowGoal;
import frc.robot.commands.SmartCommands.OneBallFenderHighGoalAuto;
import frc.robot.commands.SmartCommands.OneBallFenderHighGoalAutowithD;
import frc.robot.commands.SmartCommands.OneBallFenderHighGoalAutowithDNoDelay;
import frc.robot.commands.SmartCommands.SmartCollect;
import frc.robot.commands.SmartCommands.SmartLaunch;
import frc.robot.commands.SmartCommands.SmartLaunchWithReverse;
import frc.robot.commands.SmartCommands.StopCollecting;
import frc.robot.commands.SmartCommands.ThreeBallAutoRightSide;
import frc.robot.commands.SmartCommands.TwoBallAutoLeftSide;
import frc.robot.commands.SmartCommands.TwoBallAutoRightSide;
import frc.robot.commands.SmartCommands.TwoBallAutoRightSideClose;
import frc.robot.subsystems.climber;
import frc.robot.subsystems.hopper;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.launcher;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.pneumatics;
import frc.robot.subsystems.swerveDrivetrain;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();


// The robot's subsystems
    //public final drivetrain m_drivetrain = new drivetrain()
    public final pneumatics m_pneumatics = new pneumatics();
    public final climber m_climber = new climber();
    public final launcher m_launcher = new launcher();
    public final intake m_intake = new intake();
    public final swerveDrivetrain m_swerveDrivetrain = new swerveDrivetrain();
    public final hopper m_hopper = new hopper();
    public limelight m_limelight = new limelight();
  
  //public final swerveModules m_swerveModules = new swerveModules();
// Joysticks
private final XboxController xboxController0 = new XboxController(0);
private final XboxController xboxController1 = new XboxController(1);



  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
     
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    
    /*SmartDashboard.putData("Autonomous Command", new AutoDrive(m_swerveDrivetrain));
    SmartDashboard.putData("SpinIntake", new IntakeSpin( m_intake, 0.5 ));
    */
    SmartDashboard.putData("LaunchBall", new LaunchLowGoal(m_hopper, m_launcher));
    /*SmartDashboard.putData("Climb", new Climb( m_climber ));
    SmartDashboard.putData("ChangeHoodAngle", new HoodExtend( m_launcher ));
    SmartDashboard.putData("LowerIntake", new LowerIntake( m_intake ));
    SmartDashboard.putData("ReleaseArm", new ReleaseArm( m_climber ));
    SmartDashboard.putData("StopIntake", new IntakeStop( m_intake ));
    */
    SmartDashboard.putData("LauncherStop", new LauncherGo( m_launcher,0));
    SmartDashboard.putData("LauncherCombinedSpeed", new LauncherCombinedSpeed(m_launcher, 3000, 3000));
    SmartDashboard.putData("LaunchHighHGoalTarmac", new LaunchHighGoalTarmac(m_hopper, m_launcher));
    SmartDashboard.putData("SMartLaunch with Rotate and Speed from Limelight", new SmartLaunch(m_hopper, m_limelight, m_swerveDrivetrain, m_launcher));
    SmartDashboard.putData("Disable Climb Soft Limits", new ClimbDisableSoftLimits(m_climber));
    SmartDashboard.putData(" Enable Soft Limits", new ClimbEnableSoftLimits(m_climber));
    SmartDashboard.putData("Reset Climb Encoders", new ClimbReset(m_climber));
    SmartDashboard.putData("Hook extend", new HighBarHookExtend(m_climber));
    SmartDashboard.putData("Hook retract", new HighBarHookRetract(m_climber));
    SmartDashboard.putData("High Bar Extend", new HighBarClimberExtend(m_climber));
    SmartDashboard.putData("High Bar Retract", new HighBarClimberRetract(m_climber));
    SmartDashboard.putData("Hood Retract", new HoodRetract(m_launcher));
    SmartDashboard.putData("Hood Extend", new HoodExtend(m_launcher));
    SmartDashboard.putData("Swag Flag DEPLOY!", new HighBarHookExtend(m_climber));
    SmartDashboard.putData("Swag Flag RETRETE!", new HighBarHookRetract(m_climber));
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands

  //Used only for tuning turn off when complete!!

    //m_launcher.setDefaultCommand(new LauncherLimelightLaunch(m_launcher,m_limelight));
 
    m_swerveDrivetrain.setDefaultCommand(new DriveWithJoystick(m_swerveDrivetrain, xboxController0));
    m_climber.setDefaultCommand(new ClimbWithJoystick(m_climber, xboxController1));
  
    // Configure autonomous sendable chooser
    
    
    
      m_chooser = new SendableChooser<>();
      m_chooser.setDefaultOption("2 Ball Auto Left Side (Long Distance)", new TwoBallAutoLeftSide(m_hopper, m_launcher, m_swerveDrivetrain, m_intake, m_limelight,m_climber));
      m_chooser.addOption("2 Ball Auto Right Side (Short Distance)", new TwoBallAutoRightSide(m_hopper, m_launcher, m_swerveDrivetrain, m_intake, m_limelight,m_climber));
      m_chooser.addOption("Low Goal Auto", new LaunchLowGoal(m_hopper, m_launcher));
      m_chooser.addOption("Drive Backwards Auto", new DriveBackwards(m_swerveDrivetrain, 40));
      m_chooser.addOption("One Ball High Goal Auto", new OneBallFenderHighGoalAuto(m_hopper, m_launcher, m_swerveDrivetrain, m_intake, m_limelight));
      m_chooser.addOption("Three Ball Auto Right Side", new ThreeBallAutoRightSide(m_hopper, m_launcher, m_swerveDrivetrain, m_intake, m_limelight,m_climber));
      m_chooser.addOption("One Ball With D", new OneBallFenderHighGoalAutowithD(m_hopper, m_launcher, m_swerveDrivetrain, m_intake, m_limelight,m_climber));
      m_chooser.addOption("One Ball With D, NO DELAY", new OneBallFenderHighGoalAutowithDNoDelay(m_hopper, m_launcher, m_swerveDrivetrain, m_intake, m_limelight,m_climber));
      m_chooser.addOption("2 Ball Right Side Drive Close", new TwoBallAutoRightSideClose(m_hopper, m_launcher, m_swerveDrivetrain, m_intake, m_limelight,m_climber));
      SmartDashboard.putData("Auto Mode", m_chooser);


  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
private void configureButtonBindings() {    

// Create some buttons

//new JoystickButton(xboxController0, Button.kRightBumper.value).whenPressed(new LauncherTestBoth(m_launcher,0.6, 0.1));
//new JoystickButton(xboxController0, Button.kLeftBumper.value).whenPressed(new LauncherTestBoth(m_launcher, 0, 0));


/*
new JoystickButton(xboxController0, Button.kLeftBumper.value).whileHeld(new RotateToTarget(m_limelight, m_swerveDrivetrain));
new JoystickButton(xboxController1, Button.kRightBumper.value).whenPressed(new HoodExtend(m_launcher));
new JoystickButton(xboxController1, Button.kLeftBumper.value).whenPressed(new HoodRetract(m_launcher));

*/


//intake extend/retract
//----------------DRIVER CONTROLS-----------------------------//

new JoystickButton(xboxController0, Button.kY.value).whenPressed(new LaunchHighGoalFender(m_hopper, m_launcher));
new JoystickButton(xboxController0, Button.kX.value).whenPressed(new SmartCollect(m_hopper, m_intake, m_climber));
new JoystickButton(xboxController0, Button.kB.value).whenPressed(new LaunchLowGoal(m_hopper, m_launcher));
new JoystickButton(xboxController0, Button.kA.value).whenPressed(new SmartLaunchWithReverse(m_hopper, m_limelight, m_swerveDrivetrain, m_launcher));
new JoystickButton(xboxController0, Button.kRightBumper.value).whenPressed(new StopCollecting(m_hopper, m_intake, m_climber));
new JoystickButton(xboxController0, Button.kLeftStick.value).whenPressed(new EjectBall(m_hopper, m_intake, m_climber));
//---Driver also controls drivetrain with left x&y joysticks and twist with triggers ----//
//----resets gyro with Left Bumper ------------------------------//



//-------------------OPERATOR CONTROLS-----------------------------//

new JoystickButton(xboxController1, Button.kX.value).whenPressed(new RaiseIntake(m_intake)); 
/*new JoystickButton(xboxController1, Button.kRightBumper.value).whenPressed(new IntakeSpin(m_intake, 0.25)); //<--- maybe
new JoystickButton(xboxController1, Button.kB.value).whenPressed(new LowerIntake(m_intake));
new JoystickButton(xboxController1, Button.kA.value).whenPressed(new SmartRotateToTarget(m_hopper, m_launcher, 0.3, 0.4, m_limelight, m_swerveDrivetrain));
new JoystickButton(xboxController1, Button.kY.value).whenPressed(new turnOffLEDs(m_limelight));
*/
new JoystickButton(xboxController1, Button.kA.value).whenPressed(new ClimbEnableSoftLimits(m_climber));
new JoystickButton(xboxController1, Button.kB.value).whenPressed(new ClimbDisableSoftLimits(m_climber));
new JoystickButton(xboxController1, Button.kY.value).whenPressed(new ClimbReset(m_climber));

new JoystickButton(xboxController1, Button.kRightBumper.value).whenPressed(new EjectBall(m_hopper, m_intake,m_climber).withTimeout(0.75));
new JoystickButton(xboxController1, Button.kLeftBumper.value).whenPressed(new HighBarClimberRetract(m_climber));

//---OPerator Also controls climber with left and right y- Joysticks -----//


//new JoystickButton(xboxController1, Button.kX.value).whenPressed(new LowerIntake(m_intake));

//new JoystickButton(xboxController0, Button.kB.value).whenPressed(new HoodRetract(m_launcher));
//new JoystickButton(xboxController0, Button.kY.value).whenPressed(new HoodExtend(m_launcher));
//new JoystickButton(xboxController0, Button.kLeftBumper.value).whenPressed(new ClimbBoth(m_climber, 0.25));
//new JoystickButton(xboxController0, Button.kB.value).whenPressed(new ClimbBoth(m_climber,0));
//new JoystickButton(xboxController0, Button.kRightBumper.value).whenPressed(new ClimbBoth(m_climber,-.25));


/*new JoystickButton(xboxController1, Button.kX.value).whenPressed(new HopperASetPower(m_hopper, 0.25));
new JoystickButton(xboxController1, Button.kA.value).whenPressed(new HopperBSetPower(m_hopper, 0.25));

new JoystickButton(xboxController1, Button.kY.value).whenPressed(new HopperASetPower(m_hopper, 0));
new JoystickButton(xboxController1, Button.kB.value).whenPressed(new HopperBSetPower(m_hopper, 0));
*/




//new JoystickButton(xboxController1, Button.kRightBumper.value).whenPressed(new IntakeSpin(m_intake, -0.5));



  }

   
public XboxController getxboxController0() {
      return xboxController0;
    }
public XboxController getxboxController1() {
  return xboxController1;
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  
  

}

