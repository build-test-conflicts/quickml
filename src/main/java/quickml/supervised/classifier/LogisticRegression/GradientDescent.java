package quickml.supervised.classifier.logisticRegression;

import java.util.List;

/**
 * Created by alexanderhawk on 10/12/15.
 */
public interface GradientDescent {
     double[] minimize(List<SparseClassifierInstance> sparseClassifierInstances, int numFeatures);


}
