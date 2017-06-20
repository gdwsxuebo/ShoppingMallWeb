package com.gws.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gws.dao.impl.XfTillidDao;
@Service
public class xfTillidService {
	@Resource XfTillidDao xfTillidDao;
	public List<Map<String, Object>> getAllXfTillid(){
		return xfTillidDao.getAllXfTillid();
	}
}
