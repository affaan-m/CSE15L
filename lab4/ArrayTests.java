import static org.junit.Assert.*;
import org.junit.*;

public class ArrayTests {
	@Test 
	public void testReverseInPlace() {
    int[] input1 = { 3 };
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{ 3 }, input1);
	}


  @Test
  public void testReversed() {
    int[] input1 = { };
    assertArrayEquals(new int[]{ }, ArrayExamples.reversed(input1));
  }

  @Test
  public void testRIP() { 
    int[] input1 = {4, 3, 1, 1};
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{1, 1, 3, 4}, input1);
  }
  @Test
  public void testReversed2() {
    int[] input1 = {1, 2, 4, 8};
    assertArrayEquals(new int[]{8, 4, 2, 1}, ArrayExamples.reversed(input1));
  }
}
