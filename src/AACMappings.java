/**
 * Class to keep track of the complete set of AAC mappings.
 * @author Keely Miyamoto
 */
import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class AACMappings {
  // Fields
  AACCategory currentCategory;
  AACCategory homePage;
  // Keeps track of file location (String) and AACCategory
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
    // Initialize homePage, set currentCategory to homePage, and initialize categoryPairs AssociativeArray
    this.homePage = new AACCategory("");
    this.currentCategory = this.homePage;
    this.categoryPairs = new AssociativeArray<String, AACCategory>();

    // Use Scanner 'eyes' to read in file input.
    try {
      Scanner eyes = new Scanner(new File(filename));
      // While there are more lines in the file, read in the next line and split around " "
      while (eyes.hasNextLine()) {
        String line = eyes.nextLine();
        String[] lineContents = line.split(" ");

        // If line is a category (does not start with ">"), add to homePage and categoryPairs. 
        if(!line.startsWith(">")) {
          this.homePage.addItem(lineContents[0], lineContents[1]);
          try {
            this.categoryPairs.set(lineContents[0], new AACCategory(lineContents[1]));
            // Update currentCateory 
            this.currentCategory = this.categoryPairs.get(lineContents[0]);
          } catch (Exception e) {
            throw new Error("Error setting/getting desired key.");
          }
        // If line is within a category, remove ">" and add to currentCategory
        } else {
          String imageLoc = lineContents[0].substring(1);
          this.currentCategory.addItem(imageLoc, lineContents[1]);
        } // else
      } // while
      eyes.close();
      // Set currentCategory to homePage.
      this.currentCategory = this.homePage;
    } catch (FileNotFoundException f) {
      throw new Error("The file " + filename + " was not found.");
    } 
  } // AACMappings(String)

  // Methods
  /**
   * Adds the mapping to the current category 
   * (or the default category if that is the current category)
   * @param imageLoc - the location of the image
   * @param text - the text associated with the image
   */
  void add(String imageLoc, String text) {
    try {
      // If the current category is homePage, addItem to homePage and update categoryPairs.
      if (this.getCurrentCategory().equals("")) {
        this.homePage.addItem(imageLoc, text);
        this.categoryPairs.set(imageLoc, new AACCategory(text));
      // Otherwise, addItem to currentCategory
      } else {
        this.currentCategory.addItem(imageLoc, text);
      } // else
    } catch (Exception e) {}
  } // add(String, String)

  /**
   * Gets the current category
   * @return - returns the current category, 
   * or the empty string if on the default category
   */
  String getCurrentCategory() {
    return this.currentCategory.getCategory();
  } // getCurrentCategory()

  /**
   * Provides an array of all the images in the current category
   * @return - the array of images in the current category
   */
  String[] getImageLocs() {
    return this.currentCategory.getImages();
  } // getImageLoc()

  /**
   * Given the image location selected, it determines the 
   * associated text with the image. If the image provided is a 
   * category, it also updates the AAC's current category to be 
   * the category associated with that image.
   * @param imageLoc - the location where the image is stored
   * @return - the text associated with the current image
   */
  String getText(String imageLoc) {
    try {
      // If current category is homePage, set currentCategory to ACCCateogry associated with 'imageLoc'
      if (this.getCurrentCategory().equals("")) {
        this.currentCategory = this.categoryPairs.get(imageLoc);
        return this.homePage.getText(imageLoc);
      // Otherwise, return the text associated with 'imageLoc' in the currentCategory
      } else {
        return this.currentCategory.getText(imageLoc);
      } // else
    } catch (Exception e) {throw new Error("Failure to retrieve given image location.");}
  } // getText(String)

  /**
   * Determines if the image represents a category or text to speak
   * @param imageLoc - the location where the image is stored
   * @return - true if the image represents a category, 
   * false if the image represents text to speak
   */
  boolean isCategory(String imageLoc) {
    return this.homePage.hasImage(imageLoc);
  } // isCategory(String)

  /**
   * Resets 'currentCategory' of the AAC back to the default category (homePage)
   */
  void reset() {
    this.currentCategory = this.homePage;
  } // reset()

  /**
   * Writes the ACC mappings stored to a file. The file is formatted as 
   * the text location of the category followed by the text name of the 
   * category and then one line per item in the category that starts with
   * > and then has the file name and text of that image
   */
   void writeToFile(String filename) {
    // Declare respective String arrays for the filenames and texts of ACC mappings.
    String[] filenames;
    String[] texts;
    try {
      // Initialize PrintWriter pen to write to filename
      PrintWriter pen = new PrintWriter(filename);
      // For each index in categoryPairs, print the imageLoc of the cateogry and its text
      for (int i = 0; i < this.categoryPairs.size(); i++) {
        pen.println(this.categoryPairs.getKey(i) + " " + this.categoryPairs.getVal(i).getCategory());
        filenames = this.categoryPairs.getVal(i).getImages();
        texts = this.categoryPairs.getVal(i).getTexts();
        // Then, print each image filename, followed by its associated text.
        for (int j = 0; j < filenames.length; j++) {
          pen.println(">" + filenames[j] + " " + texts[j]);
        } // for
      } // for
    pen.close();
    } catch (Exception e) {}
  } // writeToFile(String)
} // AACMappings
