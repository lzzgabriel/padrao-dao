package dev.lzzgabriel.faces.converter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "instantConverter")
public class InstantConverter implements Converter<Instant> {

	@Override
	public Instant getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		Instant obj = (Instant) DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
				.withZone(ZoneId.of("America/Sao_Paulo")).parse(value);
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Instant value) throws ConverterException {
		String str = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
				.format(value.atZone(ZoneId.of("America/Sao_Paulo")));
		return str;
	}

}
