package org.monet.core.model.ncc;

import org.monet.core.model.namespace.Namespace;
import org.monet.core.serialization.Deserializer;

/**
 * Pair containing a namespace and meta data.
 */
public class NamespaceMetaDataPair extends AbstractMetaDataPair<Namespace, DefaultMetaData> {

	/**
	 * Creates a new pair.
	 *
	 * @param namespace The namespace.
	 * @param metaData The meta data.
	 */
	public NamespaceMetaDataPair(final Namespace namespace, final DefaultMetaData metaData) {
		super("namespace", "meta", namespace, metaData);
	}

	/**
	 * Deserializes a pair.
	 *
	 * @param deserializer The deserializer
	 */
	public NamespaceMetaDataPair(final Deserializer deserializer) {
		super("namespace", "meta", Namespace::new, DefaultMetaData::new, deserializer);
	}
}
