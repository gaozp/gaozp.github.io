---
layout: post
title: Java 动态代理原理解析
categories: [tech]
---
#### 前言：
Java中常见的两种代理方式：静态代理和动态代理。那什么是代理模式呢，代理模式就是给某一个对象提供一个代理，并由代理对象来控制对真实对象的访问，代理模式是一种结构型的设计模式。
![](../../img/stability/Proxy.png)
而代理模式**按照职责**来进行分类，可以分为：
1. 动态代理
2. 虚拟代理
3. Copy-on-Write 代理
4. 保护代理
5. Cache代理
6. 防火墙代理
7. 同步化代理
8. 只能引用代理

而根据**字节码创建时机**来进行分类，可以分为静态代理和动态代理：
1. 静态就是在程序运行钱就已经存在代理类的字节码文件，代理类和真实主题角色的关系在运行前就决定了。
2. 动态代理的源码是在程序运行期间有jvm根据反射等机制动态生成，所处在运行钱并不存在代理类的字节码文件。

#### 从一个例子开始：
```JAVA
// 接口函数 也就是上图中的Subject
    public interface Test {
        int add(int a,int b); // 上图中的doaction
    }
    // 接口的实现类，也就是上图中的 RealSubject
    static class TestDemo implements Test{

        @Override
        public int add(int a, int b) {
            return a+b;
        }
    }
    // 对应的handler
    static class TestHandler implements InvocationHandler {

        Object target;

        public TestHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before...");
            Object object = method.invoke(target,args);
            System.out.println("after...");
            return object;
        }
    }

    public static void main(String[] args) {
        // 创建实现类 realsubject
        TestDemo demo = new TestDemo();
        // 创建handler
        TestHandler handler = new TestHandler(demo);
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true"); 
        // 创建对应的proxy
        Test proxy = (Test) Proxy.newProxyInstance(demo.getClass().getClassLoader(), demo.getClass().getInterfaces(), handler);
        // 调用proxy的接口方法。
        System.out.println(proxy.add(1,2));
    }

```
运行结果：
```
before...
after...
3

Process finished with exit code 0
```
#### 带着问题去看整个过程：
1. invoke是在什么时候调用的？
2. Proxy类必然是一个实现了接口的类，而这个类在哪儿呢？

#### 关键函数newProxyInstance
```JAVA
// Proxy.java
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h) {

        /*
         * Look up or generate the designated proxy class.
         */
        Class<?> cl = getProxyClass0(loader, intfs);

        return cons.newInstance(new Object[]{h});

    }
```
上面我们只保留了关键的代码，首先创建了一个class，然后通过这个class的一参构造函数，创建了一个proxy对象返回了回去，但是还没有告诉我们具体invoke是什么时候调用的，以及这个类是怎么来的。我们接着看getProxyClass0
#### getProxyClass0
```JAVA
    /**
     * Generate a proxy class.  Must call the checkProxyAccess method
     * to perform permission checks before calling this.
     */
    private static Class<?> getProxyClass0(ClassLoader loader,
                                           Class<?>... interfaces) {
        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }

        // If the proxy class defined by the given loader implementing
        // the given interfaces exists, this will simply return the cached copy;
        // otherwise, it will create the proxy class via the ProxyClassFactory
        return proxyClassCache.get(loader, interfaces);
    }

        /**
     * a cache of proxy classes
     */
    private static final WeakCache<ClassLoader, Class<?>[], Class<?>>
        proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());
```
这下好了，从注释中我们就可以看到，是创建了一个proxy类，但是需要查看是否超过65535这个上限。再往下看，是通过weakcache来获取到对应的class。此处的weakcache，我们也可以看到是传入了两个工厂类。至于其中的weakcache如何获取到value，不是本篇需要讨论的内容。只要知道是通过后面的factory也就是ProxyClassFactory进行创建出来的就可以，我们查看factory的代码：
```JAVA
        @Override
        public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {
            /*
             * Generate the specified proxy class.
             */
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);
        }
```
其中的关键函数就是创建对应的proxy字节码，创建的过程不表，我们来看一下对应的创建出来的字节码：
```JAVA
public final class $Proxy0 extends Proxy implements Test {
    private static Method m1;
    private static Method m2;
    private static Method m3;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int add(int var1, int var2) throws  {
        try {
            return (Integer)super.h.invoke(this, m3, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m3 = Class.forName("com.company.Test").getMethod("add", Integer.TYPE, Integer.TYPE);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
```
可以看到，确实是实现了test的接口，同时，构造函数也有一个是接受InvocationHandler作为参数，传给了super的。
所以此时我们就解决了第二个问题：Proxy的创建是在获取weakcache的时候进行创建的，如果没有，就通过接口，动态的创建一个proxy类，同时将handler传入。  
那么此时再查看其中的函数，可以发现，其实都是调用的handler的invoke方法，那么其实也就解决了第一个问题：就是在创建的Proxy中，所有的方法都是通过调用handler的invoke方法来实现的。  
这也就是为什么handler中需要一个具体的接口实现的原因。  
#### 总结：
在newProxyInstance的时候，动态的创建了proxy类来实现接口，而proxy中的所有方法同时通过传入的invocationhandler的invoke方法来实现具体功能。而此时其实并没有具体的接口实现类，所以我们需要在handler中接受一个具体的接口实现，在invoke中通过调用具体的实现来完成功能。  
通过这样的解析，我们也就知道了： 
1. 动态代理的效率比较低，因为在创建的时候需要生成字节码，同时运用的是反射调用
2. invoke的调用是在proxy的生成类中每个方法中进行调用的
3. proxy的生成是在程序运行的时候进行生成的
