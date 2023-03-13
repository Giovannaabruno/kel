package Model;

public interface LayerInterface<T> {

  public int getHeight();

  public int getWidth();

  public String getName();

  public T[][] getGrid();

  public T getPixelAt(int row, int colum);

  public void setFilter(String filter, int amount);


  public void setFilter(String filter);
  public String getFilter();

  public T[][] getFilteredGrid();
}
