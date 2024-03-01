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

  } // addItem(String, String)

  String getCategory() {
    return "Not yet implemented";
  } // getCategory()

  String[] getImages() {
    String[] ret = new String[] {""};
    return ret;
  } // getImages()

  String getText(String imageLoc) {
    return "Not yet implemented";
  } // getText(String)

  boolean hasImage(String imageLoc) {
    return false;
  } // hasImage(String)
  
} // AACCategory
