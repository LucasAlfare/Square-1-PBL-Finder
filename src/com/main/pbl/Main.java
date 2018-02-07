package com.main.pbl;

import com.main.puzzle.SquareOne;

public class Main {

    public static void main(String[] args) {
        Finder finder = new Finder(Finder.allPbls()[409]);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                finder.check();
            }
        });

        try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        t.start();
    }
}
