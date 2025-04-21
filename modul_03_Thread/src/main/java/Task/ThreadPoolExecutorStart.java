package Task;

public class ThreadPoolExecutorStart {
    // Пуск
    public static void main(String[] args) {
        MainThredPolExecutor mainThredPolExecutor = new MainThredPolExecutor(4);

        for (int i = 0; i < 100; i++) {                   //  Накидываем задачи
            int j = i;
            if (i >= 40) {mainThredPolExecutor.shutdown(); } // Для теста остановки потока
            mainThredPolExecutor.execute(                // Выполнение задачи
                    () -> UtilsMultiTreading.printMark(j)
            );
        }
        mainThredPolExecutor.shutdown();
    }
}
