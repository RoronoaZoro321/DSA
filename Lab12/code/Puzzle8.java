package code;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Puzzle8 {
    public int size = 3;
    public int[] sequence = new int[size * size];
    public ArrayList<Puzzle8State> explored = new ArrayList<>();
    public Stack<Puzzle8State> dfs = new Stack<>();

    public Puzzle8(int[] input){
        for(int i = 0; i < input.length; i+=3) {
            int tileValue = input[i];
            int row = input[i+1];
            int col = input[i+2];
            sequence[row * size + col] = tileValue;
        }
    }

    public void displayBoard() {
        for (int i = 0; i < sequence.length; i++) {
            if (i % size == 0 && i != 0)
                System.out.println();
            if (sequence[i] == 9){
                System.out.print("  ");
            }else{
                System.out.print(sequence[i] + " ");
            }
        }
        System.out.println();
    }

    public void generateNexMove() {
        int score = 0;
        int wrong = 9;
        int blankPos = 0;
        Puzzle8State currentState = new Puzzle8State(sequence);
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] == 9) {
                blankPos = i;
                break;
            }
        }
        // south
        if (blankPos + 3 < size * size){
            int[] newSequence = sequence.clone();
            newSequence[blankPos] = newSequence[blankPos + 3];
            newSequence[blankPos + 3] = 9;
            Puzzle8State newState = new Puzzle8State(newSequence);
            if (!Puzzle8State.equals(newState, this)){
                dfs.push(newState);
            }
            for (int i = 0; i < newState.sequence.length; i++) {
                if (newState.sequence[i] != i+1)
                    if (newState.sequence[i] != 9)
                        score++;
            }
            System.out.println("Pushing south "+score);
            System.out.println(newState.toString());
            if (score < wrong){
                wrong = score;
                currentState = newState;
            }
            score = 0;
        }
        // north
        if (blankPos - 3 > -1){
            int[] newSequence = sequence.clone();
            newSequence[blankPos] = newSequence[blankPos - 3];
            newSequence[blankPos - 3] = 9;
            Puzzle8State newState = new Puzzle8State(newSequence);
            if (!Puzzle8State.equals(newState, this)){
                dfs.push(newState);
            }
            for (int i = 0; i < newState.sequence.length; i++) {
                if (newState.sequence[i] != i+1)
                    if (newState.sequence[i] != 9)
                        score++;
            }
            System.out.println("Pushing north "+score);
            System.out.println(newState.toString());
            if (score < wrong){
             wrong = score;
                currentState = newState;
            }
            score = 0;
        }
        // east
        if (blankPos % 3 < 2){
            int[] newSequence = sequence.clone();
            newSequence[blankPos] = newSequence[blankPos + 1];
            newSequence[blankPos + 1] = 9;
            Puzzle8State newState = new Puzzle8State(newSequence);
            if (!Puzzle8State.equals(newState, this)){
                dfs.push(newState);
            }
            for (int i = 0; i < newState.sequence.length; i++) {
                if (newState.sequence[i] != i+1)
                    if (newState.sequence[i] != 9)
                        score++;
            }
            System.out.println("Pushing east "+score);
            System.out.println(newState.toString());
            if (score < wrong){
             wrong = score;
                currentState = newState;
            }
            score = 0;
        }
        // west
        if (blankPos % 3 > 0){
            int[] newSequence = sequence.clone();
            newSequence[blankPos] = newSequence[blankPos - 1];
            newSequence[blankPos - 1] = 9;
            Puzzle8State newState = new Puzzle8State(newSequence);
            if (!Puzzle8State.equals(newState, this)){
                dfs.push(newState);
            }
            for (int i = 0; i < newState.sequence.length; i++) {
                if (newState.sequence[i] != i+1)
                    if (newState.sequence[i] != 9)
                        score++;
            }
            System.out.println("Pushing west "+score);
            System.out.println(newState.toString());
            if (score < wrong){
             wrong = score;
                currentState = newState;
            }
            score = 0;
        }
        dfs.push(currentState);
    }

    public List<Puzzle8State> generateNextMove(Puzzle8State currentState) {
        List<Puzzle8State> nextMoves = new ArrayList<>();
        int[] currentSequence = currentState.sequence;
        int blankPos = findBlankPositionInSequence(currentSequence);
        // south
        if (blankPos + 3 < size * size) {
            nextMoves.add(swapAndReturnNewState(currentSequence, blankPos, blankPos + 3));
        }
        // north
        if (blankPos - 3 >= 0) {
            nextMoves.add(swapAndReturnNewState(currentSequence, blankPos, blankPos - 3));
        }
        // east
        if (blankPos % 3 < 2) { 
            nextMoves.add(swapAndReturnNewState(currentSequence, blankPos, blankPos + 1));
        }
        // west
        if (blankPos % 3 > 0) { 
            nextMoves.add(swapAndReturnNewState(currentSequence, blankPos, blankPos - 1));
        }
        return nextMoves;
    }
    
    private Puzzle8State swapAndReturnNewState(int[] sequence, int blankPos, int targetPos) {
        int[] clonedSequence = sequence.clone();
        int temp = clonedSequence[blankPos];
        clonedSequence[blankPos] = clonedSequence[targetPos];
        clonedSequence[targetPos] = temp;
    
        return new Puzzle8State(clonedSequence);
    }
    
    private int findBlankPositionInSequence(int[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] == 9) {
                return i;
            }
        }
        return -1; 
    }

    private int popCount = 0;

    public void nextMoveWithStack() {
        Puzzle8State initialState = new Puzzle8State(sequence);
        dfs.push(initialState);
        
        boolean foundGoal = false;
        
        while(!dfs.isEmpty()) {
            Puzzle8State currentState = dfs.pop();
            popCount++;

            System.out.println("number of pop invocation = " + popCount + " stack size = " + dfs.size() + " explored size = " + explored.size());
        
            if(isGoal(currentState)) {
                System.out.println("from isGoal [" + Arrays.toString(currentState.sequence) + "] found goal [" + Arrays.toString(currentState.sequence) + "] let's terminate the loop");
                foundGoal = true;
                break;
            }
        
            for(Puzzle8State nextState : generateNextMove(currentState)) {
                if(isGoal(nextState)) { 
                    System.out.println("from nextState [" + Arrays.toString(nextState.sequence) + "] found goal [" + Arrays.toString(nextState.sequence) + "] let's terminate the loop");
                    foundGoal = true; 
                    break; 
                }
                if(!explored.contains(nextState)) {
                    explored.add(nextState);
                    dfs.push(nextState);
                }
            }
            if(foundGoal) { 
                break;
            }
        }
        if (dfs.isEmpty() && !foundGoal) {
            System.out.println("no solution");
        }
    }
    
    private boolean isGoal(Puzzle8State state) {
        return Arrays.equals(state.sequence, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    }
    
}