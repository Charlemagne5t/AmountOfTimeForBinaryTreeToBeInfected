import java.util.*;

public class Solution {
    public int amountOfTime(TreeNode root, int start) {
        if(root.left == null && root.right == null){
            return 0;
        }
        Map<TreeNode, List<TreeNode>> adjList = new HashMap<>();
        TreeNode[] startNode = new TreeNode[1];
        build(adjList, root, startNode, start);
        System.out.println(adjList);
        System.out.println(startNode[0]);

        return bfs(startNode[0], adjList);


    }
    private int bfs(TreeNode root, Map<TreeNode, List<TreeNode>> adjList){
        if(root == null){
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        Set<TreeNode> visited = new HashSet<>();
        visited.add(root);
        while(!q.isEmpty()){
            level++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode cur = q.poll();
                List<TreeNode> nbrs = adjList.get(cur);
                for(int j = 0; j < nbrs.size(); j++){
                    if(!visited.contains(nbrs.get(j))){
                        q.offer(nbrs.get(j));
                        visited.add(nbrs.get(j));
                    }
                }
            }

        }
        return level - 1;
    }

    private void build(Map<TreeNode, List<TreeNode>> adjList, TreeNode root, TreeNode[] startNode, int start){
        if(root == null){
            return;
        }

        if(root.val == start){
            startNode[0] = root;
        }
        if(root.left != null){
            List<TreeNode> neighboursR = adjList.getOrDefault(root, new ArrayList<>());
            neighboursR.add(root.left);
            adjList.put(root, neighboursR);

            List<TreeNode> neighboursChild = adjList.getOrDefault(root.left, new ArrayList<>());
            neighboursChild.add(root);
            adjList.put(root.left, neighboursChild);
            build(adjList, root.left, startNode, start);
        }

        if(root.right != null){
            List<TreeNode> neighboursR = adjList.getOrDefault(root, new ArrayList<>());
            neighboursR.add(root.right);
            adjList.put(root, neighboursR);

            List<TreeNode> neighboursChild = adjList.getOrDefault(root.right, new ArrayList<>());
            neighboursChild.add(root);
            adjList.put(root.right, neighboursChild);
            build(adjList, root.right, startNode, start);
        }
    }

}


