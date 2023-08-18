package com.example.myapplication;

public class Game {
    //private final static int player1 = 1;
    //private final static int player2 = 2;
    private int turn = 1;
    int[][] board;

    public Game(int size) {
        if(!legalBoard(size)) {
            //Chuck an error
        }
        
        board = new int[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }
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

    public boolean makeMove(int xCoord, int yCoord) {
        if(legalMove(xCoord, yCoord)) {
            board[yCoord][xCoord] = turn;
        }
        changeTurn();

    }

    public boolean hasWon(int xCoord, int yCoord) {
            
    }

    public boolean hasDraw() {

    }

    public boolean TleftTobRightDiagonal(int xCoord, int yCoord) {
        
    }

    public boolean tRightTobLeftDiagonal(int xCoord, int yCoord) {

    }

    public boolean topToBottom(int xCoord, int yCoord) {

    }

    public boolean leftToRight(int xCoord, int yCoord) {

    }
}
