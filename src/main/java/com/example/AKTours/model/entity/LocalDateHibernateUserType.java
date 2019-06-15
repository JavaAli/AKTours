package com.example.AKTours.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import org.hibernate.usertype.EnhancedUserType;

public class LocalDateHibernateUserType implements EnhancedUserType, Serializable{

    private static final long serialVersionUID = -7500395414633111738L;
    private static final int[] sql_types = new int[]{Types.TIMESTAMP};

    @Override
    public Object assemble(Serializable arg0, Object arg1)
            throws HibernateException {
        return arg0;
    }

    @Override
    public Object deepCopy(Object arg0) throws HibernateException {
        return arg0;
    }

    @Override
    public Serializable disassemble(Object arg0) throws HibernateException {
        return (Serializable) arg0;
    }

    @Override
    public boolean equals(Object obj1, Object obj2) throws HibernateException {
        if(obj1 == obj2)
            return true;

        if(obj1==null || obj2 == null)
            return false;

        LocalDate ldt1 = (LocalDate)obj1;
        LocalDate ldt2 = (LocalDate)obj2;

        return ldt1.equals(ldt2);
    }

    @Override
    public int hashCode(Object arg0) throws HibernateException {
        return arg0.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {

    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class returnedClass() {
        return LocalDate.class;
    }

    @Override
    public int[] sqlTypes() {
        return sql_types;
    }

    @Override
    public Object fromXMLString(String arg0) {
        return LocalDate.parse(arg0);
    }

    @Override
    public String objectToSQLString(Object arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toXMLString(Object arg0) {
        return arg0.toString();
    }

}