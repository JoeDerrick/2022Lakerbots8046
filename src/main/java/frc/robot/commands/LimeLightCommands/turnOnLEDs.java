package frc.robot.commands.LimeLightCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class turnOnLEDs extends CommandBase {
  limelight limelight;

  public turnOnLEDs(limelight limelight) {
    this.limelight = limelight;
  }

  @Override
  public void initialize() {
    limelight.turnOnLEDs();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}