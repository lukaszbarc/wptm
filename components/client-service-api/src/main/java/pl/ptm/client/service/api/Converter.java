package pl.ptm.client.service.api;

@FunctionalInterface
public interface Converter<FROM, TO> {

    TO convert(FROM from);
}
