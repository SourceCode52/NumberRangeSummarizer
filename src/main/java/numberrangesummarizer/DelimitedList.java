package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class DelimitedList implements NumberRangeSummarizer {
    @Override
    public Collection<Integer> collect(String input) {
        List<Integer> result = new ArrayList<>();

        // Handle null or empty input
        if (input == null || input.isBlank()) {
            return result;
        }

        // Split the input string by commas and parse each number
        String numbers[] = input.split(",");
        for (String n : numbers) {
            result.add(Integer.parseInt(n.trim()));
        }

        return result;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        // Handle null or empty input
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Initialize a StringBuilder to build the result string
        StringBuilder result = new StringBuilder();
        Integer prevNum = null, startNum = null;

        for (Integer num : input) {
            if (prevNum == null) {
                // First number in the collection
                startNum = num;
                prevNum = num;
            } else if (num == prevNum + 1) {
                // Continue the current range
                prevNum = num;

            } else {
                // End the current range and start a new one
                appendGroup(result, startNum, prevNum);

                startNum = num;
                prevNum = num;

            }
        }

        // Append the last range or number
        appendGroup(result, startNum, prevNum);

        // Remove the trailing comma and space, and return the result
        return result.substring(0, result.length() - 2);
    }
    
    // Helper method to append a group of numbers to the result string
    private void appendGroup(StringBuilder sb, int start, int end) {
        if (start == end) {
            sb.append(start).append(", ");
        } else {
            sb.append(start).append("-").append(end).append(", ");
        }
    }
}