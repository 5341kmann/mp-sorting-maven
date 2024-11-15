package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Test of SackmannGrant Sort.
 *
 * @author Grant Sackmann
 */
public class TestSackmannGrantSorter extends TestSorter {

  /**
   * Set-up sorter.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new SackmannGrantSorter<String>(String::compareTo);
    intSorter = new SackmannGrantSorter<Integer>(Integer::compareTo);
  } // setup()
} // TestSackmannGrantSorter class
