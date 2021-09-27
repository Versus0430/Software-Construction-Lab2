/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import P1.graph.Graph;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    // Testing strategy
    //   使用多个样例文件对GraphPoet进行测试，并检测结果是否匹配

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // TODO tests

    /**
     * 通过测试空图和正常图来测试toString
     * @throws IOException
     */
    @Test
    public void testtoString() throws IOException {
        new P1.poet.Main().main(new String[0]);
        final GraphPoet n1 = new GraphPoet(new File("c:/github/HIT-Lab2-1190501809/test/P1/poet/empty.txt"));
        assertEquals("GraphPoet{graph=}",n1.toString());
        final GraphPoet n2 = new GraphPoet(new File("c:/github/HIT-Lab2-1190501809/test/P1/poet/oneline.txt"));
        assertEquals("GraphPoet{graph=Vertex{mark=this, Source={}, Target={is=1}}Vertex{mark=is, Source={this=1}, Target={a=1}}Vertex{mark=a, Source={is=1}, Target={test=1}}Vertex{mark=test, Source={a=1}, Target={of=1}}Vertex{mark=of, Source={test=1}, Target={the=1}}Vertex{mark=the, Source={of=1}, Target={mugar=1}}Vertex{mark=mugar, Source={the=1}, Target={omni=1}}Vertex{mark=omni, Source={mugar=1}, Target={theater=1}}Vertex{mark=theater, Source={omni=1}, Target={sound=1}}Vertex{mark=sound, Source={theater=1}, Target={system.=1}}Vertex{mark=system., Source={sound=1}, Target={}}}",n2.toString());
    }

    /**
     * Testing strategy
     * 通过含不同的权值的图来测试Poem
     * @throws IOException
     */
    @Test
    public void testPoem() throws IOException {
        final GraphPoet n1 = new GraphPoet(new File("c:/github/HIT-Lab2-1190501809/test/P1/poet/onePoet.txt"));
        final String input1 = "Test the system";
        assertEquals("Test of the system", n1.poem(input1));
        final GraphPoet n2 = new GraphPoet(new File("c:/github/HIT-Lab2-1190501809/test/P1/poet/multiPoet.txt"));
        final String input2 = "Test the system";
        assertEquals("Test a the system", n2.poem(input2));
    }

    /**
     * Testing strategy
     * 通过不同行数的字符串文件来测试GraphPoet
     * 行数：0，1，2
     * @throws IOException
     */
    @Test
    public void testGraphPoet() throws IOException{
        final GraphPoet n1 = new GraphPoet(new File("c:/github/HIT-Lab2-1190501809/test/P1/poet/empty.txt"));
        final String input = "Test the system.";
        assertEquals("Test the system.",n1.poem(input));
        final GraphPoet n2 = new GraphPoet(new File("test/P1/poet/oneline.txt"));
        final String input1 = "Test the system.";
        assertEquals("Test of the system.",n2.poem(input1));
        final GraphPoet n3 = new GraphPoet(new File("test/P1/poet/multiline.txt"));
        final String input2 = "Theater system am";
        assertEquals("Theater sound system i am",n3.poem(input2));
    }
}
