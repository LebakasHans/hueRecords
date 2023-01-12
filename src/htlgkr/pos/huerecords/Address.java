package htlgkr.pos.huerecords;

public record Address(
        String streetAndHouseNumber,
        String zipCodeAndCity,
        String country
) {
}
