package org.monet.core.model.ncc;

import org.monet.core.model.*;
import org.monet.core.serialization.Deserializer;

/**
 * Pair containing a Transaction and a TransactionMetaData
 */
public class TransactionMetaDataPair extends AbstractMetaDataPair<Transaction, TransactionMetaData> {

	/**
	 * Creates a new pair.
	 *
	 * @param transaction The transaction.
	 * @param metaData The meta data.
	 */
	public TransactionMetaDataPair(final Transaction transaction, final TransactionMetaData metaData) {
		super("transaction", "meta", transaction, metaData);
	}

	/**
	 * Deserializes a pair.
	 *
	 * @param deserializer The deserializer
	 */
	public TransactionMetaDataPair(final Deserializer deserializer) {
		super("transaction", "meta", TransactionFactory.VERIFIABLE, TransactionMetaData::new, deserializer);
	}
}