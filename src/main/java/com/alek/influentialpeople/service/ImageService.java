package com.alek.influentialpeople.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	public byte[] getImageBytes(String absoluteHeroImagePath) {

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			File imageFile = new File(absoluteHeroImagePath);
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			ImageIO.write(bufferedImage, "jpg", baos);
			baos.flush();
			byte[] imageBytes = baos.toByteArray();
			baos.close();
			return imageBytes;
		} catch (IOException exception) {
			return null;
		}
	}
}
