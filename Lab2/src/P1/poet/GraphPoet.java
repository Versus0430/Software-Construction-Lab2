/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

import P1.graph.Graph;
import sun.security.provider.certpath.Vertex;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   AF(graph) = 由输入的字符串构成的顶点、边的图
    // Representation invariant:
    //   graph中无重复顶点与空顶点
    //   graph的边权值大于0
    // Safety from rep exposure:
    //   将graph设置为private类型
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        //throw new RuntimeException("not implemented");
        String line,temp;
        temp = null;
        int i;
        int l=0;
        try(BufferedReader in = new BufferedReader(new FileReader(corpus))) {
           while((line = in.readLine()) != null)
           {
               String[] s = line.trim().split(" ");
               l = s.length;
               if(temp != null)
                   graph.set(temp,s[0].toLowerCase(),1);
               for(i=0; i<l-1; i++)
               {
                   graph.set(s[i].toLowerCase(),s[i+1].toLowerCase(),1);
               }
               temp = s[i].toLowerCase();
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkRep();
    }
    
    // TODO checkRep
    public void checkRep(){
        assert graph != null;
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        //throw new RuntimeException("not implemented");
        String[] s1 = new String[100];
        String[] s2 = new String[100];
        String res = "";
        int i,j,l;
        j=0;
        s1 = input.trim().split(" ");
        l = s1.length;
        for(i=0; i<l; i++)
        {
            s2[j++] = s1[i];
            if(i<l-1)
            {
                for(String v:graph.vertices())
                    if (v.equals(s1[i].toLowerCase()))
                    {
                        if(!graph.targets(v).containsKey(s1[i+1].toLowerCase()))
                        {
                            for(String m:graph.targets(v).keySet())
                            {
                                if(graph.targets(m).containsKey(s1[i+1].toLowerCase()))
                                {
                                    s2[j++] = m;
                                    break;
                                }
                            }
                        }
                        break;
                    }
            }
        }
        l = s2.length;
        res += s2[0];
        for(j=1; j<l; j++) {
            if (s2[j] == null) break;
            res += " " + s2[j];
        }
        return res;
    }
    
    // TODO toString()


    @Override
    public String toString() {
        return "GraphPoet{" +
                "graph=" + graph +
                '}';
    }
}
