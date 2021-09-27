/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   测试三个点的toString结果是否匹配
    
    // TODO tests for ConcreteVerticesGraph.toString()

    @Test
    public void testtoString() {
       Graph<String> p = emptyInstance();
       p.add("a");
       p.add("b");
       p.add("c");
       p.set("a","b",3);
       p.set("b","c",2);
       p.set("c","a",1);
       assertEquals("Vertex{mark=a, Source={c=1}, Target={b=3}}Vertex{mark=b, Source={a=3}, Target={c=2}}" +
               "Vertex{mark=c, Source={b=2}, Target={a=1}}",p.toString());
    }


    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   测试Vertex中的每一个方法
    
    // TODO tests for operations of Vertex

    /**
     * Testing strategy
     * 测试点的返回值
     */
    @Test
    public void testgetMark() {
        Map<String,Integer> m1 = new HashMap<String,Integer>();
        Map<String,Integer> m2 = new HashMap<String,Integer>();
        Vertex v =new Vertex("v",m1,m2);
        assertEquals("v",v.getMark());
    }

    /**
     * Testing strategy
     * 测试该点的所有源点与边权值
     */
    @Test
    public void testgetSource() {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        m1.put("a", 1);
        m1.put("b", 2);
        Vertex v = new Vertex("v", m1, m2);
        assertEquals(m1, v.getSource());
    }

    /**
     * Testing strategy
     * 测试该点的所有终点与边权值
     */
    @Test
    public void testgetTarget() {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        m2.put("a",1);
        m2.put("b",2);
        Vertex v = new Vertex("v", m1, m2);
        assertEquals(m2,v.getTarget());
    }

    /**
     * Testing strategy
     * 按加入的点进行划分：点已经存在，不存在
     * 按加入的点的权值划分：权值大于0，等于0，小于0
     */
    @Test
    public void testaddSource() {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        Vertex v = new Vertex("v", m1, m2);
        assertEquals(0,v.addSource("a",1)); //权值大于0，点不存在
        assertEquals(0,v.addSource("b",0)); //权值等于0，点不存在
        assertEquals(-1,v.addSource("c",-1)); //权值小于0
        assertEquals(1,v.addSource("a",2)); //权值大于0，点存在
        assertEquals(2,v.addSource("a",0)); //权值等于0，点存在
    }

    /**
     * Testing strategy
     * 按加入的点进行划分：点已经存在，不存在
     * 按加入的点的权值划分：权值大于0，等于0，小于0
     */
    @Test
    public void testaddTarget() {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        Vertex v = new Vertex("v", m1, m2);
        assertEquals(0,v.addTarget("a",1)); //权值大于0，点不存在
        assertEquals(0,v.addTarget("b",0)); //权值等于0，点不存在
        assertEquals(-1,v.addTarget("c",-1)); //权值小于0
        assertEquals(1,v.addTarget("a",2)); //权值大于0，点存在
        assertEquals(2,v.addTarget("a",0)); //权值等于0，点存在
    }

    /**
     * Testing strategy
     * 根据删除的某源点进行划分：该源点已存在，不存在
     */
    @Test
    public void testremoveSource() {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        Vertex v = new Vertex("v", m1, m2);
        m1.put("a",1);
        assertEquals(1,v.removeSource("a")); //源点已存在
        assertEquals(0,v.removeSource("b")); //源点不存在
    }

    /**
     * Testing strategy
     * 根据删除的某终点进行划分：该终点已存在，不存在
     */
    @Test
    public void testremoveTarget() {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        Vertex v = new Vertex("v", m1, m2);
        m2.put("a",1);
        assertEquals(1,v.removeTarget("a")); //终点已存在
        assertEquals(0,v.removeTarget("b")); //终点不存在
    }
}
