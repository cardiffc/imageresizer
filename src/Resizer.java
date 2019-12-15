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
            int tempNewWidth = newWidth * 2;
            for (File file1 : files)
            {
                BufferedImage image = ImageIO.read(file1);
                if (image == null) {
                    continue;
                }
                int tempNewHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) tempNewWidth));
                BufferedImage tempImage = new BufferedImage(tempNewWidth, tempNewHeight, BufferedImage.TYPE_INT_RGB);
                int tempWidthStep = image.getWidth() / tempNewWidth;
                int tempHeightStep = image.getHeight() / tempNewHeight;
                for (int x = 0; x < tempNewWidth ; x++) {
                    for (int y = 0; y < tempNewHeight; y++) {
                        int rgb = image.getRGB(x * tempWidthStep , y * tempHeightStep );
                        tempImage.setRGB(x, y, rgb);
                    }

                }
                BufferedImage imgForResize = tempImage;
                int newHeight = (int) Math.round(
                        imgForResize.getHeight() / (imgForResize.getWidth() / (double) newWidth)
                );
                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                int widthStep = imgForResize.getWidth() / newWidth  ;
                int heightStep = imgForResize.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++)
                {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = imgForResize.getRGB(x * widthStep , y * heightStep );
                        resizedImage.setRGB(x, y, rgb);
                    }
                }
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
