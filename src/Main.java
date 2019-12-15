import java.io.File;

public class Main
{
    private static int newWidth = 300;
    private static String srcFolder = "/home/cardiff/VIDEOS/G9";
    private static String dstFolder = "/home/cardiff/VIDEOS/dst";
    public static void main(String[] args)
    {
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        int vCPUCount = Runtime.getRuntime().availableProcessors() / 2; // Кол-во CPU доступных JVM. /2 в силу HT
        int filesInThread = files.length / vCPUCount;
        int srcFromIndex = 0;

        for (int i = 0; i < vCPUCount ; i++) {
            filesInThread = (i < vCPUCount - 1) ? filesInThread : filesInThread + (files.length % vCPUCount);
            File[] files1 = new File[filesInThread];
            System.arraycopy(files, srcFromIndex, files1,0,filesInThread);
            Resizer resizer = new Resizer(files1,newWidth, dstFolder);
            resizer.start();
            srcFromIndex += filesInThread;
        }
    }
}
