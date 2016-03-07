package org.assignment;

import org.assignment.factory.IFactory;

public class ConfigService {
	
	private static IFactory objectFactory;

	/**
	 * @return the objectFactory
	 */
	public static IFactory getObjectFactory() {
		return objectFactory;
	}

	/**
	 * @param objectFactory the objectFactory to set
	 */
	public static void setObjectFactory(IFactory objectFactory) {
		ConfigService.objectFactory = objectFactory;
	}

}
