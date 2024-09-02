/*************************************
 * Class: InGameCharacters
 * Programmer: Mustaffa Kashif
 * Date: 2022/12/27
 * Details: Making a blueprint for a in game character object
 *************************************/
package onepiece;

public class InGameCharacters {
    
    /*
    Making protected fields for class. Making it protected because 
    its gonna be used in the enemies and main character subclass
    */
    protected String name;
    protected String imageArt;
    
    //Creating non arg constructor
    public InGameCharacters()
    {
        //Setting fields to base value
        name = null;
        imageArt = null;
    }
    
    //Creating arg constructor
    public InGameCharacters(String n, String i)
    {
        //Setting fields to variable values
        name = n;
        imageArt = i;
    }
    
    //Creating a mutator for name
    public void setName(String n)
    {
        name = n;
    }
    
    //Creating a mutator for imageart
    public void setImageArt(String i)
    {
        imageArt = i;
    }
    
    //Creating a accessor for name
    public String getName()
    {
        return name;
    }
    
    //Creating a accessor for image art
    public String getImageArt()
    {
        return imageArt;
    }
}
