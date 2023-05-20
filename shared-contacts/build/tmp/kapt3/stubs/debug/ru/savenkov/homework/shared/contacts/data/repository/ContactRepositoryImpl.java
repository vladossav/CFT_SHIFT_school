package ru.savenkov.homework.shared.contacts.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\n\u001a\u00020\u0006H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000eH\u0016J\u0019\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lru/savenkov/homework/shared/contacts/data/repository/ContactRepositoryImpl;", "Lru/savenkov/homework/shared/contacts/domain/repository/ContactRepository;", "db", "Lru/savenkov/homework/shared/contacts/data/datasource/ContactDao;", "(Lru/savenkov/homework/shared/contacts/data/datasource/ContactDao;)V", "addContact", "", "contact", "Lru/savenkov/homework/shared/contacts/data/model/Contact;", "(Lru/savenkov/homework/shared/contacts/data/model/Contact;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "getAll", "Lkotlinx/coroutines/flow/Flow;", "", "updateContact", "shared-contacts_debug"})
public final class ContactRepositoryImpl implements ru.savenkov.homework.shared.contacts.domain.repository.ContactRepository {
    private final ru.savenkov.homework.shared.contacts.data.datasource.ContactDao db = null;
    
    public ContactRepositoryImpl(@org.jetbrains.annotations.NotNull()
    ru.savenkov.homework.shared.contacts.data.datasource.ContactDao db) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object addContact(@org.jetbrains.annotations.NotNull()
    ru.savenkov.homework.shared.contacts.data.model.Contact contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object updateContact(@org.jetbrains.annotations.NotNull()
    ru.savenkov.homework.shared.contacts.data.model.Contact contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object deleteContact(@org.jetbrains.annotations.NotNull()
    ru.savenkov.homework.shared.contacts.data.model.Contact contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<java.util.List<ru.savenkov.homework.shared.contacts.data.model.Contact>> getAll() {
        return null;
    }
}