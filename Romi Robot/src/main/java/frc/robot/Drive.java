package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
    
  public final Spark left_motor = new Spark(0); // creates a Spark object for the left motor
  public final Spark right_motor = new Spark(1); // creates a Spark object for the right motor
  private final Encoder left_encoder = new Encoder(4,5); //creates an Encoder object for the left encoder;
  private final Encoder right_encoder = new Encoder(6,7); // creates an Encoder object for the right encoder;

  /***
   * This method will reset the encoder at startup
   * to assure that we have travled 0 distance.
   * Also changes the units we measure distance from ticks to meters
   */
  public Drive(){

    left_encoder.reset();
    right_encoder.reset();
    left_encoder.setDistancePerPulse((70*Math.PI) / 1000 / 1440);
    right_encoder.setDistancePerPulse((70*Math.PI) / 1000 / 1440);
  }

  /***
   * 
   * @param speed 
   *  speed that we set the motor to
   * @param turn 
   *  the displacement of the turn to either slow or speed up the wheel
   */
  public void driveWheels(double speed, double turn){
    left_motor.set(speed + turn); // calculation which adds the speed and turn parameters to set the speed of the left wheel
    right_motor.set(speed - turn); // calculatino which subtracts the turn from the speed to set the speed of the right wheel
  }

  // 1 revolution = 1440
  // wheel diameter = 70 mm
  /***
   * this method grabs the distances from the encoders and puts them into the smartdashboard for the user to see the information
   */
  public void getDistances(){

    SmartDashboard.putNumber("Left Distance (M): ", left_encoder.getDistance());
    SmartDashboard.putNumber("Right Distance (M): ", right_encoder.getDistance());
  }

  /***
   * this method resets the distances that were tracked on the encoder
   */
  public void resetDistances(){

    left_encoder.reset();
    right_encoder.reset();
  }

  /***
   * 
   * this method alters the speed and turning variables if we wanted to change the rate of each param
   * 
   * @param speed speed we want the romi to drive at
   * @param turn offset that we want to add or subtract to one of the wheels to turn them
   * @param speedMag amplifies the rate of speed
   * @param turnMag amplifies the turning speed
   */
  public void driveWheels(double speed, double turn, double speedMag, double turnMag){
    left_motor.set(speed*speedMag + turn*turnMag);
    right_motor.set(speed*speedMag - turn*turnMag);
  }
}
