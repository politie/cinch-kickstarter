package eu.icolumbo.cinch.kickstarter;

import eu.icolumbo.cinch.CinchContext;
import eu.icolumbo.cinch.CinchJob;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.stereotype.Component;

/**
 * @author Jethro Bakker
 */
@Component
public class BatchJob extends CinchJob {

	private static final Logger logger = Logger.getLogger(BatchJob.class);

	/**
	 * Main method to start job.
	 */
	public static void main(String[] args) {
		CinchContext.springJavaConfiguration(SpringConfiguration.class).executeJob();
	}

	/**
	 * Execute method contains all the business logic.
	 * The cinch context is used to create Spark functions.
	 */
	@Override
	protected void execute(JavaSparkContext sparkContext, CinchContext cinchContext) {
		logger.info("Start execution of example cinch batch job");

		JavaRDD<String> rdd = sparkContext.textFile("src/main/resources/example.txt");
		rdd.foreach(cinchContext.voidFunction(PrintFunction.class));

		logger.info("Finished execution example cinch batch job");
	}
}