package org.example;

import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class Utility5Test
{

   static class ParameterSource implements ArgumentsProvider
   {
      @Override
      public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception
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

         return parameters.stream().map(Arguments::of);
      }
   }

   @ParameterizedTest(name = "testHashMod5{0}")
   @ArgumentsSource(value = ParameterSource.class)
   void testHashMod5(String inputString) throws Exception
   {
      assertTrue("Failed to validate input " + inputString, Utility.hash(inputString));
   }
}