---
layout: post
title: java1.8方法引用官方文档
categories: [tech]
---
#### 方法引用
你可以使用lambda表达式来创建匿名方法，但是，有时候lambda表达式仅仅是调用了另外一个已经存在的方法，再这样的情况下，直接引用方法反而更加清晰，而方法引用就是为此而诞生的。他们小巧紧凑，并且易于阅读。  

首先，让我们来看到Person类
```java
public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        // ...
    }
    
    public Calendar getBirthday() {
        return birthday;
    }    

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }}
```
那我们现在假设你的应用程序将上面bean的用户放在一个数组里，而你需要将他们通过age进行排序。你可以像下面这样写
```JAVA
Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

class PersonAgeComparator implements Comparator<Person> {
    public int compare(Person a, Person b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }
}
        
Arrays.sort(rosterAsArray, new PersonAgeComparator());

static <T> void sort(T[] a, Comparator<? super T> c)
```
可以看到compactor接口是一个功能接口，所以，我们可以直接使用lambda表达式来代替创建一个新的类来实现comparator接口：
```JAVA
Arrays.sort(rosterAsArray,
    (Person a, Person b) -> {
        return a.getBirthday().compareTo(b.getBirthday());
    }
);
```
同时，我们可以看到，比较两个person的age的方法已经存在于Person类中，所以可以这样修改我们的代码
```JAVA
Arrays.sort(rosterAsArray,
    (a, b) -> Person.compareByAge(a, b)
);
```
因为lambda表达式直接调用了一个存在的方法，你可以直接使用方法引用来替代lambda表达式：
```JAVA
Arrays.sort(rosterAsArray, Person::compareByAge);
```
方法引用Person::compareByAge 是和lambda表达式(a,b)->PersoncompareByAge(a,b)都有着下面的特征：  
1.方法的参数都是从Comparator<Person>.compare, 也就是(Person,Person)  
2.方法体都是调用了Person.compareByAge  
#### 方法引用的种类
一共有4种方法引用
|种类|例子|
|-|-|
|静态方法引用|ContainingClass::staticMethodName|
|实例方法引用|containingObject::instanceMethodName|
|类的任意对象的实例方法引用|ContainingType::methodName|
|构造器引用|ClassName::new|
##### 静态方法引用
如Person::compareByAge就是一个静态方法引用
##### 实例方法引用
如下代码就是一个典型的实例方法引用
```JAVA
class ComparisonProvider {
    public int compareByName(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }
        
    public int compareByAge(Person a, Person b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }
}
ComparisonProvider myComparisonProvider = new ComparisonProvider();
Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);
```
这个方法引用myComparisonProvider::compareByName 是调用myComparisonProvider的方法compareByName，所以jre环境会推断出方法的参数，在这里就是(Person,Person).
##### 类的任意对象的实例方法引用
请看下方的示例：
```JAVA
String[] stringArray = { "Barbara", "James", "Mary", "John",
    "Patricia", "Robert", "Michael", "Linda" };
Arrays.sort(stringArray, String::compareToIgnoreCase);
```
与之等价的lambda表达式是 STRING::compareToIgnoreCase，参数是（String a,String b）,所以此处的方法引用实际调用的就是a.compareToTgnoreCase(b).
##### 构造器引用
你可以像使用静态方法引用那样去使用构造器引用，底下就是例子,这里是将一个集合中的成员拷贝到另外一个集合中
```JAVA
public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(
        SOURCE sourceCollection,
        Supplier<DEST> collectionFactory) {
        
        DEST result = collectionFactory.get();
        for (T t : sourceCollection) {
            result.add(t);
        }
        return result;
}
```
supplier接口包含了一个get方法，接受空参然后返回一个object。所以你可以这样使用lambda表达式：
```JAVA
Set<Person> rosterSetLambda =
    transferElements(roster, () -> { return new HashSet<>(); });
```
你也可以使用构造器引用来替代lambda表达式
```JAVA
Set<Person> rosterSet = transferElements(roster, HashSet::new);
```
而java编译器会推断你想要创建HashSet，或者你也可以指定泛型
```JAVA
Set<Person> rosterSet = transferElements(roster, HashSet<Person>::new);
```
#### 原文地址
[tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)
