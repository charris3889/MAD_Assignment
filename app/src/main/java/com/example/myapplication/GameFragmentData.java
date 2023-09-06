package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameFragmentData extends ViewModel {
    private MutableLiveData<Integer> gameState; //0 if draw, 1 if p1 win, 2 if p2 win
    private Game game;
    private Player player1;
    private Player player2;

    public GameFragmentData(Game g, Player p1, Player p2) {
        gameState.setValue(-1);
        game = g;
        player1 = p1;
        player2 = p2;
    }
}
