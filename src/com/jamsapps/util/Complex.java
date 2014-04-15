package com.jamsapps.util;

import android.util.Log;

public class Complex {
	
	private static final String TAG = null;
	public float real;
	public float imaginary;
	
	public Complex(){
		this.real=0;
		this.imaginary=0;
	}
	public Complex(float real, float imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}
	/**
	 * Get String Representation of complex number
	 * @return
	 */
	public String ToString(){
		String result = "" + real;
		if (this.imaginary>=0){
			result = result + " + ";
		}else{
			result = result +  " ";
		}
		result = result + imaginary +"j";		
		return result;
	}
	
	
	// TODO implement this comparison method
	public boolean Equals(Object o2){
		return true;
	}
	
	public static Complex AddNumber(Complex augend, Complex addend){
		Complex result = new Complex(augend.real + addend.real, augend.imaginary + addend.imaginary);
		return result;
	}
	
	public static Complex SubstractNumber(Complex minuend, Complex substraend){
		Complex result = new Complex(minuend.real - substraend.real, minuend.imaginary - substraend.imaginary);
		return result;
		
	}
	
	public static Complex MultiplyNumber(Complex multiplicand, Complex multiplier){
		Complex result = new Complex(multiplicand.real* multiplier.real-
									multiplicand.imaginary* multiplier.imaginary,									
									multiplicand.real*multiplier.imaginary+
									multiplicand.imaginary*multiplier.real);
		return result;
		
	}
	
	public static Complex DivideNumber(Complex dividend, Complex divisor) throws Exception{
		Complex result;
	    if(divisor.real ==0.0f && divisor.imaginary ==0.0f){
	        throw new Exception();
	    }
	    float newreal = 
	    		(dividend.real*divisor.real + dividend.imaginary *divisor.imaginary )/
	    		(divisor.real*divisor.real + divisor.imaginary *divisor.imaginary);
	    
	    float newImaginary =
	    		(divisor.real * dividend.imaginary  - dividend.real *   divisor.imaginary )/
	    		(divisor.real * divisor.real  + divisor.imaginary *divisor.imaginary );
	    result = new Complex(newreal, newImaginary);
		return result;
		
	}
	
	public static Complex GetInverse(Complex complexNumber){
		Complex conjugate;
		float tmpdivisor;
		Complex divisor;
		if(complexNumber.real!=0 || complexNumber.imaginary!=0){
			conjugate = new Complex(complexNumber.real, - complexNumber.imaginary);
			Log.d(TAG,"conjugate " +conjugate.ToString());
			tmpdivisor = (complexNumber.real*complexNumber.real) + (complexNumber.imaginary*complexNumber.imaginary);
			divisor = new Complex(tmpdivisor, 0);
			Log.d(TAG,"divisor " + divisor.ToString());
			Complex result;
			try {
				result = Complex.DivideNumber(conjugate, divisor);
				Log.d(TAG,"result " + result.ToString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Complex();
			}
			
			return result;
		}else{	
		return new Complex();
		}
	}
	
	
	
}
