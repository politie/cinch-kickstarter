package eu.icolumbo.cinch.kickstarter;

import eu.icolumbo.cinch.streaming.StreamingCinchContext;
import eu.icolumbo.cinch.streaming.StreamingCinchJob;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Jethro Bakker
 */
@Component
public class StreamingJob extends StreamingCinchJob {

	@Value("${streaming.host}")
	private String host;

	@Value("${streaming.port}")
	private int port;

	/**
	 * duration of micro batch is 5 seconds.
	 */
	public StreamingJob() {
		super(5000);
	}

	/**
	 * Main method to launch job.
	 */
	public static void main(String[] args) {
		StreamingCinchContext.springJavaConfiguration(SpringConfiguration.class).executeJob();
	}

	/**
	 * Execute job, this method contains all the business logic.
	 * The cinch context is used to create the Spark functions.
	 */
	@Override
	protected void defineJob(JavaStreamingContext streamingContext, StreamingCinchContext cinchContext) {
		JavaReceiverInputDStream<String> dStream = streamingContext.socketTextStream(host, port);
		dStream.foreachRDD(cinchContext.foreachRddFunction(PrintFunction.class));
	}
}
