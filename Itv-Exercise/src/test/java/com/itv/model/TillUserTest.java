package com.itv.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 21/12/17.
 */
public class TillUserTest {

    @Test
    public void testConstructor() {
        LocalDate dateOfBirth = LocalDate.now();

        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", dateOfBirth, "harry.osbourne@gmail.com", "username", "password");
        Assert.assertNotNull(tillUser);
        Assert.assertEquals(tillUser.getTitle(), Title.MR);
        Assert.assertEquals(tillUser.getFirstName(), "Harry");
        Assert.assertEquals(tillUser.getLastName(), "Osbourne");
        Assert.assertEquals(tillUser.getEmail(), "harry.osbourne@gmail.com");
        Assert.assertEquals(tillUser.getDateOfBirth(), dateOfBirth);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTitleNull() {

        new TillUser(null, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFirstNameNull() {

        new TillUser(Title.MR, null, "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFirstNameEmpty() {

        new TillUser(Title.MR, "", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLastNameNull() {

        new TillUser(Title.MR, "Harry", null, LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLastNameEmpty() {

        new TillUser(Title.MR, "Harry", "", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDOBNull() {

        new TillUser(Title.MR, "Harry", "Osbourne", null, "harry.osbourne@gmail.com", "username", "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmailNull() {

        new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), null, "username", "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmailEmpty() {

        new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "", "username", "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorUsernameNull() {

        new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", null, "password");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorUsernameEmpty() {

        new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "", "password");
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorPasswordNull() {
        new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", null);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorPasswordEmpty() {
        new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "");
        Assert.fail();
    }




    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {

        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Field idField = TillUser.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(tillUser, 1);

        Assert.assertEquals(tillUser.getId(), 1);
    }


    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {

        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Field versionField = TillUser.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(tillUser, 1);

        Assert.assertEquals(tillUser.getVersion(), 1);
    }


    @Test
    public void testEquals() {
        EqualsVerifier.forClass(TillUser.class)
                .usingGetClass()
                .verify();
    }

    @Test
    public void testToString() {
        TillUser tillUser1 = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        TillUser tillUser2 = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");

        Assert.assertTrue(tillUser1.toString().equals(tillUser2.toString()));
    }
}
