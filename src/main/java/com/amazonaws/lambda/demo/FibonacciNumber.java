package com.amazonaws.lambda.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FibonacciNumber implements RequestHandler<Object, String> {

	@Override
	public String handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);

		JSONObject jsonObj = null;

		JSONArray inputNumbers = null;

		String responseString = "";
		try {
			jsonObj = new JSONObject("" + input);
			// "{ \"number\": \"5\"}"
			// inputNumber = Integer.parseInt(jsonObj.getString("number"));

			// "{\"numbers\":[1,2,3,4,5,6]}"
			inputNumbers = jsonObj.getJSONArray("numbers");

		} catch (JSONException e) {
			 e.printStackTrace();
		}
		/*
		 * if (isFibonacci(inputNumber)) { responseString = " Fibonacci"; } else {
		 * responseString = " not Fibonacci"; } return inputNumber + " - " +
		 * responseString;
		 */

		responseString = checkNumbersForFibo(inputNumbers);
		return responseString;

	}

	private String checkNumbersForFibo(JSONArray inputNumbers) {
		// TODO Auto-generated method stub
		String response = "";

		for (Object num : inputNumbers) {
			int value = Integer.parseInt(num.toString());
			if (isFibonacci(value)) {
				response += value + " - Fibonacci. ";
			} else {
				response += value + " - not Fibonacci. ";
			}
		}
		return response;
	}

	// Returns true if n is a Fibonacci Number, else false
	public boolean isFibonacci(int n) {
		// n is Fibonacci if one of 5*n*n + 4 or 5*n*n - 4 or both
		// is a perfect square
		return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
	}

	public boolean isPerfectSquare(int x) {
		int s = (int) Math.sqrt(x);
		return (s * s == x);
	}

}
