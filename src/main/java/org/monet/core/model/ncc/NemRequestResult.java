package org.monet.core.model.ncc;

import org.monet.core.model.ValidationResult;
import org.monet.core.serialization.*;

/**
 * Result for monet request.
 */
public class monetRequestResult implements SerializableEntity {
	/**
	 * Result type indicating a validation result
	 */
	public static final int TYPE_VALIDATION_RESULT = 0x00000001;

	/**
	 * Result type indicating a heartbeat.
	 */
	public static final int TYPE_HEARTBEAT = 0x00000002;

	/**
	 * Result type indicating the status.
	 */
	public static final int TYPE_STATUS = 0x00000004;

	/**
	 * Special code representing a neutral result.
	 */
	public static final int CODE_NEUTRAL = 0x00000000;

	/**
	 * Special code representing a successful result.
	 */
	public static final int CODE_SUCCESS = 0x00000001;

	private final int type;
	private final int code;
	private final String message;

	/**
	 * Creates a monet request result.
	 *
	 * @param type The result type.
	 * @param code The result code.
	 * @param message The message.
	 */
	public monetRequestResult(final int type, final int code, final String message) {
		this.type = type;
		this.code = code;
		this.message = message;
	}

	/**
	 * Creates a monet request result from a validation result.
	 *
	 * @param result The validation result.
	 */
	public monetRequestResult(final ValidationResult result) {
		this(TYPE_VALIDATION_RESULT, result.getValue(), result.toString());
	}

	/**
	 * Deserializes a monet request result.
	 *
	 * @param deserializer The deserializer.
	 */
	public monetRequestResult(final Deserializer deserializer) {
		this.type = deserializer.readInt("type");
		this.code = deserializer.readInt("code");
		this.message = deserializer.readString("message");
	}

	/**
	 * Gets the result type.
	 *
	 * @return The result type.
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * Gets the result code.
	 *
	 * @return The result code.
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * Gets the message.
	 *
	 * @return The message.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Gets a value indicating whether or not this result indicates an error.
	 *
	 * @return true if this result indicates an error.
	 */
	public boolean isError() {
		return CODE_NEUTRAL != this.code && CODE_SUCCESS != this.code;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeInt("type", this.type);
		serializer.writeInt("code", this.code);
		serializer.writeString("message", this.message);
	}
}
