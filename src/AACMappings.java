/**
 * Class to keep track of the complete set of AAC mappings.
 * @author Keely Miyamoto
 */

import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AACMappings {
  // Fields
  String currentName;
  AACCategory currentCategory;
  AssociativeArray<String, AACCategory> categoryPairs;

  // Constructors
  /**
   * This method should read in the file and create the relevant mappings 
   * from images to categories and add all the items to each category. 
   * It should also start the AAC on the home screen
   * 
   * @param filename -  In the file, the first line will represent the category. 
   * It will have the file location first (will be one "word") and then the 
   * category name. Then each of the items that is in the category will follow 
   * and the line will start with a ">". The line will then have the file location
   * (will be one "word") and then the text to speak. 
   */
  public AACMappings(String filename) {
    // Use 'eyes' to read in user input.
    try {
      BufferedReader eyes = new BufferedReader(new FileReader(filename));
      this.currentCategory = new AACCategory("");
      this.categoryPairs = new AssociativeArray<String, AACCategory>();
      this.currentName = "";
      
    } catch (FileNotFoundException f) {
      throw new Error("The file " + filename + " was not found.");
    }
  } // AACMappings(String)

  // Methods
  void add(String imageLoc, String text) {
    // STUB
  } // add(String, String)

  String getCurrentCategory() {
    return "food";  // STUB
  } // getCurrentCategory()

  String[] getImageLocs() {
    return new String[] { "img/food/icons8-french-fries-96.png", "img/food/icons8-watermelon-96.png" }; // STUB
  } // getImageLoc()

  String getText(String imageLoc) {
    return "television";  // STUB
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
