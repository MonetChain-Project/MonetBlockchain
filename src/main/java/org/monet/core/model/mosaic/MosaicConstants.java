package org.monet.core.model.mosaic;

import org.monet.core.crypto.PublicKey;
import org.monet.core.model.*;
import org.monet.core.model.namespace.*;
import org.monet.core.model.primitive.BlockHeight;

import java.util.Properties;

/**
 * Common place to have Mosaic-related constants accessible from all modules.
 */
public class MosaicConstants {
	// TODO 20150813 J-*: we need to make these accounts multisig before moving to mainnet
	private static final PublicKey NAMESPACE_OWNER_monet_KEY = PublicKey.fromHexString("3e82e1c1e4a75adaa3cba8c101c3cd31d9817a2eb966eb3b511fb2ed45b8e262");
	private static final PublicKey MOSAIC_CREATION_FEE_SINK_KEY = PublicKey.fromHexString("53e140b5947f104cabc2d6fe8baedbc30ef9a0609c717d9613de593ec2a266d3");

	/**
	 * The maximum allowable quantity of a mosaic.
	 */
	public static final long MAX_QUANTITY = 9_000_000_000_000_000L;

	/**
	 * The 'monet' namespace owner.
	 */
	public static Account NAMESPACE_OWNER_monet;

	/**
	 * The 'monet' namespace id.
	 */
	public static final NamespaceId NAMESPACE_ID_monet = new NamespaceId("monet");

	/**
	 * The 'monet' namespace.
	 */
	public static Namespace NAMESPACE_monet;

	/**
	 * The mosaic creation fee sink.
	 */
	public static Account MOSAIC_CREATION_FEE_SINK;

	/**
	 * The xem mosaic id.
	 */
	public static final MosaicId MOSAIC_ID_XEM = new MosaicId(NAMESPACE_ID_monet, "xem");

	static {
		// by default it will set to testnet
		setAccounts();
	}

	/**
	 * The 'monet.xem' mosaic definition.
	 */
	public static MosaicDefinition MOSAIC_DEFINITION_XEM;

	private static MosaicDefinition createXemMosaicDefinition() {
		final MosaicDescriptor descriptor = new MosaicDescriptor("reserved xem mosaic");
		final Properties properties = new Properties();
		properties.put("divisibility", "6");
		properties.put("initialSupply", "8999999999");
		properties.put("mutableSupply", "false");
		properties.put("transferable", "true");
		return new MosaicDefinition(
				NAMESPACE_OWNER_monet,
				MOSAIC_ID_XEM,
				descriptor,
				new DefaultMosaicProperties(properties),
				null);
	}

	public static void setAccounts() {
		NAMESPACE_OWNER_monet = new Account(Address.fromPublicKey(NAMESPACE_OWNER_monet_KEY));
		NAMESPACE_monet = new Namespace(NAMESPACE_ID_monet, NAMESPACE_OWNER_monet, BlockHeight.MAX);
		MOSAIC_CREATION_FEE_SINK = new Account(Address.fromPublicKey(MOSAIC_CREATION_FEE_SINK_KEY));
		MOSAIC_DEFINITION_XEM = createXemMosaicDefinition();
	}
}
