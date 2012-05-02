// Определим 2 fetch profile. В DAO будем выставлять нужный, в зависимости от того, какой сервис вызван.
@FetchProfiles({
	@FetchProfile(name = "companyWithSuppliers", fetchOverrides = {
		@FetchProfile.FetchOverride(entity = Company.class, association = "suppliers", mode = FetchMode.JOIN),
	}),
	@FetchProfile(name = "companyWithCustomers", fetchOverrides = {
		@FetchProfile.FetchOverride(entity = Company.class, association = "customers", mode = FetchMode.JOIN)		
	})
})
// Подключим custom реализацю AccessorFactory для JAXB
@XmlAccessorFactory(JAXBHibernateAccessorFactory.class)
package ru.habr.zrd.hls.domain;

import com.sun.xml.bind.XmlAccessorFactory;
import ru.habr.zrd.hls.jaxb.JAXBHibernateAccessorFactory;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;