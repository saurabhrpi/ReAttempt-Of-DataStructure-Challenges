/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// LeetCode problem #863

class Solution {     
    // DFS.
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();
        if(root.left == null && root.right == null)
        {
            if(k == 0)
            {
                result.add(root.val);
            }
        }
        else
            dfs(root, target, k, 0, result);
        return result;        
    }
    
    public int dfs(TreeNode root, TreeNode target, int k, int dist, List<Integer> result)
    {
        if(root == null)
            return -1;
        if(root.val == target.val)
        {
            checkSubtree(root, result, 0, k);
            return 1;
        }
        int left = dfs(root.left, target, k, dist, result);
        int right = dfs(root.right, target, k, dist, result);
        if(left != -1)
        {
            if(left == k)
                result.add(root.val);
            checkSubtree(root.right, result, left + 1, k);
            return left + 1; // returning parent(or next in the path while traveling from target)'s distance from target. 
        }
        if(right != -1)
        {
            if(right == k)
                result.add(root.val);
            checkSubtree(root.left, result, right + 1, k);
            return right + 1;
        }
        return -1;
    }
    
    public void checkSubtree(TreeNode root, List<Integer> result, int dist, int k)
    {
        if(root == null)
            return;
        if(dist == k)
        {
            result.add(root.val);
            return;
        }
        checkSubtree(root.left, result, dist + 1, k);
        checkSubtree(root.right, result, dist + 1, k);
    }
    
}

    
    // Approach #1. Create link to parent.
    /*
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
       Map<Integer, TreeNode> map = new HashMap<>();              
       createLinkToParent(root, map);                
       return bfs(target, k, map);
    }
    
    public List<Integer> bfs(TreeNode target, int k, Map<Integer, TreeNode> map)
    {
       List<Integer> result = new ArrayList<>();
       Set<TreeNode> set = new HashSet<>(); 
       Queue<TreeNode> queue = new LinkedList<>();
       queue.offer(target);
       set.add(target); 
       int dist = 0;
       while(!queue.isEmpty())
       {
           if(dist == k)
           {
               while(!queue.isEmpty())
               {
                   TreeNode t = queue.poll();
                   if(t != null)
                        result.add(t.val);
               }
           }
           else
           {
               int size = queue.size();
               List<TreeNode> l = new ArrayList<>();
               for(int i = 0 ; i < size; i++)
               {
                  TreeNode n = queue.poll();                  
                  if(n.left != null)
                  {
                    if(set.add(n.left))
                    {
                        queue.offer(n.left);
                    }   
                  }
                  if(n.right != null)
                  {
                    if(set.add(n.right))
                    {
                        queue.offer(n.right);
                    }   
                  }
                  TreeNode p = map.get(n.val); 
                  if(p != null && set.add(p))
                  {
                      queue.offer(p);
                  }
               }
               dist++;   
           }           
       }
       return result; 
    }
    
    public void createLinkToParent(TreeNode root, Map<Integer, TreeNode> map)
    {
        if(root == null)
        {
            return;
        }
        if(root.left != null)
            map.put(root.left.val, root);
        if(root.right != null)
            map.put(root.right.val, root);
        createLinkToParent(root.left, map);
        createLinkToParent(root.right, map);
    }
*/
