package code;

public class TreeNode {
    int data;
    TreeNode left, right, parent;

    public TreeNode(int d) {
        data = d;
        left = right = parent = null;
    }

    public int getData() {
        return data;
    }
    public TreeNode getLeft() {
        return left;
    }
    public TreeNode getRight() {
        return right;
    }
    public TreeNode getParent() {
        return parent;
    }

    public void setData(int d) {
        data = d;
    }
    public void setLeft(TreeNode t) {
        left = t;
    }
    public void setRight(TreeNode t) {
        right = t;
    }
    public void setParent(TreeNode t) {
        parent = t;
    }

    @Override
    public String toString() {
        // There are 4 cases no child,
        // left-child-only,
        // right-child-only,
        // and both children
        /* your code 6 */
        if (left != null && right != null)
            return left.data + "<-" + data + "->" + right.data;
        else if (left != null)
            return left.data + "<-" + data + "->null";
        else if (right != null)
            return "null<-" + data + "->" + right.data;
        else
            return "null<-" + data + "->null";
        // no child
    }
}