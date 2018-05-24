package org.monet.core.crypto.ed25519;

import org.monet.core.crypto.*;

/**
 * Implementation of the key analyzer for Ed25519.
 */
public class Ed25519KeyAnalyzer implements KeyAnalyzer {
	private static final int COMPRESSED_KEY_SIZE = 32;

	@Override
	public boolean isKeyCompressed(final PublicKey publicKey) {
		return COMPRESSED_KEY_SIZE == publicKey.getRaw().length;
	}
}
