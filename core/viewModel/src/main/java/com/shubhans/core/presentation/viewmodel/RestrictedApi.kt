package com.shubhans.core.presentation.viewmodel

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPEALIAS,
)
@Retention(AnnotationRetention.BINARY)
@RequiresOptIn(
    message = "This is API has been restricted. Do not depend on this API for working properly",
    level = RequiresOptIn.Level.ERROR,
)
public annotation class RestrictedApi