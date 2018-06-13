package org.monet.core.crypto.ed25519;

import org.monet.core.crypto.*;

public class Ed25519KeyAnalyzerTest extends KeyAnalyzerTest {

	@Override
	protected CryptoEngine getCryptoEngine() {
		return CryptoEngines.ed25519Engine();
	}
}
