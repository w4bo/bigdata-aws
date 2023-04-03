package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static lambda.Utils.STATUS_200;
import static lambda.Utils.invokeLambda;

/**
 * Get the frequent itemsets with their support.
 */
public final class FIM implements RequestHandler<List<List<String>>, String> {
    @Override
    public String handleRequest(final List<List<String>> input, final Context context) {
        invokeLambda("StoreDB", run(input, 2));
        return STATUS_200;
    }

    /**
     * The transactional dataset.
     */
    private List<List<String>> dataset;
    /**
     * The minimum support.
     */
    private int support;
    /**
     * The list of current itemsets.
     */
    private List<Pair<List<String>, Integer>> itemsets;
    /**
     * The accumulator of frequent itemsets.
     */
    private final List<Pair<List<String>, Integer>> itemsetAccumulator;

    /**
     * Set up Apriori.
     */
    public FIM() {
        this.itemsetAccumulator = new ArrayList<>();
    }

    /**
     * Run the Apriori algorithm.
     *
     * @param dataset transactional dataset
     * @param support minimum (absolute) number of transactions
     * @return the list of frequent itemsets along with their support
     */
    public List<Pair<List<String>, Integer>> run(final List<List<String>> dataset, final int support) {
        this.dataset = dataset;
        this.support = support;
        // set the 1-itemsets
        this.itemsets = dataset.stream().flatMap(Collection::stream).distinct().map(i -> {
            List<String> itemset = Lists.newArrayList(i); // create 1-itemset
            return Pair.of(itemset, -1); // add a "fake" support
        }).collect(Collectors.toList());
        while (!itemsets.isEmpty()) {
            // check the support of the current patterns and filter them
            getFrequentItemsets();
            itemsetAccumulator.addAll(itemsets);
            // create the new itemsets
            createNewItemsetsFromPreviousOnes();
        }
        return itemsetAccumulator;
    }

    /**
     * If n is the size of the current itemsets, generate all possible itemsets of size n + 1 from pairs of current itemsets.
     */
    private void createNewItemsetsFromPreviousOnes() {
        final Set<Pair<List<String>, Integer>> tempCandidates = new HashSet<>(); // temporary candidates
        for (int i = 0; i < itemsets.size(); i++) {
            for (int j = i + 1; j < itemsets.size(); j++) {
                final List<String> x = itemsets.get(i).getLeft();
                final List<String> y = itemsets.get(j).getLeft();
                final List<String> newCand = Lists.newArrayList(x);
                final int prevSize = newCand.size();
                y.forEach(s1 -> {
                    if (!x.contains(s1)) {
                        newCand.add(s1);
                    }
                });
                if (newCand.size() - prevSize == 1) {
                    tempCandidates.add(Pair.of(newCand.stream().sorted().collect(Collectors.toList()), -1));
                }
            }
        }
        itemsets = new ArrayList<>(tempCandidates);
    }

    /**
     * Filter the itemsets.
     */
    private void getFrequentItemsets() {
        itemsets =
                itemsets
                        .stream()
                        .map(i -> Pair.of(i.getLeft(), (int) dataset.stream().filter(t -> t.containsAll(i.getLeft())).count()))
                        .filter(i -> i.getRight() >= support)
                        .collect(Collectors.toList());
    }
}
