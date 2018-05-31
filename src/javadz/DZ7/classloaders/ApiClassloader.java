package javadz.DZ7.classloaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ApiClassloader extends ClassLoader {

    ClassLoader parent;

    public ApiClassloader(ClassLoader parent) {
        this.parent = parent;
    }

    Class getClassFromFile(String name) throws ClassNotFoundException {

        try {
            String classFileUrl = new StringBuilder(System.getProperty("user.dir").replace('\\', '/'))
                    .append("/build/classes/")
                    .append(name.replace('.', '/'))
                    .append(".class").toString();

            File file = new File(classFileUrl);

            if (file.exists()) {
                byte[] result = new byte[(int) file.length()];

                FileInputStream fileInStream = new FileInputStream(file);
                fileInStream.read(result, 0, result.length);

                System.out.println(this.getClass().getName() + " works");
                return defineClass(name, result, 0, result.length);
            }
        } catch (IOException ex) {
            throw new ClassNotFoundException("Cannot load class " + name + ": " + ex);
        }
        return null;
    }

    public Class LoadClass(String name) throws ClassNotFoundException {

        if (!name.startsWith("javadz.DZ7.api.")) {
            //System.out.println(this.parent.getClass().getName());
            return parent.loadClass(name);
        }
        return getClassFromFile(name);
    }
}
