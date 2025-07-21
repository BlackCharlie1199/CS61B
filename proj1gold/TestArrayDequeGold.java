import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class TestArrayDequeGold {

    @Test
    public void testAddFirst() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        for(int i = 0; i < 10; i++) {
            student.addFirst(i);
            solution.addFirst(i);
        }

        for(int i = 0; i < 10; i++) {
            assertEquals(solution.removeFirst(), student.removeFirst());
        }
    }

    @Test
    public void testAddLast() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        for(int i = 0; i < 10; i++) {
            student.addLast(i);
            solution.addLast(i);
        }

        for(int i = 0; i < 10; i++) {
            assertEquals(solution.removeFirst(), student.removeFirst());
        }
    }

    @Test
    public void testAddLastFirst() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        for(int i = 0; i < 10; i++) {
            boolean randomChance = StdRandom.bernoulli((float)1 / 3); // The success possibilities is 1/2 by default.
            if (randomChance) {
                student.addFirst(i);
                solution.addFirst(i);
            } else {
                student.addLast(i);
                solution.addLast(i);
            }
        }

        for(int i = 0; i < 10; i++) {
            assertEquals(solution.removeFirst(), student.removeFirst());
        }
    }

    @Test
    public void testRemoveFirst() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        for(int i = 0; i < 10; i++) {
            boolean randomChance = StdRandom.bernoulli(); // The success possibilities is 1/2 by default.
            if (randomChance) {
                student.addFirst(i);
                solution.addFirst(i);
            } else {
                student.addLast(i);
                solution.addLast(i);
            }
            if (randomChance) {
                assertEquals(solution.removeLast(), student.removeLast());
            }
        }
        for(int i = 0; i < solution.size(); i++) {
            assertEquals(solution.removeLast(), student.removeLast());
        }
    }
    @Test
    public void testRemoveLast() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String errorMessage = "";

        for(int i = 0; i < 10; i++) {
                student.addLast(i);
                solution.addLast(i);
                errorMessage += "addLast()\n";
        }

        for(int i = 0; i < 10; i++) {
            errorMessage += "removeLast()\n";
            assertEquals(errorMessage, solution.removeLast(), student.removeLast());
        }
    }

    @Test
    public void testRemoveLastFirst() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String errorMessage = "";

        for(int i = 0; i < 10; i++) {
            boolean randomChance = StdRandom.bernoulli(); // The success possibilities is 1/2 by default.
            if (randomChance) {
                student.addFirst(i);
                solution.addFirst(i);
                errorMessage += "addLast()\n";
            } else {
                student.addLast(i);
                solution.addLast(i);
                errorMessage += "addLast()\n";
            }
        }

        for(int i = 0; i < 10; i++) {
            boolean randomChance = StdRandom.bernoulli((float) 1 / 5); // The success possibilities is 1/2 by default.

            if (randomChance) {
                errorMessage += "removeFirst()\n";
                assertEquals(errorMessage, solution.removeFirst(), student.removeFirst());
            } else {
                errorMessage += "removeLast()\n";
                assertEquals(errorMessage, solution.removeLast(), student.removeLast());
            }
        }
    }
}