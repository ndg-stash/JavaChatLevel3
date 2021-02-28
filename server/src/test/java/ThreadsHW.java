public class ThreadsHW {

    final static int cyclesCount = 100;
    final static char[] chars = new char[]{'A','B','C','D','E','F','G','H','I','J','K'};
    static int idx = 0;

    public static void main(String[] args) {

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

        //Почему при использовании слипа они уже синхронизируются?
//        new Thread(() -> {
//            try {
//                Thread.sleep(1);
//                for (int i = 0; i < cyclesCount; i++) {
//                    printChars();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(1);
//                for (int i = 0; i < cyclesCount; i++) {
//                    printChars();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(1);
//                for (int i = 0; i < cyclesCount; i++) {
//                    printChars();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

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

}
