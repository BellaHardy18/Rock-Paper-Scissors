/**
 * Name: Bella Hardy
 * ID: A16369822
 * Email: IHardy@UCSD.edu
 * Sources used: Zybooks and Lecture Slides
 * 
 * this file contains the main function that runs the 
 * actual game. This file also determines the winner of
 * the game to correcly award variables.
 */

import java.util.Scanner;

/*
 * This class determines both the winner and 
 * holds the main function of RPS. Prompts user for
 * and input to then be used in the game. 
 * This class also keeps track of player moves,
 * cpu moves, and possible moves for different methods. 
 * 
 */
public class RPS extends RPSAbstract {
    
    /**
     * assigns the possible moves to the possiblemoves array
     * and assigns player and cpu moves at the size of the 
     * Max ammount of games
     * @param moves - string array to hold moves
     * @return none
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove)
    {
        if(isValidMove(playerMove) != true || isValidMove(cpuMove) != true){
            return INVALID_INPUT_OUTCOME;
        }

        int indexCPUHolder = 0;
        int indexPlayerHolder = 0;
        //check where the index of the player move
        for(int indexPlayer = 0; indexPlayer < possibleMoves.length; indexPlayer++){
            if(possibleMoves[indexPlayer].equals(playerMove)){
                indexPlayerHolder = indexPlayer;
                
                break;
            }
        }
        //check index of the CPU move
        for(int indexCPU = 0; indexCPU < possibleMoves.length; indexCPU++){
            if(possibleMoves[indexCPU].equals(cpuMove)){
                indexCPUHolder = indexCPU;
                
                break;
            }
        }
        int endcheck = 1;

        //different cases to account for who the winner
        // of the game is with various possible moves. 
        if(indexPlayerHolder == 0 && indexCPUHolder == possibleMoves.length - endcheck){
            return CPU_WIN_OUTCOME;
        }
        else if(indexPlayerHolder == indexCPUHolder - endcheck){
            return PLAYER_WIN_OUTCOME;
        }
        else if(indexCPUHolder == 0 && indexPlayerHolder == possibleMoves.length - endcheck){
            return PLAYER_WIN_OUTCOME;
        }
        else if(indexCPUHolder == indexPlayerHolder - endcheck){
            return CPU_WIN_OUTCOME;
        }
        else if(indexCPUHolder == indexPlayerHolder){
            return TIE_OUTCOME;
        }
        else if(indexCPUHolder > indexPlayerHolder + 1 || indexCPUHolder < indexPlayerHolder - 1){
            return TIE_OUTCOME;
        }
        else if(indexPlayerHolder > indexCPUHolder+ 1 || indexPlayerHolder < indexCPUHolder - 1){
            return TIE_OUTCOME;
        }
        else{
            return INVALID_INPUT_OUTCOME;
        }
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        //System.out.println("Game not yet implemented.");
        
        // TODO: Insert the code to play the game. 
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written 
        // to do most of the work!

        while(true){
            System.out.println(PROMPT_MOVE);
            //promt for input
            String playerHolder = in.next();
            //end game
            if(playerHolder.equals("q")){
                game.end();
                break;
            }
            //check for valid move
            else if(game.isValidMove(playerHolder) == true){
                String CPUMoveHolder = game.genCPUMove();
                //actaully play the game
                game.play(playerHolder, CPUMoveHolder);
                game.determineWinner(playerHolder, CPUMoveHolder);
            }
            else{
                System.out.println(INVALID_INPUT);
            }
        }

        in.close();
    }
}
