package c06realm.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

	@Override
	public String getName() {
		return "a";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		return new SimpleAuthenticationInfo(
				"zhang",
				"123",
				getName()
		);
	}
}
