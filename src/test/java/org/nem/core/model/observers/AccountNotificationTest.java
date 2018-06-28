package org.monet.core.model.observers;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.monet.core.model.Account;
import org.monet.core.test.Utils;

public class AccountNotificationTest {

	@Test
	public void canCreateNotification() {
		// Act:
		final Account account = Utils.generateRandomAccount();
		final AccountNotification notification = new AccountNotification(account);

		// Assert:
		Assert.assertThat(notification.getType(), IsEqual.equalTo(NotificationType.Account));
		Assert.assertThat(notification.getAccount(), IsEqual.equalTo(account));
	}
}