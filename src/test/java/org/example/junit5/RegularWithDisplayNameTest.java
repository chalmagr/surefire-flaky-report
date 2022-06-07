package org.example.junit5;

import org.example.Utility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RegularWithDisplayNameTest
{
    @Test
    @DisplayName( "testHashMod" )
    public void nonFlakyYLQ() throws Exception
    {
        assertTrue( Utility.hash( "YLQ" ), "Failed to validate input YLQ" );
    }

    @Test
    @DisplayName( "testHashMod" )
    public void nonFlaky9SK() throws Exception
    {
        assertTrue( Utility.hash( "9SK" ), "Failed to validate input 9SK" );
    }
}
