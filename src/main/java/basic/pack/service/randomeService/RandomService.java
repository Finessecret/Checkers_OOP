package basic.pack.service.randomeService;

public class RandomService {
    public static int interval(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
