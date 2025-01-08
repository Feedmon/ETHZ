package dynamic_programming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class Fall2020ExamPalindromTest {

    @ParameterizedTest
    @MethodSource("providePalindromes")
    public void testPalindrome(String input,int expectedPalLength) {
        assertEquals(expectedPalLength, Fall2020ExamPalindrom.getLongestPalindromeSubsequence(input.toCharArray(), input.length()));
    }

    private static Stream<Arguments> providePalindromes() {
        return Stream.of(
                Arguments.of("ETZHEEHU", 4),
                Arguments.of("ETHZE", 3),
                Arguments.of("RTRREEUR", 4),
                Arguments.of("RACECAR", 7),
                Arguments.of("BANANA", 5),
                Arguments.of("ABCBA", 5),
                Arguments.of("CHARACTER", 5),
                Arguments.of("DEED", 4),
                Arguments.of("ABBA", 4),
                Arguments.of("ABCDEFG", 1)
        );
    }
}
