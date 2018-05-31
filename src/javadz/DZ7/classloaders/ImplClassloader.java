package javadz.DZ7.classloaders;

public class ImplClassloader extends ApiClassloader {

    public ImplClassloader(ClassLoader parent) {
        super(ApiClassloader.class.getClassLoader());
        this.parent = parent;
    }

    public Class LoadClass(String name) throws ClassNotFoundException {

        if (!name.startsWith("javadz.DZ7.impl.")) {
            //System.out.println(this.parent.getClass().getName());
            return this.parent.loadClass(name);
        }
        return getClassFromFile(name);
    }
}
