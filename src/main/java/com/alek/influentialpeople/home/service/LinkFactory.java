package com.alek.influentialpeople.home.service;

import com.alek.influentialpeople.model.Link;

public interface LinkFactory {
	
	Link getLink(String url,String rel);

}
