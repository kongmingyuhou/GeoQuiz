package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button mTrueButton,mFalseButton;
    private ImageButton mNextButton,mPreButton;
    private TextView mQuestionTextView;
    //private Toast showToast=null;

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
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mPreButton=(ImageButton)findViewById(R.id.pre_button);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);

        updateQuestion(mCurrentIndext);

        mTrueButton.setOnClickListener(MainActivity.this);
        mFalseButton.setOnClickListener(MainActivity.this);
        mNextButton.setOnClickListener(MainActivity.this);
        mPreButton.setOnClickListener(MainActivity.this);
        mQuestionTextView.setOnClickListener(MainActivity.this);
//        mNextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCurrentIndext=(mCurrentIndext+1)%mQuestionBank.length;
//                updateQuestion();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.true_button:
                //showToast=Toast.makeText(MainActivity.this,"回答正确",Toast.LENGTH_SHORT);
                checkAnswer(true);
                break;
            case R.id.false_button:
                //showToast=Toast.makeText(MainActivity.this,"回答错误",Toast.LENGTH_SHORT);
                checkAnswer(false);
                break;
            case R.id.next_button:
            case R.id.question_text_view:
                mCurrentIndext=(mCurrentIndext+1)%mQuestionBank.length;
                updateQuestion(mCurrentIndext);
                break;
            case R.id.pre_button:
                mCurrentIndext=(mCurrentIndext+3)%mQuestionBank.length;
                updateQuestion(mCurrentIndext);
                break;
            default:
                break;
        }
    }

    private void updateQuestion(int currentIndext)
    {
        int question=mQuestionBank[currentIndext].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue=mQuestionBank[mCurrentIndext].isAnswerTrue();

        int messageResId=0;

        if(userPressedTrue==answerIsTrue)
            messageResId=R.string.toast_正确;
        else
            messageResId=R.string.toast_错误;

        Toast showToast = Toast.makeText(this,messageResId,Toast.LENGTH_SHORT);
        showToast.setGravity(Gravity.TOP,0,0);
        showToast.show();
    }
}


