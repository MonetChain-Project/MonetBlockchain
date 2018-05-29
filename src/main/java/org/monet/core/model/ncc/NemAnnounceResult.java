package org.monet.core.model.ncc;

import org.monet.core.crypto.Hash;
import org.monet.core.model.ValidationResult;
import org.monet.core.serialization.*;

/**
 * An extended monet request result that is used to convey additional information from an announce operation.
 */
public class monetAnnounceResult extends monetRequestResult {
	private final Hash transactionHash;
	private final Hash innerTransactionHash;

	/**
	 * Creates a monet announce result from a validation result.
	 *
	 * @param result The validation result.
	 */
	public monetAnnounceResult(final ValidationResult result) {
		this(result, null, null);
	}

	/**
	 * Creates a monet announce result from a validation result and optional transaction hashes.
	 *
	 * @param result The validation result.
	 * @param transactionHash The transaction hash.
	 * @param innerTransactionHash The inner transaction hash.
	 */
	public monetAnnounceResult(final ValidationResult result, final Hash transactionHash, final Hash innerTransactionHash) {
		super(result);
		this.transactionHash = transactionHash;
		this.innerTransactionHash = innerTransactionHash;
	}

	/**
	 * Deserializes a monet announce result.
	 *
	 * @param deserializer The deserializer.
	 */
	public monetAnnounceResult(final Deserializer deserializer) {
		super(deserializer);
		this.innerTransactionHash = deserializer.readOptionalObject("innerTransactionHash", Hash::new);
		this.transactionHash = deserializer.readOptionalObject("transactionHash", Hash::new);
	}

	/**
	 * Gets the transaction hash.
	 *
	 * @return The transaction hash.
	 */
	public Hash getTransactionHash() {
		return this.transactionHash;
	}

	/**
	 * Gets the inner transaction hash.
	 *
	 * @return The inner transaction hash.
	 */
	public Hash getInnerTransactionHash() {
		return this.innerTransactionHash;
	}

	@Override
	public void serialize(final Serializer serializer) {
		super.serialize(serializer);
		serializer.writeObject("innerTransactionHash", this.innerTransactionHash);
		serializer.writeObject("transactionHash", this.transactionHash);
	}
}
