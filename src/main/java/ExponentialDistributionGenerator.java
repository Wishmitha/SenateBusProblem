/**
 * Created by wolfpack on 2/8/18.
 */

import org.apache.commons.math3.distribution.ExponentialDistribution;

public class ExponentialDistributionGenerator {

    private ExponentialDistribution exp;

    ExponentialDistributionGenerator(int mean) {
        exp = new ExponentialDistribution(mean);
    }

    public long getSample() {
        return (long) exp.sample();
    }
}
