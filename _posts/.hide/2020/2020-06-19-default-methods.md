---
layout: post
title: 接口的默认实现(Default Methods)
categories: tech
---
#### 前言
本文是对java文档[Default Methods](https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)的翻译。旨在用理解官方设计api的方式和想法。

#### Default Methods
default methods允许你去添加新方法给接口或者你的公共库中，来给旧版本的代码进行兼容。  
让我们来思考一下如果有如下的接口 TimeClient：
```java
import java.time.*; 
 
public interface TimeClient {
    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year,
                               int hour, int minute, int second);
    LocalDateTime getLocalDateTime();
}
```
而下面的SimpleTimeClient，实现了这个接口：
```
package defaultmethods;

import java.time.*;
import java.lang.*;
import java.util.*;

public class SimpleTimeClient implements TimeClient {
    
    private LocalDateTime dateAndTime;
    
    public SimpleTimeClient() {
        dateAndTime = LocalDateTime.now();
    }
    
    public void setTime(int hour, int minute, int second) {
        LocalDate currentDate = LocalDate.from(dateAndTime);
        LocalTime timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(currentDate, timeToSet);
    }
    
    public void setDate(int day, int month, int year) {
        LocalDate dateToSet = LocalDate.of(day, month, year);
        LocalTime currentTime = LocalTime.from(dateAndTime);
        dateAndTime = LocalDateTime.of(dateToSet, currentTime);
    }
    
    public void setDateAndTime(int day, int month, int year,
                               int hour, int minute, int second) {
        LocalDate dateToSet = LocalDate.of(day, month, year);
        LocalTime timeToSet = LocalTime.of(hour, minute, second); 
        dateAndTime = LocalDateTime.of(dateToSet, timeToSet);
    }
    
    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }
    
    public String toString() {
        return dateAndTime.toString();
    }
    
    public static void main(String... args) {
        TimeClient myTimeClient = new SimpleTimeClient();
        System.out.println(myTimeClient.toString());
    }
}
```

我们来假设，如果你需要添加一个新的方法到TimeClient接口中，比如添加一个通过ZonedDateTime来改变的时区：
```java
public interface TimeClient {
    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year,
        int hour, int minute, int second);
    LocalDateTime getLocalDateTime();                           
    ZonedDateTime getZonedDateTime(String zoneString);
}
```

在我们添加完了这个方法之后，你需要修改SimpleTimeCLient来实现这个方法，但是，如果你可以用一个默认的实现，是不是比仅仅用抽象方法更好呢？
```java
package defaultmethods;
 
import java.time.*;

public interface TimeClient {
    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year,
                               int hour, int minute, int second);
    LocalDateTime getLocalDateTime();
    
    static ZoneId getZoneId (String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString +
                "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }
        
    default ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }
}
```

你用default关键字标记了在接口中的某个方法是一个默认实现。因为在接口中的所有方法都是public的，所以你可以省略public关键字。  
所以在现在的情况下，你就不需要再去修改SimpleTimeClient因为增加了新接口，而依然可以使用到新增加的接口。如下测试用例：
```java
package defaultmethods;
 
import java.time.*;
import java.lang.*;
import java.util.*;

public class TestSimpleTimeClient {
    public static void main(String... args) {
        TimeClient myTimeClient = new SimpleTimeClient();
        System.out.println("Current time: " + myTimeClient.toString());
        System.out.println("Time in California: " +
            myTimeClient.getZonedDateTime("Blah blah").toString());
    }
}
```

#### Exending Interfaces That Contain Default Methods
当你需要扩展一个有着默认方法的接口时，你可以按照下面的方式进行
1. 使用默认方法，不进行复写
2. 重新定义默认方法，但是这样会让该方法抽象
3. 重新定义默认方法，复写这个方法

假设你需要继承TimeClient接口
```java
public interface AnotherTimeClient extends TimeClient { }
```

另外一个实现了AntherTimeClient的同样也会有default的TimeClient.getZonedDateTime。  

