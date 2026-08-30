package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class DelimitedListTest {

    private final DelimitedList summarizer = new DelimitedList();

    @Test
    void collect_parsesCommaSeparatedNumbers() {
        Collection<Integer> result = summarizer.collect("1,3,6,7,8");
        assertEquals(5, result.size());
        assertTrue(result.containsAll(java.util.List.of(1, 3, 6, 7, 8)));
    }

    @Test
    void summarizeCollection_SampleMatch() {
        Collection<Integer> input = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String result = summarizer.summarizeCollection(input);
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", result);
    }

    @Test
    void summarizeCollection_handlesEmptyInput() {
        Collection<Integer> input = summarizer.collect("");
        assertEquals("", summarizer.summarizeCollection(input));
    }

    @Test
    void summarizeCollection_handlesSingleNumber() {
        Collection<Integer> input = summarizer.collect("5");
        assertEquals("5", summarizer.summarizeCollection(input));
    }

    @Test
    void summarizeCollection_handlesAllSequential() {
        Collection<Integer> input = summarizer.collect("1,2,3,4,5");
        assertEquals("1-5", summarizer.summarizeCollection(input));
    }

    @Test
    void summarizeCollection_handlesNoSequential() {
        Collection<Integer> input = summarizer.collect("1,3,5,7");
        assertEquals("1, 3, 5, 7", summarizer.summarizeCollection(input));
    }

    @Test
    void summarizeCollection_handlesDuplicates() {
        Collection<Integer> input = summarizer.collect("1,2,2,3,5,5");
        assertEquals("1-3, 5", summarizer.summarizeCollection(input));
    }
}