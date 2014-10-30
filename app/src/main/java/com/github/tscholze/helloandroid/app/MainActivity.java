package com.github.tscholze.helloandroid.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


/**
 * Main activity of this app.
 * <p />
 * User: tobias
 * Date: 30/10/14
 * Time: 19:04
 */
public class MainActivity
        extends Activity
{
    public final static String EXTRA_MESSAGE = "com.github.tscholze.helloandroid.app.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Sends an intend to {@link com.github.tscholze.helloandroid.app.ShowMessageActivity}
     *
     * @param view origin
     */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, ShowMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
