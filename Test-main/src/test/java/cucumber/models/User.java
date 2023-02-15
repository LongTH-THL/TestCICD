package cucumber.models;

import lombok.Data;

@Data
public class User extends BaseModel {
	/**
	 * Tên đăng nhập
	 */
	String email;
	/**
	 * Mật khẩu
	 */
	String password;
	
	/**
	 * Đối tượng định nghĩa thông tin user sử dụng để đăng nhập vào hệ thống
	 * @param email Tên đăng nhập
	 * @param password Mật khẩu
	 */
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

}
