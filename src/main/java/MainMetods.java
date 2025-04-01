import Annotation.AfterSuite;
import Annotation.BeforeSuite;
import Annotation.Test;

public class MainMetods {
    public MainMetods() {
    }

    @BeforeSuite
    public void metBeforeSuite(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Отработал метод  " + methodName);;
    }

    @Test
    public void metTest01_def(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Отработал метод  " + methodName);
    }

    @Test(priority = 2)
    public void metTest02_02(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Отработал метод  " + methodName);
    }

    @Test(priority = 7)
    public void metTest03_07(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Отработал метод  " + methodName);
    }

    @Test(priority = 7)
    public void metTest04_07(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Отработал метод  " + methodName);

    }

    @AfterSuite
    public void metAfterSuite(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Отработал метод  " + methodName);
    }
}
