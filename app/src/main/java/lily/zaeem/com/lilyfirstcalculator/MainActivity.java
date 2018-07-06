package lily.zaeem.com.lilyfirstcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonZero;
    Button buttonClear, buttonBack, buttonCalculate, buttonPlus, buttonMinus;

    TextView inputScreen, outputScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateViews();
    }

    private void setTextToInputScreen( String text ){
        String currentInputText = inputScreen.getText().toString();
        inputScreen.setText(currentInputText + text );
    }

    private int performAddition(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }

    private int preformCalculation(){

        int index = 0;
        String currentInputText = inputScreen.getText().toString();
        ArrayList<String> tokenizedString = tokenize("\\+", currentInputText);
        for( String s: tokenizedString ) {
            if(s.contains("-")) {
                ArrayList<String> allSubtractions = tokenize("\\-", s);
                int number = Integer.parseInt(allSubtractions.get(0));
                for( int walker=1; walker < allSubtractions.size(); walker++) {
                    number -= Integer.parseInt(allSubtractions.get(walker));
                }

                tokenizedString.set(index, String.valueOf(number));
            }
            index++;
        }

        int sum = Integer.parseInt(tokenizedString.get(0));
        for( int walker=1; walker < tokenizedString.size(); walker++) {
            sum += Integer.parseInt(tokenizedString.get(walker));
        }
        return sum;
    }

    private ArrayList<String> tokenize(String delimiter, String str) {
        String[] splitted = str.split(delimiter);

        ArrayList<String> resultString =  new ArrayList<>();
        Collections.addAll(resultString, splitted);
        return resultString;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private void handleBackButton() {
        String currentInputText = inputScreen.getText().toString();
        CharSequence newString = "";

        newString = inputScreen.getText().length() > 0 ? currentInputText.subSequence(0, currentInputText.length()-1) : "";

        inputScreen.setText("");
        setTextToInputScreen(newString.toString());
    }

    private void initiateViews() {

        buttonOne = (Button) findViewById(R.id.button1);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen(getString(R.string.one));
            }
        });
        buttonTwo = (Button) findViewById(R.id.button2);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("2");
            }
        });

        buttonThree = (Button) findViewById(R.id.button3);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("3");
            }
        });

        buttonFour = (Button) findViewById(R.id.button4);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("4");
            }
        });

        buttonFive = (Button) findViewById(R.id.button5);
        buttonFive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("5");
            }
        });

        buttonSix = (Button) findViewById(R.id.button6);
        buttonSix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("6");
            }
        });

        buttonSeven = (Button) findViewById(R.id.button7);
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("7");
            }
        });

        buttonEight = (Button) findViewById(R.id.button8);
        buttonEight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("8");
            }
        });

        buttonNine = (Button) findViewById(R.id.button9);
        buttonNine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("9");
            }
        });

        buttonZero = (Button) findViewById(R.id.button0);
        buttonZero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("0");
            }
        });

        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("+");
            }
        });

        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTextToInputScreen("-");
            }
        });
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleBackButton();
            }
        });

        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputScreen.setText("");
                outputScreen.setText("");
            }
        });

        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                outputScreen.setText( String.valueOf(preformCalculation()));
                Toast.makeText(getApplicationContext(), String.valueOf(preformCalculation()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        inputScreen = (TextView) findViewById(R.id.inputScreen);
        outputScreen = (TextView) findViewById(R.id.outputScreen);
    }
}
