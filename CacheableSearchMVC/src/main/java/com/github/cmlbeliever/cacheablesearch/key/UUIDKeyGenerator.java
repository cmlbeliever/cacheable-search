package com.github.cmlbeliever.cacheablesearch.key;

import java.util.UUID;

public class UUIDKeyGenerator implements KeyGenerator {

	@Override
	public String generateKey() {
		return UUID.randomUUID().toString();
	}

}
