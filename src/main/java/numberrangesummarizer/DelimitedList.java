package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class DelimitedList implements NumberRangeSummarizer {
    @Override
    public Collection<Integer> collect(String input) {
        List<Integer> result = new ArrayList<>();
        if (input == null || input.isBlank()) {
            return result;
        }

        String numbers[] = input.split(",");
        for (String n : numbers) {
            result.add(Integer.parseInt(n.trim()));
        }

        return result;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Integer prevNum = null, startNum = null;

        for (Integer num : input) {
            if (prevNum == null) {
                startNum = num;
                prevNum = num;
            } else if (num == prevNum + 1) {
                prevNum = num;

            } else {
                appendGroup(result, startNum, prevNum);

                startNum = num;
                prevNum = num;

            }
        }

        appendGroup(result, startNum, prevNum);

        return result.substring(0, result.length() - 2);
    }
    
    private void appendGroup(StringBuilder sb, int start, int end) {
        if (start == end) {
            sb.append(start).append(", ");
        } else {
            sb.append(start).append("-").append(end).append(", ");
        }
    }
}