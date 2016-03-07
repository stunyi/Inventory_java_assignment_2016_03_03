package org.assignment;

import org.assignment.factory.impl.DefaultFactory;

public class Engine {

	public static void main(String[] args) {

		// Configure the Service Engine
		ConfigService.setObjectFactory(new DefaultFactory());
		
		
	}

}
