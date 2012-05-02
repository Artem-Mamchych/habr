package ru.habr.zrd.hls.json;

import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.module.hibernate.HibernateModule;

public class JSONHibernateObjectMapper extends ObjectMapper {
	
	public JSONHibernateObjectMapper() {
		registerModule(new HibernateModule());
		/*
		 * Справедливости ради, стоит отметить, что тут разработчики рекомендуют установить еще
		 * какие-то малопонятные property, см. https://github.com/FasterXML/jackson-module-hibernate
		 */
	}
}