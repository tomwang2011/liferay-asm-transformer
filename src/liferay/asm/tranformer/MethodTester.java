/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liferay.asm.tranformer;

import java.io.IOException;

/**
 *
 * @author tom
 */
public class MethodTester {

	/**
	 * @param args the command line arguments
	 */

	public static void main(String[] args) throws Exception {
		System.out.println("hello");

		testingMethod testMethod = new testingMethod();

		System.out.println("calling setter: ");

		testMethod.setTestVariable(true);

		System.out.println("calling testing method: ");

		try {
			testMethod.testing();
		}
		catch (Exception e) {
			System.out.println("the following exception was thrown: " + e);
		}

			System.out.println("calling method with parameters: ");

		try {
			testMethod.testingWithParameters(0);
		}
		catch (Exception e) {
			System.out.println("the following exception was thrown: " + e);
		}
	}

	public static void testReturn() {
		return;
	}

	public static void testIOException() throws IOException {
		throw new IOException();
	}

	public static void testUnsupportedException() {
		throw new UnsupportedOperationException();
	}
}