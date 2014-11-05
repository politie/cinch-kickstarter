package eu.icolumbo.cinch.kickstarter;

import org.apache.spark.api.java.function.VoidFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jethro Bakker
 */
@Component
public class PrintFunction implements VoidFunction<String> {

	@Autowired
	private Printer printer;

	@Override
	public void call(String s) throws Exception {
		printer.print(s);
	}
}
