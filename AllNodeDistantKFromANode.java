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
}
