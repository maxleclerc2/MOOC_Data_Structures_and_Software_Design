package week2.graphs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MinDistanceTest {
    public static final String FILENAME = "graph_builder_test.txt";

    public MinDistanceTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testNullGraph() {
        try {
            int var1 = GraphUtils.minDistance((Graph)null, "src", "dest");
            Assert.assertTrue("minDistance should return -1 when input Graph is null", var1 == -1);
        } catch (Exception var2) {
            Assert.fail("minDistance throws " + var2 + " when input Graph is null");
        }

    }

    @Test
    public void testNullSrc() {
        UndirectedGraph var1 = new UndirectedGraph();

        try {
            int var2 = GraphUtils.minDistance(var1, (String)null, "dest");
            Assert.assertTrue("minDistance should return -1 when input src is null", var2 == -1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when input src is null");
        }

    }

    @Test
    public void testNullDest() {
        UndirectedGraph var1 = new UndirectedGraph();

        try {
            int var2 = GraphUtils.minDistance(var1, "src", (String)null);
            Assert.assertTrue("minDistance should return -1 when input dest is null", var2 == -1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when input dest is null");
        }

    }

    @Test
    public void testSrcNotInGraph() {
        UndirectedGraph var1 = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "BAD", "3");
            Assert.assertTrue("minDistance should return -1 when input src is not in graph", var2 == -1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when input src is not in graph");
        }

    }

    @Test
    public void testDestNotInGraph() {
        UndirectedGraph var1 = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "2", "BAD");
            Assert.assertTrue("minDistance should return -1 when input dest is not in graph", var2 == -1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when input dest is not in graph");
        }

    }

    @Test
    public void testSrcDestSame() {
        UndirectedGraph var1 = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "0", "0");
            Assert.assertTrue("minDistance should return 0 when src and dest are the same", var2 == 0);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when src and dest are the same");
        }

    }

    @Test
    public void testSrcDestConnectedUndirected() {
        UndirectedGraph var1 = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "1", "2");
            Assert.assertTrue("minDistance should return 1 when src and dest are connected by a single edge in an undirected graph", var2 == 1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when src and dest are connected by a single edge in an undirected graph");
        }

    }

    @Test
    public void testSrcDestConnectedDirected() {
        DirectedGraph var1 = GraphBuilder.buildDirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "1", "2");
            Assert.assertTrue("minDistance should return 1 when src and dest are connected by a single edge in a directed graph", var2 == 1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when src and dest are connected by a single edge in a directed graph");
        }

    }

    @Test
    public void testSrcDestMultipleEdgesUndirected() {
        UndirectedGraph var1 = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "0", "6");
            Assert.assertTrue("minDistance returns incorrect output when src and dest are connected via multiple edges in an undirected graph", var2 == 3);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when src and dest are connected via multiple edges in an undirected graph");
        }

    }

    @Test
    public void testSrcDestMultipleEdgesDirected() {
        DirectedGraph var1 = GraphBuilder.buildDirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "0", "6");
            Assert.assertTrue("minDistance returns incorrect output when src and dest are connected via multiple edges in a directed graph", var2 == 3);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when src and dest are connected via multiple edges in a directed graph");
        }

    }

    @Test
    public void testSrcDestNotConnectedUndirected() {
        UndirectedGraph var1 = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "0", "8");
            Assert.assertTrue("minDistance returns incorrect output when src and dest are not connected in an undirected graph", var2 == -1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when src and dest are not connected in an undirected graph");
        }

    }

    @Test
    public void testSrcDestNotConnectedDirected() {
        DirectedGraph var1 = GraphBuilder.buildDirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "0", "8");
            Assert.assertTrue("minDistance returns incorrect output when src and dest are not connected in a directed graph", var2 == -1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when src and dest are not connected in a directed graph");
        }

    }

    @Test
    public void testSrcDestNotConnectedDirectedButConnectedUndirected() {
        DirectedGraph var1 = GraphBuilder.buildDirectedGraph("graph_builder_test.txt");
        if (var1 == null) {
            Assert.fail("Could not run test: unable to build graph from file graph_builder_test.txt");
        }

        try {
            int var2 = GraphUtils.minDistance(var1, "1", "3");
            Assert.assertTrue("minDistance returns incorrect output when src and dest are not connected in a directed graph but are connected in underlying undirected graph", var2 == -1);
        } catch (Exception var3) {
            Assert.fail("minDistance throws " + var3 + " when src and dest are not connected in a directed graph but are connected in underlying undirected graph");
        }

    }
}
