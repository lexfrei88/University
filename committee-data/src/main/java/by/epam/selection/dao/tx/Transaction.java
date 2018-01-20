package by.epam.selection.dao.tx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to make method on a service layer work in transaction mode
 *
 * {@see by.epam.selection.dao.tx.TxAspect}
 *
 * @author Alex Aksionchik 12/15/2017
 * @version 1.0
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Transaction {
}
