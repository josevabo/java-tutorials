import java.util.Optional;

public class OptionalTests {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("foo");
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("bar"));


        Optional<String> optional2 = Optional.empty();
        System.out.println(optional2.isPresent());
        // System.out.println(optional2.get());
        System.out.println(optional2.orElse("bar"));
        System.out.println(optional2.orElse("tipo será string").getClass().getName());
    }
}
