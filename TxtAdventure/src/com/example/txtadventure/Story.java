package com.example.txtadventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class Story {
   
   private String title;
   private String author;
   private String description;
   private String keyID;

   private ArrayList<Scene> scenes;
   private Scene currentScene;

   public Story(String title, String author, String description, String keyID) {
      this.title = title;
      this.author = author;
      this.description = description;
      this.keyID = keyID;
      this.scenes = new ArrayList<Scene>();
   }

   public void getScenes() {
	   try {
	        JSONArray jsonArray = new JSONArray(getActions());
	        Log.i(StoryDetailActivity.class.getName(), "Number of entries " + jsonArray.length());
	        for(int i =0; i < jsonArray.length(); i++) {
	        	JSONObject jsonObject = jsonArray.getJSONObject(i);
	        	JSONArray actionArray = jsonObject.getJSONArray("actions");
	        	String[] stringArr = new String[actionArray.length()];
	        	for(int j = 0; j < actionArray.length(); j++) {
	        		stringArr[j] = actionArray.getJSONObject(j).toString();
	        	}
	        	Scene scene = new Scene(jsonObject.getString("description"), stringArr, 
	        			jsonObject.getString("keyID"));
	        	this.scenes.add(scene);
	        	Log.i(StoryDetailActivity.class.getName(), jsonObject.getString("text"));
	        }
	        
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
   }
   
   
   // JSON parsing code...
   public String getActions() {
   	StringBuilder builder = new StringBuilder();
   	HttpClient client = new DefaultHttpClient();
       HttpGet httpGet = new HttpGet("http://mhacks-text-adventure.appspot.com/story/" + this.keyID + ".json");
       
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

   public Scene makeDecision(String decision) {
       Scene newScene = null;
       for (int i = 0; i < currentScene.getNumActions(); i++) {
          if (decision.equals(currentScene.getOption(i))) {
              String nextID = currentScene.getTarget(i);
              newScene = getSceneById(nextID);
          }
      }
      return newScene;
   }
   
   public Scene getSceneById(String keyID) {
       for (int i = 0; i < scenes.size(); i++) {
           if (keyID.equals(scenes.get(i).getKeyId())) {
               currentScene = scenes.get(i);
               return currentScene;
           }
       }
       return null;
   }
}