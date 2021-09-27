package P2;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendshipGraphTest {

    /**
     *
     * 测试方法：创建四个对象，执行addVertex，并检查people中是否包含这四个对象
     *
     */
    @Test
    public void addVertextest() {
        FriendshipGraph graph = new FriendshipGraph();
        Person p1 = new Person("Xiong");
        Person p2 = new Person("Yang");
        Person p3 = new Person("Huang");
        Person p4 = new Person("Zhang");
        graph.addVertex(p1);
        graph.addVertex(p2);
        graph.addVertex(p3);
        graph.addVertex(p4);
        assertTrue(graph.getGraph().vertices().contains(p1));
        assertTrue(graph.getGraph().vertices().contains(p2));
        assertTrue(graph.getGraph().vertices().contains(p3));
        assertTrue(graph.getGraph().vertices().contains(p4));
    }

    /**
     *
     * 测试方法：创建四个对象，然后依次执行addVertex、addEdge(任取两组）
     * 最后检查每个对象的friend中是否包含彼此。
     */
    @Test
    public void addEdgetest() {
        FriendshipGraph graph = new FriendshipGraph();
        Person p1 = new Person("Xiong");
        Person p2 = new Person("Yang");
        Person p3 = new Person("Huang");
        Person p4 = new Person("Zhang");
        graph.addVertex(p1);
        graph.addVertex(p2);
        graph.addVertex(p3);
        graph.addVertex(p4);
        graph.addEdge(p1,p2);
        graph.addEdge(p2,p1);
        graph.addEdge(p2,p3);
        graph.addEdge(p3,p2);
        assertTrue(graph.getGraph().targets(p1).keySet().contains(p2));
        assertTrue(graph.getGraph().targets(p2).keySet().contains(p1) && graph.getGraph().targets(p2).keySet().contains(p3));
        assertTrue(graph.getGraph().targets(p3).keySet().contains(p2));
    }

    /**
     *
     * 测试方法：建立四个Person对象，执行addVertex、addEdge后，保证三种距离类都存在（-1、0、正整数）。
     *
     */
    @Test
    public void getDistancetest() {
        FriendshipGraph graph = new FriendshipGraph();
        Person p1 = new Person("Xiong");
        Person p2 = new Person("Yang");
        Person p3 = new Person("Huang");
        Person p4 = new Person("Zhang");
        graph.addVertex(p1);
        graph.addVertex(p2);
        graph.addVertex(p3);
        graph.addVertex(p4);
        graph.addEdge(p1,p2);
        graph.addEdge(p2,p1);
        graph.addEdge(p2,p3);
        graph.addEdge(p3,p2);
        assertTrue(graph.getDistance(p1,p3) == 2); //正整数
        assertTrue(graph.getDistance(p1,p1) == 0); //0
        assertTrue(graph.getDistance(p1,p4) == -1); //-1
        graph.main(new String[0]);
    }
}