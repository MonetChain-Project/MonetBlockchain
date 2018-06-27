package org.monet.core.model.ncc;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.monet.core.model.Address;
import org.monet.core.test.Utils;

public class AccountIdBuilderTest {

	@Test
	public void accountIdCanBeBuilt() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final AccountIdBuilder builder = new AccountIdBuilder();

		// Act:
		builder.setAddress(address.getEncoded());
		final AccountId accountId = builder.build();

		// Assert:
		Assert.assertThat(accountId.getAddress(), IsEqual.equalTo(address));
	}
}