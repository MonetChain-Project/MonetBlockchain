package org.monet.core.model.ncc;

import org.monet.core.model.*;
import org.monet.core.model.primitive.*;
import org.monet.core.test.Utils;

import java.util.Collections;

public class AccountMetaDataPairTest extends AbstractMetaDataPairTest<AccountInfo, AccountMetaData> {

	public AccountMetaDataPairTest() {
		super(
				account -> new AccountInfo(account.getAddress(), Amount.ZERO, Amount.ZERO, BlockAmount.ZERO, null, 0.0),
				id -> new AccountMetaData(
						AccountStatus.LOCKED,
						AccountRemoteStatus.ACTIVATING,
						Collections.singletonList(new AccountInfo(Utils.generateRandomAddress(), Amount.ZERO, Amount.ZERO, new BlockAmount(id), null, 0.0)),
						Collections.emptyList()),
				AccountMetaDataPair::new,
				AccountMetaDataPair::new,
				AccountInfo::getAddress,
				metaData -> (int)metaData.getCosignatoryOf().get(0).getNumHarvestedBlocks().getRaw());
	}
}
