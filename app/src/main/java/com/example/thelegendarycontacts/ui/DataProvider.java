package com.example.thelegendarycontacts.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Java로 작성된 더미 데이터 공급 클래스
 */
public class DataProvider {

    public static List<Contact> getDummyContacts() {
        List<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact("홍길동", "010-1234-5678", "Album A"));
        contacts.add(new Contact("이영희", "010-2345-6789", "Album B"));
        contacts.add(new Contact("김철수", "010-3456-7890", "Album C"));
        contacts.add(new Contact("박미나", "010-4567-8901", "Album D"));
        contacts.add(new Contact("최영수", "010-5678-9012", "Album E"));
        contacts.add(new Contact("박지성", "010-6789-0123", "Album F"));
        contacts.add(new Contact("손흥민", "010-7890-1234", "Album G"));

        return contacts;
    }
}
