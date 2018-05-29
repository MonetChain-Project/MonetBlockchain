package org.monet.core.model.observers;

import org.monet.core.model.Account;
import org.monet.core.model.mosaic.MosaicId;
import org.monet.core.model.primitive.Quantity;

/**
 * A notification that one account has transferred a quantity of a mosaic to another account.
 */
public class MosaicTransferNotification extends Notification {
	private final Account sender;
	private final Account recipient;
	private final MosaicId mosaicId;
	private final Quantity quantity;

	/**
	 * Creates a new mosaic transfer notification.
	 *
	 * @param sender The sender.
	 * @param recipient The recipient.
	 * @param mosaicId The mosaic id.
	 * @param quantity The quantity
	 */
	public MosaicTransferNotification(
			final Account sender,
			final Account recipient,
			final MosaicId mosaicId,
			final Quantity quantity) {
		super(NotificationType.MosaicTransfer);
		this.sender = sender;
		this.recipient = recipient;
		this.mosaicId = mosaicId;
		this.quantity = quantity;
	}

	/**
	 * Gets the sender.
	 *
	 * @return The sender.
	 */
	public Account getSender() {
		return this.sender;
	}

	/**
	 * Gets the recipient.
	 *
	 * @return The recipient.
	 */
	public Account getRecipient() {
		return this.recipient;
	}

	/**
	 * Gets the mosaic id.
	 *
	 * @return The mosaic id.
	 */
	public MosaicId getMosaicId() {
		return this.mosaicId;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return The quantity.
	 */
	public Quantity getQuantity() {
		return this.quantity;
	}
}
