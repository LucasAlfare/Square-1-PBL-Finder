package com.main.pbl;

public class Main {

    public static void main(String[] args) {
        new Core(new Gui()).getGui().setVisible(true);

//        Thread a = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    System.out.println("oi rsrsrs");
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        Thread b = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    System.out.println("thread 2 rodando");
//                    try {
//                        Thread.sleep(1500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        a.start();
//        b.start();
    }
}
