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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = 图中的点的列表（非空）
    // Representation invariant:
    //   vertices中的点不为空且不重复
    // Safety from rep exposure:
    //   设置vertices为private的
    //   由于vertices是mutable的，所以返回点集时进行了defensive copies
    
    // TODO constructor

    /**
     * 初始化构造
     */
    public ConcreteVerticesGraph() {
    }

    // TODO checkRep
    public void checkRep(){
        Set<Vertex<L>> test = new HashSet<>();
        test.addAll(vertices);
        assert test.size() == vertices.size();
    }
    
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
        Vertex v1 = new Vertex(vertex,new HashMap<>(),new HashMap<>());
        if(vertex != null)
        {
            for(Vertex v2:vertices)
            {
                if(v2.getMark().equals(vertex))
                    return false;
            }
            vertices.add(v1);
            checkRep();
            return true;
        }
        return false;
    }
    
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
        int re = 0;
        if(weight > 0) {
            this.add(source);
            this.add(target);
        }
        for(Vertex<L> v:vertices)
        {
            if(v.getMark().equals(source))
                re = v.addTarget(target,weight);
            if(v.getMark().equals(target))
                re =  v.addSource(source,weight);
        }
        checkRep();
        return re;
    }


    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
        for(Vertex<L> v:vertices)
        {
            if(v.getMark().equals(vertex))
            {
                vertices.remove(v);
                checkRep();
                return true;
            }
            else
            {
                if(v.getSource().containsKey(vertex))
                    v.removeSource(vertex);
                if(v.getTarget().containsKey(vertex))
                    v.removeTarget(vertex);
            }
        }
        checkRep();
        return false;
    }


    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
        Set<L> s = new HashSet<>();
        for(Vertex<L> v:vertices)
        {
            s.add((L) v.getMark());
        }
        return s;
    }


    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
        for(Vertex<L> v:vertices)
        {
            if(v.getMark().equals(target))
                return v.getSource();
        }
        return new HashMap<>();
    }
    
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
        for(Vertex<L> v:vertices)
        {
            if(v.getMark().equals(source))
                return v.getTarget();
        }
        return new HashMap<>();
    }
    
    // TODO toString()
    @Override public String toString() {
        StringBuilder re = new StringBuilder();
        for(Vertex<L> v:vertices)
        {
            re.append(v.toString());
        }
        return re.toString();
    }

}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {

    // TODO fields
    private final L mark; //顶点的名字
    private final Map<L,Integer> Source; //存储该顶点的所有source点及其边的权值的map
    private final Map<L,Integer> Target; //存储该顶点的所有target点及其边的权值的map

    // Abstraction function:
    //   AF(mark) = 点的名字
    //   AF(source) = 存储顶点的所有source点及其边的权值的map
    //   AF(target) = 存储顶点的所有target点及其边的权值的map
    // Representation invariant:
    //   每条边的权值应该大于0
    // Safety from rep exposure:
    //   将mark,source,target设置为private
    
    // TODO constructor

    /**
     * 构造器
     * @param mark
     * @param Source
     * @param Target
     */
    public Vertex(L mark, Map<L, Integer> Source, Map<L, Integer> Target) {
        this.mark = mark;
        this.Source = Source;
        this.Target = Target;
        checkRep();
    }

    // TODO checkRep

    /**
     * 检查图中是否有边权值小于0
     */
    public void checkRep(){
      Set<L> s1 = Source.keySet();
      Set<L> s2 = Target.keySet();
        for(L s:s1)
        {
            assert Source.get(s) > 0;
        }
        if (s2 == null) {
            return;
        }
        for(L s:s2)
        {
            assert Target.get(s) > 0;
        }
    }
    
    // TODO methods

    /**
     * 返回点的名字
     * @return 点的名字
     */
    public L getMark(){ return mark;}

    /**
     * 返回顶点的源点及其相连边的权值的map
     * @return 顶点的源点及其相连边的权值的map
     */
    public Map<L, Integer> getSource() {
        return new HashMap<L,Integer>(Source);
    }

    /**
     * 返回顶点的终点及其相连边的权值的map
     * @return 顶点的终点及其相连边的权值的map
     */
    public Map<L, Integer> getTarget() {
        return new HashMap<L,Integer>(Target);
    }

    /**
     * 若weight不为0，则将其加入source中(若源点已存在，则更新其weight并返回原weight，不存在则直接构建新点并返回0)
     * 若weight为0，则移除源点(不存在返回0，存在返回原weight)
     * @param source
     * @param weight
     * @return 若weight不为0，若源点已存在，则更新其weight并返回原weight，不存在则直接构建新点并返回0
     * 若weight为0，不存在返回0，存在返回原weight
     */
    public int addSource(L source,int weight){
        int re = 0;
        Set<L> s = Source.keySet();
        if(weight < 0)
            return -1;
        else if(weight == 0)
        {
            for(L v:s)
            {
                if(v.equals(source))
                {
                    re = Source.get(v);
                    Source.remove(v,Source.get(v));
                }
            }
        }
        else
        {
            for (L v : s)
            {
                if (v.equals(source))
                {
                    re = Source.get(v);
                    Source.remove(v, Source.get(v));
                    break;
                }
            }
            Source.put(source,weight);
        }
        checkRep();
        return re;
    }

    /**
     * 若weight不为0，则将其加入target中(若终点已存在，则更新其weight并返回原weight，不存在则直接构建新点并返回0)
     * 若weight为0，则移除终点(不存在返回0，存在返回原weight)
     * @param target
     * @param weight
     * @return 若weight不为0，若终点已存在，则更新其weight并返回原weight，不存在则直接构建新点并返回0
     * 若weight为0，不存在返回0，存在返回原weight
     */
    public int addTarget(L target,int weight){
        int re = 0;
        Set<L> s = Target.keySet();
        if(weight < 0)
            return -1;
        else if(weight == 0)
        {
            for(L v:s)
            {
                if(v.equals(target))
                {
                    re = Target.get(v);
                    Target.remove(v,Target.get(v));
                }
            }
        }
        else
        {
            for (L v : s)
            {
                if (v.equals(target))
                {
                    re = Target.get(v);
                    Target.remove(v, Target.get(v));
                    break;
                }
            }
            Target.put(target,weight);
        }
        checkRep();
        return re;
    }

    /**
     * 在源点表中删除一个源点
     * @param source
     * @return 若这个源点存在，则返回旧权值，否则返回0
     */
    public int removeSource(L source){
        Integer weight=Source.remove(source);
        if(weight==null) {
            return 0;
        }
        else {
            return weight;
        }
    }

    /**
     * 在终点表中删除一个终点
     * @param target
     * @return 若这个终点存在，则返回旧权值，否则返回0
     */
    public int removeTarget(L target){
        Integer weight=Target.remove(target);
        if(weight==null) {
            return 0;
        }
        else {
            return weight;
        }
    }
    // TODO toString()

    @Override
    public String toString() {
        return "Vertex{" +
                "mark=" + mark +
                ", Source=" + Source +
                ", Target=" + Target +
                '}';
    }
}
