package com.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.project.dao.MyConnectionFactory;
import com.project.dao.ReimburDao;
import com.project.dao.UserDao;
import com.project.model.Reimbursment;
import com.project.model.User;

public class DaoTest {
	
	private static MyConnectionFactory conn;
	private static ReimburDao reimburDao;
	private static UserDao userDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn = new MyConnectionFactory("jdbc:h2:./testDBFolder/testData", "sa", "sa");
		userDao = new UserDao();
		reimburDao = new ReimburDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		conn.h2InitDao();
	}

	@After
	public void tearDown() throws Exception {
		conn.h2DestroyDao();
	}

	
	
	
	@Test
	public void selectByUsernameTest() {	
		User retrieved = userDao.selectByUsername("daviles");
		User retrieved2 = userDao.selectByUsername("admin");
		User retrieved3 = userDao.selectByUsername("elon");
		
		assertEquals("daviles", retrieved.getUsername());
		assertEquals("admin", retrieved2.getUsername());
		assertEquals("elon", retrieved3.getUsername());
	}
	
	
	@Test
	public void selectAllUsersTest() {
		
		List<User> retrievedUsers = userDao.selectAllUsers();
		
		User user1 = retrievedUsers.get(0);
		User user2 = retrievedUsers.get(1);
		User user3 = retrievedUsers.get(2);
		
		assertEquals(1, user1.getUserId());
		assertEquals("test", user1.getPw());
		assertEquals("Diego", user1.getFirstName());
		assertEquals("Aviles", user1.getLastName());
		assertEquals("davil022@fiu.edu", user1.getEmail());
		assertEquals(1, user1.getUserRole());
		
		assertEquals(2, user2.getUserId());
		assertEquals("admin", user2.getPw());
		assertEquals("Lebron", user2.getFirstName());
		assertEquals("James", user2.getLastName());
		assertEquals("jordanGOAT@gmail.com", user2.getEmail());
		assertEquals(2, user2.getUserRole());
		
		assertEquals(3, user3.getUserId());
		assertEquals("sus", user3.getPw());
		assertEquals("Elon", user3.getFirstName());
		assertEquals("Musket", user3.getLastName());
		assertEquals("tesla>edison@gmail.com", user3.getEmail());
		assertEquals(1, user3.getUserRole());
		
	}
	
	@Test
	public void selectAllReimbursTest() {
		List<Reimbursment> retrieveReimburs = reimburDao.selectAllReimburs();
		
		Reimbursment r1 = retrieveReimburs.get(0);
		Reimbursment r4 = retrieveReimburs.get(3);
		
		assertEquals(1, r1.getReimburId());
		assertEquals(17.38, r1.getAmount(), 0.1);
		assertEquals(Timestamp.valueOf("2020-12-11 11:14:00.0"), r1.getSubmitted());
		assertEquals("testing tables", r1.getDescription());

		assertEquals(4, r4.getReimburId());
		assertEquals(3899.99, r4.getAmount(), 0.1);
		assertEquals(Timestamp.valueOf("2020-12-23 12:14:00.0"), r4.getSubmitted());
		assertEquals("cybertruck", r4.getDescription());
	}
	
	@Test
	public void selectUserReimbursTest(){
		List<Reimbursment> specificUserReimburs = reimburDao.selectAllUserReimburs(userDao.selectByUsername("daviles"));
		
		Reimbursment userReimbur1 = specificUserReimburs.get(0);
		Reimbursment userReimbur2 = specificUserReimburs.get(1);
		Reimbursment userReimbur3 = specificUserReimburs.get(2);
		
		assertEquals(1, userReimbur1.getReimburId());
		assertEquals(2, userReimbur2.getReimburId());
		assertEquals(3, userReimbur3.getReimburId());
		assertEquals(3, specificUserReimburs.size());
	}
	
	@Test
	public void insertNewReimburTest() {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		reimburDao.insertNewReimbur(10.23, now, "testing insert", 3, 4);

		List<Reimbursment> specificUserReimburs = reimburDao.selectAllUserReimburs(userDao.selectByUsername("elon"));
		Reimbursment testInsert = specificUserReimburs.get(1);
		
		assertEquals(10.23, testInsert.getAmount(), 0.1);
		assertEquals(now, testInsert.getSubmitted());
		assertEquals("testing insert", testInsert.getDescription());
		assertEquals(3, testInsert.getAuthorFK());
		assertEquals(4, testInsert.getReimburType());
		assertEquals(5, testInsert.getReimburId());
		
	}
	
	@Test
	public void selectByStatusTest() {
		List<Reimbursment> pending = reimburDao.selectByStatus(1);
		assertEquals(4, pending.size());
		
		reimburDao.updateReimbur(Timestamp.valueOf(LocalDateTime.now()), 2, 3, 1);
		
		List<Reimbursment> pendingNow = reimburDao.selectByStatus(1);
		List<Reimbursment> denied = reimburDao.selectByStatus(3);
		
		assertEquals(3, pendingNow.size());
		assertEquals(1, denied.size());
	}
	
	@Test
	public void updateReimburTest() {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());

		List<Reimbursment> allReimburs = reimburDao.selectAllReimburs();
		Reimbursment preUpdate = allReimburs.get(0);
		
		assertNull(preUpdate.getResolved());
		assertEquals(0, preUpdate.getResolverFK());
		assertEquals(1, preUpdate.getReimburStatus());
		
		reimburDao.updateReimbur(now, 2, 2, 1);
		
		allReimburs = reimburDao.selectAllReimburs();
		Reimbursment postUpdate = allReimburs.get(0);
		
		assertEquals(now, postUpdate.getResolved());
		assertEquals(2, postUpdate.getResolverFK());
		assertEquals(2, postUpdate.getReimburStatus());
	}
	

}
