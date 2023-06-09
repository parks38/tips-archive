/**
    이진탐색 
 */
class 2148_CountElementWithSmallerAndCreaterElement {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0; 
        int right = letters.length - 1;

        while (left <= right) {
            int mid = (right + left)/2;

            if (letters[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // There are no characters in letters that is lexicographically greater than 'z' so we return letters[0].
        if(left == letters.length) return letters[0];
            
        return letters[left];
    }
}