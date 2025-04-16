package Task;

public class ThreadPoolExecutorStart {
    public static void main(String[] args) {
        MainThredPolExecutor mainThredPolExecutor = new MainThredPolExecutor(4);

//        for (int i = 0; i < 4; i++) {                   //  Накидываем задачи
//            int j = i;
//            mainThredPolExecutor.execute(                // Выполнение задачи
//                    () -> UtilsMultiTreading.printMark(j)
//            );
//        }

        mainThredPolExecutor.execute(() -> UtilsMultiTreading.printMark(1));
        mainThredPolExecutor.execute(() -> UtilsMultiTreading.printMark(2));
        mainThredPolExecutor.execute(() -> UtilsMultiTreading.printMark(3));
        mainThredPolExecutor.execute(() -> UtilsMultiTreading.printMark(4));
        mainThredPolExecutor.shutdown();                                       // Для теста остановки потока
        //UtilsMultiTreading.sleepSec(1);
        mainThredPolExecutor.execute(() -> UtilsMultiTreading.printMark(5));
        mainThredPolExecutor.execute(() -> UtilsMultiTreading.printMark(6));
        mainThredPolExecutor.execute(() -> UtilsMultiTreading.printMark(7));
        mainThredPolExecutor.execute(() -> UtilsMultiTreading.printMark(8));

    }
}
