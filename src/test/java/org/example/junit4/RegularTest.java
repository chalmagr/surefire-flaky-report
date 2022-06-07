package org.example.junit4;

import org.example.Utility;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegularTest
{
    @Test
    public void nonFlakyYLQ() throws Exception
    {
        assertTrue( "Failed to validate input YLQ", Utility.hash( "YLQ" ) );
    }

    @Test
    public void nonFlaky9SK() throws Exception
    {
        assertTrue( "Failed to validate input 9SK", Utility.hash( "9SK" ) );
    }
}
