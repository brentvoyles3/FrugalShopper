package edu.uga.cs.frugalshopper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView editTextPriceA;
    private TextView editTextPriceB;
    private TextView editTextPriceC;

    private TextView editTextPoundA;
    private TextView editTextPoundB;
    private TextView editTextPoundC;

    private TextView editTextOzA;
    private TextView editTextOzB;
    private TextView editTextOzC;

    private TextView editTextPriceLabelA;
    private TextView editTextPriceLabelB;
    private TextView editTextPriceLabelC;


    private TextView frugalPurchase;
    private Button Compare;
    double priceA = 0.00;
    double priceB = 0.00;
    double priceC = 0.00;
    double weightA= 0.00;
    double weightB= 0.00;
    double weightC= 0.00;
    double ounceA = 0.00;
    double ounceB = 0.00;
    double ounceC = 0.00;
    double unitCostA = 0.00;
    double unitCostB = 0.00;
    double unitCostC = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Compare = findViewById( R.id.Compare );
        editTextPriceA =  findViewById(R.id.priceA);
        editTextPriceB = findViewById(R.id.priceB);
        editTextPriceC =  findViewById(R.id.priceC);
        editTextPoundA =  findViewById(R.id.wtPoundA);
        editTextPoundB =  findViewById(R.id.wtPoundB);
        editTextPoundC = findViewById(R.id.wtPoundC);
        editTextOzA =  findViewById(R.id.wtOzA);
        editTextOzB =  findViewById(R.id.wtOzB);
        editTextOzC =  findViewById(R.id.wtOzC);
        editTextPriceLabelA = findViewById(R.id.unitPriceLabelA);
        editTextPriceLabelB =  findViewById(R.id.unitPriceLabelB);
        editTextPriceLabelC = findViewById(R.id.unitPriceLabelC);
        frugalPurchase = findViewById(R.id.frugalBuy);
        Compare.setOnClickListener( (view) -> {

       try {
                priceA = Double.parseDouble(editTextPriceA.getText().toString());
                priceB = Double.parseDouble(editTextPriceB.getText().toString());
                priceC = Double.parseDouble(editTextPriceC.getText().toString());
                weightA = Double.parseDouble(editTextPoundA.getText().toString());
                weightB = Double.parseDouble(editTextPoundB.getText().toString());
                weightC = Double.parseDouble(editTextPoundC.getText().toString());
                ounceA = Double.parseDouble(editTextOzA.getText().toString());
                ounceB = Double.parseDouble(editTextOzB.getText().toString());
                ounceC = Double.parseDouble(editTextOzC.getText().toString());
            } catch (NumberFormatException nfe) {
                Toast toast = Toast.makeText( getApplicationContext(),
                        "Must provide weight in either pounds or ounces for product A.",
                        Toast.LENGTH_SHORT );
                toast.show();
                return;
            }

             if ( priceA != 0.00 && (weightA == 0 && ounceA == 0)) {
                Toast toast = Toast.makeText( getApplicationContext(),
                        "Must provide weight in either pounds or ounces for product A.",
                        Toast.LENGTH_SHORT );
                toast.show();
                return;
            }

            if ((priceB != 0.00) && (weightB == 0.00 && ounceB == 0.00)) {
                Toast toast = Toast.makeText( getApplicationContext(),
                        "Must provide weight in either pounds or ounces for product B.",
                        Toast.LENGTH_SHORT );
                toast.show();
                return;
            }

            if ( priceC != 0.00 && (weightC == 0 && ounceC == 0)) {
                Toast toast = Toast.makeText( getApplicationContext(),
                        "Must provide weight in either pounds or ounces for product C.",
                        Toast.LENGTH_SHORT );
                toast.show();
                return;
            }


            DecimalFormat df = new DecimalFormat( "####0.00" );
            unitCostA = priceA / ((weightA*16) + ounceA);
            String ucA = df.format( unitCostA );
            editTextPriceLabelA.setText("$" + ucA + " per oz.");
            unitCostB = priceB / ((weightB*16) + ounceB);
            String ucB = df.format( unitCostB );
            editTextPriceLabelB.setText("$" + ucB + " per oz.");
            unitCostC = priceC / ((weightC*16) + ounceC);
            String ucC = df.format( unitCostC );
            editTextPriceLabelC.setText("$" + ucC + " per oz.");
            if ((unitCostA < unitCostB) && (unitCostA < unitCostC)){
                frugalPurchase.setText("Product A at $" + ucA + " per oz.");
            } else if ((unitCostB < unitCostA) && (unitCostB < unitCostC)) {
                frugalPurchase.setText("Product B at $"+ ucB + " per oz.");
            } else {
                frugalPurchase.setText("Product C at $"+ ucC + " per oz.");
            }
        });

    }
}