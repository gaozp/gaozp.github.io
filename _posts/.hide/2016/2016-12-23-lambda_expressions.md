---
layout: post
title: lambda expressions
categories: [tech]
---

#### 前言：

其实lambda表达式在java1.8的时候就出来了，只是安卓源码开发环境中，直到6.0也还是1.7，直到7.0的牛轧糖的退出，才算是用上了1.8环境，也就是16年中旬的时候才算是开始用上了1.8，这样的情况下，对1.8特性的了解就变成了必不可少的学习了。首先就找了官方的lambda表达式的doc来翻译一下看看吧。



#### lambda 表达式(简介)

关于匿名内部类的一个问题：如果你需要实现的匿名内部类是一个十分简单，例如一个接口只包含一个方法的话。这个时候你的语法实现就会显得非常笨重不整洁。所以在这样的情况下，你习惯尝试将函数当成参数传给其他方法，例如当点击了一个按钮的时候需要干一些什么。lambda表达式允许你去做这些，将函数当成方法参数以及代码当做数据。

#### lambda表达式的理想应用场景

假设你正在写一个网络社交类的APP，你需要实现一个管理员，管理员可以执行所有的动作，如发送一个消息。下面的表格描述了这些字段：

| Field                   | Description                              |
| ----------------------- | ---------------------------------------- |
| Name                    | Perform action on selected members       |
| Primary Actor           | Administrator                            |
| Preconditions           | Administrator is logged in to the system. |
| Postconditions          | Action is performed only on members that fit the specified criteria. |
| Main Success Scenario   | Administrator specifies criteria of members on which to perform a certain action.Administrator specifies an action to perform on those selected members.Administrator selects the **Submit** button.The system finds all members that match the specified criteria.The system performs the specified action on all matching members. |
| Extensions              | 1a. Administrator has an option to preview those members who match the specified criteria before he or she specifies the action to be performed or before selecting the **Submit** button. |
| Frequency of Occurrence | Many times during the day.               |

假设这个应用中的用户是通过下面的数据格式进行保存的：

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

    public void printPerson() {
        // ...
    }
}
```

假设你APP的所有用户都存放在一个List<Person>中。

这一节将是一个简单的方法来用这些数据。使用了一些匿名内部类。然后使用了更为高效和整洁的lambda表达式来结束。

##### 方法1：筛选某一典型的用户

一种简单的方法就是写多个方法，每一个方法来匹配某一个典型。比如年龄或者行吧。下面的方法就是用来筛选年龄的：

```java
public static void printPersonsOlderThan(List<Person> roster, int age) {
    for (Person p : roster) {
        if (p.getAge() >= age) {
            p.printPerson();
        }
    }
}
```

这个方法会让你的APP更不具有兼容性，一旦采用了新的更新（如新的数据类型）十有八九会导致你的APP无法工作了。假设你升级了你的应用，让你的Person类用用了更多的类型，或者是你选择了另外一个格式的年龄或者是年龄的算法。你就需要去重写许许多多的方法来兼容这次更新。另外，这个方法也是限制性的，如果这个时候你需要打印的是小于某个年纪的呢？

##### 方法2： 写一个更加普适的方法

下面的是一个更加普遍的筛选年纪的方法，可以用来筛选一个年纪段的：

```java
public static void printPersonsWithinAgeRange(
    List<Person> roster, int low, int high) {
    for (Person p : roster) {
        if (low <= p.getAge() && p.getAge() < high) {
            p.printPerson();
        }
    }
}
```

这个时候如果你希望打印一些特定性别的人呢，或者是既筛选性别，又筛选年纪呢？又或者这个时候你打算改变Person类来添加诸如 relationship 和 location 呢？即使当前的方法已经比printpersonsolderthan更加普适，为每一种筛选都新建一个方法会使你的代码更加易碎。取代分散的代码的方式就是将判断的条件单独写在一个工具类中。

##### 方法3：将搜索条件提取成接口

你可以这样创建搜索条件：

```java
public static void printPersons(
    List<Person> roster, CheckPerson tester) {
    for (Person p : roster) {
        if (tester.test(p)) {
            p.printPerson();
        }
    }
}
```

你会检查list中的每一个实例是否满足CheckPerson的tester的test方法。如果满足的话就会打印这个用户。

如果需要添加标准，你只需要实现CheckPerson接口：

```java
interface CheckPerson {
    boolean test(Person p);
}
```

就比如下面的这个类实现了checkperson接口并且实现了test方法。它筛选出了男性并且在18-25之间的人。

```java
class CheckPersonEligibleForSelectiveService implements CheckPerson {
    public boolean test(Person p) {
        return p.gender == Person.Sex.MALE &&
            p.getAge() >= 18 &&
            p.getAge() <= 25;
    }
}
```

使用这个接口是，可以创建一个checkperson的实例，像这样：

```java
printPersons(
    roster, new CheckPersonEligibleForSelectiveService());
