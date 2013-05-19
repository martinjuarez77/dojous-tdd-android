package com.example.multitemp01;

import java.math.BigDecimal;

import com.example.multitemp01.logic.KelvinAndFahrenheitTemp;
import com.example.multitemp01.logic.TempConversor;

import android.os.Bundle;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// INICIO Desactivar el bloqueo de pantalla
		// también debemos modificar el fichero AndroidManifest.xml añadiendo la siguiente linea antes de <application
		// 	<uses-permission android:name="android.permission.DISABLE_KEYGUARD"></uses-permission>
		// cuando la aplicación pase a producción debemos quitar este bloque de código y eliminar la línea anterior del archivo manifest
		KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
		KeyguardLock lock = keyguardManager.newKeyguardLock("MainActivity");
		lock.disableKeyguard();	
		// FIN Desactivar el bloqueo de pantalla
		
		EditText et = (EditText )this.findViewById(R.id.editCentigrados);
		et.requestFocus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	
	public void convertTemperatures(View view) {
		BigDecimal bd = this.getCentigrades();
		
		// Convertir
		TempConversor tc = new TempConversor();
		KelvinAndFahrenheitTemp kft = tc.convert(bd);
		
		this.showResult(kft);
	}
	
	public void resetForm(View view) {
		this.resetForm();
	}
	
	
	public void showResult(KelvinAndFahrenheitTemp temp) {
		EditText view;
		view = (EditText) findViewById(R.id.editKelvin);
		view.setText(temp.getKelvinsAsSTring());
		view = (EditText) findViewById(R.id.editFahrenheit);
		view.setText(temp.getFahrenheitsAsString());
	}

	public BigDecimal getCentigrades() {
		EditText gradosCView = (EditText) findViewById(R.id.editCentigrados);
		Editable gradosCEditable = gradosCView.getText();
		BigDecimal val;
		String content = gradosCEditable.toString();

		if ( (content != null) && (content.compareTo("") != 0) ) {
			 val = new BigDecimal(content);

		} else {
			val = BigDecimal.ZERO;
		}
		
		return val;

	}

	public void resetForm() {
		// TODO Auto-generated method stub
		EditText view;
		view = (EditText) findViewById(R.id.editKelvin);
		view.setText("");
		view = (EditText) findViewById(R.id.editFahrenheit);
		view.setText("");
	}
}
