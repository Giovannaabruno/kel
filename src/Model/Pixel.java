package Model;

public class Pixel implements PixelInterface {
  private int red;
  private int green;
  private int blue;
  // amount of transparency
  private int alpha;


  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = 255;
  }

  public Pixel(int red, int green, int blue, int alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;

  }

  @Override
  public boolean equals(Object other) {
    Pixel otherPixel = (Pixel) other;
    return this.red == otherPixel.red && this.blue == otherPixel.blue &&
            this.green == otherPixel.green && this.alpha == otherPixel.alpha;
  }

  public int getRed() {
    return red;
  }

  public int getBlue() {
    return blue;
  }

  public int getGreen() {
    return green;
  }

  public int getAlpha() {
    return alpha;
  }

  public int getValue() {
    return Math.max(red, Math.max(green, blue));
  }

  public int getIntensity() {
    return (red + green + blue) / 3;
  }

  public int getLuma() {
    return (int)(0.2126 * (float) red + 0.7152 * (float) green + 0.0722 * (float) blue);
    //if luma must be between 0 and 1, add "/255" after each color (ex: red/255)
  }

  public void setRed(int red) {
    this.red = red;
  }

  public void setGreen(int green) {
    this.green = green;
  }

  public void setBlue(int blue) {
    this.blue = blue;
  }

  public void setAlpha(int alpha) {
    this.alpha = alpha;
  }
}