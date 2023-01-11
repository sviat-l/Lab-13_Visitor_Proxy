import org.junit.Test;
import task1_visitor.Group;
import org.junit.jupiter.api.Assertions;
import task1_visitor.Signature;
import java.util.concurrent.atomic.AtomicReference;

public class TestVisitor {
    AtomicReference<Integer> example = new AtomicReference<>(0);

    @Test
    public void testVisitor(){

        Group<Integer> nestedGroup = new Group<>();
        nestedGroup.addTask(new Signature<>(System.out::println)).addTask(new Signature<>(x -> System.out.println(x + 111)));
        nestedGroup.apply(10);

        Assertions.assertThrows(NullPointerException.class, () -> nestedGroup.getHeader("stamping"));

        Group<Integer> group = new Group<>();
        group.addTask(nestedGroup).addTask(new Signature<>(x -> example.set(x*x*x))).addTask(new Signature<>(x -> example.set(x*x)));
        group.apply(9);

        Assertions.assertEquals(example.get(), 81);


        Group<Integer> groupSet = new Group<>();
        groupSet.addTask(new Signature<>(x -> example.set(x+10)));
        groupSet.apply(90);

        Assertions.assertEquals(100, example.get());
    }
}
