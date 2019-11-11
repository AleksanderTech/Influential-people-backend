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
public class ImageService {

    private Properties properties;
    private static final String IMAGE_FORMAT = "jpg";
    private static final String AVATAR = "avatar";

    public ImageService(Properties properties) {
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

    public void storeImage(String path, String fullName, MultipartFile image) {

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

    public void storeImage(String fullName, MultipartFile image) {

        if (image == null) {
            throw new EmptyFileException(ExceptionMessages.EMPTY_FILE_EXCEPTION);
        }
        String path = createHeroAvatarPath(fullName);
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedOutputStream stream =
                     new BufferedOutputStream(new FileOutputStream(new File(path + File.separatorChar + createHeroAvatarName(fullName)), false))) {
            byte[] imageBytes = image.getBytes();

            stream.write(imageBytes);
        } catch (IOException e) {
            throw new StorageException(ExceptionMessages.FILE_STORAGE_FAIL_MESSAGE, e);
        }
    }

    public String createHeroAvatarPath(String fullName) {

        String modifiedFullName = fullName.replace(" ", "_");
        String path = properties.getConfig("heroes.images.path") + File.separatorChar + modifiedFullName + File.separatorChar + AVATAR;
        return path;
    }

    public String createHeroAvatarName(String fullName) {

        return fullName.replace(" ", "_") + "." + IMAGE_FORMAT;
    }

    public String createUserAvatarPath(String username) {

        String modifiedUsername = username.replace(" ", "_");
        String path = properties.getConfig("users.images.path") + File.separatorChar + modifiedUsername + File.separatorChar + AVATAR;
        return path;
    }

    public String createHeroAvatarUrl(String fullName) {

        String url = Urls.ROOT_URL + Urls.HERO + File.separatorChar + fullName + Urls.IMAGE;
        return url;
    }

    public String createUserAvatarUrl(String username) {

        String url = Urls.ROOT_URL + Urls.USER + File.separatorChar + username + Urls.IMAGE;
        return url;
    }

    public String appendImageName(String name, String path) {
        return path + File.separatorChar + createHeroAvatarName(name);
    }
}
