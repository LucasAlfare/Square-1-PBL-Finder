package com.main.pbl;

import com.main.puzzle.SquareOne;

public class Main {

    public static void main(String[] args) {
        Finder finder = new Finder(Finder.allPbls()[50]);
        finder.check();
    }
}
