import classes.Menu;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JsfTemplateDownloader {
    private static final String ZIP_URL = "https://github.com/eliezerBrasilian/jsf-template/releases/download/v1/jsfapp.zip";
    private static final String ZIP_FILE_NAME = "jsfapp.zip";
    private static final String OUTPUT_DIR = "jsf-template";

    public static void main(String[] args) {
        var s = new Scanner(System.in);

        Menu.exibirBanner();

        System.out.println("Do you want to proceed with downloading the template (Y/N)");
        String procced = s.nextLine().trim();

        if(!procced.equalsIgnoreCase("Y")){
            System.out.println("Operation canceled by user");
            System.exit(0);
        }

        System.out.println("Downloading Template...");
        if (!downloadZipFile(ZIP_URL,
                ZIP_FILE_NAME)) {
            System.out.println("Error downloading Template.");
            System.exit(1);
        }

        System.out.println("Extracting Template...");

        var currentDirectory = System.getProperty("user.dir");

        if (!extractZip(new File(ZIP_FILE_NAME), new File(currentDirectory))) {
            System.out.println("Error extracting template.");
            System.exit(1);
        }

        System.out.println("Template downloaded and extracted successfully!");
    }

    private static boolean downloadZipFile(String fileURL, String fileName) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileURL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean extractZip(File zipFile, File destDir) {
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                File outFile = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    outFile.mkdirs();
                } else {
                    try (FileOutputStream out = new FileOutputStream(outFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipIn.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                    }
                }
                zipIn.closeEntry();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}