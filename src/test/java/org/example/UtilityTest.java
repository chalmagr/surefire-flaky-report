package org.example;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class UtilityTest
{

   private final String inputString;

   @Parameterized.Parameters(name = "testHashMod5{0}")
   public static List<String> parameters() throws Exception
   {
      List<String> parameters = new ArrayList<>(5);
      Random r = new Random(0);
      byte[] bytes = new byte[16];
      Base64.Encoder encoder = Base64.getEncoder();

      for (int i = 0; i < 5; i++)
      {
         r.nextBytes(bytes);
         parameters.add(encoder.encodeToString(bytes).toUpperCase().substring(0, 3));
      }
      return parameters;
   }

   public UtilityTest(String inputString)
   {
      this.inputString = inputString;
   }

   @Test
   public void testHashMod5() throws Exception
   {
      assertTrue("Failed to validate input " + inputString, Utility.hash(inputString));
   }
}