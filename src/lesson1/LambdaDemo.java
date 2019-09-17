package lesson1;

import java.util.stream.Stream;

public class LambdaDemo {

    public static void main(String[] args) {
        Action a = () -> {

        };
    }

    // 显式指定接口为函数式接口（只含有一个方法的接口）
    @FunctionalInterface
    public interface Action {
        void execute();

        //
        default void doExecute() {
            execute();
        }
    }

    private static void stream() {
        Stream.of(1, 2, 3, 4, 5)
              .map(String::valueOf);
    }
}
