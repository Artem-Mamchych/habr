package ru.habr.zrd.hls.jaxb;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.bind.JAXBException;

import org.hibernate.Hibernate;

import com.sun.xml.bind.AccessorFactory;
import com.sun.xml.bind.AccessorFactoryImpl;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;

public class JAXBHibernateAccessorFactory implements AccessorFactory {
	/*
	 * Реализация AccessorFactory уже написана - AccessorFactoryImpl. Судя по всему это singleton, 
	 * и отнаследоваться от него не получится, поэтому сделаем его делегатом и напишем wrapper.
	 */
	private final AccessorFactory accessorFactory = AccessorFactoryImpl.getInstance();
	
	/*
	 * Также потребуется некая реализация Accessor. Поскольку больше она нигде не нужна, сделаем
	 * ее в виде private inner class, чтобы не болталась по проекту.
	 */
	private static class JAXBHibernateAccessor<B, V> extends Accessor<B, V> {
		private final Accessor<B, V> accessor;
		public JAXBHibernateAccessor(Accessor<B, V> accessor) {
			super(accessor.getValueType());
			this.accessor = accessor;
		}

		@Override
		public V get(B bean) throws AccessorException {
			V value = accessor.get(bean);
			/*
			 * Вот оно! Ради этого весь сыр-бор. Если кому-то простое зануление может показаться неправильным,
			 * он волен сделать тут все, что захочется.
			 * Метод Hibernate.isInitialized() c одинаковым поведением присутствует и в Hibernate3,  и Hibernate4. 
			 */
			return Hibernate.isInitialized(value) ? value : null;
		}

		@Override
		public void set(B bean, V value) throws AccessorException {
			accessor.set(bean, value);			
		}		
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public Accessor createFieldAccessor(Class bean, Field field, boolean readOnly) throws JAXBException {
		return new JAXBHibernateAccessor(accessorFactory.createFieldAccessor(bean, field, readOnly));
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public Accessor createPropertyAccessor(Class bean, Method getter, Method setter) throws JAXBException {
		return new JAXBHibernateAccessor(accessorFactory.createPropertyAccessor(bean, getter, setter));
	}
}
