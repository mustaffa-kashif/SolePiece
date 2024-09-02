/*************************************
 * Class: Bosses
 * Programmer: Mustaffa Kashif
 * Date: 2022/12/27
 * Details: Making a blueprint for the bosses object
 *************************************/
package onepiece;

import java.util.Random;

public class Bosses extends Enemies {
    
    //Creating private fields for class
    private int attackBoost;
    
    //Creating a non arg constructor
    public Bosses()
    {
        //Making fields equal base value
        name = null;
        imageArt = null;
        health = 0;
        attack = 0;
        //Setting attackboost to game value
        attackBoost = 2;
    }
    
    //Creating arg constructor
    public Bosses(String n, int h, int a)
    {
        //Making fields equal variable values
        super(n, h, a);
    }
    
    //Creating a mutator for special attack
    public void setSpecialAttack(int h)
    {
        health = h;
    }
    
    //Creating a accessor for special attack
    public int getSpecialAttack()
    {
        return health;
    }
    
    /***************
     * Method name: randomBoost
     * Description: It gives the enemy a random boost of health
     **************/
    @Override
    public void randomBoost()
    {
        //Creating a random number between 10-15
        Random random = new Random();
        int boost = random.nextInt(15)+10;
        //That random number will be added to health
        health = health + boost;
        
        //Attack will be increased by attackboost
        attack = attack + attackBoost;
        
    }
}
