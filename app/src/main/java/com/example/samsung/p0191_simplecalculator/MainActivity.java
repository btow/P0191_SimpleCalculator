package com.example.samsung.p0191_simplecalculator;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int MENU_RESET_ID = 1, MENU_QUIT_ID = 2;

    private EditText etNum1, etNum2;
    private Button btnAdd, btnSub, btnMult, btnDiv;
    private TextView tvResult;

    private String oper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnSub.setOnClickListener(this);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnMult.setOnClickListener(this);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(this);

        tvResult = (TextView) findViewById(R.id.tvResult);

    }

    @Override
    public void onClick(View v) {

        float num1 = 0, num2 = 0, result = 0;
        boolean isResult = true;

        if (TextUtils.isEmpty(etNum1.getText().toString())) {

            Toast.makeText(MainActivity.this, R.string.left_field, Toast.LENGTH_LONG).show();
            return;

        } else if (TextUtils.isEmpty(etNum2.getText().toString())) {

            Toast.makeText(MainActivity.this, R.string.raght_field, Toast.LENGTH_LONG).show();
            return;

        }

        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        switch (v.getId()) {

            case R.id.btnAdd:
                oper = "+";
                result = num1 + num2;
                break;
            case R.id.btnSub:
                oper = "-";
                result = num1 - num2;
                break;
            case R.id.btnMult:
                oper = "*";
                result = num1 * num2;
                break;
            case R.id.btnDiv:

                if (num2 == 0.0) {
                    isResult = false;
                    Toast.makeText(this, R.string.div_on_zero, Toast.LENGTH_LONG).show();
                } else {
                    oper = "/";
                    result = num1 / num2;
                }
                break;
            default:
                break;
        }

        if (isResult) {
            tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
        } else {
            tvResult.setText(R.string.div_on_zero);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, MENU_RESET_ID, 0, R.string.reset);
        menu.add(0, MENU_QUIT_ID, 0, R.string.quit);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case MENU_RESET_ID:
                etNum1.setText("");
                etNum2.setText("");
                tvResult.setText("");
                break;
            case MENU_QUIT_ID:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
