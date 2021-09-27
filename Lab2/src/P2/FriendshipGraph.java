package P2;

import P1.graph.ConcreteEdgesGraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FriendshipGraph {

    private final ConcreteEdgesGraph<Person> graph;

    // Abstraction function:
    //  AF(graph) = 社交网络图
    // Representation invariant:
    //  graph不为空
    // Safety from rep exposure:
    //  graph设置为private
    //  因为graph是immutable的，所以不需要defensive copies

    /**
     * 初始化构造
     */
    public FriendshipGraph() {
        graph = new ConcreteEdgesGraph<>();
    }


    /**
     * 添加新的顶点
     * @param newone
     */
    public void addVertex(Person newone)
    {
        for(Person p:graph.vertices())
        {
            if(p.getName() == newone.getName()) {
                System.out.println("error:有重复对象！\n");
            }
        }
        graph.add(newone);
    }

    /**
     * 返回graph
     */
    public ConcreteEdgesGraph<Person> getGraph(){
        return graph;
    }

    /**
     * 添加边
     * @param p1
     * @param p2
     */
    public void addEdge(Person p1, Person p2)
    {
        graph.set(p1,p2,1);
    }

    /**
     * 基于ConcreteEdgesGraph的rep，使用广度优先求最短路径
     * @param p1
     * @param p2
     * @return 两个对象p1、p2的距离（正整数\0\-1）
     */
    public int getDistance(Person p1, Person p2)
    {
        int distance=0;
        Queue<Person> q1 = new LinkedList<>();
        Set<Person> set1 = new HashSet<>();
        if(p1 == p2)
            return distance;
        distance++;
        q1.offer(p1);
        set1.add(p1);
        Person head=null;
        while(!q1.isEmpty())
        {
            head = q1.poll(); //head等于q1删除的队首
            Set<Person> set2 = graph.targets(head).keySet();
            if(set2.contains(p2))
                return distance;
            else
            {
                for(Person p: set2)
                    if (!set1.contains(p)) {
                        q1.offer(p);
                        set1.add(p);
                    }
                distance++;
            }
        }
        return -1;
    }

   public void main(String[] args) {
       System.out.println("实验样例:");
       FriendshipGraph graph = new FriendshipGraph();
       Person rachel = new Person("Rachel");
       Person ross = new Person("Ross");
       Person ben = new Person("Ben");
       Person kramer = new Person("Kramer");
       graph.addVertex(rachel);
       graph.addVertex(ross);
       graph.addVertex(ben);
       graph.addVertex(kramer);
       graph.addEdge(rachel, ross);
       graph.addEdge(ross, rachel);
       graph.addEdge(ross, ben);
       graph.addEdge(ben, ross);
       System.out.println("(rachel,ross)之间的距离：" + graph.getDistance(rachel, ross)); //should print 1
       System.out.println("(rachel,ben)之间的距离:" + graph.getDistance(rachel, ben));//should print 2
       System.out.println("(rachel,rachel)之间的距离:" + graph.getDistance(rachel, rachel));//should print 0
       System.out.println("(rachel,kramer)之间的距离:" + graph.getDistance(rachel, kramer));//should print -1

       System.out.println();
       System.out.println("若rachel与ross只存在单向关系ross->rachel：");
       graph = new FriendshipGraph();
       rachel = new Person("Rachel");
       ross = new Person("Ross");
       ben = new Person("Ben");
       kramer = new Person("Kramer");
       graph.addVertex(rachel);
       graph.addVertex(ross);
       graph.addVertex(ben);
       graph.addVertex(kramer);
//        graph.addEdge(rachel, ross);
       graph.addEdge(ross, rachel);
       graph.addEdge(ross, ben);
       graph.addEdge(ben, ross);
       System.out.println("(rachel,ross)之间的距离：" + graph.getDistance(rachel, ross)); //should print 1
       System.out.println("(rachel,ben)之间的距离:" + graph.getDistance(rachel, ben));//should print 2
       System.out.println("(rachel,rachel)之间的距离:" + graph.getDistance(rachel, rachel));//should print 0
       System.out.println("(rachel,kramer)之间的距离:" + graph.getDistance(rachel, kramer));//should print -1

       System.out.println();
       System.out.println("若将ross替换为rachel:");
       graph.addVertex(new Person("Rachel"));
       }
}

