/*************************************
 * Class: Joker
 * Programmer: Mustaffa Kashif
 * Date: 2022/12/27
 * Details: Making a blueprint for a joker object
 *************************************/
package onepiece;


public class Joker extends Potions {
    
    //Creating private field for class
    private int damage;
    
    //Creating a non arg contructor
    public Joker()
    {
        //Setting fields to beginner values
        super("Wild Card", 0, 10);
        damage = 35;
    }
    
    //Creating mutator for damage
    public void setDamage(int d)
    {
        damage = d;
    }
    
    //Creating accessor for damage
    public int getDamage()
    {
        return damage;
    }
}
