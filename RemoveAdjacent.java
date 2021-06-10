import java.util.stream.IntStream;

public class RemoveAdjacent {

	public static void main(String[] args) {
		String input = "aaaacddegghtttt";
		String firstCharacter = (input.charAt(0) == input.charAt(1)) ? "" : String.valueOf(input.charAt(0));
		String lastCharacter = (input.charAt(input.length()-1) == input.charAt(input.length()-2)) ? "" : String.valueOf(input.charAt(input.length()-1));
		String result = firstCharacter + String.join("", 
			    IntStream.range(1, input.length() - 1)
			        .parallel().mapToObj(i -> input.charAt(i) != input.charAt(i + 1) && input.charAt(i) != input.charAt(i - 1)?
			            String.valueOf(input.charAt(i)) : "")
			        .toArray(size -> new String[size])).toString() + lastCharacter;
		System.out.println(result);
	}
}
