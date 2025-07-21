import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class TestArrayDequeGold {

    @Test
    public void testRemoveLastFirst() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StringBuilder errorMessage = new StringBuilder();

        for(int i = 0; i < 100; i++) {
            double chance = StdRandom.uniform(0.0, 1.0);
            if (chance < 0.25) {
                student.addLast(4);
                solution.addLast(4);
                errorMessage.append("addLast()\n");
                Integer expect = solution.removeFirst();
                Integer actual = student.removeFirst();
                errorMessage.append("removeFirst()\n");
                assertEquals(errorMessage.toString(), expect, actual);
                student.addFirst(4);
                solution.addFirst(4);
                errorMessage.append("addFirst()\n");
            } else if (chance < 0.5) {
                student.addLast(8);
                solution.addLast(8);
                errorMessage.append("addLast()\n");
                Integer expect = solution.removeLast();
                Integer actual = student.removeLast();
                errorMessage.append("removeLast()\n");
                assertEquals(errorMessage.toString(), expect, actual);
            } else if (chance < 0.75) {
                student.addFirst(-100);
                solution.addFirst(-100);
                errorMessage.append("addFirst()\n");
                Integer expect = solution.removeFirst();
                Integer actual = student.removeFirst();
                errorMessage.append("removeFirst()\n");
                assertEquals(errorMessage.toString(), expect, actual);
            } else {
                student.addFirst(1);
                solution.addFirst(1);
                errorMessage.append("addFirst()\n");
                Integer expect = solution.removeLast();
                Integer actual = student.removeLast();
                errorMessage.append("removeLast()\n");
                assertEquals(errorMessage.toString(), expect, actual);
            }
        }

        /*
        for(int i = 0; i < solution.size(); i++) {
            boolean chance = StdRandom.bernoulli();

            if (chance) {
                Integer expect = solution.removeFirst();
                Integer actual = student.removeFirst();
                errorMessage.append("removeFirst()\n");
                assertEquals(errorMessage.toString(), expect, actual);
            } else {
                Integer expect = solution.removeLast();
                Integer actual = student.removeLast();
                errorMessage.append("removeLast()\n");
                assertEquals(errorMessage.toString(), expect, actual);
            }
        }

         */
    }
}