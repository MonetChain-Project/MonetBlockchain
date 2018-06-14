package org.monet.core.crypto.secp256k1;

import org.monet.core.crypto.*;

public class SecP256K1CryptoEngineTest extends CryptoEngineTest {

	@Override
	protected CryptoEngine getCryptoEngine() {
		return CryptoEngines.secp256k1Engine();
	}
}
