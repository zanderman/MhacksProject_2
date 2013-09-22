package com.example.txtadventure;

public class Scene {

   private String description;
   private String[] actions;
   private String[] targets;
   private String keyID;
   

   public Scene(String description, String[] actions, String keyID) {
      this.description = description;
      this.actions = actions;
      this.keyID = keyID;
      targets = new String[actions.length];
   }

   public void parseActionTargets(String[] targets) {
      for (int i = 0; i < targets.length; i++) {
         targets[i] = actions[i].substring(actions[i].indexOf("|") + 1,
             actions[i].length());
         actions[i] = actions[i].substring(actions[i].indexOf("|"));
      }
   }

   public String getOption(int opt) {
       if (opt < actions.length)
           return actions[opt];
       return "";
   }
   
   public String getTarget(int opt) {
       if (opt < targets.length)
           return targets[opt];
       return "";
   }
   
   public int getNumActions() {
       return actions.length;
   }
   
   public String getKeyId() {
       return this.keyID;
   }

}