package org.monet.core.model.ncc;

import org.monet.core.crypto.Hash;
import org.monet.core.model.Transaction;
import org.monet.core.model.primitive.BlockHeight;
import org.monet.core.test.RandomTransactionFactory;

public class TransactionMetaDataPairTest extends AbstractMetaDataPairTest<Transaction, TransactionMetaData> {

	public TransactionMetaDataPairTest() {
		super(
				account -> {
					final Transaction transfer = RandomTransactionFactory.createTransfer(account);
					transfer.sign();
					return transfer;
				},
				id -> new TransactionMetaData(BlockHeight.ONE, (long)id, Hash.ZERO),
				TransactionMetaDataPair::new,
				TransactionMetaDataPair::new,
				transaction -> transaction.getSigner().getAddress(),
				metaData -> metaData.getId().intValue());
	}
}
