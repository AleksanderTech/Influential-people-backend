package com.alek.influentialpeople.home.service.storage;

import java.io.InputStream;

public interface ImageStorage {
	
	String getPath(String username);

	boolean saveImage(byte[] image);

	byte[] getBytes(InputStream imageStream);
	
	String getImageName(long id);
	
}
