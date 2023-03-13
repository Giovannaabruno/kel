package Model;

public interface LayerInterface<T> {

  int getHeight();

  int getWidth();

  String getName();

  T[][] getGrid();

  T getPixelAt(int row, int colum);

  void setFilter(String filter, int amount);


  void setFilter(String filter);
  String getFilter();

  T[][] getFilteredGrid();
}
