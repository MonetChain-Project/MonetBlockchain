package org.monet.core.model.observers;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.monet.core.model.Account;
import org.monet.core.model.primitive.Amount;
import org.monet.core.test.Utils;

public class BalanceTransferNotificationTest {

	@Test
	public void canCreateNotification() {
		// Act:
		final Account sender = Utils.generateRandomAccount();
		final Account recipient = Utils.generateRandomAccount();
		final BalanceTransferNotification notification = new BalanceTransferNotification(
				sender,
				recipient,
				Amount.frommonet(123));

		// Assert:
		Assert.assertThat(notification.getType(), IsEqual.equalTo(NotificationType.BalanceTransfer));
		Assert.assertThat(notification.getSender(), IsEqual.equalTo(sender));
		Assert.assertThat(notification.getRecipient(), IsEqual.equalTo(recipient));
		Assert.assertThat(notification.getAmount(), IsEqual.equalTo(Amount.frommonet(123)));
	}
}