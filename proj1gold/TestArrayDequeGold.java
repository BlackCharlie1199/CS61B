import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class TestArrayDequeGold {

    @Test
    public void testRemoveLastFirst() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StringBuilder errorMessage = new StringBuilder();

        for (int i = 0; i < 1000; i++) {
            student.addFirst(i);
            solution.addFirst(i);
            errorMessage.append("addFirst()\n");
        }

        Integer expect, actual;
        for(int i = 0; i < 998; i++) {
            double chance = StdRandom.uniform();
            if (chance < 0.5) {
                solution.removeLast();
                student.removeLast();
                errorMessage.append("removeLast()\n");
            } else {
                solution.removeFirst();
                student.removeFirst();
                errorMessage.append("removeFirst()\n");
            }
        }
        expect = solution.removeLast();
        actual = student.removeLast();
        assertEquals(errorMessage.toString(), expect, actual);
    }

}