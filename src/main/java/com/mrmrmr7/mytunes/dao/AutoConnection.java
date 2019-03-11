package com.mrmrmr7.mytunes.dao;

import java.lang.annotation.*;

/**
 * Annotation for DAO methods which use the connection
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface  AutoConnection  {

}