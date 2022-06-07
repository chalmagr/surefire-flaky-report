package org.example.junit5.parameterized;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.example.TestConstants;
import org.example.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class WithoutDisplayNameTest
{
    static class ParameterSource implements ArgumentsProvider
    {
        @Override
        public Stream<? extends Arguments> provideArguments( ExtensionContext context ) throws Exception
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

            return parameters.stream().map( Arguments::of );
        }
    }

    @ParameterizedTest
    @ArgumentsSource( value = ParameterSource.class )
    void testHashMod5( String inputString ) throws Exception
    {
        Assertions.assertTrue( Utility.hash( inputString ), "Failed to validate input " + inputString );
    }
}