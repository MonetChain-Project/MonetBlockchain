package org.monet.core.model.ncc;

import org.monet.core.model.mosaic.MosaicDefinition;
import org.monet.core.test.Utils;

public class MosaicDefinitionMetaDataPairTest extends AbstractMetaDataPairTest<MosaicDefinition, DefaultMetaData> {

	public MosaicDefinitionMetaDataPairTest() {
		super(
				Utils::createMosaicDefinition,
				id -> new DefaultMetaData((long)id),
				MosaicDefinitionMetaDataPair::new,
				MosaicDefinitionMetaDataPair::new,
				mosaicDefinition -> mosaicDefinition.getCreator().getAddress(),
				metaData -> metaData.getId().intValue());
	}
}
