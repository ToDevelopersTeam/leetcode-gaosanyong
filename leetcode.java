import java.math.BigInteger; 
import java.util.AbstractMap;
import java.util.HashMap;

class Solution {

    /*1. Two Sum (Easy)
    Given an array of integers nums and an integer target, return indices of 
    the two numbers such that they add up to target. You may assume that each 
    input would have exactly one solution, and you may not use the same element 
    twice. You can return the answer in any order.

    Example 1:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

    Example 2:
    Input: nums = [3,2,4], target = 6
    Output: [1,2]

    Example 3:
    Input: nums = [3,3], target = 6
    Output: [0,1]

    Constraints:
    * 2 <= nums.length <= 10^4
    * -10^9 <= nums[i] <= 10^9
    * -10^9 <= target <= 10^9
    * Only one valid answer exists.

    Follow-up: Can you come up with an algorithm that is less than O(n2) time 
               complexity?*/

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>(); 
        for (int i = 0; i < nums.length; ++i) {
            int diff = target - nums[i]; 
            if (seen.containsKey(diff)) return new int[]{seen.get(diff), i}; 
            seen.put(nums[i], i); 
        }
        return null; 
    }


    /*9. Palindrome Number (Easy)
    Given an integer x, return true if x is a palindrome, and false otherwise.

    Example 1:
    Input: x = 121
    Output: true
    Explanation: 121 reads as 121 from left to right and from right to left.

    Example 2:
    Input: x = -121
    Output: false
    Explanation: From left to right, it reads -121. From right to left, it 
                 becomes 121-. Therefore it is not a palindrome.

    Example 3:
    Input: x = 10
    Output: false
    Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

    Constraints: -2^31 <= x <= 2^31 - 1

    Follow up: Could you solve it without converting the integer to a string?*/

    public boolean isPalindrome(int x) {
        if (x % 10 == 0 && x != 0 || x < 0) return false; 
        int rev = 0; 
        for (; x > rev; x /= 10) 
            rev = 10*rev + x%10; 
        return x == rev || x == rev/10; 
    }


    /*13. Roman to Integer (Easy)
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D 
    and M.
        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
    For example, 2 is written as II in Roman numeral, just two ones added 
    together. 12 is written as XII, which is simply X + II. The number 27 is 
    written as XXVII, which is XX + V + II. Roman numerals are usually written 
    largest to smallest from left to right. However, the numeral for four is 
    not IIII. Instead, the number four is written as IV. Because the one is 
    before the five we subtract it making four. The same principle applies to 
    the number nine, which is written as IX. There are six instances where 
    subtraction is used:
    * I can be placed before V (5) and X (10) to make 4 and 9. 
    * X can be placed before L (50) and C (100) to make 40 and 90. 
    * C can be placed before D (500) and M (1000) to make 400 and 900.
    Given a roman numeral, convert it to an integer.

    Example 1:
    Input: s = "III"
    Output: 3
    Explanation: III = 3.

    Example 2:
    Input: s = "LVIII"
    Output: 58
    Explanation: L = 50, V= 5, III = 3.

    Example 3:
    Input: s = "MCMXCIV"
    Output: 1994
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

    Constraints:
    * 1 <= s.length <= 15
    * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
    * It is guaranteed that s is a valid roman numeral in the range [1, 3999].*/

    public int romanToInt(String s) {
        HashMap<Character, Integer> mp = new HashMap<>() {{
            put('I', 1); 
            put('V', 5); 
            put('X', 10); 
            put('L', 50); 
            put('C', 100); 
            put('D', 500); 
            put('M', 1000); 
        }}; 
        int ans = 0; 
        for (int i = 0; i < s.length(); ++i) {
            if (i+1 < s.length() && mp.get(s.charAt(i)) < mp.get(s.charAt(i+1))) ans -= mp.get(s.charAt(i)); 
            else ans += mp.get(s.charAt(i)); 
        }
        return ans; 
    }


    /*14. Longest Common Prefix (Easy)
    Write a function to find the longest common prefix string amongst an array 
    of strings. If there is no common prefix, return an empty string "".

    Example 1:
    Input: strs = ["flower","flow","flight"]
    Output: "fl"

    Example 2:
    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.

    Constraints:
    * 1 <= strs.length <= 200
    * 0 <= strs[i].length <= 200
    * strs[i] consists of only lowercase English letters.*/

    public String longestCommonPrefix(String[] strs) {
        String lo = Collections.min(Arrays.asList(strs)), hi = Collections.max(Arrays.asList(strs)); 
        int i = 0; 
        for (; i < lo.length() && i < hi.length() && lo.charAt(i) == hi.charAt(i); ++i); 
        return lo.substring(0, i); 
    }


    /*20. Valid Parentheses (Easy)
    Given a string s containing just the characters '(', ')', '{', '}', '[' and 
    ']', determine if the input string is valid. An input string is valid if:
    * Open brackets must be closed by the same type of brackets.
    * Open brackets must be closed in the correct order.
    * Every close bracket has a corresponding open bracket of the same type.

    Example 1:
    Input: s = "()"
    Output: true

    Example 2:
    Input: s = "()[]{}"
    Output: true

    Example 3:
    Input: s = "(]"
    Output: false

    Constraints:
    * 1 <= s.length <= 10^4
    * s consists of parentheses only '()[]{}'.*/

    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>(); 
        for (char ch : s.toCharArray()) {
            if (ch == '(') stk.push(')'); 
            else if (ch == '[') stk.push(']'); 
            else if (ch == '{') stk.push('}'); 
            else if (stk.empty() || stk.pop() != ch) return false; 
        }
        return stk.empty(); 
    }


    /*21. Merge Two Sorted Lists (Easy)
    You are given the heads of two sorted linked lists list1 and list2. Merge 
    the two lists in a one sorted list. The list should be made by splicing 
    together the nodes of the first two lists. Return the head of the merged 
    linked list.

    Example 1:
    Input: list1 = [1,2,4], list2 = [1,3,4]
    Output: [1,1,2,3,4,4]

    Example 2:
    Input: list1 = [], list2 = []
    Output: []

    Example 3:
    Input: list1 = [], list2 = [0]
    Output: [0]

    Constraints:
    * The number of nodes in both lists is in the range [0, 50].
    * -100 <= Node.val <= 100
    * Both list1 and list2 are sorted in non-decreasing order.*/

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(), node = dummy; 
        while (list1 != null && list2 != null) 
            if (list1.val < list2.val) {
                node = node.next = list1; 
                list1 = list1.next; 
            } else {
                node = node.next = list2; 
                list2 = list2.next; 
            }
        if (list1 != null) node.next = list1; 
        if (list2 != null) node.next = list2; 
        return dummy.next; 
    }


    /*26. Remove Duplicates from Sorted Array (Easy)
    Given an integer array nums sorted in non-decreasing order, remove the 
    duplicates in-place such that each unique element appears only once. The 
    relative order of the elements should be kept the same. Since it is 
    impossible to change the length of the array in some languages, you must 
    instead have the result be placed in the first part of the array nums. More 
    formally, if there are k elements after removing the duplicates, then the 
    first k elements of nums should hold the final result. It does not matter 
    what you leave beyond the first k elements. Return k after placing the 
    final result in the first k slots of nums. Do not allocate extra space for 
    another array. You must do this by modifying the input array in-place with 
    O(1) extra memory.

    Custom Judge:
    The judge will test your solution with the following code:

    int[] nums = [...]; // Input array
    int[] expectedNums = [...]; // The expected answer with correct length

    int k = removeDuplicates(nums); // Calls your implementation

    assert k == expectedNums.length;
    for (int i = 0; i < k; i++) {
        assert nums[i] == expectedNums[i];
    }
    If all assertions pass, then your solution will be accepted.

    Example 1:
    Input: nums = [1,1,2]
    Output: 2, nums = [1,2,_]
    Explanation: Your function should return k = 2, with the first two elements 
                 of nums being 1 and 2 respectively. It does not matter what 
                 you leave beyond the returned k (hence they are underscores).
    
    Example 2:
    Input: nums = [0,0,1,1,1,2,2,3,3,4]
    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
    Explanation: Your function should return k = 5, with the first five 
                 elements of nums being 0, 1, 2, 3, and 4 respectively. It does 
                 not matter what you leave beyond the returned k (hence they 
                 are underscores).

    Constraints:
    * 1 <= nums.length <= 3 * 10^4
    * -100 <= nums[i] <= 100
    * nums is sorted in non-decreasing order.*/

    public int removeDuplicates(int[] nums) {
        int k = 0; 
        for (int x : nums) 
            if (k == 0 || nums[k-1] < x) nums[k++] = x; 
        return k; 
    }


    /*27. Remove Element (Easy)
    Given an integer array nums and an integer val, remove all occurrences of 
    val in nums in-place. The relative order of the elements may be changed.
    Since it is impossible to change the length of the array in some languages, 
    you must instead have the result be placed in the first part of the array 
    nums. More formally, if there are k elements after removing the duplicates, 
    then the first k elements of nums should hold the final result. It does 
    not matter what you leave beyond the first k elements. Return k after 
    placing the final result in the first k slots of nums. Do not allocate 
    extra space for another array. You must do this by modifying the input 
    array in-place with O(1) extra memory.

    Custom Judge:
    The judge will test your solution with the following code:

    int[] nums = [...]; // Input array
    int val = ...; // Value to remove
    int[] expectedNums = [...]; // The expected answer with correct length.
                                // It is sorted with no values equaling val.

    int k = removeElement(nums, val); // Calls your implementation

    assert k == expectedNums.length;
    sort(nums, 0, k); // Sort the first k elements of nums
    for (int i = 0; i < actualLength; i++) {
        assert nums[i] == expectedNums[i];
    }
    If all assertions pass, then your solution will be accepted.

    Example 1:
    Input: nums = [3,2,2,3], val = 3
    Output: 2, nums = [2,2,_,_]
    Explanation: Your function should return k = 2, with the first two elements 
                 of nums being 2. It does not matter what you leave beyond the 
                 returned k (hence they are underscores).
    
    Example 2:
    Input: nums = [0,1,2,2,3,0,4,2], val = 2
    Output: 5, nums = [0,1,4,0,3,_,_,_]
    Explanation: Your function should return k = 5, with the first five 
                 elements of nums containing 0, 0, 1, 3, and 4. Note that the 
                 five elements can be returned in any order. It does not matter 
                 what you leave beyond the returned k (hence they are 
                 underscores).

    Constraints:
    * 0 <= nums.length <= 100
    * 0 <= nums[i] <= 50
    * 0 <= val <= 100*/

    public int removeElement(int[] nums, int val) {
        int k = 0; 
        for (int x : nums) 
            if (x != val) nums[k++] = x; 
        return k; 
    }


    /*35. Search Insert Position (Easy)
    Given a sorted array of distinct integers and a target value, return the 
    index if the target is found. If not, return the index where it would be if 
    it were inserted in order. You must write an algorithm with O(log n) 
    runtime complexity.

    Example 1:
    Input: nums = [1,3,5,6], target = 5
    Output: 2

    Example 2:
    Input: nums = [1,3,5,6], target = 2
    Output: 1

    Example 3:
    Input: nums = [1,3,5,6], target = 7
    Output: 4

    Constraints:
    * 1 <= nums.length <= 10^4
    * -10^4 <= nums[i] <= 10^4
    * nums contains distinct values sorted in ascending order.
    * -10^4 <= target <= 10^4*/

    public int searchInsert(int[] nums, int target) {
        int k = Arrays.binarySearch(nums, target); 
        return k >= 0 ? k : -k-1; 
    }


    /*45. Jump Game II (Medium)
    You are given a 0-indexed array of integers nums of length n. You are 
    initially positioned at nums[0]. Each element nums[i] represents the 
    maximum length of a forward jump from index i. In other words, if you are 
    at nums[i], you can jump to any nums[i + j] where:
    * 0 <= j <= nums[i] and
    * i + j < n
    Return the minimum number of jumps to reach nums[n - 1]. The test cases are 
    generated such that you can reach nums[n - 1].

    Example 1:
    Input: nums = [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2. Jump 
                 1 step from index 0 to 1, then 3 steps to the last index.
    
    Example 2:
    Input: nums = [2,3,0,1,4]
    Output: 2

    Constraints:
    * 1 <= nums.length <= 10^4
    * 0 <= nums[i] <= 1000*/

    public int jump(int[] nums) {
        int ans = 0; 
        for (int i = 0, prev = 0, curr = 0; i < nums.length; ++i) {
            if (prev < i) {
                ++ans; 
                prev = curr; 
            }
            curr = Math.max(curr, i + nums[i]); 
        }
        return ans; 
    }
    

    /*55. Jump Game (Medium)
    You are given an integer array nums. You are initially positioned at the 
    array's first index, and each element in the array represents your maximum 
    jump length at that position. Return true if you can reach the last index, 
    or false otherwise.

    Example 1:
    Input: nums = [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

    Example 2:
    Input: nums = [3,2,1,0,4]
    Output: false
    Explanation: You will always arrive at index 3 no matter what. Its maximum 
                 jump length is 0, which makes it impossible to reach the last 
                 index.

    Constraints:
    * 1 <= nums.length <= 10^4
    * 0 <= nums[i] <= 10^5*/

    public boolean canJump(int[] nums) {
        for (int i = 0, ii = 0; i < nums.length; ++i) {
            if (ii < i) return false; 
            ii = Math.max(ii, i+nums[i]); 
        }
        return true; 
    }


    /*58. Length of Last Word (Easy)
    Given a string s consisting of words and spaces, return the length of the 
    last word in the string. A word is a maximal substring consisting of non-
    space characters only.

    Example 1:
    Input: s = "Hello World"
    Output: 5
    Explanation: The last word is "World" with length 5.

    Example 2:
    Input: s = "   fly me   to   the moon  "
    Output: 4
    Explanation: The last word is "moon" with length 4.

    Example 3:
    Input: s = "luffy is still joyboy"
    Output: 6
    Explanation: The last word is "joyboy" with length 6.

    Constraints:
    * 1 <= s.length <= 10^4
    * s consists of only English letters and spaces ' '.
    * There will be at least one word in s.*/

    public int lengthOfLastWord(String s) {
        String[] words = s.split(" "); 
        return words[words.length-1].length(); 
    }


    /*66. Plus One (Easy)
    You are given a large integer represented as an integer array digits, where 
    each digits[i] is the ith digit of the integer. The digits are ordered from 
    most significant to least significant in left-to-right order. The large 
    integer does not contain any leading 0's. Increment the large integer by 
    one and return the resulting array of digits.

    Example 1:
    Input: digits = [1,2,3]
    Output: [1,2,4]
    Explanation: The array represents the integer 123. Incrementing by one 
                 gives 123 + 1 = 124. Thus, the result should be [1,2,4].
    
    Example 2:
    Input: digits = [4,3,2,1]
    Output: [4,3,2,2]
    Explanation: The array represents the integer 4321. Incrementing by one 
                 gives 4321 + 1 = 4322. Thus, the result should be [4,3,2,2].
    
    Example 3:
    Input: digits = [9]
    Output: [1,0]
    Explanation: The array represents the integer 9. Incrementing by one gives 
                 9 + 1 = 10. Thus, the result should be [1,0].

    Constraints:
    * 1 <= digits.length <= 100
    * 0 <= digits[i] <= 9
    * digits does not contain any leading 0's.*/

    public int[] plusOne(int[] digits) {
        int carry = 1; 
        for (int i = digits.length-1; i >= 0; --i, carry /= 10) {
            carry += digits[i]; 
            digits[i] = carry % 10; 
        }
        if (carry == 0) return digits; 
        int[] ans = new int[digits.length+1]; 
        ans[0] = 1; 
        return ans; 
    }


    /*67. Add Binary (Easy)
    Given two binary strings a and b, return their sum as a binary string.

    Example 1:
    Input: a = "11", b = "1"
    Output: "100"

    Example 2:
    Input: a = "1010", b = "1011"
    Output: "10101"

    Constraints:
    * 1 <= a.length, b.length <= 10^4
    * a and b consist only of '0' or '1' characters.
    * Each string does not contain leading zeros except for the zero itself.*/

    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder(); 
        for (int i = a.length()-1, j = b.length()-1, carry = 0; 0 <= i || 0 <= j || carry > 0; carry /= 2) {
            if (0 <= i) carry += a.charAt(i--) - '0'; 
            if (0 <= j) carry += b.charAt(j--) - '0'; 
            ans.append(carry % 2);
        }
        return ans.reverse().toString(); 
    }


    /*69. Sqrt(x) (Easy)
    Given a non-negative integer x, return the square root of x rounded down to 
    the nearest integer. The returned integer should be non-negative as well.
    You must not use any built-in exponent function or operator. For example, 
    do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

    Example 1:
    Input: x = 4
    Output: 2
    Explanation: The square root of 4 is 2, so we return 2.

    Example 2:
    Input: x = 8
    Output: 2
    Explanation: The square root of 8 is 2.82842..., and since we round it down 
                 to the nearest integer, 2 is returned.

    Constraints: 0 <= x <= 2^31 - 1*/

    public int mySqrt(int x) {
        long ans = x; 
        while (ans * ans > x) 
            ans = (ans + x/ans)/2; 
        return (int) ans;
    }


    /*70. Climbing Stairs (Easy)
    You are climbing a staircase. It takes n steps to reach the top. Each time 
    you can either climb 1 or 2 steps. In how many distinct ways can you climb 
    to the top?

    Example 1:
    Input: n = 2
    Output: 2
    Explanation: There are two ways to climb to the top.
                 1. 1 step + 1 step
                 2. 2 steps
    
    Example 2:
    Input: n = 3
    Output: 3
    Explanation: There are three ways to climb to the top.
                 1. 1 step + 1 step + 1 step
                 2. 1 step + 2 steps
                 3. 2 steps + 1 step

    Constraints: 1 <= n <= 45*/

    public int climbStairs(int n) {
        int f0 = 1, f1 = 1; 
        for (int i = 1; i <= n-1; ++i) {
            int ff = f0; 
            f0 = f1; 
            f1 = ff + f1; 
        }
        return f1; 
    }


    /*72. Edit Distance (Hard)
    Given two strings word1 and word2, return the minimum number of operations 
    required to convert word1 to word2. You have the following three operations 
    permitted on a word:
    * Insert a character
    * Delete a character
    * Replace a character

    Example 1:
    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation: horse -> rorse (replace 'h' with 'r')
                 rorse -> rose (remove 'r')
                 rose -> ros (remove 'e')
    
    Example 2:
    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation: intention -> inention (remove 't')
                 inention -> enention (replace 'i' with 'e')
                 enention -> exention (replace 'n' with 'x')
                 exention -> exection (replace 'n' with 'c')
                 exection -> execution (insert 'u')

    Constraints:
    * 0 <= word1.length, word2.length <= 500
    * word1 and word2 consist of lowercase English letters.*/

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1]; 
        for (int i = 0; i < m; ++i) dp[i][n] = m-i; 
        for (int j = 0; j < n; ++j) dp[m][j] = n-j; 
        for (int i  = m-1; i >= 0; --i) 
            for (int j = n-1; j >= 0; --j)
                if (word1.charAt(i) == word2.charAt(j)) dp[i][j] = dp[i+1][j+1]; 
                else dp[i][j] = 1 + Math.min(dp[i+1][j], Math.min(dp[i+1][j+1], dp[i][j+1])); 
        return dp[0][0]; 
    }


    /*83. Remove Duplicates from Sorted List (Easy)
    Given the head of a sorted linked list, delete all duplicates such that 
    each element appears only once. Return the linked list sorted as well.

    Example 1:
    Input: head = [1,1,2]
    Output: [1,2]

    Example 2:
    Input: head = [1,1,2,3,3]
    Output: [1,2,3]

    Constraints:
    * The number of nodes in the list is in the range [0, 300].
    * -100 <= Node.val <= 100
    * The list is guaranteed to be sorted in ascending order.*/

    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head; 
        while (node != null)
            if (node.next != null && node.val == node.next.val) node.next = node.next.next; 
            else node = node.next; 
        return head; 
    }


    /*88. Merge Sorted Array (Easy)
    You are given two integer arrays nums1 and nums2, sorted in non-decreasing 
    order, and two integers m and n, representing the number of elements in 
    nums1 and nums2 respectively. Merge nums1 and nums2 into a single array 
    sorted in non-decreasing order. The final sorted array should not be 
    returned by the function, but instead be stored inside the array nums1. To 
    accommodate this, nums1 has a length of m + n, where the first m elements 
    denote the elements that should be merged, and the last n elements are set 
    to 0 and should be ignored. nums2 has a length of n.

    Example 1:
    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    Output: [1,2,2,3,5,6]
    Explanation: The arrays we are merging are [1,2,3] and [2,5,6]. The result 
                 of the merge is [1,2,2,3,5,6] with the underlined elements 
                 coming from nums1.
    
    Example 2:
    Input: nums1 = [1], m = 1, nums2 = [], n = 0
    Output: [1]
    Explanation: The arrays we are merging are [1] and []. The result of the 
                 merge is [1].
    
    Example 3:
    Input: nums1 = [0], m = 0, nums2 = [1], n = 1
    Output: [1]
    Explanation: The arrays we are merging are [] and [1]. The result of the 
                 merge is [1]. Note that because m = 0, there are no elements 
                 in nums1. The 0 is only there to ensure the merge result can 
                 fit in nums1.

    Constraints:
    * nums1.length == m + n
    * nums2.length == n
    * 0 <= m, n <= 200
    * 1 <= m + n <= 200
    * -10^9 <= nums1[i], nums2[j] <= 10^9

    Follow up: Can you come up with an algorithm that runs in O(m + n) time?*/

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (n > 0) 
            if (m > 0 && nums1[m-1] >= nums2[n-1]) nums1[m+n-1] = nums1[--m]; 
            else nums1[m+n-1] = nums2[--n]; 
    }


    /*94. Binary Tree Inorder Traversal (Easy)
    Given the root of a binary tree, return the inorder traversal of its nodes' 
    values.

    Example 1:
    Input: root = [1,null,2,3]
    Output: [1,3,2]

    Example 2:
    Input: root = []
    Output: []

    Example 3:
    Input: root = [1]
    Output: [1]

    Constraints:
    * The number of nodes in the tree is in the range [0, 100].
    * -100 <= Node.val <= 100

    Follow up: Recursive solution is trivial, could you do it iteratively?*/

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList(); 
        TreeNode node = root; 
        Stack<TreeNode> stack = new Stack(); 
        while (node != null || !stack.isEmpty()) 
            if (node != null) {
                stack.push(node); 
                node = node.left; 
            } else {
                node = stack.pop();
                ans.add(node.val); 
                node = node.right; 
            }
        return ans; 
    }


    /*100. Same Tree (Easy)
    Given the roots of two binary trees p and q, write a function to check if 
    they are the same or not. Two binary trees are considered the same if they 
    are structurally identical, and the nodes have the same value.

    Example 1:
    Input: p = [1,2,3], q = [1,2,3]
    Output: true

    Example 2:
    Input: p = [1,2], q = [1,null,2]
    Output: false

    Example 3:
    Input: p = [1,2,1], q = [1,1,2]
    Output: false

    Constraints:
    * The number of nodes in both trees is in the range [0, 100].
    * -10^4 <= Node.val <= 10^4*/

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true; 
        if (p == null || q == null) return false; 
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right); 
    }


    /*101. Symmetric Tree (Easy)
    Given the root of a binary tree, check whether it is a mirror of itself 
    (i.e., symmetric around its center).

    Example 1:
    Input: root = [1,2,2,3,4,4,3]
    Output: true

    Example 2:
    Input: root = [1,2,2,null,3,null,3]
    Output: false

    Constraints:
    * The number of nodes in the tree is in the range [1, 1000].
    * -100 <= Node.val <= 100

    Follow up: Could you solve it both recursively and iteratively?*/

    public boolean isSymmetric(TreeNode root) {
        Stack<Pair<TreeNode, TreeNode>> stk = new Stack(); 
        stk.push(new Pair(root, root)); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            TreeNode left = elem.getKey(), right = elem.getValue(); 
            if (right == null || left.val != right.val) return false; 
            if (left.left != null) stk.push(new Pair(left.left, right.right)); 
            if (left.right != null) stk.push(new Pair(left.right, right.left)); 
        }
        return true; 
    }


    /*104. Maximum Depth of Binary Tree (Easy)
    Given the root of a binary tree, return its maximum depth. A binary tree's 
    maximum depth is the number of nodes along the longest path from the root 
    node down to the farthest leaf node.

    Example 1:
        3
       / \
      9  20
        /  \
       15   7
    Input: root = [3,9,20,null,null,15,7]
    Output: 3

    Example 2:
    Input: root = [1,null,2]
    Output: 2

    Constraints:
    * The number of nodes in the tree is in the range [0, 10^4].
    * -100 <= Node.val <= 100*/

    public int maxDepth(TreeNode root) {
        if (root == null) return 0; 
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right)); 
    }



    /*108. Convert Sorted Array to Binary Search Tree (Easy)
    Given an integer array nums where the elements are sorted in ascending 
    order, convert it to a height-balanced binary search tree.

    Example 1:
    Input: nums = [-10,-3,0,5,9]
           0
          / \
        -3   9
        /   /
      -10  5
    Output: [0,-3,9,-10,null,5]
           0
          / \
        -10  5
          \   \
          -3   9
    Explanation: [0,-10,5,null,-3,null,9] is also accepted:

    Example 2:
    Input: nums = [1,3]
    Output: [3,1]
           3  1
          /    \
         1      3
    Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.

    Constraints:
    * 1 <= nums.length <= 10^4
    * -10^4 <= nums[i] <= 10^4
    * nums is sorted in a strictly increasing order.*/

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = null; 
        Stack<Object[]> stk = new Stack(); 
        stk.push(new Object[]{null, 0, nums.length, false}); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            TreeNode node = (TreeNode) elem[0]; 
            int lo = (int) elem[1], hi = (int) elem[2]; 
            boolean tf = (boolean) elem[3]; 
            if (lo < hi) {
                int mid = lo + (hi - lo)/2; 
                if (root == null) node = root = new TreeNode(nums[mid]); 
                else if (tf) node = node.right = new TreeNode(nums[mid]); 
                else node = node.left = new TreeNode(nums[mid]); 
                stk.push(new Object[]{node, lo, mid, false}); 
                stk.push(new Object[]{node, mid+1, hi, true}); 
            }
        }
        return root; 
    }


    /*110. Balanced Binary Tree (Easy)
    Given a binary tree, determine if it is height-balanced.

    Example 1:
    Input: root = [3,9,20,null,null,15,7] 
        3
       / \
      9  20
        /  \
       15   7
    Output: true

    Example 2:
    Input: root = [1,2,2,3,3,null,null,4,4]
           1
          / \
         2   2
        / \ 
       3   3
      / \
     4   4
    Output: false

    Example 3:
    Input: root = []
    Output: true

    Constraints:
    * The number of nodes in the tree is in the range [0, 5000].
    * -10^4 <= Node.val <= 10^4*/

    public boolean isBalanced(TreeNode root) {
        Map<TreeNode, Pair<Boolean, Integer>> mp = new HashMap(); mp.put(null, new Pair(true, 0)); 
        TreeNode node = root, prev = null; 
        Stack<TreeNode> stk = new Stack(); 
        while (node != null || !stk.isEmpty()) {
            if (node != null) {
                stk.push(node); 
                node = node.left; 
            } else {
                node = stk.peek(); 
                if (node.right != null && node.right != prev) node = node.right; 
                else {
                    boolean b0 = mp.get(node.left).getKey(), b1 = mp.get(node.right).getKey(); 
                    int d0 = mp.get(node.left).getValue(), d1 = mp.get(node.right).getValue(); 
                    boolean b = b0 && b1 && Math.abs(d0-d1) <= 1; 
                    int d = 1 + Math.max(d0, d1); 
                    mp.put(node, new Pair(b, d)); 
                    stk.pop(); 
                    prev = node; 
                    node = null; 
                }
            }
        }
        return mp.get(root).getKey(); 
    }


    /*111. Minimum Depth of Binary Tree (Easy)
    Given a binary tree, find its minimum depth. The minimum depth is the 
    number of nodes along the shortest path from the root node down to the 
    nearest leaf node. Note: A leaf is a node with no children.

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 2

    Example 2:
    Input: root = [2,null,3,null,4,null,5,null,6]
    Output: 5

    Constraints:
    * The number of nodes in the tree is in the range [0, 10^5].
    * -1000 <= Node.val <= 1000*/

    public int minDepth(TreeNode root) {
        if (root == null) return 0; 
        int left = minDepth(root.left), right = minDepth(root.right); 
        return left > 0 && right > 0 ? 1 + Math.min(left, right) : 1 + Math.max(left, right); 
    }


    /*121. Best Time to Buy and Sell Stock (Easy)
    You are given an array prices where prices[i] is the price of a given stock 
    on the ith day. You want to maximize your profit by choosing a single day 
    to buy one stock and choosing a different day in the future to sell that 
    stock. Return the maximum profit you can achieve from this transaction. If 
    you cannot achieve any profit, return 0.

    Example 1:
    Input: prices = [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), 
                 profit = 6-1 = 5. Note that buying on day 2 and selling on day 
                 1 is not allowed because you must buy before you sell.
    
    Example 2:
    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transactions are done and the max profit = 0.

    Constraints:
    * 1 <= prices.length <= 10^5
    * 0 <= prices[i] <= 10^4*/

    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE, sell = 0; 
        for (var x : prices) {
            buy = Math.min(buy, x); 
            sell = Math.max(sell, x - buy); 
        }
        return sell; 
    }


    /*129. Sum Root to Leaf Numbers (Medium)
    You are given the root of a binary tree containing digits from 0 to 9 only.
    Each root-to-leaf path in the tree represents a number.
    * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
    Return the total sum of all root-to-leaf numbers. Test cases are generated 
    so that the answer will fit in a 32-bit integer. A leaf node is a node with 
    no children.

    Example 1:
    Input: root = [1,2,3]
    Output: 25
    Explanation: The root-to-leaf path 1->2 represents the number 12. The 
                 root-to-leaf path 1->3 represents the number 13. Therefore, 
                 sum = 12 + 13 = 25.
    
    Example 2:
    Input: root = [4,9,0,5,1]
    Output: 1026
    Explanation: The root-to-leaf path 4->9->5 represents the number 495. The 
                 root-to-leaf path 4->9->1 represents the number 491. The 
                 root-to-leaf path 4->0 represents the number 40. Therefore, 
                 sum = 495 + 491 + 40 = 1026.

    Constraints:
    * The number of nodes in the tree is in the range [1, 1000].
    * 0 <= Node.val <= 9
    * The depth of the tree will not exceed 10.*/

    public int sumNumbers(TreeNode root) {
        int ans = 0; 
        Stack<Pair<TreeNode, Integer>> stk = new Stack(); 
        stk.push(new Pair(root, 0)); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            TreeNode node = elem.getKey(); 
            int val = elem.getValue();
            val = 10*val + node.val; 
            if (node.left == null && node.right == null) ans += val; 
            if (node.left != null) stk.push(new Pair(node.left, val)); 
            if (node.right != null) stk.push(new Pair(node.right, val));
        }
        return ans; 
    }


    /*142. Linked List Cycle II (Medium)
    Given the head of a linked list, return the node where the cycle begins. If 
    there is no cycle, return null. There is a cycle in a linked list if there 
    is some node in the list that can be reached again by continuously 
    following the next pointer. Internally, pos is used to denote the index of 
    the node that tail's next pointer is connected to (0-indexed). It is -1 if 
    there is no cycle. Note that pos is not passed as a parameter. Do not 
    modify the linked list.

    Example 1:
    Input: head = [3,2,0,-4], pos = 1
    Output: tail connects to node index 1
    Explanation: There is a cycle in the linked list, where tail connects to 
                 the second node.
    
    Example 2:
    Input: head = [1,2], pos = 0
    Output: tail connects to node index 0
    Explanation: There is a cycle in the linked list, where tail connects to 
                 the first node.
    
    Example 3:
    Input: head = [1], pos = -1
    Output: no cycle
    Explanation: There is no cycle in the linked list.

    Constraints:
    * The number of the nodes in the list is in the range [0, 10^4].
    * -10^5 <= Node.val <= 10^5
    * pos is -1 or a valid index in the linked-list.

    Follow up: Can you solve it using O(1) (i.e. constant) memory?*/

    public ListNode detectCycle(ListNode head) {
        for (ListNode fast = head, slow = head; fast != null && fast.next != null; ) {
            fast = fast.next.next; 
            slow = slow.next; 
            if (fast == slow) {
                for (fast = head; fast != slow; fast = fast.next, slow = slow.next); 
                return slow; 
            }
        }
        return null; 
    }


    /*144. Binary Tree Preorder Traversal (Easy)
    Given the root of a binary tree, return the preorder traversal of its nodes' 
    values.

    Example 1:
    Input: root = [1,null,2,3]
    Output: [1,2,3]

    Example 2:
    Input: root = []
    Output: []

    Example 3:
    Input: root = [1]
    Output: [1]

    Constraints:
    * The number of nodes in the tree is in the range [0, 100].
    * -100 <= Node.val <= 100

    Follow up: Recursive solution is trivial, could you do it iteratively?*/

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList(); 
        Stack<TreeNode> stk = new Stack(); stk.push(root); 
        while (!stk.isEmpty()) {
            var node = stk.pop(); 
            if (node != null) {
                ans.add(node.val); 
                if (node.right != null) stk.push(node.right); 
                if (node.left != null) stk.push(node.left); 
            }
        }
        return ans;
    }


    /*149. Max Points on a Line (Hard)
    Given an array of points where points[i] = [xi, yi] represents a point on 
    the X-Y plane, return the maximum number of points that lie on the same 
    straight line.

    Example 1:
    Input: points = [[1,1],[2,2],[3,3]]
    Output: 3

    Example 2:
    Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    Output: 4

    Constraints:
    * 1 <= points.length <= 300
    * points[i].length == 2
    * -10^4 <= xi, yi <= 10^4
    * All the points are unique.*/

    public int maxPoints(int[][] points) {
        int ans = 1; 
        for (int i = 0, n = points.length; i < n; ++i) {
            int x = points[i][0], y = points[i][1]; 
            Map<Pair<Integer, Integer>, Integer> freq = new HashMap(); 
            for (int ii = i+1; ii < n; ++ii) {
                int dx = points[i][0] - points[ii][0], dy = points[i][1] - points[ii][1], g = dx; 
                for(int gg = dy; gg != 0; ) {
                    int tmp = g; g = gg; gg = tmp % gg; 
                }
                dx /= g; 
                dy /= g; 
                if (dx == 0) dy = Math.abs(dy); 
                var p = new Pair(dx, dy); 
                freq.merge(p, 1, Integer::sum); 
                ans = Math.max(ans, freq.get(p) + 1); 
            }
        }
        return ans; 
    }


    /*160. Intersection of Two Linked Lists (Easy)
    Given the heads of two singly linked-lists headA and headB, return the node 
    at which the two lists intersect. If the two linked lists have no 
    intersection at all, return null. For example, the following two linked 
    lists begin to intersect at node c1:

         a1 - a2 
                \
                 c1 - c2 - c3
                /
    b1 - b2 - b3

    The test cases are generated such that there are no cycles anywhere in the 
    entire linked structure. Note that the linked lists must retain their 
    original structure after the function returns.

    Custom Judge:
    The inputs to the judge are given as follows (your program is not given 
    these inputs):
    * intersectVal - The value of the node where the intersection occurs. This 
      is 0 if there is no intersected node.
    * listA - The first linked list.
    * listB - The second linked list.
    * skipA - The number of nodes to skip ahead in listA (starting from the 
      head) to get to the intersected node.
    * skipB - The number of nodes to skip ahead in listB (starting from the 
      head) to get to the intersected node.
    The judge will then create the linked structure based on these inputs and 
    pass the two heads, headA and headB to your program. If you correctly 
    return the intersected node, then your solution will be accepted.

    Example 1:
    Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
    Output: Intersected at '8'
    Explanation: The intersected node's value is 8 (note that this must not be 
                 0 if the two lists intersect). From the head of A, it reads as 
                 [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. 
                 There are 2 nodes before the intersected node in A; There are 
                 3 nodes before the intersected node in B.
                 - Note that the intersected node's value is not 1 because the 
                   nodes with value 1 in A and B (2nd node in A and 3rd node in 
                   B) are different node references. In other words, they point 
                   to two different locations in memory, while the nodes with 
                   value 8 in A and B (3rd node in A and 4th node in B) point 
                   to the same location in memory.
    
    Example 2:
    Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
    Output: Intersected at '2'
    Explanation: The intersected node's value is 2 (note that this must not be 
                 0 if the two lists intersect). From the head of A, it reads as 
                 [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There 
                 are 3 nodes before the intersected node in A; There are 1 node 
                 before the intersected node in B.
    
    Example 3:
    Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
    Output: No intersection
    Explanation: From the head of A, it reads as [2,6,4]. From the head of B, 
                 it reads as [1,5]. Since the two lists do not intersect, 
                 intersectVal must be 0, while skipA and skipB can be arbitrary 
                 values. Explanation: The two lists do not intersect, so return 
                 null.

    Constraints:
    * The number of nodes of listA is in the m.
    * The number of nodes of listB is in the n.
    * 1 <= m, n <= 3 * 10^4
    * 1 <= Node.val <= 10^5
    * 0 <= skipA < m
    * 0 <= skipB < n
    * intersectVal is 0 if listA and listB do not intersect.
    * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
     
    Follow up: Could you write a solution that runs in O(m + n) time and use 
               only O(1) memory?*/

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB; 
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next; 
            nodeB = nodeB == null ? headA : nodeB.next; 
        }
        return nodeA; 
    }


    /*163. Missing Ranges (Easy)
    You are given an inclusive range [lower, upper] and a sorted unique integer 
    array nums, where all elements are in the inclusive range. A number x is 
    considered missing if x is in the range [lower, upper] and x is not in nums.
    Return the smallest sorted list of ranges that cover every missing number 
    exactly. That is, no element of nums is in any of the ranges, and each 
    missing number is in one of the ranges. Each range [a,b] in the list should 
    be output as:
    * "a->b" if a != b
    * "a" if a == b

    Example 1:
    Input: nums = [0,1,3,50,75], lower = 0, upper = 99
    Output: ["2","4->49","51->74","76->99"]
    Explanation: The ranges are: [2,2] --> "2"
                                 [4,49] --> "4->49"
                                 [51,74] --> "51->74"
                                 [76,99] --> "76->99"
    
    Example 2:
    Input: nums = [-1], lower = -1, upper = -1
    Output: []
    Explanation: There are no missing ranges since there are no missing numbers.

    Constraints:
    * -10^9 <= lower <= upper <= 10^9
    * 0 <= nums.length <= 100
    * lower <= nums[i] <= upper
    * All the values of nums are unique.*/

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList(); 
        int prev = lower; 
        for (var x : nums) {
            if (prev < x) ans.add(prev == x-1 ? String.valueOf(prev) : String.format("%d->%d", prev, x-1)); 
            prev = x+1; 
        }
        if (prev <= upper) ans.add(prev == upper ? String.valueOf(upper) : String.format("%d->%d", prev, upper)); 
        return ans; 
    }


    /*168. Excel Sheet Column Title (Easy)
    Given an integer columnNumber, return its corresponding column title as it 
    appears in an Excel sheet. For example:
        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28 
        ...

    Example 1:
    Input: columnNumber = 1
    Output: "A"

    Example 2:
    Input: columnNumber = 28
    Output: "AB"

    Example 3:
    Input: columnNumber = 701
    Output: "ZY"

    Constraints: 1 <= columnNumber <= 2^31 - 1*/

    public String convertToTitle(int columnNumber) {
        StringBuilder ans = new StringBuilder(); 
        for (; columnNumber > 0; columnNumber /= 26) {
            int x = --columnNumber % 26; 
            ans.append((char) (x+'A')); 
        }
        ans.reverse(); 
        return ans.toString(); 
    }


    /*169. Majority Element (Easy)
    Given an array nums of size n, return the majority element. The majority 
    element is the element that appears more than ⌊n / 2⌋ times. You may assume 
    that the majority element always exists in the array.

    Example 1:
    Input: nums = [3,2,3]
    Output: 3

    Example 2:
    Input: nums = [2,2,1,1,1,2,2]
    Output: 2

    Constraints:
    * n == nums.length
    * 1 <= n <= 5 * 10^4
    * -10^9 <= nums[i] <= 10^9

    Follow-up: Could you solve the problem in linear time and in O(1) space?*/

    public int majorityElement(int[] nums) {
        /*Boyer-Moore majority vote algo*/
        int ans = 0, vote = 0; 
        for (var x : nums) {
            if (vote == 0) ans = x; 
            if (x == ans) ++vote; 
            else --vote; 
        }
        return ans; 
    }


    /*171. Excel Sheet Column Number (Easy)
    Given a string columnTitle that represents the column title as appears in 
    an Excel sheet, return its corresponding column number. For example: 
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...

    Example 1:
    Input: columnTitle = "A"
    Output: 1

    Example 2:
    Input: columnTitle = "AB"
    Output: 28

    Example 3:
    Input: columnTitle = "ZY"
    Output: 701

    Constraints:
    * 1 <= columnTitle.length <= 7
    * columnTitle consists only of uppercase English letters.
    * columnTitle is in the range ["A", "FXSHRXW"].*/

    public int titleToNumber(String columnTitle) {
        int ans = 0; 
        for (var ch : columnTitle.toCharArray()) 
            ans = 26*ans + (ch - 'A' + 1); 
        return ans; 
    }


    /*190. Reverse Bits (Easy)
    Reverse bits of a given 32 bits unsigned integer.
    Note:
    * Note that in some languages, such as Java, there is no unsigned integer 
      type. In this case, both input and output will be given as a signed 
      integer type. They should not affect your implementation, as the integer's 
      internal binary representation is the same, whether it is signed or 
      unsigned.
    * In Java, the compiler represents the signed integers using 2's complement 
      notation. Therefore, in Example 2 above, the input represents the signed 
      integer -3 and the output represents the signed integer -1073741825.

    Example 1:
    Input: n = 00000010100101000001111010011100
    Output:    964176192 (00111001011110000010100101000000)
    Explanation: The input binary string 00000010100101000001111010011100 
                 represents the unsigned integer 43261596, so return 964176192 
                 which its binary representation is 
                 00111001011110000010100101000000.
    
    Example 2:
    Input: n = 11111111111111111111111111111101
    Output:   3221225471 (10111111111111111111111111111111)
    Explanation: The input binary string 11111111111111111111111111111101 
                 represents the unsigned integer 4294967293, so return 
                 3221225471 which its binary representation is 
                 10111111111111111111111111111111.

    Constraints: The input must be a binary string of length 32

    Follow up: If this function is called many times, how would you optimize 
               it?*/

    public int reverseBits(int n) {
        int ans = 0; 
        for (int i = 0; i < 32; ++i, n >>= 1) 
            ans = (ans<<1) + (n & 1); 
        return ans; 
    }


    /*191. Number of 1 Bits (Easy)
    Write a function that takes an unsigned integer and returns the number of 
    '1' bits it has (also known as the Hamming weight).

    Note:
    * Note that in some languages, such as Java, there is no unsigned integer 
      type. In this case, the input will be given as a signed integer type. It 
      should not affect your implementation, as the integer's internal binary 
      representation is the same, whether it is signed or unsigned.
    * In Java, the compiler represents the signed integers using 2's complement 
      notation. Therefore, in Example 3, the input represents the signed 
      integer. -3.

    Example 1:
    Input: n = 00000000000000000000000000001011
    Output: 3
    Explanation: The input binary string 00000000000000000000000000001011 has a 
                 total of three '1' bits.

    Example 2:
    Input: n = 00000000000000000000000010000000
    Output: 1
    Explanation: The input binary string 00000000000000000000000010000000 has a 
                 total of one '1' bit.
    
    Example 3:
    Input: n = 11111111111111111111111111111101
    Output: 31
    Explanation: The input binary string 11111111111111111111111111111101 has a 
                 total of thirty one '1' bits.

    Constraints: The input must be a binary string of length 32.

    Follow up: If this function is called many times, how would you optimize it?*/

    public int hammingWeight(int n) {
        /* Brian Kernighan’s Algo */
        int ans = 0; 
        for (; n != 0; ++ans, n &= n-1); 
        return ans; 
    }


    /*226. Invert Binary Tree (Easy)
    Given the root of a binary tree, invert the tree, and return its root.

    Example 1:
    Input: root = [4,2,7,1,3,6,9]
    Output: [4,7,2,9,6,3,1]

    Example 2:
    Input: root = [2,1,3]
    Output: [2,3,1]

    Example 3:
    Input: root = []
    Output: []

    Constraints:
    * The number of nodes in the tree is in the range [0, 100].
    * -100 <= Node.val <= 100*/

    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stk = new Stack(); 
            stk.push(root); 
            while (!stk.isEmpty()) {
                var node = stk.pop(); 
                TreeNode temp = node.left; 
                node.left = node.right; 
                node.right = temp; 
                if (node.left != null) stk.push(node.left); 
                if (node.right != null) stk.push(node.right); 
            }
        }
        return root; 
    }


    /*245. Shortest Word Distance III (Medium)
    Given an array of strings wordsDict and two strings that already exist in 
    the array word1 and word2, return the shortest distance between the 
    occurrence of these two words in the list. Note that word1 and word2 may be 
    the same. It is guaranteed that they represent two individual words in the 
    list.

    Example 1:
    Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
    Output: 1

    Example 2:
    Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
    Output: 3

    Constraints:
    * 1 <= wordsDict.length <= 10^5
    * 1 <= wordsDict[i].length <= 10
    * wordsDict[i] consists of lowercase English letters.
    * word1 and word2 are in wordsDict.*/

    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int n = wordsDict.length, ans = n, i1 = n, i2 = 2*n; 
        for (int i = 0; i < n; ++i) {
            if (wordsDict[i].equals(word1)) i1 = word1.equals(word2) ? i2 : i; 
            if (wordsDict[i].equals(word2)) i2 = i; 
            ans = Math.min(ans, Math.abs(i1-i2)); 
        }
        return ans; 
    }


    /*280. Wiggle Sort (Medium)
    Given an integer array nums, reorder it such that 
    nums[0] <= nums[1] >= nums[2] <= nums[3].... You may assume the input array 
    always has a valid answer.

    Example 1:
    Input: nums = [3,5,2,1,6,4]
    Output: [3,5,1,6,2,4]
    Explanation: [1,6,2,5,3,4] is also accepted.

    Example 2:
    Input: nums = [6,6,5,6,3,8]
    Output: [6,6,5,6,3,8]

    Constraints:
    * 1 <= nums.length <= 5 * 10^4
    * 0 <= nums[i] <= 10^4
    * It is guaranteed that there will be an answer for the given input nums.

    Follow up: Could you solve the problem in O(n) time complexity?*/

    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length-1; ++i) 
            if (i%2 == 1 && nums[i] < nums[i+1] || i%2 == 0 && nums[i] > nums[i+1]) {
                int temp = nums[i]; 
                nums[i] = nums[i+1]; 
                nums[i+1] = temp; 
            }
    }


    /*305. Number of Islands II (Hard)
    You are given an empty 2D binary grid grid of size m x n. The grid 
    represents a map where 0's represent water and 1's represent land. 
    Initially, all the cells of grid are water cells (i.e., all the cells are 
    0's). We may perform an add land operation which turns the water at 
    position into a land. You are given an array positions where 
    positions[i] = [ri, ci] is the position (ri, ci) at which we should operate 
    the ith operation. Return an array of integers answer where answer[i] is 
    the number of islands after turning the cell (ri, ci) into a land. An 
    island is surrounded by water and is formed by connecting adjacent lands 
    horizontally or vertically. You may assume all four edges of the grid are 
    all surrounded by water.

    Example 1:
    Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
    Output: [1,1,2,3]
    Explanation: Initially, the 2d grid is filled with water.
                 - Operation #1: addLand(0, 0) turns the water at grid[0][0] 
                                 into a land. We have 1 island.
                 - Operation #2: addLand(0, 1) turns the water at grid[0][1] 
                                 into a land. We still have 1 island.
                 - Operation #3: addLand(1, 2) turns the water at grid[1][2] 
                                 into a land. We have 2 islands.
                 - Operation #4: addLand(2, 1) turns the water at grid[2][1] 
                                 into a land. We have 3 islands.
    
    Example 2:
    Input: m = 1, n = 1, positions = [[0,0]]
    Output: [1]

    Constraints:
    * 1 <= m, n, positions.length <= 10^4
    * 1 <= m * n <= 10^4
    * positions[i].length == 2
    * 0 <= ri < m
    * 0 <= ci < n

    Follow up: Could you solve it in time complexity O(k log(mn)), where 
               k == positions.length?*/

    private static int find(int p, int[] parent) {
        if (p != parent[p]) 
            parent[p] = find(parent[p], parent); 
        return parent[p]; 
    }
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parent = new int[m*n]; 
        for (int i = 0; i < m*n; ++i) parent[i] = i; 
        List<Integer> ans = new ArrayList(); 
        boolean[][] seen = new boolean[m][n]; 
        int prefix = 0; 
        int[] dir = new int[]{-1, 0, 1, 0, -1}; 
        for (var position : positions) {
            int i = position[0], j = position[1]; 
            if (!seen[i][j]) {
                ++prefix; 
                seen[i][j] = true; 
                for (int k = 0; k < 4; ++k) {
                    int ii = i + dir[k], jj = j + dir[k+1]; 
                    if (0 <= ii && ii < m && 0 <= jj && jj < n && seen[ii][jj]) {
                        int p = find(i*n + j, parent), pp = find(ii*n + jj, parent); 
                        if (p != pp) {
                            --prefix; 
                            parent[p] = pp; 
                        }
                    }
                }
            }
            ans.add(prefix); 
        }
        return ans; 
    }


    /*358. Rearrange String k Distance Apart (Hard)
    Given a string s and an integer k, rearrange s such that the same 
    characters are at least distance k from each other. If it is not possible 
    to rearrange the string, return an empty string "".

    Example 1:
    Input: s = "aabbcc", k = 3
    Output: "abcabc"
    Explanation: The same letters are at least a distance of 3 from each other.

    Example 2:
    Input: s = "aaabc", k = 3
    Output: ""
    Explanation: It is not possible to rearrange the string.

    Example 3:
    Input: s = "aaadbbcc", k = 2
    Output: "abacabcd"
    Explanation: The same letters are at least a distance of 2 from each other.

    Constraints:
    * 1 <= s.length <= 3 * 10^5
    * s consists of only lowercase English letters.
    * 0 <= k <= s.length*/

    public String rearrangeString(String s, int k) {
        StringBuilder ans = new StringBuilder(); 
        int[] freq = new int[26], prev = new int[26]; 
        for (var ch : s.toCharArray()) ++freq[ch - 'a']; 
        Arrays.fill(prev, -k); 
        for (int i = 0; i < s.length(); ++i) {
            int m = -1; 
            for (int c = 0; c < 26; ++c) 
                if (freq[c] > 0 && (m == -1 || freq[c] > freq[m]) && i - prev[c] >= k) m = c; 
            if (m == -1) return ""; 
            ans.append((char)('a' + m)); 
            --freq[m]; 
            prev[m] = i; 
        }
        return ans.toString(); 
    }


    /*427. Construct Quad Tree (Medium)
    Given a n * n matrix grid of 0's and 1's only. We want to represent the 
    grid with a Quad-Tree. Return the root of the Quad-Tree representing the 
    grid. Notice that you can assign the value of a node to True or False when 
    isLeaf is False, and both are accepted in the answer. A Quad-Tree is a tree 
    data structure in which each internal node has exactly four children. 
    Besides, each node has two attributes:
    * val: True if the node represents a grid of 1's or False if the node 
      represents a grid of 0's.
    * isLeaf: True if the node is leaf node on the tree or False if the node 
      has the four children.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    }
    We can construct a Quad-Tree from a two-dimensional area using the 
    following steps:
    * If the current grid has the same value (i.e all 1's or all 0's) set 
      isLeaf True and set val to the value of the grid and set the four 
      children to Null and stop.
    * If the current grid has different values, set isLeaf to False and set val 
      to any value and divide the current grid into four sub-grids as shown in 
      the photo.
    * Recurse for each of the children with the proper sub-grid.
    If you want to know more about the Quad-Tree, you can refer to the wiki. 
    Quad-Tree format:
    * The output represents the serialized format of a Quad-Tree using level 
      order traversal, where null signifies a path terminator where no node 
      exists below.
    * It is very similar to the serialization of the binary tree. The only 
      difference is that the node is represented as a list [isLeaf, val].
    * If the value of isLeaf or val is True we represent it as 1 in the list 
      [isLeaf, val] and if the value of isLeaf or val is False we represent it 
      as 0.

    Example 1:
    Input: grid = [[0,1],[1,0]]
    Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
    Explanation: The explanation of this example is shown below: Notice that 0 
                 represnts False and 1 represents True in the photo 
                 representing the Quad-Tree.

    Example 2:
    Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
    Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
    Explanation: All values in the grid are not the same. We divide the grid 
                 into four sub-grids. The topLeft, bottomLeft and bottomRight 
                 each has the same value. The topRight have different values so 
                 we divide it into 4 sub-grids where each has the same value. 
                 Explanation is shown in the photo below:

    Constraints:
    * n == grid.length == grid[i].length
    * n == 2x where 0 <= x <= 6*/

    private Node fn(int i, int ii, int j, int jj, int[][] prefix, int[][] grid) {
        int diff = prefix[ii][jj] - prefix[i][jj] - prefix[ii][j] + prefix[i][j]; 
        if (diff == 0 || diff == (ii-i)*(jj-j)) return new Node(grid[i][j] == 1 ? true : false, true); 
        Node tl = fn(i, (i+ii)/2, j, (j+jj)/2, prefix, grid); 
        Node tr = fn(i, (i+ii)/2, (j+jj)/2, jj, prefix, grid); 
        Node bl = fn((i+ii)/2, ii, j, (j+jj)/2, prefix, grid); 
        Node br = fn((i+ii)/2, ii, (j+jj)/2, jj, prefix, grid); 
        return new Node(false, false, tl, tr, bl, br); 
    }
    
    public Node construct(int[][] grid) {
        int n = grid.length; 
        int[][] prefix = new int[n+1][n+1]; 
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                prefix[i+1][j+1] = prefix[i][j+1] + prefix[i+1][j] - prefix[i][j] + grid[i][j]; 
        return fn(0, n, 0, n, prefix, grid); 
    }


    /*452. Minimum Number of Arrows to Burst Balloons (Medium)
    There are some spherical balloons taped onto a flat wall that represents 
    the XY-plane. The balloons are represented as a 2D integer array points 
    where points[i] = [xstart, xend] denotes a balloon whose horizontal 
    diameter stretches between xstart and xend. You do not know the exact 
    y-coordinates of the balloons. Arrows can be shot up directly vertically 
    (in the positive y-direction) from different points along the x-axis. A 
    balloon with xstart and xend is burst by an arrow shot at x if 
    xstart <= x <= xend. There is no limit to the number of arrows that can be 
    shot. A shot arrow keeps traveling up infinitely, bursting any balloons in 
    its path. Given the array points, return the minimum number of arrows that 
    must be shot to burst all balloons.

    Example 1:
    Input: points = [[10,16],[2,8],[1,6],[7,12]]
    Output: 2
    Explanation: The balloons can be burst by 2 arrows:
                 - Shoot an arrow at x = 6, bursting the balloons [2,8] and 
                   [1,6].
                 - Shoot an arrow at x = 11, bursting the balloons [10,16] and 
                   [7,12].
    
    Example 2:
    Input: points = [[1,2],[3,4],[5,6],[7,8]]
    Output: 4
    Explanation: One arrow needs to be shot for each balloon for a total of 4 
                 arrows.
    
    Example 3:
    Input: points = [[1,2],[2,3],[3,4],[4,5]]
    Output: 2
    Explanation: The balloons can be burst by 2 arrows:
                 - Shoot an arrow at x = 2, bursting the balloons [1,2] and 
                   [2,3].
                 - Shoot an arrow at x = 4, bursting the balloons [3,4] and 
                   [4,5].

    Constraints:
    * 1 <= points.length <= 10^5
    * points[i].length == 2
    * -2^31 <= xstart < xend <= 2^31 - 1*/

    public int findMinArrowShots(int[][] points) {
        int ans = 0; 
        long prev = Long.MIN_VALUE; 
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1])); 
        for (var p : points) 
            if (prev < p[0]) {
                ++ans; 
                prev = p[1]; 
            }
        return ans; 
    }


    /*502. IPO (Hard)
    Suppose LeetCode will start its IPO soon. In order to sell a good price of 
    its shares to Venture Capital, LeetCode would like to work on some projects 
    to increase its capital before the IPO. Since it has limited resources, it 
    can only finish at most k distinct projects before the IPO. Help LeetCode 
    design the best way to maximize its total capital after finishing at most k 
    distinct projects. You are given n projects where the ith project has a 
    pure profit profits[i] and a minimum capital of capital[i] is needed to 
    start it. Initially, you have w capital. When you finish a project, you 
    will obtain its pure profit and the profit will be added to your total 
    capital. Pick a list of at most k distinct projects from given projects to 
    maximize your final capital, and return the final maximized capital. The 
    answer is guaranteed to fit in a 32-bit signed integer.

    Example 1:
    Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
    Output: 4
    Explanation: Since your initial capital is 0, you can only start the 
                 project indexed 0. After finishing it you will obtain profit 1 
                 and your capital becomes 1. With capital 1, you can either 
                 start the project indexed 1 or the project indexed 2. Since 
                 you can choose at most 2 projects, you need to finish the 
                 project indexed 2 to get the maximum capital. Therefore, 
                 output the final maximized capital, which is 0 + 1 + 3 = 4.
    
    Example 2:
    Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
    Output: 6

    Constraints:
    * 1 <= k <= 10^5
    * 0 <= w <= 10^9
    * n == profits.length
    * n == capital.length
    * 1 <= n <= 10^5
    * 0 <= profits[i] <= 10^4
    * 0 <= capital[i] <= 10^9*/

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length; 
        int[][] aug = new int[n][2]; 
        for (int i = 0; i < n; ++i) {
            aug[i][0] = capital[i]; 
            aug[i][1] = profits[i]; 
        }
        Arrays.sort(aug, (a, b) -> Integer.compare(a[0], b[0])); 
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); 
        for (int i = 0; k > 0; --k) {
            for (; i < n && aug[i][0] <= w; ++i) pq.add(aug[i][1]); 
            if (!pq.isEmpty()) w += pq.poll(); 
        }
        return w; 
    }


    /*516. Longest Palindromic Subsequence (Medium)
    Given a string s, find the longest palindromic subsequence's length in s. A 
    subsequence is a sequence that can be derived from another sequence by 
    deleting some or no elements without changing the order of the remaining 
    elements.

    Example 1:
    Input: s = "bbbab"
    Output: 4
    Explanation: One possible longest palindromic subsequence is "bbbb".

    Example 2:
    Input: s = "cbbd"
    Output: 2
    Explanation: One possible longest palindromic subsequence is "bb".

    Constraints:
    * 1 <= s.length <= 1000
    * s consists only of lowercase English letters.*/

    public int longestPalindromeSubseq(String s) {
        int n = s.length(); 
        int[][] dp = new int[n][n]; 
        for (int i = n-1; i >= 0; --i) {
            dp[i][i] = 1; 
            for (int j = i+1; j < n; ++j) 
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = 2 + dp[i+1][j-1]; 
                else dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]); 
        }
        return dp[0][n-1]; 
    }


    /*540. Single Element in a Sorted Array (Medium)
    You are given a sorted array consisting of only integers where every 
    element appears exactly twice, except for one element which appears exactly 
    once. Return the single element that appears only once. Your solution must 
    run in O(log n) time and O(1) space.

    Example 1:
    Input: nums = [1,1,2,3,3,4,4,8,8]
    Output: 2

    Example 2:
    Input: nums = [3,3,7,7,10,11,11]
    Output: 10

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^5*/

    public int singleNonDuplicate(int[] nums) {
        int lo = 0, hi = nums.length-1; 
        while (lo < hi) {
            int mid = lo + (hi-lo)/2; 
            if (nums[mid] == nums[mid^1]) lo = mid+1; 
            else hi = mid; 
        }
        return nums[lo]; 
    }


    /*548. Split Array with Equal Sum (Hard)
    Given an integer array nums of length n, return true if there is a triplet 
    (i, j, k) which satisfies the following conditions:
    * 0 < i, i + 1 < j, j + 1 < k < n - 1
    * The sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and 
      (k + 1, n - 1) is equal.
    A subarray (l, r) represents a slice of the original array starting from 
    the element indexed l to the element indexed r.

    Example 1:
    Input: nums = [1,2,1,2,1,2,1]
    Output: true
    Explanation: i = 1, j = 3, k = 5. 
                 sum(0, i - 1) = sum(0, 0) = 1
                 sum(i + 1, j - 1) = sum(2, 2) = 1
                 sum(j + 1, k - 1) = sum(4, 4) = 1
                 sum(k + 1, n - 1) = sum(6, 6) = 1
    
    Example 2:
    Input: nums = [1,2,1,2,1,2,1,2]
    Output: false

    Constraints:
    * n == nums.length
    * 1 <= n <= 2000
    * -10^6 <= nums[i] <= 10^6*/

    public boolean splitArray(int[] nums) {
        int n = nums.length; 
        int[] prefix = new int[n+1]; 
        for (int i = 0; i < n; ++i) prefix[i+1] = prefix[i] + nums[i]; 
        for (int j = 0; j < n; ++j) {
            Set<Integer> seen = new HashSet(); 
            for (int i = 1; i+1 < j; ++i) 
                if (prefix[i] == prefix[j] - prefix[i+1]) seen.add(prefix[i]); 
            for (int k = j+2; k+1 < n; ++k) 
                if (prefix[k] - prefix[j+1] == prefix[n] - prefix[k+1] && seen.contains(prefix[k] - prefix[j+1])) return true; 
        }
        return false; 
    }


    /*567. Permutation in String (Medium)
    Given two strings s1 and s2, return true if s2 contains a permutation of s1, 
    or false otherwise. In other words, return true if one of s1's permutations 
    is the substring of s2.

    Example 1:
    Input: s1 = "ab", s2 = "eidbaooo"
    Output: true
    Explanation: s2 contains one permutation of s1 ("ba").

    Example 2:
    Input: s1 = "ab", s2 = "eidboaoo"
    Output: false

    Constraints:
    * 1 <= s1.length, s2.length <= 10^4
    * s1 and s2 consist of lowercase English letters.*/

    public boolean checkInclusion(String s1, String s2) {
        int[] freq = new int[26]; 
        for (var ch : s1.toCharArray()) ++freq[ch - 'a']; 
        int bal = 0; 
        for (int i = 0; i < 26; ++i) 
            if (freq[i] > 0) ++bal; 
        for (int i = 0, n = s1.length(); i < s2.length(); ++i) {
            if (freq[s2.charAt(i)-'a'] == 0) ++bal; 
            if (--freq[s2.charAt(i)-'a'] == 0) --bal; 
            if (i >= n) {
                if (freq[s2.charAt(i-n)-'a'] == 0) ++bal; 
                if (++freq[s2.charAt(i-n)-'a'] == 0) --bal; 
            }
            if (bal == 0) return true; 
        }
        return false; 
    }


    /*590. N-ary Tree Postorder Traversal (Easy)
    Given the root of an n-ary tree, return the postorder traversal of its 
    nodes' values. Nary-Tree input serialization is represented in their level 
    order traversal. Each group of children is separated by the null value (See 
    examples)

    Example 1:
    Input: root = [1,null,3,2,4,null,5,6]
    Output: [5,6,3,2,4,1]

    Example 2:
    Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]

    Constraints:
    * The number of nodes in the tree is in the range [0, 10^4].
    * 0 <= Node.val <= 10^4
    * The height of the n-ary tree is less than or equal to 1000.
     
    Follow up: Recursive solution is trivial, could you do it iteratively?*/

    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList(); 
        if (root != null) {
            Stack<Node> stk = new Stack(); stk.push(root); 
            Node prev = null; 
            while (!stk.isEmpty()) {
                var node = stk.peek(); 
                if (!node.children.isEmpty() && prev != node.children.get(node.children.size()-1))
                    for (int i = node.children.size()-1; i >= 0; --i) 
                        stk.push(node.children.get(i)); 
                else {
                    ans.add(node.val); 
                    stk.pop(); 
                    prev = node; 
                }
            }
        }
        return ans; 
    }


    /*616. Add Bold Tag in String (Medium)
    You are given a string s and an array of strings words. You should add a 
    closed pair of bold tag <b> and </b> to wrap the substrings in s that exist 
    in words.
    * If two such substrings overlap, you should wrap them together with only 
      one pair of closed bold-tag.
    * If two substrings wrapped by bold tags are consecutive, you should 
      combine them.
    Return s after adding the bold tags.

    Example 1:
    Input: s = "abcxyz123", words = ["abc","123"]
    Output: "<b>abc</b>xyz<b>123</b>"
    Explanation: The two strings of words are substrings of s as following: 
                 "abcxyz123". We add <b> before each substring and </b> after 
                 each substring.
    
    Example 2:
    Input: s = "aaabbb", words = ["aa","b"]
    Output: "<b>aaabbb</b>"
    Explanation: "aa" appears as a substring two times: "aaabbb" and "aaabbb".
                 "b" appears as a substring three times: "aaabbb", "aaabbb", 
                 and "aaabbb". We add <b> before each substring and </b> after 
                 each substring: "<b>a<b>a</b>a</b><b>b</b><b>b</b><b>b</b>".
                 Since the first two <b>'s overlap, we merge them: 
                 "<b>aaa</b><b>b</b><b>b</b><b>b</b>". Since now the four <b>'s 
                 are consecuutive, we merge them: "<b>aaabbb</b>".

    Constraints:
    * 1 <= s.length <= 1000
    * 0 <= words.length <= 100
    * 1 <= words[i].length <= 1000
    * s and words[i] consist of English letters and digits.
    * All the values of words are unique.

    Note: This question is the same as 758: 
          https://leetcode.com/problems/bold-words-in-string/*/

    public String addBoldTag(String s, String[] words) {
        int[] line = new int[s.length()+1]; 
        for (String word : words) {
            for (int k = -1; true; ) {
                k = s.indexOf(word, ++k); 
                if (k == -1) break; 
                ++line[k]; 
                --line[k+word.length()]; 
            }
        }
        StringBuilder ans = new StringBuilder(); 
        for (int i = 0, prefix = 0; i < s.length(); ++i) {
            if (prefix == 0 && prefix+line[i] > 0) ans.append("<b>"); 
            ans.append(s.charAt(i)); 
            prefix += line[i]; 
            if (prefix > 0 && prefix+line[i+1] == 0) ans.append("</b>"); 
        }
        return ans.toString(); 
    }


    /*644. Maximum Average Subarray II (Hard)
    You are given an integer array nums consisting of n elements, and an 
    integer k. Find a contiguous subarray whose length is greater than or equal 
    to k that has the maximum average value and return this value. Any answer 
    with a calculation error less than 10-5 will be accepted.

    Example 1:
    Input: nums = [1,12,-5,-6,50,3], k = 4
    Output: 12.75000
    Explanation: - When the length is 4, averages are [0.5, 12.75, 10.5] and 
                   the maximum average is 12.75
                 - When the length is 5, averages are [10.4, 10.8] and the 
                   maximum average is 10.8
                 - When the length is 6, averages are [9.16667] and the maximum 
                   average is 9.16667
                 The maximum average is when we choose a subarray of length 4 
                 (i.e., the sub array [12, -5, -6, 50]) which has the max 
                 average 12.75, so we return 12.75. Note that we do not 
                 consider the subarrays of length < 4.
    
    Example 2:
    Input: nums = [5], k = 1
    Output: 5.00000

    Constraints:
    * n == nums.length
    * 1 <= k <= n <= 10^4
    * -10^4 <= nums[i] <= 10^4*/

    public double findMaxAverage(int[] nums, int k) {
        double lo = -1e4, hi = 1e4; 
        while (lo + 1e-5 <= hi) {
            double mid = (lo + hi)/2, lag = 0, prefix = 0; 
            boolean found = false; 
            for (int i = 0; i < nums.length; ++i) {
                prefix += nums[i] - mid; 
                if (i >= k) lag += nums[i-k] - mid; 
                if (lag < 0) {
                    prefix -= lag; 
                    lag = 0; 
                }
                if (i >= k-1 && prefix >= 0) {found = true; break;}
            }
            if (found) lo = mid; 
            else hi = mid; 
        }
        return lo; 
    }


    /*656. Coin Path (Hard)
    You are given an integer array coins (1-indexed) of length n and an integer 
    maxJump. You can jump to any index i of the array coins if coins[i] != -1 
    and you have to pay coins[i] when you visit index i. In addition to that, 
    if you are currently at index i, you can only jump to any index i + k where 
    i + k <= n and k is a value in the range [1, maxJump]. You are initially 
    positioned at index 1 (coins[1] is not -1). You want to find the path that 
    reaches index n with the minimum cost. Return an integer array of the 
    indices that you will visit in order so that you can reach index n with the 
    minimum cost. If there are multiple paths with the same cost, return the 
    lexicographically smallest such path. If it is not possible to reach index 
    n, return an empty array. A path p1 = [Pa1, Pa2, ..., Pax] of length x is 
    lexicographically smaller than p2 = [Pb1, Pb2, ..., Pbx] of length y, if 
    and only if at the first j where Paj and Pbj differ, Paj < Pbj; when no 
    such j exists, then x < y.

    Example 1:
    Input: coins = [1,2,4,-1,2], maxJump = 2
    Output: [1,3,5]

    Example 2:
    Input: coins = [1,2,4,-1,2], maxJump = 1
    Output: []

    Constraints:
    * 1 <= coins.length <= 1000
    * -1 <= coins[i] <= 100
    * coins[1] != -1
    * 1 <= maxJump <= 100*/

    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length; 
        int[] dp = new int[n]; 
        Arrays.fill(dp, -1); 
        if (coins[n-1] != -1) dp[n-1] = coins[n-1]; 
        int[] jump = new int[n]; 
        Arrays.fill(jump, -1); 
        for (int i = n-2; i >= 0; --i) 
            if (coins[i] != -1) 
                for (int ii = Math.min(n-1, i+maxJump); ii > i; --ii) 
                    if (dp[ii] != -1) {
                        int cand = coins[i] + dp[ii]; 
                        if (dp[i] == -1 || cand <= dp[i]) {
                            dp[i] = cand; 
                            jump[i] = ii; 
                        }
                    }
        List<Integer> ans = new ArrayList(); 
        if (dp[0] != -1) 
            for (int i = 0; i >= 0; i = jump[i])
                ans.add(i+1); 
        return ans; 
    }


    /*660. Remove 9 (Hard)
    Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...
    Now, you will have a new integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...].
    Given an integer n, return the nth (1-indexed) integer in the new sequence.

    Example 1:
    Input: n = 9
    Output: 10

    Example 2:
    Input: n = 10
    Output: 11

    Constraints: 1 <= n <= 8 * 10^8*/

    public int newInteger(int n) {
        int ans = 0; 
        for (int base = 1; n > 0; base *= 10) {
            ans = n%9 * base + ans; 
            n /= 9; 
        }
        return ans; 
    }


    /*662. Maximum Width of Binary Tree (Medium)
    Given the root of a binary tree, return the maximum width of the given tree.
    The maximum width of a tree is the maximum width among all levels. The 
    width of one level is defined as the length between the end-nodes (the 
    leftmost and rightmost non-null nodes), where the null nodes between the 
    end-nodes that would be present in a complete binary tree extending down to 
    that level are also counted into the length calculation. It is guaranteed 
    that the answer will in the range of a 32-bit signed integer.

    Example 1:
    Input: root = [1,3,2,5,3,null,9]
    Output: 4
    Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).

    Example 2:
    Input: root = [1,3,2,5,null,null,9,6,null,7]
    Output: 7
    Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).

    Example 3:
    Input: root = [1,3,2,5]
    Output: 2
    Explanation: The maximum width exists in the second level with length 2 (3,2).

    Constraints:
    * The number of nodes in the tree is in the range [1, 3000].
    * -100 <= Node.val <= 100*/

    public int widthOfBinaryTree(TreeNode root) {
        int ans = 0; 
        Deque<Map.Entry<TreeNode, Integer>> q = new LinkedList(); 
        q.add(new AbstractMap.SimpleEntry(root, 0)); 
        while (!q.isEmpty()) {
            ans = Math.max(ans, q.getLast().getValue() - q.peek().getValue() + 1); 
            for (int sz = q.size(); sz > 0; --sz) {
                var elem = q.poll(); 
                TreeNode node = elem.getKey(); 
                int x = elem.getValue(); 
                if (node.left != null) q.add(new AbstractMap.SimpleEntry(node.left, 2*x)); 
                if (node.right != null) q.add(new AbstractMap.SimpleEntry(node.right, 2*x+1)); 
            }
        }
        return ans; 
    }



    /*683. K Empty Slots (Hard)
    You have n bulbs in a row numbered from 1 to n. Initially, all the bulbs 
    are turned off. We turn on exactly one bulb every day until all bulbs are 
    on after n days. You are given an array bulbs of length n where 
    bulbs[i] = x means that on the (i+1)th day, we will turn on the bulb at 
    position x where i is 0-indexed and x is 1-indexed. Given an integer k, 
    return the minimum day number such that there exists two turned on bulbs 
    that have exactly k bulbs between them that are all turned off. If there 
    isn't such day, return -1.

    Example 1:
    Input: bulbs = [1,3,2], k = 1
    Output: 2
    Explanation: - On the first day: bulbs[0] = 1, first bulb is turned on: 
                   [1,0,0]
                 - On the second day: bulbs[1] = 3, third bulb is turned on: 
                   [1,0,1]
                 - On the third day: bulbs[2] = 2, second bulb is turned on: 
                   [1,1,1]
                 We return 2 because on the second day, there were two on 
                 bulbs with one off bulb between them.
    
    Example 2:
    Input: bulbs = [1,2,3], k = 1
    Output: -1

    Constraints:
    * n == bulbs.length
    * 1 <= n <= 2 * 10^4
    * 1 <= bulbs[i] <= n
    * bulbs is a permutation of numbers from 1 to n.
    * 0 <= k <= 2 * 10^4*/

    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length, ans = Integer.MAX_VALUE; 
        int[] days = new int[n]; 
        for (int i = 0; i < n; ++i) days[bulbs[i]-1] = i+1; 
        for (int i = 0, lo = 0, hi = k+1; hi < n; ++i) 
            if (days[lo] > days[i] || days[i] < days[hi] || i == hi) {
                if (i == hi) ans = Math.min(ans, Math.max(days[lo], days[hi])); 
                lo = i; 
                hi = i+k+1; 
            }
        return ans == Integer.MAX_VALUE ? -1 : ans; 
    }


    /*711. Number of Distinct Islands II (Hard)
    You are given an m x n binary matrix grid. An island is a group of 1's 
    (representing land) connected 4-directionally (horizontal or vertical.) You 
    may assume all four edges of the grid are surrounded by water. An island is 
    considered to be the same as another if they have the same shape, or have 
    the same shape after rotation (90, 180, or 270 degrees only) or reflection 
    (left/right direction or up/down direction). Return the number of distinct 
    islands.

    Example 1:
    Input: grid = [[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]
    Output: 1
    Explanation: The two islands are considered the same because if we make a 
                 180 degrees clockwise rotation on the first island, then two 
                 islands will have the same shapes.
    
    Example 2:
    Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
    Output: 1

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 1 <= m, n <= 50
    * grid[i][j] is either 0 or 1.*/

    public int numDistinctIslands2(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length; 
        int[] dir = new int[]{-1, 0, 1, 0, -1}; 
        Set<String> seen = new HashSet(); 
        for (int r = 0; r < m; ++r) 
            for (int c = 0; c < n; ++c) 
                if (grid[r][c] == 1) {
                    List<int[]>[] vals = new ArrayList[8]; 
                    for (int k = 0; k < 8; ++k) vals[k] = new ArrayList(); 
                    grid[r][c] = 0; 
                    Stack<int[]> stk = new Stack(); 
                    stk.add(new int[]{r, c}); 
                    while (!stk.isEmpty()) {
                        var elem = stk.pop(); 
                        int i = elem[0], j = elem[1]; 
                        int[][] d = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; 
                        for (int k = 0; k < 4; ++k) {
                            vals[k].add(new int[]{i*d[k][0], j*d[k][1]}); 
                            vals[k+4].add(new int[]{j*d[k][0], i*d[k][1]}); 
                        }
                        for (int k = 0; k < 4; ++k) {
                            int ii = i + dir[k], jj = j + dir[k+1]; 
                            if (0 <= ii && ii < m && 0 <= jj && jj < n && grid[ii][jj] == 1) {
                                grid[ii][jj] = 0; 
                                stk.add(new int[]{ii, jj}); 
                            }
                        }
                    }
                    boolean found = false; 
                    Set<String> temp = new HashSet(); 
                    for (int k = 0; k < 8; ++k) {
                        int mx = Integer.MAX_VALUE, my = Integer.MAX_VALUE; 
                        for (var elem : vals[k]) {
                            mx = Math.min(mx, elem[0]); 
                            my = Math.min(my, elem[1]); 
                        }
                        for (int i = 0; i < vals[k].size(); ++i) {
                            vals[k].get(i)[0] -= mx; 
                            vals[k].get(i)[1] -= my; 
                        }
                        Collections.sort(vals[k], (a, b) -> (a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]))); 
                        StringBuilder sb = new StringBuilder(); 
                        for (var elem : vals[k]) 
                            sb.append(elem[0] + "," + elem[1] + ";"); 
                        String key = sb.toString(); 
                        if (seen.contains(key)) {
                            found = true; 
                            break; 
                        }
                        temp.add(key); 
                    }
                    if (!found) {
                        ++ans; 
                        for (var v : temp) seen.add(v); 
                    }
                }
        return ans; 
    }


    /*727. Minimum Window Subsequence (Hard)
    Given strings s1 and s2, return the minimum contiguous substring part of s1, 
    so that s2 is a subsequence of the part. If there is no such window in s1 
    that covers all characters in s2, return the empty string "". If there are 
    multiple such minimum-length windows, return the one with the left-most 
    starting index.

    Example 1:
    Input: s1 = "abcdebdde", s2 = "bde"
    Output: "bcde"
    Explanation: "bcde" is the answer because it occurs before "bdde" which has 
                 the same length. "deb" is not a smaller window because the 
                 elements of s2 in the window must occur in order.
    
    Example 2:
    Input: s1 = "jmeqksfrsdcmsiwvaovztaqenprpvnbstl", s2 = "u"
    Output: ""

    Constraints:
    * 1 <= s1.length <= 2 * 10^4
    * 1 <= s2.length <= 100
    * s1 and s2 consist of lowercase English letters.*/

    public String minWindow(String s1, String s2) {
        int m = s1.length(), n = s2.length(); 
        int[][] dp = new int[m][n]; 
        for (int i = 0; i < m; ++i) Arrays.fill(dp[i], -1); 
        if (s1.charAt(0) == s2.charAt(0)) dp[0][0] = 0; 
        for (int i = 1; i < m; ++i) 
            for (int j = 0; j < n; ++j)
                if (s1.charAt(i) == s2.charAt(j))
                    if (j > 0) dp[i][j] = dp[i-1][j-1]; 
                    else dp[i][j] = i; 
                else dp[i][j] = dp[i-1][j]; 
        String ans = ""; 
        for (int i = 0; i < m; ++i) {
            int k = dp[i][n-1]; 
            if (k != -1 && (ans.equals("") || i-k+1 < ans.length())) ans = s1.substring(k, i+1); 
        }
        return ans; 
    }


    /*734. Sentence Similarity (Easy)
    We can represent a sentence as an array of words, for example, the sentence 
    "I am happy with leetcode" can be represented as 
    arr = ["I","am",happy","with","leetcode"]. Given two sentences sentence1 
    and sentence2 each represented as a string array and given an array of 
    string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that 
    the two words xi and yi are similar. Return true if sentence1 and sentence2 
    are similar, or false if they are not similar. Two sentences are similar if:
    * They have the same length (i.e., the same number of words)
    * sentence1[i] and sentence2[i] are similar.
    Notice that a word is always similar to itself, also notice that the 
    similarity relation is not transitive. For example, if the words a and b 
    are similar, and the words b and c are similar, a and c are not necessarily 
    similar.

    Example 1:
    Input: sentence1 = ["great","acting","skills"], 
           sentence2 = ["fine","drama","talent"], 
           similarPairs = [["great","fine"],["drama","acting"],["skills","talent"]]
    Output: true
    Explanation: The two sentences have the same length and each word i of 
                 sentence1 is also similar to the corresponding word in 
                 sentence2.
    
    Example 2:
    Input: sentence1 = ["great"], sentence2 = ["great"], similarPairs = []
    Output: true
    Explanation: A word is similar to itself.

    Example 3:
    Input: sentence1 = ["great"], 
           sentence2 = ["doubleplus","good"], 
           similarPairs = [["great","doubleplus"]]
    Output: false
    Explanation: As they don't have the same length, we return false.

    Constraints:
    * 1 <= sentence1.length, sentence2.length <= 1000
    * 1 <= sentence1[i].length, sentence2[i].length <= 20
    * sentence1[i] and sentence2[i] consist of English letters.
    * 0 <= similarPairs.length <= 1000
    * similarPairs[i].length == 2
    * 1 <= xi.length, yi.length <= 20
    * xi and yi consist of lower-case and upper-case English letters.
    * All the pairs (xi, yi) are distinct.*/

    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false; 
        Map<String, Set<String>> mp = new HashMap(); 
        for (var p : similarPairs) {
            mp.putIfAbsent(p.get(0), new HashSet()); 
            mp.putIfAbsent(p.get(1), new HashSet()); 
            mp.get(p.get(0)).add(p.get(1)); 
            mp.get(p.get(1)).add(p.get(0)); 
        }
        for (int i = 0; i < sentence1.length; ++i) 
            if (!sentence1[i].equals(sentence2[i]) && (!mp.containsKey(sentence1[i]) || !mp.get(sentence1[i]).contains(sentence2[i]))) return false; 
        return true; 
    }


    /*751. IP to CIDR (Medium)
    An IP address is a formatted 32-bit unsigned integer where each group of 8 
    bits is printed as a decimal number and the dot character '.' splits the 
    groups. For example, the binary number 00001111 10001000 11111111 01101011 
    (spaces added for clarity) formatted as an IP address would be 
    "15.136.255.107". A CIDR block is a format used to denote a specific set of 
    IP addresses. It is a string consisting of a base IP address, followed by a 
    slash, followed by a prefix length k. The addresses it covers are all the 
    IPs whose first k bits are the same as the base IP address. For example, 
    "123.45.67.89/20" is a CIDR block with a prefix length of 20. Any IP 
    address whose binary representation matches 
    01111011 00101101 0100xxxx xxxxxxxx, where x can be either 0 or 1, is in 
    the set covered by the CIDR block. You are given a start IP address ip and 
    the number of IP addresses we need to cover n. Your goal is to use as few 
    CIDR blocks as possible to cover all the IP addresses in the inclusive 
    range [ip, ip + n - 1] exactly. No other IP addresses outside of the range 
    should be covered. Return the shortest list of CIDR blocks that covers the 
    range of IP addresses. If there are multiple answers, return any of them.

    Example 1:
    Input: ip = "255.0.0.7", n = 10
    Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
    Explanation: The IP addresses that need to be covered are:
                 - 255.0.0.7  -> 11111111 00000000 00000000 00000111
                 - 255.0.0.8  -> 11111111 00000000 00000000 00001000
                 - 255.0.0.9  -> 11111111 00000000 00000000 00001001
                 - 255.0.0.10 -> 11111111 00000000 00000000 00001010
                 - 255.0.0.11 -> 11111111 00000000 00000000 00001011
                 - 255.0.0.12 -> 11111111 00000000 00000000 00001100
                 - 255.0.0.13 -> 11111111 00000000 00000000 00001101
                 - 255.0.0.14 -> 11111111 00000000 00000000 00001110
                 - 255.0.0.15 -> 11111111 00000000 00000000 00001111
                 - 255.0.0.16 -> 11111111 00000000 00000000 00010000
                 The CIDR block "255.0.0.7/32" covers the first address. The 
                 CIDR block "255.0.0.8/29" covers the middle 8 addresses 
                 (binary format of 11111111 00000000 00000000 00001xxx). The 
                 CIDR block "255.0.0.16/32" covers the last address. Note that 
                 while the CIDR block "255.0.0.0/28" does cover all the 
                 addresses, it also includes addresses outside of the range, so 
                 we cannot use it.
    
    Example 2:
    Input: ip = "117.145.102.62", n = 8
    Output: ["117.145.102.62/31","117.145.102.64/30","117.145.102.68/31"]

    Constraints:
    * 7 <= ip.length <= 15
    * ip is a valid IPv4 on the form "a.b.c.d" where a, b, c, and d are 
      integers in the range [0, 255].
    * 1 <= n <= 1000
    * Every implied address ip + x (for x < n) will be a valid IPv4 address.*/

    public List<String> ipToCIDR(String ip, int n) {
        int val = 0; 
        for (var x : ip.split("\\.")) 
            val = (val << 8) | Integer.valueOf(x); 
        List<String> ans = new ArrayList(); 
        for (int i = 0; n > 0; n -= 1<<i, val += 1<<i) {
            for (i = 0; i < 32 && (val & 1<<i) == 0 && (1<<i+1) <= n; ++i); 
            StringBuilder elem = new StringBuilder(); 
            for (int k = 24; k >= 0; k -= 8) {
                elem.append(Integer.toString(val >> k & 0xFF)); 
                if (k > 0) elem.append("."); 
            }
            ans.add(elem + "/" + Integer.toString(32-i)); 
        }
        return ans; 
    }


    /*758. Bold Words in String (Medium)
    Given an array of keywords words and a string s, make all appearances of 
    all keywords words[i] in s bold. Any letters between <b> and </b> tags 
    become bold. Return s after adding the bold tags. The returned string 
    should use the least number of tags possible, and the tags should form a 
    valid combination.

    Example 1:
    Input: words = ["ab","bc"], s = "aabcd"
    Output: "a<b>abc</b>d"
    Explanation: Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, 
                 so it is incorrect.
    
    Example 2:
    Input: words = ["ab","cb"], s = "aabcd"
    Output: "a<b>ab</b>cd"

    Constraints:
    * 1 <= s.length <= 500
    * 0 <= words.length <= 50
    * 1 <= words[i].length <= 10
    * s and words[i] consist of lowercase English letters.

    Note: This question is the same as 616: 
          https://leetcode.com/problems/add-bold-tag-in-string/*/

    public String boldWords(String[] words, String s) {
        int[] line = new int[s.length()+1]; 
        for (String word : words) {
            for (int k = -1; true; ) {
                k = s.indexOf(word, ++k); 
                if (k == -1) break; 
                ++line[k]; 
                --line[k+word.length()]; 
            }
        }
        StringBuilder ans = new StringBuilder(); 
        for (int i = 0, prefix = 0; i < s.length(); ++i) {
            if (prefix == 0 && prefix+line[i] > 0) ans.append("<b>"); 
            ans.append(s.charAt(i)); 
            prefix += line[i]; 
            if (prefix > 0 && prefix+line[i+1] == 0) ans.append("</b>"); 
        }
        return ans.toString(); 
    }


    /*759. Employee Free Time (Hard)
    We are given a list schedule of employees, which represents the working 
    time for each employee. Each employee has a list of non-overlapping 
    Intervals, and these intervals are in sorted order. Return the list of 
    finite intervals representing common, positive-length free time for all 
    employees, also in sorted order. (Even though we are representing Intervals 
    in the form [x, y], the objects inside are Intervals, not lists or arrays. 
    For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and 
    schedule[0][0][0] is not defined).  Also, we wouldn't include intervals 
    like [5, 5] in our answer, as they have zero length.

    Example 1:
    Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
    Output: [[3,4]]
    Explanation: There are a total of three employees, and all common free time 
                 intervals would be [-inf, 1], [3, 4], [10, inf]. We discard 
                 any intervals that contain inf as they aren't finite.
    
    Example 2:
    Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
    Output: [[5,6],[7,9]]

    Constraints:
    * 1 <= schedule.length , schedule[i].length <= 50
    * 0 <= schedule[i].start < schedule[i].end <= 10^8*/

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> line = new ArrayList(); 
        for (var elem : schedule) 
            for (var x : elem) {
                line.add(new int[] {x.start, 1}); 
                line.add(new int[] {x.end, -1}); 
            }
        Collections.sort(line, (a, b) -> (a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]))); 
        List<Interval> ans = new ArrayList(); 
        int prev = Integer.MIN_VALUE, prefix = 0; 
        for (int i = 0; i < line.size(); ) {
            int k = line.get(i)[0]; 
            if (prefix == 0 && prev > Integer.MIN_VALUE) ans.add(new Interval(prev, k)); 
            for (int ii = i; i < line.size() && line.get(ii)[0] == line.get(i)[0]; ++i) 
                prefix += line.get(i)[1]; 
            if (prefix == 0) prev = k; 
        }
        return ans; 
    }


    /*772. Basic Calculator III (Hard)
    Implement a basic calculator to evaluate a simple expression string. The 
    expression string contains only non-negative integers, '+', '-', '*', '/' 
    operators, and open '(' and closing parentheses ')'. The integer division 
    should truncate toward zero. You may assume that the given expression is 
    always valid. All intermediate results will be in the range of 
    [-2^31, 2^31 - 1]. Note: You are not allowed to use any built-in function 
    which evaluates strings as mathematical expressions, such as eval().

    Example 1:
    Input: s = "1+1"
    Output: 2

    Example 2:
    Input: s = "6-4/2"
    Output: 4

    Example 3:
    Input: s = "2*(5+5*2)/3+(6/2+8)"
    Output: 21

    Constraints:
    * 1 <= s <= 10^4
    * s consists of digits, '+', '-', '*', '/', '(', and ')'.
    * s is a valid expression.*/

    public int calculate(String s) {
        s = "(" + s + ")"; 
        Map<Character, Integer> precedence = new HashMap(); 
        precedence.put('(', 0); 
        precedence.put(')', 1); 
        precedence.put('+', 1); 
        precedence.put('-', 1); 
        precedence.put('*', 2); 
        precedence.put('/', 2); 
        Stack<Character> ops = new Stack(); 
        List<String> postfix = new ArrayList(); 
        for (int i = 0, v = 0; i < s.length(); ++i) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') v = 10*v + s.charAt(i) - '0';  
            else {
                if (i > 0 && '0' <= s.charAt(i-1) && s.charAt(i-1) <= '9') postfix.add(Integer.toString(v)); 
                v = 0; 
                if (s.charAt(i) == '(') ops.push(s.charAt(i)); 
                else {
                    while (!ops.isEmpty() && precedence.get(ops.peek()) >= precedence.get(s.charAt(i))) 
                        postfix.add(String.valueOf(ops.pop())); 
                    if (s.charAt(i) == ')') ops.pop(); 
                    else ops.push(s.charAt(i)); 
                }
            }
        }
        Stack<Integer> stk = new Stack(); 
        for (var v : postfix) {
            if (v.equals("+") || v.equals("-") || v.equals("*") || v.equals("/")) {
                int y = stk.pop(), x = stk.pop(); 
                if (v.equals("+")) x += y; 
                else if (v.equals("-")) x -= y; 
                else if (v.equals("*")) x *= y; 
                else x /= y; 
                stk.push(x); 
            } else stk.push(Integer.parseInt(v)); 
        }
        return stk.pop(); 
    }


    /*774. Minimize Max Distance to Gas Station (Hard)
    You are given an integer array stations that represents the positions of 
    the gas stations on the x-axis. You are also given an integer k. You should 
    add k new gas stations. You can add the stations anywhere on the x-axis, 
    and not necessarily on an integer position. Let penalty() be the maximum 
    distance between adjacent gas stations after adding the k new stations. 
    Return the smallest possible value of penalty(). Answers within 10^-6 of 
    the actual answer will be accepted.

    Example 1:
    Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
    Output: 0.50000

    Example 2:
    Input: stations = [23,24,36,39,46,56,57,65,84,98], k = 1
    Output: 14.00000

    Constraints:
    * 10 <= stations.length <= 2000
    * 0 <= stations[i] <= 10^8
    * stations is sorted in a strictly increasing order.
    * 1 <= k <= 10^6*/

    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length; 
        double lo = 0, hi = stations[n-1] - stations[0]; 
        while (lo + 1e-6 < hi) {
            double mid = (lo + hi)/2; 
            int kk = 0; 
            for (int i = 0; i < n-1; ++i)
                kk += (int) (stations[i+1]-stations[i])/mid; 
            if (kk <= k) hi = mid; 
            else lo = mid; 
        }
        return lo; 
    }


    /*783. Minimum Distance Between BST Nodes (Easy)
    Given the root of a Binary Search Tree (BST), return the minimum difference 
    between the values of any two different nodes in the tree.

    Example 1:
    Input: root = [4,2,6,1,3]
    Output: 1

    Example 2:
    Input: root = [1,0,48,null,null,12,49]
    Output: 1

    Constraints:
    * The number of nodes in the tree is in the range [2, 100].
    * 0 <= Node.val <= 10^5

    Note: This question is the same as 530: 
          https://leetcode.com/problems/minimum-absolute-difference-in-bst/*/

    public int minDiffInBST(TreeNode root) {
        int ans = Integer.MAX_VALUE, prev = -1; 
        TreeNode node = root; 
        Stack<TreeNode> stk = new Stack(); 
        while (node != null || !stk.isEmpty()) 
            if (node != null) {
                stk.add(node); 
                node = node.left; 
            } else {
                node = stk.pop(); 
                if (prev >= 0) ans = Math.min(ans, node.val - prev); 
                prev = node.val; 
                node = node.right; 
            }
        return ans; 
    }


    /*787. Cheapest Flights Within K Stops (Medium)
    There are n cities connected by some number of flights. You are given an 
    array flights where flights[i] = [fromi, toi, pricei] indicates that there 
    is a flight from city fromi to city toi with cost pricei. You are also 
    given three integers src, dst, and k, return the cheapest price from src to 
    dst with at most k stops. If there is no such route, return -1.

    Example 1:
    Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
    Output: 700
    Explanation: The graph is shown above. The optimal path with at most 1 stop 
                 from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
                 Note that the path through cities [0,1,2,3] is cheaper but is 
                 invalid because it uses 2 stops.
    
    Example 2:
    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
    Output: 200
    Explanation: The graph is shown above. The optimal path with at most 1 stop 
                 from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
    
    Example 3:
    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
    Output: 500
    Explanation: The graph is shown above. The optimal path with no stops from 
                 city 0 to 2 is marked in red and has cost 500.

    Constraints:
    * 1 <= n <= 100
    * 0 <= flights.length <= (n * (n - 1) / 2)
    * flights[i].length == 3
    * 0 <= fromi, toi < n
    * fromi != toi
    * 1 <= pricei <= 10^4
    * There will not be any multiple flights between two cities.
    * 0 <= src, dst, k < n
    * src != dst*/

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] graph = new ArrayList[n]; 
        for (int i = 0; i < n; ++i) graph[i] = new ArrayList(); 
        for (var f : flights) 
            graph[f[0]].add(new int[] {f[1], f[2]});
        Queue<int[]> pq = new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0])); 
        pq.add(new int[] {0, src, 0}); 
        int[][] dist = new int[n][2]; 
        for (int i = 0; i < n; ++i) 
            if (i != src) dist[i][0] = dist[i][1] = Integer.MAX_VALUE; 
        while (!pq.isEmpty()) {
            var elem = pq.poll(); 
            int p = elem[0], u = elem[1], x = elem[2]; 
            if (u == dst) return p; 
            if (x <= k) 
                for (var v : graph[u]) 
                    if (p+v[1] < dist[v[0]][0] || x+1 < dist[v[0]][1]) {
                        pq.add(new int[] {p+v[1], v[0], x+1}); 
                        if (p+v[1] < dist[v[0]][0] || p+v[1] == dist[v[0]][0] && x+1 < dist[v[0]][1]) {
                            dist[v[0]][0] = p+v[1]; 
                            dist[v[0]][1] = x+1; 
                        }
                    }
        }
        return -1; 
    }


    /*881. Boats to Save People (Medium)
    You are given an array people where people[i] is the weight of the ith 
    person, and an infinite number of boats where each boat can carry a maximum 
    weight of limit. Each boat carries at most two people at the same time, 
    provided the sum of the weight of those people is at most limit. Return the 
    minimum number of boats to carry every given person.

    Example 1:
    Input: people = [1,2], limit = 3
    Output: 1
    Explanation: 1 boat (1, 2)

    Example 2:
    Input: people = [3,2,2,1], limit = 3
    Output: 3
    Explanation: 3 boats (1, 2), (2) and (3)

    Example 3:
    Input: people = [3,5,3,4], limit = 5
    Output: 4
    Explanation: 4 boats (3), (3), (4), (5)

    Constraints:
    * 1 <= people.length <= 5 * 10^4
    * 1 <= people[i] <= limit <= 3 * 10^4*/

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people); 
        int ans = 0; 
        for (int lo = 0, hi = people.length-1; lo <= hi; ) {
            if (lo < hi && people[lo] + people[hi] <= limit) ++lo; 
            --hi; 
            ++ans; 
        }
        return ans; 
    }


    /*904. Fruit Into Baskets (Medium)
    You are visiting a farm that has a single row of fruit trees arranged from 
    left to right. The trees are represented by an integer array fruits where 
    fruits[i] is the type of fruit the ith tree produces. You want to collect 
    as much fruit as possible. However, the owner has some strict rules that 
    you must follow:
    * You only have two baskets, and each basket can only hold a single type of 
      fruit. There is no limit on the amount of fruit each basket can hold.
    * Starting from any tree of your choice, you must pick exactly one fruit 
      from every tree (including the start tree) while moving to the right. The 
      picked fruits must fit in one of your baskets.
    * Once you reach a tree with fruit that cannot fit in your baskets, you 
      must stop.
    Given the integer array fruits, return the maximum number of fruits you can 
    pick.

    Example 1:
    Input: fruits = [1,2,1]
    Output: 3
    Explanation: We can pick from all 3 trees.

    Example 2:
    Input: fruits = [0,1,2,2]
    Output: 3
    Explanation: We can pick from trees [1,2,2]. If we had started at the first 
                 tree, we would only pick from trees [0,1].
    
    Example 3:
    Input: fruits = [1,2,3,2,2]
    Output: 4
    Explanation: We can pick from trees [2,3,2,2]. If we had started at the 
                 first tree, we would only pick from trees [1,2].

    Constraints:
    * 1 <= fruits.length <= 10^5
    * 0 <= fruits[i] < fruits.length*/

    public int totalFruit(int[] fruits) {
        int ans = 0; 
        Map<Integer, Integer> freq = new HashMap(); 
        for (int i = 0, ii = 0; i < fruits.length; ++i) {
            freq.merge(fruits[i], 1, Integer::sum); 
            while (freq.size() > 2) {
                freq.merge(fruits[ii], -1, Integer::sum); 
                if (freq.get(fruits[ii]) == 0) freq.remove(fruits[ii]); 
                ++ii; 
            }
            ans = Math.max(ans, i - ii + 1); 
        }
        return ans; 
    }


    /*909. Snakes and Ladders (Medium)
    You are given an n x n integer matrix board where the cells are labeled 
    from 1 to n2 in a Boustrophedon style starting from the bottom left of the 
    board (i.e. board[n - 1][0]) and alternating direction each row. You start 
    on square 1 of the board. In each move, starting from square curr, do the 
    following:
    * Choose a destination square next with a label in the range 
      [curr + 1, min(curr + 6, n2)].
      + This choice simulates the result of a standard 6-sided die roll: i.e., 
        there are always at most 6 destinations, regardless of the size of the 
        board.
    * If next has a snake or ladder, you must move to the destination of that 
      snake or ladder. Otherwise, you move to next.
    * The game ends when you reach the square n2.
    A board square on row r and column c has a snake or ladder if 
    board[r][c] != -1. The destination of that snake or ladder is board[r][c]. 
    Squares 1 and n2 do not have a snake or ladder. Note that you only take a 
    snake or ladder at most once per move. If the destination to a snake or 
    ladder is the start of another snake or ladder, you do not follow the 
    subsequent snake or ladder.
    * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, 
      your destination square is 2. You follow the ladder to square 3, but do 
      not follow the subsequent ladder to 4.
    Return the least number of moves required to reach the square n2. If it is 
    not possible to reach the square, return -1.

    Example 1:
    Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
    Output: 4
    Explanation: In the beginning, you start at square 1 (at row 5, column 0).
                 You decide to move to square 2 and must take the ladder to 
                 square 15. You then decide to move to square 17 and must take 
                 the snake to square 13. You then decide to move to square 14 
                 and must take the ladder to square 35. You then decide to move 
                 to square 36, ending the game. This is the lowest possible 
                 number of moves to reach the last square, so return 4.
    
    Example 2:
    Input: board = [[-1,-1],[-1,3]]
    Output: 1

    Constraints:
    * n == board.length == board[i].length
    * 2 <= n <= 20
    * grid[i][j] is either -1 or in the range [1, n^2].
    * The squares labeled 1 and n2 do not have any ladders or snakes.*/

    public int snakesAndLadders(int[][] board) {
        int n = board.length, ans = 0; 
        Queue<Integer> q = new LinkedList(); 
        q.add(1); 
        boolean[] seen = new boolean[n*n]; 
        seen[0] = true; 
        while (!q.isEmpty()) {
            for (int sz = q.size(); sz > 0; --sz) {
                int x = q.poll(); 
                if (x == n*n) return ans; 
                for (int xx = x+1; xx <= x+6 && xx <= n*n; ++xx) {
                    int i = (xx-1)/n, j = (xx-1)%n, jj = i%2 == 1 ? n-1-j : j, val = xx; 
                    if (board[n-1-i][jj] != -1) val = board[n-1-i][jj]; 
                    if (!seen[val-1]) {
                        q.add(val); 
                        seen[val-1] = true; 
                    }
                }
            }
            ++ans; 
        }
        return -1; 
    }


    /*918. Maximum Sum Circular Subarray (Medium)
    Given a circular integer array nums of length n, return the maximum 
    possible sum of a non-empty subarray of nums. A circular array means the 
    end of the array connects to the beginning of the array. Formally, the 
    next element of nums[i] is nums[(i + 1) % n] and the previous element of 
    nums[i] is nums[(i - 1 + n) % n]. A subarray may only include each element 
    of the fixed buffer nums at most once. Formally, for a subarray nums[i], 
    nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with 
    k1 % n == k2 % n.

    Example 1:
    Input: nums = [1,-2,3,-2]
    Output: 3
    Explanation: Subarray [3] has maximum sum 3.

    Example 2:
    Input: nums = [5,-3,5]
    Output: 10
    Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

    Example 3:
    Input: nums = [-3,-2,-3]
    Output: -2
    Explanation: Subarray [-2] has maximum sum -2.

    Constraints:
    * n == nums.length
    * 1 <= n <= 3 * 10^4
    * -3 * 10^4 <= nums[i] <= 3 * 10^4*/

    public int maxSubarraySumCircular(int[] nums) {
        int large = 0, small = 0, total = 0, most = Integer.MIN_VALUE, least = Integer.MIN_VALUE; 
        for (var x : nums) {
            total += x; 
            large = Math.max(0, large) + x; 
            most  = Math.max(most, large); 
            small = Math.max(0, small - x); 
            least = Math.max(least, small); 
        }
        return most >= 0 ? Math.max(most, total + least) : most; 
    }


    /*926. Flip String to Monotone Increasing (Medium)
    A binary string is monotone increasing if it consists of some number of 0's 
    (possibly none), followed by some number of 1's (also possibly none). You 
    are given a binary string s. You can flip s[i] changing it from 0 to 1 or 
    from 1 to 0. Return the minimum number of flips to make s monotone 
    increasing.

    Example 1:
    Input: s = "00110"
    Output: 1
    Explanation: We flip the last digit to get 00111.

    Example 2:
    Input: s = "010110"
    Output: 2
    Explanation: We flip to get 011111, or alternatively 000111.

    Example 3:
    Input: s = "00011000"
    Output: 2
    Explanation: We flip to get 00000000.

    Constraints:
    * 1 <= s.length <= 10^5
    * s[i] is either '0' or '1'.*/

    public int minFlipsMonoIncr(String s) {
        int ones = 0, flip = 0; 
        for (var ch : s.toCharArray()) {
            if (ch == '1') ++ones; 
            else flip = Math.min(ones, flip+1); 
        }
        return flip; 
    }


    /*953. Verifying an Alien Dictionary (Easy)
    In an alien language, surprisingly, they also use English lowercase letters, 
    but possibly in a different order. The order of the alphabet is some 
    permutation of lowercase letters. Given a sequence of words written in the 
    alien language, and the order of the alphabet, return true if and only if 
    the given words are sorted lexicographically in this alien language.

    Example 1:
    Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
    Output: true
    Explanation: As 'h' comes before 'l' in this language, then the sequence is 
                 sorted.

    Example 2:
    Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
    Output: false
    Explanation: As 'd' comes after 'l' in this language, then 
                 words[0] > words[1], hence the sequence is unsorted.
    
    Example 3:
    Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
    Output: false
    Explanation: The first three characters "app" match, and the second string 
                 is shorter (in size.) According to lexicographical rules 
                 "apple" > "app", because 'l' > '∅', where '∅' is defined as 
                 the blank character which is less than any other character 
                 (More info).

    Constraints:
    * 1 <= words.length <= 100
    * 1 <= words[i].length <= 20
    * order.length == 26
    * All characters in words[i] and order are English lowercase letters.*/

    public boolean isAlienSorted(String[] words, String order) {
        int[] mp = new int[26]; 
        for (int i = 0; i < 26; ++i) 
            mp[order.charAt(i)-'a'] = i; 
        String prev = "\n"; 
        for (var word : words) {
            StringBuilder sb = new StringBuilder(); 
            for (var ch : word.toCharArray()) 
                sb.append((char) (mp[ch-'a'] + 'a')); 
            if (prev.compareTo(sb.toString()) > 0) return false; 
            prev = sb.toString(); 
        }
        return true; 
    }


    /*974. Subarray Sums Divisible by K (Medium)
    Given an integer array nums and an integer k, return the number of non-
    empty subarrays that have a sum divisible by k. A subarray is a contiguous 
    part of an array.

    Example 1:
    Input: nums = [4,5,0,-2,-3,1], k = 5
    Output: 7
    Explanation: There are 7 subarrays with a sum divisible by k = 5:
                 [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], 
                 [0, -2, -3], [-2, -3]
    
    Example 2:
    Input: nums = [5], k = 9
    Output: 0

    Constraints:
    * 1 <= nums.length <= 3 * 10^4
    * -10^4 <= nums[i] <= 10^4
    * 2 <= k <= 10^4*/

    public int subarraysDivByK(int[] nums, int k) {
        int ans = 0, prefix = 0; 
        Map<Integer, Integer> freq = new HashMap(); 
        freq.put(0, 1); 
        for (var x : nums) {
            prefix = (prefix + x%k + k) % k; 
            ans += freq.getOrDefault(prefix, 0); 
            freq.merge(prefix, 1, Integer::sum); 
        }
        return ans; 
    }


    /*989. Add to Array-Form of Integer (Easy)
    The array-form of an integer num is an array representing its digits in 
    left to right order. For example, for num = 1321, the array form is 
    [1,3,2,1]. Given num, the array-form of an integer, and an integer k, 
    return the array-form of the integer num + k.

    Example 1:
    Input: num = [1,2,0,0], k = 34
    Output: [1,2,3,4]
    Explanation: 1200 + 34 = 1234

    Example 2:
    Input: num = [2,7,4], k = 181
    Output: [4,5,5]
    Explanation: 274 + 181 = 455

    Example 3:
    Input: num = [2,1,5], k = 806
    Output: [1,0,2,1]
    Explanation: 215 + 806 = 1021

    Constraints:
    * 1 <= num.length <= 10^4
    * 0 <= num[i] <= 9
    * num does not contain any leading zeros except for the zero itself.
    * 1 <= k <= 10^4*/

    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = Arrays.stream(num).boxed().collect(Collectors.toList());
        Collections.reverse(ans); 
        for (int i = 0; k > 0; ++i) {
            if (i == ans.size()) ans.add(0); 
            k += ans.get(i); 
            ans.set(i, k % 10); 
            k /= 10; 
        }
        Collections.reverse(ans); 
        return ans; 
    }


    /*997. Find the Town Judge (Easy)
    In a town, there are n people labeled from 1 to n. There is a rumor that 
    one of these people is secretly the town judge. If the town judge exists, 
    then:
    * The town judge trusts nobody.
    * Everybody (except for the town judge) trusts the town judge.
    * There is exactly one person that satisfies properties 1 and 2.
    You are given an array trust where trust[i] = [ai, bi] representing that 
    the person labeled ai trusts the person labeled bi. Return the label of the 
    town judge if the town judge exists and can be identified, or return -1 
    otherwise.

    Example 1:
    Input: n = 2, trust = [[1,2]]
    Output: 2

    Example 2:
    Input: n = 3, trust = [[1,3],[2,3]]
    Output: 3

    Example 3:
    Input: n = 3, trust = [[1,3],[2,3],[3,1]]
    Output: -1

    Constraints:
    * 1 <= n <= 1000
    * 0 <= trust.length <= 10^4
    * trust[i].length == 2
    * All the pairs of trust are unique.
    * ai != bi
    * 1 <= ai, bi <= n*/

    public int findJudge(int n, int[][] trust) {
        int[] degree = new int[n]; 
        for (var t : trust) {
            degree[t[0]-1] -= 1; 
            degree[t[1]-1] += 1; 
        }
        for (int i = 0; i < n; ++i)
            if (degree[i] == n-1) return i+1; 
        return -1; 
    }


    /*1011. Capacity To Ship Packages Within D Days (Medium)
    A conveyor belt has packages that must be shipped from one port to another 
    within days days. The ith package on the conveyor belt has a weight of 
    weights[i]. Each day, we load the ship with packages on the conveyor belt 
    (in the order given by weights). We may not load more weight than the 
    maximum weight capacity of the ship. Return the least weight capacity of 
    the ship that will result in all the packages on the conveyor belt being 
    shipped within days days.

    Example 1:
    Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
    Output: 15
    Explanation: A ship capacity of 15 is the minimum to ship all the packages 
                 in 5 days like this:
                 1st day: 1, 2, 3, 4, 5
                 2nd day: 6, 7
                 3rd day: 8
                 4th day: 9
                 5th day: 10
                 Note that the cargo must be shipped in the order given, so 
                 using a ship of capacity 14 and splitting the packages into 
                 parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not 
                 allowed.
    
    Example 2:
    Input: weights = [3,2,2,4,1,4], days = 3
    Output: 6
    Explanation: A ship capacity of 6 is the minimum to ship all the packages 
                 in 3 days like this:
                 1st day: 3, 2
                 2nd day: 2, 4
                 3rd day: 1, 4
    
    Example 3:
    Input: weights = [1,2,3,1,1], days = 4
    Output: 3
    Explanation: 1st day: 1
                 2nd day: 2
                 3rd day: 3
                 4th day: 1, 1

    Constraints:
    * 1 <= days <= weights.length <= 5 * 10^4
    * 1 <= weights[i] <= 500*/

    public int shipWithinDays(int[] weights, int days) {
        int lo = 0, hi = 0; 
        for (var w : weights) {
            lo = Math.max(lo, w); 
            hi += w; 
        }
        while (lo < hi) {
            int mid = lo + (hi - lo)/2, cnt = 0, val = hi; 
            for (var w : weights) {
                if (val + w > mid) {
                    ++cnt; 
                    val = 0; 
                }
                val += w; 
            }
            if (cnt <= days) hi = mid; 
            else lo = mid + 1; 
        }
        return lo; 
    }


    /*1046. Last Stone Weight (Easy)
    You are given an array of integers stones where stones[i] is the weight of 
    the ith stone. We are playing a game with the stones. On each turn, we 
    choose the heaviest two stones and smash them together. Suppose the 
    heaviest two stones have weights x and y with x <= y. The result of this 
    smash is:
    * If x == y, both stones are destroyed, and
    * If x != y, the stone of weight x is destroyed, and the stone of weight y 
      has new weight y - x.
    At the end of the game, there is at most one stone left. Return the weight 
    of the last remaining stone. If there are no stones left, return 0.

    Example 1:
    Input: stones = [2,7,4,1,8,1]
    Output: 1
    Explanation: - We combine 7 and 8 to get 1 so the array converts to 
                   [2,4,1,1,1] then,
                 - we combine 2 and 4 to get 2 so the array converts to 
                   [2,1,1,1] then,
                 - we combine 2 and 1 to get 1 so the array converts to 
                   [1,1,1] then,
                 - we combine 1 and 1 to get 0 so the array converts to [1] 
                   then that's the value of the last stone.
    
    Example 2:
    Input: stones = [1]
    Output: 1

    Constraints:
    * 1 <= stones.length <= 30
    * 1 <= stones[i] <= 1000*/

    public int lastStoneWeight(int[] stones) {
        Queue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x)); 
        for (var x : stones) pq.add(x); 
        while (pq.size() > 1) {
            int x = pq.poll(), y = pq.poll(); 
            if (x > y) pq.add(x-y); 
        }
        return pq.size() == 1 ? pq.poll() : 0; 
    }


    /*1055. Shortest Way to Form String (Medium)
    A subsequence of a string is a new string that is formed from the original 
    string by deleting some (can be none) of the characters without disturbing 
    the relative positions of the remaining characters. (i.e., "ace" is a 
    subsequence of "abcde" while "aec" is not). Given two strings source and 
    target, return the minimum number of subsequences of source such that their 
    concatenation equals target. If the task is impossible, return -1.

    Example 1:
    Input: source = "abc", target = "abcbc"
    Output: 2
    Explanation: The target "abcbc" can be formed by "abc" and "bc", which are 
                 subsequences of source "abc".
    
    Example 2:
    Input: source = "abc", target = "acdbc"
    Output: -1
    Explanation: The target string cannot be constructed from the subsequences 
                 of source string due to the character "d" in target string.
    
    Example 3:
    Input: source = "xyz", target = "xzyxz"
    Output: 3
    Explanation: The target string can be constructed as follows 
                 "xz" + "y" + "xz".

    Constraints:
    * 1 <= source.length, target.length <= 1000
    * source and target consist of lowercase English letters.*/

    public int shortestWay(String source, String target) {
        Map<Character, List<Integer>> locs = new HashMap(); 
        for (int i = 0; i < source.length(); ++i) {
            locs.putIfAbsent(source.charAt(i), new ArrayList()); 
            locs.get(source.charAt(i)).add(i); 
        }
        int ans = 0, i = 0; 
        for (var x : target.toCharArray()) {
            if (!locs.containsKey(x)) return -1; 
            int k = Collections.binarySearch(locs.get(x), i); 
            if (k < 0) k = -k-1; 
            if (k == locs.get(x).size()) {
                k = 0; 
                ++ans; 
            }
            i = locs.get(x).get(k) + 1; 
        }
        return ++ans; 
    }


    /*1063. Number of Valid Subarrays (Hard)
    Given an integer array nums, return the number of non-empty subarrays with 
    the leftmost element of the subarray not larger than other elements in the 
    subarray. A subarray is a contiguous part of an array.

    Example 1:
    Input: nums = [1,4,2,5,3]
    Output: 11
    Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],
                 [1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].
    
    Example 2:
    Input: nums = [3,2,1]
    Output: 3
    Explanation: The 3 valid subarrays are: [3],[2],[1].

    Example 3:
    Input: nums = [2,2,2]
    Output: 6
    Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].

    Constraints:
    * 1 <= nums.length <= 5 * 10^4
    * 0 <= nums[i] <= 10^5*/

    public int validSubarrays(int[] nums) {
        int ans = 0; 
        Stack<Integer> stk = new Stack(); 
        for (var x : nums) {
            while (!stk.isEmpty() && stk.peek() > x) stk.pop(); 
            stk.push(x); 
            ans += stk.size(); 
        }
        return ans; 
    }


    /*1071. Greatest Common Divisor of Strings (Easy)
    For two strings s and t, we say "t divides s" if and only if s = t + ... + t 
    (i.e., t is concatenated with itself one or more times). Given two strings 
    str1 and str2, return the largest string x such that x divides both str1 
    and str2.

    Example 1:
    Input: str1 = "ABCABC", str2 = "ABC"
    Output: "ABC"

    Example 2:
    Input: str1 = "ABABAB", str2 = "ABAB"
    Output: "AB"

    Example 3:
    Input: str1 = "LEET", str2 = "CODE"
    Output: ""

    Constraints:
    * 1 <= str1.length, str2.length <= 1000
    * str1 and str2 consist of English uppercase letters.*/

    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return ""; 
        int g = BigInteger.valueOf(str1.length()).gcd(BigInteger.valueOf(str2.length())).intValue();
        return str1.substring(0, g); 
    }


    /*1121. Divide Array Into Increasing Sequences (Hard)
    Given an integer array nums sorted in non-decreasing order and an integer k, 
    return true if this array can be divided into one or more disjoint 
    increasing subsequences of length at least k, or false otherwise.

    Example 1:
    Input: nums = [1,2,2,3,3,4,4], k = 3
    Output: true
    Explanation: The array can be divided into two subsequences [1,2,3,4] and 
                 [2,3,4] with lengths at least 3 each.
    
    Example 2:
    Input: nums = [5,6,6,7,8], k = 3
    Output: false
    Explanation: There is no way to divide the array using the conditions 
                 required.

    Constraints:
    * 1 <= k <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^5
    * nums is sorted in non-decreasing order.*/

    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap(); 
        int m = 0; 
        for (var x : nums) {
            freq.merge(x, 1, Integer::sum); 
            m = Math.max(m, freq.get(x)); 
        }
        return m * k <= nums.length; 
    }


    /*1129. Shortest Path with Alternating Colors (Medium)
    You are given an integer n, the number of nodes in a directed graph where 
    the nodes are labeled from 0 to n - 1. Each edge is red or blue in this 
    graph, and there could be self-edges and parallel edges. You are given two 
    arrays redEdges and blueEdges where:
    * redEdges[i] = [ai, bi] indicates that there is a directed red edge from 
      node ai to node bi in the graph, and
    * blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from 
      node uj to node vj in the graph.
    Return an array answer of length n, where each answer[x] is the length of 
    the shortest path from node 0 to node x such that the edge colors alternate 
    along the path, or -1 if such a path does not exist.

    Example 1:
    Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
    Output: [0,1,-1]

    Example 2:
    Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
    Output: [0,1,-1]

    Constraints:
    * 1 <= n <= 100
    * 0 <= redEdges.length, blueEdges.length <= 400
    * redEdges[i].length == blueEdges[j].length == 2
    * 0 <= ai, bi, uj, vj < n*/

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<int[]>[] graph = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) graph[u] = new ArrayList(); 
        for (var e : redEdges) graph[e[0]].add(new int[]{e[1], 0}); 
        for (var e : blueEdges) graph[e[0]].add(new int[]{e[1], 1}); 
        Queue<int[]> q = new LinkedList(); 
        q.add(new int[]{0, 0}); 
        q.add(new int[]{0, 1}); 
        int[][] dist = new int[n][2]; 
        for (int i = 0; i < n; ++i) dist[i][0] = dist[i][1] = Integer.MAX_VALUE; 
        for (int k = 0; !q.isEmpty(); ++k) 
            for (int sz = q.size(); sz > 0; --sz) {
                var elem = q.poll(); 
                int u = elem[0], c = elem[1]; 
                if (dist[u][c] > k) {
                    dist[u][c] = k; 
                    for (var v : graph[u]) 
                        if (v[1] != c) q.add(v); 
                }
            }
        int[] ans = new int[n]; 
        for (int i = 0; i < n; ++i) {
            int v = Math.min(dist[i][0], dist[i][1]); 
            ans[i] = v < Integer.MAX_VALUE ? v : -1; 
        }
        return ans; 
    }


    /*1137. N-th Tribonacci Number (Easy)
    The Tribonacci sequence Tn is defined as follows: 
    T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
    Given n, return the value of Tn.

    Example 1:
    Input: n = 4
    Output: 4
    Explanation: T_3 = 0 + 1 + 1 = 2
                 T_4 = 1 + 1 + 2 = 4
    
    Example 2:
    Input: n = 25
    Output: 1389537

    Constraints:
    * 0 <= n <= 37
    * The answer is guaranteed to fit within a 32-bit integer, ie. 
      answer <= 2^31 - 1.*/

    public int tribonacci(int n) {
        int[] dp = new int[] {0, 1, 1}; 
        for (int i = 0; i < n; ++i) 
            dp[i%3] = dp[0] + dp[1] + dp[2]; 
        return dp[n%3]; 
    }


    /*1183. Maximum Number of Ones (Hard)
    Consider a matrix M with dimensions width * height, such that every cell 
    has value 0 or 1, and any square sub-matrix of M of size 
    sideLength * sideLength has at most maxOnes ones. Return the maximum 
    possible number of ones that the matrix M can have.

    Example 1:
    Input: width = 3, height = 3, sideLength = 2, maxOnes = 1
    Output: 4
    Explanation: In a 3*3 matrix, no 2*2 sub-matrix can have more than 1 one.
                 The best solution that has 4 ones is:
                 [1,0,1]
                 [0,0,0]
                 [1,0,1]
    
    Example 2:
    Input: width = 3, height = 3, sideLength = 2, maxOnes = 2
    Output: 6
    Explanation: [1,0,1]
                 [1,0,1]
                 [1,0,1]

    Constraints:
    * 1 <= width, height <= 100
    * 1 <= sideLength <= width, height
    * 0 <= maxOnes <= sideLength * sideLength*/

    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        if (width < height) { int temp = width; width = height; height = temp; }
        int nw = width/sideLength, rw = width%sideLength, nh = height/sideLength, rh = height%sideLength; 
        int ans = nw * nh * maxOnes + (nw + nh + 1) * Math.min(maxOnes, rw * rh); 
        maxOnes -= rw * rh; 
        if (maxOnes > 0) {
            ans += nw * Math.min(maxOnes, (sideLength - rw) * rh); 
            maxOnes -= (sideLength - rw) * rh; 
            if (maxOnes > 0) ans += nh * Math.min(maxOnes, rw * (sideLength - rh)); 
        }
        return ans; 
    }


    /*1199. Minimum Time to Build Blocks (Hard)
    You are given a list of blocks, where blocks[i] = t means that the i-th 
    block needs t units of time to be built. A block can only be built by 
    exactly one worker. A worker can either split into two workers (number of 
    workers increases by one) or build a block then go home. Both decisions 
    cost some time. The time cost of spliting one worker into two workers is 
    given as an integer split. Note that if two workers split at the same time, 
    they split in parallel so the cost would be split. Output the minimum time 
    needed to build all blocks. Initially, there is only one worker.

    Example 1:
    Input: blocks = [1], split = 1
    Output: 1
    Explanation: We use 1 worker to build 1 block in 1 time unit.

    Example 2:
    Input: blocks = [1,2], split = 5
    Output: 7
    Explanation: We split the worker into 2 workers in 5 time units then assign 
                 each of them to a block so the cost is 5 + max(1, 2) = 7.
    
    Example 3:
    Input: blocks = [1,2,3], split = 1
    Output: 4
    Explanation: Split 1 worker into 2, then assign the first worker to the 
                 last block and split the second worker into 2. Then, use the 
                 two unassigned workers to build the first two blocks. The cost 
                 is 1 + max(3, 1 + max(1, 2)) = 4.

    Constraints:
    * 1 <= blocks.length <= 1000
    * 1 <= blocks[i] <= 10^5
    * 1 <= split <= 100*/

    public int minBuildTime(int[] blocks, int split) {
        Queue<Integer> pq = new PriorityQueue(); 
        for (var x : blocks) pq.add(x); 
        while (pq.size() > 1) {
            pq.poll();
            int v = pq.poll(); 
            pq.add(v+split); 
        }
        return pq.peek(); 
    }


    /*1216. Valid Palindrome III (Hard)
    Given a string s and an integer k, return true if s is a k-palindrome. A 
    string is k-palindrome if it can be transformed into a palindrome by 
    removing at most k characters from it.

    Example 1:
    Input: s = "abcdeca", k = 2
    Output: true
    Explanation: Remove 'b' and 'e' characters.

    Example 2:
    Input: s = "abbababa", k = 1
    Output: true

    Constraints:
    * 1 <= s.length <= 1000
    * s consists of only lowercase English letters.
    * 1 <= k <= s.length*/

    public boolean isValidPalindrome(String s, int k) {
        int n = s.length(); 
        int[][] dp = new int[n+1][n]; 
        for (int i = n-1; i >= 0; --i) {
            dp[i][i] = 1; 
            for (int j = i+1; j < n; ++j) 
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = 2 + dp[i+1][j-1]; 
                else dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]); 
        }
        return dp[0][n-1] >= n-k; 
    }


    /*1231. Divide Chocolate (Hard)
    You have one chocolate bar that consists of some chunks. Each chunk has its 
    own sweetness given by the array sweetness. You want to share the chocolate 
    with your k friends so you start cutting the chocolate bar into k + 1 
    pieces using k cuts, each piece consists of some consecutive chunks. Being 
    generous, you will eat the piece with the minimum total sweetness and give 
    the other pieces to your friends. Find the maximum total sweetness of the 
    piece you can get by cutting the chocolate bar optimally.

    Example 1:
    Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
    Output: 6
    Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

    Example 2:
    Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
    Output: 1
    Explanation: There is only one way to cut the bar into 9 pieces.

    Example 3:
    Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
    Output: 5
    Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]

    Constraints:
    * 0 <= k < sweetness.length <= 10^4
    * 1 <= sweetness[i] <= 10^5*/

    public int maximizeSweetness(int[] sweetness, int k) {
        int lo = 0, hi = 1_000_000_000;
        while (lo < hi) {
            int mid = lo + (hi-lo+1)/2, cnt = 0, prefix = 0; 
            for (var x : sweetness) {
                prefix += x; 
                if (prefix >= mid) {
                    ++cnt; 
                    prefix = 0; 
                }
            }
            if (cnt < k+1) hi = mid-1; 
            else lo = mid; 
        }
        return lo; 
    }


    /*1246. Palindrome Removal (Hard)
    You are given an integer array arr. In one move, you can select a 
    palindromic subarray arr[i], arr[i + 1], ..., arr[j] where i <= j, and 
    remove that subarray from the given array. Note that after removing a 
    subarray, the elements on the left and on the right of that subarray move 
    to fill the gap left by the removal. Return the minimum number of moves 
    needed to remove all numbers from the array.

    Example 1:
    Input: arr = [1,2]
    Output: 2

    Example 2:
    Input: arr = [1,3,4,1,5]
    Output: 3
    Explanation: Remove [4] then remove [1,3,1] then remove [5].

    Constraints:
    * 1 <= arr.length <= 100
    * 1 <= arr[i] <= 20*/

    public int minimumMoves(int[] arr) {
        int n = arr.length; 
        int[][] dp = new int[n][n+1]; 
        for (int i = 0; i < n; ++i) Arrays.fill(dp[i], Integer.MAX_VALUE); 
        for (int i = n-1; i >= 0; --i)
            for (int j = 0; j <= n; ++j)
                if (i+1 >= j) dp[i][j] = 1; 
                else {
                    dp[i][j] = j-i; 
                    if (arr[i] == arr[j-1]) dp[i][j] = Math.min(dp[i][j], dp[i+1][j-1]); 
                    for (int k = i+1; k < j; ++k) 
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]); 
                }
        return dp[0][n]; 
    }


    /*1259. Handshakes That Don't Cross (Hard)
    You are given an even number of people numPeople that stand around a circle 
    and each person shakes hands with someone else so that there are 
    numPeople / 2 handshakes total. Return the number of ways these handshakes 
    could occur such that none of the handshakes cross. Since the answer could 
    be very large, return it modulo 10^9 + 7.

    Example 1:
    Input: numPeople = 4
    Output: 2
    Explanation: There are two ways to do it, the first way is [(1,2),(3,4)] 
                 and the second one is [(2,3),(4,1)].
    
    Example 2:
    Input: numPeople = 6
    Output: 5

    Constraints:
    * 2 <= numPeople <= 1000
    * numPeople is even.*/

    public int numberOfWays(int numPeople) {
        int mod = 1_000_000_007, n = numPeople/2; 
        long[] fact = new long[2*n+1], ifact = new long[n+2], inv = new long[n+2]; 
        fact[0] = ifact[0] = inv[0] = inv[1] = 1; 
        for (int x = 1; x <= 2*n; ++x) {
            if (2 <= x && x <= n+1) inv[x] = mod - mod/x * inv[mod % x] % mod; 
            fact[x] = fact[x-1] * x % mod; 
            if (x <= n+1) ifact[x] = ifact[x-1] * inv[x] % mod; 
        }
        return (int) (fact[2*n] * ifact[n] % mod * ifact[n+1] % mod); 
    }


    /*1274. Number of Ships in a Rectangle (Hard)
    (This problem is an interactive problem.) Each ship is located at an 
    integer point on the sea represented by a cartesian plane, and each integer 
    point may contain at most 1 ship. You have a function 
    Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and 
    returns true If there is at least one ship in the rectangle represented by 
    the two points, including on the boundary. Given two points: the top right 
    and bottom left corners of a rectangle, return the number of ships present 
    in that rectangle. It is guaranteed that there are at most 10 ships in that 
    rectangle. Submissions making more than 400 calls to hasShips will be 
    judged Wrong Answer. Also, any solutions that attempt to circumvent the 
    judge will result in disqualification.

    Example :
    Input: ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
    Output: 3
    Explanation: From [0,0] to [4,4] we can count 3 ships within the range.
    
    Example 2:
    Input: ans = [[1,1],[2,2],[3,3]], topRight = [1000,1000], bottomLeft = [0,0]
    Output: 3

    Constraints:
    * On the input ships is only given to initialize the map internally. You 
      must solve this problem "blindfolded". In other words, you must find the 
      answer using the given hasShips API, without knowing the ships position.
    * 0 <= bottomLeft[0] <= topRight[0] <= 1000
    * 0 <= bottomLeft[1] <= topRight[1] <= 1000
    * topRight != bottomLeft*/

    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (!sea.hasShips(topRight, bottomLeft)) return 0;
        int x0 = bottomLeft[0], y0 = bottomLeft[1], x1 = topRight[0], y1 = topRight[1]; 
        if (x0 == x1 && y0 == y1) return 1;
        if (x0 < x1) {
            int mid = (x0 + x1)/2; 
            return countShips(sea, topRight, new int[]{mid+1, y0}) + countShips(sea, new int[]{mid, y1}, bottomLeft); 
        }
        int mid = (y0 + y1)/2; 
        return countShips(sea, topRight, new int[]{x0, mid+1}) + countShips(sea, new int[]{x1, mid}, bottomLeft);
    }


    /*1372. Longest ZigZag Path in a Binary Tree (Medium)
    You are given the root of a binary tree. A ZigZag path for a binary tree is 
    defined as follow:
    * Choose any node in the binary tree and a direction (right or left).
    * If the current direction is right, move to the right child of the current 
      node; otherwise, move to the left child.
    * Change the direction from right to left or from left to right.
    * Repeat the second and third steps until you can't move in the tree.
    Zigzag length is defined as the number of nodes visited - 1. (A single node 
    has a length of 0). Return the longest ZigZag path contained in that tree.

    Example 1:
    Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
    Output: 3
    Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

    Example 2:
    Input: root = [1,1,1,null,1,null,null,1,1,null,1]
    Output: 4
    Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> 
                 right).
    
    Example 3:
    Input: root = [1]
    Output: 0

    Constraints:
    * The number of nodes in the tree is in the range [1, 5 * 10^4].
    * 1 <= Node.val <= 100*/

    public int longestZigZag(TreeNode root) {
        int ans = 0; 
        Stack<Object[]> stk = new Stack(); 
        stk.push(new Object[]{root, false, 0}); 
        stk.push(new Object[]{root, true, 0}); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            TreeNode node = (TreeNode) elem[0]; 
            boolean left = (boolean) elem[1]; 
            int val = (int) elem[2]; 
            ans = Math.max(ans, val); 
            if (node.left != null) stk.push(new Object[]{node.left, true, left ? 1 : val+1}); 
            if (node.right != null) stk.push(new Object[]{node.right, false, left ? val+1 : 1}); 
        }
        return ans; 
    }


    /*1443. Minimum Time to Collect All Apples in a Tree (Medium)
    Given an undirected tree consisting of n vertices numbered from 0 to n-1, 
    which has some apples in their vertices. You spend 1 second to walk over 
    one edge of the tree. Return the minimum time in seconds you have to spend 
    to collect all apples in the tree, starting at vertex 0 and coming back to 
    this vertex. The edges of the undirected tree are given in the array edges, 
    where edges[i] = [ai, bi] means that exists an edge connecting the vertices 
    ai and bi. Additionally, there is a boolean array hasApple, where 
    hasApple[i] = true means that vertex i has an apple; otherwise, it does not 
    have any apple.

    Example 1:
    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], 
           hasApple = [false,false,true,false,true,true,false]
    Output: 8 
    Explanation: The figure above represents the given tree where red vertices 
                 have an apple. One optimal path to collect all apples is shown 
                 by the green arrows.  
    
    Example 2:
    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], 
           hasApple = [false,false,true,false,false,true,false]
    Output: 6
    Explanation: The figure above represents the given tree where red vertices 
                 have an apple. One optimal path to collect all apples is shown 
                 by the green arrows.  
    
    Example 3:
    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], 
           hasApple = [false,false,false,false,false,false,false]
    Output: 0

    Constraints:
    * 1 <= n <= 10^5
    * edges.length == n - 1
    * edges[i].length == 2
    * 0 <= ai < bi <= n - 1
    * fromi < toi
    * hasApple.length == n*/

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] tree = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) tree[u] = new ArrayList(); 
        for (var e : edges) {
            tree[e[0]].add(e[1]); 
            tree[e[1]].add(e[0]); 
        }
        int[] ans = new int[n]; 
        boolean[] seen = new boolean[n]; 
        Stack<Pair<Integer, Integer>> stk = new Stack(); stk.push(new Pair(0, -1)); 
        while (!stk.isEmpty()) {
            var elem = stk.peek(); 
            int u = elem.getKey(), p = elem.getValue(); 
            if (seen[u]) {
                for (var v : tree[u]) 
                    if (v != p) ans[u] += ans[v]; 
                if (u > 0 && (ans[u] > 0 || hasApple.get(u))) ++ans[u]; 
                stk.pop(); 
            } else {
                for (var v : tree[u]) 
                    if (v != p) stk.push(new Pair(v, u)); 
                seen[u] = true; 
            }
        }
        return ans[0]*2; 
    }


    /*1470. Shuffle the Array (Easy)
    Given the array nums consisting of 2n elements in the form 
    [x1,x2,...,xn,y1,y2,...,yn]. Return the array in the form 
    [x1,y1,x2,y2,...,xn,yn].

    Example 1:
    Input: nums = [2,5,1,3,4,7], n = 3
    Output: [2,3,5,4,1,7] 
    Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is 
                 [2,3,5,4,1,7].

    Example 2:
    Input: nums = [1,2,3,4,4,3,2,1], n = 4
    Output: [1,4,2,3,3,2,4,1]
    
    Example 3:
    Input: nums = [1,1,2,2], n = 2
    Output: [1,2,1,2]

    Constraints:
    * 1 <= n <= 500
    * nums.length == 2n
    * 1 <= nums[i] <= 10^3*/

    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2*n]; 
        for (int i = 0; i < n; ++i) {
            ans[2*i] = nums[i]; 
            ans[2*i+1] = nums[i+n]; 
        }
        return ans; 
    }


    /*1516. Move Sub-Tree of N-Ary Tree (Hard)
    Given the root of an N-ary tree of unique values, and two nodes of the tree 
    p and q. You should move the subtree of the node p to become a direct child 
    of node q. If p is already a direct child of q, do not change anything. 
    Node p must be the last child in the children list of node q. Return the 
    root of the tree after adjusting it. There are 3 cases for nodes p and q:
    * Node q is in the sub-tree of node p.
    * Node p is in the sub-tree of node q.
    * Neither node p is in the sub-tree of node q nor node q is in the sub-tree 
      of node p.
    In cases 2 and 3, you just need to move p (with its sub-tree) to be a child 
    of q, but in case 1 the tree may be disconnected, thus you need to 
    reconnect the tree again. Please read the examples carefully before solving 
    this problem. Nary-Tree input serialization is represented in their level 
    order traversal, each group of children is separated by the null value (See 
    examples). For example, the above tree is serialized as 
    [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
     
    Example 1:
    Input: root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 4, q = 1
    Output: [1,null,2,3,4,null,5,null,6,null,7,8]
    Explanation: This example follows the second case as node p is in the sub-
                 tree of node q. We move node p with its sub-tree to be a 
                 direct child of node q. Notice that node 4 is the last child 
                 of node 1.
    
    Example 2:
    Input: root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 7, q = 4
    Output: [1,null,2,3,null,4,5,null,6,null,7,8]
    Explanation: Node 7 is already a direct child of node 4. We don't change 
                 anything.
    
    Example 3:
    Input: root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 3, q = 8
    Output: [1,null,2,null,4,5,null,7,8,null,null,null,3,null,6]
    Explanation: This example follows case 3 because node p is not in the sub-
                 tree of node q and vice-versa. We can move node 3 with its 
                 sub-tree and make it as node 8's child.

    Constraints:
    * The total number of nodes is between [2, 1000].
    * Each node has a unique value.
    * p != null
    * q != null
    * p and q are two different nodes (i.e. p != q).*/

    public Node moveSubTree(Node root, Node p, Node q) {
        if (!q.children.contains(p)) {
            Map<Node, Node> parent = new HashMap(); parent.put(root, null); 
            Stack<Node> stk = new Stack(); stk.push(root); 
            while (!stk.isEmpty()) {
                var u = stk.pop(); 
                for (var v : u.children) {
                    parent.put(v, u); 
                    stk.push(v); 
                }
            }
            Set<Node> anc = new HashSet(); 
            for (var n = q; n != null; n = parent.get(n)) anc.add(n); 
            if (anc.contains(p)) {
                parent.get(q).children.remove(q); 
                if (p == root) root = q; 
                else {
                    int i = parent.get(p).children.indexOf(p); 
                    parent.get(p).children.set(i, q); 
                }
            } else parent.get(p).children.remove(p); 
            q.children.add(p); 
        }
        return root; 
    }


    /*1519. Number of Nodes in the Sub-Tree With the Same Label (Medium)
    You are given a tree (i.e. a connected, undirected graph that has no cycles) 
    consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. The 
    root of the tree is the node 0, and each node of the tree has a label which 
    is a lower-case character given in the string labels (i.e. The node with 
    the number i has the label labels[i]). The edges array is given on the form 
    edges[i] = [ai, bi], which means there is an edge between nodes ai and bi 
    in the tree. Return an array of size n where ans[i] is the number of nodes 
    in the subtree of the ith node which have the same label as node i. A 
    subtree of a tree T is the tree consisting of a node in T and all of its 
    descendant nodes.

    Example 1:
    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
    Output: [2,1,1,1,1,1,1]
    Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 
                 'a' as well, thus the answer is 2. Notice that any node is 
                 part of its sub-tree. Node 1 has a label 'b'. The sub-tree of 
                 node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have 
                 different labels than node 1, the answer is just 1 (the node 
                 itself).
    
    Example 2:
    Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
    Output: [4,2,1,1]
    Explanation: The sub-tree of node 2 contains only node 2, so the answer is 
                 1. The sub-tree of node 3 contains only node 3, so the answer 
                 is 1. The sub-tree of node 1 contains nodes 1 and 2, both have 
                 label 'b', thus the answer is 2. The sub-tree of node 0 
                 contains nodes 0, 1, 2 and 3, all with label 'b', thus the 
                 answer is 4.
    
    Example 3:
    Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
    Output: [3,2,1,1,1]

    Constraints:
    * 1 <= n <= 10^5
    * edges.length == n - 1
    * edges[i].length == 2
    * 0 <= ai, bi < n
    * ai != bi
    * labels.length == n
    * labels is consisting of only of lowercase English letters.*/

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<Integer>[] tree = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) tree[u] = new ArrayList(); 
        for (var e : edges) {
            tree[e[0]].add(e[1]); 
            tree[e[1]].add(e[0]); 
        }
        int[] ans = new int[n]; 
        boolean[] seen = new boolean[n]; 
        int[][] freq = new int[n][26]; 
        Stack<Pair<Integer, Integer>> stk = new Stack(); 
        stk.push(new Pair(0, -1)); 
        while (!stk.isEmpty()) {
            var elem = stk.peek(); 
            int u = elem.getKey(), p = elem.getValue(); 
            if (seen[u]) {
                ++freq[u][labels.charAt(u) - 'a']; 
                for (var v : tree[u]) 
                    if (v != p) 
                        for (int i = 0; i < 26; ++i)
                            freq[u][i] += freq[v][i]; 
                ans[u] = freq[u][labels.charAt(u) - 'a']; 
                stk.pop(); 
            } else {
                for (var v : tree[u])
                    if (v != p) stk.push(new Pair(v, u)); 
                seen[u] = true; 
            }
        }
        return ans; 
    }


    /*1523. Count Odd Numbers in an Interval Range (Easy)
    Given two non-negative integers low and high. Return the count of odd 
    numbers between low and high (inclusive).

    Example 1:
    Input: low = 3, high = 7
    Output: 3
    Explanation: The odd numbers between 3 and 7 are [3,5,7].

    Example 2:
    Input: low = 8, high = 10
    Output: 1
    Explanation: The odd numbers between 8 and 10 are [9].

    Constraints: 0 <= low <= high <= 10^9*/

    public int countOdds(int low, int high) {
        return (high+1)/2 - low/2; 
    }


    /*1539. Kth Missing Positive Number (Easy)
    Given an array arr of positive integers sorted in a strictly increasing 
    order, and an integer k. Return the kth positive integer that is missing 
    from this array.

    Example 1:
    Input: arr = [2,3,4,7,11], k = 5
    Output: 9
    Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. 
                 The 5th missing positive integer is 9.
    
    Example 2:
    Input: arr = [1,2,3,4], k = 2
    Output: 6
    Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing 
                 positive integer is 6.

    Constraints:
    * 1 <= arr.length <= 1000
    * 1 <= arr[i] <= 1000
    * 1 <= k <= 1000
    * arr[i] < arr[j] for 1 <= i < j <= arr.length

    Follow up: Could you solve this problem in less than O(n) complexity?*/

    public int findKthPositive(int[] arr, int k) {
        int lo = 0, hi = arr.length; 
        while (lo < hi){
            int mid = lo + (hi-lo)/2; 
            if (arr[mid] - (mid+1) < k) lo = mid+1; 
            else hi = mid; 
        }
        return lo+k; 
    }


    /*1548. The Most Similar Path in a Graph (Hard)
    We have n cities and m bi-directional roads where roads[i] = [ai, bi] 
    connects city ai with city bi. Each city has a name consisting of exactly 
    three upper-case English letters given in the string array names. Starting 
    at any city x, you can reach any city y where y != x (i.e., the cities and 
    the roads are forming an undirected connected graph). You will be given a 
    string array targetPath. You should find a path in the graph of the same 
    length and with the minimum edit distance to targetPath. You need to return 
    the order of the nodes in the path with the minimum edit distance. The path 
    should be of the same length of targetPath and should be valid (i.e., there 
    should be a direct road between ans[i] and ans[i + 1]). If there are 
    multiple answers return any one of them. 

    Example 1:
    Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
    Output: [0,2,4,2]
    Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
                 [0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has 
                           edit distance = 1 with targetPath.
                 [0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has 
                           edit distance = 1 with targetPath.
                 [0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has 
                           edit distance = 1 with targetPath.
    
    Example 2:
    Input: n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
    Output: [0,1,0,1,0,1,0,1]
    Explanation: Any path in this graph has edit distance = 8 with targetPath.

    Example 3:
    Input: n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
    Output: [3,4,5,4,3,2,1]
    Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with 
                 targetPath. It's equivalent to 
                 ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]

    Constraints:
    * 2 <= n <= 100
    * m == roads.length
    * n - 1 <= m <= (n * (n - 1) / 2)
    * 0 <= ai, bi <= n - 1
    * ai != bi
    * The graph is guaranteed to be connected and each pair of nodes may have 
      at most one direct road.
    * names.length == n
    * names[i].length == 3
    * names[i] consists of upper-case English letters.
    * There can be two cities with the same name.
    * 1 <= targetPath.length <= 100
    * targetPath[i].length == 3
    * targetPath[i] consists of upper-case English letters.

    Follow up: If each node can be visited only once in the path, What should 
               you change in your solution?*/

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<Integer>[] graph = new ArrayList[n]; 
        for (int i = 0; i < n; ++i) graph[i] = new ArrayList(); 
        for (var r : roads) {
            graph[r[0]].add(r[1]); 
            graph[r[1]].add(r[0]); 
        }
        int m = targetPath.length; 
        int[][] mp = new int[m][n], dp = new int[m+1][n]; 
        for (int i = m-1; i >= 0; --i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE); 
            for (int j = 0; j < n; ++j) {
                for (var k : graph[j]) 
                    if (dp[i+1][k] < dp[i][j]) {
                        dp[i][j] = dp[i+1][k]; 
                        mp[i][j] = k; 
                    }
                if (!targetPath[i].equals(names[j])) ++dp[i][j]; 
            }
        }
        int jj = 0; 
        for (int j = 0, v = Integer.MAX_VALUE; j < n; ++j) 
            if (dp[0][j] < v) {
                jj = j; 
                v = dp[0][j]; 
            }
        List<Integer> ans = new ArrayList(); 
        for (int i = 0; i < m; ++i) {
            ans.add(jj); 
            jj = mp[i][jj]; 
        }
        return ans; 
    }


    /*1597. Build Binary Expression Tree From Infix Expression (Hard)
    A binary expression tree is a kind of binary tree used to represent 
    arithmetic expressions. Each node of a binary expression tree has either 
    zero or two children. Leaf nodes (nodes with 0 children) correspond to 
    operands (numbers), and internal nodes (nodes with 2 children) correspond 
    to the operators '+' (addition), '-' (subtraction), '*' (multiplication), 
    and '/' (division). For each internal node with operator o, the infix 
    expression it represents is (A o B), where A is the expression the left 
    subtree represents and B is the expression the right subtree represents.
    You are given a string s, an infix expression containing operands, the 
    operators described above, and parentheses '(' and ')'. Return any valid 
    binary expression tree, whose in-order traversal reproduces s after 
    omitting the parenthesis from it. Please note that order of operations 
    applies in s. That is, expressions in parentheses are evaluated first, and 
    multiplication and division happen before addition and subtraction. 
    Operands must also appear in the same order in both s and the in-order 
    traversal of the tree.

    Example 1:
    Input: s = "3*4-2*5"
    Output: [-,*,*,3,4,2,5]
    Explanation: The tree above is the only valid tree whose inorder traversal 
                 produces s.
    
    Example 2:
    Input: s = "2-3/(5*2)+1"
    Output: [+,-,1,2,/,null,null,null,null,3,*,null,null,5,2]
    Explanation: The inorder traversal of the tree above is 2-3/5*2+1 which is 
                 the same as s without the parenthesis. The tree also produces 
                 the correct result and its operands are in the same order as 
                 they appear in s. The tree below is also a valid binary 
                 expression tree with the same inorder traversal as s, but it 
                 not a valid answer because it does not evaluate to the same 
                 value. The third tree below is also not valid. Although it 
                 produces the same result and is equivalent to the above trees, 
                 its inorder traversal does not produce s and its operands are 
                 not in the same order as s.

    Example 3:
    Input: s = "1+2+3+4+5"
    Output: [+,+,5,+,4,null,null,+,3,null,null,1,2]
    Explanation: The tree [+,+,5,+,+,null,null,1,2,3,4] is also one of many 
                 other valid trees.

    Constraints:
    * 1 <= s.length <= 100
    * s consists of digits and the characters '+', '-', '*', and '/'.
    * Operands in s are exactly 1 digit.
    * It is guaranteed that s is a valid expression.*/

    public Node expTree(String s) {
        s = "(" + s + ")"; 
        Map<Character, Integer> precedence = new HashMap(); 
        precedence.put('(', 0); 
        precedence.put(')', 1); 
        precedence.put('+', 1); 
        precedence.put('-', 1); 
        precedence.put('*', 2); 
        precedence.put('/', 2); 
        Stack<Character> ops = new Stack(); 
        List<Character> postfix = new ArrayList(); 
        for (var ch : s.toCharArray()) 
            if ('0' <= ch && ch <= '9') postfix.add(ch); 
            else if (ch == '(') ops.push(ch); 
            else {
                while (!ops.isEmpty() && precedence.get(ops.peek()) >= precedence.get(ch)) 
                    postfix.add(ops.pop()); 
                if (ch == ')') ops.pop(); 
                else ops.push(ch); 
            }
        Stack<Node> stack = new Stack(); 
        for (var ch : postfix) {
            Node node = new Node(ch); 
            if (ch < '0' || ch > '9') {
                node.right = stack.pop(); 
                node.left = stack.pop(); 
            }
            stack.push(node); 
        }
        return stack.pop(); 
    }


    /*1675. Minimize Deviation in Array (Hard)
    You are given an array nums of n positive integers. You can perform two 
    types of operations on any element of the array any number of times:
    * If the element is even, divide it by 2.
      + For example, if the array is [1,2,3,4], then you can do this operation 
        on the last element, and the array will be [1,2,3,2].
    * If the element is odd, multiply it by 2.
      + For example, if the array is [1,2,3,4], then you can do this operation 
        on the first element, and the array will be [2,2,3,4].
    The deviation of the array is the maximum difference between any two 
    elements in the array. Return the minimum deviation the array can have 
    after performing some number of operations.

    Example 1:
    Input: nums = [1,2,3,4]
    Output: 1
    Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], 
                 then the deviation will be 3 - 2 = 1.
    
    Example 2:
    Input: nums = [4,1,5,20,3]
    Output: 3
    Explanation: You can transform the array after two operations to 
                 [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
    
    Example 3:
    Input: nums = [2,10,8]
    Output: 3

    Constraints:
    * n == nums.length
    * 2 <= n <= 5 * 10^4
    * 1 <= nums[i] <= 10^9*/

    public int minimumDeviation(int[] nums) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); 
        int lo = Integer.MAX_VALUE; 
        for (var x : nums) {
            if (x % 2 == 1) x *= 2; 
            pq.add(x); 
            lo = Math.min(lo, x); 
        }
        int ans = pq.peek() - lo; 
        while (pq.peek() % 2 == 0) {
            int val = pq.poll() / 2; 
            pq.add(val); 
            lo = Math.min(lo, val); 
            ans = Math.min(ans, pq.peek() - lo); 
        }
        return ans; 
    }


    /*1697. Checking Existence of Edge Length Limited Paths (Hard)
    An undirected graph of n nodes is defined by edgeList, where 
    edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with 
    distance disi. Note that there may be multiple edges between two nodes.
    Given an array queries, where queries[j] = [pj, qj, limitj], your task is 
    to determine for each queries[j] whether there is a path between pj and qj 
    such that each edge on the path has a distance strictly less than limitj .
    Return a boolean array answer, where answer.length == queries.length and 
    the jth value of answer is true if there is a path for queries[j] is true, 
    and false otherwise.

    Example 1:
    Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
    Output: [false,true]
    Explanation: The above figure shows the given graph. Note that there are 
                 two overlapping edges between 0 and 1 with distances 2 and 16.
                 For the first query, between 0 and 1 there is no path where 
                 each distance is less than 2, thus we return false for this 
                 query. For the second query, there is a path (0 -> 1 -> 2) of 
                 two edges with distances less than 5, thus we return true for 
                 this query.
    
    Example 2:
    Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
    Output: [true,false]
    Exaplanation: The above figure shows the given graph.

    Constraints:
    * 2 <= n <= 10^5
    * 1 <= edgeList.length, queries.length <= 10^5
    * edgeList[i].length == 3
    * queries[j].length == 3
    * 0 <= ui, vi, pj, qj <= n - 1
    * ui != vi
    * pj != qj
    * 1 <= disi, limitj <= 10^9
    * There may be multiple edges between two nodes.*/
    
    private int find(int p, int[] parent) {
        if (p != parent[p]) 
            parent[p] = find(parent[p], parent); 
        return parent[p]; 
    }
    
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int[] parent = new int[n]; 
        for (int i = 0; i < n; ++i) parent[i] = i; 
        int m = queries.length; 
        boolean[] ans = new boolean[m]; 
        Arrays.sort(edgeList, (a, b) -> Integer.compare(a[2], b[2])); 
        int[][] augs = new int[m][4]; 
        for (int i = 0; i < m; ++i) {
            augs[i][0] = queries[i][0]; 
            augs[i][1] = queries[i][1]; 
            augs[i][2] = queries[i][2]; 
            augs[i][3] = i; 
        }
        Arrays.sort(augs, (a, b) -> Integer.compare(a[2], b[2])); 
        int ii = 0; 
        for (var aug : augs) {
            int p = aug[0], q = aug[1], w = aug[2], i = aug[3]; 
            for (; ii < edgeList.length && edgeList[ii][2] < w; ++ii) {
                int uu = find(edgeList[ii][0], parent), vv = find(edgeList[ii][1], parent); 
                if (uu != vv) parent[uu] = vv; 
            }
            if (find(p, parent) == find(q, parent)) ans[i] = true; 
        }
        return ans; 
    }


    /*1714. Sum Of Special Evenly-Spaced Elements In Array (Hard)
    You are given a 0-indexed integer array nums consisting of n non-negative 
    integers. You are also given an array queries, where queries[i] = [xi, yi]. 
    The answer to the ith query is the sum of all nums[j] where xi <= j < n 
    and (j - xi) is divisible by yi. Return an array answer where 
    answer.length == queries.length and answer[i] is the answer to the ith 
    query modulo 10^9 + 7.

    Example 1:
    Input: nums = [0,1,2,3,4,5,6,7], queries = [[0,3],[5,1],[4,2]]
    Output: [9,18,10]
    Explanation: The answers of the queries are as follows:
                 1) The j indices that satisfy this query are 0, 3, and 6. 
                    nums[0] + nums[3] + nums[6] = 9
                 2) The j indices that satisfy this query are 5, 6, and 7. 
                    nums[5] + nums[6] + nums[7] = 18
                 3) The j indices that satisfy this query are 4 and 6. 
                    nums[4] + nums[6] = 10
    
    Example 2:
    Input: nums = [100,200,101,201,102,202,103,203], queries = [[0,7]]
    Output: [303]

    Constraints:
    * n == nums.length
    * 1 <= n <= 5 * 10^4
    * 0 <= nums[i] <= 10^9
    * 1 <= queries.length <= 1.5 * 10^5
    * 0 <= xi < n
    * 1 <= yi <= 5 * 10^4*/

    public int[] solve(int[] nums, int[][] queries) {
        final int mod = 1_000_000_007; 
        int n = nums.length, r = (int) Math.sqrt(n); 
        long[][] dp = new long[n][r]; 
        for (int i = n-1; i >= 0; --i) 
            for (int j = 0; j < r; ++j) {
                dp[i][j] = nums[i]; 
                if (i+j < n) dp[i][j] = (dp[i][j] + dp[i+j][j]) % mod; 
            }
        int[] ans = new int[queries.length]; 
        for (int i = 0; i < queries.length; ++i) {
            int x = queries[i][0], y = queries[i][1]; 
            if (y < r) ans[i] = (int) dp[x][y]; 
            else {
                long val = 0; 
                for (int j = x; j < n; j += y) val = (val + nums[j]) % mod; 
                ans[i] = (int) val; 
            }
        }
        return ans; 
    }


    /*1842. Next Palindrome Using Same Digits (Hard)
    You are given a numeric string num, representing a very large palindrome. 
    Return the smallest palindrome larger than num that can be created by 
    rearranging its digits. If no such palindrome exists, return an empty 
    string "". A palindrome is a number that reads the same backward as 
    forward.

    Example 1:
    Input: num = "1221"
    Output: "2112"
    Explanation: The next palindrome larger than "1221" is "2112".

    Example 2:
    Input: num = "32123"
    Output: ""
    Explanation: No palindromes larger than "32123" can be made by rearranging the digits.

    Example 3:
    Input: num = "45544554"
    Output: "54455445"
    Explanation: The next palindrome larger than "45544554" is "54455445".

    Constraints:
    * 1 <= num.length <= 10^5
    * num is a palindrome.*/

    public String nextPalindrome(String num) {
        char[] chars = num.toCharArray(); 
        for (int n = chars.length, i = n/2-2; i >= 0; --i) 
            if (chars[i] < chars[i+1]) {
                int ii = i+1; 
                for (int j = i+1; j < n/2; ++j)
                    if (chars[i] < chars[j]) ii = j; 
                char temp = chars[i]; 
                chars[i] = chars[ii]; 
                chars[ii] = temp; 
                for (int j = i+1; j < (i+1+n/2)/2; ++j) {
                    temp = chars[j]; 
                    chars[j] = chars[n/2+i-j]; 
                    chars[n/2+i-j] = temp; 
                }
                for (int j = (n+1)/2; j < n; ++j) chars[j] = chars[n-1-j]; 
                return String.valueOf(chars); 
            }
        return ""; 
    }


    /*1956. Minimum Time For K Virus Variants to Spread (Hard)
    There are n unique virus variants in an infinite 2D grid. You are given a 
    2D array points, where points[i] = [xi, yi] represents a virus originating 
    at (xi, yi) on day 0. Note that it is possible for multiple virus variants 
    to originate at the same point. Every day, each cell infected with a virus 
    variant will spread the virus to all neighboring points in the four 
    cardinal directions (i.e. up, down, left, and right). If a cell has 
    multiple variants, all the variants will spread without interfering with 
    each other. Given an integer k, return the minimum integer number of days 
    for any point to contain at least k of the unique virus variants.

    Example 1:
    Input: points = [[1,1],[6,1]], k = 2
    Output: 3
    Explanation: On day 3, points (3,1) and (4,1) will contain both virus 
                 variants. Note that these are not the only points that will 
                 contain both virus variants.
    
    Example 2:
    Input: points = [[3,3],[1,2],[9,2]], k = 2
    Output: 2
    Explanation: On day 2, points (1,3), (2,3), (2,2), and (3,2) will contain 
                 the first two viruses. Note that these are not the only points 
                 that will contain both virus variants.
    
    Example 3:
    Input: points = [[3,3],[1,2],[9,2]], k = 3
    Output: 4
    Explanation: On day 4, the point (5,2) will contain all 3 viruses. Note 
                 that this is not the only point that will contain all 3 virus 
                 variants.

    Constraints:
    * n == points.length
    * 2 <= n <= 50
    * points[i].length == 2
    * 1 <= xi, yi <= 100
    * 2 <= k <= n*/

    private boolean fn(int day, int[][] points, int k) {
        Map<Integer, Map<Integer, Integer>> lines = new HashMap(); 
        for (var p : points) {
            int x = p[0], y = p[1]; 
            lines.putIfAbsent(x+y-day, new HashMap()); 
            lines.putIfAbsent(x+y+day, new HashMap()); 
            lines.putIfAbsent(x+y+day+1, new HashMap()); 
            lines.get(x+y-day).merge(y-x+day, 0, Integer::sum); 
            lines.get(x+y+day).merge(y-x-day, 0, Integer::sum); 
            lines.get(x+y+day).merge(y-x+day, 0, Integer::sum); 
            lines.get(x+y-day).merge(y-x-day, 1, Integer::sum); 
            lines.get(x+y-day).merge(y-x+day+1, -1, Integer::sum); 
            lines.get(x+y+day+1).merge(y-x-day, -1, Integer::sum); 
            lines.get(x+y+day+1).merge(y-x+day+1, 1, Integer::sum); 
        }
        List<Integer> rows = new ArrayList(); 
        for (var xx : lines.keySet()) rows.add(xx); 
        Collections.sort(rows); 
        Map<Integer, Integer> line = new HashMap(); 
        for (var xx : rows) {
            for (var elem : lines.get(xx).entrySet()) {
                int yy = elem.getKey(), vv = elem.getValue(); 
                line.merge(yy, vv, Integer::sum); 
            }
            int prefix = 0; 
            List<Integer> keys = new ArrayList(); 
            for (var yy : line.keySet()) keys.add(yy); 
            Collections.sort(keys); 
            for (var yy : keys) {
                prefix += line.get(yy); 
                if (prefix >= k && ((xx-yy)%2 == 0 || !lines.containsKey(xx+1) || prefix+line.getOrDefault(yy+1, 0) >= k)) return true; 
            }
        }
        return false; 
    }
    
    public int minDayskVariants(int[][] points, int k) {
        int lo = 0, hi = 1_000_000_000; 
        while (lo < hi) {
            int mid = lo + (hi - lo)/2; 
            if (fn(mid, points, k)) hi = mid; 
            else lo = mid + 1; 
        }
        return lo; 
    }


    /*1962. Remove Stones to Minimize the Total (Medium)
    You are given a 0-indexed integer array piles, where piles[i] represents 
    the number of stones in the ith pile, and an integer k. You should apply 
    the following operation exactly k times:
    * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
    Notice that you can apply the operation on the same pile more than once.
    Return the minimum possible total number of stones remaining after applying 
    the k operations. floor(x) is the greatest integer that is smaller than or 
    equal to x (i.e., rounds x down).

    Example 1:
    Input: piles = [5,4,9], k = 2
    Output: 12
    Explanation: Steps of a possible scenario are:
                 - Apply the operation on pile 2. The resulting piles are 
                   [5,4,5].
                 - Apply the operation on pile 0. The resulting piles are 
                   [3,4,5].
                 The total number of stones in [3,4,5] is 12.
    
    Example 2:
    Input: piles = [4,3,6,7], k = 3
    Output: 12
    Explanation: Steps of a possible scenario are:
                 - Apply the operation on pile 2. The resulting piles are 
                   [4,3,3,7].
                 - Apply the operation on pile 3. The resulting piles are 
                   [4,3,3,4].
                 - Apply the operation on pile 0. The resulting piles are 
                   [2,3,3,4].
                 The total number of stones in [2,3,3,4] is 12.

    Constraints:
    * 1 <= piles.length <= 10^5
    * 1 <= piles[i] <= 10^4
    * 1 <= k <= 10^5*/

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder()); 
        for (var x : piles) pq.add(x); 
        while (k-- > 0) {
            var x = pq.poll(); 
            x -= x/2; 
            pq.add(x); 
        }
        int ans = 0; 
        while (!pq.isEmpty()) ans += pq.poll(); 
        return ans; 
    }


    /*2005. Subtree Removal Game with Fibonacci Tree (Hard)
    A Fibonacci tree is a binary tree created using the order function order(n):
    * order(0) is the empty tree.
    * order(1) is a binary tree with only one node.
    * order(n) is a binary tree that consists of a root node with the left 
      subtree as order(n - 2) and the right subtree as order(n - 1).
    Alice and Bob are playing a game with a Fibonacci tree with Alice staring 
    first. On each turn, a player selects a node and removes that node and its 
    subtree. The player that is forced to delete root loses. Given the integer 
    n, return true if Alice wins the game or false if Bob wins, assuming both 
    players play optimally. A subtree of a binary tree tree is a tree that 
    consists of a node in tree and all of this node's descendants. The tree 
    tree could also be considered as a subtree of itself.

    Example 1:
    Input: n = 3
    Output: true
    Explanation: Alice takes the node 1 in the right subtree. Bob takes either 
                 the 1 in the left subtree or the 2 in the right subtree. Alice 
                 takes whichever node Bob doesn't take. Bob is forced to take 
                 the root node 3, so Bob will lose. Return true because Alice 
                 wins.
    
    Example 2:
    Input: n = 1
    Output: false
    Explanation: Alice is forced to take the root node 1, so Alice will lose. 
                 Return false because Alice loses.
    
    Example 3:
    Input: n = 2
    Output: true
    Explanation: Alice takes the node 1. Bob is forced to take the root node 2, 
                 so Bob will lose. Return true because Alice wins.

    Constraints: 1 <= n <= 100*/

    public boolean findGameWinner(int n) {
        return (n-1) % 6 > 0; 
    }


    /*2046. Sort Linked List Already Sorted Using Absolute Values (Medium)
    Given the head of a singly linked list that is sorted in non-decreasing 
    order using the absolute values of its nodes, return the list sorted in 
    non-decreasing order using the actual values of its nodes.

    Example 1:
    Input: head = [0,2,-5,5,10,-10]
    Output: [-10,-5,0,2,5,10]
    Explanation: The list sorted in non-descending order using the absolute 
                 values of the nodes is [0,2,-5,5,10,-10]. The list sorted in 
                 non-descending order using the actual values is 
                 [-10,-5,0,2,5,10].
    
    Example 2:
    Input: head = [0,1,2]
    Output: [0,1,2]
    Explanation: The linked list is already sorted in non-decreasing order.
    
    Example 3:
    Input: head = [1]
    Output: [1]
    Explanation: The linked list is already sorted in non-decreasing order.

    Constraints:
    * The number of nodes in the list is the range [1, 10^5].
    * -5000 <= Node.val <= 5000
    * head is sorted in non-decreasing order using the absolute value of its 
      nodes.

    Follow up: Can you think of a solution with O(n) time complexity?*/

    public ListNode sortLinkedList(ListNode head) {
        ListNode prev = head, node = head.next; 
        while (node != null) 
            if (node.val < 0) {
                prev.next = node.next; 
                node.next = head; 
                head = node; 
                node = prev.next; 
            } else {
                prev = node; 
                node = node.next; 
            }
        return head; 
    }


    /*2052. Minimum Cost to Separate Sentence Into Rows (Medium)
    You are given a string sentence containing words separated by spaces, and 
    an integer k. Your task is to separate sentence into rows where the number 
    of characters in each row is at most k. You may assume that sentence does 
    not begin or end with a space, and the words in sentence are separated by a 
    single space. You can split sentence into rows by inserting line breaks 
    between words in sentence. A word cannot be split between two rows. Each 
    word must be used exactly once, and the word order cannot be rearranged. 
    Adjacent words in a row should be separated by a single space, and rows 
    should not begin or end with spaces. The cost of a row with length n is 
    (k - n)2, and the total cost is the sum of the costs for all rows except 
    the last one.
    * For example if sentence = "i love leetcode" and k = 12:
      + Separating sentence into "i", "love", and "leetcode" has a cost of 
        (12 - 1)2 + (12 - 4)2 = 185.
      + Separating sentence into "i love", and "leetcode" has a cost of 
        (12 - 6)2 = 36.
      + Separating sentence into "i", and "love leetcode" is not possible 
        because the length of "love leetcode" is greater than k.
    Return the minimum possible total cost of separating sentence into rows.

    Example 1:
    Input: sentence = "i love leetcode", k = 12
    Output: 36
    Explanation: Separating sentence into "i", "love", and "leetcode" has a 
                 cost of (12 - 1)^2 + (12 - 4)^2 = 185. Separating sentence 
                 into "i love", and "leetcode" has a cost of (12 - 6)^2 = 36. 
                 Separating sentence into "i", "love leetcode" is not possible 
                 because "love leetcode" has length 13. 36 is the minimum 
                 possible total cost so return it.
    
    Example 2:
    Input: sentence = "apples and bananas taste great", k = 7
    Output: 21
    Explanation: Separating sentence into "apples", "and", "bananas", "taste", 
                 and "great" has a cost of 
                 (7 - 6)^2 + (7 - 3)^2 + (7 - 7)^2 + (7 - 5)^2 = 21. 21 is the 
                 minimum possible total cost so return it.
    
    Example 3:
    Input: sentence = "a", k = 5
    Output: 0
    Explanation: The cost of the last row is not included in the total cost, 
                 and since there is only one row, return 0.

    Constraints:
    * 1 <= sentence.length <= 5000
    * 1 <= k <= 5000
    * The length of each word in sentence is at most k.
    * sentence consists of only lowercase English letters and spaces.
    * sentence does not begin or end with a space.
    * Words in sentence are separated by a single space.*/

    public int minimumCost(String sentence, int k) {
        if (sentence.length() <= k) return 0; 
        String[] words = sentence.split(" ");
        int n = words.length; 
        int[] dp = new int[n]; 
        Arrays.fill(dp, Integer.MAX_VALUE); 
        dp[n-1] = 0; 
        for (int i = n-2; i >= 0; --i) {
            int prefix = -1; 
            for (int j = i; j < n; ++j) {
                prefix += 1 + words[j].length(); 
                if (prefix <= k) dp[i] = j == n-1 ? 0 : Math.min(dp[i], (prefix-k)*(prefix-k) + dp[j+1]); 
                else break; 
            }
        }
        return dp[0]; 
    }


    /*2061. Number of Spaces Cleaning Robot Cleaned (Medium)
    A room is represented by a 0-indexed 2D binary matrix room where a 0 
    represents an empty space and a 1 represents a space with an object. The 
    top left corner of the room will be empty in all test cases. A cleaning 
    robot starts at the top left corner of the room and is facing right. The 
    robot will continue heading straight until it reaches the edge of the room 
    or it hits an object, after which it will turn 90 degrees clockwise and 
    repeat this process. The starting space and all spaces that the robot 
    visits are cleaned by it. Return the number of clean spaces in the room if 
    the robot runs indefinetely.

    Example 1:
    Input: room = [[0,0,0],[1,1,0],[0,0,0]]
    Output: 7
    Explanation: The robot cleans the spaces at (0, 0), (0, 1), and (0, 2). The 
                 robot is at the edge of the room, so it turns 90 degrees 
                 clockwise and now faces down. The robot cleans the spaces at 
                 (1, 2), and (2, 2). The robot is at the edge of the room, so 
                 it turns 90 degrees clockwise and now faces left. The robot 
                 cleans the spaces at (2, 1), and (2, 0). The robot has cleaned 
                 all 7 empty spaces, so return 7.
    
    Example 2:
    Input: room = [[0,1,0],[1,0,0],[0,0,0]]
    Output: 1
    Explanation: The robot cleans the space at (0, 0). The robot hits an object, 
                 so it turns 90 degrees clockwise and now faces down. The robot 
                 hits an object, so it turns 90 degrees clockwise and now faces 
                 left. The robot is at the edge of the room, so it turns 90 
                 degrees clockwise and now faces up. The robot is at the edge 
                 of the room, so it turns 90 degrees clockwise and now faces 
                 right. The robot is back at its starting position. The robot 
                 has cleaned 1 space, so return 1.

    Constraints:
    * m == room.length
    * n == room[r].length
    * 1 <= m, n <= 300
    * room[r][c] is either 0 or 1.
    * room[0][0] == 0*/

    public int numberOfCleanRooms(int[][] room) {
        int m = room.length, n = room[0].length, ans = 0; 
        int[] dir = new int[]{0, 1, 0, -1, 0}; 
        int[][] seen = new int[m][n]; 
        for (int i = 0, j = 0, k = 0; (seen[i][j] & 1<<k) == 0; ) {
            if (room[i][j] == 0) {
                ++ans; 
                room[i][j] = -1; 
            }
            seen[i][j] |= 1<<k; 
            int ii = i + dir[k], jj = j + dir[k+1]; 
            if (0 <= ii && ii < m && 0 <= jj && jj < n && room[ii][jj] != 1) { i = ii; j = jj; }
            else k = (k+1) % 4; 
        }
        return ans; 
    }


    /*2067. Number of Equal Count Substrings (Medium)
    You are given a 0-indexed string s consisting of only lowercase English 
    letters, and an integer count. A substring of s is said to be an equal 
    count substring if, for each unique letter in the substring, it appears 
    exactly count times in the substring. Return the number of equal count 
    substrings in s. A substring is a contiguous non-empty sequence of 
    characters within a string.

    Example 1:
    Input: s = "aaabcbbcc", count = 3
    Output: 3
    Explanation: The substring that starts at index 0 and ends at index 2 is 
                 "aaa". The letter 'a' in the substring ap`pears exactly 3 
                 times. The substring that starts at index 3 and ends at index 
                 8 is "bcbbcc". The letters 'b' and 'c' in the substring appear 
                 exactly 3 times. The substring that starts at index 0 and ends 
                 at index 8 is "aaabcbbcc". The letters 'a', 'b', and 'c' in 
                 the substring appear exactly 3 times.
    
    Example 2:
    Input: s = "abcd", count = 2
    Output: 0
    Explanation: The number of times each letter appears in s is less than 
                 count. Therefore, no substrings in s are equal count 
                 substrings, so return 0.
    
    Example 3:
    Input: s = "a", count = 5
    Output: 0
    Explanation: The number of times each letter appears in s is less than 
                 count. Therefore, no substrings in s are equal count 
                 substrings, so return 0

    Constraints:
    * 1 <= s.length <= 3 * 10^4
    * 1 <= count <= 3 * 10^4
    * s consists only of lowercase English letters.*/

    public int equalCountSubstrings(String s, int count) {
        int ans = 0; 
        for (int k = 1; k <= 26; ++k) {
            int[] freq = new int[26]; 
            int uniq = 0; 
            for (int i = 0; i < s.length(); ++i) {
                if(++freq[s.charAt(i)-'a'] == count) ++uniq; 
                if (i >= k*count && freq[s.charAt(i-k*count)-'a']-- == count) --uniq; 
                if (uniq == k) ++ans; 
            }
        }
        return ans; 
    }


    /*2077. Paths in Maze That Lead to Same Room (Medium)
    A maze consists of n rooms numbered from 1 to n, and some rooms are 
    connected by corridors. You are given a 2D integer array corridors where 
    corridors[i] = [room1i, room2i] indicates that there is a corridor 
    connecting room1i and room2i, allowing a person in the maze to go from 
    room1i to room2i and vice versa. The designer of the maze wants to know 
    how confusing the maze is. The confusion score of the maze is the number 
    of different cycles of length 3.
    * For example, 1 → 2 → 3 → 1 is a cycle of length 3, but 1 → 2 → 3 → 4 and 
      1 → 2 → 3 → 2 → 1 are not.
    Two cycles are considered to be different if one or more of the rooms 
    visited in the first cycle is not in the second cycle. Return the confusion 
    score of the maze.

    Example 1:
    Input: n = 5, corridors = [[1,2],[5,2],[4,1],[2,4],[3,1],[3,4]]
    Output: 2
    Explanation: One cycle of length 3 is 4 → 1 → 3 → 4, denoted in red. Note 
                 that this is the same cycle as 3 → 4 → 1 → 3 or 1 → 3 → 4 → 1 
                 because the rooms are the same. Another cycle of length 3 is 
                 1 → 2 → 4 → 1, denoted in blue. Thus, there are two different 
                 cycles of length 3.
    
    Example 2:
    Input: n = 4, corridors = [[1,2],[3,4]]
    Output: 0
    Explanation: There are no cycles of length 3.

    Constraints:
    * 2 <= n <= 1000
    * 1 <= corridors.length <= 5 * 10^4
    * corridors[i].length == 2
    * 1 <= room1i, room2i <= n
    * room1i != room2i
    * There are no duplicate corridors.*/

    public int numberOfPaths(int n, int[][] corridors) {
        HashSet<Integer>[] graph = new HashSet[n]; 
        for (int i = 0; i < n; ++i) graph[i] = new HashSet(); 
        for (int[] c : corridors) {
            graph[c[0]-1].add(c[1]-1); 
            graph[c[1]-1].add(c[0]-1); 
        }
        int ans = 0; 
        for (int[] c : corridors) 
            for (int u : graph[c[0]-1]) 
                if (graph[c[1]-1].contains(u)) ++ans; 
        return ans/3; 
    }


    /*2083. Substrings That Begin and End With the Same Letter (Medium)
    You are given a 0-indexed string s consisting of only lowercase English 
    letters. Return the number of substrings in s that begin and end with the 
    same character. A substring is a contiguous non-empty sequence of 
    characters within a string.

    Example 1:
    Input: s = "abcba"
    Output: 7
    Explanation: The substrings of length 1 that start and end with the same 
                 letter are: "a", "b", "c", "b", and "a". The substring of 
                 length 3 that starts and ends with the same letter is: "bcb".
                 The substring of length 5 that starts and ends with the same 
                 letter is: "abcba".
    
    Example 2:
    Input: s = "abacad"
    Output: 9
    Explanation: The substrings of length 1 that start and end with the same 
                 letter are: "a", "b", "a", "c", "a", and "d". The substrings 
                 of length 3 that start and end with the same letter are: "aba" 
                 and "aca". The substring of length 5 that starts and ends with 
                 the same letter is: "abaca".
    
    Example 3:
    Input: s = "a"
    Output: 1
    Explanation: The substring of length 1 that starts and ends with the same 
                 letter is: "a".

    Constraints:
    * 1 <= s.length <= 10^5
    * s consists only of lowercase English letters.*/

    public long numberOfSubstrings(String s) {
        long ans = 0; 
        int[] freq = new int[26]; 
        for (char ch : s.toCharArray()) 
            ans += ++freq[ch - 'a']; 
        return ans; 
    }


    /*2098. Subsequence of Size K With the Largest Even Sum (Medium)
    You are given an integer array nums and an integer k. Find the largest even 
    sum of any subsequence of nums that has a length of k. Return this sum, or 
    -1 if such a sum does not exist. A subsequence is an array that can be 
    derived from another array by deleting some or no elements without changing 
    the order of the remaining elements.

    Example 1:
    Input: nums = [4,1,5,3,1], k = 3
    Output: 12
    Explanation: The subsequence with the largest possible even sum is [4,5,3]. 
                 It has a sum of 4 + 5 + 3 = 12.
    
    Example 2:
    Input: nums = [4,6,2], k = 3
    Output: 12
    Explanation: The subsequence with the largest possible even sum is [4,6,2]. 
                 It has a sum of 4 + 6 + 2 = 12.
    
    Example 3:
    Input: nums = [1,3,5], k = 1
    Output: -1
    Explanation: No subsequence of nums with length 1 has an even sum.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^5
    * 1 <= k <= nums.length*/

    public long largestEvenSum(int[] nums, int k) {
        long ans = -1, prefix = 0; 
        int[] least = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}; 
        Arrays.sort(nums); 
        for (int i = 0, n = nums.length; i < n/2; ++i) { 
            nums[i] ^= nums[n-1-i]; 
            nums[n-1-i] ^= nums[i]; 
            nums[i] ^= nums[n-1-i]; 
        }
        for (int i = 0; i < nums.length; ++i) 
            if (i < k) {
                prefix += nums[i]; 
                least[nums[i]&1] = Math.min(least[nums[i]&1], nums[i]); 
                if (i == k-1 && (prefix&1) == 0) return prefix; 
            } else ans = Math.max(ans, prefix - least[1-(nums[i]&1)] + nums[i]); 
        return ans; 
    }


    /*2107. Number of Unique Flavors After Sharing K Candies (Medium)
    You are given a 0-indexed integer array candies, where candies[i] 
    represents the flavor of the ith candy. Your mom wants you to share these 
    candies with your little sister by giving her k consecutive candies, but 
    you want to keep as many flavors of candies as possible. Return the maximum 
    number of unique flavors of candy you can keep after sharing with your 
    sister.

    Example 1:
    Input: candies = [1,2,2,3,4,3], k = 3
    Output: 3
    Explanation: Give the candies in the range [1, 3] (inclusive) with flavors 
                 [2,2,3]. You can eat candies with flavors [1,4,3]. There are 3 
                 unique flavors, so return 3.
    
    Example 2:
    Input: candies = [2,2,2,2,3,3], k = 2
    Output: 2
    Explanation: Give the candies in the range [3, 4] (inclusive) with flavors 
                 [2,3]. You can eat candies with flavors [2,2,2,3]. There are 2 
                 unique flavors, so return 2. Note that you can also share the 
                 candies with flavors [2,2] and eat the candies with flavors 
                 [2,2,3,3].
    
    Example 3:
    Input: candies = [2,4,5], k = 0
    Output: 3
    Explanation: You do not have to give any candies. You can eat the candies 
                 with flavors [2,4,5]. There are 3 unique flavors, so return 3.

    Constraints:
    * 1 <= candies.length <= 10^5
    * 1 <= candies[i] <= 10^5
    * 0 <= k <= candies.length*/

    public int shareCandies(int[] candies, int k) {
        int ans = 0; 
        HashMap<Integer, Integer> freq = new HashMap(); 
        for (int x : candies) freq.merge(x, 1, Integer::sum); 
        for (int i = 0; i < candies.length; ++i) {
            freq.merge(candies[i], -1, Integer::sum); 
            if (freq.get(candies[i]) == 0) freq.remove(candies[i]); 
            if (i >= k) freq.merge(candies[i-k], 1, Integer::sum); 
            if (i >= k-1) ans = Math.max(ans, freq.size()); 
        }
        return ans; 
    }


    /*2113. Elements in Array After Removing and Replacing Elements (Medium)
    You are given a 0-indexed integer array nums. Initially on minute 0, the 
    array is unchanged. Every minute, the leftmost element in nums is removed 
    until no elements remain. Then, every minute, one element is appended to 
    the end of nums, in the order they were removed in, until the original 
    array is restored. This process repeats indefinitely.
    * For example, the array [0,1,2] would change as follows: [0,1,2] → [1,2] 
      → [2] → [] → [0] → [0,1] → [0,1,2] → [1,2] → [2] → [] → [0] → [0,1] 
      → [0,1,2] → ...
    You are also given a 2D integer array queries of size n where 
    queries[j] = [timej, indexj]. The answer to the jth query is:
    * nums[indexj] if indexj < nums.length at minute timej
    * -1 if indexj >= nums.length at minute timej
    Return an integer array ans of size n where ans[j] is the answer to the jth 
    query.

    Example 1:
    Input: nums = [0,1,2], queries = [[0,2],[2,0],[3,2],[5,0]]
    Output: [2,2,-1,0]
    Explanation: Minute 0: [0,1,2] - All elements are in the nums.
                 Minute 1: [1,2]   - The leftmost element, 0, is removed.
                 Minute 2: [2]     - The leftmost element, 1, is removed.
                 Minute 3: []      - The leftmost element, 2, is removed.
                 Minute 4: [0]     - 0 is added to the end of nums.
                 Minute 5: [0,1]   - 1 is added to the end of nums.
                 At minute 0, nums[2] is 2.
                 At minute 2, nums[0] is 2.
                 At minute 3, nums[2] does not exist.
                 At minute 5, nums[0] is 0.

    Example 2:
    Input: nums = [2], queries = [[0,0],[1,0],[2,0],[3,0]]
    Output: [2,-1,2,-1]
    Explanation: Minute 0: [2] - All elements are in the nums.
                 Minute 1: []  - The leftmost element, 2, is removed.
                 Minute 2: [2] - 2 is added to the end of nums.
                 Minute 3: []  - The leftmost element, 2, is removed.
                 At minute 0, nums[0] is 2.
                 At minute 1, nums[0] does not exist.
                 At minute 2, nums[0] is 2.
                 At minute 3, nums[0] does not exist.

    Constraints:
    * 1 <= nums.length <= 100
    * 0 <= nums[i] <= 100
    * n == queries.length
    * 1 <= n <= 10^5
    * queries[j].length == 2
    * 0 <= timej <= 10^5
    * 0 <= indexj < nums.length*/

    public int[] elementInNums(int[] nums, int[][] queries) {
        int[] ans = new int[queries.length]; 
        for (int i = 0; i < queries.length; ++i) {
            int t = queries[i][0], k = queries[i][1]; 
            t %= 2*nums.length; 
            if (t < nums.length-k) ans[i] = nums[k+t]; 
            else if (t <= nums.length+k) ans[i] = -1; 
            else ans[i] = nums[k]; 
        }
        return ans; 
    }


    /*2123. Minimum Operations to Remove Adjacent Ones in Matrix (Hard)
    You are given a 0-indexed binary matrix grid. In one operation, you can 
    flip any 1 in grid to be 0. A binary matrix is well-isolated if there is no 
    1 in the matrix that is 4-directionally connected (i.e., horizontal and 
    vertical) to another 1. Return the minimum number of operations to make 
    grid well-isolated.

    Example 1:
    Input: grid = [[1,1,0],[0,1,1],[1,1,1]]
    Output: 3
    Explanation: Use 3 operations to change grid[0][1], grid[1][2], and 
                 grid[2][1] to 0. After, no more 1's are 4-directionally 
                 connected and grid is well-isolated.
    
    Example 2:
    Input: grid = [[0,0,0],[0,0,0],[0,0,0]]
    Output: 0
    Explanation: There are no 1's in grid and it is well-isolated. No 
                 operations were done so return 0.
    
    Example 3:
    Input: grid = [[0,1],[1,0]]
    Output: 0
    Explanation: None of the 1's are 4-directionally connected and grid is 
                 well-isolated. No operations were done so return 0.

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 1 <= m, n <= 300
    * grid[i][j] is either 0 or 1.*/

    private boolean dfs(int i, int j, int x, int[][] grid, int[][] match, int[][] seen) {
        /* Return true if an augmenting path is found via Hungarian algo. */
        int m = grid.length, n = grid[0].length; 
        int[] dir = new int[]{-1, 0, 1, 0, -1}; 
        for (int k = 0; k < 4; ++k) {
            int ii = i + dir[k], jj = j + dir[k+1]; 
            if (0 <= ii && ii < m && 0 <= jj && jj < n && grid[ii][jj] == 1 && seen[ii][jj] != x) {
                seen[ii][jj] = x; 
                if (match[ii][jj] == -1 || dfs(match[ii][jj]/n, match[ii][jj]%n, x, grid, match, seen)) {
                    match[ii][jj] = i*n + j; 
                    match[i][j] = ii*n + jj; 
                    return true; 
                }
            }
        }
        return false; 
    }
    
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0; 
        int[][] match = new int[m][n], seen = new int[m][n]; 
        for (int i = 0; i < m; ++i) {
            Arrays.fill(match[i], -1); 
            Arrays.fill(seen[i], -1); 
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1 && (i+j)%2 == 1 && match[i][j] == -1 && dfs(i, j, seen[i][j] = i*n+j, grid, match, seen)) 
                    ++ans; 
        }
        return ans; 
    }


    /*2143. Choose Numbers From Two Arrays in Range (Hard)
    You are given two 0-indexed integer arrays nums1 and nums2 of length n. A 
    range [l, r] (inclusive) where 0 <= l <= r < n is balanced if:
    * For every i in the range [l, r], you pick either nums1[i] or nums2[i].
    * The sum of the numbers you pick from nums1 equals to the sum of the 
      numbers you pick from nums2 (the sum is considered to be 0 if you pick no 
      numbers from an array).
    Two balanced ranges from [l1, r1] and [l2, r2] are considered to be 
    different if at least one of the following is true:
    * l1 != l2
    * r1 != r2
    * nums1[i] is picked in the first range, and nums2[i] is picked in the 
      second range or vice versa for at least one i.
    Return the number of different ranges that are balanced. Since the answer 
    may be very large, return it modulo 10^9 + 7.

    Example 1:
    Input: nums1 = [1,2,5], nums2 = [2,6,3]
    Output: 3
    Explanation: The balanced ranges are:
                 - [0, 1] where we choose nums2[0], and nums1[1].
                   The sum of the numbers chosen from nums1 equals the sum of 
                   the numbers chosen from nums2: 2 = 2.
                 - [0, 2] where we choose nums1[0], nums2[1], and nums1[2].
                   The sum of the numbers chosen from nums1 equals the sum of 
                   the numbers chosen from nums2: 1 + 5 = 6.
                 - [0, 2] where we choose nums1[0], nums1[1], and nums2[2].
                   The sum of the numbers chosen from nums1 equals the sum of 
                   the numbers chosen from nums2: 1 + 2 = 3.
                 Note that the second and third balanced ranges are different.
                 In the second balanced range, we choose nums2[1] and in the 
                 third balanced range, we choose nums1[1].
    
    Example 2:
    Input: nums1 = [0,1], nums2 = [1,0]
    Output: 4
    Explanation: The balanced ranges are:
                 - [0, 0] where we choose nums1[0].
                   The sum of the numbers chosen from nums1 equals the sum of 
                   the numbers chosen from nums2: 0 = 0.
                 - [1, 1] where we choose nums2[1].
                   The sum of the numbers chosen from nums1 equals the sum of 
                   the numbers chosen from nums2: 0 = 0.
                 - [0, 1] where we choose nums1[0] and nums2[1].
                   The sum of the numbers chosen from nums1 equals the sum of 
                   the numbers chosen from nums2: 0 = 0.
                 - [0, 1] where we choose nums2[0] and nums1[1].
                   The sum of the numbers chosen from nums1 equals the sum of 
                   the numbers chosen from nums2: 1 = 1.

    Constraints:
    * n == nums1.length == nums2.length
    * 1 <= n <= 100
    * 0 <= nums1[i], nums2[i] <= 100*/

    public int countSubranges(int[] nums1, int[] nums2) {
        final int mod = 1_000_000_007; 
        long ans = 0; 
        Map<Integer, Long> freq = new HashMap(); 
        for (int i = 0; i < nums1.length; ++i) {
            Map<Integer, Long> ff = new HashMap(); 
            ff.merge(nums1[i], 1l, Long::sum); 
            ff.merge(-nums2[i], 1l, Long::sum); 
            for (var elem : freq.entrySet()) {
                int k = elem.getKey(); 
                long v = elem.getValue(); 
                ff.put(k+nums1[i], (ff.getOrDefault(k+nums1[i], 0l) + v) % mod);
                ff.put(k-nums2[i], (ff.getOrDefault(k-nums2[i], 0l) + v) % mod); 
            }
            freq = ff; 
            ans = (ans + freq.getOrDefault(0, 0l)) % mod; 
        }
        return (int) ans; 
    }


    /*2152. Minimum Number of Lines to Cover Points (Medium)
    You are given an array points where points[i] = [xi, yi] represents a point 
    on an X-Y plane. Straight lines are going to be added to the X-Y plane, 
    such that every point is covered by at least one line. Return the minimum 
    number of straight lines needed to cover all the points.

    Example 1:
    Input: points = [[0,1],[2,3],[4,5],[4,3]]
    Output: 2
    Explanation: The minimum number of straight lines needed is two. One 
                 possible solution is to add:
                 - One line connecting the point at (0, 1) to the point at 
                   (4, 5).
                 - Another line connecting the point at (2, 3) to the point at 
                   (4, 3).
    
    Example 2:
    Input: points = [[0,2],[-2,-2],[1,4]]
    Output: 1
    Explanation: The minimum number of straight lines needed is one. The only 
                 solution is to add:
                 - One line connecting the point at (-2, -2) to the point at 
                   (1, 4).

    Constraints:
    * 1 <= points.length <= 10
    * points[i].length == 2
    * -100 <= xi, yi <= 100
    * All the points are unique.*/

    public int minimumLines(int[][] points) {
        int n = points.length; 
        int[][] mask = new int[n][n]; 
        for (int i = 0; i < n; ++i) 
            for (int j = i+1; j < n; ++j) {
                mask[i][j] ^= (1<<i) ^ (1<<j); 
                for (int k = j+1; k < n; ++k) 
                    if ((points[i][0]-points[j][0])*(points[i][1]-points[k][1]) == (points[i][1]-points[j][1])*(points[i][0]-points[k][0])) mask[i][j] ^= 1<<k; 
            }
        int[] dp = new int[1<<n]; 
        Arrays.fill(dp, n/2+1); 
        dp[0] = 0; 
        for (int m = 1; m < (1<<n); ++m) {
            int bits = 0; 
            for (int mm = m; mm > 0; ++bits, mm &= mm-1); 
            if (bits <= 2) dp[m] = 1; 
            else {
                int i = 0; 
                for (; i < n; ++i) 
                    if ((m & (1<<i)) > 0) break; 
                for (int j = i+1; j < n; ++j) 
                    if ((m & (1<<j)) > 0) dp[m] = Math.min(dp[m], 1+dp[m^mask[i][j]]); 
            }
        }
        return dp[(1<<n)-1]; 
    }


    /*2158. Amount of New Area Painted Each Day (Hard)
    There is a long and thin painting that can be represented by a number line. 
    You are given a 0-indexed 2D integer array paint of length n, where 
    paint[i] = [starti, endi]. This means that on the ith day you need to paint 
    the area between starti and endi. Painting the same area multiple times 
    will create an uneven painting so you only want to paint each area of the 
    painting at most once. Return an integer array worklog of length n, where 
    worklog[i] is the amount of new area that you painted on the ith day.

    Example 1:
    Input: paint = [[1,4],[4,7],[5,8]]
    Output: [3,3,1]
    Explanation: On day 0, paint everything between 1 and 4. The amount of new 
                 area painted on day 0 is 4 - 1 = 3. On day 1, paint everything 
                 between 4 and 7. The amount of new area painted on day 1 is 
                 7 - 4 = 3. On day 2, paint everything between 7 and 8. 
                 Everything between 5 and 7 was already painted on day 1. The 
                 amount of new area painted on day 2 is 8 - 7 = 1. 
    
    Example 2:
    Input: paint = [[1,4],[5,8],[4,7]]
    Output: [3,3,1]
    Explanation: On day 0, paint everything between 1 and 4. The amount of new 
                 area painted on day 0 is 4 - 1 = 3. On day 1, paint everything 
                 between 5 and 8. The amount of new area painted on day 1 is 
                 8 - 5 = 3. On day 2, paint everything between 4 and 5. 
                 Everything between 5 and 7 was already painted on day 1. The 
                 amount of new area painted on day 2 is 5 - 4 = 1. 
    
    Example 3:
    Input: paint = [[1,5],[2,4]]
    Output: [4,0]
    Explanation: On day 0, paint everything between 1 and 5. The amount of new 
                 area painted on day 0 is 5 - 1 = 4. On day 1, paint nothing 
                 because everything between 2 and 4 was already painted on day 
                 0. The amount of new area painted on day 1 is 0.

    Constraints:
    * 1 <= paint.length <= 10^5
    * paint[i].length == 2
    * 0 <= starti < endi <= 5 * 10^4*/

    public int[] amountPainted(int[][] paint) {
        int n = paint.length; 
        int[] ans = new int[n]; 
        TreeMap<Integer, Integer> tree = new TreeMap(); 
        for (int i = 0; i < n; ++i) {
            int x = paint[i][0], y = paint[i][1], diff = 0; 
            var p = tree.floorEntry(x); 
            if (p != null && x < p.getValue()) {
                int xx = p.getKey(), yy = p.getValue(); 
                x = Math.min(x, xx); 
                y = Math.max(y, yy); 
                diff -= yy - xx; 
                tree.remove(p.getKey()); 
            }
            p = tree.ceilingEntry(x); 
            while (p != null && p.getKey() < y) {
                int xx = p.getKey(), yy = p.getValue(); 
                y = Math.max(y, yy); 
                diff -= yy - xx; 
                tree.remove(p.getKey()); 
                p = tree.ceilingEntry(x); 
            }
            ans[i] = y - x + diff; 
            tree.put(x, y); 
        }
        return ans; 
    }


    /*2187. Minimum Time to Complete Trips (Medium)
    You are given an array time where time[i] denotes the time taken by the ith 
    bus to complete one trip. Each bus can make multiple trips successively; 
    that is, the next trip can start immediately after completing the current 
    trip. Also, each bus operates independently; that is, the trips of one bus 
    do not influence the trips of any other bus. You are also given an integer 
    totalTrips, which denotes the number of trips all buses should make in 
    total. Return the minimum time required for all buses to complete at least 
    totalTrips trips.

    Example 1:
    Input: time = [1,2,3], totalTrips = 5
    Output: 3
    Explanation: - At time t = 1, the number of trips completed by each bus are 
                   [1,0,0]. The total number of trips completed is 
                   1 + 0 + 0 = 1.
                 - At time t = 2, the number of trips completed by each bus are 
                   [2,1,0]. The total number of trips completed is 
                   2 + 1 + 0 = 3.
                 - At time t = 3, the number of trips completed by each bus are 
                   [3,1,1]. The total number of trips completed is 
                   3 + 1 + 1 = 5.
                 So the minimum time needed for all buses to complete at least 
                 5 trips is 3.
    
    Example 2:
    Input: time = [2], totalTrips = 1
    Output: 2
    Explanation: There is only one bus, and it will complete its first trip at 
                 t = 2. So the minimum time needed to complete 1 trip is 2.

    Constraints:
    * 1 <= time.length <= 10^5
    * 1 <= time[i], totalTrips <= 10^7*/

    public long minimumTime(int[] time, int totalTrips) {
        long lo = 0, hi = 100_000_000_000_000l; 
        while (lo < hi) {
            long mid = lo + (hi-lo)/2, val = 0; 
            for (var x : time) val += mid/x; 
            if (val < totalTrips) lo = mid+1; 
            else hi = mid; 
        }
        return lo; 
    }


    /*2189. Number of Ways to Build House of Cards (Medium)
    You are given an integer n representing the number of playing cards you 
    have. A house of cards meets the following conditions:
    * A house of cards consists of one or more rows of triangles and horizontal 
      cards.
    * Triangles are created by leaning two cards against each other.
    * One card must be placed horizontally between all adjacent triangles in a 
      row.
    * Any triangle on a row higher than the first must be placed on a 
      horizontal card from the previous row.
    * Each triangle is placed in the leftmost available spot in the row.
    Return the number of distinct house of cards you can build using all n 
    cards. Two houses of cards are considered distinct if there exists a row 
    where the two houses contain a different number of cards.

    Example 1:
    Input: n = 16
    Output: 2
    Explanation: The two valid houses of cards are shown. The third house of 
                 cards in the diagram is not valid because the rightmost 
                 triangle on the top row is not placed on top of a horizontal 
                 card.
    
    Example 2:
    Input: n = 2
    Output: 1
    Explanation: The one valid house of cards is shown.

    Example 3:
    Input: n = 4
    Output: 0
    Explanation: The three houses of cards in the diagram are not valid. The 
                 first house of cards needs a horizontal card placed between 
                 the two triangles. The second house of cards uses 5 cards. The 
                 third house of cards uses 2 cards.

    Constraints: 1 <= n <= 500*/

    public int houseOfCards(int n) {
        int[] dp = new int[n+1]; 
        dp[0] = 1; 
        for (int x = 2; x <= n; x += 3) 
            for (int i = n; i >= x; --i)
                dp[i] += dp[i-x]; 
        return dp[n]; 
    }


    /*2198. Number of Single Divisor Triplets (Medium)
    You are given a 0-indexed array of positive integers nums. A triplet of 
    three distinct indices (i, j, k) is called a single divisor triplet of nums 
    if nums[i] + nums[j] + nums[k] is divisible by exactly one of nums[i], 
    nums[j], or nums[k]. Return the number of single divisor triplets of nums.

    Example 1:
    Input: nums = [4,6,7,3,2]
    Output: 12
    Explanation: The triplets (0, 3, 4), (0, 4, 3), (3, 0, 4), (3, 4, 0), 
                 (4, 0, 3), and (4, 3, 0) have the values of [4, 3, 2] (or a 
                 permutation of [4, 3, 2]). 4 + 3 + 2 = 9 which is only 
                 divisible by 3, so all such triplets are single divisor 
                 triplets. The triplets (0, 2, 3), (0, 3, 2), (2, 0, 3), 
                 (2, 3, 0), (3, 0, 2), and (3, 2, 0) have the values of 
                 [4, 7, 3] (or a permutation of [4, 7, 3]). 4 + 7 + 3 = 14 
                 which is only divisible by 7, so all such triplets are single 
                 divisor triplets. There are 12 single divisor triplets in 
                 total.
    
    Example 2:
    Input: nums = [1,2,2]
    Output: 6
    Explanation: The triplets (0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), 
                 (2, 0, 1), and (2, 1, 0) have the values of [1, 2, 2] (or a 
                 permutation of [1, 2, 2]). 1 + 2 + 2 = 5 which is only 
                 divisible by 1, so all such triplets are single divisor 
                 triplets. There are 6 single divisor triplets in total.
    
    Example 3:
    Input: nums = [1,1,1]
    Output: 0
    Explanation: There are no single divisor triplets. Note that (0, 1, 2) is 
                 not a single divisor triplet because 
                 nums[0] + nums[1] + nums[2] = 3 and 3 is divisible by nums[0], 
                 nums[1], and nums[2].

    Constraints:
    * 3 <= nums.length <= 10^5
    * 1 <= nums[i] <= 100*/

    public long singleDivisorTriplet(int[] nums) {
        int[] freq = new int[101]; 
        for (int x : nums) ++freq[x]; 
        long ans = 0; 
        for (int i = 1; i <= 100; ++i) 
            for (int j = i; freq[i] > 0 && j <= 100; ++j) 
                for (int k = j; freq[j] > 0 && k <= 100; ++k) 
                    if (freq[k] > 0) {
                        int total = i + j + k; 
                        if ((total%i > 0 ? 1 : 0) + (total%j > 0 ? 1 : 0) + (total%k > 0 ? 1 : 0) == 2) 
                            if (i == j) ans += (long) freq[i]*(freq[j]-1)/2*freq[k]; 
                            else if (j == k) ans += (long) freq[i]*freq[j]*(freq[k]-1)/2; 
                            else ans += (long) freq[i]*freq[j]*freq[k]; 
                    }
        return ans*6; 
    }


    /*2204. Distance to a Cycle in Undirected Graph (Hard)
    You are given a positive integer n representing the number of nodes in a 
    connected undirected graph containing exactly one cycle. The nodes are 
    numbered from 0 to n - 1 (inclusive). You are also given a 2D integer array 
    edges, where edges[i] = [node1i, node2i] denotes that there is a 
    bidirectional edge connecting node1i and node2i in the graph. The distance 
    between two nodes a and b is defined to be the minimum number of edges that 
    are needed to go from a to b. Return an integer array answer of size n, 
    where answer[i] is the minimum distance between the ith node and any node 
    in the cycle.

    Example 1:
    Input: n = 7, edges = [[1,2],[2,4],[4,3],[3,1],[0,1],[5,2],[6,5]]
    Output: [1,0,0,0,0,1,2]
    Explanation: The nodes 1, 2, 3, and 4 form the cycle.
                 The distance from 0 to 1 is 1.
                 The distance from 1 to 1 is 0.
                 The distance from 2 to 2 is 0.
                 The distance from 3 to 3 is 0.
                 The distance from 4 to 4 is 0.
                 The distance from 5 to 2 is 1.
                 The distance from 6 to 2 is 2.
    
    Example 2:
    Input: n = 9, edges = [[0,1],[1,2],[0,2],[2,6],[6,7],[6,8],[0,3],[3,4],[3,5]]
    Output: [0,0,0,1,2,2,1,2,2]
    Explanation: The nodes 0, 1, and 2 form the cycle.
                 The distance from 0 to 0 is 0.
                 The distance from 1 to 1 is 0.
                 The distance from 2 to 2 is 0.
                 The distance from 3 to 1 is 1.
                 The distance from 4 to 1 is 2.
                 The distance from 5 to 1 is 2.
                 The distance from 6 to 2 is 1.
                 The distance from 7 to 2 is 2.
                 The distance from 8 to 2 is 2.

    Constraints:
    * 3 <= n <= 10^5
    * edges.length == n
    * edges[i].length == 2
    * 0 <= node1i, node2i <= n - 1
    * node1i != node2i
    * The graph is connected.
    * The graph has exactly one cycle.
    * There is at most one edge between any pair of vertices.*/

    public int[] distanceToCycle(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) 
            graph[u] = new ArrayList(); 
        for (var e : edges) {
            graph[e[0]].add(e[1]); 
            graph[e[1]].add(e[0]); 
        }
        Stack<Integer> stk = new Stack(); 
        int[] degree = new int[n]; 
        for (int u = 0; u < n; ++u) {
            degree[u] = graph[u].size(); 
            if (degree[u] == 1) stk.push(u); 
        }
        while (!stk.isEmpty()) {
            var u = stk.pop(); 
            for (var v : graph[u]) 
                if (degree[v] > 1 && --degree[v] == 1) stk.push(v); 
        }
        int[] ans = new int[n]; 
        Arrays.fill(ans, -1); 
        Queue<Integer> q = new LinkedList(); 
        for (int u = 0; u < n; ++u) 
            if (degree[u] > 1) {
                q.add(u); 
                ans[u] = 0; 
            }
        for (int val = 1; !q.isEmpty(); ++val) 
            for (int sz = q.size(); sz > 0; --sz) {
                var u = q.poll(); 
                for (var v : graph[u]) 
                    if (ans[v] == -1) {
                        q.add(v); 
                        ans[v] = val; 
                    }
            }
        return ans; 
    }


    /*2247. Maximum Cost of Trip With K Highways (Hard)
    A series of highways connect n cities numbered from 0 to n - 1. You are 
    given a 2D integer array highways where highways[i] = [city1i, city2i, tolli] 
    indicates that there is a highway that connects city1i and city2i, allowing 
    a car to go from city1i to city2i and vice versa for a cost of tolli. You 
    are also given an integer k. You are going on a trip that crosses exactly k 
    highways. You may start at any city, but you may only visit each city at 
    most once during your trip. Return the maximum cost of your trip. If there 
    is no trip that meets the requirements, return -1.

    Example 1:
    Input: n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], k = 3
    Output: 17
    Explanation: One possible trip is to go from 0 -> 1 -> 4 -> 3. The cost of 
                 this trip is 4 + 11 + 2 = 17. Another possible trip is to go 
                 from 4 -> 1 -> 2 -> 3. The cost of this trip is 
                 11 + 3 + 3 = 17. It can be proven that 17 is the maximum 
                 possible cost of any valid trip. Note that the trip 
                 4 -> 1 -> 0 -> 1 is not allowed because you visit the city 1 
                 twice.
    
    Example 2:
    Input: n = 4, highways = [[0,1,3],[2,3,2]], k = 2
    Output: -1
    Explanation: There are no valid trips of length 2, so return -1.

    Constraints:
    * 2 <= n <= 15
    * 1 <= highways.length <= 50
    * highways[i].length == 3
    * 0 <= city1i, city2i <= n - 1
    * city1i != city2i
    * 0 <= tolli <= 100
    * 1 <= k <= 50
    * There are no duplicate highways.*/

    public int maximumCost(int n, int[][] highways, int k) {
        List<Pair<Integer, Integer>>[] graph = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) graph[u] = new ArrayList(); 
        for (var h : highways) {
            graph[h[0]].add(new Pair(h[1], h[2])); 
            graph[h[1]].add(new Pair(h[0], h[2])); 
        }
        int[][] dp = new int[n][1<<n]; 
        for (var row : dp) Arrays.fill(row, Integer.MIN_VALUE); 
        for (int m = (1<<n)-1; m >= 0; --m) 
            for (int u = 0; u < n; ++u)
                if ((m & 1<<u) > 0) {
                    int cnt = Integer.bitCount(m); 
                    if (cnt == k+1) dp[u][m] = 0; 
                    else if (cnt < k+1) 
                        for (var elem : graph[u]) {
                            int v = elem.getKey(), w = elem.getValue(); 
                            if ((m & 1<<v) == 0) dp[u][m] = Math.max(dp[u][m], w + dp[v][m ^ 1<<v]); 
                        }
                }
        int ans = -1; 
        for (int u = 0; u < n; ++u) ans = Math.max(ans, dp[u][1<<u]); 
        return ans; 
    }


    /*2263. Make Array Non-decreasing or Non-increasing (Hard)
    You are given a 0-indexed integer array nums. In one operation, you can:
    * Choose an index i in the range 0 <= i < nums.length
    * Set nums[i] to nums[i] + 1 or nums[i] - 1
    Return the minimum number of operations to make nums non-decreasing or non-
    increasing.

    Example 1:
    Input: nums = [3,2,4,5,0]
    Output: 4
    Explanation: One possible way to turn nums into non-increasing order is to:
                 - Add 1 to nums[1] once so that it becomes 3.
                 - Subtract 1 from nums[2] once so it becomes 3.
                 - Subtract 1 from nums[3] twice so it becomes 3.
                 After doing the 4 operations, nums becomes [3,3,3,3,0] which 
                 is in non-increasing order. Note that it is also possible to 
                 turn nums into [4,4,4,4,0] in 4 operations. It can be proven 
                 that 4 is the minimum number of operations needed.
    
    Example 2:
    Input: nums = [2,2,3,4]
    Output: 0
    Explanation: nums is already in non-decreasing order, so no operations are 
                 needed and we return 0.
    
    Example 3:
    Input: nums = [0]
    Output: 0
    Explanation: nums is already in non-decreasing order, so no operations are 
                 needed and we return 0.

    Constraints:
    * 1 <= nums.length <= 1000
    * 0 <= nums[i] <= 1000

    Follow up: Can you solve it in O(n*log(n)) time complexity?*/

    private int fn(int[] nums) {
        Queue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        int ans = 0; 
        for (var x : nums) {
            if (!pq.isEmpty() && x < pq.peek()) {
                ans += pq.poll() - x; 
                pq.add(x); 
            }
            pq.add(x); 
        }
        return ans; 
    }
    
    public int convertArray(int[] nums) {
        int ans = fn(nums); 
        for (int i = 0, n = nums.length; i < n/2; ++i) {
            int temp = nums[i]; 
            nums[i] = nums[n-1-i]; 
            nums[n-1-i] = temp; 
        }
        return Math.min(ans, fn(nums)); 
    }


    /*2277. Closest Node to Path in Tree (Hard)
    You are given a positive integer n representing the number of nodes in a 
    tree, numbered from 0 to n - 1 (inclusive). You are also given a 2D integer 
    array edges of length n - 1, where edges[i] = [node1i, node2i] denotes that 
    there is a bidirectional edge connecting node1i and node2i in the tree. You 
    are given a 0-indexed integer array query of length m where 
    query[i] = [starti, endi, nodei] means that for the ith query, you are 
    tasked with finding the node on the path from starti to endi that is 
    closest to nodei. Return an integer array answer of length m, where 
    answer[i] is the answer to the ith query.

    Example 1:
    Input: n = 7, edges = [[0,1],[0,2],[0,3],[1,4],[2,5],[2,6]], query = [[5,3,4],[5,3,6]]
    Output: [0,2]
    Explanation: The path from node 5 to node 3 consists of the nodes 5, 2, 0, 
                 and 3. The distance between node 4 and node 0 is 2. Node 0 is 
                 the node on the path closest to node 4, so the answer to the 
                 first query is 0. The distance between node 6 and node 2 is 1.
                 Node 2 is the node on the path closest to node 6, so the 
                 answer to the second query is 2.
    
    Example 2:
    Input: n = 3, edges = [[0,1],[1,2]], query = [[0,1,2]]
    Output: [1]
    Explanation: The path from node 0 to node 1 consists of the nodes 0, 1. The 
                 distance between node 2 and node 1 is 1. Node 1 is the node on 
                 the path closest to node 2, so the answer to the first query 
                 is 1.
    
    Example 3:
    Input: n = 3, edges = [[0,1],[1,2]], query = [[0,0,0]]
    Output: [0]
    Explanation: The path from node 0 to node 0 consists of the node 0. Since 0 
                 is the only node on the path, the answer to the first query is 
                 0.

    Constraints:
    * 1 <= n <= 1000
    * edges.length == n - 1
    * edges[i].length == 2
    * 0 <= node1i, node2i <= n - 1
    * node1i != node2i
    * 1 <= query.length <= 1000
    * query[i].length == 3
    * 0 <= starti, endi, nodei <= n - 1
    * The graph is a tree.*/

    private int lca(int u, int v, int[] depth, int[][] lift) {
        if (depth[u] > depth[v]) { int tmp = u; u = v; v = tmp; }
        for (int i = 0; i < 32; ++i) 
            if ((depth[v] - depth[u] & 1<<i) > 0) v = lift[v][i]; 
        if (u == v) return u; 
        for (int i = 31; i >= 0; --i)
            if (lift[u][i] != lift[v][i]) {
                u = lift[u][i]; 
                v = lift[v][i]; 
            }
        return lift[u][0]; 
    }
    
    public int[] closestNode(int n, int[][] edges, int[][] query) {
        List<Integer>[] tree = new ArrayList[n]; 
        for (int i = 0; i < n; ++i) tree[i] = new ArrayList(); 
        for (var e : edges) {
            tree[e[0]].add(e[1]); 
            tree[e[1]].add(e[0]); 
        }
        int[][] lift = new int[n][32]; 
        for (int i = 0; i < n; ++i) Arrays.fill(lift[i], -1); 
        int[] depth = new int[n]; 
        Arrays.fill(depth, -1); 
        Stack<int[]> stk = new Stack(); 
        stk.add(new int[] {0, -1, 0}); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            int u = elem[0], p = elem[1], d = elem[2]; 
            depth[u] = d; 
            for (var v : tree[u]) 
                if (v != p) {
                    lift[v][0] = u; 
                    for (int j = 1; j < 32 && lift[v][j-1] != -1; ++j) 
                        lift[v][j] = lift[lift[v][j-1]][j-1]; 
                    stk.add(new int[] {v, u, d+1}); 
                }
        }
        int[] ans = new int[query.length]; 
        for (int i = 0; i < query.length; ++i) {
            int x = lca(query[i][0], query[i][1], depth, lift), y = lca(query[i][1], query[i][2], depth, lift), z = lca(query[i][2], query[i][0], depth, lift); 
            if (depth[x] < depth[y]) x = y; 
            if (depth[x] < depth[z]) x = z; 
            ans[i] = x; 
        }
        return ans; 
    }


    /*2279. Maximum Bags With Full Capacity of Rocks (Medium)
    You have n bags numbered from 0 to n - 1. You are given two 0-indexed 
    integer arrays capacity and rocks. The ith bag can hold a maximum of 
    capacity[i] rocks and currently contains rocks[i] rocks. You are also given 
    an integer additionalRocks, the number of additional rocks you can place in 
    any of the bags. Return the maximum number of bags that could have full 
    capacity after placing the additional rocks in some bags.

    Example 1:
    Input: capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
    Output: 3
    Explanation: Place 1 rock in bag 0 and 1 rock in bag 1. The number of rocks 
                 in each bag are now [2,3,4,4]. Bags 0, 1, and 2 have full 
                 capacity. There are 3 bags at full capacity, so we return 3. 
                 It can be shown that it is not possible to have more than 3 
                 bags at full capacity. Note that there may be other ways of 
                 placing the rocks that result in an answer of 3.
    
    Example 2:
    Input: capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
    Output: 3
    Explanation: Place 8 rocks in bag 0 and 2 rocks in bag 2. The number of 
                 rocks in each bag are now [10,2,2]. Bags 0, 1, and 2 have full 
                 capacity. There are 3 bags at full capacity, so we return 3. 
                 It can be shown that it is not possible to have more than 3 
                 bags at full capacity. Note that we did not use all of the 
                 additional rocks.

    Constraints:
    * n == capacity.length == rocks.length
    * 1 <= n <= 5 * 10^4
    * 1 <= capacity[i] <= 10^9
    * 0 <= rocks[i] <= capacity[i]
    * 1 <= additionalRocks <= 10^9*/

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int[] diff = new int[capacity.length]; 
        for (int i = 0; i < capacity.length; ++i) diff[i] = capacity[i] - rocks[i]; 
        Arrays.sort(diff);
        int ans = 0; 
        for (var x : diff) 
            if (x <= additionalRocks) {
                ++ans; 
                additionalRocks -= x; 
            }
        return ans; 
    }


    /*2307. Check for Contradictions in Equations (Hard)
    You are given a 2D array of strings equations and an array of real numbers 
    values, where equations[i] = [Ai, Bi] and values[i] means that 
    Ai / Bi = values[i]. Determine if there exists a contradiction in the 
    equations. Return true if there is a contradiction, or false otherwise.

    Note:
    * When checking if two numbers are equal, check that their absolute 
      difference is less than 10-5.
    * The testcases are generated such that there are no cases targeting 
      precision, i.e. using double is enough to solve the problem.

    Example 1:
    Input: equations = [["a","b"],["b","c"],["a","c"]], values = [3,0.5,1.5]
    Output: false
    Explanation: The given equations are: a / b = 3, b / c = 0.5, a / c = 1.5 
                 There are no contradictions in the equations. One possible 
                 assignment to satisfy all equations is: a = 3, b = 1 and c = 2.
    
    Example 2:
    Input: equations = [["le","et"],["le","code"],["code","et"]], values = [2,5,0.5]
    Output: true
    Explanation: The given equations are: le / et = 2, le / code = 5, 
                 code / et = 0.5 Based on the first two equations, we get 
                 code / et = 0.4. Since the third equation is 
                 code / et = 0.5, we get a contradiction.

    Constraints:
    * 1 <= equations.length <= 100
    * equations[i].length == 2
    * 1 <= Ai.length, Bi.length <= 5
    * Ai, Bi consist of lowercase English letters.
    * equations.length == values.length
    * 0.0 < values[i] <= 10.0
    * values[i] has a maximum of 2 decimal places.*/

    public boolean checkContradictions(List<List<String>> equations, double[] values) {
        Set<String> node = new HashSet(); 
        Map<String, List<Pair<String, Double>>> graph = new HashMap(); 
        for (int i = 0; i < equations.size(); ++i) {
            String u = equations.get(i).get(0), v = equations.get(i).get(1); 
            node.add(u); 
            node.add(v); 
            if (!graph.containsKey(u)) graph.put(u, new ArrayList()); 
            if (!graph.containsKey(v)) graph.put(v, new ArrayList()); 
            graph.get(u).add(new Pair(v, values[i])); 
            graph.get(v).add(new Pair(u, 1/values[i])); 
        }
        Map<String, Double> vals = new HashMap(); 
        for (var u : node) 
            if (!vals.containsKey(u)) {
                Stack<String> stk = new Stack(); stk.push(u); 
                vals.put(u, 1.); 
                while (!stk.isEmpty()) {
                    var x = stk.pop(); 
                    for (var elem : graph.get(x)) {
                        String v = elem.getKey(); 
                        Double w = elem.getValue(); 
                        if (vals.containsKey(v)) {
                            if (Math.abs(vals.get(v) - vals.get(x)/w) > 1e-5*vals.get(v)) return true; 
                        } else {
                            stk.push(v); 
                            vals.put(v, vals.get(x)/w); 
                        }
                    }
                }
            }
        return false; 
    }


    /*2359. Find Closest Node to Given Two Nodes (Medium)
    You are given a directed graph of n nodes numbered from 0 to n - 1, where 
    each node has at most one outgoing edge. The graph is represented with a 
    given 0-indexed array edges of size n, indicating that there is a directed 
    edge from node i to node edges[i]. If there is no outgoing edge from i, 
    then edges[i] == -1. You are also given two integers node1 and node2. 
    Return the index of the node that can be reached from both node1 and node2, 
    such that the maximum between the distance from node1 to that node, and 
    from node2 to that node is minimized. If there are multiple answers, return 
    the node with the smallest index, and if no possible answer exists, return 
    -1. Note that edges may contain cycles.

    Example 1:
    Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
    Output: 2
    Explanation: The distance from node 0 to node 2 is 1, and the distance from 
                 node 1 to node 2 is 1. The maximum of those two distances is 1. 
                 It can be proven that we cannot get a node with a smaller 
                 maximum distance than 1, so we return node 2.
    
    Example 2:
    Input: edges = [1,2,-1], node1 = 0, node2 = 2
    Output: 2
    Explanation: The distance from node 0 to node 2 is 2, and the distance from 
                 node 2 to itself is 0. The maximum of those two distances is 2. 
                 It can be proven that we cannot get a node with a smaller 
                 maximum distance than 2, so we return node 2.

    Constraints:
    * n == edges.length
    * 2 <= n <= 10^5
    * -1 <= edges[i] < n
    * edges[i] != i
    * 0 <= node1, node2 < n*/

    private static int[] bfs(int u, int[] edges) {
        int[] dist = new int[edges.length]; 
        Arrays.fill(dist, Integer.MAX_VALUE); 
        for (int k = 0; u != -1 && dist[u] == Integer.MAX_VALUE; ++k, u = edges[u]) 
            dist[u] = k; 
        return dist; 
    }
    
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int ans = -1, small = Integer.MAX_VALUE; 
        int[] dist1 = bfs(node1, edges), dist2 = bfs(node2, edges); 
        for (int i = 0, n = edges.length; i < n; ++i) {
            int cand = Math.max(dist1[i], dist2[i]); 
            if (cand < small) {
                ans = i; 
                small = cand; 
            }
        }
        return ans; 
    }


    /*2444. Count Subarrays With Fixed Bounds (Hard)
    You are given an integer array nums and two integers minK and maxK. A 
    fixed-bound subarray of nums is a subarray that satisfies the following 
    conditions:
    * The minimum value in the subarray is equal to minK.
    * The maximum value in the subarray is equal to maxK.
    Return the number of fixed-bound subarrays. A subarray is a contiguous part 
    of an array.

    Example 1:
    Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
    Output: 2
    Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].

    Example 2:
    Input: nums = [1,1,1,1], minK = 1, maxK = 1
    Output: 10
    Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 
                 possible subarrays.

    Constraints:
    * 2 <= nums.length <= 10^5
    * 1 <= nums[i], minK, maxK <= 10^6*/

    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0; 
        for (int i = 0, ii = -1, imin = -1, imax = -1; i < nums.length; ++i) {
            if (minK <= nums[i] && nums[i] <= maxK) {
                if (minK == nums[i]) imin = i; 
                if (maxK == nums[i]) imax = i; 
                ans += Math.max(0, Math.min(imax, imin) - ii); 
            } else ii = i; 
        }
        return ans; 
    }


    /*2477. Minimum Fuel Cost to Report to the Capital (Medium)
    There is a tree (i.e., a connected, undirected graph with no cycles) 
    structure country network consisting of n cities numbered from 0 to n - 1 
    and exactly n - 1 roads. The capital city is city 0. You are given a 2D 
    integer array roads where roads[i] = [ai, bi] denotes that there exists a 
    bidirectional road connecting cities ai and bi. There is a meeting for the 
    representatives of each city. The meeting is in the capital city. There is 
    a car in each city. You are given an integer seats that indicates the 
    number of seats in each car. A representative can use the car in their city 
    to travel or change the car and ride with another representative. The cost 
    of traveling between two cities is one liter of fuel. Return the minimum 
    number of liters of fuel to reach the capital city.

    Example 1:
    Input: roads = [[0,1],[0,2],[0,3]], seats = 5
    Output: 3
    Explanation: - Representative1 goes directly to the capital with 1 liter of 
                   fuel.
                 - Representative2 goes directly to the capital with 1 liter of 
                   fuel.
                 - Representative3 goes directly to the capital with 1 liter of 
                   fuel.
                 It costs 3 liters of fuel at minimum. It can be proven that 3 
                 is the minimum number of liters of fuel needed.
    
    Example 2:
    Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
    Output: 7
    Explanation: - Representative2 goes directly to city 3 with 1 liter of fuel.
                 - Representative2 and representative3 go together to city 1 
                   with 1 liter of fuel.
                 - Representative2 and representative3 go together to the 
                   capital with 1 liter of fuel.
                 - Representative1 goes directly to the capital with 1 liter of 
                   fuel.
                 - Representative5 goes directly to the capital with 1 liter of 
                   fuel.
                 - Representative6 goes directly to city 4 with 1 liter of fuel.
                 - Representative4 and representative6 go together to the 
                   capital with 1 liter of fuel.
                 It costs 7 liters of fuel at minimum. It can be proven that 7 
                 is the minimum number of liters of fuel needed.
    
    Example 3:
    Input: roads = [], seats = 1
    Output: 0
    Explanation: No representatives need to travel to the capital city.

    Constraints:
    * 1 <= n <= 10^5
    * roads.length == n - 1
    * roads[i].length == 2
    * 0 <= ai, bi < n
    * ai != bi
    * roads represents a valid tree.
    * 1 <= seats <= 10^5*/

    private long[] dfs(int u, int p, int seats, List<Integer>[] graph) {
        long ans = 0, ppl = 1; 
        for (var v : graph[u]) {
            if (v != p) {
                var val = dfs(v, u, seats, graph); 
                ppl += val[0]; 
                ans += val[1]; 
            }
        }
        if (u > 0) ans += (ppl + seats - 1) / seats; 
        return new long[]{ppl, ans}; 
    }
    
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length+1; 
        List<Integer>[] graph = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) graph[u] = new ArrayList(); 
        for (var r : roads) {
            graph[r[0]].add(r[1]); 
            graph[r[1]].add(r[0]); 
        }
        return dfs(0, -1, seats, graph)[1];         
    }


    /*2479. Maximum XOR of Two Non-Overlapping Subtrees (Hard)
    There is an undirected tree with n nodes labeled from 0 to n - 1. You are 
    given the integer n and a 2D integer array edges of length n - 1, where 
    edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi 
    in the tree. The root of the tree is the node labeled 0. Each node has an 
    associated value. You are given an array values of length n, where values[i] 
    is the value of the ith node. Select any two non-overlapping subtrees. Your 
    score is the bitwise XOR of the sum of the values within those subtrees.
    Return the maximum possible score you can achieve. If it is impossible to 
    find two nonoverlapping subtrees, return 0.

    Note that:
    * The subtree of a node is the tree consisting of that node and all of its 
      descendants.
    * Two subtrees are non-overlapping if they do not share any common node.

    Example 1:
    Input: n = 6, edges = [[0,1],[0,2],[1,3],[1,4],[2,5]], values = [2,8,3,6,2,5]
    Output: 24
    Explanation: Node 1's subtree has sum of values 16, while node 2's subtree 
                 has sum of values 8, so choosing these nodes will yield a 
                 score of 16 XOR 8 = 24. It can be proved that is the maximum 
                 possible score we can obtain.
    
    Example 2:
    Input: n = 3, edges = [[0,1],[1,2]], values = [4,6,1]
    Output: 0
    Explanation: There is no possible way to select two non-overlapping 
                 subtrees, so we just return 0.

    Constraints:
    * 2 <= n <= 5 * 10^4
    * edges.length == n - 1
    * 0 <= ai, bi < n
    * values.length == n
    * 1 <= values[i] <= 10^9
    * It is guaranteed that edges represents a valid tree.*/

    class TrieNode {
        public TrieNode[] child = new TrieNode[] {null, null}; 
        public long val = 0; 
    }
    
    private long dfs(int u, int p, int[] values, long[] sum, List<Integer>[] tree) {
        sum[u] = values[u];
        for (var v : tree[u]) 
            if (v != p) sum[u] += dfs(v, u, values, sum, tree); 
        return sum[u]; 
    }
    
    private long calc(int u, int p, TrieNode trie, long[] sum, List<Integer>[] tree) {
        long ans = 0; 
        if (trie.child[0] != null || trie.child[1] != null) {
            TrieNode node = trie; 
            for (int i = 45; i >= 0; --i) {
                int b = (int) (sum[u] >> i) & 1; 
                if (node.child[1-b] != null) node = node.child[1-b]; 
                else node = node.child[b]; 
            }
            ans = node.val ^ sum[u]; 
        }
        for (var v : tree[u]) 
            if (v != p) ans = Math.max(ans, calc(v, u, trie, sum, tree)); 
        TrieNode node = trie; 
        for (int i = 45; i >= 0; --i) {
            int b = (int) (sum[u] >> i) & 1; 
            if (node.child[b] == null) node.child[b] = new TrieNode(); 
            node = node.child[b]; 
        }
        node.val = sum[u]; 
        return ans; 
    }
    
    public long maxXor(int n, int[][] edges, int[] values) {
        List<Integer>[] tree = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) tree[u] = new ArrayList(); 
        for (var e : edges) {
            tree[e[0]].add(e[1]); 
            tree[e[1]].add(e[0]); 
        }
        
        long[] sum = new long[n]; 
        dfs(0, -1, values, sum, tree); 
        
        TrieNode trie = new TrieNode(); 
        return calc(0, -1, trie, sum, tree); 
    }


    /*2481. Minimum Cuts to Divide a Circle (Easy)
    A valid cut in a circle can be:
    * A cut that is represented by a straight line that touches two points on 
      the edge of the circle and passes through its center, or
    * A cut that is represented by a straight line that touches one point on 
      the edge of the circle and its center.
    Some valid and invalid cuts are shown in the figures below. Given the 
    integer n, return the minimum number of cuts needed to divide a circle into 
    n equal slices.

    Example 1:
    Input: n = 4
    Output: 2
    Explanation: The above figure shows how cutting the circle twice through 
                 the middle divides it into 4 equal slices.
    
    Example 2:
    Input: n = 3
    Output: 3
    Explanation: At least 3 cuts are needed to divide the circle into 3 equal 
                 slices. It can be shown that less than 3 cuts cannot result in 
                 3 slices of equal size and shape. Also note that the first cut 
                 will not divide the circle into distinct parts.

    Constraints: 1 <= n <= 100*/

    public int numberOfCuts(int n) {
        if (n == 1) return 0; 
        return n%2 == 1 ? n : n/2; 
    }


    /*2482. Difference Between Ones and Zeros in Row and Column (Medium)
    You are given a 0-indexed m x n binary matrix grid. A 0-indexed m x n 
    difference matrix diff is created with the following procedure:
    * Let the number of ones in the ith row be onesRowi.
    * Let the number of ones in the jth column be onesColj.
    * Let the number of zeros in the ith row be zerosRowi.
    * Let the number of zeros in the jth column be zerosColj.
    * diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj
    Return the difference matrix diff.

    Example 1:
    Input: grid = [[0,1,1],[1,0,1],[0,0,1]]
    Output: [[0,0,4],[0,0,4],[-2,-2,2]]
    Explanation: - diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 
                   = 2 + 1 - 1 - 2 = 0 
                 - diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1
                   = 2 + 1 - 1 - 2 = 0 
                 - diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 
                   = 2 + 3 - 1 - 0 = 4 
                 - diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 
                   = 2 + 1 - 1 - 2 = 0 
                 - diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 
                   = 2 + 1 - 1 - 2 = 0 
                 - diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 
                   = 2 + 3 - 1 - 0 = 4 
                 - diff[2][0] = onesRow2 + onesCol0 - zerosRow2 - zerosCol0 
                   = 1 + 1 - 2 - 2 = -2
                 - diff[2][1] = onesRow2 + onesCol1 - zerosRow2 - zerosCol1 
                   = 1 + 1 - 2 - 2 = -2
                 - diff[2][2] = onesRow2 + onesCol2 - zerosRow2 - zerosCol2 
                   = 1 + 3 - 2 - 0 = 2
    
    Example 2:
    Input: grid = [[1,1,1],[1,1,1]]
    Output: [[5,5,5],[5,5,5]]
    Explanation: - diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 
                   = 3 + 2 - 0 - 0 = 5
                 - diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 
                   = 3 + 2 - 0 - 0 = 5
                 - diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 
                   = 3 + 2 - 0 - 0 = 5
                 - diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 
                   = 3 + 2 - 0 - 0 = 5
                 - diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 
                   = 3 + 2 - 0 - 0 = 5
                 - diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 
                   = 3 + 2 - 0 - 0 = 5

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 1 <= m, n <= 10^5
    * 1 <= m * n <= 10^5
    * grid[i][j] is either 0 or 1.*/

    public int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length; 
        int[] row = new int[m], col = new int[n]; 
        for (int i = 0; i < m; ++i) 
            for (int j = 0; j < n; ++j) {
                row[i] += grid[i][j]; 
                col[j] += grid[i][j]; 
            }
        int[][] ans = new int[m][n]; 
        for (int i = 0; i < m; ++i) 
            for (int j = 0; j < n; ++j) 
                ans[i][j] = 2*row[i] + 2*col[j] - m - n; 
        return ans; 
    }


    /*2483. Minimum Penalty for a Shop (Medium)
    You are given the customer visit log of a shop represented by a 0-indexed 
    string customers consisting only of characters 'N' and 'Y':
    * if the ith character is 'Y', it means that customers come at the ith hour
    * whereas 'N' indicates that no customers come at the ith hour.
    If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated 
    as follows:
    * For every hour when the shop is open and no customers come, the penalty 
      increases by 1.
    * For every hour when the shop is closed and customers come, the penalty 
      increases by 1.
    Return the earliest hour at which the shop must be closed to incur a 
    minimum penalty. Note that if a shop closes at the jth hour, it means the 
    shop is closed at the hour j.

    Example 1:
    Input: customers = "YYNY"
    Output: 2
    Explanation: - Closing the shop at the 0th hour incurs in 1+1+0+1 = 3 
                   penalty.
                 - Closing the shop at the 1st hour incurs in 0+1+0+1 = 2 
                   penalty.
                 - Closing the shop at the 2nd hour incurs in 0+0+0+1 = 1 
                   penalty.
                 - Closing the shop at the 3rd hour incurs in 0+0+1+1 = 2 
                   penalty.
                 - Closing the shop at the 4th hour incurs in 0+0+1+0 = 1 
                   penalty.
                 Closing the shop at 2nd or 4th hour gives a minimum penalty. 
                 Since 2 is earlier, the optimal closing time is 2.
    
    Example 2:
    Input: customers = "NNNNN"
    Output: 0
    Explanation: It is best to close the shop at the 0th hour as no customers 
                 arrive.
    
    Example 3:
    Input: customers = "YYYY"
    Output: 4
    Explanation: It is best to close the shop at the 4th hour as customers 
                 arrive at each hour.

    Constraints:
    * 1 <= customers.length <= 10^5
    * customers consists only of characters 'Y' and 'N'.*/

    public int bestClosingTime(String customers) {
        int ans = 0, prefix = (int) customers.chars().filter(ch -> ch =='Y').count(), least = prefix; 
        for (int i = 0; i < customers.length(); ++i) {
            if (customers.charAt(i) == 'N') ++prefix; 
            else --prefix; 
            if (prefix < least) {
                ans = i+1; 
                least = prefix; 
            }
        }
        return ans; 
    }


    /*2484. Count Palindromic Subsequences (Hard)
    Given a string of digits s, return the number of palindromic subsequences 
    of s having length 5. Since the answer may be very large, return it modulo 
    10^9 + 7.

    Note:
    * A string is palindromic if it reads the same forward and backward.
    * A subsequence is a string that can be derived from another string by 
      deleting some or no characters without changing the order of the 
      remaining characters.

    Example 1:
    Input: s = "103301"
    Output: 2
    Explanation: There are 6 possible subsequences of length 5: "10330","10331",
                 "10301","10301","13301","03301". Two of them (both equal to 
                 "10301") are palindromic.
    
    Example 2:
    Input: s = "0000000"
    Output: 21
    Explanation: All 21 subsequences are "00000", which is palindromic.

    Example 3:
    Input: s = "9999900000"
    Output: 2
    Explanation: The only two palindromic subsequences are "99999" and "00000".

    Constraints:
    * 1 <= s.length <= 10^4
    * s consists of digits.*/

    public int countPalindromes(String s) {
        final int mod = 1_000_000_007; 
        long ans = 0; 
        for (int x = 0; x <= 9; ++x) 
            for (int y = 0; y <= 9; ++y) {
                int[] pattern = new int[] {x, y, 0, y, x};
                long[] dp = new long[6]; 
                dp[5] = 1; 
                for (int i = 0; i < s.length(); ++i) 
                    for (int j = 0; j < 5; ++j) 
                        if (s.charAt(i) == pattern[j] + '0' || j == 2) dp[j] = (dp[j] + dp[j+1]) % mod; 
                ans = (ans + dp[0]) % mod; 
            }
        return (int) ans; 
    }


    /*2485. Find the Pivot Integer (Easy)
    Given a positive integer n, find the pivot integer x such that:
    * The sum of all elements between 1 and x inclusively equals the sum of all 
      elements between x and n inclusively.
    Return the pivot integer x. If no such integer exists, return -1. It is 
    guaranteed that there will be at most one pivot index for the given input.

    Example 1:
    Input: n = 8
    Output: 6
    Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.

    Example 2:
    Input: n = 1
    Output: 1
    Explanation: 1 is the pivot integer since: 1 = 1.

    Example 3:
    Input: n = 4
    Output: -1
    Explanation: It can be proved that no such integer exist.

    Constraints: 1 <= n <= 1000*/

    public int pivotInteger(int n) {
        int sm = n*(n+1)/2, val = (int) Math.sqrt(sm); 
        return val*val == sm ? val : -1; 
    }


    /*2486. Append Characters to String to Make Subsequence (Medium)
    You are given two strings s and t consisting of only lowercase English 
    letters. Return the minimum number of characters that need to be appended 
    to the end of s so that t becomes a subsequence of s. A subsequence is a 
    string that can be derived from another string by deleting some or no 
    characters without changing the order of the remaining characters.

    Example 1:
    Input: s = "coaching", t = "coding"
    Output: 4
    Explanation: Append the characters "ding" to the end of s so that 
                 s = "coachingding". Now, t is a subsequence of s 
                 ("coachingding"). It can be shown that appending any 3 
                 characters to the end of s will never make t a subsequence.
    
    Example 2:
    Input: s = "abcde", t = "a"
    Output: 0
    Explanation: t is already a subsequence of s ("abcde").

    Example 3:
    Input: s = "z", t = "abcde"
    Output: 5
    Explanation: Append the characters "abcde" to the end of s so that 
                 s = "zabcde". Now, t is a subsequence of s ("zabcde"). It can 
                 be shown that appending any 4 characters to the end of s will 
                 never make t a subsequence.

    Constraints:
    * 1 <= s.length, t.length <= 10^5
    * s and t consist only of lowercase English letters.*/

    public int appendCharacters(String s, String t) {
        int i = 0; 
        for (char ch : s.toCharArray()) 
            if (i < t.length() && ch == t.charAt(i)) ++i; 
        return t.length()-i; 
    }


    /*2487. Remove Nodes From Linked List (Medium)
    You are given the head of a linked list. Remove every node which has a node 
    with a strictly greater value anywhere to the right side of it. Return the 
    head of the modified linked list.

    Example 1:
    Input: head = [5,2,13,3,8]
    Output: [13,8]
    Explanation: The nodes that should be removed are 5, 2 and 3.
                 - Node 13 is to the right of node 5.
                 - Node 13 is to the right of node 2.
                 - Node 8 is to the right of node 3.
    
    Example 2:
    Input: head = [1,1,1,1]
    Output: [1,1,1,1]
    Explanation: Every node has value 1, so no nodes are removed.

    Constraints:
    * The number of the nodes in the given list is in the range [1, 10^5].
    * 1 <= Node.val <= 10^5*/

    public ListNode removeNodes(ListNode head) {
        ListNode dummy = new ListNode(Integer.MAX_VALUE); 
        Stack<ListNode> stk = new Stack<ListNode>(); stk.push(dummy); 
        for (ListNode node = head; node != null; node = node.next) {
            while (stk.peek().val < node.val) stk.pop(); 
            stk.peek().next = node; 
            stk.push(node); 
        }
        return dummy.next; 
    }


    /*2488. Count Subarrays With Median K (Hard)
    You are given an array nums of size n consisting of distinct integers from 
    1 to n and a positive integer k. Return the number of non-empty subarrays 
    in nums that have a median equal to k.

    Note:
    * The median of an array is the middle element after sorting the array in 
      ascending order. If the array is of even length, the median is the left 
      middle element.
      + For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] 
        is 4.
    * A subarray is a contiguous part of an array.

    Example 1:
    Input: nums = [3,2,1,4,5], k = 4
    Output: 3
    Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] 
                 and [1,4,5].
    
    Example 2:
    Input: nums = [2,3,1], k = 3
    Output: 1
    Explanation: [3] is the only subarray that has a median equal to 3.

    Constraints:
    * n == nums.length
    * 1 <= n <= 10^5
    * 1 <= nums[i], k <= n
    * The integers in nums are distinct.*/

    public int countSubarrays(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(); 
        freq.put(0, 1); 
        int ans = 0, diff = 0; 
        boolean found = false; 
        for (int x : nums) {
            if (x < k) --diff; 
            else if (x > k) ++diff; 
            else found = true; 
            if (found) ans += freq.getOrDefault(diff, 0) + freq.getOrDefault(diff-1, 0); 
            else freq.merge(diff, 1, Integer::sum); 
        }
        return ans; 
    }


    /*2489. Number of Substrings With Fixed Ratio (Medium)
    You are given a binary string s, and two integers num1 and num2. num1 and 
    num2 are coprime numbers. A ratio substring is a substring of s where the 
    ratio between the number of 0's and the number of 1's in the substring is 
    exactly num1 : num2.
    * For example, if num1 = 2 and num2 = 3, then "01011" and "1110000111" are 
      ratio substrings, while "11000" is not.
    Return the number of non-empty ratio substrings of s.

    Note that:
    * A substring is a contiguous sequence of characters within a string.
    * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the 
      greatest common divisor of x and y.

    Example 1:
    Input: s = "0110011", num1 = 1, num2 = 2
    Output: 4
    Explanation: There exist 4 non-empty ratio substrings.
                 - The substring s[0..2]: "0110011". It contains one 0 and two 
                   1's. The ratio is 1 : 2.
                 - The substring s[1..4]: "0110011". It contains one 0 and two 
                   1's. The ratio is 1 : 2.
                 - The substring s[4..6]: "0110011". It contains one 0 and two 
                   1's. The ratio is 1 : 2.
                 - The substring s[1..6]: "0110011". It contains two 0's and 
                   four 1's. The ratio is 2 : 4 == 1 : 2.
                 It can be shown that there are no more ratio substrings.
    
    Example 2:
    Input: s = "10101", num1 = 3, num2 = 1
    Output: 0
    Explanation: There is no ratio substrings of s. We return 0.

    Constraints:
    * 1 <= s.length <= 10^5
    * 1 <= num1, num2 <= s.length
    * num1 and num2 are coprime integers.*/

    public long fixedRatio(String s, int num1, int num2) {
        HashMap<Long, Integer> freq = new HashMap<>(); freq.put(0l, 1); 
        long ans = 0, prefix = 0; 
        for (char ch : s.toCharArray()) {
            if (ch == '0') prefix += num2; 
            else prefix -= num1; 
            ans += freq.getOrDefault(prefix, 0); 
            freq.merge(prefix, 1, Integer::sum); 
        }
        return ans; 
    }


    /*2492. Minimum Score of a Path Between Two Cities (Medium)
    You are given a positive integer n representing n cities numbered from 1 to 
    n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] 
    indicates that there is a bidirectional road between cities ai and bi with 
    a distance equal to distancei. The cities graph is not necessarily 
    connected. The score of a path between two cities is defined as the minimum 
    distance of a road in this path. Return the minimum possible score of a 
    path between cities 1 and n.

    Note:
    * A path is a sequence of roads between two cities.
    * It is allowed for a path to contain the same road multiple times, and you 
      can visit cities 1 and n multiple times along the path.
    * The test cases are generated such that there is at least one path between 
      1 and n.

    Example 1:
    Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
    Output: 5
    Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 
                 4. The score of this path is min(9,5) = 5. It can be shown 
                 that no other path has less score.
    
    Example 2:
    Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
    Output: 2
    Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 
                 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.

    Constraints:
    * 2 <= n <= 10^5
    * 1 <= roads.length <= 10^5
    * roads[i].length == 3
    * 1 <= ai, bi <= n
    * ai != bi
    * 1 <= distancei <= 10^4
    * There are no repeated edges.
    * There is at least one path between 1 and n.*/

    private int find(int p, int[] parent) {
        if (p != parent[p]) parent[p] = find(parent[p], parent); 
        return parent[p]; 
    }
    
    public int minScore(int n, int[][] roads) {
        int[] parent = IntStream.rangeClosed(0, n-1).toArray(); 
        int[] mp = new int[n]; 
        Arrays.fill(mp, Integer.MAX_VALUE); 
        for (int[] r : roads) {
            int u = find(r[0]-1, parent), v = find(r[1]-1, parent); 
            parent[u] = v; 
            mp[u] = mp[v] = Math.min(mp[u], Math.min(mp[v], r[2])); 
        }
        return find(0, parent) == find(n-1, parent) ? mp[find(0, parent)] : -1; 
    }


    /*2495. Number of Subarrays Having Even Product (Medium)
    Given a 0-indexed integer array nums, return the number of subarrays of 
    nums having an even product.

    Example 1:
    Input: nums = [9,6,7,13]
    Output: 6
    Explanation: There are 6 subarrays with an even product:
                 - nums[0..1] = 9 * 6 = 54.
                 - nums[0..2] = 9 * 6 * 7 = 378.
                 - nums[0..3] = 9 * 6 * 7 * 13 = 4914.
                 - nums[1..1] = 6.
                 - nums[1..2] = 6 * 7 = 42.
                 - nums[1..3] = 6 * 7 * 13 = 546.
    
    Example 2:
    Input: nums = [7,3,5]
    Output: 0
    Explanation: There are no subarrays with an even product.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^5*/

    public long evenProduct(int[] nums) {
        long ans = 0; 
        for (int i = 0, val = 0; i < nums.length; ++i) {
            if (nums[i] % 2 == 0) val = i+1; 
            ans += val; 
        }
        return ans; 
    }


    /*2496. Maximum Value of a String in an Array (Easy)
    The value of an alphanumeric string can be defined as:
    * The numeric representation of the string in base 10, if it comprises of 
      digits only.
    * The length of the string, otherwise.
    Given an array strs of alphanumeric strings, return the maximum value of 
    any string in strs.

    Example 1:
    Input: strs = ["alic3","bob","3","4","00000"]
    Output: 5
    Explanation: - "alic3" consists of both letters and digits, so its value is 
                   its length, i.e. 5.
                 - "bob" consists only of letters, so its value is also its 
                   length, i.e. 3.
                 - "3" consists only of digits, so its value is its numeric 
                   equivalent, i.e. 3.
                 - "4" also consists only of digits, so its value is 4.
                 - "00000" consists only of digits, so its value is 0.
                 Hence, the maximum value is 5, of "alic3".
    
    Example 2:
    Input: strs = ["1","01","001","0001"]
    Output: 1
    Explanation: Each string in the array has value 1. Hence, we return 1.

    Constraints:
    * 1 <= strs.length <= 100
    * 1 <= strs[i].length <= 9
    * strs[i] consists of only lowercase English letters and digits.*/

    public int maximumValue(String[] strs) {
        int ans = 0; 
        for (String s : strs) 
            if (s.matches("[0-9]+")) ans = Math.max(ans, Integer.valueOf(s)); 
            else ans = Math.max(ans, s.length()); 
        return ans; 
    }


    /*2497. Maximum Star Sum of a Graph (Medium)
    There is an undirected graph consisting of n nodes numbered from 0 to n - 1. 
    You are given a 0-indexed integer array vals of length n where vals[i] 
    denotes the value of the ith node. You are also given a 2D integer array 
    edges where edges[i] = [ai, bi] denotes that there exists an undirected 
    edge connecting nodes ai and bi. A star graph is a subgraph of the given 
    graph having a center node containing 0 or more neighbors. In other words, 
    it is a subset of edges of the given graph such that there exists a common 
    node for all edges. The image below shows star graphs with 3 and 4 
    neighbors respectively, centered at the blue node. The star sum is the sum 
    of the values of all the nodes present in the star graph. Given an integer 
    k, return the maximum star sum of a star graph containing at most k edges.

    Example 1:
    Input: vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
    Output: 16
    Explanation: The above diagram represents the input graph. The star graph 
                 with the maximum star sum is denoted by blue. It is centered 
                 at 3 and includes its neighbors 1 and 4. It can be shown it is 
                 not possible to get a star graph with a sum greater than 16.
    
    Example 2:
    Input: vals = [-5], edges = [], k = 0
    Output: -5
    Explanation: There is only one possible star graph, which is node 0 itself. 
                 Hence, we return -5.

    Constraints:
    * n == vals.length
    * 1 <= n <= 10^5
    * -10^4 <= vals[i] <= 10^4
    * 0 <= edges.length <= min(n * (n - 1) / 2, 10^5)
    * edges[i].length == 2
    * 0 <= ai, bi <= n - 1
    * ai != bi
    * 0 <= k <= n - 1*/

    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length; 
        List<Integer>[] graph = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) graph[u] = new ArrayList(); 
        for (int[] e : edges) {
            graph[e[0]].add(e[1]); 
            graph[e[1]].add(e[0]); 
        }
        int ans = Integer.MIN_VALUE; 
        for (int u = 0; u < n; ++u) {
            int cand = vals[u]; 
            if (graph[u].size() > k) Collections.sort(graph[u], (a, b) -> vals[b] - vals[a]);
            for (int v = 0; v < k && v < graph[u].size(); ++v)
                cand += Math.max(0, vals[graph[u].get(v)]); 
            ans = Math.max(ans, cand); 
        }
        return ans; 
    }


    /*2498. Frog Jump II (Medium)
    You are given a 0-indexed integer array stones sorted in strictly 
    increasing order representing the positions of stones in a river. A frog, 
    initially on the first stone, wants to travel to the last stone and then 
    return to the first stone. However, it can jump to any stone at most once.
    The length of a jump is the absolute difference between the position of the 
    stone the frog is currently on and the position of the stone to which the 
    frog jumps. More formally, if the frog is at stones[i] and is jumping to 
    stones[j], the length of the jump is |stones[i] - stones[j]|. The cost of a 
    path is the maximum length of a jump among all jumps in the path. Return 
    the minimum cost of a path for the frog.

    Example 1:
    Input: stones = [0,2,5,6,7]
    Output: 5
    Explanation: The above figure represents one of the optimal paths the frog 
                 can take. The cost of this path is 5, which is the maximum 
                 length of a jump. Since it is not possible to achieve a cost 
                 of less than 5, we return it.
    
    Example 2:
    Input: stones = [0,3,9]
    Output: 9
    Explanation: The frog can jump directly to the last stone and come back to 
                 the first stone. In this case, the length of each jump will be 
                 9. The cost for the path will be max(9, 9) = 9. It can be 
                 shown that this is the minimum achievable cost.

    Constraints:
    * 2 <= stones.length <= 10^5
    * 0 <= stones[i] <= 10^9
    * stones[0] == 0
    * stones is sorted in a strictly increasing order.*/

    public int maxJump(int[] stones) {
        int ans = stones[1]; 
        for (int i = 2; i < stones.length; ++i) 
            ans = Math.max(ans, stones[i] - stones[i-2]); 
        return ans; 
    }


    /*2499. Minimum Total Cost to Make Arrays Unequal (Hard)
    You are given two 0-indexed integer arrays nums1 and nums2, of equal length 
    n. In one operation, you can swap the values of any two indices of nums1. 
    The cost of this operation is the sum of the indices. Find the minimum 
    total cost of performing the given operation any number of times such that 
    nums1[i] != nums2[i] for all 0 <= i <= n - 1 after performing all the 
    operations. Return the minimum total cost such that nums1 and nums2 satisfy 
    the above condition. In case it is not possible, return -1.

    Example 1:
    Input: nums1 = [1,2,3,4,5], nums2 = [1,2,3,4,5]
    Output: 10
    Explanation: One of the ways we can perform the operations is:
                 - Swap values at indices 0 and 3, incurring cost = 0 + 3 = 3. 
                   Now, nums1 = [4,2,3,1,5]
                 - Swap values at indices 1 and 2, incurring cost = 1 + 2 = 3. 
                   Now, nums1 = [4,3,2,1,5].
                 - Swap values at indices 0 and 4, incurring cost = 0 + 4 = 4. 
                   Now, nums1 =[5,3,2,1,4].
                 We can see that for each index i, nums1[i] != nums2[i]. The 
                 cost required here is 10. Note that there are other ways to 
                 swap values, but it can be proven that it is not possible to 
                 obtain a cost less than 10.
    
    Example 2:
    Input: nums1 = [2,2,2,1,3], nums2 = [1,2,2,3,3]
    Output: 10
    Explanation: One of the ways we can perform the operations is:
                 - Swap values at indices 2 and 3, incurring cost = 2 + 3 = 5. 
                   Now, nums1 = [2,2,1,2,3].
                 - Swap values at indices 1 and 4, incurring cost = 1 + 4 = 5. 
                   Now, nums1 = [2,3,1,2,2].
                 The total cost needed here is 10, which is the minimum 
                 possible.
    
    Example 3:
    Input: nums1 = [1,2,2], nums2 = [1,2,2]
    Output: -1
    Explanation: It can be shown that it is not possible to satisfy the given 
                 conditions irrespective of the number of operations we 
                 perform. Hence, we return -1.

    Constraints:
    * n == nums1.length == nums2.length
    * 1 <= n <= 10^5
    * 1 <= nums1[i], nums2[i] <= n*/

    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length, total = 0; 
        int[] freq = new int[n+1]; 
        long ans = 0; 
        for (int i = 0; i < n; ++i) 
            if (nums1[i] == nums2[i]) {
                ++freq[nums1[i]]; 
                ++total; 
                ans += i; 
            }
        int most = 0, key = 0; 
        for (int i = 0; i <= n; ++i) 
            if (freq[i] > most) {
                key = i; 
                most = freq[i]; 
            }
        for (int i = 0; i < n && 2*most > total; ++i) 
            if (nums1[i] != nums2[i] && key != nums1[i] && key != nums2[i]) {
                ++total; 
                ans += i; 
            }
        return 2*most <= total ? ans : -1; 
    }


    /*2500. Delete Greatest Value in Each Row (Easy)
    You are given an m x n matrix grid consisting of positive integers. Perform 
    the following operation until grid becomes empty:
    * Delete the element with the greatest value from each row. If multiple 
      such elements exist, delete any of them.
    * Add the maximum of deleted elements to the answer.
    Note that the number of columns decreases by one after each operation. 
    Return the answer after performing the operations described above.

    Example 1:
    Input: grid = [[1,2,4],[3,3,1]]
    Output: 8
    Explanation: The diagram above shows the removed values in each step.
                 - In the first operation, we remove 4 from the first row and 3 
                   from the second row (notice that, there are two cells with 
                   value 3 and we can remove any of them). We add 4 to the 
                   answer.
                 - In the second operation, we remove 2 from the first row and 
                   3 from the second row. We add 3 to the answer.
                 - In the third operation, we remove 1 from the first row and 1 
                   from the second row. We add 1 to the answer.
                 The final answer = 4 + 3 + 1 = 8.
    
    Example 2:
    Input: grid = [[10]]
    Output: 10
    Explanation: The diagram above shows the removed values in each step.
                 - In the first operation, we remove 10 from the first row. We 
                   add 10 to the answer.
                 The final answer = 10.

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 1 <= m, n <= 50
    * 1 <= grid[i][j] <= 100*/

    public int deleteGreatestValue(int[][] grid) {
        for (int[] row : grid) Arrays.sort(row); 
        int ans = 0; 
        for (int j = 0; j < grid[0].length; ++j) {
            int cand = 0; 
            for (int i = 0; i < grid.length; ++i) 
                cand = Math.max(cand, grid[i][j]); 
            ans += cand; 
        }
        return ans; 
    }


    /*2501. Longest Square Streak in an Array (Medium)
    You are given an integer array nums. A subsequence of nums is called a 
    square streak if:
    * The length of the subsequence is at least 2, and
    * after sorting the subsequence, each element (except the first element) is 
      the square of the previous number.
    Return the length of the longest square streak in nums, or return -1 if 
    there is no square streak. A subsequence is an array that can be derived 
    from another array by deleting some or no elements without changing the 
    order of the remaining elements.

    Example 1:
    Input: nums = [4,3,6,16,8,2]
    Output: 3
    Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes 
                 [2,4,16].
                 - 4 = 2 * 2.
                 - 16 = 4 * 4.
                 Therefore, [4,16,2] is a square streak. It can be shown that 
                 every subsequence of length 4 is not a square streak.
    
    Example 2:
    Input: nums = [2,3,5,6,7]
    Output: -1
    Explanation: There is no square streak in nums so return -1.

    Constraints:
    * 2 <= nums.length <= 10^5
    * 2 <= nums[i] <= 10^5*/

    public int longestSquareStreak(int[] nums) {
        int[] dp = new int[100_001]; 
        Arrays.sort(nums); 
        int ans = 0; 
        for (int x : nums) {
            dp[x] = Math.max(1, dp[x]); 
            int v = (int) Math.sqrt(x); 
            if (v*v == x) dp[x] = 1 + dp[v]; 
            ans = Math.max(ans, dp[x]); 
        }
        return ans > 1 ? ans : -1; 
    }


    /*2503. Maximum Number of Points From Grid Queries (Hard)
    You are given an m x n integer matrix grid and an array queries of size k.
    Find an array answer of size k such that for each integer queres[i] you 
    start in the top left cell of the matrix and repeat the following process:
    * If queries[i] is strictly greater than the value of the current cell that 
      you are in, then you get one point if it is your first time visiting this 
      cell, and you can move to any adjacent cell in all 4 directions: up, down, 
      left, and right.
    * Otherwise, you do not get any points, and you end this process.
    After the process, answer[i] is the maximum number of points you can get. 
    Note that for each query you are allowed to visit the same cell multiple 
    times. Return the resulting array answer.

    Example 1:
    Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
    Output: [5,8,1]
    Explanation: The diagrams above show which cells we visit to get points for 
                 each query.

    Example 2:
    Input: grid = [[5,2,1],[1,1,2]], queries = [3]
    Output: [0]
    Explanation: We can not get any points because the value of the top left 
                 cell is already greater than or equal to 3.

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 2 <= m, n <= 1000
    * 4 <= m * n <= 10^5
    * k == queries.length
    * 1 <= k <= 10^4
    * 1 <= grid[i][j], queries[i] <= 10^6*/

    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length, prev = Integer.MIN_VALUE, prefix = 0; 
        int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; 
        Queue<int[]> pq = new PriorityQueue<>((a, b)->(a[0]-b[0])); 
        pq.add(new int[]{grid[0][0], 0, 0}); 
        grid[0][0] = 0; 
        List<Integer> keys = new ArrayList(); 
        List<Integer> vals = new ArrayList(); 
        while (pq.size() > 0) {
            int[] elem = pq.remove(); 
            int v = elem[0], i = elem[1], j = elem[2]; 
            if (prev != v) {
                keys.add(prev); 
                vals.add(prefix); 
            }
            ++prefix; 
            prev = v; 
            for (var d : dir) {
                int ii = i + d[0], jj = j + d[1]; 
                if (0 <= ii && ii < m && 0 <= jj && jj < n && grid[ii][jj] > 0) {
                    int vv = Math.max(v, grid[ii][jj]); 
                    pq.add(new int[]{vv, ii, jj}); 
                    grid[ii][jj] = 0; 
                }
            }
        } 
        keys.add(prev); 
        vals.add(prefix); 
        int sz = queries.length; 
        int[] ans = new int[sz]; 
        for (int i = 0; i < sz; ++i) {
            int k = Collections.binarySearch(keys, queries[i]);
            if (k < 0) k = -k-1; 
            ans[i] = vals.get(k-1); 
        }
        return ans; 
    }


    /*2505. Bitwise OR of All Subsequence Sums (Medium)
    Given an integer array nums, return the value of the bitwise OR of the sum 
    of all possible subsequences in the array. A subsequence is a sequence that 
    can be derived from another sequence by removing zero or more elements 
    without changing the order of the remaining elements.

    Example 1:
    Input: nums = [2,1,0,3]
    Output: 7
    Explanation: All possible subsequence sums that we can have are: 
                 0, 1, 2, 3, 4, 5, 6. And we have 
                 0 OR 1 OR 2 OR 3 OR 4 OR 5 OR 6 = 7, so we return 7.
    
    Example 2:
    Input: nums = [0,0,0]
    Output: 0
    Explanation: 0 is the only possible subsequence sum we can have, so we 
                 return 0.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^9*/

    public long subsequenceSumOr(int[] nums) {
        long ans = 0, prefix = 0; 
        for (var x : nums) {
            prefix += x; 
            ans |= x | prefix; 
        }
        return ans; 
    }


    /*2506. Count Pairs Of Similar Strings (Easy)
    You are given a 0-indexed string array words. Two strings are similar if 
    they consist of the same characters.
    * For example, "abca" and "cba" are similar since both consist of 
      characters 'a', 'b', and 'c'.
    * However, "abacba" and "bcfd" are not similar since they do not consist of 
      the same characters.
    Return the number of pairs (i, j) such that 0 <= i < j <= word.length - 1 
    and the two strings words[i] and words[j] are similar.

    Example 1:
    Input: words = ["aba","aabb","abcd","bac","aabc"]
    Output: 2
    Explanation: There are 2 pairs that satisfy the conditions:
                 - i = 0 and j = 1 : both words[0] and words[1] only consist of 
                   characters 'a' and 'b'. 
                 - i = 3 and j = 4 : both words[3] and words[4] only consist of 
                   characters 'a', 'b', and 'c'. 
    
    Example 2:
    Input: words = ["aabb","ab","ba"]
    Output: 3
    Explanation: There are 3 pairs that satisfy the conditions:
                 - i = 0 and j = 1 : both words[0] and words[1] only consist of 
                   characters 'a' and 'b'. 
                 - i = 0 and j = 2 : both words[0] and words[2] only consist of 
                   characters 'a' and 'b'.
                 - i = 1 and j = 2 : both words[1] and words[2] only consist of 
                   characters 'a' and 'b'.
    
    Example 3:
    Input: words = ["nba","cba","dba"]
    Output: 0
    Explanation: Since there does not exist any pair that satisfies the 
                 conditions, we return 0.

    Constraints:
    * 1 <= words.length <= 100
    * 1 <= words[i].length <= 100
    * words[i] consist of only lowercase English letters.*/

    public int similarPairs(String[] words) {
        int ans = 0; 
        HashMap<Integer, Integer> freq = new HashMap(); 
        for (var word : words) {
            int mask = 0; 
            for (var ch : word.toCharArray()) 
                mask |= 1<<ch-'a'; 
            ans += freq.getOrDefault(mask, 0); 
            freq.merge(mask, 1, Integer::sum); 
        }
        return ans; 
    }


    /*2507. Smallest Value After Replacing With Sum of Prime Factors (Medium)
    You are given a positive integer n. Continuously replace n with the sum of 
    its prime factors. 
    * Note that if a prime factor divides n multiple times, it should be 
      included in the sum as many times as it divides n.
    Return the smallest value n will take on.

    Example 1:
    Input: n = 15
    Output: 5
    Explanation: Initially, n = 15.
                 15 = 3 * 5, so replace n with 3 + 5 = 8.
                 8 = 2 * 2 * 2, so replace n with 2 + 2 + 2 = 6.
                 6 = 2 * 3, so replace n with 2 + 3 = 5.
                 5 is the smallest value n will take on.
    
    Example 2:
    Input: n = 3
    Output: 3
    Explanation: Initially, n = 3. 3 is the smallest value n will take on.

    Constraints: 2 <= n <= 10^5*/

    public int smallestValue(int n) {
        while (true) {
            int sm = 0; 
            for (int f = 2, nn = n; f <= nn; ++f)
                for (; nn % f == 0; nn /= f, sm += f); 
            if (sm == n) break; 
            n = sm; 
        }
        return n; 
    }


    /*2508. Add Edges to Make Degrees of All Nodes Even (Hard)
    There is an undirected graph consisting of n nodes numbered from 1 to n. 
    You are given the integer n and a 2D array edges where edges[i] = [ai, bi] 
    indicates that there is an edge between nodes ai and bi. The graph can be 
    disconnected. You can add at most two additional edges (possibly none) to 
    this graph so that there are no repeated edges and no self-loops. Return 
    true if it is possible to make the degree of each node in the graph even, 
    otherwise return false. The degree of a node is the number of edges 
    connected to it.

    Example 1:
    Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
    Output: true
    Explanation: The above diagram shows a valid way of adding an edge. Every 
                 node in the resulting graph is connected to an even number of 
                 edges.
    
    Example 2:
    Input: n = 4, edges = [[1,2],[3,4]]
    Output: true
    Explanation: The above diagram shows a valid way of adding two edges.

    Example 3:
    Input: n = 4, edges = [[1,2],[1,3],[1,4]]
    Output: false
    Explanation: It is not possible to obtain a valid graph with adding at most 
                 2 edges.

    Constraints:
    * 3 <= n <= 10^5
    * 2 <= edges.length <= 10^5
    * edges[i].length == 2
    * 1 <= ai, bi <= n
    * ai != bi
    * There are no repeated edges.*/

    public boolean isPossible(int n, List<List<Integer>> edges) {
        HashSet<Integer>[] graph = new HashSet[n]; 
        for (int i = 0; i < n; ++i) graph[i] = new HashSet(); 
        for (var e : edges) {
            graph[e.get(0)-1].add(e.get(1)-1); 
            graph[e.get(1)-1].add(e.get(0)-1); 
        }
        List<Integer> odd = new ArrayList(); 
        for (int i = 0; i < n; ++i) 
            if (graph[i].size() % 2 == 1) odd.add(i); 
        if (odd.size() == 2) {
            for (int i = 0; i < n; ++i) 
                if (!graph[i].contains(odd.get(0)) && !graph[i].contains(odd.get(1))) return true; 
            return false; 
        }
        if (odd.size() == 4) 
            return !graph[odd.get(0)].contains(odd.get(1)) && !graph[odd.get(2)].contains(odd.get(3)) 
                || !graph[odd.get(0)].contains(odd.get(2)) && !graph[odd.get(1)].contains(odd.get(3)) 
                || !graph[odd.get(0)].contains(odd.get(3)) && !graph[odd.get(1)].contains(odd.get(2)); 
        return odd.size() == 0; 
    }


    /*2509. Cycle Length Queries in a Tree (Hard)
    You are given an integer n. There is a complete binary tree with 2n - 1 
    nodes. The root of that tree is the node with the value 1, and every node 
    with a value val in the range [1, 2n - 1 - 1] has two children where:
    * The left node has the value 2 * val, and
    * The right node has the value 2 * val + 1.
    You are also given a 2D integer array queries of length m, where 
    queries[i] = [ai, bi]. For each query, solve the following problem:
    * Add an edge between the nodes with values ai and bi.
    * Find the length of the cycle in the graph.
    * Remove the added edge between nodes with values ai and bi.
    Note that:
    * A cycle is a path that starts and ends at the same node, and each edge in 
      the path is visited only once.
    * The length of a cycle is the number of edges visited in the cycle.
    * There could be multiple edges between two nodes in the tree after adding 
      the edge of the query.
    Return an array answer of length m where answer[i] is the answer to the ith 
    query.

    Example 1:
    Input: n = 3, queries = [[5,3],[4,7],[2,3]]
    Output: [4,5,3]
    Explanation: The diagrams above show the tree of 23 - 1 nodes. Nodes 
                 colored in red describe the nodes in the cycle after adding 
                 the edge.
                 - After adding the edge between nodes 3 and 5, the graph 
                   contains a cycle of nodes [5,2,1,3]. Thus answer to the 
                   first query is 4. We delete the added edge and process the 
                   next query.
                 - After adding the edge between nodes 4 and 7, the graph 
                   contains a cycle of nodes [4,2,1,3,7]. Thus answer to the 
                   second query is 5. We delete the added edge and process the 
                   next query.
                 - After adding the edge between nodes 2 and 3, the graph 
                   contains a cycle of nodes [2,1,3]. Thus answer to the third 
                   query is 3. We delete the added edge.
    
    Example 2:
    Input: n = 2, queries = [[1,2]]
    Output: [2]
    Explanation: The diagram above shows the tree of 22 - 1 nodes. Nodes 
                 colored in red describe the nodes in the cycle after adding 
                 the edge.
                 - After adding the edge between nodes 1 and 2, the graph 
                   contains a cycle of nodes [2,1]. Thus answer for the first 
                   query is 2. We delete the added edge.

    Constraints:
    * 2 <= n <= 30
    * m == queries.length
    * 1 <= m <= 10^5
    * queries[i].length == 2
    * 1 <= ai, bi <= 2n - 1
    * ai != bi*/

    public int[] cycleLengthQueries(int n, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int dist = 1; 
            for (int u = queries[i][0], v = queries[i][1]; u != v; u /= 2, ++dist) 
                if (u < v) { int tmp = v; v = u; u = tmp; }
            ans[i] = dist; 
        }
        return ans;  
    }


    /*2510. Check if There is a Path With Equal Number of 0's And 1's (Medium)
    You are given a 0-indexed m x n binary matrix grid. You can move from a 
    cell (row, col) to any of the cells (row + 1, col) or (row, col + 1). 
    Return true if there is a path from (0, 0) to (m - 1, n - 1) that visits an 
    equal number of 0's and 1's. Otherwise return false.

    Example 1:
    Input: grid = [[0,1,0,0],[0,1,0,0],[1,0,1,0]]
    Output: true
    Explanation: The path colored in blue in the above diagram is a valid path 
                 because we have 3 cells with a value of 1 and 3 with a value 
                 of 0. Since there is a valid path, we return true.
    
    Example 2:
    Input: grid = [[1,1,0],[0,0,1],[1,0,0]]
    Output: false
    Explanation: There is no path in this grid with an equal number of 0's and 
                 1's.

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 2 <= m, n <= 100
    * grid[i][j] is either 0 or 1.*/

    public boolean isThereAPath(int[][] grid) {
        int m = grid.length, n = grid[0].length; 
        if ((m+n) % 2 == 1) {
            int[][] lo = new int[m][n], hi = new int[m][n]; 
            Arrays.stream(lo).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));
            Arrays.stream(hi).forEach(a -> Arrays.fill(a, Integer.MIN_VALUE));
            lo[0][0] = hi[0][0] = 2*grid[0][0]-1; 
            for (int i = 0; i < m; ++i) 
                for (int j = 0; j < n; ++j) {
                    if (i > 0) {
                        lo[i][j] = Math.min(lo[i][j], lo[i-1][j] + 2*grid[i][j]-1); 
                        hi[i][j] = Math.max(hi[i][j], hi[i-1][j] + 2*grid[i][j]-1); 
                    }
                    if (j > 0) {
                        lo[i][j] = Math.min(lo[i][j], lo[i][j-1] + 2*grid[i][j]-1); 
                        hi[i][j] = Math.max(hi[i][j], hi[i][j-1] + 2*grid[i][j]-1); 
                    }
                }
            return lo[m-1][n-1] <= 0 && 0 <= hi[m-1][n-1]; 
        }
        return false; 
    }


    /*2511. Maximum Enemy Forts That Can Be Captured (Easy)
    You are given a 0-indexed integer array forts of length n representing the 
    positions of several forts. forts[i] can be -1, 0, or 1 where:
    * -1 represents there is no fort at the ith position.
    * 0 indicates there is an enemy fort at the ith position.
    * 1 indicates the fort at the ith the position is under your command.
    Now you have decided to move your army from one of your forts at position i 
    to an empty position j such that:
    * 0 <= i, j <= n - 1
    * The army travels over enemy forts only. Formally, for all k where 
      min(i,j) < k < max(i,j), forts[k] == 0.
    While moving the army, all the enemy forts that come in the way are 
    captured. Return the maximum number of enemy forts that can be captured. In 
    case it is impossible to move your army, or you do not have any fort under 
    your command, return 0.

    Example 1:
    Input: forts = [1,0,0,-1,0,0,0,0,1]
    Output: 4
    Explanation: - Moving the army from position 0 to position 3 captures 2 
                   enemy forts, at 1 and 2.
                 - Moving the army from position 8 to position 3 captures 4 
                   enemy forts.
                 Since 4 is the maximum number of enemy forts that can be 
                 captured, we return 4.
    
    Example 2:
    Input: forts = [0,0,1,-1]
    Output: 0
    Explanation: Since no enemy fort can be captured, 0 is returned.

    Constraints:
    * 1 <= forts.length <= 1000
    * -1 <= forts[i] <= 1*/

    public int captureForts(int[] forts) {
        int ans = 0; 
        for (int i = 0, ii = 0; i < forts.length; ++i) 
            if (forts[i] != 0) {
                if (forts[ii] == -forts[i]) ans = Math.max(ans, i-ii-1); 
                ii = i; 
            }
        return ans; 
    }


    /*2512. Reward Top K Students (Medium)
    You are given two string arrays positive_feedback and negative_feedback, 
    containing the words denoting positive and negative feedback, respectively. 
    Note that no word is both positive and negative. Initially every student 
    has 0 points. Each positive word in a feedback report increases the points 
    of a student by 3, whereas each negative word decreases the points by 1. 
    You are given n feedback reports, represented by a 0-indexed string array 
    report and a 0-indexed integer array student_id, where student_id[i] 
    represents the ID of the student who has received the feedback report 
    report[i]. The ID of each student is unique. Given an integer k, return 
    the top k students after ranking them in non-increasing order by their 
    points. In case more than one student has the same points, the one with the 
    lower ID ranks higher.

    Example 1:
    Input: positive_feedback = ["smart","brilliant","studious"], 
           negative_feedback = ["not"], 
           report = ["this student is studious","the student is smart"], 
           student_id = [1,2], k = 2
    Output: [1,2]
    Explanation: Both the students have 1 positive feedback and 3 points but 
                 since student 1 has a lower ID he ranks higher.
    
    Example 2:
    Input: positive_feedback = ["smart","brilliant","studious"], 
           negative_feedback = ["not"], 
           report = ["this student is not studious","the student is smart"], 
           student_id = [1,2], k = 2
    Output: [2,1]
    Explanation: - The student with ID 1 has 1 positive feedback and 1 negative 
                   feedback, so he has 3-1=2 points. 
                 - The student with ID 2 has 1 positive feedback, so he has 3 
                   points. 
                 Since student 2 has more points, [2,1] is returned.

    Constraints:
    * 1 <= positive_feedback.length, negative_feedback.length <= 10^4
    * 1 <= positive_feedback[i].length, negative_feedback[j].length <= 100
    * Both positive_feedback[i] and negative_feedback[j] consists of lowercase 
      English letters.
    * No word is present in both positive_feedback and negative_feedback.
    * n == report.length == student_id.length
    * 1 <= n <= 10^4
    * report[i] consists of lowercase English letters and spaces ' '.
    * There is a single space between consecutive words of report[i].
    * 1 <= report[i].length <= 100
    * 1 <= student_id[i] <= 10^9
    * All the values of student_id[i] are unique.
    * 1 <= k <= n*/

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Set<String> positive = new HashSet(Arrays.asList(positive_feedback)), negative = new HashSet(Arrays.asList(negative_feedback)); 
        Map<Integer, Integer> mp = new HashMap(); 
        for (int i = 0; i < report.length; ++i) {
            int point = 0; 
            for (var word : report[i].split(" ")) {
                if (positive.contains(word)) point += 3; 
                else if (negative.contains(word)) --point; 
            }
            mp.put(student_id[i], point); 
        }
        List<Integer> vals = new ArrayList(); 
        for (var elem : mp.entrySet()) vals.add(elem.getKey()); 
        Collections.sort(vals, (a, b)->(mp.get(a) != mp.get(b) ? Integer.compare(mp.get(b), mp.get(a)) : Integer.compare(a, b))); 
        List<Integer> ans = new ArrayList(); 
        for (int i = 0; i < k; ++i) ans.add(vals.get(i)); 
        return ans; 
    }


    /*2513. Minimize the Maximum of Two Arrays (Medium)
    We have two arrays arr1 and arr2 which are initially empty. You need to add 
    positive integers to them such that they satisfy all the following 
    conditions:
    * arr1 contains uniqueCnt1 distinct positive integers, each of which is not 
      divisible by divisor1.
    * arr2 contains uniqueCnt2 distinct positive integers, each of which is not 
      divisible by divisor2.
    * No integer is present in both arr1 and arr2.
    Given divisor1, divisor2, uniqueCnt1, and uniqueCnt2, return the minimum 
    possible maximum integer that can be present in either array.

    Example 1:
    Input: divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
    Output: 4
    Explanation: We can distribute the first 4 natural numbers into arr1 and 
                 arr2. arr1 = [1] and arr2 = [2,3,4]. We can see that both 
                 arrays satisfy all the conditions. Since the maximum value is 
                 4, we return it.
    
    Example 2:
    Input: divisor1 = 3, divisor2 = 5, uniqueCnt1 = 2, uniqueCnt2 = 1
    Output: 3
    Explanation: Here arr1 = [1,2], and arr2 = [3] satisfy all conditions. 
                 Since the maximum value is 3, we return it.
    
    Example 3:
    Input: divisor1 = 2, divisor2 = 4, uniqueCnt1 = 8, uniqueCnt2 = 2
    Output: 15
    Explanation: Here, the final possible arrays can be 
                 arr1 = [1,3,5,7,9,11,13,15], and arr2 = [2,6]. It can be shown 
                 that it is not possible to obtain a lower maximum satisfying 
                 all conditions. 

    Constraints:
    * 2 <= divisor1, divisor2 <= 10^5
    * 1 <= uniqueCnt1, uniqueCnt2 < 10^9
    * 2 <= uniqueCnt1 + uniqueCnt2 <= 10^9*/

    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        int g = divisor1; 
        for (int x = divisor2; x > 0; ) {int tmp = g; g = x; x = tmp % x; }
        long lo = 0, hi = Integer.MAX_VALUE, mult = ((long) divisor1*divisor2/g); 
        while (lo < hi) {
            long mid = lo + (hi-lo)/2;
            if (uniqueCnt1 <= mid-mid/divisor1 && uniqueCnt2 <= mid-mid/divisor2 && uniqueCnt1+uniqueCnt2 <= mid-mid/mult) hi = mid; 
            else lo = mid+1; 
        }
        return (int) lo; 
    }


    /*2514. Count Anagrams (Hard)
    You are given a string s containing one or more words. Every consecutive 
    pair of words is separated by a single space ' '. A string t is an anagram 
    of string s if the ith word of t is a permutation of the ith word of s.
    * For example, "acb dfe" is an anagram of "abc def", but "def cab" and 
      "adc bef" are not.
    Return the number of distinct anagrams of s. Since the answer may be very 
    large, return it modulo 10^9 + 7.

    Example 1:
    Input: s = "too hot"
    Output: 18
    Explanation: Some of the anagrams of the given string are "too hot", 
                 "oot hot", "oto toh", "too toh", and "too oht".
    
    Example 2:
    Input: s = "aa"
    Output: 1
    Explanation: There is only one anagram possible for the given string.

    Constraints:
    * 1 <= s.length <= 10^5
    * s consists of lowercase English letters and spaces ' '.
    * There is single space between consecutive words.*/

    public int countAnagrams(String s) {
        final int mod = 1_000_000_007; 
        int n = s.length(); 
        long[] fact = new long[n+1], ifact = new long[n+1], inv = new long[n+1]; 
        fact[0] = ifact[0] = inv[0] = inv[1] = 1; 
        for (int x = 1; x <= n; ++x) {
            if (x >= 2) inv[x] = mod - mod/x * inv[mod%x] % mod; 
            fact[x] = fact[x-1] * x % mod; 
            ifact[x] = ifact[x-1] * inv[x] % mod; 
        }
        long ans = 1; 
        for (var word : s.split(" ")) {
            ans = ans * fact[word.length()] % mod; 
            int[] freq = new int[26]; 
            for (var ch : word.toCharArray())  ++freq[ch-'a']; 
            for (var x : freq) ans = ans * ifact[x] % mod; 
        }
        return (int) ans; 
    }


    /*2515. Shortest Distance to Target String in a Circular Array (Easy)
    You are given a 0-indexed circular string array words and a string target. 
    A circular array means that the array's end connects to the array's 
    beginning.
    * Formally, the next element of words[i] is words[(i + 1) % n] and the 
      previous element of words[i] is words[(i - 1 + n) % n], where n is the 
      length of words.
    Starting from startIndex, you can move to either the next word or the 
    previous word with 1 step at a time. Return the shortest distance needed to 
    reach the string target. If the string target does not exist in words, 
    return -1.

    Example 1:
    Input: words = ["hello","i","am","leetcode","hello"], target = "hello", startIndex = 1
    Output: 1
    Explanation: We start from index 1 and can reach "hello" by
                 - moving 3 units to the right to reach index 4.
                 - moving 2 units to the left to reach index 4.
                 - moving 4 units to the right to reach index 0.
                 - moving 1 unit to the left to reach index 0.
                 The shortest distance to reach "hello" is 1.
    
    Example 2:
    Input: words = ["a","b","leetcode"], target = "leetcode", startIndex = 0
    Output: 1
    Explanation: We start from index 0 and can reach "leetcode" by
                 - moving 2 units to the right to reach index 3.
                 - moving 1 unit to the left to reach index 3.
                 The shortest distance to reach "leetcode" is 1.
    
    Example 3:
    Input: words = ["i","eat","leetcode"], target = "ate", startIndex = 0
    Output: -1
    Explanation: Since "ate" does not exist in words, we return -1.

    Constraints:
    * 1 <= words.length <= 100
    * 1 <= words[i].length <= 100
    * words[i] and target consist of only lowercase English letters.
    * 0 <= startIndex < words.length*/

    public int closetTarget(String[] words, String target, int startIndex) {
        int ans = Integer.MAX_VALUE; 
        for (int i = 0, n = words.length; i < n; ++i) 
            if (words[i].equals(target)) {
                int cand = Math.abs(i - startIndex); 
                ans = Math.min(ans, Math.min(cand, n - cand)); 
            }
        return ans < Integer.MAX_VALUE ? ans : -1; 
    }


    /*2516. Take K of Each Character From Left and Right (Medium)
    You are given a string s consisting of the characters 'a', 'b', and 'c' and 
    a non-negative integer k. Each minute, you may take either the leftmost 
    character of s, or the rightmost character of s. Return the minimum number 
    of minutes needed for you to take at least k of each character, or return 
    -1 if it is not possible to take k of each character.

    Example 1:
    Input: s = "aabaaaacaabc", k = 2
    Output: 8
    Explanation: Take three characters from the left of s. You now have two 'a' 
                 characters, and one 'b' character. Take five characters from 
                 the right of s. You now have four 'a' characters, two 'b' 
                 characters, and two 'c' characters. A total of 3 + 5 = 8 
                 minutes is needed. It can be proven that 8 is the minimum 
                 number of minutes needed.
    
    Example 2:
    Input: s = "a", k = 1
    Output: -1
    Explanation: It is not possible to take one 'b' or 'c' so return -1.

    Constraints:
    * 1 <= s.length <= 10^5
    * s consists of only the letters 'a', 'b', and 'c'.
    * 0 <= k <= s.length*/

    public int takeCharacters(String s, int k) {
        int ans = Integer.MAX_VALUE; 
        int[] freq= new int[3]; 
        for (int i = 0, ii = 0, n = s.length(); i < 2*n; ++i) {
            ++freq[s.charAt(i % n) - 'a']; 
            while (ii < n && i >= n-1 && freq[s.charAt(ii)-'a'] > k && freq[0] >= k && freq[1] >= k && freq[2] >= k) {
                ans = Math.min(ans, i-ii); 
                --freq[s.charAt(ii++) - 'a']; 
            }
        }
        return ans <= s.length() ? ans : -1; 
    }


    /*2517. Maximum Tastiness of Candy Basket (Medium)
    You are given an array of positive integers price where price[i] denotes 
    the price of the ith candy and a positive integer k. The store sells 
    baskets of k distinct candies. The tastiness of a candy basket is the 
    smallest absolute difference of the prices of any two candies in the basket.
    Return the maximum tastiness of a candy basket.

    Example 1:
    Input: price = [13,5,1,8,21,2], k = 3
    Output: 8
    Explanation: Choose the candies with the prices [13,5,21]. The tastiness of 
                 the candy basket is: 
                 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8. It can 
                 be proven that 8 is the maximum tastiness that can be achieved.
    
    Example 2:
    Input: price = [1,3,1], k = 2
    Output: 2
    Explanation: Choose the candies with the prices [1,3]. The tastiness of the 
                 candy basket is: min(|1 - 3|) = min(2) = 2. It can be proven 
                 that 2 is the maximum tastiness that can be achieved.
    
    Example 3:
    Input: price = [7,7,7,7], k = 2
    Output: 0
    Explanation: Choosing any two distinct candies from the candies we have 
                 will result in a tastiness of 0.

    Constraints:
    * 1 <= price.length <= 10^5
    * 1 <= price[i] <= 10^9
    * 2 <= k <= price.length*/

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price); 
        int n = price.length; 
        int lo = 0, hi = price[n-1] - price[0]; 
        while (lo < hi) {
            int mid = lo + (hi-lo+1)/2, val = price[0], cnt = 0; 
            for (var x : price) 
                if (x >= val + mid) { ++cnt; val = x; }
            if (cnt >= k-1) lo = mid; 
            else hi = mid-1; 
        }
        return lo; 
    }


    /*2518. Number of Great Partitions (Hard)
    You are given an array nums consisting of positive integers and an integer 
    k. Partition the array into two ordered groups such that each element is in 
    exactly one group. A partition is called great if the sum of elements of 
    each group is greater than or equal to k. Return the number of distinct 
    great partitions. Since the answer may be too large, return it modulo 
    10^9 + 7. Two partitions are considered distinct if some element nums[i] is 
    in different groups in the two partitions.

    Example 1:
    Input: nums = [1,2,3,4], k = 4
    Output: 6
    Explanation: The great partitions are: ([1,2,3], [4]), ([1,3], [2,4]), 
                 ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) and 
                 ([4], [1,2,3]).
    
    Example 2:
    Input: nums = [3,3,3], k = 4
    Output: 0
    Explanation: There are no great partitions for this array.

    Example 3:
    Input: nums = [6,6], k = 2
    Output: 2
    Explanation: We can either put nums[0] in the first partition or in the 
                 second partition. The great partitions will be ([6], [6]) and 
                 ([6], [6]).

    Constraints:
    * 1 <= nums.length, k <= 1000
    * 1 <= nums[i] <= 10^9*/

    public int countPartitions(int[] nums, int k) {
        final int mod = 1_000_000_007; 
        long ans = 1, total = 0; 
        long[] dp = new long[k]; 
        dp[0] = 1; 
        for (var x : nums) {
            ans = 2*ans % mod; 
            total += x; 
            for (int i = k-1-x; i >= 0; --i) 
                dp[i+x] = (dp[i] + dp[i+x]) % mod; 
        }
        ans -= 2*LongStream.of(dp).sum(); 
        return total >= 2*k ? (int) (ans % mod + mod) % mod : 0; 
    }


    /*2519. Count the Number of K-Big Indices (Hard)
    You are given a 0-indexed integer array nums and a positive integer k. We 
    call an index i k-big if the following conditions are satisfied:
    * There exist at least k different indices idx1 such that idx1 < i and 
      nums[idx1] < nums[i].
    * There exist at least k different indices idx2 such that idx2 > i and 
      nums[idx2] < nums[i].
    Return the number of k-big indices.

    Example 1:
    Input: nums = [2,3,6,5,2,3], k = 2
    Output: 2
    Explanation: There are only two 2-big indices in nums:
                 - i = 2 --> There are two valid idx1: 0 and 1. There are three 
                             valid idx2: 2, 3, and 4.
                 - i = 3 --> There are two valid idx1: 0 and 1. There are two 
                             valid idx2: 3 and 4.
    
    Example 2:
    Input: nums = [1,1,1], k = 3
    Output: 0
    Explanation: There are no 3-big indices in nums.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i], k <= nums.length*/

    public int kBigIndices(int[] nums, int k) {
        int n = nums.length; 
        boolean[] prefix = new boolean[n]; 
        Queue<Integer> pq = new PriorityQueue(Collections.reverseOrder()); 
        for (int i = 0; i < n; ++i) {
            if (pq.size() == k && pq.peek() < nums[i]) prefix[i] = true; 
            pq.add(nums[i]); 
            if (pq.size() > k) pq.poll(); 
        }
        int ans = 0; 
        pq.clear(); 
        for (int i = n-1; i >= 0; --i) {
            if (pq.size() == k && pq.peek() < nums[i] && prefix[i]) ++ans; 
            pq.add(nums[i]); 
            if (pq.size() > k) pq.poll(); 
        }
        return ans; 
    }


    /*2520. Count the Digits That Divide a Number (Easy)
    Given an integer num, return the number of digits in num that divide num. 
    An integer val divides nums if nums % val == 0.

    Example 1:
    Input: num = 7
    Output: 1
    Explanation: 7 divides itself, hence the answer is 1.

    Example 2:
    Input: num = 121
    Output: 2
    Explanation: 121 is divisible by 1, but not 2. Since 1 occurs twice as a 
                 digit, we return 2.
    
    Example 3:
    Input: num = 1248
    Output: 4
    Explanation: 1248 is divisible by all of its digits, hence the answer is 4.

    Constraints:
    * 1 <= num <= 10^9
    * num does not contain 0 as one of its digits.*/

    public int countDigits(int num) {
        int ans = 0; 
        for (int n = num; n > 0; n /= 10) 
            if (num % (n % 10) == 0) ++ans; 
        return ans; 
    }


    /*2521. Distinct Prime Factors of Product of Array (Medium)
    Given an array of positive integers nums, return the number of distinct 
    prime factors in the product of the elements of nums. Note that:
    * A number greater than 1 is called prime if it is divisible by only 1 and 
      itself.
    * An integer val1 is a factor of another integer val2 if val2 / val1 is an 
      integer.

    Example 1:
    Input: nums = [2,4,3,7,10,6]
    Output: 4
    Explanation: The product of all the elements in nums is: 
                 2 * 4 * 3 * 7 * 10 * 6 = 10080 = 2^5 * 3^2 * 5 * 7.
                 There are 4 distinct prime factors so we return 4.
    
    Example 2:
    Input: nums = [2,4,8,16]
    Output: 1
    Explanation: The product of all the elements in nums is: 
                 2 * 4 * 8 * 16 = 1024 = 2^10.
                 There is 1 distinct prime factor so we return 1.

    Constraints:
    * 1 <= nums.length <= 10^4
    * 2 <= nums[i] <= 1000*/

    public int distinctPrimeFactors(int[] nums) {
        HashSet<Integer> seen = new HashSet(); 
        for (var x : nums) {
            for (int f = 2; f <= Math.sqrt(x); ++f) 
                for (; x % f == 0; x /= f) 
                    seen.add(f); 
            if (1 < x) seen.add(x);
        }
        return seen.size(); 
    }


    /*2522. Partition String Into Substrings With Values at Most K (Medium)
    You are given a string s consisting of digits from 1 to 9 and an integer k.
    A partition of a string s is called good if:
    * Each digit of s is part of exactly one substring.
    * The value of each substring is less than or equal to k.
    Return the minimum number of substrings in a good partition of s. If no 
    good partition of s exists, return -1. Note that:
    * The value of a string is its result when interpreted as an integer. For 
      example, the value of "123" is 123 and the value of "1" is 1.
    * A substring is a contiguous sequence of characters within a string.

    Example 1:
    Input: s = "165462", k = 60
    Output: 4
    Explanation: We can partition the string into substrings "16", "54", "6", 
                 and "2". Each substring has a value less than or equal to 
                 k = 60. It can be shown that we cannot partition the string 
                 into less than 4 substrings.
    
    Example 2:
    Input: s = "238182", k = 5
    Output: -1
    Explanation: There is no good partition for this string.

    Constraints:
    * 1 <= s.length <= 10^5
    * s[i] is a digit from '1' to '9'.
    * 1 <= k <= 10^9*/

    public int minimumPartition(String s, int k) {
        int ans = 1; 
        long val = 0; 
        for (var ch : s.toCharArray()) {
            if (10*val + (ch - '0') > k) {
                ++ans; 
                val = 0; 
            }
            val = 10*val + (ch - '0'); 
            if (val > k) return -1; 
        }
        return ans; 
    }


    /*2523. Closest Prime Numbers in Range (Medium)
    Given two positive integers left and right, find the two integers num1 and 
    num2 such that:
    * left <= nums1 < nums2 <= right .
    * nums1 and nums2 are both prime numbers.
    * nums2 - nums1 is the minimum amongst all other pairs satisfying the above 
      conditions.
    Return the positive integer array ans = [nums1, nums2]. If there are 
    multiple pairs satisfying these conditions, return the one with the minimum 
    nums1 value or [-1, -1] if such numbers do not exist. A number greater than 
    1 is called prime if it is only divisible by 1 and itself.

    Example 1:
    Input: left = 10, right = 19
    Output: [11,13]
    Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19. 
                 The closest gap between any pair is 2, which can be achieved 
                 by [11,13] or [17,19]. Since 11 is smaller than 17, we return 
                 the first pair.
    
    Example 2:
    Input: left = 4, right = 6
    Output: [-1,-1]
    Explanation: There exists only one prime number in the given range, so the 
                 conditions cannot be satisfied.

    Constraints: 1 <= left <= right <= 10^6*/

    public int[] closestPrimes(int left, int right) {
        boolean[] sieve = new boolean[right+1]; 
        Arrays.fill(sieve, true); 
        sieve[0] = sieve[1] = false; 
        for (int x = 2; x <= Math.sqrt(right); ++x) 
            if (sieve[x]) 
                for (int xx = x*x; xx <= right; xx +=x) 
                    sieve[xx] = false; 
        int[] ans = {-1, -1}; 
        int prev = 0, least = Integer.MAX_VALUE; 
        for (int x = left; x <= right; ++x) 
            if (sieve[x]) {
                if (prev > 0 && x - prev < least) {
                    ans = new int[]{prev, x}; 
                    least = x - prev; 
                }
                prev = x; 
            }
        return ans; 
    }


    /*2524. Maximum Frequency Score of a Subarray (Hard)
    You are given an integer array nums and a positive integer k. The frequency 
    score of an array is the sum of the distinct values in the array raised to 
    the power of their frequencies, taking the sum modulo 10^9 + 7. For example, 
    the frequency score of the array [5,4,5,7,4,4] is 
    (4^3 + 5^2 + 7^1) modulo (10^9 + 7) = 96. Return the maximum frequency 
    score of a subarray of size k in nums. You should maximize the value under 
    the modulo and not the actual value. A subarray is a contiguous part of an 
    array.

    Example 1:
    Input: nums = [1,1,1,2,1,2], k = 3
    Output: 5
    Explanation: The subarray [2,1,2] has a frequency score equal to 5. It can 
                 be shown that it is the maximum frequency score we can have.
    
    Example 2:
    Input: nums = [1,1,1,1,1,1], k = 4
    Output: 1
    Explanation: All the subarrays of length 4 have a frequency score equal to 
                 1.

    Constraints:
    * 1 <= k <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^6*/

    // private long pow(long x, int p, int m) {
    //     long ans = 1; 
    //     for (; p > 0; p >>= 1) {
    //         if (p % 2 == 1) ans = ans * x % m; 
    //         x = x * x % m; 
    //     }
    //     return ans; 
    // }
    
    public int maxFrequencyScore(int[] nums, int k) {
        final int mod = 1_000_000_007; 
        long ans = 0, val = 0; 
        HashMap<Integer, Integer> freq = new HashMap(); 
        for (int i = 0; i < nums.length; ++i) {
            if (freq.getOrDefault(nums[i], 0) > 0) val = (val - pow(nums[i], freq.get(nums[i]), mod)) % mod; 
            freq.merge(nums[i], 1, Integer::sum); 
            val = (val + pow(nums[i], freq.get(nums[i]), mod)) % mod; 
            if (i >= k) {
                val = (val - pow(nums[i-k], freq.get(nums[i-k]), mod)) % mod; 
                freq.merge(nums[i-k], -1, Integer::sum); 
                if (freq.getOrDefault(nums[i-k], 0) > 0) val = (val + pow(nums[i-k], freq.get(nums[i-k]), mod)) % mod; 
            }
            val = (val + mod) % mod; 
            if (i >= k-1) ans = Math.max(ans, val); 
        }
        return (int) ans; 
    }


    /*2525. Categorize Box According to Criteria (Easy)
    Given four integers length, width, height, and mass, representing the 
    dimensions and mass of a box, respectively, return a string representing 
    the category of the box. 
    * The box is "Bulky" if:
      + Any of the dimensions of the box is greater or equal to 10^4.
      + Or, the volume of the box is greater or equal to 10^9.
    * If the mass of the box is greater or equal to 100, it is "Heavy".
    * If the box is both "Bulky" and "Heavy", then its category is "Both".
    * If the box is neither "Bulky" nor "Heavy", then its category is "Neither".
    * If the box is "Bulky" but not "Heavy", then its category is "Bulky".
    * If the box is "Heavy" but not "Bulky", then its category is "Heavy".
    Note that the volume of the box is the product of its length, width and 
    height.

    Example 1:
    Input: length = 1000, width = 35, height = 700, mass = 300
    Output: "Heavy"
    Explanation: None of the dimensions of the box is greater or equal to 10^4. 
                 Its volume = 24500000 <= 10^9. So it cannot be categorized as 
                 "Bulky". However mass >= 100, so the box is "Heavy". Since the 
                 box is not "Bulky" but "Heavy", we return "Heavy". 
    
    Example 2:
    Input: length = 200, width = 50, height = 800, mass = 50
    Output: "Neither"
    Explanation: None of the dimensions of the box is greater or equal to 10^4.
                 Its volume = 8 * 10^6 <= 10^9. So it cannot be categorized as 
                 "Bulky". Its mass is also less than 100, so it cannot be 
                 categorized as "Heavy" either.  Since its neither of the two 
                 above categories, we return "Neither".

    Constraints:
    * 1 <= length, width, height <= 10^5
    * 1 <= mass <= 10^3*/

    public String categorizeBox(int length, int width, int height, int mass) {
        boolean bulky = Math.max(length, Math.max(width, height)) >= 1e4 || (long) length*width*height >= 1e9, heavy = mass >= 100; 
        if (bulky && heavy) return "Both"; 
        if (bulky) return "Bulky"; 
        if (heavy) return "Heavy"; 
        return "Neither"; 
    }


    /*2527. Find Xor-Beauty of Array (Medium)
    You are given a 0-indexed integer array nums. The effective value of three 
    indices i, j, and k is defined as ((nums[i] | nums[j]) & nums[k]). The xor-
    beauty of the array is the XORing of the effective values of all the 
    possible triplets of indices (i, j, k) where 0 <= i, j, k < n. Return the 
    xor-beauty of nums. Note that:
    * val1 | val2 is bitwise OR of val1 and val2.
    * val1 & val2 is bitwise AND of val1 and val2.

    Example 1:
    Input: nums = [1,4]
    Output: 5
    Explanation: The triplets and their corresponding effective values are 
                 listed below:
                 - (0,0,0) with effective value ((1 | 1) & 1) = 1
                 - (0,0,1) with effective value ((1 | 1) & 4) = 0
                 - (0,1,0) with effective value ((1 | 4) & 1) = 1
                 - (0,1,1) with effective value ((1 | 4) & 4) = 4
                 - (1,0,0) with effective value ((4 | 1) & 1) = 1
                 - (1,0,1) with effective value ((4 | 1) & 4) = 4
                 - (1,1,0) with effective value ((4 | 4) & 1) = 0
                 - (1,1,1) with effective value ((4 | 4) & 4) = 4 
                 Xor-beauty of array will be bitwise XOR of all beauties = 
                 1 ^ 0 ^ 1 ^ 4 ^ 1 ^ 4 ^ 0 ^ 4 = 5.
    
    Example 2:
    Input: nums = [15,45,20,2,34,35,5,44,32,30]
    Output: 34
    Explanation: The xor-beauty of the given array is 34.

    Constraints:
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^9*/

    public int xorBeauty(int[] nums) {
        int val = 0; 
        for (int x : nums) val ^= x; 
        return (val | val) & val; 
    }


    /*2528. Maximize the Minimum Powered City (Hard)
    You are given a 0-indexed integer array stations of length n, where 
    stations[i] represents the number of power stations in the ith city. Each 
    power station can provide power to every city in a fixed range. In other 
    words, if the range is denoted by r, then a power station at city i can 
    provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n - 1.
    * Note that |x| denotes absolute value. For example, |7 - 5| = 2 and 
      |3 - 10| = 7.
    The power of a city is the total number of power stations it is being 
    provided power from. The government has sanctioned building k more power 
    stations, each of which can be built in any city, and have the same range 
    as the pre-existing ones. Given the two integers r and k, return the 
    maximum possible minimum power of a city, if the additional power stations 
    are built optimally. Note that you can build the k power stations in 
    multiple cities.

    Example 1:
    Input: stations = [1,2,4,5,0], r = 1, k = 2
    Output: 5
    Explanation: One of the optimal ways is to install both the power stations 
                 at city 1. So stations will become [1,4,4,5,0].
                 - City 0 is provided by 1 + 4 = 5 power stations.
                 - City 1 is provided by 1 + 4 + 4 = 9 power stations.
                 - City 2 is provided by 4 + 4 + 5 = 13 power stations.
                 - City 3 is provided by 5 + 4 = 9 power stations.
                 - City 4 is provided by 5 + 0 = 5 power stations.
                 So the minimum power of a city is 5. Since it is not possible 
                 to obtain a larger power, we return 5.
    
    Example 2:
    Input: stations = [4,4,4,4], r = 0, k = 3
    Output: 4
    Explanation: It can be proved that we cannot make the minimum power of a 
                 city greater than 4.

    Constraints:
    * n == stations.length
    * 1 <= n <= 10^5
    * 0 <= stations[i] <= 10^5
    * 0 <= r <= n - 1
    * 0 <= k <= 10^9*/

    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length; 
        long lo = 0, hi = k + Arrays.stream(stations).asLongStream().sum();
        while (lo < hi) {
            long mid = lo + (hi-lo+1)/2, prefix = 0; 
            int kk = k; 
            int[] ss = stations.clone(); 
            boolean ok = true; 
            for (int i = 0; i < n+r; ++i) {
                if (i < n) prefix += ss[i]; 
                if (i >= 2*r+1) prefix -= ss[i-2*r-1]; 
                if (i >= r && prefix < mid) {
                    if (kk < mid - prefix) {
                        ok = false; 
                        break; 
                    }
                    kk -= mid - prefix; 
                    if (i < n) ss[i] += mid - prefix; 
                    prefix = mid; 
                }
            }
            if (ok) lo = mid; 
            else hi = mid - 1; 
        }
        return lo; 
    }


    /*2529. Maximum Count of Positive Integer and Negative Integer (Easy)
    Given an array nums sorted in non-decreasing order, return the maximum 
    between the number of positive integers and the number of negative integers.
    In other words, if the number of positive integers in nums is pos and the 
    number of negative integers is neg, then return the maximum of pos and neg.
    Note that 0 is neither positive nor negative.

    Example 1:
    Input: nums = [-2,-1,-1,1,2,3]
    Output: 3
    Explanation: There are 3 positive integers and 3 negative integers. The 
                 maximum count among them is 3.
    
    Example 2:
    Input: nums = [-3,-2,-1,0,0,1,2]
    Output: 3
    Explanation: There are 2 positive integers and 3 negative integers. The 
                 maximum count among them is 3.
    
    Example 3:
    Input: nums = [5,20,66,1314]
    Output: 4
    Explanation: There are 4 positive integers and 0 negative integers. The 
                 maximum count among them is 4.

    Constraints:
    * 1 <= nums.length <= 2000
    * -2000 <= nums[i] <= 2000
    * nums is sorted in a non-decreasing order.

    private static int bisect_left(int[] nums, int target) {
        int lo = 0, hi = nums.length; 
        while (lo < hi) {
            int mid = lo + (hi - lo)/2; 
            if (nums[mid] < target) lo = mid+1; 
            else hi = mid; 
        }
        return lo; 
    }*/
    
    public int maximumCount(int[] nums) {
        int neg = bisect_left(nums, 0), pos = nums.length - bisect_left(nums, 1); 
        return Math.max(neg, pos); 
    }


    /*2530. Maximal Score After Applying K Operations (Medium)
    You are given a 0-indexed integer array nums and an integer k. You have a 
    starting score of 0. In one operation:
    * choose an index i such that 0 <= i < nums.length,
    * increase your score by nums[i], and
    * replace nums[i] with ceil(nums[i] / 3).
    Return the maximum possible score you can attain after applying exactly k 
    operations. The ceiling function ceil(val) is the least integer greater 
    than or equal to val.

    Example 1:
    Input: nums = [10,10,10,10,10], k = 5
    Output: 50
    Explanation: Apply the operation to each array element exactly once. The 
                 final score is 10 + 10 + 10 + 10 + 10 = 50.
    
    Example 2:
    Input: nums = [1,10,3,3,3], k = 3
    Output: 17
    Explanation: You can do the following operations:
                 Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your 
                              score increases by 10.
                 Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your 
                              score increases by 4.
                 Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your 
                              score increases by 3.
                 The final score is 10 + 4 + 3 = 17.

    Constraints:
    * 1 <= nums.length, k <= 10^5
    * 1 <= nums[i] <= 10^9*/

    public long maxKelements(int[] nums, int k) {
        long ans = 0; 
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder()); 
        for (int x : nums) pq.add(x); 
        while (k-- > 0) {
            int val = pq.poll(); 
            ans += val; 
            pq.add((val+2)/3); 
        }
        return ans; 
    }


    /*2531. Make Number of Distinct Characters Equal (Medium)
    You are given two 0-indexed strings word1 and word2. A move consists of 
    choosing two indices i and j such that 0 <= i < word1.length and 
    0 <= j < word2.length and swapping word1[i] with word2[j]. Return true if 
    it is possible to get the number of distinct characters in word1 and word2 
    to be equal with exactly one move. Return false otherwise.

    Example 1:
    Input: word1 = "ac", word2 = "b"
    Output: false
    Explanation: Any pair of swaps would yield two distinct characters in the 
                 first string, and one in the second string.
    
    Example 2:
    Input: word1 = "abcc", word2 = "aab"
    Output: true
    Explanation: We swap index 2 of the first string with index 0 of the second 
                 string. The resulting strings are word1 = "abac" and 
                 word2 = "cab", which both have 3 distinct characters.
    
    Example 3:
    Input: word1 = "abcde", word2 = "fghij"
    Output: true
    Explanation: Both resulting strings will have 5 distinct characters, 
                 regardless of which indices we swap.

    Constraints:
    * 1 <= word1.length, word2.length <= 10^5
    * word1 and word2 consist of only lowercase English letters.*/

    public boolean isItPossible(String word1, String word2) {
        HashMap<Character, Integer> freq1 = new HashMap(), freq2 = new HashMap(); 
        for (var ch : word1.toCharArray()) freq1.merge(ch, 1, Integer::sum); 
        for (var ch : word2.toCharArray()) freq2.merge(ch, 1, Integer::sum); 
        int sz1 = freq1.size(), sz2 = freq2.size(); 
        for (char c1 = 'a'; c1 <= 'z'; ++c1) 
            for (char c2 = 'a'; c2 <= 'z'; ++c2) 
                if (freq1.getOrDefault(c1, 0) > 0 && freq2.getOrDefault(c2, 0) > 0) 
                    if (c1 == c2) {
                        if (sz1 == sz2) return true; 
                    } else {
                        int cnt1 = sz1, cnt2 = sz2; 
                        if (freq1.getOrDefault(c1, 0) == 1) --cnt1; 
                        if (freq1.getOrDefault(c2, 0) == 0) ++cnt1; 
                        if (freq2.getOrDefault(c1, 0) == 0) ++cnt2; 
                        if (freq2.getOrDefault(c2, 0) == 1) --cnt2; 
                        if (cnt1 == cnt2) return true; 
                    }
        return false; 
    }


    /*2532. Time to Cross a Bridge (Hard)
    There are k workers who want to move n boxes from an old warehouse to a new 
    one. You are given the two integers n and k, and a 2D integer array time of 
    size k x 4 where time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi].
    The warehouses are separated by a river and connected by a bridge. The old 
    warehouse is on the right bank of the river, and the new warehouse is on 
    the left bank of the river. Initially, all k workers are waiting on the 
    left side of the bridge. To move the boxes, the ith worker (0-indexed) can :
    * Cross the bridge from the left bank (new warehouse) to the right bank 
      (old warehouse) in leftToRighti minutes.
    * Pick a box from the old warehouse and return to the bridge in pickOldi 
      minutes. Different workers can pick up their boxes simultaneously.
    * Cross the bridge from the right bank (old warehouse) to the left bank 
      (new warehouse) in rightToLefti minutes.
    * Put the box in the new warehouse and return to the bridge in putNewi 
      minutes. Different workers can put their boxes simultaneously.
    A worker i is less efficient than a worker j if either condition is met:
    * leftToRighti + rightToLefti > leftToRightj + rightToLeftj
    * leftToRighti + rightToLefti == leftToRightj + rightToLeftj and i > j
    The following rules regulate the movement of the workers through the bridge :
    * If a worker x reaches the bridge while another worker y is crossing the 
      bridge, x waits at their side of the bridge.
    * If the bridge is free, the worker waiting on the right side of the bridge 
      gets to cross the bridge. If more than one worker is waiting on the right 
      side, the one with the lowest efficiency crosses first.
    * If the bridge is free and no worker is waiting on the right side, and at 
      least one box remains at the old warehouse, the worker on the left side 
      of the river gets to cross the bridge. If more than one worker is waiting 
      on the left side, the one with the lowest efficiency crosses first.
    Return the instance of time at which the last worker reaches the left bank 
    of the river after all n boxes have been put in the new warehouse.

    Example 1:
    Input: n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
    Output: 6
    Explanation: From 0 to 1: worker 2 crosses the bridge from the left bank to 
                              the right bank.
                 From 1 to 2: worker 2 picks up a box from the old warehouse.
                 From 2 to 6: worker 2 crosses the bridge from the right bank 
                              to the left bank.
                 From 6 to 7: worker 2 puts a box at the new warehouse.
                 The whole process ends after 7 minutes. We return 6 because 
                 the problem asks for the instance of time at which the last 
                 worker reaches the left bank.
    
    Example 2:
    Input: n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
    Output: 50
    Explanation: From 0  to 10: worker 1 crosses the bridge from the left bank 
                                to the right bank.
                 From 10 to 20: worker 1 picks up a box from the old warehouse.
                 From 10 to 11: worker 0 crosses the bridge from the left bank 
                                to the right bank.
                 From 11 to 20: worker 0 picks up a box from the old warehouse.
                 From 20 to 30: worker 1 crosses the bridge from the right bank 
                                to the left bank.
                 From 30 to 40: worker 1 puts a box at the new warehouse.
                 From 30 to 31: worker 0 crosses the bridge from the right bank 
                                to the left bank.
                 From 31 to 39: worker 0 puts a box at the new warehouse.
                 From 39 to 40: worker 0 crosses the bridge from the left bank 
                                to the right bank.
                 From 40 to 49: worker 0 picks up a box from the old warehouse.
                 From 49 to 50: worker 0 crosses the bridge from the right bank 
                                to the left bank.
                 From 50 to 58: worker 0 puts a box at the new warehouse.
                 The whole process ends after 58 minutes. We return 50 because 
                 the problem asks for the instance of time at which the last 
                 worker reaches the left bank.

    Constraints:
    * 1 <= n, k <= 10^4
    * time.length == k
    * time[i].length == 4
    * 1 <= leftToRighti, pickOldi, rightToLefti, putNewi <= 1000*/

    public int findCrossingTime(int n, int k, int[][] time) {
        int ans = 0, free = 0; 
        PriorityQueue<int[]> l = new PriorityQueue<>((a, b)->(a[0]-b[0])); 
        PriorityQueue<int[]> r = new PriorityQueue<>((a, b)->(a[0]-b[0])); 
        PriorityQueue<int[]> ll = new PriorityQueue<>((a, b)->(a[0] != b[0] ? b[0]-a[0] : b[1]-a[1]));
        PriorityQueue<int[]> rr = new PriorityQueue<>((a, b)->(a[0] != b[0] ? b[0]-a[0] : b[1]-a[1])); 
        for (int i = 0; i < time.length; ++i) 
            ll.add(new int[]{time[i][0]+time[i][2], i}); 
        while (n > 0 || r.size() > 0 || rr.size() > 0) {
            if (rr.isEmpty() && (r.isEmpty() || r.peek()[0] > free) && (n == 0 || ll.isEmpty() && (l.isEmpty() || l.peek()[0] > free))) {
                int cand = Integer.MAX_VALUE; 
                if (n > 0 && l.size() > 0) cand = Math.min(cand, l.peek()[0]); 
                if (r.size() > 0) cand = Math.min(cand, r.peek()[0]); 
                free = cand; 
            }
            while (l.size() > 0 && l.peek()[0] <= free) {
                int i = l.poll()[1]; 
                ll.add(new int[] {time[i][0] + time[i][2], i}); 
            }
            while (r.size() > 0 && r.peek()[0] <= free) {
                int i = r.poll()[1]; 
                rr.add(new int[] {time[i][0] + time[i][2], i}); 
            }
            if (rr.size() > 0) {
                int i = rr.poll()[1]; 
                free += time[i][2]; 
                if (n > 0) l.add(new int[] {free+time[i][3], i}); 
                else ans = Math.max(ans, free); 
            } else {
                int i = ll.poll()[1]; 
                free += time[i][0]; 
                r.add(new int[] {free+time[i][1], i}); 
                --n; 
            }
        }
        return ans; 
    }


    /*2533. Number of Good Binary Strings (Medium)
    You are given four integers minLenght, maxLength, oneGroup and zeroGroup. A 
    binary string is good if it satisfies the following conditions:
    * The length of the string is in the range [minLength, maxLength].
    * The size of each block of consecutive 1's is a multiple of oneGroup.
      + For example in a binary string 00110111100 sizes of each block of 
        consecutive ones are [2,4].
    * The size of each block of consecutive 0's is a multiple of zeroGroup.
      + For example, in a binary string 00110111100 sizes of each block of 
        consecutive ones are [2,1,2].
    Return the number of good binary strings. Since the answer may be too large, 
    return it modulo 10^9 + 7. Note that 0 is considered a multiple of all the 
    numbers.

    Example 1:
    Input: minLength = 2, maxLength = 3, oneGroup = 1, zeroGroup = 2
    Output: 5
    Explanation: There are 5 good binary strings in this example: "00", "11", 
                 "001", "100", and "111". It can be proven that there are only 
                 5 good strings satisfying all conditions.
    
    Example 2:
    Input: minLength = 4, maxLength = 4, oneGroup = 4, zeroGroup = 3
    Output: 1
    Explanation: There is only 1 good binary string in this example: "1111". It 
                 can be proven that there is only 1 good string satisfying all 
                 conditions.

    Constraints:
    * 1 <= minLength <= maxLength <= 10^5
    * 1 <= oneGroup, zeroGroup <= maxLength*/

    public int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        long[] dp = new long[maxLength+1]; 
        for (int i = maxLength; i >= 0; --i) {
            if (minLength <= i) ++dp[i]; 
            if (i + oneGroup <= maxLength) dp[i] = (dp[i] + dp[i+oneGroup]) % 1_000_000_007; 
            if (i + zeroGroup <= maxLength) dp[i] = (dp[i] + dp[i+zeroGroup]) % 1_000_000_007; 
        }
        return (int) dp[0]; 
    }


    /*2534. Time Taken to Cross the Door (Hard)
    There are n persons numbered from 0 to n - 1 and a door. Each person can 
    enter or exit through the door once, taking one second. You are given a 
    non-decreasing integer array arrival of size n, where arrival[i] is the 
    arrival time of the ith person at the door. You are also given an array 
    state of size n, where state[i] is 0 if person i wants to enter through the 
    door or 1 if they want to exit through the door. If two or more persons 
    want to use the door at the same time, they follow the following rules:
    * If the door was not used in the previous second, then the person who 
      wants to exit goes first.
    * If the door was used in the previous second for entering, the person who 
      wants to enter goes first.
    * If the door was used in the previous second for exiting, the person who 
      wants to exit goes first.
    * If multiple persons want to go in the same direction, the person with the 
      smallest index goes first.
    Return an array answer of size n where answer[i] is the second at which the 
    ith person crosses the door.

    Note that:
    * Only one person can cross the door at each second.
    * A person may arrive at the door and wait without entering or exiting to 
      follow the mentioned rules.

    Example 1:
    Input: arrival = [0,1,1,2,4], state = [0,1,0,0,1]
    Output: [0,3,1,2,4]
    Explanation: At each second we have the following:
                 - At t = 0: Person 0 is the only one who wants to enter, so 
                             they just enter through the door.
                 - At t = 1: Person 1 wants to exit, and person 2 wants to 
                             enter. Since the door was used the previous second 
                             for entering, person 2 enters.
                 - At t = 2: Person 1 still wants to exit, and person 3 wants 
                             to enter. Since the door was used the previous 
                             second for entering, person 3 enters.
                 - At t = 3: Person 1 is the only one who wants to exit, so 
                             they just exit through the door.
                 - At t = 4: Person 4 is the only one who wants to exit, so 
                             they just exit through the door.
    
    Example 2:
    Input: arrival = [0,0,0], state = [1,0,1]
    Output: [0,2,1]
    Explanation: At each second we have the following:
                 - At t = 0: Person 1 wants to enter while persons 0 and 2 want 
                             to exit. Since the door was not used in the 
                             previous second, the persons who want to exit get 
                             to go first. Since person 0 has a smaller index, 
                             they exit first.
                 - At t = 1: Person 1 wants to enter, and person 2 wants to 
                             exit. Since the door was used in the previous 
                             second for exiting, person 2 exits.
                 - At t = 2: Person 1 is the only one who wants to enter, so 
                             they just enter through the door.

    Constraints:
    * n == arrival.length == state.length
    * 1 <= n <= 10^5
    * 0 <= arrival[i] <= n
    * arrival is sorted in non-decreasing order.
    * state[i] is either 0 or 1.*/

    public int[] timeTaken(int[] arrival, int[] state) {
        int n = arrival.length; 
        int[] ans = new int[n]; 
        Deque<Integer> qin = new ArrayDeque<>(), qout = new ArrayDeque<>(); 
        boolean enter = false; 
        for (int i = 0, time = 0; i < n || !qin.isEmpty() || !qout.isEmpty(); ) {
            if (i == n || time < arrival[i]) {
                if (!qin.isEmpty() || !qout.isEmpty()) {
                    if (!qin.isEmpty() && (qout.isEmpty() || enter)) {
                        ans[qin.pollFirst()] = time++; 
                        enter = true; 
                    } else {
                        ans[qout.pollFirst()] = time++; 
                        enter = false; 
                    }
                } else {
                    if (time+1 <= arrival[i]) enter = false; 
                    time = arrival[i]; 
                }
            }
            for (; i < n && time == arrival[i]; ++i) {
                if (state[i] == 0) qin.addLast(i); 
                else qout.addLast(i); 
            }
        }
        return ans; 
    }


    /*2535. Difference Between Element Sum and Digit Sum of an Array (Easy)
    You are given a positive integer array nums. 
    * The element sum is the sum of all the elements in nums.
    * The digit sum is the sum of all the digits (not necessarily distinct) 
      that appear in nums.
    Return the absolute difference between the element sum and digit sum of 
    nums. Note that the absolute difference between two integers x and y is 
    defined as |x - y|.

    Example 1:
    Input: nums = [1,15,6,3]
    Output: 9
    Explanation: The element sum of nums is 1 + 15 + 6 + 3 = 25. The digit sum 
                 of nums is 1 + 1 + 5 + 6 + 3 = 16. The absolute difference 
                 between the element sum and digit sum is |25 - 16| = 9.
    
    Example 2:
    Input: nums = [1,2,3,4]
    Output: 0
    Explanation: The element sum of nums is 1 + 2 + 3 + 4 = 10. The digit sum 
                 of nums is 1 + 2 + 3 + 4 = 10. The absolute difference between 
                 the element sum and digit sum is |10 - 10| = 0.

    Constraints:
    * 1 <= nums.length <= 2000
    * 1 <= nums[i] <= 2000*/

    public int differenceOfSum(int[] nums) {
        int ans = 0; 
        for (int x : nums) {
            ans += x; 
            for (; x > 0; x /= 10) 
                ans -= x % 10; 
        }
        return ans; 
    }


    /*2536. Increment Submatrices by One (Medium)
    You are given a positive integer n, indicating that we initially have an 
    n x n 0-indexed integer matrix mat filled with zeroes. You are also given a 
    2D integer array query. For each query[i] = [row1i, col1i, row2i, col2i], 
    you should do the following operation:
    * Add 1 to every element in the submatrix with the top left corner 
      (row1i, col1i) and the bottom right corner (row2i, col2i). That is, add 1 
      to mat[x][y] for for all row1i <= x <= row2i and col1i <= y <= col2i.
    Return the matrix mat after performing every query.

    Example 1:
    Input: n = 3, queries = [[1,1,2,2],[0,0,1,1]]
    Output: [[1,1,0],[1,2,1],[0,1,1]]
    Explanation: The diagram above shows the initial matrix, the matrix after 
                 the first query, and the matrix after the second query.
                 - In the first query, we add 1 to every element in the 
                   submatrix with the top left corner (1, 1) and bottom right 
                   corner (2, 2).
                 - In the second query, we add 1 to every element in the 
                   submatrix with the top left corner (0, 0) and bottom right 
                   corner (1, 1).
    
    Example 2:
    Input: n = 2, queries = [[0,0,1,1]]
    Output: [[1,1],[1,1]]
    Explanation: The diagram above shows the initial matrix and the matrix 
                 after the first query.
                 - In the first query we add 1 to every element in the matrix.

    Constraints:
    * 1 <= n <= 500
    * 1 <= queries.length <= 10^4
    * 0 <= row1i <= row2i < n
    * 0 <= col1i <= col2i < n*/

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] ans = new int[n][n]; 
        for (int[] q : queries) {
            int i = q[0], j = q[1], ii = q[2], jj = q[3]; 
            ++ans[i][j]; 
            if (ii+1 < n) --ans[ii+1][j]; 
            if (jj+1 < n) --ans[i][jj+1]; 
            if (ii+1 < n && jj+1 < n) ++ans[ii+1][jj+1]; 
        }
        for (int i = 0; i < n; ++i) {
            int prefix = 0; 
            for (int j = 0; j < n; ++j) {
                prefix += ans[i][j]; 
                ans[i][j] = prefix; 
                if (i > 0) ans[i][j] += ans[i-1][j]; 
            }
        }
        return ans; 
    }


    /*2537. Count the Number of Good Subarrays (Medium)
    Given an integer array nums and an integer k, return the number of good 
    subarrays of nums. A subarray arr is good if it there are at least k pairs 
    of indices (i, j) such that i < j and arr[i] == arr[j]. A subarray is a 
    contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [1,1,1,1,1], k = 10
    Output: 1
    Explanation: The only good subarray is the array nums itself.

    Example 2:
    Input: nums = [3,1,4,3,2,2,4], k = 2
    Output: 4
    Explanation: There are 4 different good subarrays:
                 - [3,1,4,3,2,2] that has 2 pairs.
                 - [3,1,4,3,2,2,4] that has 3 pairs.
                 - [1,4,3,2,2,4] that has 2 pairs.
                 - [4,3,2,2,4] that has 2 pairs.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i], k <= 10^9*/

    public long countGood(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>(); 
        long ans = 0; 
        for (int i = 0, ii = 0; i < nums.length; ++i) {
            k -= freq.getOrDefault(nums[i], 0); 
            freq.put(nums[i], freq.getOrDefault(nums[i], 0)+1); 
            for (; k <= 0; ++ii) {
                freq.put(nums[ii], freq.get(nums[ii])-1); 
                k += freq.get(nums[ii]); 
            }
            ans += ii; 
        }
        return ans; 
    }


    /*2538. Difference Between Maximum and Minimum Price Sum (Hard)
    There exists an undirected and initially unrooted tree with n nodes indexed 
    from 0 to n - 1. You are given the integer n and a 2D integer array edges 
    of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge 
    between nodes ai and bi in the tree. Each node has an associated price. You 
    are given an integer array price, where price[i] is the price of the ith 
    node. The price sum of a given path is the sum of the prices of all nodes 
    lying on that path. The tree can be rooted at any node root of your choice. 
    The incurred cost after choosing root is the difference between the maximum 
    and minimum price sum amongst all paths starting at root. Return the 
    maximum possible cost amongst all possible root choices.

    Example 1:
    Input: n = 6, edges = [[0,1],[1,2],[1,3],[3,4],[3,5]], price = [9,8,7,6,10,5]
    Output: 24
    Explanation: The diagram above denotes the tree after rooting it at node 2. 
                 The first part (colored in red) shows the path with the 
                 maximum price sum. The second part (colored in blue) shows the 
                 path with the minimum price sum.
                 - The first path contains nodes [2,1,3,4]: the prices are 
                   [7,8,6,10], and the sum of the prices is 31.
                 - The second path contains the node [2] with the price [7].
                 The difference between the maximum and minimum price sum is 24. 
                 It can be proved that 24 is the maximum cost.
    
    Example 2:
    Input: n = 3, edges = [[0,1],[1,2]], price = [1,1,1]
    Output: 2
    Explanation: The diagram above denotes the tree after rooting it at node 0. 
                 The first part (colored in red) shows the path with the 
                 maximum price sum. The second part (colored in blue) shows the 
                 path with the minimum price sum.
                 - The first path contains nodes [0,1,2]: the prices are 
                   [1,1,1], and the sum of the prices is 3.
                 - The second path contains node [0] with a price [1].
                 The difference between the maximum and minimum price sum is 2. 
                 It can be proved that 2 is the maximum cost.

    Constraints:
    * 1 <= n <= 10^5
    * edges.length == n - 1
    * 0 <= ai, bi <= n - 1
    * edges represents a valid tree.
    * price.length == n
    * 1 <= price[i] <= 10^5*/

    long ans = 0; 
    
    private long[] dfs(int u, int p, List<Integer>[] tree, int[] price) {
        long x = price[u], y = 0; 
        for (var v : tree[u]) {
            if (v != p) {
                var elem = dfs(v, u, tree, price); 
                long xx = elem[0], yy = elem[1]; 
                ans = Math.max(ans, x + yy); 
                ans = Math.max(ans, xx + y); 
                x = Math.max(x, xx + price[u]); 
                y = Math.max(y, yy + price[u]); 
            }
        }
        return new long[]{x, y}; 
    }
    
    public long maxOutput(int n, int[][] edges, int[] price) {
        List<Integer>[] tree = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) tree[u] = new ArrayList(); 
        for (var e : edges) {
            tree[e[0]].add(e[1]); 
            tree[e[1]].add(e[0]); 
        }
        dfs(0, -1, tree, price); 
        return ans; 
    }


    /*2539. Count the Number of Good Subsequences (Medium)
    A subsequence of a string is good if it is not empty and the frequency of 
    each one of its characters is the same. Given a string s, return the number 
    of good subsequences of s. Since the answer may be too large, return it 
    modulo 10^9 + 7. A subsequence is a string that can be derived from another 
    string by deleting some or no characters without changing the order of the 
    remaining characters.

    Example 1:
    Input: s = "aabb"
    Output: 11
    Explanation: The total number of subsequences is 24. There are five 
                 subsequences which are not good: "aabb", "aabb", "aabb", 
                 "aabb", and the empty subsequence. Hence, the number of good 
                 subsequences is 24-5 = 11.
    
    Example 2:
    Input: s = "leet"
    Output: 12
    Explanation: There are four subsequences which are not good: "leet", "leet", 
                 "leet", and the empty subsequence. Hence, the number of good 
                 subsequences is 24-4 = 12.
    
    Example 3:
    Input: s = "abcd"
    Output: 15
    Explanation: All of the non-empty subsequences are good subsequences. 
                 Hence, the number of good subsequences is 24-1 = 15.

    Constraints:
    * 1 <= s.length <= 10^4
    * s consists of only lowercase English letters.*/

    public int countGoodSubsequences(String s) {
        final int mod = 1_000_000_007; 
        int[] freq = new int[26]; 
        for (var ch : s.toCharArray()) ++freq[ch-'a']; 
        long[] coef = new long[26], inv = new long[s.length()+1]; 
        Arrays.fill(coef, 1); 
        inv[0] = inv[1] = 1; 
        long ans = 0; 
        for (int x = 1; x <= s.length(); ++x) {
            long val = 1; 
            if (x >= 2) inv[x] = mod - mod/x * inv[mod%x] % mod; 
            for (int i = 0; i < 26; ++i) {
                coef[i] = coef[i] * (freq[i]-x+1) % mod; 
                coef[i] = coef[i] * inv[x] % mod; 
                val = val * (1+coef[i]) % mod; 
            }
            ans = (ans + val - 1) % mod; 
        }
        return (int) ans; 
    }


    /*2540. Minimum Common Value (Easy)
    Given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
    return the minimum integer common to both arrays. If there is no common 
    integer amongst nums1 and nums2, return -1. Note that an integer is said 
    to be common to nums1 and nums2 if both arrays have at least one 
    occurrence of that integer.

    Example 1:
    Input: nums1 = [1,2,3], nums2 = [2,4]
    Output: 2
    Explanation: The smallest element common to both arrays is 2, so we return 
                 2.

    Example 2:
    Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
    Output: 2
    Explanation: There are two common elements in the array 2 and 3 out of 
                 which 2 is the smallest, so 2 is returned.

    Constraints:
    * 1 <= nums1.length, nums2.length <= 10^5
    * 1 <= nums1[i], nums2[j] <= 10^9
    * Both nums1 and nums2 are sorted in non-decreasing order.*/

    public int getCommon(int[] nums1, int[] nums2) {
        for (int i = 0, ii = 0; i < nums1.length && ii < nums2.length; ) {
            if (nums1[i] < nums2[ii]) ++i;
            else if (nums1[i] == nums2[ii]) return nums1[i]; 
            else ++ii; 
        }
        return -1; 
    }


    /*2541. Minimum Operations to Make Array Equal II (Medium)
    You are given two integer arrays nums1 and nums2 of equal length n and an 
    integer k. You can perform the following operation on nums1:
    * Choose two indexes i and j and increment nums1[i] by k and decrement 
      nums1[j] by k. In other words, nums1[i] = nums1[i] + k and 
      nums1[j] = nums1[j] - k.
    nums1 is said to be equal to nums2 if for all indices i such that 
    0 <= i < n, nums1[i] == nums2[i]. Return the minimum number of operations 
    required to make nums1 equal to nums2. If it is impossible to make them 
    equal, return -1.

    Example 1:
    Input: nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
    Output: 2
    Explanation: In 2 operations, we can transform nums1 to nums2. 1st 
                 operation: i = 2, j = 0. After applying the operation, 
                 nums1 = [1,3,4,4]. 2nd operation: i = 2, j = 3. After applying 
                 the operation, nums1 = [1,3,7,1]. One can prove that it is 
                 impossible to make arrays equal in fewer operations.
    
    Example 2:
    Input: nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
    Output: -1
    Explanation: It can be proved that it is impossible to make the two arrays 
                 equal.

    Constraints:
    * n == nums1.length == nums2.length
    * 2 <= n <= 10^5
    * 0 <= nums1[i], nums2[j] <= 10^9
    * 0 <= k <= 10^5*/

    public long minOperations(int[] nums1, int[] nums2, int k) {
        long ans = 0, total = 0; 
        for (int i = 0; i < nums1.length; ++i) {
            int diff = nums1[i] - nums2[i]; 
            if (k == 0 && diff > 0 || k > 0 && diff % k != 0) return -1; 
            if (k > 0) ans += Math.abs(diff) / k; 
            total += diff; 
        }
        return total == 0 ? ans/2 : -1; 
    }


    /*2542. Maximum Subsequence Score (Medium)
    You are given two 0-indexed integer arrays nums1 and nums2 of equal length 
    n and a positive integer k. You must choose a subsequence of indices from 
    nums1 of length k. For chosen indices i0, i1, ..., ik - 1, your score is 
    defined as:
    * The sum of the selected elements from nums1 multiplied with the minimum 
      of the selected elements from nums2.
    * It can defined simply as: 
      (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
    Return the maximum possible score. A subsequence of indices of an array is 
    a set that can be derived from the set {0, 1, ..., n-1} by deleting some or 
    no elements.

    Example 1:
    Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
    Output: 12
    Explanation: The four possible subsequence scores are:
                 - We choose the indices 0, 1, and 2 with 
                   score = (1+3+3) * min(2,1,3) = 7.
                 - We choose the indices 0, 1, and 3 with 
                   score = (1+3+2) * min(2,1,4) = 6. 
                 - We choose the indices 0, 2, and 3 with 
                   score = (1+3+2) * min(2,3,4) = 12. 
                 - We choose the indices 1, 2, and 3 with 
                   score = (3+3+2) * min(1,3,4) = 8.
                 Therefore, we return the max score, which is 12.
    
    Example 2:
    Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
    Output: 30
    Explanation: Choosing index 2 is optimal: 
                 nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible 
                 score.

    Constraints:
    * n == nums1.length == nums2.length
    * 1 <= n <= 10^5
    * 0 <= nums1[i], nums2[j] <= 10^5
    * 1 <= k <= n*/

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length; 
        int[][] aug = new int[n][2]; 
        for (int i = 0; i < n; ++i) {
            aug[i][0] = nums1[i]; 
            aug[i][1] = nums2[i]; 
        }
        Arrays.sort(aug, new Comparator<int[]>(){
            public int compare(int[] lhs, int[] rhs) {
                return -Integer.compare(lhs[1], rhs[1]); 
            }
        }); 
        PriorityQueue<Integer> pq = new PriorityQueue(); 
        long ans = 0, total = 0; 
        for (int i = 0; i < aug.length; ++i) {
            total += aug[i][0]; 
            pq.add(aug[i][0]); 
            if (i >= k) total -= pq.poll(); 
            if (i >= k-1) ans = Math.max(ans, total * aug[i][1]); 
        }
        return ans; 
    }


    /*2543. Check if Point Is Reachable (Hard)
    There exists an infinitely large grid. You are currently at point (1, 1), 
    and you need to reach the point (targetX, targetY) using a finite number of 
    steps. In one step, you can move from point (x, y) to any one of the 
    following points: (x, y - x)
                      (x - y, y)
                      (2 * x, y)
                      (x, 2 * y)
    Given two integers targetX and targetY representing the X-coordinate and Y-
    coordinate of your final position, return true if you can reach the point 
    from (1, 1) using some number of steps, and false otherwise.

    Example 1:
    Input: targetX = 6, targetY = 9
    Output: false
    Explanation: It is impossible to reach (6,9) from (1,1) using any sequence 
                 of moves, so false is returned.
    
    Example 2:
    Input: targetX = 4, targetY = 7
    Output: true
    Explanation: You can follow the path (1,1) -> (1,2) -> (1,4) -> (1,8) -> 
                 (1,7) -> (2,7) -> (4,7).

    Constraints: 1 <= targetX, targetY <= 10^9*/

    public boolean isReachable(int targetX, int targetY) {
        int g = BigInteger.valueOf(targetX).gcd(BigInteger.valueOf(targetY)).intValue();
        return (g & (g-1)) == 0; 
    }


    /*2544. Alternating Digit Sum (Easy)
    You are given a positive integer n. Each digit of n has a sign according to 
    the following rules:
    * The most significant digit is assigned a positive sign.
    * Each other digit has an opposite sign to its adjacent digits.
    Return the sum of all digits with their corresponding sign.

    Example 1:
    Input: n = 521
    Output: 4
    Explanation: (+5) + (-2) + (+1) = 4.

    Example 2:
    Input: n = 111
    Output: 1
    Explanation: (+1) + (-1) + (+1) = 1.

    Example 3:
    Input: n = 886996
    Output: 0
    Explanation: (+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0.

    Constraints: 1 <= n <= 10^9*/

    public int alternateDigitSum(int n) {
        int ans = 0, sign = 1; 
        for (; n > 0; n /= 10) {
            sign *= -1; 
            ans += (n % 10) * sign; 
        }
        return ans * sign; 
    }


    /*2545. Sort the Students by Their Kth Score (Medium)
    There is a class with m students and n exams. You are given a 0-indexed 
    m x n integer matrix score, where each row represents one student and 
    score[i][j] denotes the score the ith student got in the jth exam. The 
    matrix score contains distinct integers only. You are also given an integer 
    k. Sort the students (i.e., the rows of the matrix) by their scores in the 
    kth (0-indexed) exam from the highest to the lowest. Return the matrix 
    after sorting it.

    Example 1:
    Input: score = [[10,6,9,1],[7,5,11,2],[4,8,3,15]], k = 2
    Output: [[7,5,11,2],[10,6,9,1],[4,8,3,15]]
    Explanation: In the above diagram, S denotes the student, while E denotes 
                 the exam.
                 - The student with index 1 scored 11 in exam 2, which is the 
                   highest score, so they got first place.
                 - The student with index 0 scored 9 in exam 2, which is the 
                   second highest score, so they got second place.
                 - The student with index 2 scored 3 in exam 2, which is the 
                   lowest score, so they got third place.
    
    Example 2:
    Input: score = [[3,4],[5,6]], k = 0
    Output: [[5,6],[3,4]]
    Explanation: In the above diagram, S denotes the student, while E denotes 
                 the exam.
                 - The student with index 1 scored 5 in exam 0, which is the 
                   highest score, so they got first place.
                 - The student with index 0 scored 3 in exam 0, which is the 
                   lowest score, so they got second place.

    Constraints:
    * m == score.length
    * n == score[i].length
    * 1 <= m, n <= 250
    * 1 <= score[i][j] <= 10^5
    * score consists of distinct integers.
    * 0 <= k < n*/

    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> (b[k] - a[k])); 
        return score; 
    }


    /*2546. Apply Bitwise Operations to Make Strings Equal (Medium)
    You are given two 0-indexed binary strings s and target of the same length 
    n. You can do the following operation on s any number of times:
    * Choose two different indices i and j where 0 <= i, j < n.
    * Simultaneously, replace s[i] with (s[i] OR s[j]) and s[j] with (s[i] XOR 
      s[j]).
    For example, if s = "0110", you can choose i = 0 and j = 2, then 
    simultaneously replace s[0] with (s[0] OR s[2] = 0 OR 1 = 1), and s[2] with 
    (s[0] XOR s[2] = 0 XOR 1 = 1), so we will have s = "1110". Return true if 
    you can make the string s equal to target, or false otherwise.

    Example 1:
    Input: s = "1010", target = "0110"
    Output: true
    Explanation: We can do the following operations:
                 - Choose i = 2 and j = 0. We have now s = "0010".
                 - Choose i = 2 and j = 1. We have now s = "0110".
                 Since we can make s equal to target, we return true.
    
    Example 2:
    Input: s = "11", target = "00"
    Output: false
    Explanation: It is not possible to make s equal to target with any number 
                 of operations.

    Constraints:
    * n == s.length == target.length
    * 2 <= n <= 10^5
    * s and target consist of only the digits 0 and 1.*/

    public boolean makeStringsEqual(String s, String target) {
        return s.contains("1") == target.contains("1"); 
    }


    /*2547. Minimum Cost to Split an Array (Hard)
    You are given an integer array nums and an integer k. Split the array into 
    some number of non-empty subarrays. The cost of a split is the sum of the 
    importance value of each subarray in the split. Let trimmed(subarray) be 
    the version of the subarray where all numbers which appear only once are 
    removed.
    * For example, trimmed([3,1,2,4,3,4]) = [3,4,3,4].
    The importance value of a subarray is k + trimmed(subarray).length.
    * For example, if a subarray is [1,2,3,3,3,4,4], then 
      trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4].The importance value of this 
      subarray will be k + 5.
    Return the minimum possible cost of a split of nums. A subarray is a 
    contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [1,2,1,2,1,3,3], k = 2
    Output: 8
    Explanation: We split nums to have two subarrays: [1,2], [1,2,1,3,3].
                 The importance value of [1,2] is 2 + (0) = 2.
                 The importance value of [1,2,1,3,3] is 2 + (2 + 2) = 6.
                 The cost of the split is 2 + 6 = 8. It can be shown that this 
                 is the minimum possible cost among all the possible splits.
    
    Example 2:
    Input: nums = [1,2,1,2,1], k = 2
    Output: 6
    Explanation: We split nums to have two subarrays: [1,2], [1,2,1].
                 The importance value of [1,2] is 2 + (0) = 2.
                 The importance value of [1,2,1] is 2 + (2) = 4.
                 The cost of the split is 2 + 4 = 6. It can be shown that this 
                 is the minimum possible cost among all the possible splits.
    
    Example 3:
    Input: nums = [1,2,1,2,1], k = 5
    Output: 10
    Explanation: We split nums to have one subarray: [1,2,1,2,1].
                 The importance value of [1,2,1,2,1] is 5 + (3 + 2) = 10.
                 The cost of the split is 10. It can be shown that this is the 
                 minimum possible cost among all the possible splits.

    Constraints:
    * 1 <= nums.length <= 1000
    * 0 <= nums[i] < nums.length
    * 1 <= k <= 10^9*/

    public int minCost(int[] nums, int k) {
        int n = nums.length; 
        int[] dp = new int[n+1]; 
        Arrays.fill(dp, Integer.MAX_VALUE); 
        dp[n] = 0; 
        for (int i = n-1; i >= 0; --i) {
            int val = 0; 
            int[] freq = new int[n]; 
            for (int ii = i; ii < n; ++ii) {
                ++freq[nums[ii]]; 
                if (freq[nums[ii]] == 2) val += 2; 
                else if (freq[nums[ii]] > 2) ++val; 
                dp[i] = Math.min(dp[i], k + val + dp[ii+1]); 
            }
        }
        return dp[0]; 
    }


    /*2548. Maximum Price to Fill a Bag (Medium)
    You are given a 2D integer array items where items[i] = [pricei, weighti] 
    denotes the price and weight of the ith item, respectively. You are also 
    given a positive integer capacity. Each item can be divided into two items 
    with ratios part1 and part2, where part1 + part2 == 1.
    * The weight of the first item is weighti * part1 and the price of the 
      first item is pricei * part1.
    * Similarly, the weight of the second item is weighti * part2 and the price 
      of the second item is pricei * part2.
    Return the maximum total price to fill a bag of capacity capacity with 
    given items. If it is impossible to fill a bag return -1. Answers within 
    10^-5 of the actual answer will be considered accepted.

    Example 1:
    Input: items = [[50,1],[10,8]], capacity = 5
    Output: 55.00000
    Explanation: We divide the 2nd item into two parts with part1 = 0.5 and 
                 part2 = 0.5. The price and weight of the 1st item are 5, 4. 
                 And similarly, the price and the weight of the 2nd item are 
                 5, 4. The array items after operation becomes 
                 [[50,1],[5,4],[5,4]]. To fill a bag with capacity 5 we take 
                 the 1st element with a price of 50 and the 2nd element with a 
                 price of 5. It can be proved that 55.0 is the maximum total 
                 price that we can achieve.
    
    Example 2:
    Input: items = [[100,30]], capacity = 50
    Output: -1.00000
    Explanation: It is impossible to fill a bag with the given item.

    Constraints:
    * 1 <= items.length <= 10^5
    * items[i].length == 2
    * 1 <= pricei, weighti <= 10^4
    * 1 <= capacity <= 10^9*/

    public double maxPrice(int[][] items, int capacity) {
        double ans = 0; 
        Arrays.sort(items, (a, b) -> Double.compare((double) b[0]/b[1], (double) a[0]/a[1])); 
        for (var item : items) {
            double p = item[0]; 
            if (item[1] > capacity) p *= (double) capacity/item[1]; 
            ans += p; 
            capacity -= Math.min(item[1], capacity); 
            if (capacity == 0) break; 
        }
        return capacity == 0 ? ans : -1; 
    }


    /*2549. Count Distinct Numbers on Board (Easy)
    You are given a positive integer n, that is initially placed on a board. 
    Every day, for 10^9 days, you perform the following procedure:
    * For each number x present on the board, find all numbers 1 <= i <= n such 
      that x % i == 1.
    * Then, place those numbers on the board.
    Return the number of distinct integers present on the board after 10^9 days 
    have elapsed.

    Note:
    * Once a number is placed on the board, it will remain on it until the end.
    * % stands for the modulo operation. For example, 14 % 3 is 2.

    Example 1:
    Input: n = 5
    Output: 4
    Explanation: Initially, 5 is present on the board. The next day, 2 and 4 
                 will be added since 5 % 2 == 1 and 5 % 4 == 1. After that day, 
                 3 will be added to the board because 4 % 3 == 1. At the end of 
                 a billion days, the distinct numbers on the board will be 2, 3, 
                 4, and 5. 
    
    Example 2:
    Input: n = 3
    Output: 2
    Explanation: Since 3 % 2 == 1, 2 will be added to the board. After a 
                 billion days, the only two distinct numbers on the board are 2 
                 and 3. 

    Constraints: 1 <= n <= 100*/

    public int distinctIntegers(int n) {
        return n > 1 ? n-1 : 1; 
    }


    /*2550. Count Collisions of Monkeys on a Polygon (Medium)
    There is a regular convex polygon with n vertices. The vertices are labeled 
    from 0 to n - 1 in a clockwise direction, and each vertex has exactly one 
    monkey. The following figure shows a convex polygon of 6 vertices. Each 
    monkey moves simultaneously to a neighboring vertex. A neighboring vertex 
    for a vertex i can be:
    * the vertex (i + 1) % n in the clockwise direction, or
    * the vertex (i - 1 + n) % n in the counter-clockwise direction.
    A collision happens if at least two monkeys reside on the same vertex after 
    the movement. Return the number of ways the monkeys can move so that at 
    least one collision happens. Since the answer may be very large, return it 
    modulo 10^9 + 7. Note that each monkey can only move once.

    Example 1:
    Input: n = 3
    Output: 6
    Explanation: There are 8 total possible movements. Two ways such that they 
                 collide at some point are:
                 - Monkey 1 moves in a clockwise direction; monkey 2 moves in 
                   an anticlockwise direction; monkey 3 moves in a clockwise 
                   direction. Monkeys 1 and 2 collide.
                 - Monkey 1 moves in an anticlockwise direction; monkey 2 moves 
                   in an anticlockwise direction; monkey 3 moves in a clockwise 
                   direction. Monkeys 1 and 3 collide.
                 It can be shown 6 total movements result in a collision.
    
    Example 2:
    Input: n = 4
    Output: 14
    Explanation: It can be shown that there are 14 ways for the monkeys to 
                 collide.

    Constraints: 3 <= n <= 10^9*/

    private static long pow(long x, int p, int mod) {
        long ans = 1; 
        for (; p > 0; p >>= 1) {
            if (p % 2 == 1) ans = ans * x % mod; 
            x = x * x % mod; 
        }
        return ans;
    }
    
    public int monkeyMove(int n) {
        final int mod = 1_000_000_007; 
        return (int) (pow(2, n, mod) - 2 + mod) % mod; 
    }


    /*2551. Put Marbles in Bags (Hard)
    You have k bags. You are given a 0-indexed integer array weights where 
    weights[i] is the weight of the ith marble. You are also given the integer 
    k. Divide the marbles into the k bags according to the following rules:
    * No bag is empty.
    * If the ith marble and jth marble are in a bag, then all marbles with an 
      index between the ith and jth indices should also be in that same bag.
    * If a bag consists of all the marbles with an index from i to j 
      inclusively, then the cost of the bag is weights[i] + weights[j].
    The score after distributing the marbles is the sum of the costs of all the 
    k bags. Return the difference between the maximum and minimum scores among 
    marble distributions.

    Example 1:
    Input: weights = [1,3,5,1], k = 2
    Output: 4
    Explanation: The distribution [1],[3,5,1] results in the minimal score of 
                 (1+1) + (3+1) = 6. The distribution [1,3],[5,1], results in 
                 the maximal score of (1+3) + (5+1) = 10. Thus, we return their 
                 difference 10 - 6 = 4.
    
    Example 2:
    Input: weights = [1, 3], k = 2
    Output: 0
    Explanation: The only distribution possible is [1],[3]. Since both the 
                 maximal and minimal score are the same, we return 0.

    Constraints:
    * 1 <= k <= weights.length <= 10^5
    * 1 <= weights[i] <= 10^9*/

    public long putMarbles(int[] weight, int k) {
        if (k == 1) return 0;
        int n = weight.length; 
        int[] vals = new int[n-1]; 
        for (int i = 0; i < n-1; ++i) 
            vals[i] = weight[i] + weight[i+1]; 
        Arrays.sort(vals); 
        long diff = 0; 
        for (int i = 0; i < k-1; ++i) 
            diff += vals[n-2-i] - vals[i]; 
        return diff; 
    }


    /*2552. Count Increasing Quadruplets (Hard)
    Given a 0-indexed integer array nums of size n containing all numbers from 
    1 to n, return the number of increasing quadruplets. A quadruplet 
    (i, j, k, l) is increasing if:
    * 0 <= i < j < k < l < n, and
    * nums[i] < nums[k] < nums[j] < nums[l].

    Example 1:
    Input: nums = [1,3,2,4,5]
    Output: 2
    Explanation: - When i = 0, j = 1, k = 2, and l = 3, 
                   nums[i] < nums[k] < nums[j] < nums[l].
                 - When i = 0, j = 1, k = 2, and l = 4, 
                   nums[i] < nums[k] < nums[j] < nums[l]. 
                 There are no other quadruplets, so we return 2.
    
    Example 2:
    Input: nums = [1,2,3,4]
    Output: 0
    Explanation: There exists only one quadruplet with i = 0, j = 1, k = 2, 
                 l = 3, but since nums[j] < nums[k], we return 0.

    Constraints:
    * 4 <= nums.length <= 4000
    * 1 <= nums[i] <= nums.length
    * All the integers of nums are unique. nums is a permutation.*/

    public long countQuadruplets(int[] nums) {
        long ans = 0; 
        long[] dp = new long[nums.length]; 
        for (int j = 0; j < nums.length; ++j) {
            int prev = 0; 
            for (int i = 0; i < j; ++i) 
                if (nums[i] < nums[j]) {
                    ++prev; 
                    ans += dp[i]; 
                } else if (nums[i] > nums[j]) dp[i] += prev; 
        }
        return ans; 
    }


    /*2553. Separate the Digits in an Array (Easy)
    Given an array of positive integers nums, return an array answer that 
    consists of the digits of each integer in nums after separating them in the 
    same order they appear in nums. To separate the digits of an integer is to 
    get all the digits it has in the same order. For example, for the integer 
    10921, the separation of its digits is [1,0,9,2,1].

    Example 1:
    Input: nums = [13,25,83,77]
    Output: [1,3,2,5,8,3,7,7]
    Explanation: - The separation of 13 is [1,3].
                 - The separation of 25 is [2,5].
                 - The separation of 83 is [8,3].
                 - The separation of 77 is [7,7].
                 answer = [1,3,2,5,8,3,7,7]. Note that answer contains the 
                 separations in the same order.
    
    Example 2:
    Input: nums = [7,1,3,9]
    Output: [7,1,3,9]
    Explanation: The separation of each integer in nums is itself. 
                 answer = [7,1,3,9].

    Constraints:
    * 1 <= nums.length <= 1000
    * 1 <= nums[i] <= 10^5*/

    public int[] separateDigits(int[] nums) {
        List<Integer> ans = new ArrayList(); 
        for (int i = nums.length-1; i >= 0; --i) 
            for (int x = nums[i]; x > 0; x /= 10) 
                ans.add(x % 10); 
        Collections.reverse(ans); 
        return ans.stream().mapToInt(i->i).toArray(); 
    }


    /*2554. Maximum Number of Integers to Choose From a Range I (Medium)
    You are given an integer array banned and two integers n and maxSum. You 
    are choosing some number of integers following the below rules:
    * The chosen integers have to be in the range [1, n].
    * Each integer can be chosen at most once.
    * The chosen integers should not be in the array banned.
    * The sum of the chosen integers should not exceed maxSum.
    Return the maximum number of integers you can choose following the 
    mentioned rules.

    Example 1:
    Input: banned = [1,6,5], n = 5, maxSum = 6
    Output: 2
    Explanation: You can choose the integers 2 and 4. 2 and 4 are from the 
                 range [1, 5], both did not appear in banned, and their sum is 
                 6, which did not exceed maxSum.
    
    Example 2:
    Input: banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1
    Output: 0
    Explanation: You cannot choose any integer while following the mentioned 
                 conditions.
    
    Example 3:
    Input: banned = [11], n = 7, maxSum = 50
    Output: 7
    Explanation: You can choose the integers 1, 2, 3, 4, 5, 6, and 7. They are 
                 from the range [1, 7], all did not appear in banned, and their 
                 sum is 28, which did not exceed maxSum.

    Constraints:
    * 1 <= banned.length <= 10^4
    * 1 <= banned[i], n <= 10^4
    * 1 <= maxSum <= 10^9*/

    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> tabu = new HashSet();
        for (var x : banned) tabu.add(x); 
        int ans = 0; 
        for (int x = 1; x <= n && x <= maxSum; ++x) 
            if (!tabu.contains(x)) {
                maxSum -= x; 
                ++ans; 
            }
        return ans; 
    }


    /*2555. Maximize Win From Two Segments (Medium)
    There are some prizes on the X-axis. You are given an integer array 
    prizePositions that is sorted in non-decreasing order, where 
    prizePositions[i] is the position of the ith prize. There could be 
    different prizes at the same position on the line. You are also given an 
    integer k. You are allowed to select two segments with integer endpoints. 
    The length of each segment must be k. You will collect all prizes whose 
    position falls within at least one of the two selected segments (including 
    the endpoints of the segments). The two selected segments may intersect.
    For example if k = 2, you can choose segments [1, 3] and [2, 4], and you 
    will win any prize i that satisfies 1 <= prizePositions[i] <= 3 or 
    2 <= prizePositions[i] <= 4. Return the maximum number of prizes you can 
    win if you choose the two segments optimally.

    Example 1:
    Input: prizePositions = [1,1,2,2,3,3,5], k = 2
    Output: 7
    Explanation: In this example, you can win all 7 prizes by selecting two 
                 segments [1, 3] and [3, 5].
    
    Example 2:
    Input: prizePositions = [1,2,3,4], k = 0
    Output: 2
    Explanation: For this example, one choice for the segments is [3, 3] and 
                 [4, 4], and you will be able to get 2 prizes. 

    Constraints:
    * 1 <= prizePositions.length <= 10^5
    * 1 <= prizePositions[i] <= 10^9
    * 0 <= k <= 10^9
    * prizePositions is sorted in non-decreasing order.*/

    public int maximizeWin(int[] prizePositions, int k) {
        int ans = 0, n = prizePositions.length; 
        int[] dp = new int[n+1]; 
        for (int i = 0, ii = 0; i < n; ++i) {
            while (prizePositions[i] - prizePositions[ii] > k) ++ii; 
            ans = Math.max(ans, dp[ii] + i - ii + 1); 
            dp[i+1] = Math.max(dp[i], i - ii + 1); 
        }
        return ans; 
    }


    /*2556. Disconnect Path in a Binary Matrix by at Most One Flip (Medium)
    You are given a 0-indexed m x n binary matrix grid. You can move from a 
    cell (row, col) to any of the cells (row + 1, col) or (row, col + 1) that 
    has the value 1. The matrix is disconnected if there is no path from 
    (0, 0) to (m - 1, n - 1). You can flip the value of at most one (possibly 
    none) cell. You cannot flip the cells (0, 0) and (m - 1, n - 1). Return 
    true if it is possible to make the matrix disconnect or false otherwise.
    Note that flipping a cell changes its value from 0 to 1 or from 1 to 0.

    Example 1:
    Input: grid = [[1,1,1],[1,0,0],[1,1,1]]
    Output: true
    Explanation: We can change the cell shown in the diagram above. There is no 
                 path from (0, 0) to (2, 2) in the resulting grid.
    
    Example 2:
    Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
    Output: false
    Explanation: It is not possible to change at most one cell such that there 
                 is not path from (0, 0) to (2, 2).

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 1 <= m, n <= 1000
    * 1 <= m * n <= 10^5
    * grid[i][j] is either 0 or 1.
    * grid[0][0] == grid[m - 1][n - 1] == 1*/

    public boolean isPossibleToCutPath(int[][] grid) {
        int m = grid.length, n = grid[0].length; 
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if ((i > 0 || j > 0) && (i == 0 || grid[i-1][j] == 0) && (j == 0 || grid[i][j-1] == 0)) grid[i][j] = 0; 
        for (int i = m-1; i >= 0; --i)
            for (int j = n-1; j >= 0; --j)
                if ((i < m-1 || j < n-1) && (i == m-1 || grid[i+1][j] == 0) && (j == n-1 || grid[i][j+1] == 0)) grid[i][j] = 0; 
        int[] freq = new int[m+n-1]; 
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                freq[i+j] += grid[i][j]; 
        for (int i = 1; i < m+n-2; ++i)
            if (freq[i] <= 1) return true; 
        return false; 
    }


    /*2557. Maximum Number of Integers to Choose From a Range II (Medium)
    You are given an integer array banned and two integers n and maxSum. You 
    are choosing some number of integers following the below rules:
    * The chosen integers have to be in the range [1, n].
    * Each integer can be chosen at most once.
    * The chosen integers should not be in the array banned.
    * The sum of the chosen integers should not exceed maxSum.
    Return the maximum number of integers you can choose following the 
    mentioned rules.

    Example 1:
    Input: banned = [1,4,6], n = 6, maxSum = 4
    Output: 1
    Explanation: You can choose the integer 2 and 3. 2 and 3 are are in the 
                 range [1, 6], both do not appear in banned, and their sum is 5, 
                 which does not exceed maxSum.
    
    Example 2:
    Input: banned = [4,3,5,6], n = 7, maxSum = 18
    Output: 3
    Explanation: You can choose the integers 1, 2, 3 and 7. All these integers 
                 are in the range [1, 7], all do not appear in banned, and 
                 their sum is 13, which does not exceed maxSum.

    Constraints:
    * 1 <= banned.length <= 10^5
    * 1 <= banned[i] <= n <= 10^9
    * 1 <= maxSum <= 10^15*/

    public int maxCount(int[] banned, int n, long maxSum) {
        int lo = 0, hi = n; 
        while (lo < hi) {
            int mid = lo + (hi - lo + 1)/2; 
            long total = (long) mid*(mid+1)/2; 
            for (var x : banned) 
                if (x <= mid) total -= x; 
            if (total <= maxSum) lo = mid; 
            else hi = mid-1; 
        }
        int ans = lo; 
        for (var x : banned)
            if (x <= lo) --ans; 
        return ans; 
    }


    /*2558. Take Gifts From the Richest Pile (Easy)
    You are given an integer array gifts denoting the number of gifts in 
    various piles. Every second, you do the following:
    * Choose the pile with the maximum number of gifts.
    * If there is more than one pile with the maximum number of gifts, choose 
      any.
    * Leave behind the floor of the square root of the number of gifts in the 
      pile. Take the rest of the gifts.
    Return the number of gifts remaining after k seconds.

    Example 1:
    Input: gifts = [25,64,9,4,100], k = 4
    Output: 29
    Explanation: The gifts are taken in the following way:
                 - In the first second, the last pile is chosen and 10 gifts 
                   are left behind.
                 - Then the second pile is chosen and 8 gifts are left behind.
                 - After that the first pile is chosen and 5 gifts are left 
                   behind.
                 - Finally, the last pile is chosen again and 3 gifts are left 
                   behind.
                 The final remaining gifts are [5,8,9,4,3], so the total number 
                 of gifts remaining is 29.
    
    Example 2:
    Input: gifts = [1,1,1,1], k = 4
    Output: 4
    Explanation: In this case, regardless which pile you choose, you have to 
                 leave behind 1 gift in each pile. That is, you can't take any 
                 pile with you. So, the total gifts remaining are 4.

    Constraints:
    * 1 <= gifts.length <= 10^3
    * 1 <= gifts[i] <= 10^9
    * 1 <= k <= 10^3*/

    public long pickGifts(int[] gifts, int k) {
        long ans = 0; 
        Queue<Integer> pq = new PriorityQueue(Collections.reverseOrder()); 
        for (var x : gifts) {
            ans += x; 
            pq.add(x);
        }        
        while (k-- > 0) {
            int vv = pq.poll(), v = (int) Math.sqrt(vv); 
            ans -= vv - v; 
            pq.add(v); 
        }
        return ans; 
    }


    /*2559. Count Vowel Strings in Ranges (Medium)
    You are given a 0-indexed array of strings words and a 2D array of integers 
    queries. Each query queries[i] = [li, ri] asks us to find the number of 
    strings present in the range li to ri (both inclusive) of words that start 
    and end with a vowel. Return an array ans of size queries.length, where 
    ans[i] is the answer to the ith query. Note that the vowel letters are 
    'a', 'e', 'i', 'o', and 'u'.

    Example 1:
    Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
    Output: [2,3,0]
    Explanation: The strings starting and ending with a vowel are "aba", "ece", 
                 "aa" and "e". The answer to the query [0,2] is 2 (strings 
                 "aba" and "ece"). 
                 to query [1,4] is 3 (strings "ece", "aa", "e").
                 to query [1,1] is 0.
                 We return [2,3,0].
    
    Example 2:
    Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
    Output: [3,2,1]
    Explanation: Every string satisfies the conditions, so we return [3,2,1].

    Constraints:
    * 1 <= words.length <= 10^5
    * 1 <= words[i].length <= 40
    * words[i] consists only of lowercase English letters.
    * sum(words[i].length) <= 3 * 10^5
    * 1 <= queries.length <= 10^5
    * 0 <= li <= ri < words.length*/

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length; 
        int[] prefix = new int[n+1]; 
        for (int i = 0; i < n; ++i) {
            prefix[i+1] = prefix[i]; 
            if ("aeiou".indexOf(words[i].charAt(0)) >= 0 && "aeiou".indexOf(words[i].charAt(words[i].length()-1)) >= 0) ++prefix[i+1]; 
        }
        int[] ans = new int[queries.length]; 
        for (int i = 0; i < queries.length; ++i) 
            ans[i] = prefix[queries[i][1]+1] - prefix[queries[i][0]]; 
        return ans; 
    }


    /*2560. House Robber IV (Medium)
    There are several consecutive houses along a street, each of which has some 
    money inside. There is also a robber, who wants to steal money from the 
    homes, but he refuses to steal from adjacent homes. The capability of the 
    robber is the maximum amount of money he steals from one house of all the 
    houses he robbed. You are given an integer array nums representing how much 
    money is stashed in each house. More formally, the ith house from the left 
    has nums[i] dollars. You are also given an integer k, representing the 
    minimum number of houses the robber will steal from. It is always possible 
    to steal at least k houses. Return the minimum capability of the robber out 
    of all the possible ways to steal at least k houses.

    Example 1:
    Input: nums = [2,3,5,9], k = 2
    Output: 5
    Explanation: There are three ways to rob at least 2 houses:
                 - Rob the houses at indices 0 and 2. Capability is 
                   max(nums[0], nums[2]) = 5.
                 - Rob the houses at indices 0 and 3. Capability is 
                   max(nums[0], nums[3]) = 9.
                 - Rob the houses at indices 1 and 3. Capability is 
                   max(nums[1], nums[3]) = 9.
                 Therefore, we return min(5, 9, 9) = 5.
    
    Example 2:
    Input: nums = [2,7,9,3,1], k = 2
    Output: 2
    Explanation: There are 7 ways to rob the houses. The way which leads to 
                 minimum capability is to rob the house at index 0 and 4. 
                 Return max(nums[0], nums[4]) = 2.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9
    * 1 <= k <= (nums.length + 1)/2*/

    public int minCapability(int[] nums, int k) {
        int lo = 0, hi = 1_000_000_000; 
        while (lo < hi) {
            int mid = (lo + hi)/2, cnt = 0, prev = Integer.MIN_VALUE; 
            for (int i = 0; i < nums.length; ++i) 
                if (nums[i] <= mid && prev+1 < i) {
                    ++cnt; 
                    prev = i; 
                }
            if (cnt < k) lo = mid + 1; 
            else hi = mid; 
        }
        return lo; 
    }


    /*2561. Rearranging Fruits (Hard)
    You have two fruit baskets containing n fruits each. You are given two 0-
    indexed integer arrays basket1 and basket2 representing the cost of fruit 
    in each basket. You want to make both baskets equal. To do so, you can use 
    the following operation as many times as you want:
    * Chose two indices i and j, and swap the ith fruit of basket1 with the jth 
      fruit of basket2.
    * The cost of the swap is min(basket1[i],basket2[j]).
    Two baskets are considered equal if sorting them according to the fruit 
    cost makes them exactly the same baskets. Return the minimum cost to make 
    both the baskets equal or -1 if impossible.

    Example 1:
    Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
    Output: 1
    Explanation: Swap index 1 of basket1 with index 0 of basket2, which has 
                 cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. 
                 Rearranging both the arrays makes them equal.
    
    Example 2:
    Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
    Output: -1
    Explanation: It can be shown that it is impossible to make both the baskets 
                 equal.

    Constraints:
    * basket1.length == bakste2.length
    * 1 <= basket1.length <= 10^5
    * 1 <= basket1[i],basket2[i] <= 10^9*/

    public long minCost(int[] basket1, int[] basket2) {
        System.out.println(-5%2); 
        TreeMap<Integer, Integer> freq = new TreeMap(); 
        for (var x : basket1) freq.merge(x, 1, Integer::sum); 
        for (var x : basket2) freq.merge(x, -1, Integer::sum); 
        int total = 0, m = Integer.MAX_VALUE; 
        for (var elem : freq.entrySet()) {
            int k = elem.getKey(), v = Math.abs(elem.getValue()); 
            m = Math.min(m, k); 
            if (v%2 == 1) return -1; 
            total += v/2; 
        }
        total /= 2; 
        long ans = 0; 
        for (var elem : freq.entrySet()) {
            int k = elem.getKey(), v = Math.abs(elem.getValue()); 
            v = Math.min(v/2, total); 
            ans += (long) v * Math.min(2*m, k); 
            total -= v; 
        }
        return ans; 
    }


    /*2562. Find the Array Concatenation Value (Easy)
    You are given a 0-indexed integer array nums. The concatenation of two 
    numbers is the number formed by concatenating their numerals.
    * For example, the concatenation of 15, 49 is 1549.
    The concatenation value of nums is initially equal to 0. Perform this 
    operation until nums becomes empty:
    * If there exists more than one number in nums, pick the first element and 
      last element in nums respectively and add the value of their 
      concatenation to the concatenation value of nums, then delete the first 
      and last element from nums.
    * If one element exists, add its value to the concatenation value of nums, 
      then delete it.
    Return the concatenation value of the nums.

    Example 1:
    Input: nums = [7,52,2,4]
    Output: 596
    Explanation: Before performing any operation, nums is [7,52,2,4] and 
                 concatenation value is 0.
                  - In the first operation:
                 We pick the first element, 7, and the last element, 4. Their 
                 concatenation is 74, and we add it to the concatenation value, 
                 so it becomes equal to 74. Then we delete them from nums, so 
                 nums becomes equal to [52,2].
                  - In the second operation:
                 We pick the first element, 52, and the last element, 2. Their 
                 concatenation is 522, and we add it to the concatenation value, 
                 so it becomes equal to 596. Then we delete them from the nums, 
                 so nums becomes empty. Since the concatenation value is 596 so 
                 the answer is 596.
    
    Example 2:
    Input: nums = [5,14,13,8,12]
    Output: 673
    Explanation: Before performing any operation, nums is [5,14,13,8,12] and 
                 concatenation value is 0.
                  - In the first operation:
                 We pick the first element, 5, and the last element, 12. Their 
                 concatenation is 512, and we add it to the concatenation value, 
                 so it becomes equal to 512. Then we delete them from the nums, 
                 so nums becomes equal to [14,13,8].
                  - In the second operation:
                 We pick the first element, 14, and the last element, 8. Their 
                 concatenation is 148, and we add it to the concatenation value, 
                 so it becomes equal to 660. Then we delete them from the nums, 
                 so nums becomes equal to [13].
                  - In the third operation:
                 nums has only one element, so we pick 13 and add it to the 
                 concatenation value, so it becomes equal to 673. Then we 
                 delete it from nums, so nums become empty. Since the 
                 concatenation value is 673 so the answer is 673.

    Constraints:
    * 1 <= nums.length <= 1000
    * 1 <= nums[i] <= 10^4*/

    public long findTheArrayConcVal(int[] nums) {
        long ans = 0; 
        for (int i = 0, n = nums.length; i < (n+1)/2; ++i) 
            if (i == n-1-i) ans += nums[i]; 
            else {
                int m = 1; 
                for (int x = nums[n-1-i]; x > 0; x /= 10, m *= 10); 
                ans += m * nums[i] + nums[n-1-i]; 
            }
        return ans; 
    }


    /*2563. Count the Number of Fair Pairs (Medium)
    Given a 0-indexed integer array nums of size n and two integers lower and 
    upper, return the number of fair pairs. A pair (i, j) is fair if:
    * 0 <= i < j < n, and
    * lower <= nums[i] + nums[j] <= upper

    Example 1:
    Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
    Output: 6
    Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and 
                 (1,5).

    Example 2:
    Input: nums = [1,7,9,2,5], lower = 11, upper = 11
    Output: 1
    Explanation: There is a single fair pair: (2,3).

    Constraints:
    * 1 <= nums.length <= 10^5
    * nums.length == n
    * -10^9 <= nums[i] <= 10^9
    * -10^9 <= lower <= upper <= 10^9*/

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums); 
        long ans = 0; 
        for (int i = 0, n = nums.length, lo = n-1, hi = n-1; i < n; ++i) {
            while (0 <= hi && nums[i] + nums[hi] > upper) --hi;
            while (0 <= lo && nums[i] + nums[lo] >= lower) --lo; 
            ans += hi - lo; 
            if (lo < i && i <= hi) --ans; 
        }
        return ans/2; 
    }


    /*2564. Substring XOR Queries (Medium)
    You are given a binary string s, and a 2D integer array queries where 
    queries[i] = [firsti, secondi]. For the ith query, find the shortest 
    substring of s whose decimal value, val, yields secondi when bitwise XORed 
    with firsti. In other words, val ^ firsti == secondi. The answer to the ith 
    query is the endpoints (0-indexed) of the substring [lefti, righti] or 
    [-1, -1] if no such substring exists. If there are multiple answers, choose 
    the one with the minimum lefti. Return an array ans where 
    ans[i] = [lefti, righti] is the answer to the ith query. A substring is a 
    contiguous non-empty sequence of characters within a string.

    Example 1:
    Input: s = "101101", queries = [[0,5],[1,2]]
    Output: [[0,2],[2,3]]
    Explanation: For the first query the substring in range [0,2] is "101" 
                 which has a decimal value of 5, and 5 ^ 0 = 5, hence the 
                 answer to the first query is [0,2]. In the second query, the 
                 substring in range [2,3] is "11", and has a decimal value of 3, 
                 and 3 ^ 1 = 2. So, [2,3] is returned for the second query. 

    Example 2:
    Input: s = "0101", queries = [[12,8]]
    Output: [[-1,-1]]
    Explanation: In this example there is no substring that answers the query, 
                 hence [-1,-1] is returned.
    
    Example 3:
    Input: s = "1", queries = [[4,5]]
    Output: [[0,0]]
    Explanation: For this example, the substring in range [0,0] has a decimal 
                 value of 1, and 1 ^ 4 = 5. So, the answer is [0,0].

    Constraints:
    * 1 <= s.length <= 10^4
    * s[i] is either '0' or '1'.
    * 1 <= queries.length <= 10^5
    * 0 <= firsti, secondi <= 10^9*/

    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Integer, int[]> seen = new HashMap(); 
        for (int i = 0, n = s.length(); i < n; ++i) 
            if (s.charAt(i) == '1') {
                int val = 0; 
                for (int j = i; j < n && j < i+30; ++j) {
                    val <<= 1; 
                    if (s.charAt(j) == '1') val ^= 1; 
                    if (!seen.containsKey(val)) seen.put(val, new int[]{i, j}); 
                } 
            } else if (!seen.containsKey(0)) seen.put(0, new int[]{i, i}); 
        int[][] ans = new int[queries.length][2]; 
        for (int i = 0; i < queries.length; ++i) 
            ans[i] = seen.getOrDefault(queries[i][0] ^ queries[i][1], new int[]{-1, -1}); 
        return ans; 
    }


    /*2565. Subsequence With the Minimum Score (Hard)
    You are given two strings s and t. You are allowed to remove any number of 
    characters from the string t. The score string is 0 if no characters are 
    removed from the string t, otherwise:
    * Let left be the minimum index among all removed characters.
    * Let right be the maximum index among all removed characters.
    Then the score of the string is right - left + 1. Return the minimum 
    possible score to make t a subsequence of s. A subsequence of a string is a 
    new string that is formed from the original string by deleting some (can be 
    none) of the characters without disturbing the relative positions of the 
    remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" 
    is not).

    Example 1:
    Input: s = "abacaba", t = "bzaa"
    Output: 1
    Explanation: In this example, we remove the character "z" at index 1 
                 (0-indexed). The string t becomes "baa" which is a subsequence 
                 of the string "abacaba" and the score is 1 - 1 + 1 = 1. It can 
                 be proven that 1 is the minimum score that we can achieve.
    
    Example 2:
    Input: s = "cde", t = "xyz"
    Output: 3
    Explanation: In this example, we remove characters "x", "y" and "z" at 
                 indices 0, 1, and 2 (0-indexed). The string t becomes "" which 
                 is a subsequence of the string "cde" and the score is 
                 2 - 0 + 1 = 3. It can be proven that 3 is the minimum score 
                 that we can achieve.

    Constraints:
    * 1 <= s.length, t.length <= 10^5
    * s and t consist of only lowercase English letters.*/

    public int minimumScore(String s, String t) {
        int m = s.length(), n = t.length(); 
        int[] prefix = new int[m]; 
        int j = 0; 
        for (int i = 0; i < m; ++i) {
            if (j < n && s.charAt(i) == t.charAt(j)) ++j; 
            prefix[i] = j; 
        }
        int ans = n - j; 
        j = n; 
        for (int i = m-1; i >= 0; --i) {
            ans = Math.min(ans, Math.max(0, j - prefix[i])); 
            if (0 < j && s.charAt(i) == t.charAt(j-1)) --j; 
        }
        return Math.min(ans, j); 
    }


    /*2566. Maximum Difference by Remapping a Digit (Easy)
    You are given an integer num. You know that Danny Mittal will sneakily 
    remap one of the 10 possible digits (0 to 9) to another digit. Return the 
    difference between the maximum and minimum values Danny can make by 
    remapping exactly one digit in num.

    Notes:
    * When Danny remaps a digit d1 to another digit d2, Danny replaces all 
      occurrences of d1 in num with d2.
    * Danny can remap a digit to itself, in which case num does not change.
    * Danny can remap different digits for obtaining minimum and maximum values 
      respectively.
    * The resulting number after remapping can contain leading zeroes.
    * We mentioned "Danny Mittal" to congratulate him on being in the top 10 in 
      Weekly Contest 326.

    Example 1:
    Input: num = 11891
    Output: 99009
    Explanation: To achieve the maximum value, Danny can remap the digit 1 to 
                 the digit 9 to yield 99899. To achieve the minimum value, 
                 Danny can remap the digit 1 to the digit 0, yielding 890. The 
                 difference between these two numbers is 99009.
    
    Example 2:
    Input: num = 90
    Output: 99
    Explanation: The maximum value that can be returned by the function is 99 
                 (if 0 is replaced by 9) and the minimum value that can be 
                 returned by the function is 0 (if 9 is replaced by 0). Thus, 
                 we return 99.

    Constraints: 1 <= num <= 10^8*/

    public int findBug(int num) {
        List<Integer> digits = new ArrayList(); 
        int v = 9, diff = 0; 
        for (int x = num; x > 0; x /= 10) {
            int d = x % 10; 
            if (d < 9) v = d; 
            digits.add(d); 
        }
        Collections.reverse(digits); 
        for (var x : digits) {
            diff = 10*diff; 
            if (x == digits.get(0)) diff += x; 
            if (x == v) diff += 9-x; 
        }
        return diff; 
    }


    /*2567. Minimum Score by Changing Two Elements (Medium)
    You are given a 0-indexed integer array nums.
    * The low score of nums is the minimum value of |nums[i] - nums[j]| over 
      all 0 <= i < j < nums.length.
    * The high score of nums is the maximum value of |nums[i] - nums[j]| over 
      all 0 <= i < j < nums.length.
    * The score of nums is the sum of the high and low scores of nums.
    To minimize the score of nums, we can change the value of at most two 
    elements of nums. Return the minimum possible score after changing the 
    value of at most two elements of nums. Note that |x| denotes the absolute 
    value of x.

    Example 1:
    Input: nums = [1,4,3]
    Output: 0
    Explanation: Change value of nums[1] and nums[2] to 1 so that nums becomes 
                 [1,1,1]. Now, the value of |nums[i] - nums[j]| is always equal 
                 to 0, so we return 0 + 0 = 0.
    
    Example 2:
    Input: nums = [1,4,7,8,5]
    Output: 3
    Explanation: Change nums[0] and nums[1] to be 6. Now nums becomes 
                 [6,6,7,8,5]. Our low score is achieved when i = 0 and j = 1, 
                 in which case |nums[i] - nums[j]| = |6 - 6| = 0. Our high 
                 score is achieved when i = 3 and j = 4, in which case 
                 |nums[i] - nums[j]| = |8 - 5| = 3. The sum of our high and low 
                 score is 3, which we can prove to be minimal.

    Constraints:
    * 3 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9*/

    public int minimizeSum(int[] nums) {
        Arrays.sort(nums); 
        int n = nums.length; 
        return Math.min(nums[n-1]-nums[2], Math.min(nums[n-2]-nums[1], nums[n-3]-nums[0])); 
    }


    /*2568. Minimum Impossible OR (Medium)
    You are given a 0-indexed integer array nums. We say that an integer x is 
    expressible from nums if there exist some integers 
    0 <= index1 < index2 < ... < indexk < nums.length for which 
    nums[index1] | nums[index2] | ... | nums[indexk] = x. In other words, an 
    integer is expressible if it can be written as the bitwise OR of some 
    subsequence of nums. Return the minimum positive non-zero integer that is 
    not expressible from nums.

    Example 1:
    Input: nums = [2,1]
    Output: 4
    Explanation: 1 and 2 are already present in the array. We know that 3 is 
                 expressible, since nums[0] | nums[1] = 2 | 1 = 3. Since 4 is 
                 not expressible, we return 4.
    
    Example 2:
    Input: nums = [5,3,2]
    Output: 1
    Explanation: We can show that 1 is the smallest number that is not 
                 expressible.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9*/

    public int minImpossibleOR(int[] nums) {
        int mask = 0; 
        for (var x : nums) 
            if ((x & x-1) == 0) mask |= x; 
        for (int i = 0; i < 32; ++i)
            if ((mask & 1<<i) == 0) return 1<<i; 
        return -1; 
    }


    /*2569. Handling Sum Queries After Update (Hard)
    You are given two 0-indexed arrays nums1 and nums2 and a 2D array queries 
    of queries. There are three types of queries:
    * For a query of type 1, queries[i] = [1, l, r]. Flip the values from 0 to 
      1 and from 1 to 0 in nums1 from index l to index r. Both l and r are 0-
      indexed.
    * For a query of type 2, queries[i] = [2, p, 0]. For every index 0 <= i < n, 
      set nums2[i] = nums2[i] + nums1[i] * p.
    * For a query of type 3, queries[i] = [3, 0, 0]. Find the sum of the 
      elements in nums2.
    Return an array containing all the answers to the third type queries.

    Example 1:
    Input: nums1 = [1,0,1], nums2 = [0,0,0], queries = [[1,1,1],[2,1,0],[3,0,0]]
    Output: [3]
    Explanation: After the first query nums1 becomes [1,1,1]. After the second 
                 query, nums2 becomes [1,1,1], so the answer to the third query 
                 is 3. Thus, [3] is returned.
    
    Example 2:
    Input: nums1 = [1], nums2 = [5], queries = [[2,0,0],[3,0,0]]
    Output: [5]
    Explanation: After the first query, nums2 remains [5], so the answer to the 
                 second query is 5. Thus, [5] is returned.

    Constraints:
    * 1 <= nums1.length,nums2.length <= 10^5
    * nums1.length = nums2.length
    * 1 <= queries.length <= 10^5
    * queries[i].length = 3
    * 0 <= l <= r <= nums1.length - 1
    * 0 <= p <= 10^6
    * 0 <= nums1[i] <= 1
    * 0 <= nums2[i] <= 10^9

class SegTreeLazy {
    private int n = 0; 
    private int[] tree, lazy; 
    
    private void build(int[] arr, int k, int lo, int hi) {
        if (lo+1 == hi) tree[k] = arr[lo]; 
        else {
            int mid = lo + (hi-lo)/2; 
            build(arr, 2*k+1, lo, mid); 
            build(arr, 2*k+2, mid, hi); 
            tree[k] = tree[2*k+1] + tree[2*k+2]; 
        }
    }

    public SegTreeLazy(int[] arr) {
        n = arr.length; 
        tree = new int[4*n]; 
        lazy = new int[4*n]; 
        build(arr, 0, 0, n); 
    }

    public void update(int qlo, int qhi, int k, int lo, int hi) {
        if (hi == 0) hi = n; 
        if (lazy[k] > 0) {
            tree[k] = (hi - lo) - tree[k]; 
            if (lo+1 < hi) {
                lazy[2*k+1] ^= 1; 
                lazy[2*k+2] ^= 1;
            }
            lazy[k] = 0; 
        }
        if (lo < hi && qlo < hi && lo < qhi) 
            if (qlo <= lo && hi <= qhi) {
                tree[k] = (hi - lo) - tree[k]; 
                if (lo+1 < hi) {
                    lazy[2*k+1] ^= 1; 
                    lazy[2*k+2] ^= 1; 
                }
            } else {
                int mid = lo + (hi - lo)/2; 
                update(qlo, qhi, 2*k+1, lo, mid); 
                update(qlo, qhi, 2*k+2, mid, hi); 
                tree[k] = tree[2*k+1] + tree[2*k+2]; 
            }
    }

    public int query() {
        return tree[0]; 
    }
}*/

    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        SegTreeLazy tree = new SegTreeLazy(nums1); 
        List<Long> ans = new ArrayList(); 
        long val = 0;
        for (var x : nums2) val += x; 
        for (var q : queries) {
            if (q[0] == 1) tree.update(q[1], q[2]+1, 0, 0, 0); 
            else if (q[0] == 2) val += (long) q[1] * tree.query(); 
            else ans.add(val); 
        }
        return ans.stream().mapToLong(i->i).toArray();
    }


    /*2570. Merge Two 2D Arrays by Summing Values (Easy)
    You are given two 2D integer arrays nums1 and nums2.
    * nums1[i] = [idi, vali] indicate that the number with the id idi has a 
      value equal to vali.
    * nums2[i] = [idi, vali] indicate that the number with the id idi has a 
      value equal to vali.
    Each array contains unique ids and is sorted in ascending order by id. 
    Merge the two arrays into one array that is sorted in ascending order by 
    id, respecting the following conditions:
    * Only ids that appear in at least one of the two arrays should be included 
      in the resulting array.
    * Each id should be included only once and its value should be the sum of 
      the values of this id in the two arrays. If the id does not exist in one 
      of the two arrays then its value in that array is considered to be 0.
    Return the resulting array. The returned array must be sorted in ascending 
    order by id.

    Example 1:
    Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
    Output: [[1,6],[2,3],[3,2],[4,6]]
    Explanation: The resulting array contains the following:
                 - id = 1, the value of this id is 2 + 4 = 6.
                 - id = 2, the value of this id is 3.
                 - id = 3, the value of this id is 2.
                 - id = 4, the value of this id is 5 + 1 = 6.
    
    Example 2:
    Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
    Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
    Explanation: There are no common ids, so we just include each id with its 
                 value in the resulting list.

    Constraints:
    * 1 <= nums1.length, nums2.length <= 200
    * nums1[i].length == nums2[j].length == 2
    * 1 <= idi, vali <= 1000
    * Both arrays contain unique ids.
    * Both arrays are in strictly ascending order by id.*/

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> ans = new ArrayList(); 
        for (int i = 0, j = 0, n1 = nums1.length, n2 = nums2.length; i < n1 || j < n2; ) 
            if (j == n2 || i < n1 && nums1[i][0] <= nums2[j][0]) ans.add(nums1[i++]); 
            else {
                if (i+j > 0 && ans.get(ans.size()-1)[0] == nums2[j][0]) ans.get(ans.size()-1)[1] += nums2[j][1]; 
                else ans.add(nums2[j]); 
                ++j; 
            }
        return ans.stream().map(x -> x).toArray(int[][]::new);
    }


    /*2571. Minimum Operations to Reduce an Integer to 0 (Easy)
    You are given a positive integer n, you can do the following operation any 
    number of times:
    * Add or subtract a power of 2 from n.
    Return the minimum number of operations to make n equal to 0. A number x is 
    power of 2 if x == 2i where i >= 0.

    Example 1:
    Input: n = 39
    Output: 3
    Explanation: We can do the following operations:
                 - Add 20 = 1 to n, so now n = 40.
                 - Subtract 23 = 8 from n, so now n = 32.
                 - Subtract 25 = 32 from n, so now n = 0.
                 It can be shown that 3 is the minimum number of operations we 
                 need to make n equal to 0.
    
    Example 2:
    Input: n = 54
    Output: 3
    Explanation: We can do the following operations:
                 - Add 21 = 2 to n, so now n = 56.
                 - Add 23 = 8 to n, so now n = 64.
                 - Subtract 26 = 64 from n, so now n = 0.
                 So the minimum number of operations is 3.

    Constraints: 1 <= n <= 10^5*/

    public int minOperations(int n) {
        int ans = 0, carry = 0; 
        for (; n > 0; n >>= 1) {
            carry += n & 1; 
            if (carry == 1) {
                if ((n&2) == 0) carry = 0; 
                ++ans; 
            } else if (carry == 2) carry = 1; 
        }
        return ans + carry; 
    }


    /*2572. Count the Number of Square-Free Subsets (Medium)
    You are given a positive integer 0-indexed array nums. A subset of the 
    array nums is square-free if the product of its elements is a square-free 
    integer. A square-free integer is an integer that is divisible by no square 
    number other than 1. Return the number of square-free non-empty subsets of 
    the array nums. Since the answer may be too large, return it modulo 
    10^9 + 7. A non-empty subset of nums is an array that can be obtained by 
    deleting some (possibly none but not all) elements from nums. Two subsets 
    are different if and only if the chosen indices to delete are different.

    Example 1:
    Input: nums = [3,4,4,5]
    Output: 3
    Explanation: There are 3 square-free subsets in this example:
                 - The subset consisting of the 0th element [3]. The product of 
                   its elements is 3, which is a square-free integer.
                 - The subset consisting of the 3rd element [5]. The product of 
                   its elements is 5, which is a square-free integer.
                 - The subset consisting of 0th and 3rd elements [3,5]. The 
                   product of its elements is 15, which is a square-free 
                   integer.
                 It can be proven that there are no more than 3 square-free 
                 subsets in the given array.
    
    Example 2:
    Input: nums = [1]
    Output: 1
    Explanation: There is 1 square-free subset in this example:
                 - The subset consisting of the 0th element [1]. The product of 
                   its elements is 1, which is a square-free integer.
                 It can be proven that there is no more than 1 square-free 
                 subset in the given array.

    Constraints:
    * 1 <= nums.length <= 1000
    * 1 <= nums[i] <= 30*/

    public int squareFreeSubsets(int[] nums) {
        final int mod = 1_000_000_007; 
        int[] freq = new int[31], mask = new int[31], primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}; 
        for (var x : nums) ++freq[x]; 
        long pow = 1; 
        for (long p = freq[1], v = 2; p > 0; p >>= 1, v = v*v % mod) 
            if (p % 2 == 1) pow = pow * v % mod; 
        for (int x = 2; x <= 30; ++x) {
            int m = 0, v = 1; 
            for (int i = 0; i < 10; ++i)
                if (x % primes[i] == 0) {
                    v *= primes[i]; 
                    m ^= 1<<i; 
                }
            if (v == x) mask[x] = m; 
        }
        long[][] dp = new long[1<<10][32]; 
        for (int j = 0; j <= 31; ++j) dp[0][j] = 1; 
        for (int i = 1; i < 1<<10; ++i) 
            for (int j = 2; j <= 30; ++j)
                for (int jj = j; jj <= 30; ++jj)
                    if (mask[jj] > 0 && (mask[jj] & i) == mask[jj]) 
                        dp[i][j] = (dp[i][j] + freq[jj] * dp[i^mask[jj]][jj+1]) % mod; 
        long ans = 1; 
        for (int i = 1; i < 1<<10; ++i) 
            ans = (ans + dp[i][2]) % mod; 
        return (int) ((ans * pow - 1) % mod); 
    }


    /*2573. Find the String with LCP (Hard)
    We define the lcp matrix of any 0-indexed string word of n lowercase 
    English letters as an n x n grid such that:
    * lcp[i][j] is equal to the length of the longest common prefix between the 
      substrings word[i,n-1] and word[j,n-1].
    Given an n x n matrix lcp, return the alphabetically smallest string word 
    that corresponds to lcp. If there is no such string, return an empty string.
    A string a is lexicographically smaller than a string b (of the same length) 
    if in the first position where a and b differ, string a has a letter that 
    appears earlier in the alphabet than the corresponding letter in b. For 
    example, "aabd" is lexicographically smaller than "aaca" because the first 
    position they differ is at the third letter, and 'b' comes before 'c'.

    Example 1:
    Input: lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
    Output: "abab"
    Explanation: lcp corresponds to any 4 letter string with two alternating 
                 letters. The lexicographically smallest of them is "abab".
    
    Example 2:
    Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
    Output: "aaaa"
    Explanation: lcp corresponds to any 4 letter string with a single distinct 
                 letter. The lexicographically smallest of them is "aaaa". 
    
    Example 3:
    Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
    Output: ""
    Explanation: lcp[3][3] cannot be equal to 3 since word[3,...,3] consists of 
                 only a single letter; Thus, no answer exists.

    Constraints:
    * 1 <= n == lcp.length == lcp[i].length <= 1000
    * 0 <= lcp[i][j] <= n*/

    public String findTheString(int[][] lcp) {
        int n = lcp.length; 
        char[] ans = new char[n]; 
        Arrays.fill(ans, '*'); 
        for (int i = 0, c = 0; i < n; ++i) {
            if (lcp[i][i] != n-i) return ""; 
            for (int j = i+1; j < n; ++j) 
                if (lcp[i][j] != lcp[j][i] || lcp[i][j] > 0 && lcp[i][j] != 1 + (i+1 < n && j+1 < n ? lcp[i+1][j+1] : 0)) return ""; 
            if (ans[i] == '*') {
                for (int j = i; j < n; ++j) 
                    if (lcp[i][j] > 0) {
                        if (ans[j] != '*' || c == 26) return ""; 
                        ans[j] = (char) ('a' + c); 
                    }
                ++c; 
            }
        }
        return String.valueOf(ans); 
    }


    /*2574. Left and Right Sum Differences (Easy)
    Given a 0-indexed integer array nums, find a 0-indexed integer array answer 
    where:
    * answer.length == nums.length.
    * answer[i] = |leftSum[i] - rightSum[i]|.
    Where:
    * leftSum[i] is the sum of elements to the left of the index i in the array 
      nums. If there is no such element, leftSum[i] = 0.
    * rightSum[i] is the sum of elements to the right of the index i in the 
      array nums. If there is no such element, rightSum[i] = 0.
    Return the array answer.

    Example 1:
    Input: nums = [10,4,8,3]
    Output: [15,1,11,22]
    Explanation: The array leftSum is [0,10,14,22] and the array rightSum is 
                 [15,11,3,0]. The array answer is 
                 [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].
    
    Example 2:
    Input: nums = [1]
    Output: [0]
    Explanation: The array leftSum is [0] and the array rightSum is [0]. The 
                 array answer is [|0 - 0|] = [0].

    Constraints:
    * 1 <= nums.length <= 1000
    * 1 <= nums[i] <= 10^5*/

    public int[] leftRigthDifference(int[] nums) {
        int n = nums.length, diff = IntStream.of(nums).sum(); 
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Math.abs(diff - nums[i]); 
            diff -= 2*nums[i]; 
        }
        return ans; 
    }


    /*2575. Find the Divisibility Array of a String (Medium)
    You are given a 0-indexed string word of length n consisting of digits, and 
    a positive integer m. The divisibility array div of word is an integer 
    array of length n such that:
    * div[i] = 1 if the numeric value of word[0,...,i] is divisible by m, or
    * div[i] = 0 otherwise.
    Return the divisibility array of word.

    Example 1:
    Input: word = "998244353", m = 3
    Output: [1,1,0,0,0,1,1,0,0]
    Explanation: There are only 4 prefixes that are divisible by 3: "9", "99", 
                 "998244", and "9982443".
    
    Example 2:
    Input: word = "1010", m = 10
    Output: [0,1,0,1]
    Explanation: There are only 2 prefixes that are divisible by 10: "10", and 
                 "1010".

    Constraints:
    * 1 <= n <= 10^5
    * word.length == n
    * word consists of digits from 0 to 9
    * 1 <= m <= 10^9*/

    public int[] divisibilityArray(String word, int m) {
        int n = word.length(); 
        int[] ans = new int[n]; 
        long prefix = 0; 
        for (int i = 0; i < n; ++i) {
            prefix = 10*prefix + (word.charAt(i) - '0'); 
            prefix %= m; 
            if (prefix == 0) ans[i] = 1; 
        }
        return ans; 
    }


    /*2576. Find the Maximum Number of Marked Indices (Medium)
    You are given a 0-indexed integer array nums. Initially, all of the indices 
    are unmarked. You are allowed to make this operation any number of times:
    * Pick two different unmarked indices i and j such that 
      2 * nums[i] <= nums[j], then mark i and j.
    Return the maximum possible number of marked indices in nums using the 
    above operation any number of times.

    Example 1:
    Input: nums = [3,5,2,4]
    Output: 2
    Explanation: In the first operation: pick i = 2 and j = 1, the operation is 
                 allowed because 2 * nums[2] <= nums[1]. Then mark index 2 and 
                 1. It can be shown that there's no other valid operation so 
                 the answer is 2.
    
    Example 2:
    Input: nums = [9,2,5,4]
    Output: 4
    Explanation: In the first operation: pick i = 3 and j = 0, the operation is 
                 allowed because 2 * nums[3] <= nums[0]. Then mark index 3 and 
                 0. In the second operation: pick i = 1 and j = 2, the 
                 operation is allowed because 2 * nums[1] <= nums[2]. Then mark 
                 index 1 and 2. Since there is no other operation, the answer 
                 is 4.
    
    Example 3:
    Input: nums = [7,6,8]
    Output: 0
    Explanation: There is no valid operation to do, so the answer is 0.
     
    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9*/

    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums); 
        int ans = 0; 
        for (int n = nums.length, i = n/2-1, j = n-1; i >= 0; --i) 
            if (nums[i]*2 <= nums[j]) {
                ans += 2; 
                --j; 
            }
        return ans; 
    }


    /*2577. Minimum Time to Visit a Cell In a Grid (Hard)
    You are given a m x n matrix grid consisting of non-negative integers where 
    grid[row][col] represents the minimum time required to be able to visit the 
    cell (row, col), which means you can visit the cell (row, col) only when 
    the time you visit it is greater than or equal to grid[row][col]. You are 
    standing in the top-left cell of the matrix in the 0th second, and you must 
    move to any adjacent cell in the four directions: up, down, left, and right. 
    Each move you make takes 1 second. Return the minimum time required in 
    which you can visit the bottom-right cell of the matrix. If you cannot 
    visit the bottom-right cell, then return -1.

    Example 1:
    Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
    Output: 7
    Explanation: One of the paths that we can take is the following:
                 - at t = 0, we are on the cell (0,0).
                 - at t = 1, we move to the cell (0,1). It is possible because 
                   grid[0][1] <= 1.
                 - at t = 2, we move to the cell (1,1). It is possible because 
                   grid[1][1] <= 2.
                 - at t = 3, we move to the cell (1,2). It is possible because 
                   grid[1][2] <= 3.
                 - at t = 4, we move to the cell (1,1). It is possible because 
                   grid[1][1] <= 4.
                 - at t = 5, we move to the cell (1,2). It is possible because 
                   grid[1][2] <= 5.
                 - at t = 6, we move to the cell (1,3). It is possible because 
                   grid[1][3] <= 6.
                 - at t = 7, we move to the cell (2,3). It is possible because 
                   grid[1][3] <= 7.
                 The final time is 7. It can be shown that it is the minimum 
                 time possible.
    
    Example 2:
    Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
    Output: -1
    Explanation: There is no path from the top left to the bottom-right cell.

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 2 <= m, n <= 1000
    * 4 <= m * n <= 10^5
    * 0 <= grid[i][j] <= 10^5
    * grid[0][0] == 0*/

    public int minimumTime(int[][] grid) {
        if (grid[0][1] <= 1 || grid[1][0] <= 1) {
            int m = grid.length, n = grid[0].length; 
            int[] dir = new int[]{-1, 0, 1, 0, -1}; 
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); pq.add(new int[]{0, 0, 0}); 
            int[][] dist = new int[m][n]; 
            for (int i = 0; i < m; ++i) Arrays.fill(dist[i], Integer.MAX_VALUE); 
            dist[0][0] = 0; 
            while (!pq.isEmpty()) {
                var elem = pq.poll(); 
                int x = elem[0], i = elem[1], j = elem[2]; 
                if (i == m-1 && j == n-1) return x; 
                int cnt = 0; 
                for (int k = 0; k < 4; ++k) {
                    int ii = i + dir[k], jj = j + dir[k+1]; 
                    if (0 <= ii && ii < m && 0 <= jj && jj < n) {
                        if (grid[ii][jj] <= x+1) ++cnt; 
                        int xx = x + 1 + Math.max(0, (grid[ii][jj] - x)/2*2); 
                        if (dist[ii][jj] > xx) {
                            pq.add(new int[]{xx, ii, jj}); 
                            dist[ii][jj] = xx; 
                        }
                    }
                }
                if (cnt == 0) break; 
            }
        }
        return -1; 
    }


    /*2582. Pass the Pillow (Easy)
    There are n people standing in a line labeled from 1 to n. The first person 
    in the line is holding a pillow initially. Every second, the person holding 
    the pillow passes it to the next person standing in the line. Once the 
    pillow reaches the end of the line, the direction changes, and people 
    continue passing the pillow in the opposite direction.
    * For example, once the pillow reaches the nth person they pass it to the 
      n - 1th person, then to the n - 2th person and so on.
    Given the two positive integers n and time, return the index of the person 
    holding the pillow after time seconds.

    Example 1:
    Input: n = 4, time = 5
    Output: 2
    Explanation: People pass the pillow in the following way: 
                 1 -> 2 -> 3 -> 4 -> 3 -> 2. Afer five seconds, the pillow is 
                 given to the 2nd person.
    
    Example 2:
    Input: n = 3, time = 2
    Output: 3
    Explanation: People pass the pillow in the following way: 1 -> 2 -> 3. Afer 
                 two seconds, the pillow is given to the 3rd person.

    Constraints:
    * 2 <= n <= 1000
    * 1 <= time <= 1000*/

    public int passThePillow(int n, int time) {
        time %= 2*(n-1); 
        if (time > n-1) time = 2*(n-1) - time; 
        return time+1; 
    }


    /*2583. Kth Largest Sum in a Binary Tree (Medium)
    You are given the root of a binary tree and a positive integer k. The level 
    sum in the tree is the sum of the values of the nodes that are on the same 
    level. Return the kth largest level sum in the tree (not necessarily 
    distinct). If there are fewer than k levels in the tree, return -1. Note 
    that two nodes are on the same level if they have the same distance from 
    the root.

    Example 1:
    Input: root = [5,8,9,2,1,3,7,4,6], k = 2
    Output: 13
    Explanation: The level sums are the following:
                 - Level 1: 5.
                 - Level 2: 8 + 9 = 17.
                 - Level 3: 2 + 1 + 3 + 7 = 13.
                 - Level 4: 4 + 6 = 10.
                 The 2nd largest level sum is 13.
    
    Example 2:
    Input: root = [1,2,null,3], k = 1
    Output: 3
    Explanation: The largest level sum is 3.

    Constraints:
    * The number of nodes in the tree is n.
    * 2 <= n <= 10^5
    * 1 <= Node.val <= 10^6
    * 1 <= k <= n*/

    public long kthLargestLevelSum(TreeNode root, int k) {
        List<Long> vals = new ArrayList(); 
        Stack<Pair<TreeNode, Integer>> stk = new Stack(); 
        stk.add(new Pair(root, 0)); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            TreeNode node = elem.getKey(); 
            int i = elem.getValue(); 
            if (i == vals.size()) vals.add(0l); 
            vals.set(i, vals.get(i)+node.val); 
            if (node.left != null) stk.add(new Pair(node.left, i+1)); 
            if (node.right != null) stk.add(new Pair(node.right, i+1)); 
        }
        if (vals.size() < k) return -1; 
        Collections.sort(vals, Collections.reverseOrder()); 
        return vals.get(k-1); 
    }


    /*2584. Split the Array to Make Coprime Products (Medium)
    You are given a 0-indexed integer array nums of length n. A split at an 
    index i where 0 <= i <= n - 2 is called valid if the product of the first 
    i + 1 elements and the product of the remaining elements are coprime. 
    * For example, if nums = [2, 3, 3], then a split at the index i = 0 is 
      valid because 2 and 9 are coprime, while a split at the index i = 1 is 
      not valid because 6 and 3 are not coprime. A split at the index i = 2 is 
      not valid because i == n - 1.
    Return the smallest index i at which the array can be split validly or -1 
    if there is no such split. Two values val1 and val2 are coprime if 
    gcd(val1, val2) == 1 where gcd(val1, val2) is the greatest common divisor 
    of val1 and val2.

    Example 1:
    Input: nums = [4,7,8,15,3,5]
    Output: 2
    Explanation: The table above shows the values of the product of the first 
                 i + 1 elements, the remaining elements, and their gcd at each 
                 index i. The only valid split is at index 2.
    
    Example 2:
    Input: nums = [4,7,15,8,3,5]
    Output: -1
    Explanation: The table above shows the values of the product of the first 
                 i + 1 elements, the remaining elements, and their gcd at each 
                 index i. There is no valid split.

    Constraints:
    * n == nums.length
    * 1 <= n <= 10^4
    * 1 <= nums[i] <= 10^6*/

    public int findValidSplit(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap(); 
        for (var x : nums) {
            for (int p = 2; p <= Math.sqrt(x); ++p) 
                for (; x % p == 0; x /= p) 
                    freq.merge(p, 1, Integer::sum); 
            if (x > 1) freq.merge(x, 1, Integer::sum); 
        }
        HashSet<Integer> ovlp = new HashSet(); 
        for (int i = 0, x = nums[0]; i < nums.length-1; x = nums[++i]) {
            for (int p = 2; p <= Math.sqrt(x); ++p) 
                for (; x % p == 0; x /= p) {
                    ovlp.add(p); 
                    freq.merge(p, -1, Integer::sum); 
                    if (freq.get(p) == 0) ovlp.remove(p); 
                }
            if (x > 1) {
                ovlp.add(x); 
                freq.merge(x, -1, Integer::sum); 
                if (freq.get(x) == 0) ovlp.remove(x); 
            }
            if (ovlp.isEmpty()) return i; 
        }
        return -1; 
    }


    /*2585. Number of Ways to Earn Points (Hard)
    There is a test that has n types of questions. You are given an integer 
    target and a 0-indexed 2D integer array types where 
    types[i] = [counti, marksi] indicates that there are counti questions of 
    the ith type, and each one of them is worth marksi points. Return the 
    number of ways you can earn exactly target points in the exam. Since the 
    answer may be too large, return it modulo 10^9 + 7. Note that questions of 
    the same type are indistinguishable. For example, if there are 3 questions 
    of the same type, then solving the 1st and 2nd questions is the same as 
    solving the 1st and 3rd questions, or the 2nd and 3rd questions.

    Example 1:
    Input: target = 6, types = [[6,1],[3,2],[2,3]]
    Output: 7
    Explanation: You can earn 6 points in one of the seven ways:
                 - Solve 6 questions of the 0th type: 1 + 1 + 1 + 1 + 1 + 1 = 6
                 - Solve 4 questions of the 0th type and 1 question of the 1st 
                   type: 1 + 1 + 1 + 1 + 2 = 6
                 - Solve 2 questions of the 0th type and 2 questions of the 1st 
                   type: 1 + 1 + 2 + 2 = 6
                 - Solve 3 questions of the 0th type and 1 question of the 2nd 
                   type: 1 + 1 + 1 + 3 = 6
                 - Solve 1 question of the 0th type, 1 question of the 1st type 
                   and 1 question of the 2nd type: 1 + 2 + 3 = 6
                 - Solve 3 questions of the 1st type: 2 + 2 + 2 = 6
                 - Solve 2 questions of the 2nd type: 3 + 3 = 6
    
    Example 2:
    Input: target = 5, types = [[50,1],[50,2],[50,5]]
    Output: 4
    Explanation: You can earn 5 points in one of the four ways:
                 - Solve 5 questions of the 0th type: 1 + 1 + 1 + 1 + 1 = 5
                 - Solve 3 questions of the 0th type and 1 question of the 1st 
                   type: 1 + 1 + 1 + 2 = 5
                 - Solve 1 questions of the 0th type and 2 questions of the 1st 
                   type: 1 + 2 + 2 = 5
                 - Solve 1 question of the 2nd type: 5
    
    Example 3:
    Input: target = 18, types = [[6,1],[3,2],[2,3]]
    Output: 1
    Explanation: You can only earn 18 points by answering all questions.

    Constraints:
    * 1 <= target <= 1000
    * n == types.length
    * 1 <= n <= 50
    * types[i].length == 2
    * 1 <= counti, marksi <= 50*/

    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length; 
        long[][] dp = new long[target+1][n+1]; 
        for (int j = 0; j <= n; ++j) dp[0][j] = 1; 
        for (int i = 1; i <= target; ++i) {
            for (int j = n-1; j >= 0; --j) {
                int c = types[j][0], m = types[j][1]; 
                for (int x = 0; x <= c && i-x*m >= 0; ++x) 
                    dp[i][j] = (dp[i][j] + dp[i-x*m][j+1]) % 1_000_000_007; 
            }
        }
        return (int) dp[target][0]; 
    }


    /*2586. Count the Number of Vowel Strings in Range (Easy)
    You are given a 0-indexed array of string words and two integers left and 
    right. A string is called a vowel string if it starts with a vowel 
    character and ends with a vowel character where vowel characters are 'a', 
    'e', 'i', 'o', and 'u'. Return the number of vowel strings words[i] where i 
    belongs to the inclusive range [left, right].

    Example 1:
    Input: words = ["are","amy","u"], left = 0, right = 2
    Output: 2
    Explanation: - "are" is a vowel string because it starts with 'a' and ends 
                   with 'e'.
                 - "amy" is not a vowel string because it does not end with a 
                   vowel.
                 - "u" is a vowel string because it starts with 'u' and ends 
                   with 'u'.
                 The number of vowel strings in the mentioned range is 2.
    
    Example 2:
    Input: words = ["hey","aeo","mu","ooo","artro"], left = 1, right = 4
    Output: 3
    Explanation: - "aeo" is a vowel string because it starts with 'a' and ends 
                   with 'o'.
                 - "mu" is not a vowel string because it does not start with a 
                   vowel.
                 - "ooo" is a vowel string because it starts with 'o' and ends 
                   with 'o'.
                 - "artro" is a vowel string because it starts with 'a' and 
                   ends with 'o'.
                 The number of vowel strings in the mentioned range is 3.

    Constraints:
    * 1 <= words.length <= 1000
    * 1 <= words[i].length <= 10
    * words[i] consists of only lowercase English letters.
    * 0 <= left <= right < words.length*/

    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0; 
        for (int i = left; i <= right; ++i) 
            if ("aeiou".indexOf(words[i].charAt(0)) != -1 && "aeiou".indexOf(words[i].charAt(words[i].length()-1)) != -1) ++ans; 
        return ans; 
    }


    /*2587. Rearrange Array to Maximize Prefix Score (Medium)
    You are given a 0-indexed integer array nums. You can rearrange the 
    elements of nums to any order (including the given order). Let prefix be 
    the array containing the prefix sums of nums after rearranging it. In other 
    words, prefix[i] is the sum of the elements from 0 to i in nums after 
    rearranging it. The score of nums is the number of positive integers in the 
    array prefix. Return the maximum score you can achieve.

    Example 1:
    Input: nums = [2,-1,0,1,-3,3,-3]
    Output: 6
    Explanation: We can rearrange the array into nums = [2,3,1,-1,-3,0,-3]. 
                 prefix = [2,5,6,5,2,2,-1], so the score is 6. It can be shown 
                 that 6 is the maximum score we can obtain.
    
    Example 2:
    Input: nums = [-2,-3,0]
    Output: 0
    Explanation: Any rearrangement of the array will result in a score of 0.

    Constraints:
    * 1 <= nums.length <= 10^5
    * -10^6 <= nums[i] <= 10^6*/

    public int maxScore(int[] nums) {
        long prefix = 0; 
        Arrays.sort(nums); 
        for (int i = nums.length-1; i >= 0; --i) {
            prefix += nums[i]; 
            if (prefix <= 0) return nums.length-i-1; 
        }
        return nums.length; 
    }


    /*2588. Count the Number of Beautiful Subarrays (Medium)
    You are given a 0-indexed integer array nums. In one operation, you can:
    * Choose two different indices i and j such that 0 <= i, j < nums.length.
    * Choose a non-negative integer k such that the kth bit (0-indexed) in the 
      binary representation of nums[i] and nums[j] is 1.
    * Subtract 2k from nums[i] and nums[j].
    A subarray is beautiful if it is possible to make all of its elements equal 
    to 0 after applying the above operation any number of times. Return the 
    number of beautiful subarrays in the array nums. A subarray is a contiguous 
    non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [4,3,1,2,4]
    Output: 2
    Explanation: There are 2 beautiful subarrays in nums: [4,3,1,2,4] and 
                 [4,3,1,2,4].
                 - We can make all elements in the subarray [3,1,2] equal to 0 
                   in the following way:
                   - Choose [3, 1, 2] and k = 1. Subtract 21 from both numbers. 
                     The subarray becomes [1, 1, 0].
                   - Choose [1, 1, 0] and k = 0. Subtract 20 from both numbers. 
                     The subarray becomes [0, 0, 0].
                 - We can make all elements in the subarray [4,3,1,2,4] equal 
                   to 0 in the following way:
                   - Choose [4, 3, 1, 2, 4] and k = 2. Subtract 22 from both 
                     numbers. The subarray becomes [0, 3, 1, 2, 0].
                   - Choose [0, 3, 1, 2, 0] and k = 0. Subtract 20 from both 
                     numbers. The subarray becomes [0, 2, 0, 2, 0].
                   - Choose [0, 2, 0, 2, 0] and k = 1. Subtract 21 from both 
                     numbers. The subarray becomes [0, 0, 0, 0, 0].
    
    Example 2:
    Input: nums = [1,10,4]
    Output: 0
    Explanation: There are no beautiful subarrays in nums.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^6*/

    public long beautifulSubarrays(int[] nums) {
        long ans = 0; 
        int prefix = 0; 
        HashMap<Integer, Integer> freq = new HashMap(); 
        freq.put(0, 1); 
        for (var x : nums) {
            prefix ^= x; 
            ans += freq.getOrDefault(prefix, 0); 
            freq.merge(prefix, 1, Integer::sum); 
        }
        return ans; 
    }


    /*2589. Minimum Time to Complete All Tasks (Hard)
    There is a computer that can run an unlimited number of tasks at the same 
    time. You are given a 2D integer array tasks where 
    tasks[i] = [starti, endi, durationi] indicates that the ith task should run 
    for a total of durationi seconds (not necessarily continuous) within the 
    inclusive time range [starti, endi]. You may turn on the computer only when 
    it needs to run a task. You can also turn it off if it is idle. Return the 
    minimum time during which the computer should be turned on to complete all 
    tasks.

    Example 1:
    Input: tasks = [[2,3,1],[4,5,1],[1,5,2]]
    Output: 2
    Explanation: - The first task can be run in the inclusive time range [2, 2].
                 - The second task can be run in the inclusive time range [5, 5].
                 - The third task can be run in the two inclusive time ranges 
                   [2, 2] and [5, 5].
                 The computer will be on for a total of 2 seconds.
    
    Example 2:
    Input: tasks = [[1,3,2],[2,5,3],[5,6,2]]
    Output: 4
    Explanation: - The first task can be run in the inclusive time range [2, 3].
                 - The second task can be run in the inclusive time ranges 
                   [2, 3] and [5, 5].
                 - The third task can be run in the two inclusive time range 
                   [5, 6].
                 The computer will be on for a total of 4 seconds.

    Constraints:
    * 1 <= tasks.length <= 2000
    * tasks[i].length == 3
    * 1 <= starti, endi <= 2000
    * 1 <= durationi <= endi - starti + 1*/

    public int findMinimumTime(int[][] tasks) {
        int[] line = new int[2001]; 
        Arrays.sort(tasks, (a, b)->Integer.compare(a[1], b[1])); 
        for (var t : tasks) {
            int lo = t[0], hi = t[1], time = t[2]; 
            for (int x = lo; x <= hi && time > 0; ++x) time -= line[x]; 
            for (int x = hi; x >= lo && time > 0; --x) {
                if (line[x] == 0) {
                    line[x] = 1; 
                    --time; 
                }
            }
        }
        return Arrays.stream(line).sum(); 
    }


    /*2591. Distribute Money to Maximum Children (Easy)
    You are given an integer money denoting the amount of money (in dollars) 
    that you have and another integer children denoting the number of children 
    that you must distribute the money to. You have to distribute the money 
    according to the following rules:
    * All money must be distributed.
    * Everyone must receive at least 1 dollar.
    * Nobody receives 4 dollars.
    Return the maximum number of children who may receive exactly 8 dollars if 
    you distribute the money according to the aforementioned rules. If there is 
    no way to distribute the money, return -1.

    Example 1:
    Input: money = 20, children = 3
    Output: 1
    Explanation: The maximum number of children with 8 dollars will be 1. One 
                 of the ways to distribute the money is:
                 - 8 dollars to the first child.
                 - 9 dollars to the second child. 
                 - 3 dollars to the third child.
                 It can be proven that no distribution exists such that number 
                 of children getting 8 dollars is greater than 1.
    
    Example 2:
    Input: money = 16, children = 2
    Output: 2
    Explanation: Each child can be given 8 dollars.

    Constraints:
    * 1 <= money <= 200
    * 2 <= children <= 30*/

    public int distMoney(int money, int children) {
        if (money < children) return -1; 
        if (money > 8*children) return children-1; 
        int ans = (money-children)/7; 
        if (ans == children-1 && (money-children) % 7 == 3) --ans; 
        return ans; 
    }


    /*2592. Maximize Greatness of an Array (Medium)
    You are given a 0-indexed integer array nums. You are allowed to permute 
    nums into a new array perm of your choosing. We define the greatness of 
    nums be the number of indices 0 <= i < nums.length for which 
    perm[i] > nums[i]. Return the maximum possible greatness you can achieve 
    after permuting nums.

    Example 1:
    Input: nums = [1,3,5,2,1,3,1]
    Output: 4
    Explanation: One of the optimal rearrangements is perm = [2,5,1,3,3,1,1].
                 At indices = 0, 1, 3, and 4, perm[i] > nums[i]. Hence, we 
                 return 4.
    
    Example 2:
    Input: nums = [1,2,3,4]
    Output: 3
    Explanation: We can prove the optimal perm is [2,3,4,1]. At indices = 0, 1, 
                 and 2, perm[i] > nums[i]. Hence, we return 3.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^9*/

    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums); 
        int k = 0; 
        for (var x : nums) 
            if (nums[k] < x) ++k; 
        return k; 
    }


    /*2593. Find Score of an Array After Marking All Elements (Medium)
    You are given an array nums consisting of positive integers. Starting with 
    score = 0, apply the following algorithm:
    * Choose the smallest integer of the array that is not marked. If there is 
      a tie, choose the one with the smallest index.
    * Add the value of the chosen integer to score.
    * Mark the chosen element and its two adjacent elements if they exist.
    * Repeat until all the array elements are marked.
    Return the score you get after applying the above algorithm.

    Example 1:
    Input: nums = [2,1,3,4,5,2]
    Output: 7
    Explanation: We mark the elements as follows:
                 - 1 is the smallest unmarked element, so we mark it and its 
                   two adjacent elements: [2,1,3,4,5,2].
                 - 2 is the smallest unmarked element, so we mark it and its 
                   left adjacent element: [2,1,3,4,5,2].
                 - 4 is the only remaining unmarked element, so we mark it: 
                   [2,1,3,4,5,2].
                 Our score is 1 + 2 + 4 = 7.
    
    Example 2:
    Input: nums = [2,3,5,1,3,2]
    Output: 5
    Explanation: We mark the elements as follows:
                 - 1 is the smallest unmarked element, so we mark it and its 
                   two adjacent elements: [2,3,5,1,3,2].
                 - 2 is the smallest unmarked element, since there are two of 
                   them, we choose the left-most one, so we mark the one at 
                   index 0 and its right adjacent element: [2,3,5,1,3,2].
                 - 2 is the only remaining unmarked element, so we mark it: 
                   [2,3,5,1,3,2].
                 Our score is 1 + 2 + 2 = 5.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^6*/

    public long findScore(int[] nums) {
        int n = nums.length; 
        long ans = 0; 
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])); 
        for (int i = 0; i < n; ++i) 
            pq.add(new int[] {nums[i], i}); 
        boolean[] mark = new boolean[n]; 
        while (!pq.isEmpty()) {
            var elem = pq.poll(); 
            int x = elem[0], i = elem[1]; 
            if (!mark[i]) {
                ans += x; 
                mark[i] = true; 
                if (i > 0) mark[i-1] = true; 
                if (i+1 < n) mark[i+1] = true; 
            }
        }
        return ans; 
    }


    /*2594. Minimum Time to Repair Cars (Medium)
    You are given an integer array ranks representing the ranks of some 
    mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r 
    can repair n cars in r * n2 minutes. You are also given an integer cars 
    representing the total number of cars waiting in the garage to be repaired.
    Return the minimum time taken to repair all the cars. Note: All the 
    mechanics can repair the cars simultaneously.

    Example 1:
    Input: ranks = [4,2,3,1], cars = 10
    Output: 16
    Explanation: - The first mechanic will repair two cars. The time required 
                   is 4 * 2 * 2 = 16 minutes.
                 - The second mechanic will repair two cars. The time required 
                   is 2 * 2 * 2 = 8 minutes.
                 - The third mechanic will repair two cars. The time required 
                   is 3 * 2 * 2 = 12 minutes.
                 - The fourth mechanic will repair four cars. The time required 
                   is 1 * 4 * 4 = 16 minutes.
                 It can be proved that the cars cannot be repaired in less than 
                 16 minutes.
    
    Example 2:
    Input: ranks = [5,1,8], cars = 6
    Output: 16
    Explanation: - The first mechanic will repair one car. The time required is 
                   5 * 1 * 1 = 5 minutes.
                 - The second mechanic will repair four cars. The time required 
                   is 1 * 4 * 4 = 16 minutes.
                 - The third mechanic will repair one car. The time required is 
                   8 * 1 * 1 = 8 minutes.
                 It can be proved that the cars cannot be repaired in less than 
                 16 minutes.

    Constraints:
    * 1 <= ranks.length <= 10^5
    * 1 <= ranks[i] <= 100
    * 1 <= cars <= 10^6*/

    public long repairCars(int[] ranks, int cars) {
        long lo = 0, hi = (long) Arrays.stream(ranks).max().getAsInt() * cars * cars; 
        while (lo < hi) {
            long mid = lo + (hi - lo)/2, cnt = 0; 
            for (var x : ranks) cnt += Math.sqrt(mid/x); 
            if (cnt < cars) lo = mid+1; 
            else hi = mid; 
        }
        return lo; 
    }


    /*2595. Number of Even and Odd Bits (Easy)
    You are given a positive integer n. Let even denote the number of even 
    indices in the binary representation of n (0-indexed) with value 1. Let odd 
    denote the number of odd indices in the binary representation of n 
    (0-indexed) with value 1. Return an integer array answer where 
    answer = [even, odd].

    Example 1:
    Input: n = 17
    Output: [2,0]
    Explanation: The binary representation of 17 is 10001. It contains 1 on the 
                 0th and 4th indices. There are 2 even and 0 odd indices.
    
    Example 2:
    Input: n = 2
    Output: [0,1]
    Explanation: The binary representation of 2 is 10. It contains 1 on the 1st 
                 index. There are 0 even and 1 odd indices.

    Constraints: 1 <= n <= 1000*/

    public int[] evenOddBit(int n) {
        int[] ans = new int[2]; 
        for (int i = 0; n > 0; n >>= 1, i ^= 1) 
            if (n % 2 == 1) ++ans[i]; 
        return ans; 
    }


    /*2596. Check Knight Tour Configuration (Medium)
    There is a knight on an n x n chessboard. In a valid configuration, the 
    knight starts at the top-left cell of the board and visits every cell on 
    the board exactly once. You are given an n x n integer matrix grid 
    consisting of distinct integers from the range [0, n * n - 1] where 
    grid[row][col] indicates that the cell (row, col) is the grid[row][col]th 
    cell that the knight visited. The moves are 0-indexed. Return true if grid 
    represents a valid configuration of the knight's movements or false 
    otherwise. Note that a valid knight move consists of moving two squares 
    vertically and one square horizontally, or two squares horizontally and one 
    square vertically. The figure below illustrates all the possible eight 
    moves of a knight from some cell.

    Example 1:
    Input: grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
    Output: true
    Explanation: The above diagram represents the grid. It can be shown that it 
                 is a valid configuration.
    
    Example 2:
    Input: grid = [[0,3,6],[5,8,1],[2,7,4]]
    Output: false
    Explanation: The above diagram represents the grid. The 8th move of the 
                 knight is not valid considering its position after the 7th 
                 move.

    Constraints:
    * n == grid.length == grid[i].length
    * 3 <= n <= 7
    * 0 <= grid[row][col] < n * n
    * All integers in grid are unique.*/

    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length; 
        HashMap<Integer, Pair<Integer, Integer>> loc = new HashMap(); 
        for (int i = 0; i < n; ++i) 
            for (int j = 0; j < n; ++j) 
                loc.put(grid[i][j], new Pair(i, j)); 
        for (int i = 0, j = 0, ii = 0, jj = 0, x = 1; x < n*n; ++x, ii = i, jj = j) {
            i = loc.get(x).getKey();
            j = loc.get(x).getValue(); 
            int di = Math.abs(i-ii), dj = Math.abs(j-jj); 
            if (!(di == 1 && dj == 2 || di == 2 && dj == 1)) return false; 
        }
        return true; 
    }


    /*2597. The Number of Beautiful Subsets (Medium)
    You are given an array nums of positive integers and a positive integer k. 
    A subset of nums is beautiful if it does not contain two integers with an 
    absolute difference equal to k. Return the number of non-empty beautiful 
    subsets of the array nums. A subset of nums is an array that can be 
    obtained by deleting some (possibly none) elements from nums. Two subsets 
    are different if and only if the chosen indices to delete are different.

    Example 1:
    Input: nums = [2,4,6], k = 2
    Output: 4
    Explanation: The beautiful subsets of the array nums are: [2], [4], [6], 
                 [2, 6]. It can be proved that there are only 4 beautiful 
                 subsets in the array [2,4,6].
    
    Example 2:
    Input: nums = [1], k = 1
    Output: 1
    Explanation: The beautiful subset of the array nums is [1]. It can be 
                 proved that there is only 1 beautiful subset in the array [1].

    Constraints:
    * 1 <= nums.length <= 20
    * 1 <= nums[i], k <= 1000*/

    public int beautifulSubsets(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap(); 
        for (var x : nums) freq.merge(x, 1, Integer::sum); 
        Map<Integer, List<Integer>> mp = new HashMap(); 
        List<Integer> keys = new ArrayList(); 
        for (var x : freq.keySet()) keys.add(x); 
        Collections.sort(keys); 
        for (var x : keys) {
            mp.putIfAbsent(x-k, new ArrayList()); 
            mp.get(x-k).add(x); 
            mp.put(x, mp.get(x-k)); 
            mp.remove(x-k); 
        }
        int ans = 1; 
        for (var v : mp.values()) {
            int f0 = 1, f1 = 1; 
            for (var x : v) {
                int f2 = f0*((int) Math.pow(2, freq.get(x))-1) + f1; 
                f0 = f1; 
                f1 = f2; 
            }
            ans *= f1; 
        }
        return ans-1; 
    }


    /*2598. Smallest Missing Non-negative Integer After Operations (Medium)
    You are given a 0-indexed integer array nums and an integer value. In one 
    operation, you can add or subtract value from any element of nums.
    * For example, if nums = [1,2,3] and value = 2, you can choose to subtract 
      value from nums[0] to make nums = [-1,2,3].
    The MEX (minimum excluded) of an array is the smallest missing non-
    negative integer in it.
    * For example, the MEX of [-1,2,3] is 0 while the MEX of [1,0,3] is 2.
    Return the maximum MEX of nums after applying the mentioned operation any 
    number of times.

    Example 1:
    Input: nums = [1,-10,7,13,6,8], value = 5
    Output: 4
    Explanation: One can achieve this result by applying the following 
                 operations:
                 - Add value to nums[1] twice to make nums = [1,0,7,13,6,8]
                 - Subtract value from nums[2] once to make 
                   nums = [1,0,2,13,6,8]
                 - Subtract value from nums[3] twice to make 
                   nums = [1,0,2,3,6,8]
                 The MEX of nums is 4. It can be shown that 4 is the maximum 
                 MEX we can achieve.
    
    Example 2:
    Input: nums = [1,-10,7,13,6,8], value = 7
    Output: 2
    Explanation: One can achieve this result by applying the following 
                 operation:
                 - subtract value from nums[2] once to make 
                   nums = [1,-10,0,13,6,8]
                 The MEX of nums is 2. It can be shown that 2 is the maximum 
                 MEX we can achieve.

    Constraints:
    * 1 <= nums.length, value <= 10^5
    * -10^9 <= nums[i] <= 10^9*/

    public int findSmallestInteger(int[] nums, int value) {
        HashMap<Integer, Integer> freq = new HashMap(); 
        for (var x : nums) freq.merge((x % value + value) % value, 1, Integer::sum); 
        int k = 0; 
        for (int x = 1; x < value; ++x) 
            if (freq.getOrDefault(x, 0) < freq.getOrDefault(k, 0)) 
                k = x; 
        return k + freq.getOrDefault(k, 0)*value; 
    }


    /*2599. Make the Prefix Sum Non-negative (Medium)
    You are given a 0-indexed integer array nums. You can apply the following 
    operation any number of times:
    * Pick any element from nums and put it at the end of nums.
    The prefix sum array of nums is an array prefix of the same length as nums 
    such that prefix[i] is the sum of all the integers nums[j] where j is in 
    the inclusive range [0, i]. Return the minimum number of operations such 
    that the prefix sum array does not contain negative integers. The test 
    cases are generated such that it is always possible to make the prefix sum 
    array non-negative.

    Example 1:
    Input: nums = [2,3,-5,4]
    Output: 0
    Explanation: we do not need to do any operations. The array is [2,3,-5,4]. 
                 The prefix sum array is [2, 5, 0, 4].
    
    Example 2:
    Input: nums = [3,-5,-2,6]
    Output: 1
    Explanation: we can do one operation on index 1. The array after the 
                 operation is [3,-2,6,-5]. The prefix sum array is [3, 1, 7, 2].

    Constraints:
    * 1 <= nums.length <= 10^5
    * -10^9 <= nums[i] <= 10^9*/

    public int makePrefSumNonNegative(int[] nums) {
        List<Integer> vals = new ArrayList(); 
        for (var x : nums) vals.add(x); 
        int ans = 0; 
        long prefix = 0; 
        Queue<Integer> pq = new PriorityQueue(); 
        for (int i = 0; i < vals.size(); ++i) {
            prefix += vals.get(i); 
            pq.add(vals.get(i)); 
            if (prefix < 0) {
                ++ans; 
                int xx = pq.poll(); 
                prefix -= xx; 
                vals.add(xx); 
            }
        }
        return ans; 
    }


    /*2600. K Items With the Maximum Sum (Easy)
    There is a bag that consists of items, each item has a number 1, 0, or -1 
    written on it. You are given four non-negative integers numOnes, numZeros, 
    numNegOnes, and k. The bag initially contains:
    * numOnes items with 1s written on them.
    * numZeroes items with 0s written on them.
    * numNegOnes items with -1s written on them.
    We want to pick exactly k items among the available items. Return the 
    maximum possible sum of numbers written on the items.

    Example 1:
    Input: numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
    Output: 2
    Explanation: We have a bag of items with numbers written on them 
                 {1, 1, 1, 0, 0}. We take 2 items with 1 written on them and 
                 get a sum in a total of 2. It can be proven that 2 is the 
                 maximum possible sum.
    
    Example 2:
    Input: numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
    Output: 3
    Explanation: We have a bag of items with numbers written on them 
                 {1, 1, 1, 0, 0}. We take 3 items with 1 written on them, and 1 
                 item with 0 written on it, and get a sum in a total of 3. It 
                 can be proven that 3 is the maximum possible sum.

    Constraints:
    * 0 <= numOnes, numZeros, numNegOnes <= 50
    * 0 <= k <= numOnes + numZeros + numNegOnes*/

    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        return Math.min(k, Math.min(numOnes, 2*numOnes+numZeros-k)); 
    }


    /*2601. Prime Subtraction Operation (Medium)
    You are given a 0-indexed integer array nums of length n. You can perform 
    the following operation as many times as you want:
    * Pick an index i that you haven’t picked before, and pick a prime p 
      strictly less than nums[i], then subtract p from nums[i].
    Return true if you can make nums a strictly increasing array using the 
    above operation and false otherwise. A strictly increasing array is an 
    array whose each element is strictly greater than its preceding element.

    Example 1:
    Input: nums = [4,9,6,10]
    Output: true
    Explanation: In the first operation: Pick i = 0 and p = 3, and then 
                 subtract 3 from nums[0], so that nums becomes [1,9,6,10]. In 
                 the second operation: i = 1, p = 7, subtract 7 from nums[1], 
                 so nums becomes equal to [1,2,6,10]. After the second 
                 operation, nums is sorted in strictly increasing order, so the 
                 answer is true.
    
    Example 2:
    Input: nums = [6,8,11,12]
    Output: true
    Explanation: Initially nums is sorted in strictly increasing order, so we 
                 don't need to make any operations.
    
    Example 3:
    Input: nums = [5,8,3]
    Output: false
    Explanation: It can be proven that there is no way to perform operations to 
                 make nums sorted in strictly increasing order, so the answer 
                 is false.

    Constraints:
    * 1 <= nums.length <= 1000
    * 1 <= nums[i] <= 1000
    * nums.length == n*/

    public boolean primeSubOperation(int[] nums) {
        boolean[] sieve = new boolean[1001]; 
        Arrays.fill(sieve, true); 
        sieve[0] = sieve[1] = false; 
        for (int x = 2; x*x <= 1000; ++x)
            if (sieve[x]) 
                for (int xx = x*x; xx <= 1000; xx += x)
                    sieve[xx] = false; 
        int prev = 0; 
        for (var x : nums) {
            if (prev >= x) return false; 
            int p = x-1; 
            for (; p > 0; --p) 
                if (sieve[p] && x-p > prev) break; 
            prev = x-p; 
        }
        return true; 
    }


    /*2602. Minimum Operations to Make All Array Elements Equal (Medium)
    You are given an array nums consisting of positive integers. You are also 
    given an integer array queries of size m. For the ith query, you want to 
    make all of the elements of nums equal to queries[i]. You can perform the 
    following operation on the array any number of times:
    * Increase or decrease an element of the array by 1.
    Return an array answer of size m where answer[i] is the minimum number of 
    operations to make all elements of nums equal to queries[i]. Note that 
    after each query the array is reset to its original state.

    Example 1:
    Input: nums = [3,1,6,8], queries = [1,5]
    Output: [14,10]
    Explanation: For the first query we can do the following operations:
                 - Decrease nums[0] 2 times, so that nums = [1,1,6,8].
                 - Decrease nums[2] 5 times, so that nums = [1,1,1,8].
                 - Decrease nums[3] 7 times, so that nums = [1,1,1,1].
                 So the total number of operations for the first query is 
                 2 + 5 + 7 = 14. For the second query we can do the following 
                 operations:
                 - Increase nums[0] 2 times, so that nums = [5,1,6,8].
                 - Increase nums[1] 4 times, so that nums = [5,5,6,8].
                 - Decrease nums[2] 1 time, so that nums = [5,5,5,8].
                 - Decrease nums[3] 3 times, so that nums = [5,5,5,5].
                 So the total number of operations for the second query is 
                 2 + 4 + 1 + 3 = 10.
    
    Example 2:
    Input: nums = [2,9,6,3], queries = [10]
    Output: [20]
    Explanation: We can increase each value in the array to 10. The total 
                 number of operations will be 8 + 1 + 4 + 7 = 20.

    Constraints:
    * n == nums.length
    * m == queries.length
    * 1 <= n, m <= 10^5
    * 1 <= nums[i], queries[i] <= 10^9*/

    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums); 
        int n = nums.length; 
        long[] prefix = new long[n+1]; 
        for (int i = 0; i < n; ++i)
            prefix[i+1] = prefix[i] + nums[i]; 
        List<Long> ans = new ArrayList(); 
        for (var q : queries) {
            int lo = 0, hi = n; 
            while (lo < hi) {
                int mid = lo + (hi-lo)/2; 
                if (nums[mid] < q) lo = mid+1; 
                else hi = mid; 
            }
            long val = prefix[n] - 2*prefix[lo] + (long) q*(2*lo - n); 
            ans.add(val); 
        }
        return ans; 
    }


    /*2603. Collect Coins in a Tree (Hard)
    There exists an undirected and unrooted tree with n nodes indexed from 0 to 
    n - 1. You are given an integer n and a 2D integer array edges of length 
    n - 1, where edges[i] = [ai, bi] indicates that there is an edge between 
    nodes ai and bi in the tree. You are also given an array coins of size n 
    where coins[i] can be either 0 or 1, where 1 indicates the presence of a 
    coin in the vertex i. Initially, you choose to start at any vertex in the 
    tree. Then, you can perform the following operations any number of times: 
    * Collect all the coins that are at a distance of at most 2 from the 
      current vertex, or
    * Move to any adjacent vertex in the tree.
    Find the minimum number of edges you need to go through to collect all the 
    coins and go back to the initial vertex. Note that if you pass an edge 
    several times, you need to count it into the answer several times.

    Example 1:
    Input: coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
    Output: 2
    Explanation: Start at vertex 2, collect the coin at vertex 0, move to 
                 vertex 3, collect the coin at vertex 5 then move back to 
                 vertex 2.
    
    Example 2:
    Input: coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
    Output: 2
    Explanation: Start at vertex 0, collect the coins at vertices 4 and 3, move 
                 to vertex 2,  collect the coin at vertex 7, then move back to 
                 vertex 0.

    Constraints:
    * n == coins.length
    * 1 <= n <= 3 * 10^4
    * 0 <= coins[i] <= 1
    * edges.length == n - 1
    * edges[i].length == 2
    * 0 <= ai, bi < n
    * ai != bi
    * edges represents a valid tree.*/

    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length; 
        Set<Integer>[] tree = new HashSet[n]; 
        for (int i = 0; i < n; ++i) tree[i] = new HashSet(); 
        for (var e : edges) {
            tree[e[0]].add(e[1]); 
            tree[e[1]].add(e[0]); 
        }
        Queue<Integer> leaf = new LinkedList(); 
        for (int i = 0; i < n; ++i) {
            int u = i; 
            while (tree[u].size() == 1 && coins[u] == 0) {
                int v = tree[u].iterator().next(); 
                tree[u].remove(v); 
                tree[v].remove(u); 
                u = v; 
            }
            if (tree[u].size() == 1) leaf.add(u); 
        }
        for (int j = 0; j < 2; ++j) {
            for (int sz = leaf.size(); sz > 0; --sz) {
                int u = leaf.poll(); 
                if (tree[u].size() == 1) {
                    int v = tree[u].iterator().next(); 
                    tree[u].remove(v); 
                    tree[v].remove(u); 
                    if (tree[v].size() == 1) leaf.add(v); 
                }
            }
        }
        int ans = 0; 
        for (int i = 0; i < n; ++i) ans += tree[i].size(); 
        return ans; 
    }


    /*2604. Minimum Time to Eat All Grains (Hard)
    There are n hens and m grains on a line. You are given the initial 
    positions of the hens and the grains in two integer arrays hens and grains 
    of size n and m respectively. Any hen can eat a grain if they are on the 
    same position. The time taken for this is negligible. One hen can also eat 
    multiple grains. In 1 second, a hen can move right or left by 1 unit. The 
    hens can move simultaneously and independently of each other. Return the 
    minimum time to eat all grains if the hens act optimally.

    Example 1:
    Input: hens = [3,6,7], grains = [2,4,7,9]
    Output: 2
    Explanation: One of the ways hens eat all grains in 2 seconds is described 
                 below:
                 - The first hen eats the grain at position 2 in 1 second. 
                 - The second hen eats the grain at position 4 in 2 seconds. 
                 - The third hen eats the grains at positions 7 and 9 in 2 
                   seconds. 
                 So, the maximum time needed is 2. It can be proven that the 
                 hens cannot eat all grains before 2 seconds.
    
    Example 2:
    Input: hens = [4,6,109,111,213,215], grains = [5,110,214]
    Output: 1
    Explanation: One of the ways hens eat all grains in 1 second is described 
                 below:
                 - The first hen eats the grain at position 5 in 1 second. 
                 - The fourth hen eats the grain at position 110 in 1 second.
                 - The sixth hen eats the grain at position 214 in 1 second. 
                 - The other hens do not move. 
                 So, the maximum time needed is 1.

    Constraints:
    * 1 <= hens.length, grains.length <= 2*10^4
    * 0 <= hens[i], grains[j] <= 10^9*/

    public int minimumTime(int[] hens, int[] grains) {
        Arrays.sort(hens); 
        Arrays.sort(grains); 
        int lo = 0, hi = 1_000_000_000; 
        while (lo < hi) {
            int mid = lo + (hi-lo)/2, i = 0; 
            for (var h : hens) 
                for (int ii = i; i < grains.length && (grains[i] <= h && h-grains[i] <= mid || h <= grains[ii] && grains[i]-h <= mid || grains[ii] <= h && h <= grains[i] && grains[i]-grains[ii]+Math.min(grains[i]-h, h-grains[ii]) <= mid); ++i); 
            if (i == grains.length) hi = mid; 
            else lo = mid+1; 
        }
        return lo; 
    }


    /*2605. Form Smallest Number From Two Digit Arrays (Easy)
    Given two arrays of unique digits nums1 and nums2, return the smallest 
    number that contains at least one digit from each array.

    Example 1:
    Input: nums1 = [4,1,3], nums2 = [5,7]
    Output: 15
    Explanation: The number 15 contains the digit 1 from nums1 and the digit 5 
                 from nums2. It can be proven that 15 is the smallest number we 
                 can have.
    
    Example 2:
    Input: nums1 = [3,5,2,6], nums2 = [3,1,7]
    Output: 3
    Explanation: The number 3 contains the digit 3 which exists in both arrays.

    Constraints:
    * 1 <= nums1.length, nums2.length <= 9
    * 1 <= nums1[i], nums2[i] <= 9
    * All digits in each array are unique.*/

    public int minNumber(int[] nums1, int[] nums2) {
        int m1 = 0, m2 = 0; 
        for (var x : nums1) m1 |= 1 << x; 
        for (var x : nums2) m2 |= 1 << x; 
        for (int x = 0; x <= 9; ++x) 
            if ((m1 & 1<<x) > 0 && (m2 & 1<<x) > 0) return x; 
        int d1 = Arrays.stream(nums1).min().getAsInt(), d2 = Arrays.stream(nums2).min().getAsInt(); 
        return 10*Math.min(d1, d2) + Math.max(d1, d2); 
    }


    /*2606. Find the Substring With Maximum Cost (Medium)
    You are given a string s, a string chars of distinct characters and an 
    integer array vals of the same length as chars. The cost of the substring 
    is the sum of the values of each character in the substring. The cost of an 
    empty string is considered 0. The value of the character is defined in the 
    following way:
    * If the character is not in the string chars, then its value is its 
      corresponding position (1-indexed) in the alphabet.
      + For example, the value of 'a' is 1, the value of 'b' is 2, and so on. 
        The value of 'z' is 26.
    * Otherwise, assuming i is the index where the character occurs in the 
      string chars, then its value is vals[i].
    Return the maximum cost among all substrings of the string s.

    Example 1:
    Input: s = "adaa", chars = "d", vals = [-1000]
    Output: 2
    Explanation: The value of the characters "a" and "d" is 1 and -1000 
                 respectively. The substring with the maximum cost is "aa" and 
                 its cost is 1 + 1 = 2. It can be proven that 2 is the maximum 
                 cost.
    
    Example 2:
    Input: s = "abc", chars = "abc", vals = [-1,-1,-1]
    Output: 0
    Explanation: The value of the characters "a", "b" and "c" is -1, -1, and -1 
                 respectively. The substring with the maximum cost is the empty 
                 substring "" and its cost is 0. It can be proven that 0 is the 
                 maximum cost.

    Constraints:
    * 1 <= s.length <= 10^5
    * s consist of lowercase English letters.
    * 1 <= chars.length <= 26
    * chars consist of distinct lowercase English letters.
    * vals.length == chars.length
    * -1000 <= vals[i] <= 1000*/

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        HashMap<Character, Integer> mp = new HashMap(); 
        for (int i = 0; i < chars.length(); ++i) 
            mp.put(chars.charAt(i), vals[i]); 
        int ans = 0, val = 0; 
        for (var ch : s.toCharArray()) {
            val = Math.max(0, val + mp.getOrDefault(ch, ch - 'a' + 1)); 
            ans = Math.max(ans, val); 
        }
        return ans; 
    }


    /*2607. Make K-Subarray Sums Equal (Medium)
    You are given a 0-indexed integer array arr and an integer k. The array arr 
    is circular. In other words, the first element of the array is the next 
    element of the last element, and the last element of the array is the 
    previous element of the first element. You can do the following operation 
    any number of times:
    * Pick any element from arr and increase or decrease it by 1.
    Return the minimum number of operations such that the sum of each subarray 
    of length k is equal. A subarray is a contiguous part of the array.

    Example 1:
    Input: arr = [1,4,1,3], k = 2
    Output: 1
    Explanation: we can do one operation on index 1 to make its value equal to 
                 3. The array after the operation is [1,3,1,3]
                 - Subarray starts at index 0 is [1, 3], and its sum is 4 
                 - Subarray starts at index 1 is [3, 1], and its sum is 4 
                 - Subarray starts at index 2 is [1, 3], and its sum is 4 
                 - Subarray starts at index 3 is [3, 1], and its sum is 4 
    
    Example 2:
    Input: arr = [2,5,5,7], k = 3
    Output: 5
    Explanation: we can do three operations on index 0 to make its value equal 
                 to 5 and two operations on index 3 to make its value equal to 
                 5. The array after the operations is [5,5,5,5]
                 - Subarray starts at index 0 is [5, 5, 5], and its sum is 15
                 - Subarray starts at index 1 is [5, 5, 5], and its sum is 15
                 - Subarray starts at index 2 is [5, 5, 5], and its sum is 15
                 - Subarray starts at index 3 is [5, 5, 5], and its sum is 15 

    Constraints:
    * 1 <= k <= arr.length <= 10^5
    * 1 <= arr[i] <= 10^9*/

    public long makeSubKSumEqual(int[] arr, int k) {
        long ans = 0; 
        int n = arr.length, g = n; 
        for (int v = k; v > 0; ) { 
            int gg = g; g = v; v = gg % v; 
        }
        for (int i = 0; i < g; ++i) {
            int[] vals = new int[n/g]; 
            for (int j = 0; j < n/g; ++j, i = (i+k) % n) 
                vals[j] = arr[i]; 
            Arrays.sort(vals); 
            int target = vals[vals.length/2]; 
            for (var x : vals) ans += Math.abs(x - target); 
        }
        return ans; 
    }


    /*2608. Shortest Cycle in a Graph (Hard)
    There is a bi-directional graph with n vertices, where each vertex is 
    labeled from 0 to n - 1. The edges in the graph are represented by a given 
    2D integer array edges, where edges[i] = [ui, vi] denotes an edge between 
    vertex ui and vertex vi. Every vertex pair is connected by at most one 
    edge, and no vertex has an edge to itself. Return the length of the 
    shortest cycle in the graph. If no cycle exists, return -1. A cycle is a 
    path that starts and ends at the same node, and each edge in the path is 
    used only once.

    Example 1:
    Input: n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]
    Output: 3
    Explanation: The cycle with the smallest length is : 0 -> 1 -> 2 -> 0 

    Example 2:
    Input: n = 4, edges = [[0,1],[0,2]]
    Output: -1
    Explanation: There are no cycles in this graph.

    Constraints:
    * 2 <= n <= 1000
    * 1 <= edges.length <= 1000
    * edges[i].length == 2
    * 0 <= ui, vi < n
    * ui != vi
    * There are no repeated edges.*/

    public int findShortestCycle(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) graph[u] = new ArrayList(); 
        for (var e : edges) {
            graph[e[0]].add(e[1]); 
            graph[e[1]].add(e[0]); 
        }
        int ans = Integer.MAX_VALUE; 
        for (int x = 0; x < n; ++x) {
            int[] dist = new int[n]; 
            Arrays.fill(dist, -1); 
            Queue<int[]> q = new LinkedList(); 
            q.add(new int[] {x, -1, 0}); 
            while (!q.isEmpty()) {
                var elem = q.poll(); 
                int u = elem[0], p = elem[1], d = elem[2]; 
                if (dist[u] >= 0) {
                    ans = Math.min(ans, d + dist[u]); 
                    break; 
                } 
                dist[u] = d; 
                for (var v : graph[u]) 
                    if (v != p) q.add(new int[] {v, u, d+1}); 
            }
        }
        return ans < Integer.MAX_VALUE ? ans : -1; 
    }


    /*2609. Find the Longest Balanced Substring of a Binary String (Easy)
    You are given a binary string s consisting only of zeroes and ones. A 
    substring of s is considered balanced if all zeroes are before ones and the 
    number of zeroes is equal to the number of ones inside the substring. 
    Notice that the empty substring is considered a balanced substring. Return 
    the length of the longest balanced substring of s. A substring is a 
    contiguous sequence of characters within a string.

    Example 1:
    Input: s = "01000111"
    Output: 6
    Explanation: The longest balanced substring is "000111", which has length 6.

    Example 2:
    Input: s = "00111"
    Output: 4
    Explanation: The longest balanced substring is "0011", which has length 4. 
    
    Example 3:
    Input: s = "111"
    Output: 0
    Explanation: There is no balanced substring except the empty substring, so 
                 the answer is 0.

    Constraints:
    * 1 <= s.length <= 50
    * '0' <= s[i] <= '1'*/

    public int findTheLongestBalancedSubstring(String s) {
        int ans = 0, val = 0; 
        for (int i = 0, ii = 0, n = s.length(); i <= n; ++i) 
            if (i == n || i > 0 && s.charAt(i-1) != s.charAt(i)) {
                if (s.charAt(i-1) == '0') val = i - ii; 
                else {
                    val = Math.min(val, i-ii); 
                    ans = Math.max(2*val, ans); 
                }
                ii = i; 
            }
        return ans; 
    }


    /*2610. Convert an Array Into a 2D Array With Conditions (Medium)
    You are given an integer array nums. You need to create a 2D array from 
    nums satisfying the following conditions:
    * The 2D array should contain only the elements of the array nums.
    * Each row in the 2D array contains distinct integers.
    * The number of rows in the 2D array should be minimal.
    Return the resulting array. If there are multiple answers, return any of 
    them. Note that the 2D array can have a different number of elements on 
    each row.

    Example 1:
    Input: nums = [1,3,4,1,2,3,1]
    Output: [[1,3,4,2],[1,3],[1]]
    Explanation: We can create a 2D array that contains the following rows:
                 - 1,3,4,2
                 - 1,3
                 - 1
                 All elements of nums were used, and each row of the 2D array 
                 contains distinct integers, so it is a valid answer. It can be 
                 shown that we cannot have less than 3 rows in a valid array.
    
    Example 2:
    Input: nums = [1,2,3,4]
    Output: [[4,3,2,1]]
    Explanation: All elements of the array are distinct, so we can keep all of 
                 them in the first row of the 2D array.

    Constraints:
    * 1 <= nums.length <= 200
    * 1 <= nums[i] <= nums.length*/

    public List<List<Integer>> findMatrix(int[] nums) {
        int m = 0; 
        Map<Integer, Integer> freq = new HashMap(); 
        for (var x : nums) {
            freq.merge(x, 1, Integer::sum); 
            m = Math.max(m, freq.get(x)); 
        }
        List<List<Integer>> ans = new ArrayList(); 
        for (int i = 0; i < m; ++i) ans.add(new ArrayList()); 
        for (var elem : freq.entrySet()) {
            int k = elem.getKey(), v = elem.getValue(); 
            for (int i = 0; i < v; ++i) 
                ans.get(i).add(k); 
        }
        return ans; 
    }


    /*2611. Mice and Cheese (Medium)
    There are two mice and n different types of cheese, each type of cheese 
    should be eaten by exactly one mouse. A point of the cheese with index i 
    (0-indexed) is:
    * reward1[i] if the first mouse eats it.
    * reward2[i] if the second mouse eats it.
    You are given a positive integer array reward1, a positive integer array 
    reward2, and a non-negative integer k. Return the maximum points the mice 
    can achieve if the first mouse eats exactly k types of cheese.

    Example 1:
    Input: reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
    Output: 15
    Explanation: In this example, the first mouse eats the 2nd (0-indexed) and 
                 the 3rd types of cheese, and the second mouse eats the 0th and 
                 the 1st types of cheese. The total points are 
                 4 + 4 + 3 + 4 = 15. It can be proven that 15 is the maximum 
                 total points that the mice can achieve.
    
    Example 2:
    Input: reward1 = [1,1], reward2 = [1,1], k = 2
    Output: 2
    Explanation: In this example, the first mouse eats the 0th (0-indexed) and 
                 1st types of cheese, and the second mouse does not eat any 
                 cheese. The total points are 1 + 1 = 2. It can be proven that 
                 2 is the maximum total points that the mice can achieve.

    Constraints:
    * 1 <= n == reward1.length == reward2.length <= 10^5
    * 1 <= reward1[i], reward2[i] <= 1000
    * 0 <= k <= n*/

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int ans = 0; 
        for (int i = 0; i < reward1.length; ++i) {
            ans += reward2[i]; 
            reward1[i] -= reward2[i]; 
        }
        Arrays.sort(reward1); 
        for (int i = 0, n = reward1.length; i < k; ++i) ans += reward1[n-1-i]; 
        return ans; 
    }


    /*2612. Minimum Reverse Operations (Hard)
    You are given an integer n and an integer p in the range [0, n - 1]. 
    Representing a 0-indexed array arr of length n where all positions are set 
    to 0's, except position p which is set to 1. You are also given an integer 
    array banned containing some positions from the array. For the ith position 
    in banned, arr[banned[i]] = 0, and banned[i] != p. You can perform multiple 
    operations on arr. In an operation, you can choose a subarray with size k 
    and reverse the subarray. However, the 1 in arr should never go to any of 
    the positions in banned. In other words, after each operation 
    arr[banned[i]] remains 0. Return an array ans where for each i from 
    [0, n - 1], ans[i] is the minimum number of reverse operations needed to 
    bring the 1 to position i in arr, or -1 if it is impossible. 
    * A subarray is a contiguous non-empty sequence of elements within an array.
    * The values of ans[i] are independent for all i's.
    * The reverse of an array is an array containing the values in reverse 
      order.

    Example 1:
    Input: n = 4, p = 0, banned = [1,2], k = 4
    Output: [0,-1,-1,1]
    Explanation: In this case k = 4 so there is only one possible reverse 
                 operation we can perform, which is reversing the whole array. 
                 Initially, 1 is placed at position 0 so the amount of 
                 operations we need for position 0 is 0. We can never place a 1 
                 on the banned positions, so the answer for positions 1 and 2 
                 is -1. Finally, with one reverse operation we can bring the 1 
                 to index 3, so the answer for position 3 is 1. 
    
    Example 2:
    Input: n = 5, p = 0, banned = [2,4], k = 3
    Output: [0,-1,-1,-1,-1]
    Explanation: In this case the 1 is initially at position 0, so the answer 
                 for that position is 0. We can perform reverse operations of 
                 size 3. The 1 is currently located at position 0, so we need 
                 to reverse the subarray [0, 2] for it to leave that position, 
                 but reversing that subarray makes position 2 have a 1, which 
                 shouldn't happen. So, we can't move the 1 from position 0, 
                 making the result for all the other positions -1. 
    
    Example 3:
    Input: n = 4, p = 2, banned = [0,1,3], k = 1
    Output: [-1,-1,0,-1]
    Explanation: In this case we can only perform reverse operations of size 1. 
                 So the 1 never changes its position.

    Constraints:
    * 1 <= n <= 10^5
    * 0 <= p <= n - 1
    * 0 <= banned.length <= n - 1
    * 0 <= banned[i] <= n - 1
    * 1 <= k <= n 
    * banned[i] != p
    * all values in banned are unique*/

    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] ans = new int[n]; 
        Arrays.fill(ans, -1); 
        boolean[] ban = new boolean[n]; 
        for (var x : banned) ban[x] = true; 
        TreeSet<Integer>[] avail = new TreeSet[2]; 
        for (int i = 0; i < 2; ++i) avail[i] = new TreeSet(); 
        for (int i = 0; i < n; ++i) 
            if (!ban[i]) avail[i&1].add(i); 
        Queue<Integer> q = new LinkedList(); 
        q.add(p); 
        avail[p&1].remove(p); 
        for (int val = 0; !q.isEmpty(); ++val) 
            for (int sz = q.size(); sz > 0; --sz) {
                var v = q.poll(); 
                ans[v] = val; 
                int lo = Math.abs(v-k+1), hi = n-1-Math.abs(n-v-k); 
                Integer x = avail[lo&1].ceiling(lo); 
                while (x != null && x <= hi) {
                    q.add(x); 
                    avail[lo&1].remove(x); 
                    x = avail[lo&1].higher(x); 
                }
            }
        return ans; 
    }


    /*2613. Beautiful Pairs (Hard)
    You are given two 0-indexed integer arrays nums1 and nums2 of the same 
    length. A pair of indices (i,j) is called beautiful if
    |nums1[i] - nums1[j]| + |nums2[i] - nums2[j]| is the smallest amongst all 
    possible indices pairs where i < j. Return the beautiful pair. In the case 
    that there are multiple beautiful pairs, return the lexicographically 
    smallest pair. Note that
    * |x| denotes the absolute value of x.
    * A pair of indices (i1, j1) is lexicographically smaller than (i2, j2) if 
      i1 < i2 or i1 == i2 and j1 < j2.

    Example 1:
    Input: nums1 = [1,2,3,2,4], nums2 = [2,3,1,2,3]
    Output: [0,3]
    Explanation: Consider index 0 and index 3. The value of 
                 |nums1[i]-nums1[j]| + |nums2[i]-nums2[j]| is 1, which is the 
                 smallest value we can achieve.
    
    Example 2:
    Input: nums1 = [1,2,4,3,2,5], nums2 = [1,4,2,3,5,1]
    Output: [1,4]
    Explanation: Consider index 1 and index 4. The value of 
                 |nums1[i]-nums1[j]| + |nums2[i]-nums2[j]| is 1, which is the 
                 smallest value we can achieve.

    Constraints:
    * 2 <= nums1.length, nums2.length <= 10^5
    * nums1.length == nums2.length
    * 0 <= nums1i <= nums1.length
    * 0 <= nums2i <= nums2.length*/

    private int[] solve(int lo, int hi, int[][] points) {
        int delta = Integer.MAX_VALUE, i = -1, ii = -1; 
        if (lo+2 == hi) {
            delta = Math.abs(points[lo][1] - points[lo+1][1]) + Math.abs(points[lo][2] - points[lo+1][2]); 
            i = Math.min(points[lo][0], points[lo+1][0]); 
            ii = Math.max(points[lo][0], points[lo+1][0]); 
        } else if (lo+2 < hi) {
            int mid = (lo + hi)/2; 
            var left = solve(lo, mid, points); 
            int ld = left[0], li = left[1], lii = left[2]; 
            var right = solve(mid, hi, points); 
            int rd = right[0], ri = right[1], rii = right[2]; 
            if (ld < rd || ld == rd && li < ri) { delta = ld; i = li; ii = lii; }
            else { delta = rd; i = ri; ii = rii; }
            int split = points[mid][1]; 
            List<int[]> strip = new ArrayList(); 
            for (int k = lo; k < hi; ++k) 
                if (split-delta <= points[k][1] && points[k][1] <= split+delta) strip.add(points[k]); 
            Collections.sort(strip, (a, b)->Integer.compare(a[2], b[2])); 
            for (int k = 0, n = strip.size(); k < n; ++k) {
                int x = strip.get(k)[1], y = strip.get(k)[2]; 
                for (int kk = k+1; kk < n && kk < k+10; ++kk) {
                    int xx = strip.get(kk)[1], yy = strip.get(kk)[2], cand = Math.abs(x-xx) + Math.abs(y-yy); 
                    int j = Math.min(strip.get(k)[0], strip.get(kk)[0]), jj = Math.max(strip.get(k)[0], strip.get(kk)[0]); 
                    if (cand < delta || cand == delta && (j < i || j == i && jj < ii)) { delta = cand; i = j; ii = jj; }
                }
            }
        }
        return new int[]{delta, i, ii}; 
    }
    
    public int[] beautifulPair(int[] nums1, int[] nums2) {
        int n = nums1.length; 
        int[][] points = new int[n][3]; 
        for (int i = 0; i < n; ++i)
            points[i] = new int[]{i, nums1[i], nums2[i]}; 
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1])); 
        var ans = solve(0, n, points); 
        return new int[]{ans[1], ans[2]}; 
    }


    /*2614. Prime In Diagonal (Easy)
    You are given a 0-indexed two-dimensional integer array nums. Return the 
    largest prime number that lies on at least one of the diagonals of nums. In 
    case, no prime is present on any of the diagonals, return 0. Note that:
    * An integer is prime if it is greater than 1 and has no positive integer 
      divisors other than 1 and itself.
    * An integer val is on one of thediagonals of nums if there exists an 
      integer i for which nums[i][i] = val or an i for which 
      nums[i][nums.length - i - 1]= val.
    In the above diagram, one diagonal is [1,5,9] and another diagonal is [3,5,7].

    Example 1:
    Input: nums = [[1,2,3],[5,6,7],[9,10,11]]
    Output: 11
    Explanation: The numbers 1, 3, 6, 9, and 11 are the only numbers present on 
                 at least one of the diagonals. Since 11 is the largest prime, 
                 we return 11.
    
    Example 2:
    Input: nums = [[1,2,3],[5,17,7],[9,11,10]]
    Output: 17
    Explanation: The numbers 1, 3, 9, 10, and 17 are all present on at least 
                 one of the diagonals. 17 is the largest prime, so we return 17.

    Constraints:
    * 1 <= nums.length <= 300
    * nums.length == numsi.length
    * 1 <= nums[i][j] <= 4*10^6*/

    private boolean fn(int x) {
        if (x == 1) return false; 
        for (int p = 2; p <= Math.sqrt(x); ++p)
            if (x % p == 0) return false; 
        return true; 
    }
    
    public int diagonalPrime(int[][] nums) {
        int ans = 0; 
        for (int i = 0, n = nums.length; i < n; ++i) {
            if (fn(nums[i][i])) ans = Math.max(ans, nums[i][i]); 
            if (fn(nums[i][n-1-i])) ans = Math.max(ans, nums[i][n-1-i]); 
        }
        return ans; 
    }


    /*2615. Sum of Distances (Medium)
    You are given a 0-indexed integer array nums. There exists an array arr of 
    length nums.length, where arr[i] is the sum of |i - j| over all j such that 
    nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
    Return the array arr.

    Example 1:
    Input: nums = [1,3,1,1,2]
    Output: [5,0,3,4,0]
    Explanation: - When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. 
                 Therefore, arr[0] = |0 - 2| + |0 - 3| = 5. 
                 - When i = 1, arr[1] = 0 because there is no other index with 
                   value 3.
                 - When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. 
                   Therefore, arr[2] = |2 - 0| + |2 - 3| = 3. 
                 - When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. 
                   Therefore, arr[3] = |3 - 0| + |3 - 2| = 4. 
                 - When i = 4, arr[4] = 0 because there is no other index with 
                   value 2. 

    Example 2:
    Input: nums = [0,5,3]
    Output: [0,0,0]
    Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^9*/

    public long[] distance(int[] nums) {
        Map<Integer, List<Integer>> mp = new HashMap(); 
        for (int i = 0; i < nums.length; ++i) {
            mp.putIfAbsent(nums[i], new ArrayList()); 
            mp.get(nums[i]).add(i); 
        }
        long[] ans = new long[nums.length]; 
        for (var idx : mp.values()) {
            int n = idx.size(); 
            long[] prefix = new long[n+1]; 
            for (int i = 0; i < n; ++i) prefix[i+1] = prefix[i] + idx.get(i); 
            for (int i = 0; i < n; ++i) 
                ans[idx.get(i)] = prefix[n] - 2*prefix[i] + (long) (2*i - n)*idx.get(i); 
        }
        return ans; 
    }


    /*2616. Minimize the Maximum Difference of Pairs (Medium)
    You are given a 0-indexed integer array nums and an integer p. Find p pairs 
    of indices of nums such that the maximum difference amongst all the pairs 
    is minimized. Also, ensure no index appears more than once amongst the p 
    pairs. Note that for a pair of elements at the index i and j, the 
    difference of this pair is |nums[i] - nums[j]|, where |x| represents the 
    absolute value of x. Return the minimum maximum difference among all p 
    pairs.

    Example 1:
    Input: nums = [10,1,2,7,1,3], p = 2
    Output: 1
    Explanation: The first pair is formed from the indices 1 and 4, and the 
                 second pair is formed from the indices 2 and 5. The maximum 
                 difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) 
                 = max(0, 1) = 1. Therefore, we return 1.
    
    Example 2:
    Input: nums = [4,2,1,2], p = 1
    Output: 0
    Explanation: Let the indices 1 and 3 form a pair. The difference of that 
                 pair is |2 - 2| = 0, which is the minimum we can attain.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^9
    * 0 <= p <= (nums.length)/2*/

    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums); 
        int lo = 0, hi = 1_000_000_000; 
        while (lo < hi) {
            int mid = lo + (hi-lo)/2, cnt = 0; 
            for (int i = 0, n = nums.length; i < n; ++i) 
                if (i+1 < n && nums[i+1] - nums[i] <= mid) {
                    ++cnt; 
                    ++i; 
                }
            if (cnt < p) lo = mid+1; 
            else hi = mid; 
        }
        return lo; 
    }


    /*2617. Minimum Number of Visited Cells in a Grid (Hard)
    You are given a 0-indexed m x n integer matrix grid. Your initial position 
    is at the top-left cell (0, 0). Starting from the cell (i, j), you can move 
    to one of the following cells:
    * Cells (i, k) with j < k <= grid[i][j] + j (rightward movement), or
    * Cells (k, j) with i < k <= grid[i][j] + i (downward movement).
    Return the minimum number of cells you need to visit to reach the bottom-
    right cell (m - 1, n - 1). If there is no valid path, return -1.

    Example 1:
    Input: grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
    Output: 4
    Explanation: The image above shows one of the paths that visits exactly 4 cells.

    Example 2:
    Input: grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
    Output: 3
    Explanation: The image above shows one of the paths that visits exactly 3 cells.

    Example 3:
    Input: grid = [[2,1,0],[1,0,0]]
    Output: -1
    Explanation: It can be proven that no path exists.

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 1 <= m, n <= 105
    * 1 <= m * n <= 105
    * 0 <= grid[i][j] < m * n
    * grid[m - 1][n - 1] == 0*/

    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length; 
        Queue<int[]>[] pqs = new PriorityQueue[n]; 
        for (int j = 0; j < n; ++j) pqs[j] = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); 
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) Arrays.fill(dist[i], Integer.MAX_VALUE); 
        dist[0][0] = 1; 
        for (int i = 0; i < m; ++i) {
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); 
            for (int j = 0; j < n; ++j) {
                while (!pq.isEmpty() && pq.peek()[1] < j) pq.poll(); 
                while (!pqs[j].isEmpty() && pqs[j].peek()[1] < i) pqs[j].poll(); 
                if (!pq.isEmpty()) dist[i][j] = Math.min(dist[i][j], pq.peek()[0] + 1); 
                if (!pqs[j].isEmpty()) dist[i][j] = Math.min(dist[i][j], pqs[j].peek()[0] + 1); 
                if (dist[i][j] < Integer.MAX_VALUE) {
                    pq.add(new int[] {dist[i][j], j + grid[i][j]}); 
                    pqs[j].add(new int[] {dist[i][j], i + grid[i][j]}); 
                }
            }
        }
        return dist[m-1][n-1] < Integer.MAX_VALUE ? dist[m-1][n-1] : -1; 
    }


    /*2638. Count the Number of K-Free Subsets (Medium)
    You are given an integer array nums, which contains distinct elements and 
    an integer k. A subset is called a k-Free subset if it contains no two 
    elements with an absolute difference equal to k. Notice that the empty set 
    is a k-Free subset. Return the number of k-Free subsets of nums. A subset 
    of an array is a selection of elements (possibly none) of the array.

    Example 1:
    Input: nums = [5,4,6], k = 1
    Output: 5
    Explanation: There are 5 valid subsets: {}, {5}, {4}, {6} and {4, 6}.

    Example 2:
    Input: nums = [2,3,5,8], k = 5
    Output: 12
    Explanation: There are 12 valid subsets: {}, {2}, {3}, {5}, {8}, {2, 3}, 
                 {2, 3, 5}, {2, 5}, {2, 5, 8}, {2, 8}, {3, 5} and {5, 8}.
    
    Example 3:
    Input: nums = [10,5,9,11], k = 20
    Output: 16
    Explanation: All subsets are valid. Since the total count of subsets is 
                 2^4 = 16, so the answer is 16. 

    Constraints:
    * 1 <= nums.length <= 50
    * 1 <= nums[i] <= 1000
    * 1 <= k <= 1000*/

    public long countTheNumOfKFreeSubsets(int[] nums, int k) {
        Arrays.sort(nums); 
        Map<Integer, Integer> size = new HashMap(); 
        int m = 0; 
        for (var x : nums) {
            size.put(x, 1 + size.getOrDefault(x-k, 0)); 
            size.remove(x-k); 
            m = Math.max(m, size.get(x)); 
        }
        long[] fib = new long[m+2]; 
        fib[0] = fib[1] = 1; 
        for (int i = 2; i < m+2; ++i) fib[i] = fib[i-2] + fib[i-1]; 
        long ans = 1; 
        for (var v : size.values()) 
            ans *= fib[v+1]; 
        return ans; 
    }


    /*2639. Find the Width of Columns of a Grid (Easy)
    You are given a 0-indexed m x n integer matrix grid. The width of a column 
    is the maximum length of its integers.
    * For example, if grid = [[-10], [3], [12]], the width of the only column 
      is 3 since -10 is of length 3.
    Return an integer array ans of size n where ans[i] is the width of the ith 
    column. The length of an integer x with len digits is equal to len if x is 
    non-negative, and len + 1 otherwise.

    Example 1:
    Input: grid = [[1],[22],[333]]
    Output: [3]
    Explanation: In the 0th column, 333 is of length 3.

    Example 2:
    Input: grid = [[-15,1,3],[15,7,12],[5,6,-2]]
    Output: [3,1,2]
    Explanation: In the 0th column, only -15 is of length 3.
                 In the 1st column, all integers are of length 1. 
                 In the 2nd column, both 12 and -2 are of length 2.

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 1 <= m, n <= 100
    * -10^9 <= grid[r][c] <= 10^9*/

    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length, n = grid[0].length; 
        int[] ans = new int[n]; 
        for (int i = 0; i < m; ++i) 
            for (int j = 0; j < n; ++j) {
                int val = 0; 
                for (int x = grid[i][j]; x != 0; ++val, x /= 10); 
                if (grid[i][j] < 0) ++val; 
                ans[j] = Math.max(ans[j], Math.max(1, val)); 
            }
        return ans; 
    }


    /*2640. Find the Score of All Prefixes of an Array (Medium)
    We define the conversion array conver of an array arr as follows:
    * conver[i] = arr[i] + max(arr[0..i]) where max(arr[0..i]) is the maximum 
      value of arr[j] over 0 <= j <= i.
    We also define the score of an array arr as the sum of the values of the 
    conversion array of arr. Given a 0-indexed integer array nums of length n, 
    return an array ans of length n where ans[i] is the score of the prefix 
    nums[0..i].

    Example 1:
    Input: nums = [2,3,7,5,10]
    Output: [4,10,24,36,56]
    Explanation: - For the prefix [2], the conversion array is [4] hence the 
                   score is 4
                 - For the prefix [2, 3], the conversion array is [4, 6] hence 
                   the score is 10
                 - For the prefix [2, 3, 7], the conversion array is [4, 6, 14] 
                   hence the score is 24
                 - For the prefix [2, 3, 7, 5], the conversion array is 
                   [4, 6, 14, 12] hence the score is 36
                 - For the prefix [2, 3, 7, 5, 10], the conversion array is 
                   [4, 6, 14, 12, 20] hence the score is 56
    
    Example 2:
    Input: nums = [1,1,2,4,8,16]
    Output: [2,4,8,16,32,64]
    Explanation: - For the prefix [1], the conversion array is [2] hence the 
                   score is 2
                 - For the prefix [1, 1], the conversion array is [2, 2] hence 
                   the score is 4
                 - For the prefix [1, 1, 2], the conversion array is [2, 2, 4] 
                   hence the score is 8
                 - For the prefix [1, 1, 2, 4], the conversion array is 
                   [2, 2, 4, 8] hence the score is 16
                 - For the prefix [1, 1, 2, 4, 8], the conversion array is 
                   [2, 2, 4, 8, 16] hence the score is 32
                 - For the prefix [1, 1, 2, 4, 8, 16], the conversion array is 
                   [2, 2, 4, 8, 16, 32] hence the score is 64

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9*/

    public long[] findPrefixScore(int[] nums) {
        long[] ans = new long[nums.length]; 
        long prefix = 0; 
        for (int i = 0, most = 0; i < nums.length; ++i) {
            most = Math.max(most, nums[i]); 
            prefix += nums[i] + most; 
            ans[i] = prefix; 
        }
        return ans; 
    }



    /*2641. Cousins in Binary Tree II (Medium)
    Given the root of a binary tree, replace the value of each node in the tree 
    with the sum of all its cousins' values. Two nodes of a binary tree are 
    cousins if they have the same depth with different parents. Return the root 
    of the modified tree. Note that the depth of a node is the number of edges 
    in the path from the root node to it.

    Example 1:
    Input: root = [5,4,9,1,10,null,7]
    Output: [0,0,0,7,7,null,11]
    Explanation: The diagram above shows the initial binary tree and the binary 
                 tree after changing the value of each node.
                 - Node with value 5 does not have any cousins so its sum is 0.
                 - Node with value 4 does not have any cousins so its sum is 0.
                 - Node with value 9 does not have any cousins so its sum is 0.
                 - Node with value 1 has a cousin with value 7 so its sum is 7.
                 - Node with value 10 has a cousin with value 7 so its sum is 7.
                 - Node with value 7 has cousins with values 1 and 10 so its 
                   sum is 11.
    
    Example 2:
    Input: root = [3,1,2]
    Output: [0,0,0]
    Explanation: The diagram above shows the initial binary tree and the binary 
                 tree after changing the value of each node.
                 - Node with value 3 does not have any cousins so its sum is 0.
                 - Node with value 1 does not have any cousins so its sum is 0.
                 - Node with value 2 does not have any cousins so its sum is 0.

    Constraints:
    * The number of nodes in the tree is in the range [1, 10^5].
    * 1 <= Node.val <= 10^4*/

    public TreeNode replaceValueInTree(TreeNode root) {
        List<Integer> total = new ArrayList(); 
        Map<TreeNode, Integer> mp = new HashMap(); 
        Stack<Object[]> stk = new Stack(); stk.push(new Object[]{root, null, 0}); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            TreeNode node = (TreeNode) elem[0], p = (TreeNode) elem[1]; 
            int d = (int) elem[2]; 
            if (total.size() == d) total.add(0); 
            total.set(d, total.get(d) + node.val); 
            mp.put(p, mp.getOrDefault(p, 0) + node.val); 
            if (node.left != null) stk.push(new Object[]{node.left, node, d+1}); 
            if (node.right != null) stk.push(new Object[]{node.right, node, d+1}); 
        }
        stk.push(new Object[]{root, null, 0}); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            TreeNode node = (TreeNode) elem[0], p = (TreeNode) elem[1]; 
            int d = (int) elem[2]; 
            node.val = total.get(d) - mp.get(p); 
            if (node.left != null) stk.push(new Object[]{node.left, node, d+1}); 
            if (node.right != null) stk.push(new Object[]{node.right, node, d+1}); 
        }
        return root; 
    }


    /*2643. Row With Maximum Ones (Easy)
    Given a m x n binary matrix mat, find the 0-indexed position of the row 
    that contains the maximum count of ones, and the number of ones in that row.
    In case there are multiple rows that have the maximum count of ones, the 
    row with the smallest row number should be selected. Return an array 
    containing the index of the row, and the number of ones in it.

    Example 1:
    Input: mat = [[0,1],[1,0]]
    Output: [0,1]
    Explanation: Both rows have the same number of 1's. So we return the index 
                 of the smaller row, 0, and the maximum count of ones (1). So, 
                 the answer is [0,1]. 
    
    Example 2:
    Input: mat = [[0,0,0],[0,1,1]]
    Output: [1,2]
    Explanation: The row indexed 1 has the maximum count of ones (2). So we 
                 return its index, 1, and the count. So, the answer is [1,2].
    
    Example 3:
    Input: mat = [[0,0],[1,1],[0,0]]
    Output: [1,2]
    Explanation: The row indexed 1 has the maximum count of ones (2). So the 
                 answer is [1,2].

    Constraints:
    * m == mat.length 
    * n == mat[i].length 
    * 1 <= m, n <= 100 
    * mat[i][j] is either 0 or 1.*/

    public int[] rowAndMaximumOnes(int[][] mat) {
        int idx = 0, cnt = 0; 
        for (int i = 0; i < mat.length; ++i) {
            int c = 0; 
            for (int j = 0; j < mat[i].length; ++j)
                if (mat[i][j] == 1) ++c; 
            if (c > cnt) {
                idx = i; 
                cnt = c; 
            }
        }
        return new int[]{idx, cnt}; 
    }


    /*2644. Find the Maximum Divisibility Score (Easy)
    You are given two 0-indexed integer arrays nums and divisors. The 
    divisibility score of divisors[i] is the number of indices j such that 
    nums[j] is divisible by divisors[i]. Return the integer divisors[i] with 
    the maximum divisibility score. If there is more than one integer with the 
    maximum score, return the minimum of them.

    Example 1:
    Input: nums = [4,7,9,3,9], divisors = [5,2,3]
    Output: 3
    Explanation: The divisibility score for every element in divisors is:
                 - The divisibility score of divisors[0] is 0 since no number 
                   in nums is divisible by 5.
                 - The divisibility score of divisors[1] is 1 since nums[0] is 
                   divisible by 2.
                 - The divisibility score of divisors[2] is 3 since nums[2], 
                   nums[3], and nums[4] are divisible by 3.
                 - Since divisors[2] has the maximum divisibility score, we 
                   return it.
    
    Example 2:
    Input: nums = [20,14,21,10], divisors = [5,7,5]
    Output: 5
    Explanation: The divisibility score for every element in divisors is:
                 - The divisibility score of divisors[0] is 2 since nums[0] and 
                   nums[3] are divisible by 5.
                 - The divisibility score of divisors[1] is 2 since nums[1] and 
                   nums[2] are divisible by 7.
                 - The divisibility score of divisors[2] is 2 since nums[0] and 
                   nums[3] are divisible by 5.
                 Since divisors[0], divisors[1], and divisors[2] all have the 
                 maximum divisibility score, we return the minimum of them 
                 (i.e., divisors[2]).
    
    Example 3:
    Input: nums = [12], divisors = [10,16]
    Output: 10
    Explanation: The divisibility score for every element in divisors is:
                 - The divisibility score of divisors[0] is 0 since no number 
                   in nums is divisible by 10.
                 - The divisibility score of divisors[1] is 0 since no number 
                   in nums is divisible by 16.
                 Since divisors[0] and divisors[1] both have the maximum 
                 divisibility score, we return the minimum of them (i.e., 
                 divisors[0]).

    Constraints:
    * 1 <= nums.length, divisors.length <= 1000
    * 1 <= nums[i], divisors[i] <= 10^9*/

    public int maxDivScore(int[] nums, int[] divisors) {
        int ans = 0, most = -1; 
        for (var d : divisors) {
            int cnt = 0; 
            for (var n : nums) 
                if (n % d == 0) ++cnt; 
            if (cnt > most || cnt == most && d < ans) {
                ans = d; 
                most = cnt; 
            }
        }
        return ans; 
    }


    /*2645. Minimum Additions to Make Valid String (Medium)
    Given a string word to which you can insert letters "a", "b" or "c" 
    anywhere and any number of times, return the minimum number of letters that 
    must be inserted so that word becomes valid. A string is called valid if it 
    can be formed by concatenating the string "abc" several times.

    Example 1:
    Input: word = "b"
    Output: 2
    Explanation: Insert the letter "a" right before "b", and the letter "c" 
                 right next to "a" to obtain the valid string "abc".
    
    Example 2:
    Input: word = "aaa"
    Output: 6
    Explanation: Insert letters "b" and "c" next to each "a" to obtain the 
                 valid string "abcabcabc".
    
    Example 3:
    Input: word = "abc"
    Output: 0
    Explanation: word is already valid. No modifications are needed. 

    Constraints:
    * 1 <= word.length <= 50
    * word consists of letters "a", "b" and "c" only.*/

    public int addMinimum(String word) {
        int ans = 0, cnt = 3; 
        for (int i = 0; i < word.length(); ++i, --cnt) {
            if (i > 0 && word.charAt(i-1) >= word.charAt(i)) {
                ans += cnt; 
                cnt = 3; 
            }
        }
        return ans + cnt; 
    }


    /*2646. Minimize the Total Price of the Trips (Hard)
    There exists an undirected and unrooted tree with n nodes indexed from 0 to 
    n - 1. You are given the integer n and a 2D integer array edges of length 
    n - 1, where edges[i] = [ai, bi] indicates that there is an edge between 
    nodes ai and bi in the tree. Each node has an associated price. You are 
    given an integer array price, where price[i] is the price of the ith node.
    The price sum of a given path is the sum of the prices of all nodes lying 
    on that path. Additionally, you are given a 2D integer array trips, where 
    trips[i] = [starti, endi] indicates that you start the ith trip from the 
    node starti and travel to the node endi by any path you like. Before 
    performing your first trip, you can choose some non-adjacent nodes and 
    halve the prices. Return the minimum total price sum to perform all the 
    given trips.

    Example 1:
    Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
    Output: 23
    Explanation: The diagram above denotes the tree after rooting it at node 2. 
                 The first part shows the initial tree and the second part 
                 shows the tree after choosing nodes 0, 2, and 3, and making 
                 their price half.
                 - For the 1st trip, we choose path [0,1,3]. The price sum of 
                   that path is 1 + 2 + 3 = 6.
                 - For the 2nd trip, we choose path [2,1]. The price sum of 
                   that path is 2 + 5 = 7.
                 - For the 3rd trip, we choose path [2,1,3]. The price sum of 
                   that path is 5 + 2 + 3 = 10.
                 The total price sum of all trips is 6 + 7 + 10 = 23. It can be 
                 proven, that 23 is the minimum answer that we can achieve.
    
    Example 2:
    Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
    Output: 1
    Explanation: The diagram above denotes the tree after rooting it at node 0. 
                 The first part shows the initial tree and the second part 
                 shows the tree after choosing node 0, and making its price 
                 half.
                 - For the 1st trip, we choose path [0]. The price sum of that 
                   path is 1.
                 The total price sum of all trips is 1. It can be proven, that 
                 1 is the minimum answer that we can achieve.

    Constraints:
    * 1 <= n <= 50
    * edges.length == n - 1
    * 0 <= ai, bi <= n - 1
    * edges represents a valid tree.
    * price.length == n
    * price[i] is an even integer.
    * 1 <= price[i] <= 1000
    * 1 <= trips.length <= 100
    * 0 <= starti, endi <= n - 1*/

    private int[] dfs(int u, int p, List<Integer>[] tree, int[] price, int[] freq) {
        int full = 0, half = 0; 
        for (var v : tree[u]) 
            if (v != p) {
                int[] ans = dfs(v, u, tree, price, freq); 
                full += ans[0]; 
                half += Math.min(ans[0], ans[1]); 
            }
        return new int[]{price[u]*freq[u] + half, price[u]*freq[u]/2 + full}; 
    }
    
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        List<Integer>[] tree = new ArrayList[n]; 
        for (int u = 0; u < n; ++u) tree[u] = new ArrayList(); 
        for (var e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]); 
        }
        int[] freq = new int[n]; 
        for (var t : trips) {
            Queue<int[]> q = new LinkedList(); 
            q.add(new int[]{t[0], -1}); 
            Map<Integer, Integer> parent = new HashMap(); 
            parent.put(t[0], -1); 
            while (!q.isEmpty()) {
                var elem = q.poll(); 
                int u = elem[0], p = elem[1]; 
                if (u == t[1]) break; 
                for (var v : tree[u]) 
                    if (v != p) {
                        q.add(new int[]{v, u}); 
                        parent.put(v, u); 
                    }
            }
            for (int u = t[1]; u >= 0; ++freq[u], u = parent.get(u)); 
        }
        
        int[] ans = dfs(0, -1, tree, price, freq); 
        return Math.min(ans[0], ans[1]); 
    }


    /*2647. Color the Triangle Red (Hard)
    You are given an integer n. Consider an equilateral triangle of side length 
    n, broken up into n2 unit equilateral triangles. The triangle has n 
    1-indexed rows where the ith row has 2i - 1 unit equilateral triangles.
    The triangles in the ith row are also 1-indexed with coordinates from (i, 1) 
    to (i, 2i - 1). The following image shows a triangle of side length 4 with 
    the indexing of its triangle. Two triangles are neighbors if they share a 
    side. For example:
    * Triangles (1,1) and (2,2) are neighbors
    * Triangles (3,2) and (3,3) are neighbors.
    * Triangles (2,2) and (3,3) are not neighbors because they do not share any 
      side.
    Initially, all the unit triangles are white. You want to choose k triangles 
    and color them red. We will then run the following algorithm:
    * Choose a white triangle that has at least two red neighbors.
      - If there is no such triangle, stop the algorithm.
    * Color that triangle red.
    * Go to step 1.
    Choose the minimum k possible and set k triangles red before running this 
    algorithm such that after the algorithm stops, all unit triangles are 
    colored red. Return a 2D list of the coordinates of the triangles that you 
    will color red initially. The answer has to be of the smallest size 
    possible. If there are multiple valid solutions, return any.

    Example 1:
    Input: n = 3
    Output: [[1,1],[2,1],[2,3],[3,1],[3,5]]
    Explanation: Initially, we choose the shown 5 triangles to be red. Then, we 
                 run the algorithm:
                 - Choose (2,2) that has three red neighbors and color it red.
                 - Choose (3,2) that has two red neighbors and color it red.
                 - Choose (3,4) that has three red neighbors and color it red.
                 - Choose (3,3) that has three red neighbors and color it red.
                 It can be shown that choosing any 4 triangles and running the 
                 algorithm will not make all triangles red.
    
    Example 2:
    Input: n = 2
    Output: [[1,1],[2,1],[2,3]]
    Explanation: Initially, we choose the shown 3 triangles to be red. Then, we 
                 run the algorithm:
                 - Choose (2,2) that has three red neighbors and color it red.
                 It can be shown that choosing any 2 triangles and running the 
                 algorithm will not make all triangles red.

    Constraints: 1 <= n <= 1000*/

    public int[][] colorRed(int n) {
        List<int[]> ans = new ArrayList(); 
        int p = 0; 
        for (int i = n; i >= 1; --i, p = (p+1)%4) 
            if (p == 0) 
                for (int j = 2*i-1; j >= 1; j -= 2) ans.add(new int[]{i, j}); 
            else if (p == 1) {
                if (2 <= i) ans.add(new int[]{i, 2}); 
            } else if (p == 2) 
                for (int j = 2*i-1; j >= 3; j -= 2) ans.add(new int[]{i, j}); 
            else ans.add(new int[]{i, 1}); 
        if (p == 2 || p == 3) ans.add(new int[]{1, 1}); 
        Collections.reverse(ans);
        return ans.toArray(new int[0][0]); 
    }


    /*2655. Find Maximal Uncovered Ranges (Medium)
    You are given an integer n which is the length of a 0-indexed array nums, 
    and a 0-indexed 2D-array ranges, which is a list of sub-ranges of nums 
    (sub-ranges may overlap). Each row ranges[i] has exactly 2 cells:
    * ranges[i][0], which shows the start of the ith range (inclusive)
    * ranges[i][1], which shows the end of the ith range (inclusive)
    These ranges cover some cells of nums and leave some cells uncovered. Your 
    task is to find all of the uncovered ranges with maximal length. Return a 
    2D-array answer of the uncovered ranges, sorted by the starting point in 
    ascending order. By all of the uncovered ranges with maximal length, we 
    mean satisfying two conditions:
    * Each uncovered cell should belong to exactly one sub-range
    * There should not exist two ranges (l1, r1) and (l2, r2) such that 
      r1 + 1 = l2

    Example 1:
    Input: n = 10, ranges = [[3,5],[7,8]]
    Output: [[0,2],[6,6],[9,9]]
    Explanation: The ranges (3, 5) and (7, 8) are covered, so if we simplify 
                 the array nums to a binary array where 0 shows an uncovered 
                 cell and 1 shows a covered cell, the array becomes 
                 [0,0,0,1,1,1,0,1,1,0] in which we can observe that the ranges 
                 (0, 2), (6, 6) and (9, 9) aren't covered.
    
    Example 2:
    Input: n = 3, ranges = [[0,2]]
    Output: []
    Explanation: In this example, the whole of the array nums is covered and 
                 there are no uncovered cells so the output is an empty array.
    
    Example 3:
    Input: n = 7, ranges = [[2,4],[0,3]]
    Output: [[5,6]]
    Explanation: The ranges (0, 3) and (2, 4) are covered, so if we simplify 
                 the array nums to a binary array where 0 shows an uncovered 
                 cell and 1 shows a covered cell, the array becomes 
                 [1,1,1,1,1,0,0] in which we can observe that the range 
                 (5, 6) is uncovered.

    Constraints:
    * 1 <= n <= 10^9
    * 0 <= ranges.length <= 10^6
    * ranges[i].length = 2
    * 0 <= ranges[i][j] <= n - 1
    * ranges[i][0] <= ranges[i][1]*/

    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {
        Arrays.sort(ranges, (x, y)->Integer.compare(x[1], y[1])); 
        Stack<int[]> stk = new Stack(); 
        for (var r : ranges) {
            while (!stk.isEmpty() && r[0] <= stk.peek()[1]) {
                var elem = stk.pop(); 
                r[0] = Math.min(r[0], elem[0]); 
            }
            stk.add(r); 
        }
        List<int[]> ans = new ArrayList(); 
        int prev = 0; 
        for (var elem : stk) {
            if (prev < elem[0]) ans.add(new int[]{prev, elem[0]-1}); 
            prev = Math.max(prev, elem[1]+1); 
        }
        if (prev <= n-1) ans.add(new int[]{prev, n-1}); 
        return ans.toArray(new int[0][0]); 
    }


    /*2656. Maximum Sum With Exactly K Elements (Easy)
    You are given a 0-indexed integer array nums and an integer k. Your task is 
    to perform the following operation exactly k times in order to maximize 
    your score:
    * Select an element m from nums.
    * Remove the selected element m from the array.
    * Add a new element with a value of m + 1 to the array.
    * Increase your score by m.
    Return the maximum score you can achieve after performing the operation 
    exactly k times.

    Example 1:
    Input: nums = [1,2,3,4,5], k = 3
    Output: 18
    Explanation: We need to choose exactly 3 elements from nums to maximize the 
                 sum.
                 - For the first iteration, we choose 5. Then sum is 5 and 
                   nums = [1,2,3,4,6]
                 - For the second iteration, we choose 6. Then sum is 5 + 6 and 
                   nums = [1,2,3,4,7]
                 - For the third iteration, we choose 7. Then sum is 
                   5 + 6 + 7 = 18 and nums = [1,2,3,4,8]
                 So, we will return 18. It can be proven, that 18 is the 
                 maximum answer that we can achieve.
    
    Example 2:
    Input: nums = [5,5,5], k = 2
    Output: 11
    Explanation: We need to choose exactly 2 elements from nums to maximize the 
                 sum.
                 - For the first iteration, we choose 5. Then sum is 5 and 
                   nums = [5,5,6]
                 - For the second iteration, we choose 6. Then sum is 
                   5 + 6 = 11 and nums = [5,5,7]
                 So, we will return 11. It can be proven, that 11 is the 
                 maximum answer that we can achieve.

    Constraints:
    * 1 <= nums.length <= 100
    * 1 <= nums[i] <= 100
    * 1 <= k <= 100*/

    public int maximizeSum(int[] nums, int k) {
        int m = Arrays.stream(nums).max().getAsInt();
        return k*(2*m+k-1)/2; 
    }


    /*2657. Find the Prefix Common Array of Two Arrays (Medium)
    You are given two 0-indexed integer permutations A and B of length n. A 
    prefix common array of A and B is an array C such that C[i] is equal to the 
    count of numbers that are present at or before the index i in both A and B.
    Return the prefix common array of A and B. A sequence of n integers is 
    called a permutation if it contains all integers from 1 to n exactly once.

    Example 1:
    Input: A = [1,3,2,4], B = [3,1,2,4]
    Output: [0,2,3,4]
    Explanation: At i = 0: no number is common, so C[0] = 0.
                 At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
                 At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
                 At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.
    
    Example 2:
    Input: A = [2,3,1], B = [3,1,2]
    Output: [0,1,3]
    Explanation: At i = 0: no number is common, so C[0] = 0.
                 At i = 1: only 3 is common in A and B, so C[1] = 1.
                 At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.

    Constraints:
    * 1 <= A.length == B.length == n <= 50
    * 1 <= A[i], B[i] <= n
    * It is guaranteed that A and B are both a permutation of n integers.*/

    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length; 
        int[] ans = new int[n], seen = new int[n]; 
        for (int i = 0, prefix = 0; i < n; ++i) {
            if (++seen[A[i]-1] == 0) ++prefix; 
            if (--seen[B[i]-1] == 0) ++prefix; 
            ans[i] = prefix; 
        }
        return ans; 
    }


    /*2658. Maximum Number of Fish in a Grid (Hard)
    You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) 
    represents:
    * A land cell if grid[r][c] = 0, or
    * A water cell containing grid[r][c] fish, if grid[r][c] > 0.
    A fisher can start at any water cell (r, c) and can do the following 
    operations any number of times:
    * Catch all the fish at cell (r, c), or
    * Move to any adjacent water cell.
    Return the maximum number of fish the fisher can catch if he chooses his 
    starting cell optimally, or 0 if no water cell exists. An adjacent cell of 
    the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or 
    (r - 1, c) if it exists.

    Example 1:
    Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
    Output: 7
    Explanation: The fisher can start at cell (1,3) and collect 3 fish, then 
                 move to cell (2,3) and collect 4 fish.
    
    Example 2:
    Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
    Output: 1
    Explanation: The fisher can start at cells (0,0) or (3,3) and collect a 
                 single fish. 

    Constraints:
    * m == grid.length
    * n == grid[i].length
    * 1 <= m, n <= 10
    * 0 <= grid[i][j] <= 10*/

    public int findMaxFish(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length; 
        int[] dir = new int[]{-1, 0, 1, 0, -1}; 
        Stack<int[]> stk = new Stack(); 
        for (int r = 0; r < m; ++r) 
            for (int c = 0; c < n; ++c)
                if (grid[r][c] > 0) {
                    int cand = grid[r][c]; 
                    stk.push(new int[]{r, c}); 
                    grid[r][c] = 0; 
                    while (!stk.isEmpty()) {
                        var elem = stk.pop(); 
                        int i = elem[0], j = elem[1]; 
                        for (int k = 0; k < 4; ++k) {
                            int ii = i + dir[k], jj = j + dir[k+1]; 
                            if (0 <= ii && ii < m && 0 <= jj && jj < n && grid[ii][jj] > 0) {
                                cand += grid[ii][jj]; 
                                stk.push(new int[]{ii, jj}); 
                                grid[ii][jj] = 0; 
                            }
                        }
                    }
                    ans = Math.max(ans, cand); 
                }
        return ans; 
    }


    /*2659. Make Array Empty (Hard)
    You are given an integer array nums containing distinct numbers, and you 
    can perform the following operations until the array is empty:
    * If the first element has the smallest value, remove it
    * Otherwise, put the first element at the end of the array.
    Return an integer denoting the number of operations it takes to make nums 
    empty.

    Example 1:
    Input: nums = [3,4,-1]
    Output: 5
    Operation   Array
            1   [4, -1, 3]
            2   [-1, 3, 4]
            3   [3, 4]
            4   [4]
            5   []
    
    Example 2:
    Input: nums = [1,2,4,3]
    Output: 5
    Operation   Array
            1   [2, 4, 3]
            2   [4, 3]
            3   [3, 4]
            4   [4]
            5   []
    
    Example 3:
    Input: nums = [1,2,3]
    Output: 3
    Operation   Array
            1   [2, 3]
            2   [3]
            3   []

    Constraints:
    * 1 <= nums.length <= 10^5
    * -10^9 <= nums[i] <= 10^9
    * All values in nums are distinct.*/

    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length; 
        long ans = n; 
        Map<Integer, Integer> loc = new HashMap(); 
        for (int i = 0; i < n; ++i) loc.put(nums[i], i); 
        Arrays.sort(nums); 
        for (int i = 1; i < n; ++i) 
            if (loc.get(nums[i-1]) > loc.get(nums[i])) ans += n-i; 
        return ans; 
    }


    /*2660. Determine the Winner of a Bowling Game (Easy)
    You are given two 0-indexed integer arrays player1 and player2, that 
    represent the number of pins that player 1 and player 2 hit in a bowling 
    game, respectively. The bowling game consists of n turns, and the number of 
    pins in each turn is exactly 10. Assume a player hit xi pins in the ith 
    turn. The value of the ith turn for the player is:
    * 2xi if the player hit 10 pins in any of the previous two turns.
    * Otherwise, It is xi.
    The score of the player is the sum of the values of their n turns. Return
    * 1 if the score of player 1 is more than the score of player 2,
    * 2 if the score of player 2 is more than the score of player 1, and
    * 0 in case of a draw.

    Example 1:
    Input: player1 = [4,10,7,9], player2 = [6,5,2,3]
    Output: 1
    Explanation: The score of player1 is 4 + 10 + 2*7 + 2*9 = 46.
                 The score of player2 is 6 + 5 + 2 + 3 = 16.
                 Score of player1 is more than the score of player2, so, 
                 player1 is the winner, and the answer is 1.
    
    Example 2:
    Input: player1 = [3,5,7,6], player2 = [8,10,10,2]
    Output: 2
    Explanation: The score of player1 is 3 + 5 + 7 + 6 = 21.
                 The score of player2 is 8 + 10 + 2*10 + 2*2 = 42.
                 Score of player2 is more than the score of player1, so, 
                 player2 is the winner, and the answer is 2.
    
    Example 3:
    Input: player1 = [2,3], player2 = [4,1]
    Output: 0
    Explanation: The score of player1 is 2 + 3 = 5
                 The score of player2 is 4 + 1 = 5
                 The score of player1 equals to the score of player2, so, 
                 there is a draw, and the answer is 0.

    Constraints:
    * n == player1.length == player2.length
    * 1 <= n <= 1000
    * 0 <= player1[i], player2[i] <= 10*/

    private static int calc(int[] player) {
        int ans = 0; 
        for (int i = 0; i < player.length; ++i) {
            ans += player[i]; 
            if (i >= 1 && player[i-1] == 10 || i >= 2 && player[i-2] == 10) ans += player[i]; 
        }
        return ans; 
    }
    
    public int isWinner(int[] player1, int[] player2) {
        int diff = calc(player1) - calc(player2); 
        return diff > 0 ? 1 : diff < 0 ? 2 : 0;
    }


    /*2661. First Completely Painted Row or Column (Medium)
    You are given a 0-indexed integer array arr, and an m x n integer matrix 
    mat. arr and mat both contain all the integers in the range [1, m * n].
    Go through each index i in arr starting from index 0 and paint the cell in 
    mat containing the integer arr[i]. Return the smallest index i at which 
    either a row or a column will be completely painted in mat.

    Example 1:
    image explanation for example 1
    Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
    Output: 2
    Explanation: The moves are shown in order, and both the first row and 
                 second column of the matrix become fully painted at arr[2].
    
    Example 2:
    image explanation for example 2
    Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
    Output: 3
    Explanation: The second column becomes fully painted at arr[3].

    Constraints:
    * m == mat.length
    * n = mat[i].length
    * arr.length == m * n
    * 1 <= m, n <= 10^5
    * 1 <= m * n <= 10^5
    * 1 <= arr[i], mat[r][c] <= m * n
    * All the integers of arr are unique.
    * All the integers of mat are unique.*/

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length; 
        int[][] loc = new int[m*n][2]; 
        for (int i = 0; i < m; ++i) 
            for (int j = 0; j < n; ++j) {
                loc[mat[i][j]-1][0] = i; 
                loc[mat[i][j]-1][1] = j; 
            }
        int[] rows = new int[m], cols = new int[n]; 
        for (int k = 0; k < arr.length; ++k) {
            int i = loc[arr[k]-1][0], j = loc[arr[k]-1][1]; 
            if (++rows[i] == n || ++cols[j] == m) return k; 
        }
        return -1; 
    }


    /*2662. Minimum Cost of a Path With Special Roads (Medium)
    You are given an array start where start = [startX, startY] represents your 
    initial position (startX, startY) in a 2D space. You are also given the 
    array target where target = [targetX, targetY] represents your target 
    position (targetX, targetY). The cost of going from a position (x1, y1) to 
    any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|. There 
    are also some special roads. You are given a 2D array specialRoads where 
    specialRoads[i] = [x1i, y1i, x2i, y2i, costi] indicates that the ith 
    special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal 
    to costi. You can use each special road any number of times. Return the 
    minimum cost required to go from (startX, startY) to (targetX, targetY).

    Example 1:
    Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
    Output: 5
    Explanation: The optimal path from (1,1) to (4,5) is the following:
                 - (1,1) -> (1,2). This move has a cost of |1 - 1| + |2 - 1| = 1.
                 - (1,2) -> (3,3). This move uses the first special edge, the 
                   cost is 2.
                 - (3,3) -> (3,4). This move has a cost of |3 - 3| + |4 - 3| = 1.
                 - (3,4) -> (4,5). This move uses the second special edge, the 
                   cost is 1.
                 So the total cost is 1 + 2 + 1 + 1 = 5. It can be shown that 
                 we cannot achieve a smaller total cost than 5.
    
    Example 2:
    Input: start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
    Output: 7
    Explanation: It is optimal to not use any special edges and go directly 
                 from the starting to the ending position with a cost 
                 |5 - 3| + |7 - 2| = 7.

    Constraints:
    * start.length == target.length == 2
    * 1 <= startX <= targetX <= 10^5
    * 1 <= startY <= targetY <= 10^5
    * 1 <= specialRoads.length <= 200
    * specialRoads[i].length == 5
    * startX <= x1i, x2i <= targetX
    * startY <= y1i, y2i <= targetY
    * 1 <= costi <= 10^5*/

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        long m = 100001; 
        Map<Long, List<long[]>> mp = new HashMap(); 
        mp.put(target[0]*m + target[1], new ArrayList()); 
        mp.get(target[0]*m + target[1]).add(new long[]{0, 0}); 
        for (var x : specialRoads) {
            if (!mp.containsKey(x[0]*m + x[1])) mp.put(x[0]*m + x[1], new ArrayList()); 
            mp.get(x[0]*m + x[1]).add(new long[]{x[2]*m + x[3], x[4]}); 
        }
        Map<Long, Long> dist = new HashMap(); 
        dist.put(start[0]*m + start[1], 0l); 
        Queue<long[]> pq = new PriorityQueue<>((a, b)->Long.compare(a[0], b[0])); 
        pq.add(new long[]{0, start[0]*m + start[1]}); 
        while (!pq.isEmpty()) {
            var elem = pq.poll(); 
            long d = elem[0], x = elem[1]; 
            if (x == target[0]*m + target[1]) return (int) d; 
            if (mp.containsKey(x)) 
                for (var p : mp.get(x)) {
                    long xx = p[0], dd = d + p[1]; 
                    if (!dist.containsKey(xx) || dd < dist.get(xx)) {
                        dist.put(xx, dd); 
                        pq.add(new long[]{dd, xx}); 
                    }
                }
            for (var xx : mp.keySet()) {
                long dd = d + Math.abs(xx%m - x%m) + Math.abs(xx/m - x/m); 
                if (!dist.containsKey(xx) || dd < dist.get(xx)) {
                    dist.put(xx, dd); 
                    pq.add(new long[] {dd, xx}); 
                }
            }
        }
        return -1; 
    }


    /*2663. Lexicographically Smallest Beautiful String (Hard)
    A string is beautiful if:
    * It consists of the first k letters of the English lowercase alphabet.
    * It does not contain any substring of length 2 or more which is a 
      palindrome.
    You are given a beautiful string s of length n and a positive integer k.
    Return the lexicographically smallest string of length n, which is larger 
    than s and is beautiful. If there is no such string, return an empty string.
    A string a is lexicographically larger than a string b (of the same length) 
    if in the first position where a and b differ, a has a character strictly 
    larger than the corresponding character in b. 
    * For example, "abcd" is lexicographically larger than "abcc" because the 
      first position they differ is at the fourth character, and d is greater 
      than c.

    Example 1:
    Input: s = "abcz", k = 26
    Output: "abda"
    Explanation: The string "abda" is beautiful and lexicographically larger 
                 than the string "abcz". It can be proven that there is no 
                 string that is lexicographically larger than the string 
                 "abcz", beautiful, and lexicographically smaller than the 
                 string "abda".
    
    Example 2:
    Input: s = "dc", k = 4
    Output: ""
    Explanation: It can be proven that there is no string that is 
                 lexicographically larger than the string "dc" and is 
                 beautiful.

    Constraints:
    * 1 <= n == s.length <= 10^5
    * 4 <= k <= 26
    * s is a beautiful string.*/

    public String smallestBeautifulString(String s, int k) {
        StringBuilder sb = new StringBuilder(s); 
        for (int n = sb.length(), i = n-1; i >= 0; --i) 
            for (char c = (char) (sb.charAt(i)+1); c < 'a'+k; ++c) 
                if ((i == 0 || s.charAt(i-1) != c) && (i <= 1 || sb.charAt(i-2) != c)) {
                    sb.setCharAt(i, c); 
                    for (int ii = i+1; ii < n; ++ii) 
                        for (char cc = 'a'; cc < 'a'+k; ++cc) 
                            if (cc != sb.charAt(ii-1) && (ii == 1 || cc != sb.charAt(ii-2))) {
                                sb.setCharAt(ii, cc); 
                                break; 
                            }
                    return sb.toString(); 
                }
        return ""; 
    }


    /*2664. The Knight’s Tour (Medium)
    Given two positive integers m and n which are the height and width of a 
    0-indexed 2D-array board, a pair of positive integers (r, c) which is the 
    starting position of the knight on the board. Your task is to find an order 
    of movements for the knight, in a manner that every cell of the board gets 
    visited exactly once (the starting cell is considered visited and you 
    shouldn't visit it again). Return the array board in which the cells' 
    values show the order of visiting the cell starting from 0 (the initial 
    place of the knight). Note that a knight can move from cell (r1, c1) to 
    cell (r2, c2) if 0 <= r2 <= m - 1 and 0 <= c2 <= n - 1 and 
    min(abs(r1 - r2), abs(c1 - c2)) = 1 and max(abs(r1 - r2), abs(c1 - c2)) = 2.

    Example 1:
    Input: m = 1, n = 1, r = 0, c = 0
    Output: [[0]]
    Explanation: There is only 1 cell and the knight is initially on it so 
                 there is only a 0 inside the 1x1 grid.
    
    Example 2:
    Input: m = 3, n = 4, r = 0, c = 0
    Output: [[0,3,6,9],[11,8,1,4],[2,5,10,7]]
    Explanation: By the following order of movements we can visit the entire 
                 board. (0,0)->(1,2)->(2,0)->(0,1)->(1,3)->(2,1)->(0,2)->(2,3)
                 ->(1,1)->(0,3)->(2,2)->(1,0)

    Constraints:
    * 1 <= m, n <= 5
    * 0 <= r <= m - 1
    * 0 <= c <= n - 1
    * The inputs will be generated such that there exists at least one possible
      order of movements with the given condition*/

    private boolean fn(int i, int j, int k, int m, int n, int[][] board) {
        if (k == m*n) return true; 
        int[][] dir = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}}; 
        for (var d : dir) {
            int ii = i + d[0], jj = j + d[1]; 
            if (0 <= ii && ii < m && 0 <= jj && jj < n && board[ii][jj] == -1) {
                board[ii][jj] = k; 
                if (fn(ii, jj, k+1, m, n, board)) return true; 
                board[ii][jj] = -1; 
            }
        }
        return false; 
    }
    
    public int[][] tourOfKnight(int m, int n, int r, int c) {
        int[][] board = new int[m][n]; 
        for (int i = 0; i < m; ++i) Arrays.fill(board[i], -1); 
        board[r][c] = 0; 
        fn(r, c, 1, m, n, board); 
        return board; 
    }


    /*2670. Find the Distinct Difference Array (Easy)
    You are given a 0-indexed array nums of length n. The distinct difference 
    array of nums is an array diff of length n such that diff[i] is equal to 
    the number of distinct elements in the suffix nums[i + 1, ..., n - 1] 
    subtracted from the number of distinct elements in the prefix 
    nums[0, ..., i]. Return the distinct difference array of nums. Note that 
    nums[i, ..., j] denotes the subarray of nums starting at index i and 
    ending at index j inclusive. Particularly, if i > j then nums[i, ..., j] 
    denotes an empty subarray.

    Example 1:
    Input: nums = [1,2,3,4,5]
    Output: [-3,-1,1,3,5]
    Explanation: - For index i = 0, there is 1 element in the prefix and 4 
                   distinct elements in the suffix. Thus, diff[0] = 1 - 4 = -3.
                 - For index i = 1, there are 2 distinct elements in the prefix 
                   and 3 distinct elements in the suffix. Thus, 
                   diff[1] = 2 - 3 = -1.
                 - For index i = 2, there are 3 distinct elements in the prefix 
                   and 2 distinct elements in the suffix. Thus, 
                   diff[2] = 3 - 2 = 1.
                 - For index i = 3, there are 4 distinct elements in the prefix 
                   and 1 distinct element in the suffix. Thus, 
                   diff[3] = 4 - 1 = 3.
                 - For index i = 4, there are 5 distinct elements in the prefix 
                   and no elements in the suffix. Thus, diff[4] = 5 - 0 = 5.
    
    Example 2:
    Input: nums = [3,2,3,4,2]
    Output: [-2,-1,0,2,3]
    Explanation: - For index i = 0, there is 1 element in the prefix and 3 
                   distinct elements in the suffix. Thus, diff[0] = 1 - 3 = -2.
                 - For index i = 1, there are 2 distinct elements in the prefix 
                   and 3 distinct elements in the suffix. Thus, 
                   diff[1] = 2 - 3 = -1.
                 - For index i = 2, there are 2 distinct elements in the prefix 
                   and 2 distinct elements in the suffix. Thus, 
                   diff[2] = 2 - 2 = 0.
                 - For index i = 3, there are 3 distinct elements in the prefix 
                   and 1 distinct element in the suffix. Thus, 
                   diff[3] = 3 - 1 = 2.
                 - For index i = 4, there are 3 distinct elements in the prefix 
                   and no elements in the suffix. Thus, diff[4] = 3 - 0 = 3.

    Constraints:
    * 1 <= n == nums.length <= 50
    * 1 <= nums[i] <= 50*/

    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length; 
        int[] ans = new int[n]; 
        Set<Integer> prefix = new HashSet(); 
        Map<Integer, Integer> suffix = new HashMap(); 
        for (var x : nums) suffix.merge(x, 1, Integer::sum); 
        for (int i = 0; i < n; ++i) {
            prefix.add(nums[i]); 
            suffix.merge(nums[i], -1, Integer::sum); 
            if (suffix.get(nums[i]) == 0) suffix.remove(nums[i]); 
            ans[i] = prefix.size() - suffix.size(); 
        }
        return ans; 
    }


    /*2672. Number of Adjacent Elements With the Same Color (Medium)
    There is a 0-indexed array nums of length n. Initially, all elements are 
    uncolored (has a value of 0). You are given a 2D integer array queries 
    where queries[i] = [indexi, colori]. For each query, you color the index 
    indexi with the color colori in the array nums. Return an array answer of 
    the same length as queries where answer[i] is the number of adjacent 
    elements with the same color after the ith query. More formally, answer[i] 
    is the number of indices j, such that 0 <= j < n - 1 and 
    nums[j] == nums[j + 1] and nums[j] != 0 after the ith query.

    Example 1:
    Input: n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]
    Output: [0,1,1,0,2]
    Explanation: Initially array nums = [0,0,0,0], where 0 denotes uncolored 
                 elements of the array.
                 - After the 1st query nums = [2,0,0,0]. The count of adjacent 
                   elements with the same color is 0.
                 - After the 2nd query nums = [2,2,0,0]. The count of adjacent 
                   elements with the same color is 1.
                 - After the 3rd query nums = [2,2,0,1]. The count of adjacent 
                   elements with the same color is 1.
                 - After the 4th query nums = [2,1,0,1]. The count of adjacent 
                   elements with the same color is 0.
                 - After the 5th query nums = [2,1,1,1]. The count of adjacent 
                   elements with the same color is 2.
    
    Example 2:
    Input: n = 1, queries = [[0,100000]]
    Output: [0]
    Explanation: Initially array nums = [0], where 0 denotes uncolored elements 
                 of the array.
                 - After the 1st query nums = [100000]. The count of adjacent 
                   elements with the same color is 0.

    Constraints:
    * 1 <= n <= 10^5
    * 1 <= queries.length <= 10^5
    * queries[i].length == 2
    * 0 <= indexi <= n - 1
    * 1 <=  colori <= 10^5*/

    public int[] colorTheArray(int n, int[][] queries) {
        List<Integer> ans = new ArrayList(); 
        int[] nums = new int[n]; 
        int cnt = 0; 
        for (var q : queries) {
            int i = q[0], x = q[1]; 
            if (i > 0 && nums[i] > 0 && nums[i-1] == nums[i]) --cnt; 
            if (i+1 < n && nums[i] > 0 && nums[i] == nums[i+1]) --cnt; 
            nums[i] = x; 
            if (i > 0 && nums[i-1] == nums[i]) ++cnt; 
            if (i+1 < n && nums[i] == nums[i+1]) ++cnt; 
            ans.add(cnt); 
        }
        return ans.stream().mapToInt(i -> i).toArray(); 
    }


    /*2673. Make Costs of Paths Equal in a Binary Tree (Medium)
    You are given an integer n representing the number of nodes in a perfect 
    binary tree consisting of nodes numbered from 1 to n. The root of the tree 
    is node 1 and each node i in the tree has two children where the left child 
    is the node 2 * i and the right child is 2 * i + 1. Each node in the tree 
    also has a cost represented by a given 0-indexed integer array cost of size 
    n where cost[i] is the cost of node i + 1. You are allowed to increment the 
    cost of any node by 1 any number of times. Return the minimum number of 
    increments you need to make the cost of paths from the root to each leaf 
    node equal.

    Note:
    * A perfect binary tree is a tree where each node, except the leaf nodes, 
      has exactly 2 children.
    * The cost of a path is the sum of costs of nodes in the path.

    Example 1:
    Input: n = 7, cost = [1,5,2,2,3,3,1]
    Output: 6
    Explanation: We can do the following increments:
                 - Increase the cost of node 4 one time.
                 - Increase the cost of node 3 three times.
                 - Increase the cost of node 7 two times.
                 Each path from the root to a leaf will have a total cost of 9.
                 The total increments we did is 1 + 3 + 2 = 6. It can be shown 
                 that this is the minimum answer we can achieve.
    
    Example 2:
    Input: n = 3, cost = [5,3,3]
    Output: 0
    Explanation: The two paths already have equal total costs, so no increments 
                 are needed.

    Constraints:
    * 3 <= n <= 10^5
    * n + 1 is a power of 2
    * cost.length == n
    * 1 <= cost[i] <= 10^4*/

    public int minIncrements(int n, int[] cost) {
        int ans = 0; 
        for (int i = n/2-1; i >= 0; --i) {
            ans += Math.abs(cost[2*i+1] - cost[2*i+2]); 
            cost[i] += Math.max(cost[2*i+1], cost[2*i+2]); 
        }
        return ans; 
    }


    /*2769. Find the Maximum Achievable Number (Easy)
    You are given two integers, num and t. An integer x is called achievable if 
    it can become equal to num after applying the following operation no more 
    than t times:
    * Increase or decrease x by 1, and simultaneously increase or decrease num 
      by 1.
    Return the maximum possible achievable number. It can be proven that there 
    exists at least one achievable number.

    Example 1:
    Input: num = 4, t = 1
    Output: 6
    Explanation: The maximum achievable number is x = 6; it can become equal to 
                 num after performing this operation:
                 * 1- Decrease x by 1, and increase num by 1. Now, x = 5 and 
                 num = 5. 
                 It can be proven that there is no achievable number larger 
                 than 6.

    Example 2:
    Input: num = 3, t = 2
    Output: 7
    Explanation: The maximum achievable number is x = 7; after performing these 
                 operations, x will equal num: 
                 * 1- Decrease x by 1, and increase num by 1. Now, x = 6 and 
                   num = 4.
                 * 2- Decrease x by 1, and increase num by 1. Now, x = 5 and 
                   num = 5.
                 It can be proven that there is no achievable number larger 
                 than 7.

    Constraints: 1 <= num, t <= 50*/

    public int theMaximumAchievableX(int num, int t) {
        return num + 2*t; 
    }


    /*2770. Maximum Number of Jumps to Reach the Last Index (Medium)
    You are given a 0-indexed array nums of n integers and an integer target. 
    You are initially positioned at index 0. In one step, you can jump from 
    index i to any index j such that:
    * 0 <= i < j < n
    * -target <= nums[j] - nums[i] <= target
    Return the maximum number of jumps you can make to reach index n - 1. If 
    there is no way to reach index n - 1, return -1.

    Example 1:
    Input: nums = [1,3,6,4,1,2], target = 2
    Output: 3
    Explanation: To go from index 0 to index n - 1 with the maximum number of 
                 jumps, you can perform the following jumping sequence:
                 - Jump from index 0 to index 1. 
                 - Jump from index 1 to index 3.
                 - Jump from index 3 to index 5.
                 It can be proven that there is no other jumping sequence that 
                 goes from 0 to n - 1 with more than 3 jumps. Hence, the answer 
                 is 3. 
    
    Example 2:
    Input: nums = [1,3,6,4,1,2], target = 3
    Output: 5
    Explanation: To go from index 0 to index n - 1 with the maximum number of 
                 jumps, you can perform the following jumping sequence:
                 - Jump from index 0 to index 1.
                 - Jump from index 1 to index 2.
                 - Jump from index 2 to index 3.
                 - Jump from index 3 to index 4.
                 - Jump from index 4 to index 5.
                 It can be proven that there is no other jumping sequence that 
                 goes from 0 to n - 1 with more than 5 jumps. Hence, the answer 
                 is 5. 
    
    Example 3:
    Input: nums = [1,3,6,4,1,2], target = 0
    Output: -1
    Explanation: It can be proven that there is no jumping sequence that goes 
                 from 0 to n - 1. Hence, the answer is -1. 

    Constraints:
    * 2 <= nums.length == n <= 1000
    * -10^9 <= nums[i] <= 10^9
    * 0 <= target <= 2 * 10^9*/

    public int maximumJumps(int[] nums, int target) {
        int n = nums.length; 
        int[] dp = new int[n]; 
        Arrays.fill(dp, -1); 
        dp[n-1] = 0; 
        for (int i = n-2; i >= 0; --i) 
            for (int j = i+1; j < n; ++j)
                if (dp[j] != -1 && Math.abs(nums[i]-nums[j]) <= target) 
                    dp[i] = Math.max(dp[i], 1+dp[j]); 
        return dp[0]; 
    }


    /*2771. Longest Non-decreasing Subarray From Two Arrays (Medium)
    You are given two 0-indexed integer arrays nums1 and nums2 of length n. 
    Let's define another 0-indexed integer array, nums3, of length n. For each 
    index i in the range [0, n - 1], you can assign either nums1[i] or nums2[i] 
    to nums3[i]. Your task is to maximize the length of the longest non-
    decreasing subarray in nums3 by choosing its values optimally. Return an 
    integer representing the length of the longest non-decreasing subarray in 
    nums3. Note: A subarray is a contiguous non-empty sequence of elements 
    within an array.

    Example 1:
    Input: nums1 = [2,3,1], nums2 = [1,2,1]
    Output: 2
    Explanation: One way to construct nums3 is: 
                 nums3 = [nums1[0], nums2[1], nums2[2]] => [2,2,1]. 
                 The subarray starting from index 0 and ending at index 1, 
                 [2,2], forms a non-decreasing subarray of length 2. We can 
                 show that 2 is the maximum achievable length.
    
    Example 2:
    Input: nums1 = [1,3,2,1], nums2 = [2,2,3,4]
    Output: 4
    Explanation: One way to construct nums3 is: 
                 nums3 = [nums1[0], nums2[1], nums2[2], nums2[3]] => [1,2,3,4]. 
                 The entire array forms a non-decreasing subarray of length 4, 
                 making it the maximum achievable length.
    
    Example 3:
    Input: nums1 = [1,1], nums2 = [2,2]
    Output: 2
    Explanation: One way to construct nums3 is: 
                 nums3 = [nums1[0], nums1[1]] => [1,1]. 
                 The entire array forms a non-decreasing subarray of length 2, 
                 making it the maximum achievable length.

    Constraints:
    * 1 <= nums1.length == nums2.length == n <= 10^5
    * 1 <= nums1[i], nums2[i] <= 10^9*/

    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int ans = 0, dp1 = 1, dp2 = 1; 
        for (int i = 0; i < nums1.length; ++i) {
            int dp11 = 1, dp12 = 1, dp21 = 1, dp22 = 1; 
            if (i > 0 && nums1[i-1] <= nums1[i]) dp11 += dp1; 
            if (i > 0 && nums2[i-1] <= nums1[i]) dp21 += dp2; 
            if (i > 0 && nums1[i-1] <= nums2[i]) dp12 += dp1; 
            if (i > 0 && nums2[i-1] <= nums2[i]) dp22 += dp2; 
            dp1 = Math.max(dp11, dp21); 
            dp2 = Math.max(dp12, dp22); 
            ans = Math.max(ans, Math.max(dp1, dp2)); 
        }
        return ans; 
    }


    /*2772. Apply Operations to Make All Array Elements Equal to Zero (Medium)
    You are given a 0-indexed integer array nums and a positive integer k. You 
    can apply the following operation on the array any number of times:
    * Choose any subarray of size k from the array and decrease all its 
      elements by 1.
    Return true if you can make all the array elements equal to 0, or false 
    otherwise. A subarray is a contiguous non-empty part of an array.

    Example 1:
    Input: nums = [2,2,3,1,1,0], k = 3
    Output: true
    Explanation: We can do the following operations:
                 - Choose the subarray [2,2,3]. The resulting array will be 
                   nums = [1,1,2,1,1,0].
                 - Choose the subarray [2,1,1]. The resulting array will be 
                   nums = [1,1,1,0,0,0].
                 - Choose the subarray [1,1,1]. The resulting array will be 
                   nums = [0,0,0,0,0,0].
    
    Example 2:
    Input: nums = [1,3,1,1], k = 2
    Output: false
    Explanation: It is not possible to make all the array elements equal to 0.

    Constraints:
    * 1 <= k <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^6*/

    public boolean checkArray(int[] nums, int k) {
        int prefix = 0; 
        for (int i = 0; i < nums.length; ++i) {
            if (prefix > nums[i]) return false; 
            int temp = nums[i]; 
            nums[i] -= prefix; 
            prefix = temp; 
            if (i >= k-1) prefix -= nums[i-k+1]; 
        }
        return prefix == 0; 
    }


    /*2778. Sum of Squares of Special Elements (Easy)
    You are given a 1-indexed integer array nums of length n. An element nums[i] 
    of nums is called special if i divides n, i.e. n % i == 0. Return the sum 
    of the squares of all special elements of nums.

    Example 1:
    Input: nums = [1,2,3,4]
    Output: 21
    Explanation: There are exactly 3 special elements in nums: nums[1] since 1 
                 divides 4, nums[2] since 2 divides 4, and nums[4] since 4 
                 divides 4. Hence, the sum of the squares of all special 
                 elements of nums is 
                 nums[1] * nums[1] + nums[2] * nums[2] + nums[4] * nums[4] = 
                 1 * 1 + 2 * 2 + 4 * 4 = 21.  
    
    Example 2:
    Input: nums = [2,7,1,19,18,3]
    Output: 63
    Explanation: There are exactly 4 special elements in nums: nums[1] since 1 
                 divides 6, nums[2] since 2 divides 6, nums[3] since 3 divides 
                 6, and nums[6] since 6 divides 6. Hence, the sum of the 
                 squares of all special elements of nums is 
                 nums[1] * nums[1] + nums[2] * nums[2] + nums[3] * nums[3] + 
                 nums[6] * nums[6] = 2 * 2 + 7 * 7 + 1 * 1 + 3 * 3 = 63. 

    Constraints:
    * 1 <= nums.length == n <= 50
    * 1 <= nums[i] <= 50*/

    public int sumOfSquares(int[] nums) {
        int ans = 0; 
        for (int i = 0, n = nums.length; i < n; ++i)
            if (n % (i+1) == 0) ans += nums[i] * nums[i]; 
        return ans; 
    }


    /*2779. Maximum Beauty of an Array After Applying Operation (Medium)
    You are given a 0-indexed array nums and a non-negative integer k. In one 
    operation, you can do the following:
    * Choose an index i that hasn't been chosen before from the range 
      [0, nums.length - 1].
    * Replace nums[i] with any integer from the range 
      [nums[i] - k, nums[i] + k].
    The beauty of the array is the length of the longest subsequence consisting 
    of equal elements. Return the maximum possible beauty of the array nums 
    after applying the operation any number of times. Note that you can apply 
    the operation to each index only once. A subsequence of an array is a new 
    array generated from the original array by deleting some elements (possibly 
    none) without changing the order of the remaining elements.

    Example 1:
    Input: nums = [4,6,1,2], k = 2
    Output: 3
    Explanation: In this example, we apply the following operations:
                 - Choose index 1, replace it with 4 (from range [4,8]), 
                   nums = [4,4,1,2].
                 - Choose index 3, replace it with 4 (from range [0,4]), 
                   nums = [4,4,1,4].
                 After the applied operations, the beauty of the array nums is 
                 3 (subsequence consisting of indices 0, 1, and 3). It can be 
                 proven that 3 is the maximum possible length we can achieve.
    
    Example 2:
    Input: nums = [1,1,1,1], k = 10
    Output: 4
    Explanation: In this example we don't have to apply any operations. The 
                 beauty of the array nums is 4 (whole array).

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i], k <= 10^5*/

    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums); 
        int i = 0, ii = 0; 
        for (; i < nums.length; ++i) 
            if (nums[i] - nums[ii] > 2*k) ++ii; 
        return i - ii; 
    }


    /*2780. Minimum Index of a Valid Split (Medium)
    An element x of an integer array arr of length m is dominant if 
    freq(x) * 2 > m, where freq(x) is the number of occurrences of x in arr. 
    Note that this definition implies that arr can have at most one dominant 
    element. You are given a 0-indexed integer array nums of length n with one 
    dominant element. You can split nums at an index i into two arrays 
    nums[0, ..., i] and nums[i + 1, ..., n - 1], but the split is only valid if:
    * 0 <= i < n - 1
    * nums[0, ..., i], and nums[i + 1, ..., n - 1] have the same dominant 
      element.
    Here, nums[i, ..., j] denotes the subarray of nums starting at index i and 
    ending at index j, both ends being inclusive. Particularly, if j < i then 
    nums[i, ..., j] denotes an empty subarray. Return the minimum index of a 
    valid split. If no valid split exists, return -1.

    Example 1:
    Input: nums = [1,2,2,2]
    Output: 2
    Explanation: We can split the array at index 2 to obtain arrays [1,2,2] and 
                 [2]. In array [1,2,2], element 2 is dominant since it occurs 
                 twice in the array and 2 * 2 > 3. In array [2], element 2 is 
                 dominant since it occurs once in the array and 1 * 2 > 1. Both 
                 [1,2,2] and [2] have the same dominant element as nums, so 
                 this is a valid split. It can be shown that index 2 is the 
                 minimum index of a valid split. 
    
    Example 2:
    Input: nums = [2,1,3,1,1,1,7,1,2,1]
    Output: 4
    Explanation: We can split the array at index 4 to obtain arrays [2,1,3,1,1] 
                 and [1,7,1,2,1]. In array [2,1,3,1,1], element 1 is dominant 
                 since it occurs thrice in the array and 3 * 2 > 5. In array 
                 [1,7,1,2,1], element 1 is dominant since it occurs thrice in 
                 the array and 3 * 2 > 5. Both [2,1,3,1,1] and [1,7,1,2,1] have 
                 the same dominant element as nums, so this is a valid split. 
                 It can be shown that index 4 is the minimum index of a valid 
                 split.
    
    Example 3:
    Input: nums = [3,3,3,3,7,2,2]
    Output: -1
    Explanation: It can be shown that there is no valid split.
     
    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9
    * nums has exactly one dominant element.*/

    public int minimumIndex(List<Integer> nums) {
        Map<Integer, Integer> freq = new HashMap(); 
        int k = 0, v = 0; 
        for (var x : nums) {
            freq.merge(x, 1, Integer::sum); 
            if (freq.get(x) > v) {
                k = x; 
                v = freq.get(x); 
            }
        }
        for (int i = 0, n = nums.size(), prefix = 0; i < n-1; ++i) {
            if (nums.get(i) == k) ++prefix; 
            if (prefix*2 > i+1 && (v-prefix)*2 > n-i-1) return i; 
        }
        return -1; 
    }


    /*2781. Length of the Longest Valid Substring (Hard)
    You are given a string word and an array of strings forbidden. A string is 
    called valid if none of its substrings are present in forbidden. Return the 
    length of the longest valid substring of the string word. A substring is a 
    contiguous sequence of characters in a string, possibly empty.

    Example 1:
    Input: word = "cbaaaabc", forbidden = ["aaa","cb"]
    Output: 4
    Explanation: There are 9 valid substrings in word: "c", "b", "a", "ba", 
                 "aa", "bc", "baa", "aab", and "aabc". The length of the 
                 longest valid substring is 4. It can be shown that all other 
                 substrings contain either "aaa" or "cb" as a substring. 
    
    Example 2:
    Input: word = "leetcode", forbidden = ["de","le","e"]
    Output: 4
    Explanation: There are 11 valid substrings in word: "l", "t", "c", "o", 
                 "d", "tc", "co", "od", "tco", "cod", and "tcod". The length of 
                 the longest valid substring is 4. It can be shown that all 
                 other substrings contain either "de", "le", or "e" as a 
                 substring. 

    Constraints:
    * 1 <= word.length <= 10^5
    * word consists only of lowercase English letters.
    * 1 <= forbidden.length <= 10^5
    * 1 <= forbidden[i].length <= 10
    * forbidden[i] consists only of lowercase English letters.*/

    public int longestValidSubstring(String word, List<String> forbidden) {
        Set<String> forbid = new HashSet<>(forbidden); 
        int ans = 0, val = 0; 
        for (int i = word.length()-1; i >= 0; --i) {
            ++val; 
            for (int k = 0; k < 10 && k < val; ++k) 
                if (forbid.contains(word.substring(i, i+k+1))) {
                    val = k; 
                    break; 
                }
            ans = Math.max(ans, val); 
        }
        return ans; 
    }


    /*2784. Check if Array is Good (Easy)
    You are given an integer array nums. We consider an array good if it is a 
    permutation of an array base[n]. base[n] = [1, 2, ..., n - 1, n, n] (in 
    other words, it is an array of length n + 1 which contains 1 to n - 1 
    exactly once, plus two occurrences of n). For example, base[1] = [1, 1] and 
    base[3] = [1, 2, 3, 3]. Return true if the given array is good, otherwise 
    return false. Note: A permutation of integers represents an arrangement of 
    these numbers.

    Example 1:
    Input: nums = [2, 1, 3]
    Output: false
    Explanation: Since the maximum element of the array is 3, the only 
                 candidate n for which this array could be a permutation of 
                 base[n], is n = 3. However, base[3] has four elements but 
                 array nums has three. Therefore, it can not be a permutation 
                 of base[3] = [1, 2, 3, 3]. So the answer is false.
    
    Example 2:
    Input: nums = [1, 3, 3, 2]
    Output: true
    Explanation: Since the maximum element of the array is 3, the only 
                 candidate n for which this array could be a permutation of 
                 base[n], is n = 3. It can be seen that nums is a permutation 
                 of base[3] = [1, 2, 3, 3] (by swapping the second and fourth 
                 elements in nums, we reach base[3]). Therefore, the answer is 
                 true.
    
    Example 3:
    Input: nums = [1, 1]
    Output: true
    Explanation: Since the maximum element of the array is 1, the only 
                 candidate n for which this array could be a permutation of 
                 base[n], is n = 1. It can be seen that nums is a permutation 
                 of base[1] = [1, 1]. Therefore, the answer is true.
    
    Example 4:
    Input: nums = [3, 4, 4, 1, 2, 1]
    Output: false
    Explanation: Since the maximum element of the array is 4, the only 
                 candidate n for which this array could be a permutation of 
                 base[n], is n = 4. However, base[4] has five elements but 
                 array nums has six. Therefore, it can not be a permutation of 
                 base[4] = [1, 2, 3, 4, 4]. So the answer is false.

    Constraints:
    * 1 <= nums.length <= 100
    * 1 <= num[i] <= 200*/

    public boolean isGood(int[] nums) {
        int n = nums.length-1, cnt = 0; 
        for (int i = 0; i <= n && cnt <= n; ) 
            if (nums[i] == i+1 || i == n && nums[n] == n) ++i; 
            else {
                if (nums[i] < 0 || nums[i] > n) return false; 
                int ii = nums[i]-1; 
                if (nums[i] == n && nums[n] != n) ii = n; 
                int temp = nums[i]; 
                nums[i] = nums[ii]; 
                nums[ii] = temp; 
                ++cnt; 
            }
        return 0 < n && cnt <= n; 
    }


    /*2785. Sort Vowels in a String (Medium)
    Given a 0-indexed string s, permute s to get a new string t such that:
    * All consonants remain in their original places. More formally, if there 
      is an index i with 0 <= i < s.length such that s[i] is a consonant, then 
      t[i] = s[i].
    * The vowels must be sorted in the nondecreasing order of their ASCII 
      values. More formally, for pairs of indices i, j with 
      0 <= i < j < s.length such that s[i] and s[j] are vowels, then t[i] must 
      not have a higher ASCII value than t[j].
    Return the resulting string. The vowels are 'a', 'e', 'i', 'o', and 'u', 
    and they can appear in lowercase or uppercase. Consonants comprise all 
    letters that are not vowels.

    Example 1:
    Input: s = "lEetcOde"
    Output: "lEOtcede"
    Explanation: 'E', 'O', and 'e' are the vowels in s; 'l', 't', 'c', and 'd' 
                 are all consonants. The vowels are sorted according to their 
                 ASCII values, and the consonants remain in the same places.
    
    Example 2:
    Input: s = "lYmpH"
    Output: "lYmpH"
    Explanation: There are no vowels in s (all characters in s are consonants), 
                 so we return "lYmpH".

    Constraints:
    * 1 <= s.length <= 10^5
    * s consists only of letters of the English alphabet in uppercase and 
      lowercase.*/

    public String sortVowels(String s) {
        StringBuilder sb = new StringBuilder(); 
        for (var ch : s.toCharArray()) 
            if ("aeiouAEIOU".indexOf(ch) >= 0) sb.append(ch); 
        String vowels = sb.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        StringBuilder ans = new StringBuilder(); 
        int i = 0; 
        for (var ch : s.toCharArray()) 
            if ("aeiouAEIOU".indexOf(ch) >= 0) ans.append(vowels.charAt(i++)); 
            else ans.append(ch); 
        return ans.toString(); 
    }


    /*2786. Visit Array Positions to Maximize Score (Medium)
    You are given a 0-indexed integer array nums and a positive integer x. You 
    are initially at position 0 in the array and you can visit other positions 
    according to the following rules:
    * If you are currently in position i, then you can move to any position j 
      such that i < j.
    * For each position i that you visit, you get a score of nums[i].
    * If you move from a position i to a position j and the parities of nums[i] 
      and nums[j] differ, then you lose a score of x.
    Return the maximum total score you can get. Note that initially you have 
    nums[0] points.

    Example 1:
    Input: nums = [2,3,6,1,9,2], x = 5
    Output: 13
    Explanation: We can visit the following positions in the array: 0 -> 2 -> 
                 3 -> 4. The corresponding values are 2, 6, 1 and 9. Since the 
                 integers 6 and 1 have different parities, the move 2 -> 3 will 
                 make you lose a score of x = 5. The total score will be: 
                 2 + 6 + 1 + 9 - 5 = 13.
    
    Example 2:
    Input: nums = [2,4,6,8], x = 3
    Output: 20
    Explanation: All the integers in the array have the same parities, so we 
                 can visit all of them without losing any score. The total 
                 score is: 2 + 4 + 6 + 8 = 20.

    Constraints:
    * 2 <= nums.length <= 10^5
    * 1 <= nums[i], x <= 10^6*/

    public long maxScore(int[] nums, int x) {
        long[] dp = new long[2]; 
        Arrays.fill(dp, -x); 
        for (int i = 0; i < nums.length; ++i) 
            if (i > 0) dp[nums[i]&1] = nums[i] + Math.max(dp[nums[i]&1], dp[nums[i]&1^1]-x); 
            else dp[nums[i]&1] = nums[i]; 
        return Arrays.stream(dp).max().getAsLong(); 
    }


    /*2787. Ways to Express an Integer as Sum of Powers (Medium)
    Given two positive integers n and x. Return the number of ways n can be 
    expressed as the sum of the xth power of unique positive integers, in other 
    words, the number of sets of unique integers [n1, n2, ..., nk] where 
    n = n1^x + n2^x + ... + nk^x. Since the result can be very large, return it 
    modulo 10^9 + 7. For example, if n = 160 and x = 3, one way to express n is 
    n = 2^3 + 3^3 + 5^3.

    Example 1:
    Input: n = 10, x = 2
    Output: 1
    Explanation: We can express n as the following: n = 32 + 12 = 10. It can be 
                 shown that it is the only way to express 10 as the sum of the 
                 2nd power of unique integers.
    
    Example 2:
    Input: n = 4, x = 1
    Output: 2
    Explanation: We can express n in the following ways:
                 - n = 4^1 = 4.
                 - n = 3^1 + 1^1 = 4.

    Constraints:
    * 1 <= n <= 300
    * 1 <= x <= 5*/

    public int numberOfWays(int n, int x) {
        long[] dp = new long[n+1]; 
        dp[0] = 1; 
        for (int k = 1, v = 1; v <= n; v = (int) Math.pow(++k, x)) 
            for (int i = n; i >= v; --i)
                dp[i] = (dp[i-v] + dp[i]) % 1_000_000_007; 
        return (int) dp[n]; 
    }


    /*2806. Account Balance After Rounded Purchase (Easy)
    Initially, you have a bank account balance of 100 dollars. You are given an 
    integer purchaseAmount representing the amount you will spend on a purchase 
    in dollars. At the store where you will make the purchase, the purchase 
    amount is rounded to the nearest multiple of 10. In other words, you pay a 
    non-negative amount, roundedAmount, such that roundedAmount is a multiple 
    of 10 and abs(roundedAmount - purchaseAmount) is minimized. If there is 
    more than one nearest multiple of 10, the largest multiple is chosen. 
    Return an integer denoting your account balance after making a purchase 
    worth purchaseAmount dollars from the store. Note: 0 is considered to be a 
    multiple of 10 in this problem.

    Example 1:
    Input: purchaseAmount = 9
    Output: 90
    Explanation: In this example, the nearest multiple of 10 to 9 is 10. Hence, 
                 your account balance becomes 100 - 10 = 90.
    
    Example 2:
    Input: purchaseAmount = 15
    Output: 80
    Explanation: In this example, there are two nearest multiples of 10 to 15: 
                 10 and 20. So, the larger multiple, 20, is chosen. Hence, 
                 your account balance becomes 100 - 20 = 80.

    Constraints: 0 <= purchaseAmount <= 100*/

    public int accountBalanceAfterPurchase(int purchaseAmount) {
        return 100 - (purchaseAmount+5)/10*10; 
    }


    /*2807. Insert Greatest Common Divisors in Linked List (Medium)
    Given the head of a linked list head, in which each node contains an 
    integer value. Between every pair of adjacent nodes, insert a new node with 
    a value equal to the greatest common divisor of them. Return the linked 
    list after insertion. The greatest common divisor of two numbers is the 
    largest positive integer that evenly divides both numbers.

    Example 1:
    Input: head = [18,6,10,3]
    Output: [18,6,6,2,10,1,3]
    Explanation: The 1st diagram denotes the initial linked list and the 2nd 
                 diagram denotes the linked list after inserting the new nodes 
                 (nodes in blue are the inserted nodes).
                 - We insert the greatest common divisor of 18 and 6 = 6 
                   between the 1st and the 2nd nodes.
                 - We insert the greatest common divisor of 6 and 10 = 2 
                   between the 2nd and the 3rd nodes.
                 - We insert the greatest common divisor of 10 and 3 = 1 
                   between the 3rd and the 4th nodes.
                 There are no more adjacent nodes, so we return the linked list.
    
    Example 2:
    Input: head = [7]
    Output: [7]
    Explanation: The 1st diagram denotes the initial linked list and the 2nd 
                 diagram denotes the linked list after inserting the new nodes.
                 There are no pairs of adjacent nodes, so we return the initial 
                 linked list.

    Constraints:
    * The number of nodes in the list is in the range [1, 5000].
    * 1 <= Node.val <= 1000*/

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head; 
        while (node != null && node.next != null) {
            ListNode temp = new ListNode(BigInteger.valueOf(node.val).gcd(BigInteger.valueOf(node.next.val)).intValue(), node.next); 
            node.next = temp; 
            node = node.next.next; 
        }
        return head; 
    }


    /*2808. Minimum Seconds to Equalize a Circular Array (Medium)
    You are given a 0-indexed array nums containing n integers. At each second, 
    you perform the following operation on the array:
    * For every index i in the range [0, n - 1], replace nums[i] with either 
      nums[i], nums[(i - 1 + n) % n], or nums[(i + 1) % n].
    Note that all the elements get replaced simultaneously. Return the minimum 
    number of seconds needed to make all elements in the array nums equal.

    Example 1:
    Input: nums = [1,2,1,2]
    Output: 1
    Explanation: We can equalize the array in 1 second in the following way:
                 - At 1st second, replace values at each index with 
                   [nums[3],nums[1],nums[3],nums[3]]. After replacement, 
                   nums = [2,2,2,2].
                 It can be proven that 1 second is the minimum amount of 
                 seconds needed for equalizing the array.
    
    Example 2:
    Input: nums = [2,1,3,3,2]
    Output: 2
    Explanation: We can equalize the array in 2 seconds in the following way:
                 - At 1st second, replace values at each index with 
                   [nums[0],nums[2],nums[2],nums[2],nums[3]]. After replacement, 
                   nums = [2,3,3,3,3].
                 - At 2nd second, replace values at each index with 
                   [nums[1],nums[1],nums[2],nums[3],nums[4]]. After replacement, 
                   nums = [3,3,3,3,3].
                 It can be proven that 2 seconds is the minimum amount of 
                 seconds needed for equalizing the array.
    
    Example 3:
    Input: nums = [5,5,5,5]
    Output: 0
    Explanation: We don't need to perform any operations as all elements in the 
                 initial array are the same.

    Constraints:
    * 1 <= n == nums.length <= 10^5
    * 1 <= nums[i] <= 10^9*/

    public int minimumSeconds(List<Integer> nums) {
        int ans = Integer.MAX_VALUE, n = nums.size(); 
        Map<Integer, List<Integer>> pos = new HashMap(); 
        for (int i = 0; i < n; ++i) {
            pos.putIfAbsent(nums.get(i), new ArrayList()); 
            pos.get(nums.get(i)).add(i); 
        }
        for (var elem : pos.entrySet()) {
            int k = elem.getKey(), cand = 0, prev = -1; 
            List<Integer> v = elem.getValue(); 
            for (var x : v) {
                if (prev >= 0) cand = Math.max(cand, x - prev); 
                prev = x; 
            }
            cand = Math.max(cand, v.get(0) + n - v.get(v.size()-1)); 
            ans = Math.min(ans, cand); 
        }
        return ans/2; 
    }


    /*2809. Minimum Time to Make Array Sum At Most x (Hard)
    You are given two 0-indexed integer arrays nums1 and nums2 of equal length. 
    Every second, for all indices 0 <= i < nums1.length, value of nums1[i] is 
    incremented by nums2[i]. After this is done, you can do the following 
    operation:
    * Choose an index 0 <= i < nums1.length and make nums1[i] = 0.
    You are also given an integer x. Return the minimum time in which you can 
    make the sum of all elements of nums1 to be less than or equal to x, or -1 
    if this is not possible.

    Example 1:
    Input: nums1 = [1,2,3], nums2 = [1,2,3], x = 4
    Output: 3
    Explanation: For the 1st second, we apply the operation on i = 0. Therefore 
                 nums1 = [0,2+2,3+3] = [0,4,6]. 
                 For the 2nd second, we apply the operation on i = 1. Therefore 
                 nums1 = [0+1,0,6+3] = [1,0,9]. 
                 For the 3rd second, we apply the operation on i = 2. Therefore 
                 nums1 = [1+1,0+2,0] = [2,2,0]. 
                 Now sum of nums1 = 4. It can be shown that these operations 
                 are optimal, so we return 3.

    Example 2:
    Input: nums1 = [1,2,3], nums2 = [3,3,3], x = 4
    Output: -1
    Explanation: It can be shown that the sum of nums1 will always be greater 
                 than x, no matter which operations are performed.

    Constraints:
    * 1 <= nums1.length <= 10^3
    * 1 <= nums1[i] <= 10^3
    * 0 <= nums2[i] <= 10^3
    * nums1.length == nums2.length
    * 0 <= x <= 10^6*/

    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size(), s1 = nums1.stream().mapToInt(i -> i).sum(), s2 = nums2.stream().mapToInt(i -> i).sum(); 
        List<int[]> aug = new ArrayList(); 
        for (int i = 0; i < n; ++i)
            aug.add(new int[]{nums1.get(i), nums2.get(i)}); 
        Collections.sort(aug, (a, b) -> Integer.compare(a[1], b[1])); 
        int[][] dp = new int[n+1][n+1]; 
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= i; ++j)
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1] + aug.get(i-1)[1]*j + aug.get(i-1)[0]); 
        for (int t = 0; t <= n; ++t)
            if (s1 + s2*t - dp[n][t] <= x) return t; 
        return -1; 
    }


    /*2810. Faulty Keyboard (Easy)
    Your laptop keyboard is faulty, and whenever you type a character 'i' on it, 
    it reverses the string that you have written. Typing other characters works 
    as expected. You are given a 0-indexed string s, and you type each 
    character of s using your faulty keyboard. Return the final string that 
    will be present on your laptop screen.

    Example 1:
    Input: s = "string"
    Output: "rtsng"
    Explanation: - After typing first character, the text on the screen is "s".
                 - After the second character, the text is "st". 
                 - After the third character, the text is "str".
                 - Since the fourth character is an 'i', the text gets reversed 
                 - and becomes "rts".
                 - After the fifth character, the text is "rtsn". 
                 - After the sixth character, the text is "rtsng". 
                 - Therefore, we return "rtsng".
    
    Example 2:
    Input: s = "poiinter"
    Output: "ponter"
    Explanation: - After the first character, the text on the screen is "p".
                 - After the second character, the text is "po". 
                 - Since the third character you type is an 'i', the text gets 
                   reversed and becomes "op". 
                 - Since the fourth character you type is an 'i', the text gets 
                   reversed and becomes "po".
                 - After the fifth character, the text is "pon".
                 - After the sixth character, the text is "pont". 
                 - After the seventh character, the text is "ponte". 
                 - After the eighth character, the text is "ponter". 
                 - Therefore, we return "ponter".

    Constraints:
    * 1 <= s.length <= 100
    * s consists of lowercase English letters.
    * s[0] != 'i'*/

    public String finalString(String s) {
        Deque<Character> qq = new ArrayDeque(); 
        boolean flip = false; 
        for (var ch : s.toCharArray()) {
            if (ch == 'i') flip = !flip; 
            else if (!flip) qq.addLast(ch); 
            else qq.addFirst(ch); 
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Character> it = qq.iterator();
        if (flip) it = qq.descendingIterator(); 
        while (it.hasNext()) 
            sb.append(it.next()); 
        return sb.toString(); 
    }


    /*2811. Check if it is Possible to Split Array (Medium)
    You are given an array nums of length n and an integer m. You need to 
    determine if it is possible to split the array into n non-empty arrays by 
    performing a series of steps. In each step, you can select an existing 
    array (which may be the result of previous steps) with a length of at least 
    two and split it into two subarrays, if, for each resulting subarray, at 
    least one of the following holds:
    * The length of the subarray is one, or
    * The sum of elements of the subarray is greater than or equal to m.
    Return true if you can split the given array into n arrays, otherwise 
    return false. Note: A subarray is a contiguous non-empty sequence of 
    elements within an array.

    Example 1:
    Input: nums = [2, 2, 1], m = 4
    Output: true
    Explanation: We can split the array into [2, 2] and [1] in the first step. 
                 Then, in the second step, we can split [2, 2] into [2] and [2]. 
                 As a result, the answer is true.
    
    Example 2:
    Input: nums = [2, 1, 3], m = 5 
    Output: false
    Explanation: We can try splitting the array in two different ways: the 
                 first way is to have [2, 1] and [3], and the second way is to 
                 have [2] and [1, 3]. However, both of these ways are not valid. 
                 So, the answer is false.
    
    Example 3:
    Input: nums = [2, 3, 3, 2, 3], m = 6
    Output: true
    Explanation: We can split the array into [2, 3, 3, 2] and [3] in the first 
                 step. Then, in the second step, we can split [2, 3, 3, 2] into 
                 [2, 3, 3] and [2]. Then, in the third step, we can split 
                 [2, 3, 3] into [2] and [3, 3]. And in the last step we can 
                 split [3, 3] into [3] and [3]. As a result, the answer is true.

    Constraints:
    * 1 <= n == nums.length <= 100
    * 1 <= nums[i] <= 100
    * 1 <= m <= 200*/

    public boolean canSplitArray(List<Integer> nums, int m) {
        if (nums.size() <= 2) return true; 
        for (int i = 0; i < nums.size()-1; ++i)
            if (nums.get(i) + nums.get(i+1) >= m) return true; 
        return false; 
    }


    /*2812. Find the Safest Path in a Grid (Medium)
    You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) 
    represents:
    * A cell containing a thief if grid[r][c] = 1
    * An empty cell if grid[r][c] = 0
    You are initially positioned at cell (0, 0). In one move, you can move to 
    any adjacent cell in the grid, including cells containing thieves. The 
    safeness factor of a path on the grid is defined as the minimum manhattan 
    distance from any cell in the path to any thief in the grid. Return the 
    maximum safeness factor of all paths leading to cell (n - 1, n - 1). An 
    adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), 
    (r + 1, c) and (r - 1, c) if it exists. The Manhattan distance between two 
    cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes 
    the absolute value of val.

    Example 1:
    Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
    Output: 0
    Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves 
                 in cells (0, 0) and (n - 1, n - 1).
    
    Example 2:
    Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
    Output: 2
    Explanation: The path depicted in the picture above has a safeness factor 
                 of 2 since:
                 - The closest cell of the path to the thief at cell (0, 2) is 
                   cell (0, 0). The distance between them is 
                   | 0 - 0 | + | 0 - 2 | = 2.
                 It can be shown that there are no other paths with a higher 
                 safeness factor.
    
    Example 3:
    Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
    Output: 2
    Explanation: The path depicted in the picture above has a safeness factor 
                 of 2 since:
                 - The closest cell of the path to the thief at cell (0, 3) is 
                   cell (1, 2). The distance between them is 
                   | 0 - 1 | + | 3 - 2 | = 2.
                 - The closest cell of the path to the thief at cell (3, 0) is 
                   cell (3, 2). The distance between them is 
                   | 3 - 3 | + | 0 - 2 | = 2.
                 It can be shown that there are no other paths with a higher 
                 safeness factor.

    Constraints:
    * 1 <= grid.length == n <= 400
    * grid[i].length == n
    * grid[i][j] is either 0 or 1.
    * There is at least one thief in the grid.*/

    private boolean check(int mid, int[][] dist, int n) {
        if (dist[0][0] >= mid) {
            int[] dir = {-1, 0, 1, 0, -1}; 
            boolean[][] seen = new boolean[n][n]; seen[0][0] = true; 
            Stack<int[]> stk = new Stack(); stk.push(new int[]{0, 0}); 
            while (!stk.isEmpty()) {
                var elem = stk.pop(); 
                int i = elem[0], j = elem[1]; 
                if (i == n-1 && j == n-1) return true; 
                for (int k = 0; k < 4; ++k) {
                    int ii = i + dir[k], jj = j + dir[k+1]; 
                    if (0 <= ii && ii < n && 0 <= jj && jj < n && !seen[ii][jj] && dist[ii][jj] >= mid) {
                        seen[ii][jj] = true; 
                        stk.push(new int[]{ii, jj}); 
                    }
                }
            }
        }
        return false; 
    }
    
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size(); 
        int[] dir = {-1, 0, 1, 0, -1}; 
        int[][] dist = new int[n][n]; 
        for (int i = 0; i < n; ++i) Arrays.fill(dist[i], -1); 
        Queue<int[]> q = new LinkedList(); 
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0; 
                    q.add(new int[]{i, j}); 
                }
        for (int v = 1; !q.isEmpty(); ++v) {
            for (int sz = q.size(); sz > 0; --sz) {
                var elem = q.poll(); 
                int i = elem[0], j = elem[1]; 
                for (int k = 0; k < 4; ++k) {
                    int ii = i + dir[k], jj = j + dir[k+1]; 
                    if (0 <= ii && ii < n && 0 <= jj && jj < n && dist[ii][jj] == -1) {
                        dist[ii][jj] = v; 
                        q.add(new int[]{ii, jj}); 
                    }
                }
            }
        }
        int lo = 0, hi = n; 
        while (lo < hi) {
            int mid = lo + (hi - lo + 1)/2; 
            if (check(mid, dist, n)) lo = mid; 
            else hi = mid-1; 
        }
        return lo; 
    }


    /*2813. Maximum Elegance of a K-Length Subsequence (Hard)
    You are given a 0-indexed 2D integer array items of length n and an integer 
    k. items[i] = [profiti, categoryi], where profiti and categoryi denote the 
    profit and category of the ith item respectively. Let's define the elegance 
    of a subsequence of items as total_profit + distinct_categories2, where 
    total_profit is the sum of all profits in the subsequence, and 
    distinct_categories is the number of distinct categories from all the 
    categories in the selected subsequence. Your task is to find the maximum 
    elegance from all subsequences of size k in items. Return an integer 
    denoting the maximum elegance of a subsequence of items with size exactly k.
    Note: A subsequence of an array is a new array generated from the original 
    array by deleting some elements (possibly none) without changing the 
    remaining elements' relative order.

    Example 1:
    Input: items = [[3,2],[5,1],[10,1]], k = 2
    Output: 17
    Explanation: In this example, we have to select a subsequence of size 2. We 
                 can select items[0] = [3,2] and items[2] = [10,1]. The total 
                 profit in this subsequence is 3 + 10 = 13, and the subsequence 
                 contains 2 distinct categories [2,1]. Hence, the elegance is 
                 13 + 22 = 17, and we can show that it is the maximum 
                 achievable elegance. 
    
    Example 2:
    Input: items = [[3,1],[3,1],[2,2],[5,3]], k = 3
    Output: 19
    Explanation: In this example, we have to select a subsequence of size 3. We 
                 can select items[0] = [3,1], items[2] = [2,2], and 
                 items[3] = [5,3]. The total profit in this subsequence is 
                 3 + 2 + 5 = 10, and the subsequence contains 3 distinct 
                 categories [1,2,3]. Hence, the elegance is 10 + 32 = 19, and 
                 we can show that it is the maximum achievable elegance.
    
    Example 3:
    Input: items = [[1,1],[2,1],[3,1]], k = 3
    Output: 7
    Explanation: In this example, we have to select a subsequence of size 3. We 
                 should select all the items. The total profit will be 
                 1 + 2 + 3 = 6, and the subsequence contains 1 distinct 
                 category [1]. Hence, the maximum elegance is 6 + 12 = 7.  

    Constraints:
    * 1 <= items.length == n <= 10^5
    * items[i].length == 2
    * items[i][0] == profiti
    * items[i][1] == categoryi
    * 1 <= profiti <= 10^9
    * 1 <= categoryi <= n
    * 1 <= k <= n*/

    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> Integer.compare(b[0], a[0])); 
        long ans = 0, cand = 0; 
        Set<Integer> seen = new HashSet(); 
        Stack<Integer> stk = new Stack(); 
        for (int i = 0; i < items.length; ++i) {
            int p = items[i][0], c = items[i][1]; 
            if (i <= k-1 || !seen.contains(c) && !stk.isEmpty()) {
                cand += p; 
                if (i >= k) cand -= stk.pop(); 
                if (seen.contains(c)) stk.push(p); 
                seen.add(c); 
            }
            ans = Math.max(ans, cand + (long) Math.pow(seen.size(), 2)); 
        }
        return ans; 
    }


    /*2815. Max Pair Sum in an Array (Easy)
    You are given a 0-indexed integer array nums. You have to find the maximum 
    sum of a pair of numbers from nums such that the maximum digit in both 
    numbers are equal. Return the maximum sum or -1 if no such pair exists.

    Example 1:
    Input: nums = [51,71,17,24,42]
    Output: 88
    Explanation: For i = 1 and j = 2, nums[i] and nums[j] have equal maximum 
                 digits with a pair sum of 71 + 17 = 88. For i = 3 and j = 4, 
                 nums[i] and nums[j] have equal maximum digits with a pair sum 
                 of 24 + 42 = 66. It can be shown that there are no other pairs 
                 with equal maximum digits, so the answer is 88.
    
    Example 2:
    Input: nums = [1,2,3,4]
    Output: -1
    Explanation: No pair exists in nums with equal maximum digits.

    Constraints:
    * 2 <= nums.length <= 100
    * 1 <= nums[i] <= 10^4*/

    public int maxSum(int[] nums) {
        int ans = -1; 
        int[] seen = new int[10]; 
        for (var v : nums) {
            int d = 0; 
            for (int x = v; x > 0; d = Math.max(d, x % 10), x /= 10); 
            if (seen[d] > 0) ans = Math.max(ans, seen[d] + v); 
            seen[d] = Math.max(seen[d], v); 
        }
        return ans; 
    }


    /*2816. Double a Number Represented as a Linked List (Medium)
    You are given the head of a non-empty linked list representing a non-
    negative integer without leading zeroes. Return the head of the linked list 
    after doubling it.

    Example 1:
    Input: head = [1,8,9]
    Output: [3,7,8]
    Explanation: The figure above corresponds to the given linked list which 
                 represents the number 189. Hence, the returned linked list 
                 represents the number 189 * 2 = 378.
    
    Example 2:
    Input: head = [9,9,9]
    Output: [1,9,9,8]
    Explanation: The figure above corresponds to the given linked list which 
                 represents the number 999. Hence, the returned linked list 
                 reprersents the number 999 * 2 = 1998. 

    Constraints:
    * The number of nodes in the list is in the range [1, 10^4]
    * 0 <= Node.val <= 9
    * The input is generated such that the list represents a number that does 
      not have leading zeros, except the number 0 itself.*/

    public ListNode doubleIt(ListNode head) {
        if (head.val >= 5) head = new ListNode(0, head); 
        for (ListNode node = head; node != null; node = node.next) {
            node.val = 2*node.val % 10; 
            if (node.next != null && node.next.val >= 5) ++node.val; 
        }
        return head; 
    }


    /*2817. Minimum Absolute Difference Between Elements With Constraint (Medium)
    You are given a 0-indexed integer array nums and an integer x. Find the 
    minimum absolute difference between two elements in the array that are at 
    least x indices apart. In other words, find two indices i and j such that 
    abs(i - j) >= x and abs(nums[i] - nums[j]) is minimized. Return an integer 
    denoting the minimum absolute difference between two elements that are at 
    least x indices apart.

    Example 1:
    Input: nums = [4,3,2,4], x = 2
    Output: 0
    Explanation: We can select nums[0] = 4 and nums[3] = 4. They are at least 2 
                 indices apart, and their absolute difference is the minimum, 0. 
                 It can be shown that 0 is the optimal answer.
    
    Example 2:
    Input: nums = [5,3,2,10,15], x = 1
    Output: 1
    Explanation: We can select nums[1] = 3 and nums[2] = 2. They are at least 1 
                 index apart, and their absolute difference is the minimum, 1.
                 It can be shown that 1 is the optimal answer.
    
    Example 3:
    Input: nums = [1,2,3,4], x = 3
    Output: 3
    Explanation: We can select nums[0] = 1 and nums[3] = 4. They are at least 3 
                 indices apart, and their absolute difference is the minimum, 3.
                 It can be shown that 3 is the optimal answer.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9
    * 0 <= x < nums.length*/

    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeSet<Integer> vals = new TreeSet(); 
        int ans = Integer.MAX_VALUE; 
        for (int i = 0; i < nums.size(); ++i) {
            if (i >= x) {
                vals.add(nums.get(i-x)); 
                Integer lo = vals.floor(nums.get(i)); 
                if (lo != null) ans = Math.min(ans, nums.get(i) - lo); 
                Integer hi = vals.ceiling(nums.get(i)); 
                if (hi != null) ans = Math.min(ans, hi - nums.get(i)); 
            }
        }
        return ans; 
    }


    /*2818. Apply Operations to Maximize Score (Hard)
    You are given an array nums of n positive integers and an integer k. 
    Initially, you start with a score of 1. You have to maximize your score by 
    applying the following operation at most k times:
    * Choose any non-empty subarray nums[l, ..., r] that you haven't chosen 
      previously.
    * Choose an element x of nums[l, ..., r] with the highest prime score. If 
      multiple such elements exist, choose the one with the smallest index.
    * Multiply your score by x.
    Here, nums[l, ..., r] denotes the subarray of nums starting at index l and 
    ending at the index r, both ends being inclusive. The prime score of an 
    integer x is equal to the number of distinct prime factors of x. For 
    example, the prime score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5. Return 
    the maximum possible score after applying at most k operations. Since the 
    answer may be large, return it modulo 10^9 + 7.

    Example 1:
    Input: nums = [8,3,9,3,8], k = 2
    Output: 81
    Explanation: To get a score of 81, we can apply the following operations:
                 - Choose subarray nums[2, ..., 2]. nums[2] is the only element 
                   in this subarray. Hence, we multiply the score by nums[2]. 
                   The score becomes 1 * 9 = 9.
                 - Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] 
                   have a prime score of 1, but nums[2] has the smaller index. 
                   Hence, we multiply the score by nums[2]. The score becomes 
                   9 * 9 = 81.
                 It can be proven that 81 is the highest score one can obtain.
    
    Example 2:
    Input: nums = [19,12,14,6,10,18], k = 3
    Output: 4788
    Explanation: To get a score of 4788, we can apply the following operations: 
                 - Choose subarray nums[0, ..., 0]. nums[0] is the only element 
                   in this subarray. Hence, we multiply the score by nums[0]. 
                   The score becomes 1 * 19 = 19.
                 - Choose subarray nums[5, ..., 5]. nums[5] is the only element 
                   in this subarray. Hence, we multiply the score by nums[5]. 
                   The score becomes 19 * 18 = 342.
                 - Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] 
                   have a prime score of 2, but nums[2] has the smaller index. 
                   Hence, we multipy the score by nums[2]. The score becomes 
                   342 * 14 = 4788.
                 It can be proven that 4788 is the highest score one can obtain.

    Constraints:
    * 1 <= nums.length == n <= 10^5
    * 1 <= nums[i] <= 10^5
    * 1 <= k <= min(n * (n + 1) / 2, 10^9)*/

    private int powmod(long x, int p, int m) {
        long ans = 1; 
        for (; p > 0; p >>= 1) {
            if ((p & 1) == 1) ans = ans * x % m; 
            x = x * x % m; 
        }
        return (int) ans; 
    }
    
    public int maximumScore(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        int n = nums.size(); 
        int[][] vals = new int[n][2]; 
        for (int i = 0; i < n; ++i) {
            int x = nums.get(i), s = 0; 
            for (int p = 2, sx = (int) Math.sqrt(x); p <= sx; ++p) {
                if (x % p == 0) ++s; 
                for (; x % p == 0; x /= p); 
            }
            if (x > 1) ++s; 
            vals[i] = new int[]{i, s}; 
        }
        Arrays.sort(vals, (a, b) -> (a[1] != b[1] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]))); 
        TreeSet<Integer> indices = new TreeSet(); 
        indices.add(-1); 
        indices.add(nums.size()); 
        Map<Integer, Integer> freq = new HashMap();
        for (var elem : vals) {
            int i = elem[0], lo = indices.floor(i), hi = indices.ceiling(i); 
            int left = i - lo, right = hi - i; 
            freq.merge(nums.get(i), left * right, Integer::sum); 
            indices.add(i); 
        }
        long ans = 1; 
        int prefix = 0; 
        List<int[]> aug = new ArrayList(); 
        for (var elem : freq.entrySet()) {
            int x = elem.getKey(), v = elem.getValue(); 
            aug.add(new int[]{x, v}); 
        } 
        Collections.sort(aug, (a, b) -> Integer.compare(b[0], a[0])); 
        for (var elem : aug) {
            int x = elem[0], v = elem[1]; 
            if (prefix < k) {
                ans = ans * powmod(x, Math.min(k - prefix, v), MOD) % MOD; 
                prefix += v; 
            }
        }
        return (int) ans; 
    }


    /*2833. Furthest Point From Origin (Easy)
    You are given a string moves of length n consisting only of characters 'L', 
    'R', and '_'. The string represents your movement on a number line starting 
    from the origin 0. In the ith move, you can choose one of the following 
    directions:
    * move to the left if moves[i] = 'L' or moves[i] = '_'
    * move to the right if moves[i] = 'R' or moves[i] = '_'
    Return the distance from the origin of the furthest point you can get to 
    after n moves.

    Example 1:
    Input: moves = "L_RL__R"
    Output: 3
    Explanation: The furthest point we can reach from the origin 0 is point -3 
                 through the following sequence of moves "LLRLLLR".
    
    Example 2:
    Input: moves = "_R__LL_"
    Output: 5
    Explanation: The furthest point we can reach from the origin 0 is point -5 
                 through the following sequence of moves "LRLLLLL".
    
    Example 3:
    Input: moves = "_______"
    Output: 7
    Explanation: The furthest point we can reach from the origin 0 is point 7 
                 through the following sequence of moves "RRRRRRR".

    Constraints:
    * 1 <= moves.length == n <= 50
    * moves consists only of characters 'L', 'R' and '_'.*/

    public int furthestDistanceFromOrigin(String moves) {
        int L = 0, R = 0; 
        for (var ch : moves.toCharArray()) 
            if (ch == 'L') ++L; 
            else if (ch == 'R') ++R;
        return moves.length() - L - R + Math.abs(L - R); 
    }


    /*2834. Find the Minimum Possible Sum of a Beautiful Array (Medium)
    You are given positive integers n and target. An array nums is beautiful if 
    it meets the following conditions:
    * nums.length == n.
    * nums consists of pairwise distinct positive integers.
    * There doesn't exist two distinct indices, i and j, in the range 
      [0, n - 1], such that nums[i] + nums[j] == target.
    Return the minimum possible sum that a beautiful array could have.

    Example 1:
    Input: n = 2, target = 3
    Output: 4
    Explanation: We can see that nums = [1,3] is beautiful.
                 - The array nums has length n = 2.
                 - The array nums consists of pairwise distinct positive integers.
                 - There doesn't exist two distinct indices, i and j, with 
                   nums[i] + nums[j] == 3.
                 It can be proven that 4 is the minimum possible sum that a 
                 beautiful array could have.
    
    Example 2:
    Input: n = 3, target = 3
    Output: 8
    Explanation: We can see that nums = [1,3,4] is beautiful.
                 - The array nums has length n = 3.
                 - The array nums consists of pairwise distinct positive integers.
                 - There doesn't exist two distinct indices, i and j, with 
                   nums[i] + nums[j] == 3.
                 It can be proven that 8 is the minimum possible sum that a 
                 beautiful array could have.
    
    Example 3:
    Input: n = 1, target = 1
    Output: 1
    Explanation: We can see, that nums = [1] is beautiful.

    Constraints:
    * 1 <= n <= 10^5
    * 1 <= target <= 10^5*/

    public long minimumPossibleSum(int n, int target) {
        long m = target/2; 
        return n <= m ? (long) n*(n+1)/2 : m*(m+1)/2 + target*(n-m) + (n-m-1)*(n-m)/2; 
    }


    /*2835. Minimum Operations to Form Subsequence With Target Sum (Hard)
    You are given a 0-indexed array nums consisting of non-negative powers of 2, 
    and an integer target. In one operation, you must apply the following 
    changes to the array:
    * Choose any element of the array nums[i] such that nums[i] > 1.
    * Remove nums[i] from the array.
    * Add two occurrences of nums[i] / 2 to the end of nums.
    Return the minimum number of operations you need to perform so that nums 
    contains a subsequence whose elements sum to target. If it is impossible to 
    obtain such a subsequence, return -1. A subsequence is an array that can be 
    derived from another array by deleting some or no elements without changing 
    the order of the remaining elements.

    Example 1:
    Input: nums = [1,2,8], target = 7
    Output: 1
    Explanation: In the first operation, we choose element nums[2]. The array 
                 becomes equal to nums = [1,2,4,4]. At this stage, nums 
                 contains the subsequence [1,2,4] which sums up to 7. It can be 
                 shown that there is no shorter sequence of operations that 
                 results in a subsequnce that sums up to 7.
    
    Example 2:
    Input: nums = [1,32,1,2], target = 12
    Output: 2
    Explanation: In the first operation, we choose element nums[1]. The array 
                 becomes equal to nums = [1,1,2,16,16]. In the second operation, 
                 we choose element nums[3]. The array becomes equal to 
                 nums = [1,1,2,16,8,8]. At this stage, nums contains the 
                 subsequence [1,1,2,8] which sums up to 12. It can be shown 
                 that there is no shorter sequence of operations that results 
                 in a subsequence that sums up to 12.
    
    Example 3:
    Input: nums = [1,32,1], target = 35
    Output: -1
    Explanation: It can be shown that no sequence of operations results in a 
                 subsequence that sums up to 35.

    Constraints:
    * 1 <= nums.length <= 1000
    * 1 <= nums[i] <= 2^30
    * nums consists only of non-negative powers of two.
    * 1 <= target < 2^31*/

    public int minOperations(List<Integer> nums, int target) {
        int ans = 0, ii = -1; 
        Map<Integer, Integer> freq = new HashMap(); 
        for (var x : nums) freq.merge(x, 1, Integer::sum); 
        for (int i = 0, cnt = 0; i <= 30; ++i) {
            cnt /= 2; 
            cnt += freq.getOrDefault(1<<i, 0); 
            if ((target & 1<<i) > 0) 
                if (cnt > 0) --cnt; 
                else if (ii == -1) ii = i; 
            if (cnt > 0 && ii >= 0) {
                ans += i - ii; 
                --cnt; 
                ii = -1; 
            }
        }
        return ii == -1 ? ans : -1; 
    }


    /*2836. Maximize Value of Function in a Ball Passing Game (Hard)
    You are given a 0-indexed integer array receiver of length n and an integer 
    k. There are n players having a unique id in the range [0, n - 1] who will 
    play a ball passing game, and receiver[i] is the id of the player who 
    receives passes from the player with id i. Players can pass to themselves, 
    i.e. receiver[i] may be equal to i. You must choose one of the n players as 
    the starting player for the game, and the ball will be passed exactly k 
    times starting from the chosen player. For a chosen starting player having 
    id x, we define a function f(x) that denotes the sum of x and the ids of 
    all players who receive the ball during the k passes, including repetitions. 
    In other words, 
    f(x) = x + receiver[x] + receiver[receiver[x]] + ... + receiver(k)[x].
    Your task is to choose a starting player having id x that maximizes the 
    value of f(x). Return an integer denoting the maximum value of the function.
    Note: receiver may contain duplicates.

    Example 1:
    Pass Number Sender ID   Receiver ID x + Receiver IDs
                2
    1   2   1   3
    2   1   0   3
    3   0   2   5
    4   2   1   6
    Input: receiver = [2,0,1], k = 4
    Output: 6
    Explanation: The table above shows a simulation of the game starting with 
                 the player having id x = 2. From the table, f(2) is equal to 6. 
                 It can be shown that 6 is the maximum achievable value of the 
                 function. Hence, the output is 6. 
    
    Example 2:
    Pass Number Sender ID   Receiver ID x + Receiver IDs
                4
    1   4   3   7
    2   3   2   9
    3   2   1   10
    Input: receiver = [1,1,1,2,3], k = 3
    Output: 10
    Explanation: The table above shows a simulation of the game starting with 
                 the player having id x = 4. From the table, f(4) is equal to 
                 10. It can be shown that 10 is the maximum achievable value of 
                 the function. Hence, the output is 10. 

    Constraints:
    * 1 <= receiver.length == n <= 10^5
    * 0 <= receiver[i] <= n - 1
    * 1 <= k <= 10^10*/

    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        int n = receiver.size(), m = (int) (Math.log(k)/Math.log(2))+2; 
        int[][] lift = new int[n][m]; 
        for (int x = 0; x < n; ++x) Arrays.fill(lift[x], -1); 
        long[][] score = new long[n][m]; 
        for (int i = 0; i < m; ++i)
            for (int x = 0; x < n; ++x)
                if (i == 0) {
                    lift[x][0] = receiver.get(x); 
                    score[x][0] = x; 
                } else if (lift[x][i-1] != -1) {
                    lift[x][i] = lift[lift[x][i-1]][i-1]; 
                    score[x][i] = score[x][i-1] + score[lift[x][i-1]][i-1]; 
                }
        long ans = 0; 
        for (int v = 0; v < n; ++v) {
            long cand = 0; 
            for (int i = 0, x = v; i < m; ++i) 
                if ((k+1 & 1l<<i) > 0) {
                    cand += score[x][i]; 
                    x = lift[x][i]; 
                }
            ans = Math.max(ans, cand); 
        }
        return ans; 
    }


    /*2839. Check if Strings Can be Made Equal With Operations I (Easy)
    You are given two strings s1 and s2, both of length 4, consisting of 
    lowercase English letters. You can apply the following operation on any of 
    the two strings any number of times:
    * Choose any two indices i and j such that j - i = 2, then swap the two 
      characters at those indices in the string.
    Return true if you can make the strings s1 and s2 equal, and false 
    otherwise.

    Example 1:
    Input: s1 = "abcd", s2 = "cdab"
    Output: true
    Explanation: We can do the following operations on s1:
                 - Choose the indices i = 0, j = 2. The resulting string is 
                   s1 = "cbad".
                 - Choose the indices i = 1, j = 3. The resulting string is 
                   s1 = "cdab" = s2.
    
    Example 2:
    Input: s1 = "abcd", s2 = "dacb"
    Output: false
    Explanation: It is not possible to make the two strings equal.

    Constraints:
    * s1.length == s2.length == 4
    * s1 and s2 consist only of lowercase English letters.*/

    public boolean canBeEqual(String s1, String s2) {
        char[] ch = s1.toCharArray(); 
        if (ch[0] != s2.charAt(0)) { char temp = ch[0]; ch[0] = ch[2]; ch[2] = temp; }
        if (ch[1] != s2.charAt(1)) { char temp = ch[1]; ch[1] = ch[3]; ch[3] = temp; }
        return String.valueOf(ch).equals(s2); 
    }


    /*2840. Check if Strings Can be Made Equal With Operations II (Medium)
    You are given two strings s1 and s2, both of length n, consisting of 
    lowercase English letters. You can apply the following operation on any of 
    the two strings any number of times:
    * Choose any two indices i and j such that i < j and the difference j - i 
      is even, then swap the two characters at those indices in the string.
    Return true if you can make the strings s1 and s2 equal, and false 
    otherwise.

    Example 1:
    Input: s1 = "abcdba", s2 = "cabdab"
    Output: true
    Explanation: We can apply the following operations on s1:
                 - Choose the indices i = 0, j = 2. The resulting string is 
                   s1 = "cbadba".
                 - Choose the indices i = 2, j = 4. The resulting string is 
                   s1 = "cbbdaa".
                 - Choose the indices i = 1, j = 5. The resulting string is 
                   s1 = "cabdab" = s2.
    
    Example 2:
    Input: s1 = "abe", s2 = "bea"
    Output: false
    Explanation: It is not possible to make the two strings equal.

    Constraints:
    * n == s1.length == s2.length
    * 1 <= n <= 10^5
    * s1 and s2 consist only of lowercase English letters.*/

    public boolean checkStrings(String s1, String s2) {
        int[] freq = new int[52]; 
        for (int i = 0; i < s1.length(); ++i) {
            ++freq[s1.charAt(i)-'a' + 26*(i&1)]; 
            --freq[s2.charAt(i)-'a' + 26*(i&1)]; 
        }
        for (var x : freq) 
            if (x > 0) return false; 
        return true; 
    }


    /*2841. Maximum Sum of Almost Unique Subarray (Medium)
    You are given an integer array nums and two positive integers m and k. 
    Return the maximum sum out of all almost unique subarrays of length k of 
    nums. If no such subarray exists, return 0. A subarray of nums is almost 
    unique if it contains at least m distinct elements. A subarray is a 
    contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [2,6,7,3,1,7], m = 3, k = 4
    Output: 18
    Explanation: There are 3 almost unique subarrays of size k = 4. These 
                 subarrays are [2, 6, 7, 3], [6, 7, 3, 1], and [7, 3, 1, 7]. 
                 Among these subarrays, the one with the maximum sum is 
                 [2, 6, 7, 3] which has a sum of 18.
    
    Example 2:
    Input: nums = [5,9,9,2,4,5,4], m = 1, k = 3
    Output: 23
    Explanation: There are 5 almost unique subarrays of size k. These subarrays 
                 are [5, 9, 9], [9, 9, 2], [9, 2, 4], [2, 4, 5], and [4, 5, 4]. 
                 Among these subarrays, the one with the maximum sum is 
                 [5, 9, 9] which has a sum of 23.
    
    Example 3:
    Input: nums = [1,2,1,2,1,2,1], m = 3, k = 3
    Output: 0
    Explanation: There are no subarrays of size k = 3 that contain at least 
                 m = 3 distinct elements in the given array [1,2,1,2,1,2,1]. 
                 Therefore, no almost unique subarrays exist, and the maximum 
                 sum is 0.

    Constraints:
    * 1 <= nums.length <= 2 * 10^4
    * 1 <= m <= k <= nums.length
    * 1 <= nums[i] <= 10^9*/

    public long maxSum(List<Integer> nums, int m, int k) {
        long ans = 0, prefix = 0; 
        Map<Integer, Integer> freq = new HashMap(); 
        for (int i = 0; i < nums.size(); ++i) {
            prefix += nums.get(i); 
            freq.merge(nums.get(i), 1, Integer::sum); 
            if (i >= k) {
                prefix -= nums.get(i-k); 
                freq.merge(nums.get(i-k), -1, Integer::sum); 
                if (freq.get(nums.get(i-k)) == 0) freq.remove(nums.get(i-k)); 
            }
            if (i >= k-1 && freq.size() >= m) ans = Math.max(ans, prefix); 
        }
        return ans; 
    }


    /*2842. Count K-Subsequences of a String With Maximum Beauty (Hard)
    You are given a string s and an integer k. A k-subsequence is a subsequence 
    of s, having length k, and all its characters are unique, i.e., every 
    character occurs once. Let f(c) denote the number of times the character c 
    occurs in s. The beauty of a k-subsequence is the sum of f(c) for every 
    character c in the k-subsequence. For example, consider s = "abbbdd" and 
    k = 2:
    * f('a') = 1, f('b') = 3, f('d') = 2
    * Some k-subsequences of s are:
      + "abbbdd" -> "ab" having a beauty of f('a') + f('b') = 4
      + "abbbdd" -> "ad" having a beauty of f('a') + f('d') = 3
      + "abbbdd" -> "bd" having a beauty of f('b') + f('d') = 5
    Return an integer denoting the number of k-subsequences whose beauty is the 
    maximum among all k-subsequences. Since the answer may be too large, return 
    it modulo 10^9 + 7. A subsequence of a string is a new string formed from 
    the original string by deleting some (possibly none) of the characters 
    without disturbing the relative positions of the remaining characters.

    Notes
    * f(c) is the number of times a character c occurs in s, not a k-subsequence.
    * Two k-subsequences are considered different if one is formed by an index 
      that is not present in the other. So, two k-subsequences may form the 
      same string.

    Example 1:
    Input: s = "bcca", k = 2
    Output: 4
    Explanation: From s we have f('a') = 1, f('b') = 1, and f('c') = 2.
                 The k-subsequences of s are: 
                 bcca having a beauty of f('b') + f('c') = 3 
                 bcca having a beauty of f('b') + f('c') = 3 
                 bcca having a beauty of f('b') + f('a') = 2 
                 bcca having a beauty of f('c') + f('a') = 3
                 bcca having a beauty of f('c') + f('a') = 3 
                 There are 4 k-subsequences that have the maximum beauty, 3. 
                 Hence, the answer is 4. 
    
    Example 2:
    Input: s = "abbcd", k = 4
    Output: 2
    Explanation: From s we have f('a') = 1, f('b') = 2, f('c') = 1, and f('d') = 1. 
                 The k-subsequences of s are: 
                 abbcd having a beauty of f('a') + f('b') + f('c') + f('d') = 5
                 abbcd having a beauty of f('a') + f('b') + f('c') + f('d') = 5 
                 There are 2 k-subsequences that have the maximum beauty, 5. 
                 Hence, the answer is 2. 

    Constraints:
    * 1 <= s.length <= 2 * 10^5
    * 1 <= k <= s.length
    * s consists only of lowercase English letters.*/

    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int[] freq = new int[26]; 
        for (var ch : s.toCharArray()) ++freq[ch-'a']; 
        Arrays.sort(freq); 
        if (k > 26 || freq[26-k] == 0) return 0; 
        long ans = 1, comb = 1; 
        int bep = freq[26-k], mod = 1_000_000_007, n = 0; 
        for (var x : freq) {
            if (x > bep) {
                --k; 
                ans = ans * x % mod; 
            } else if (x == bep) ++n; 
        }
        for (int i = 0; i < k; ++i) {
            comb = comb * (n-i)/(i+1); 
            ans = ans * bep % mod; 
        }
        return (int) (ans * comb % mod); 
    }


    /*2843. Count Symmetric Integers (Easy)
    You are given two positive integers low and high. An integer x consisting 
    of 2 * n digits is symmetric if the sum of the first n digits of x is equal 
    to the sum of the last n digits of x. Numbers with an odd number of digits 
    are never symmetric. Return the number of symmetric integers in the range 
    [low, high].

    Example 1:
    Input: low = 1, high = 100
    Output: 9
    Explanation: There are 9 symmetric integers between 1 and 100: 11, 22, 33, 
                 44, 55, 66, 77, 88, and 99.
    
    Example 2:
    Input: low = 1200, high = 1230
    Output: 4
    Explanation: There are 4 symmetric integers between 1200 and 1230: 1203, 
                 1212, 1221, and 1230.

    Constraints: 1 <= low <= high <= 10^4*/

    public int countSymmetricIntegers(int low, int high) {
        int ans = 0; 
        for (int x = low; x <= high; ++x) {
            String s = String.valueOf(x); 
            if (s.length() % 2 == 0) {
                int bal = 0; 
                for (int i = 0, n = s.length(); i < n; ++i)
                    if (i < n/2) bal += s.charAt(i)-'a';
                    else bal -= s.charAt(i)-'a';
                if (bal == 0) ++ans; 
            }
        }
        return ans; 
    }


    /*2844. Minimum Operations to Make a Special Number (Medium)
    You are given a 0-indexed string num representing a non-negative integer. 
    In one operation, you can pick any digit of num and delete it. Note that if 
    you delete all the digits of num, num becomes 0. Return the minimum number 
    of operations required to make num special. An integer x is considered 
    special if it is divisible by 25.

    Example 1:
    Input: num = "2245047"
    Output: 2
    Explanation: Delete digits num[5] and num[6]. The resulting number is 
                 "22450" which is special since it is divisible by 25. It can 
                 be shown that 2 is the minimum number of operations required 
                 to get a special number.
    
    Example 2:
    Input: num = "2908305"
    Output: 3
    Explanation: Delete digits num[3], num[4], and num[6]. The resulting number 
                 is "2900" which is special since it is divisible by 25. It can 
                 be shown that 3 is the minimum number of operations required 
                 to get a special number.
    
    Example 3:
    Input: num = "10"
    Output: 1
    Explanation: Delete digit num[0]. The resulting number is "0" which is 
                 special since it is divisible by 25. It can be shown that 1 is 
                 the minimum number of operations required to get a special 
                 number.

    Constraints:
    * 1 <= num.length <= 100
    * num only consists of digits '0' through '9'.
    * num does not contain any leading zeros.*/

    public int minimumOperations(String num) {
        int ans = Integer.MAX_VALUE; 
        for (var p : new String[]{"00", "25", "50", "75"}) 
            for (int i = num.length()-1, k = 1, cand = 0; i >= 0; --i) {
                if (p.charAt(k) == num.charAt(i)) --k; 
                else ++cand; 
                if (k == -1) {
                    ans = Math.min(ans, cand); 
                    break; 
                }
            }
        return ans < Integer.MAX_VALUE ? ans : num.contains("0") ? num.length()-1 : num.length(); 
    }


    /*2845. Count of Interesting Subarrays (Medium)
    You are given a 0-indexed integer array nums, an integer modulo, and an 
    integer k. Your task is to find the count of subarrays that are interesting.
    A subarray nums[l..r] is interesting if the following condition holds:
    * Let cnt be the number of indices i in the range [l, r] such that 
      nums[i] % modulo == k. Then, cnt % modulo == k.
    Return an integer denoting the count of interesting subarrays. Note: A 
    subarray is a contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [3,2,4], modulo = 2, k = 1
    Output: 3
    Explanation: In this example the interesting subarrays are: 
                 The subarray nums[0..0] which is [3]. 
                 - There is only one index, i = 0, in the range [0, 0] that 
                   satisfies nums[i] % modulo == k. 
                 - Hence, cnt = 1 and cnt % modulo == k.  
                 The subarray nums[0..1] which is [3,2].
                 - There is only one index, i = 0, in the range [0, 1] that 
                   satisfies nums[i] % modulo == k.  
                 - Hence, cnt = 1 and cnt % modulo == k.
                 The subarray nums[0..2] which is [3,2,4]. 
                 - There is only one index, i = 0, in the range [0, 2] that 
                   satisfies nums[i] % modulo == k. 
                 - Hence, cnt = 1 and cnt % modulo == k. 
                 It can be shown that there are no other interesting subarrays. 
                 So, the answer is 3.
    
    Example 2:
    Input: nums = [3,1,9,6], modulo = 3, k = 0
    Output: 2
    Explanation: In this example the interesting subarrays are: 
                 The subarray nums[0..3] which is [3,1,9,6]. 
                 - There are three indices, i = 0, 2, 3, in the range [0, 3] 
                   that satisfy nums[i] % modulo == k. 
                 - Hence, cnt = 3 and cnt % modulo == k. 
                 The subarray nums[1..1] which is [1]. 
                 - There is no index, i, in the range [1, 1] that satisfies 
                   nums[i] % modulo == k. 
                 - Hence, cnt = 0 and cnt % modulo == k. 
                 It can be shown that there are no other interesting subarrays. 
                 So, the answer is 2.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9
    * 1 <= modulo <= 10^9
    * 0 <= k < modulo*/

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long ans = 0; 
        Map<Integer, Integer> freq = new HashMap(); 
        freq.put(0, 1); 
        int prefix = 0; 
        for (var x : nums) {
            if (x % modulo == k) ++prefix; 
            prefix %= modulo; 
            ans += freq.getOrDefault((prefix-k+modulo) % modulo, 0); 
            freq.merge(prefix, 1, Integer::sum); 
        }
        return ans; 
    }


    /*2846. Minimum Edge Weight Equilibrium Queries in a Tree (Hard)
    There is an undirected tree with n nodes labeled from 0 to n - 1. You are 
    given the integer n and a 2D integer array edges of length n - 1, where 
    edges[i] = [ui, vi, wi] indicates that there is an edge between nodes ui 
    and vi with weight wi in the tree. You are also given a 2D integer array 
    queries of length m, where queries[i] = [ai, bi]. For each query, find the 
    minimum number of operations required to make the weight of every edge on 
    the path from ai to bi equal. In one operation, you can choose any edge of 
    the tree and change its weight to any value.

    Note that:
    * Queries are independent of each other, meaning that the tree returns to 
      its initial state on each new query.
    * The path from ai to bi is a sequence of distinct nodes starting with node 
      ai and ending with node bi such that every two adjacent nodes in the 
      sequence share an edge in the tree.
    Return an array answer of length m where answer[i] is the answer to the ith 
    query.

    Example 1:
    Input: n = 7, edges = [[0,1,1],[1,2,1],[2,3,1],[3,4,2],[4,5,2],[5,6,2]], queries = [[0,3],[3,6],[2,6],[0,6]]
    Output: [0,0,1,3]
    Explanation: In the first query, all the edges in the path from 0 to 3 have 
                 a weight of 1. Hence, the answer is 0. In the second query, 
                 all the edges in the path from 3 to 6 have a weight of 2. 
                 Hence, the answer is 0. In the third query, we change the 
                 weight of edge [2,3] to 2. After this operation, all the edges 
                 in the path from 2 to 6 have a weight of 2. Hence, the answer 
                 is 1. In the fourth query, we change the weights of edges 
                 [0,1], [1,2] and [2,3] to 2. After these operations, all the 
                 edges in the path from 0 to 6 have a weight of 2. Hence, the 
                 answer is 3. For each queries[i], it can be shown that 
                 answer[i] is the minimum number of operations needed to 
                 equalize all the edge weights in the path from ai to bi.
    
    Example 2:
    Input: n = 8, edges = [[1,2,6],[1,3,4],[2,4,6],[2,5,3],[3,6,6],[3,0,8],[7,0,2]], queries = [[4,6],[0,4],[6,5],[7,4]]
    Output: [1,2,2,3]
    Explanation: In the first query, we change the weight of edge [1,3] to 6. 
                 After this operation, all the edges in the path from 4 to 6 
                 have a weight of 6. Hence, the answer is 1. In the second 
                 query, we change the weight of edges [0,3] and [3,1] to 6. 
                 After these operations, all the edges in the path from 0 to 4 
                 have a weight of 6. Hence, the answer is 2. In the third 
                 query, we change the weight of edges [1,3] and [5,2] to 6. 
                 After these operations, all the edges in the path from 6 to 5 
                 have a weight of 6. Hence, the answer is 2. In the fourth 
                 query, we change the weights of edges [0,7], [0,3] and [1,3] 
                 to 6. After these operations, all the edges in the path from 7 
                 to 4 have a weight of 6. Hence, the answer is 3. For each 
                 queries[i], it can be shown that answer[i] is the minimum 
                 number of operations needed to equalize all the edge weights 
                 in the path from ai to bi.

    Constraints:
    * 1 <= n <= 10^4
    * edges.length == n - 1
    * edges[i].length == 3
    * 0 <= ui, vi < n
    * 1 <= wi <= 26
    * The input is generated such that edges represents a valid tree.
    * 1 <= queries.length == m <= 2 * 10^4
    * queries[i].length == 2
    * 0 <= ai, bi < n*/

    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        List<int[]>[] tree = new ArrayList[n]; 
        for (int i = 0; i < n; ++i) tree[i] = new ArrayList(); 
        for (var edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2]; 
            tree[u].add(new int[]{v, w}); 
            tree[v].add(new int[]{u, w}); 
        }
        int m = (int) (Math.log(n)/Math.log(2)) + 1; 
        int[][] lift = new int[n][m], freq = new int[n][27]; 
        for (int i = 0; i < n; ++i) Arrays.fill(lift[i], -1); 
        int[] depth = new int[n]; 
        Arrays.fill(depth, -1); 
        Stack<int[]> stk = new Stack(); stk.add(new int[]{0, -1, 0}); 
        while (!stk.isEmpty()) {
            var elem = stk.pop(); 
            int u = elem[0], p = elem[1], d = elem[2]; 
            depth[u] = d; 
            for (var vw : tree[u]) {
                int v = vw[0], w = vw[1]; 
                if (v != p) {
                    lift[v][0] = u; 
                    for (int x = 1; x < 27; ++x) freq[v][x] = freq[u][x]; 
                    ++freq[v][w]; 
                    for (int j = 1; j < m; ++j) {
                        if (lift[v][j-1] == -1) break; 
                        lift[v][j] = lift[lift[v][j-1]][j-1]; 
                    }
                    stk.add(new int[]{v, u, d+1}); 
                }
            }
        }
        List<Integer> ans = new ArrayList(); 
        for (var q : queries) {
            int u = q[0], v = q[1], uu = u, vv = v, k = 0, sm = 0, mx = 0; 
            if (depth[u] > depth[v]) {
                int temp = u; u = v; v = temp; 
            }
            for (int i = 0; i < m; ++i) 
                if ((depth[v]-depth[u] & 1<<i) > 0) v = lift[v][i]; 
            if (u == v) k = u; 
            else {
                for (int i = m-1; i >= 0; --i)
                    if (lift[u][i] != lift[v][i]) {
                        u = lift[u][i]; 
                        v = lift[v][i]; 
                    }
                k = lift[u][0]; 
            }
            for (int w = 1; w < 27; ++w) {
                int val = freq[uu][w] + freq[vv][w] - 2*freq[k][w]; 
                sm += val; 
                mx = Math.max(mx, val); 
            }
            ans.add(sm-mx); 
        }
        return ans.stream().mapToInt(i->i).toArray(); 
    }


    /*2859. Sum of Values at Indices With K Set Bits (Easy)
    You are given a 0-indexed integer array nums and an integer k. Return an 
    integer that denotes the sum of elements in nums whose corresponding 
    indices have exactly k set bits in their binary representation. The set 
    bits in an integer are the 1's present when it is written in binary. For 
    example, the binary representation of 21 is 10101, which has 3 set bits.

    Example 1:
    Input: nums = [5,10,1,5,2], k = 1
    Output: 13
    Explanation: The binary representation of the indices are: 
                 0 = 0002
                 1 = 0012
                 2 = 0102
                 3 = 0112
                 4 = 1002 
                 Indices 1, 2, and 4 have k = 1 set bits in their binary 
                 representation. Hence, the answer is 
                 nums[1] + nums[2] + nums[4] = 13.
    
    Example 2:
    Input: nums = [4,3,2,1], k = 2
    Output: 1
    Explanation: The binary representation of the indices are:
                 0 = 002
                 1 = 012
                 2 = 102
                 3 = 112
                 Only index 3 has k = 2 set bits in its binary representation.
                 Hence, the answer is nums[3] = 1.

    Constraints:
    * 1 <= nums.length <= 1000
    * 1 <= nums[i] <= 10^5
    * 0 <= k <= 10*/

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int ans = 0; 
        for (int i = 0; i < nums.size(); ++i) 
            if (Integer.bitCount(i) == k) ans += nums.get(i); 
        return ans; 
    }


    /*2860. Happy Students (Medium)
    You are given a 0-indexed integer array nums of length n where n is the 
    total number of students in the class. The class teacher tries to select a 
    group of students so that all the students remain happy. The ith student 
    will become happy if one of these two conditions is met:
    * The student is selected and the total number of selected students is 
      strictly greater than nums[i].
    * The student is not selected and the total number of selected students is 
      strictly less than nums[i].
    Return the number of ways to select a group of students so that everyone 
    remains happy.

    Example 1:
    Input: nums = [1,1]
    Output: 2
    Explanation: The two possible ways are:
                 1. The class teacher selects no student.
                 2. The class teacher selects both students to form the group. 
                 If the class teacher selects just one student to form a group 
                 then the both students will not be happy. Therefore, there are 
                 only two possible ways.
    
    Example 2:
    Input: nums = [6,0,3,3,6,7,2,7]
    Output: 3
    Explanation: The three possible ways are:
                 1. The class teacher selects the student with index = 1 to 
                    form the group.
                 2. The class teacher selects the students with 
                    index = 1, 2, 3, 6 to form the group.
                 The class teacher selects all the students to form the group.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] < nums.length*/

    public int countWays(List<Integer> nums) {
        int ans = 0;
        Collections.sort(nums); 
        for (int i = 0, n = nums.size(); i < n; ++i) {
            if (i == 0 && nums.get(i) > 0) ++ans; 
            if (nums.get(i) < i+1 && (i+1 == n || i+1 < nums.get(i+1))) ++ans; 
        }
        return ans; 
    }


    /*2861. Maximum Number of Alloys (Medium)
    You are the owner of a company that creates alloys using various types of 
    metals. There are n different types of metals available, and you have 
    access to k machines that can be used to create alloys. Each machine 
    requires a specific amount of each metal type to create an alloy. For the 
    ith machine to create an alloy, it needs composition[i][j] units of metal 
    of type j. Initially, you have stock[i] units of metal type i, and 
    purchasing one unit of metal type i costs cost[i] coins. Given integers 
    n, k, budget, a 1-indexed 2D array composition, and 1-indexed arrays stock 
    and cost, your goal is to maximize the number of alloys the company can 
    create while staying within the budget of budget coins. All alloys must be 
    created with the same machine. Return the maximum number of alloys that the 
    company can create.

    Example 1:
    Input: n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,0], cost = [1,2,3]
    Output: 2
    Explanation: It is optimal to use the 1st machine to create alloys.
                 To create 2 alloys we need to buy the:
                 - 2 units of metal of the 1st type.
                 - 2 units of metal of the 2nd type.
                 - 2 units of metal of the 3rd type.
                 In total, we need 2 * 1 + 2 * 2 + 2 * 3 = 12 coins, which is 
                 smaller than or equal to budget = 15. Notice that we have 0 
                 units of metal of each type and we have to buy all the 
                 required units of metal. It can be proven that we can create 
                 at most 2 alloys.
    
    Example 2:
    Input: n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,100], cost = [1,2,3]
    Output: 5
    Explanation: It is optimal to use the 2nd machine to create alloys.
                 To create 5 alloys we need to buy:
                 - 5 units of metal of the 1st type.
                 - 5 units of metal of the 2nd type.
                 - 0 units of metal of the 3rd type.
                 In total, we need 5 * 1 + 5 * 2 + 0 * 3 = 15 coins, which is 
                 smaller than or equal to budget = 15. It can be proven that we 
                 can create at most 5 alloys.
    
    Example 3:
    Input: n = 2, k = 3, budget = 10, composition = [[2,1],[1,2],[1,1]], stock = [1,1], cost = [5,5]
    Output: 2
    Explanation: It is optimal to use the 3rd machine to create alloys.
                 To create 2 alloys we need to buy the:
                 - 1 unit of metal of the 1st type.
                 - 1 unit of metal of the 2nd type.
                 In total, we need 1 * 5 + 1 * 5 = 10 coins, which is smaller 
                 than or equal to budget = 10. It can be proven that we can 
                 create at most 2 alloys.

    Constraints:
    * 1 <= n, k <= 100
    * 0 <= budget <= 10^8
    * composition.length == k
    * composition[i].length == n
    * 1 <= composition[i][j] <= 100
    * stock.length == cost.length == n
    * 0 <= stock[i] <= 10^8
    * 1 <= cost[i] <= 100*/

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int ans = 0; 
        for (var comp : composition) {
            long lo = 0, hi = (long) 2e8; 
            while (lo < hi) {
                long mid = lo + (hi-lo+1)/2, spend = 0; 
                for (int i = 0; i < n; ++i)
                    spend += Math.max(0l, cost.get(i)*(mid*comp.get(i) - stock.get(i))); 
                if (spend <= budget) lo = mid; 
                else hi = mid-1; 
            }
            ans = Math.max(ans, (int) lo);
        }
        return ans; 
    }


    /*2862. Maximum Element-Sum of a Complete Subset of Indices (Hard)
    You are given a 1-indexed array nums of n integers. A set of numbers is 
    complete if the product of every pair of its elements is a perfect square.
    For a subset of the indices set {1, 2, ..., n} represented as 
    {i1, i2, ..., ik}, we define its element-sum as: 
    nums[i1] + nums[i2] + ... + nums[ik]. Return the maximum element-sum of a 
    complete subset of the indices set {1, 2, ..., n}. A perfect square is a 
    number that can be expressed as the product of an integer by itself.

    Example 1:
    Input: nums = [8,7,3,5,7,2,4,9]
    Output: 16
    Explanation: Apart from the subsets consisting of a single index, there are 
                 two other complete subsets of indices: {1,4} and {2,8}. 
                 - The sum of the elements corresponding to indices 1 and 4 is 
                   equal to nums[1] + nums[4] = 8 + 5 = 13. 
                 - The sum of the elements corresponding to indices 2 and 8 is 
                   equal to nums[2] + nums[8] = 7 + 9 = 16. 
                 Hence, the maximum element-sum of a complete subset of indices 
                 is 16.
    
    Example 2:
    Input: nums = [5,10,3,10,1,13,7,9,4]
    Output: 19
    Explanation: Apart from the subsets consisting of a single index, there are 
                 four other complete subsets of indices: 
                 {1,4}, {1,9}, {2,8}, {4,9}, and {1,4,9}. 
                 - The sum of the elements corresponding to indices 1 and 4 is 
                   equal to nums[1] + nums[4] = 5 + 10 = 15.
                 - The sum of the elements corresponding to indices 1 and 9 is 
                   equal to nums[1] + nums[9] = 5 + 4 = 9.
                 - The sum of the elements corresponding to indices 2 and 8 is 
                   equal to nums[2] + nums[8] = 10 + 9 = 19.
                 - The sum of the elements corresponding to indices 4 and 9 is 
                   equal to nums[4] + nums[9] = 10 + 4 = 14.
                 - The sum of the elements corresponding to indices 1, 4, and 9 
                   is equal to nums[1] + nums[4] + nums[9] = 5 + 10 + 4 = 19.
                 Hence, the maximum element-sum of a complete subset of indices 
                 is 19.

    Constraints:
    * 1 <= n == nums.length <= 10^4
    * 1 <= nums[i] <= 10^9*/

    public long maximumSum(List<Integer> nums) {
        long ans = 0; 
        Map<Integer, Long> mp = new HashMap(); 
        for (int i = 0; i < nums.size(); ++i) {
            int k = i+1; 
            for (int v = 2; v*v <= k; ++v) 
                while (k % (v*v) == 0) k /= v*v; 
            mp.merge(k, (long) nums.get(i), Long::sum); 
            ans = Math.max(ans, mp.get(k)); 
        }
        return ans; 
    }


    /*2864. Maximum Odd Binary Number (Easy)
    You are given a binary string s that contains at least one '1'. You have to 
    rearrange the bits in such a way that the resulting binary number is the 
    maximum odd binary number that can be created from this combination. Return 
    a string representing the maximum odd binary number that can be created 
    from the given combination. Note that the resulting string can have leading 
    zeros.
    
    Example 1:    
    Input: s = "010"
    Output: "001"
    Explanation: Because there is just one '1', it must be in the last position. 
                 So the answer is "001".
    
    Example 2:
    Input: s = "0101"
    Output: "1001"
    Explanation: One of the '1's must be in the last position. The maximum 
                 number that can be made with the remaining digits is "100". So 
                 the answer is "1001".
    
    Constraints:
    * 1 <= s.length <= 100
    * s consists only of '0' and '1'.
    * s contains at least one '1'.*/

    public String maximumOddBinaryNumber(String s) {
        int ones = (int) s.chars().filter(ch -> ch == '1').count(); 
        return "1".repeat(ones-1) + "0".repeat(s.length()-ones) + "1"; 
    }


    /*2865. Beautiful Towers I (Medium)
    You are given a 0-indexed array maxHeights of n integers. You are tasked 
    with building n towers in the coordinate line. The ith tower is built at 
    coordinate i and has a height of heights[i]. A configuration of towers is 
    beautiful if the following conditions hold:
    * 1 <= heights[i] <= maxHeights[i]
    * heights is a mountain array.
    Array heights is a mountain if there exists an index i such that:
    * For all 0 < j <= i, heights[j - 1] <= heights[j]
    * For all i <= k < n - 1, heights[k + 1] <= heights[k]
    Return the maximum possible sum of heights of a beautiful configuration of 
    towers.
    
    Example 1:
    Input: maxHeights = [5,3,4,1,1]
    Output: 13
    Explanation: One beautiful configuration with a maximum sum is 
                 heights = [5,3,3,1,1]. This configuration is beautiful since:
                 - 1 <= heights[i] <= maxHeights[i]  
                 - heights is a mountain of peak i = 0.
                 It can be shown that there exists no other beautiful 
                 configuration with a sum of heights greater than 13.
    
    Example 2:
    Input: maxHeights = [6,5,3,9,2,7]
    Output: 22
    Explanation: One beautiful configuration with a maximum sum is 
                 heights = [3,3,3,9,2,2]. This configuration is beautiful since:
                 - 1 <= heights[i] <= maxHeights[i]
                 - heights is a mountain of peak i = 3.
                 It can be shown that there exists no other beautiful 
                 configuration with a sum of heights greater than 22.
    
    Example 3:
    Input: maxHeights = [3,2,5,5,2,3]
    Output: 18
    Explanation: One beautiful configuration with a maximum sum is 
                 heights = [2,2,5,5,2,2]. This configuration is beautiful since:
                 - 1 <= heights[i] <= maxHeights[i]
                 - heights is a mountain of peak i = 2. 
                 Note that, for this configuration, i = 3 can also be 
                 considered a peak. It can be shown that there exists no other 
                 beautiful configuration with a sum of heights greater than 18.
    
    Constraints:
    * 1 <= n == maxHeights <= 10^3
    * 1 <= maxHeights[i] <= 10^9*/

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size(); 
        long[] prefix = new long[n]; 
        Stack<Integer> stk = new Stack(); stk.push(-1); 
        long val = 0; 
        for (int i = 0; i < n; ++i) {
            while (stk.size() > 1 && maxHeights.get(stk.peek()) >= maxHeights.get(i)) {
                var ii = stk.pop(); 
                val -= (long) (ii - stk.peek())*maxHeights.get(ii); 
            }
            val += (long) (i - stk.peek())*maxHeights.get(i); 
            prefix[i] = val; 
            stk.add(i); 
        }
        long ans = 0; val = 0; 
        stk.clear(); stk.push(n); 
        for (int i = n-1; i >= 0; --i) {
            while (stk.size() > 1 && maxHeights.get(stk.peek()) >= maxHeights.get(i)) {
                var ii = stk.pop(); 
                val -= (long) (stk.peek() - ii)*maxHeights.get(ii); 
            }
            val += (long) (stk.peek() - i)*maxHeights.get(i); 
            stk.push(i); 
            ans = Math.max(ans, prefix[i] + val - maxHeights.get(i)); 
        }
        return ans; 
    }


    /*2866. Beautiful Towers II (Medium)
    You are given a 0-indexed array maxHeights of n integers. You are tasked 
    with building n towers in the coordinate line. The ith tower is built at 
    coordinate i and has a height of heights[i]. A configuration of towers is 
    beautiful if the following conditions hold:
    * 1 <= heights[i] <= maxHeights[i]
    * heights is a mountain array.
    Array heights is a mountain if there exists an index i such that:
    * For all 0 < j <= i, heights[j - 1] <= heights[j]
    * For all i <= k < n - 1, heights[k + 1] <= heights[k]
    Return the maximum possible sum of heights of a beautiful configuration of 
    towers.

    Example 1:
    Input: maxHeights = [5,3,4,1,1]
    Output: 13
    Explanation: One beautiful configuration with a maximum sum is 
                 heights = [5,3,3,1,1]. This configuration is beautiful since:
                 - 1 <= heights[i] <= maxHeights[i]  
                 - heights is a mountain of peak i = 0.
                 It can be shown that there exists no other beautiful 
                 configuration with a sum of heights greater than 13.
    
    Example 2:
    Input: maxHeights = [6,5,3,9,2,7]
    Output: 22
    Explanation: One beautiful configuration with a maximum sum is 
                 heights = [3,3,3,9,2,2]. This configuration is beautiful since:
                 - 1 <= heights[i] <= maxHeights[i]
                 - heights is a mountain of peak i = 3.
                 It can be shown that there exists no other beautiful 
                 configuration with a sum of heights greater than 22.
    
    Example 3:
    Input: maxHeights = [3,2,5,5,2,3]
    Output: 18
    Explanation: One beautiful configuration with a maximum sum is 
                 heights = [2,2,5,5,2,2]. This configuration is beautiful since:
                 - 1 <= heights[i] <= maxHeights[i]
                 - heights is a mountain of peak i = 2. 
                 Note that, for this configuration, i = 3 can also be 
                 considered a peak. It can be shown that there exists no other 
                 beautiful configuration with a sum of heights greater than 18.

    Constraints:
    * 1 <= n == maxHeights <= 10^5
    * 1 <= maxHeights[i] <= 10^9*/

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size(); 
        long[] prefix = new long[n]; 
        Stack<Integer> stk = new Stack(); stk.push(-1); 
        long val = 0; 
        for (int i = 0; i < n; ++i) {
            while (stk.size() > 1 && maxHeights.get(stk.peek()) >= maxHeights.get(i)) {
                var ii = stk.pop(); 
                val -= (long) (ii - stk.peek())*maxHeights.get(ii); 
            }
            val += (long) (i - stk.peek())*maxHeights.get(i); 
            prefix[i] = val; 
            stk.add(i); 
        }
        long ans = 0; val = 0; 
        stk.clear(); stk.push(n); 
        for (int i = n-1; i >= 0; --i) {
            while (stk.size() > 1 && maxHeights.get(stk.peek()) >= maxHeights.get(i)) {
                var ii = stk.pop(); 
                val -= (long) (stk.peek() - ii)*maxHeights.get(ii); 
            }
            val += (long) (stk.peek() - i)*maxHeights.get(i); 
            stk.push(i); 
            ans = Math.max(ans, prefix[i] + val - maxHeights.get(i)); 
        }
        return ans; 
    }


    /*2867. Count Valid Paths in a Tree (Hard) 
    There is an undirected tree with n nodes labeled from 1 to n. You are given 
    the integer n and a 2D integer array edges of length n - 1, where 
    edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi 
    in the tree. Return the number of valid paths in the tree. A path (a, b) is 
    valid if there exists exactly one prime number among the node labels in the 
    path from a to b.

    Note that:
    * The path (a, b) is a sequence of distinct nodes starting with node a and 
      ending with node b such that every two adjacent nodes in the sequence 
      share an edge in the tree.
    * Path (a, b) and path (b, a) are considered the same and counted only once.

    Example 1:
    Input: n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
    Output: 4
    Explanation: The pairs with exactly one prime number on the path between 
                 them are: 
                 - (1, 2) since the path from 1 to 2 contains prime number 2. 
                 - (1, 3) since the path from 1 to 3 contains prime number 3.
                 - (1, 4) since the path from 1 to 4 contains prime number 2.
                 - (2, 4) since the path from 2 to 4 contains prime number 2.
                 It can be shown that there are only 4 valid paths.
    
    Example 2:
    Input: n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
    Output: 6
    Explanation: The pairs with exactly one prime number on the path between 
                 them are: 
                 - (1, 2) since the path from 1 to 2 contains prime number 2.
                 - (1, 3) since the path from 1 to 3 contains prime number 3.
                 - (1, 4) since the path from 1 to 4 contains prime number 2.
                 - (1, 6) since the path from 1 to 6 contains prime number 3.
                 - (2, 4) since the path from 2 to 4 contains prime number 2.
                 - (3, 6) since the path from 3 to 6 contains prime number 3.
                 It can be shown that there are only 6 valid paths.

    Constraints:
    * 1 <= n <= 10^5
    * edges.length == n - 1
    * edges[i].length == 2
    * 1 <= ui, vi <= n
    * The input is generated such that edges represent a valid tree.*/

    public long countPaths(int n, int[][] edges) {
        boolean[] prime = new boolean[n+1]; 
        Arrays.fill(prime, true); 
        prime[0] = prime[1] = false; 
        for (int x = 2; x <= Math.sqrt(n); ++x) 
            if (prime[x])
                for (int xx = x*x; xx <= n; xx += x)
                    prime[xx] = false; 
        List<Integer>[] tree = new ArrayList[n+1]; 
        for (int i = 0; i <= n; ++i) 
            tree[i] = new ArrayList(); 
        for (var e : edges) {
            tree[e[0]].add(e[1]); 
            tree[e[1]].add(e[0]); 
        }
        int[] mp = new int[n+1];
        for (int x = 1; x <= n; ++x) mp[x] = x; 
        for (int x = 1; x <= n; ++x) 
            if (!prime[x] && mp[x] == x) {
                Stack<int[]> stk = new Stack(); stk.push(new int[]{x, -1}); 
                while (!stk.isEmpty()) {
                    var elem = stk.pop(); 
                    int u = elem[0], p = elem[1]; 
                    for (var v : tree[u]) 
                        if (v != p && !prime[v]) {
                            mp[v] = x; 
                            stk.push(new int[]{v, u}); 
                        }
                }
            }
        Map<Integer, Integer> freq = new HashMap(); 
        for (var x : mp) freq.merge(x, 1, Integer::sum); 
        long ans = 0; 
        for (int u = 2; u <= n; ++u) 
            if (prime[u]) {
                long cand = 0, prefix = 1; 
                for (var v : tree[u]) 
                    if (!prime[v]) {
                        cand += prefix * freq.get(mp[v]); 
                        prefix += freq.get(mp[v]); 
                    }
                ans += cand; 
            }
        return ans; 
    }


    /*2869. Minimum Operations to Collect Elements (Easy)
    You are given an array nums of positive integers and an integer k. In one 
    operation, you can remove the last element of the array and add it to your 
    collection. Return the minimum number of operations needed to collect 
    elements 1, 2, ..., k.

    Example 1:
    Input: nums = [3,1,5,4,2], k = 2
    Output: 4
    Explanation: After 4 operations, we collect elements 2, 4, 5, and 1, in 
                 this order. Our collection contains elements 1 and 2. Hence, 
                 the answer is 4.
    
    Example 2:
    Input: nums = [3,1,5,4,2], k = 5
    Output: 5
    Explanation: After 5 operations, we collect elements 2, 4, 5, 1, and 3, in 
                 this order. Our collection contains elements 1 through 5. 
                 Hence, the answer is 5.
    
    Example 3:
    Input: nums = [3,2,5,3,1], k = 3
    Output: 4
    Explanation: After 4 operations, we collect elements 1, 3, 5, and 2, in 
                 this order. Our collection contains elements 1 through 3. 
                 Hence, the answer is 4.

    Constraints:
    * 1 <= nums.length <= 50
    * 1 <= nums[i] <= nums.length
    * 1 <= k <= nums.length
    * The input is generated such that you can collect elements 1, 2, ..., k.*/

    public int minOperations(List<Integer> nums, int k) {
        Long left = (1l<<k) - 1; 
        for (int n = nums.size(), i = n-1; i >= 0; --i) {
            if (nums.get(i) <= k && (left & 1l<<nums.get(i)-1) > 0) left ^= 1l<<nums.get(i)-1; 
            if (left == 0) return n-i; 
        }
        return -1; 
    }


    /*2870. Minimum Number of Operations to Make Array Empty (Medium)
    You are given a 0-indexed array nums consisting of positive integers. There 
    are two types of operations that you can apply on the array any number of 
    times:
    * Choose two elements with equal values and delete them from the array.
    * Choose three elements with equal values and delete them from the array.
    Return the minimum number of operations required to make the array empty, 
    or -1 if it is not possible.

    Example 1:
    Input: nums = [2,3,3,2,2,4,2,3,4]
    Output: 4
    Explanation: We can apply the following operations to make the array empty:
                 - Apply the first operation on the elements at indices 0 and 3. 
                   The resulting array is nums = [3,3,2,4,2,3,4].
                 - Apply the first operation on the elements at indices 2 and 4. 
                   The resulting array is nums = [3,3,4,3,4].
                 - Apply the second operation on the elements at indices 0, 1, 
                   and 3. The resulting array is nums = [4,4].
                 - Apply the first operation on the elements at indices 0 and 1. 
                   The resulting array is nums = [].
                 It can be shown that we cannot make the array empty in less 
                 than 4 operations.
    
    Example 2:
    Input: nums = [2,1,2,2,3,3]
    Output: -1
    Explanation: It is impossible to empty the array.

    Constraints:
    * 2 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^6*/

    public int minOperations(int[] nums) {
        Map<Integer, Integer> freq = new HashMap(); 
        for (var x : nums) freq.merge(x, 1, Integer::sum); 
        int ans = 0; 
        for (var v : freq.values()) {
            if (v == 1) return -1; 
            ans += (v + 2)/3; 
        }
        return ans; 
    }


    /*2871. Split Array Into Maximum Number of Subarrays (Medium)
    You are given an array nums consisting of non-negative integers. We define 
    the score of subarray nums[l..r] such that l <= r as 
    nums[l] AND nums[l + 1] AND ... AND nums[r] where AND is the bitwise AND 
    operation. Consider splitting the array into one or more subarrays such 
    that the following conditions are satisfied:
    * Each element of the array belongs to exactly one subarray.
    * The sum of scores of the subarrays is the minimum possible.
    Return the maximum number of subarrays in a split that satisfies the 
    conditions above. A subarray is a contiguous part of an array.

    Example 1:
    Input: nums = [1,0,2,0,1,2]
    Output: 3
    Explanation: We can split the array into the following subarrays:
                 - [1,0]. The score of this subarray is 1 AND 0 = 0.
                 - [2,0]. The score of this subarray is 2 AND 0 = 0.
                 - [1,2]. The score of this subarray is 1 AND 2 = 0.
                 The sum of scores is 0 + 0 + 0 = 0, which is the minimum 
                 possible score that we can obtain. It can be shown that we 
                 cannot split the array into more than 3 subarrays with a total 
                 score of 0. So we return 3.
    
    Example 2:
    Input: nums = [5,7,1,3]
    Output: 1
    Explanation: We can split the array into one subarray: [5,7,1,3] with a 
                 score of 1, which is the minimum possible score that we can 
                 obtain. It can be shown that we cannot split the array into 
                 more than 1 subarray with a total score of 1. So we return 1.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 0 <= nums[i] <= 10^6*/

    public int maxSubarrays(int[] nums) {
        int ans = 0, prefix = -1; 
        for (var x : nums) {
            prefix &= x; 
            if (prefix == 0) {
                ++ans; 
                prefix = -1; 
            }
        }
        return Math.max(1, ans); 
    }


    /*2872. Maximum Number of K-Divisible Components (Hard)
    There is an undirected tree with n nodes labeled from 0 to n - 1. You are 
    given the integer n and a 2D integer array edges of length n - 1, where 
    edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi 
    in the tree. You are also given a 0-indexed integer array values of length 
    n, where values[i] is the value associated with the ith node, and an 
    integer k. A valid split of the tree is obtained by removing any set of 
    edges, possibly empty, from the tree such that the resulting components all 
    have values that are divisible by k, where the value of a connected 
    component is the sum of the values of its nodes. Return the maximum number 
    of components in any valid split.

    Example 1:
    Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
    Output: 2
    Explanation: We remove the edge connecting node 1 with 2. The resulting 
                 split is valid because:
                 - The value of the component containing nodes 1 and 3 is 
                   values[1] + values[3] = 12.
                 - The value of the component containing nodes 0, 2, and 4 is 
                   values[0] + values[2] + values[4] = 6.
                 It can be shown that no other valid split has more than 2 
                 connected components.
    
    Example 2:
    Input: n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
    Output: 3
    Explanation: We remove the edge connecting node 0 with 2, and the edge 
                 connecting node 0 with 1. The resulting split is valid because:
                 - The value of the component containing node 0 is values[0] = 3.
                 - The value of the component containing nodes 2, 5, and 6 is 
                   values[2] + values[5] + values[6] = 9.
                 - The value of the component containing nodes 1, 3, and 4 is 
                   values[1] + values[3] + values[4] = 6.
                 It can be shown that no other valid split has more than 3 
                 connected components.

    Constraints:
    * 1 <= n <= 3 * 10^4
    * edges.length == n - 1
    * edges[i].length == 2
    * 0 <= ai, bi < n
    * values.length == n
    * 0 <= values[i] <= 10^9
    * 1 <= k <= 10^9
    * Sum of values is divisible by k.
    * The input is generated such that edges represents a valid tree.*/

    private long fn(int u, int p, List<Integer>[] tree, int[] values, int k) {
        for (var v : tree[u]) 
            if (v != p) values[u] = (int) ((values[u] + fn(v, u, tree, values, k)) % k); 
        return values[u]; 
    }
    
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] tree = new ArrayList[n]; 
        Arrays.setAll(tree, x -> new ArrayList()); 
        for (var e : edges) {
            tree[e[0]].add(e[1]); 
            tree[e[1]].add(e[0]); 
        }
        fn(0, -1, tree, values, k); 
        return (int) IntStream.of(values).filter(x -> x % k == 0).count(); 
    }


    /*2873. Maximum Value of an Ordered Triplet I (Easy)
    You are given a 0-indexed integer array nums. Return the maximum value over 
    all triplets of indices (i, j, k) such that i < j < k. If all such triplets 
    have a negative value, return 0. The value of a triplet of indices (i, j, k) 
    is equal to (nums[i] - nums[j]) * nums[k].

    Example 1:
    Input: nums = [12,6,1,2,7]
    Output: 77
    Explanation: The value of the triplet (0, 2, 4) is 
                 (nums[0] - nums[2]) * nums[4] = 77. It can be shown that there 
                 are no ordered triplets of indices with a value greater than 
                 77. 
    
    Example 2:
    Input: nums = [1,10,3,4,19]
    Output: 133
    Explanation: The value of the triplet (1, 2, 4) is 
                 (nums[1] - nums[2]) * nums[4] = 133. It can be shown that 
                 there are no ordered triplets of indices with a value greater 
                 than 133.
    
    Example 3:
    Input: nums = [1,2,3]
    Output: 0
    Explanation: The only ordered triplet of indices (0, 1, 2) has a negative 
                 value of (nums[0] - nums[1]) * nums[2] = -3. Hence, the answer 
                 would be 0.

    Constraints:
    * 3 <= nums.length <= 100
    * 1 <= nums[i] <= 10^6*/

    public long maximumTripletValue(int[] nums) {
        long ans = 0;
        int diff = 0, prefix = 0; 
        for (var x : nums) {
            ans = Math.max(ans, (long) x * diff); 
            diff = Math.max(diff, prefix - x); 
            prefix = Math.max(prefix, x); 
        }
        return ans; 
    }


    /*2874. Maximum Value of an Ordered Triplet II (Medium)
    You are given a 0-indexed integer array nums. Return the maximum value over 
    all triplets of indices (i, j, k) such that i < j < k. If all such triplets 
    have a negative value, return 0. The value of a triplet of indices (i, j, k) 
    is equal to (nums[i] - nums[j]) * nums[k].

    Example 1:
    Input: nums = [12,6,1,2,7]
    Output: 77
    Explanation: The value of the triplet (0, 2, 4) is 
                 (nums[0] - nums[2]) * nums[4] = 77. It can be shown that there 
                 are no ordered triplets of indices with a value greater than 
                 77. 
    
    Example 2:
    Input: nums = [1,10,3,4,19]
    Output: 133
    Explanation: The value of the triplet (1, 2, 4) is 
                 (nums[1] - nums[2]) * nums[4] = 133. It can be shown that 
                 there are no ordered triplets of indices with a value greater 
                 than 133.
    
    Example 3:
    Input: nums = [1,2,3]
    Output: 0
    Explanation: The only ordered triplet of indices (0, 1, 2) has a negative 
                 value of (nums[0] - nums[1]) * nums[2] = -3. Hence, the answer 
                 would be 0.

    Constraints:
    * 3 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^6*/

    public long maximumTripletValue(int[] nums) {
        long ans = 0;
        int diff = 0, prefix = 0; 
        for (var x : nums) {
            ans = Math.max(ans, (long) x * diff); 
            diff = Math.max(diff, prefix - x); 
            prefix = Math.max(prefix, x); 
        }
        return ans; 
    }


    /*2875. Minimum Size Subarray in Infinite Array (Medium)
    You are given a 0-indexed array nums and an integer target. A 0-indexed 
    array infinite_nums is generated by infinitely appending the elements of 
    nums to itself. Return the length of the shortest subarray of the array 
    infinite_nums with a sum equal to target. If there is no such subarray 
    return -1.

    Example 1:
    Input: nums = [1,2,3], target = 5
    Output: 2
    Explanation: In this example infinite_nums = [1,2,3,1,2,3,1,2,...]. The 
                 subarray in the range [1,2], has the sum equal to target = 5 
                 and length = 2. It can be proven that 2 is the shortest length 
                 of a subarray with sum equal to target = 5.
    
    Example 2:
    Input: nums = [1,1,1,2,3], target = 4
    Output: 2
    Explanation: In this example infinite_nums = [1,1,1,2,3,1,1,1,2,3,1,1,...].
                 The subarray in the range [4,5], has the sum equal to 
                 target = 4 and length = 2. It can be proven that 2 is the 
                 shortest length of a subarray with sum equal to target = 4.
    
    Example 3:
    Input: nums = [2,4,6,8], target = 3
    Output: -1
    Explanation: In this example infinite_nums = [2,4,6,8,2,4,6,8,...]. It can 
                 be proven that there is no subarray with sum equal to 
                 target = 3.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^5
    * 1 <= target <= 10^9*/

    public int minSizeSubarray(int[] nums, int target) {
        long total = 0; 
        for (var x : nums) total += x; 
        int ans = (int) (target / total) * nums.length, cand = Integer.MAX_VALUE; 
        target %= total; 
        for (int i = 0, ii = 0, n = nums.length; i < 2*n; ++i) {
            target -= nums[i % n]; 
            for (; target < 0; ++ii) 
                target += nums[ii % n]; 
            if (target == 0) cand = Math.min(cand, i-ii+1); 
        }
        return cand < Integer.MAX_VALUE ? ans + cand : -1; 
    }


    /*2876. Count Visited Nodes in a Directed Graph (Hard)
    There is a directed graph consisting of n nodes numbered from 0 to n - 1 
    and n directed edges. You are given a 0-indexed array edges where edges[i] 
    indicates that there is an edge from node i to node edges[i]. Consider the 
    following process on the graph:
    * You start from a node x and keep visiting other nodes through edges until 
      you reach a node that you have already visited before on this same 
      process.
    Return an array answer where answer[i] is the number of different nodes 
    that you will visit if you perform the process starting from node i.

    Example 1:
    Input: edges = [1,2,0,0]
    Output: [3,3,3,4]
    Explanation: We perform the process starting from each node in the 
                 following way:
                 - Starting from node 0, we visit the nodes 0 -> 1 -> 2 -> 0. 
                   The number of different nodes we visit is 3.
                 - Starting from node 1, we visit the nodes 1 -> 2 -> 0 -> 1. 
                   The number of different nodes we visit is 3.
                 - Starting from node 2, we visit the nodes 2 -> 0 -> 1 -> 2. 
                   The number of different nodes we visit is 3.
                 - Starting from node 3, we visit the nodes 3 -> 0 -> 1 -> 2 -> 
                   0. The number of different nodes we visit is 4.
    
    Example 2:
    Input: edges = [1,2,3,4,0]
    Output: [5,5,5,5,5]
    Explanation: Starting from any node we can visit every node in the graph in 
                 the process.

    Constraints:
    * n == edges.length
    * 2 <= n <= 10^5
    * 0 <= edges[i] <= n - 1
    * edges[i] != i*/

    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size(); 
        int[] ans = new int[n]; 
        for (int x = 0; x < n; ++x) 
            if (ans[x] == 0) {
                Stack<Integer> stk = new Stack(); 
                int v = 0, k = x; 
                for (; ans[k] == 0; k = edges.get(k)) {
                    ans[k] = --v; 
                    stk.push(k); 
                }
                for (int pp = k; !stk.isEmpty(); ) {
                    var kk = stk.pop(); 
                    if (ans[k] > 0) ans[kk] = ans[pp] + 1; 
                    else ans[kk] = ans[k] - v + 1; 
                    pp = kk; 
                }
            }
        return ans; 
    }


    /*2894. Divisible and Non-divisible Sums Difference (Easy)
    You are given positive integers n and m. Define two integers, num1 and num2, 
    as follows:
    * num1: The sum of all integers in the range [1, n] that are not divisible 
      by m.
    * num2: The sum of all integers in the range [1, n] that are divisible by m.
    Return the integer num1 - num2.

    Example 1:
    Input: n = 10, m = 3
    Output: 19
    Explanation: In the given example:
                 - Integers in the range [1, 10] that are not divisible by 3 
                   are [1,2,4,5,7,8,10], num1 is the sum of those integers = 37.
                 - Integers in the range [1, 10] that are divisible by 3 are 
                   [3,6,9], num2 is the sum of those integers = 18.
                 We return 37 - 18 = 19 as the answer.
    
    Example 2:
    Input: n = 5, m = 6
    Output: 15
    Explanation: In the given example:
                 - Integers in the range [1, 5] that are not divisible by 6 are 
                   [1,2,3,4,5], num1 is the sum of those integers = 15.
                 - Integers in the range [1, 5] that are divisible by 6 are [], 
                   num2 is the sum of those integers = 0.
                 We return 15 - 0 = 15 as the answer.
    
    Example 3:
    Input: n = 5, m = 1
    Output: -15
    Explanation: In the given example:
                 - Integers in the range [1, 5] that are not divisible by 1 are 
                   [], num1 is the sum of those integers = 0.
                 - Integers in the range [1, 5] that are divisible by 1 are 
                   [1,2,3,4,5], num2 is the sum of those integers = 15.
                 We return 0 - 15 = -15 as the answer.

    Constraints: 1 <= n, m <= 1000*/

    public int differenceOfSums(int n, int m) {
        return n*(n+1)/2 - (n/m)*(n/m+1)*m; 
    }


    /*2895. Minimum Processing Time (Medium)
    You have n processors each having 4 cores and n * 4 tasks that need to be 
    executed such that each core should perform only one task. Given a 
    0-indexed integer array processorTime representing the time at which each 
    processor becomes available for the first time and a 0-indexed integer 
    array tasks representing the time it takes to execute each task, return the 
    minimum time when all of the tasks have been executed by the processors.
    Note: Each core executes the task independently of the others.

    Example 1:
    Input: processorTime = [8,10], tasks = [2,2,3,1,8,7,4,5]
    Output: 16
    Explanation: It's optimal to assign the tasks at indexes 4, 5, 6, 7 to the 
                 first processor which becomes available at time = 8, and the 
                 tasks at indexes 0, 1, 2, 3 to the second processor which 
                 becomes available at time = 10. Time taken by the first 
                 processor to finish execution of all tasks = 
                 max(8 + 8, 8 + 7, 8 + 4, 8 + 5) = 16. Time taken by the second 
                 processor to finish execution of all tasks = 
                 max(10 + 2, 10 + 2, 10 + 3, 10 + 1) = 13. Hence, it can be 
                 shown that the minimum time taken to execute all the tasks is 
                 16.
    
    Example 2:
    Input: processorTime = [10,20], tasks = [2,3,1,2,5,8,4,3]
    Output: 23
    Explanation: It's optimal to assign the tasks at indexes 1, 4, 5, 6 to the 
                 first processor which becomes available at time = 10, and the 
                 tasks at indexes 0, 2, 3, 7 to the second processor which 
                 becomes available at time = 20. Time taken by the first 
                 processor to finish execution of all tasks = 
                 max(10 + 3, 10 + 5, 10 + 8, 10 + 4) = 18. Time taken by the 
                 second processor to finish execution of all tasks = 
                 max(20 + 2, 20 + 1, 20 + 2, 20 + 3) = 23. Hence, it can be 
                 shown that the minimum time taken to execute all the tasks is 
                 23.

    Constraints:
    * 1 <= n == processorTime.length <= 25000
    * 1 <= tasks.length <= 10^5
    * 0 <= processorTime[i] <= 10^9
    * 1 <= tasks[i] <= 10^9
    * tasks.length == 4 * n*/

    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        Collections.sort(processorTime); 
        Collections.sort(tasks, (a, b) -> Integer.compare(b, a)); 
        int ans = 0; 
        for (int i = 0; i < tasks.size(); ++i) 
            ans = Math.max(ans, processorTime.get(i/4) + tasks.get(i)); 
        return ans; 
    }


    /*2896. Apply Operations to Make Two Strings Equal (Medium)
    You are given two 0-indexed binary strings s1 and s2, both of length n, and 
    a positive integer x. You can perform any of the following operations on 
    the string s1 any number of times:
    * Choose two indices i and j, and flip both s1[i] and s1[j]. The cost of 
      this operation is x.
    * Choose an index i such that i < n - 1 and flip both s1[i] and s1[i + 1]. 
      The cost of this operation is 1.
    Return the minimum cost needed to make the strings s1 and s2 equal, or 
    return -1 if it is impossible. Note that flipping a character means 
    changing it from 0 to 1 or vice-versa.

    Example 1:
    Input: s1 = "1100011000", s2 = "0101001010", x = 2
    Output: 4
    Explanation: We can do the following operations:
                 - Choose i = 3 and apply the second operation. The resulting 
                   string is s1 = "1101111000".
                 - Choose i = 4 and apply the second operation. The resulting 
                   string is s1 = "1101001000".
                 - Choose i = 0 and j = 8 and apply the first operation. The 
                   resulting string is s1 = "0101001010" = s2.
                 The total cost is 1 + 1 + 2 = 4. It can be shown that it is 
                 the minimum cost possible.
    
    Example 2:
    Input: s1 = "10110", s2 = "00011", x = 4
    Output: -1
    Explanation: It is not possible to make the two strings equal.

    Constraints:
    * n == s1.length == s2.length
    * 1 <= n, x <= 500
    * s1 and s2 consist only of the characters '0' and '1'.*/

    public int minOperations(String s1, String s2, int x) {
        List<Integer> diff = new ArrayList(); 
        for (int i = 0; i < s1.length(); ++i)
            if (s1.charAt(i) != s2.charAt(i)) diff.add(i); 
        int n = diff.size(); 
        if (n%2 == 1) return -1; 
        double f0 = 0, f1 = 0; 
        for (int i = 0; i < n; ++i) {
            double f2 = f1 + x/2.0; 
            if (i > 0) f2 = Math.min(f2, f0 + diff.get(i) - diff.get(i-1)); 
            f0 = f1; 
            f1 = f2; 
        }
        return (int) f1; 
    }


    /*2897. Apply Operations on Array to Maximize Sum of Squares (Hard)
    You are given a 0-indexed integer array nums and a positive integer k. You 
    can do the following operation on the array any number of times:
    * Choose any two distinct indices i and j and simultaneously update the 
      values of nums[i] to (nums[i] AND nums[j]) and nums[j] to 
      (nums[i] OR nums[j]). Here, OR denotes the bitwise OR operation, and AND 
      denotes the bitwise AND operation.
    You have to choose k elements from the final array and calculate the sum of 
    their squares. Return the maximum sum of squares you can achieve. Since the 
    answer can be very large, return it modulo 10^9 + 7.

    Example 1:
    Input: nums = [2,6,5,8], k = 2
    Output: 261
    Explanation: We can do the following operations on the array:
                 - Choose i = 0 and j = 3, then change nums[0] to (2 AND 8) = 0 
                   and nums[3] to (2 OR 8) = 10. The resulting array is 
                   nums = [0,6,5,10].
                 - Choose i = 2 and j = 3, then change nums[2] to (5 AND 10) = 0 
                   and nums[3] to (5 OR 10) = 15. The resulting array is 
                   nums = [0,6,0,15].
                 We can choose the elements 15 and 6 from the final array. The 
                 sum of squares is 152 + 62 = 261. It can be shown that this is 
                 the maximum value we can get.
    
    Example 2:
    Input: nums = [4,5,4,7], k = 3
    Output: 90
    Explanation: We do not need to apply any operations. We can choose the 
                 elements 7, 5, and 4 with a sum of squares: 72 + 52 + 42 = 90.
                 It can be shown that this is the maximum value we can get.

    Constraints:
    * 1 <= k <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9*/

    public int maxSum(List<Integer> nums, int k) {
        int[] freq = new int[32]; 
        for (var x : nums) 
            for (int i = 0; i < 32; ++i)
                if ((x & 1<<i) > 0) ++freq[i]; 
        long ans = 0; 
        while (k-- > 0) {
            long val = 0; 
            for (int i = 31; i >= 0; --i)
                if (freq[i] > 0) {
                    --freq[i]; 
                    val ^= 1<<i; 
                }
            ans = (ans + val*val) % 1_000_000_007; 
        }
        return (int) ans; 
    }


    /*2928. Distribute Candies Among Children I (Easy)
    You are given two positive integers n and limit. Return the total number of 
    ways to distribute n candies among 3 children such that no child gets more 
    than limit candies.

    Example 1:
    Input: n = 5, limit = 2
    Output: 3
    Explanation: There are 3 ways to distribute 5 candies such that no child 
                 gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
    
    Example 2:
    Input: n = 3, limit = 3
    Output: 10
    Explanation: There are 10 ways to distribute 3 candies such that no child 
                 gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), 
                 (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), 
                 (2, 1, 0) and (3, 0, 0).

    Constraints:
    * 1 <= n <= 50
    * 1 <= limit <= 50*/

    public int distributeCandies(int n, int limit) {
        int ans = 0; 
        for (int x = 0; x <= limit; ++x)
            for (int y = 0; y <= limit; ++y)
                if (0 <= n-x-y && n-x-y <= limit) ++ans; 
        return ans; 
    }


    /*2929. Distribute Candies Among Children II (Medium)
    You are given two positive integers n and limit. Return the total number of 
    ways to distribute n candies among 3 children such that no child gets more 
    than limit candies.

    Example 1:
    Input: n = 5, limit = 2
    Output: 3
    Explanation: There are 3 ways to distribute 5 candies such that no child 
                 gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
    Example 2:
    Input: n = 3, limit = 3
    Output: 10
    Explanation: There are 10 ways to distribute 3 candies such that no child 
                 gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), 
                 (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), 
                 (2, 1, 0) and (3, 0, 0).

    Constraints:
    * 1 <= n <= 10^6
    * 1 <= limit <= 10^6*/

    public long distributeCandies(int n, int limit) {
        long ans = 0; 
        for (int x = 0; x <= limit; ++x)
            ans += Math.max(0, Math.min(n-x, 2*limit-n+x)+1); 
        return ans; 
    }


    /*2930. Number of Strings Which Can Be Rearranged to Contain Substring (Medium)
    You are given an integer n. A string s is called good if it contains only 
    lowercase English characters and it is possible to rearrange the characters 
    of s such that the new string contains "leet" as a substring.

    For example:
    * The string "lteer" is good because we can rearrange it to form "leetr" .
    * "letl" is not good because we cannot rearrange it to contain "leet" as a 
      substring.
    Return the total number of good strings of length n. Since the answer may 
    be large, return it modulo 10^9 + 7. A substring is a contiguous sequence 
    of characters within a string.
     
    Example 1:
    Input: n = 4
    Output: 12
    Explanation: The 12 strings which can be rearranged to have "leet" as a 
                 substring are: "eelt", "eetl", "elet", "elte", "etel", "etle", 
                 "leet", "lete", "ltee", "teel", "tele", and "tlee".
    
    Example 2:
    Input: n = 10
    Output: 83943898
    Explanation: The number of strings with length 10 which can be rearranged 
                 to have "leet" as a substring is 526083947580. Hence the 
                 answer is 526083947580 % (10^9 + 7) = 83943898.

    Constraints: 1 <= n <= 10^5*/

    private long pow(long x, int p, int m) {
        long ans = 1; 
        for (; p > 0; p >>= 1) {
            if (p % 2 == 1) ans = ans * x % m; 
            x = x * x % m; 
        }
        return ans; 
    }
    
    public int stringCount(int n) {
        final int mod = 1_000_000_007; 
        return (int) (((pow(26, n, mod) - (75+n)*pow(25, n-1, mod) + (72+2*n)*pow(24, n-1, mod) - (23+n)*pow(23, n-1, mod)) % mod + mod) % mod); 
    }


    /*2931. Maximum Spending After Buying Items (Hard)
    You are given a 0-indexed m * n integer matrix values, representing the 
    values of m * n different items in m different shops. Each shop has n items 
    where the jth item in the ith shop has a value of values[i][j]. 
    Additionally, the items in the ith shop are sorted in non-increasing order 
    of value. That is, values[i][j] >= values[i][j + 1] for all 0 <= j < n - 1.
    On each day, you would like to buy a single item from one of the shops. 
    Specifically, On the dth day you can:
    * Pick any shop i.
    * Buy the rightmost available item j for the price of values[i][j] * d. 
      That is, find the greatest index j such that item j was never bought 
      before, and buy it for the price of values[i][j] * d.
    Note that all items are pairwise different. For example, if you have bought 
    item 0 from shop 1, you can still buy item 0 from any other shop. Return 
    the maximum amount of money that can be spent on buying all m * n products.

    Example 1:
    Input: values = [[8,5,2],[6,4,1],[9,7,3]]
    Output: 285
    Explanation: - On the first day, we buy product 2 from shop 1 for a price 
                   of values[1][2] * 1 = 1.
                 - On the second day, we buy product 2 from shop 0 for a price 
                   of values[0][2] * 2 = 4.
                 - On the third day, we buy product 2 from shop 2 for a price 
                   of values[2][2] * 3 = 9.
                 - On the fourth day, we buy product 1 from shop 1 for a price 
                   of values[1][1] * 4 = 16.
                 - On the fifth day, we buy product 1 from shop 0 for a price 
                   of values[0][1] * 5 = 25.
                 - On the sixth day, we buy product 0 from shop 1 for a price 
                   of values[1][0] * 6 = 36.
                 - On the seventh day, we buy product 1 from shop 2 for a price 
                   of values[2][1] * 7 = 49.
                 - On the eighth day, we buy product 0 from shop 0 for a price 
                   of values[0][0] * 8 = 64.
                 - On the ninth day, we buy product 0 from shop 2 for a price 
                   of values[2][0] * 9 = 81.
                 Hence, our total spending is equal to 285. It can be shown 
                 that 285 is the maximum amount of money that can be spent 
                 buying all m * n products. 
    
    Example 2:
    Input: values = [[10,8,6,4,2],[9,7,5,3,2]]
    Output: 386
    Explanation: - On the first day, we buy product 4 from shop 0 for a price 
                   of values[0][4] * 1 = 2.
                 - On the second day, we buy product 4 from shop 1 for a price 
                   of values[1][4] * 2 = 4.
                 - On the third day, we buy product 3 from shop 1 for a price of values[1][3] * 3 = 9.
                 - On the fourth day, we buy product 3 from shop 0 for a price of values[0][3] * 4 = 16.
                 - On the fifth day, we buy product 2 from shop 1 for a price of values[1][2] * 5 = 25.
                 - On the sixth day, we buy product 2 from shop 0 for a price of values[0][2] * 6 = 36.
                 - On the seventh day, we buy product 1 from shop 1 for a price of values[1][1] * 7 = 49.
                 - On the eighth day, we buy product 1 from shop 0 for a price of values[0][1] * 8 = 64
                 - On the ninth day, we buy product 0 from shop 1 for a price of values[1][0] * 9 = 81.
                 - On the tenth day, we buy product 0 from shop 0 for a price of values[0][0] * 10 = 100.
                 Hence, our total spending is equal to 386. It can be shown 
                 that 386 is the maximum amount of money that can be spent 
                 buying all m * n products.

    Constraints:
    * 1 <= m == values.length <= 10
    * 1 <= n == values[i].length <= 10^4
    * 1 <= values[i][j] <= 10^6
    * values[i] are sorted in non-increasing order.*/

    public long maxSpending(int[][] values) {
        int m = values.length, n = values[0].length; 
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0])); 
        for (int i = 0; i < m; ++i)
            pq.add(new int[]{values[i][n-1], i, n-1}); 
        long ans = 0; 
        for (int k = 0; k < m*n; ++k) {
            var elem = pq.poll(); 
            int v = elem[0], i = elem[1], j = elem[2]; 
            ans += (long) v * (k+1); 
            if (j > 0) pq.add(new int[]{values[i][j-1], i, j-1}); 
        }
        return ans; 
    }
    

    /*2937. Make Three Strings Equal (Easy)
    You are given three strings s1, s2, and s3. You have to perform the 
    following operation on these three strings as many times as you want. In 
    one operation you can choose one of these three strings such that its 
    length is at least 2 and delete the rightmost character of it. Return the 
    minimum number of operations you need to perform to make the three strings 
    equal if there is a way to make them equal, otherwise, return -1.

    Example 1:
    Input: s1 = "abc", s2 = "abb", s3 = "ab"
    Output: 2
    Explanation: Performing operations on s1 and s2 once will lead to three 
                 equal strings. It can be shown that there is no way to make 
                 them equal with less than two operations.
    
    Example 2:
    Input: s1 = "dac", s2 = "bac", s3 = "cac"
    Output: -1
    Explanation: Because the leftmost letters of s1 and s2 are not equal, they 
                 could not be equal after any number of operations. So the 
                 answer is -1.

    Constraints:
    * 1 <= s1.length, s2.length, s3.length <= 100
    * s1, s2 and s3 consist only of lowercase English letters.*/

    public int findMinimumOperations(String s1, String s2, String s3) {
        int i = 0; 
        for (int m = Math.min(s1.length(), Math.min(s2.length(), s3.length())); i < m && s1.charAt(i) == s2.charAt(i) && s2.charAt(i) == s3.charAt(i); ++i); 
        return i > 0 ? s1.length() + s2.length() + s3.length() - 3*i : -1; 
    }


    /*2938. Separate Black and White Balls (Medium)
    There are n balls on a table, each ball has a color black or white. You are 
    given a 0-indexed binary string s of length n, where 1 and 0 represent 
    black and white balls, respectively. In each step, you can choose two 
    adjacent balls and swap them. Return the minimum number of steps to group 
    all the black balls to the right and all the white balls to the left.

    Example 1:
    Input: s = "101"
    Output: 1
    Explanation: We can group all the black balls to the right in the following 
                 way:
                 - Swap s[0] and s[1], s = "011".
                 Initially, 1s are not grouped together, requiring at least 1 
                 step to group them to the right.
    
    Example 2:
    Input: s = "100"
    Output: 2
    Explanation: We can group all the black balls to the right in the following 
                 way:
                 - Swap s[0] and s[1], s = "010".
                 - Swap s[1] and s[2], s = "001".
                 It can be proven that the minimum number of steps needed is 2.
    
    Example 3:
    Input: s = "0111"
    Output: 0
    Explanation: All the black balls are already grouped to the right.

    Constraints:
    * 1 <= n == s.length <= 10^5
    * s[i] is either '0' or '1'.*/

    public long minimumSteps(String s) {
        long ans = 0; 
        int prefix = 0; 
        for (var ch : s.toCharArray()) 
            if (ch == '1') ++prefix; 
            else ans += prefix; 
        return ans; 
    }


    /*2939. Maximum Xor Product (Medium)
    Given three integers a, b, and n, return the maximum value of 
    (a XOR x) * (b XOR x) where 0 <= x < 2n. Since the answer may be too large, 
    return it modulo 10^9 + 7. Note that XOR is the bitwise XOR operation.

    Example 1:
    Input: a = 12, b = 5, n = 4
    Output: 98
    Explanation: For x = 2, (a XOR x) = 14 and (b XOR x) = 7. Hence, 
                 (a XOR x) * (b XOR x) = 98. It can be shown that 98 is the 
                 maximum value of (a XOR x) * (b XOR x) for all 0 <= x < 2n.
    
    Example 2:
    Input: a = 6, b = 7 , n = 5
    Output: 930
    Explanation: For x = 25, (a XOR x) = 31 and (b XOR x) = 30. Hence, 
                 (a XOR x) * (b XOR x) = 930. It can be shown that 930 is the 
                 maximum value of (a XOR x) * (b XOR x) for all 0 <= x < 2n.
    
    Example 3:
    Input: a = 1, b = 6, n = 3
    Output: 12
    Explanation: For x = 5, (a XOR x) = 4 and (b XOR x) = 3. Hence, 
                 (a XOR x) * (b XOR x) = 12. It can be shown that 12 is the 
                 maximum value of (a XOR x) * (b XOR x) for all 0 <= x < 2n.

    Constraints:
    * 0 <= a, b < 2^50
    * 0 <= n <= 50*/

    public int maximumXorProduct(long a, long b, int n) {
        final int mod = 1_000_000_007; 
        for (int i = n-1; i >= 0; --i)
            if ((Math.min(a, b) & 1l<<i) == 0) {
                a ^= 1l<<i; 
                b ^= 1l<<i; 
            }
        return (int) (a % mod * (b % mod) % mod); 
    }


    /*2940. Find Building Where Alice and Bob Can Meet (Hard)
    You are given a 0-indexed array heights of positive integers, where 
    heights[i] represents the height of the ith building. If a person is in 
    building i, they can move to any other building j if and only if i < j and 
    heights[i] < heights[j]. You are also given another array queries where 
    queries[i] = [ai, bi]. On the ith query, Alice is in building ai while Bob 
    is in building bi. Return an array ans where ans[i] is the index of the 
    leftmost building where Alice and Bob can meet on the ith query. If Alice 
    and Bob cannot move to a common building on query i, set ans[i] to -1.

    Example 1:
    Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
    Output: [2,5,-1,5,2]
    Explanation: - In the first query, Alice and Bob can move to building 2 
                   since heights[0] < heights[2] and heights[1] < heights[2]. 
                 - In the second query, Alice and Bob can move to building 5 
                   since heights[0] < heights[5] and heights[3] < heights[5]. 
                 - In the third query, Alice cannot meet Bob since Alice cannot 
                   move to any other building.
                 - In the fourth query, Alice and Bob can move to building 5 
                   since heights[3] < heights[5] and heights[4] < heights[5].
                 - In the fifth query, Alice and Bob are already in the same 
                   building.  
                 For ans[i] != -1, It can be shown that ans[i] is the leftmost 
                 building where Alice and Bob can meet. For ans[i] == -1, It 
                 can be shown that there is no building where Alice and Bob can 
                 meet.
    
    Example 2:
    Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
    Output: [7,6,-1,4,6]
    Explanation: - In the first query, Alice can directly move to Bob's 
                   building since heights[0] < heights[7].
                 - In the second query, Alice and Bob can move to building 6 
                   since heights[3] < heights[6] and heights[5] < heights[6].
                 - In the third query, Alice cannot meet Bob since Bob cannot 
                   move to any other building.
                 - In the fourth query, Alice and Bob can move to building 4 
                   since heights[3] < heights[4] and heights[0] < heights[4].
                 - In the fifth query, Alice can directly move to Bob's 
                   building since heights[1] < heights[6].
                 For ans[i] != -1, It can be shown that ans[i] is the leftmost 
                 building where Alice and Bob can meet. For ans[i] == -1, It 
                 can be shown that there is no building where Alice and Bob can 
                 meet.

    Constraints:
    * 1 <= heights.length <= 5 * 10^4
    * 1 <= heights[i] <= 10^9
    * 1 <= queries.length <= 5 * 10^4
    * queries[i] = [ai, bi]
    * 0 <= ai, bi <= heights.length - 1*/

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int m = heights.length, n = queries.length; 
        List<int[]>[] qs = new ArrayList[m]; 
        int[] ans = new int[n]; 
        Arrays.fill(ans, -1); 
        for (int i = 0; i < m; ++i) qs[i] = new ArrayList<>(); 
        for (int i = 0; i < n; ++i) {
            int a = queries[i][0], b = queries[i][1]; 
            if (a > b) { var temp = a; a = b; b = temp; }
            if (a == b || heights[a] < heights[b]) ans[i] = b; 
            else qs[b].add(new int[]{heights[a], i}); 
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); 
        for (int k = 0; k < m; ++k) {
            while (!pq.isEmpty() && pq.peek()[0] < heights[k]) 
                ans[pq.poll()[1]] = k; 
            for (var elem : qs[k]) pq.add(elem); 
        }
        return ans; 
    }


    /*2946. Matrix Similarity After Cyclic Shifts (Easy)
    You are given a 0-indexed m x n integer matrix mat and an integer k. You 
    have to cyclically right shift odd indexed rows k times and cyclically left 
    shift even indexed rows k times. Return true if the initial and final 
    matrix are exactly the same and false otherwise.

    Example 1:
    Input: mat = [[1,2,1,2],[5,5,5,5],[6,3,6,3]], k = 2
    Output: true
    Explanation: Initially, the matrix looks like the first figure. Second 
                 figure represents the state of the matrix after one right and 
                 left cyclic shifts to even and odd indexed rows. Third figure 
                 is the final state of the matrix after two cyclic shifts which 
                 is similar to the initial matrix. Therefore, return true.
    
    Example 2:
    Input: mat = [[2,2],[2,2]], k = 3
    Output: true
    Explanation: As all the values are equal in the matrix, even after 
                 performing cyclic shifts the matrix will remain the same. 
                 Therefeore, we return true.
    
    Example 3:
    Input: mat = [[1,2]], k = 1
    Output: false
    Explanation: After one cyclic shift, mat = [[2,1]] which is not equal to 
                 the initial matrix. Therefore we return false.

    Constraints:
    * 1 <= mat.length <= 25
    * 1 <= mat[i].length <= 25
    * 1 <= mat[i][j] <= 25
    * 1 <= k <= 50*/

    public boolean areSimilar(int[][] mat, int k) {
        for (var row : mat)
            for (int j = 0, n = row.length; j < n; ++j)
                if (row[j] != row[(j+k)%n]) return false; 
        return true; 
    }


    /*2947. Count Beautiful Substrings I (Medium)
    You are given a string s and a positive integer k. Let vowels and 
    consonants be the number of vowels and consonants in a string. A string is 
    beautiful if:
    * vowels == consonants.
    * (vowels * consonants) % k == 0, in other terms the multiplication of 
      vowels and consonants is divisible by k.
    Return the number of non-empty beautiful substrings in the given string s.
    A substring is a contiguous sequence of characters in a string. Vowel 
    letters in English are 'a', 'e', 'i', 'o', and 'u'. Consonant letters in 
    English are every letter except vowels. 

    Example 1:
    Input: s = "baeyh", k = 2
    Output: 2
    Explanation: There are 2 beautiful substrings in the given string.
                 - Substring "baeyh", vowels = 2 (["a",e"]), 
                   consonants = 2 (["y","h"]). You can see that string "aeyh" 
                   is beautiful as vowels == consonants and 
                   vowels * consonants % k == 0.
                 - Substring "baeyh", vowels = 2 (["a",e"]), 
                   consonants = 2 (["b","y"]). You can see that string "baey" 
                   is beautiful as vowels == consonants and 
                   vowels * consonants % k == 0.
                 It can be shown that there are only 2 beautiful substrings in 
                 the given string.
    
    Example 2:
    Input: s = "abba", k = 1
    Output: 3
    Explanation: There are 3 beautiful substrings in the given string.
                 - Substring "abba", vowels = 1 (["a"]), consonants = 1 (["b"]). 
                 - Substring "abba", vowels = 1 (["a"]), consonants = 1 (["b"]).
                 - Substring "abba", vowels = 2 (["a","a"]), 
                   consonants = 2 (["b","b"]).
                 It can be shown that there are only 3 beautiful substrings in 
                 the given string.
    
    Example 3:
    Input: s = "bcdf", k = 1
    Output: 0
    Explanation: There are no beautiful substrings in the given string.

    Constraints:
    * 1 <= s.length <= 1000
    * 1 <= k <= 1000
    * s consists of only English lowercase letters.*/

    public int beautifulSubstrings(String s, int k) {
        int ans = 0; 
        for (int i = 0, n = s.length(); i < n; ++i) {
            int vowels = 0, consonants = 0; 
            for (int j = i; j < n; ++j) {
                if ("aeiou".indexOf(s.charAt(j)) >= 0) ++vowels; 
                else ++consonants; 
                if (vowels == consonants && vowels * consonants % k == 0) ++ans;
            }
        }
        return ans; 
    }


    /*2948. Make Lexicographically Smallest Array by Swapping Elements (Medium)
    You are given a 0-indexed array of positive integers nums and a positive 
    integer limit. In one operation, you can choose any two indices i and j and 
    swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit. Return the 
    lexicographically smallest array that can be obtained by performing the 
    operation any number of times. An array a is lexicographically smaller than 
    an array b if in the first position where a and b differ, array a has an 
    element that is less than the corresponding element in b. For example, the 
    array [2,10,3] is lexicographically smaller than the array [10,2,3] because 
    they differ at index 0 and 2 < 10. 

    Example 1:
    Input: nums = [1,5,3,9,8], limit = 2
    Output: [1,3,5,8,9]
    Explanation: Apply the operation 2 times:
                 - Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
                 - Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
                 We cannot obtain a lexicographically smaller array by applying 
                 any more operations. Note that it may be possible to get the 
                 same result by doing different operations.
    
    Example 2:
    Input: nums = [1,7,6,18,2,1], limit = 3
    Output: [1,6,7,18,1,2]
    Explanation: Apply the operation 3 times:
                 - Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
                 - Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
                 - Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
                 We cannot obtain a lexicographically smaller array by applying 
                 any more operations.
    
    Example 3:
    Input: nums = [1,7,28,19,10], limit = 3
    Output: [1,7,28,19,10]
    Explanation: [1,7,28,19,10] is the lexicographically smallest array we can 
                 obtain because we cannot apply the operation on any two 
                 indices.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9
    * 1 <= limit <= 10^9*/

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length; 
        int[][] vals = new int[n][2]; 
        for (int i = 0; i < n; ++i) vals[i] = new int[]{nums[i], i}; 
        Arrays.sort(vals, (x, y) -> Integer.compare(x[0], y[0])); 
        List<Integer> idx = new ArrayList(); 
        for (int k = 0, kk = 0; k < n; ++k) {
            int x = vals[k][0], i = vals[k][1]; 
            idx.add(i); 
            if (k == n-1 || x+limit < vals[k+1][0]) {
                Collections.sort(idx); 
                for (int j = 0; j < idx.size(); ++j) nums[idx.get(j)] = vals[kk+j][0]; 
                idx.clear(); 
                kk = k+1; 
            }
        }
        return nums; 
    }


    /*2949. Count Beautiful Substrings II (Hard)
    You are given a string s and a positive integer k. Let vowels and 
    consonants be the number of vowels and consonants in a string. A string is 
    beautiful if:
    * vowels == consonants.
    * (vowels * consonants) % k == 0, in other terms the multiplication of 
      vowels and consonants is divisible by k.
    Return the number of non-empty beautiful substrings in the given string s.
    A substring is a contiguous sequence of characters in a string. Vowel 
    letters in English are 'a', 'e', 'i', 'o', and 'u'. Consonant letters in 
    English are every letter except vowels.

    Example 1:
    Input: s = "baeyh", k = 2
    Output: 2
    Explanation: There are 2 beautiful substrings in the given string.
                 - Substring "baeyh", vowels = 2 (["a",e"]), 
                   consonants = 2 (["y","h"]). You can see that string "aeyh" 
                   is beautiful as vowels == consonants and 
                   vowels * consonants % k == 0.
                 - Substring "baeyh", vowels = 2 (["a",e"]), 
                   consonants = 2 (["b","y"]). You can see that string "baey" 
                   is beautiful as vowels == consonants and 
                   vowels * consonants % k == 0.
                 It can be shown that there are only 2 beautiful substrings in 
                 the given string.
    
    Example 2:
    Input: s = "abba", k = 1
    Output: 3
    Explanation: There are 3 beautiful substrings in the given string.
                 - Substring "abba", vowels = 1 (["a"]), consonants = 1 (["b"]).
                 - Substring "abba", vowels = 1 (["a"]), consonants = 1 (["b"]).
                 - Substring "abba", vowels = 2 (["a","a"]), consonants = 2 
                   (["b","b"]).
                 It can be shown that there are only 3 beautiful substrings in 
                 the given string.
    
    Example 3:
    Input: s = "bcdf", k = 1
    Output: 0
    Explanation: There are no beautiful substrings in the given string.
     
    Constraints:
    * 1 <= s.length <= 5 * 10^4
    * 1 <= k <= 1000
    * s consists of only English lowercase letters.*/

    public long beautifulSubstrings(String s, int k) {
        int n = 1; 
        for (; n <= k; ++n)
            if (n * n % k == 0) break; 
        n *= 2; 
        HashMap<Integer, Integer>[] seen = new HashMap[n]; 
        for (int i = 0; i < n; ++i) seen[i] = new HashMap(); 
        seen[n-1].put(0, 1); 
        long ans = 0; 
        for (int i = 0, diff = 0; i < s.length(); ++i) {
            if ("aeiou".indexOf(s.charAt(i)) >= 0) ++diff; 
            else --diff; 
            ans += seen[i % n].getOrDefault(diff, 0); 
            seen[i % n].merge(diff, 1, Integer::sum); 
        }
        return ans; 
    }


    /*2960. Count Tested Devices After Test Operations (Easy)
    You are given a 0-indexed integer array batteryPercentages having length n,
    denoting the battery percentages of n 0-indexed devices. Your task is to
    test each device i in order from 0 to n - 1, by performing the following
    test operations:
    * If batteryPercentages[i] is greater than 0:
      + Increment the count of tested devices.
      + Decrease the battery percentage of all devices with indices j in the
        range [i + 1, n - 1] by 1, ensuring their battery percentage never goes
        below 0, i.e, batteryPercentages[j] = max(0, batteryPercentages[j] - 1).
      + Move to the next device.
    * Otherwise, move to the next device without performing any test.
    Return an integer denoting the number of devices that will be tested after
    performing the test operations in order.

    Example 1:
    Input: batteryPercentages = [1,1,2,1,3]
    Output: 3
    Explanation: Performing the test operations in order starting from device 0:
                 - At device 1, batteryPercentages [1] == 0, so we move to the
                   next device without testing.
                 - At device 2, batteryPercentages[2] > 0, so there are now 2
                   tested devices, and batteryPercentages becomes [1,0,1,0,1].
                 - At device 3, batteryPercentages [3] == 0, so we move to the
                   next device without testing.
                 - At device 0, batteryPercentages[0] > 0, so there is now 1
                   tested device, and batteryPercentages becomes [1,0,1,0,2].
                 - At device 4, batteryPercentages[4] > 0, so there are now 3
                   tested devices, and batteryPercentages stays the same.
                 So, the answer is 3.

    Example 2:
    Input: batteryPercentages = [0,1,2]
    Output: 2
    Explanation: Performing the test operations in order starting from device 0:
                 - At device 0, batteryPercentages[0] == 0, so we move to the
                   next device without testing.
                 - At device 1, batteryPercentages[1] > 0, so there is now 1
                   tested device, and batteryPercentages becomes [0,1,1].
                 - At device 2, batteryPercentages [2] > 0, so there are now 2
                   tested devices, and batteryPercentages stays the same.
                 So, the answer is 2.

    Constraints:
    * 1 <= n == batteryPercentages.length <= 100
    * 0 <= batteryPercentages[i] <= 100*/

    public int countTestedDevices(int[] batteryPercentages) {
        int ans = 0;
        for (var x : batteryPercentages)
            if (ans < x) ++ans;
        return ans;
    }


    /*2961. Double Modular Exponentiation (Medium)
    You are given a 0-indexed 2D array variables where
    variables[i] = [ai, bi, ci, mi], and an integer target. An index i is good
    if the following formula holds:
    * 0 <= i < variables.length
    * ((aibi % 10)ci) % mi == target
    Return an array consisting of good indices in any order.

    Example 1:
    Input: variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
    Output: [0,2]
    Explanation: For each index i in the variables array:
                 1) For the index 0, variables[0] = [2,3,3,10],
                    (23 % 10)3 % 10 = 2.
                 2) For the index 1, variables[1] = [3,3,3,1],
                    (33 % 10)3 % 1 = 0.
                 3) For the index 2, variables[2] = [6,1,1,4],
                    (61 % 10)1 % 4 = 2.
                 Therefore we return [0,2] as the answer.

    Example 2:
    Input: variables = [[39,3,1000,1000]], target = 17
    Output: []
    Explanation: For each index i in the variables array:
                 1) For the index 0, variables[0] = [39,3,1000,1000],
                    (393 % 10)1000 % 1000 = 1.
                 Therefore we return [] as the answer.

    Constraints:
    * 1 <= variables.length <= 100
    * variables[i] == [ai, bi, ci, mi]
    * 1 <= ai, bi, ci, mi <= 10^3
    * 0 <= target <= 10^3*/

    private int pow(long x, int p, int m) {
        long ans = 1;
        for (; p > 0; p >>= 1) {
            if ((p & 1) > 0) ans = ans * x % m;
            x = x * x % m;
        }
        return (int) ans;
    }

    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < variables.length; ++i) {
            int a = variables[i][0], b = variables[i][1], c = variables[i][2], m = variables[i][3];
            if (pow(pow(a, b, 10), c, m) == target) ans.add(i);
        }
        return ans;
    }


    /*2962. Count Subarrays Where Max Element Appears at Least K Times (Meidum)
    You are given an integer array nums and a positive integer k. Return the
    number of subarrays where the maximum element of nums appears at least k
    times in that subarray. A subarray is a contiguous sequence of elements
    within an array.

    Example 1:
    Input: nums = [1,3,2,3,3], k = 2
    Output: 6
    Explanation: The subarrays that contain the element 3 at least 2 times are:
                 [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].

    Example 2:
    Input: nums = [1,4,2,1], k = 3
    Output: 0
    Explanation: No subarray contains the element 4 at least 3 times.

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^6
    * 1 <= k <= 10^5*/

    public long countSubarrays(int[] nums, int k) {
        long ans = 0; 
        int m = Arrays.stream(nums).max().getAsInt(), ii = 0, freq = 0;
        for (var x : nums) {
            if (x == m) ++freq; 
            while (freq == k) 
                if (nums[ii++] == m) --freq; 
            ans += ii; 
        }
        return ans; 
    }


    /*2963. Count the Number of Good Partitions (Hard)
    You are given a 0-indexed array nums consisting of positive integers. A
    partition of an array into one or more contiguous subarrays is called good
    if no two subarrays contain the same number. Return the total number of good
    partitions of nums. Since the answer may be large, return it modulo
    10^9 + 7.

    Example 1:
    Input: nums = [1,2,3,4]
    Output: 8
    Explanation: The 8 possible good partitions are: ([1], [2], [3], [4]),
                 ([1], [2], [3,4]), ([1], [2,3], [4]), ([1], [2,3,4]),
                 ([1,2], [3], [4]), ([1,2], [3,4]), ([1,2,3], [4]), and
                 ([1,2,3,4]).

    Example 2:
    Input: nums = [1,1,1,1]
    Output: 1
    Explanation: The only possible good partition is: ([1,1,1,1]).

    Example 3:
    Input: nums = [1,2,1,3]
    Output: 2
    Explanation: The 2 possible good partitions are: ([1,2,1], [3]) and
                 ([1,2,1,3]).

    Constraints:
    * 1 <= nums.length <= 10^5
    * 1 <= nums[i] <= 10^9*/

    public int numberOfGoodPartitions(int[] nums) {
        final int mod = 1_000_000_007; 
        Map<Integer, Integer> last = new HashMap(); 
        for (int i = 0; i < nums.length; ++i) last.put(nums[i], i); 
        long ans = 1; 
        for (int i = 0, ii = 0; i < nums.length; ++i) {
            if (ii < i) ans = ans * 2 % mod;  
            ii = Math.max(ii, last.get(nums[i])); 
        }
        return (int) ans; 
    }
}


/*170. Two Sum III - Data structure design (Easy)
Design a data structure that accepts a stream of integers and checks if it has 
a pair of integers that sum up to a particular value. Implement the TwoSum 
class:
* TwoSum() Initializes the TwoSum object, with an empty array initially.
* void add(int number) Adds number to the data structure.
* boolean find(int value) Returns true if there exists any pair of numbers 
  whose sum is equal to value, otherwise, it returns false.

Example 1:
Input: ["TwoSum", "add", "add", "add", "find", "find"]
       [[], [1], [3], [5], [4], [7]]
Output: [null, null, null, null, true, false]
Explanation
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --> [1]
twoSum.add(3);   // [1] --> [1,3]
twoSum.add(5);   // [1,3] --> [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false

Constraints:
* -10^5 <= number <= 10^5
* -2^31 <= value <= 2^31 - 1
* At most 10^4 calls will be made to add and find.*/

class TwoSum {
    private HashMap<Integer, Integer> freq; 
    
    public TwoSum() {
        freq = new HashMap(); 
    }
    
    public void add(int number) {
        freq.merge(number, 1, Integer::sum); 
    }
    
    public boolean find(int value) {
        for (var k : freq.keySet()) 
            if (freq.containsKey(value - k) && (k != value - k || freq.get(k) > 1)) return true; 
        return false; 
    }
}


/*308. Range Sum Query 2D - Mutable (Hard)
Given a 2D matrix matrix, handle multiple queries of the following types:
* Update the value of a cell in matrix.
* Calculate the sum of the elements of matrix inside the rectangle defined by 
  its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:
* NumMatrix(int[][] matrix) Initializes the object with the integer matrix 
  matrix.
* void update(int row, int col, int val) Updates the value of matrix[row][col] 
  to be val.
* int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the 
  elements of matrix inside the rectangle defined by its upper left corner 
  (row1, col1) and lower right corner (row2, col2).

Example 1:
Input: ["NumMatrix", "sumRegion", "update", "sumRegion"]
       [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
Output: [null, 8, null, 10]
Explanation: 
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e. sum of the left red rectangle)
numMatrix.update(3, 2, 2);       // matrix changes from left image to right image
numMatrix.sumRegion(2, 1, 4, 3); // return 10 (i.e. sum of the right red rectangle)

Constraints:
* m == matrix.length
* n == matrix[i].length
* 1 <= m, n <= 200
* -1000 <= matrix[i][j] <= 1000
* 0 <= row < m
* 0 <= col < n
* -1000 <= val <= 1000
* 0 <= row1 <= row2 < m
* 0 <= col1 <= col2 < n
* At most 5000 calls will be made to sumRegion and update.*/

class Fenwick2D {
    private int m, n; 
    private int[][] nums; 
    
    public Fenwick2D(int m, int n) {
        this.m = m; 
        this.n = n; 
        nums = new int[m+1][n+1]; 
    }
    
    public int query(int i, int j) {
        int ans = 0; 
        for (++i, ++j; i > 0; i -= i & -i) 
            for (int jj = j; jj > 0; jj -= jj & -jj) 
                ans += nums[i][jj]; 
        return ans; 
    }
    
    public void add(int i, int j, int delta) {
        for (++i, ++j; i <= m; i += i & -i) 
            for (int jj = j; jj <= n; jj += jj & -jj) 
                nums[i][jj] += delta; 
    }
}


class NumMatrix {
    private int m, n; 
    private int[][] vals; 
    private Fenwick2D tree; 
    
    public NumMatrix(int[][] matrix) {
        m = matrix.length; 
        n = matrix[0].length; 
        vals = new int[m][n]; 
        tree = new Fenwick2D(m, n); 
        for (int i = 0; i < m; ++i) 
            for (int j = 0; j < n; ++j)
                update(i, j, matrix[i][j]); 
    }
    
    public void update(int row, int col, int val) {
        int delta = val - vals[row][col]; 
        vals[row][col] = val; 
        tree.add(row, col, delta); 
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return tree.query(row2, col2) - tree.query(row2, col1-1) - tree.query(row1-1, col2) + tree.query(row1-1, col1-1); 
    }
}


/*1724. Checking Existence of Edge Length Limited Paths II (Hard)
An undirected graph of n nodes is defined by edgeList, where 
edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with 
distance disi. Note that there may be multiple edges between two nodes, and 
the graph may not be connected. Implement the DistanceLimitedPathsExist 
class:
* DistanceLimitedPathsExist(int n, int[][] edgeList) Initializes the class 
  with an undirected graph.
* boolean query(int p, int q, int limit) Returns true if there exists a 
  path from p to q such that each edge on the path has a distance strictly 
  less than limit, and otherwise false.

Example 1:
Input: ["DistanceLimitedPathsExist", "query", "query", "query", "query"]
       [[6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]], [2, 3, 2], [1, 3, 3], [2, 0, 3], [0, 5, 6]]
Output: [null, true, false, true, false]
Explanation: 
DistanceLimitedPathsExist distanceLimitedPathsExist = new DistanceLimitedPathsExist(6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]);
distanceLimitedPathsExist.query(2, 3, 2); // return true. There is an edge from 2 to 3 of distance 1, which is less than 2.
distanceLimitedPathsExist.query(1, 3, 3); // return false. There is no way to go from 1 to 3 with distances strictly less than 3.
distanceLimitedPathsExist.query(2, 0, 3); // return true. There is a way to go from 2 to 0 with distance < 3: travel from 2 to 3 to 0.
distanceLimitedPathsExist.query(0, 5, 6); // return false. There are no paths from 0 to 5.

Constraints:
* 2 <= n <= 10^4
* 0 <= edgeList.length <= 10^4
* edgeList[i].length == 3
* 0 <= ui, vi, p, q <= n-1
* ui != vi
* p != q
* 1 <= disi, limit <= 10^9
* At most 104 calls will be made to query.*/

class DistanceLimitedPathsExist {
    private int[] depth, parent; 
    private int[][] lift, weight; 
    
    private int find(int p) {
        if (p != parent[p]) 
            parent[p] = find(parent[p]); 
        return parent[p]; 
    }
    
    public DistanceLimitedPathsExist(int n, int[][] edgeList) {
        List<int[]>[] tree = new ArrayList[n]; 
        for (int i = 0; i < n; ++i) tree[i] = new ArrayList(); 
        parent = new int[n]; 
        for (int i = 0; i < n; ++i) parent[i] = i; 
        Arrays.sort(edgeList, (a, b) -> Integer.compare(a[2], b[2])); 
        for (var e : edgeList) {
            int uu = find(e[0]), vv = find(e[1]); 
            if (uu != vv) {
                tree[e[0]].add(new int[] {e[1], e[2]}); 
                tree[e[1]].add(new int[] {e[0], e[2]}); 
                parent[uu] = vv; 
            }
        }
        depth = new int[n]; 
        Arrays.fill(depth, -1); 
        lift = new int[n][32]; 
        for (int i = 0; i < n; ++i) Arrays.fill(lift[i], -1); 
        weight = new int[n][32]; 
        for (int i = 0; i < n; ++i) 
            if (depth[i] == -1) {
                Stack<int[]> stk = new Stack(); 
                stk.add(new int[] {i, -1, 0}); 
                while (!stk.isEmpty()) {
                    var elem = stk.pop(); 
                    int u = elem[0], p = elem[1], d = elem[2]; 
                    depth[u] = d; 
                    for (var uu : tree[u]) {
                        int v = uu[0], w = uu[1]; 
                        if (v != p) {
                            lift[v][0] = u; 
                            weight[v][0] = w; 
                            for (int j = 1; j < 32 && lift[v][j-1] != -1; ++j) {
                                weight[v][j] = Math.max(weight[v][j-1], weight[lift[v][j-1]][j-1]); 
                                lift[v][j] = lift[lift[v][j-1]][j-1]; 
                            }
                            stk.add(new int[] {v, u, d+1}); 
                        }
                    }
                }
            }
    }
    
    public boolean query(int p, int q, int limit) {
        if (find(p) != find(q)) return false; 
        if (depth[p] > depth[q]) {
            int tmp = p; 
            p = q; 
            q = tmp; 
        }
        int wt = 0; 
        for (int i = 0; i < 32; ++i) 
            if ((depth[q]-depth[p] & 1<<i) > 0) {
                wt = Math.max(wt, weight[q][i]); 
                q = lift[q][i]; 
            }
        if (p == q) return wt < limit; 
        for (int i = 31; i >= 0; --i) 
            if (lift[p][i] != lift[q][i] && 0 <= q) {
                wt = Math.max(wt, Math.max(weight[p][i], weight[q][i])); 
                p = lift[p][i]; 
                q = lift[q][i]; 
            }
        return Math.max(wt, Math.max(weight[p][0], weight[q][0])) < limit; 
    }
}


/*2254. Design Video Sharing Platform (Hard)
You have a video sharing platform where users can upload and delete videos. 
Each video is a string of digits, where the ith digit of the string represents 
the content of the video at minute i. For example, the first digit represents 
the content at minute 0 in the video, the second digit represents the content 
at minute 1 in the video, and so on. Viewers of videos can also like and 
dislike videos. Internally, the platform keeps track of the number of views, 
likes, and dislikes on each video. When a video is uploaded, it is associated 
with the smallest available integer videoId starting from 0. Once a video is 
deleted, the videoId associated with that video can be reused for another 
video. Implement the VideoSharingPlatform class:
* VideoSharingPlatform() Initializes the object.
* int upload(String video) The user uploads a video. Return the videoId 
  associated with the video.
* void remove(int videoId) If there is a video associated with videoId, remove 
  the video.
* String watch(int videoId, int startMinute, int endMinute) If there is a video 
  associated with videoId, increase the number of views on the video by 1 and 
  return the substring of the video string starting at startMinute and ending 
  at min(endMinute, video.length - 1) (inclusive). Otherwise, return "-1".
* void like(int videoId) Increases the number of likes on the video associated 
  with videoId by 1 if there is a video associated with videoId.
* void dislike(int videoId) Increases the number of dislikes on the video 
  associated with videoId by 1 if there is a video associated with videoId.
* int[] getLikesAndDislikes(int videoId) Return a 0-indexed integer array 
  values of length 2 where values[0] is the number of likes and values[1] is 
  the number of dislikes on the video associated with videoId. If there is no 
  video associated with videoId, return [-1].
* int getViews(int videoId) Return the number of views on the video associated 
  with videoId, if there is no video associated with videoId, return -1.

Example 1:
Input: ["VideoSharingPlatform", "upload", "upload", "remove", "remove", "upload", "watch", "watch", "like", "dislike", "dislike", "getLikesAndDislikes", "getViews"]
       [[], ["123"], ["456"], [4], [0], ["789"], [1, 0, 5], [1, 0, 1], [1], [1], [1], [1], [1]]
Output: [null, 0, 1, null, null, 0, "456", "45", null, null, null, [1, 2], 2]
Explanation
VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
videoSharingPlatform.upload("123");          // The smallest available videoId is 0, so return 0.
videoSharingPlatform.upload("456");          // The smallest available videoId is 1, so return 1.
videoSharingPlatform.remove(4);              // There is no video associated with videoId 4, so do nothing.
videoSharingPlatform.remove(0);              // Remove the video associated with videoId 0.
videoSharingPlatform.upload("789");          // Since the video associated with videoId 0 was deleted,
                                             // 0 is the smallest available videoId, so return 0.
videoSharingPlatform.watch(1, 0, 5);         // The video associated with videoId 1 is "456".
                                             // The video from minute 0 to min(5, 3 - 1) = 2 is "456", so return "453".
videoSharingPlatform.watch(1, 0, 1);         // The video associated with videoId 1 is "456".
                                             // The video from minute 0 to min(1, 3 - 1) = 1 is "45", so return "45".
videoSharingPlatform.like(1);                // Increase the number of likes on the video associated with videoId 1.
videoSharingPlatform.dislike(1);             // Increase the number of dislikes on the video associated with videoId 1.
videoSharingPlatform.dislike(1);             // Increase the number of dislikes on the video associated with videoId 1.
videoSharingPlatform.getLikesAndDislikes(1); // There is 1 like and 2 dislikes on the video associated with videoId 1, so return [1, 2].
videoSharingPlatform.getViews(1);            // The video associated with videoId 1 has 2 views, so return 2.

Example 2:
Input: ["VideoSharingPlatform", "remove", "watch", "like", "dislike", "getLikesAndDislikes", "getViews"]
       [[], [0], [0, 0, 1], [0], [0], [0], [0]]
Output: [null, null, "-1", null, null, [-1], -1]
Explanation
VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
videoSharingPlatform.remove(0);              // There is no video associated with videoId 0, so do nothing.
videoSharingPlatform.watch(0, 0, 1);         // There is no video associated with videoId 0, so return "-1".
videoSharingPlatform.like(0);                // There is no video associated with videoId 0, so do nothing.
videoSharingPlatform.dislike(0);             // There is no video associated with videoId 0, so do nothing.
videoSharingPlatform.getLikesAndDislikes(0); // There is no video associated with videoId 0, so return [-1].
videoSharingPlatform.getViews(0);            // There is no video associated with videoId 0, so return -1.

Constraints:
* 1 <= video.length <= 10^5
* The sum of video.length over all calls to upload does not exceed 10^5
* video consists of digits.
* 0 <= videoId <= 10^5
* 0 <= startMinute < endMinute < 10^5
* startMinute < video.length
* The sum of endMinute - startMinute over all calls to watch does not exceed 10^5.
* At most 10^5 calls in total will be made to all functions.*/

class VideoSharingPlatform {
    private Queue<Integer> pq; 
    private List<int[]> stats; 
    private List<String> videos; 
    
    public VideoSharingPlatform() {
        pq = new PriorityQueue(); 
        stats = new ArrayList(); 
        videos = new ArrayList(); 
    }
    
    public int upload(String video) {
        int videoId = videos.size(); 
        if (pq.size() > 0) {
            videoId = pq.poll(); 
            videos.set(videoId, video); 
        } else {
            stats.add(new int[]{0, 0, 0}); 
            videos.add(video); 
        }
        return videoId; 
    }
    
    public void remove(int videoId) {
        if (videoId < videos.size() && !videos.get(videoId).isEmpty()) {
            pq.add(videoId); 
            stats.set(videoId, new int[]{0, 0, 0}); 
            videos.set(videoId, ""); 
        }
    }
    
    public String watch(int videoId, int startMinute, int endMinute) {
        if (videoId < videos.size() && !videos.get(videoId).isEmpty()) {
            ++stats.get(videoId)[0]; 
            String video = videos.get(videoId); 
            return video.substring(startMinute, Math.min(video.length(), endMinute+1)); 
        }
        return "-1"; 
    }
    
    public void like(int videoId) {
        if (videoId < videos.size() && !videos.get(videoId).isEmpty()) 
            ++stats.get(videoId)[1]; 
    }
    
    public void dislike(int videoId) {
        if (videoId < videos.size() && !videos.get(videoId).isEmpty()) 
            ++stats.get(videoId)[2]; 
    }
    
    public int[] getLikesAndDislikes(int videoId) {
        if (videoId < videos.size() && !videos.get(videoId).isEmpty()) 
            return new int[]{stats.get(videoId)[1], stats.get(videoId)[2]}; 
        return new int[]{-1}; 
    }
    
    public int getViews(int videoId) {
        if (videoId < videos.size() && !videos.get(videoId).isEmpty()) 
            return stats.get(videoId)[0]; 
        return -1; 
    }
}


/*2502. Design Memory Allocator (Medium)
You are given an integer n representing the size of a 0-indexed memory array. 
All memory units are initially free. You have a memory allocator with the 
following functionalities:
* Allocate a block of size consecutive free memory units and assign it the id 
  mID.
* Free all memory units with the given id mID.
Note that:
* Multiple blocks can be allocated to the same mID.
* You should free all the memory units with mID, even if they were allocated in 
  different blocks.
Implement the Allocator class:
* Allocator(int n) Initializes an Allocator object with a memory array of size 
  n.
* int allocate(int size, int mID) Find the leftmost block of size consecutive 
  free memory units and allocate it with the id mID. Return the block's first 
  index. If such a block does not exist, return -1.
* int free(int mID) Free all memory units with the id mID. Return the number of 
  memory units you have freed.

Example 1:
Input: ["Allocator", "allocate", "allocate", "allocate", "free", "allocate", "allocate", "allocate", "free", "allocate", "free"]
       [[10], [1, 1], [1, 2], [1, 3], [2], [3, 4], [1, 1], [1, 1], [1], [10, 2], [7]]
Output: [null, 0, 1, 2, 1, 3, 1, 6, 3, -1, 0]
Explanation: 
Allocator loc = new Allocator(10); // Initialize a memory array of size 10. All memory units are initially free.
loc.allocate(1, 1); // The leftmost block's first index is 0. The memory array becomes [1,_,_,_,_,_,_,_,_,_]. We return 0.
loc.allocate(1, 2); // The leftmost block's first index is 1. The memory array becomes [1,2,_,_,_,_,_,_,_,_]. We return 1.
loc.allocate(1, 3); // The leftmost block's first index is 2. The memory array becomes [1,2,3,_,_,_,_,_,_,_]. We return 2.
loc.free(2); // Free all memory units with mID 2. The memory array becomes [1,_, 3,_,_,_,_,_,_,_]. We return 1 since there is only 1 unit with mID 2.
loc.allocate(3, 4); // The leftmost block's first index is 3. The memory array becomes [1,_,3,4,4,4,_,_,_,_]. We return 3.
loc.allocate(1, 1); // The leftmost block's first index is 1. The memory array becomes [1,1,3,4,4,4,_,_,_,_]. We return 1.
loc.allocate(1, 1); // The leftmost block's first index is 6. The memory array becomes [1,1,3,4,4,4,1,_,_,_]. We return 6.
loc.free(1); // Free all memory units with mID 1. The memory array becomes [_,_,3,4,4,4,_,_,_,_]. We return 3 since there are 3 units with mID 1.
loc.allocate(10, 2); // We can not find any free block with 10 consecutive free memory units, so we return -1.
loc.free(7); // Free all memory units with mID 7. The memory array remains the same since there is no memory unit with mID 7. We return 0.

Constraints:
* 1 <= n, size, mID <= 1000
* At most 1000 calls will be made to allocate and free.*/

class Allocator {
    private int[] memory; 
    
    public Allocator(int n) {
        memory = new int[n]; 
    }
    
    public int allocate(int size, int mID) {
        int cnt = 0; 
        for (int i = 0; i < memory.length; ++i) {
            if (memory[i] == 0) {
                if (++cnt == size) {
                    for (int ii = i; ii >= i-size+1; --ii) 
                        memory[ii] = mID; 
                    return i-size+1; 
                }
            } else cnt = 0; 
        }
        return -1; 
    }
    
    public int free(int mID) {
        int ans = 0; 
        for (int i = 0; i < memory.length; ++i) 
            if (memory[i] == mID) {
                ++ans; 
                memory[i] = 0; 
            }
        return ans; 
    }
}


/*2526. Find Consecutive Integers from a Data Stream (Medium)
For a stream of integers, implement a data structure that checks if the last k 
integers parsed in the stream are equal to value. Implement the DataStream 
class:
* DataStream(int value, int k) Initializes the object with an empty integer 
  stream and the two integers value and k.
* boolean consec(int num) Adds num to the stream of integers. Returns true if 
  the last k integers are equal to value, and false otherwise. If there are 
  less than k integers, the condition does not hold true, so returns false.

Example 1:
Input: ["DataStream", "consec", "consec", "consec", "consec"]
       [[4, 3], [4], [4], [4], [3]]
Output: [null, false, false, true, false]
Explanation: 
DataStream dataStream = new DataStream(4, 3); //value = 4, k = 3 
dataStream.consec(4); // Only 1 integer is parsed, so returns False. 
dataStream.consec(4); // Only 2 integers are parsed.
                      // Since 2 is less than k, returns False. 
dataStream.consec(4); // The 3 integers parsed are all equal to value, so returns True. 
dataStream.consec(3); // The last k integers parsed in the stream are [4,4,3].
                      // Since 3 is not equal to value, it returns False.
 
Constraints:
* 1 <= value, num <= 10^9
* 1 <= k <= 10^5
* At most 10^5 calls will be made to consec.*/

class DataStream {
    private int value = 0, k = 0, cnt = 0; 

    public DataStream(int value, int k) {
        this.value = value; 
        this.k = k; 
    }
    
    public boolean consec(int num) {
        if (value == num) ++cnt; 
        else cnt = 0; 
        return cnt >= k; 
    }
}


/*2642. Design Graph With Shortest Path Calculator (Hard)
There is a directed weighted graph that consists of n nodes numbered from 0 to 
n - 1. The edges of the graph are initially represented by the given array 
edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge 
from fromi to toi with the cost edgeCosti. Implement the Graph class:
* Graph(int n, int[][] edges) initializes the object with n nodes and the given 
  edges.
* addEdge(int[] edge) adds an edge to the list of edges where 
  edge = [from, to, edgeCost]. It is guaranteed that there is no edge between 
  the two nodes before adding this one.
* int shortestPath(int node1, int node2) returns the minimum cost of a path 
  from node1 to node2. If no path exists, return -1. The cost of a path is the 
  sum of the costs of the edges in the path.

Example 1:
Input: ["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
[[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
Output: [null, 6, -1, null, 6]
Explanation: 
Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the first 
                      // diagram above is 3 -> 0 -> 1 -> 2 with a total cost of 
                      // 3 + 2 + 1 = 6.
g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.
g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get the 
                      // second diagram above.
g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 
                      // 0 -> 1 -> 3 with a total cost of 2 + 4 = 6.

Constraints:
* 1 <= n <= 100
* 0 <= edges.length <= n * (n - 1)
* edges[i].length == edge.length == 3
* 0 <= fromi, toi, from, to, node1, node2 <= n - 1
* 1 <= edgeCosti, edgeCost <= 10^6
* There are no repeated edges and no self-loops in the graph at any point.
* At most 100 calls will be made for addEdge.
* At most 100 calls will be made for shortestPath.*/

class Graph {
    private int n; 
    private List<int[]>[] graph; 

    public Graph(int n, int[][] edges) {
        this.n = n; 
        graph = new ArrayList[n]; 
        for (int i = 0; i < n; ++i) 
            graph[i] = new ArrayList(); 
        for (var e : edges) 
            graph[e[0]].add(new int[]{e[1], e[2]}); 
    }
    
    public void addEdge(int[] edge) {
        graph[edge[0]].add(new int[]{edge[1], edge[2]}); 
    }
    
    public int shortestPath(int node1, int node2) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); 
        pq.add(new int[]{0, node1}); 
        int[] dist = new int[n]; 
        Arrays.fill(dist, Integer.MAX_VALUE); 
        dist[node1] = 0; 
        while (!pq.isEmpty()) {
            var elem = pq.poll(); 
            int cost = elem[0], u = elem[1]; 
            if (u == node2) return cost; 
            for (var nei : graph[u]) {
                int v = nei[0], w = nei[1]; 
                if (cost + w < dist[v]) {
                    dist[v] = cost + w; 
                    pq.add(new int[]{cost + w, v}); 
                }
            }
        }
        return -1; 
    }
}


/*2671. Frequency Tracker (Medium)
Design a data structure that keeps track of the values in it and answers some 
queries regarding their frequencies. Implement the FrequencyTracker class.
* FrequencyTracker(): Initializes the FrequencyTracker object with an empty 
  array initially.
* void add(int number): Adds number to the data structure.
* void deleteOne(int number): Deletes one occurence of number from the data 
  structure. The data structure may not contain number, and in this case 
  nothing is deleted.
* bool hasFrequency(int frequency): Returns true if there is a number in the 
  data structure that occurs frequency number of times, otherwise, it returns 
  false.

Example 1:
Input: ["FrequencyTracker", "add", "add", "hasFrequency"]
       [[], [3], [3], [2]]
Output: [null, null, null, true]
Explanation
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.add(3); // The data structure now contains [3]
frequencyTracker.add(3); // The data structure now contains [3, 3]
frequencyTracker.hasFrequency(2); // Returns true, because 3 occurs twice

Example 2:
Input: ["FrequencyTracker", "add", "deleteOne", "hasFrequency"]
       [[], [1], [1], [1]]
Output: [null, null, null, false]
Explanation
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.add(1); // The data structure now contains [1]
frequencyTracker.deleteOne(1); // The data structure becomes empty []
frequencyTracker.hasFrequency(1); // Returns false, because the data structure is empty

Example 3:
Input: ["FrequencyTracker", "hasFrequency", "add", "hasFrequency"]
       [[], [2], [3], [1]]
Output: [null, false, null, true]
Explanation
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.hasFrequency(2); // Returns false, because the data structure is empty
frequencyTracker.add(3); // The data structure now contains [3]
frequencyTracker.hasFrequency(1); // Returns true, because 3 occurs once

Constraints:
* 1 <= number <= 10^5
* 1 <= frequency <= 10^5
* At most, 2 * 10^5 calls will be made to add, deleteOne, and hasFrequency in total.*/

class FrequencyTracker {
    public Map<Integer, Integer> freq; 
    public Map<Integer, Set<Integer>> group; 
    
    public FrequencyTracker() {
        freq = new HashMap(); 
        group = new HashMap(); 
    }
    
    public void add(int number) {
        if (freq.getOrDefault(number, 0) > 0) group.get(freq.get(number)).remove(number); 
        freq.merge(number, 1, Integer::sum); 
        group.putIfAbsent(freq.get(number), new HashSet()); 
        group.get(freq.get(number)).add(number); 
    }
    
    public void deleteOne(int number) {
        if (freq.getOrDefault(number, 0) > 0) {
            group.get(freq.get(number)).remove(number); 
            freq.merge(number, -1, Integer::sum); 
            if (freq.get(number) > 0) group.get(freq.get(number)).add(number); 
        }
    }
    
    public boolean hasFrequency(int frequency) {
        return group.containsKey(frequency) && !group.get(frequency).isEmpty(); 
    }
}
