package Task;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

// Реализация собственного пула потоков
public class MainThredPolExecutor  {
    private LinkedList<Runnable> taskQueue;   // Список задач
    private final Thread[] threads;               // Список потоков
    //private volatile boolean stopProc;          // Флаг стоп (volatile - убрал из кеша, что бы соблюсти многопоточность)
    private AtomicBoolean stopProc;               // Флаг стоп (AtomicBoolean имеет методы, которые выполняют свои составные операции атомарно)
    Object monitor = new Object();                // Монитор

    // Инициализация с количеством рабочих потоков
    public MainThredPolExecutor(int poolSize) {
        this.taskQueue = new LinkedList<>();
        this.threads = new Thread[poolSize];
        //this.stopProc = false;
        this.stopProc = new AtomicBoolean(false);

        for (int i=0; i<poolSize; i++){
            threads[i] = new Thread(new Worker());   // Создаём потоки с обработчиком задачи
            threads[i].start();                      // Запускаем поток
            //System.out.println(threads[i].threadId());
        }
    }

    //Отправка задачи в очередь исполнения
    public void execute(Runnable task){
        if (stopProc.get()){                               // Если уже выполнили  остановку потоков
            throw new IllegalStateException("Новые задачи больше не принимаются");
        }
        synchronized (monitor) {
            taskQueue.add(task);                         // Добавляем задачу
            monitor.notify();                            // Говорим одному из ожидающих ( wait() ) потоку, что появилась новая задача
            //monitor.notifyAll();                         // Говорим всем ожидающим ( wait() ) потокам, что появилась новая задача
        }
    }

    // Остановка потока
    public synchronized void shutdown(){
        //stopProc = true;                           // Поставим флаг остановки
        stopProc.set(true);                          // Поставим флаг остановки
        //awaitTermination();
        for (Thread thread : threads){
            thread.interrupt();                      // Останавливаем поток
        }
        System.out.println(Thread.currentThread().getName()+"-Финиш");
    }

    public class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                Runnable task;
                synchronized(monitor) {                        //синхронизируемся
                    while (taskQueue.isEmpty() && !stopProc.get()) {  //пока список заданий пуст и нас не остановили,
                        try {
                            monitor.wait();                  // ждем: блокирует выполнение текущего потока до тех пор,
                                                             // тем самым позволяя выполнять операции другим потокам,
                                                             // до тех пор, пока на том же мониторе не будет вызван метод notify()
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }

                task = taskQueue.poll();                      // берем следующее задание
                if (task != null) {                           // если оно не пусто,
                    task.run();                               // выполняем его
                }
                else if (stopProc.get()) {                          // Если была команда остановки
                    Thread.currentThread().interrupt();       // Останавливаем поток
                    break;
                }
            }
        }
    }
}
