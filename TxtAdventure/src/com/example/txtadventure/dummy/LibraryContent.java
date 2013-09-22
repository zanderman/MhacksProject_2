package com.example.txtadventure.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryContent {

	/**
     * An array of sample (library) items.
     */
    public static List<LibraryItem> ITEMS = new ArrayList<LibraryItem>();

    /**
     * A map of sample (library) items, by ID.
     */
    public static Map<String, LibraryItem> ITEM_MAP = new HashMap<String, LibraryItem>();

    
    static {
    	int numStories = 5;  ///*GET FROM SERVER*/
    	
    	
        // Add # of items that are in story database.
    	for(int i = 0; i < numStories; i++)
    	{
    		// Test for app...
    		addItem(new LibraryItem(" " + i, "Story" + i));
    	}
    	
    }

    private static void addItem(LibraryItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class LibraryItem {
        public String id;
        public String content;

        public LibraryItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }

}
