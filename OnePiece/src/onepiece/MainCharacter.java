/*************************************
 * Class: MainCharacter
 * Programmer: Mustaffa Kashif
 * Date: 2022/12/27
 * Details: Making a blueprint for a player object
 *************************************/
package onepiece;

public class MainCharacter extends InGameCharacters{
    
    //Creating private fields for class
    private int health;
    private int level;
    private int reputation;
    
    //Creating a non arg contructor
    public MainCharacter()
    {
        //Making fields = to their base values 
        super(null, null);
        
        //Making image art = to a art based string
        imageArt = "                                                      "
                + "      \n" +
"                          ,,,,,,,,*,,,,.             \n" +
"                        .,***,,,,,,,,,,,,*           \n" +
"                       .****,,*,,,,,*****/,          \n" +
"                  .,,**(#%%%###%##%#%*/**//          \n" +
"            .*////*//#&&&@(#@@&@(*,(%%%#%//          \n" +
"                 .(((&%&#&,.&@*@*@#&&(/*%%(          \n" +
"                      %#*/...@*,,,(&@@@@/*,*         \n" +
"                     ,(. /./,,*.% ,@@&&(*///**      \n" +
"                        *...,,..***,%(/.  .*(/(//*   \n" +
"                          *.**....#&.                \n" +
"                          .//***,,*#                 \n" +
"                   /(((((,..,.,.*,,..#(              \n" +
"                 ./####%,...,,..,,,,,#(####*         \n" +
"                /(#%###%(,/,.,.,,*############       \n" +
"              (#(#%(###(/*,****/*#############       \n" +
"           *(###%%/##,#/,*.,,/**(#####%#######       \n" +
"         (####%#&(###.//,.,,,,*,/####%#(#######      \n" +
"      /######(*,,.,,,,,.**,...*,*##%#%%%########     \n" +
"      %####%%#,,,**(/*/(/(/**,....(####%%%#%#####    \n" +
"       *#&%#%%%%%(#*,,*...,,,*///*############%###   \n" +
"             /%#%/.,..*,,,*,(,.,/(#(###############  \n" +
"            (###%.,,,.*...../.,.,(#%%%%%%%&%%&%%%(   \n" +
"           .%%%.**....,,..,.,..,,*#####%%%.          \n" +
"          .*%%%,,,*,,,,**,,,,,/,*.,*#%%%%%/          \n" +
"          .%#,,,*********/***/*,,**%%%%%%#          \n" +
"            ,*,,,.,..,,,,***//*,,,/#%%%%%#          \n";
        
        //Making fields = to beginner value
        health = 30;
        level = 1;
        reputation = 50;
    }
    
    //Creating mutator for health
    public void setHealth(int h)
    {
        health = h;
    }
    
    //Creating mutator for reputation
    public void setReputation(int r)
    {
        reputation = r;
    }
    
    //Creating mutator for level
    public void setLevel(int l)
    {
        level = l;
    }
    
    //Creating accessor for health
    public int getHealth()
    {
        return health;
    }
    
    //Creating accessor for Level
    public int getLevel()
    {
        return level;
    }
    
    //Creating accessor for reputation
    public int getReputation()
    {
        return reputation;
    }
    
    //Creating method to print out player status
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
        
        //returning all status values for the player
        return ("\t|--------------------------------------------|\n\t  " +
                name + ":\n\t  Health: " + healthBar + "    " +health + "\n\t|"
                + "-----------------------------------------" + "---" + "|" + 
                imageArt);
    }
}