如果你继承TimeClient，但是重新将其改成abstract
```java
public interface AbstractZoneTimeClient extends TimeClient {
    public ZonedDateTime getZonedDateTime(String zoneString);
}
```

那么其他的集成了AbstractZoneTimeClient的则必须自己去实现getZonedDateTime方法，这个方法就和普通的接口方法一样了。

那么，如果你继承了TimeClient，但是自己又重新写了default方法：
```java
public interface HandleInvalidTimeZoneClient extends TimeClient {
    default public ZonedDateTime getZonedDateTime(String zoneString) {
        try {
            return ZonedDateTime.of(getLocalDateTime(),ZoneId.of(zoneString)); 
        } catch (DateTimeException e) {
            System.err.println("Invalid zone ID: " + zoneString +
                "; using the default time zone instead.");
            return ZonedDateTime.of(getLocalDateTime(),ZoneId.systemDefault());
        }
    }
}
```
那么其他实现这个接口的，就不需要再自己实现，而是使用的这个新定义的方法。
#### Static Method
当然，除了default方法之外，你还是可以定义静态方法在接口中。这种方式能够更好的方便你来编码一些utils。你可以在接口定义对应的static方法，而不用在其他的类中定义。如下方的例子：
```java
public interface TimeClient {
    // ...
    static public ZoneId getZoneId (String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString +
                "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }

    default public ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }    
}
```
如上所示，你可以在接口里面添加static的方法。而可以直接省略public标识。
#### Integrating Default Methods into Existing Libraries
我们知道，default的方式可以让你在现存的接口中添加新的接口还能保证使用该接口的老版本的兼容。同时，更厉害的是，default方法还能接受lambda表达式作为参数.这一节，我们将延时comparator是如何通过static和default方法来增强的。  
假设我们有如下两个接口类，card和deck
```java
package defaultmethods;

public interface Card extends Comparable<Card> {
    
    public enum Suit { 
        DIAMONDS (1, "Diamonds"), 
        CLUBS    (2, "Clubs"   ), 
        HEARTS   (3, "Hearts"  ), 
        SPADES   (4, "Spades"  );
        
        private final int value;
        private final String text;
        Suit(int value, String text) {
            this.value = value;
            this.text = text;
        }
        public int value() {return value;}
        public String text() {return text;}
    }
    
    public enum Rank { 
        DEUCE  (2 , "Two"  ),
        THREE  (3 , "Three"), 
        FOUR   (4 , "Four" ), 
        FIVE   (5 , "Five" ), 
        SIX    (6 , "Six"  ), 
        SEVEN  (7 , "Seven"),
        EIGHT  (8 , "Eight"), 
        NINE   (9 , "Nine" ), 
        TEN    (10, "Ten"  ), 
        JACK   (11, "Jack" ),
        QUEEN  (12, "Queen"), 
        KING   (13, "King" ),
        ACE    (14, "Ace"  );
        private final int value;
        private final String text;
        Rank(int value, String text) {
            this.value = value;
            this.text = text;
        }
        public int value() {return value;}
        public String text() {return text;}
    }
    
    public Card.Suit getSuit();
    public Card.Rank getRank();
}
```

而deck接口是如下：
```java
package defaultmethods; 
 
import java.util.*;
import java.util.stream.*;
import java.lang.*;
 
public interface Deck {
    
    List<Card> getCards();
    Deck deckFactory();
    int size();
    void addCard(Card card);
    void addCards(List<Card> cards);
    void addDeck(Deck deck);
    void shuffle();
    void sort();
    void sort(Comparator<Card> c);
    String deckToString();

    Map<Integer, Deck> deal(int players, int numberOfCards)
        throws IllegalArgumentException;

}
```
PlayingCard类实现了card接口，而StandardDeck实现了deck接口.
```java
public class StandardDeck implements Deck {
    
    private List<Card> entireDeck;
    
    // ...
    
    public void sort() {
        Collections.sort(entireDeck);
    }
    
    // ...
}
```

