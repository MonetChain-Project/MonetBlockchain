package org.monet.core.model.ncc;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.monet.core.crypto.PublicKey;
import org.monet.core.test.Utils;

public class PublicKeyBuilderTest {

    @Test
    public void publicKeyCanBeBuilt() {
        // Arrange:
        final PublicKey original = Utils.generateRandomPublicKey();
        final PublicKeyBuilder builder = new PublicKeyBuilder();

        // Act:
        builder.setPublicKey(original.toString());
        final PublicKey publicKey = builder.build();

        // Assert:
        Assert.assertThat(publicKey, IsEqual.equalTo(original));
    }
}