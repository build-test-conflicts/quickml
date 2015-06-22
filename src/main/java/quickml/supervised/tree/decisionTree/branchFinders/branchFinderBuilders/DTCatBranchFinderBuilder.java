package quickml.supervised.tree.decisionTree.branchFinders.branchFinderBuilders;

import quickml.supervised.tree.attributeValueIgnoringStrategies.AttributeValueIgnoringStrategy;
import quickml.supervised.tree.branchFinders.branchFinderBuilders.BranchFinderBuilder;
import quickml.supervised.tree.decisionTree.attributeValueIgnoringStrategies.BinaryClassAttributeValueIgnoringStrategyBuilder;
import quickml.supervised.tree.decisionTree.attributeValueIgnoringStrategies.MultiClassAttributeValueIgnoringStrategyBuilder;
import quickml.supervised.tree.decisionTree.branchFinders.DTBinaryCatBranchFinder;
import quickml.supervised.tree.decisionTree.branchFinders.DTreeNClassCatBranchFinder;
import quickml.supervised.tree.decisionTree.valueCounters.ClassificationCounter;
import quickml.supervised.tree.decisionTree.nodes.DTNode;

import java.util.Set;

/**
 * Created by alexanderhawk on 4/26/15.
 */
public class DTCatBranchFinderBuilder extends DTBranchFinderBuilder {

    @Override
    public DTCatBranchFinderBuilder createBranchFinderBuilder() {
        return new DTCatBranchFinderBuilder();
    }

    @Override
    public DTreeNClassCatBranchFinder buildBranchFinder(ClassificationCounter classificationCounts, Set<String> candidateAttributes) {
        AttributeValueIgnoringStrategy<ClassificationCounter> attributeValueIgnoringStrategy;
        if (getAttributeValueIgnoringStrategyBuilder() == null) {
            MultiClassAttributeValueIgnoringStrategyBuilder multiClassAttributeValueIgnoringStrategyBuilder = new MultiClassAttributeValueIgnoringStrategyBuilder(getMinOccurencesOfAttributeValue());
            attributeValueIgnoringStrategy = multiClassAttributeValueIgnoringStrategyBuilder.createAttributeValueIgnoringStrategy(classificationCounts);

        } else {
            attributeValueIgnoringStrategy = attributeValueIgnoringStrategyBuilder.createAttributeValueIgnoringStrategy(classificationCounts);
        }
        return new DTreeNClassCatBranchFinder(candidateAttributes, branchingConditions, scorer, attributeValueIgnoringStrategy, attributeIgnoringStrategy, branchType);
    }
}
