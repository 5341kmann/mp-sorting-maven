package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * My custom sort class.
 *
 * @param <T>
 *   The types of values to be sorted.
 *
 * @author Grant Sackmann
 */
public class SackmannGrantSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a sorter using provided comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public SackmannGrantSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // FakeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort array against provided
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  @SuppressWarnings("unchecked")
  public void sort(T[] values) {
    int len = values.length;
    T[] valuesCopy = Arrays.copyOf(values, len);
    int insertionIndex;

    for (int i = 0; i < len; i++) {
      T val = valuesCopy[i];
      insertionIndex = len - 1;
      for (int j = 0; j < len; j++) {
        int comparison = order.compare(valuesCopy[j], val);
        if (comparison > 0 || (comparison == 0 && j < i)) {
          insertionIndex--;
        } // if
      } // for
//      inserting item in sorted position.
      values[insertionIndex] = val;
    } // for
  } // sort(T[])

} // SackmannGrantSort class
