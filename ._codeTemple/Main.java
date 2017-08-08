package com.company;


public class Main {

    private static Object a = new Object();
    private static Object b = new Object();
    static Thread A = new Thread(){
        @Override
        public void run() {
            synchronized (a){
                System.out.println("1aholds    "+Thread.holdsLock(a));
                System.out.println("1bholds    "+Thread.holdsLock(b));
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){

                    System.out.println("12aholds    "+Thread.holdsLock(a));
                    System.out.println("12bholds    "+Thread.holdsLock(b));
                    System.out.print("A");
                }

            }
            super.run();
        }
    };

    static Thread B = new Thread(){
        @Override
        public void run() {
            synchronized (b){
                System.out.println("2aholds    "+Thread.holdsLock(a));
                System.out.println("2bholds    "+Thread.holdsLock(b));
                synchronized (a){
                    System.out.println("22aholds    "+Thread.holdsLock(a));
                    System.out.println("22bholds    "+Thread.holdsLock(b));
                    System.out.print("B");
                }

            }
            super.run();
        }
    };


    public static void main(String[] args) {
        A.start();
        B.start();
	// write your code here
    }
}
