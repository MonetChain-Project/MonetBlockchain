package org.monet.core.model;

import net.minidev.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.monet.core.serialization.*;

import java.io.*;

/**
 * Represents the monetesis block.
 */
public class monetesisBlock {

	/**
	 * Loads the monetesis block from the default project resource.
	 *
	 * @param monetesisBlockInfo The monetesis block information.
	 * @param context The deserialization context to use.
	 * @return The monetesis block.
	 */
	public static Block fromResource(final monetesisBlockInfo monetesisBlockInfo, final DeserializationContext context) {
		try (final InputStream fin = monetesisBlock.class.getClassLoader().getResourceAsStream(monetesisBlockInfo.getDataFileName())) {
			final byte[] buffer = IOUtils.toByteArray(fin);
			return fromBlobObject(monetesisBlockInfo, buffer, context);
		} catch (final IOException e) {
			throw new IllegalStateException("unable to parse monetesis block stream");
		}
	}

	/**
	 * Loads the monetesis block from a json object.
	 *
	 * @param monetesisBlockInfo The monetesis block information.
	 * @param jsonObject The json object.
	 * @param context The deserialization context to use.
	 * @return The monetesis block.
	 */
	public static Block fromJsonObject(final monetesisBlockInfo monetesisBlockInfo, final JSONObject jsonObject, final DeserializationContext context) {
		final Deserializer deserializer = new JsonDeserializer(jsonObject, context);
		return deserialize(monetesisBlockInfo, deserializer);
	}

	/**
	 * Loads the monetesis block from binary data.
	 *
	 * @param monetesisBlockInfo The monetesis block information.
	 * @param buffer The binary data.
	 * @param context The deserialization context to use.
	 * @return The monetesis block.
	 */
	public static Block fromBlobObject(final monetesisBlockInfo monetesisBlockInfo, final byte[] buffer, final DeserializationContext context) {
		final Deserializer deserializer = new BinaryDeserializer(buffer, context);
		return deserialize(monetesisBlockInfo, deserializer);
	}

	private static Block deserialize(final monetesisBlockInfo monetesisBlockInfo, final Deserializer deserializer) {
		if (BlockTypes.monetESIS != deserializer.readInt("type")) {
			throw new IllegalArgumentException("deserializer does not have correct type set");
		}

		final Block block = new Block(BlockTypes.monetESIS, VerifiableEntity.DeserializationOptions.VERIFIABLE, deserializer);
		block.setGenerationHash(monetesisBlockInfo.getGenerationHash());
		return block;
	}
}