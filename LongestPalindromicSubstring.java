class Solution {    
    // O(N).. Manacher's Algo.
    public String longestPalindrome(String s) {
        char[] paddedCharArr = new char[s.length()*2 + 1];
        int[] arrOfOneSideLenInclCenterAtI = new int[paddedCharArr.length];
        int indexOfCenterOfCurrLongPal = 0, lasIndexCurrLongestPal = 0; 
        for(int i = 0; i < s.length(); i++){
            paddedCharArr[2*i+1] = s.charAt(i);
        }
        
        // for(int i = 0; i < paddedCharArr.length; i++)
        //     System.out.println("paddedCharArr[i]: " + paddedCharArr[i]);        
        
        arrOfOneSideLenInclCenterAtI[0] = 1; // Length of palindrome from the center to one end (incl. center)
        for(int i = 1; i < arrOfOneSideLenInclCenterAtI.length; i++){
            System.out.println("start of iteration " + i);
            System.out.println("for paddedCharArr[i] " + paddedCharArr[i]);
            System.out.println("lasIndexCurrLongestPal " + lasIndexCurrLongestPal);
            System.out.println("indexOfCenterOfCurrLongPal " + indexOfCenterOfCurrLongPal);
            // if index i is outside the bounds of longest palindrome so far, set Len[i] to 1. Else
            // set Len[i] = minimum of the (Len of its mirror image character or its distance from the palindrome's end + 1).            
            // If index i is within the currentLongestPal then take min of 
            // (i's mirror image's arrOfOneSideLenInclCenterAtI value, i's distance from lasIndexCurrLongestPal).
            // One reason being it's possible the mirror image is center of a previous palindrome, 
            // thus causing arrOfOneSideLenInclCenterAtI[i] to be > than i's distance from the last char of current palindrome.
             arrOfOneSideLenInclCenterAtI[i] = i < lasIndexCurrLongestPal ?  
                 Math.min(arrOfOneSideLenInclCenterAtI[2*indexOfCenterOfCurrLongPal - i], lasIndexCurrLongestPal-i+1) : 1;             
             System.out.println("arrOfOneSideLenInclCenterAtI[i] " + arrOfOneSideLenInclCenterAtI[i]);// Length of palindrome centerd at i
            
            // Before passing variables as paddedString 's indices, checking for their validity. And then,
            // checking if there is some palindrome of length > 1 centered at index i.
            while(i-arrOfOneSideLenInclCenterAtI[i] >= 0 && i+arrOfOneSideLenInclCenterAtI[i] < paddedCharArr.length && 
                  paddedCharArr[i-arrOfOneSideLenInclCenterAtI[i]] == paddedCharArr[i+arrOfOneSideLenInclCenterAtI[i]])             
            {
                System.out.println("inside while");
                arrOfOneSideLenInclCenterAtI[i]++; // increase and then check again lower and higher indices for palindrome
            }

            if (arrOfOneSideLenInclCenterAtI[i] > lasIndexCurrLongestPal-indexOfCenterOfCurrLongPal+1) { 
                indexOfCenterOfCurrLongPal = i;  // index of center of longest palindrome so far
                lasIndexCurrLongestPal = i + arrOfOneSideLenInclCenterAtI[i] - 1; 
            }
            System.out.println("end of iteration " + i);
        }
        System.out.println("returning " + s.substring((2*indexOfCenterOfCurrLongPal-lasIndexCurrLongestPal)/2, lasIndexCurrLongestPal/2));
        // divide by 2 as for any index i in original string s, i = newIndex/2. For e.g. 0 == 1/2, 1 == 3/2 and so on. 
        return s.substring((2*indexOfCenterOfCurrLongPal-lasIndexCurrLongestPal)/2, lasIndexCurrLongestPal/2);  
        
    }    
}
    /*
    // O(N^2)
    public String longestPalindrome(String s) {
        if(s.length() <= 1)
            return s;
        char[] ch = new char[2*(s.length()) + 1];
        int j = 0;
        for(int i = 0; i < ch.length; i++)
        {
            if(i%2 == 0)
                ch[i] = '#';
            else
            {   
                ch[i] = s.charAt(j);
                j++;
            }                
        }
        String padS = new String(ch);        
        int center = 0, left = 0, right = 0;
        int maxLen = 0;
        int startInd = 0;
        for(int i = 1; i < padS.length(); i++)
        {
            center = i;                    
            left = center - 1;
            right = center + 1;
            while((left >= 0) && (right < padS.length()) && (padS.charAt(left) == padS.charAt(right)))    
            {            
                left--;
                right++;
            }
            if(maxLen < right - left - 1)
            {
                maxLen = right - left - 1;
                startInd = left + 1;   
            }            
        }
        int ind = startInd/2; // this relationship can be derived between old and new string's indices
        return s.substring(ind, ind + (maxLen/2));
        
        // String str = padS.substring(startInd,startInd + maxLen);
        // StringBuilder sb = new StringBuilder();
        // for(int i = 0; i < str.length(); i++)
        // {
        //     if(str.charAt(i) != '#')
        //         sb.append(str.charAt(i));
        // }
        // return sb.toString();            
    }    
*/
