package nikita.epam.project_4.model.dao;

public class SQLDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new SQLUserDAO();
	}

	@Override
	public TourDAO getTourDAO() {
		return new SQLTourDAO();
	}
}
