package c05encoder;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

public class PasswordTest extends BaseTest {

	@Test
	public void testPasswordServiceWithMyRealm() {
		login("classpath:c05encoder/shiro-passwordservice.ini", "wu", "123");
	}

	@Test
	public void testPasswordServiceWithJdbcRealm() {
		login("classpath:c05encoder/shiro-jdbc-passwordservice.ini", "wu", "123");
	}

	@Test
	public void testGeneratePassword() {
		String algorithmName = "md5";
		String username = "liu";
		String password = "123";
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;

		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println(salt2);
		System.out.println(encodedPassword);
	}

	@Test
	public void testHashedCredentialsMatcherWithMyRealm2() {
		//使用testGeneratePassword生成的散列密码
		login("classpath:c05encoder/shiro-hashedCredentialsMatcher.ini", "liu", "123");
	}

	@Test
	public void testHashedCredentialsMatcherWithJdbcRealm() {

		BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);

		//使用testGeneratePassword生成的散列密码
		login("classpath:c05encoder/shiro-jdbc-hashedCredentialsMatcher.ini", "liu", "123");
	}


	private class EnumConverter extends AbstractConverter {
		@Override
		protected String convertToString(final Object value) {
			return ((Enum) value).name();
		}

		@Override
		protected Object convertToType(final Class type, final Object value) {
			return Enum.valueOf(type, value.toString());
		}

		@Override
		protected Class getDefaultType() {
			return null;
		}

	}

	@Test(expected = ExcessiveAttemptsException.class)
	public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
		for (int i = 1; i <= 5; i++) {
			try {
				login("classpath:c05encoder/shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
			} catch (Exception e) {/*ignore*/}
		}
		login("classpath:c05encoder/shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
	}
}