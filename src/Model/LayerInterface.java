package Model;

/**
 * interface for LayerInterface.
 *
 * @param <T> pixel
 */
public interface LayerInterface<T> {

  int getHeight();

  int getWidth();

  String getName();

  T[][] getGrid();

  T getPixelAt(int row, int colum);

  void setFilter(String filter, int amount);

  String getFilter();

  void setFilter(String filter);

  T[][] getFilteredGrid();
}
