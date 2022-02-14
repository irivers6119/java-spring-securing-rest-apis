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
		User user = new User("user", "{bcrypt}$2a$10$TvWbsgPItnpVnUJ9y9WEmeWz.DXNMlacqPuJHytiW.ZHHN74dc.he");
		user.grantAuthority("resolution:read");
		user.grantAuthority("resolution:write");
		this.users.save(user);


		User hasread = new User();
		hasread.setUsername("hasread");
		hasread.setPassword("{bcrypt}$2a$10$TvWbsgPItnpVnUJ9y9WEmeWz.DXNMlacqPuJHytiW.ZHHN74dc.he");
		hasread.grantAuthority("resolution:read");
		this.users.save(hasread);

		User haswrite = new User();
		haswrite.setUsername("haswrite");
		haswrite.setPassword("{bcrypt}$2a$10$TvWbsgPItnpVnUJ9y9WEmeWz.DXNMlacqPuJHytiW.ZHHN74dc.he");
		haswrite.grantAuthority("resolution:write");
		this.users.save(haswrite);
	}
}
