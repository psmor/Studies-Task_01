import Except.MainException;

import java.lang.reflect.InvocationTargetException;

public class main {
    public static void main(String[] args) {
        try {
            TestRunner.runTest(MainMetods.class);
        } catch (MainException e) {
            System.out.println("ERROR MainException: " + e.toString());
        } catch (InvocationTargetException e) {
            System.out.println("ERROR InvocationTargetException: InvocationTargetException");
        } catch (IllegalAccessException e) {
            System.out.println("ERROR IllegalAccessException: " + e.toString());
        } catch (Exception e) {
            System.out.println("ERROR: Exception"+ e.toString());
        }
    }
}
