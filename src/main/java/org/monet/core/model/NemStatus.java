package org.monet.core.model;

/**
 * Possible monet statuses.
 */
public enum monetStatus {
	/**
	 * Unknown status.
	 */
	UNKNOWN(0),

	/**
	 * NIS/NCC is stopped.
	 */
	STOPPED(1),

	/**
	 * NIS/NCC is starting.
	 */
	STARTING(2),

	/**
	 * NIS/NCC is running.
	 */
	RUNNING(3),

	/**
	 * Local node is booting (implies RUNNING).
	 */
	BOOTING(4),

	/**
	 * Local node is booted (implies RUNNING).
	 */
	BOOTED(5),

	/**
	 * NIS local node is synchronized (implies RUNNING and BOOTED).
	 */
	SYNCHRONIZED(6),

	/**
	 * NIS local node does not see any remote NIS node (implies RUNNING and BOOTED).
	 */
	NO_REMOTE_NIS_AVAILABLE(7),

	/**
	 * NIS is currently loading the block chain.
	 */
	LOADING(8);

	private final int value;

	monetStatus(final int value) {
		this.value = value;
	}

	/**
	 * Creates a monet status given a raw value.
	 *
	 * @param value The value.
	 * @return The monet status if the value is known.
	 * @throws IllegalArgumentException if the value is unknown.
	 */
	public static monetStatus fromValue(final int value) {
		for (final monetStatus result : values()) {
			if (result.getValue() == value) {
				return result;
			}
		}

		throw new IllegalArgumentException("Invalid monet status: " + value);
	}

	/**
	 * Gets the underlying integer representation of the status.
	 *
	 * @return The underlying value.
	 */
	public int getValue() {
		return this.value;
	}
}
