/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //测试三个点的toString结果是否匹配

    @Test
    public void testtoString()
    {
        Graph<String> p = emptyInstance();
        p.set("a","b",3);
        p.set("b","c",2);
        p.set("c","a",1);
        assertEquals("a->b:3"+"b->c:2"+"c->a:1",p.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    /**
     * Testing strategy
     * 测试source点的返回值是否匹配
     */
    @Test
    public void testgetSource()
    {
        Edge e = new Edge("a","b",1);
        assertEquals("a",e.getSource());
    }

    /**
     * Testing strategy
     * 测试target点的返回值是否匹配
     */
    @Test
    public void testgetTarget()
    {
        Edge e = new Edge("a", "b", 1);
        assertEquals("b",e.getTarget());
    }

    /**
     * Testing strategy
     * 测试边的weight返回值是否匹配
     */
    @Test
    public void testgetWeight()
    {
        Edge e = new Edge("a", "b", 1);
        assertEquals(1,e.getWeight());
    }
    // TODO tests for operations of Edge
    
}
