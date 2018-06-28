package org.monet.core.model.observers;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.monet.core.model.Account;
import org.monet.core.model.primitive.Amount;
import org.monet.core.test.Utils;

public class BalanceAdjustmentNotificationTest {

	@Test
	public void canCreateNotification() {
		// Act:
		final Account account = Utils.generateRandomAccount();
		final BalanceAdjustmentNotification notification = new BalanceAdjustmentNotification(
				NotificationType.BalanceCredit,
				account,
				Amount.frommonet(123));

		// Assert:
		Assert.assertThat(notification.getType(), IsEqual.equalTo(NotificationType.BalanceCredit));
		Assert.assertThat(notification.getAccount(), IsEqual.equalTo(account));
		Assert.assertThat(notification.getAmount(), IsEqual.equalTo(Amount.frommonet(123)));
	}
}