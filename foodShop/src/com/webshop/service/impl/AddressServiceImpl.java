package com.webshop.service.impl;

import java.util.List;

import com.webshop.dao.AddressDao;
import com.webshop.dao.impl.AddressDaoImpl;
import com.webshop.entity.Address;
import com.webshop.service.AddressService;

/**
 * @author 黄勇康
 * @date   2017
 */
public class AddressServiceImpl implements AddressService {

	private AddressDao addressDao = new AddressDaoImpl();
	
	public List<Address> getAddressByUserId(String userId) {
		return addressDao.selectByUserId(userId);
	}

	public Address getAddressByUserIdAndAddress(String uid, String add) {
		return addressDao.selectByAddress(uid, add);
	}

	public boolean insertAddress(Address address) {
		return addressDao.insertAddress(address);
	}

	

}
