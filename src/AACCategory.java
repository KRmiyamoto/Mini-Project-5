/**
 * Class to represent single categories of AAC items. Stores mapping between the 
 * image location and the text that should be spoken and the name of the category.
 * 
 * @author Keely Miyamoto
 */

import java.util.*;

import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

import static java.lang.reflect.Array.newInstance;

public class AACCategory {
  // Fields
  AssociativeArray<String, String> itemMappings;

  // Constructors
  public AACCategory() {
    this.itemMappings = new AssociativeArray<String, String>();
  } // AACCategory()

  // Methods
  void addItem(String imageLoc, String text) {
    // STUB
  } // addItem(String, String)

  String getCategory() {
    return "food";  // STUB
  } // getCategory()

  String[] getImages() {
    return new String[] { "img/food/icons8-french-fries-96.png", "img/food/icons8-watermelon-96.png" }; // STUB
  } // getImages()

  String getText(String imageLoc) {
    return "television";  // STUB
  } // getText(String)

  boolean hasImage(String imageLoc) {
    return false;    // STUB
  } // hasImage(String)
} // AACCategory
