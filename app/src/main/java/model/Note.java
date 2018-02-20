package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igorr on 15.02.2018.
 */

public class Note {
    private static List<PersonItem> personList = new ArrayList<>();

    public static class PersonItem {
        private String surname, name, patronymic, phoneNumber;

        private PersonItem(String surname, String name, String patronymic, String phoneNumber) {
            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getSurname() {
            return surname;
        }

        private void setName(String s) {
            name = s;
        }

        private void setSurname(String s) {
            surname = s;
        }

        private void setPatronymic(String s) {
            patronymic = s;
        }

        private void setPhoneNumber(String s) {
            phoneNumber = s;
        }
    }

    public static class Actions {
        public static void addNote(String[] args) {
            if (args != null) {
                personList.add(new PersonItem(args[0], args[1], args[2], args[3]));
            }
        }

        public static PersonItem getNote(int position) {
            return personList.get(position);
        }

        public static void removeItem(int position) {
            personList.remove(position);
        }

        public static int getNoteSize() {
            return personList.size();
        }

        public static void overwriteItem(int position, String[] args) {
            if (args != null) {
                personList.get(position).setSurname(args[0]);
                personList.get(position).setName(args[1]);
                personList.get(position).setPatronymic(args[2]);
                personList.get(position).setPhoneNumber(args[3]);
            }
        }
    }
}