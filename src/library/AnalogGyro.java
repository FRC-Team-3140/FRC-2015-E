package library;

import edu.wpi.first.wpilibj.Gyro;
/**
 * AnalogGyro
 * Wrapper class that extends the functionality of gyro, and fixes some issues with it
 * 
 * @author Ryan Cook
 */
public class AnalogGyro extends Gyro {
  
  /**
   * Constructor for AnalogGyro
   * @param port sets the port of the gyro
   */
  public AnalogGyro(int port) {
    super(port);
  }
    /**
   * Constructor for AnalogGyro
   * @param port sets the port of the gyro
   * @param sensitivity sets the sensitivity of the gyro
   */
    public AnalogGyro(int port, int sensitivty) {
    super(port);
    super.setSensitivity(sensitivty);
  }
  
    public AnalogGyro(int port, int sensitivty, PIDSourceParameter source) {
      super(port);
      super.setSensitivity(sensitivty);
      super.setPIDSourceParameter(source);
  }
  
  public void init() {
    super.initGyro();
  }
  
  public void reset() {
    super.reset();
  }
  
  public double getAngle() {
    return Math.round( (super.getAngle() * 4));
  }
  
  public double getRate() {
    return super.getRate();
  }
  
  public double pidGet() {
    return super.pidGet();
  }
}