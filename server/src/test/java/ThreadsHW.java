public class ThreadsHW {

    final static int cyclesCount = 5;
    final static char[] chars = new char[]{'A','B','C'};
    static volatile int idx = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                printChars();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                printChars();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < cyclesCount; i++) {
                printChars();
            }
        }).start();
    }

    static synchronized void printChars(){
        System.out.print(chars[idx]);
        //idx = idx == 2 ? 0 : idx + 1;
        if (idx < 2){
            idx++;
        } else{
            idx = 0;
            System.out.println();
        }
    }

}
