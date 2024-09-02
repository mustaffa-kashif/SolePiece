/*************************************
 * Project: One Piece 
 * Programmer: Mustaffa Kashif
 * Date: December 9th, 2022
 * Program: onepiece.java
 * Description: Making a online shopping experience 
 *************************************/
package onepiece;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class OnePiece {

    //Creating global fields for methods 
    //Creating randomizers for a random attack damage
    public static int randomDamage = 15;
    public static int randomDamageIncrease = 5;
    //Creating damage array which holds different damage values
    public static int[] damage = new int[12];

    public static void main(String[] args) throws InterruptedException {
        
        //Creating a scanner object for numbers
        Scanner scanN = new Scanner(System.in);  
        
        //Creating a object for random numbers
        Random random = new Random();
        
        //Creating int which will be stored in a file for difficulty level
        int gameDifficulty = 0;
        
        //Create a menu option int to check which option the player wants to use
        int menuOption = 0;
        
        //Creating a menu count int to display menu options in numbered order
        int menuCount;
        
        //Create enemynumber int to change which enemy fights the player
        int enemyNumber = 0;
        
        //Creating a checkpoint int to save checkpoint in file
        int checkPoint = 0;
        
        //initializing a file to store data
        File gameSave = new File("SavedGame.txt");
        
        //Creating object arrays for enemies/items/attacks/and npcs
        MainCharacter player = new MainCharacter();
        Attacks[] attack = new Attacks[6];
        Enemies enemy[] = new Enemies[12];
        Potions[] potion = new Potions[2];
        Joker wild = new Joker();
       
        //Creating try catch to error trap user input
        try 
        {
            //Creating a file scanner object
            Scanner readFile = new Scanner(gameSave);
            
            //Accessing data from the file loading the game
            checkPoint = readFile.nextInt();
            player.setName(readFile.next());
            player.setLevel(readFile.nextInt());
            gameDifficulty = readFile.nextInt();
            player.setReputation(readFile.nextInt());
            
            //closing file reader scanner
            readFile.close();
        }
        //Catching error
        catch(IOException e)
        {
            //Printint error message
            System.out.println("Error: " + e.getMessage());
        }
        
        //Initializing potion array and giving its fields values
        potion[0] = new Potions("Small Pot", 0 , 5);
        potion[1] = new Potions("Big Pot", 0 , 35);
        
        //Initializing attack array and giving its fields values
        attack[0] = new Attacks("Strike", 5);
        attack[1] = new Attacks("Locked", 15);
        attack[2] = new Attacks("Locked", 25);
        attack[3] = new Attacks("Locked", 35);
        attack[4] = new Attacks("Locked", 45);
        attack[5] = new Attacks("Locked", 55);
        
        //calling on load game to initialize other fields and objects
        loadGameData(gameSave, enemy, player, gameDifficulty, attack);
       
        //Setting the field image art and storing a artistic image 
        enemy[0].setImageArt("                                      "
                + "  \n" +
"\t\t\t\t\t\t\t                      (  ,              \n" +
"\t\t\t\t\t\t\t                   ,.,*.                \n" +
"\t\t\t\t\t\t\t                  %.(                   \n" +
"\t\t\t\t\t\t\t                  &(/                   \n" +
"\t\t\t\t\t\t\t                   #*/                  \n" +
"\t\t\t\t\t\t\t                     ,/.(               \n" +
"\t\t\t\t\t\t\t                       * #/             \n" +
"\t\t\t\t\t\t\t                        .** ,           \n" +
"\t\t\t\t\t\t\t                       ,* /,            \n" +
"\t\t\t\t\t\t\t                     /,,@@#             \n" +
"\t\t\t\t\t\t\t      .           *(/(/#(((//(%         \n" +
"\t\t\t\t\t\t\t      ((...%/ , ,(/#&,,...#@//((        \n" +
"\t\t\t\t\t\t\t     #%...,/#*&##&/#,,....%/(//(#*      \n" +
"\t\t\t\t\t\t\t      %#%#/////*&%(###,..%(%////#%%,*.  \n" +
"\t\t\t\t\t\t\t       . #&#%#%& ,%###%#%///(///(#%*,,/ \n" +
"\t\t\t\t\t\t\t        /.*&###* %.####(////(#((##*(&*  \n" +
"\t\t\t\t\t\t\t        //#////#//&###%//////##% /,* .* \n" +
"\t\t\t\t\t\t\t        #///(//(/####%(/(#((#%(.* *,,   \n" +
"\t\t\t\t\t\t\t         (  .%((&##%%///#&%% (*.        \n" +
"\t\t\t\t\t\t\t        #/&%&@&&%%%#*((# (*,.           \n" +
"\t\t\t\t\t\t\t     (/%#%  (/*** @(/#/ *               \n" +
"\t\t\t\t\t\t\t   .#/ (*.       %/# (*                 \n" +
"\t\t\t\t\t\t\t  / ,   *      ,,... *                  \n" +
"\t\t\t\t\t\t\t   ,.          ( #  /.                 \n" +
"\t\t\t\t\t\t\t                                       ");
        
        //Setting the field image art and storing a artistic image 
        enemy[1].setImageArt("                                    "
                + "                        \n" +
"\t\t\t\t\t\t\t                                                            \n" +
"\t\t\t\t\t\t\t                     /@* @%  &,                             \n" +
"\t\t\t\t\t\t\t                    %,  %(#     .*&                         \n" +
"\t\t\t\t\t\t\t                       @,@@@,.   .@ *                       \n" +
"\t\t\t\t\t\t\t                       &   @/  *%(,                         \n" +
"\t\t\t\t\t\t\t                          %@   #   ./#@                     \n" +
"\t\t\t\t\t\t\t                      %  #.&  , ,%/   (                     \n" +
"\t\t\t\t\t\t\t                     @ *  #    %  % *@                      \n" +
"\t\t\t\t\t\t\t                    % % /    .#/ (&                        \n" +
"\t\t\t\t\t\t\t                      @@      % &&                          \n" +
"\t\t\t\t\t\t\t                      /      @,                             \n" +
"\t\t\t\t\t\t\t                    ,.     ,@                               \n" +
"\t\t\t\t\t\t\t                    *     @%  #&.    *#       (,%           \n" +
"\t\t\t\t\t\t\t            .(%%(, &  ., /@     @##@%####@@@@@              \n" +
"\t\t\t\t\t\t\t        @    ,     @   .  @      .&&      /,    &@          \n" +
"\t\t\t\t\t\t\t            , #@@, &    , @ @.                %      /      \n" +
"\t\t\t\t\t\t\t      /       @     / ,* @ @          /&@@&((*@.      *     \n" +
"\t\t\t\t\t\t\t       /.            (    .  %/&@@            &      *.     \n" +
"\t\t\t\t\t\t\t          %%          &           (%@&%#(           &*      \n" +
"\t\t\t\t\t\t\t                         @                        @/        \n" +
"\t\t\t\t\t\t\t                              &@#*.     ,(@@@@.             ");
        
        //Setting the field image art and storing a artistic image 
        enemy[2].setImageArt("                           "
                + "                                 \n" +
"\t\t\t\t\t\t\t                                                            \n" +
"\t\t\t\t\t\t\t                           (%/#&%&@%,#/                     \n" +
"\t\t\t\t\t\t\t                        %,#(#         #                     \n" +
"\t\t\t\t\t\t\t                     (*%(                                   \n" +
"\t\t\t\t\t\t\t                   &@&                                      \n" +
"\t\t\t\t\t\t\t                 *&%%                                       \n" +
"\t\t\t\t\t\t\t                #&#                                         \n" +
"\t\t\t\t\t\t\t               &*%                                          \n" +
"\t\t\t\t\t\t\t              #@&*(                                         \n" +
"\t\t\t\t\t\t\t             %&&%%%,                                        \n" +
"\t\t\t\t\t\t\t             (%%@&%%,                                       \n" +
"\t\t\t\t\t\t\t             ##%%/*&&#                                      \n" +
"\t\t\t\t\t\t\t          ,(  ##&&(&%#&#   %%/                              \n" +
"\t\t\t\t\t\t\t       *&&  &&%%&%&%%&%###%&(  %%&% (./%#(/#,,(.            \n" +
"\t\t\t\t\t\t\t      &   .&&&%@@@%(&&@#(&/%&%#*%.,&(&(/@@%(&###%#%@        \n" +
"\t\t\t\t\t\t\t     .&   &     .(&#*.#&&&@@&(@%%@@&./#(...   .&&&&%  #     \n" +
"\t\t\t\t\t\t\t   %,     @    ..&#,,,*&&&%&&&#(/(%%.,,,#     ..  .,.&&     \n" +
"\t\t\t\t\t\t\t                   @* .,**((#(%#%&%&%#%                     \n" +
"\t\t\t\t\t\t\t                    #.,,,*#%@@&&&@@@&%%&&                   \n" +
"\t\t\t\t\t\t\t                          ,***&%&%%%&@                      \n" +
"\t\t\t\t\t\t\t                                 .,*//%&&&#                 ");
        
        //Setting the field image art and storing a artistic image 
        enemy[3].setImageArt("                  "
                + "                                          \n" +
"\t\t\t\t\t\t\t                      #%#                                   \n" +
"\t\t\t\t\t\t\t                     %%%%%%#                                \n" +
"\t\t\t\t\t\t\t                    %%%%%%%##%(                             \n" +
"\t\t\t\t\t\t\t                    %%%%%%%%%%%@@.                          \n" +
"\t\t\t\t\t\t\t                   *%%%%%%#@@@@@@@&                         \n" +
"\t\t\t\t\t\t\t                   /%%%%#@@@@@@@@@@&/                       \n" +
"\t\t\t\t\t\t\t                 .%%%%%%#@@@@@@@@@&%                        \n" +
"\t\t\t\t\t\t\t                 &%%%%@#%#@@@@@&&.                          \n" +
"\t\t\t\t\t\t\t              (%#######%&%%&%%%%%                           \n" +
"\t\t\t\t\t\t\t             ###(#((####%%%%%&&%###                         \n" +
"\t\t\t\t\t\t\t            ,######(((######%##&####                        \n" +
"\t\t\t\t\t\t\t            %%%((#(#(((####((&#@#&((,                       \n" +
"\t\t\t\t\t\t\t            &%#((#(((((##((((#(@@(&(#                       \n" +
"\t\t\t\t\t\t\t           /&%#####(#((((#((((%@@@(##,                      \n" +
"\t\t\t\t\t\t\t           #&#((###((((((#(((((&&&@(((                      \n" +
"\t\t\t\t\t\t\t           #&#(####((((((((((((%@@@@((.                     \n" +
"\t\t\t\t\t\t\t           %&#(##(##((((((#((((%@@@@@((                     \n" +
"\t\t\t\t\t\t\t          *#&((####((((((((((((%@&&@&%(                     \n" +
"\t\t\t\t\t\t\t          #%%((#####(((((((((((&%%@@&@((                    \n" +
"\t\t\t\t\t\t\t          #%#(((###((((((((((((@&&&@&&#(                    \n" +
"\t\t\t\t\t\t\t          %%#(((###(((((((((((#@&&&@@(((,                   \n" +
"\t\t\t\t\t\t\t          %%(((#######((((((((%&&&%@@((((                   \n" +
"\t\t\t\t\t\t\t         *%#((((######((((((((&&@@@@@((((                   \n" +
"\t\t\t\t\t\t\t         #%#(((((######(((((((@@@@@@@@(((                   ");
        
        //Setting the field image art and storing a artistic image 
        enemy[4].setImageArt("     .                      "
                + "   .&%%##%                      \n" +
"\t\t\t\t\t\t\t    ...  .                    .&%%##&#/                     \n" +
"\t\t\t\t\t\t\t      ,###*.                 *&&%%%%%##                     \n" +
"\t\t\t\t\t\t\t      *%#(#%%%(.           ,/%%&&&&%%/(.                    \n" +
"\t\t\t\t\t\t\t       /#&((///(&/*,,*****/#%#%%%#####*.                    \n" +
"\t\t\t\t\t\t\t        *(((#((%#%%#%(((((((##(#%%###(,.                    \n" +
"\t\t\t\t\t\t\t         *%((#((#%(#(((#(((/#/////(((/*,.                   \n" +
"\t\t\t\t\t\t\t           *#(/(((//(((/../(((%@%###/(*,.                   \n" +
"\t\t\t\t\t\t\t            ,/*(/(#@@(/,   .,(*/&.**////,                   \n" +
"\t\t\t\t\t\t\t            .,****,#@*/,     ,,.,*/////**                   \n" +
"\t\t\t\t\t\t\t             ,*******,,.       .,***##(/*.                  \n" +
"\t\t\t\t\t\t\t              ,,****,.   ....,   .,.,**,...**.              \n" +
"\t\t\t\t\t\t\t               .,*,,.      ....   ......   .,*/*.           \n" +
"\t\t\t\t\t\t\t                  ... . .............        .*//*          \n" +
"\t\t\t\t\t\t\t                        .....,.......         .*/**.        \n" +
"\t\t\t\t\t\t\t                         ........             .*//*.      ");
        
        //Setting the field image art and storing a artistic image 
        enemy[5].setImageArt("                          "
                + "                                  \n" +
"\t\t\t\t\t\t\t                         ///////%                           \n" +
"\t\t\t\t\t\t\t                       %///#/(/%(                           \n" +
"\t\t\t\t\t\t\t                       //*(,,///%#/.                        \n" +
"\t\t\t\t\t\t\t                 &&&&&&&(**,,,,#/&&&&&&&&,                  \n" +
"\t\t\t\t\t\t\t              #,,&&&&&&&//%.  //%&&&&&&*,,,,(/#             \n" +
"\t\t\t\t\t\t\t            .,,,,,&&&&&&&&/(##/%&&&&&&&,,,,//*## ,          \n" +
"\t\t\t\t\t\t\t           /,,,,,,&&&&&&&&&&&&&&&&&&&&&,,,,,,,##*/,         \n" +
"\t\t\t\t\t\t\t          (,,,,,,,*&&&&&&&&&(&&&&&&&&&&//,,,,/%%,*,.        \n" +
"\t\t\t\t\t\t\t         .,,,,,,/*(&&&&&&&&&&&&&&&&&&&&%%#,,,,,,.,/,/       \n" +
"\t\t\t\t\t\t\t       ,,,,,,*/ ,&&&&&&&&&&&&&&&&&&&&&&&#(,#/(*//*,(       \n" +
"\t\t\t\t\t\t\t       /,,,,,,(  &&&&&&&&&&&&&&&&&&&&&&&&&     .,           \n" +
"\t\t\t\t\t\t\t       #,*/,,*%  &&&&&&&&&&&&&&&&&&&&&&&&&                  \n" +
"\t\t\t\t\t\t\t        .**,,/   &&&&&&&&&&&&&&&&&&&&&&&&&                  \n" +
"\t\t\t\t\t\t\t         ,,,,/,  &&&@&&&&&&&&&&&&&&&&@&&&&&                 \n" +
"\t\t\t\t\t\t\t        *,,,,,/ @&&&&&&&&&&&@@@&&%&&&&&&&&&&                \n" +
"\t\t\t\t\t\t\t        (,,(,#/, @&&&&&&&&&&&@&&&&&&&&&&&&&&#               \n" +
"\t\t\t\t\t\t\t         &,,*#(  ,//*@&&&&&&&.&&&&&&&&&&&&&                 \n" +
"\t\t\t\t\t\t\t                 #,,,///*/#   .%&#((*////,.                 \n" +
"\t\t\t\t\t\t\t                  *,,,,/*          #//,,,,,(                \n" +
"\t\t\t\t\t\t\t                 (,#(,*/           */,,#,,,/                \n" +
"\t\t\t\t\t\t\t                 /,,,,*#             /,,,,,                 \n" +
"\t\t\t\t\t\t\t                 (,,,,                (,,,,                 \n" +
"\t\t\t\t\t\t\t               *##,,,/(...............#((,,*                \n" +
"\t\t\t\t\t\t\t                                                            ");
        
        //Setting the field image art and storing a artistic image 
        enemy[6].setImageArt("                     "
                + " *#%(.                                 \n" +
"\t\t\t\t\t\t\t                 /%#%#############                          \n" +
"\t\t\t\t\t\t\t               ######################                       \n" +
"\t\t\t\t\t\t\t             %#####################%%%                      \n" +
"\t\t\t\t\t\t\t            %%######################%%/%                    \n" +
"\t\t\t\t\t\t\t        %&%%%%%%&&&&#####*############%%#                   \n" +
"\t\t\t\t\t\t\t  ,   &%%%%%%%%%%%%%%&## ,#############%%                   \n" +
"\t\t\t\t\t\t\t  ,.  &&&&%&&&&#&&&&&& ,,*############%%&&.              @&@\n" +
"\t\t\t\t\t\t\t   *,,#########%#######(#############%%%&%%%&          &&&  \n" +
"\t\t\t\t\t\t\t  .#####@#####(@#/#####*/##########%%%%&%%%%%%%&       &&&  \n" +
"\t\t\t\t\t\t\t #%**(##################%###%####%#%%%&%%%%%%%%%%&      %   \n" +
"\t\t\t\t\t\t\t     (#%####(##########%#########%%%%%%%%%%%%%%%%%&&  &&    \n" +
"\t\t\t\t\t\t\t      .%%%#%#######%%%#########%%%%&&%%%%%%%%%%%%&          \n" +
"\t\t\t\t\t\t\t         %###%%%%%#############%%&&%%%%%%%%%%%%%&           \n" +
"\t\t\t\t\t\t\t            %%#%###%#########%%%%&&&&&&&&&&%%%&&            \n" +
"\t\t\t\t\t\t\t               #,###%%#####%%%*        /%&%&%%%,            \n" +
"\t\t\t\t\t\t\t                  %%%%%%%%%# ,        &%&&  *%%             \n" +
"\t\t\t\t\t\t\t                %%%%& %%&(         .&&&&  .%%&              \n" +
"\t\t\t\t\t\t\t                   %%%%&                 .&&&               \n" +
"\t\t\t\t\t\t\t                  #        *                                ");
        
        //Setting the field image art and storing a artistic image 
        enemy[7].setImageArt("                     "
                + "                                       \n" +
"\t\t\t\t\t\t\t                                                            \n" +
"\t\t\t\t\t\t\t                                                            \n" +
"\t\t\t\t\t\t\t                  *          %%%%%%%.                      \n" +
"\t\t\t\t\t\t\t               ****,(      .%%%%%%%%%%%%                    \n" +
"\t\t\t\t\t\t\t           ***...   (    *%%%%%%    %%%%%%                  \n" +
"\t\t\t\t\t\t\t         *,.         ,  #%%%%%        %%%%%.                \n" +
"\t\t\t\t\t\t\t        *            ( %%%%%,          #%%%%%               \n" +
"\t\t\t\t\t\t\t                     / %%%%%##%     %(% %%%%%               \n" +
"\t\t\t\t\t\t\t                      / %%%%%  .    ,  %%%%#                \n" +
"\t\t\t\t\t\t\t                    .%#%   %%#  *.*/ .%%%.                  \n" +
"\t\t\t\t\t\t\t                      ##,    %%%%%%%%%%                     \n" +
"\t\t\t\t\t\t\t                       /%%%%%%%%%,,%%%%%%                   \n" +
"\t\t\t\t\t\t\t                       *    %%%%%%%%%%%%%%                  \n" +
"\t\t\t\t\t\t\t                        (   %%%%%%%%%%%&*%/                 \n" +
"\t\t\t\t\t\t\t                        (  .%%%%%%%%%%%%.%%                 \n" +
"\t\t\t\t\t\t\t                         . .%%%%%%%%%%%%                    \n" +
"\t\t\t\t\t\t\t                         ( .%%%%%%%%%%%%                    \n" +
"\t\t\t\t\t\t\t                         / .%%%%%%%%%%%%                    \n" +
"\t\t\t\t\t\t\t                          /*%%%%%%%%%%%%                    \n" +
"\t\t\t\t\t\t\t                          (%%%%%%%%%%%%%*                   \n" +
"\t\t\t\t\t\t\t                      ..*%%#%%%%%%%%%%%%%%&..               \n" +
"\t\t\t\t\t\t\t                                                            \n" +
"\t\t\t\t\t\t\t                                                        ");
        
        //Setting the field image art and storing a artistic image 
        enemy[8].setImageArt("                            "
                + "            .                   \n" +
"\t\t\t\t\t\t\t                        @(@  @%%%%%       *                 \n" +
"\t\t\t\t\t\t\t                @     %%%@%%%%%%%%/@&&@@@  @                \n" +
"\t\t\t\t\t\t\t               @  @@&&%@@@@@###%%@@@@&&&&@@@                \n" +
"\t\t\t\t\t\t\t              @@@@&&&%%%@@%%%%%@//**.*,@&@@                 \n" +
"\t\t\t\t\t\t\t                 @&@/  ,*//@%%%***,,,,**@@&  @@@            \n" +
"\t\t\t\t\t\t\t          @%%%%%@@&&**,,,**/&&&%*,,..,,%@@@&@&*////.        \n" +
"\t\t\t\t\t\t\t        @&&&&&&@@&@%&&%%&%&&&&&&&&&&&@@@@@&*@@&&&&&&@@      \n" +
"\t\t\t\t\t\t\t     @@@@@@@@@@@@@@@@%@@@@@@#%@%%&&&@&@&&@@/@@@@#@@@@@@@@   \n" +
"\t\t\t\t\t\t\t  &@@@/@ &&@*(@@@@@@@&&&&&&&&&&&&&&&&@%@@&#@%@@@&@    @@@%@ \n" +
"\t\t\t\t\t\t\t @@@%           @@@@*@@@@@%%%%%%&@@@@@@@@@@@            @@@@\n" +
"\t\t\t\t\t\t\t.///*             @@@@@@/@@@@@@@@@@*@@@@@@              @@@@\n" +
"\t\t\t\t\t\t\t.@@@@                @&@@@/@****@@@@@&@.                @@@/\n" +
"\t\t\t\t\t\t\t @@@@@                 @@@@@@@@@@@@@@              @&@@&&&@@\n" +
"\t\t\t\t\t\t\t  @&&&&@@@@           .@@@@@#**%@@@@&@                %@@@@ \n" +
"\t\t\t\t\t\t\t  @@%%@,              .@@@&&&&&&&&&@@@              .@@ @.@@\n" +
"\t\t\t\t\t\t\t @@@@@ @@@             #@@&@@@@@@%%@@               @ @@& @@\n" +
"\t\t\t\t\t\t\t     %               @@@@@  @&&@@  @@@@@                    \n" +
"\t\t\t\t\t\t\t                   @@@@@             @@@@&                  \n" +
"\t\t\t\t\t\t\t                  @@@@               @@@@                  \n" +
"\t\t\t\t\t\t\t                   @//#@             @**/@                  \n" +
"\t\t\t\t\t\t\t                   @@@@@@@         @@@@@@%                  \n" +
"\t\t\t\t\t\t\t             ##@@&&&&@@@@         @@@@&&&&@@##             \n" +
"\t\t\t\t\t\t\t               @#%##%%%&            .&%%%##%#@            ");
        
        //Setting the field image art and storing a artistic image 
        enemy[9].setImageArt("                       "
                + "      %@                             \n" +
"\t\t\t\t\t\t\t                    @*,.,,..*@        .,%@                  \n" +
"\t\t\t\t\t\t\t               ,/.,,,////*//*//#        @,,,,,@             \n" +
"\t\t\t\t\t\t\t             /,,*///%/*((@##(######/#@/.@((@///,,,#         \n" +
"\t\t\t\t\t\t\t           /*//((######//(#/*///////////(####&%@((&/*       \n" +
"\t\t\t\t\t\t\t         @//(%&#(#////////#%@*////////////&%//&%%%%%@(      \n" +
"\t\t\t\t\t\t\t    @    @@%%%%#/*/////////#@////////////((// %@@,          \n" +
"\t\t\t\t\t\t\t   ,&,           @/**/////////////////////@                 \n" +
"\t\t\t\t\t\t\t *(, &/**///////.@@@@  &#//**///////@(%,#/&./***/&#*  @     \n" +
"\t\t\t\t\t\t\t @,/((/@&@ @@*..,*/@   @/////,@%///*#,@*/(@     ,&##*/ @    \n" +
"\t\t\t\t\t\t\t  /    %*@/(% @@       @@#######/@    (@   ,((/@@           \n" +
"\t\t\t\t\t\t\t         @         /*/*@ /@////*/./*,@  @@@                 \n" +
"\t\t\t\t\t\t\t             @@@@,/,,   @//(      .(@. /*#                  \n" +
"\t\t\t\t\t\t\t            @ &@          ,@%@        @(//@#@               \n" +
"\t\t\t\t\t\t\t                        ,/((*(@%          @/,,/             \n" +
"\t\t\t\t\t\t\t                       @..*(((            @(///*,(          \n" +
"\t\t\t\t\t\t\t                      @    ,*               (////**/        \n" +
"\t\t\t\t\t\t\t                             @              @(/////**@      \n" +
"\t\t\t\t\t\t\t                                          &#/((((((@%,,/@(  \n" +
"\t\t\t\t\t\t\t                                          @@////(/(/////@&  \n" +
"\t\t\t\t\t\t\t                                         @                @ \n" +
"\t\t\t\t\t\t\t *.                                     .          *       "); 
        
        //Setting the field image art and storing a artistic image 
        enemy[10].setImageArt("                      "
                + "                                     \n" +
"\t\t\t\t\t\t\t            & .@@%&,  @                                     \n" +
"\t\t\t\t\t\t\t           ,@%..@&&&@%@% @                                  \n" +
"\t\t\t\t\t\t\t        @@@@(@@&&%%%%&&&@@%*.                               \n" +
"\t\t\t\t\t\t\t           **@@&&&@&&&%%%.,  *                              \n" +
"\t\t\t\t\t\t\t         ,@&@@%. &@@@@@&@&&&*(                              \n" +
"\t\t\t\t\t\t\t          %@%&&&&&@&@@%% #       .                          \n" +
"\t\t\t\t\t\t\t        ,@%&&@(@.@@&@&@%@@@,  @# .* @  @        @           \n" +
"\t\t\t\t\t\t\t            @@ &/@@&&@@&%%@*@*..      @%/     @/(           \n" +
"\t\t\t\t\t\t\t          *.,,&*&@%&&@#**@**%@@@@@@@% @ ,@(  ,  %           \n" +
"\t\t\t\t\t\t\t          @  *%%@&%&@&@***@&@&@&&&&&%&@%,@  . #             \n" +
"\t\t\t\t\t\t\t            /&@%@%&&& */*&(@@@@@@@@@@%&**@ &  @@  #         \n" +
"\t\t\t\t\t\t\t            @@&&%%&&&@@&&&&&&&&&&&@&@&(*%*   &&&&#  *       \n" +
"\t\t\t\t\t\t\t              &&@&@@@&&&&&&&&&&&&&&&&@@*  ,  &&.@@&&  %     \n" +
"\t\t\t\t\t\t\t              %&%@@@@%&&&&&&&&&&&&&&&&(    ,,(&&&&&&&&  @   \n" +
"\t\t\t\t\t\t\t              @@&&&&&&@&&@*,%@@@&&&&&&      ***&&&&&@&&#.*  \n" +
"\t\t\t\t\t\t\t               &&&&&@(&&&&&@@@@@&&&&&&@&@@@&@.  &@@@@@&&%,@ \n" +
"\t\t\t\t\t\t\t              @&&&&,  @&&&@@@@@&@&@&@&@&@@,@@&@&@/.@&&&&&*& \n" +
"\t\t\t\t\t\t\t             &%@@@&@ @@@&&@@@&&&&@%@&&&&&@%&&&&&&@*@&&&&&*# \n" +
"\t\t\t\t\t\t\t             &&@&&@&&&@@@@@@&@&&&@%&&&%%%%%&@%%@@@@&@%&&*@  \n" +
"\t\t\t\t\t\t\t            @&&&%  &&&&&@@%%@%&&&&@&&&&&&&&&&@@%@@@&&&(*&   \n" +
"\t\t\t\t\t\t\t            &&&   @&&%   @&&&&&@@@&&@&&&&&&&@&@,@@/**@      \n" +
"\t\t\t\t\t\t\t           %&@   %&&. @@%@  @@&&&&&@&&&&&&&&&&&&&@@&        \n" +
"\t\t\t\t\t\t\t         (%&@  ,%&&%#(@&@&&&&@/  @@%&  ,&@&&&&&&&@&&        \n" +
"\t\t\t\t\t\t\t     @&%@@@@  &@&&(&(.      %&&&@@@@&%%&&&&&@@*.            \n" +
"\t\t\t\t\t\t\t    *&%*@,.##@@&@@         ,%****@&@@&@/                    \n" +
"\t\t\t\t\t\t\t                                                            ");
        
        //Setting the field image art and storing a artistic image 
        enemy[11].setImageArt("          "
                + "                 ,,, .#./                         \n" +
"\t\t\t\t\t\t\t                         (*******,,,                        \n" +
"\t\t\t\t\t\t\t                    .*.*,@@@@#***/@@@.*.                    \n" +
"\t\t\t\t\t\t\t                    @. /.../....%*..**,**&                  \n" +
"\t\t\t\t\t\t\t                       @(@@/  /,.*@@ @                      \n" +
"\t\t\t\t\t\t\t@@/                   @@@@.,      .&@@@@#                   \n" +
"\t\t\t\t\t\t\t@   @@@             @@@@@@@@  . % @@@@@@@@@@@               \n" +
"\t\t\t\t\t\t\t    @   .@@@*      @@@@@/@@@.*/. @@%@@@@@@@@@%              \n" +
"\t\t\t\t\t\t\t        .@@@   @@@@@@@@&@@@@    &@@@@@@@@%@@@@(             \n" +
"\t\t\t\t\t\t\t            @@@@@@@@  @@@@@     @@@@@.  %@@@@@@@            \n" +
"\t\t\t\t\t\t\t      @@@@@@@@@@*      @@@@@@@@   ..  .% .*@@@@@@           \n" +
"\t\t\t\t\t\t\t,@@  @,.@@@@@@         @@@@/.  *#@@.  % @    (& @           \n" +
"\t\t\t\t\t\t\t@@   .%  @@            @@@@*# # .@@@ .@@ * (,@@%,,@#.# &    \n" +
"\t\t\t\t\t\t\t (,@@ (              @@@@@@     ,@@@@@@@..,,,,/*    *       \n" +
"\t\t\t\t\t\t\t                   @@@@@@/@@ . ,*,@@@@@(@@@@@&              \n" +
"\t\t\t\t\t\t\t                 @@@@@@@@@@@(%/@&@(@@@@@@@@@@@@@,           \n" +
"\t\t\t\t\t\t\t                 @@@@ @@**,@@@@@@@@@@@@@@@@@@@@@            \n" +
"\t\t\t\t\t\t\t                   @@@@,    ,,,,***/*/@@@@@*.               \n" +
"\t\t\t\t\t\t\t                 .@,.,,   .,,.  ...  **@#@@@@               \n" +
"\t\t\t\t\t\t\t                @,,.,,,  .****@@@@*,*  ...@@@,@@@/          \n" +
"\t\t\t\t\t\t\t              &,,,       ,,@      @,*   .,,.,*(@@@@&  @(&@@@");
        
        //Creating a title image
        System.out.println("                                             "
                + "                                   \n" +
"                                                                          "
                + "      \n" +
"                ...                                                        "
                + "     \n" +
"             ,&#.  /&#                                     #%#####(          "
                + "   \n" +
"          .@&%&.    *&#                                   #%.    .%(        "
                + "    \n" +
"         (%,       ,&#                                    /%(       *%#     "
                + "    \n" +
"         *&#.   .    *&#          ..,,,,.. .            .%#*          #*    "
                + "    \n" +
"           ,(%###&(.    &%/.(&%#(*.       .*(#%%/    .#%/     ,(%###%%*    "
                + "     \n" +
"                  *&#.  ,&%(.                   .(%%%#.    .#%*            "
                + "     \n" +
"                    .%%&(.. .   . . .        ..   . (%/ .(%*                "
                + "    \n" +
"                    /&%/(((%%%%%%%#%%%%%##((/**,.... .(%#.                  "
                + "    \n" +
"                .,*#&###%%%%%%%%#%#%%%%%%%%%#####(((###%&(                 "
                + "     \n" +
"         ,/%&&#/,. . ,.,*,***///*///**/*****,,.. ...,*/(#%%%#/*.           "
                + "     \n" +
"     *#%%&&%((*,...(%,.                         ..,**/(#&(,   .*(#%%/.    "
                + "      \n" +
"               ,/#%&(     ,%&&&&&/.         /(((/*       (#..,**/(##%#(#/   "
                + "    \n" +
"                   ##    %&&&&&&&&&/     .&%&&%%%&%%.    (#*,.              "
                + "    \n" +
"                   .&(  .&&&&&&&&&&%.    #&&&%&&%&%&/   ,%,                "
                + "     \n" +
"                    ,@#  #&&&&&&&&&,     (&&&&&&&&&&*  /%*                 "
                + "     \n" +
"                     .%&/ .*%&&%#,  .**,  .#&%&%&%(. .%%                   "
                + "     \n" +
"                   *%&*.%&#.        %&&&&          *%%*(%*                  "
                + "    \n" +
"                .#&*.    /&&&&/.      ..      .*(%%%..   /&(                "
                + "    \n" +
"        ./%%%%%&(.    *&%,  ,@%(%&&&&%%#%%&%%&&#   .%%/    .#%//(/*         "
                + "    \n" +
"       #&,    .    ,%@,     %#/&&&#,,,%...,%%%%%%    ./&#.    ,.  ,%(       "
                + "    \n" +
"       #&*       (&#.      ,@#%%&/   .&. . ,#,#%&.     .*%#.       ,%/       "
                + "   \n" +
"        .*%&(     #&.      .%%.  ..***////*,,  #&      .%%.    *#%%#,        "
                + "   \n" +
"           ,@&/,/%&*         /&%* .         *#%*        ,%%,  .%%.          "
                + "    \n" +
"              ..                .,/%@@@@@&#*.               ...            ");
        System.out.print("\n");
        //Creating a title
        System.out.println("░█████╗░███╗░░██╗███"
                + "████╗  ██████╗░██╗██████"
                + "█╗░█████╗░███████╗\n" +
"██╔══██╗████╗░██║██╔════╝  ██╔══██╗█"
                + "█║██╔════╝██╔══██╗██╔════╝\n" +
"██║░░██║██╔██╗██║█████╗░░  ██████╔╝█"
                + "█║█████╗░░██║░░╚═╝█████╗░░\n" +
"██║░░██║██║╚████║██╔══╝░░  ██╔═══╝░█"
                + "█║██╔══╝░░██║░░██╗██╔══╝░░\n" +
"╚█████╔╝██║░╚███║███████╗  ██║░░░░░██"
                + "║███████╗╚█████╔╝███████╗\n" +
"░╚════╝░╚═╝░░╚══╝╚══════╝  ╚═╝░░░░░╚═"
                + "╝╚══════╝░╚════╝░╚══════╝");
        
        //Making a barrier to split the title and main menu
        System.out.println("============================================="
                + "==========================================================="
                + "============");
        
        //Welcoming user to main menu
        System.out.println("WELCOME TO THE MAIN MENU! (Click ENTER to "
                + "continue) ");
        
        //Calling on dialoguecontinue method for special effects
        dialogueContinue();
        
        //Creating a do-while loop to loop back to the main menu options
        do{    
            //Creating a do-while loop to error trap for user input
            do{
                //Making menu count = 1
                menuCount = 1;
                
                //Asking user to input which option from main meny they want
                System.out.println("Please select one of the following "
                        + "options to continue your journey: ");
                
                //Displaying menu options
                System.out.println(menuCount + ". NEW GAME");
                
                //After each option is displayed count will be ++ for others
                menuCount++;
                
                /*
                Creating if statement for if checkPoint is greater then one 
                a continue option will print as the user will have old saved 
                data, if not there will be no continue option
                */
                if (checkPoint > 1)
                {
                    //Printint continue option
                    System.out.println(menuCount + ". CONTINUE");
                    
                    //After each option is displayed count will be ++ for others
                    menuCount++;
                }
                
                //Print rules option
                System.out.println(menuCount + ". RULES");
                
                //After each option is displayed count will be ++ for others
                menuCount++;
                
                //Print backstory option
                System.out.println(menuCount + ". BACKSTORY");
                
                //Create try-catch for error trapping
                try
                {   
                    //Create scanning for user input
                    menuOption = scanN.nextInt();
                }
                //Catching if the user inputs anything other then a integer
                catch (Exception e)
                {
                    //Telling user to print correct value
                    System.out.println("\n");
                    System.out.println("Please enter an integer.");

                    //Resetting scanner
                    scanN.nextLine();
                }
                
                //If the user inputs an option not on the menu it will resetloop
                if(menuOption < 1 || menuOption > menuCount)
                {
                    //Print error message
                    System.out.println("Invalid option, chose a number "
                            + "between (1-4)");
                }
            
            //Create while statement to loop back if user input is not valid
            }while (menuOption < 1 || menuOption > menuCount);
            
            //Create switch method which checks what the user inputted
            switch (menuOption)
            {
                //If user inputted 1, case one would run a new game file
                case 1:
                {
                    //Calling on introduction method for new game
                    introduction(gameSave, enemy, gameDifficulty, checkPoint, 
                            player, enemyNumber, attack, potion, wild);
                    break;

                }
                
                //If the user inputted 2 they would continue their journey
                case 2:
                {
                    
                    //Creating a do-while loop for error trapping
                    do{
                        
                        //Creating a switch statement to load checkpoint
                        switch(checkPoint)
                        {
                            /*
                            If checkpoint = 2, 3, 4 ,5 then the player will be
                            taken to the first island 
                            */
                            case 2: case 3: case 4: case 5:{

                                islandOne(gameSave, enemy, gameDifficulty, 
                                        checkPoint, player, enemyNumber, 
                                        attack, potion, wild);
                                break;
                            }
                            /*
                            If checkpoint = 6, 7, 8, 9 then the player will be
                            taken to the second island
                            */
                            case 6: case 7: case 8: case 9:
                            {
                                islandTwo(gameSave, enemy, gameDifficulty, 
                                        checkPoint, player, enemyNumber, 
                                        attack, potion, wild);
                                break;
                            }
                            /*
                            If checkpoint = 10,11,12 then the player will be
                            taken to the third island 
                            */
                            case 10: case 11: case 12:
                            {
                                islandThree(gameSave, enemy, gameDifficulty, 
                                        checkPoint, player, enemyNumber, 
                                        attack, potion, wild);
                                break;
                            }
                            /*
                            If checkpoint = 13,14,15 then the player will be
                            taken to the fourth island
                            */
                            case 13: case 14: case 15:
                            {
                                islandFour(gameSave, enemy, gameDifficulty, 
                                        checkPoint, player, enemyNumber, 
                                        attack, potion, wild);
                                break;
                            }
                            /*
                            If checkpoint = 16 then the player will be
                            taken to the final destination and puzzle
                            */
                            case 16:
                            {
                                puzzle(player);
                                finalEnding(gameSave, enemy, gameDifficulty, 
                                        checkPoint, player, enemyNumber, 
                                        attack, potion, wild);
                                break;
                            }
                        }
                    /*
                     Creating while which keeps looping back until the player 
                        finishes all the checkpoints, making the checkpoint 
                        transition smooth
                    */    
                    }while(checkPoint <= 16);
                }
                
                /*
                if the user inputs 3 then he will be displayed with all the 
                rules
                */
                case 3:
                {
                    //Print out game rules 
                    System.out.println("================================="
                            + "============================");
                    System.out.println("RULES:");
                    System.out.println("Continuing Dialogue: Press ENTER "
                            + "to continue with the story.");
                    System.out.println("\nFighting Prompts: Press whichever ava"
                            + "ilable option to proceed \nto the next round, "
                            + "EX. *I press 1 to use the strike attack* ");
                    System.out.println("If you use stranger sacrifice it "
                            + "can have consequences to the future.");
                    System.out.println("\nItems: Any items that you gain "
                            + "during your journey will be lost if the game is "
                            + "ended.");
                    System.out.println("\nFinishing Game: Proceed through "
                            + "all four islands and make it \nto the final "
                            + "destination to complete the game.");
                    System.out.println("\nFinal Ending: Some of the actions "
                            + "you commit during your playthrough \neffect the "
                            + "type of ending you recieve.");
                    System.out.println("\nSaving Game: The game will "
                            + "automatically save at times.");
                    System.out.println("================================="
                            + "============================");
                    //end barrier
                    System.out.println("\n");

                    break;
                }
                
                /*
                if the user inputs 4 then he will be dsplayed which the 
                backstory of the game
                */
                case 4:
                {
                    System.out.println("\n");
                    System.out.println("================================="
                            + "==============================================="
                            + "====================================");
                    System.out.println("BACKSTORY: ");
                    System.out.println("You were a brave pirate known to "
                            + "many people, you protected those who were "
                            + "innocent and killed those who were not. \nOne "
                            + "day you found the mystery of the ONE PIECE (the "
                            + "greatest tressure known to mankind) and you were"
                            + " lost in desire. \nYou swore to obtain the One P"
                            + "iece as you were the greatest pirate known to ma"
                            + "n. After a long journey you obtained a \nmagical"
                            + " compass which led to the location of One Pice, "
                            +"but as you were making your way to it, you were a"
                            +"mbushed by \nthe PIRATE KING himself. The compass"
                            + " broke into 5 pieces and you fell into a deep "
                            + "sleep. Now you will wake up and find \nthose "
                            + "missing pieces to complete your journey.");
                    System.out.println("================================="
                            + "==============================================="
                            + "====================================");
                    System.out.println("\n");
                    break;
                }
            }
        //creating a while to keep looping until the player finishes the game
        }while(checkPoint <= 16);
        
        //Print out the end message for final remarks
        System.out.println(" .----------------. .----------------. .---"
                + "-------------.   .----------------. .----------------"
                + "-..----------------. \n" +
"| .--------------. | .--------------. | .--------------. | | .----------"
                + "----. | .--------------. | .--------------. |\n" +
"| |  _________   | | |  ____  ____  | | |  _________   | | | |  _______"
                + "__   | | | ____  _____  | | |  ________    | |\n" +
"| | |  _   _  |  | | | |_   ||   _| | | | |_   ___  |  | | | | |_   ___ "
                + " |  | | ||_   \\|_   _| | | | |_   ___ `.  | |\n" +
"| | |_/ | | \\_|  | | |   | |__| |   | | |   | |_  \\_|  | | | |   | |_ "
                + " \\_|  | | |  |   \\ | |   | | |   | |   `. \\ | |\n" +
"| |     | |      | | |   |  __  |   | | |   |  _|  _   | | | |   |  _|  "
                + "_   | | |  | |\\ \\| |   | | |   | |    | | | |\n" +
"| |    _| |_     | | |  _| |  | |_  | | |  _| |___/ |  | | | |  _| |___/"
                + " |  | | | _| |_\\   |_  | | |  _| |___.' / | |\n" +
"| |   |_____|    | | | |____||____| | | | |_________|  | | | | |________"
                + "_|  | | ||_____|\\____| | | | |________.'  | |\n" +
"| |              | | |              | | |              | | | |          "
                + "    | | |              | | |              | |\n" +
"| '--------------' | '--------------' | '--------------' | | '----------"
                + "----' | '--------------' | '--------------' |\n" +
" '----------------' '----------------' '----------------'   '------------"
                + "----' '----------------' '----------------' ");
    }
    
    /***************
    * Method name: islandOne
    * Description: All your journey at island one 
     * @param gameSave
     * @param enemy
     * @param gameDifficulty
     * @param checkPoint
     * @param player
     * @param enemyNumber
     * @param attack
     * @param potion
     * @param wild
     * @throws java.lang.InterruptedException
    **************/
    public static void islandOne(File gameSave, Enemies[] enemy, int 
            gameDifficulty, int checkPoint, MainCharacter player, 
            int enemyNumber, Attacks[] attack, Potions[] potion, Joker wild) 
            throws InterruptedException
    {
        //Creating a scanner object for strings 
        Scanner scanS = new Scanner(System.in);
        
        //Creating choice string for user input
        String choice;
        
        //Creating do-while loop
        do
        {
            //Creating a switch which reads the checkPoint value
            switch(checkPoint)
            {
                /*
                If checkPoint = 2 then it will print the journey after that
                checkpoint
                */
                case 2: 
                {
                    System.out.println("================================"
                            + "==============================================="
                            + "===");
                    //print out opening statement
                    System.out.println("*You travel for 40 days straight "
                            + "and when supplies finally run out...you make it "
                            + "to the island*");
                    dialogueContinue();
                    
                    //print out image art of the island and title
                    System.out.println("  ________                __    "
                            + "                __     _      __         "
                            + "       __\n" +
" /_  __/ /_  ___     ____/ /__  ________  _____/ /_   (_)____/ /___ ___"
                            + "__  ____/ /\n" +
"  / / / __ \\/ _ \\   / __  / _ \\/ ___/ _ \\/ ___/ __/  / / ___/ / __ "
                            + "`/ __ \\/ __  / \n" +
" / / / / / /  __/  / /_/ /  __(__  )  __/ /  / /_   / (__  ) / /_/ / /"
                            + " / / /_/ /  \n" +
"/_/ /_/ /_/\\___/   \\__,_/\\___/____/\\___/_/   \\__/  /_/____/_/\\__,"
                            + "_/_/ /_/\\__,_/   \n" +
"                                                                        "
                            + "          ");
                    System.out.println("           ,                "
                            + "        '           .        '        ,  \n" +
"   .            .        '       .         ,         \n" +
"                                                   .       '     +\n" +
"       +          .-'''''-.     \n" +
"                .'         `.   +     .     ________||\n" +
"       ___     :             :     |       /        ||  .     '___\n" +
"  ____/   \\   :               :   ||.    _/      || ||\\_______/   \\\n" +
" /         \\  :      _/|      :   `|| __/      ,.|| ||             \\\n" +
"/  ,   '  . \\  :   =/_/      :     |'_______     || ||  ||   .      \\\n" +
"    |        \\__`._/ |     .'   ___|        \\__   \\\\||  ||...    ,"
                            + "   \\\n" +
"   l|,   '   (   /  ,|...-'        \\   '   ,     __\\||_//___          \n" +
" ___|____     \\_/^\\/||__    ,    .  ,__             ||//    \\    .  ,\n" +
"           _/~  `\"\"~`\"` \\_           ''(       ....,||/       '   \n" +
" ..,...  __/  -'/  `-._ `\\_\\__        | \\           ||  _______   .\n" +
"   itz/jgs/ccm'`  `\\   \\  \\-.\\        /(_1_,..      || /\n" +
"                                            ______/\"\"\"\"");
                    
                    //print story line
                    
                    dialogueContinue();
                    System.out.println(player.getName()+": FINALLY I MADE IT!");
                    dialogueContinue();
                    System.out.println(player.getName()+": NOW... FIRST THINGS "
                            + "FIRST...I NEED FOOOOOD!");
                    dialogueContinue();
                    System.out.println("*You dock your ship and run "
                            + "straight ahead*");
                    dialogueContinue();
                    System.out.println("*A giant snake comes across "
                            + "your path*");
                    dialogueContinue();
                    
                    //print giant snake for special effect
                    System.out.println("                /||\\\n" +
"                ||||\n" +
"                ||||                      _____.-..-.\n" +
"                |||| /|\\               .-~@@/ / q  p \\\n" +
"           /|\\  |||| |||             .'@ _@/..\\-.__.-/\n" +
"           |||  |||| |||            /@.-~/|~~~`\\|__|/\n" +
"           |||  |||| |||            |'--<||     '~~'\n" +
"           |||  |||| d||            |>--<\\@\\\n" +
"           |||  |||||||/            \\>---<\\@`\\.\n" +
"           ||b._||||~~'              `\\>---<`\\@`\\.\n" +
"           \\||||||||                   `\\>----<`\\@`\\.\n" +
"            `~~~||||               _     `\\>-----<`\\@`\\.\n" +
"                ||||              (_)      `\\>-----<`\\.@`\\.\n" +
"                ||||              (_)        `\\>------<`\\.@`\\.\n" +
"~~~~~~~~~~~~~~~~||||~~~~~~~~~~~~~~(__)~~~~~~~~~`\\>-------<`\\.@`\\~~~~~~~~"
                            + "~~~~~\n" +
"  \\/..__..--  . |||| \\/  .  ..____( _)@@@--..____\\..--\\@@@/~`\\@@>-._  "
                            + " \\/ .\n" +
"\\/         \\/ \\/    \\/     / - -\\@@@@--@/- - \\@@@/ - - \\@/- -@@@@/-"
                            + " \\.   --._\n" +
"   .   \\/    _..\\/-...--.. |- - -\\@@/ - -\\@@@@/~~~~\\@@@@/- - \\@@/- -"
                            + " |   .\\/\n" +
"        .  \\/              | - - -@@ - - -\\@@/- - - \\@@/- - - @@- - -| "
                            + "     .\n" +
". \\/             .   \\/     ~-.__ - - - - -@@- - - - @@- - - - -__.-~  "
                            + ". \\/\n" +
"   __...--..__..__       .  \\/   ~~~--..____- - - - -____..--~~~    \\/_"
                            + "..--..\n" +
"\\/  .   .    \\/     \\/    __..--... \\/      ~~~~~~~~~     \\/ . \\/  .");
                    dialogueContinue();
                    System.out.println(player.getName()+": YOU'LL DO! IM GONNA "
                            + "EAT YOU UP.. NOW LETS DANCE.");
                    dialogueContinue();
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    
                    //Make enemy = 1 for player to fight first enemy
                    enemyNumber = 1;
                    
                    //Call on enemy battle method
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, wild
                            , gameSave, gameDifficulty);
                    System.out.println("*You cook the snake and eat it*");
                    dialogueContinue();
                    System.out.println(player.getName()+": BON APPETIT!");
                    dialogueContinue();
                    System.out.println("*You eat all the snake meat*");
                    dialogueContinue();
                    System.out.println(player.getName()+": Now then, I think "
                            + "its time to do some actual work.");
                    dialogueContinue();
                    
                    /*
                    Make checkpoint = 3 so when the do-while loops back it 
                    starts from the next checkpoint
                    */
                    checkPoint = 3;
                    
                    //Call on savegame method to save game data in file
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    break;
                }
                /*
                If checkPoint = 3 then it will print the journey after that
                checkpoint
                */
                case 3: 
                {
                    //Print out story line for third checkpoint
                    System.out.println("*You take out your broken compass"
                            + " and follow where it leads*");
                    dialogueContinue();
                    System.out.println(player.getName()+": If I keep following "
                            + "this path, I should find the lost piece of the "
                            + "compass.");
                    dialogueContinue();
                    System.out.println("Distant voice: HELP! HELP ME "
                            + "PLEASE.");
                    dialogueContinue();
                    System.out.println("*You run towards the voice and see"
                            + " a man getting attacked by a snake*");
                    dialogueContinue();
                    
                    //Give player an option which will change ending
                    System.out.println("BEWARE, YOUR ACTIONS WILL "
                                + "RESULT IN DIFFERENT FUTURES");
                    
                    //Create do-while loop for error trapping
                    do{
                        //ask the player if they would like to help
                        System.out.println("Would you like to help the "
                                + "man (yes/no)");
                        
                        //Create scanner
                        choice = scanS.nextLine();
                        
                        //If they dont chose yes or no print error message
                        if(!(choice.equalsIgnoreCase("Yes") || 
                                choice.equalsIgnoreCase("No")))
                        {
                            //printing error message
                            System.out.println("Invalid Option, "
                                    + "please enter either yes or no.");
                        }
                    
                    /*
                    Create while loop which will keep looping back until the 
                    player inputs a valid option
                    */    
                    } while (!(choice.equalsIgnoreCase("Yes") || 
                                choice.equalsIgnoreCase("No")));
                   
                   
                    //If player chose yes print this 
                    if(choice.equalsIgnoreCase("yes"))
                    {
                        //Make them battle the snake
                        System.out.println("\nPRESS (ENTER) "
                                + "TO START BATTLE");
                        dialogueContinue();
                        
                        //Make enemynumber = 1 for player to fight snake
                        enemyNumber = 1;
                        
                        //call on battle method
                        EnemyBattle(enemy, player, enemyNumber, attack, potion, 
                                wild, gameSave, gameDifficulty);
                        
                        //print thank you from the survivor
                        System.out.println("Stranger: Thank you so much "
                                + "warrior! Here take this as a gift for your "
                                + "bravery.");
                        dialogueContinue();
                        System.out.println("*Small Potion Acquired*");
                        
                        //Make potion quantity + 1 for the gift
                        potion[0].setQuantity(potion[0].getQuantity() + 1);
                        
                        //Add 10 to player reputation for a good deed
                        player.setReputation(player.getReputation() + 10);
                    }
                    //if player chose no this will print
                    else
                    {
                        System.out.println(player.getName()+": Im sorry "
                                + "stranger but Im busy.");
                        
                        //Sub 10 from player reputation for bad deed
                        player.setReputation(player.getReputation() - 10);
                    }
                    
                    /*
                    Make checkpoint = 4 so when the do-while loops back it 
                    starts from the next checkpoint
                    */
                    checkPoint = 4;
                    
                    //call on save game method to save data to a file
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    dialogueContinue();
                    break;
                }
                /*
                If checkPoint = 4 then it will print the journey after that
                checkpoint
                */
                case 4: 
                {
                    //print story line for checkpoint 4
                    System.out.println("*You continue on with "
                            + "your journey*");
                    dialogueContinue();
                    
                    System.out.println("*The compass leads you to "
                            + "some old ruins in the desert*");
                    dialogueContinue();
                    
                    System.out.println(player.getName() + ": Now we're getting "
                            + "somewhere.");
                    dialogueContinue();
                    
                    System.out.println("*You enter the old ruins and "
                            + "find a evolved scorpian, you have no chance but "
                            + "to fight*");
                    dialogueContinue();
                    
                    //Start battle with enemy
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    
                    //Make enemy number = 2 for player to fight second battle
                    enemyNumber = 2;
                    
                    //Call on enemy battle method to fight
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, wild
                            , gameSave, gameDifficulty); 
                    
                    //Inform user that their level went up
                    System.out.println("PLAYER LEVEL UP! ATTACKS UNLOCKED, "
                        + "HEALTH BOOSTED");
                    
                    //Set player level to 3 
                    player.setLevel(3);
                    
                    //set player stats to new stats
                    setPlayerStats(player, attack);
                    
                    //Make checkpoint = 5 to run the next checkpoint or save
                    checkPoint = 5;
                    
                    //Saving game data
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    break;
                    
                }
                /*
                If checkPoint = 5 then it will print the journey after that
                checkpoint
                */
                case 5: 
                {
                    //Continue with the story 
                    System.out.println("*You progress through the ruins "
                            + "and arrive to throne room*");
                    dialogueContinue();
                    System.out.println(player.getName() +": HERE IT IS!!");
                    dialogueContinue();
                    System.out.println(player.getName() +": THE LOST PEICE OF "
                            + "THE COMPASS!");
                    dialogueContinue();
                    
                    //Print missing piece for special effects
                    System.out.println("     ---   .    ____        -----    "
                            + "  ______   -----        .\n" +
"  ___     / \\             .....................      ____   / \\\n" +
"        .'   '.  --  ..:::::''''''''''''''''':::::..      .'   '.\n" +
"  ---   | ^ ^ |    .::::''''          (_     ''''::::. -- | ^ ^ '\n" +
"        | ^ ^ |  .::''                       _)    ''::.  | ^ ^ | --\n" +
"____     '...'  .::'              .-.      (_        '::.  '...'\n" +
"        .-.!_  .::'       _)     /   \\                '::.   ! ____\n" +
"       / / `-`.:'                '-.-'            _)    ':..\"\"\".\n" +
" --    ' |  '.|:'      _)         .'.       (_          ':/' |  \\\n" +
"       | |   |'.               _/^---^\\_                  |     . --\n" +
" ___    \\ .  '|               \\-------../         (_      \\   '.'\n" +
"        ' :   '        _)      '.\\:::/.'       (_   )_    |'   || ___\n" +
"        | |  .|      _(         | | |'|                   / ' . |\n" +
"    --  | '. | \\                '.\\ /.'                   '.  | |--\n" +
"        |'.   '|                 |[ ]|           (_       | .'  |____\n" +
"__    .'\\ |  .'\\                 '.^.'                    \\ |.  .\n" +
"     .'-.\\'. | |        _)        (:)                     | ||| |\n" +
"   .'    \\'..' .             _..--'''--.._      (_       /'-._.-'| ---\n" +
"   |       `-..'.         .-'             '-.           |      .-'.\n" +
"    \\            `-.    .'  ..            .. '.        .'-._.-'    `.\n" +
"--   )              `-./    '::.        .::'   \\   _.-'             /\n" +
"     '._/-..          /       '::.    .::'      \\-'              .-'\n" +
"         ::.`-.      ''        '::   ::'        ''       _..-\\_.'\n" +
"         :::   '._   | \\         '   '         / |    .-'   .:: _____\n" +
"____     :::      `-.|  '  .----..___..----.  '  | .-'      :::\n" +
"         :::          \\ |  _..--.     .--.._  | /-'         ::: ---\n" +
"         :::   _)     | ' /     |     |     \\ ' |  (        :::\n" +
"   --    :::          )   |   _.'     '._   |   (   )_      :::____\n" +
"    ____ :::          /'. \\_.'   )\\ /(   '._/ .'\\     (_    :::\n" +
"         :::       .-'|  `-->-@ /     \\ @->--'  |-.         :::\n" +
"         :::    .-'   \\         | / \\ |         /  `-.      :::  ---\n" +
" ----    '' _.-'       |        )/   \\(        |      `-.   :::  _____\n" +
"  _.-=--..-'          . \\ /\\               /\\ /          `-. ''\n" +
" /.._    `.        .-'   .\\ '-.\\.\\\\.//./.-' /.`-.           `---.._\n" +
"|    `.    \\    .-'      | '.             .' |   `-.                \\ \n" +
" \\    _\\.   `.-'         |   '-././.\\.\\.-'   |      `.               |\n" +
"  `.-'  |   /::::::::::: \\                   /::::::::`.      ,-.    /\n" +
" - |   /   /LGB     ----  '-.             .-'     ----  `.    |  \\_.'\n" +
"__ \\   | .'     _____        '-._._._._.-'     ____      |    |   |\n" +
"    `--'                                                 `-.  '._ / --\n" +
"                                                            `...-'");
                    dialogueContinue();
                    
                    //continue with story
                    System.out.println("*You pick up the piece and the "
                            + "building starts collapsing*");
                    dialogueContinue();
                    System.out.println("*You run for your life and make "
                            + "it outside*");
                    dialogueContinue();
                    System.out.println(player.getName() +": I MA- I MADE IT. "
                            + "IM OUTTA HERE!");
                    dialogueContinue();
                    System.out.println("*You run back to your ship and "
                            + "see a cloaked man sitting on your bench*");
                    dialogueContinue();
                    System.out.println(player.getName() +": Who the hell are "
                            + "you and why you on my ship?");
                    dialogueContinue();
                    System.out.println("Cloaked Man: I am the owner of "
                            + "what you have stolen.");
                    dialogueContinue();
                    System.out.println("Cloaked Man: Now give me back "
                            + "that crystal.");
                    dialogueContinue();
                    System.out.println(player.getName() +": You gonna have to "
                            + "fight me for it boss");
                    dialogueContinue();
                    
                    //Promptint player to start battle
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    
                    //Make enemynumber = 3 to fight the third enemy in list
                    enemyNumber = 3;
                    
                    //calling on enemy battle method to fight
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, wild
                            , gameSave, gameDifficulty);  
                    
                    System.out.println("*You dispose of the cloaked mans "
                            + "body and head to the next island*");
                    
                    dialogueContinue();
                    
                    //Making checkpoint = 6 to save to file and start next point
                    checkPoint = 6;
                    
                    //Save game data
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    System.out.println("================================"
                            + "==============================================="
                            + "===");
                    break;
                }
            }
        /*
        Making a while loop which will keep repeating until checkPoint = 6 and 
        the first island is completed
        */
        }while(checkPoint <= 5);
    }
    
    /***************
    * Method name: islandTwo
    * Description: All your journey at island two
     * @param gameSave
     * @param enemy
     * @param gameDifficulty
     * @param checkPoint
     * @param player
     * @param enemyNumber
     * @param attack
     * @param potion
     * @param wild
     * @throws java.lang.InterruptedException
    **************/
    public static void islandTwo(File gameSave, Enemies[] enemy, int 
        gameDifficulty, int checkPoint, MainCharacter player, 
        int enemyNumber, Attacks[] attack, Potions[] potion, Joker wild) 
            throws InterruptedException
    {
        //Create a do-while loop
        do
        {
            //Create a switch statement which reads checkpoint value
            switch(checkPoint)
            {
                
                /*
                If checkPoint = 6 then it will print the journey after that
                checkpoint
                */
                case 6: 
                {
                    System.out.println("================================"
                            + "==============================================="
                            + "===");
                    //print story line 
                    System.out.println("*After two long weeks of sailing,"
                            + " you finally reached the next island*");
                    dialogueContinue();
                    
                    //print island title and art
                    System.out.println("$$$$$$$$\\ $$\\                        "
                            + "$$$$$$\\  $$\\  $$\\                     $$$"
                            + "$$$\\   $$$$$$\\         $$$$$$\\  $$\\      "
                            + "               $$\\               \n" +
"\\__$$  __|$$ |                      $$  __$$\\ \\__| $$ |                 "
                            + "  $$  __$$\\ $$  __$$\\       $$  __$$\\ \\__"
                            + "|                    $$ |              \n" +
"   $$ |   $$$$$$$\\   $$$$$$\\        $$ /  \\__|$$\\$$$$$$\\  $$\\   $$\\ "
                            + "      $$ /  $$ |$$ /  \\__|      $$ /  \\__|$"
                            + "$\\  $$$$$$\\  $$$$$$$\\$$$$$$\\    $$$$$$$\\"
                            + " \n" +
"   $$ |   $$  __$$\\ $$  __$$\\       $$ |      $$ \\_$$  _| $$ |  $$ |     "
                            + " $$ |  $$ |$$$$\\           $$ |$$$$\\ $$ |"
                            + " \\____$$\\ $$  __$$\\_$$  _|  $$  _____|\n" +
"   $$ |   $$ |  $$ |$$$$$$$$ |      $$ |      $$ | $$ |   $$ |  $$ |     "
                            + " $$ |  $$ |$$  _|          $$ |\\_$$ |$$ |"
                            + " $$$$$$$ |$$ |  $$ |$$ |    \\$$$$$$\\  \n" +
"   $$ |   $$ |  $$ |$$   ____|      $$ |  $$\\ $$ | $$ |$$\\$$ |  $$ |   "
                            + "   $$ |  $$ |$$ |            $$ |  $$ |$$ |$"
                            + "$  __$$ |$$ |  $$ |$$ |$$\\  \\____$$\\ \n" +
"   $$ |   $$ |  $$ |\\$$$$$$$\\       \\$$$$$$  |$$ | \\$$$$  \\$$$$$$$ | "
                            + "      $$$$$$  |$$ |            \\$$$$$$  |$$ "
                            + "|\\$$$$$$$ |$$ |  $$ |\\$$$$  |$$$$$$$  |\n" +
"   \\__|   \\__|  \\__| \\_______|       \\______/ \\__|  \\____/ \\____$$ "
                            + "|       \\______/ \\__|             \\______/"
                            + " \\__| \\_______|\\__|  \\__| \\____/ \\____"
                            + "___/ \n" +
"                                                          $$\\   $$ |     "
                            + "                                           "
                            + "                                    \n" +
"                                                          \\$$$$$$  |      "
                            + "                                             "
                            + "                                 \n" +
"                                                           \\______/      "
                            + "                                            "
                            + "                                   ");
                    System.out.println("                           "
                            + "     ____                                      "
                            + "   \n" +
"                              .-\"    `-.      ,                             "
                            + "  \n" +
"                            .'          '.   /j\\                           "
                            + "   \n" +
"                           /              \\,/:/#\\                /\\      "
                            + "     \n" +
"                          ;              ,//' '/#\\              //#\\       "
                            + "   \n" +
"                          |             /' :   '/#\\            /  /#\\     "
                            + "    \n" +
"                          :         ,  /' /'    '/#\\__..--\"\"\"\"/    /#\\"
                            + "__      \n" +
"                           \\       /'\\'-._:__    '/#\\        ;      /#, \""
                            + "\"\"---\n" +
"                            `-.   / ;#\\']\" ; \"\"\"--./#J       ':____...! "
                            + "      \n" +
"                               `-/   /#\\  J  [;[;[;Y]         |      ;      "
                            + "  \n" +
"\"\"\"\"\"\"---....             __.--\"/    '/#\\ ;   \" \"  |     !    |   #"
                            + "! |        \n" +
"             \"\"--.. _.--\"\"     /      ,/#\\'-..____.;_,   |    |   '  |  "
                            + "      \n" +
"                   \"-.        :_....___,/#} \"####\" | '_.-\",   | #['  |   "
                            + "     \n" +
"                      '-._      |[;[;[;[;|         |.;'  /;\\  |      |       "
                            + " \n" +
"                      ,   `-.   |        :     _   .;'    /;\\ |   #\" |     "
                            + "   \n" +
"                      !      `._:      _  ;   ##' .;'      /;\\|  _,  |     "
                            + "   \n" +
"                     .#\\\"\"\"---..._    ';, |      .;{___     /;\\  ]#' |__"
                            + "....--\n" +
"          .--.      ;'/#\\         \\    ]! |       \"| , \"\"\"--./_J    /   "
                            + "      \n" +
"         /  '%;    /  '/#\\         \\   !' :        |!# #! #! #|    :`.__   "
                            + "   \n" +
"        i__..'%] _:_   ;##J         \\      :\"#...._!   '  \"  \"|__  |    `"
                            + "--.._\n" +
"         | .--\"\"\" !|\"\"\"\"  |\"\"\"----...J     | '##\"\" `-._       |  "
                            + "\"\"\"---.._    \n" +
"     ____: |      #|      |         #|     |          \"]      ;   ___...-\"T"
                            + ",  \n" +
"    /   :  :      !|      |   _______!_    |           |__..--;\"\"\"     ,;"
                            + "MM;  \n" +
"   :____| :    .-.#|      |  /\\      /#\\   |          /'               ''M"
                            + "M;  \n" +
"    |\"\"\": |   /   \\|   .----+  ;      /#\\  :___..--\"\";                "
                            + "  ,'MM; \n" +
"   _Y--:  |  ;     ;.-'      ;  \\______/#: /         ;                  ''M"
                            + "M; \n" +
"  /    |  | ;_______;     ____!  |\"##\"\"\"MM!         ;                   "
                            + " ,'MM;\n" +
" !_____|  |  |\"#\"#\"|____.'\"\"##\"  |       :         ;                   "
                            + "  ''MM  \n" +
"  | \"\"\"\"--!._|     |##\"\"         !       !         :____.....-------\"\""
                            + "\"\"\"\" |'\n" +
"  |          :     |______                        ___!_ \"#\"\"#\"\"#\"\"\"#"
                            + "\"\"\"#\"\"\"|  \n" +
"__|          ;     |MM\"MM\"\"\"\"\"---..._______...--\"\"MM\"MM]            "
                            + "       |   \n" +
"  \"\\-.      :      |#                                  :                   "
                            + "|  \n" +
"    /#'.    |      /##,                                !                   |"
                            + "  \n" +
"   .',/'\\   |       #:#,                                ;       .==.       "
                            + "|  \n" +
"  /\"\\'#\"\\',.|       ##;#,                               !     ,'||||',  "
                            + "   |  \n" +
"        /;/`:       ######,          ____             _ :     M||||||M     |"
                            + "  \n" +
"       ###          /;\"\\.__\"-._   \"\"\"                   |===..M!!!!!!M"
                            + "_____|  \n" +
"                           `--..`--.._____             _!_                   "
                            + " \n" +
"                                          `--...____,=\"_.'`-.____       "
                            + " fsc");
                    dialogueContinue();
                    System.out.println(player.getName()+": Man I hate Giants.");
                    dialogueContinue();
                    System.out.println(player.getName()+": I gotta get that "
                            + "lost piece and get out of here ASAP.");
                    dialogueContinue();
                    System.out.println("*You dock the ship and make your "
                            + "way in the city*");
                    dialogueContinue();
                    System.out.println(player.getName()+": The compass is tell"
                            + "ing me to go to the main castle..");
                    dialogueContinue();
                    System.out.println(player.getName()+": But the punishment "
                            + "for tresspassing there is death.....oh well lets"
                            + " get to it then.");
                    
                    /*
                    Make checkpoint = 7 so when the do-while loops back it 
                    starts from the next checkpoint
                    */
                    checkPoint = 7;
                    
                    //Save game data to file 
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    dialogueContinue();
                    break;
                }
                
                /*
                If checkPoint = 7 then it will print the journey after that
                checkpoint
                */
                case 7: 
                {
                    //Print story line
                    System.out.println("*You make your way to the castle "
                            + "and try to find a way in*");
                    dialogueContinue();
                    System.out.println("*You see an open window and climb "
                            + "through*");
                    dialogueContinue();
                    System.out.println(player.getName()+": Ah, Im finally in.");
                    dialogueContinue();
                    System.out.println("*A giant pet cat approaches you "
                            + "and tries to fight you*");
                    dialogueContinue();
                    
                    //print cat for special effects 
                    System.out.println("          .__....._            "
                            + " _.....__,\n" +
"            .\": o :':         ;': o :\".\n" +
"            `. `-' .'.       .'. `-' .'\n" +
"              `---'             `---'\n" +
"\n" +
"    _...----...      ...   ...      ...----..._\n" +
" .-'__..-\"\"'----    `.  `\"`  .'    ----'\"\"-..__`-.\n" +
"'.-'   _.--\"\"\"'       `-._.-'       '\"\"\"--._   `-.`\n" +
"'  .-\"'                  :                  `\"-.  `\n" +
"  '   `.              _.'\"'._              .'   `\n" +
"        `.       ,.-'\"       \"'-.,       .'\n" +
"          `.                           .'\n" +
"            `-._                   _.-'\n" +
"                `\"'--...___...--'\"`");
                    dialogueContinue();
                    System.out.println(player.getName()+": Im not scared of "
                            + "you big guy, lets do this!");
                    
                    dialogueContinue();
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    enemyNumber = 4;
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, wild
                            , gameSave, gameDifficulty); 
                    System.out.println("PLAYER LEVEL UP! ATTACKS UNLOCKED, "
                         + "HEALTH BOOSTED");
                    
                    //set player level to 4 so they level up
                    player.setLevel(4);
                    
                    //call on set players stats method to level up
                    setPlayerStats(player, attack);
                    dialogueContinue();
                    System.out.println(player.getName()+": Thats what you get "
                            + "kitty.");
                    
                    /*
                    Make checkpoint = 7 so when the do-while loops back it 
                    starts from the next checkpoint
                    */
                    checkPoint = 8;
                    
                    //Save game data to file
                    saveGame(gameSave, checkPoint, player, gameDifficulty); 
                    dialogueContinue();
                    break;
                }
                
                /*
                If checkPoint = 8 then it will print the journey after that
                checkpoint
                */
                case 8: 
                {
                    System.out.println("*You sneakily head towards the "
                            + "treassure room but keep hearing loud noises on "
                            + "the way*");
                    dialogueContinue();
                    System.out.println("*You make it to the tressure room"
                            + " but are suprised to find a sloppy giant "
                            + "guarding the door*");
                    dialogueContinue();
                    System.out.println(player.getName()+": Out of the way fatty"
                            + " I need something in there.");
                    dialogueContinue();
                    System.out.println("Giant: Who you calling fatty "
                            + "shorty? HAHAHAHAHA.");
                    dialogueContinue();
                    System.out.println(player.getName()+": Now you've done it, "
                            + "LETS FIGHT THEN!");
                    dialogueContinue();
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    
                    //Make enemy number = 5 to fight 5fth enemy
                    enemyNumber = 5;
                    
                    //Call on enemy battle method to fight 
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, wild
                            , gameSave, gameDifficulty); 
                    
                    System.out.println(player.getName()+": They never learn.");
                    dialogueContinue();
                    System.out.println("*You go inside the treassure room to "
                            + "find nothing there*");
                    dialogueContinue();
                    System.out.println(player.getName()+": DARN A DECOY!");
                    dialogueContinue();
                    System.out.println("*Suddenly you are surrounded "
                            + "by guards and cuffed*");
                    dialogueContinue();
                    System.out.println(player.getName()+": Ah they got me "
                            + "now!");
                    dialogueContinue();
                    System.out.println("*They bag you, making you go "
                            + "unconscious*");
                    
                    /*
                    Make checkpoint = 9 so when the do-while loops back it 
                    starts from the next checkpoint
                    */
                    checkPoint = 9;
                    
                    //save game data to file
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    dialogueContinue(); 
                    break;
                }
                /*
                If checkPoint = 9 then it will print the journey after that
                checkpoint
                */
                case 9: 
                {
                    //Print story line 
                    System.out.println("*You later wake up on your knees "
                            + "infront of the giant king*");
                    dialogueContinue();
                    System.out.println("<\\                     __\n" +
" \\\\                 .~~  ~~.\n" +
"  \\\\               /|~|     |\n" +
"   \\\\             /======\\  |\n" +
"  //\\\\           |>/_\\_<_=' |\n" +
"  ~\\  \\          `-`__  \\\\__|    _\n" +
" <<\\ \\ \\    ___    \\..'  `--'   / ~-.\n" +
"<<\\\\' )_) .+++++ _ (___.--'| _ /~-.  ~-.\n" +
"\\_\\' /   x||||||| `-._    _.' /~-. ~-.  `.\n" +
" |   |  |X++++++|     \\  /   /~-. ~-. ~-./\n" +
" |   `. .||||||||      []   /~-. ~-. ~-./\n" +
" |    |'  ++++++| :::  [] : `-. ~-. ~-.'\n" +
" |    `.        '  ::: []:: _.-:-. ~-/ |\n" +
" (_   /|     _.        []  |____~|`-'  |\n" +
" ||`-'| |_.-' |        |\\\\/|JUDGE|     `.\n" +
" `.___.-'     `.       ||`' \\~~~/ >.    l\n" +
"               |   ::  ||    `-' / /`---'\\\n" +
"               |    :  ||       ( [(_)[_](]\n" +
"               |       ||       | [       ]\n" +
"              .'       ||       |  \\.---./\n" +
"            __|________||_______|=.|____|_\n" +
"           |: o | o |~\\|%/~ o |_ o||-----|\n" +
"           |:--'|`-'||X\\/X|`-'| `-|`.  .-|\n" +
"           `--------'  ll `-------' |  |o|\n" +
"              |                  |  `. |_|\n" +
"              |               :: `.  |   |\n" +
"              |               ::  |  |  .'\n" +
"              |         |\\    ::  | .'  |\n" +
"              |         ||     :  | |.  |\n" +
"              `.       .'|     :  | ||..'\n" +
"               |       | `.    :  | |'//\n" +
"               |       |  |    : .' `='\n" +
"               |       |  |   :: |\n" +
"               `.      |  |   :: |\n" +
"                |      |  |   :: |\n" +
"                |_____ |  .-------.\n" +
"               / _     `./.-- :::  \\\n" +
"              | | `----' ||        |\n" +
"              | |        |        ||\n" +
"               \\         \\.-------./\n" +
"                |-------||.-------.|\n" +
"                /-.____.||         |                       \n" +
"                 |      ||         /\n" +
"                 `.     `:.       /\n" +
"                  |       |      |\n" +
"                  `,      |      |\n" +
"                   |      |      /\n" +
"                   `.     |     |\n" +
"                    |     |     |              \n" +
"                    |     |     |\n" +
"               __/  |     <     >  \\\n" +
"                /   /     /     \\\n" +
"               /   :.___.|.'\"\"`. |      \\\n" +
"            LS     `----//      \\\\    ___\\\n" +
"        /               `---------'       \\\n" +
"      _/          /\n" +
"            /__            |________\\");
                    System.out.println(player.getName()+": Hello your highness,"
                            + " long time no see! haha.");
                    dialogueContinue();
                    
                    //sorry for swearing miss kaiser
                    System.out.println("King: Cut the bullshit. I "
                            + "know your here for the missing compass piece.");
                    dialogueContinue();
                    System.out.println(player.getName()+": Nono sir, I would "
                            + "never steal from you!");
                    dialogueContinue();
                    System.out.println("King: Again, shut up and liste"
                            + "n.");
                    System.out.println("King: I will give it to you, but "
                            + "you must promise me half the tressure "
                            + "you find");
                    dialogueContinue();
                    System.out.println(player.getName()+": Sure, its better"
                            + " than dying here with nothing.");
                    dialogueContinue();
                    System.out.println("*You recieve the mising piece and"
                            + " leave the castle*");
                    dialogueContinue();
                    System.out.println("*As you are about to board your "
                            + "ship you hear a familiar voice*");
                    dialogueContinue();
                    System.out.println("Giant: Hey SHORTY, we arent "
                            + "done yet.");
                    dialogueContinue();
                    System.out.println(player.getName()+": Ready for round "
                            + "two already? Lets do this.");
                    dialogueContinue();
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    
                    //Make enemy number = 5 to fight 5fth enemy 
                    enemyNumber = 5;
                    
                    //Call on enemy battle method to fight
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, wild
                            , gameSave, gameDifficulty);
                    
                    System.out.println(player.getName()+": Again, I hate "
                            + "giants");
                    System.out.println("*You board your ship and set sail "
                            + "for the next island*");
                    
                    /*
                    Make checkpoint = 10 so when the do-while loops back it 
                    starts from the next checkpoint
                    */
                    checkPoint = 10;
                    
                    //save game data to file
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    System.out.println("================================"
                            + "==============================================="
                            + "===");
                    break;
                }
            }
        /*
        Making a while loop which will keep repeating until checkPoint = 9 and 
        the first island is completed
        */
        }while(checkPoint <= 9);
    }
    
    /***************
    * Method name: islandThree
    * Description: All your journey at island three 
     * @param gameSave
     * @param enemy
     * @param gameDifficulty
     * @param checkPoint
     * @param player
     * @param enemyNumber
     * @param attack
     * @param potion
     * @param wild
     * @throws java.lang.InterruptedException
    **************/
    public static void islandThree(File gameSave, Enemies[] enemy, int 
        gameDifficulty, int checkPoint, MainCharacter player, 
        int enemyNumber, Attacks[] attack, Potions[] potion, Joker wild) 
            throws InterruptedException
    {
        //Create scanner object 
        Scanner scanS = new Scanner(System.in);
        
        //Create string to store user input
        String choice;
        
        //Create do-while loop
        do
        {
            //Create switch statement which reads checkPoint 
            switch(checkPoint)
            {
                
                /*
                If checkPoint = 10 then it will print the journey after that
                checkpoint
                */
                case 10: 
                {
                    System.out.println("================================"
                            + "==============================================="
                            + "===");
                    
                    //Print story line
                    System.out.println("*After the incident with the "
                            + "giants you are feeling upset*");
                    dialogueContinue();
                    System.out.println("*Your mind is clouded and so "
                            + "is the sky*");
                    dialogueContinue();
                    System.out.println("*Out of nowhere your ship starts "
                            + "shaking immensely*");
                    dialogueContinue();
                    System.out.println(player.getName()+": What the HELL is "
                            + "going on here.");
                    dialogueContinue();
                    System.out.println("*A big stream of water lifts "
                            + "your ship up and takes it towards the sky*");
                    dialogueContinue();
                    System.out.println(player.getName()+": WOAAAH WOOOA MY "
                            + "SHIPPP!");
                    dialogueContinue();
                    System.out.println("*After a couple seconds longer it"
                            + " goes quiet*");
                    dialogueContinue();
                    System.out.println("*You look down the ship, "
                            + "and you realize you are in the sky, "
                            + "sailing in the clouds*");
                    dialogueContinue();
                    System.out.println("*You have reached your next "
                            + "island*");
                    dialogueContinue();
                    //Print island title and art
                    System.out.println("                             "
                            + "                                               "
                            + "                            \n" +
"                                                                             "
                            + "                            \n" +
"  .--.--.         ,-.                      ,---,              ,--,          "
                            + "                             \n" +
" /  /    '.   ,--/ /|                   ,`--.' |            ,--.'|          "
                            + "                       ,---, \n" +
"|  :  /`. / ,--. :/ |                   |   :  :            |  | :          "
                            + "           ,---,     ,---.'| \n" +
";  |  |--`  :  : ' /                    :   |  '  .--.--.   :  : '          "
                            + "       ,-+-. /  |    |   | : \n" +
"|  :  ;_    |  '  /        .--,         |   :  | /  /    '  |  ' |     ,--.-"
                            + "-.    ,--.'|'   |    |   | | \n" +
" \\  \\    `. '  |  :      /_ ./|         '   '  ;|  :  /`./  '  | |    /   "
                            + "    \\  |   |  ,\"' |  ,--.__| | \n" +
"  `----.   \\|  |   \\  , ' , ' :         |   |  ||  :  ;_    |  | :   .--. "
                            + " .-. | |   | /  | | /   ,'   | \n" +
"  __ \\  \\  |'  : |. \\/___/ \\: |         '   :  ; \\  \\    `. '  : |__  "
                            + "\\__\\/: . . |   | |  | |.   '  /  | \n" +
" /  /`--'  /|  | ' \\ \\.  \\  ' |         |   |  '  `----.   \\|  | '.'"
                            + "| ,\" .--.; | |   | |  |/ '   ; |:  | \n" +
"'--'.     / '  : |--'  \\  ;   :         '   :  | /  /`--'  /;  :    ;/  / "
                            + " ,.  | |   | |--'  |   | '/  ' \n" +
"  `--'---'  ;  |,'      \\  \\  ;         ;   |.' '--'.     / |  ,   /;  : "
                            + "  .'   \\|   |/      |   :    :| \n" +
"            '--'         :  \\  \\        '---'     `--'---'   ---`-' |  ,"
                            + "     .-./'---'        \\   \\  /   \n" +
"                          \\  ' ;                                     `--"
                            + "`---'                  `----'    \n" +
"                           `--`                                           "
                            + "                               ");
                    System.out.println("              ,.  _~-.,             "
                            + "  .\n" +
"           ~'`_ \\/,_. \\_\n" +
"          / ,\"_>@`,__`~.)             |           .\n" +
"          | |  @@@@'  \",! .           .          '\n" +
"          |/   ^^@     .!  \\          |         /\n" +
"          `' .^^^     ,'    '         |        .             .\n" +
"           .^^^   .          \\                /          .\n" +
"          .^^^       '  .     \\       |      /       . '\n" +
".,.,.     ^^^             ` .   .,+~'`^`'~+,.     , '\n" +
"&&&&&&,  ,^^^^.  . ._ ..__ _  .'             '. '_ __ ____ __ _ .. .  .\n" +
"%%%%%%%%%^^^^^^%%&&;_,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.,\n" +
"&&&&&%%%%%%%%%%%%%%%%%%&&;,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=\n" +
"%%%%%&&&&&&&&&&&%%%%&&&_,.;^`'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.,__,\n" +
"%%%%%%%%%&&&&&&&&&-=~'`^`'~=-.,__,.-=~'`^`'~=-.,__,.-==--^'~=-.,__,.-=~'\n" +
"##mjy#####*\"'\n" +
"_,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.,.-=~'`^`'~=-.,__,.-=~'\n" +
"\n" +
"~`'^`'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^");
                    dialogueContinue();
                    System.out.println(player.getName()+": Ive never seen "
                            + "anything as beatiful as this in my life.");
                    dialogueContinue();
                    System.out.println(player.getName()+": What if I retire? "
                            + "Live here and give up on the one piece? "
                            + "Sounds nice.");
                    dialogueContinue();
                    System.out.println("Distant Voice: No one is allowed"
                            + " to live here HAHAHA. ");
                    dialogueContinue();
                    System.out.println("Distant Voice: Your funny, "
                            + "human.");
                    dialogueContinue();
                    System.out.println(player.getName()+": SHOW YOURSELF, YOU "
                            + "COWARD!");
                    dialogueContinue();
                    System.out.println("Distant Voice: Gladly.");
                    dialogueContinue();
                    System.out.println("                                 "
                            + "   ___,,___\n" +
"                                ,d8888888888b,_\n" +
"                            _,d889'        8888b,\n" +
"                        _,d8888'          8888888b,\n" +
"                    _,d8889'           888888888888b,_\n" +
"                _,d8889'             888888889'688888, /b\n" +
"            _,d8889'               88888889'     `6888d 6,_\n" +
"         ,d88886'              _d888889'           ,8d  b888b,  d\\\n" +
"       ,d889'888,             d8889'               8d   9888888Y  )\n" +
"     ,d889'   `88,          ,d88'                 d8    `,88aa88 9\n" +
"    d889'      `88,        ,88'                   `8b     )88a88'\n" +
"   d88'         `88       ,88                   88 `8b,_ d888888\n" +
"  d89            88,      88                  d888b  `88`_  8888\n" +
"  88             88b      88                 d888888 8: (6`) 88')\n" +
"  88             8888b,   88                d888aaa8888, `   'Y'\n" +
"  88b          ,888888888888                 `d88aa `88888b ,d8\n" +
"  `88b       ,88886 `88888888                 d88a  d8a88` `8/\n" +
"   `q8b    ,88'`888  `888'\"`88          d8b  d8888,` 88/ 9)_6\n" +
"     88  ,88\"   `88  88p    `88        d88888888888bd8( Z~/\n" +
"     88b 8p      88 68'      `88      88888888' `688889`\n" +
"     `88 8        `8 8,       `88    888 `8888,   `qp'\n" +
"       8 8,        `q 8b       `88  88\"    `888b\n" +
"       q8 8b        \"888        `8888'\n" +
"        \"888                     `q88b\n" +
"                                  \"888'");
                    /*
                    Make checkpoint = 11 so when the do-while loops back it 
                    starts from the next checkpoint
                    */
                    checkPoint = 11;
                    
                    //Save data in file
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    dialogueContinue();
                    break;
                    
                }
                /*
                If checkPoint = 11 then it will print the journey after that
                checkpoint
                */
                case 11: 
                {
                    System.out.println(player.getName()+": Who are you?");
                    dialogueContinue();
                    System.out.println("Flying Bison: I am the protector "
                            + "of these lands, and I will not allow you to "
                            + "leave with anything.");
                    dialogueContinue();
                    System.out.println(player.getName()+": Well thats "
                            + "unfortunate, looks "
                            + "like Im gonna have to use force.");
                    dialogueContinue();
                    System.out.println("Flying Bison: We are a peaceful "
                            + "species, if you are a sane man, "
                            + "please leave us.");
                    dialogueContinue();
                    
                    System.out.println("BEWARE, YOUR ACTIONS WILL "
                                + "RESULT IN DIFFERENT FUTURES");
                    
                    //Ask user for input
                    System.out.println("Would you like to battle the bison "
                                + "or leave him at peace while entering the "
                                + "island another way?");
                    
                    //Create do-while loop for error trapping
                    do{
                        System.out.println("Enter (Battle) to fight "
                                + "and (Run) to evade the peaceful bisons:  ");
                        
                        //Create scnner for user input
                        choice = scanS.nextLine();
                        
                        //If user inputs a invalid option it will send a message
                        if(!(choice.equalsIgnoreCase("Battle") || 
                                choice.equalsIgnoreCase("Run")))
                        {
                            //print invalid message
                            System.out.println("Invalid Option, "
                                    + "please enter either Battle or Run.");
                        }
                    
                    //Create a while loop to loop back to scanner    
                    } while (!(choice.equalsIgnoreCase("Battle") || 
                                choice.equalsIgnoreCase("Run")));
                    
                    //If user inputs battle a battle will be started 
                    if(choice.equalsIgnoreCase("Battle"))
                    {
                        System.out.println("\nPRESS (ENTER) "
                                + "TO START BATTLE");
                        dialogueContinue();
                        
                        //Make enemyNumber 6 to fight 6th enemy
                        enemyNumber = 6;
                        
                        //Call on enemy battle method to start fight
                        EnemyBattle(enemy, player, enemyNumber, attack, potion, 
                                wild, gameSave, gameDifficulty);
                        System.out.println("Flying Bison: I hope the "
                                + "gods put curses upon yo-");
                        dialogueContinue();
                        
                        //Sub 10 from player reputation 
                        player.setReputation(player.getReputation() -10);
                    }
                    //Else you leave the bisons home
                    else
                    {
                        System.out.println(player.getName()+": Okay bison, "
                                + "I will leave you at peace.");
                        dialogueContinue();
                        System.out.println("*You circle around the island "
                                + "and dock the ship on a different shore*");
                        dialogueContinue();
                        
                        //Reputation + 10 for good deed
                        player.setReputation(player.getReputation() + 10);
                    }
                    
                    //Player level up message
                    System.out.println("PLAYER LEVEL UP! ATTACKS UNLOCKED, "
                        + "HEALTH BOOSTED");
                    
                    //Set player level to 5
                    player.setLevel(5);
                    
                    //Call on playerstatus method to level up
                    setPlayerStats(player, attack);
                    
                    //Make checkPoint = 13 to get to next checkPoint
                    checkPoint = 13;
                    
                    //Save game data to file
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    break;
                }
                /*
                If checkPoint = 12 then it will print the journey after that
                checkpoint
                */
                case 12: 
                {
                    System.out.println(player.getName()+": Now then, its time "
                            + "to find that missing compass piece! ");
                    dialogueContinue();
                    System.out.println("*You explore around the island "
                            + "and see a giant temple*");
                    dialogueContinue();
                    System.out.println("*You go towards the temple as "
                            + "the compass is leading there as well.*");
                    dialogueContinue();
                    System.out.println("               )\\         O_._._."
                            + "_A_._._._O         /(\n" +
"                \\`--.___,'=================`.___,--'/\n" +
"                 \\`--._.__                 __._,--'/\n" +
"                   \\  ,. l`~~~~~~~~~~~~~~~'l ,.  /\n" +
"       __            \\||(_)!_!_!_.-._!_!_!(_)||/            __\n" +
"       \\\\`-.__        ||_|____!!_|;|_!!____|_||        __,-'//\n" +
"        \\\\    `==---='-----------'='-----------`=---=='    //\n" +
"        | `--.                                         ,--' |\n" +
"         \\  ,.`~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~',.  /\n" +
"           \\||  ____,-------._,-------._,-------.____  ||/\n" +
"            ||\\|___!`=======\"!`=======\"!`=======\"!___|/||\n" +
"            || |---||--------||-| | |-!!--------||---| ||\n" +
"  __O_____O_ll_lO_____O_____O|| |'|'| ||O_____O_____Ol_ll_O_____O__\n" +
"  o H o o H o o H o o H o o |-----------| o o H o o H o o H o o H o\n" +
" ___H_____H_____H_____H____O =========== O____H_____H_____H_____H___\n" +
"                          /|=============|\\\n" +
"()______()______()______() '==== +-+ ====' ()______()______()______()\n" +
"||{_}{_}||{_}{_}||{_}{_}/| ===== |_| ===== |\\{_}{_}||{_}{_}||{_}{_}||\n" +
"||      ||      ||     / |==== s(   )s ====| \\     ||      ||      ||\n" +
"======================()  =================  ()======================\n" +
"----------------------/| ------------------- |\\----------------------\n" +
"                     / |---------------------| \\\n" +
"-'--'--'           ()  '---------------------'  ()\n" +
"                   /| ------------------------- |\\    --'--'--'\n" +
"       --'--'     / |---------------------------| \\    '--'\n" +
"                ()  |___________________________|  ()           '--'-\n" +
"  --'-          /| _______________________________  |\\\n" +
" --' gpyy      / |__________________________________| \\");
                    dialogueContinue();
                    System.out.println(player.getName()+": If only I could "
                            + "live here... ");
                    dialogueContinue();
                    System.out.println("*You go inside the temple in "
                            + "search of the missing compass piece*");
                    dialogueContinue();
                    System.out.println("Stranger: WHO DARES TO E"
                            + "NTER MY DOMAIN");
                    dialogueContinue();
                    System.out.println(player.getName()+": I dont mean any ha"
                            + "rm, Im here looking for something.");
                    dialogueContinue();
                    System.out.println("Stranger: You'll have to go "
                            + "through me first!");
                    dialogueContinue();
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    
                    //Make enemyNumber = 7 to fight 7th enemy
                    enemyNumber = 7;
                    
                    //Call on enemy battle method 
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, 
                        wild, gameSave, gameDifficulty);
                    
                    System.out.println(player.getName()+": There better not be"
                            + "any more tricks this time.");
                    
                    System.out.println("*You enter the temple storage "
                            + "room, and there the missing piece lies*");
                    
                    System.out.println("      _----------_,\n" +
"    ,\"__         _-:, \n" +
"   /    \"\"--_--\"\"...:\\\n" +
"  /         |.........\\\n" +
" /          |..........\\\n" +
"/,         _'_........./:\n" +
"! -,    _-\"   \"-_... ,;;:\n" +
"\\   -_-\"         \"-_/;;;;\n" +
" \\   \\             /;;;;'\n" +
"  \\   \\           /;;;;\n" +
"   '.  \\         /;;;'\n" +
"     \"-_\\_______/;;'");
                    
                    System.out.println("*You pick it up and make a "
                            + "run for it*");
                    
                    System.out.println(player.getName()+": Well this went "
                            + "better then last time.");
                    
                    System.out.println("*You reach your ship and "
                            + "leave for the next island.*"); 
                    
                    //Make checkpoint = 13 to get to next checkPoint 
                    checkPoint = 13;
                    
                    //Call on saveGame method to save game data to file
                    System.out.println("================================"
                            + "==============================================="
                            + "===");
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    break;
                }
            }
        /*
        Making a while loop which will keep repeating until checkPoint = 12 and 
        the first island is completed
        */
        }while(checkPoint <= 12);
    }
    
    /***************
    * Method name: islandFour
    * Description: All your journey at island four
     * @param gameSave
     * @param enemy
     * @param gameDifficulty
     * @param checkPoint
     * @param player
     * @param enemyNumber
     * @param attack
     * @param potion
     * @param wild
     * @throws java.lang.InterruptedException
    **************/
    public static void islandFour(File gameSave, Enemies[] enemy, int 
        gameDifficulty, int checkPoint, MainCharacter player, 
        int enemyNumber, Attacks[] attack, Potions[] potion, Joker wild) 
            throws InterruptedException
    {
        //Create a scanner object for user input
        Scanner scanS = new Scanner (System.in);
        
        //create string for user input 
        String choice;      
        
        //Create a do-while loop 
        do
        {
            //Create a switch which reads checkPoint value
            switch(checkPoint)
            {
                /*
                If checkPoint = 13 then it will print the journey after that
                checkpoint
                */
                case 13: 
                {
                    System.out.println("================================"
                            + "==============================================="
                            + "===");
                    //Print story line
                    System.out.println("*You approach the final island*");
                    dialogueContinue();
                    System.out.println(player.getName()+": This is it, the "
                            + "final island.");
                    dialogueContinue();
                    System.out.println(player.getName()+": After this, all I "
                            + "have dreamed about will be mine.");
                    dialogueContinue();
                    System.out.println(player.getName()+": I will be able to "
                            + "live in peace away from those who hate me.");
                    dialogueContinue();
                    System.out.println("*A robot emerges from the "
                            + "water*");
                    dialogueContinue();
                    System.out.println(player.getName()+": Speaking of people"
                            + " who hate me, I bet this one does too.");
                    dialogueContinue();
                    System.out.println("Robot: Do not proceed further, "
                            + "it is a restricted zone");
                    dialogueContinue();
                    System.out.println(player.getName()+": Yeah? and whos "
                            + "gonna stop me?");
                    dialogueContinue();
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    //Make enemy Number = 8 to fight 8th enemy
                    enemyNumber = 8;
                    
                    //Call on enemybattle method
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, 
                        wild, gameSave, gameDifficulty);
                    System.out.println("PLAYER LEVEL UP! ATTACKS UNLOCKED, "
                         + "HEALTH BOOSTED");
                    
                    //Set player level to 6 foe level up
                    player.setLevel(6);
                    
                    //Call on set player states to level up
                    setPlayerStats(player, attack);
                    dialogueContinue();
                    
                    System.out.println("*You sail on ahead and reach "
                            + "the final island*");
                    dialogueContinue();
                    
                    //Print island title and art
                    System.out.println(" ______  __  __   _______  ______  __"
                            + "____   ______  __  __  ___   __   ___   ___   "
                            + "   ______    ________  _________  __  __    \n" +
"/_____/\\/_/\\/_/\\/_______/\\/_____/\\/_____/\\ /_____/\\/_/\\/_/\\/__/\\ /"
                            + "__/\\/___/\\/__/\\    /_____/\\  /_______/\\/__"
                            + "______/\\/_/\\/_/\\   \n" +
"\\:::__\\/\\ \\ \\ \\ \\::: _  \\ \\::::_\\/\\:::_ \\ \\\\:::_ \\ \\:\\ \\:\\"
                            + " \\::\\_\\\\  \\ \\::.\\ \\\\ \\ \\   \\:::__\\/"
                            + "  \\__.::._\\/\\__.::.__\\/\\ \\ \\ \\ \\  \n" +
" \\:\\ \\  _\\:\\_\\ \\ \\::(_)  \\/\\:\\/___/\\:(_) ) )\\:(_) \\ \\:\\ \\:\\"
                            + " \\:. `-\\  \\ \\:: \\/_) \\ \\   \\:\\ \\  __ "
                            + "  \\::\\ \\    \\::\\ \\   \\:\\_\\ \\ \\ \n" +
"  \\:\\ \\/_/\\::::_\\/\\::  _  \\ \\::___\\/\\: __ `\\ \\: ___\\/\\:\\ \\:\\"
                            + " \\:. _    \\ \\:. __  ( (    \\:\\ \\"
                            + "/_/\\  _\\::\\ \\__  \\::\\ \\   \\::::_\\/ \n" +
"   \\:\\_\\ \\ \\\\::\\ \\ \\::(_)  \\ \\:\\____/\\ \\ `\\ \\ \\ \\ \\   \\"
                            + ":\\_\\:\\ \\. \\`-\\  \\ \\: \\ )  \\ "
                            + "\\    \\:\\_\\ \\ \\/__\\::\\__/\\  \\::\\ \\"
                            + "    \\::\\ \\ \n" +
"    \\_____\\/ \\__\\/  \\_______\\/\\_____\\/\\_\\/ \\_\\/\\_\\/    \\___"
                            + "__\\/\\__\\/ \\__\\/\\__\\/\\__\\/    "
                            + " \\_____\\/\\________\\/   \\__\\/     \\"
                            + "__\\/ \n" +
"                                                                    "
                            + "                                   "
                            + "                        ");
                    System.out.println("              . .                    "
                            + " -:-             .  .  .\n" +
"            .'.:,'.        .  .  .     ' .           . \\ | / .\n" +
"            .'.;.`.       ._. ! ._.       \\          .__\\:/__.\n" +
"             `,:.'         ._\\!/_.                     .';`.      . ' .\n" +
"             ,'             . ! .        ,.,      ..======..       .:.\n" +
"            ,                 .         ._!_.     ||::: : | .        ',\n" +
"     .====.,                  .           ;  .~.===: : : :|   ..===.\n" +
"     |.::'||      .=====.,    ..=======.~,   |\"|: :|::::::|   ||:::|=="
                            + "===|\n" +
"  ___| :::|!__.,  |:::::|!_,   |: :: ::|\"|l_l|\"|:: |:;;:::|___!| ::|: : "
                            + ":|\n" +
" |: :|::: |:: |!__|; :: |: |===::: :: :|\"||_||\"| : |: :: :|: : |:: |::::"
                            + ":|\n" +
" |:::| _::|: :|:::|:===:|::|:::|:===F=:|\"!/|\\!\"|::F|:====:|::_:|: :|::_"
                            + "_:|\n" +
" !_[]![_]_!_[]![]_!_[__]![]![_]![_][I_]!//_:_\\\\![]I![_][_]!_[_]![]_!_[__]"
                            + "!\n" +
" -----------------------------------\"---''''```---\"---------------------"
                            + "--\n" +
" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |= _ _:_ _ =| _ _ _ _ _ _ _ _ _ _ _ _"
                            + "\n" +
"                                     |=    :    =|                Valkyr"
                            + "ie\n" +
"_____________________________________L___________J______________________"
                            + "__\n" +
"------------------------------------------------------------------------"
                            + "--");
                    
                    //Make checkpoint = to 14 to print next checkpoint
                    checkPoint = 14;
                    
                    //Call on savegame method to save game data
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    dialogueContinue();
                    break;  
                }
                /*
                If checkPoint = 14 then it will print the journey after that
                checkpoint
                */
                case 14: 
                {
                    //Print story line
                    System.out.println(player.getName()+": Alright, this "
                            + "shouldnt be too hard.");
                    dialogueContinue();
                    System.out.println("*You dock your ship on the "
                            + "nearest shore and start following the compass*");
                    dialogueContinue();
                    System.out.println("*A robotic cop approaches you*");
                    dialogueContinue();
                    System.out.println("Cop: Please show me your ID.");
                    dialogueContinue();
                    System.out.println(player.getName()+": How about you see "
                            + "these FISTS.");
                    dialogueContinue();
                    System.out.println("\nPRESS (ENTER) TO START BATTLE");
                    dialogueContinue();
                    enemyNumber = 9;
                    EnemyBattle(enemy, player, enemyNumber, attack, potion, 
                        wild, gameSave, gameDifficulty);
                    System.out.println(player.getName()+": Im tired of all this"
                            + " fighting, I just wanna go home.");
                    dialogueContinue();
                    System.out.println("*The compass leads you to a "
                            + "underground tunnel*");
                    dialogueContinue();
                    System.out.println("*You swim through manure and find "
                            + "the missing compass piece deep inside the "
                            + "gutter*");
                    dialogueContinue();
                    System.out.println(player.getName()+": Im out.");
                    
                    //Make checkpoint = 15 to print next checkPoint
                    checkPoint = 15;
                    
                    //Call on savegame method
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    dialogueContinue();
                    break;  
                }
                /*
                If checkPoint = 15 then it will print the journey after that
                checkpoint
                */
                case 15: 
                {
                    System.out.println("BEWARE, YOUR ACTIONS WILL "
                                + "RESULT IN DIFFERENT FUTURES");
                    System.out.println("*On your way to your ship you see "
                            + "a couple of innocent people getting harrased "
                            + "by a robot, will you help them or go to your "
                            + "ship?*");
                    
                    //Make do-while loop to error trap
                    do{
                        System.out.println("Enter (Help) to fight "
                                + "and (Run) to evade the robot:  ");
                        choice = scanS.nextLine();
                        
                        //If user inputs invalid option loop back
                        if(!(choice.equalsIgnoreCase("Help") || 
                                choice.equalsIgnoreCase("Run")))
                        {
                            //Print invalid input message
                            System.out.println("Invalid Option, "
                                    + "please enter either Battle or Run.");
                        }
                    
                    //Make while statement to loop back if the user inputs wrong
                    } while (!(choice.equalsIgnoreCase("Help") || 
                                choice.equalsIgnoreCase("Run")));
                    
                    //Print story if user decides to help
                    if(choice.equalsIgnoreCase("Help"))
                    {
                        //Prompt player to input 
                        System.out.println("\nPRESS (ENTER) "
                                + "TO START BATTLE");
                        dialogueContinue();
                        
                        //Make enemy number to 10 to fight 10th enemy 
                        enemyNumber = 10;
                        
                        //Call on enemybattle method
                        EnemyBattle(enemy, player, enemyNumber, attack, potion, 
                                wild, gameSave, gameDifficulty);
                        System.out.println("Strangers: Thank you for "
                                + "saving us");
                        System.out.println("Strangers: As a token of our "
                                + "appreciation, please accept this gift.");
                        System.out.println("*Wild Card accuired, "
                                + "Big Pot accuired*");
                        
                        //Add 1 to potion and wild card quantity
                        potion[1].setQuantity(potion[1].getQuantity() + 1);
                        wild.setQuantity(wild.getQuantity() + 1);
                        dialogueContinue();
                        
                        //Add 10 to player reputation 
                        player.setReputation(player.getReputation() + 10);
                    }
                    else
                    {
                        System.out.println(player.getName()+": I just wanna "
                                + "go home");
                        dialogueContinue();
                        System.out.println("*You head straight to your "
                                + "ship*");
                        dialogueContinue();
                        
                        //Sub 10 from player reputation
                        player.setReputation(player.getReputation() - 10);
                    }
                    
                    System.out.println("*You have now completed the "
                            + "compass and its magnitational pull will "
                            + "lead you to the final island!*");
                    dialogueContinue();
                    
                    System.out.println(player.getName()+": Finally, lets end "
                            + "this.");
                    dialogueContinue();
                    
                    System.out.println("*You head for the final island*");
                    
                    //Make chekpoint = 16 to go through the finals
                    checkPoint = 16;
                    
                    //Call on saveGame method to same game data
                    saveGame(gameSave, checkPoint, player, gameDifficulty);
                    System.out.println("================================"
                            + "==============================================="
                            + "===");
                    break;  
                }
            }
        /*
        Making a while loop which will keep repeating until checkPoint = 15 and 
        the first island is completed
        */
        }while(checkPoint <= 15);
    }
    
    /***************
    * Method name: finalEnding
    * Description: Final ending to your journey
     * @param gameSave
     * @param enemy
     * @param gameDifficulty
     * @param checkPoint
     * @param player
     * @param enemyNumber
     * @param attack
     * @param potion
     * @param wild
     * @throws java.lang.InterruptedException
    **************/
    public static void finalEnding (File gameSave, Enemies[] enemy, int 
            gameDifficulty, int checkPoint, MainCharacter player, 
            int enemyNumber, Attacks[] attack, Potions[] potion, Joker wild) 
            throws InterruptedException
    {
        System.out.println("================================"
                            + "==============================================="
                            + "===");
        //Print story line for final events 
        System.out.println("*You finally make it to the final "
                + "destination, the one piece*");
        dialogueContinue();
        System.out.println("*But there lies one last obstacle "
                + "ahead.. the pirate king himself*");
        dialogueContinue();
        System.out.println("Pirate King: So you have come at last!");
        dialogueContinue();
        System.out.println(player.getName()+": I have been waiting for this "
                + "day my whole life.");
        dialogueContinue();
        System.out.println("Pirate King: Well then lad, lets "
                + "clash swords");
        dialogueContinue();
        System.out.println("\nPRESS (ENTER) TO START BATTLE");
        dialogueContinue();
        
        //Make enemyNumber = 11 to fight final final boss
        enemyNumber = 11;
        
        //Call on enemy battle method to initialize fight 
        EnemyBattle(enemy, player, enemyNumber, attack, potion, 
            wild, gameSave, gameDifficulty);
        
        System.out.println("Pirate King: You have bested me, "
                + "the prize is yours to take");
        dialogueContinue();
        System.out.println("*You walk towards the middle of the "
                + "island and...*");
        dialogueContinue();
        
        //If player reputation is above 50 player will get good ending
        if(player.getReputation() > 50)
        {
            //print good ending 
            System.out.println("*The middle was filled with gold and "
                    + "good fortune, and also the citizenship in the sky "
                    + "world*");
            dialogueContinue();
            System.out.println("*In the end you got all you wanted, "
                    + "and lived peacefully in the sky*");
            dialogueContinue();
        }
        //If player reputation is below 50 player will get bad ending
        else if(player.getReputation() < 50) 
        {
            //print bad ending
            System.out.println("*The island was empty, there was "
                    + "nothing there, it was all a lie*");
            dialogueContinue();
            System.out.println("*A couple of days later you were "
                    + "captured by the giants and sentenced to death*");
            dialogueContinue();
        }
        System.out.println("================================"
                            + "==============================================="
                            + "===");
    }
    
    /***************
    * Method name: introduction
    * Description: intro to your adventure
     * @param gameSave
     * @param enemy
     * @param gameDifficulty
     * @param checkPoint
     * @param player
     * @param enemyNumber
     * @param attack
     * @param potion
     * @param wild
     * @throws java.lang.InterruptedException
    **************/
    public static void introduction (File gameSave, Enemies[] enemy, int 
            gameDifficulty, int checkPoint, MainCharacter player, 
            int enemyNumber, Attacks[] attack, Potions[] potion, Joker wild) 
            throws InterruptedException
    {
        //Create scanner object for numbers and strings
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);
        
        //Create a string for user input
        String nameAsk;
        
        //Create boolean for error trapping
        boolean check;
        
        //Ask user for difficulty level
        System.out.println("\n");
        System.out.println("==========================================="
                + "=====");
        System.out.println("What difficulty would you like to "
                + "play with? (1-3): ");
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Hard");
        
        //Create do-while loop 
        do
        {
            //Create do-while loop
            do
            {
                //Creating try catch to error trap user input
                try
                {
                    //Create scanner for user input 
                    gameDifficulty = scanN.nextInt();
                    
                    //If boolean = true loop will break
                    check = true;
                }
                     
                catch (Exception e)
                {
                    //Telling user to print correct value
                    System.out.println("Please enter an integer.");

                    //Resetting scanner
                    scanN.nextLine();
                    
                    //loop back
                    check = false;
                }
            
            //Create a while statement which will loop back if check == false
            } while (check == false);
            
            //if user inputs invalid int print error message 
            if(gameDifficulty > 3 || gameDifficulty < 1)
            {
                //Tell player to input correct value
                System.out.println("Invalid Option, please enter "
                    + "a number between (1-3): ");
                
                //Make check = false to make loop loop back
                check = false;
            }
        
        //Loop back if user inputs invalid number 
        } while ((gameDifficulty > 3 || gameDifficulty < 1));
        System.out.println("==========================================="
                + "=====");
        System.out.println("\n");
        System.out.println("==========================================="
                + "=========================");
        System.out.println("*Wakes up from a coma*"); 
        dialogueContinue();
        System.out.println("Doctor: Good morning, can you hear me? (pause)"
                + " Can you open your eyes for me?");
        dialogueContinue();           
        System.out.println("You (groggily): Wh-what happened?");
        dialogueContinue();            
        System.out.println("Doctor: You've been in a coma for several "
                + "weeks. We've been monitoring your condition closely\nand "
                + "we're pleased to see that you're making progress.");
        dialogueContinue();
        System.out.println("You: I...I don't remember anything.");
        dialogueContinue();
        System.out.println("Doctor: That's not uncommon, try remembering "
                + "your name.");
        dialogueContinue();
        
        //Create do-while loop 
        do
        {
            //Ask for players name
            System.out.println("You: My name... my name is (Please type in "
                + "your name: )");
            nameAsk = scanS.nextLine();            
            if(nameAsk.equals("")){
                System.out.println("Invalid option, please enter a name");
            }
        //Make while for error trapping to loop 
        }while((nameAsk.equals("")));
        
        //Make player name = user input
        player.setName(nameAsk);
        System.out.println("\n");
        System.out.println(player.getName() + ": My name is " + player.getName
        ());
        dialogueContinue();
        System.out.println("Doctor: Thats a great start! I would recommend "
                + "you rest for now and take it easy.");
        dialogueContinue();
        System.out.println(player.getName() + ": For sure!");
        dialogueContinue();
        System.out.println("Doctor: Alright its my time to leave, if you"
                + " need help you can always ring that big bell.");
        dialogueContinue();
        System.out.println(player.getName()+ ": Great, thanks for everything.");
        dialogueContinue();
        System.out.println("*You pick up the daily newspaper on the side "
                + "table*");
        System.out.println("        ______________\n" +
                  "       |  DAILY NEWS  |\n" +
                  "       |    ======    |\n" +
                  "       |Pirate Gone   |\n" +
                  "       |Missing       |\n" +
                  "       |[_] ======    |\n" +
                  "       |=== ===!##    |\n" +
                  "       |______________|");
        dialogueContinue();
        System.out.println(player.getName() + ": Pirate gone missing huh... "
                + "wait... Im a pirate.");
        dialogueContinue();
        System.out.println("*Your memories start coming back*");
        dialogueContinue();
        System.out.println(player.getName() + ": They are taking about me! I "
                + "gotta get to my ship.");
        dialogueContinue();
        System.out.println("*You get out of your bed and escape through "
                + "your window*");
        dialogueContinue();
        System.out.println("*You start running for the shore but encounter "
                + "a evolved rat, you have no chance but to fight*");
        System.out.println("\nPRESS (ENTER) TO START BATTLE");
        dialogueContinue();
        enemyNumber = 0;
        EnemyBattle(enemy, player, enemyNumber, attack, potion, wild, gameSave, 
                gameDifficulty);
        System.out.println("PLAYER LEVEL UP! ATTACKS UNLOCKED, "
                + "HEALTH BOOSTED");
        
        //Make player level up 
        player.setLevel(2);
        
        //Call on set player stats method to make player level up
        setPlayerStats(player, attack);
        dialogueContinue();
        
        System.out.println(player.getName()+": WOO I havent fought in a while, "
                + "my body must have gotten weaker while I was in a coma.");
        dialogueContinue();
        System.out.println(player.getName()+": Anyways I should get to my ship"
                + ".");
        dialogueContinue();
        System.out.println("*You run to your ship go into your room*");
        dialogueContinue();
        System.out.println("*You pick up a map on your table*");
        System.out.println("                                              "
                + "      "
                + "        \n" +
"                   (%%%%%%%%%%%%%%%%%%%%                    \n" +
"              #%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%               \n" +
"           %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            \n" +
"        #%%%%%%%%%%*****&%%%%%%%%%%%%%%/%%%%%%/%%%%         \n" +
"      %%%%%%%,**/*,**%%%%%%%%%%%%%%%%%/&%(&&&/%#%%%%%       \n" +
"     %%%%%%%**//***/%%%%%%%%%%%%%%%%%%%&&%%/&&%%&&#%%%/     \n" +
"   ,%%%%%%%%%%/**/(%%%%%%%%%%%%%%%%%%%%%%%&&&&&&&&%%%%%%    \n" +
"  *%%%%%%%%%%%(%%%%%%%%%%%%%%%%%%%%%%%%%%%&&&/%@&%%%%%%%%   \n" +
"  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/%%&%%%%%%%%*  \n" +
" %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  \n" +
" %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  \n" +
" %%%%%%%%%%%(###/%%%%%%%%%%%%(%%%%%%%%%%%%%%%%%%%%%%%%%%%%  \n" +
" %%%%%%%%%%(%###%%%%%%%%%%%%%%%%%%%%%%%%%%%/(%///%%%#%%%%%  \n" +
"  %%%%%%%/#%%###%%%%%%%%%%%%%%%%%%%%%%%%%%##&&%%#%%%(%%%%%  \n" +
"  %%%%%%%%%%%#&#%%%/%%%%%%%%%%%%%%%%%&%%/#%%%%#%&&%%#%%%%   \n" +
"   %%%%%%%%%%#((%%%%%%%%%%%%%%%%%%%%(#%%&%#%%%%%#%%%%%%%    \n" +
"    %%%%%%%%%%%#%%#%%%%%%%%%%%%%%%%%/%%#&@%%%%#(/%%%%%%     \n" +
"      %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/%%%/#%%%%%%%%%%(      \n" +
"       .%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%        \n" +
"          %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%           \n" +
"             %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%              \n" +
"                 %%%%%%%%%%%%%%%%%%%%%%%%%                  \n" +
"                         /%%%%%%%#.                       ");
        dialogueContinue();
        System.out.println(player.getName()+": THATS RIGHT! I need to go to "
                + "each one of these islands to complete my compass.");
        dialogueContinue();
        System.out.println(player.getName()+": Then that final compass will "
                + "lead me to the ONE PIECE.");
        dialogueContinue();
        System.out.println(player.getName()+": MY MEMORIES ARE FINALLY COMING "
                + "BACK I WILL FIND THE ONE PIECE.");
        dialogueContinue();
        System.out.println("*You immediatly sail away and head to the "
                + "direction of the first island*");
        dialogueContinue();
        System.out.println("*Redirecting you to the main menu*");
        System.out.println("\n");
        
        //Make checkPoint = 2 to continue the story
        checkPoint = 2;
        
        //Save game data to file 
        saveGame(gameSave, checkPoint, player, gameDifficulty);
    }
    
    /***************
    * Method name: alphaSort
    * Description: make alpha sort method 
    * @param words
    * @param player
    **************/
    public static void alphaSort(String[]words)
    {
        //create counter 
        int i;
        
        //create temp string
        String temp;
        
        //create boolean 
        boolean flag = true;
        
        //Create while loop to keep looping until its jobs done
        while(flag) 
        { 
            //Boolean = false to break loop
            flag = false;
            
            //Alphabetically sort words
            for (i = 0; i < words.length-1; i++) 
            {
                if(words[i].compareToIgnoreCase(words[i+1]) > 0) 
                {
                    temp = words[i];
                    words[i] = words[i+1];
                    words[i+1] = temp;
                    
                    //Make flag = true to break loop
                    flag = true;
                }
            }
        }
    }//end of method
    
    /***************
    * Method name: puzzle
    * Description: Making puzzle mini game
    * @param player
    **************/
    public static void puzzle(MainCharacter player)
            
    {
        System.out.println("================================"
                            + "==============================================="
                            + "===");
        //Create scanner object for user input 
        Scanner scanS = new Scanner(System.in);
        
        //Create array for user input 
        String [] nameEnter = new String[3];
        
        //Initialize file with names 
        File puzzelSave = new File("PuzzelNames.txt");
        
        //Create string which saves file data
        String nameSave = null; 
        
        //Create randomizer
        Random random = new Random();
        
        //Create namechecking array 
        String [] nameCheck = new String[3];
        
        //Use try catch for error printint
        try 
        {
            //Read from file
            Scanner readFile = new Scanner(puzzelSave);
            //Make namesave hold files contents
            nameSave = readFile.nextLine();
            //closing file reader scanner
            readFile.close();
        }
        catch(IOException e)
        {
            //Print error message
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("*Right before you reach the final destination"
                + " the water starts rising*");
        dialogueContinue();
        System.out.println("*You must complete a puzzle before finshing "
                + "your journey*");
        dialogueContinue();
        System.out.println("RULES: THREE WORDS WILL BE PROVIDED TO YOU, "
                + "WRITE THEM DOWN ONE BY ONE IN ALPHABETICAL ORDER.");
        System.out.println("IF YOU LOSE. YOUR HEALTH WILL BE DEDUCTED "
                + "FOR THE FINAL BATTLE.");
        dialogueContinue();
        System.out.println("PRESS (ENTER) TO START");
        dialogueContinue();
        
        //Make split method to organize names
        String[] names = nameSave.split("-");
             
        //Create for loop to print random names from file
        for (int i = 0; i < nameCheck.length; i++) 
        {
            int randomNames = random.nextInt(8)+1;
            System.out.println(names[randomNames]);
            nameCheck[i] = names[randomNames];
        }
        
        //Use alphasort method to organize names alphabetically  
        alphaSort(nameCheck);

        //Make user input 3 names in order
        System.out.println("Enter these names in alphabetical order:");

        //Make user input 3 names
        for (int i = 0; i < nameEnter.length; i++) 
        {
            nameEnter[i] = scanS.nextLine();
        }

        //If the names do not match it will print failure message
        if(!(nameCheck[0].equals(nameEnter[0]) || nameCheck[1].equals(nameEnter
                [1]) || nameCheck[2].equals(nameEnter[2])))
        {
            //print failure message
            System.out.println("You failed, your health will now"
                    + " be deducted and it will be harder to fight the "
                    + "final boss.");
            
            //Sub 10 from player health
            player.setHealth(player.getHealth() - 10);
            dialogueContinue();
        }
        //Print success message
        else
        {
            System.out.println("*The water goes down and the path "
                + "is cleared out*");
            dialogueContinue();
        }
        System.out.println("================================"
                            + "==============================================="
                            + "===");
        
    }//end of method
    
    /***************
    * Method name: loadGameData
    * Description: loading game data
     * @param gameSave
     * @param enemy
    * @param player
     * @param gameDifficulty
     * @param attack
    **************/
    public static void loadGameData (File gameSave, Enemies[] enemy, 
            MainCharacter player, int gameDifficulty, 
            Attacks[] attack)
    {  
        //Make randomizer for random damage value
        Random random = new Random();
        
        //Create int for values in randomizer
        int randomDamage = 5;
        int randomDamageIncrease = 0;
        
        //Create switch statement which reads game difficulty 
        switch(gameDifficulty)
        {
            
            //If game difficulty is 1, enemy damage will be less
            case 1:
            {
                //Create for loop to give damage values in the array
                for (int i = 0; i < damage.length; i++) 
                {
                    //Make damage = random number
                    damage[i] = random.nextInt(randomDamage) + 
                            randomDamageIncrease;
                    randomDamage = randomDamage + 4;
                    randomDamageIncrease = randomDamageIncrease + 4;
                    
                }
                //Give enemy objects fields values 
                enemy[0] = new Enemies("RAT", 30, damage[0]);
                enemy[1] = new Enemies("SNAKE", 40, damage[1]);
                enemy[2] = new Enemies("SCORPIAN",30, damage[2]);
                enemy[3] = new Bosses("KING OF THE SAHARA",50, damage[3]);
                enemy[4] = new Enemies("GIANT GATO", 50, damage[4]);
                enemy[5] = new Bosses("TOM  THE GIANT",90, damage[5]);
                enemy[6] = new Enemies("FLYING BISON", 80, damage[6]);
                enemy[7] = new Bosses("ANGEL OF DEATH",120, damage[7]);
                enemy[8] = new Enemies("ROBOMAN", 90, damage[8]);
                enemy[9] = new Enemies("AUTOPILOT",150, damage[9]); 
                enemy[10] = new Bosses("DEATH STRANDED",150, damage[10]);
                enemy[11] = new Bosses("KING OF THE PIRATES",
                        150, damage[5]);
                break;
            }
            case 2:
            {
                //Create for loop to give damage values in the array
                for (int i = 0; i < damage.length; i++) 
                {
                    //Make damage = random number
                    damage[i] = random.nextInt(randomDamage) + 
                            randomDamageIncrease;
                    randomDamage = randomDamage + 5;
                    randomDamageIncrease = randomDamageIncrease + 5;
                    
                }
                //Give enemy objects fields values 
                enemy[0] = new Enemies("RAT", 30, damage[0]);
                enemy[1] = new Enemies("SNAKE", 40, damage[1]);
                enemy[2] = new Enemies("SCORPIAN",30, damage[2]);
                enemy[3] = new Bosses("KING OF THE SAHARA",50, damage[3]);
                enemy[4] = new Enemies("GIANT GATO", 50, damage[4]);
                enemy[5] = new Bosses("TOM  THE GIANT",90, damage[5]);
                enemy[6] = new Enemies("FLYING BISON", 80, damage[6]);
                enemy[7] = new Bosses("ANGEL OF DEATH",120, damage[7]);
                enemy[8] = new Enemies("ROBOMAN", 90, damage[8]);
                enemy[9] = new Enemies("AUTOPILOT",150, damage[9]); 
                enemy[10] = new Bosses("DEATH STRANDED",150, damage[10]);
                enemy[11] = new Bosses("KING OF THE PIRATES",
                        150, damage[5]);
                break;
              
            }
            case 3:
            {
                //Create for loop to give damage values in the array
                for (int i = 0; i < damage.length; i++) 
                {
                    //Make damage = random number
                    damage[i] = random.nextInt(randomDamage) + 
                            randomDamageIncrease;
                    randomDamage = randomDamage + 8;
                    randomDamageIncrease = randomDamageIncrease + 8;
                    
                }
                //Give enemy objects fields values 
                enemy[0] = new Enemies("RAT", 30, damage[0]);
                enemy[1] = new Enemies("SNAKE", 40, damage[1]);
                enemy[2] = new Enemies("SCORPIAN",30, damage[2]);
                enemy[3] = new Bosses("KING OF THE SAHARA",50, damage[3]);
                enemy[4] = new Enemies("GIANT GATO", 50, damage[4]);
                enemy[5] = new Bosses("TOM  THE GIANT",90, damage[5]);
                enemy[6] = new Enemies("FLYING BISON", 80, damage[6]);
                enemy[7] = new Bosses("ANGEL OF DEATH",120, damage[7]);
                enemy[8] = new Enemies("ROBOMAN", 90, damage[8]);
                enemy[9] = new Enemies("AUTOPILOT",150, damage[9]); 
                enemy[10] = new Bosses("DEATH STRANDED",150, damage[10]);
                enemy[11] = new Bosses("KING OF THE PIRATES",
                        150, damage[5]);
                break;
              
            }
        }
        
        //call on set player stats for loading it in the game
        setPlayerStats(player, attack);
        
    }
    /***************
    * Method name: setPlaterStats
    * Description: setting player stats and resetting them
    * @param player
     * @param attack
    **************/
    public static void setPlayerStats(MainCharacter player, Attacks[] attack)
    {
        //Create switch statement which reads player level
        switch(player.getLevel())
        {
            //If player level is now 2 health increases and attack unlocks
            case 2:
            {
                //increase player health
                player.setHealth(45);
                
                //unlock attack
                attack[1].setName("Gum Punch");
                break;
            }
            
            //If player level is now 3 health increases and attack unlocks
            case 3:
            {
                //increase player health
                player.setHealth(60);
                //unlock attack
                attack[1].setName("Gum Punch");
                
                //unlock attack
                attack[2].setName("Gum Shotgun");
                break;
            }
            
            //If player level is now 4 health increases and attack unlocks
            case 4:
            {
                //increase player health
                player.setHealth(75);
                //unlock attack
                attack[1].setName("Gum Punch");
                
                //unlock attack
                attack[2].setName("Gum Shotgun");
                
                //unlock attack
                attack[3].setName("Gum Gatling Gun");
                break;
            }
            
            //If player level is now 5 health increases and attack unlocks
            case 5:
            {
                //increase player health
                player.setHealth(90);
                //unlock attack
                attack[1].setName("Gum Punch");
                
                //unlock attack
                attack[2].setName("Gum Shotgun");
                
                //unlock attack
                attack[3].setName("Gum Gatling Gun");
                
                //unlock attack
                attack[4].setName("Gum Grenade");
                break;
            }
            
            //If player level is now 6 health increases and attack unlocks
            case 6:
            {
                //increase player health
                player.setHealth(145);
                        
                //unlock attack
                attack[1].setName("Gum Punch");
                
                //unlock attack
                attack[2].setName("Gum Shotgun");
                
                //unlock attack
                attack[3].setName("Gum Gatling Gun");
                
                //unlock attack
                attack[4].setName("Gum Grenade");
                
                //unlock attack
                attack[5].setName("Gum Bazooka");
                break;
            }
            //Create default
            default:
            {
                break;
            }
        }
    }
    
    /***************
    * Method name: saveGame
    * Description: saving game by saving data to file
     * @param gameSave
     * @param checkPoint
    * @param player
     * @param gameDifficulty
    **************/
    public static void saveGame(File gameSave, int checkPoint, MainCharacter 
            player, int gameDifficulty)
    {
        
        //Create filewriter 
        FileWriter fw = null;
        
        //error handle file writter 
        try 
        {
        
        fw = new FileWriter (gameSave);

        } catch (IOException e) 
        {

        System.out.println(e.getMessage());

        }

        //Create printwriter 
        PrintWriter pw = new PrintWriter (fw);
        
        //Save game data to file by printing values in the file         
        pw.println(checkPoint);
        pw.println(player.getName());
        pw.println(player.getLevel());
        pw.println(gameDifficulty);
        pw.println(player.getReputation());
        
        //close printwriter
        pw.close();   
    }

    /***************
    * Method name: dialogueContinue
    * Description: makes a scanner for user to be able to continue by 
    * pressing enter
    **************/
    public static void dialogueContinue()
    {
        //Make scanner object
        Scanner scanS = new Scanner(System.in);
        
        //scan for user input
        scanS.nextLine();
    }
    
    /***************
    * Method name: loadGameData
    * Description: loading game data
     * @param gameSave
     * @param enemy
     * @param enemyNumber
    * @param player
     * @param potion
     * @param gameDifficulty
     * @param wild
     * @param attack
     * @throws java.lang.InterruptedException
    **************/
    public static void EnemyBattle(Enemies[] enemy, MainCharacter player, 
            int enemyNumber, Attacks[] attack, Potions[] potion, Joker wild, 
            File gameSave, int gameDifficulty) 
            throws InterruptedException
    {
        //Create scanner for numbers
        Scanner scanN = new Scanner(System.in);
        
        //Create int for player move 
        int playerMove = 0;
        
        //Create boolean for looping 
        boolean check = false;
        
        //Create randomizer and random int for dodging chance
        int randomChance;
        Random random = new Random();
        
        //Create do-while loop
        do{
            //Create a userfriendly artistic message
            System.out.println("                                               "
                    + "    "
                    + "                                                       "
                    + "  \n"+
    "  ______   ______   ______   ______   ______   ______   ______   ______   "
                    + "___"
                    + "___   ______   ______   ______ \n /_____/  /_____/  /__"
                    + "___"
                    + "/  /_____/  /_____/  /_____/  /_____/  /_____/  /_____/"
                    + "  /"
                    + "_____/  /_____/  /_____/ \n /_____/  /_____/  /_____/  "
                    + "/_"
                    + "____/  /_____/  /_____/  /_____/  /_____/  /_____/  /__"
                    + "_"
                    + "__/  /_____/  /_____/ \n                              "
                    + "     "
                    + "                                                       "
                    + "   "
                    + "               \n" +
    "                                                                         "
                    + "   "
                    + "                                ");
            System.out.println(enemy[enemyNumber]);
            System.out.println(player);
            System.out.println("                                               "
                    + "    "
                    + "                                                       "
                    + "  \n"+
    "  ______   ______   ______   ______   ______   ______   ______   ______   "
                    + "___"
                    + "___   ______   ______   ______ \n /_____/  /_____/  /__"
                    + "___"
                    + "/  /_____/  /_____/  /_____/  /_____/  /_____/  /_____/"
                    + "  /"
                    + "_____/  /_____/  /_____/ \n /_____/  /_____/  /_____/  "
                    + "/_"
                    + "____/  /_____/  /_____/  /_____/  /_____/  /_____/  /__"
                    + "_"
                    + "__/  /_____/  /_____/ \n                              "
                    + "     "
                    + "                                                       "
                    + "   "
                    + "               \n" +
    "                                                                         "
                    + "   "
                    + "                                ");
            //Tell player to either chose an attack or item
            System.out.println("Choose one of the following options: ");
            System.out.println("|=======================================|"
                    + "");
            System.out.print("1. " + attack[0].getName());
            System.out.println("\t6. " + attack[5].getName());
            System.out.print("2. " + attack[1].getName());
            System.out.println("\t7. " + potion[0].getName() + " (" + 
                    potion[0].getQuantity()+ ")");
            System.out.print("3. " + attack[2].getName());
            System.out.println("\t8. " + potion[1].getName() + " (" + 
                    potion[1].getQuantity()+ ")");
            System.out.print("4. " + attack[3].getName());
            System.out.println("\t9. " + wild.getName() + " (" + 
                    wild.getQuantity()+ ")");
            System.out.print("5. " + attack[4].getName());
            System.out.println("\t10. Sacrifice Stranger");
            System.out.println("|====================================="
                    + "==|");
            
            //make a do-while loop for error trapping 
            do
            {
                //make a do-while loop for error trapping 
                do
                {
                    //make a do-while loop for error trapping 
                    do
                    {
                        //Creating try catch to error trap user input
                        try
                        {
                            //Askking user to chose round option
                            playerMove = scanN.nextInt();
                            /*
                            If the correct input is given check = true breaking 
                            the loop
                            */
                            check = true;
                        }
                     
                        catch (Exception e)
                        {
                            //Telling user to print correct value
                            System.out.println("Please enter an integer."
                            );

                            //Resetting scanner
                            scanN.nextLine();
                            check = false;
                        }
                    //if user input it incorrect it will loop back   
                    } while (check == false);
                    
                    //if user input it incorrect it will loop back   
                    if(playerMove > 10 || playerMove < 1)
                        {
                            System.out.println("Invalid Option, please enter "
                                    + "a number between (1-10): ");
                            check = false;
                        }
                //if user input it incorrect it will loop back      
                } while ((playerMove > 10 || playerMove < 1));
                
                //create random number out of 100
                randomChance = random.nextInt(100);
                System.out.println("===================================="
                        + "================");
                
                //Create switch statement which reads from playerMove
                switch(playerMove)
                {
                    //Case one will play if player chooses attack 1
                    case 1:
                    {
                        System.out.println("You have chosen: " + 
                                attack[0].getName());
                        /*
                            If random number out of 100 is less then 20 
                            then the enemy dodges the attack (20%) chance
                        */
                        if(randomChance < 20)
                        {
                            System.out.println("DARN! Your opponent dodged "
                                    + "the attack." );
                        }
                        //Attack lands and enemy looses health
                        else{
                            enemy[enemyNumber].setHealth(enemy[enemyNumber].
                                    getHealth() - attack[0].getDamage());
                            System.out.println("Your attack was succesfu"
                                    + "l! You did " + attack[0].getDamage()
                                    + " damager.");
                        }
                        break;
                    }
                    //Case one will play if player chooses attack 1
                    case 2:
                    {
                        if(attack[1].getName().equals("Locked"))
                        {
                            System.out.println("This attack is currently not "
                                    + "available\nChoose another option:");
                            check = false;
                            break;
                        }
                        System.out.println("You have chosen: " + 
                                attack[1].getName
                        ());
                        if(randomChance < 20)
                        {
                            System.out.println("DARN! Your opponent dodged "
                                    + "the attack." );
                        }
                        else{
                            enemy[enemyNumber].setHealth(enemy[enemyNumber].
                                    getHealth() - attack[1].getDamage());
                            System.out.println("Your attack was succesfu"
                                    + "l! You did " + attack[1].getDamage()
                                    + " damager.");
                        }
                        break;
                    }
                    //Case one will play if player chooses attack 3
                    case 3:
                    {
                        //if attack player chose is not available it loops back
                        if(attack[2].getName().equals("Locked"))
                        {
                            System.out.println("This attack is currently not "
                                    + "available\nChoose another option:");
                            check = false;
                            break;
                        }
                        System.out.println("You have chosen: " + 
                                attack[2].getName
                        ());
                        /*
                            If random number out of 100 is less then 20 
                            then the enemy dodges the attack (20%) chance
                        */
                        if(randomChance < 20)
                        {
                            System.out.println("DARN! Your opponent dodged "
                                    + "the attack." );
                        }
                        //Your attack landed on the enemy and health decreased
                        else{
                            enemy[enemyNumber].setHealth(enemy[enemyNumber].
                                    getHealth() - attack[2].getDamage());
                            System.out.println("Your attack was succesfu"
                                    + "l! You did " + attack[2].getDamage()
                                    + " damager.");
                        }
                        break;
                    }
                    //Case one will play if player chooses attack 4
                    case 4:
                    {
                        //if attack player chose is not available it loops back
                        if(attack[3].getName().equals("Locked"))
                        {
                            System.out.println("This attack is currently not "
                                    + "available\nChoose another option:");
                            check = false;
                            break;
                        }
                        System.out.println("You have chosen: " + 
                                attack[3].getName
                        ());
                        /*
                            If random number out of 100 is less then 20 
                            then the enemy dodges the attack (20%) chance
                        */
                        if(randomChance < 20)
                        {
                            System.out.println("DARN! Your opponent dodged "
                                    + "the attack." );
                        }
                        //Your attack landed on the enemy and health decreased
                        else{
                            enemy[enemyNumber].setHealth(enemy[enemyNumber].
                                    getHealth() - attack[3].getDamage());
                            System.out.println("Your attack was succesfu"
                                    + "l! You did " + attack[3].getDamage()
                                    + " damager.");
                        }
                        break;
                    }
                    //Case one will play if player chooses attack 5
                    case 5:
                    {
                        //if attack player chose is not available it loops back
                        if(attack[4].getName().equals("Locked"))
                        {
                            System.out.println("This attack is currently not "
                                    + "available\nChoose another option:");
                           check = false;
                            break;
                        }
                        
                        System.out.println("You have chosen: " + 
                                attack[4].getName
                        ());
                        /*
                            If random number out of 100 is less then 20 
                            then the enemy dodges the attack (20%) chance
                        */
                        if(randomChance < 20)
                        {
                            System.out.println("DARN! Your opponent dodged "
                                    + "the attack." );
                        }
                        else{
                            enemy[enemyNumber].setHealth(enemy[enemyNumber].
                                    getHealth() - attack[4].getDamage());
                            System.out.println("Your attack was succesfu"
                                    + "l! You did " + attack[4].getDamage()
                                    + " damager.");
                        }
                        break;
                    }
                    //Case one will play if player chooses attack 6
                    case 6:
                    {
                        if(attack[5].getName().equals("Locked"))
                        {
                            System.out.println("This attack is currently not "
                                    + "available\nChoose another option:");
                            check = false;
                            break;
                        }
                        System.out.println("You have chosen: " + 
                                    attack[5].
                                    getName());
                        /*
                            If random number out of 100 is less then 20 
                            then the enemy dodges the attack (20%) chance
                        */
                        if(randomChance < 20)
                        {
                            System.out.println("DARN! Your opponent dodged "
                                    + "the attack." );
                        }
                        else{
                            enemy[enemyNumber].setHealth(enemy[enemyNumber].
                                    getHealth() - attack[5].getDamage());
                            System.out.println("Your attack was succesfu"
                                    + "l! You did " + attack[5].getDamage()
                                    + " damager.");
                        }
                        break;
                    }

                    case 7:
                    {
                        //if quantity is 0 it will loop back
                        if (potion[0].getQuantity() == 0){
                            System.out.println("You do not have any of these "
                                    + "potions. Please choose another option:");
                            check = false;
                            break;
                        }
                        //Otherwise you will heal
                        player.setHealth(player.getHealth() + potion[0].
                                getHealthBoost());
                        System.out.print("Using potion");

                        for (int i = 0; i < 3; i++) {
                            System.out.println(".");
                            Thread.sleep(500);
                        }

                        System.out.println("Health succesfully "
                                + "increased!");
                        break;
                    }

                    case 8:
                    {
                        //If the quantity is zero you will loop back
                        if (potion[1].getQuantity() == 0){
                            System.out.println("You do not have any of these "
                                    + "potions. Please choose another option:");
                            check = false;
                            break;
                        }
                        //You will succesfully heal
                        player.setHealth(player.getHealth() + potion[1].
                                getHealthBoost());
                        System.out.print("Using potion");

                        for (int i = 0; i < 3; i++) {
                            System.out.println(".");
                            Thread.sleep(500);
                        }

                        System.out.println("Health succesfully "
                                + "increased!");
                        break;
                    }

                    case 9:
                    {
                        //If the quantity is zero you will loop back
                        if (wild.getQuantity() == 0){
                            System.out.println("You do not have any wild "
                                    + "cards. Please choose another option:");
                            check = false;
                            break;
                        }
                         //You will succesfully heal
                        player.setHealth(player.getHealth() + wild.
                                getHealthBoost());
                        
                        /*
                            If random number out of 100 is less then 20 
                            then the enemy dodges the attack (20%) chance
                        */
                        if(randomChance < 20)
                        {
                            System.out.println("DARN! Your health increased "
                                    + "but your opponent dodged the attack." );
                        }
                        //You land the attack as well
                        else{
                            enemy[enemyNumber].setHealth(enemy[enemyNumber].
                                    getHealth() - wild.getDamage());
                            System.out.println("Your attack was succesful, "
                                    + "and your health has increased.");
                        }
                        break;
                    }
                    
                    case 10: 
                    {
                        System.out.println("*You used a stranger as a "
                                + "decoy and stabbed the enemy in the back*");
                        System.out.println("You lost 10 reputation");
                        System.out.println("============================"
                                + "========================");
                        
                        //sub 10 from reputation for bad deed
                        player.setReputation(player.getReputation() - 10);
                        return;
                    }
                  
                }
             // to loop back if user makes wrong input 
            } while (check == false);
            
            //If the enemies health goes to 0 you win
            if (enemy[enemyNumber].getHealth() <= 0)
            {
                System.out.print("ENEMY HEALTH HAS DEPLETED TO 0");
                
                //Make for statement for special effects 
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");
                    Thread.sleep(500);
                }
                System.out.println("\n");
                System.out.println(" .----------------. .----------------. .---"
                        + "-------------. \n" +
"| .--------------. | .--------------. | .--------------. |\n" +
"| |  ____  ____  | | |     ____     | | | _____  _____ | |\n" +
"| | |_  _||_  _| | | |   .'    `.   | | ||_   _||_   _|| |\n" +
"| |   \\ \\  / /   | | |  /  .--.  \\  | | |  | |    | |  | |\n" +
"| |    \\ \\/ /    | | |  | |    | |  | | |  | '    ' |  | |\n" +
"| |    _|  |_    | | |  \\  `--'  /  | | |   \\ `--' /   | |\n" +
"| |   |______|   | | |   `.____.'   | | |    `.__.'    | |\n" +
"| |              | | |              | | |              | |\n" +
"| '--------------' | '--------------' | '--------------' |\n" +
" '----------------' '----------------' '----------------' ");
                System.out.println(" .----------------. .----------------. "
                        + ".-----------------.\n" +
"| .--------------. | .--------------. | .--------------. |\n" +
"| | _____  _____ | | |     _____    | | | ____  _____  | |\n" +
"| ||_   _||_   _|| | |    |_   _|   | | ||_   \\|_   _| | |\n" +
"| |  | | /\\ | |  | | |      | |     | | |  |   \\ | |   | |\n" +
"| |  | |/  \\| |  | | |      | |     | | |  | |\\ \\| |   | |\n" +
"| |  |   /\\   |  | | |     _| |_    | | | _| |_\\   |_  | |\n" +
"| |  |__/  \\__|  | | |    |_____|   | | ||_____|\\____| | |\n" +
"| |              | | |              | | |              | |\n" +
"| '--------------' | '--------------' | '--------------' |\n" +
" '----------------' '----------------' '----------------' ");
                System.out.println("\nPRESS (ENTER) TO END BATTLE");
                dialogueContinue();
                //Resset player health
                setPlayerStats(player, attack);
                //return to other method
                return;
            }
            
            //printint for special effects
            System.out.print("Waiting for opponent attack");
            for (int i = 0; i < 5; i++) {
                System.out.print(".");
                Thread.sleep(500);
            }
            
            //if random number is less then 0 you dodge the attack
            if(randomChance < 20){
                System.out.println("You dodged the attack!");
            }
            //The enemy is succesful with his attack
            else
            {
               player.setHealth(player.getHealth() - enemy[enemyNumber].
                       getAttack());

                System.out.println("\nThe opponent was succesful with "
                        + "their attack. They did " + 
                        enemy[enemyNumber].getAttack() +" damage!");
            }
            
            //random chance of enemy getting special boosts 
            if(randomChance > 30 && randomChance < 40)
            {
                System.out.println("The opponent also got "
                        + "health/attack boosts.");
                enemy[enemyNumber].randomBoost();
            }
            System.out.println("========================================="
                    + "===========");
            
            //if your health goes down to zero you lose
            if(player.getHealth() <= 0)
            {
                System.out.print("YOUR HEALTH HAS DEPLETED TO 0");
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");
                    Thread.sleep(500);
                }
                System.out.println("\n");
                System.out.println(" .----------------. .----------------. .--"
                        + "--"
                        + "------------. \n" +
"| .--------------. | .--------------. | .--------------. |\n" +
"| |  ____  ____  | | |     ____     | | | _____  _____ | |\n" +
"| | |_  _||_  _| | | |   .'    `.   | | ||_   _||_   _|| |\n" +
"| |   \\ \\  / /   | | |  /  .--.  \\  | | |  | |    | |  | |\n" +
"| |    \\ \\/ /    | | |  | |    | |  | | |  | '    ' |  | |\n" +
"| |    _|  |_    | | |  \\  `--'  /  | | |   \\ `--' /   | |\n" +
"| |   |______|   | | |   `.____.'   | | |    `.__.'    | |\n" +
"| |              | | |              | | |              | |\n" +
"| '--------------' | '--------------' | '--------------' |\n" +
" '----------------' '----------------' '----------------' ");
                System.out.println(" .----------------. .----------------. .---"
                        + "-------------. \n" +
"| .--------------. | .--------------. | .--------------. |\n" +
"| |      __      | | |  _______     | | |  _________   | |\n" +
"| |     /  \\     | | | |_   __ \\    | | | |_   ___  |  | |\n" +
"| |    / /\\ \\    | | |   | |__) |   | | |   | |_  \\_|  | |\n" +
"| |   / ____ \\   | | |   |  __ /    | | |   |  _|  _   | |\n" +
"| | _/ /    \\ \\_ | | |  _| |  \\ \\_  | | |  _| |___/ |  | |\n" +
"| ||____|  |____|| | | |____| |___| | | | |_________|  | |\n" +
"| |              | | |              | | |              | |\n" +
"| '--------------' | '--------------' | '--------------' |\n" +
" '----------------' '----------------' '----------------' ");
                System.out.println(" .----------------. .----------------. .-"
                        + "---------------. .----------------. \n" +
"| .--------------. | .--------------. | .--------------. | .--------------. "
                        + "|\n" +
"| |  ________    | | |  _________   | | |      __      | | |  ________    | "
                        + "|\n" +
"| | |_   ___ `.  | | | |_   ___  |  | | |     /  \\     | | | |_   ___ `.  | "
                        + "|\n" +
"| |   | |   `. \\ | | |   | |_  \\_|  | | |    / /\\ \\    | | |   | |   `. \\"
                        + " | |\n" +
"| |   | |    | | | | |   |  _|  _   | | |   / ____ \\   | | |   | |    | | | |"
                        + "\n" +
"| |  _| |___.' / | | |  _| |___/ |  | | | _/ /    \\ \\_ | | |  _| |___.' / | "
                        + "|\n" +
"| | |________.'  | | | |_________|  | | ||____|  |____|| | | |________.'  | "
                        + "|\n" +
"| |              | | |              | | |              | | |              |"
                        + " |\n" +
"| '--------------' | '--------------' | '--------------' | '--------------' "
                        + "|\n" +
" '----------------' '----------------' '----------------' '----------------' "
                        + "");
                System.out.println("\nPRESS (ENTER) TO RESTART BATTLE");
                dialogueContinue();
                //resetting values
                loadGameData(gameSave, enemy, player, gameDifficulty, 
                        attack);
                setPlayerStats(player, attack);
                check = true;
            }
            else
            {
                check = true;
            }
        //loop back for more rounds in the game
        } while(check);
    }
}
