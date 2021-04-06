package lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestFim {
    @Test
    void testAllFrequent() {
        final List<List<String>> dataset =
            Lists.newArrayList(
                Lists.newArrayList("a", "b", "c"),
                Lists.newArrayList("a", "b", "c"),
                Lists.newArrayList("a", "b", "c")
            );
        assertEquals(Sets.newHashSet(new FIM().run(dataset, 3)), Sets.newHashSet(
            Pair.of(Lists.newArrayList("a"), 3),
            Pair.of(Lists.newArrayList("b"), 3),
            Pair.of(Lists.newArrayList("c"), 3),
            Pair.of(Lists.newArrayList("a", "b"), 3),
            Pair.of(Lists.newArrayList("a", "c"), 3),
            Pair.of(Lists.newArrayList("b", "c"), 3),
            Pair.of(Lists.newArrayList("a", "b", "c"), 3))
        );
    }

    @Test
    void testUnfrequent() {
        final List<List<String>> dataset =
            Lists.newArrayList(
                Lists.newArrayList("a", "b", "c"),
                Lists.newArrayList("a", "b", "c"),
                Lists.newArrayList("a", "d")
            );
        assertEquals(Sets.newHashSet(new FIM().run(dataset, 1)), Sets.newHashSet(
            Pair.of(Lists.newArrayList("a"), 3),
            Pair.of(Lists.newArrayList("b"), 2),
            Pair.of(Lists.newArrayList("c"), 2),
            Pair.of(Lists.newArrayList("a", "b"), 2),
            Pair.of(Lists.newArrayList("a", "c"), 2),
            Pair.of(Lists.newArrayList("b", "c"), 2),
            Pair.of(Lists.newArrayList("a", "b", "c"), 2),
            Pair.of(Lists.newArrayList("d"), 1),
            Pair.of(Lists.newArrayList("a", "d"), 1))
        );

        assertEquals(Sets.newHashSet(new FIM().run(dataset, 2)), Sets.newHashSet(
            Pair.of(Lists.newArrayList("a"), 3),
            Pair.of(Lists.newArrayList("b"), 2),
            Pair.of(Lists.newArrayList("c"), 2),
            Pair.of(Lists.newArrayList("a", "b"), 2),
            Pair.of(Lists.newArrayList("a", "c"), 2),
            Pair.of(Lists.newArrayList("b", "c"), 2),
            Pair.of(Lists.newArrayList("a", "b", "c"), 2))
        );
    }

    @Test
    void testDataset() {
        final List<List<String>> dataset = new Preprocess().unnest(ReadDataset.read());
        assertFalse(new FIM().run(dataset, 2).isEmpty());
    }
}