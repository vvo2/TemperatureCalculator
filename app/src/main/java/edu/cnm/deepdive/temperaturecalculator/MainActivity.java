package edu.cnm.deepdive.temperaturecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author Vuong Vo
 * This temperature converter app converts on-the-fly as user input the numbers.
 * To eliminate exceptions the EditText's textType allows limits user to negative and decimal numbers,
 * while the matches("") and ("-") prevent exceptions until user have a number inputted.
 * The skipOnChange is the talking stick to prevent the infinite recursion,
 * allowing only one EditText to do the math at a time.
 */
public class MainActivity extends AppCompatActivity {

  private EditText etKevin;
  private EditText etCelcius;
  private EditText etFahrenh;
  private String strKevin;
  private String strCelcius;
  private String strFahrenh;
  private float f = 0;
  private float k = 0;
  private float c = 0;
  private boolean skipOnChange;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etKevin = findViewById(R.id.edtext_kelvin);
    etCelcius = findViewById(R.id.edtext_celcius);
    etFahrenh = findViewById(R.id.edtext_fahrenh);

    etFahrenh.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        strFahrenh = etFahrenh.getText().toString();
        if (!strFahrenh.matches("") && !strFahrenh.matches("-")) {
          f = Float.parseFloat(strFahrenh);
        }

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (skipOnChange) {
          return;
        } else {
          skipOnChange = true;

          k = (5f / 9) * (f - 32) + 273;
          c = (5f / 9) * (f - 32);

          etCelcius.setText(strCelcius.valueOf(c));
          etKevin.setText(strKevin.valueOf(k));

          skipOnChange = false;
        }
      }
    });

    etKevin.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        strKevin = etKevin.getText().toString();
        if (!strKevin.matches("") && !strKevin.matches("-")) {
          k = Float.parseFloat(strKevin);
        }
      }

      @Override
      public void afterTextChanged(Editable s) {
        if (skipOnChange) {
          return;
        } else {
          skipOnChange = true;

          c = k - 273;
          f = (9f / 5) * (k - 273) + 32;

          etCelcius.setText(strCelcius.valueOf(c));
          etFahrenh.setText(strFahrenh.valueOf(f));

          skipOnChange = false;
        }
      }
    });

    etCelcius.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        strCelcius = etCelcius.getText().toString();
        if (!strCelcius.matches("") && !strCelcius.matches("-")) {
          c = Float.parseFloat(strCelcius);
        }

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (skipOnChange) {
          return;
        } else {
          skipOnChange = true;

          k = c + 273;
          f = (9f / 5) * (c) + 32;

          etKevin.setText(strKevin.valueOf(k));
          etFahrenh.setText(strFahrenh.valueOf(f));

          skipOnChange = false;
        }
      }
    });


  }

}
