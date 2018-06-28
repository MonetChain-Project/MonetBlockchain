package org.monet.core.model.ncc;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.monet.core.crypto.Hash;
import org.monet.core.model.ValidationResult;
import org.monet.core.serialization.Deserializer;
import org.monet.core.test.Utils;

@RunWith(Enclosed.class)
public class monetAnnounceResultTest {

    private abstract static class monetAnnounceResultTestBase {

        @Test
        public void canCreateResult() {
            // Act:
            final monetAnnounceResult result = this.createResult(ValidationResult.FAILURE_CHAIN_INVALID);

            // Assert:
            assertmonetRequestResult(result);
            Assert.assertThat(result.getTransactionHash(), IsEqual.equalTo(this.getExpectedHash()));
            Assert.assertThat(result.getInnerTransactionHash(), IsEqual.equalTo(this.getExpectedInnerHash()));
        }

        @Test
        public void canRoundtripResult() {
            // Act:
            final Deserializer deserializer = Utils.roundtripSerializableEntity(
                    this.createResult(ValidationResult.FAILURE_CHAIN_INVALID),
                    null);
            final monetAnnounceResult result = new monetAnnounceResult(deserializer);

            // Assert:
            assertmonetRequestResult(result);
            Assert.assertThat(result.getTransactionHash(), IsEqual.equalTo(this.getExpectedHash()));
            Assert.assertThat(result.getInnerTransactionHash(), IsEqual.equalTo(this.getExpectedInnerHash()));
        }

        @Test
        public void canRoundtripResultAsmonetRequestResult() {
            // Act:
            final Deserializer deserializer = Utils.roundtripSerializableEntity(
                    this.createResult(ValidationResult.FAILURE_CHAIN_INVALID),
                    null);
            final monetRequestResult result = new monetRequestResult(deserializer);

            // Assert:
            assertmonetRequestResult(result);
        }

        private static void assertmonetRequestResult(final monetRequestResult result) {
            Assert.assertThat(result.getType(), IsEqual.equalTo(monetRequestResult.TYPE_VALIDATION_RESULT));
            Assert.assertThat(result.getCode(), IsEqual.equalTo(ValidationResult.FAILURE_CHAIN_INVALID.getValue()));
            Assert.assertThat(result.getMessage(), IsEqual.equalTo("FAILURE_CHAIN_INVALID"));
        }

        protected abstract monetAnnounceResult createResult(final ValidationResult result);

        protected abstract Hash getExpectedHash();

        protected abstract Hash getExpectedInnerHash();
    }

    public static class WithoutTransactionHashTest extends monetAnnounceResultTestBase {

        @Override
        protected monetAnnounceResult createResult(final ValidationResult result) {
            return new monetAnnounceResult(result);
        }

        @Override
        protected Hash getExpectedHash() {
            return null;
        }

        @Override
        protected Hash getExpectedInnerHash() {
            return null;
        }
    }

    public static class WithOuterTransactionHashTest extends monetAnnounceResultTestBase {
        private static final Hash DEFAULT_HASH = Utils.generateRandomHash();

        protected monetAnnounceResult createResult(final ValidationResult result) {
            return new monetAnnounceResult(result, DEFAULT_HASH, null);
        }

        @Override
        protected Hash getExpectedHash() {
            return DEFAULT_HASH;
        }

        @Override
        protected Hash getExpectedInnerHash() {
            return null;
        }
    }

    public static class WithOuterAndInnerTransactionHashTest extends monetAnnounceResultTestBase {
        private static final Hash DEFAULT_HASH = Utils.generateRandomHash();
        private static final Hash DEFAULT_INNER_HASH = Utils.generateRandomHash();

        protected monetAnnounceResult createResult(final ValidationResult result) {
            return new monetAnnounceResult(result, DEFAULT_HASH, DEFAULT_INNER_HASH);
        }

        @Override
        protected Hash getExpectedHash() {
            return DEFAULT_HASH;
        }

        @Override
        protected Hash getExpectedInnerHash() {
            return DEFAULT_INNER_HASH;
        }
    }
}