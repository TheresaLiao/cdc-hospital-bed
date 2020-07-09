package org.itri.view.login.dao;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.itri.view.admin.dao.LoginControllerHibernateImpl;
import org.itri.view.humanhealth.hibernate.PatientInfo;
import org.itri.view.login.User;
import org.itri.view.login.UserInfoService;

public class UserInfoServiceHibernateImpl implements UserInfoService, Serializable {
	private static final long serialVersionUID = 1L;

	private LoginControllerHibernateImpl hqe;

	public synchronized User findUser(String username, String pd) {
		System.out.println("findUser");
		User user = new User(username, pd);

		hqe = new LoginControllerHibernateImpl();

		// sha256
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(pd.getBytes("utf8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		String hashPd = String.format("%064x", new BigInteger(1, digest.digest()));

		// check user
		PatientInfo patientInfo = hqe.getPatientInfo(username, hashPd);
		if (patientInfo == null) {
			System.out.println("can't find user");
			return null;
		}

		long id = patientInfo.getPatient().getPatientId();
		user.setPatientId(id);
		return user;
	}

//	public User updateUser(User user) {
//		return null;
//	}
}
