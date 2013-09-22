package com.example.txtadventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * An activity representing a single Story detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link StoryListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link StoryDetailFragment}.
 */
public class StoryDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        
        String getStoryFeed = getStoryFeed();
        try {
        JSONArray jsonArray = new JSONArray(getStoryFeed);
        Log.i(StoryDetailActivity.class.getName(), "Number of entries " + jsonArray.length());
        for(int i =0; i < jsonArray.length(); i++) {
        	JSONObject jsonObject = jsonArray.getJSONObject(i);
        	Log.i(StoryDetailActivity.class.getName(), jsonObject.getString("text"));
        }
        
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        
        // Once we have determined a story, we can get it's scenes.
        int storyId = 0;  //EDIT LATER w/real scene ID
		String sceneString = "http://mhacks-text-adventure.appspot.com/story/" + storyId + ".json";
        // ^ will need to grab the initial scene in the story during 'onCreate'.


        // ------------ INITIALIZATION -----------
		// Setup each button to be operated on (will need to alter their text once a story is picked).
		TextView t1,t2,t3,t4 = new TextView(this);
		t1=(TextView)findViewById(R.id.choice_1);
		t2=(TextView)findViewById(R.id.choice_2);
		t3=(TextView)findViewById(R.id.choice_3);
		t4=(TextView)findViewById(R.id.choice_4);

        // Initialize the text of each button to be a response.
        t1.setText(/*Response #1*/);
        t2.setText(/*Response #2*/);
        t3.setText(/*Response #3*/);
        t4.setText(/*Response #4*/);
        // ----------------------------------------
        
        

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragmentM and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(StoryDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(StoryDetailFragment.ARG_ITEM_ID));
            StoryDetailFragment fragment = new StoryDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.story_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpTo(this, new Intent(this, StoryListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    

    /* This section of code is for manipulation of each of the software
     * buttons and their associated functions.
    */
    //public TextView t=new TextView(this);
    // ------------- BUTTON ACTIONS ---------------
    public void onClick(View view) {
    	
		// Determines 
        TextView t=new TextView(this);
		t=(TextView)findViewById(R.id.text_dialog);
		
		switch(view.getId()){
		case R.id.choice_1:
			t.setText("Option #1");
			break;
		case R.id.choice_2:
			t.setText("Option #2");
			break;
		case R.id.choice_3:
			t.setText("Option #3");
			break;
		case R.id.choice_4:
			t.setText("Option #4");
			break;
		}
	}
    
    
    // JSON parsing code...
    public String getStoryFeed() {
    	StringBuilder builder = new StringBuilder();
    	HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://mhacks-text-adventure.appspot.com/.json");
        
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                  builder.append(line);
                }
        } else {
            Log.e(StoryDetailActivity.class.toString(), "Failed to download file");
        }
    } catch (ClientProtocolException e) {
        e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
        return builder.toString();
    }
}
    
    
    
    

