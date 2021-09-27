package P2;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private final String Name;

    // Abstraction function:
    //  AF(Name) = 该Person对象的名字
    // Representation invariant:
    //  无重复名字
    // Safety from rep exposure:
    //  Name设置为private
    //  因为Name是immutable的，所以不需要defensive copies

    /**
     * 给Person类的变量Name赋值
     *
     * @param name
     */
    public Person(String name) {
        Name = name;
    }

    /**
     * 返回Person对象的名字
     * @return Person对象的名字
     */
    public String getName(){
        return Name;
    }

}