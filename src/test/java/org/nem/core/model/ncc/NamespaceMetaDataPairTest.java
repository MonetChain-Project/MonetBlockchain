package org.monet.core.model.ncc;

import org.monet.core.model.namespace.*;
import org.monet.core.model.primitive.BlockHeight;

public class NamespaceMetaDataPairTest extends AbstractMetaDataPairTest<Namespace, DefaultMetaData> {

	public NamespaceMetaDataPairTest() {
		super(
				account -> new Namespace(new NamespaceId("foo"), account, new BlockHeight(17)),
				id -> new DefaultMetaData((long)id),
				NamespaceMetaDataPair::new,
				NamespaceMetaDataPair::new,
				namespace -> namespace.getOwner().getAddress(),
				metaData -> metaData.getId().intValue());
	}
}