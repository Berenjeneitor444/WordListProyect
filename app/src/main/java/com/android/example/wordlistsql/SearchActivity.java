package com.android.example.wordlistsql;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = SearchActivity.class.getSimpleName();
    private TextView mTextView;
    private EditText mEditWordView;
    private WordListOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mEditWordView = (EditText) findViewById(R.id.search_word);
        mTextView = (TextView) findViewById(R.id.search_result);
        mDB = new WordListOpenHelper(this);

    }
    public void showResult(View view) {
        String word = mEditWordView.getText().toString();
        mTextView.setText("");
        mTextView.append("Search term: " + word + ":\n\n");

        Cursor cursor = mDB.search(word);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int index;
            String result;
            do {
                index = cursor.getColumnIndex(WordListOpenHelper.KEY_WORD);
                result = cursor.getString(index);
                mTextView.append(result + "/n");

            } while (cursor.moveToNext());
            cursor.close();

        }
        else {
            mTextView.append("No results found!");
        }

    }
}