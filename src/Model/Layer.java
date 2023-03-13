package Model;
// need to have atleast 1 layer
public class Layer implements LayerInterface<Pixel>{
  private int height;
  private int width;
  private Pixel[][] grid;

  private String name;

  private String filter; //store the type of filter

  private int amount; //store the intensity of the filter

  public Layer(int height, int width, String name) {
    this.height = height;
    this.width = width;
    this.name = name;
    this.grid = new Pixel[height][width];
    for( int row = 0 ; row < grid.length; row++) {
      for(int colum = 0; colum < grid[0].length; colum++) {
        grid[row][colum] = new Pixel(255, 255, 255);
      }
    }
  }

  public Layer(Pixel[][] grid, String name) {
    this.height = grid.length;
    this.width = grid[0].length;
    this.grid = grid;
    this.name = name;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public String getName() {
    return name;
  }

  public Pixel[][] getGrid() {
    return grid;
  }
  public Pixel getPixelAt(int row, int colum) {
    return grid[row][colum];
  }
  
  public void setPixelAt(int row, int colum, Pixel pixel) {
    grid[row][colum] = pixel;
  }

  public void setFilter(String filter, int amount) {
    //ideally, this should check whether filter is a valid choice
    //and throw an error if it's not
    this.filter = filter;
    this.amount = amount;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }
  public String getFilter() {
    return this.filter;
  }

  public Pixel[][] getFilteredGrid() {
    switch(filter) {
      case "red-component":
        return redComponent();
      case "green-component":
        return greenComponent();
      case "blue-component":
        return blueComponent();
      case "brighten-value":
        //still not sure how brighten value is different from
        // brighten intensity or brighten luma
        return brighten();
      case "brighten-intensity":
        return brighten();
      case "brighten-luma":
        return brighten();
      case "darken-value":
        return darken();
      case "darken-intensity":
        return darken();
      case "darken-luma":
        return darken();
      default:
        System.out.println("Invalid filter");
        return null;

    }
  }


  //Note: we could move all of these methods to a helper class
  //named Filter if we wanted (not necessary, might be good practice)


  private Pixel[][] darken() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = grid[row][colum].getRed();
        int green = grid[row][colum].getGreen();
        int blue = grid[row][colum].getBlue();

        int resultRed = Math.max(0, red - amount);
        int resultGreen = Math.max(0, green - amount);
        int resultBlue = Math.max(0, blue - amount);

        result[row][colum] = new Pixel(resultRed, resultBlue, resultGreen);
        //assume full opaqueness/full alpha
      }
    }
    return result;
  }

  //not sure what brighten intensity and brighten luma are,
  //or how they differ from this brighten method
  private Pixel[][] brighten() {
    Pixel[][] result = new Pixel[height][width];

    for(int row = 0; row < grid.length; row++) {
      for(int colum = 0; colum < grid[0].length; colum++) {
        int red = grid[row][colum].getRed();
        int green = grid[row][colum].getGreen();
        int blue = grid[row][colum].getBlue();

        int resultRed = Math.min(255, red + amount);
        int resultGreen = Math.min(255, green + amount);
        int resultBlue = Math.min(255, blue + amount);

        result[row][colum] = new Pixel(resultRed, resultBlue, resultGreen);
        //assume full opaqueness/full alpha
      }
    }
    return result;
  }

  private Pixel[][] redComponent() {
    Pixel[][] result = new Pixel[height][width];

    for(int row = 0; row < grid.length; row++) {
      for(int colum = 0; colum < grid[0].length; colum++) {
        int red = grid[row][colum].getRed();

        result[row][colum] = new Pixel(red, 0, 0);
        //assume full opaqueness/full alpha
      }
    }
    return result;
  }
  private Pixel[][] greenComponent() {
    Pixel[][] result = new Pixel[height][width];

    for(int row = 0; row < grid.length; row++) {
      for(int colum = 0; colum < grid[0].length; colum++) {
        int green = grid[row][colum].getGreen();

        result[row][colum] = new Pixel(0, green, 0);
        //assume full opaqueness/full alpha
      }
    }
    return result;
  }
  private Pixel[][] blueComponent() {
    Pixel[][] result = new Pixel[height][width];

    for(int row = 0; row < grid.length; row++) {
      for(int colum = 0; colum < grid[0].length; colum++) {
        int blue = grid[row][colum].getBlue();

        result[row][colum] = new Pixel(0, 0, blue);
        //assume full opaqueness/full alpha
      }
    }
    return result;
  }

}

