package base;

import static org.junit.Assert;

import domain.PersonDomainModel;

public class Person_Test {
	private static PersonDomainModel per1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Date dDate = null;
		try{
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1995-05-18");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		per1 = new PersonDomainModel();
		per1.setFirstName("Jamie");
		per1.setLastName("McKeon");
		per1.setBirthday(dDate);
		per1.setCity("Staten Island");
		per1.setPostalCode(10307);
		per1.setStreet("452 Manhattan Street);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This person should not be in the database", per);
	}

	@Test
	public void AddPersonTest() {
		PersonDomainModel per;
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This person should not be in the database", per);
		PersonDAL.addPerson(per1);

		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + "found");
		assertNotNull("This person should be in the database", per);
	}

	@Test
	public void UpdatePersonTest()
	{
		PersonDomainModel per;
		final String last_name = "Jones";
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This person should not be in the database",per);
		PersonDAL.addPerson(per1);
		per1.setLastName(last_name);
		PersonDAL.updatePerson(per1);
		per = PersonDAL.getPerson(per1.getPersonID());
		assertTrue("Name did not change,"per1.getLastName() == last_name);
	}

	@Test
	public void DeletePersonTest()
	{
		PersonDomainModel per;
		per= PersonDAL.getPerson(per1.getpersonID());
		assertNull("This person should not be in the database.", per);
		
		PersonDAL.addPerson(per1);
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + "found");
		assertNotNull("This person should have been added in the database,", per);
		
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This person should not be in the database," per);
	}
