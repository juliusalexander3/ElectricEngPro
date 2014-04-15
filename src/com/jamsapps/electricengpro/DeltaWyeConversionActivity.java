package com.jamsapps.electricengpro;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DeltaWyeConversionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delta_wye_conversion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delta_wye_conversion, menu);
		return true;
	}

}
