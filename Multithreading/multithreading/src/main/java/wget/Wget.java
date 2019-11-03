package wget;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Wget implements Runnable {
    private String url;
    private String fileDestination;
    private double speed;

    @Override
    public void run() {
        download(url, speed, fileDestination);
    }

    private void download(String url, double speed, String fileDestination) {
        try {
            URL urlOfSite = new URL(url);
            HttpURLConnection connection =
                    (HttpURLConnection) urlOfSite.openConnection();
            try (BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());
                 BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(fileDestination))) {
                byte[] buffer = new byte[1024 * 100];
                long startTime = System.currentTimeMillis();
                int countOfByte = reader.read(buffer);
                while (countOfByte >= 0) {
                    output.write(buffer, 0, countOfByte);
                    long endTime = System.currentTimeMillis();
                    long actualPeriod = endTime - startTime;
                    double expectedPeriod = countOfByte  / speed;
                    if (actualPeriod < expectedPeriod) {
                        Thread.currentThread().sleep((long) (expectedPeriod - actualPeriod) + 1);
                    }
                    startTime = System.currentTimeMillis();
                    countOfByte = reader.read(buffer);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFileDestination(String fileDestination) {
        this.fileDestination = fileDestination;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public static void main(String[] args) {
        Wget wget = new Wget();
        wget.setUrl("https://yandex.ru");
        wget.setSpeed(200);
        wget.setFileDestination("yandex.html");
        new Thread(wget).start();
    }
}
