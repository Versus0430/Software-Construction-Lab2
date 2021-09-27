/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    //   TODO

    /**
     * Overridden by implementation-specific test classes.
     *
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    // TODO other tests for instance methods of Graph

    /**
     * Testing strategy
     * 根据加入的点的类型进行划分：加入的点图中不存在，加入的点图中存在
     * 覆盖每个取指为：
     */
    @Test
    public void testAdd() {
        //testVertices();
        Graph<String> graph = emptyInstance();
        String a = "a";
        assertEquals(true,graph.add(a));
        assertEquals(false,graph.add(a));
    }

    /**
     * Testing strategy
     * 根据加入的边的类型进行划分：加入的边图中不存在，加入的边图中存在
     * 根据加入的边的权值进行划分：加入的边的权值为0，加入的边的权值大于0，加入的边的权值小于0
     * 覆盖每个取指为：
     */
    @Test
    public void testSet() {
        Graph<String> graph =emptyInstance();
        assertEquals(0,graph.set("a", "b", 5));
        assertEquals(0,graph.set("b", "c", 4));
        assertEquals(5,graph.set("a", "b", 6));
        assertEquals(6,graph.set("a", "b", 0));
        assertEquals(0,graph.set("a", "c", 0));
        assertEquals(-1,graph.set("a", "b", -3));
    }

    /**
     * Testing strategy
     * 根据给定的点的类型进行划分：给定的点存在，给定的点不存在
     * 覆盖每个取指为：
     */
    @Test
    public void testRemove() {
        //testAdd();
        Graph<String> graph = emptyInstance();
        String s1 = "a", s2 = "b";
        graph.add(s1);
        assertTrue(graph.remove(s1));
        assertFalse(graph.remove(s2));
    }

    /**
     * Testing strategy
     * 根据图的类型进行划分：空图，非空图
     * 覆盖每个取指为：
     */
    @Test
    public void testVertices() {
        //testAdd();
        Graph<String> g1 = emptyInstance();
        Graph<String> g2 = emptyInstance();
        Set<String> set =new HashSet<String>();
        String s1 = "a", s2 = "b";
        g2.add(s1);
        set.add(s1);
        assertEquals(new HashSet<String>(), g1.vertices());
        assertEquals(set,g2.vertices());
        g2.add(s2);
        assertNotEquals(set,g2.vertices());
        set.add(s2);
        assertEquals(set,g2.vertices());
    }

    /**
     * Testing strategy
     * 根据边的类型进行划分：有边，无边
     * 覆盖每个取指为：
     */
    @Test
    public void testSources() {
        //testAdd();
        //testSet();
        Graph<String> graph = emptyInstance();
        Map<String,Integer> m1 = new HashMap<String,Integer>();
        graph.add("a");
        assertEquals(m1,graph.sources("a")); //无边
        graph.set("a","b",1);
        graph.set("c","b",2);
        m1.put("a",1);
        m1.put("c",2);
        assertEquals(m1,graph.sources("b"));
    }

    /**
     * Testing strategy
     * 根据边的类型进行划分：有边，无边
     * 覆盖每个取指为：
     */
    @Test
    public void testTargets() {
        //testAdd();
        //testSet();
        Graph<String> graph = emptyInstance();
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        graph.add("a");
        assertEquals(m1, graph.targets("a")); //无边
        graph.set("b", "a", 1);
        graph.set("b", "c", 2);
        m1.put("a", 1);
        m1.put("c", 2);
        assertEquals(m1, graph.targets("b"));
    }
}
