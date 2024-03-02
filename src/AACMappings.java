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
import java.io.IOException;
import java.io.PrintWriter;

public class AACMappings {
  // Fields
  String currentName;
  AACCategory currentCategory;
  AACCategory homePage;
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
      this.homePage = new AACCategory("");
      this.currentCategory = this.homePage;
      this.categoryPairs = new AssociativeArray<String, AACCategory>();
      this.currentName = "";
      
      String line = eyes.readLine();
      while (line != null) {
        String[] lineContents = line.split(" ");

        if(line.charAt(0) != '>') {
          this.homePage.addItem(lineContents[0], lineContents[1]);
          try {
            this.categoryPairs.set(lineContents[0], new AACCategory(lineContents[1]));
            this.currentCategory = this.categoryPairs.get(lineContents[0]);
          } catch (Exception e) {
            throw new Error("Error setting/getting desired key.");
          }
        } else {
          String imageLoc = lineContents[0].substring(1);
          this.currentCategory.addItem(imageLoc, lineContents[1]);
        }
      line = eyes.readLine();
      } // while
      eyes.close();
    } catch (FileNotFoundException f) {
      throw new Error("The file " + filename + " was not found.");
    } catch (IOException i) {
      throw new Error("Failed to read in file contents.");
    }
    this.currentName = "";
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
      if (this.getCurrentCategory().equals("")) {
        this.homePage.addItem(imageLoc, text);
        this.categoryPairs.set(text, new AACCategory(text));
      } else {
        boolean foundCategory = false;
        int index = 0;
        while (!foundCategory && (index < this.categoryPairs.size())) {
          if (this.categoryPairs.getKey(index).equals(this.getCurrentCategory())) {
            this.categoryPairs.getVal(index).addItem(imageLoc, text);
            foundCategory = true;
          } // if
          index++;
        } // while
      } // if
    } catch (Exception e) {}
  } // add(String, String)

  /**
   * Gets the current category
   * @return - returns the current category, 
   * or the empty string if on the default category
   */
  String getCurrentCategory() {
    return this.currentName;
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
    if (this.getCurrentCategory().equals("")) {
      this.currentName = this.homePage.getText(imageLoc);
      return this.currentName;
    } else {
      for (int i = 0; i < this.categoryPairs.size(); i++) {
        if (this.getCurrentCategory().equals(this.categoryPairs.getKey(i))) {
          return this.categoryPairs.getVal(i).getText(imageLoc);
        } // if
      } // for
    } // else
    throw new Error("Text associated with the given image not found.");
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
   * Resets the current category of the AAC back to the default category
   */
  void reset() {
    this.currentCategory = this.homePage;
    this.currentName = "";
  } // reset()

  /**
   * Writes the ACC mappings stored to a file. The file is formatted as 
   * the text location of the category followed by the text name of the 
   * category and then one line per item in the category that starts with
   * > and then has the file name and text of that image
   */
   void writeToFile(String filename) {
    String[] filenames;
    try {
      PrintWriter pen = new PrintWriter(filename);
      
      for (int i = 0; i < this.categoryPairs.size(); i++) {
        pen.println(this.categoryPairs.getKey(i) + " " + this.categoryPairs.getVal(i).getCategory());
        filenames = this.categoryPairs.getVal(i).getImages();
       
        for (int j = 0; j < filenames.length; i++) {
          pen.println(">" + filenames[j] + " " + this.categoryPairs.getVal(i).getText(filenames[j]));
        } // for
      } // for
    pen.flush();
    } catch (Exception e) {}
  } // writeToFile(String)

} // AACMappings
