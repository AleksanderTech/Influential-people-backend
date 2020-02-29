package com.alek.influentialpeople.common;

import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.EmptyFileException;
import com.alek.influentialpeople.exception.exceptions.StorageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageManager {

    private Properties properties;
    private static final String IMAGE_FORMAT = "jpg";
    private static final String AVATAR = "avatar";
    private static final String PRIME = "prime";

    public ImageManager(Properties properties) {
        this.properties = properties;
    }

    public byte[] getImage(String path) {
        File imageFile = new File(path);
        try {
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
            return imageBytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void storeImage(String path, MultipartFile image) {
        if (image.isEmpty()) {
            throw new EmptyFileException(ExceptionMessages.EMPTY_FILE_EXCEPTION);
        }
        try (BufferedOutputStream stream =
                     new BufferedOutputStream(new FileOutputStream(new File(path), false))) {
            byte[] imageBytes = image.getBytes();
            stream.write(imageBytes);

        } catch (IOException e) {
            throw new StorageException(ExceptionMessages.FILE_STORAGE_FAIL_MESSAGE, e);
        }
    }

    public void storeImage(ImageType imageType, String name, MultipartFile image) {
        if (image == null) {
            throw new EmptyFileException(ExceptionMessages.EMPTY_FILE_EXCEPTION);
        }
        String path = createPath(imageType, name);
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedOutputStream stream =
                     new BufferedOutputStream(new FileOutputStream(new File(path + File.separatorChar + createFileName(name)), false))) {
            byte[] imageBytes = image.getBytes();

            stream.write(imageBytes);
        } catch (IOException e) {
            throw new StorageException(ExceptionMessages.FILE_STORAGE_FAIL_MESSAGE, e);
        }
    }

    private String createFileName(String name) {
        return name.replace(" ", "_") + "." + IMAGE_FORMAT;
    }

    public String createPath(ImageType imageType, String name) {
        String path = null;
        if (imageType.equals(ImageType.USER)) {
            String modifiedUsername = name.replace(" ", "_");
            path = properties.getConfig("users.images.path") + File.separatorChar + modifiedUsername + File.separatorChar + AVATAR;
            System.out.println(path + " result path");
        } else if (imageType.equals(ImageType.HERO)) {
            String modifiedFullName = name.replace(" ", "_");
            path = properties.getConfig("heroes.images.path") + File.separatorChar + modifiedFullName + File.separatorChar + AVATAR;
            System.out.println(path + " result path hero");
        } else if (imageType.equals(ImageType.CATEGORY)) {
            String modifiedFullName = name.replace(" ", "_");
            path = properties.getConfig("category.images.path") + File.separatorChar + modifiedFullName + File.separatorChar + PRIME;
            System.out.println(path + " result path category");
        }
        return path;
    }

    public String createUrl(ImageType imageType, String name) {
        name = name.replace(" ", "%20");
        String url = null;
        if (imageType.equals(ImageType.USER)) {
            url = properties.getConfig("server.url") + Urls.USER + File.separatorChar + name + Urls.IMAGE;
        } else if (imageType.equals(ImageType.HERO)) {
            url = properties.getConfig("server.url") + Urls.HERO + File.separatorChar + name + Urls.IMAGE;
        } else if (imageType.equals(ImageType.CATEGORY)) {
            url = properties.getConfig("server.url") + Urls.CATEGORY + File.separatorChar + name + Urls.IMAGE;
        }
        return url;
    }

    public static String createUrl(String imagePath, String serverUrl, ImageType type, String id) {
        if (imagePath != null) {
            id = id.replace(" ", "%20");
            String url = "";
            switch (type) {
                case USER: {
                    url = serverUrl + Urls.USER + "/" + id + Urls.IMAGE;
                    break;
                }
                case HERO: {
                    url = serverUrl + Urls.HERO + "/" + id + Urls.IMAGE;
                    break;
                }
                case CATEGORY: {
                    url = serverUrl + Urls.CATEGORY + "/" + id + Urls.IMAGE;
                    break;
                }
            }
            return url;
        }
        return null;
    }

    public String appendImageName(String name, String path) {
        return path + File.separatorChar + createFileName(name);
    }

}
