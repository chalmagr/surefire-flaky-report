package org.example.junit5;

import org.example.Utility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RegularTest
{
    @Test
    public void testHashMod5YLQ() throws Exception
    {
        assertTrue( Utility.hash( "YLQ" ), "Failed to validate input YLQ" );
    }

    @Test
    public void testHashMod59SK() throws Exception
    {
        assertTrue( Utility.hash( "9SK" ), "Failed to validate input 9SK" );
    }
}