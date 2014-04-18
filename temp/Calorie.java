package com.temp.temp;

import com.stat.stat.AppVariable;

import android.content.Context;

public class Calorie {

	public Calorie(Context applicationContext) {
		// TODO Auto-generated constructor stub
	}

	public float calc() {
		
		@SuppressWarnings("unused")
		float higt=Float.parseFloat(AppVariable.height);
		float weit=Float.parseFloat(AppVariable.weight);
		
		weit=(float) (weit*2.2046);
		
		float calory=(float) ((.57*weit)/2200);
		
		float res=calory*AppVariable.st;
		
		return res;
	}

}
