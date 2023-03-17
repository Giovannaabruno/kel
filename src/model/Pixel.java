package model;

/**
 * Class Pixel which implements PixelInterface.
 */
public class Pixel implements PixelInterface {
  private int red;
  private int green;
  private int blue;
  // amount of transparency
  private int alpha;


  /**
   * Constructor for Pixel, represents integer objects red, green, and blue.
   *
   * @param red   equals red
   * @param green equals green
   * @param blue  equals blue
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = 255;
  }

  /**
   * Constructor for Pixel, represents alpha and integer objects red, green, and blue.
   *
   * @param red   equals red
   * @param green equals green
   * @param blue  equals blue
   * @param alpha the amount of transparency
   */
  public Pixel(int red, int green, int blue, int alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;

  }


  /**
   * Method equals, represents total components.
   *
   * @param other object
   * @return pixels components
   */
  @Override
  public boolean equals(Object other) {
    Pixel otherPixel = (Pixel) other;
    return this.red == otherPixel.red && this.blue == otherPixel.blue &&
            this.green == otherPixel.green && this.alpha == otherPixel.alpha;
  }


  /**
   * Method hashCode, run through each pixel's integer value.
   *
   * @return hashcode for the whole pixel value
   */
  @Override
  public int hashCode() {

    return Integer.hashCode(this.red) + Integer.hashCode(this.blue) + Integer.hashCode(this.green);
  }


  /**
   * Method getRed, gets red component.
   *
   * @return red
   */
  public int getRed() {
    return red;
  }

  /**
   * Method setRed, sets reds components.
   *
   * @param red red
   */
  public void setRed(int red) {
    this.red = red;
  }

  /**
   * Method getBlue, gets blue component.
   *
   * @return blue
   */
  public int getBlue() {
    return blue;
  }

  /**
   * Method setBlue, sets blue components.
   *
   * @param blue blue
   */
  public void setBlue(int blue) {
    this.blue = blue;
  }

  /**
   * Method getGreen, gets green component.
   *
   * @return green
   */
  public int getGreen() {
    return green;
  }

  /**
   * Method setGreen, sets green components.
   *
   * @param green green
   */
  public void setGreen(int green) {
    this.green = green;
  }

  /**
   * Method getAlpha,gets alpha value.
   *
   * @return alpha
   */
  public int getAlpha() {
    return alpha;
  }

  /**
   * Method setAlpha, sets alpha value.
   *
   * @param alpha aplpha
   */
  public void setAlpha(int alpha) {
    this.alpha = alpha;
  }

  /**
   * Method getValue, get the maximum value of the three components for each pixel.
   *
   * @return values of the image RGB
   */
  public int getValue() {
    return Math.max(red, Math.max(green, blue));
  }

  /**
   * Method getIntensity, gets the average of the three components for each pixel.
   *
   * @return total intensity  of the image RGB
   */
  public int getIntensity() {
    return (red + green + blue) / 3;
  }

  /**
   * Method getLume, gets the weighted sum. if
   * luma must be between 0 and 1, add "/255" after each color (ex: red/255).
   *
   * @return luma of the image RGB
   */
  public int getLuma() {
    return (int) (0.2126 * (float) red + 0.7152 * (float) green + 0.0722 * (float) blue);

  }

  /**
   * Method clone, creates a copy of the red green blue alpha values.
   *
   * @return RGBA values
   */
  @Override
  public Pixel clone() {
    return new Pixel(red, green, blue, alpha);
  }
}