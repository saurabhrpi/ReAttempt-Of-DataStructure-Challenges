class Solution {
    public int maxProfit(int[] prices) {
        int diff = 0, min = Integer.MAX_VALUE;
        int max = 0;
        for(int i = 0; i < prices.length; i++)
        {
            if(min > prices[i])
                min = prices[i];
            else if(diff < prices[i] - min)
            {                
                diff = prices[i] - min;
            }
        }
        return diff;
    }
}
