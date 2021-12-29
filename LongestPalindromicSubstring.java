class Solution {    
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
        String str = padS.substring(startInd,startInd + maxLen);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) != '#')
                sb.append(str.charAt(i));
        }
        return sb.toString();
    }    
}
