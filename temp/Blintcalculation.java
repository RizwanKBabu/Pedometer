package com.temp.temp;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

import com.servicess.servicess.accelerometer;
import com.servicess.servicess.gravity;
import com.servicess.servicess.orientation;
import com.stat.stat.AppVariable;

public class Blintcalculation {

	int i = 0;
	float alpha = (float) 0.8;
	float val1, val2, val3;
	float cal1, cal2, cal3;
	float[] linear_acceleration=new float[3];
	float gv,average;
	int step=0;
	float sum=0;
	float[] store=new float[100];
	static Context context;
	float[] or=new float[100];
	String result=",";

	

	public Blintcalculation(Context appln) {
		// TODO Auto-generated constructor stub
		context = appln;
	}



	public void gravit() {
		// TODO Auto-generated method stub

		
		
		String array2=PreferenceManager.getDefaultSharedPreferences(context).getString("ORIENTATION", "");
		String[] split2 = array2.split("#");
		
//		String array = PreferenceManager.getDefaultSharedPreferences(context)
//				.getString("HELLO", "");
//		String[] split = array.split("#");
		
		String array3=PreferenceManager.getDefaultSharedPreferences(context).getString("ACCELERATION", "");
		String[] split3 = array3.split("#");
		
		for (int i = 0; i < split2.length; i++) {
//			Log.d("TAG", split[i]);
			Log.d("TAG1",split2[i]);
			Log.d("TAG2",split3[i]);
			
	//		String[] gravity=split[i].split(",");
			String[] accelerate=split3[i].split(",");
			String[] orient    =split2[i].split(",");
			
			
				
				
		//		val1 = alpha * Float.parseFloat(gravity[0]) + (1 - alpha)
		//				* Float.parseFloat(accelerate[0]);
		//		val2 = alpha * Float.parseFloat(gravity[1]) + (1 - alpha)
		//				* Float.parseFloat(accelerate[1]);
		//		val3 = alpha * Float.parseFloat(gravity[2]) + (1 - alpha)
		//				* Float.parseFloat(accelerate[2]);

				
		//		linear_acceleration[0] = Float.parseFloat(accelerate[0]) + val1;
		//		linear_acceleration[1] = Float.parseFloat(accelerate[1]) + val2;
		//		linear_acceleration[2] = Float.parseFloat(accelerate[2]) + val3;
				
				

				cal1 = (float) ((-Float.parseFloat(accelerate[0])) * Math.sin(Double
						.parseDouble(orient[2])));
				cal2 = (float) ((-Float.parseFloat(accelerate[1])) * Math.sin(Double
						.parseDouble(orient[1])));
				cal3 = (float) ((-Float.parseFloat(accelerate[2]))
						* Math.cos(Double.parseDouble(orient[2])) * Math
						.cos(Double.parseDouble(orient[1])));
				
				
				gv = cal1 + cal2 + cal3;

				or[i]=Float.parseFloat(orient[0]);
			
			store[i]=gv;
			sum=sum+store[i];
			
			

		}
		
		 average=(float) ((sum/100)+10.5);
		 
		 
		 int test=(90*Math.round(or[5]/90));
		  if(test==360)
	       {
	    	   test=0;
	       }
		  
			for(int k=5;k<100;k++)
	         {
	       int orient=(90*Math.round(or[k]/90));
	       if(orient==360)
	       {
	    	   orient=0;
	       }
	       if(test!=orient)
	       {
			switch (test)
			{
			case 0:
				if (orient==90)
				{
				     if(step!=0){
                        result=result+step+","+"r"+",";
                     }
                 else{
                        result=result+"r"+",";
                     }
				}
				else
				{
					  if(step!=0){
                         result=result+step+","+"l"+",";
	                 }else{
		                  result=result+"l"+",";
	                      }   
				}
				break;
			case 90:
				if (orient==180)
				{
				     if(step!=0){
                        result=result+step+","+"r"+",";
                     }
                 else{
                        result=result+"r"+",";
                     }
				}
				else
				{
					  if(step!=0){
                         result=result+step+","+"l"+",";
	                 }else{
		                  result=result+"l"+",";
	                      }   
				}
				break;
			case 180:
				if (orient==270)
				{
				     if(step!=0){
                        result=result+step+","+"r"+",";
                     }
                 else{
                        result=result+"r"+",";
                     }
				}
				else
				{
					  if(step!=0){
                         result=result+step+","+"l"+",";
	                 }else{
		                  result=result+"l"+",";
	                      }   
				}
				break;
			default :
				if (orient==0)
				{
				     if(step!=0){
                        result=result+step+","+"r"+",";
                     }
                 else{
                        result=result+"r"+",";
                     }
				}
				else
				{
					  if(step!=0){
                         result=result+step+","+"l"+",";
	                 }else{
		                  result=result+"l"+",";
	                      }   
				}
				break;
			}
			
			
			test=orient;
	       }
	       else	if(store[k]>average)
      	 {
      	  step++;
      	 }
      	 
       }

		
	         result=result+step;
		  AppVariable.res=AppVariable.res+result;
		create();
	}

	

	public static void create() {
		// TODO Auto-generated method stub

		

		
		context.startService(new Intent(context, accelerometer.class));
//		context.startService(new Intent(context,gravity.class));
		context.startService(new Intent(context,orientation.class));

	}

}

