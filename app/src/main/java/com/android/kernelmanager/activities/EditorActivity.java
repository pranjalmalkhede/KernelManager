
package com.android.kernelmanager.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.MenuItem;

import com.android.kernelmanager.R;


public class EditorActivity extends BaseActivity {

    public static final String TITLE_INTENT = "title";
    public static final String TEXT_INTENT = "text";
    private static final String EDITTEXT_INTENT = "edittext";

    private AppCompatEditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        initToolBar();
        String title = getIntent().getStringExtra(TITLE_INTENT);
        if (title != null) {
            getSupportActionBar().setTitle(title);
        }

        CharSequence text = getIntent().getCharSequenceExtra(TEXT_INTENT);
        mEditText = findViewById(R.id.edittext);
        if (text != null) {
            mEditText.append(text);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(EDITTEXT_INTENT, mEditText.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_save);
        DrawableCompat.setTint(drawable, Color.WHITE);
        menu.add(0, Menu.FIRST, Menu.FIRST, getString(R.string.save)).setIcon(drawable)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra(TEXT_INTENT, mEditText.getText());
        setResult(0, intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

}
