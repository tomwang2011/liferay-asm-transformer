/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package liferay.asm.tranformer;

import java.io.IOException;

/**
 * @author Tom Wang
 */
public class testingMethod {
	public void testing() throws IOException {
		System.out.println("testing method!");
	}

	public int testingWithParameters(int i) throws IllegalAccessException {
		int j = i;

		System.out.println("int j is: " + j);
		return j;
	}

	public testingMethod() {
		_testVariable = true;
		System.out.println("this is constructor");
	}

	public void setTestVariable(boolean testVariable) {
		_testVariable = testVariable;
		System.out.println("variable set to: " + testVariable);
	}

	private boolean _testVariable;
}
