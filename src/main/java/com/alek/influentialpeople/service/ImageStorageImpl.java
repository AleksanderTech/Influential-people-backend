package com.alek.influentialpeople.service;

import java.io.File;
import java.io.InputStream;

import com.alek.influentialpeople.service.storage.ImageStorage;

public class ImageStorageImpl implements ImageStorage{

	@Override
	public String getPath(String username) {
		return null;
	}

	@Override
	public boolean saveImage(byte[] image) {
		return false;
	}

	@Override
	public byte[] getBytes(InputStream imageStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImageName(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
