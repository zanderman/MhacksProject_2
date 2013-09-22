package com.example.txtadventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class LibraryListActivity extends FragmentActivity

	 implements LibraryListFragment.Callbacks {

		    /**
		     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
		     * device.
		     */
		    private boolean mTwoPane;

		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_story_list);

		        if (findViewById(R.id.story_detail_container) != null) {
		            // The detail container view will be present only in the
		            // large-screen layouts (res/values-large and
		            // res/values-sw600dp). If this view is present, then the
		            // activity should be in two-pane mode.
		            mTwoPane = true;

		            // In two-pane mode, list items should be given the
		            // 'activated' state when touched.
		            ((StoryListFragment) getSupportFragmentManager()
		                    .findFragmentById(R.id.story_list))
		                    .setActivateOnItemClick(true);
		        }

		        // TODO: If exposing deep links into your app, handle intents here.
		    }

		    /**
		     * Callback method from {@link StoryListFragment.Callbacks}
		     * indicating that the item with the given ID was selected.
		     */
		    public void onItemSelected(String id) {
		        if (mTwoPane) {
		            // In two-pane mode, show the detail view in this activity by
		            // adding or replacing the detail fragment using a
		            // fragment transaction.
		            Bundle arguments = new Bundle();
		            arguments.putString(StoryDetailFragment.ARG_ITEM_ID, id);
		            StoryDetailFragment fragment = new StoryDetailFragment();
		            fragment.setArguments(arguments);
		            getSupportFragmentManager().beginTransaction()
		                    .replace(R.id.story_detail_container, fragment)
		                    .commit();

		        } else {
		            // In single-pane mode, simply start the detail activity
		            // for the selected item ID.
		            Intent detailIntent = new Intent(this, StoryDetailActivity.class);
		            detailIntent.putExtra(StoryDetailFragment.ARG_ITEM_ID, id);
		            startActivity(detailIntent);
		        }
		    }

}
