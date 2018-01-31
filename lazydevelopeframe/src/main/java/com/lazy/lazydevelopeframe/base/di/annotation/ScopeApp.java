package com.lazy.lazydevelopeframe.base.di.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/29.
 *  desc   :
 *  modify :
 * </pre>
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ScopeApp {
}
