package c.c.quadraticfunction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import c.c.quadraticfunction.solvers.PolynomialEquation;
import c.c.quadraticfunction.solvers.SolverFactory;

public class MainActivity extends AppCompatActivity {
    EditText editTextA,editTextB,editTextC;
    Double parameterA = 0.0,parameterB = 0.0,parameterC = 0.0;
    TextView textViewResults;
    CharSequence result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextA = (EditText) findViewById(R.id.editTextA);
        editTextB = (EditText) findViewById(R.id.editTextB);
        editTextC = (EditText) findViewById(R.id.editTextC);
        textViewResults = (TextView) findViewById(R.id.textViewResults);

        Button buttonOk = (Button) findViewById(R.id.buttonCompute);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parameterA = parseEditText(editTextA);
                parameterB = parseEditText(editTextB);
                parameterC = parseEditText(editTextC);

                compute();
            }
        });

        if (savedInstanceState != null) {
            parameterA = getBundleDouble(savedInstanceState, "paramA", 0.0);
            parameterB = getBundleDouble(savedInstanceState, "paramB", 0.0);
            parameterC = getBundleDouble(savedInstanceState, "paramC", 0.0);
            compute();
        }
    }

    public void compute(){
        PolynomialEquation solver = SolverFactory.getSolver(parameterC,parameterB,parameterA);
        try {
            List results = solver.compute();
            showResults(results);
        } catch (Exception e) {
            showExceptionMessage(e);
        }
    }

    private void showResults(List<Double> results ){
        StringBuilder sb = new StringBuilder();
        int i=1;
        for(double result:results){
            sb.append("X<sub><small> ");
            sb.append(i);
            sb.append("</small></sub> = ");
            sb.append(result);
            sb.append("<br/>");
            i++;
        }
        textViewResults.setText(Html.fromHtml(sb.toString()));
        result = sb.toString();
    }
    private void showExceptionMessage(Exception e){
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(e.getMessage(), "string", packageName);
        textViewResults.setText(getString(resId));
        result = getString(resId);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("paramA", parameterA);
        savedInstanceState.putDouble("paramB", parameterB);
        savedInstanceState.putDouble("paramC", parameterC);
        savedInstanceState.putCharSequence("result", result);
    }

    private double parseEditText(EditText editText){
        String text = editText.getText().toString();
        text = text.equals("")? "0":text;
        Log.d("parse", "text: " + text);
        return Double.parseDouble(text);
    }
    public Double getBundleDouble(Bundle b, String key, Double def)
    {
        Double value = b.getDouble(key);
        if (value == null)
            value = def;
        return value;
    }
//    private CharSequence getBundleString(Bundle b, String key, String def)
//    {
//        CharSequence value = b.getCharSequence(key);
//        if (value == null)
//            value = def;
//        return Html.fromHtml(value.toString());
//    }
}
