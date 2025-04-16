package Task;

import java.util.LinkedList;
import java.util.concurrent.*;

import static Task.UtilsMultiTreading.sleepSec;

public class MainThredPolExecutor {
    private final ThreadPoolExecutor threadPoolExecutor;
    private final LinkedList<Runnable> taskQueue = new LinkedList<Runnable>();;
    //private BlockingQueue<Runnable> taskQueue; //= new LinkedList<>();

    public MainThredPolExecutor(int nThreads) {
        //taskQueue = new LinkedList<>();
        this.threadPoolExecutor = new ThreadPoolExecutor(
                nThreads,                  // количество потоков, которое создаётся при старте
                nThreads,                  // max количество потоков. Расширение пула.
                10,                        // Время жизни потоков
                TimeUnit.SECONDS,          // Размерность времени жизни (екунд)
                new ArrayBlockingQueue<>(nThreads, false, taskQueue), // Где будет хранится очередь задач, в какой коллекции.
                //(BlockingQueue<Runnable>) taskQueue,
                new ThreadPoolExecutor.AbortPolicy() // Если при добавлении новой задачи всё будет занято, выбросит исключение
        );
    }

    // Отправка задачи в очередь исполнения
    public void execute(Runnable task){
        taskQueue.add(task);                 // Добавляем задачу
        try {
            threadPoolExecutor.execute(task);
        } catch (RejectedExecutionException e) {
            shutdown();
            throw new IllegalStateException("Новые задачи больше не принимаются");
        }
    }

    // Остановка потока
    public  void shutdown(){
        threadPoolExecutor.shutdown();
    }
}
