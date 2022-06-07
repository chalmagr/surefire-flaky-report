package org.example.junit4.parameterized;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.example.TestConstants;
import org.example.Utility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith( Parameterized.class )
public class WithDisplayNameTest
{
    @Parameterized.Parameters( name = "displayName{0}" )
    public static List<String> parameters() throws Exception
    {
        List<String> parameters = new ArrayList<>();
        Random r = new Random( 0 );
        byte[] bytes = new byte[16];
        Base64.Encoder encoder = Base64.getEncoder();

        for ( int i = 0; i < TestConstants.PARAMETERS; i++ )
        {
            r.nextBytes( bytes );
            parameters.add( encoder.encodeToString( bytes ).toUpperCase().substring( 0, 3 ) );
        }
        return parameters;
    }

    @Parameterized.Parameter
    public String inputString;

    @Test
    public void testHashMod5() throws Exception
    {
        assertTrue( "Failed to validate input " + inputString, Utility.hash( inputString ) );
    }
}