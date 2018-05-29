package org.monet.core.model.mosaic;

import org.monet.core.model.*;
import org.monet.core.model.primitive.Supply;
import org.monet.core.utils.MustBe;

import java.util.*;

/**
 * Class holding properties of a mosaic.
 */
public class DefaultMosaicProperties implements MosaicProperties {
	private final monetProperties properties;

	/**
	 * Creates a new mosaic properties bag.
	 *
	 * @param properties The properties.
	 */
	public DefaultMosaicProperties(final Properties properties) {
		MustBe.notNull(properties, "properties");
		this.properties = new monetProperties(properties);
		this.validateProperties();
	}

	/**
	 * Creates a new mosaic properties bag.
	 *
	 * @param properties The list of monet property objects.
	 */
	public DefaultMosaicProperties(final Collection<monetProperty> properties) {
		final Properties props = new Properties();
		properties.stream().forEach(p -> props.put(p.getName(), p.getValue()));
		this.properties = new monetProperties(props);
		this.validateProperties();
	}

	@Override
	public int getDivisibility() {
		return this.properties.getOptionalInteger("divisibility", 0);
	}

	@Override
	public long getInitialSupply() {
		return this.properties.getOptionalLong("initialSupply", 1_000L);
	}

	@Override
	public boolean isSupplyMutable() {
		return this.properties.getOptionalBoolean("supplyMutable", false);
	}

	@Override
	public boolean isTransferable() {
		return this.properties.getOptionalBoolean("transferable", true);
	}

	@Override
	public Collection<monetProperty> asCollection() {
		return Arrays.asList(
				new monetProperty("divisibility", Integer.toString(this.getDivisibility())),
				new monetProperty("initialSupply", Long.toString(this.getInitialSupply())),
				new monetProperty("supplyMutable", Boolean.toString(this.isSupplyMutable())),
				new monetProperty("transferable", Boolean.toString(this.isTransferable())));
	}

	private void validateProperties() {
		final int divisibility = this.getDivisibility();
		MustBe.inRange(divisibility, "divisibility", 0, 6);

		// note that MosaicUtils.add will throw if quantity is too large
		MosaicUtils.add(divisibility, Supply.ZERO, new Supply(this.getInitialSupply()));
	}

	@Override
	public int hashCode() {
		return this.asCollection().hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof DefaultMosaicProperties)) {
			return false;
		}

		final DefaultMosaicProperties rhs = (DefaultMosaicProperties)obj;
		return this.asCollection().equals(rhs.asCollection());
	}
}