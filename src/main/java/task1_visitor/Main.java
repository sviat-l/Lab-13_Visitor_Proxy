package task1_visitor;

public class Main {
    public static void main(String[] args) {

        Group<Integer> nestedGroup = new Group<>();
        nestedGroup.addTask(new Signature<>(System.out::println)).addTask(new Signature<>(x -> System.out.println(x * x)));
        nestedGroup.apply(10);


        Group<Integer> group = new Group<>();
        group.addTask(nestedGroup).addTask(new Signature<>(x -> System.out.println(x * x * x))).addTask(new Signature<>(x -> System.out.println(x * (-1))));
        group.apply(9);

        Group<String> groupStr = new Group<>();
        groupStr.addTask(new Signature<>(System.out::println)).addTask(new Signature<>(x -> System.out.println(x+"|" + x)));
        groupStr.apply("repeat");
    }
}