Collections中有sort的工具方法来对list的数据类型进行排序。StandardCeck中的entireDeck中的card就是实现了comparable接口的类。所以我们可以进行排序。而另外的类中PlayingCard实现了compareTo方法。
```java
public int hashCode() {
    return ((suit.value()-1)*13)+rank.value();
}

public int compareTo(Card o) {
    return this.hashCode() - o.hashCode();
}
```
这样，compareTo就能让StandardDeck的sort方法，先按照花色排序，然后按照大小排序。  
但是如果你想先按照大小排序，再按照花色进行排序呢？首先需要创建一个新的排序方式，也就是实现comparator接口，然后将StandardDeck的sort方法进行补充，来接收一个比较器：
```java
public void sort(Comparator<Card> c) {
    Collections.sort(entireDeck, c);
} 
```

那么这样，你就可以将你新建的比较器传入进行比较了，比较器代码如下:
```java
package defaultmethods;

import java.util.*;
import java.util.stream.*;
import java.lang.*;

public class SortByRankThenSuit implements Comparator<Card> {
    public int compare(Card firstCard, Card secondCard) {
        int compVal =
            firstCard.getRank().value() - secondCard.getRank().value();
        if (compVal != 0)
            return compVal;
        else
            return firstCard.getSuit().value() - secondCard.getSuit().value(); 
    }
}
```

我们就可以调用实现，来进行排序了：
```java
StandardDeck myDeck = new StandardDeck();
myDeck.shuffle();
myDeck.sort(new SortByRankThenSuit());
```

但是，这个方式也太冗长了，如果你能确定你是想排序什么，而不是怎么排序那样会更好。如果你是开发者，你应该怎么添加default和静态方法，这样其他开发者能更容易的进行自己想要的方式排序呢？  
首先，假设你现在只想按照大小排序，而不管花色。你可以这样写：
```java
StandardDeck myDeck = new StandardDeck();
myDeck.shuffle();
myDeck.sort(
    (firstCard, secondCard) ->
        firstCard.getRank().value() - secondCard.getRank().value()
); 
```

当然了，我们采用了lambda表达式的方式进行了简化，这是一个匿名内部类。  
这个时候我们发现，如果我们可以直接获取到getrank这个方法，那么就可以简化很多：
```java
myDeck.sort(Comparator.comparing((card) -> card.getRank()));  
```

更进一步，你甚至可以使用方法引用来进行简化
```java
myDeck.sort(Comparator.comparing(Card::getRank));  
```

以上就是演示了，我们要对什么排序：rank，而不是怎么排序的方式。  
接着，如果你的开发，想要先进行大小排序，然后再进行花色的排序呢？回到开始的地方，你可以使用lambda表达式来创建一个匿名内部类，实现排序方法。
```java
StandardDeck myDeck = new StandardDeck();
myDeck.shuffle();
myDeck.sort(
    (firstCard, secondCard) -> {
        int compare =
            firstCard.getRank().value() - secondCard.getRank().value();
        if (compare != 0)
            return compare;
        else
            return firstCard.getSuit().value() - secondCard.getSuit().value();
    }      
); 
```

这个方式自然也不够优雅，我们可以采用default方法来进行增强：
```java
myDeck.sort(
    Comparator
        .comparing(Card::getRank)
        .thenComparing(Comparator.comparing(Card::getSuit)));
```

因为comparator本身接口的default方法已经可以实现这样的调用方式，所以我们就可以直接使用。  
那么，又来，如果你的开发有想要一个反向排序呢？比如首先按大小排序，进行翻转，再进行花色排序呢？
```java
myDeck.sort(
    Comparator.comparing(Card::getRank)
        .reversed()
        .thenComparing(Comparator.comparing(Card::getSuit)));
```

那么很明显，你依旧可以使用comparator的default方法reversed。  
#### 结语
通过以上的例子，我们可以理解接口的default方法，default方法的扩展，以及一个例子，来演示default方法是如何帮我们来简化我们的代码的。
