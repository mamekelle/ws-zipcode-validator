import javax.validation.constraints.NotNull;

public class Main {
    @NotNull(message = "{null.message}")
    static String str;
    public static void main(String[] args) {

        System.out.println(str);
    }

}