```

是的，这样的会就不会像之前那样不兼容——你不需要重新写方法当你改变Person的数据结构的时候——但是你还是需要添加额外的代码：一个新的接口，每一种你需要的筛选条件。当然了，你也可以使用匿名内部类。

##### 方法4：使用匿名内部类

将匿名内部类作为printPerson的参数传入

```java
printPersons(
    roster,
    new CheckPerson() {
        public boolean test(Person p) {
            return p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25;
        }
    }
);
```

这个方法会降低你的代码量，因为你不需要再为每一种filter特地去实现接口。但是匿名内部类的语法是笨重的。而且考虑到CheckPerson接口只包含一个方法，在这样的情况下，我们可以使用lambda表达式来替代匿名内部类。

##### 方法5：使用lambda表达式

CheckPerson接口是一个functional接口，functional接口是指只有一个抽象方法的接口（__functional接口可以包含一个或多个default或者static的方法__），因为只有一个抽象方法，你可以省略到方法的名字当你实现它的时候。如下例，lambda表达式用粗体表示出来了

```java
printPersons(
    roster,
    (Person p) -> p.getGender() == Person.Sex.MALE
        && p.getAge() >= 18
        && p.getAge() <= 25
);
```

当然了，你也可以使用标准接口来取代checkperson，这样会减少更多的代码量。

##### 方法6：使用标准接口

让我们来重新思考下checkperson接口

```java
interface CheckPerson {
    boolean test(Person p);
}
```

这是一个非常简单的接口，同时也是一个functional接口因为只包含了一个抽象方法。这个方法接受一个参数，返回一个boolean的值。这个接口是如此的简单以至于你不需要在自己的应用中去实现它。因此，jdk提供了许多标准的functional接口，你可以在java.util.function包中找到它们。

比如，你可以使用Predicate<T>接口来替代checkperson，这个接口包含了 boolean test(T t):

```java
interface Predicate<T> {
    boolean test(T t);
}
```

Predicate只是一个泛型接口，泛型这边就不多说了。这个接口只包含一个类型的参数T，当你声明或者实例了一个参数来替代泛型，那么你就确定了参数类型。比如：

```java
interface Predicate<Person> {
    boolean test(Person t);
}
```

所以此时，你就可以使用Predicate<T>来取代CheckPerson了，像下面这样:

```java
public static void printPersonsWithPredicate(
    List<Person> roster, Predicate<Person> tester) {
    for (Person p : roster) {
        if (tester.test(p)) {
            p.printPerson();
        }
    }
}
```

所以，当你需要去调用这个方法的时候，你只需要像方法3那样就可以

```java
printPersonsWithPredicate(
    roster,
    p -> p.getGender() == Person.Sex.MALE
        && p.getAge() >= 18
        && p.getAge() <= 25
);
```

当然了，这也不是唯一的lambda表达式可以用到的地方。下面还有其他地方也可以用到。

##### 方法7：在你的应用外使用lambda表达式

重新考虑下printPersonWithPredicate,想想还有什么地方可以使用到lambda表达式：

```java
public static void printPersonsWithPredicate(
    List<Person> roster, Predicate<Person> tester) {
    for (Person p : roster) {
        if (tester.test(p)) {
            p.printPerson();
        }
    }
}
```

这个方法是用来在roster这个集合中筛选满足tester的test筛选条件的功能。如果满足，就调用person的打印自己方法。

如果你需要的并不是打印自己呢，你想让满足条件的person来执行特定的操作，这个时候，你可以使用lambda表达式来指定动作。假如你想一个动作和printPerson相似，同样是接收一个参数，同时不需要返回值的void。记住，使用到了lambda表达式，你都需要一个functional接口。在这里，你需要一个接收一个参数，但是没有返回值的接口。Consumer<T>接口包含了void accept(T t)接口，下面的例子就使用了Consumer<Person>来替代p.printPerson();

```java
public static void processPersons(
    List<Person> roster,
    Predicate<Person> tester,
    Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
}
```

实际上，同样是和方法3一样的调用方式：

```java
processPersons(
     roster,
     p -> p.getGender() == Person.Sex.MALE
         && p.getAge() >= 18
         && p.getAge() <= 25,
     p -> p.printPerson()
);
```

如果你想有更多的操作而不仅仅是将他们打印出来呢，假设你想确认他们的资料，或者想取回他们的联系方式，在这样的情况下，你需要一个functional接口包含一个抽象方法，但是返回一个值。这个时候Function<T,R>就包含了 R apply(T t)。如下实例，就是取出这些数据通过mapper，然后再进行blocker的accept的操作：

```java
public static void processPersonsWithFunction(
    List<Person> roster,
    Predicate<Person> tester,
    Function<Person, String> mapper,
    Consumer<String> block) {
    for (Person p : roster) {
        if (tester.test(p)) {
            String data = mapper.apply(p);
            block.accept(data);
        }
    }
}
```

下面的实现就是打印出符合条件人的邮箱地址：

```java
processElements(
    roster,
    p -> p.getGender() == Person.Sex.MALE
        && p.getAge() >= 18
        && p.getAge() <= 25,
    p -> p.getEmailAddress(),
    email -> System.out.println(email)
);
```

这个调用包含了下列的操作

1.从list中取出一个person的实例

2.筛选出符合条件筛选人tester的实例

3.对筛选出来的实例进行操作，在本例中就是取出对应的email地址

4.对第3步的操作结果进行操作，在本例中就是打印上一步得到的email地址

你也可以使用流的方式进行操作：

##### 方法9：使用流的方式进行操作

下面的例子使用了流的方式打印了email地址：

```java
roster
    .stream()
    .filter(
        p -> p.getGender() == Person.Sex.MALE
            && p.getAge() >= 18
            && p.getAge() <= 25)
    .map(p -> p.getEmailAddress())
    .forEach(email -> System.out.println(email));

```

下面的表格是将流的操作与之前的4步进行了一一对应。

| processEleme Action                      | Aggregate Operation                      |
| ---------------------------------------- | ---------------------------------------- |
| Obtain a source of objects               | `Stream<E> **stream**()`                 |
| Filter objects that match a `Predicate` object | `Stream<T> **filter**(Predicate<? super T> predicate)` |
| Map objects to another value as specified by a `Function` object | `<R> Stream<R> **map**(Function<? super T,? extends R> mapper)` |
| Perform an action as specified by a `Consumer` object | `void **forEach**(Consumer<? super T> action)` |

如filter，map和foreach这些操作都是集合的操作，集合操作是通过流而不是从集合（这就是为什么本例中第一个方法是stream），流是指一组顺序的元素。和集合不同，这个不是保存数据用的，而是流是从源中获取到的的值载体。集合的流操作是一个典型的可以接受lambda表达式的例子。