import java.util.*;

import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

import static java.lang.reflect.Array.newInstance;

public class AACMappings {
  // Fields
  String currentName;
  AACCategory currentCategory;
  AssociativeArray<String, AACCategory> categoryPairs;

  // Constructors
  public AACMappings(String filename) {
    this.currentCategory = new AACCategory();
    this.categoryPairs = new AssociativeArray<String, AACCategory>();
    this.currentName = "";
  } // AACMappings(String)

  // Methods
  void add(String imageLoc, String text) {
    // STUB
  } // add(String, String)

  String getCurrentCategory() {
    return "Not yet implemented"; // STUB
  } // getCurrentCategory()

  String getImageLoc() {
    return "Not yet implemented"; // STUB
  } // getImageLoc()

  String getText(String imageLoc) {
    return "Not yet implemented";    // STUB
  } // getText(String)

  boolean isCategory(String imageLoc) {
    return false;    // STUB
  } // isCategory(String)

  void reset() {
    // STUB
  } // reset()

  void writeToFile(String filename) {
    // STUB
  } // writeToFile(String)
} // AACMappings
