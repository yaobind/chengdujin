package yuan.jin.interviewQuestions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A small demonstration of using java.lang.reflect to dynamically load a class
 * and call a method.
 * 
 * http://www.dreamincode.net/code/snippet907.htm
 * 
 * @author Yuan
 * 
 */
public class Reflection {
	public static void main(String args[])
			throws // a fit
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException

	{
		ClassLoader j = ClassLoader.getSystemClassLoader();
		Class someClass = j.loadClass("ReflectionY");
		Object instanceOfSomething = someClass.newInstance();

		// note, the second param is null since the runMe method takes no
		// parameters
		Method aMethod = someClass.getMethod("runMe", null);

		// again, a null second parameter since runMe has no params
		Object returnValue = aMethod.invoke(instanceOfSomething, null);

		System.out.println("return value of runMe : " + returnValue);

	}// main

}// class
