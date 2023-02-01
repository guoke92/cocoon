import java.util.stream.IntStream;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class Solution {

    public static void main(String[] args) {
        //int[] nums1 = {1, 2};
        //int[] nums2 = {3, 4};
        //double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        //System.out.println("medianSortedArrays = " + medianSortedArrays);

        String s = "aa";
        String p = "a";
        boolean match = isMatch(s, p, false);
        System.out.println("match = " + match);
    }

    public static boolean isMatch(String s, String p, boolean preMatchFlag) {

        boolean matchFlag = true;
        int indexP = 0;
        for (int i = 0; i < s.length(); i++) {

            char sc = s.charAt(i);
            char pc = p.charAt(indexP);
            if('*' == pc){
                if(indexP == p.length() -1){
                    return true;
                }
                while(!(matchFlag = isMatch(s.substring(i), p.substring(indexP + 1), preMatchFlag))){
                    i++;
                    if(i == s.length()){
                        break;
                    }
                }
                return matchFlag;
            }
            if('.' != pc && sc != pc){
                matchFlag = false;
                break;
            }
            preMatchFlag = true;
            indexP++;
        }

        return matchFlag;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int length1 = nums1.length;
        int length2 = nums2.length;

        int length = length1 + length2;
        int midlength = length / 2;
        boolean flag = false;// 数组长度是否为偶数
        if (length % 2 == 0) {
            flag = true;
        }

        if (length1 == 0) {
            int value1 = nums2[midlength];
            if (flag) {
                int value2 = nums2[midlength - 1];
                return (value1 + value2 + 0.0D) / 2;
            } else {
                return value1 + 0.0D;
            }
        }

        if (length2 == 0) {
            int value1 = nums1[midlength];
            if (flag) {
                int value2 = nums1[midlength - 1];
                return (value1 + value2 + 0.0D) / 2;
            } else {
                return value1 + 0.0D;
            }
        }

        int index1 = 0;
        int index2 = 0;
        int curr = 0;
        for (int i = 0; i <= midlength; i++) {
            if (index1 < length1 && index2 < length2) {
                if (nums1[index1] < nums2[index2]) {
                    if (i == midlength) {
                        if (flag) {
                            return (curr + nums1[index1] + 0.0D) / 2;
                        } else {
                            return nums1[index1] + 0.0D;
                        }
                    } else {
                        curr = nums1[index1];
                        index1++;
                    }
                } else {
                    if (i == midlength) {
                        if (flag) {
                            return (curr + nums2[index2] + 0.0D) / 2;
                        } else {
                            return nums2[index2] + 0.0D;
                        }
                    } else {
                        curr = nums2[index2];
                        index2++;
                    }
                }
            } else if (index1 >= length1) {
                if (i == midlength) {
                    if (flag) {
                        return (curr + nums2[index2] + 0.0D) / 2;
                    } else {
                        return nums2[index2] + 0.0D;
                    }
                } else {
                    curr = nums2[index2];
                    index2++;
                }
            } else if (index2 == length2) {
                if (i == midlength) {
                    if (flag) {
                        return (curr + nums1[index1] + 0.0D) / 2;
                    } else {
                        return nums1[index1] + 0.0D;
                    }
                } else {
                    curr = nums1[index1];
                    index1++;
                }
            }
        }

        return 0D;

    }
}
