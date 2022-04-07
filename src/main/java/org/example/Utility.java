package org.example;

public class Utility
{
   private static final String SALT = "someSalt";

   private Utility()
   {
   }

   public static boolean hash(String input)
   {
      int hash = input.concat(SALT).hashCode();
      return hash % 5 != 0;
   }
}
