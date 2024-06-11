package lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("checkstyle:Indentation")
public class BridgeWordsTest {
    private BridgeWordsFinder bridgeWordsFinder;

    @BeforeEach
    public void setUp() {
        DirectedGraph directedGraph = new DirectedGraph();
        String text = "space the final frontier these are the voyages of the starship enterprise";
        directedGraph.createGraphFromText(text);
        bridgeWordsFinder = new BridgeWordsFinder(directedGraph);
    }

    @Test
    public void testQueryBridgeWords1() {
        List<String> bridgeWords = bridgeWordsFinder.queryBridgeWords("space", "final");
        assertEquals(1, bridgeWords.size());
        assertTrue(bridgeWords.contains("the"));
    }

    @Test
    public void testQueryBridgeWords2() {
        List<String> bridgeWords = bridgeWordsFinder.queryBridgeWords("", "");
        assertTrue(bridgeWords.isEmpty());
    }

    @Test
    public void testQueryBridgeWords3() {
        List<String> bridgeWords = bridgeWordsFinder.queryBridgeWords("space", "");
        assertTrue(bridgeWords.isEmpty());
    }

    @Test
    public void testQueryBridgeWords4() {
        List<String> bridgeWords = bridgeWordsFinder.queryBridgeWords("space", "#a");
        assertTrue(bridgeWords.isEmpty());
    }

    @Test
    public void testQueryBridgeWords5() {
        List<String> bridgeWords = bridgeWordsFinder.queryBridgeWords("", "space");
        assertTrue(bridgeWords.isEmpty());
    }
    @Test
    void testNull() {
        ArrayList expected = new ArrayList();
        expected.add("the");
        String Word1 = "space";
        String Word2 = "final";
        assertEquals(expected,bridgeWordsFinder.queryBridgeWords(Word1,Word2));
    }
}
