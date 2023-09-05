package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Game {
    //private final static int player1 = 1;
    //private final static int player2 = 2;
    private int turn = 1;
    private int winLength;
    private int[][] board;
    private int boardDimensions;
    private List<int[]> moveList;  
    

    public Game(int size, int winCondition) {
        if(!legalCoord(size)) {
            //Chuck an error
        }
        board = makeBoard(size);
        boardDimensions = size;
        moveList = new ArrayList<int[]>();
        winLength = winCondition;
    }

    public int[][] makeBoard(int size) {

        int[][] board = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }
        
        return board;
    }

    public boolean legalCoord(int size) {
        if(size > 6) {
            return false;
        }
        else if(size < 2) {
            return false;
        }
        
        return true;
    }

    public boolean legalMove(int xCoord, int yCoord) {
        if(!legalCoord(yCoord)) {
            return false;
        }
        if(!legalCoord(xCoord)) {
            return false;
        } 
        if(board[yCoord][xCoord] != 0) {
            return false;
        }
        return true;
    }

    public void changeTurn() {
        if(turn == 1) {
            turn = 2;
        }
        else {
            turn = 1;
        }
    }

    public int makeMove(int xCoord, int yCoord) {
        int infoReturn = -1;
        
        if(legalMove(xCoord, yCoord)) {
            
            board[yCoord][xCoord] = turn;

            if(hasWon(xCoord, yCoord)) {
                infoReturn = 1;
            }
            else if(hasDraw()) {
                infoReturn = 2;
            }
            else {
                infoReturn = 0;
            }
            int[] move = {xCoord, yCoord};
            moveList.add(move);
            changeTurn();
        }

        return infoReturn;
    }
    private int movesMade() {
        return moveList.size();
    }

    private int movesLeft() {
        return (boardDimensions * boardDimensions - movesMade());
    }
    
    public void resetGame() {
        board = makeBoard(boardDimensions);
        moveList = new ArrayList<int[]>();
        turn = 1;
    }
    public void undoLastMove() {
        if(moveList.size() != 0) {
            int[] move = moveList.remove(moveList.size() - 1);
            board[move[1]][move[0]] = 0;
            changeTurn();
        }
        //Maybe have it throw an error but nothing is ok for now
    }

    public boolean hasWon(int xCoord, int yCoord) {
        if(TleftTobRightDiagonal(xCoord, yCoord)) {
            return true;
        }
        else if(tRightTobLeftDiagonal(xCoord, yCoord)) {
            return true;
        }
        else if(topToBottom(xCoord, yCoord)) {
            return true;
        }
        else if(leftToRight(xCoord, yCoord)) {
            return true;
        }

        return false;
    }

    public boolean hasDraw() {
        for(int i = 0; i < boardDimensions; i++) {
            for(int j = 0; j < boardDimensions; j++) {
                if(board[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean TleftTobRightDiagonal(int xCoord, int yCoord) {
        int i = 0;
        int j = xCoord - yCoord;
        int consecutiveTokens = 0;
        
        if(j < 0) {
            i = 0 - j;
            j = 0;
        }
        while(i < boardDimensions)
        {
            if(board[j][i] != turn) {
                consecutiveTokens = 0;
            }
            else {
                consecutiveTokens++;
            }   
            
            if(consecutiveTokens >= winLength) {
                return true;
            }
            
            i++;
        }

        return false;
    }

    public boolean tRightTobLeftDiagonal(int xCoord, int yCoord) {
        int consecutiveTokens = 0;
        int i = 0;
        int j = xCoord + yCoord;
        if(j > (boardDimensions - 1)) {
            i = j - boardDimensions - 1; 
            j = boardDimensions - 1;
        } 
    
        while(i >= 0 && j < boardDimensions)
        {
            if(board[j][i] != turn) {
                consecutiveTokens = 0;
            }
            else {
                consecutiveTokens++;
            }    

            if(consecutiveTokens >= winLength) {
                return true;
            }
            i--;
            j++;
        }
        

        return false;
    }

    public boolean topToBottom(int xCoord, int yCoord) {
        int consecutiveTokens = 0;
        for(int i = 0; i < boardDimensions; i++) {
            if(board[i][xCoord] != turn) {
                consecutiveTokens = 0;
            }
            else {
                consecutiveTokens++;
            }
        
            if(consecutiveTokens >= winLength) {
                return true;
            }
        }
        
        return false;
    }

    public boolean leftToRight(int xCoord, int yCoord) {
        int consecutiveTokens = 0;  
        for(int i = 0; i < boardDimensions; i++) {
            if(board[yCoord][i] != turn) {
                consecutiveTokens = 0;
            }
            else {
                consecutiveTokens++;
            }
            if(consecutiveTokens >= winLength) {
                return true;
            }
        }
        
        return false;
    }
}
