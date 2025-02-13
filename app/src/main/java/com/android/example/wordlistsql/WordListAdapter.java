/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.wordlistsql;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Implements a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    /**
     *  Custom view holder with a text view and two buttons.
     */
    private static final String TAG = WordListAdapter.class.getSimpleName();
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_WORD = "WORD";
    private static final String EXTRA_POSITION = "POSITION";
    private WordListOpenHelper mDB;
    private final LayoutInflater mInflater;
    Context mContext;

    public WordListAdapter(Context context, WordListOpenHelper db) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDB = db;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(itemView, mDB);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        WordItem current = mDB.query(position);
        holder.wordItemView.setText(current.getWord());
    }

    @Override
    public int getItemCount() {
        // Placeholder so we can see some mock data.
        return (int) mDB.count();
    }
    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        Button delete_button;
        Button edit_button;
        WordListOpenHelper mDB;

        public WordViewHolder(View itemView, WordListOpenHelper db) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.word);
            delete_button = (Button)itemView.findViewById(R.id.delete_button);
            delete_button.setOnClickListener(new MyButtonOnClickListener(this, wordItemView.getText().toString()));
            edit_button = (Button)itemView.findViewById(R.id.edit_button);
            edit_button.setOnClickListener(new MyButtonOnClickListener(this, wordItemView.getText().toString()));
            mDB = db;
        }
        public void launchEditActivity(int id, String word){
            Intent intent = new Intent(mContext, EditWordActivity.class);
            intent.putExtra(EXTRA_ID, id);
            intent.putExtra(EXTRA_POSITION, id);
            intent.putExtra(EXTRA_WORD, word);
            // Start an empty edit activity.
            ((Activity) mContext).startActivityForResult(
                    intent, MainActivity.WORD_EDIT);
        }

    }

}


