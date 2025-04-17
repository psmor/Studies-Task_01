package Task;

import java.util.LinkedList;

public class MainThredPolExecutor  {
    private LinkedList<Runnable> taskQueue;   // Список задач
    private final Thread[] threads;           // Список потоков
    private volatile boolean stopProc;        // Флаг стоп (volatile - убрал из кеша, что бы соблюсти многопоточность)
    Object monitopr = new Object();           // Монитор

    // Инициализация с количеством рабочих потоков
    public MainThredPolExecutor(int poolSize) {
        this.taskQueue = new LinkedList<>();
        this.threads = new Thread[poolSize];
        this.stopProc = false;

        for (int i=0; i<poolSize; i++){
            threads[i] = new Thread(new Worker());   // Создаём потоки с обработчиком задачи
            threads[i].start();                      // Запускаем поток
            //System.out.println(threads[i].threadId());
        }
    }

    //Отправка задачи в очередь исполнения
    public synchronized void execute(Runnable task){
        if (stopProc){                               // Если уже выполнили  остановку потоков
            shutdown();
            throw new IllegalStateException("Новые задачи больше не принимаются");
        }
        taskQueue.add(task);                         // Добавляем задачу
        notify();                                    // Говорим ожидающим ( wait() ) потокам, что появилась новая задача
        //monitopr.notify();                           // Говорим ожидающим ( wait() ) потокам, что появилась новая задача

    }

    // Остановка потока
    public synchronized void shutdown(){
        stopProc = true;                           // Поставим флаг остановки
        for (Thread thread : threads){
            thread.interrupt();                    // Останавливаем поток
        }
    }

    public class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                Runnable task;
                synchronized(monitopr) {                        //синхронизируемся
                    while (taskQueue.isEmpty() && !stopProc) {  //пока список заданий пуст и нас не остановили,
                        try {
                            monitopr.wait();                  // ждем: блокирует выполнение текущего потока до тех пор,
                                                               // пока на том же мониторе не будет вызван метод notify()
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
                else if (stopProc) {                          // Если была команда остановки
                    Thread.currentThread().interrupt();       // Останавливаем поток
                    break;
                }

            }
        }
    }
}
