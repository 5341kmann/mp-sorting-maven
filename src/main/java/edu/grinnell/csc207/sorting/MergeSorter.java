package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Grant Sackmann
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
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
    T[] sorted = mergeSortHelper(values);
    System.arraycopy(sorted, 0, values, 0, values.length);
  } // sort(T[])

  private T[] mergeSortHelper(T[] values) {
    if (values.length < 2) {
      return values;
    } // if

    T[] left = Arrays.copyOfRange(values, 0, values.length / 2);
    T[] right = Arrays.copyOfRange(values, values.length / 2, values.length);
    return merge(mergeSortHelper(left), mergeSortHelper(right));
  } // mergeSortHelper()

  /**
   * Merges together two sorted sub arrays.
   *
   * @param left sorted subarray
   * @param right sorted subarray
   * @return sorted array of merge of the left and right arrays
   */
  @SuppressWarnings("unchecked")
  private T[] merge(T[] left, T[] right){
    int leftIndex = 0;
    int leftLen = left.length;
    int rightIndex = 0;
    int rightLen = right.length;
    T[] merged = (T[]) new Object[rightLen + leftLen];
    int mergedIndex = 0;

    while (leftIndex < leftLen && rightIndex < rightLen){
      int comparison = order.compare(left[leftIndex], right[rightIndex]);
      if (comparison < 0){
        merged[mergedIndex++] = left[leftIndex++];
      } else if (comparison > 0){
        merged[mergedIndex++] = right[rightIndex++];
      } else { // equal elements
        merged[mergedIndex++] = left[leftIndex++];
        merged[mergedIndex++] = right[rightIndex++];
      } // if
    } // while

//    filling remaining values
    while (leftIndex < leftLen){
      merged[mergedIndex++] = left[leftIndex++];
    } // while

    while (rightIndex < rightLen){
      merged[mergedIndex++] = right[rightIndex++];
    } // while
    return merged;
  } // merge()
} // class MergeSorter
