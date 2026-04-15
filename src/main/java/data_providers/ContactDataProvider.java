package data_providers;

import dto.Contact;
import org.testng.annotations.DataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.ContactFactory.positiveContact;

public class ContactDataProvider {

    private static final Logger logger = LoggerFactory.getLogger(ContactDataProvider.class);

    @DataProvider
    public Iterator<Contact> dataProviderFromFile() {
        List<Contact> list = new ArrayList<>();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader("src/main/resources/data_csv/data_contacts.csv"))) {

            logger.info("Reading contacts from data_contacts.csv");

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                list.add(Contact.builder()
                        .name(splitArray[0])
                        .lastName(splitArray[1])
                        .email(splitArray[2])
                        .phone(splitArray[3])
                        .address(splitArray[4])
                        .description(splitArray[5])
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException exception) {
            logger.error("Failed to read data_contacts.csv", exception);
            throw new RuntimeException("IO exception", exception);
        }
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Contact> dataProviderFromFileWrongPhone() {
        List<Contact> list = new ArrayList<>();
        Contact contact = positiveContact();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader("src/main/resources/data_csv/dp_wrong_phone.csv"))) {

            logger.info("Reading wrong phone data from dp_wrong_phone.csv");

            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(Contact.builder()
                        .name(contact.getName())
                        .lastName(contact.getLastName())
                        .email(contact.getEmail())
                        .phone(line)
                        .address(contact.getAddress())
                        .description(contact.getDescription())
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException exception) {
            logger.error("Failed to read dp_wrong_phone.csv", exception);
            throw new RuntimeException("IO exception", exception);
        }
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Contact> dataProviderFromFile_WrongPhone() {
        List<Contact> list = new ArrayList<>();
        Contact contact = positiveContact();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader("src/test/resources/data_csv/dp_wrong_phone.csv"))) {

            logger.info("Reading wrong phone data from test/resources dp_wrong_phone.csv");

            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(Contact.builder()
                        .name(contact.getName())
                        .lastName(contact.getLastName())
                        .email(contact.getEmail())
                        .phone(line)
                        .address(contact.getAddress())
                        .description(contact.getDescription())
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            logger.error("Failed to read dp_wrong_phone.csv from test/resources", e);
            throw new RuntimeException("IO exception", e);
        }
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Contact> dataProviderFromFile_Wrong_EmptyField() {
        List<Contact> list = new ArrayList<>();
        Contact contact = positiveContact();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader("src/main/resources/data_csv/dp_empty_field.csv"))) {

            logger.info("Reading empty field data from dp_empty_field.csv");

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                list.add(Contact.builder()
                        .name(splitArray[0])
                        .lastName(splitArray[1])
                        .email(contact.getEmail())
                        .phone(contact.getPhone())
                        .address(splitArray[2])
                        .description(contact.getDescription())
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException exception) {
            logger.error("Failed to read dp_empty_field.csv", exception);
            throw new RuntimeException("IO exception", exception);
        }
        return list.listIterator();
    }
}