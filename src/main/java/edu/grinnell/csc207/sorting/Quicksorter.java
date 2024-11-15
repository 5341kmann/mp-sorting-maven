package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    Random rand = new Random();
    quickSortHelper(values, 0, values.length, rand);
  } // sort(T[])

  /**
   * Dutch National Flag orderer using a random pivot selection.
   * @param values array to be ordered by pivot value
   * @param low lower bound index (inclusive)
   * @param high upper bound index (exclusive)
   * @param rand Random object for pivot value selection
   */
  private void quickSortHelper(T[] values, int low, int high, Random rand) {
//    base case
    if (low >= high - 1) {
      return;
    }

    T pivot = values[rand.nextInt(high - low) + low];

    int lBound = low;
    int index = low;
    int hBound = high - 1;

    while (index <= hBound) {
      int comparison = order.compare(values[index], pivot);
      if (comparison < 0) {
        swap(values, index, lBound);
        lBound++;
        index++;
      } else if (comparison > 0){
        swap(values, index, hBound);
        hBound--;
      } else {
        index++;
      } // if
    } // while


//    recursive call on less than pivot
    quickSortHelper(values, low, lBound, rand);
//    recursive call on less than pivot
    quickSortHelper(values, hBound, high, rand);
  } // quickSortHelper(T[], int, int)

  /**
   * Simple array value swap method.
   *
   * @param values T[] array
   * @param i      index
   * @param j      index
   */
  private void swap(T[] values, int i, int j) {
    T tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
  } // swap(T[], int, int)
} // class Quicksorter
