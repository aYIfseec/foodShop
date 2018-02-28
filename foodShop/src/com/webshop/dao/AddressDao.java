package com.webshop.dao;

import java.util.List;

import com.webshop.entity.Address;

public interface AddressDao {
    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

	/**
	 * @param userId
	 * @return
	 */
	List<Address> selectByUserId(String userId);

	/**
	 * @param add
	 * @return
	 */
	Address selectByAddress(String uid, String add);

	/**
	 * @param address
	 * @return
	 */
	boolean insertAddress(Address address);
}