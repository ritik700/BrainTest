package com.example.ritik.braintest;
import android.os.CountDownTimer;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionText,answerMsg,marksText,timerText;
    Button opt1, opt2, opt3, opt4, goButton;
    ArrayList<Integer> answers;
    Group group3;
    int locationOfCorrectAnswer,totalMarks,earnMarks,i,incorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.textView2);
        marksText = findViewById(R.id.textView3);
        timerText = findViewById(R.id.textView);
        answerMsg = findViewById(R.id.textView4);
        answerMsg.setText("Welcome to the Brain-test!");
        opt1 = findViewById(R.id.button);
        opt2 = findViewById(R.id.button2);
        opt3 = findViewById(R.id.button3);
        opt4 = findViewById(R.id.button4);
        group3 = findViewById(R.id.group3);
        goButton = findViewById(R.id.button5);
        answers = new ArrayList<>();
        marksText.setText(earnMarks+"/"+totalMarks);
        generateQuestion();

    }
    public void startGame(View view)
    {
        group3 = findViewById(R.id.group3);
        Button btn = findViewById(R.id.button5);
        group3.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
        startTimer();


    }
    private void generateQuestion()
    {
        answers.clear();
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        questionText.setText(a+"+"+b+"=?");
        locationOfCorrectAnswer = random.nextInt(4);
        for(i=0;i<4;i++)
        {
            if(locationOfCorrectAnswer==i)
            {
                answers.add(a+b);

            }else {
                incorrectAnswer = random.nextInt(200);
                while(incorrectAnswer == a+b){
                    incorrectAnswer = random.nextInt(200);
                }
                answers.add(incorrectAnswer);
            }

        }
        opt1.setText(answers.get(0)+"");
        opt2.setText(answers.get(1)+"");
        opt3.setText(answers.get(2)+"");
        opt4.setText(answers.get(3)+"");
    }
    public void checkCorrect(View view) {
        Button btn = (Button) view;
        if (btn.getTag().equals(locationOfCorrectAnswer + "")) {
            earnMarks++;
            answerMsg.setText("correct");
            //marksText.setText(String.valueOf(earnMarks)+"/"+String.valueOf(totalMarks));
        }
        else if(!btn.getTag().toString().equals(locationOfCorrectAnswer + "")){

            answerMsg.setText("Incorrect");
        }
        totalMarks++;
            marksText.setText(String.valueOf(earnMarks)+"/"+String.valueOf(totalMarks));


        generateQuestion();
    }
       // if(btn.getTag().toString().equals(locationOfCorrectAnswer + ""))
        //{
            //earnMarks++;

        //}else{
            //answerMsg.setText(earnMarks+"/"+totalMarks);
        //}
        //totalMarks++;
        //marksText.setText(String.valueOf(earnMarks)+"/"+String.valueOf(totalMarks));
        //generateQuestion();

    public void startTimer()
    {
        //Button btn = findViewById(R.id.button5);
        //group3.setVisibility(View.VISIBLE);
        //btn.setVisibility(View.GONE);

    totalMarks=0;
    earnMarks=0;
    timerText.setText("30s");
    marksText.setText("0/0");
    //answerMsg.setText("welcome to the mind test");

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(millisUntilFinished/1000)+"s");
                Log.i("passed-", millisUntilFinished+"");

            }

            @Override
            public void onFinish() {
                timerText.setText(0+"s");
                group3.setVisibility(View.GONE);
                goButton.setText("play again!");
                goButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}
