package org.demyo.plugin.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Spring configuration for the Books plugin.
 */
@Configuration
public class PluginConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(PluginConfiguration.class);

	private final MessageSource coreMessageSource;

	/**
	 * Creates the configuration.
	 * 
	 * @param messageSource The core MessageSource, to enable delegation.
	 */
	public PluginConfiguration(@Qualifier("demyo.core.messageSource") MessageSource messageSource) {
		this.coreMessageSource = messageSource;
	}

	/**
	 * Creates a {@link MessageSource} that overrides the default one in Demyo.
	 * 
	 * @return The overriding {@link MessageSource}.
	 */
	@Bean
	@Primary
	public MessageSource getMessageSource() {
		LOGGER.debug("Creating overriding MessageSource");
		ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();
		msgSource.setParentMessageSource(coreMessageSource);
		msgSource
				.setBasenames(new String[]
		{ //
				"org/demyo/plugin/books/i18n/core", //
				"org/demyo/plugin/books/i18n/fields", //
				"org/demyo/plugin/books/i18n/menu", //
				"org/demyo/plugin/books/i18n/pages", //
				"org/demyo/plugin/books/i18n/widgets", //
		});

		return msgSource;
	}
}
