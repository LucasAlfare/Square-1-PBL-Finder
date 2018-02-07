package com.main.pbl;

public class Main {

    public static void main(String[] args) {
        Finder finder = new Finder(Finder.allPbls()[46]);
        Thread t = new Thread(finder::check);

        //try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        t.start();
    }
}
