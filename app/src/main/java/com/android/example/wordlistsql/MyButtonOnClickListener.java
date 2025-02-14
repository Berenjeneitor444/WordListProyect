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
import android.content.Intent;
import android.view.View;

/**
 * Instantiated for the Edit and Delete buttons in WordListAdapter.
 */
public class MyButtonOnClickListener implements View.OnClickListener {
    private static final String TAG = View.OnClickListener.class.getSimpleName();

    int id;
    String word;
    private WordListAdapter.WordViewHolder holder;
    public MyButtonOnClickListener(WordListAdapter.WordViewHolder holder) {
        this.holder = holder;
    }

    public void onClick(View v) {
        this.id = holder.getAbsoluteAdapterPosition();
        this.word = holder.wordItemView.getText().toString();
        if (v.getId() == R.id.delete_button) {
            holder.mDB.delete(id);
            holder.mFather.notifyDataSetChanged();
        }
        if (v.getId() == R.id.edit_button) {
            holder.launchEditActivity(holder.getAbsoluteAdapterPosition(), word);
        }
    }
}
