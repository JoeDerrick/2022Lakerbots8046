

package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.Timer;

import static edu.wpi.first.networktables.NetworkTableInstance.getDefault;

public class limelight extends SubsystemBase {
    private volatile VisionState state = new VisionState(0,0,0);

    public limelight () {
        getDefault().getTable("limelight").getEntry("tx").addListener(event -> {
            if (getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1) {
              double xOffset = getDefault().getTable("limelight").getEntry("tx").getDouble(0);
              double yOffset = getDefault().getTable("limelight").getEntry("ty").getDouble(0);
              double latency = getDefault().getTable("limelight").getEntry("tl").getDouble(0) / 1000.0 + 0.011;
              state = new VisionState(xOffset, yOffset, Timer.getFPGATimestamp() - latency);
            }
          }, EntryListenerFlags.kUpdate);
        }

        public VisionState getState(){
            return state;
        }

public boolean hasTarget() {
    return getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1.0;
}
    //1	force off
    //2	force blink
    //3	force on

public void turnOffLEDs() {
  getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
}

public void turnOnLEDs() {
  getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
}
public double getLEDState(){
    return getDefault().getTable("limelight").getEntry("ledMode").getDouble(123);
}

public static class VisionState {
  public final double xOffset;
  public final double yOffset;
  public final double timestamp;

  public VisionState(double xOffset, double yOffset, double timestamp) {
    this.xOffset = xOffset;
    this.yOffset = yOffset;
    this.timestamp = timestamp;
  }

}
/*   private final NetworkTable m_limelightTable;
private double ty,tx,ta;

/*
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    public limelight(){
        m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }
    
*/
    @Override
    public void periodic(){
        /*
    //read values periodically
    ty = m_limelightTable.getEntry("ty").getDouble(0.0);
    tx = m_limelightTable.getEntry("tx").getDouble(0.0);
    ta = m_limelightTable.getEntry("ta").getDouble(0.0);
    
    */
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", getState().xOffset);
    SmartDashboard.putNumber("LimelightY",getState().yOffset);
   // SmartDashboard.putNumber("LimelightArea", getState().);
    
    }
    /*
    public double getx(){
        return tx;
    }
    */
    /*
    public double gety(){
        return
    }
    */
    
    //0	use the LED Mode set in the current pipeline
    
    public void changeLEDMode(int number){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(number);
    //
    System.out.println("Changed the mode");
    }
    /*
    0	Standard - Side-by-side streams if a webcam is attached to Limelight
    1	PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream
    2	PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream
    */
    public void changePipeMode(int number){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(number);
        }
        /*
    public boolean onRotationalTarget(double threshold){
        if(getx() == 0 && gety() == 0){
            return true;
        }
        if(Math.abs(tx) < threshold){
            return false;
        }
        else return true;
    }
    */
    
}
