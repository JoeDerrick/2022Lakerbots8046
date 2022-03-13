package frc.robot.commands.LimeLightCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class turnOffLEDs extends CommandBase {
  limelight limelight;

  public turnOffLEDs(limelight limelight) {
    this.limelight = limelight;
  }

  @Override
  public void initialize() {
    limelight.turnOffLEDs();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}