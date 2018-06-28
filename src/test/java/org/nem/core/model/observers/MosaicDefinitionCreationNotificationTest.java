package org.monet.core.model.observers;

import org.hamcrest.core.*;
import org.junit.*;
import org.monet.core.model.mosaic.MosaicDefinition;
import org.monet.core.test.Utils;

public class MosaicDefinitionCreationNotificationTest {

	@Test
	public void canCreateNotification() {
		// Act:
		final MosaicDefinition mosaicDefinition = Utils.createMosaicDefinition(Utils.generateRandomAccount());
		final MosaicDefinitionCreationNotification notification = new MosaicDefinitionCreationNotification(mosaicDefinition);

		// Assert:
		Assert.assertThat(notification.getType(), IsEqual.equalTo(NotificationType.MosaicDefinitionCreation));
		Assert.assertThat(notification.getMosaicDefinition(), IsSame.sameInstance(mosaicDefinition));
	}
}