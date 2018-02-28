package com.webshop.service;

import java.util.List;

import com.webshop.entity.Address;

/**
 * @author 黄勇康
 * @date   2017
 */
public interface AddressService {

	/**
	 * @param userId
	 * @return
	 */
	List<Address> getAddressByUserId(String userId);
	
	/**
	 * @param add
	 * @return
	 */
	public Address getAddressByUserIdAndAddress(String uid, String add);

	/**
	 * @param address
	 */
	public boolean insertAddress(Address address);

}
