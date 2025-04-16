package Task;

public class UtilsMultiTreading {
    public static  void printMark(int mark){
        System.out.printf("%s - %d\n",             // два параметра: Имя потока
                Thread.currentThread().getName(),  // Имя потока
                mark);                             // Номер потока
    }


    public static  void sleepSec(long sec){
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
