/*************************************
 * Class: Potions
 * Programmer: Mustaffa Kashif
 * Date: 2022/12/27
 * Details: Making a blueprint for a potions object
 *************************************/
package onepiece;


public class Potions{
    
    /*
    Making a protected int for health boosting. Making it protected because 
    its gonna be used in the joker subclass
    */
    protected String name;
    protected int quantity;
    protected int healthBoost;
    
    //Creating a no arg contructor which extends items contructor 
    public Potions()
    {
        //Making fields = to their base values 
        name = null;
        quantity = 0;
        healthBoost = 0;
    }
    
    //Creating a arg constructor which extends items contructor 
    public Potions (String n, int q, int h)
    {
        //Making fields = to variable values
        name = n;
        quantity = q;
        healthBoost = h;
    }
    
    //Creating mutator for name
    public void setName(String n)
    {
        name = n;
    }
    
    //Creating mutator for quantity
    public void setQuantity(int q)
    {
        quantity = q;
    }
    
    //Creating mutator for name
    public String getName()
    {
        return name;
    }
    
    //Creating mutator for quantity
    public int getQuantity()
    {
        return quantity;
    }
    
    //Creating mutator for healthboost
    public void setHealthBoost(String h)
    {
        name = h;
    }
    //Creating accessor for healthboost
    public int getHealthBoost()
    {
        return healthBoost;
    }
    
    
    
}
