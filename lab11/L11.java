import java.util.ArrayList;

public class L11 {

    public static void main(String[] args) {
        q2();
    }
    
    final static int inf = Integer.MAX_VALUE;
    
    static void q2() {
        // A B C D E
        // Given thisGraph = (V,E) which
        // V = {A, B, C, D, E} and
        // E = { (A,B,3), (A,E,2), (B,C,1), (C,D,4), (D,E,5) }
        int[][] thisGraph = {
                { 0, 3, 0, 0, 2 },
                { 3, 0, 1, 0, 0 },
                { 0, 1, 0, 4, 0 },
                { 0, 0, 4, 0, 5 },
                { 2, 0, 0, 5, 0 }
        };

        System.out.println("computing dfs");
        q2_dfs(thisGraph);
    }

    private static void q2_dfs(int[][] thisGraph) {
        ArrayList<Integer> stack = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        stack.add(0); // root is at A, we'll suffix next city
        while (notEmpty(stack)) {
            int parent = stack.remove(stack.size() - 1); // pop()
            visited.add(parent);
            for (int x = 0; x < thisGraph.length; x++) {  
                if (0 < thisGraph[parent][x] && thisGraph[parent][x] < inf && !visited.contains(x) ) {
                    /* your code 1b */
                    stack.add(x);
                    System.out.println("Edge " + parent + ", " + x);
                    // System.out.println("Edge (" + (char) ('A' + parent) + ", " + (char) ('A' + x) + ")");
                }
            } //for
        }
    }
    
    private static boolean notEmpty(ArrayList<Integer> stack) {
        return !stack.isEmpty();
    }
}

