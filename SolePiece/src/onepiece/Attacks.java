/*************************************
 * Class: Attacks
 * Programmer: Mustaffa Kashif
 * Date: 2022/12/27
 * Details: Making a blueprint for a attacks object
 *************************************/
package onepiece;


public class Attacks extends InGameCharacters{
    
    //Creating private fields for class
    private int damage;
    
    //Creating a non arg contructor
    public Attacks()
    {
        //Setting fields to their base values
        super(null, null);
        damage = 0;
    }
    
    //Creating a arg contructor
    public Attacks(String n, int d)
    {
        //Setting fields to the variable values
        name = n;
        damage = d;
    }
    
    //Creating a mutator for damage
    public void setDamage(int d)
    {
        damage = d;
    }
    
    //Creating a accessor for damage
    public int getDamage()
    {
        return damage;
    }
}
