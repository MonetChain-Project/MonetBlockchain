package org.monet.core.model.primitive;

import org.monet.core.serialization.*;

/**
 * Represents an amount of monet.
 */
public class Amount extends AbstractPrimitive<Amount, Long> {

	public static final int MICROmonetS_IN_monet = 1000000;

	/**
	 * Amount representing 0 monet.
	 */
	public static final Amount ZERO = new Amount(0);

	/**
	 * Creates a new amount given a quantity of monet.
	 *
	 * @param amount The amount of monet.
	 * @return The new amount.
	 */
	public static Amount frommonet(final long amount) {
		return new Amount(amount * MICROmonetS_IN_monet);
	}

	/**
	 * Creates a new amount given a quantity of micro monet.
	 *
	 * @param amount The amount of micro monet.
	 * @return The new amount.
	 */
	public static Amount fromMicromonet(final long amount) {
		return new Amount(amount);
	}

	/**
	 * Creates a monet amount.
	 *
	 * @param amount The number of micro monet.
	 */
	public Amount(final long amount) {
		super(amount, Amount.class);

		if (amount < 0) {
			throw new NegativeBalanceException(amount);
		}
	}

	/**
	 * Creates a new Amount by adding the specified amount to this amount.
	 *
	 * @param amount The specified amount.
	 * @return The new amount.
	 */
	public Amount add(final Amount amount) {
		return new Amount(this.getNumMicromonet() + amount.getNumMicromonet());
	}

	/**
	 * Creates a new Amount by subtracting the specified amount from this amount.
	 *
	 * @param amount The specified amount.
	 * @return The new amount.
	 */
	public Amount subtract(final Amount amount) {
		return new Amount(this.getNumMicromonet() - amount.getNumMicromonet());
	}

	/**
	 * Creates a new Amount by multiplying this amount by the specified scalar
	 *
	 * @param scalar The specified scalar.
	 * @return The new amount.
	 */
	public Amount multiply(final int scalar) {
		return new Amount(this.getNumMicromonet() * scalar);
	}

	/**
	 * Returns the number of micro monet.
	 *
	 * @return The number of micro monet.
	 */
	public long getNumMicromonet() {
		return this.getValue();
	}

	/**
	 * Returns the number of monet (rounded down to the nearest monet).
	 *
	 * @return The number of monet.
	 */
	public long getNummonet() {
		return this.getValue() / MICROmonetS_IN_monet;
	}

	//region inline serialization

	/**
	 * Writes an amount object.
	 *
	 * @param serializer The serializer to use.
	 * @param label The optional label.
	 * @param amount The object.
	 */
	public static void writeTo(final Serializer serializer, final String label, final Amount amount) {
		serializer.writeLong(label, amount.getNumMicromonet());
	}

	/**
	 * Reads an amount object.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static Amount readFrom(final Deserializer deserializer, final String label) {
		return new Amount(deserializer.readLong(label));
	}

	/**
	 * Reads an optional amount object.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static Amount readFromOptional(final Deserializer deserializer, final String label) {
		final Long value = deserializer.readOptionalLong(label);
		return null == value ? null : new Amount(value);
	}

	//endregion
}
