package quickml.supervised.classifier.logRegression;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickml.InstanceLoader;
import quickml.data.instances.ClassifierInstance;
import quickml.data.OnespotDateTimeExtractor;
import quickml.supervised.crossValidation.ClassifierLossChecker;
import quickml.supervised.crossValidation.CrossValidator;
import quickml.supervised.crossValidation.data.OutOfTimeData;
import quickml.supervised.crossValidation.lossfunctions.classifierLossFunctions.WeightedAUCCrossValLossFunction;

import java.util.List;

/**
 * Created by alexanderhawk on 10/13/15.
 */
public class LogisticRegressionBuilderTest {
    public static final Logger logger = LoggerFactory.getLogger(LogisticRegressionBuilderTest.class);

    @Test
    public void testAdInstances() {
        List<ClassifierInstance> instances = InstanceLoader.getAdvertisingInstances();

        LogisticRegressionBuilder logisticRegressionBuilder = new LogisticRegressionBuilder(nameToIndexMap);
        CrossValidator crossValidator = new CrossValidator(logisticRegressionBuilder,
                new ClassifierLossChecker(new WeightedAUCCrossValLossFunction(1.0)),
                new OutOfTimeData<ClassifierInstance>(instances, 0.25, 48, new OnespotDateTimeExtractor()));

        logger.info("out of time loss: {}", crossValidator.getLossForModel());
    }

}