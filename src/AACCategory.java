/**
 * Class to represent single categories of AAC items. Stores mappings between the 
 * image locations and the text that should be spoken, as well as the name of the category.
 * 
 * @author Keely Miyamoto
 */
import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

public class AACCategory {
  // Fields
  AssociativeArray<String, String> itemMappings;
  String categoryName;

  // Constructors
  public AACCategory(String name) {
    this.itemMappings = new AssociativeArray<String, String>();
    this.categoryName = name;
  } // AACCategory(String)

  // Methods
  /**
   * Adds the mapping of the imageLoc to the text to the category.
   * @param imageLoc - the location of the image to add
   * @param text - the text that image maps to
   */
  void addItem(String imageLoc, String text) {
    try {
      this.itemMappings.set(imageLoc, text);
    } catch (NullKeyException n) {
      throw new Error("The Image Location cannot be null.");
    }
  } // addItem(String, String)

  /**
   * Returns the name of the category
   * @return - category name
   */
  String getCategory() {
    return this.categoryName;
  } // getCategory()

  /**
   * Returns an array of all the images in the category
   * @return - an array of image locations
   */
  String[] getImages() {
    return this.itemMappings.getAllKeys();
  } // getImages()

  /**
   * Returns the text associated with the given image loc in this category
   * @param imageLoc - the location of the image
   * @return - the text associated with the image
   */
  String getText(String imageLoc) {
    try {
      return this.itemMappings.get(imageLoc);
    } catch (KeyNotFoundException k) {
      throw new Error("Image not found in " + this.getCategory());
    }
  } // getText(String)

    /**
   * Returns all texts associated with this AACCategory
   * @return - the texts associated with the AACCategory
   */
  String[] getTexts() {
    String[] texts = new String[this.itemMappings.size()];
    for (int i = 0; i < texts.length; i++) {
      texts[i] = this.itemMappings.getVal(i);
    }
    return texts;
  } // getText(String)

  /**
   * Determines if the provided images is stored in the category
   * @param imageLoc - the location of the category
   * @return - true if the provided images is stored in the category; false otherwise.
   */
  boolean hasImage(String imageLoc) {
    return this.itemMappings.hasKey(imageLoc);
  } // hasImage(String)
} // AACCategory
