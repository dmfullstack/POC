/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.poc.restfulpoc.config;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * JMSApplicationConfig class.
 *
 * @author Raja Kolli
 * @since 0.0.5
 */

@EnableJms
@Configuration
public class JMSApplicationConfig {

	/**
	 * myJMSFactory.
	 * @param connectionFactory a {@link javax.jms.ConnectionFactory} object.
	 * @param configurer a
	 * {@link org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer}
	 * object.
	 * @return a {@link org.springframework.jms.config.JmsListenerContainerFactory}
	 * object.
	 */
	@Bean
	public JmsListenerContainerFactory<DefaultMessageListenerContainer> myJMSFactory(
			ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message
		// converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		return factory;
	}

	/**
	 * jacksonJmsMessageConverter.
	 * @return a {@link org.springframework.jms.support.converter.MessageConverter}
	 * object.
	 */
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
