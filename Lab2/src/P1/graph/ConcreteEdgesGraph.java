/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //  AF(vertices) = 图中的点的集合
    //  AF(edges) = 图中的边的列表
    // Representation invariant:
    //  vetices中包含所有vertex点
    //  edges起始的点不为空，且权值大于0
    // Safety from rep exposure:
    //  将类型设置为private
    //  由于vertices和edges使Mutable的，所以在返回它们时进行了defensive copies
    
    // TODO constructor

    /**
     * 初始化构造，初始化图中的点集和边集
     */
    public ConcreteEdgesGraph() {
    }

    // TODO checkRep

    /**
     * 检查rep invariant是否为true
     */
    public void checkRep(){
        for(Edge<L> e : edges)
        {
            assert e.getSource() != null;
            assert vertices.contains(e.getSource());
            assert e.getTarget() != null;
            assert vertices.contains(e.getTarget());
            assert  e.getWeight() > 0;
        }
    }

    /**
     * 在图中添加顶点
     * @param vertex label for the new vertex
     * @return
     */
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
        if(vertex != null && !vertices.contains(vertex)) {  //顶点不为空且顶点表中没有这个点
            vertices.add(vertex);
            checkRep();
            return true;
        }
        return false;
    }

    /**
     * 输入source，target, weight
     * 若weight为负，则返回-1
     * 若weight为0且新边已经存在，则删去原边
     * 若weight为正整数，且新边不存在，则直接添加新边
     * 只要改变了原边的权值，都返回原边的权值，没有权值则返回0
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight nonnegative weight of the edge
     * @return
     */
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
        int re = 0;
        Edge<L> e = new Edge<>(source,target,weight);
        if(weight < 0 )
            return -1;
        else if(weight == 0)
        {
            for(Edge<L> e2:edges)
            {
                if(e2.getSource().equals(source) && e2.getTarget().equals(target)){
                    edges.remove(e2);
                    return e2.getWeight();
                }
            }
        }
        else
        {
            for(Edge<L> e3:edges)
            {
                if (e3.getSource().equals(source) && e3.getTarget().equals(target))
                {
                    re = e3.getWeight();
                    edges.remove(e3);
                    break;
                }
            }
            vertices.add(e.getSource());
            vertices.add(e.getTarget());
            edges.add(e);
        }
        checkRep();
        return re;
    }

    /**
     * 删除某个点以及与它相邻的所有边
     * @param vertex label of the vertex to remove
     * @return
     */
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
        if(vertices.contains(vertex))
        {
            vertices.remove(vertex);
            edges.removeIf(e -> e.getSource() == vertex || e.getTarget() == vertex);
        }
        else
            return false;
        checkRep();
        return true;
    }

    /**
     * 返回所有点集
     * @return
     */
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
        return new HashSet<>(vertices);
    }

    /**
     * 给定终点，返回所有与它相连的边的起点与权值
     * @param target a label
     * @return
     */
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
        Map<L,Integer> map = new HashMap<>();
        for(Edge<L> e:edges)
        {
            if(e.getTarget()==target)
            {
                map.put(e.getSource(),e.getWeight());
            }
        }
        checkRep();
        return map;
    }

    /**
     * 给定起点，返回所有与它相连的边的终点和权值
     * @param source a label
     * @return
     */
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
        Map<L,Integer> map = new HashMap<>();
        for(Edge<L> e:edges)
        {
            if(e.getSource()==source)
            {
                map.put(e.getTarget(),e.getWeight());
            }
        }
        checkRep();
        return map;
    }
    
    // TODO toString()


    @Override public String toString()
    {
        String gra = "";
        Iterator<Edge<L>> i = edges.iterator();
        while(i.hasNext())
        {
            Edge<L> e = i.next();
            gra = gra + e.toString();
        }
        checkRep();
        return gra;
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    private final L source;
    private final L target;
    private final int weight;
    
    // Abstraction function:
    //      AF(source) = 边的源点
    //      AF(target) = 边的终点
    //      AF(weight) = 边的权值
    // Representation invariant:
    //      weight >= 0
    //      source != null && target != null
    // Safety from rep exposure:
    //      source, target, weight设置为private的
    
    // TODO constructor

    /**
     * 初始化构造，初始化新的边的两个点与边的权值
     * @param source 新边的源点
     * @param target 新边的终点
     * @param weight 新边的权值
     */
    public Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        //checkRep();
    }


    // TODO checkRep

    /**
     * 检查rep invariant是否为true
     */
    public void checkRep(){
        assert  weight >= 0;
        assert source != null;
        assert target != null;
    }
    
    // TODO methods


    /**
     *
     * @return 边的源点
     */
     public L getSource() {
         checkRep();
         return source;
    }

    /**
     *
     * @return 边的终点
     */
    public L getTarget() {
        checkRep();
        return target;
    }

    /**
     *
     * @return 边的权值
     */
    public int getWeight() {
        checkRep();
        return weight;
    }

    // TODO toString()
    @Override
    public String toString() {
        return source + "->" + target + ":" + weight;
    }






}
