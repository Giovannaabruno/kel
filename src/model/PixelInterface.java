package model;

/**
 * interface for PixelInterface.
 */
public interface PixelInterface {
  /**
   * Method equals, represents total components.
   *
   * @param other object
   * @return pixels components
   */
  boolean equals(Object other);


  /**
   * Method getRed, gets red component.
   *
   * @return red
   */
  int getRed();

  /**
   * Method setRed, sets reds components.
   *
   * @param red red
   */
  void setRed(int red);

  /**
   * Method getBlue, gets blue component.
   *
   * @return blue
   */
  int getBlue();

  /**
   * Method setBlue, sets blue components.
   *
   * @param blue blue
   */
  void setBlue(int blue);

  /**
   * Method getGreen, gets green component.
   *
   * @return green
   */
  int getGreen();

  /**
   * Method setGreen, sets green components.
   *
   * @param green green
   */
  void setGreen(int green);

  /**
   * Method getAlpha,gets alpha value.
   *
   * @return alpha
   */
  int getAlpha();

  /**
   * Method setAlpha, sets alpha value.
   *
   * @param alpha alpha
   */
  void setAlpha(int alpha);

  /**
   * Method getValue, get the maximum value of the three components for each pixel.
   *
   * @return value
   */
  int getValue();

  /**
   * Method getIntensity, gets the average of the three components for each pixel.
   *
   * @return total intensity
   */
  int getIntensity();

  /**
   * Method getLume, gets the weighted sum. if
   * luma must be between 0 and 1, add "/255" after each color (ex: red/255).
   *
   * @return luma
   */
  public int getLuma();

  /**
   * Method getLume, gets the weighted sum. if
   * luma must be between 0 and 1, add "/255" after each color (ex: red/255).
   *
   * @return RGBA values
   */
  Pixel clone();


}
