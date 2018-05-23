package org.monet.core.connect.client;

import org.monet.core.connect.ErrorResponse;

/**
 * Strategy for dealing with client connection errors.
 */
@FunctionalInterface
public interface ErrorResponseStrategy {

	/**
	 * Maps an ErrorResponse to a runtime exception.
	 *
	 * @param response The response.
	 * @return The runtime exception.
	 */
	RuntimeException mapToException(final ErrorResponse response);
}
