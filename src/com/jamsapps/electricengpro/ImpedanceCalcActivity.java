package com.jamsapps.electricengpro;

import com.jamsapps.util.Complex;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;



public class ImpedanceCalcActivity extends Activity implements OnItemSelectedListener {
	private static final String TAG = "Testing";
	Spinner spinnerSerPar, spinnerRLC;
	TextView TVres, TVind, TVcap;
	EditText ETres, ETind, ETcap, ETfreq, ETAnswer;
	Button CalculateButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_impedance_calc);


		spinnerRLC = (Spinner) findViewById(R.id.spinnerRLC);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterRLC = ArrayAdapter.createFromResource(this,
				R.array.ImpedanceCalcs_Type, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapterRLC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerRLC.setAdapter(adapterRLC);

		spinnerSerPar = (Spinner) findViewById(R.id.spinnerSerPar);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterSerPar = ArrayAdapter.createFromResource(this,
				R.array.ImpedanceCalcs_Config, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapterSerPar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerSerPar.setAdapter(adapterSerPar);
		spinnerRLC.setOnItemSelectedListener(this);
		spinnerSerPar.setOnItemSelectedListener(this);

		TVres = (TextView) findViewById(R.id.textViewRes);
		TVind = (TextView) findViewById(R.id.textViewInd);
		TVcap = (TextView) findViewById(R.id.textViewCap);
		
		ETres = (EditText) findViewById(R.id.editTextRes);
		ETind = (EditText) findViewById(R.id.editTextInd);
		ETcap = (EditText) findViewById(R.id.editTextCap);
		ETfreq = (EditText) findViewById(R.id.editTextFreq);
		ETAnswer = (EditText) findViewById(R.id.editTextAnswer);
		CalculateButton = (Button) findViewById(R.id.Calculate_Button);
		CalculateButton.setOnClickListener(CalculateListener);

	}
	private OnClickListener CalculateListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {	
			CalculateImpedance();
		}		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.impedance_calc, menu);
		return true;
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		// An item was selected. You can retrieve the selected item using
		Log.d(TAG, "Test " + parent.getItemAtPosition(pos) );
		System.out.println("TEST"  + parent.getItemAtPosition(pos) );
		// parent.getItemAtPosition(pos)
		
		if (parent.getItemAtPosition(pos).toString().equals("R")){
			TVres.setVisibility(View.VISIBLE);
			ETres.setVisibility(View.VISIBLE);
			TVind.setVisibility(View.INVISIBLE);
			ETind.setVisibility(View.INVISIBLE);
			TVcap.setVisibility(View.INVISIBLE);
			ETcap.setVisibility(View.INVISIBLE);
			
		}else if (parent.getItemAtPosition(pos).toString().equals("L")){
			TVres.setVisibility(View.INVISIBLE);
			ETres.setVisibility(View.INVISIBLE);
			TVind.setVisibility(View.VISIBLE);
			ETind.setVisibility(View.VISIBLE);
			TVcap.setVisibility(View.INVISIBLE);
			ETcap.setVisibility(View.INVISIBLE);
			
		}else if (parent.getItemAtPosition(pos).toString().equals("C")){
			TVres.setVisibility(View.INVISIBLE);
			ETres.setVisibility(View.INVISIBLE);
			TVind.setVisibility(View.INVISIBLE);
			ETind.setVisibility(View.INVISIBLE);
			TVcap.setVisibility(View.VISIBLE);
			ETcap.setVisibility(View.VISIBLE);
			
		}else if (parent.getItemAtPosition(pos).toString().equals("RL")){
			TVres.setVisibility(View.VISIBLE);
			ETres.setVisibility(View.VISIBLE);
			TVind.setVisibility(View.VISIBLE);
			ETind.setVisibility(View.VISIBLE);
			TVcap.setVisibility(View.INVISIBLE);
			ETcap.setVisibility(View.INVISIBLE);
			
		}else if (parent.getItemAtPosition(pos).toString().equals("RC")){
			TVres.setVisibility(View.VISIBLE);
			ETres.setVisibility(View.VISIBLE);
			TVind.setVisibility(View.INVISIBLE);
			ETind.setVisibility(View.INVISIBLE);
			TVcap.setVisibility(View.VISIBLE);
			ETcap.setVisibility(View.VISIBLE);
			
		}else if (parent.getItemAtPosition(pos).toString().equals("RLC")){
			TVres.setVisibility(View.VISIBLE);
			ETres.setVisibility(View.VISIBLE);
			TVind.setVisibility(View.VISIBLE);
			ETind.setVisibility(View.VISIBLE);
			TVcap.setVisibility(View.VISIBLE);
			ETcap.setVisibility(View.VISIBLE);
			
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		System.out.println("No Sel" );
	}
	
	public void CalculateImpedance(){
		
	    float resistor = Float.parseFloat(ETres.getText().toString());
	    float inductor = Float.parseFloat(ETind.getText().toString());
	    float capacitor = Float.parseFloat(ETcap.getText().toString());
	    float frequency = Float.parseFloat(ETfreq.getText().toString());
	    Log.d(TAG, "r"+resistor + " l " + inductor + " c " + capacitor);
	    Complex CalculatedImp;
	    if (spinnerSerPar.getSelectedItem().toString().equals("Series")){
	    	CalculatedImp = CalculateSeriesImpedance(resistor, inductor, capacitor, frequency);
	    	ETAnswer.setText(CalculatedImp.ToString());	    	
	    }else if(spinnerSerPar.getSelectedItem().toString().equals("Parallel")){
	    	CalculatedImp = CalculateParallelImpedance(resistor, inductor, capacitor, frequency);
	    	ETAnswer.setText(CalculatedImp.ToString());	    
	    }
	    
	}
	
	public static Complex CalculateSeriesImpedance(float Resistor, float Inductor, float Capacitor, float Frequency ){
		float ang_freq = (float) (2.0f*3.14159265*Frequency);
		Complex ZR= new Complex();
	    Complex ZL= new Complex();
	    Complex ZC= new Complex();
	   ZR.real= Resistor;
	   Log.d(TAG,"ZR" + ZR.ToString());
	   Log.d(TAG,"ind" + Inductor);
	   if(Inductor>0){
		   ZL.imaginary= ang_freq*Inductor;
		   Log.d(TAG,"ZL" + ZL.ToString());
	   }
	   
	   if (Capacitor>0){
		   ZC.imaginary=(-1/(ang_freq*Capacitor));
		   Log.d(TAG,"ZC" + ZC.ToString());
	   }
	   
	   Complex tmp1 = Complex.AddNumber(ZR,ZL);
	   Log.d(TAG,"tmp1" + tmp1.ToString());
	   Complex result = Complex.AddNumber(tmp1, ZC);
	   Log.d(TAG,"result" + result.ToString());
	   return result;
		
	}
	
	
	public static Complex CalculateParallelImpedance(float Resistor, float Inductor, float Capacitor, float Frequency ){
		float ang_freq = (float) (2.0f*3.14159265*Frequency);
		Complex ZR= new Complex();
	    Complex ZL= new Complex();
	    Complex ZC= new Complex();
	   ZR.real= Resistor;
	   Log.d(TAG,"ZR" + ZR.ToString());
	   if(Inductor>0){
		   
		   ZL.imaginary= ang_freq*Inductor;
		   Log.d(TAG,"ZL" + ZL.ToString());
	   }
	   
	   if (Capacitor>0){
		   ZC.imaginary=(-1/(ang_freq*Capacitor));
		   Log.d(TAG,"ZC" + ZC.ToString());
	   }
	   
	   Complex invZR = Complex.GetInverse(ZR);
	   Log.d(TAG,"invZR" + invZR.ToString());
	   Complex invZL = Complex.GetInverse(ZL);
	   Log.d(TAG,"invZL" + invZL.ToString());
	   Complex invZC = Complex.GetInverse(ZC);
	   Log.d(TAG,"invZC" + invZC.ToString());

	   Complex tmp1 = Complex.AddNumber(invZR, invZL);
	   Log.d(TAG,"tmp1" + tmp1.ToString());
	   Complex admitance = Complex.AddNumber(tmp1, invZC);
	   Log.d(TAG,"admitance" + admitance.ToString());
	   Complex result = Complex.GetInverse(admitance);
	   Log.d(TAG,"result" + result.ToString());
	   return result;
		
	}
	
	
	
	

}
