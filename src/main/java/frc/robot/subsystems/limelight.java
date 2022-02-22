package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableType;

public class limelight extends SubsystemBase {
private final NetworkTable m_limelightTable;
private double ty,tx,ta;

/*
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
*/
    public limelight(){
        m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }
    
    
    @Override
    public void periodic(){

    //read values periodically
    ty = m_limelightTable.getEntry("ty").getDouble(0.0);
    tx = m_limelightTable.getEntry("tx").getDouble(0.0);
    ta = m_limelightTable.getEntry("ta").getDouble(0.0);
    
    
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", tx);
    SmartDashboard.putNumber("LimelightY", ty);
    SmartDashboard.putNumber("LimelightArea", ta);
    }
    public double getx(){
        return tx;
    }
    public double gety(){
        return ty;
    }
    /*0	use the LED Mode set in the current pipeline
    1	force off
    2	force blink
    3	force on*/
    public void changeLEDMode(int number){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(number);
    }
    /*
    0	Standard - Side-by-side streams if a webcam is attached to Limelight
    1	PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream
    2	PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream
    */
    public void changePipeMode(int number){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(number);
        }

    public boolean onRotationalTarget(double threshold){
        if(getx() == 0 && gety() == 0){
            return true;
        }
        if(Math.abs(tx) < threshold){
            return false;
        }
        else return true;
    }
}
