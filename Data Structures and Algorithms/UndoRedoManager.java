package linkedlist;

/**
 * Implement an application that support undo/redo functionality. Use a linked list to maintain a sequence of states
 * Each state change is stored as a node in the list, allowing for the easy navigation.
 * 1<>2<>3<>4<>5
 */


public class UndoRedoManager<T> {
    private class Node {
        private T state;
        private Node next;
        private Node previous;

        private Node (T state) {
            this.state = state;
        }
    }

    private Node currentState;

    //Undo Operation
    public T Undo(){
        if (currentState == null) {
            System.out.println("No state to undo");
            return null;
        }
        Node previousState = currentState.previous;
        if (previousState == null) {
            System.out.println("Reached the initial start state");
        } else {
            currentState = previousState;
        }
        return currentState.state;
    }

    //Perform an operation
    public void addState(T newState) {
        // Lets create a new for the new task
        Node newNode = new Node(newState);

        //Setting the link for the new node
        newNode.previous = currentState;
        newNode.next = null;

        //Updating the next link for the current state
        if (currentState != null) {
            currentState.next = newNode;
        }
        //updating the current to the new state
        currentState = newNode;

    }

    // ASSIGNMENT: Implement the Redo Method.
    public T Redo() {
        // Check if there is a current state to redo
        if (currentState == null) {
            System.out.println("No state to redo");
            return null;
        }
        // Get the next state from the current state
        Node nextState = currentState.next;
        
        if (nextState == null) {
            System.out.println("Reached the latest state");
        } else {
            currentState = nextState;
        }
        return currentState.state;
    }

        
    public void displayAllStates() {

        Node tempNode = currentState;

        // Move backward to the first node
        while (tempNode != null && tempNode.previous != null) {
            tempNode = tempNode.previous;
        }

        // Display all states from first to last
        System.out.print("All States: [");

        while (tempNode != null) {
            System.out.print(tempNode.state);

            if (tempNode.next != null) {
                System.out.print(", ");
            }

            tempNode = tempNode.next;
        }

        System.out.println("]");
    }

    public static void main(String[] args) {
        UndoRedoManager<String> undoRedoManager = new UndoRedoManager<>();
        undoRedoManager.addState("Wake up from bed");
        undoRedoManager.addState("Brush Teeth");
        undoRedoManager.addState("Take a Shower");
        undoRedoManager.addState("Go to Class");

        undoRedoManager.displayAllStates();
        System.out.println("");

        System.out.println("Current State: " + undoRedoManager.currentState.state);
        undoRedoManager.Undo();
        System.out.println("Current State: " + undoRedoManager.currentState.state);
        undoRedoManager.Undo();
        System.out.println("Current State: " + undoRedoManager.currentState.state);
        undoRedoManager.Undo();
        System.out.println("Current State: " + undoRedoManager.currentState.state);
        undoRedoManager.Undo();
        System.out.println("Current State: " + undoRedoManager.currentState.state);

    }

}
