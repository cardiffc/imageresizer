import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Resizer extends Thread {

    private File[] files;
    int newWidth;
    String dstFolder;

    public Resizer(File[] files, int newWidth, String dstFolder) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
    }
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try
        {
            for (File file1 : files)
            {
                BufferedImage image = ImageIO.read(file1);
                if (image == null) {
                    continue;
                }
                BufferedImage resizedImage = Scalr.resize(image, Scalr.Method.SPEED, newWidth * 2);
                resizedImage = Scalr.resize(resizedImage,Scalr.Method.QUALITY, newWidth);
                File newFile = new File(dstFolder + "/" + file1.getName());
                ImageIO.write(resizedImage, "jpg", newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
