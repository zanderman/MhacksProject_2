package com.example.txtadventure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.txtadventure.dummy.DummyContent;
import com.example.txtadventure.dummy.LibraryContent;

/**
 * A fragment representing a single Story detail screen.
 * This fragment is either contained in a {@link StoryListActivity}
 * in two-pane mode (on tablets) or a {@link StoryDetailActivity}
 * on handsets.
 */
public class StoryDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    //private LibraryContent.LibraryItem lItem;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StoryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    /* We want to first open the "library_story_detail.xml" file.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_story_detail, container, false);

        // Show the dummy content as text in a TextView.
        //if (mItem != null) {
            //((TextView) rootView.findViewById(R.id.story_detail)).setText(mItem.content);
        //}
        

        return rootView;
    }
}
