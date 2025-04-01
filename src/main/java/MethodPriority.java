import java.lang.reflect.Method;

public class MethodPriority implements Comparable<MethodPriority> {
    private int priority;
    private Method method;
    public MethodPriority(int priority, Method method){
        this.priority = priority;
        this.method = method;
    }

    public int getPriority() {
        return priority;
    }

    public Method getMethod() {
        return method;
    }
    @Override
    public int compareTo(MethodPriority o){
        //return this.priority - o.priority; // Сортировка по возрастанию
        return  o.priority - this.priority;  // Сортировка по убыванию
    }
}
