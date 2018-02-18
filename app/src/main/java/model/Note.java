package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igorr on 15.02.2018.
 */

public class Note {
    public static List<PersonItem> personList = new ArrayList<>();
    private static int ID = 0;

    public class PersonItem {
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

        public void setName(String name) {
            this.name = name;
        }
    }


    public class NoteActions {
        public void addNote(String... args) {
            String[] fields = {" ", " ", " ", " "};
            System.arraycopy(args, 0, fields, 0, args.length);
            personList.add(new PersonItem(fields[0], fields[1], fields[2], fields[3]));
        }

        public PersonItem getNote(int position) {
            return personList.get(position);
        }

        public void removeItem(int position) {
            personList.remove(position);
        }

        public int getNoteSize() {
            return personList.size();
        }

        public void overwriteItem(int position, String name) {
            if(name != null && !name.equals(" ")){
                personList.get(position).setName(name);
            }

        }
    }

    public NoteActions getActions() {
        return new NoteActions();
    }
}