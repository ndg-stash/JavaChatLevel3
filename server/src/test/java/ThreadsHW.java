public class ThreadsHW {

    final static int cyclesCount = 1000;
    final static char[] chars = new char[]{'A','B','C','D','E','F','G','H','I','J','K'};
    final static char[] charsLC = new char[]{'a','b','c','d','e','f','g','h','i','j','k'};

    static int idx = 0;
    static int globalCounter = 0;

    public static void main(String[] args) {

        //threads();
        threadsV0();
        //threadsV1();
        //threadsV2(); //без синхронизации

    }

    static void threads(){
        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                printCharsSyncMod();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                printCharsSyncMod();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                printCharsSyncMod();
            }
        }).start();
    }

    static void threadsV0(){
        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printCharsSyncModwithCases(0);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printCharsSyncModwithCases(1);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printCharsSyncModwithCases(2);
            }
        }).start();
    }


    static void threadsV1(){
        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {

                printCharsSync();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                printCharsSync();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                printCharsSync();
            }
        }).start();
    }

    static void threadsV2(){
        //Почему при использовании слипа они уже синхронизируются?
        //Вопрос не актуален, просто тред отрабатывает за время слипа другого:)
        new Thread(() -> {
            try {
                Thread.sleep(100);
                for (int i = 0; i < cyclesCount; i++) {
                    printChars();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(50);
                for (int i = 0; i < cyclesCount; i++) {
                    printChars();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10);
                for (int i = 0; i < cyclesCount; i++) {
                    printChars();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    static void printChars(){
        System.out.print(chars[idx]);
        //idx = idx == 2 ? 0 : idx + 1;
        if (idx < chars.length-1){
            idx++;
        } else{
            idx = 0;
            System.out.println();
        }
    }

    static synchronized void printCharsSync(){
        System.out.print(chars[idx]);
        //idx = idx == 2 ? 0 : idx + 1;
        if (idx < chars.length-1){
            idx++;
        } else{
            idx = 0;
            System.out.println();
        }
    }

    static synchronized void printCharsSyncMod(){
        int counterIdx = globalCounter%chars.length;
        System.out.print(chars[counterIdx]);
        if (counterIdx == chars.length-1){
            System.out.println();
        }
        globalCounter++;
    }

    static synchronized void printCharsSyncModwithCases(int threadId){
        int counterIdx = globalCounter%chars.length;
        if (threadId == 1){
            System.out.print(charsLC[counterIdx]);
        } else {
            System.out.print(chars[counterIdx]);
        }

        if (counterIdx == chars.length-1){
            System.out.println();
        }
        globalCounter++;
    }

}
