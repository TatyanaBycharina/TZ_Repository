import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
        File arrayFile = new File(args[0]);
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader reader = new FileReader(arrayFile)) {

            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);

            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        stringBuilder.trimToSize();

        String[] strings = Arrays.stream(new String(stringBuilder).split("\n")).toArray(String[]::new);

        int[] numbers = new int[strings.length];

        for (int i = 0; i < strings.length; i++) {
            numbers[i] = Integer.parseInt(strings[i].trim());
        }

        System.out.println(steps(numbers));
    }
    public static int steps(int[] nums) {
        Arrays.sort(nums);
        int mid = nums.length/2;
        int count = 0;
        for (int i = 0; i < mid; i ++) {
            int num = nums[mid] - nums[i];
            count += num;
        }
        for (int j = mid + 1; j < nums.length; j ++) {
            int num = nums[j] - nums[mid];
            count += num;
        }
        return count;
    }
}