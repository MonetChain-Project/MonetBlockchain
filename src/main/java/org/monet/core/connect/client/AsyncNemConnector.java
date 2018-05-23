package org.monet.core.connect.client;

import org.monet.core.connect.HttpPostRequest;
import org.monet.core.node.NodeEndpoint;
import org.monet.core.serialization.Deserializer;

import java.util.concurrent.CompletableFuture;

/**
 * An asynchronous monet connector that can be used to connect to both NCC and NIS nodes.
 *
 * @param <TApiId> The api id type. This can be useful to allow connector scoping (e.g. certain connector
 * instances can only call certain APIs).
 */
@SuppressWarnings("unused")
public interface AsyncmonetConnector<TApiId> {

	/**
	 * Gets a response from the specified NIS relative url path.
	 *
	 * @param endpoint The NIS endpoint.
	 * @param apiId The api to call.
	 * @param query The get query string or null.
	 * @return The result.
	 */
	CompletableFuture<Deserializer> getAsync(
			final NodeEndpoint endpoint,
			final TApiId apiId,
			final String query);

	/**
	 * Posts a request to the specified NIS relative url path.
	 *
	 * @param endpoint The NIS endpoint.
	 * @param apiId The api to call.
	 * @param postRequest The request data.
	 * @return The result.
	 */
	CompletableFuture<Deserializer> postAsync(
			final NodeEndpoint endpoint,
			final TApiId apiId,
			final HttpPostRequest postRequest);

	/**
	 * Posts a request to the specified NIS relative url path.
	 *
	 * @param endpoint The NIS endpoint.
	 * @param apiId The api to call.
	 * @param postRequest The request data.
	 * @return The future.
	 */
	CompletableFuture<Void> postVoidAsync(
			final NodeEndpoint endpoint,
			final TApiId apiId,
			final HttpPostRequest postRequest);
}
