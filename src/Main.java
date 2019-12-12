import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class Main
{

    public static void main(String[] args)
    {
        String srcFolder = "/home/cardiff/VIDEOS/G9";
        String dstFolder = "/home/cardiff/VIDEOS/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int vCPUCount = Runtime.getRuntime().availableProcessors();
        int filesInThread = files.length / vCPUCount;
        int filesLastThread = filesInThread + files.length % vCPUCount;
        int index = 0;

        for (int i = 0; i < vCPUCount ; i++) {
            if (i != vCPUCount - 1) {
               // File[] thread = Arrays.copyOfRange(File[] i, i+ filesInThread);
                System.out.println(index + filesInThread);
                index += filesInThread;
                //System.out.println(filesInThread);
            } else {
               // System.out.println(index);
             //   System.out.println(index + filesLastThread);
                //System.out.println(filesLastThread);
            }
        }




//        try
//        {
//            for(File file : files)
//            {
//                BufferedImage image = ImageIO.read(file);
//                if(image == null) {
//                    continue;
//                }
//
//                int newWidth = 300;
//                int newHeight = (int) Math.round(
//                        image.getHeight() / (image.getWidth() / (double) newWidth)
//                );
//                BufferedImage newImage = new BufferedImage(
//                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB
//                );
//
//                int widthStep = image.getWidth() / newWidth;
//                int heightStep = image.getHeight() / newHeight;
//
//                for (int x = 0; x < newWidth; x++)
//                {
//                    for (int y = 0; y < newHeight; y++) {
//                        int rgb = image.getRGB(x * widthStep, y * heightStep);
//                        newImage.setRGB(x, y, rgb);
//                    }
//                }
//
//                File newFile = new File(dstFolder + "/" + file.getName());
//                ImageIO.write(newImage, "jpg", newFile);
//            }
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
