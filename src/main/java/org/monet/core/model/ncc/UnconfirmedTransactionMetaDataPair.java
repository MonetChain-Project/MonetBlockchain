package org.monet.core.model.ncc;

import org.monet.core.model.*;
import org.monet.core.serialization.Deserializer;

/**
 * Pair containing an unconfirmed transaction and an unconfirmed transaction meta data.
 */
public class UnconfirmedTransactionMetaDataPair extends AbstractMetaDataPair<Transaction, UnconfirmedTransactionMetaData> {

	/**
	 * Creates a new pair.
	 *
	 * @param transaction The unconfirmed transaction.
	 * @param metaData The meta data.
	 */
	public UnconfirmedTransactionMetaDataPair(final Transaction transaction, final UnconfirmedTransactionMetaData metaData) {
		super("transaction", "meta", transaction, metaData);
	}

	/**
	 * Deserializes a pair.
	 *
	 * @param deserializer The deserializer
	 */
	public UnconfirmedTransactionMetaDataPair(final Deserializer deserializer) {
		super("transaction", "meta", TransactionFactory.VERIFIABLE, UnconfirmedTransactionMetaData::new, deserializer);
	}
}