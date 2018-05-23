package org.monet.core.connect;

import org.monet.core.metadata.MetaDataFactory;
import org.monet.core.node.NodeVersion;
import org.monet.core.serialization.Deserializer;
import org.monet.core.time.SystemTimeProvider;
import org.monet.core.utils.ExceptionUtils;

import java.net.URL;
import java.util.logging.Logger;

/**
 * Helper class for getting version information.
 */
public class VersionProvider {
	private static final Logger LOGGER = Logger.getLogger(VersionProvider.class.getName());

	private static final String VERSION_PROVIDER_URL = "http://bob.monet.ninja/version.json";
	private static final String VERSION_FLAVOR = "stable";

	private final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient;

	/**
	 * Creates a new version provider.
	 *
	 * @param httpClient The http method client to use.
	 */
	public VersionProvider(final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * Gets the local monet version.
	 *
	 * @return The local monet version.
	 */
	public NodeVersion getLocalVersion() {
		final String localVersion = MetaDataFactory.loadApplicationMetaData(VersionProvider.class, new SystemTimeProvider()).getVersion();
		return NodeVersion.parse(localVersion);
	}

	/**
	 * Gets the latest monet version.
	 *
	 * @return The latest monet version.
	 */
	public NodeVersion getLatestVersion() {
		try {
			final URL url = ExceptionUtils.propagate(() -> new URL(VERSION_PROVIDER_URL));
			return this.httpClient.get(url, new HttpErrorResponseDeserializerUnionStrategy(null))
					.getFuture()
					.thenApply(union -> {
						final Deserializer deserializer = union.getDeserializer();
						return NodeVersion.parse(deserializer.readString(VERSION_FLAVOR));
					}).join();
		} catch (final Exception e) {
			// if there's an error retrieving the latest version, ignore it because
			// we still want monet monitor to be able to boot up and run
			LOGGER.warning(String.format("unable to determine latest version: %s", e));
			return NodeVersion.ZERO;
		}
	}
}
