import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File okr = new File(args[0]);
        File dot = new File(args[1]);
        StringBuilder okrB = new StringBuilder();
        StringBuilder dotsB = new StringBuilder();

        try (FileReader readerOkr = new FileReader(okr)) {

            int c;
            while ((c = readerOkr.read()) != -1){
                okrB.append((char) c);

            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        try (FileReader readerDot = new FileReader(dot)) {
            int c;
            while ((c = readerDot.read()) != -1){
                dotsB.append((char) c);
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        okrB.trimToSize();
        dotsB.trimToSize();

        String[] okrStrings = Arrays.stream(new String(okrB).split("\n")).toArray(String[]::new);
        String[] dotsStrings = Arrays.stream(new String(dotsB).split("\n")).toArray(String[]::new);

        float xOkr = Float.parseFloat(okrStrings[0].split(" ")[0]);
        float yOkr = Float.parseFloat(okrStrings[0].split(" ")[1]);
        float radius = Float.parseFloat(okrStrings[1]);

        for (int i = 0; i < dotsStrings.length; i++) {
            float x = Float.parseFloat(dotsStrings[i].split(" ")[0]);
            float y = Float.parseFloat(dotsStrings[i].split(" ")[1]);

            if (Math.pow((x - xOkr), 2) + Math.pow((y - yOkr), 2) < Math.pow(radius, 2)) {
                System.out.println(1);
            } else if (Math.pow((x - xOkr), 2) + Math.pow((y - yOkr), 2) == Math.pow(radius, 2)) {
                System.out.println(0);
            } else {
                System.out.println(2);

            }

        }
    }
}