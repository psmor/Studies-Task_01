import Annotation.AfterSuite;
import Annotation.BeforeSuite;
import Annotation.Test;
import Except.MainException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TestRunner {
    public int i;
    public TestRunner() {
    }

    public static void runTest(Class c) throws MainException, InvocationTargetException, IllegalAccessException, Exception {

        Method[] methods = c.getDeclaredMethods();

        // Проверки
        int cntBefor = 0, cntAfter = 0;
        Method methodBefor = null;
        Method methodAfter = null;
        //Method[] methodsTest = new Method[];
        ArrayList<Method> methodsTest = new ArrayList<Method>();
        ArrayList<Method> methodsRun = new ArrayList<Method>();

        //int i = 0;
        for (Method method : methods){
            method.setAccessible(true);                   // Открою доступ

            if (method.isAnnotationPresent(BeforeSuite.class)){
                cntBefor++;// cntBefor = cntBefor+1;
                methodBefor = method;
            }
            if (method.isAnnotationPresent(AfterSuite.class)){
                cntAfter++;//cntAfter = cntAfter+1;
                methodAfter = method;
            }

            if (method.isAnnotationPresent(Test.class)){
                methodsTest.add(method);
            }
        }

        //if (cntBefor == 0) { throw new MainException("Нет методов с аннотацией BeforeSuite");}
        if (cntBefor > 1)  { throw new MainException("Методов с аннотацией BeforeSuite больше 1-го"); }
        //if (cntAfter == 0) { throw new MainException("Нет методов с аннотацией AfterSuite"); }
        if (cntAfter > 1)  { throw new MainException("Методов с аннотацией AfterSuite больше 1-го"); }

        // Построение последовательности методов
        Object o = c.getConstructor().newInstance();

        methodsRun.add(methodBefor);

        int lastPriority;
        ArrayList<MethodPriority> metodsSort = new ArrayList<MethodPriority>();
        for (int i = 0; i < methodsTest.size(); i++) {
            //System.out.print(i+": "+methodsTest.get(i)+" ");  // Мнтод
            Test testAnnotation = methodsTest.get(i).getAnnotation(Test.class);
            //System.out.println("priority = "+testAnnotation.priority()); // Приоритет
            lastPriority = testAnnotation.priority();

            MethodPriority metod = new MethodPriority(lastPriority, methodsTest.get(i));
            metodsSort.add(metod);
        }
        Collections.sort(metodsSort);
        for (MethodPriority metodSort : metodsSort){
            methodsRun.add(metodSort.getMethod());
        }

        methodsRun.add(methodAfter);

        // Выполнение методоы
        for (Method method : methodsRun){
            method.invoke(o);
        }
    }

//    private ArrayList<Method> getMetodPriority(ArrayList<Method> metodsIn){
//        ArrayList<Method> metodsOut = new ArrayList<Method>();
//        int ind = 0;
//        int lastPriority, lastPriorityI, lastPriorityJ;
//        Test testAnnotation, testAnnotationI, testAnnotationJ;
//        for (int i = 0; i < metodsIn.size(); i++){
//            testAnnotationI = metodsIn.get(i).getAnnotation(Test.class);
//            lastPriorityI = testAnnotationI.priority();
//            lastPriority = lastPriorityI;
//
//
//            for (int j = 0; j < metodsIn.size(); j++){
//                testAnnotationJ = metodsIn.get(j).getAnnotation(Test.class);
//                lastPriorityJ = testAnnotationJ.priority();
//                if (lastPriorityJ >= lastPriority) {
//                    lastPriority = lastPriorityJ;
//                    metodsOut.add(ind, metodsIn.get(j));
//                } else {
//                    metodsOut.add(ind, metodsIn.get(i));
//                }
//            }
//            ind++;
//        }
//    }
}
