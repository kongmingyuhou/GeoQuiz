package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTrueButton,mFalseButton,mNextButton;
    private TextView mQuestionTextView;
    private Toast showToast;

    private Question[] mQuestionBank=new Question[]{
      new Question(R.string.问题1,true),new Question(R.string.问题2,true),
      new Question(R.string.问题3,true),new Question(R.string.问题4,true),
      new Question(R.string.问题5,true)};

    private int mCurrentIndext=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mNextButton=(Button)findViewById(R.id.next_button);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);

        updateQuestion();

        mTrueButton.setOnClickListener(MainActivity.this);
        mFalseButton.setOnClickListener(MainActivity.this);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndext=(mCurrentIndext+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.true_button:
                showToast=Toast.makeText(MainActivity.this,"回答正确",Toast.LENGTH_SHORT);
                break;
            case R.id.false_button:
                showToast=Toast.makeText(MainActivity.this,"回答错误",Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }
        showToast.setGravity(Gravity.TOP,0,0);
        showToast.show();
    }

    private void updateQuestion()
    {
        int question=mQuestionBank[mCurrentIndext].getTextResId();
        mQuestionTextView.setText(question);
    }
}


