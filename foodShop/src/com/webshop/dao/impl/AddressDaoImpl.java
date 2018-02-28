package com.webshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.webshop.dao.AddressDao;
import com.webshop.entity.Address;

/**
 * @author 黄勇康
 * @date   2017
 */
public class AddressDaoImpl implements AddressDao {

	public int deleteByPrimaryKey(Integer addressId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Address record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Address record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Address selectByPrimaryKey(Integer addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Address record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Address record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Address> selectByUserId(String userId) {
		String sql = "select * from t_address where userId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userId);
		List<Address> addressList = null;
		try {
			addressList = BaseDao.operQuery(sql, parameters, Address.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addressList;
	}

	public Address selectByAddress(String uid, String add) {
		String sql = "select * from t_address where address = ? and userId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(add);
		parameters.add(uid);
		List<Address> addressList = null;
		try {
			addressList = BaseDao.operQuery(sql, parameters, Address.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addressList.size() == 0 ? null : addressList.get(0);
	}

	public boolean insertAddress(Address address) {
		String sql = "insert into t_address(addressId, userId, address) values(?, ?, ?)";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(address.getAddressId());
		parameters.add(address.getUserId());
		parameters.add(address.getAddress());
		return BaseDao.operUpdate(sql, parameters);
	}

}
