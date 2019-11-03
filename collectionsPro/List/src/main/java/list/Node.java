package list;

public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public boolean hasCycle(Node first) {
        boolean res = false;
        if (first != null) {
            Node<T> stepOne = first;
            Node<T> stepTwo = moveFromCurrentElementToNSteps(first, 1);
            while (stepTwo != null) {
                if (stepOne == stepTwo) {
                    res = true;
                    break;
                }
                stepOne = moveFromCurrentElementToNSteps(stepOne, 1);
                stepTwo = moveFromCurrentElementToNSteps(stepTwo, 2);
            }
        }
        return res;
    }

    private Node<T> moveFromCurrentElementToNSteps(Node<T> current, int countOfSteps) {
        Node<T> result = null;
        if (current != null) {
            result = current;
            for (int i = 0; i < countOfSteps; i++) {
                result = result.next;
                if (result == null) {
                    break;
                }
            }
        }
        return result;
    }
}
