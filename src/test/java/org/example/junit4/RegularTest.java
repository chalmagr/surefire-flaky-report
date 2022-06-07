package org.example.junit4;

import org.example.Utility;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegularTest
{
    @Test
    public void testHashMod5YLQ() throws Exception
    {
        assertTrue( "Failed to validate input YLQ", Utility.hash( "YLQ" ) );
    }

    @Test
    public void testHashMod59SK() throws Exception
    {
        assertTrue( "Failed to validate input 9SK", Utility.hash( "9SK" ) );
    }
}
