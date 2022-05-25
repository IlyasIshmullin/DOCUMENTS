package com.example.documents_rewrite.mainApplication.data.remote.document

import javax.inject.Qualifier

class ClientAnnotation {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DocumentClient
}