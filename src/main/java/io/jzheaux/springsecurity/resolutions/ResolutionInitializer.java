package io.jzheaux.springsecurity.resolutions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResolutionInitializer implements SmartInitializingSingleton {
	private final ResolutionRepository resolutions;
	private final UserRepository users;

	public ResolutionInitializer(ResolutionRepository resolutions, UserRepository users) {
		this.resolutions = resolutions;
		this.users = users;
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.resolutions.save(new Resolution("Read War and Peace", "user"));
		this.resolutions.save(new Resolution("Free Solo the Eiffel Tower", "user"));
		this.resolutions.save(new Resolution("Hang Christmas Lights", "user"));
		this.resolutions.save(new Resolution("Get AWS certification", "hasread"));
		this.resolutions.save(new Resolution("Visit Grandma", "haswrite"));

		User user = new User("user", "{bcrypt}$2a$10$TvWbsgPItnpVnUJ9y9WEmeWz.DXNMlacqPuJHytiW.ZHHN74dc.he");
		user.grantAuthority("resolution:read");
		user.grantAuthority("user:read");
		user.setFullName("User user");
		this.users.save(user);

		User hasRead = new User("hasread", "{bcrypt}$2a$10$TvWbsgPItnpVnUJ9y9WEmeWz.DXNMlacqPuJHytiW.ZHHN74dc.he");
		hasRead.grantAuthority("resolution:read");
		hasRead.grantAuthority("user:read");
		hasRead.setFullName("Has Read");
		this.users.save(hasRead);

		User hasWrite = new User("haswrite", "{bcrypt}$2a$10$TvWbsgPItnpVnUJ9y9WEmeWz.DXNMlacqPuJHytiW.ZHHN74dc.he");
		hasWrite.grantAuthority("resolution:write");
		hasWrite.setSubscription("premium");
		hasWrite.addFriend(hasRead);
		hasWrite.setFullName("Has Write");
		this.users.save(hasWrite);

		User admin = new User("admin","{bcrypt}$2a$10$TvWbsgPItnpVnUJ9y9WEmeWz.DXNMlacqPuJHytiW.ZHHN74dc.he");
		admin.grantAuthority("ROLE_ADMIN");
		admin.setFullName("Admin Adminson");
		this.users.save(admin);
	}
}
