package challenge;

// import Message;
import java.util.ArrayList;
import java.util.Scanner;

public class MyClass {
    private static ArrayList<Contact> contacts;
    private static Scanner scanner;

    private static int id = 0;

    public static void main(String[] args) {

        contacts = new ArrayList<>();
        showInitialOptions();

    }

    private static void showInitialOptions() {
        System.out.println("Please select one:" +
                "\n\t1. Manage Contacts" +
                "\n\t2. Messages" +
                "\n\t3. Quit");
        scanner = new Scanner(System.in);
        // Message message = new Message(null, null, id);
        
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            case 3:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                showInitialOptions();
        }
    }

    private static void manageContacts() {
        System.out.println("Please select one:" +
                "\n\t1. Show all contacts" +
                "\n\t2. Add a new contact" +
                "\n\t3. Search for a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Go back");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showAllContacts();

                break;
            case 2:
                addNewContact();

                break;
            case 3:
                searchForContact();

                break;
            case 4:
                deleteContact();

                break;
            case 5:
                showInitialOptions();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                manageContacts();
        }
    }

    private static void manageMessages() {
        System.out.println("Please select one:" +
                "\n\t1. Show all messages" +
                "\n\t2. Send a new message" +
                "\n\t3. Go back");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showAllMessages();

                break;
            case 2:
                sendNewMessage();

                break;
            case 3:
                showInitialOptions();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                manageMessages();
        }
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c : contacts) {
            allMessages.addAll(c.getMessages());
        }

        if (allMessages.size() > 0) {
            for (Message m : allMessages) {
                m.getDetails();
                System.out.println("---------------");
            }
        } else {
            System.out.println("No messages to show.");
        }

    }

    private static void sendNewMessage() {
        System.out.println("Please enter the name of the recipient:");
        String recipient = scanner.next();
        if (recipient.equals("")) {
            System.out.println("Recipient can't be empty. Please try again.");
            sendNewMessage();
        }

        else {
            boolean doesExist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(recipient)) {
                    doesExist = true;
                    break;
                }
            }
            if (!doesExist) {
                System.out.println("Recipient not found. Please try again.");
                sendNewMessage();
            } else {
                System.out.println("Please enter the message:");
                String text = scanner.next();
                if (text.equals("")) {
                    System.out.println("Message can't be empty. Please try again.");
                    sendNewMessage();
                } else {
                    id++;
                    Message newMessage = new Message(text, recipient, id);
                    for (Contact c : contacts) {
                        if (c.getName().equals(recipient)) {
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);

                            Contact currentContact = c;
                            currentContact.setMessages(newMessages);
                            contacts.remove(c);
                            contacts.add(currentContact);
                        }
                    }
                    System.out.println("Message sent successfully!");
                    showInitialOptions();
                }
            }
        }
    }

    private static void deleteContact() {
        System.out.println("Please enter the name of the contact you want to delete:");
        String name = scanner.next();
        if (name.equals("")) {
            System.out.println("Name can't be empty. Please try again.");
            deleteContact();
        }

        boolean found = false;
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                contacts.remove(c);
                System.out.println("Contact deleted successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }

        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("Please enter the name of the contact:");
        String name = scanner.next();
        if (name.equals("")) {
            System.out.println("Name can't be empty. Please try again.");
            searchForContact();
        }

        boolean found = false;
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                c.getDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }

        showInitialOptions();
    }

    private static void showAllContacts() {
        for (Contact c : contacts) {
            c.getDetails();
            System.out.println("---------------");
        }
        showInitialOptions();
    }

    private static void addNewContact() {
        System.out.println("Please enter the name of the new contact:");
        String name = scanner.next();
        System.out.println("Please enter the phone number of the new contact:");
        String number = scanner.next();
        System.out.println("Please enter the email of the new contact:");
        String email = scanner.next();
        // empty checks
        if (name.isEmpty() || number.isEmpty() || email.isEmpty()) {
            System.out.println("Name, number and email can't be empty. Please try again.");
            addNewContact();
        }
        // check if the contact already exists
        else {
            boolean doesExist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    break;
                }
            }
            if (doesExist) {
                System.out.println("Contact with the same name already exists. Please try again.");
                addNewContact();
            } else {
                Contact newContact = new Contact(name, number, email);
                contacts.add(newContact);
                System.out.println("New contact added successfully!");
                showInitialOptions();
            }
        }
    }
}
