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
    return "Not yet implemented";    // STUB
  } // getCategory()

  String[] getImages() {
    String[] ret = new String[] {""};    // STUB
    return ret;
  } // getImages()

  String getText(String imageLoc) {
    return "Not yet implemented";    // STUB
  } // getText(String)

  boolean hasImage(String imageLoc) {
    return false;    // STUB
  } // hasImage(String)
} // AACCategory
