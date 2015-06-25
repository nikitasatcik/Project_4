package nikita.epam.project_4.model.dao;

public abstract class DAOFactory {

	public abstract UserDAO getUserDAO();
	public abstract TourDAO getTourDAO();
	
	public static DAOFactory getDAOFactory(String factoryName) {
		switch (factoryName) {
		case "MYSQL": return new SQLDAOFactory();
		default: return null;
		}
	}
	
}
