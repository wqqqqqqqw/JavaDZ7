package javadz.DZ7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javadz.DZ7.classloaders.ApiClassloader;
import javadz.DZ7.classloaders.ImplClassloader;
import javadz.DZ7.classloaders.AppClassloader;

public class Main {

    public static void main(String[] args) {

        ApiClassloader apiCL = new ApiClassloader(ClassLoader.getSystemClassLoader());
        AppClassloader appCL = new AppClassloader(apiCL);
        ImplClassloader implCL = new ImplClassloader(apiCL);

        Object calcObj;
        Object appObj;

        try {

            //CalculatorImpl грузится с помощью javadz.DZ7.classloaders.ImplClassloader
            calcObj = implCL.LoadClass("javadz.DZ7.impl.CalculatorImpl").newInstance();
            System.out.println(calcObj.getClass().getClassLoader().getClass().getName());

            System.out.println("--=========--");

            //Вызываем метод add на объекте calcObj
            Method m = calcObj.getClass().getMethod("add", new Class[]{double.class, double.class});
            m.invoke(calcObj, 2.7, 3.7);

            System.out.println("--=========--");

            //App грузится с помощью javadz.DZ7.classloaders.AppClassloader
            appObj = appCL.LoadClass("javadz.DZ7.app.App").newInstance();
            System.out.println(appObj.getClass().getClassLoader().getClass().getName());

            System.out.println("--=========--");

            //CalculatorImpl грузится с помощью sun.misc.Launcher$AppClassLoader, а не AppClassloader
            appObj = appCL.LoadClass("javadz.DZ7.impl.CalculatorImpl").newInstance();
            System.out.println(appObj.getClass().getClassLoader().getClass().getName());

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                | IllegalAccessException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
