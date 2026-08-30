# Number Range Summarizer

Implementation of `NumberRangeSummarizer`, producing a comma-delimited list of numbers, grouping sequential numbers into ranges.

## Assumptions

- Input is a comma-delimited string of integers (e.g. `"1,3,6,7,8"`).
- Input numbers are assumed to be sorted in ascending order.
- Duplicate or repeated numbers do not break a sequential range.
  (e.g. `"1,2,2,3"` → `"1-3"`)
- Whitespace around numbers/commas is trimmed and tolerated.
- Empty or null input returns an empty result.
- Negative numbers are supported as valid integers.
    - When negative integers are introduced they can cause ambiguity in the output,
      such as "-5--3" or "-1-1"