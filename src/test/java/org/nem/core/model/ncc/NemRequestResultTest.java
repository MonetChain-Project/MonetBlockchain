package org.monet.core.model.ncc;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.monet.core.model.ValidationResult;
import org.monet.core.serialization.Deserializer;
import org.monet.core.test.Utils;

public class monetRequestResultTest {

    @Test
    public void canCreateResultAroundExplicitFields() {
        // Arrange + Act:
        final monetRequestResult result = new monetRequestResult(42, 1337, "Neutral result");

        // Assert:
        Assert.assertThat(result.getType(), IsEqual.equalTo(42));
        Assert.assertThat(result.getCode(), IsEqual.equalTo(1337));
        Assert.assertThat(result.getMessage(), IsEqual.equalTo("Neutral result"));
    }

    @Test
    public void canCreateResultAroundValidationResult() {
        // Arrange + Act:
        final monetRequestResult result = new monetRequestResult(ValidationResult.FAILURE_CHAIN_INVALID);

        // Assert:
        Assert.assertThat(result.getType(), IsEqual.equalTo(monetRequestResult.TYPE_VALIDATION_RESULT));
        Assert.assertThat(result.getCode(), IsEqual.equalTo(ValidationResult.FAILURE_CHAIN_INVALID.getValue()));
        Assert.assertThat(result.getMessage(), IsEqual.equalTo("FAILURE_CHAIN_INVALID"));
    }

    @Test
    public void isErrorReturnsFalseForNeutralAndSuccessCode() {
        // Arrange + Act:
        final monetRequestResult result = new monetRequestResult(
                monetRequestResult.TYPE_VALIDATION_RESULT,
                monetRequestResult.CODE_NEUTRAL,
                "Neutral result");
        final monetRequestResult result2 = new monetRequestResult(
                monetRequestResult.TYPE_VALIDATION_RESULT,
                monetRequestResult.CODE_SUCCESS,
                "Neutral result");

        // Assert:
        Assert.assertThat(result.isError(), IsEqual.equalTo(false));
        Assert.assertThat(result2.isError(), IsEqual.equalTo(false));
    }

    @Test
    public void isErrorReturnsTrueForCodeOtherThanNeutralOrSuccess() {
        // Arrange + Act:
        final monetRequestResult result = new monetRequestResult(
                monetRequestResult.TYPE_VALIDATION_RESULT,
                2,
                "Neutral result");

        // Assert:
        Assert.assertThat(result.isError(), IsEqual.equalTo(true));
    }

    @Test
    public void canRoundTripNisRequestResult() {
        // Arrange + Act:
        final Deserializer deserializer = Utils.roundtripSerializableEntity(
                new monetRequestResult(42, 1337, "Neutral result"),
                null);
        final monetRequestResult result = new monetRequestResult(deserializer);

        // Assert:
        Assert.assertThat(result.getType(), IsEqual.equalTo(42));
        Assert.assertThat(result.getCode(), IsEqual.equalTo(1337));
        Assert.assertThat(result.getMessage(), IsEqual.equalTo("Neutral result"));
    }
}
