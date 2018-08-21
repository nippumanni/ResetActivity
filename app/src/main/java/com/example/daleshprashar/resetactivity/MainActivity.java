package com.example.daleshprashar.resetactivity;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {



        private EditText editText, maineditText, uid, editConfirmPassword;
        private TextView textView, texflagA,texflagB,texflagC,texflagD,texflagE , strongText, textt, confirmErrorText;
        private View view1, view2, view3;
        private View grey100, red25, green100, grey50,grey75, yellow50 ;
        private Button continueButton, actionBarSent;
        private ImageView check1,check2 ,check3,check4, check5,cross1, cross2,cross3,cross4,cross5;
        private TextInputLayout textInputLayout;
        private TextInputEditText textInputEditText;
        boolean isUnique = true;
        float commonChars = 0;
        int[] dataUnique = new int[256];
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initViews();
            actionBar();
            editChanges();
            SnackBar();
        }

        private void initViews()
        {
            maineditText = (EditText) findViewById(R.id.etPassword);
            editConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

            check1 = (ImageView)findViewById(R.id.checkmark1);
            check2 = (ImageView)findViewById(R.id.checkmark2);
            check3 = (ImageView)findViewById(R.id.checkmark3);
            check4 = (ImageView)findViewById(R.id.checkmark4);
            check5 = (ImageView)findViewById(R.id.checkmark5);

            cross1 = (ImageView)findViewById(R.id.cross1);
            cross2 = (ImageView)findViewById(R.id.cross2);
            cross3 = (ImageView)findViewById(R.id.cross3);
            cross4 = (ImageView)findViewById(R.id.cross4);
            cross5 = (ImageView)findViewById(R.id.cross5);

            texflagA = (TextView)findViewById(R.id.flagA650);
            texflagB = (TextView)findViewById(R.id.flagBletter1);
            texflagC = (TextView)findViewById(R.id.flagCNumber1);
            texflagD = (TextView)findViewById(R.id.flagDCharsTwoplus);
            texflagE = (TextView)findViewById(R.id.flagESpecialChars);
            confirmErrorText = (TextView)findViewById(R.id.confirmError);


            red25= (View)findViewById(R.id.linered25);
            grey75= (View)findViewById(R.id.lineBl75);
            grey50= (View)findViewById(R.id.lineBl50);
            yellow50= (View)findViewById(R.id.lineyellow50);
            grey100= (View)findViewById(R.id.lineBl100);
            green100= (View)findViewById(R.id.linegreen100);

            strongText= (TextView)findViewById(R.id.strongText);

            uid =(EditText) findViewById(R.id.userId);

            continueButton = (Button)findViewById(R.id.button);

        }

        private void editChanges() {
            maineditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int strengthMeter =    calculateStrength(s,"aaa");

                    if (strengthMeter <= 40) {
                        red25.setVisibility(View.VISIBLE);
                        grey75.setVisibility(View.VISIBLE);
                        grey50.setVisibility(View.INVISIBLE);
                        yellow50.setVisibility(View.INVISIBLE);
                        grey100.setVisibility(View.INVISIBLE);
                        grey100.setVisibility(View.INVISIBLE);
                        strongText.setVisibility(View.VISIBLE);
                        strongText.setText("Weak");
                    }

                    else if (strengthMeter > 40 && strengthMeter < 90) {
                        yellow50.setVisibility(View.VISIBLE);
                        grey50.setVisibility(View.VISIBLE);
                        red25.setVisibility(View.INVISIBLE);
                        grey75.setVisibility(View.INVISIBLE);
                        green100.setVisibility(View.INVISIBLE);
                        strongText.setVisibility(View.VISIBLE);
                        strongText.setText("Average");
                    }

                    else if (strengthMeter > 90) {
                        green100.setVisibility(View.VISIBLE);
                        grey75.setVisibility(View.INVISIBLE);
                        grey50.setVisibility(View.INVISIBLE);
                        yellow50.setVisibility(View.INVISIBLE);
                        red25.setVisibility(View.INVISIBLE);
                        grey100.setVisibility(View.INVISIBLE);
                        strongText.setVisibility(View.VISIBLE);
                        strongText.setText("Strong");
                    }

                    if (f1between6and50(s)) {
                        check1.setVisibility(View.VISIBLE);
                        cross1.setVisibility(View.INVISIBLE);
                    } else {
                        check1.setVisibility(View.INVISIBLE);
                        cross1.setVisibility(View.VISIBLE);
                    }
                    if (f2checkContainsStrings(s)) {
                        check2.setVisibility(View.VISIBLE);
                        cross2.setVisibility(View.INVISIBLE);
                    } else {
                        check2.setVisibility(View.INVISIBLE);
                        cross2.setVisibility(View.VISIBLE);
                    }
                    if (f3stringContainsNumber(s)) {
                        check3.setVisibility(View.VISIBLE);
                        cross3.setVisibility(View.INVISIBLE);
                    } else {
                        check3.setVisibility(View.INVISIBLE);
                        cross3.setVisibility(View.VISIBLE);
                    }

                    if (s.length()> 0) {
                        check4.setVisibility(View.VISIBLE);
                        cross4.setVisibility(View.INVISIBLE);
                    }

                    if (f4specialMoreThanTwice(s)) {
                        check4.setVisibility(View.INVISIBLE);
                        cross4.setVisibility(View.VISIBLE);
                    } else {
                        check4.setVisibility(View.VISIBLE);
                        cross4.setVisibility(View.INVISIBLE);
                    }


                    if (f5stringContainsSpecialChars(s)) {
                        check5.setVisibility(View.VISIBLE);
                        cross5.setVisibility(View.INVISIBLE);
                    } else {
                        check5.setVisibility(View.INVISIBLE);
                        cross5.setVisibility(View.VISIBLE);
                    }

                    if (Xbooleantruecount(s) == 5) {
                        actionBarSent.setEnabled(true);
                    } else {
                        actionBarSent.setEnabled(false);
                    }

                    if (s.length() == 0) {
                        strongText.setVisibility(View.INVISIBLE);
                        check4.setVisibility(View.INVISIBLE);
                        cross4.setVisibility(View.VISIBLE);
                        red25.setVisibility(View.INVISIBLE);
                        grey100.setVisibility(View.VISIBLE);
                        grey75.setVisibility(View.INVISIBLE);
                        grey50.setVisibility(View.INVISIBLE);
                        yellow50.setVisibility(View.INVISIBLE);
                        green100.setVisibility(View.INVISIBLE);
                    }

                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        // FLAGS
        // flag A
        private static boolean f1between6and50(CharSequence ss) {
            boolean good = false;
            if (ss.length() > 5 && ss.length() < 51) {
                good = true;
            }
            return good;
        }

        // flag B
        private boolean f2checkContainsStrings(CharSequence ss) {
            Pattern p = Pattern.compile("[A-Z]");
            Matcher m = p.matcher(ss);
            Pattern p1 = Pattern.compile("[a-z]");
            Matcher m1 = p1.matcher(ss);
            boolean k = m.find();
            boolean k1 = m1.find();
            boolean t = k || k1;
            return t;
        }

        // FLAG B1 - CHECK UPPER CASE
        private boolean f22checkUpperCase(CharSequence ss) {
            Pattern p = Pattern.compile("[A-Z]");
            Matcher m = p.matcher(ss);
            boolean up = m.find();
            return up;
        }

        // FLAG B1 - CHECK UPPER CASE
        private boolean f22checkLowerCase(CharSequence ss) {
            Pattern p = Pattern.compile("[a-z]");
            Matcher m = p.matcher(ss);
            boolean lower = m.find();
            return lower;
        }

        //flag C
        public boolean f3stringContainsNumber(CharSequence ss) {
            Pattern p = Pattern.compile("[0-9]");
            Matcher m = p.matcher(ss);
            return m.find();
        }

        // Flag D-0
        public int countOccurancesOfPattern(String pattern, CharSequence input) {
            Matcher m = Pattern.compile(pattern).matcher(input);
            int count = 0;
            while (m.find()) {
                count++;
            }
            return count;
        }

        // flag D full
        private boolean f4specialMoreThanTwice(CharSequence sss) {
            boolean acceptable = false;
            int n1 = countOccurancesOfPattern("_", sss);
            int n2 = countOccurancesOfPattern("!", sss);
            int n3 = countOccurancesOfPattern("@", sss);
            int n4 = countOccurancesOfPattern("$", sss);
            if (n1 > 2 || n2 > 2 || n3 > 2 || n4 > 2) {
                acceptable = true;// bad case
            } else {
                acceptable = false; // good case
            }
            return acceptable;

        }

        // Flag E
        public boolean f5stringContainsSpecialChars(CharSequence ss) {
            Pattern p = Pattern.compile("[_!@$]");
            Matcher m = p.matcher(ss);
            return m.find();
        }

        // flag X0
        public int Xbooleantruecount(CharSequence ss) {
            boolean[] fiinal = {f1between6and50(ss), f2checkContainsStrings(ss), f3stringContainsNumber(ss), !f4specialMoreThanTwice(ss), f5stringContainsSpecialChars(ss)};
            int count = 0;
            for (int i = 0; i < fiinal.length; i++) {
                if (fiinal[i]) {
                    count++;
                }
            }
            return count;
        }

        private void actionBar()
        {

            final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.action_bar, null);

            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(actionBarLayout);
            textt = (TextView) findViewById(R.id.headd);

            actionBarSent = (Button) findViewById(R.id.action_bar_sent);
            textt.setText("New Password Required");

            //actionBarSent.setEnabled(false);
            //  actionBarSent.setText("Submit");

            actionBarSent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(maineditText.getText().toString().equals(editConfirmPassword.getText().toString())) {

                    }
                    else {
                        confirmErrorText.setVisibility(View.VISIBLE);
                    }
                }

            });
        }

        public  int calculateStrength (CharSequence s, String userName)
        {
            int passwordStrength = 0;

            if (userName != null && userName.equals(s.toString()))
            {
                passwordStrength = 20;
            }else if(s.length() <8)
            {
                passwordStrength = passwordStrength + s.length()*5;
            }
            else {
                // check Unique
                if (s.length() > 50) {
                    passwordStrength = passwordStrength + 25;
                } else {
                    boolean isUniquee = true;
                    float commonCh = 0;
                    int[] dataUniquee = new int[256];

                    for (int i = 0; i < s.length(); i++) {
                        int val = s.charAt(i);

                        if (dataUniquee[val] == 1) {
                            commonCh++;
                            isUniquee = false;
                        }
                        dataUniquee[val] = 1;
                    }
                    if (isUniquee) {
                        passwordStrength = passwordStrength + 35;
                    } else {
                        passwordStrength += ((s.length() - commonCh) / s.length()) * 35;
                    }
                }
                if (f22checkUpperCase(s)) {
                    passwordStrength += 10;
                }

                if (f22checkLowerCase(s)) {
                    passwordStrength += 10;
                }
                if (f3stringContainsNumber(s)) {
                    passwordStrength += 10;
                }
                if (f5stringContainsSpecialChars(s)) {
                    passwordStrength += 35;
                }
                if (userName != null && !s.toString().contains(userName)) {

                } else {
                    return 50;
                    // passwordStrength = 50;
                }
            }
            return passwordStrength;
        }

        private void SnackBar()
        {
            Snackbar snackbar = Snackbar
                    .make(continueButton, "Message is deleted", Snackbar.LENGTH_LONG);
            snackbar.show();
        }}
