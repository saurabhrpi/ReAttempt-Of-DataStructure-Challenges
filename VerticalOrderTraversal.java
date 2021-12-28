// BFS. O(N)
class Solution {
public List<List<Integer>> verticalOrder(TreeNode root) {        
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Pair<TreeNode,Integer>> queue = new LinkedList<>();
        Pair<TreeNode, Integer> pr = new Pair<TreeNode, Integer>(root,0);        
        queue.offer(pr);
        int col = 0;
        while(!queue.isEmpty())
        {        
           int size = queue.size();
           for(int i = 0; i < size; i++)
           {
               Pair<TreeNode,Integer> o = queue.poll();
               TreeNode n = o.getKey();               
               col = o.getValue();     
               if(!map.containsKey(col))
                   map.put(col, new ArrayList<>());
               map.get(col).add(n.val);
               if(n.left != null)
               {
                   Pair<TreeNode, Integer> p = new Pair(n.left, col - 1);
                   queue.offer(p);
               }
               if(n.right != null)
               {
                   Pair<TreeNode, Integer> p = new Pair(n.right, col + 1);
                   queue.offer(p);
               }
           }
        }
        
        for(int i = -50; i < 50; i++)
        {
            if(map.containsKey(i))
            {
                result.add(map.get(i));
            }
        }
        return result;
    }
}
/*
// DFS. O(N*log(N))
class Solution {
public List<List<Integer>> verticalOrder(TreeNode root) {                        
        List<List<Integer>> result = new ArrayList<>();        
        Map<Integer, List<Pair<Integer, Integer>>> map = new HashMap<>();
        dfs(root, map, 0, 0);                
        for(int i = -50; i < 50; i++)
        {
            if(map.containsKey(i))
            {
                List<Pair<Integer,Integer>> l = map.get(i);
                Collections.sort(l, new Comparator<Pair<Integer,Integer>>()
                {
                    @Override
                    public int compare(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2)
                    {
                        return p1.getKey() - p2.getKey();
                    }
                }
                );
                List<Integer> listV = new ArrayList<>();
                for(Pair<Integer,Integer> p : l)
                {
                    listV.add(p.getValue());
                }
                result.add(listV);
            }
        }
        return result;
    }
    
    public void dfs(TreeNode root, Map<Integer, List<Pair<Integer, Integer>>> map, int row, int col)
    {
        if(root == null)
            return;
        Pair<Integer,Integer> pair = new Pair<Integer,Integer>(row, root.val);
        if(!map.containsKey(col))
            map.put(col, new ArrayList<>());
        map.get(col).add(pair);
        dfs(root.left, map, row + 1, col - 1);
        dfs(root.right, map, row + 1, col + 1);
    }
}
*/
