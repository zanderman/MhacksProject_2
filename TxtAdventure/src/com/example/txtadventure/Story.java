import java.util.*;

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
   }

   public void getScenes() {
      //get json and parse
   }

   public Scene makeDecision(String decision) {
       Scene newScene = null;
       for (int i = 0; i < currentScene.getNumActions()); i++) {
          if (decision.equals(currentScene.getOption(i))) {
              String nextID = currentScene.getTarget(i);
              newScene = getSceneById(nextID);
          }
      }
      return newScene;
   }
   
   public Scene getSceneById(String keyID) {
       for (int i = 0; i < scenes.length(); i++) {
           if (keyID.equals(scenes.getIndex(i).getKeyId())) {
               currentScene = scenes.getIndex(i);
               return currentScene;
           }
       }
       return null;
   }
}