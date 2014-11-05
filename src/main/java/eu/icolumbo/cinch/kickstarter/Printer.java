package eu.icolumbo.cinch.kickstarter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Jethro Bakker
 */
@Component
public class Printer {

	private static final Logger logger = Logger.getLogger(Printer.class);

	public void print(String s) {
		logger.info(s);
	}
}
