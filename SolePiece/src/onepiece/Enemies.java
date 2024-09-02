/*************************************
 * Class: Enemies
 * Programmer: Mustaffa Kashif
 * Date: 2022/12/27
 * Details: Making a blueprint for a enemy object
 *************************************/
package onepiece;

import java.util.Random;

public class Enemies extends InGameCharacters {
    
    /*
    Making protected fields for class. Making it protected because 
    they gonna be used in the bosses subclass
    */
    protected int health;
    protected int attack;
    
    //Making non arg contructor
    public Enemies()
    {
        //Setting fields to base value
        super(null, null);
        health = 0;
        attack = 0;
    }
    
    //Making arg contructor
    public Enemies(String n, int h, int a)
    {
        //Setting fields to variable values
        name = n;
        health = h;
        attack = a;
    }
    
    //Creating a mutator for health
    public void setHealth(int h)
    {
        health = h;
    }
    
    //Creating a mutator for health
    public void setAttack(int a)
    {
        attack = a;
    }
    
    //Creating a accessor for health
    public int getHealth()
    {
        return health;
    }

    //Creating a accessor for health
    public int getAttack()
    {
        return attack;
    }
    
    /***************
     * Method name: randomBoost
     * Description: It gives the enemy a random boost of health
     **************/
    public void randomBoost()
    {
        //Creating a random number between 10-15
        Random random = new Random();
        int boost = random.nextInt(13)+5;
        //That random number will be added to health
        health = health + boost;
    }
    
//Creating method to print out enemy status
    public String toString()
    {
        //Making a string which will hold the health bar 
        String healthBar = null;
     
        //If health is at max the health bar will be full
        if(health == 150)
        {
            //Making healthbar = full bar
            healthBar = ("====================\n          ==================="
                    + "=");
        }
        //else if, the health is less then max, this bar will print
        else if (health >= 125 && health < 150)
        {
            //Making healthbar = less then full bar
            healthBar = "====================\n          ===============";
        }
        //else if, the health is between 100 and 125, this bar will print
        else if (health >= 100 && health < 125)
        {
            healthBar = "====================\n          ==========";
        }
        //else if, the health is between 75 and 100, this bar will print
        else if (health >= 75 && health < 100)
        {
            healthBar = "====================\n          =====";
        }
        //else if, the health is between 50 and 75, this bar will print
        else if (health >= 50 && health < 75)
        {
            healthBar = "====================";
        }
        //else if, the health is between 25 and 50, this bar will print
        else if (health >= 25 && health < 50)
        {
            healthBar = "===============";
        }
        //else if, the health is between 0 and 25, this bar will print
        else if (health > 0 && health < 25)
        {
            healthBar = "==========";
        }
        //else if, the health is 0 this will print
        else if (health == 0)
        {
            healthBar = "---DEAD---";
        }
        
        //returning all status values for the enemy
        return("\t\t\t\t\t\t\t|-----------------------------------------|"
                + "\n\t\t\t\t\t\t\t  Enemy: " + name 
                +"\n\t\t\t\t\t\t\t"
                + "  Health: " + healthBar + "  " + health + "\n\t\t\t\t\t\t\t"
                + "|------------"
                + "-----------------------------|" + imageArt);
    }
    
}
