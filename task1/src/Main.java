public class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int i = 1;
        StringBuilder stringBuilder = new StringBuilder("1");

        while (true) {
            i = 1 + (i + m - 2) % n;

            if (i != 1) {
                stringBuilder.append(i);
            } else {
                break;
            }
        }
        System.out.println(stringBuilder);
    }
}