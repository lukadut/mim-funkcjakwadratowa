package c.c.quadraticfunction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import c.c.quadraticfunction.solvers.PolynomialEquation;
import c.c.quadraticfunction.solvers.SolverFactory;

public class MainActivity extends AppCompatActivity {
    private EditText editTextA,editTextB,editTextC;
    private Double parameterA = 0.0,parameterB = 0.0,parameterC = 0.0;
    private TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextA = (EditText) findViewById(R.id.editTextA);
        editTextB = (EditText) findViewById(R.id.editTextB);
        editTextC = (EditText) findViewById(R.id.editTextC);
        textViewResults = (TextView) findViewById(R.id.textViewResults);

        Button buttonOk = (Button) findViewById(R.id.buttonCompute);

        /**
         * Akcja wywoływana po naciśnięciu klawisza.
         * Parsuje wartości z editTextów na liczby, a następnie wywołuje funkcję obliczającą
         */
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parameterA = parseEditText(editTextA);
                parameterB = parseEditText(editTextB);
                parameterC = parseEditText(editTextC);

                compute();
            }
        });

        /**
         * Akcja wywołana przy tworzeniu widoku (po zmianie orientacji).
         * Przywraca zapisane wartości współczynników.
         */
        if (savedInstanceState != null) {
            parameterA = getBundleDouble(savedInstanceState, "paramA", 0.0);
            parameterB = getBundleDouble(savedInstanceState, "paramB", 0.0);
            parameterC = getBundleDouble(savedInstanceState, "paramC", 0.0);
            compute();
        }
    }

    /**
     * Funkcja rozwiązująca równanie
     */
    public void compute(){
        PolynomialEquation solver = SolverFactory.getSolver(parameterC,parameterB,parameterA);
        try {
            List results = solver.compute();
            showResults(results);
        } catch (Exception e) {
            showExceptionMessage(e);
        }
    }

    /**
     * Funkcja wyświetlająca rozwiązania równania
     * @param results
     */
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
    }

    /**
     * Funkcja wyświetlająca komunikaty, wyrzucone w wyjątkach przez rozwiązywacze
     * @param e
     */
    private void showExceptionMessage(Exception e){
        try {
            String packageName = getPackageName();
            int resId = getResources().getIdentifier(e.getMessage(), "string", packageName);
            textViewResults.setText(getString(resId));
        } catch (Exception exc){
            Toast.makeText(this, getResources().getString(R.string.ApplicationError), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Zapisywanie współczynników
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("paramA", parameterA);
        savedInstanceState.putDouble("paramB", parameterB);
        savedInstanceState.putDouble("paramC", parameterC);
    }

    /**
     * Funkcja parsująca tekst na liczby. W przypadku zerowego ciągu znaków zwraca 0
     * @param editText
     * @return
     */
    private double parseEditText(EditText editText){
        String text = editText.getText().toString();
        text = text.equals("")? "0":text;
        return Double.parseDouble(text);
    }

    /**
     * Funkcja zwracająca zapisane w Bundle liczby. W przypadku nieistniejącego klucza, zwraca wartość domyślną
     * @param b
     * Bundle
     * @param key
     * Klucz
     * @param def
     * Wartość domyślna
     * @return
     * Zapisana liczba w Bundle albo 0
     */
    public Double getBundleDouble(Bundle b, String key, Double def)
    {
        Double value = b.getDouble(key);
        if (value == null)
            value = def;
        return value;
    }

}
