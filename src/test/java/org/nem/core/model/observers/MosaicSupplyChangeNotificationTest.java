package org.monet.core.model.observers;

import org.hamcrest.core.*;
import org.junit.*;
import org.monet.core.model.*;
import org.monet.core.model.mosaic.MosaicId;
import org.monet.core.model.namespace.NamespaceId;
import org.monet.core.model.primitive.Supply;
import org.monet.core.test.Utils;

public class MosaicSupplyChangeNotificationTest {

	@Test
	public void canCreateNotification() {
		// Act:
		final Account supplier = Utils.generateRandomAccount();
		final MosaicId mosaicId = new MosaicId(new NamespaceId("foo"), "bar");
		final Supply delta = Supply.fromValue(123);
		final MosaicSupplyChangeNotification notification = new MosaicSupplyChangeNotification(
				supplier,
				mosaicId,
				delta,
				MosaicSupplyType.Create);

		// Assert:
		Assert.assertThat(notification.getType(), IsEqual.equalTo(NotificationType.MosaicSupplyChange));
		Assert.assertThat(notification.getSupplier(), IsSame.sameInstance(supplier));
		Assert.assertThat(notification.getMosaicId(), IsSame.sameInstance(mosaicId));
		Assert.assertThat(notification.getDelta(), IsSame.sameInstance(delta));
		Assert.assertThat(notification.getSupplyType(), IsEqual.equalTo(MosaicSupplyType.Create));
	}
}
