/* This file turns the .xml and "*Content.java" files into operatable GUIs.
 * 
 */



package com.example.txtadventure;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.txtadventure.dummy.LibraryContent;

public class LibraryListFragment extends ListFragment {

	/**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks lCallbacks = sLibraryCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int lActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sLibraryCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LibraryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: replace with a real list adapter.
        setListAdapter(new ArrayAdapter<LibraryContent.LibraryItem>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                LibraryContent.ITEMS));
    }

	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        lCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        lCallbacks = sLibraryCallbacks;
    }

    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        lCallbacks.onItemSelected(LibraryContent.ITEMS.get(position).id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (lActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, lActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        ((AbsListView) getView()).setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            ((AbsListView) getView()).setItemChecked(lActivatedPosition, false);
        } else {
            ((AbsListView) getView()).setItemChecked(position, true);
        }

        lActivatedPosition = position;
    }

}


/*
/**
 * The fragment argument representing the item ID that this fragment
 * represents.
 
public static final String ARG_ITEM_ID = "item_id";

/**
 * The dummy content this fragment is presenting.
 
private LibraryContent.LibraryItem lItem;







private Callbacks lCallbacks = sLibraryCallbacks;


public interface Callbacks {
    /**
     * Callback for when an item has been selected.
     *
    public void onItemSelected(String id);
}

private static Callbacks sLibraryCallbacks = new Callbacks() {
    @Override
    public void onItemSelected(String id) {
    }
};







/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 *
public LibraryListFragment() {
}
// ---------------------------------------------

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey(ARG_ITEM_ID)) {
        // Load the dummy content specified by the fragment
        // arguments. In a real-world scenario, use a Loader
        // to load content from a content provider.
        lItem = LibraryContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
    }
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.library_story_detail, container, false);

    // Show the dummy content as text in a TextView.
    if (lItem != null) {
        //((TextView) rootView.findViewById(R.id.story_list)).setText(lItem.content);
    }

    return rootView;
}
*/