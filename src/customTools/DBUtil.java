package customTools;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class DBUtil
{
	private static final EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("BullhornAssignment");
	
	public static EntityManagerFactory getEmFactory()
	{
		return emf;
	}
}
