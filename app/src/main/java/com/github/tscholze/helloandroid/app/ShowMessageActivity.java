package com.github.tscholze.helloandroid.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Displays a given text.
 * <p />
 * User: tobias
 * Date: 30/10/14
 * Time: 19:04
 */
public class ShowMessageActivity
        extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView detailText = (TextView) findViewById(R.id.custom_text);
        detailText.setText(message);
    }
}
