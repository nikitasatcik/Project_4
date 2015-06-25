package nikita.epam.project_4.model.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nikita.epam.project_4.model.dao.DAOFactory;

public abstract class Command {
	protected static DAOFactory daoFactory;
	
	public static void setDAOFactory(DAOFactory factory) {
		daoFactory = factory;
	}
	
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);
}