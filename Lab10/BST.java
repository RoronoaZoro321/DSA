// 65011258 Arayan Tiwari 

import code.TreeNode;

// binary search tree
public class BST {
    TreeNode root;

    public BST() {
        root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void insert(int d) {
        if (root == null) {
            root = new TreeNode(d);
        } else {
            TreeNode cur = root;
            while (cur != null) {
                if (d < cur.getData()) {
                    if (cur.getLeft() != null)
                        cur = cur.getLeft();
                    else {
                        /* your code 1 */
                        cur.setLeft(new TreeNode(d));
                        cur.getLeft().setParent(cur);
                        return;
                    }
                } else { // ! (d < p.getData())
                    if (cur.getRight() != null)
                        /* your code 2 */
                        cur = cur.getRight();
                    else {
                        cur.setRight(new TreeNode(d));
                        cur.getRight().setParent(cur);
                        return;
                    }
                }
            } // while
        }
    } // insert by iteration

    public void printPreOrder() {
        printPreOrderRecurse(root);
    }

    public void printInOrder() {
        printInOrderRecurse(root);
    }

    public void printPostOrder() {
        printPostOrderRecurse(root);
    }

    private void printPreOrderRecurse(TreeNode node) {
        /* your code 3 */
        if (node == null)
            return;
        System.out.println(node.getData() + " ");
        printPreOrderRecurse(node.getLeft());
        printPreOrderRecurse(node.getRight());
    }

    private void printInOrderRecurse(TreeNode node) {
        /* your code 4 */
        if (node == null)
            return;
        printInOrderRecurse(node.getLeft());
        System.out.println(node.getData() + " ");
        printInOrderRecurse(node.getRight());
    }

    private void printPostOrderRecurse(TreeNode node) {
        /* your code 5 */
        if (node == null)
            return;
        printPostOrderRecurse(node.getLeft());
        printPostOrderRecurse(node.getRight());
        System.out.println(node.getData() + " ");
    }

    public TreeNode search(int d) {
        TreeNode result = searchRecurse(d, root);
        return result;
    }

    public TreeNode searchRecurse(int d, TreeNode n) {
        if (n == null)
            return null;
        if (d == n.getData())
            return n;
        /* your code 7 */
        if (d < n.getData())
            return searchRecurse(d, n.getLeft());
        else
            return searchRecurse(d, n.getRight());
    }

    public TreeNode searchIter(int key) {
        if (root.getData() == key)
            return root;
        TreeNode current = root;
        while (current != null) {
            if (key < current.getData()) {
                if (current.getLeft() != null)
                    current = current.getLeft();
            } else {
                if (current.getRight() != null)
                    current = current.getRight();
            }
            if (current.getData() == key)
                return current;
                /* your code 8 */
            else {
                if (current.getLeft() == null && current.getRight() == null) {
                    return null;
                }
            }
        } // while
        return null;
    }

}