package com.github.tscholze.helloandroid.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import at.theengine.android.simple_rss2_android.RSSItem;
import at.theengine.android.simple_rss2_android.SimpleRss2Parser;
import at.theengine.android.simple_rss2_android.SimpleRss2ParserCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * Main activity of this app.
 * <p/>
 * User: tobias
 * Date: 30/10/14
 * Time: 19:04
 */
public class MainActivity
        extends Activity
{
    private final static String FEED_URL = "http://www.codebuddies.de/rss/";
    public final static String EXTRA_MESSAGE = "com.github.tscholze.helloandroid.app.MESSAGE";
    private SimpleRss2ParserCallback onParsedCallback;
    private MainActivity currentActivity;
    private ListView feedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentActivity = this;
        feedItems = (ListView)findViewById(R.id.feedItems);
        feedItems.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                RSSItem selectedItem = (RSSItem)feedItems.getItemAtPosition(position);
                Toast.makeText(currentActivity, selectedItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        new SimpleRss2Parser(FEED_URL, getCallback()).parseAsync();
    }

    /**
     * Describes what happens after the parser finished.
     * On success set adapter from the item list to the view.
     * On error make a toast to inform the user.
     *
     * @return Callback method.
     */
    private SimpleRss2ParserCallback getCallback()
    {
        if (onParsedCallback == null)
        {
            onParsedCallback = new SimpleRss2ParserCallback()
            {

                @Override
                public void onFeedParsed(List<RSSItem> items)
                {
                    feedItems.setAdapter(new RSSItemListAdapter(currentActivity, R.layout.list_item,
                            (ArrayList<RSSItem>)items));
                }

                @Override
                public void onError(Exception ex)
                {
                    Toast.makeText(currentActivity, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };
        }

        return onParsedCallback;
    }

    /**
     * List adapter to create rss item list views.
     */
    private class RSSItemListAdapter
            extends ArrayAdapter<RSSItem>
    {
        private ArrayList<RSSItem> items;
        private Context ctx;
        private int layout;

        public RSSItemListAdapter(Context context, int layout, ArrayList<RSSItem> items)
        {
            super(context, layout, items);
            this.items = items;
            this.ctx = context;
            this.layout = layout;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(layout, null);
            }

            RSSItem item = items.get(position);
            if (item != null)
            {
                TextView tvPubDate = ((TextView)convertView.findViewById(R.id.tvPubDate));
                TextView tvTitle = ((TextView)convertView.findViewById(R.id.tvTitle));
                tvPubDate.setText(item.getDate());
                tvTitle.setText(item.getTitle());
            }

            return convertView;
        }
    }
}
