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
        private int id;

        private PersonItem(String surname, String name, String patronymic, String phoneNumber) {
            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.phoneNumber = phoneNumber;
            this.id = ++ID;
        }

        public int getId() {
            return id;
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
    }


    public class NoteActions {
        public void addNote(String... args) {
            String[] fields = {" ", " ", " ", " "};
            System.arraycopy(args, 0, fields, 0, args.length);
            personList.add(new PersonItem(fields[0], fields[1], fields[2], fields[3]));
        }

        /**
         *
         * @param index in the ArrayList
         * @return ref. of the PersonItem
         */
        public PersonItem getNote(int index) {
            return personList.get(index);
        }

        /** Search on ID
         *  @return  sequence number of item
         */
        public int searchItemOnID(int itemID) {
            int i;
            for (i = 0; i < getNoteSize(); i++) {
                if (itemID == getNote(i).getId()) {
                    break;
                }
            }
            return i;
        }

        public void removeItemOnID(int itemID) {
            personList.remove(searchItemOnID(itemID));
        }

        public int getNoteSize() {
            return personList.size();
        }
    }

    public NoteActions getActions() {
        return new NoteActions();
    }
}